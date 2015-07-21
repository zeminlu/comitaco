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
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
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

import kodkod.ast.Formula;
import kodkod.engine.Solver;
import kodkod.engine.fol2sat.Translation;
import kodkod.engine.fol2sat.Translator;
import kodkod.engine.fol2sat.TrivialFormulaException;
import kodkod.engine.satlab.SATFactory;
import kodkod.engine.satlab.SATSolver;
import kodkod.instance.Bounds;
import kodkod.instance.Universe;
import kodkod.util.nodes.PrettyPrinter;

import org.apache.log4j.Logger;
import org.multijava.mjc.JCompilationUnitType;

import ar.edu.taco.TacoAnalysisResult;
import ar.edu.taco.TacoConfigurator;
import ar.edu.taco.TacoMain;
import ar.edu.taco.engine.AlloyStage;
import ar.edu.taco.engine.JUnitStage;
import ar.edu.taco.engine.SnapshotStage;
import ar.edu.taco.engine.StrykerStage;
import ar.edu.taco.jml.parser.JmlParser;
import ar.edu.taco.junit.RecoveredInformation;
import ar.edu.taco.stryker.api.impl.StrykerJavaFileInstrumenter;
import ar.edu.taco.stryker.api.impl.input.OpenJMLInput;
import ar.edu.taco.stryker.api.impl.input.OpenJMLInputWrapper;
import ar.edu.taco.utils.FileUtils;
import ar.uba.dc.rfm.dynalloy.analyzer.AlloyAnalysisResult;
import edu.mit.csail.sdg.alloy4.ConstList;
import edu.mit.csail.sdg.alloy4.Err;
import edu.mit.csail.sdg.alloy4.Pair;
import edu.mit.csail.sdg.alloy4.Pos;
import edu.mit.csail.sdg.alloy4compiler.ast.Expr;
import edu.mit.csail.sdg.alloy4compiler.translator.A4Options;
import edu.mit.csail.sdg.alloy4compiler.translator.TranslateAlloyToKodkod;

public class BugLineDetector {

	private static Logger log = Logger.getLogger(BugLineDetector.class);

	private static String TACO_ALS_OUTPUT = "output/output.als";

	private static String ORIGINAL_ALS_OUTPUT = "output/originalOutput.als";

	private static String SEQUENTIAL_ALS_OUTPUT = "output/sequentialOutput.als";
	
	private static String KODKOD_POSTCONDITION = "output/postcondition.kodkod";
	
	private static String TRACES_TESTER = "output/traces";

	private String classToCheckPath; // = "tests/roops/core/objects/SinglyLinkedList.java";
	private static String TEST_CLASS_PACKAGE = "roops.core.objects";
	
	private static String LOOP_MARK = "// original line:";

	private List<JCompilationUnitType> compilation_units = null;

	private String classToCheck = null;
	private String clazz = null;

	private String methodToCheck = null;
	private String method = null;

	private Properties overridingProperties = null;

	private String configFile = null;
	
	private Map<Integer, Integer> instrumentedMap = new TreeMap<Integer, Integer>();
	private Map<Integer, Integer> loopUnrollMap = new TreeMap<Integer, Integer>();
	
	public BugLineDetector(String configFile, Properties overridingProperties,
			String clazz, String method) {
		this.method = method;
		this.clazz = clazz;
		this.methodToCheck = method + "_0";
		this.overridingProperties = overridingProperties;
		this.configFile = configFile;
		this.overridingProperties.put("generateUnitTestCase", true);
	}


