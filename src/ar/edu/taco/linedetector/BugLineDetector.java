package ar.edu.taco.linedetector;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.multijava.mjc.JCompilationUnitType;

import edu.mit.csail.sdg.alloy4.Pair;
import edu.mit.csail.sdg.alloy4compiler.ast.ExprVar;
import edu.mit.csail.sdg.annotations.parser.JForgeParser.compilationUnit_return;
import ar.edu.jdynalloy.JDynAlloyConfig;

import com.google.common.collect.ImmutableSet;

import ar.edu.taco.TacoAnalysisResult;
import ar.edu.taco.TacoConfigurator;
import ar.edu.taco.TacoMain;
import ar.edu.taco.engine.AlloyStage;
import ar.edu.taco.engine.JUnitStage;
import ar.edu.taco.engine.SnapshotStage;
import ar.edu.taco.engine.StrykerStage;
import ar.edu.taco.jml.parser.JmlParser;
import ar.edu.taco.junit.RecoveredInformation;
import ar.edu.taco.stryker.api.impl.DarwinistController;
import ar.edu.taco.stryker.api.impl.StrykerJavaFileInstrumenter;
import ar.edu.taco.stryker.api.impl.input.DarwinistInput;
import ar.edu.taco.stryker.api.impl.input.OpenJMLInput;
import ar.edu.taco.stryker.api.impl.input.OpenJMLInputWrapper;
import ar.edu.taco.utils.FileUtils;
import ar.uba.dc.rfm.dynalloy.analyzer.AlloyAnalysisResult;
import edu.mit.csail.sdg.alloy4.Pair;
import edu.mit.csail.sdg.alloy4.Pos;
import edu.mit.csail.sdg.alloy4compiler.translator.A4Options;

public class BugLineDetector {

	private static Logger log = Logger.getLogger(BugLineDetector.class);

	private static String TACO_ALS_OUTPUT = "output/output.als";

	private static String ORIGINAL_ALS_OUTPUT = "output/originalOutput.als";

	private static String SEQUENTIAL_ALS_OUTPUT = "output/sequentialOutput.als";

	private static String TEST_CLASS_PATH_LOCATION = "tests/roops/core/objects/SinglyLinkedList.java";
	private static String TEST_CLASS_PACKAGE = "roops.core.objects";

	private List<JCompilationUnitType> compilation_units = null;

	private String classToCheck = null;

	private String methodToCheck = null;

	private Properties overridingProperties = null;

	private String configFile = null;
	
	private Map<Integer, Integer> instrumentedMap = new TreeMap<Integer, Integer>();
	
	public BugLineDetector(String configFile, Properties overridingProperties,
			String methodToCheck) {
		this.methodToCheck = methodToCheck;
		this.overridingProperties = overridingProperties;
		this.configFile = configFile;
		this.overridingProperties.put("generateUnitTestCase", true);
	}


