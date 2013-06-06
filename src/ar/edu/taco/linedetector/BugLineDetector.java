package ar.edu.taco.linedetector;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

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
import ar.edu.taco.stryker.api.impl.input.OpenJMLInput;
import ar.edu.taco.stryker.api.impl.input.OpenJMLInputWrapper;
import ar.uba.dc.rfm.dynalloy.analyzer.AlloyAnalysisResult;

public class BugLineDetector {
	
	private static Logger log = Logger.getLogger(BugLineDetector.class);
	
	private static String TACO_ALS_OUTPUT = "output.als";
	
	private List<JCompilationUnitType> compilation_units = null;
	
	private String classToCheck = null;
	
	private String methodToCheck = null;
	
	
	public void run(String configFile, Properties overridingProperties, String methodToCheck, String classFilename) {
		
		compilation_units = JmlParser.getInstance().getCompilationUnits();
		classToCheck = TacoConfigurator.getInstance().getString(TacoConfigurator.CLASS_TO_CHECK_FIELD);
		this.methodToCheck = methodToCheck;
		
		// originalAls = TacoTranslate() --- ~Postcondition
		translateToAlloy(configFile, overridingProperties); 									
		AlloyStage originalAlloyStage = new AlloyStage(TACO_ALS_OUTPUT);
		originalAlloyStage.execute();
		AlloyAnalysisResult alloyAnalysisResult = originalAlloyStage.get_analysis_result();
		TacoAnalysisResult tacoAnalysisResult = new TacoAnalysisResult(alloyAnalysisResult);
		while (alloyAnalysisResult.isSAT()){
			//badInput = alloy(varAls) 
			Class<?>[] jUnitInputExposingBug = generateJUnitInput(tacoAnalysisResult);			
			//linearCode = lulasProgram(jUnitInputExposingBug, classToCheck)
			OpenJMLInput oji = new OpenJMLInput(classFilename, jUnitInputExposingBug, methodToCheck, configFile, overridingProperties, classFilename/*originalFilename*/); //TODO verify the last parameter
			Map<String,OpenJMLInput> map = new HashMap<String, OpenJMLInput>();
			map.put(methodToCheck, oji);
			OpenJMLInputWrapper wrapper = new OpenJMLInputWrapper(classFilename, jUnitInputExposingBug, configFile, overridingProperties, methodToCheck, map);
			generateSequentialCode();
			//badAls = generate(contrato, linearCode, badInput) --- Postcondition
			
			do {
				//uCore = alloy(badAls)
				//errorlines += codeLines(uCore)
				//analizedPostConditions += postCondition(uCore)
				//alsToExposeNewBug = negatePost(badAls - analizedPosts) --- ~Postcondition
				//badInput = alloy(alsToExposeNewBug)
				//badAls = generate(Contrato - analizedPosts, linearCode, badInput)
			} while (true /* isSat */);
			//originalAls -= linearCode
			//AnalizedPosts = 0
		}
	}
	
	/** 
	 * Generates the sequential of the trace for a given junitTest
	 */
	private void generateSequentialCode(){
		// A controller similar to the OpenJMLController must be created in order 
		// to execute the instrumented code and generate the sequential one.
		// Other way can be to run only the code in the runnable method of OpenJMLController.
		// The map must be populated with the testable methods.
		
		// Call lulas instrumentator --- OpenJMLInputWrapper newWrapper = instrumentForSequentialOutput(wrapper)
		// Run instrumented code --- OpenJMLController.enqueue() --- the controller must be initialized
		// Return sequential file --- newWrapper.getSeqFilesPrefix();                                                                                       
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
		TacoMain main = new TacoMain();
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
		JUnitStage jUnitStage = new JUnitStage(recoveredInformation);
		jUnitStage.execute();
		return jUnitStage.getJunitFileName();
	}
	
	// Generates this object based on a generated jUnit test.
	private Class<?>[] generateJUnitTestFile(String junitFile) {
		Class<?>[] junitInputs = new Class<?>[50];
		

		try {
			String currentJunit = null;
			
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
		return junitInputs;
	}
	
	
}