	public void run(String classFilename) { // TODO verify className !=
										// classToCheck
		classToCheckPath = "tests/" + classFilename;
		List<Collection<Integer>> errorLines = new LinkedList<Collection<Integer>>();

		String fileBackup = null;
		try {
			fileBackup = FileUtils.readFile(classToCheckPath);
			// originalAls = TacoTranslate() --- ~Postcondition
			log.info("Traduciendo a Alloy.");
			//FileUtils.copyFile(classToCheckPath.replace(".java", "_bak.java"), classToCheckPath);
			MarkMaker mm = new MarkMaker(classToCheckPath, method);
			mm.mark();
 			//LoopUnroll
			loopUnroll(4, methodToCheck, classToCheckPath, "temp.unrolled");
 			FileUtils.copyFile("temp.unrolled", classToCheckPath);
			style(classToCheckPath);
			compileFile();
			//End loopUnroll
			instrumentBranchCoverage();
			
			compileFile();
			
			translateToAlloy(configFile, overridingProperties);
			try {
				FileUtils.copyFile(TACO_ALS_OUTPUT, ORIGINAL_ALS_OUTPUT);
			} catch (IOException e) {
				e.printStackTrace();
			}
			log.info("Traducción realizada.");
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
//			compilation_units = JmlParser.getInstance().getCompilationUnits();
			int i = 0;
			System.out.println("Alloy dio: " + alloyAnalysisResult.isSAT());
			boolean onlyOneTrace = false;
			while (alloyAnalysisResult.isSAT() && !onlyOneTrace) {
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
					if(inputBugPathAls.isSAT()) {
						System.out.println("Sequential gives SAT but it should be UNSAT. Skipping ucore translation");
					}
					log.info("Asking for the UNSAT-Core");
					Pair<Set<Pos>, Set<Pos>> uCore = inputBugPathAls.getAlloy_solution().highLevelCore();
					log.info("We have the UNSAT-Core");
					//errorlines += codeLines(uCore)
					//System.out.println(inputBugPathAls.getAlloy_solution().lowLevelCore());
					errorLines.add(getErrorLines(SEQUENTIAL_ALS_OUTPUT, uCore));
					//analizedPostConditions += postCondition(uCore)
					//alsToExposeNewBug = negatePost(badAls - analizedPosts) --- ~Postcondition
					//badInput = alloy(alsToExposeNewBug)
					//badAls = generate(Contrato - analizedPosts, linearCode, badInput)
				} while (false /* isSat */);
				// originalAls -= linearCode // restringir el camino tomado
				onlyOneTrace = !banAlsGoals();
				// AnalizedPosts = 0
				i = 1;
				originalAlloyStage = new AlloyStage(ORIGINAL_ALS_OUTPUT);
				log.info("Ejecutando Alloy.");
				originalAlloyStage.execute();
				log.info("Alloy ejecuto.");
				alloyAnalysisResult = originalAlloyStage
						.get_analysis_result();
				log.info("AlloySolution: " + alloyAnalysisResult.getAlloy_solution());
				log.info("resultado analizado.");
				tacoAnalysisResult = new TacoAnalysisResult(
						alloyAnalysisResult);
				log.info("Ejecucion terminada.");
				compilation_units = JmlParser.getInstance().getCompilationUnits();
				// Restore file
				FileUtils.copyFile(classToCheckPath.replace(".java", ".bak"), classToCheckPath);
				compileFile();
			}
			if(!errorLines.isEmpty()) {
				System.out.println("FINISHED. ERROR LINES:");
				System.out.println(errorLines);
				Map<Integer, Integer> count = countErrors(errorLines);
				System.out.println(count);
				System.out.println("CANDIDATES:");
				System.out.println(candidates(count));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				FileUtils.writeToFile(classToCheckPath, fileBackup);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	private void loopUnroll(int unrolls, String methodToCheck,
			String classToCheckPath, String destFile) throws IOException {
		//ar.edu.taco.stryker.api.impl.LoopUnrollTransformation.javaUnroll(unrolls, methodToCheck, classToCheckPath, destFile);
		LoopUnrollTransformation.javaUnroll(unrolls, classToCheckPath, destFile);
		
	}


	private Collection<Integer> candidates(Map<Integer, Integer> count) {
		Collection<Integer> candidates = new TreeSet<Integer>();
		int max = Collections.max(count.values());
		for(Entry<Integer, Integer> entry : count.entrySet()) {
			if(entry.getValue() == max) {
				candidates.add(entry.getKey());
			}
		}
		return candidates;
	}


	private void compileFile() {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int compilationResult = compiler.run(null, null, null, 
                new String[]{"-classpath", "./tests/", "-d", "./bin/", classToCheckPath});
        if (compilationResult != 0) {
            throw new RuntimeException("Compiled FAILED");
        }
	}
	
	private boolean banAlsGoals() throws IOException {
		Set<Integer> sequentialGoals = getGoals();
		PrintWriter traceTester = new PrintWriter(new BufferedWriter(new FileWriter(TRACES_TESTER, true)));
		traceTester.println(sequentialGoals);
		traceTester.close();
		if (sequentialGoals.size() == 0)
			return false;
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(ORIGINAL_ALS_OUTPUT, true)));
		out.println("fact {");
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
		return true;
	}

