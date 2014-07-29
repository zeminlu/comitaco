package ar.edu.taco.stryker.api.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import org.apache.commons.lang3.tuple.Pair;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.multijava.mjc.JCompilationUnitType;

import ar.edu.taco.TacoAnalysisResult;
import ar.edu.taco.TacoConfigurator;
import ar.edu.taco.TacoMain;
import ar.edu.taco.engine.JUnitStage;
import ar.edu.taco.engine.SnapshotStage;
import ar.edu.taco.engine.StrykerStage;
import ar.edu.taco.jml.parser.JmlParser;
import ar.edu.taco.junit.RecoveredInformation;
import ar.edu.taco.stryker.api.impl.input.DarwinistInput;
import ar.edu.taco.stryker.api.impl.input.MuJavaFeedback;
import ar.edu.taco.stryker.api.impl.input.MuJavaInput;
import ar.edu.taco.stryker.api.impl.input.OpenJMLInput;
import ar.edu.taco.stryker.api.impl.input.OpenJMLInputWrapper;
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

            TacoMain tacoMain = new TacoMain();

            @SuppressWarnings("deprecation")
            @Override
            public void run() {
                while (!willShutdown.get()) {
                    DarwinistInput input = null;
                    try {
                        input = queue.take();
                        log.debug("Queue size: "+queue.size());

                        ////////////////////////SEQ PROCESSING//////////////////////

                        if (input.isForSeqProcessing()) {

                            StrykerJavaFileInstrumenter.replaceMethodBodies(input);


                            //Negamos la postcondicion
                            //Negacion de la postcondicion:
                            //En primer lugar, cada formula de ensures va a estar en una linea

                            //Mas de un ensures significa mas de una formula, hay que hacer la conjuncion 
                            //La idea seria, a cada formula de ensures la encierro entre parentesis, despues
                            //hago el and entre cada una de ellas, y por ultimo encierro todo entre parentisis
                            // y le clavo el not adelante, de esa manera consigo negar la postcondicion.

                            //                            StrykerJavaFileInstrumenter.negatePostconditions(input);

                            //Antes de correr TACO con la postcondicion negada en cada metodo variabilizado
                            //tengo que cablear el input para el que fallo antes de variabilizarlo
                            //El input queda fijo porque quiero encontrar una mutacion en el codigo que arregle
                            //todo para ese caso en particular.
                            StrykerJavaFileInstrumenter.fixInput(input);
                            //                            StrykerJavaFileInstrumenter.enableExceptionsInContract(input);
                            StrykerJavaFileInstrumenter.negatePostconditions(input);

                            VariablizationData vdata = StrykerJavaFileInstrumenter.preprocessVariabilization(input);

                            TacoAnalysisResult analysis_result = null;
                            AlloyAnalysisResult analysisResult = null;

                            input.setSeqVariablizedFilename(input.getSeqFilesPrefix());
                            String filename = input.getSeqVariablizedFilename();
                            String originalFilename = input.getOriginalFilename();

                            File originalFile = new File(originalFilename);

                            File newFile = new File(originalFilename+"_temp");
                            newFile.createNewFile();

                            Files.copy(originalFile, newFile);

                            if(filename == null) {
                                shutdown();
                                break;
                            }
                            final String configurationFile = input.getConfigurationFile();
                            final Properties props = new Properties(input.getOverridingProperties());
                            Properties oldProps = input.getOverridingProperties();
                            for(Entry<Object,Object> o : oldProps.entrySet()){
                                props.put(o.getKey(), o.getValue());
                            }
                            Pair<Integer, Boolean> variablizationResult = null;
                            Integer variablizedID = null;
                            boolean notFixable = false;
                            boolean notCompilable = false;
                            while (analysisResult == null || analysisResult.isUNSAT()) {
                                //Analizar con TACO el metodo actual, previa variabilizacion
                                //Los que dan SAT, avisarle a MuJavaController (estoy haciendo CHECK)
                                //Los que que dan UNSAT, a variabilizar (estoy haciendo CHECK)
                                variablizationResult = StrykerJavaFileInstrumenter.variablizeNext(input, vdata);
                                if (variablizationResult == null) {
                                    //No hay mas que variabilizar, no tiene solucion
                                    //                                    System.out.println("No hay solucion");
                                    notFixable = true;
                                    break;
                                }
                                variablizedID = variablizationResult.getLeft();

                                File newTestFile = new File(filename);
                                newFile.createNewFile();

                                Files.copy(newTestFile, originalFile);


                                String currentClasspath = System.getProperty("java.class.path");
                                JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
                                long nanoPrev = System.currentTimeMillis();
                                int compilationResult = compiler.run(null, new NullOutputStream(), new NullOutputStream(), new String[]{"-classpath", currentClasspath, originalFilename});
                                StrykerStage.compilationMillis += System.currentTimeMillis() - nanoPrev;
                                /**/                    compiler = null;
                                if(compilationResult == 0){
                                    log.debug("Compilation is successful: "+filename);
                                    props.put("attemptToCorrectBug",false);
                                    props.put("generateUnitTestCase",false);
                                    //                                    System.out.println("Por arrancar TACO...");
                                    nanoPrev = System.currentTimeMillis();
                                    analysis_result = tacoMain.run(configurationFile, props);
                                    StrykerStage.tacoMillis += System.currentTimeMillis() - nanoPrev;
                                    analysisResult = analysis_result.get_alloy_analysis_result();
                                } else {
                                    //Hubo error de compilacion
                                    notCompilable = true;
                                    break;
                                }
                            }

                            //                            System.out.println("Salio del while, dio SAT para el metodo actual");
                            //                            System.out.println("Hay que darle feedback a MUJAVA, mutar hasta ID " + variablizedID);
                            //Obtener linea del no-secuencial hasta la cual hay que mutar

                            log.debug("Inside the if of finally");
                            originalFilename = input.getOriginalFilename();
                            originalFile = new File(originalFilename);

                            newFile = new File(originalFilename+"_temp");

                            originalFile.delete();

                            try {
                                log.debug("Restoring file: "+originalFile);
                                Files.copy(newFile, originalFile);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                            MuJavaInput mujavainput = new MuJavaInput(input.getOldFilename(), input.getMethod(), input.getInputs(), input.getMutantsToApply(), new AtomicInteger(0), input.getConfigurationFile(), input.getOverridingProperties(), input.getOldFilename(), input.getSyncObject());
                            mujavainput.setOldFilename(input.getOldFilename());
                            MuJavaFeedback feedback = input.getFeedback();
                            if (notFixable) {
                                feedback.setFatherable(false);
                                feedback.setMutateUntilLine(0);
                            } else if (notCompilable) {
                                feedback.setFatherable(true);
                                feedback.setMutateUntilLine(0);
                            } else {
                                int mutateUntilLine = input.getFeedback().getLineMutationIndexes().length - variablizedID - 1;
                                feedback.setFatherable(variablizationResult.getRight());
                                feedback.setMutateUntilLine(mutateUntilLine);
                                if (mutateUntilLine > 0) {
                                    StrykerStage.relevantFeedbacksFound++;
                                }
                            }
                            mujavainput.setMuJavaFeedback(feedback);
                            MuJavaController.getInstance().enqueueTask(mujavainput);
                        } else {
                            String filename;
                            String originalFilename;

                            filename = input.getFilename();
                            originalFilename = input.getOriginalFilename();

                            if(filename == null) {
                                shutdown();
                                break;
                            }
                            final String configurationFile = input.getConfigurationFile();
                            final Properties props = new Properties(input.getOverridingProperties());
                            Properties oldProps = input.getOverridingProperties();
                            for(Entry<Object,Object> o : oldProps.entrySet()){
                                props.put(o.getKey(), o.getValue());
                            }

                            File originalFile = new File(originalFilename);
                            //						originalFile.delete();

                            File newFile = new File(originalFilename+"_temp");
                            newFile.createNewFile();

                            Files.copy(originalFile, newFile);

                            File newTestFile = new File(filename);
                            newTestFile.createNewFile();

                            Files.copy(newTestFile, originalFile);


                            //						Main compiler = new Main(new PrintWriter(System.out), new PrintWriter(System.err), 
                            //								false/*systemExit*/, null/*options*/, null/*progress*/);
                            String currentClasspath = System.getProperty("java.class.path");
                            //						compiler.compile(new String[]{"-classpath", currentClasspath, originalFilename});
                            //						props.put("methodToCheck", input.getMethod()+"_0");
                            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
                            long nanoPrev = System.currentTimeMillis();
                            int compilationResult =	compiler.run(null, new NullOutputStream(), new NullOutputStream(), new String[]{"-classpath", currentClasspath, originalFilename});
                            StrykerStage.compilationMillis += System.currentTimeMillis() - nanoPrev;
                            /**/					compiler = null;
                            if(compilationResult == 0){
                                log.debug("Compilation is successful: "+filename);
                                props.put("attemptToCorrectBug",false);
                                props.put("generateUnitTestCase",false);
                                nanoPrev = System.currentTimeMillis();
                                TacoAnalysisResult analysis_result = tacoMain.run(configurationFile, props);
                                StrykerStage.tacoMillis += System.currentTimeMillis() - nanoPrev;
                                AlloyAnalysisResult analysisResult = analysis_result.get_alloy_analysis_result();

                                if(analysisResult == null || analysisResult.isSAT()) {
                                    log.debug(filename + " didn't solve the problem");
                                    //								File f = new File(filename);
                                    //								f.delete();
                                    // This is the place to update the inputs to use during RAC execution

                                    log.debug("Valor 1: " + StrykerStage.indexToLastJUnitInput);
                                    log.debug("Valor 2: " + StrykerStage.junitInputs.length);

                                    if (analysisResult.isSAT() && StrykerStage.indexToLastJUnitInput < StrykerStage.junitInputs.length){
                                        StrykerStage.falseCandidates++;

                                        String junitFile = null;
                                        // Begin JUNIT Generation Stage
                                        List<JCompilationUnitType> compilation_units = JmlParser.getInstance().getCompilationUnits();
                                        String classToCheck = TacoConfigurator.getInstance().getString(TacoConfigurator.CLASS_TO_CHECK_FIELD);
                                        String methodToCheck = props.getProperty(TacoConfigurator.METHOD_TO_CHECK_FIELD);

                                        SnapshotStage snapshotStage = new SnapshotStage(compilation_units, analysis_result, classToCheck, methodToCheck);
                                        snapshotStage.execute();

                                        RecoveredInformation recoveredInformation = snapshotStage.getRecoveredInformation();
                                        recoveredInformation.setFileNameSuffix(StrykerStage.fileSuffix);

                                        JUnitStage jUnitStage = new JUnitStage(recoveredInformation);
                                        jUnitStage.execute();
                                        junitFile = jUnitStage.getJunitFileName();

                                        // Generation of error-exposing class from junit test.									

                                        try {
                                            String currentJunit = null;

                                            String tempFilename = junitFile.substring(0, junitFile.lastIndexOf(FILE_SEP)+1) /*+ FILE_SEP*/;	
                                            String packageToWrite = "ar.edu.output.junit";
                                            String fileClasspath = tempFilename.substring(0, tempFilename.lastIndexOf(new String("ar.edu.generated.junit").replaceAll("\\.", FILE_SEP)));
                                            fileClasspath = fileClasspath.replaceFirst("generated", "output");
                                            currentClasspath = System.getProperty("java.class.path")+PATH_SEP+fileClasspath+PATH_SEP+System.getProperty("user.dir")+FILE_SEP+"generated";
                                            currentJunit = TacoMain.editTestFileToCompile(junitFile, classToCheck, packageToWrite, methodToCheck);

                                            File[] file1 = new File[]{new File(currentJunit)};
                                            JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
                                            StandardJavaFileManager fileManager = javaCompiler.getStandardFileManager(null, null, null);
                                            Iterable<? extends JavaFileObject> compilationUnit1 =
                                                    fileManager.getJavaFileObjectsFromFiles(Arrays.asList(file1));
                                            javaCompiler.getTask(null, fileManager, null, null, null, compilationUnit1).call();
                                            fileManager.close();
                                            javaCompiler = null;
                                            file1 = null;
                                            fileManager = null;

                                            //										if(compilationResult == 0) {
                                            log.debug("junit counterexample compilation succeded");
                                            ClassLoader cl = ClassLoader.getSystemClassLoader();
                                            @SuppressWarnings("resource")

                                            ClassLoader cl2 = new URLClassLoader(new URL[]{new File(fileClasspath).toURI().toURL()}, cl);
                                            //										ClassLoaderTools.addFile(fileClasspath);
                                            String classToLoad = packageToWrite+"."+TacoMain.obtainClassNameFromFileName(junitFile);
                                            Class<?> clazz = cl2.loadClass(classToLoad);
                                            cl = null;
                                            cl2 = null;
                                            //											log.warn("The class just stored is: "+clazz.getName());
                                            log.info("preparing to store a test class... "+packageToWrite+"."+MuJavaController.obtainClassNameFromFileName(junitFile));
                                            //											Result result = null;
                                            //											final Object oToRun = clazz.newInstance();
                                            StrykerStage.junitInputs[StrykerStage.indexToLastJUnitInput] = clazz;
                                            StrykerStage.junitFiles[StrykerStage.indexToLastJUnitInput] = junitFile;
                                            StrykerStage.indexToLastJUnitInput++;
                                            log.debug("In effect, junit test generation was successful");


                                            if (MuJavaController.feedbackOn) {
                                                //----------------------ENCOLADO A OPENJMLCONTROLLER FOR SEQ PROCESSING PARA BUSCAR FEEDBACK CON EL NUEVO INPUT QUE ROMPE ESTE "CANDIDATO"
                                                OpenJMLInput output = new OpenJMLInput(input.getOldFilename(),
                                                        StrykerStage.junitInputs, //OJO PORQUE QUIZAS NO LLEGA ACORRER ESTO CON EL INPUT NUEVO EN OPENJMLCONTROLLER DEBIDO AL LIMITE DE INPUTS A PROBAR
                                                        input.getMethod(),
                                                        input.getConfigurationFile(),
                                                        input.getOverridingProperties(),
                                                        input.getOriginalFilename(),
                                                        input.getFeedback(), //TODO este feedback deberia tener como numero hasta donde mutar en 0??
                                                        input.getMutantsToApply(),
                                                        input.getSyncObject());
                                                log.debug("Adding task to the list");
                                                Map<String,OpenJMLInput> map = new HashMap<String, OpenJMLInput>();
                                                map.put(input.getMethod(), output);
                                                OpenJMLInputWrapper wrapper = new OpenJMLInputWrapper(input.getFilename(), StrykerStage.junitInputs, 
                                                        input.getConfigurationFile(), input.getOverridingProperties(), input.getMethod(), map, input.getOriginalFilename());
                                                ;
                                                log.info("Creating output for OpenJMLController");

                                                //--------------Aca llamamos al instrumentador
                                                wrapper = StrykerJavaFileInstrumenter.instrumentForSequentialOutput(wrapper);
                                                wrapper.setForSeqProcessing(true);
                                                OpenJMLController.getInstance().enqueueTask(wrapper);
                                                log.debug("Adding task to the OpenJMLController");

                                            } else {
                                                MuJavaInput mujavainput = new MuJavaInput(input.getFilename(), input.getMethod(), input.getInputs(), input.getMutantsToApply(), new AtomicInteger(0), input.getConfigurationFile(), input.getOverridingProperties(), input.getOriginalFilename(), input.getSyncObject());
                                                MuJavaFeedback feedback = input.getFeedback();
                                                feedback.setFatherable(true);
                                                feedback.setMutateUntilLine(0);
                                                mujavainput.setMuJavaFeedback(feedback);
                                                MuJavaController.getInstance().enqueueTask(mujavainput);
                                            }
                                            //										} else {
                                            //											log.warn("compilation failed");
                                            //										}
                                            //												File originalFile = new File(tempFilename);
                                            //												originalFile.delete();

                                        } catch (ClassNotFoundException e) {
                                            //												e.printStackTrace();
                                        } catch (IOException e) {
                                            //												e.printStackTrace();
                                        } catch (IllegalArgumentException e) {
                                            //												e.printStackTrace();
                                        } catch (Exception e) {
                                            //												e.printStackTrace();
                                        }
                                        // Generation of error-exposing object from junit test.									

                                        //									StrykerStage.fileSuffix++;

                                    }
                                } else {
                                    if (analysisResult.isUNSAT()){
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
                                            final Object oToRun =  clazz.newInstance();

                                            final String methodName = input.getMethod();
                                            log.debug("methodName: " + methodName);
                                            Callable<Boolean> task = new Callable<Boolean>() {
                                                public Boolean call() {
                                                    boolean failed = true;
                                                    try {
                                                        runningThread = Thread.currentThread();
                                                        //														log.debug("fileClasspath :" + fileClasspath);
                                                        //														log.debug("qualifiedName :" + qualifiedName);
                                                        //														log.debug("methodName :" + methodName);
                                                        //														Object[] inputToInvoke = new Object[]{fileClasspath, qualifiedName, methodName};
                                                        long timeprev = System.currentTimeMillis();
                                                        methodToRunInCallable.invoke(oToRun, inputToInvoke);
                                                        long timepost = System.currentTimeMillis();
                                                        failed = false;
                                                        log.debug("time taken: "+(timepost - timeprev));
                                                    } catch (IllegalAccessException e) {
                                                        log.debug("Entered IllegalAccessException");
                                                        failed = true;
                                                        //														e.printStackTrace();
                                                    } catch (IllegalArgumentException e) {
                                                        log.debug("Entered IllegalArgumentException");
                                                        failed = true;
                                                        //														e.printStackTrace();
                                                    } catch (InvocationTargetException e) {
                                                        log.debug("Entered InvocationTargetException");
                                                        //                                                    e.printStackTrace();	
                                                        log.warn("FIX CANDIDATE QUIT BECAUSE OF JML RAC");
                                                        failed = true;
                                                    } catch (Throwable e) {
                                                        log.debug("Entered throwable");
                                                        //														e.printStackTrace();
                                                        failed = true;
                                                    }
                                                    return failed;
                                                }
                                            };
                                            Future<Boolean> future = executor.submit(task);
                                            try {
                                                nanoPrev = System.currentTimeMillis();
                                                failed = (Boolean)future.get(100, TimeUnit.MILLISECONDS);
                                            } catch (TimeoutException ex) {
                                                failed = true;
                                                log.warn("timeouted file: "+filename);
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
                                            } finally {
                                                StrykerStage.racMillis += System.currentTimeMillis() - nanoPrev;
                                                future.cancel(true); // may or may not desire this
                                            }

                                        }
                                        if (!failed){
                                            resolvedBugs.add(input.getFilename());
                                            log.error("Solution: "+input.getFilename());

                                            System.out.println("-----------------------STRYKER REPORT-----------------------");
                                            System.out.println("BEAR IN MIND THAT THIS RUNS MULTITHREADED, THEREFORE THE SUM OF TIME OF THE PARTS IS NOT EQUAL TO THE TOTAL TIME SPENT");
                                            System.out.println("Total Time (millis): " + (System.currentTimeMillis() - StrykerStage.initialMillis));
                                            System.out.println("Compilation Time (millis): " + StrykerStage.compilationMillis);
                                            System.out.println("TACO Time (millis): " + StrykerStage.tacoMillis);
                                            System.out.println("RAC Time (millis): " + StrykerStage.racMillis);
                                            System.out.println("Amount of generated Mutants: " + StrykerStage.mutationsGenerated);
                                            System.out.println("Amount of duplicate Mutants: " + StrykerStage.duplicateMutations);
                                            System.out.println("Amount of non-compilable Mutants: " + StrykerStage.nonCompilableMutations);
                                            System.out.println("Amount of Mutants pruned: " + StrykerStage.prunedMutations);
                                            System.out.println("Amount of Relevant Computated Feedbacks: " + StrykerStage.relevantFeedbacksFound);
                                            System.out.println("Amount of Mutants fatherized: " + MuJavaController.getInstance().getFathers().size());
                                            System.out.println("Amount of Mutants enqueued to OJMLController: " + StrykerStage.mutationsQueuedToOJMLC);
                                            System.out.println("Amount of Mutants that failed in postcondition in RAC: " + StrykerStage.postconditionFailedMutations);
                                            System.out.println("Amount of Mutants that throwed NPExcp in RAC: " + StrykerStage.nullPointerExceptionMutations);
                                            System.out.println("Amount of Mutants that timeouted in RAC: " + StrykerStage.timeoutMutations);
                                            System.out.println("Amount of Mutants enqueued to DController for Feedback: " + StrykerStage.mutationsQueuedToDarwinistForSeq);
                                            System.out.println("Amount of Mutants enqueued to DController as Candidates: " + StrykerStage.candidatesQueuedToDarwinist);
                                            System.out.println("Amount of Mutants Candidates that give SAT in DController: " + StrykerStage.falseCandidates);

                                            System.out.println("------------------------FIN DEL REPORTE-------------------------");
                                            MuJavaController.getInstance().shutdownNow();
                                            OpenJMLController.getInstance().shutdownNow();
                                            shutdown();
                                            queue.clear();
                                        } else {
                                            StrykerStage.falseCandidates++;
                                            log.error("Failed Solution: "+input.getFilename());

                                            if (MuJavaController.feedbackOn) {
                                                //----------------------ENCOLADO A OPENJMLCONTROLLER FOR SEQ PROCESSING PARA BUSCAR FEEDBACK CON EL NUEVO INPUT QUE ROMPE ESTE "CANDIDATO"
                                                OpenJMLInput output = new OpenJMLInput(input.getOldFilename(),
                                                        StrykerStage.junitInputs, //OJO PORQUE QUIZAS NO LLEGA ACORRER ESTO CON EL INPUT NUEVO EN OPENJMLCONTROLLER DEBIDO AL LIMITE DE INPUTS A PROBAR
                                                        input.getMethod(),
                                                        input.getConfigurationFile(),
                                                        input.getOverridingProperties(),
                                                        input.getOriginalFilename(),
                                                        input.getFeedback(), //TODO este feedback deberia tener como numero hasta donde mutar en 0??
                                                        input.getMutantsToApply(),
                                                        input.getSyncObject());
                                                log.debug("Adding task to the list");
                                                Map<String,OpenJMLInput> map = new HashMap<String, OpenJMLInput>();
                                                map.put(input.getMethod(), output);
                                                OpenJMLInputWrapper wrapper = new OpenJMLInputWrapper(input.getFilename(), StrykerStage.junitInputs, 
                                                        input.getConfigurationFile(), input.getOverridingProperties(), input.getMethod(), map, input.getOriginalFilename());
                                                ;
                                                log.info("Creating output for OpenJMLController");

                                                //--------------Aca llamamos al instrumentador
                                                wrapper = StrykerJavaFileInstrumenter.instrumentForSequentialOutput(wrapper);
                                                wrapper.setForSeqProcessing(true);
                                                OpenJMLController.getInstance().enqueueTask(wrapper);
                                                log.debug("Adding task to the OpenJMLController");
                                            } else {
                                                MuJavaInput mujavainput = new MuJavaInput(input.getFilename(), input.getMethod(), input.getInputs(), input.getMutantsToApply(), new AtomicInteger(0), input.getConfigurationFile(), input.getOverridingProperties(), input.getOriginalFilename(), input.getSyncObject());
                                                MuJavaFeedback feedback = input.getFeedback();
                                                feedback.setFatherable(true);
                                                feedback.setMutateUntilLine(0);
                                                mujavainput.setMuJavaFeedback(feedback);
                                                MuJavaController.getInstance().enqueueTask(mujavainput);
                                            }

                                            //										log.debug("fileClasspath: "+fileClasspath);
                                            //										log.debug("qualifiedName: " + qualifiedName);
                                            //										log.debug("methodName: " +input.getMethod());
                                        }

                                    }
                                }	
                            }else{
                                log.info("Compilation Failed");
                            }
                        }
                    } catch (InterruptedException e) {
                        //						e.printStackTrace();
                    } catch (Exception e) {
                        log.debug("Exception e: "+ e.getLocalizedMessage());
                        e.printStackTrace();
                    } finally {
                        log.debug("Entering finally");
                        if ((!(input.isForSeqProcessing() != null) || !input.isForSeqProcessing()) && input != null && input.getOriginalFilename() != null) {
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

    @Override
    protected int getQtyOfThreads() {
        return 1;
    }

    public List<String> getResolvedBugs() {
        return resolvedBugs;
    }


    public static String editTestFileToCompile(String inputFile, String outputFile, String sourceClassName, String classPackage, String methodName) {
        //		String tmpDir = inputFile.substring(0, inputFile.lastIndexOf(FILE_SEP));
        //		tmpDir = tmpDir.replaceAll("generated", "output");
        File source = new File(inputFile);
        File dest = new File(outputFile);

        String packageSentence = "package "+classPackage+";\n";
        //	int posLastUnderscore = methodName.lastIndexOf("_");
        //	methodName = methodName.substring(0, posLastUnderscore);
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
