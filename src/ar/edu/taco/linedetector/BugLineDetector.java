package ar.edu.taco.linedetector;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.multijava.mjc.JCompilationUnitType;

import edu.mit.csail.sdg.alloy4compiler.ast.ExprVar;
import edu.mit.csail.sdg.annotations.parser.JForgeParser.compilationUnit_return;
import ar.edu.jdynalloy.JDynAlloyConfig;
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
import ar.edu.taco.stryker.api.impl.input.DarwinistInput;
import ar.edu.taco.stryker.api.impl.input.OpenJMLInput;
import ar.edu.taco.stryker.api.impl.input.OpenJMLInputWrapper;
import ar.edu.taco.utils.FileUtils;
import ar.uba.dc.rfm.dynalloy.analyzer.AlloyAnalysisResult;

public class BugLineDetector {

	private static Logger log = Logger.getLogger(BugLineDetector.class);

	private static String TACO_ALS_OUTPUT = "output/output.als";

	private static String ORIGINAL_ALS_OUTPUT = "output/originalOutput.als";
	
	private static String SEQUENTIAL_ALS_OUTPUT = "output/sequentialOutput.als";
	
	private static String TEST_CLASS_PATH_LOCATION = "roops/core/objects/SinglyLinkedList.java";


	private List<JCompilationUnitType> compilation_units = null;

	private String classToCheck = null;

	private String methodToCheck = null;

	private Properties overridingProperties = null;

	private String configFile = null;



	public BugLineDetector(String configFile, Properties overridingProperties, String methodToCheck) {
		this.methodToCheck = methodToCheck;
		this.overridingProperties = overridingProperties;
		this.configFile = configFile;
		this.overridingProperties.put("generateUnitTestCase",true);
	}