	public void run(String classFilename) { // TODO verify className !=
											// classToCheck
		Set<Collection<Integer>> errorLines = new HashSet<Collection<Integer>>();

		try {
			// originalAls = TacoTranslate() --- ~Postcondition
			log.info("Traduciendo a Alloy.");
//			FileUtils.copyFile(TEST_CLASS_PATH_LOCATION.replace(".java", "_bak.java"), TEST_CLASS_PATH_LOCATION);
//			removeComments();
			LoopUnrollTransformation.javaUnroll(7, TEST_CLASS_PATH_LOCATION.replace(".java", "_bak.java"), TEST_CLASS_PATH_LOCATION);
			instrumentBranchCoverage();
			translateToAlloy(configFile, overridingProperties);
			try {
				FileUtils.copyFile(TACO_ALS_OUTPUT, ORIGINAL_ALS_OUTPUT);
			} catch (IOException e) {
				e.printStackTrace();
			}
			log.info("Traducción realizada.");
			log.debug("");
			AlloyStage originalAlloyStage = new AlloyStage(ORIGINAL_ALS_OUTPUT);
			log.info("Ejecutando Alloy.");
			originalAlloyStage.execute();
			log.info("Alloy ejecuto.");
			AlloyAnalysisResult alloyAnalysisResult = originalAlloyStage
					.get_analysis_result();
			log.info("resultado analizado.");
			TacoAnalysisResult tacoAnalysisResult = new TacoAnalysisResult(
					alloyAnalysisResult);
			log.info("Ejecucion terminada.");
			classToCheck = TacoConfigurator.getInstance().getString(
					TacoConfigurator.CLASS_TO_CHECK_FIELD);
			compilation_units = JmlParser.getInstance().getCompilationUnits();
			int i = 0;
			System.out.println("Alloy dio: " + alloyAnalysisResult.isSAT());
			while (alloyAnalysisResult.isSAT()) {
				// badInput = alloy(varAls)
				log.info("Generando  JUnit");
				Class<?>[] jUnitInputExposingBug = generateJUnitInput(tacoAnalysisResult);
				log.info("Generando OjiWrapper");
				OpenJMLInputWrapper ojiWrapper = generateInputWrapper(
						classFilename, jUnitInputExposingBug);
				// linearCode = lulasProgram(jUnitInputExposingBug,
				// classToCheck)
				log.info("Generando Codigo secuencial.");
				ojiWrapper = generateSequentialCode(ojiWrapper);
				// badAls = generate(contrato, linearCode, badInput) ---
				// Postcondition
				AlloyAnalysisResult inputBugPathAls = generateSeqAls(
						ojiWrapper, true);
				do {
					//uCore = alloy(badAls)	
					Pair<Set<Pos>, Set<Pos>> uCore = inputBugPathAls.getAlloy_solution().highLevelCore();
					//errorlines += codeLines(uCore)
					errorLines.add(getErrorLines(SEQUENTIAL_ALS_OUTPUT, uCore));
					//analizedPostConditions += postCondition(uCore)
					//alsToExposeNewBug = negatePost(badAls - analizedPosts) --- ~Postcondition
					//badInput = alloy(alsToExposeNewBug)
					//badAls = generate(Contrato - analizedPosts, linearCode, badInput)
				} while (inputBugPathAls.isSAT() /* isSat */);
				// originalAls -= linearCode // restringir el camino tomado
				banAlsGoals();
				// AnalizedPosts = 0
				i = 1;
				originalAlloyStage = new AlloyStage(ORIGINAL_ALS_OUTPUT);
				log.info("Ejecutando Alloy.");
				originalAlloyStage.execute();
				log.info("Alloy ejecuto.");
				alloyAnalysisResult = originalAlloyStage
						.get_analysis_result();
				log.info("resultado analizado.");
				tacoAnalysisResult = new TacoAnalysisResult(
						alloyAnalysisResult);
				log.info("Ejecucion terminada.");
				compilation_units = JmlParser.getInstance().getCompilationUnits();
				// Restore file
				FileUtils.copyFile(TEST_CLASS_PATH_LOCATION.replace(".java", ".bak"), TEST_CLASS_PATH_LOCATION);
			}
			System.out.println("FINISHED. ERROR LINES:");
			System.out.println(errorLines);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private void banAlsGoals() throws IOException {
		Set<Integer> sequentialGoals = getGoals();
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(ORIGINAL_ALS_OUTPUT, true)));
		out.println("assert {");
		out.println("  not(");
		boolean first = true;
		String[] paths = classToCheck.split("\\.");
		String className = paths[paths.length - 1];
		for(Integer goal : sequentialGoals) {
			String prefix = first ? "" : "and ";
			out.println("    " + prefix + "ClassFields.(QF.roops_core_objects_" + className + "_roops_goal_" + goal + "_2) = true");
			first = false;
		}
		out.println("  )");
		out.println("}");
		out.close();
	}

	private Set<Integer> getGoals() throws IOException {
		Set<Integer> goals = new HashSet<Integer>();
		BufferedReader br = new BufferedReader(new FileReader(TEST_CLASS_PATH_LOCATION));
		String line;
		Pattern p = Pattern.compile("^\\s*roops_goal_(\\d+)=true.*$");
		while((line = br.readLine())!= null) {
			Matcher m = p.matcher(line);
			if(m.matches()) {
				goals.add(Integer.parseInt(m.group(1)));
			}
		}
		br.close();
		return goals;
	}

