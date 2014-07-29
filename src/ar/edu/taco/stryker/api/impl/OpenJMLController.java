package ar.edu.taco.stryker.api.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.eclipse.jdt.core.compiler.CompilationProgress;
import org.junit.Test;

import ar.edu.taco.engine.StrykerStage;
import ar.edu.taco.stryker.api.impl.input.DarwinistInput;
import ar.edu.taco.stryker.api.impl.input.MuJavaFeedback;
import ar.edu.taco.stryker.api.impl.input.MuJavaInput;
import ar.edu.taco.stryker.api.impl.input.OpenJMLInput;
import ar.edu.taco.stryker.api.impl.input.OpenJMLInputWrapper;
import ar.edu.taco.utils.FileUtils;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class OpenJMLController extends AbstractBaseController<OpenJMLInputWrapper> {

    public static final String FILE_SEP = System.getProperty("file.separator");

    public static final String PATH_SEP = System.getProperty("path.separator");

    private static Logger log = Logger.getLogger(OpenJMLController.class);

    private static final String CLASSPATH = System.getProperty("java.class.path");

    public static final String MUTANTS_DEST_PACKAGE = "ar.edu.itba.stryker.mutants";

    private static OpenJMLController instance;

    private static int consumedMutants = 0;

    public synchronized static OpenJMLController getInstance() {
        if (instance == null) {
            instance = new OpenJMLController();
        }
        return instance;
    }

    //	private JUnitCore junit;
    private ExecutorService executor = Executors.newSingleThreadExecutor(); //Executors.newFixedThreadPool(10);

    private Thread runningThread = null;

    private OpenJMLController() {
        //		try {
        //			ClassLoaderTools.addFile(System.getProperty("user.dir")+FILE_SEP+"generated"+FILE_SEP);
        //		} catch (IOException e) {
        //			e.printStackTrace();
        //		}
    }

    @Override
    protected Runnable getRunnable() {
        return new Runnable() {

            @SuppressWarnings("deprecation")
            @Override
            public void run() {
                try {
                    while (!willShutdown.get()) {
                        try {
                            //int j = 0;
                            log.debug("Taking new input from queue");
                            OpenJMLInputWrapper wrapper = queue.take();
                            log.debug("Took from queue");
                            log.debug("Queue size: "+queue.size());
                            if(wrapper.getFilename() == null) {
                                log.debug("filename was null");
                                shutdown();
                                break;
                            }
                            log.debug("Took: "+wrapper);
                            Map<String, OpenJMLInput> map = wrapper.getMap();
                            String filename = wrapper.getFilename();
                            String tempFilename = filename.substring(0, filename.lastIndexOf(FILE_SEP)+1) + MUTANTS_DEST_PACKAGE.replaceAll("\\.", FILE_SEP) + FILE_SEP;
                            String packageToWrite = filename.substring(filename.indexOf(FILE_SEP+"a")+1, filename.lastIndexOf(FILE_SEP)+1).replaceAll(FILE_SEP, ".")+MUTANTS_DEST_PACKAGE;
                            String packageSentence = "package "+packageToWrite+";\n";
                            try {
                                File destFile = new File(tempFilename);
                                destFile.mkdirs();
                                tempFilename += filename.substring(filename.lastIndexOf(FILE_SEP) + 1);

                                destFile = new File(tempFilename);
                                destFile.createNewFile();
                                FileOutputStream fos = new FileOutputStream(destFile);

                                Scanner scan = new Scanner(new File(filename));
                                scan.useDelimiter("\n");
                                while(scan.hasNext()){
                                    String str = scan.next();
                                    if( str.contains("package")){
                                        fos.write(packageSentence.getBytes(Charset.forName("UTF-8")));
                                        break;
                                    } else if (str.contains("import")) {
                                        fos.write(packageSentence.getBytes(Charset.forName("UTF-8")));
                                        fos.write((scan.next() + "\n").getBytes(Charset.forName("UTF-8")));
                                        break;
                                    }
                                }

                                boolean reachAlreadyWritten = false;
                                boolean classFound = false;
                                while(scan.hasNext()){
                                    boolean lineAlreadyWritten = false;
                                    String str = scan.next();
                                    if(!reachAlreadyWritten && str.contains(" class ")) {
                                        classFound = true;
                                        if(str.contains("{")) {
                                            int index = str.indexOf("{");
                                            String beforeBrace = str.substring(0, index);
                                            String brace = str.substring(index, index + 1);
                                            String afterBrace = str.substring(index + 1, str.length());

                                            fos.write((beforeBrace + "\n").getBytes(Charset.forName("UTF-8")));
                                            fos.write((brace + "\n").getBytes(Charset.forName("UTF-8")));
                                            reachAlreadyWritten = true;
                                            lineAlreadyWritten = true;
                                            fos.write((StringsToWriteInFile.reachMethod + "\n").getBytes(Charset.forName("UTF-8")));
                                            fos.write((afterBrace + "\n").getBytes(Charset.forName("UTF-8")));
                                        }
                                    }
                                    if(!reachAlreadyWritten && classFound && str.contains("{")) {
                                        int index = str.indexOf("{");
                                        String beforeBrace = str.substring(0, index);
                                        String brace = str.substring(index, index + 1);
                                        String afterBrace = str.substring(index + 1, str.length());

                                        fos.write((beforeBrace + "\n").getBytes(Charset.forName("UTF-8")));
                                        fos.write((brace + "\n").getBytes(Charset.forName("UTF-8")));
                                        reachAlreadyWritten = true;
                                        lineAlreadyWritten = true;
                                        fos.write((StringsToWriteInFile.reachMethod + "\n").getBytes(Charset.forName("UTF-8")));
                                        fos.write((afterBrace + "\n").getBytes(Charset.forName("UTF-8")));
                                    }
                                    if(str.contains("\\reach")) {
                                        String[] eachReach = str.split("\\\\reach");
                                        for(int i = 0; i < eachReach.length; i++) {
                                            /*
                                             * I can be sure of this, because since the line starts always with @
                                             * it is wrong to append \\reach to the first string...
                                             */
                                            if(i != 0) {
                                                eachReach[i] = "\\reach" + eachReach[i] + " ";
                                            }
                                        }

                                        for(int i = 0; i < eachReach.length; i++) {
                                            String each = eachReach[i];

                                            if(each.contains("\\reach") == false) {
                                                continue;
                                            }

                                            String beforeReplacement = "\\reach";
                                            String afterReplacement = "reach"; 
                                            each = each.replace(beforeReplacement, afterReplacement);

                                            int openBracketIndex = each.indexOf(afterReplacement) + afterReplacement.length() + 1;
                                            int closeBracketIndex = each.substring(openBracketIndex).indexOf(")") + openBracketIndex;

                                            String beforeArgs = each.substring(0, openBracketIndex);
                                            String afterArgs = each.substring(closeBracketIndex, each.length());
                                            String args = each.substring(openBracketIndex, closeBracketIndex);

                                            String[] splittedArgs = args.split(",");

                                            String modifiedString = "";
                                            modifiedString += beforeArgs;
                                            modifiedString += splittedArgs[0];
                                            modifiedString += ",";
                                            modifiedString += splittedArgs[1] + ".class";
                                            modifiedString += ",";
                                            modifiedString += "\"" + splittedArgs[2] + "\"";
                                            modifiedString += afterArgs;

                                            eachReach[i] = modifiedString;
                                        }

                                        String lineToWrite = "";
                                        for(String each : eachReach) {
                                            lineToWrite += each;
                                        }

                                        lineAlreadyWritten = true;
                                        fos.write((lineToWrite + "\n").getBytes(Charset.forName("UTF-8")));
                                    }
                                    if(!lineAlreadyWritten) {
                                        fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
                                    }
                                }
                                fos.close();
                                scan.close();
                            } catch (FileNotFoundException e1) {
                                e1.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            final String fileClasspath = tempFilename.substring(0, tempFilename.lastIndexOf(packageToWrite.replaceAll("\\.", FILE_SEP)));
                            Boolean result = true;
                            final String qualifiedName = editFileToPassToNextStage(tempFilename);

                            String outputPath = filename.substring(0, filename.lastIndexOf(FILE_SEP) + 1);

                            String[] systemClassPathsToFilter = System.getProperty("java.class.path").split(PATH_SEP);

                            String filteredSystemClasspath = "";

                            for (int k = 0 ; k < systemClassPathsToFilter.length ; ++k) {
                                if (systemClassPathsToFilter[k].contains("org.eclipse.jdt.core") ||
                                        systemClassPathsToFilter[k].contains("org.eclipse.text") ||
                                        systemClassPathsToFilter[k].contains("org.eclipse.equinox.common") ||
                                        systemClassPathsToFilter[k].contains("org.eclipse.equinox.preferences") ||
                                        systemClassPathsToFilter[k].contains("org.eclipse.osgi") ||
                                        systemClassPathsToFilter[k].contains("org.eclipse.core.contenttype") ||
                                        systemClassPathsToFilter[k].contains("org.eclipse.core.jobs") ||
                                        systemClassPathsToFilter[k].contains("org.eclipse.core.resources") ||
                                        systemClassPathsToFilter[k].contains("org.eclipse.core.runtime")) {
                                    continue;
                                }
                                filteredSystemClasspath += systemClassPathsToFilter[k] + PATH_SEP;
                            }

                            String currentClasspath = System.getProperty("user.dir")+FILE_SEP+"lib/stryker/jml4c.jar"+PATH_SEP+fileClasspath+PATH_SEP+filteredSystemClasspath+PATH_SEP+System.getProperty("user.dir")+FILE_SEP+"generated";

                            String[] jml4cArgs = {
                                    "-cp", currentClasspath,
                                    //"-sourcepath", fileClasspath,
                                    //"-rac",
                                    //"-d", outputPath,
                                    //"-noInternalSpecs",
                                    //"-P",
                                    "-source", "1.7",       //Agregado para que funcione con otro classloader debido
                                    "-target", "1.7",       //a que conflictua con JDT para instrumentacion del codigo
                                    tempFilename
                            };
                            log.debug("STRYKER: CLASSPATH = "+ currentClasspath);
                            log.debug("STRYKER: SOURCEPATH = "+ CLASSPATH);
                            log.debug("STRYKER: TEMPFILENAME = "+ tempFilename);
                            log.debug("STRYKER: FILENAME = "+ filename);
                            log.debug("STRYKER: File Classpath = "+ fileClasspath);
                            log.debug("STRYKER: OUTPUT PATH = "+ outputPath);

                            @SuppressWarnings("resource")
                            ClassLoader cl2 = new URLClassLoader(new URL[]{new File(System.getProperty("user.dir")+FILE_SEP+"lib/stryker/jml4c.jar").toURI().toURL()}, null);
                            Class<?> clazz = cl2.loadClass("org.jmlspecs.jml4.rac.Main");
                            Class<?> clazz2 = cl2.loadClass("org.eclipse.jdt.core.compiler.CompilationProgress");

                            Object compiler = clazz.getConstructor(PrintWriter.class, PrintWriter.class, boolean.class, Map.class, clazz2)
                                    .newInstance(new PrintWriter(System.out), new PrintWriter(System.err), false/*systemExit*/, null/*options*/, null/*progress*/);
                            Method compile = clazz.getMethod("compile", String[].class);
                            compile.setAccessible(true);
                            //TODO Que onda este 'parameter'? Se usaba en una version vieja?
                            Object[] parameter = new Object[]{jml4cArgs}; 
                            long nanoPrev = System.currentTimeMillis();
                            boolean exitValue = (boolean) compile.invoke(compiler, (Object)jml4cArgs);
                            StrykerStage.compilationMillis += System.currentTimeMillis() - nanoPrev;
                            /**/            compiler = null;

                            String newFileClasspath = fileClasspath + PATH_SEP + System.getProperty("user.dir")+FILE_SEP+"lib/stryker/jml4c.jar";

                            log.debug("compiled file with exit code = "+exitValue);
                            try {
                                log.info("preparing to run a test... "+packageToWrite+"."+MuJavaController.obtainClassNameFromFileName(tempFilename));

                                Class<?>[] junitInputs = StrykerStage.junitInputs;
                                int index = 0;
                                Class<?> junitInputClass = junitInputs[0];

                                Set<String> candidateMethods = Sets.newHashSet();
                                Map<String, String> failedMethods = Maps.newHashMap();
                                Set<String> nullPointerMethods = Sets.newHashSet();
                                Set<String> timeoutMethods = Sets.newHashSet();
                                Boolean threadTimeout = false;
                                for (final String methodName : map.keySet()) {
                                    int maxNumberAttemptedInputs = Math.min(StrykerStage.indexToLastJUnitInput, 49);
                                    log.debug("maxNumberAttemptedInputs: "+maxNumberAttemptedInputs);
                                    boolean failed = false;

                                    for (int attempted = 0; attempted <= maxNumberAttemptedInputs && !failed; attempted++){
                                        if (MuJavaController.feedbackOn) {
                                            FileUtils.writeToFile(wrapper.getSeqFilesPrefix() + "_" + methodName, "");
                                        }
                                        Method[] methods = junitInputClass.getMethods();
                                        Method methodToRun = null;
                                        for(Method m : methods) {
                                            if(m.isAnnotationPresent(Test.class)) {
                                                methodToRun = m;
                                                break;
                                            }
                                        }
                                        final Method methodToRunInCallable = methodToRun; 
                                        methodToRunInCallable.setAccessible(true);
                                        final Object oToRun =  junitInputClass.newInstance();
                                        final Object[] inputToInvoke = new Object[]{newFileClasspath, qualifiedName, methodName};
                                        Callable<Boolean> task = new Callable<Boolean>() {
                                            public Boolean call() throws InvocationTargetException {
                                                Boolean result = false;
                                                try {
                                                    runningThread = Thread.currentThread();
                                                    long timeprev = System.currentTimeMillis();
                                                    methodToRunInCallable.invoke(oToRun, inputToInvoke);
                                                    long timepost = System.currentTimeMillis();
                                                    result = true;
                                                    log.debug("time taken: "+(timepost - timeprev));
                                                } catch (IllegalAccessException e) {
                                                    log.debug("Entered IllegalAccessException");
                                                    //e.printStackTrace();
                                                } catch (IllegalArgumentException e) {
                                                    log.debug("Entered IllegalArgumentException");
                                                    //e.printStackTrace();
                                                } catch (InvocationTargetException e) {
                                                    //                                                    e.printStackTrace();
                                                    log.debug("Entered InvocationTargetException");
                                                    log.debug("QUIT BECAUSE OF JML RAC");
                                                    String retValue = null;
                                                    StringWriter sw = null;
                                                    PrintWriter pw = null;
                                                    try {
                                                        sw = new StringWriter();
                                                        pw = new PrintWriter(sw);
                                                        e.printStackTrace(pw);
                                                        retValue = sw.toString();
                                                        //                                                        System.out.println(retValue);
                                                        //                                                        System.out.println("------------------------------------------------------------------------------------------------");
                                                    } finally {
                                                        try {
                                                            if(pw != null)  pw.close();
                                                            if(sw != null)  sw.close();
                                                        } catch (IOException ignore) {}
                                                    }
                                                    if (retValue.contains("JMLInternalNormalPostconditionError")) {
                                                        //                                                        System.out.println("Fallo por la postcondicion!!");
                                                        result = false;
                                                    } else if (retValue.contains("JMLExitExceptionalPostconditionError")) { 
                                                        result = null;
                                                    } else if (retValue.contains("NullPointerException")) {
                                                        //                                                        System.out.println("NULL POINTER EXCEPTION EN RAC!!!!!!!!!!!!");
                                                        result = null;
                                                    } else if (retValue.contains("ThreadDeath")) {
                                                        //                                                        System.out.println("THREAD DEATH EN RAC!!!!!!!!!!!!!!!!");
                                                        result = null;
                                                    } else {
                                                        //                                                        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" +
                                                        //                                                                "\nFAILED METHODDDD FOR NO REASON!!!!!!!!!!!!!!!!!!!!" +
                                                        //                                                                "\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                                                        result = null;
                                                    }
                                                } catch (Throwable e) {
                                                    log.debug("Entered throwable");
                                                    //                                                    System.out.println("THROWABLEEE!!!!!!!!!!!!!!!!!!!!!!");
                                                    //e.printStackTrace();
                                                    //                                                    return false;
                                                }
                                                return result;
                                            }
                                        };
                                        threadTimeout = false;
                                        Future<Boolean> future = executor.submit(task);
                                        try {
                                            nanoPrev = System.currentTimeMillis();
                                            result = future.get(250, TimeUnit.MILLISECONDS);
                                        } catch (TimeoutException ex) {
                                            //                                            System.out.println("TIMEOUT POR FUERA DE RAC!!!!!!!!!!!!!!!!!!");
                                            result = false;
                                            threadTimeout = true;
                                            runningThread.stop();
                                            executor.shutdownNow();
                                            executor = Executors.newSingleThreadExecutor();
                                            // handle the timeout
                                        } catch (InterruptedException e) {
                                            log.debug("Interrupted");
                                            // handle the interrupts
                                        } catch (ExecutionException e) {
                                            // handle other exceptions
                                            log.debug("Excecution Exception");
                                        } catch (Throwable e) {
                                            log.debug("Exception");
                                            // handle other exceptions
                                        } finally {
                                            StrykerStage.racMillis += System.currentTimeMillis() - nanoPrev;
                                            future.cancel(true); // may or may not desire this	
                                        }
                                        log.info("test ran");
                                        if (result == null) {
                                            log.warn("TEST FAILED BECAUSE OF AN EXCEPTION IN MUTATED METHOD: :( for file: " + tempFilename + ", method: "+methodName + ", input: " + index);
                                            failed = true;
                                            //                                            if (wrapper.isForSeqProcessing()) {
                                            nullPointerMethods.add(methodName);
                                            String junitfile = StrykerStage.junitFiles[index];
                                            failedMethods.put(methodName, junitfile);
                                            //                                            }
                                        } else if (!result) {
                                            if (threadTimeout) {
                                                log.error("timeouted file: "+filename);
                                                //                                                if (wrapper.isForSeqProcessing()) {
                                                timeoutMethods.add(methodName);
                                                String junitfile = StrykerStage.junitFiles[index];
                                                failedMethods.put(methodName, junitfile);
                                                //                                                }
                                            } else {
                                                log.warn("TEST FAILED: :( for file: " + tempFilename + ", method: "+methodName + ", input: " + index);
                                                //                                                if (wrapper.isForSeqProcessing()) {
                                                String junitfile = StrykerStage.junitFiles[index];
                                                failedMethods.put(methodName, junitfile);
                                                //                                                }
                                            }
                                            failed = true;
                                        } else {
                                            if (attempted == maxNumberAttemptedInputs) {
                                                log.warn("TEST PASSED: :) for file: " + tempFilename + ", method: "+methodName + ", input: " + index);
                                                OpenJMLInput input = map.get(methodName);
                                                DarwinistInput output = null;
                                                if (MuJavaController.feedbackOn) {
                                                    output = new DarwinistInput(wrapper.getOldFilename(), 
                                                            wrapper.getOriginalFilename(), wrapper.getConfigurationFile(), 
                                                            wrapper.getMethod(), input.getOverridingProperties(), qualifiedName, 
                                                            junitInputs, inputToInvoke, false, null, null, null, null, null, input.getFeedback(), input.getMutantsToApply(), input.getSyncObject());
                                                } else {
                                                    output = new DarwinistInput(wrapper.getFilename(), 
                                                            wrapper.getOriginalFilename(), wrapper.getConfigurationFile(), 
                                                            wrapper.getMethod(), input.getOverridingProperties(), qualifiedName, 
                                                            junitInputs, inputToInvoke, false, null, null, null, null, null, input.getFeedback(), input.getMutantsToApply(), input.getSyncObject());
                                                }
                                                DarwinistController.getInstance().enqueueTask(output);
                                                StrykerStage.candidatesQueuedToDarwinist++;
                                                //                                                if (wrapper.isForSeqProcessing()) {
                                                candidateMethods.add(methodName);
                                                //                                                }
                                                log.debug("Enqueded task to Darwinist Controller");
                                            } else {
                                                log.debug("TEST CANDIDATE TO PASS :), for file: " + tempFilename + ", method: "+methodName + ", input: " + index);
                                                junitInputClass = junitInputs[index];
                                                log.debug("The class to be used in OpenJMLController is: "+junitInputClass.getName());
                                            }
                                            index++;
                                            if (index == junitInputs.length || junitInputs[index] == null){
                                                index = 0;
                                            }
                                        }
                                    }	
                                }

                                consumedMutants = consumedMutants + map.size();

                                log.warn("Mutants consumed by RAC: "+consumedMutants);

                                //                                    System.out.println("----------------------- FAILED METHODS -------------------------");
                                for (String methodName : failedMethods.keySet()) {
                                    System.out.println("Postcondition Failed Mutation: " + wrapper.getFilename());
                                }
                                //                                    System.out.println("--------------------- CANDIDATE METHODS ------------------------");
                                for (String methodName : candidateMethods) {
                                    System.out.println("Candidate Mutation: " + wrapper.getFilename());
                                }
                                //                                    System.out.println("-------------------- NULL POINTER METHODS ----------------------");
                                for (String methodName : nullPointerMethods) {
                                    System.out.println("Null Pointer Mutation: " + wrapper.getFilename());
                                }
                                //                                    System.out.println("----------------------- TIMEOUT METHODS ------------------------");
                                for (String methodName : timeoutMethods) {
                                    System.out.println("Timeout Mutation " + wrapper.getFilename());
                                }
                                //                                    int registeredMethods = failedMethods.size() + candidateMethods.size() 
                                //                                            + nullPointerMethods.size() + timeoutMethods.size();
                                //                                    System.out.println("---------------- TOTAL DE METODOS REGISTRADOS: " 
                                //                                            + registeredMethods + " ------------------");

                                //Aca estoy fuera del for que itera por cada nombre de metodo mutado
                                //Deberia llamar a un metodo con todos los failedMethods
                                //Dicho metodo deberia reemplazar el codigo full de cada metodo de la lista por el secuencial
                                if (!failedMethods.isEmpty()) {
                                    //Reemplazamos por el codigo secuencial en los failedMethods
                                    //                                        System.out.println("POR LABURAR...");

                                    Set<String> methodsToCheck = failedMethods.keySet();

                                    for (String methodName : methodsToCheck) {
                                        OpenJMLInput openJMLInput = wrapper.getMap().get(methodName);

                                        if (MuJavaController.feedbackOn) {
                                            final Properties props = new Properties();
                                            Properties oldProps = openJMLInput.getOverridingProperties();
                                            for(Entry<Object,Object> o : oldProps.entrySet()){
                                                if(o.getKey().equals("attemptToCorrectBug")) {
                                                    props.put(o.getKey(), "false");
                                                } else if (o.getKey().equals("generateUnitTestCase")) {
                                                    props.put(o.getKey(), "false");
                                                } else if (o.getKey().equals("generateCheck")) {
                                                    props.put(o.getKey(), "true");
                                                } else if (o.getKey().equals("generateRun")) {
                                                    props.put(o.getKey(), "false");
                                                } else if (o.getKey().equals("methodToCheck")) {
                                                    props.put(o.getKey(), wrapper.getMethod() + "_0");
                                                } else {
                                                    props.put(o.getKey(), o.getValue());
                                                }
                                            }
                                            DarwinistInput darwinistInput = new DarwinistInput(
                                                    null, 
                                                    openJMLInput.getOriginalFilename(), 
                                                    wrapper.getConfigurationFile(), 
                                                    wrapper.getMethod(), 
                                                    props, 
                                                    null, 
                                                    junitInputs, 
                                                    null,
                                                    true, 
                                                    methodName,
                                                    failedMethods.get(methodName),
                                                    wrapper.getSeqFilesPrefix(),
                                                    null,
                                                    wrapper.getOldFilename(),
                                                    openJMLInput.getFeedback(),
                                                    openJMLInput.getMutantsToApply(),
                                                    openJMLInput.getSyncObject()
                                                    );
                                            DarwinistController.getInstance().enqueueTask(darwinistInput);
                                            StrykerStage.mutationsQueuedToDarwinistForSeq++;
                                            StrykerStage.postconditionFailedMutations++;

                                            //                                        System.out.println("HIZO TODO!!");
                                            if (!timeoutMethods.isEmpty()) {
                                                StrykerStage.mutationsQueuedToDarwinistForSeq -= timeoutMethods.size();
                                                StrykerStage.postconditionFailedMutations -= timeoutMethods.size();
                                                StrykerStage.timeoutMutations += timeoutMethods.size();

                                                //                                        for (String string : timeoutMethods) {
                                                //                                            StrykerStage.timeoutMutations++;
                                                //                                            input = map.get(string);
                                                //                                            MuJavaInput mujavainput = new MuJavaInput(wrapper.getOldFilename(), string, input.getJunitInputs(), input.getMutantsToApply(), new AtomicInteger(0), input.getConfigurationFile(), input.getOverridingProperties(), input.getOriginalFilename(), input.getSyncObject());
                                                //                                            MuJavaFeedback feedback = input.getFeedback();
                                                //                                            feedback.setMutateUntilLine(0);
                                                //                                            mujavainput.setMuJavaFeedback(feedback);
                                                //                                            mujavainput.getMuJavaFeedback().setFatherable(true);
                                                //                                            MuJavaController.getInstance().enqueueTask(mujavainput);
                                                //                                        }
                                            }

                                            if (!nullPointerMethods.isEmpty()) {
                                                StrykerStage.mutationsQueuedToDarwinistForSeq -= nullPointerMethods.size();
                                                StrykerStage.postconditionFailedMutations -= nullPointerMethods.size();
                                                StrykerStage.nullPointerExceptionMutations += timeoutMethods.size();
                                                //                                        for (String string : nullPointerMethods) {
                                                //                                            StrykerStage.nullPointerExceptionMutations++;
                                                //                                            input = map.get(string);
                                                //                                            MuJavaInput mujavainput = new MuJavaInput(wrapper.getOldFilename(), string, input.getJunitInputs(), input.getMutantsToApply(), new AtomicInteger(0), input.getConfigurationFile(), input.getOverridingProperties(), input.getOriginalFilename(), input.getSyncObject());
                                                //                                            MuJavaFeedback feedback = input.getFeedback();
                                                //                                            feedback.setMutateUntilLine(0);
                                                //                                            mujavainput.setMuJavaFeedback(feedback);
                                                //                                            mujavainput.getMuJavaFeedback().setFatherable(true);
                                                //                                            MuJavaController.getInstance().enqueueTask(mujavainput);
                                                //                                        }
                                            }
                                        } else {
                                            MuJavaInput mujavainput = new MuJavaInput(openJMLInput.getFilename(), openJMLInput.getMethod(), openJMLInput.getJunitInputs(), openJMLInput.getMutantsToApply(), new AtomicInteger(0), openJMLInput.getConfigurationFile(), openJMLInput.getOverridingProperties(), openJMLInput.getOriginalFilename(), openJMLInput.getSyncObject());
                                            MuJavaFeedback feedback = openJMLInput.getFeedback();
                                            feedback.setFatherable(true);
                                            feedback.setMutateUntilLine(0);
                                            mujavainput.setMuJavaFeedback(feedback);
                                            MuJavaController.getInstance().enqueueTask(mujavainput);
                                        }

                                    } 
                                }
                            } catch (IllegalArgumentException e) {
                                //                                System.out.println(e.getMessage());
                                e.printStackTrace();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } catch (InterruptedException e) {
                            break;
                            //e.printStackTrace();
                        }
                    }
                    log.warn("Shutting down Darwinist Controller");
                    DarwinistInput output = new DarwinistInput(null, null, null, null, null, null, null, null, false, null, null, null, null, null, null, null, null);
                    DarwinistController.getInstance().enqueueTask(output);
                    //DarwinistController.getInstance().shutdown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public String editFileToPassToNextStage(String filename) {
                String classPackage = filename.substring(filename.indexOf(FILE_SEP+"a")+1, filename.lastIndexOf(FILE_SEP)).replaceAll(FILE_SEP, ".");

                return classPackage+"."+MuJavaController.obtainClassNameFromFileName(filename);
            }

        };
    }

    public static void addUrl(URL u) {
        URLClassLoader sysloader = (URLClassLoader) ClassLoader
                .getSystemClassLoader();
        Class<URLClassLoader> sysclass = URLClassLoader.class;

        try {
            Method method = sysclass.getDeclaredMethod("addURL", URL.class);
            method.setAccessible(true);
            method.invoke(sysloader, new Object[] { u });
            sysloader = null;
        } catch (Throwable t) {
            t.printStackTrace();
            try {
                throw new IOException(
                        "Error, could not add URL to system classloader");
            } catch (IOException e) {
                log.debug(e.getMessage());
            }
        }
    }

    @Override
    protected int getQtyOfThreads() {
        return 1;
    }

}