	private Set<Integer> getGoals() throws IOException {
		Set<Integer> goals = new HashSet<Integer>();
		BufferedReader br = new BufferedReader(new FileReader(classToCheckPath));
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
		Set<Integer> errorLines = new TreeSet<Integer>();
//		SequencerLineMapper mapper = new SequencerLineMapper(TEST_CLASS_PATH_LOCATION, method);
//		mapper.parse();
		MarkParser mp = new MarkParser(alsPath);
		mp.parse();
//		Set<Integer> alloyErrorLines = new TreeSet<Integer>();
//		Set<Integer> sequentialErrorLines = new TreeSet<Integer>();
//		Set<Integer> instrumentedErrorLines = new TreeSet<Integer>();
//		Set<Integer> unrolledErrorsLines = new TreeSet<Integer>();
		for (Pos p : uCore.a) {
			for(int i = p.y; i <= p.y2; i++) {
//				Integer sequentialLine = mp.getOriginalLine(i);
//				Integer instrumentedLine = mapper.getOriginalLine(mp.getOriginalLine(i));
//				if (instrumentedLine == null) continue;
//				Integer unrolledLine = instrumentedMap.get(instrumentedLine);
//				if (unrolledLine == null) continue;
//				Integer originalLine = loopUnrollMap.get(unrolledLine);
//				if (originalLine == null) {
//					System.out.println("NULL: " + originalLine );
//				}
//				alloyErrorLines.add(i);
//				sequentialErrorLines.add(sequentialLine);
//				instrumentedErrorLines.add(instrumentedLine);
//				unrolledErrorsLines.add(unrolledLine);
				Integer originalLine = mp.getOriginalLine(i);
				if (originalLine == -1) continue;
				log.info("The original line: " + originalLine + "was added");
				errorLines.add(originalLine);
//				System.out.println("a("+ i +") -> s("+ sequentialLine +") -> i("+ instrumentedLine +") -> u(" + unrolledLine + ") -> " + originalLine);
			}
		}
//		System.out.println(instrumentedMap);
//		System.out.println();
//		System.out.println("Sequential: " + sequentialErrorLines);
//		System.out.println("Instrumented: " + instrumentedErrorLines);
//		System.out.println("Unrolled: " + unrolledErrorsLines);
		System.out.println("Original: " + errorLines);
		return errorLines;
	}

	private AlloyAnalysisResult generateSeqAls(OpenJMLInputWrapper ojiWrapper,
			boolean negatePost) throws IOException {

//		MarkMaker mm = new MarkMaker(ojiWrapper.getSeqFilesPrefix(), ojiWrapper.getMethod());
//		mm.mark();
		
		// Run Taco with sequential code
		TacoMain main = getTacoMainWithFixedInput(StrykerStage.junitInputs[StrykerStage.indexToLastJUnitInput-1]);
		Properties overridingProperties = (Properties) this.overridingProperties.clone();
		FileUtils.copyFile(classToCheckPath, classToCheckPath.replace(".java", ".bak"));
		FileUtils.copyFile(classToCheckPath.replace("objects/", "objects/sequential/"), classToCheckPath);
		compileFile();
		main.run(configFile, overridingProperties);

		// Execute ALS and return result
		FileUtils.copyFile(TACO_ALS_OUTPUT, SEQUENTIAL_ALS_OUTPUT);
		AlloyStage originalAlloyStage = new AlloyStage(SEQUENTIAL_ALS_OUTPUT);
		originalAlloyStage.setSolver(A4Options.SatSolver.MiniSatProverJNI);
		originalAlloyStage.execute();
		AlloyAnalysisResult alloyAnalysisResult = originalAlloyStage
				.get_analysis_result();
		
		//translatePostcondition(alloyAnalysisResult);
		
		return alloyAnalysisResult;
	}
	
