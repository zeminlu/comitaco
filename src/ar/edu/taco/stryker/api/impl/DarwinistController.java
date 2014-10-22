package ar.edu.taco.stryker.api.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.multijava.mjc.JCompilationUnitType;

import ar.edu.jdynalloy.JDynAlloySemanticException;
import ar.edu.taco.TacoAnalysisResult;
import ar.edu.taco.TacoConfigurator;
import ar.edu.taco.TacoMain;
import ar.edu.taco.TacoNotImplementedYetException;
import ar.edu.taco.engine.JUnitStage;
import ar.edu.taco.engine.SnapshotStage;
import ar.edu.taco.engine.StrykerStage;
import ar.edu.taco.jml.parser.JmlParser;
import ar.edu.taco.junit.RecoveredInformation;
import ar.edu.taco.stryker.api.impl.MuJavaController.MsgDigest;
import ar.edu.taco.stryker.api.impl.input.DarwinistInput;
import ar.edu.taco.stryker.api.impl.input.MuJavaFeedback;
import ar.edu.taco.stryker.api.impl.input.MuJavaInput;
import ar.edu.taco.stryker.api.impl.input.OpenJMLInput;
import ar.edu.taco.utils.FileUtils;
import ar.uba.dc.rfm.dynalloy.analyzer.AlloyAnalysisResult;

import com.google.common.collect.Lists;
import com.google.common.io.Files;
// to be removed later when editFile is replaced in this class.

public class DarwinistController extends AbstractBaseController<DarwinistInput> {

    private static Logger log = Logger.getLogger(DarwinistController.class);

    private static DarwinistController instance;

    private List<String> resolvedBugs = Lists.newArrayList();

    public static final String PATH_SEP = System.getProperty("path.separator");
    public static final String FILE_SEP = System.getProperty("file.separator");
    public static final String MUTANTS_DEST_PACKAGE = "ar.edu.itba.stryker.mutants";

    synchronized static DarwinistController getInstance() {
        if(instance == null) {
            instance = new DarwinistController();
        }
        return instance;
    }

    ExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    private Thread runningThread = null;

    private DarwinistController() {

    }