	private Collection<Integer> getErrorLines(
			String alsPath, Pair<Set<Pos>, Set<Pos>> uCore) throws IOException {
		Set<Integer> errorLines = new HashSet<Integer>();
		SequencerLineMapper mapper = new SequencerLineMapper(TEST_CLASS_PATH_LOCATION, "contains");
		mapper.parse();
		MarkParser mp = new MarkParser(alsPath);
		mp.parse();
		Set<Integer> alloyErrorLines = new TreeSet<Integer>();
		Set<Integer> sequentialErrorLines = new TreeSet<Integer>();
		Set<Integer> instrumentedErrorLines = new TreeSet<Integer>();
		for (Pos p : uCore.a) {
			for(int i = p.y; i <= p.y2; i++) {
				Integer sequentialLine = mp.getOriginalLine(i);
				Integer instrumentedLine = mapper.getOriginalLine(mp.getOriginalLine(i));
				if (instrumentedLine == null) {
					continue;
				}
				Integer originalLine = instrumentedMap.get(instrumentedLine);
				alloyErrorLines.add(i);
				sequentialErrorLines.add(sequentialLine);
				instrumentedErrorLines.add(instrumentedLine);
				errorLines.add(originalLine);
				System.out.println("a("+ i +") -> s("+ sequentialLine +") -> i("+ instrumentedLine +") -> " + originalLine);
			}
		}
		System.out.println("Sequential: " + sequentialErrorLines);
		System.out.println("Instrumented: " + instrumentedErrorLines);
		System.out.println("Original: " + errorLines);
		return errorLines;
	}

	private AlloyAnalysisResult generateSeqAls(OpenJMLInputWrapper ojiWrapper,
			boolean negatePost) throws IOException {

		// Fix sequential code input
		File seqCodeFile = new File(ojiWrapper.getSeqFilesPrefix());
		String seqMethodInput = StrykerStage.junitFiles[StrykerStage.indexToLastJUnitInput-1];
//		DarwinistInput darwinistInput = new DarwinistInput(null, null, null,
//				methodToCheck, null, null, null, null, null, null,
//				seqMethodInput, seqCodeFile.getAbsolutePath(), null, null);
//		StrykerJavaFileInstrumenter.fixInput(darwinistInput);

		// Mark
		MarkMaker mm = new MarkMaker(ojiWrapper.getSeqFilesPrefix(), ojiWrapper.getMethod());
		mm.mark();
		
		// Run Taco with sequential code
		TacoMain main = getTacoMainWithFixedInput(seqMethodInput);
		Properties overridingProperties = (Properties) this.overridingProperties.clone();
		FileUtils.copyFile(TEST_CLASS_PATH_LOCATION, TEST_CLASS_PATH_LOCATION.replace(".java", ".bak"));
		FileUtils.copyFile(TEST_CLASS_PATH_LOCATION.replace("objects/", "objects/sequential/"), TEST_CLASS_PATH_LOCATION);
		overridingProperties.put("negatePost", true);
		main.run(configFile, overridingProperties);

		// Execute ALS and return result
		FileUtils.copyFile(TACO_ALS_OUTPUT, SEQUENTIAL_ALS_OUTPUT);
		AlloyStage originalAlloyStage = new AlloyStage(SEQUENTIAL_ALS_OUTPUT);
		originalAlloyStage.setSolver(A4Options.SatSolver.MiniSatProverJNI);
		originalAlloyStage.execute();
		AlloyAnalysisResult alloyAnalysisResult = originalAlloyStage
				.get_analysis_result();
		return alloyAnalysisResult;
	}

	private String addPackageToClass(String packageName, String className) {
		String[] fragments = className.split("\\.");
		int packageIndex = fragments.length - 2;
		fragments[packageIndex] += "." + packageName;
		return StringUtils.join(fragments, ".");
	}

	/**
	 * Generates a wrapper for a given input.
	 * 
	 * @param classFilename
	 *            The analyzed class
	 * @param jUnitInputExposingBug
	 *            The input exposing the bug analyzed class
	 * @return
	 */

	private OpenJMLInputWrapper generateInputWrapper(String classFilename,
			Class<?>[] jUnitInputExposingBug) {
		// TODO verify the last parameter
		String pathToCWD = System.getProperty("user.dir") + "/tests/";
		OpenJMLInput oji = new OpenJMLInput(pathToCWD + classFilename,
				"contains", configFile,
				overridingProperties, pathToCWD + classFilename/* originalFilename */, null, null, null);
		Map<String, OpenJMLInput> map = new HashMap<String, OpenJMLInput>();
		map.put("contains", oji);
		OpenJMLInputWrapper wrapper = new OpenJMLInputWrapper(pathToCWD
				+ classFilename, configFile,
				overridingProperties, "contains", map, pathToCWD
						+ classFilename);
		
		return wrapper;
	}