	private void translatePostcondition(AlloyAnalysisResult alloyAnalysisResult){
		ConstList<Pair<String, Expr>> assertions = alloyAnalysisResult.getWorld().getAllAssertions();
		Pair<String, Expr> postcondition = assertions.get(0);
		Object translatedPostcondition = null;
		try {
			log.info("Translating postcondition to KodKod: "+ postcondition);
			translatedPostcondition = TranslateAlloyToKodkod.alloy2kodkod(alloyAnalysisResult.getAlloy_solution(), postcondition.b);
			printKodkodFormula((Formula) translatedPostcondition, KODKOD_POSTCONDITION);
		} catch (Err | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Formula postFormula = (Formula) translatedPostcondition;
		
		
		Bounds bounds = null;
		Solver solver = null;
		try {
			Class<?> c = alloyAnalysisResult.getAlloy_solution().getClass();
			Field boundsField;
			boundsField = c.getDeclaredField("bounds");
			boundsField.setAccessible(true);
			Field solverField = c.getDeclaredField("solver");
			solverField.setAccessible(true);
			bounds = (Bounds) boundsField.get(alloyAnalysisResult.getAlloy_solution());
			solver = (Solver) solverField.get(alloyAnalysisResult.getAlloy_solution());
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Translation trans = Translator.translate(postFormula, bounds, solver.options());
			SATSolver satSolver = trans.cnf();
			solver.options().setSolver(SATFactory.DefaultSAT4J);
			solver.solve(postFormula, bounds);
		} catch (TrivialFormulaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("Postcondition Translated ");
	}
	
	private void printKodkodFormula(Formula formula, String path) throws IOException{
		Set<Formula> formulas = new HashSet<>();
		formulas.add(formula);
		String s = PrettyPrinter.print(formulas, 4);
		FileWriter writer = new FileWriter(new File(path));
		writer.append(s);
		writer.close();
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
				method, configFile,
				overridingProperties, pathToCWD + classFilename/* originalFilename */, null, null, null);
		Map<String, OpenJMLInput> map = new HashMap<String, OpenJMLInput>();
		map.put(method, oji);
		OpenJMLInputWrapper wrapper = new OpenJMLInputWrapper(pathToCWD
				+ classFilename, configFile,
				overridingProperties, method, map, pathToCWD
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
		compilation_units = JmlParser.getInstance().getCompilationUnits();
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

	private void instrumentBranchCoverage() throws UnsupportedEncodingException {
		log.debug("Corriendo faji");
		try {
			Process p = Runtime.getRuntime().exec("java -jar ./lib/fajita.jar -cp tests -cf bugline.fajita.config -tf config/taco.properties.template -rp result -cs sat4j -r branch -a");
			p.waitFor();
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
		File file = new File(classToCheckPath);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			PrintWriter writer = new PrintWriter(classToCheckPath + ".no_comments", "UTF-8");
			String line = reader.readLine();
			while (line != null) {
				if (!line.trim().startsWith("//")) {
					writer.write(line + "\n");
				}
				line = reader.readLine();
			}
			reader.close();
			writer.close();		
			FileUtils.copyFile(classToCheckPath + ".no_comments", classToCheckPath);
		} catch (IOException e) {
			
		}
	}

	private void moveInstrumentedFile() {
		String instrumentedPath = "result/fajitaOut/roops_core_objects_" + clazz + "/roops/core/objectsInstrumented/" + clazz + ".java";
		File instrumented = new File(instrumentedPath);
		if (instrumented.exists()) {
			System.out.println('e');
		} else {
			throw new RuntimeException("NO EXISTIO EL ARCHIVO MAMI");
		}
		style(instrumentedPath);
		List<String> contract = new ArrayList<String>();
		boolean isInContract = false;
		try {
			BufferedReader preReader = new BufferedReader(new FileReader(classToCheckPath));
			String line = preReader.readLine();
			while (line != null) {
				if ((line.contains(method + "(") || line.contains(method + " (")) && line.contains("{")) {
					break;
				} else if (line.contains("/*@")) {
					contract = new ArrayList<String>();
					isInContract = true;
				} 
				if (isInContract) {
					contract.add(line);
				}
				line = preReader.readLine();
			}
			preReader.close();

			BufferedReader instrumentedReader = new BufferedReader(new FileReader(instrumented));
			PrintWriter writer = new PrintWriter("temp", "UTF-8");
			String instrumentedLine = instrumentedReader.readLine();
			String pack;
			while (!instrumentedLine.contains("package")) {
				writer.write(instrumentedLine + "\n");
				instrumentedLine = instrumentedReader.readLine(); 
			}
			pack = instrumentedLine.split("package ")[1];
			pack = pack.substring(0, pack.length() - 1);
			writer.write("package " + TEST_CLASS_PACKAGE + ";\n");
			instrumentedLine = instrumentedReader.readLine();
			while (instrumentedLine != null) {
				if (instrumentedLine.contains("import ") && instrumentedLine.contains(pack)) {
					String[] s = instrumentedLine.split(pack);
					instrumentedLine = s[0] + TEST_CLASS_PACKAGE + s[1];
				}
				writer.write(instrumentedLine + "\n");
				instrumentedLine = instrumentedReader.readLine();
			}
			instrumentedReader.close();
			writer.close();
			
			BufferedReader tempReader = new BufferedReader(new FileReader("temp"));
			writer = new PrintWriter("temp2", "UTF-8");
			line = tempReader.readLine();
			while (line != null) {
				if (line.contains("Modifies_Everything")) {
					for (String c : contract) {
						writer.write(c + "\n");
					}
					while (!line.contains(method)) {
						line = tempReader.readLine();
					}
				} else {
					writer.write(line + "\n");
					line = tempReader.readLine();
				}
			}
			tempReader.close();
			writer.close();
			
			FileUtils.copyFile("temp2", classToCheckPath);
			style(classToCheckPath);
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
	
	public static void main(String[] args) {
		// BugLineDetector bld = new BugLineDetector(null, null, null);
		// for (JCompilationUnitType s: bld.compilation_units){
		// System.out.println("la");
		// }
	}
	
	private TacoMain getTacoMainWithFixedInput(Class<?> claz) throws IllegalArgumentException {
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
	
	private Map<Integer, Integer> countErrors(Collection<Collection<Integer>> errorLines) {
		Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
		for(Collection<Integer> lines : errorLines) {
			for(Integer line : lines) {
				if(line == null) {
					continue;
				}
				if(map.get(line) == null) {
					map.put(line, 1);
				} else {
					map.put(line, map.get(line) + 1);
				}
			}
		}
		return map;
	}
}