    @Override
    protected Runnable getRunnable() {
        return new Runnable() {

            @Override
            public void run() {
                while (!willShutdown.get()) {
                    DarwinistInput input = null;
                    try {
                        input = queue.take();
                        log.debug("Queue size: "+queue.size());

                        if (input.isForSeqProcessing()) {
                            computateFeedback(input);
                        } else {
                            validateCandidate(input);
                        }
                    } catch (InterruptedException e) {
                        //                      e.printStackTrace();
                    } catch (Exception e) {
                        log.debug("Exception e: "+ e.getLocalizedMessage());
                        e.printStackTrace();
                    } finally {
                        log.debug("Entering finally");
                        if ((!(input.isForSeqProcessing() != null) || !input.isForSeqProcessing()) 
                                && input != null && input.getOriginalFilename() != null) {
                            log.debug("Inside the if of finally");
                            String originalFilename = input.getOriginalFilename();
                            File originalFile = new File(originalFilename);

                            File newFile = new File(originalFilename+"_temp");

                            originalFile.delete();

                            try {
                                log.debug("Restoring file: "+originalFile);
                                Files.copy(newFile, originalFile);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        };
    }
    private void variablize(TacoMain tacoMain, VariablizationData vdata, DarwinistInput input) throws IOException {
        TacoAnalysisResult analysis_result = null;
        AlloyAnalysisResult analysisResult = null;
        boolean reachedUnvariablizableExpression = false;
        boolean notCompilable = false;

        String filename = input.getSeqVariablizedFilename();
        String originalFilename = input.getOriginalFilename();

        File originalFile = new File(originalFilename);

        File newFile = new File(originalFilename+"_temp");
        newFile.createNewFile();

        Files.copy(originalFile, newFile);

        final String configurationFile = input.getConfigurationFile();
        final Properties props = new Properties(input.getOverridingProperties());
        Properties oldProps = input.getOverridingProperties();
        for(Entry<Object,Object> o : oldProps.entrySet()){
            props.put(o.getKey(), o.getValue());
        }
        props.put("attemptToCorrectBug",false);
        props.put("generateUnitTestCase",false);

        while (analysisResult == null || analysisResult.isUNSAT()) {

            //Analizar con TACO el metodo actual, previa variabilizacion
            //Los que dan SAT, avisarle a MuJavaController (estoy haciendo CHECK)
            //Los que que dan UNSAT, a variabilizar (estoy haciendo CHECK)
            if (!vdata.variablizeNext(input)) {
                //No hay mas que variabilizar, no tiene solucion
                //                                    System.out.println("No hay solucion");
                reachedUnvariablizableExpression = true;
                break;
            }

            File newTestFile = new File(filename);
            newFile.createNewFile();

            Files.copy(newTestFile, originalFile);


            String currentClasspath = System.getProperty("java.class.path");
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            long nanoPrev = System.currentTimeMillis();
            int compilationResult = compiler.run(null, new NullOutputStream(), new NullOutputStream(), 
                    new String[]{"-classpath", currentClasspath, originalFilename});
            StrykerStage.compilationMillis += System.currentTimeMillis() - nanoPrev;
            /**/                    compiler = null;
            if(compilationResult == 0){
                log.debug("Compilation is successful: "+filename);
                //                                    System.out.println("Por arrancar TACO...");
                nanoPrev = System.currentTimeMillis();
                if (VariablizedSATVerdicts.getInstance().containsFile(newTestFile)) {
                    analysis_result = VariablizedSATVerdicts.getInstance().get(newTestFile);
                    System.out.println("SAVED ONE SAT CHECK");
                } else {    
                    try {
                        analysis_result = tacoMain.run(configurationFile, props);
                        VariablizedSATVerdicts.getInstance().put(newTestFile, analysis_result);
                        System.out.println("STORING A NEW VARIABLIZATION SAT CHECK");

                    } catch (JDynAlloySemanticException e) {
                        System.out.println("TACO dio JDynAlloySemanticException, asumo no compila y salteo");
                        notCompilable = true;
                        break;
                    } catch (TacoNotImplementedYetException e) {
                        System.out.println("TACO dio TacoNotImplementedYetException, asumo no compila y salteo");
                        notCompilable = true;
                        break;
                    } catch (Exception e) {
                        System.out.println("Error desconocido en TACO.");
                        e.printStackTrace();
                    }
                }
                StrykerStage.tacoMillis += System.currentTimeMillis() - nanoPrev;
                analysisResult = analysis_result.get_alloy_analysis_result();
            } else {
                //Hubo error de compilacion
                notCompilable = true;
                break;
            }
            if (analysisResult.isUNSAT()) {
                System.out.println("Dio UNSAT");
                if (!vdata.isLastVariablizedMutIDRight()) {
                    break;
                }
            }
        }

        log.debug("Restoring file: "+originalFile);
        Files.copy(newFile, originalFile);
        vdata.setUncompilable(notCompilable);
        vdata.setReachedUnvariablizableExpression(reachedUnvariablizableExpression);
        if (analysisResult != null) {
            vdata.setUNSAT(analysisResult.isUNSAT());
        }
    }

    private TacoMain getTacoMainWithFixedInput(String junitFilename) throws IllegalArgumentException {
        Class<?>[] inputs = StrykerStage.junitInputs;
        int index = 0;
        if (junitFilename != null){
            String location = System.getProperty("user.dir") + System.getProperty("file.separator") + 
                    "generated" + System.getProperty("file.separator");
            while (index < StrykerStage.indexToLastJUnitInput && inputs[index] != null && 
                    !((location + (inputs[index].getName())
                            .replace(".", System.getProperty("file.separator")))
                            .replace("output", "generated")+".java").equals(junitFilename)){
                index++;
            }
            if (index >= inputs.length || inputs[index] == null)
                throw new IllegalArgumentException("File name does not correspond to any stored input! Broken invariant!");
        } else {
            index = 0;
        }
        Class<?> claz = inputs[index];
        Object o1;
        try {
            o1 = claz.getConstructor((Class<?>[])null).newInstance();
            Field fi = claz.getDeclaredField("theData");
            @SuppressWarnings("unchecked")
            HashMap<String, Object> o2 = (HashMap<String,Object>)fi.get(o1);
            return new TacoMain(o2);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException
                | NoSuchMethodException | SecurityException | NoSuchFieldException e) {
            //TODO manage exceptions
            return null;
        }

    }
    private void computateFeedback(DarwinistInput input) {
        final String oldFilename = input.getFilename();

        final String seqFileName = oldFilename.replaceFirst(
                oldFilename.split("/")[oldFilename.split("/").length - 1], "sequential/" +
                        oldFilename.split("/")[oldFilename.split("/").length - 1]);

        try {
            input.setSeqFilesPrefix(seqFileName);
            FileUtils.writeToFile(input.getSeqFilesPrefix(), FileUtils.readFile(input.getFilename())); //copiar de filename a seqfilesprefix
            StrykerJavaFileInstrumenter.insertMutIDs(input);
            LoopUnrollTransformation.javaUnroll(TacoConfigurator.getInstance().getDynAlloyToAlloyLoopUnroll(), 
                    input.getMethod(), input.getSeqFilesPrefix(), input.getSeqFilesPrefix());
        } catch (IOException e) {
            System.out.println("Alto problema unrolleando");
            e.printStackTrace();
        }

        VariablizationData vdata = VariablizationData.preprocessVariabilization2(input);
        boolean unvariablizable = !vdata.isVariablizable();

        input.setSeqVariablizedFilename(input.getSeqFilesPrefix());

        TacoMain tacoMain = getTacoMainWithFixedInput(input.getSeqMethodInput());

        if (!unvariablizable) {
            try {
                variablize(tacoMain, vdata, input);
            } catch (IOException e) {
                //TODO what now?
                e.printStackTrace();
            }
        }

        MuJavaInput mujavainput = new MuJavaInput(input.getFilename(), input.getMethod(), 
                input.getMutantsToApply(), new AtomicInteger(0), input.getConfigurationFile(), 
                input.getOverridingProperties(), input.getOriginalFilename(), input.getSyncObject());
        mujavainput.setOldFilename(input.getOldFilename());
        MuJavaFeedback feedback = input.getFeedback();
        if (vdata.getReachedUnvariablizableExpression()) {
            feedback.setGetSibling(true);
            if (vdata.isLastVariablizedMutIDRight() != null) {
                feedback.setMutateRight(vdata.isLastVariablizedMutIDRight());
            } else {
                feedback.setMutateRight(true);
            }
            if (vdata.getLastVariablizedMutID() != null) {
                feedback.setSkipUntilMutID(vdata.getLastVariablizedMutID() - 1);
            } else {
                feedback.setSkipUntilMutID(input.getFeedback().getLineMutationIndexes().length - 1);
            }
            feedback.setUNSAT(true);
            if (MuJavaController.fatherizationPruningOn) {
                if (vdata.isStillFatherable() != null) {
                    feedback.setFatherable(vdata.isStillFatherable());
                } else {
                    feedback.setFatherable(true);
                }
            } else {
                feedback.setFatherable(true);
            }
        } else if (unvariablizable) {
            feedback.setFatherable(false);
            feedback.setGetSibling(true);
            feedback.setSkipUntilMutID(null);
            feedback.setMutateRight(true);

        } else if (vdata.isUncompilable()) {
            feedback.setFatherable(true);
            feedback.setGetSibling(true);
            feedback.setSkipUntilMutID(null);
            feedback.setMutateRight(true);
        } else {
            feedback.setGetSibling(true);
            feedback.setMutateRight(vdata.isLastVariablizedMutIDRight());
            feedback.setSkipUntilMutID(vdata.getLastVariablizedMutID() - 1);
            feedback.setUNSAT(vdata.isUNSAT());
            if (MuJavaController.fatherizationPruningOn) {
                feedback.setFatherable(vdata.isStillFatherable());
            } else {
                feedback.setFatherable(true);
            }
        }
        mujavainput.setMuJavaFeedback(feedback);
        MuJavaController.getInstance().enqueueTask(mujavainput);
    }

    private void validateCandidate(DarwinistInput input) {
        TacoMain tacoMain = new TacoMain(null);

        String filename;
        String originalFilename;

        filename = input.getFilename();
        originalFilename = input.getOriginalFilename();

        final String configurationFile = input.getConfigurationFile();
        final Properties props = new Properties(input.getOverridingProperties());
        Properties oldProps = input.getOverridingProperties();
        for(Entry<Object,Object> o : oldProps.entrySet()){
            props.put(o.getKey(), o.getValue());
        }

        File originalFile = new File(originalFilename);
        //                      originalFile.delete();

        File newFile = new File(originalFilename+"_temp");
        try {
            newFile.createNewFile();
            Files.copy(originalFile, newFile);

            File newTestFile = new File(filename);
            newTestFile.createNewFile();

            Files.copy(newTestFile, originalFile);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        String currentClasspath = System.getProperty("java.class.path");
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        long nanoPrev = System.currentTimeMillis();
        int compilationResult = compiler.run(null, new NullOutputStream(), new NullOutputStream(), 
                new String[]{"-classpath", currentClasspath, originalFilename});
        StrykerStage.compilationMillis += System.currentTimeMillis() - nanoPrev;
        compiler = null;
        if(compilationResult == 0){
            log.debug("Compilation is successful: "+filename);
            props.put("attemptToCorrectBug",false);
            props.put("generateUnitTestCase",false);
            TacoAnalysisResult analysis_result = null;
            boolean compiles = true;
            try {
                nanoPrev = System.currentTimeMillis();
                analysis_result = tacoMain.run(configurationFile, props);
                StrykerStage.tacoMillis += System.currentTimeMillis() - nanoPrev;
            } catch (JDynAlloySemanticException e) {
                System.out.println("TACO dio JDynAlloySemanticException, asumo no compila y salteo");
//                e.printStackTrace();
                compiles = false;
            } catch (TacoNotImplementedYetException e) {
                System.out.println("TACO dio TacoNotImplementedYetException, asumo no compila y salteo");
//                e.printStackTrace();
                compiles = false;
            } catch (Exception e) {
                System.out.println("Error desconocido en TACO.");
                e.printStackTrace();
                compiles = false;
            }

            if (!compiles) {
                MuJavaInput mujavainput = new MuJavaInput(input.getFilename(), input.getMethod(), 
                        input.getMutantsToApply(), new AtomicInteger(0), input.getConfigurationFile(), 
                        input.getOverridingProperties(), input.getOriginalFilename(), input.getSyncObject());

                MuJavaFeedback prevFeedback = input.getFeedback();

                prevFeedback.setFatherable(true);
                prevFeedback.setGetSibling(true);
                prevFeedback.setSkipUntilMutID(null);
                prevFeedback.setMutateRight(true);
                mujavainput.setMuJavaFeedback(prevFeedback);

                MuJavaController.getInstance().enqueueTask(mujavainput);
                return;
            } 

            AlloyAnalysisResult analysisResult = analysis_result.get_alloy_analysis_result();
            if(analysisResult == null || analysisResult.isSAT()) {
                log.debug(filename + " didn't solve the problem");

                log.debug("Valor 1: " + (StrykerStage.indexToLastJUnitInput - 1));
                log.debug("Valor 2: " + StrykerStage.indexToLastJUnitInput);

                if (analysisResult.isSAT()){
                    queueFalseCandidate(input, analysis_result, props);
                }
            } else {
                if (analysisResult.isUNSAT()){
                    lastRACAnalysis(input);
                }
            }   
        }else{
            log.info("Compilation Failed");
        }
    }

    public void queueFalseCandidate(DarwinistInput input, TacoAnalysisResult analysis_result, Properties props) {
        StrykerStage.falseCandidates++;
        String classToCheck = TacoConfigurator.getInstance().getString(TacoConfigurator.CLASS_TO_CHECK_FIELD);
        String methodToCheck = props.getProperty(TacoConfigurator.METHOD_TO_CHECK_FIELD);

        String junitFile = generateJunitFile(analysis_result, classToCheck, methodToCheck);

        try {

            log.debug("In effect, junit test generation was successful");

            if (MuJavaController.feedbackOn) {

                final Properties props2 = new Properties();
                Properties oldProps2 = input.getOverridingProperties();
                for(Entry<Object,Object> o : oldProps2.entrySet()){
                    if(o.getKey().equals("attemptToCorrectBug")) {
                        props2.put(o.getKey(), "false");
                    } else if (o.getKey().equals("generateUnitTestCase")) {
                        props2.put(o.getKey(), "false");
                    } else if (o.getKey().equals("generateCheck")) {
                        props2.put(o.getKey(), "true");
                    } else if (o.getKey().equals("generateRun")) {
                        props2.put(o.getKey(), "false");
                    } else if (o.getKey().equals("methodToCheck")) {
                        props2.put(o.getKey(), input.getMethod() + "_0");
                    } else {
                        props2.put(o.getKey(), o.getValue());
                    }
                }
                //TODO Ver si el primer argumento no tiene que ser filename
                DarwinistInput darwinistInput = new DarwinistInput(
                        input.getFilename(), 
                        input.getOriginalFilename(), 
                        input.getConfigurationFile(), 
                        input.getMethod(), 
                        props2, 
                        null, 
                        null,
                        true, 
                        input.getMethod(),
                        junitFile,
                        null,
                        null,
                        null,
                        input.getFeedback(),
                        input.getMutantsToApply(),
                        input.getSyncObject()
                        );
                darwinistInput.setRacMethod(input.getRacMethod());
                DarwinistController.getInstance().enqueueTask(darwinistInput);
                StrykerStage.mutationsQueuedToDarwinistForSeq++;                                                

            } else {
                MuJavaInput mujavainput = new MuJavaInput(input.getFilename(), 
                        input.getMethod(), input.getMutantsToApply(), 
                        new AtomicInteger(0), input.getConfigurationFile(), 
                        input.getOverridingProperties(), input.getOriginalFilename(), 
                        input.getSyncObject());
                MuJavaFeedback feedback = input.getFeedback();
                feedback.setFatherable(true);
                feedback.setGetSibling(true);
                feedback.setMutateRight(true);
                mujavainput.setMuJavaFeedback(feedback);
                MuJavaController.getInstance().enqueueTask(mujavainput);
            }
        } catch (IllegalArgumentException e) {
            //                                              e.printStackTrace();
        } catch (Exception e) {
            //                                              e.printStackTrace();
        }

    }

    @SuppressWarnings("resource")
    private String generateJunitFile(TacoAnalysisResult analysis_result, String classToCheck, String methodToCheck) {
        String junitFile = null;


        List<JCompilationUnitType> compilation_units = JmlParser.getInstance().getCompilationUnits();

        SnapshotStage snapshotStage = new SnapshotStage(
                compilation_units, analysis_result, classToCheck, methodToCheck);
        snapshotStage.execute();


        RecoveredInformation recoveredInformation = snapshotStage.getRecoveredInformation();
        recoveredInformation.setFileNameSuffix(StrykerStage.fileSuffix);


        JUnitStage jUnitStage = new JUnitStage(recoveredInformation);
        jUnitStage.execute();

        junitFile = jUnitStage.getJunitFileName();

        String currentJunit = null;

        String tempFilename = junitFile.substring(0, junitFile.lastIndexOf(FILE_SEP)+1) /*+ FILE_SEP*/; 
        String packageToWrite = "ar.edu.output.junit";
        String fileClasspath = tempFilename.substring(0, tempFilename.lastIndexOf(
                new String("ar.edu.generated.junit").replaceAll("\\.", FILE_SEP)));
        fileClasspath = fileClasspath.replaceFirst("generated", "output");
        currentJunit = TacoMain.editTestFileToCompile(junitFile, classToCheck, packageToWrite, methodToCheck);

        File[] file1 = new File[]{new File(currentJunit)};
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = javaCompiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> compilationUnit1 =
                fileManager.getJavaFileObjectsFromFiles(Arrays.asList(file1));
        javaCompiler.getTask(null, fileManager, null, null, null, compilationUnit1).call();
        try {
            fileManager.close();
        } catch (IOException e1) {
            // TODO: Define what to do!
            e1.printStackTrace();
        }
        javaCompiler = null;
        file1 = null;
        fileManager = null;

        //                                      if(compilationResult == 0) {
        log.debug("junit counterexample compilation succeded");
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        ClassLoader cl2;
        try {
            cl2 = new URLClassLoader(new URL[]{new File(fileClasspath).toURI().toURL()}, cl);
            //                                      ClassLoaderTools.addFile(fileClasspath);
            String classToLoad = packageToWrite+"."+TacoMain.obtainClassNameFromFileName(junitFile);
            Class<?> clazz = cl2.loadClass(classToLoad);
            cl = null;
            cl2 = null;
            //                                          log.warn("The class just stored is: "+clazz.getName());
            log.info("preparing to store a test class... "+packageToWrite+"." + 
                    MuJavaController.obtainClassNameFromFileName(junitFile));
            //                                          Result result = null;
            //                                          final Object oToRun = clazz.newInstance();
            DigestOutputStream dos;
            File duplicatesTempFile = null;
            String content = null;
            try {
                content = FileUtils.readFile(junitFile);
            }
            catch (Exception e) {
                throw new IllegalArgumentException("invalid or null file");
            }
            try {
                duplicatesTempFile = File.createTempFile("forDuplicatesJunit", null);
                dos = new DigestOutputStream(new FileOutputStream(duplicatesTempFile, false), MessageDigest.getInstance("MD5"));
                dos.write(content.getBytes());
                dos.flush();
                dos.close();
            }
            catch (Exception e) {
                throw new IllegalArgumentException("exception thrown while trying to compute digest in class VariablizedSATVerdicts");
            }
            byte[] digest = dos.getMessageDigest().digest();
            MsgDigest msgDigest = new MsgDigest(digest);
            if (!StrykerStage.junitFilesHash.containsKey(msgDigest)) {
                StrykerStage.junitFilesHash.put(msgDigest, junitFile);
                StrykerStage.junitInputs[StrykerStage.indexToLastJUnitInput] = clazz;
                StrykerStage.junitFiles[StrykerStage.indexToLastJUnitInput] = junitFile;
                StrykerStage.indexToLastJUnitInput++;
            } else {
                new File(junitFile).delete();
                junitFile = StrykerStage.junitFilesHash.get(msgDigest);
            }
        } catch (MalformedURLException e1) {
            // TODO: Define what to do!
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            // TODO: Define what to do!
            e1.printStackTrace();
        }

        return junitFile;
    }

    private void lastRACAnalysis(DarwinistInput input) {
        //candidate solution found. We will now check all stored inputs and if the candidate passes all, then it becomes a FIX. 
        //Otherwise, we discard this fix candidate.

        log.warn("U  U  N   N  SSSS  AAAA  TTTTT");
        log.warn("U  U  NN  N  S     A  A    T");
        log.warn("U  U  N N N  SSSS  AAAA    T");
        log.warn("U  U  N  NN     S  A  A    T");
        log.warn("UUUU  N   N  SSSS  A  A    T");

        Class<?>[] junitInputs = StrykerStage.junitInputs;
        //                                        String junitFiles[] = StrykerStage.junitFiles;

        final Object[] inputToInvoke = input.getParametersFromOpenJML();
        boolean failed = false;
        for (int i = 0; i < junitInputs.length && junitInputs[i] != null && !failed; i++){

            Class<?> clazz = junitInputs[i];
            Method[] methods = clazz.getMethods();

            Method methodToRun = null;
            for(Method m : methods) {
                if(m.isAnnotationPresent(Test.class)) {
                    methodToRun = m;
                }
            }
            final Method methodToRunInCallable = methodToRun; 
            log.debug("methodToRunInCallable: "+methodToRunInCallable);
            methodToRunInCallable.setAccessible(true);
            Long nanoPrev = System.currentTimeMillis();
            try {
                final Object oToRun = clazz.newInstance();
                final String methodName = input.getMethod();
                log.debug("methodName: " + methodName);
                Callable<Boolean> task = new Callable<Boolean>() {
                    public Boolean call() {
                        boolean failed = true;
                        try {
                            runningThread = Thread.currentThread();
                            //                                                      log.debug("fileClasspath :" + fileClasspath);
                            //                                                      log.debug("qualifiedName :" + qualifiedName);
                            //                                                      log.debug("methodName :" + methodName);
                            //                                                      Object[] inputToInvoke = new Object[]{fileClasspath, qualifiedName, methodName};
                            long timeprev = System.currentTimeMillis();
                            methodToRunInCallable.invoke(oToRun, inputToInvoke);
                            long timepost = System.currentTimeMillis();
                            failed = false;
                            log.debug("time taken: "+(timepost - timeprev));
                        } catch (IllegalAccessException e) {
                            log.debug("Entered IllegalAccessException");
                            failed = true;
                            //                                                      e.printStackTrace();
                        } catch (IllegalArgumentException e) {
                            log.debug("Entered IllegalArgumentException");
                            failed = true;
                            //                                                      e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            log.debug("Entered InvocationTargetException");
                            //                                                    e.printStackTrace();  
                            log.warn("FIX CANDIDATE QUIT BECAUSE OF JML RAC");
                            failed = true;
                        } catch (Throwable e) {
                            log.debug("Entered throwable");
                            //                                                      e.printStackTrace();
                            failed = true;
                        }
                        return failed;
                    }
                };

                Future<Boolean> future = executor.submit(task);
                failed = (Boolean)future.get(250, TimeUnit.MILLISECONDS);
            } catch (InstantiationException | IllegalAccessException e1) {
                // TODO: Define what to do!
                e1.printStackTrace();
            } catch (TimeoutException ex) {
                failed = true;
                log.warn("timeouted file: "+input.getFilename());
                runningThread.stop();
                executor.shutdownNow();
                executor = Executors.newSingleThreadExecutor();
                // handle the timeout
            } catch (InterruptedException e) {
                log.debug("Interrupted");
                failed = true;
                // handle the interrupts
            } catch (ExecutionException e) {
                // handle other exceptions
                log.debug("Excecution Exception");
                failed = true;
            } catch (Throwable e) {
                log.debug("Exception");
                failed = true;
                // handle other exceptions
            }

            StrykerStage.racMillis += System.currentTimeMillis() - nanoPrev;

        }

        if (!failed){
            fixFound(input);
        } else {
            processFalseCandidate(input);
        }
    }

    private void fixFound(DarwinistInput input) {
        resolvedBugs.add(input.getFilename());
        try {
            FileUtils.writeToFile(System.getProperty("user.dir")+OpenJMLController.FILE_SEP +
                    "lastSolutionFilename", input.getFilename());
        } catch (IOException e) {
            // TODO: Define what to do!
        }
        log.error("Solution: "+input.getFilename());

        System.out.println("-----------------------STRYKER REPORT-----------------------");
        if (MuJavaController.feedbackOn) {
            System.out.println("------------------------FEEDBACK: ON------------------------");
        } else {
            System.out.println("------------------------FEEDBACK: OFF-----------------------");
        }
        System.out.println("BEAR IN MIND THAT THIS RUNS MULTITHREADED, THEREFORE THE SUM OF TIME OF THE PARTS IS NOT EQUAL TO THE TOTAL TIME SPENT");
        System.out.println("Total Time (millis): " + (System.currentTimeMillis() - StrykerStage.initialMillis));
        System.out.println("Compilation Time (millis): " + StrykerStage.compilationMillis);
        System.out.println("TACO Time (millis): " + StrykerStage.tacoMillis);
        System.out.println("RAC Time (millis): " + StrykerStage.racMillis);
        System.out.println("MuJava Time (millis): " + StrykerStage.muJavaMillis);
        System.out.println("Amount of generated Mutants: " + StrykerStage.mutationsGenerated);
        System.out.println("Amount of duplicate Mutants: " + StrykerStage.duplicateMutations);
        System.out.println("Amount of non-compilable Mutants: " + StrykerStage.nonCompilableMutations);
        System.out.println("Amount of Mutants pruned: " + StrykerStage.prunedMutations);
        System.out.println("Amount of Fathers pruned: " + StrykerStage.prunedFathers);
        System.out.println("Amount of Relevant Computated Feedbacks: " + StrykerStage.relevantFeedbacksFound);
        System.out.println("Amount of Mutants fatherized: " + MuJavaController.getInstance().getFathers().size());
        System.out.println("Amount of Mutants enqueued to MuJavaController: " + StrykerStage.mutationsQueuedToMJC);
        System.out.println("Amount of Mutants enqueued to OJMLController: " + StrykerStage.mutationsQueuedToOJMLC);
        System.out.println("Amount of Mutants that failed in postcondition in RAC: " + StrykerStage.postconditionFailedMutations);
        System.out.println("Amount of Mutants that throwed NPExcp in RAC: " + StrykerStage.nullPointerExceptionMutations);
        System.out.println("Amount of Mutants that timeouted in RAC: " + StrykerStage.timeoutMutations);
        System.out.println("Amount of Mutants enqueued to DController for Feedback: " + StrykerStage.mutationsQueuedToDarwinistForSeq);
        System.out.println("Amount of Mutants enqueued to DController as Candidates: " + StrykerStage.candidatesQueuedToDarwinist);
        System.out.println("Amount of Mutants Candidates that give SAT in DController: " + StrykerStage.falseCandidates);

        System.out.println("------------------------END OF REPORT-------------------------");
        UnskippableMuJavaController.getInstance().shutdownNow();
        MuJavaController.getInstance().shutdownNow();
        OpenJMLController.getInstance().shutdownNow();
        shutdown();
        queue.clear();
    }

    private void processFalseCandidate(DarwinistInput input) {
        StrykerStage.falseCandidates++;
        log.error("Failed Solution: "+input.getFilename());

        if (MuJavaController.feedbackOn) {
            //----------------------ENCOLADO A OPENJMLCONTROLLER FOR SEQ PROCESSING PARA BUSCAR FEEDBACK CON EL NUEVO INPUT QUE ROMPE ESTE "CANDIDATO"
            OpenJMLInput output = new OpenJMLInput(input.getFilename(),
                    input.getMethod(),
                    input.getConfigurationFile(),
                    input.getOverridingProperties(),
                    input.getOriginalFilename(),
                    input.getFeedback(), //TODO este feedback deberia tener como numero hasta donde mutar en 0??
                    input.getMutantsToApply(),
                    input.getSyncObject());
            log.debug("Adding task to the list");
            log.info("Creating output for OpenJMLController");

            //--------------Aca llamamos al instrumentador
            //                                                wrapper = StrykerJavaFileInstrumenter.instrumentForSequentialOutput(wrapper, input.getFeedback().getLastMutatedLines());
            output.setRacMethod(input.getRacMethod());
            OpenJMLController.getInstance().enqueueTask(output);
            log.debug("Adding task to the OpenJMLController");
        } else {
            MuJavaInput mujavainput = new MuJavaInput(input.getFilename(), 
                    input.getMethod(), input.getMutantsToApply(), new AtomicInteger(0), 
                    input.getConfigurationFile(), input.getOverridingProperties(), 
                    input.getOriginalFilename(), input.getSyncObject());
            MuJavaFeedback feedback = input.getFeedback();
            feedback.setFatherable(true);
            feedback.setGetSibling(true);
            feedback.setMutateRight(true);
            mujavainput.setMuJavaFeedback(feedback);
            MuJavaController.getInstance().enqueueTask(mujavainput);
        }
    }

    @Override
    protected int getQtyOfThreads() {
        return 1;
    }

    public List<String> getResolvedBugs() {
        return resolvedBugs;
    }


    public static String editTestFileToCompile(
            String inputFile, String outputFile, String sourceClassName, String classPackage, String methodName) {
        //      String tmpDir = inputFile.substring(0, inputFile.lastIndexOf(FILE_SEP));
        //      tmpDir = tmpDir.replaceAll("generated", "output");
        File source = new File(inputFile);
        File dest = new File(outputFile);

        String packageSentence = "package "+classPackage+";\n";
        //  int posLastUnderscore = methodName.lastIndexOf("_");
        //  methodName = methodName.substring(0, posLastUnderscore);
        try {
            dest.createNewFile();
            FileOutputStream fos = new FileOutputStream(dest);
            Scanner scan = new Scanner(source);
            scan.useDelimiter("\n");
            String str = null;
            while(scan.hasNext()){
                str = scan.next();
                if (str.contains("package")){
                    fos.write(packageSentence.getBytes(Charset.forName("UTF-8")));
                    str = "           import java.net.URL;";
                    fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
                    str = "           import java.net.URLClassLoader;";
                    fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
                    str = "           import java.net.MalformedURLException;";
                    fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
                    str = "           import java.io.File;";
                    fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
                    str = "           import java.lang.reflect.InvocationTargetException;";
                } 

                fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
            }

            fos.close();
            scan.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputFile;

    }

}