	/**
	 * Generates the sequential of the trace for a given junitTest
	 */
	private OpenJMLInputWrapper generateSequentialCode(
			OpenJMLInputWrapper wrapper) {
		// A controller similar to the OpenJMLController must be created in
		// order
		// to execute the instrumented code and generate the sequential one.
		// Other way can be to run only the code in the runnable method of
		// OpenJMLController.
		// The map must be populated with the testable methods.
		
		OpenJMLInputWrapper newWrapper = StrykerJavaFileInstrumenter.instrumentForSequentialOutput(wrapper, null, null);
		newWrapper.setForSeqProcessing(true);
		CodeSequencer codeSequencer = CodeSequencer.getInstance();
		try {
			codeSequencer.sequence2(newWrapper);
			return newWrapper;
		} catch (Exception conco) {
			conco.printStackTrace();
		}

		// Call lulas instrumentator --- OpenJMLInputWrapper newWrapper =
		// instrumentForSequentialOutput(wrapper)
		// Run instrumented code --- OpenJMLController.enqueue() --- the
		// controller must be initialized
		// Return sequential file --- newWrapper.getSeqFilesPrefix();
		return null;
	}

	/**
	 * Generates a jUnit test with the input exposing a bug, based on a result
	 * of TACO analysis.
	 * 
	 * @param tacoAnalysisResult
	 *            the result exposing at least one counterexample.
	 * @return the jUnit test as an array.
	 */
	private Class<?>[] generateJUnitInput(TacoAnalysisResult tacoAnalysisResult) {
		String jUnit = generateTest(tacoAnalysisResult);
		return generateJUnitTestFile(jUnit);
	}

	/**
	 * Runs TACO only for translation. Does not run or check alloy. Only writes
	 * an alloy translation.
	 * 
	 * @param configFile
	 *            the TACO configuration file.
	 * @param overridingProperties
	 *            the TACO configuration properties to overrides those in the
	 *            configFile.
	 */
	private void translateToAlloy(String configFile,
			Properties overridingProperties) {
		TacoMain main = new TacoMain(null);
		// TODO shouldnt verify, but verify generates correct junit
		overridingProperties.put(TacoConfigurator.NO_VERIFY, false);
		overridingProperties.put("methodToCheck", methodToCheck);
		main.run(configFile, overridingProperties);
	}

	// Generates the test based on a TacoAnalysisResult.
	private String generateTest(TacoAnalysisResult tacoAnalysisResult) {

		String classToCheck = TacoConfigurator.getInstance().getString(
				TacoConfigurator.CLASS_TO_CHECK_FIELD);
		SnapshotStage snapshotStage = new SnapshotStage(compilation_units,
				tacoAnalysisResult, classToCheck, methodToCheck);
		snapshotStage.execute();

		RecoveredInformation recoveredInformation = snapshotStage
				.getRecoveredInformation();
		recoveredInformation.setFileNameSuffix(StrykerStage.fileSuffix);
		log.debug("Recovered Information: "
				+ recoveredInformation.getFileNameSuffix());
		JUnitStage jUnitStage = new JUnitStage(recoveredInformation);
		jUnitStage.execute();
		log.debug("JUnitStageFileName: " + jUnitStage.getJunitFileName()
				+ " - JUnitStage: " + jUnitStage);
		return jUnitStage.getJunitFileName();
	}

