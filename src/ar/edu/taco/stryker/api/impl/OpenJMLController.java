package ar.edu.taco.stryker.api.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;
import org.jmlspecs.jml4.rac.Main;
import org.junit.Test;

import ar.edu.taco.engine.StrykerStage;
import ar.edu.taco.stryker.api.impl.input.DarwinistInput;
import ar.edu.taco.stryker.api.impl.input.OpenJMLInput;
import ar.edu.taco.stryker.api.impl.input.OpenJMLInputWrapper;

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
					while (!willShutdown.get() || !queue.isEmpty()) {
						try {
//							int j = 0;
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
								
	//							fos.write("package \"\";\n".getBytes(Charset.forName("UTF-8")));
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
							boolean result = true;
							final String qualifiedName = editFileToPassToNextStage(tempFilename);
							OpenJMLInput input = null;
	
							String outputPath = filename.substring(0, filename.lastIndexOf(FILE_SEP) + 1);
							String currentClasspath = System.getProperty("java.class.path")+PATH_SEP+fileClasspath+PATH_SEP+System.getProperty("user.dir")+FILE_SEP+"generated";
							String[] jml4cArgs = {
									"-cp", currentClasspath,
	//								"-sourcepath", fileClasspath,
	//								"-rac",
	//								"-d", outputPath,
	//								"-noInternalSpecs",
	//								"-P",
									 tempFilename
							};
							log.debug("STRYKER: CLASSPATH = "+ currentClasspath);
							log.debug("STRYKER: SOURCEPATH = "+ CLASSPATH);
							log.debug("STRYKER: TEMPFILENAME = "+ tempFilename);
							log.debug("STRYKER: FILENAME = "+ filename);
							log.debug("STRYKER: File Classpath = "+ fileClasspath);
							log.debug("STRYKER: OUTPUT PATH = "+ outputPath);
//							Main compiler = new Main(new PrintWriter(new NullOutputStream()), new PrintWriter(new NullOutputStream())/*new PrintWriter(System.err)*/, 
//									false/*systemExit*/, null/*options*/, null/*progress*/);
							Main compiler = new Main(new PrintWriter(System.out), new PrintWriter(System.err), 
									false/*systemExit*/, null/*options*/, null/*progress*/);
							boolean exitValue = compiler.compile(jml4cArgs);
/**/						compiler = null;


							log.debug("compiled file with exit code = "+exitValue);
							try {
//								String qualifiedName = editFileToPassToNextStage(filename);
//								String currentJunit = editTestFileToCompile(junitFile, tempFilename, packageToWrite, wrapper.getMethod());
//								JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
//	/*mfrias*/					int compilationResult =	javaCompiler.run(null, null, null /*new NullOutputStream()*/, new String[]{"-classpath", currentClasspath, currentJunit});
//								if(compilationResult == 0) {
//									log.warn("junit counterexample compilation succeded");
//									ClassLoader cl = ClassLoader.getSystemClassLoader();
//									final ClassLoader cl2 = new URLClassLoader(new URL[]{new File(fileClasspath).toURI().toURL()}, cl);
//									ClassLoaderTools.addFile(fileClasspath);
//									String classLocation = packageToWrite+"."+MuJavaController.obtainClassNameFromFileName(tempFilename);
//									Class<?> clazz = cl2.loadClass(classLocation);
									log.info("preparing to run a test... "+packageToWrite+"."+MuJavaController.obtainClassNameFromFileName(tempFilename));
	//								Result result = null;
//									try {
//										ClassLoader cl = ClassLoader.getSystemClassLoader();
//										final ClassLoader cl2 = new URLClassLoader(new URL[]{new File(fileClasspath).toURI().toURL()}, cl);
//										ClassLoaderTools.addFile(fileClasspath);
//										String classLocation = packageToWrite+"."+MuJavaController.obtainClassNameFromFileName(tempFilename);
//										Class<?> clazz = cl2.loadClass(classLocation);
////										Class<?> c = Class.forName(qualifiedName);
//										Method[] meths = clazz.getMethods();
//										for (Method m : meths) {
//											System.out.println(m.getName());
//										}
//									} catch (ClassNotFoundException e) {
//										e.printStackTrace();
//									} catch (SecurityException e) {
//										e.printStackTrace();
//									}

									
									Class<?>[] junitInputs = wrapper.getJunitInputs();
									int index = 0;
									Class<?> junitInputClass = junitInputs[0];

									
									
									
	
									
									//for (int i = 0; i < 1000; i++) {
									
												
												for (final String methodName : map.keySet()) {
													int maxNumberAttemptedInputs = Math.min(StrykerStage.indexToLastJUnitInput, 9);
													log.debug("maxNumberAttemptedInputs: "+maxNumberAttemptedInputs);
													boolean failed = false;
													
													for (int attempted = 0; attempted <= maxNumberAttemptedInputs && !failed; attempted++){
														
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
//																		Object[] inputToInvoke = new Object[]{fileClasspath, qualifiedName, methodName};
																		long timeprev = System.currentTimeMillis();
																		methodToRunInCallable.invoke(oToRun, inputToInvoke);
																		long timepost = System.currentTimeMillis();
																		result = true;
																		log.debug("time taken: "+(timepost - timeprev));
																	} catch (IllegalAccessException e) {
																		log.debug("Entered IllegalAccessException");
				//														e.printStackTrace();
																	} catch (IllegalArgumentException e) {
																		log.debug("Entered IllegalArgumentException");
				//														e.printStackTrace();
																	} catch (InvocationTargetException e) {
																		log.debug("Entered InvocationTargetException");
																			e.printStackTrace();	
																		log.debug("QUIT BECAUSE OF JML RAC");
																	} catch (Throwable e) {
																		log.debug("Entered throwable");
				//														e.printStackTrace();
																		return false;
																	}
																return result;
															}
														};
														Future<Boolean> future = executor.submit(task);
														try {
															result = (Boolean)future.get(250, TimeUnit.MILLISECONDS);
														} catch (TimeoutException ex) {
															result = false;
															failed = true;
															log.error("timeouted file: "+filename);
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
														if (!result) {
															log.warn("TEST FAILED: :( for file: " + tempFilename + ", method: "+methodName + ", input: " + index);
															failed = true;
														} else {
															if (attempted == maxNumberAttemptedInputs) {
																log.warn("TEST PASSED: :) for file: " + tempFilename + ", method: "+methodName + ", input: " + index);
																input = map.get(methodName);
																DarwinistInput output = new DarwinistInput(input.getFilename(), input.getOriginalFilename(), wrapper.getConfigurationFile(), wrapper.getMethod(), input.getOverridingProperties(), qualifiedName, junitInputs, inputToInvoke);
																DarwinistController.getInstance().enqueueTask(output);
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

															//now change the input to be executed by the next method
				//											outputPath = junitFile.substring(0, junitFile.lastIndexOf(FILE_SEP) + 1);
				//											currentClasspath = System.getProperty("java.class.path")+PATH_SEP+fileClasspath+PATH_SEP+System.getProperty("user.dir")+FILE_SEP+"generated";
				//											jml4cArgs[1] = currentClasspath;
				//											log.debug("STRYKER: CLASSPATH = "+ currentClasspath);
				//											log.debug("STRYKER: SOURCEPATH = "+ CLASSPATH);
				//											log.debug("STRYKER: TEMPFILENAME = "+ tempFilename);
				//											log.debug("STRYKER: FILENAME = "+ filename);
				//											log.debug("STRYKER: File Classpath = "+ fileClasspath);
				//											log.debug("STRYKER: OUTPUT PATH = "+ outputPath);
				////											Main compiler = new Main(new PrintWriter(new NullOutputStream()), new PrintWriter(new NullOutputStream())/*new PrintWriter(System.err)*/, 
				////													false/*systemExit*/, null/*options*/, null/*progress*/);
				//											compiler = new Main(new PrintWriter(System.out), new PrintWriter(System.err), 
				//													false/*systemExit*/, null/*options*/, null/*progress*/);
				//											exitValue = compiler.compile(jml4cArgs);
				//											log.debug("compiled file with exit code = "+exitValue);
				////											String qualifiedName = editFileToPassToNextStage(filename);
				//											currentJunit = editTestFileToCompile(junitFile, tempFilename, packageToWrite, wrapper.getMethod());
				//											javaCompiler = ToolProvider.getSystemJavaCompiler();
				//				/*mfrias*/					compilationResult =	javaCompiler.run(null, null, null /*new NullOutputStream()*/, new String[]{"-classpath", currentClasspath, currentJunit});
				//											if(compilationResult == 0) {
				//												log.warn("junit counterexample compilation succeded");
				//												cl = ClassLoader.getSystemClassLoader();
				//												final ClassLoader cl3 = new URLClassLoader(new URL[]{new File(fileClasspath).toURI().toURL()}, cl);
				////												ClassLoaderTools.addFile(fileClasspath);
				//												clazz = cl3.loadClass(packageToWrite+"."+MuJavaController.obtainClassNameFromFileName(junitFile));
				//												log.info("preparing to run a test... "+packageToWrite+"."+MuJavaController.obtainClassNameFromFileName(junitFile));
				////												Result result = null;
				//												final Object oToRun2 = clazz.newInstance();
				//											} else {
				//												log.warn("compilation failed");
				//											}
														}
													}	
												}
												
												
									
									
									
//									}
									
//									int j = 0;
									
									
									
									
									
//								} else {
//									log.warn("compilation failed");
//								}
//								File originalFile = new File(tempFilename);
//								originalFile.delete();
												
							consumedMutants = consumedMutants + map.size();
							
							log.warn("Mutants consumed by RAC: "+consumedMutants);
	
//							} catch (ClassNotFoundException e) {
//								e.printStackTrace();
//							} catch (IOException e) {
//								e.printStackTrace();
							} catch (IllegalArgumentException e) {
//								e.printStackTrace();
							} catch (Exception e) {
//								e.printStackTrace();
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					log.warn("Shutting down Darwinist Controller");
					DarwinistInput output = new DarwinistInput(null, null, null, null, null, null, null, null);
					DarwinistController.getInstance().enqueueTask(output);
	//				DarwinistController.getInstance().shutdown();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
						
						
						
//						
//						
//						
//						
//						
//						
//						
//						
//						
//						
//						while (index < junitFiles.length && junitFiles[index] != null && result) {
//							String junitFile = junitFiles[index];
//							String outputPath = junitFile.substring(0, junitFile.lastIndexOf(FILE_SEP) + 1);
//							String currentClasspath = System.getProperty("java.class.path")+PATH_SEP+fileClasspath+PATH_SEP+System.getProperty("user.dir")+FILE_SEP+"generated";
//							String[] jml4cArgs = {
//									"-cp", currentClasspath,
//	//								"-sourcepath", fileClasspath,
//	//								"-rac",
//	//								"-d", outputPath,
//	//								"-noInternalSpecs",
//	//								"-P",
//									 tempFilename
//							};
//							log.debug("STRYKER: CLASSPATH = "+ currentClasspath);
//							log.debug("STRYKER: SOURCEPATH = "+ CLASSPATH);
//							log.debug("STRYKER: TEMPFILENAME = "+ tempFilename);
//							log.debug("STRYKER: FILENAME = "+ filename);
//							log.debug("STRYKER: File Classpath = "+ fileClasspath);
//							log.debug("STRYKER: OUTPUT PATH = "+ outputPath);
//	//						Main compiler = new Main(new PrintWriter(new NullOutputStream()), new PrintWriter(new NullOutputStream())/*new PrintWriter(System.err)*/, 
//	//								false/*systemExit*/, null/*options*/, null/*progress*/);
//							Main compiler = new Main(new PrintWriter(System.out), new PrintWriter(System.err), 
//									false/*systemExit*/, null/*options*/, null/*progress*/);
//							boolean exitValue = compiler.compile(jml4cArgs);
//							log.debug("compiled file with exit code = "+exitValue);
//							try {
////								String qualifiedName = editFileToPassToNextStage(filename);
//								String currentJunit = editTestFileToCompile(junitFile, tempFilename, packageToWrite, wrapper.getMethod());
//								JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
//	/*mfrias*/					int compilationResult =	javaCompiler.run(null, null, null /*new NullOutputStream()*/, new String[]{"-classpath", currentClasspath, currentJunit});
//								if(compilationResult == 0) {
//									log.warn("junit counterexample compilation succeded");
//									ClassLoader cl = ClassLoader.getSystemClassLoader();
//									ClassLoader cl2 = new URLClassLoader(new URL[]{new File(fileClasspath).toURI().toURL()}, cl);
//	//								ClassLoaderTools.addFile(fileClasspath);
//									final Class<?> clazz = cl2.loadClass(packageToWrite+"."+MuJavaController.obtainClassNameFromFileName(junitFile));
//									log.info("preparing to run a test... "+packageToWrite+"."+MuJavaController.obtainClassNameFromFileName(junitFile));
//	//								Result result = null;
//									final Object oToRun = clazz.newInstance();
//									Method[] methods = clazz.getMethods();
//									Method methodToRun = null;
//	//								Result result = JUnitCore.runClasses(clazz);
//									for(Method m : methods) {
//										if(m.isAnnotationPresent(Test.class)) {
//											methodToRun = m;
//										}
//									}
//									final Method methodToRunInCallable = methodToRun; 
//									methodToRunInCallable.setAccessible(true);
//									
//									for(final String methodName : map.keySet()) {
//											Callable<Boolean> task = new Callable<Boolean>() {
//												public Boolean call() {
//													boolean result = false;
//														try {
//															runningThread = Thread.currentThread();
//															long timeprev = System.currentTimeMillis();
//															methodToRunInCallable.invoke(oToRun, methodName);
//															long timepost = System.currentTimeMillis();
//															result = true;
//															log.warn("time taken: "+(timepost - timeprev));
//														} catch (IllegalAccessException e) {
//															log.debug("Entro por IllegalAccessException");
//	//														e.printStackTrace();
//														} catch (IllegalArgumentException e) {
//															log.debug("Entro por IllegalArgumentException");
//	//														e.printStackTrace();
//														} catch (InvocationTargetException e) {
//															log.debug("Entro por InvocationTargetException");
//	//															e.printStackTrace();	
//															log.warn("QUIT BECAUSE OF JML RAC");
//														} catch (Throwable e) {
//															log.debug("Entro por throwable");
//	//														e.printStackTrace();
//															return false;
//														}
//													return result;
//												}
//											};
//											Future<Boolean> future = executor.submit(task);
//											try {
//												result = (Boolean)future.get(250, TimeUnit.MILLISECONDS);
//											} catch (TimeoutException ex) {
//												log.error("timeouted file: "+filename);
//												runningThread.stop();
//												executor.shutdownNow();
//												executor = Executors.newSingleThreadExecutor();
//												// handle the timeout
//											} catch (InterruptedException e) {
//												
//												log.warn("Interrupted");
//												// handle the interrupts
//											} catch (ExecutionException e) {
//												// handle other exceptions
//												log.debug("Excecution Exception");
//											} catch (Throwable e) {
//												log.debug("Exception");
//												// handle other exceptions
//											} finally {
//												future.cancel(true); // may or may not desire this
//												
//											}
//											log.info("test ran");
//											if(!result) {
//												log.warn("TEST FAILED: :( for file: " + tempFilename + ", method: "+methodName + ", input: " + junitFile);
//											} else {
//												log.warn("TEST PASSED: :) for file: " + tempFilename + ", method: "+methodName + ", input: " + junitFile);
//												index++;
//												input = map.get(methodName);
//											}
//									}
//								} else {
//									log.warn("compilation failed");
//								}
//	//							File originalFile = new File(tempFilename);
//	//							originalFile.delete();
//	
//							} catch (ClassNotFoundException e) {
//	//							e.printStackTrace();
//							} catch (IOException e) {
//	//							e.printStackTrace();
//							} catch (IllegalArgumentException e) {
//	//							e.printStackTrace();
//							} catch (Exception e) {
//	//							e.printStackTrace();
//							}
//						}
//						if (result){
//							DarwinistInput output = new DarwinistInput(input.getFilename(), input.getOriginalFilename(), wrapper.getConfigurationFile(), wrapper.getMethod(), input.getOverridingProperties(), qualifiedName);
//							DarwinistController.getInstance().enqueueTask(output);
//							log.warn("Enqueded task to Darwinist Controller");
//						}
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//				log.info("Shutting down Darwinist Controller");
//				DarwinistInput output = new DarwinistInput(null, null, null, null, null, null);
//				DarwinistController.getInstance().enqueueTask(output);
////				DarwinistController.getInstance().shutdown();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}

			public String editFileToPassToNextStage(String filename) {
				String classPackage = filename.substring(filename.indexOf(FILE_SEP+"a")+1, filename.lastIndexOf(FILE_SEP)).replaceAll(FILE_SEP, ".");
//				File destFile = new File(filename+"_tmp");
//				String packageSentence = "package "+classPackage+";\n";
//				try {
//					destFile.createNewFile();
//					FileOutputStream fos = new FileOutputStream(destFile);
//					boolean packageAlreadyWritten = false;
//					Scanner scan = new Scanner(new File(filename));
//					scan.useDelimiter("\n");
//					while(scan.hasNext()){
//						String str = scan.next();
//						if( str.contains("package") && !packageAlreadyWritten){
//							fos.write(packageSentence.getBytes(Charset.forName("UTF-8")));
//							packageAlreadyWritten = true;
//						} else if (str.contains("import") && !packageAlreadyWritten) {
//							fos.write(packageSentence.getBytes(Charset.forName("UTF-8")));
//							fos.write((scan.next() + "\n").getBytes(Charset.forName("UTF-8")));
//							packageAlreadyWritten = true;
//						} else {
//							fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
//						}
//					}
//					fos.close();
//					File originalFile = new File(filename);
//					originalFile.delete();
//
//					File newFile = new File(filename);
//					newFile.createNewFile();
//					
//					Files.move(destFile, newFile);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
				return classPackage+"."+MuJavaController.obtainClassNameFromFileName(filename);
				
			}

//			private String editTestFileToCompile(String junitFile, String filename, String classPackage, String methodName) {
//				String className = MuJavaController.obtainClassNameFromFileName(filename);
//				String tmpDir = filename.substring(0, filename.lastIndexOf(FILE_SEP));
//				File destFile = new File(tmpDir,MuJavaController.obtainClassNameFromFileName(junitFile)+".java");
//				String packageSentence = "package "+classPackage+";\n";
//				try {
//					destFile.createNewFile();
//					FileOutputStream fos = new FileOutputStream(destFile);
//					boolean packageAlreadyWritten = false;
//					Scanner scan = new Scanner(new File(junitFile));
//					scan.useDelimiter("\n");
//					boolean nextToTest = false;
//					while(scan.hasNext()){
//						String str = scan.next();
//						if( nextToTest ) {
//							str = str.replace("()","(String methodName)");
//							fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
//							nextToTest = false;
//						} else if( str.contains("package") && !packageAlreadyWritten){
//							fos.write(packageSentence.getBytes(Charset.forName("UTF-8")));
//							packageAlreadyWritten = true;
//						} else if (str.contains("import") && !packageAlreadyWritten) {
//							fos.write(packageSentence.getBytes(Charset.forName("UTF-8")));
//							fos.write((scan.next() + "\n").getBytes(Charset.forName("UTF-8")));
//							packageAlreadyWritten = true;
//						} else if( str.matches(".*(?i)[\\.a-z0-9\\_]*"+className+"(?=[^a-z0-9\\_\\.]).*")){
//							str = str.replaceAll("(?i)[\\.a-z0-9\\_]*"+className+"(?=[^a-z0-9\\_\\.])", classPackage+"."+className);
//							str = str.replace("\""+methodName+"\"", "methodName");
//							fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
//						} else if (str.contains("e.printStackTrace();")) {
//							fos.write(("throw(new java.lang.RuntimeException(e));" + "\n").getBytes(Charset.forName("UTF-8")));
//						} else {
//							if (str.contains("@Test")) {
//								nextToTest = true;
//							}
//							fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
//						}
//					}
//					fos.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				return destFile.toString();
//				
//			}
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
