package ar.edu.taco.stryker.api.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import mujava.OpenJavaException;
import mujava.api.Api;
import mujava.api.Mutant;
import mujava.api.MutantIdentifier;
import mujava.api.MutantsInformationHolder;
import openjava.ptree.CompilationUnit;
import openjava.ptree.ParseTreeException;

import org.apache.log4j.Logger;

import ar.edu.taco.stryker.api.impl.input.MuJavaInput;
import ar.edu.taco.stryker.api.impl.input.OpenJMLInput;
import ar.edu.taco.stryker.api.impl.input.OpenJMLInputWrapper;
import ar.edu.taco.stryker.exceptions.FatalStrykerStageException;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.io.Files;

public class MuJavaController extends AbstractBaseController<MuJavaInput> {
	
//	private static AtomicInteger compilationFailCount = new AtomicInteger(0);

	private int maxMethodsInFile = 50;
	
	public void setMaxMethodsInFile(int maxMethodsInFile) {
		this.maxMethodsInFile = maxMethodsInFile;
	}
	
	private static MuJavaController instance;
	
	private int i = 0;

	private static Logger log = Logger.getLogger(MuJavaController.class);
	
	public static AtomicInteger mutantCount = new AtomicInteger(0);
	
	public synchronized static MuJavaController getInstance() {
		if (instance == null) {
			instance = new MuJavaController();
		}
		return instance;
	}

	private MuJavaController() {

	}

	@Override
	protected Runnable getRunnable() {
		return new Runnable() {

			@Override
			public void run() {
				try {
					MuJavaInput input = queue.take();
					while (!willShutdown.get() || !queue.isEmpty()) {
						log.debug("Executing input: "+input);
						log.warn("Actual size: "+queue.size());
						List<MuJavaInput> inputs = execute(input);
						log.debug("Adding new inputs: "+inputs.size());
						queue.addAll(inputs);
						input = queue.poll();
						
						if (input == null) {
							if(classToMutate != null && !jmlInputs.isEmpty()){
								OpenJMLInputWrapper wrapper = createJMLInputWrapper(jmlInputs, classToMutate);
								log.info("Creating output for OpenJMLController");
								OpenJMLController.getInstance().enqueueTask(wrapper);
								log.debug("Adding task to the OpenJMLController");
							}
							jmlInputs.clear();
							log.debug("Input was null. Shutdown in progress...");
							shutdown();
							OpenJMLInputWrapper output = new OpenJMLInputWrapper(null, null, null, null, null, null);
							log.debug("Enqueuing task in the OpenJMLController");
							OpenJMLController.getInstance().enqueueTask(output);
//							log.warn("Shutting down OpenJMLController Controller");
//							OpenJMLController.getInstance().shutdown();
						}
					}
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
//					e1.printStackTrace();
				}
			}
		};
	}

	@Override
	protected int getQtyOfThreads() {
		return 1;
	}

	private static final String FILE_SEP = System.getProperty("file.separator");

	// If it is set to false then it will be assumed that if two hashes are
	// equal then that means that the two files are equal. Which of course
	// it is not necessarily true.
	private static final boolean EXTRA_CHECK = true;

	private static final int NOT_PRESENT = -1;

	public static String obtainClassNameFromFileName(String fileName) {
		int lastBackslash = fileName.lastIndexOf("/");
		int lastDot = fileName.lastIndexOf(".");

		if (lastBackslash == NOT_PRESENT) {
			lastBackslash = 0;
		} else {
			lastBackslash += 1;
		}
		if (lastDot == NOT_PRESENT) {
			lastDot = fileName.length();
		}

		return fileName.substring(lastBackslash, lastDot);
	}

	private Map<String, Integer> filenameToMutatedLine = Maps.newConcurrentMap();
	private Map<MsgDigest, String> filesHash = Maps.newConcurrentMap();
	private List<OpenJMLInput> jmlInputs = new ArrayList<OpenJMLInput>(maxMethodsInFile);
	String classToMutate;