	// Generates this object based on a generated jUnit test.
	private Class<?>[] generateJUnitTestFile(String junitFile) {
		StrykerStage.junitInputs = new Class<?>[50];
		StrykerStage.junitFiles = new String[50];

		try {
			String currentJunit = null;

			log.debug("JUnitFile: " + junitFile);

			String tempFilename = junitFile.substring(0,
					junitFile.lastIndexOf(TacoMain.FILE_SEP) + 1) /* + FILE_SEP */;
			String packageToWrite = "ar.edu.output.junit";
			String fileClasspath = tempFilename.substring(0, tempFilename
					.lastIndexOf(new String("ar.edu.generated.junit")
							.replaceAll("\\.", TacoMain.FILE_SEP)));
			fileClasspath = fileClasspath.replaceFirst("generated", "output");
			currentJunit = TacoMain.editTestFileToCompile(junitFile,
					classToCheck, packageToWrite, methodToCheck);

			File[] file1 = new File[] { new File(currentJunit) };
			JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
			StandardJavaFileManager fileManager = javaCompiler
					.getStandardFileManager(null, null, null);
			Iterable<? extends JavaFileObject> compilationUnit1 = fileManager
					.getJavaFileObjectsFromFiles(Arrays.asList(file1));
			javaCompiler.getTask(null, fileManager, null, null, null,
					compilationUnit1).call();
			fileManager.close();
			javaCompiler = null;
			file1 = null;
			fileManager = null;

			log.warn("junit counterexample compilation succeded");
			ClassLoader cl = ClassLoader.getSystemClassLoader();
			@SuppressWarnings("resource")
			ClassLoader cl2 = new URLClassLoader(new URL[] { new File(
					fileClasspath).toURI().toURL() }, cl);
			Class<?> clazz = cl2.loadClass(packageToWrite + "."
					+ TacoMain.obtainClassNameFromFileName(junitFile));
			StrykerStage.junitInputs[0] = clazz;
			StrykerStage.junitFiles[0] = junitFile;
			StrykerStage.indexToLastJUnitInput = 1;
			cl = null;
			cl2 = null;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return StrykerStage.junitInputs;
	}

	private void appendToClassPackage(String classPath, String p) {
		try {
			String content = FileUtils.readFile(classPath);
			content = content.replaceFirst("(?m)^package (.*);$", "package $1."
					+ p + ";");
			FileUtils.writeToFile(classPath, content);
		} catch (IOException e) {
			log.debug("Could not replace package");
			e.printStackTrace();
		}
	}

	private void instrumentBranchCoverage() throws UnsupportedEncodingException {
		log.debug("Corriendo faji");
		try {
			MarkMaker mm = new MarkMaker(TEST_CLASS_PATH_LOCATION, "contains");
			mm.mark();
			style(TEST_CLASS_PATH_LOCATION);
			Runtime.getRuntime().exec("java -jar ./lib/fajita.jar -cp tests -cf "
					+ "config/roops_core_objects_SinglyLinkedList/containsTest.fajita.config "
					+ "-tf config/taco.properties.template -rp result -cs sat4j -r branch -a");
			Thread.sleep(2000);
			moveInstrumentedFile();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return;
		}
	}
	
	private void style(String filePath) {
		try {
			Runtime.getRuntime().exec("/usr/local/bin/astyle " + filePath + " --style=1tbs");
			Thread.sleep(2000);
			Runtime.getRuntime().exec("/usr/local/bin/astyle " + filePath + " --style=java");
			Thread.sleep(2000);
			System.out.println("formatted bro");
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return;
		}
	}
	
	private void removeComments() {
		File file = new File(TEST_CLASS_PATH_LOCATION);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			PrintWriter writer = new PrintWriter(TEST_CLASS_PATH_LOCATION + ".no_comments", "UTF-8");
			String line = reader.readLine();
			while (line != null) {
				if (!line.trim().startsWith("//")) {
					writer.write(line + "\n");
				}
				line = reader.readLine();
			}
			reader.close();
			writer.close();		
			FileUtils.copyFile(TEST_CLASS_PATH_LOCATION + ".no_comments", TEST_CLASS_PATH_LOCATION);
		} catch (IOException e) {
			
		}
	}

