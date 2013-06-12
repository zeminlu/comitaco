package ar.edu.taco.linedetector;

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
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;
import org.eclipse.jdt.core.compiler.CompilationProgress;
import org.junit.Test;

import ar.edu.taco.stryker.api.impl.MuJavaController;
import ar.edu.taco.stryker.api.impl.StringsToWriteInFile;
import ar.edu.taco.stryker.api.impl.StrykerJavaFileInstrumenter;
import ar.edu.taco.stryker.api.impl.input.OpenJMLInput;
import ar.edu.taco.stryker.api.impl.input.OpenJMLInputWrapper;
import ar.edu.taco.utils.FileUtils;

import com.google.common.collect.Sets;

public class CodeSequencer {

    public static final String FILE_SEP = System.getProperty("file.separator");

    public static final String PATH_SEP = System.getProperty("path.separator");

    private static Logger log = Logger.getLogger(CodeSequencer.class);

    private static final String CLASSPATH = System.getProperty("java.class.path");

    public static final String DEST_PACKAGE = "ar.edu.itba.linedetector";

    private static CodeSequencer instance;

    public synchronized static CodeSequencer getInstance() {
        if (instance == null) {
            instance = new CodeSequencer();
        }
        return instance;
    }

    //	private JUnitCore junit;
    private ExecutorService executor = Executors.newSingleThreadExecutor(); //Executors.newFixedThreadPool(10);

    private Thread runningThread = null;

    private CodeSequencer() {

    }
    
    @SuppressWarnings("deprecation")
	public boolean sequence(OpenJMLInputWrapper wrapper) {
    	if(wrapper.getFilename() == null) {
    		log.debug("filename was null");
    		return false;
    	}
    	log.debug("Took: "+wrapper);
    	Map<String, OpenJMLInput> map = wrapper.getMap();
    	String filename = wrapper.getFilename();
    	String tempFilename = filename.substring(0, filename.lastIndexOf(FILE_SEP)+1) + DEST_PACKAGE.replaceAll("\\.", FILE_SEP) + FILE_SEP;
    	String packageToWrite = filename.substring(filename.indexOf(FILE_SEP+"a")+1, filename.lastIndexOf(FILE_SEP)+1).replaceAll(FILE_SEP, ".")+DEST_PACKAGE;
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
    	String currentClasspath = System.getProperty("java.class.path")+PATH_SEP+fileClasspath+PATH_SEP+System.getProperty("user.dir")+FILE_SEP+"generated";
    	String[] jml4cArgs = {
    			"-cp", currentClasspath,
    			//"-sourcepath", fileClasspath,
    			//"-rac",
    			//"-d", outputPath,
    			//"-noInternalSpecs",
    			//"-P",
    			"-source", "1.7",       //Agregado para que funcione con otro classloader debido
    			"-target", "1.7",       //a que conflictua con JDT para instrumentaci??n del c??digo
    			tempFilename
    	};
    	log.debug("BDL: CLASSPATH = "+ currentClasspath);
    	log.debug("BDL: SOURCEPATH = "+ CLASSPATH);
    	log.debug("BDL: TEMPFILENAME = "+ tempFilename);
    	log.debug("BDL: FILENAME = "+ filename);
    	log.debug("BDL: File Classpath = "+ fileClasspath);
    	log.debug("BDL: OUTPUT PATH = "+ outputPath);

    	Object compiler;
    	boolean exitValue = false;
    	try {
    		@SuppressWarnings("resource")
			ClassLoader cl2 = new URLClassLoader(new URL[]{new File("/Users/zeminlu/ITBA/Ph.D./comitaco/lib/stryker/jml4c.jar").toURI().toURL()});
    		Class<?> clazz = cl2.loadClass("org.jmlspecs.jml4.rac.Main");
    		compiler = clazz.getConstructor(PrintWriter.class, PrintWriter.class, boolean.class, Map.class, CompilationProgress.class)
    				.newInstance(new PrintWriter(System.out), new PrintWriter(System.err), false/*systemExit*/, null/*options*/, null/*progress*/);
    		Method compile = clazz.getMethod("compile", String[].class);
    		compile.setAccessible(true);
    		exitValue = (boolean) compile.invoke(compiler, (Object)jml4cArgs);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	compiler = null;

    	log.debug("compiled file with exit code = "+exitValue);
    	try {
    		log.info("preparing to run a test... "+packageToWrite+"."+MuJavaController.obtainClassNameFromFileName(tempFilename));

    		Class<?>[] junitInputs = wrapper.getJunitInputs();
    		int index = 0;
    		Class<?> junitInputClass = junitInputs[0];

    		Set<String> failedMethods = Sets.newHashSet();
    		Boolean threadTimeout = false;
    		
    		for (final String methodName : map.keySet()) {

    			FileUtils.writeToFile(wrapper.getSeqFilesPrefix() + "_" + methodName, "");
    			Method[] methods = junitInputClass.getMethods();
    			Method methodToRun = null;
    			for(Method m : methods) {
    				if(m.isAnnotationPresent(Test.class)) {
    					methodToRun = m;
    				}
    			}
    			final Method methodToRunInCallable = methodToRun; 
    			methodToRunInCallable.setAccessible(true);
    			final Object oToRun =  junitInputClass.newInstance();
    			final Object[] inputToInvoke = new Object[]{fileClasspath, qualifiedName, methodName};
    			Callable<Boolean> task = new Callable<Boolean>() {
    				public Boolean call() {
    					boolean result = false;
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
    						} finally {
    							try {
    								if(pw != null)  pw.close();
    								if(sw != null)  sw.close();
    							} catch (IOException ignore) {}
    						}
    						if (retValue.contains("NullPointerException")) {
    							return null;
    						}
    					} catch (Throwable e) {
    						log.debug("Entered throwable");
    						//e.printStackTrace();
    						return false;
    					}
    					return result;
    				}
    			};
    			threadTimeout = false;
    			Future<Boolean> future = executor.submit(task);
    			try {
    				result = future.get(500, TimeUnit.MILLISECONDS);
    			} catch (TimeoutException ex) {
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
    				future.cancel(true); // may or may not desire this	
    			}
    			
    			log.info("test ran");
    			if (result == null) {
    				log.warn("TEST FAILED BECAUSE OF NULL POINTER EXCEPTION IN METHOD: :( for file: " + tempFilename + ", method: "+methodName + ", input: " + index);
    				return false;
    			} else if (!result) {
    				if (threadTimeout) {
    					log.error("timeouted file: "+filename);
    					return false;
    				} else {
    					log.warn("TEST FAILED AS EXPECTED: :( for file: " + tempFilename + ", method: "+methodName + ", input: " + index);
    					failedMethods.add(methodName);
    					StrykerJavaFileInstrumenter.replaceMethodBodies(wrapper, failedMethods);
    					return true;
    				}
    			} else {
    				log.warn("TEST PASSED: :( for file: " + tempFilename + ", method: "+methodName + ", input: " + index);
    				return false;
    			}
    		}
    	} catch (IllegalArgumentException e) {
    		//e.printStackTrace();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return false;
    }

    public String editFileToPassToNextStage(String filename) {
        String classPackage = filename.substring(filename.indexOf(FILE_SEP+"a")+1, filename.lastIndexOf(FILE_SEP)).replaceAll(FILE_SEP, ".");

        return classPackage+"."+MuJavaController.obtainClassNameFromFileName(filename);
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


}