	public List<MuJavaInput> execute(MuJavaInput input) {

			File fileToMutate;
			String methodToCheck;
			HashSet<Mutant> mutOps;
			int generationsLeft;
			MuJavaInput muJavaInput;
	
			fileToMutate = new File(input.getFilename());
			if (!fileToMutate.exists()) {
				throw new IllegalStateException("The file " + input.getFilename()
						+ " doesn't exist. Can't continue.");
//				return Lists.newArrayList();
			}
			methodToCheck = input.getMethod();
			mutOps = Sets.newHashSet(input.getMutantsToApply());
			classToMutate = obtainClassNameFromFileName(input.getFilename());
			generationsLeft = input.getQtyOfGenerations();
			muJavaInput = input;
	
			final File tmpDir = createWorkingDirectory();
	
			log.debug("Generating mutants...");
			MutantsInformationHolder genMutants = generateMutants(fileToMutate,
					methodToCheck, mutOps, classToMutate);
			
			log.warn("MutantCount: " + mutantCount.addAndGet(genMutants.getMutantsIdentifiers().size()));
	
			log.info("Generation finished. Generated mutants: "+genMutants.getMutantsIdentifiers().size());
			List<MuJavaInput> nextGenerationInputs = new ArrayList<MuJavaInput>(
					genMutants.getMutantsIdentifiers().size());
	
			OpenJMLController outputController = OpenJMLController.getInstance();
	
			log.debug("Creating files for mutants");
			for (MutantIdentifier mutantIdentifier : genMutants
					.getMutantsIdentifiers()) {
				log.debug("Check that mutant is unique: "+ mutantIdentifier);
				String dirString = tmpDir.toString();
				File tempFile = createTempFile(classToMutate, generationsLeft, i++, fileToMutate
						.getName().substring(0, 8), tmpDir, "a"+dirString.substring(dirString.lastIndexOf(FILE_SEP)+1).replaceAll("-", ""));
				DigestOutputStream digestOutputStream = getDigestOutputStream(tempFile);
				int mutatedLine = writeMutant(genMutants.getCompUnit(),
						mutantIdentifier, digestOutputStream);
	
				MsgDigest msgDigest = new MsgDigest(digestOutputStream
						.getMessageDigest().digest());
				log.debug("generationsLeft= "+generationsLeft);
				log.debug("fileToMutate= "+fileToMutate);
				log.trace("fileToMutate.getAbsolutePath()= "+fileToMutate.getAbsolutePath());
				log.trace("mutatedLine= "+mutatedLine);
				log.trace("filenameToMutatedLine.get(fileToMutate.getAbsolutePath())= "+filenameToMutatedLine.get(fileToMutate
								.getAbsolutePath()));
				Integer lastMutatedLine = filenameToMutatedLine.get(fileToMutate.getAbsolutePath());
				log.debug("last mutated line = "+lastMutatedLine);
				if (
						/*generationsLeft != 0
						&&*/ (lastMutatedLine != null && (lastMutatedLine > mutatedLine) 
						|| filesHash.containsKey(msgDigest) ) ) {
					if(lastMutatedLine != null) {
						log.debug("lastmutadtedline > mutadtedline = "+(lastMutatedLine > mutatedLine));
					} else {
						log.debug("lastmutadtedline  = null");
					}
					log.debug("filesHash.containsKey(msgDigest) = "+filesHash.containsKey(msgDigest));
					if (EXTRA_CHECK && filesHash.containsKey(msgDigest)) {
						if (isFalseDuplicate(filesHash.get(msgDigest), tempFile)) {
							// If it is a false duplicate we don't have to delete
							// the file
							log.debug("False duplicated file");
							continue;
						}
					}
					// We have to delete this new mutant since it will be a
					// duplicate
					log.debug("Duplicated file");
					mutantCount.decrementAndGet();
					if (!tempFile.delete()) {
						log.error("Couldn't remove file " + tempFile.getName());
					}
					continue;
				}
				
				String currentClasspath = System.getProperty("java.class.path")+OpenJMLController.PATH_SEP+System.getProperty("user.dir")+FILE_SEP+"generated";
				
				JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
				int compilationResult =	javaCompiler.run(null, new NullOutputStream(), new NullOutputStream(),  new String[]{"-classpath", currentClasspath, tempFile.getAbsolutePath()});
				
				if ( compilationResult == 0 ){
					log.info("Compilation succeeded. Adding this file");
					
					filesHash.put(msgDigest, tempFile.getAbsolutePath());
					filenameToMutatedLine.put(tempFile.getAbsolutePath(), mutatedLine);
					OpenJMLInput output = new OpenJMLInput(tempFile.getAbsolutePath(),
							muJavaInput.getJunitFile(), muJavaInput.getMethod(),
							muJavaInput.getConfigurationFile(),
							muJavaInput.getOverridingProperties(),
							muJavaInput.getOriginalFilename());
					log.debug("Adding task to the list");
					jmlInputs.add(output);
					if ((generationsLeft - 1) > 0) {
						MuJavaInput mji = new MuJavaInput(tempFile.getAbsolutePath(),
								muJavaInput.getMethod(), muJavaInput.getJunitFile(),
								muJavaInput.getMutantsToApply(),
								muJavaInput.getQtyOfGenerations() - 1,
								muJavaInput.getConfigurationFile(),
								muJavaInput.getOverridingProperties(),
								muJavaInput.getOriginalFilename(), output);
						nextGenerationInputs.add(mji);
					}
					if(jmlInputs.size() >= maxMethodsInFile) {
						OpenJMLInputWrapper wrapper = createJMLInputWrapper(jmlInputs, classToMutate);
						log.info("Creating output for OpenJMLController");
						OpenJMLController.getInstance().enqueueTask(wrapper);
						log.debug("Adding task to the OpenJMLController");
						jmlInputs.clear();
					}
				} 
	//			else {
	//				log.warn("Compilation failed. Ignoring this file. Compilations failed: " + compilationFailCount.incrementAndGet() );
	//			}
				
			}
			return nextGenerationInputs;
	}