	private void moveInstrumentedFile() {
//		File original = new File(TEST_CLASS_PATH_LOCATION);
//		File originalRenamed = new File(TEST_CLASS_COPY_PATH_LOCATION);
//		original.renameTo(originalRenamed);
		File instrumented = new File("result/fajitaOut/roops_core_objects_SinglyLinkedList/roops/core/objectsInstrumented/SinglyLinkedList.java");
		if (instrumented.exists()) {
			System.out.println('e');
		} else {
			throw new RuntimeException("NO EXISTIO EL ARCHIVO MAMI");
		}
		style("result/fajitaOut/roops_core_objects_SinglyLinkedList/roops/core/objectsInstrumented/SinglyLinkedList.java");
		//		instrumented.renameTo(original);
//		int currentLine = 1;
		List<String> contract = new ArrayList<String>();
		boolean isInContract = false;
		try {
			BufferedReader preReader = new BufferedReader(new FileReader(TEST_CLASS_PATH_LOCATION));
			String line = preReader.readLine();
			while (line != null) {
				if ((line.contains("contains" + "(") || line.contains("contains" + " (")) && line.contains("{")) {
					break;
				} else if (line.contains("/*@")) {
					contract = new ArrayList<String>();
					isInContract = true;
				} else if (line.contains("@*/")) {
//					endCommentLine = currentLine;
				} 
				if (isInContract) {
					contract.add(line);
				}
//				currentLine++;
				line = preReader.readLine();
			}
			preReader.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			BufferedReader instrumentedReader = new BufferedReader(new FileReader(instrumented));
			BufferedReader originalReader = new BufferedReader(new FileReader(TEST_CLASS_PATH_LOCATION));
			PrintWriter writer = new PrintWriter("temp", "UTF-8");
			String instrumentedLine = instrumentedReader.readLine();
			String originalLine = originalReader.readLine();
			int instrumentedIndex = 1;
			int originalIndex = 1;		
			int markAmounts = 0, markAmounts2 = 0;
			boolean foundMarker = false;
			String pack;
			while (!instrumentedLine.contains("package")) {
				writer.write(instrumentedLine + "\n");
				instrumentedLine = instrumentedReader.readLine(); 
				instrumentedIndex++;
			}
			pack = instrumentedLine.split("package ")[1];
			pack = pack.substring(0, pack.length() - 1);
			writer.write("package " + TEST_CLASS_PACKAGE + ";\n");
			instrumentedLine = instrumentedReader.readLine();
			instrumentedIndex++;
			while (instrumentedLine != null) {
				if (instrumentedLine.contains("import ") && instrumentedLine.contains(pack)) {
					String[] s = instrumentedLine.split(pack);
					instrumentedLine = s[0] + TEST_CLASS_PACKAGE + s[1];
				} else if (instrumentedLine.contains("__marker__")) {
					while (!originalLine.contains("__marker__")) {
						originalLine = originalReader.readLine();
						originalIndex++;
					}
					instrumentedMap.put(instrumentedIndex - markAmounts - markAmounts2, originalIndex - markAmounts);
					originalLine = originalReader.readLine();
					originalIndex++;
				}
				if (instrumentedLine.contains("__marker__")) {
					markAmounts++;
					foundMarker = true;
				} else if (foundMarker && instrumentedLine.contains("mark ()")) {
					markAmounts2++;
				} else {
					foundMarker = false;
					writer.write(instrumentedLine + "\n");
				}
				instrumentedLine = instrumentedReader.readLine();
				instrumentedIndex++;
			}
			instrumentedReader.close();
			originalReader.close();
			writer.close();
			
			BufferedReader tempReader = new BufferedReader(new FileReader("temp"));
			writer = new PrintWriter("temp2", "UTF-8");
			String line = tempReader.readLine();
			while (line != null) {
				if (line.contains("Modifies_Everything")) {
					for (String c : contract) {
						writer.write(c + "\n");
					}
					while (!line.contains("contains")) {
						line = tempReader.readLine();
					}
				} else {
					writer.write(line + "\n");
					line = tempReader.readLine();
				}
			}
			tempReader.close();
			writer.close();
			
			FileUtils.copyFile("temp2", TEST_CLASS_PATH_LOCATION);
			style(TEST_CLASS_PATH_LOCATION);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		int contractOffset = contract.size() - 3;
		Map<Integer, Integer> newInstrumentedMap = new TreeMap<Integer, Integer>();
		for (Entry<Integer, Integer> e : instrumentedMap.entrySet()) {
			newInstrumentedMap.put(e.getKey() + contractOffset, e.getValue());
		}
		instrumentedMap = newInstrumentedMap;
	}
	
//	private void renameBack() {
//		File original = new File(TEST_CLASS_PATH_LOCATION);
//		File originalRenamed = new File(TEST_CLASS_COPY_PATH_LOCATION);
//		originalRenamed.renameTo(original);
//		log.debug("Renamed back");
//	}

	public static void main(String[] args) {
		// BugLineDetector bld = new BugLineDetector(null, null, null);
		// for (JCompilationUnitType s: bld.compilation_units){
		// System.out.println("la");
		// }
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
}