	public void run(String classFilename) { // TODO verify className != classToCheck

		// originalAls = TacoTranslate() --- ~Postcondition
		log.info("Traduciendo a Alloy.");
		translateToAlloy(configFile, overridingProperties);
		try {
			FileUtils.copyFile(TACO_ALS_OUTPUT, ORIGINAL_ALS_OUTPUT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("Traducci��n realizada.");
		log.debug("");
		AlloyStage originalAlloyStage = new AlloyStage(ORIGINAL_ALS_OUTPUT);
		log.info("Ejecutando Alloy.");
		originalAlloyStage.execute();
		log.info("Alloy ejecuto.");
		AlloyAnalysisResult alloyAnalysisResult = originalAlloyStage.get_analysis_result();
		log.info("resultado analizado.");
		TacoAnalysisResult tacoAnalysisResult = new TacoAnalysisResult(alloyAnalysisResult);
		log.info("Ejecucion terminada.");
		classToCheck = TacoConfigurator.getInstance().getString(TacoConfigurator.CLASS_TO_CHECK_FIELD);
		compilation_units = JmlParser.getInstance().getCompilationUnits();
		int i = 0;
		System.out.println("Alloy dio: " + alloyAnalysisResult.isSAT());
		while (alloyAnalysisResult.isSAT() && i != 1){
			//badInput = alloy(varAls)
			log.info("Generando  JUnit");
			Class<?>[] jUnitInputExposingBug = generateJUnitInput(tacoAnalysisResult);
			log.info("Generando OjiWrapper");
			OpenJMLInputWrapper ojiWrapper = generateInputWrapper(classFilename, jUnitInputExposingBug);
			//linearCode = lulasProgram(jUnitInputExposingBug, classToCheck)
			log.info("Generando Codigo secuencial.");
			ojiWrapper = generateSequentialCode(ojiWrapper);
			try {
				//badAls = generate(contrato, linearCode, badInput) --- Postcondition
				AlloyAnalysisResult inputBugPathAls = generateSeqAls(ojiWrapper);
				do {
//					Properties copy = (Properties) overridingProperties.clone();
//					copy.put("relevantClasses", seqCode.getAbsolutePath());
					//uCore = alloy(badAls)		
					//errorlines += codeLines(uCore)
					//analizedPostConditions += postCondition(uCore)
					//alsToExposeNewBug = negatePost(badAls - analizedPosts) --- ~Postcondition
					//badInput = alloy(alsToExposeNewBug)
					//badAls = generate(Contrato - analizedPosts, linearCode, badInput)
				} while (inputBugPathAls.isSAT() /* isSat */);
				//originalAls -= linearCode // restringir el camino tomado
				//AnalizedPosts = 0
				i = 1;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private AlloyAnalysisResult generateSeqAls(OpenJMLInputWrapper ojiWrapper) throws IOException {
		// TODO fix sequential code package
		
		// Fix sequential code input
		File seqCodeFile = new File(ojiWrapper.getSeqFilesPrefix());
		String seqMethodInput = StrykerStage.junitFiles[0];
//		DarwinistInput darwinistInput = new DarwinistInput(null, null, null, methodToCheck, null, null, null, null, null, null, seqMethodInput, seqCodeFile.getAbsolutePath(), null, null);
//		StrykerJavaFileInstrumenter.fixInput(darwinistInput);
		
		// Fix sequential code package
		appendToClassPackage(ojiWrapper.getSeqFilesPrefix(), "sequential");
		
		// Run Taco with sequential code
		TacoMain main = new TacoMain(null);
		Properties overridingProperties = (Properties) this.overridingProperties.clone();
		String sequentialClassName = addPackageToClass("sequential", classToCheck);
		overridingProperties.put("classToCheck", sequentialClassName);
		main.run(configFile, overridingProperties);
		
		// Execute ALS and return result
		FileUtils.copyFile(TACO_ALS_OUTPUT, SEQUENTIAL_ALS_OUTPUT);
		AlloyStage originalAlloyStage = new AlloyStage(SEQUENTIAL_ALS_OUTPUT);
		originalAlloyStage.execute();
		AlloyAnalysisResult alloyAnalysisResult = originalAlloyStage.get_analysis_result();
		return alloyAnalysisResult;
	}
	
	private String addPackageToClass(String packageName, String className) {
		String[] fragments = className.split("\\.");
		int packageIndex = fragments.length-2;
		fragments[packageIndex] += "." + packageName;
		return StringUtils.join(fragments, ".");
	}

	/**
	 * Generates a wrapper for a given input.
	 * @param classFilename The analyzed class
	 * @param jUnitInputExposingBug The input exposing the bug analyzed class
	 * @return
	 */
	private OpenJMLInputWrapper generateInputWrapper(String classFilename, Class<?>[] jUnitInputExposingBug) {
		//TODO verify the last parameter
//		String pathToCWD = System.getProperty("user.dir") + "/tests/";
//		OpenJMLInput oji = new OpenJMLInput(pathToCWD + classFilename, jUnitInputExposingBug, "contains", configFile, overridingProperties, pathToCWD + classFilename/*originalFilename*/);
//		Map<String,OpenJMLInput> map = new HashMap<String, OpenJMLInput>();
//		map.put("contains", oji);
//		OpenJMLInputWrapper wrapper = new OpenJMLInputWrapper(pathToCWD + classFilename, jUnitInputExposingBug, configFile, overridingProperties, "contains", map, pathToCWD+classFilename);
//		return wrapper;
		return null;
	}

	/**
	 * Generates the sequential of the trace for a given junitTest
	 */
	private OpenJMLInputWrapper generateSequentialCode(OpenJMLInputWrapper wrapper){
		// A controller similar to the OpenJMLController must be created in order 
		// to execute the instrumented code and generate the sequential one.
		// Other way can be to run only the code in the runnable method of OpenJMLController.
		// The map must be populated with the testable methods.

		OpenJMLInputWrapper newWrapper = StrykerJavaFileInstrumenter.instrumentForSequentialOutput(wrapper, null, null);
		CodeSequencer codeSequencer = CodeSequencer.getInstance();
		try {
			codeSequencer.sequence2(newWrapper);
			return newWrapper;
		} catch (Exception conco) {
			conco.printStackTrace();
		}

		// Call lulas instrumentator --- OpenJMLInputWrapper newWrapper = instrumentForSequentialOutput(wrapper)
		// Run instrumented code --- OpenJMLController.enqueue() --- the controller must be initialized
		// Return sequential file --- newWrapper.getSeqFilesPrefix();     
		return null;
	}


	/**
	 * Generates a jUnit test with the input exposing a bug, based on a result of TACO analysis.
	 *
	 * @param tacoAnalysisResult the result exposing at least one counterexample.
	 * @return the jUnit test as an array.
	 */
	private Class<?>[] generateJUnitInput(TacoAnalysisResult tacoAnalysisResult) {
		String jUnit = generateTest(tacoAnalysisResult);
		return generateJUnitTestFile(jUnit);
	}

	/**
	 * Runs TACO only for translation. Does not run or check alloy. Only writes an alloy translation.
	 *
	 * @param configFile the TACO configuration file.
	 * @param overridingProperties the TACO configuration properties to overrides those in the configFile.
	 */
	private void translateToAlloy(String configFile, Properties overridingProperties) {
		TacoMain main = new TacoMain(null);
		// TODO shouldnt verify, but verify generates correct junit
		overridingProperties.put(TacoConfigurator.NO_VERIFY, true);
		overridingProperties.put("methodToCheck", methodToCheck);
		main.run(configFile, overridingProperties);
	}


	// Generates the test based on a TacoAnalysisResult.
	private String generateTest(TacoAnalysisResult tacoAnalysisResult) {

		String classToCheck = TacoConfigurator.getInstance().getString(TacoConfigurator.CLASS_TO_CHECK_FIELD);
		SnapshotStage snapshotStage = new SnapshotStage(compilation_units, tacoAnalysisResult, classToCheck, methodToCheck);
		snapshotStage.execute();

		RecoveredInformation recoveredInformation = snapshotStage.getRecoveredInformation();
		recoveredInformation.setFileNameSuffix(StrykerStage.fileSuffix);
		log.debug("Recovered Information: " + recoveredInformation.getFileNameSuffix());
		JUnitStage jUnitStage = new JUnitStage(recoveredInformation);
		jUnitStage.execute();
		log.debug("JUnitStageFileName: " + jUnitStage.getJunitFileName() + " - JUnitStage: " + jUnitStage);
		return jUnitStage.getJunitFileName();
	}

	// Generates this object based on a generated jUnit test.
	private Class<?>[] generateJUnitTestFile(String junitFile) {
		StrykerStage.junitInputs = new Class<?>[50];
		StrykerStage.junitFiles = new String[50];
		
		try {
			String currentJunit = null;

			log.debug("JUnitFile: " + junitFile);

			String tempFilename = junitFile.substring(0, junitFile.lastIndexOf(TacoMain.FILE_SEP)+1) /*+ FILE_SEP*/;
			String packageToWrite = "ar.edu.output.junit";
			String fileClasspath = tempFilename.substring(0, tempFilename.lastIndexOf(new String("ar.edu.generated.junit").replaceAll("\\.", TacoMain.FILE_SEP)));
			fileClasspath = fileClasspath.replaceFirst("generated", "output");
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

			log.warn("junit counterexample compilation succeded");
			ClassLoader cl = ClassLoader.getSystemClassLoader();
			@SuppressWarnings("resource")
			ClassLoader cl2 = new URLClassLoader(new URL[]{new File(fileClasspath).toURI().toURL()}, cl);
			Class<?> clazz = cl2.loadClass(packageToWrite+"."+TacoMain.obtainClassNameFromFileName(junitFile));
			StrykerStage.junitInputs[StrykerStage.indexToLastJUnitInput] = clazz;
			StrykerStage.junitFiles[StrykerStage.indexToLastJUnitInput] = junitFile;
			StrykerStage.indexToLastJUnitInput++;
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
			content = content.replaceFirst("(?m)^package (.*);$", "package $1." + p + ";");
			FileUtils.writeToFile(classPath, content);
		} catch (IOException e) {
			log.debug("Could not replace package");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
//		BugLineDetector bld = new BugLineDetector(null, null, null);
//		for (JCompilationUnitType s: bld.compilation_units){
//			System.out.println("la");
//		}
	}
}