	private OpenJMLInputWrapper createJMLInputWrapper(
			List<OpenJMLInput> jmlInputs, String classToMutate) {
		if( jmlInputs.isEmpty() ){
			throw new IllegalArgumentException("You must provide at least one OpenJMLInput.");
		}
		OpenJMLInput oji = jmlInputs.remove(0);
		
		String originalFilename = oji.getOriginalFilename();
		String originalMethod = oji.getMethod();
		File newDir = createWorkingDirectory();
		String dirString = newDir.getAbsolutePath();
		String newPath = "a"+dirString.substring(dirString.lastIndexOf(FILE_SEP)+1).replaceAll("-", "")+(FILE_SEP+"aOpenJMLInWrap" + i);
		File newDir2 = new File(newDir, newPath);
		
		Map<String,OpenJMLInput> map = new HashMap<String, OpenJMLInput>();
		int index = 0;
		try {
			newDir2.mkdirs();
			File newFile = new File(newDir2, classToMutate + ".java");
			if(!newFile.createNewFile()){
				throw new IllegalStateException("Couldn't create the file");
			}
			File from = new File(oji.getFilename());
			String methodName = oji.getMethod();
			index++;
			OpenJMLInput newInput = new OpenJMLInput(oji.getFilename(), oji.getJunitFile(), methodName, oji.getConfigurationFile(), oji.getOverridingProperties(), oji.getOriginalFilename());
			map.put(methodName, newInput);
			Files.copy(from, newFile);
			for(OpenJMLInput input: jmlInputs) {
				try {
					methodName = input.getMethod() + (index++);
	//				System.out.println(newFile.getAbsolutePath());
					String codeToAdd = getMethod(input.getFilename(), input.getMethod());
					insertNewMethod(input.getMethod(), methodName, newFile.getAbsolutePath(), codeToAdd);
					newInput = new OpenJMLInput(input.getFilename(), input.getJunitFile(), methodName, input.getConfigurationFile(), input.getOverridingProperties(), input.getOriginalFilename());
					map.put(methodName, newInput);
				} catch (NoSuchElementException e) {
					
				}
			}
			OpenJMLInputWrapper ojiw = new OpenJMLInputWrapper(newFile.getPath(), oji.getJunitFile(), oji.getConfigurationFile(), oji.getOverridingProperties(), originalMethod, map);
			return ojiw;
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
	
	private String getMethod(String filename, String methodName) throws FileNotFoundException {
		StringBuilder builder = new StringBuilder();
		Scanner scan = new Scanner(new File(filename));
		scan.useDelimiter("\n");
		boolean methodFound = false;
		boolean canBreak = true;
		int balance = -1;
		int j = 0;
		while(scan.hasNext()){
			String str = scan.next();
			if(methodFound) {
				if(str.contains("this.listSize = this.listSize - 1;") ||
						str.contains("this.modCount = this.modCount + 1;") ||
						str.contains("if (this.cacheSize < this.maximumCacheSize) {") ||
						str.contains("this.cacheSize = this.cacheSize + 1;")) {
					j++;
				}
				
			}
			if(str.contains(" " + methodName)) {
				methodFound = true;
				if(!str.contains("{")) {
					canBreak = false;
				}
			}
			if(str.contains("{")) {
//				System.out.println("1: "+((" "+str+" ").split("\\{").length - 1));
				balance += ((" "+str+" ").split("\\{").length - 1);
				if(!methodFound) {
					builder = new StringBuilder();
				}
				canBreak = true;
			}
			if(str.contains("}")) {
//				System.out.println("1: "+((" "+str+" ").split("\\}").length - 1));
				balance -= ((" "+str+" ").split("\\}").length - 1);
				if(!methodFound) {
					builder = new StringBuilder();
				}
				canBreak = true;
			}
			if((balance == 0 && !str.contains("}")) || methodFound) {
				builder.append(str+"\n");
				if(balance == 0 && methodFound && canBreak) {
					break;
				}
			}
		}
		
		if(builder.length() == 0) {
			throw new NoSuchElementException("The method name was not found in this file");
		}
		return builder.toString();
	}
	
	private void insertNewMethod(String originalMethodName, String newMethodName, String filename, String codeToAdd) throws IOException {
		codeToAdd = codeToAdd.replaceAll(originalMethodName, newMethodName);
		String tempFileName = filename + "_temp";
		File destFile = new File(tempFileName);
		destFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(destFile);
		Scanner scan = new Scanner(new File(filename));
		scan.useDelimiter("\n");
		boolean classFound = false;
		while(scan.hasNext()){
			String str = scan.next();
			if(!classFound && str.contains(" class ")) {
				classFound = true;
				fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
				if(!str.contains("{")) {
					fos.write((scan.next() + "\n").getBytes(Charset.forName("UTF-8")));
				}
			} else if (classFound){
				fos.write(codeToAdd.getBytes(Charset.forName("UTF-8")));
				fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
				break;
			} else {
				fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
			}
		}
		
		while(scan.hasNext()){
			fos.write((scan.next() + "\n").getBytes(Charset.forName("UTF-8")));
		}
		fos.close();
		File originalFile = new File(filename);
		originalFile.delete();

		File newFile = new File(filename);
		newFile.createNewFile();
		
		Files.move(destFile, newFile);
	}

	/**
	 * Used in case the EXTRA_CHECK flag is on. In cases were two hashes are
	 * equal this method is called so as to assure that it is not a false
	 * duplicate (equal hash but different file). Returns true if files are
	 * equal or false if otherwise.
	 */
	private boolean isFalseDuplicate(String originalFileName, File tempFile)
			throws IllegalStateException {
		File original = new File(originalFileName);
		try {
			if (!Files.equal(original, tempFile)) {
				log.error("FALSE DUPICATE");
				log.error("Original:" + original.getAbsolutePath());
				log.error("False duplicate:" + tempFile.getAbsolutePath());
				return true;
			}
			return false;
		} catch (IOException e) {
			return false;
//			throw new IllegalStateException(
//					"An error occured while opening files " + originalFileName
//							+ " and " + tempFile.getName());
		}

	}

	// private File[] getFilesOfLastGeneration(int genNum, File tmpDir) {
	// File[] filesOfLastGen;
	// if (genNum == 0) {
	// // We are on the initial generation.
	// filesOfLastGen = new File[1];
	// filesOfLastGen[0] = fileToMutate;
	// } else {
	// filesOfLastGen = tmpDir.listFiles(new GenFileFilter(genNum - 1));
	// }
	// return filesOfLastGen;
	// }

	private DigestOutputStream getDigestOutputStream(File tempFile)
			throws IllegalStateException {
		try {
			return new DigestOutputStream(new FileOutputStream(tempFile),
					MessageDigest.getInstance("MD5"));
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("MD5 implementation not found.");
		} catch (FileNotFoundException e) {
			throw new IllegalStateException("Temp file not found.");
		}
	}

	private MutantsInformationHolder generateMutants(File mutatedFile,
			String method, HashSet<Mutant> mutOps, String classToMutate)
			throws IllegalStateException {
		try {
			return Api.generateMutants(mutatedFile, classToMutate, method,
					mutOps);
		} catch (OpenJavaException e1) {
			throw new IllegalStateException("Failed to generate mutants.");
		}
	}

	private int writeMutant(CompilationUnit compUnit,
			MutantIdentifier mutantIdentifier,
			DigestOutputStream digestOutputStream) throws IllegalStateException {
		try {
			return Api.writeMutant(compUnit, mutantIdentifier, new PrintWriter(
					digestOutputStream));
		} catch (ParseTreeException e) {
			throw new IllegalStateException("Failed to parse java file.");
		}
	}

	/**
	 * Sets up the necessary work environment prior to generating the mutants.
	 * 
	 * @throws FatalStrykerStageException
	 */
	private File createWorkingDirectory() throws IllegalStateException {
		File tmpDir = null;
		if ((tmpDir = Files.createTempDir()) == null) {
			throw new IllegalStateException(
					"Couldn't create work environment: failed"
							+ "to create temp directory");
		}
	
		 log.trace("Using directory " + tmpDir + " as working directory.");
		return tmpDir;
	}

	private File createTempFile(String className, int generation, int index, String from, File dir, String uniqueIdentifier)
			throws IllegalStateException {
		StringBuilder sb = new StringBuilder();
		sb.append("genLeft").append(generation).append("_index").append(index);
		if (from == null || from.isEmpty()) {
			sb.append("_from").append(from);
		}
		File tempDir = new File(dir, uniqueIdentifier + FILE_SEP +sb.toString() /*+ OpenJMLController.MUTANTS_DEST_PACKAGE.replaceAll("\\.", FILE_SEP)*/);
		File tempFile = new File(tempDir.getAbsolutePath() + FILE_SEP + className + ".java");
		log.debug("The tempfile is: "+tempFile.getAbsolutePath());
		try {
			Files.createParentDirs(tempFile);
			tempFile.createNewFile();
		} catch (IOException e) {
			throw new IllegalStateException();
		}
		return tempFile;
	}

	public class GenFileFilter implements FilenameFilter {

		private int genNum;

		public GenFileFilter(int genNum) {
			this.genNum = genNum;
		}

		@Override
		public boolean accept(File dir, String name) {
			return name.startsWith("gen" + genNum + "_");
		}
	}
	
	/**
	 * byte[] wrapper so as to be able to add digest to collections.
	 */
	public static class MsgDigest {

		private byte[] digest;

		public MsgDigest(byte[] digest) {
			this.digest = digest;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Arrays.hashCode(digest);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			MsgDigest other = (MsgDigest) obj;
			if (!Arrays.equals(digest, other.digest))
				return false;
			return true;
		}
	}

}
