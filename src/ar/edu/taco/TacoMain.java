/*
 * TACO: Translation of Annotated COde
 * Copyright (c) 2010 Universidad de Buenos Aires
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA,
 * 02110-1301, USA
 */
package ar.edu.taco;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.jar.Attributes;
import java.util.jar.Attributes.Name;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.multijava.mjc.JCompilationUnitType;

import ar.edu.jdynalloy.JDynAlloyConfig;
import ar.edu.jdynalloy.MethodToCheckNotFoundException;
import ar.edu.jdynalloy.ast.JDynAlloyModule;
import ar.edu.taco.engine.AlloyStage;
import ar.edu.taco.engine.DynalloyStage;
import ar.edu.taco.engine.JDynAlloyParsingStage;
import ar.edu.taco.engine.JDynAlloyPrinterStage;
import ar.edu.taco.engine.JDynAlloyStage;
import ar.edu.taco.engine.JUnitStage;
import ar.edu.taco.engine.JavaTraceStage;
import ar.edu.taco.engine.JmlStage;
import ar.edu.taco.engine.PrecompiledModules;
import ar.edu.taco.engine.SimpleJmlStage;
import ar.edu.taco.engine.SnapshotStage;
import ar.edu.taco.engine.StrykerStage;
import ar.edu.taco.jfsl.JfslStage;
import ar.edu.taco.jml.JmlToSimpleJmlContext;
import ar.edu.taco.jml.parser.JmlParser;
import ar.edu.taco.junit.RecoveredInformation;
import ar.edu.taco.simplejml.SimpleJmlToJDynAlloyContext;
import ar.uba.dc.rfm.dynalloy.DynAlloyCompiler;
import ar.uba.dc.rfm.dynalloy.analyzer.AlloyAnalysisResult;

public class TacoMain {

	private static Logger log = Logger.getLogger(TacoMain.class);

	private static final String CMD = "Taco";
	private static final String HEADER = "Taco static analysis tool.";
	private static final String FOOTER = "For questions and comments please write to jgaleotti AT dc DOT uba DOT ar";
	public static final String PATH_SEP = System.getProperty("path.separator");
	public static final String FILE_SEP = System.getProperty("file.separator");

	/**
	 * @param args
	 */
	@SuppressWarnings({ "static-access" })
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		int loopUnrooling = 3;

		String tacoVersion = getManifestAttribute(Attributes.Name.IMPLEMENTATION_VERSION);
		String tacoCreatedBy = getManifestAttribute(new Name("Created-By"));

		System.out.println("TACO: Taco static analysis tool.");
		System.out.println("Created By: " + tacoCreatedBy);
		System.out.println("Version: " + tacoVersion);
		System.out.println("");
		System.out.println("");

		Option helpOption = new Option("h", "help", false, "print this message");
		Option versionOption = new Option("v", "version", false, "shows version");

		Option configFileOption = OptionBuilder.withArgName("path").withLongOpt("configFile").hasArg().withDescription("set the configuration file")
				.create("cf");
		Option classToCheckOption = OptionBuilder.withArgName("classname").withLongOpt("classToCheck").hasArg().withDescription("set the class to be checked")
				.create('c');
		Option methodToCheckOption = OptionBuilder.withArgName("methodname").withLongOpt("methodToCheck").hasArg()
				.withDescription("set the method to be checked").create('m');
		Option dependenciesOption = OptionBuilder.withArgName("classname").withLongOpt("dependencies").hasArgs()
				.withDescription("additional sources to be parsed").create('d');
		Option relevantClassesOption = OptionBuilder.withArgName("classname").withLongOpt("relevantClasses").hasArgs()
				.withDescription("Set the relevant classes to be used").create("rd");
		Option loopsOptions = OptionBuilder.withArgName("integer").withLongOpt("unroll").hasArg().withDescription("set number of loop unrollings").create('u');
		Option bitOptions = OptionBuilder.withArgName("integer").withLongOpt("width").hasArg().withDescription("set bit width").create('w');
		Option instOptions = OptionBuilder.withArgName("integer").withLongOpt("bound").hasArg().withDescription("set class bound").create('b');
		Option skolemizeOption = OptionBuilder.withLongOpt("skolemize").withDescription("set whether or not skolemize").create("sk");
		Option simulateOption = OptionBuilder.withLongOpt("simulate").withDescription("run method instead of checking").create("r");
		Option modularReasoningOption = OptionBuilder.withLongOpt("modularReasoning").withDescription("check method using modular reasoning").create("mr");
		Option relevancyAnalysisOption = OptionBuilder.withLongOpt("relevancyAnalysis").withDescription("calculate the needed relevantClasses").create("ra");
		Option scopeRestrictionOption = OptionBuilder.withLongOpt("scopeRestriction").withDescription("restrict signature scope to value set in -b option")
				.create("sr");
		/*
		 * Option noVerifyOption = OptionBuilder.withLongOpt(
		 * "noVerify").withDescription(
		 * "builds output but does not invoke verification engine").create(
		 * "nv");
		 */
		Options options = new Options();
		options.addOption(helpOption);
		options.addOption(versionOption);
		options.addOption(configFileOption);
		options.addOption(classToCheckOption);
		options.addOption(methodToCheckOption);
		options.addOption(dependenciesOption);
		options.addOption(relevantClassesOption);
		options.addOption(loopsOptions);
		options.addOption(bitOptions);
		options.addOption(instOptions);
		options.addOption(skolemizeOption);
		options.addOption(simulateOption);
		options.addOption(modularReasoningOption);
		options.addOption(relevancyAnalysisOption);
		options.addOption(scopeRestrictionOption);
		// options.addOption(noVerifyOption)

		String configFileArgument = null;
		Properties overridingProperties = new Properties();
		TacoCustomScope tacoScope = new TacoCustomScope();

		// create the parser
		CommandLineParser parser = new PosixParser();

		try {
			// parse the command line arguments
			CommandLine line = parser.parse(options, args);

			// help
			if (line.hasOption(helpOption.getOpt())) {
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp(120, CMD, HEADER, options, FOOTER, true);
				return;
			}

			// version
			if (line.hasOption(versionOption.getOpt())) {
				System.out.println(FOOTER);
				System.out.println("");
				return;
			}

			// Configuration file
			if (line.hasOption(configFileOption.getOpt())) {
				configFileArgument = line.getOptionValue(configFileOption.getOpt());
			}

			// class to check
			if (line.hasOption(classToCheckOption.getOpt())) {
				overridingProperties.put(TacoConfigurator.CLASS_TO_CHECK_FIELD, line.getOptionValue(classToCheckOption.getOpt()));
			}

			// method to check
			if (line.hasOption(methodToCheckOption.getOpt())) {
				String methodtoCheck = line.getOptionValue(methodToCheckOption.getOpt());

				if (!methodtoCheck.matches("^[A-Za-z0-9_-]+_[0-9]")) {
					methodtoCheck = methodtoCheck + "_0";
				}
				overridingProperties.put(TacoConfigurator.METHOD_TO_CHECK_FIELD, methodtoCheck);
			}

			// Dependencies classes
			if (line.hasOption(dependenciesOption.getOpt())) {
				String dependenciesClasses = "";
				for (String aDependencyClass : line.getOptionValues(dependenciesOption.getOpt())) {
					dependenciesClasses += aDependencyClass;
				}
				overridingProperties.put(TacoConfigurator.CLASSES_FIELD, dependenciesClasses);
			}

			// Relevant classes
			if (line.hasOption(relevantClassesOption.getOpt())) {
				String relevantClasses = "";
				for (String aRelevantClass : line.getOptionValues(relevantClassesOption.getOpt())) {
					relevantClasses += aRelevantClass;
				}
				overridingProperties.put(TacoConfigurator.RELEVANT_CLASSES, relevantClasses);
			}

			// Loop unrolling
			if (line.hasOption(loopsOptions.getOpt())) {
				loopUnrooling = Integer.parseInt(line.getOptionValue(loopsOptions.getOpt()));
			}

			// Int bitwidth
			if (line.hasOption(bitOptions.getOpt())) {
				String alloy_bitwidth_str = line.getOptionValue(bitOptions.getOpt());
				overridingProperties.put(TacoConfigurator.BITWIDTH, alloy_bitwidth_str);
				int alloy_bitwidth = new Integer(alloy_bitwidth_str);
				tacoScope.setAlloyBitwidth(alloy_bitwidth);
			}

			// instances scope
			if (line.hasOption(instOptions.getOpt())) {
				String assertionsArguments = "for " + line.getOptionValue(instOptions.getOpt());
				overridingProperties.put(TacoConfigurator.ASSERTION_ARGUMENTS, assertionsArguments);
			}

			// Skolemize
			if (line.hasOption(skolemizeOption.getOpt())) {
				overridingProperties.put(TacoConfigurator.SKOLEMIZE_INSTANCE_INVARIANT, false);
				overridingProperties.put(TacoConfigurator.SKOLEMIZE_INSTANCE_ABSTRACTION, false);
			}

			// Simulation
			if (line.hasOption(simulateOption.getOpt())) {
				overridingProperties.put(TacoConfigurator.INCLUDE_SIMULATION_PROGRAM_DECLARATION, true);
				overridingProperties.put(TacoConfigurator.GENERATE_CHECK, false);
				overridingProperties.put(TacoConfigurator.GENERATE_RUN, false);
			}

			// Modular Reasoning
			if (line.hasOption(modularReasoningOption.getOpt())) {
				overridingProperties.put(TacoConfigurator.MODULAR_REASONING, true);
			}

			// Relevancy Analysis
			if (line.hasOption(relevancyAnalysisOption.getOpt())) {
				overridingProperties.put(TacoConfigurator.RELEVANCY_ANALYSIS, true);
			}

		} catch (ParseException e) {
			System.err.println("Command line parsing failed: " + e.getMessage());
		}

		try {

			System.out.println("****** Starting Taco (version. " + tacoVersion + ") ****** ");
			System.out.println("");

			File file = new File("config/log4j.xml");
			if (file.exists()) {
				DOMConfigurator.configure("config/log4j.xml");
			} else {
				System.err.println("log4j:WARN File config/log4j.xml not found");
			}

			TacoMain main = new TacoMain();
			
			// BUILD TacoScope 
			
			//
			main.run(configFileArgument, overridingProperties);

		} catch (IllegalArgumentException e) {
			System.err.println("Error found:");
			System.err.println(e.getMessage());
		} catch (MethodToCheckNotFoundException e) {
			System.err.println("Error found:");
			System.err.println("Method to check was not found. Please verify config file, or add -m option");
		} catch (TacoException e) {
			System.err.println("Error found:");
			System.err.println(e.getMessage());
		}
	}

	public void run(String configFile) throws IllegalArgumentException {
		this.run(configFile, new Properties());
	}

	/**
	 * 
	 * @param configFile
	 * @param overridingProperties
	 *            Properties that overrides properties file's values
	 */

	public TacoAnalysisResult run(String configFile, Properties overridingProperties) throws IllegalArgumentException {
		if (configFile == null) {
			throw new IllegalArgumentException("Config file not found, please verify option -cf");
		}

				
		List<JCompilationUnitType> compilation_units = null;
		String classToCheck = null;
		String methodToCheck = null;
		
		
		

		// Start configurator
		JDynAlloyConfig.reset();
		JDynAlloyConfig.buildConfig(configFile, overridingProperties);

		
		
		List<JDynAlloyModule> jdynalloy_modules = new ArrayList<JDynAlloyModule>();
		SimpleJmlToJDynAlloyContext simpleJmlToJDynAlloyContext;
		if (TacoConfigurator.getInstance().getBoolean(TacoConfigurator.JMLPARSER_ENABLED, TacoConfigurator.JMLPARSER_ENABLED_DEFAULT)) {
			// JAVA PARSING
			String sourceRootDir = TacoConfigurator.getInstance().getString(TacoConfigurator.JMLPARSER_SOURCE_PATH_STR);

			if (TacoConfigurator.getInstance().getString(TacoConfigurator.CLASS_TO_CHECK_FIELD) == null) {
				throw new TacoException("Config key 'CLASS_TO_CHECK_FIELD' is mandatory. Please check your config file or add the -c parameter");
			}
			List<String> files = new ArrayList<String>(Arrays.asList(JDynAlloyConfig.getInstance().getClasses()));
			classToCheck = TacoConfigurator.getInstance().getString(TacoConfigurator.CLASS_TO_CHECK_FIELD);
			if (!files.contains(classToCheck)) {
				files.add(classToCheck);
			}

			JmlParser.getInstance().initialize(sourceRootDir, System.getProperty("user.dir") + System.getProperty("file.separator") + "bin" /* Unused */,
					files);
			compilation_units = JmlParser.getInstance().getCompilationUnits();
			// END JAVA PARSING

			// SIMPLIFICATION
			JmlStage aJavaCodeSimplifier = new JmlStage(compilation_units);
			aJavaCodeSimplifier.execute();
			JmlToSimpleJmlContext jmlToSimpleJmlContext = aJavaCodeSimplifier.getJmlToSimpleJmlContext();
			List<JCompilationUnitType> simplified_compilation_units = aJavaCodeSimplifier.get_simplified_compilation_units();
			// END SIMPLIFICATION

			// JAVA TO JDYNALLOY TRANSLATION
			SimpleJmlStage aJavaToDynJAlloyTranslator = new SimpleJmlStage(simplified_compilation_units);
			aJavaToDynJAlloyTranslator.execute();
			// END JAVA TO JDYNALLOY TRANSLATION
			simpleJmlToJDynAlloyContext = aJavaToDynJAlloyTranslator.getSimpleJmlToJDynAlloyContext();

			// JFSL TO JDYNALLOY TRANSLATION
			JfslStage aJfslToDynJAlloyTranslator = new JfslStage(simplified_compilation_units, aJavaToDynJAlloyTranslator.getModules(), jmlToSimpleJmlContext,
					simpleJmlToJDynAlloyContext);
			aJfslToDynJAlloyTranslator.execute();
/**/		aJfslToDynJAlloyTranslator = null;			
			// END JFSL TO JDYNALLOY TRANSLATION

			// PRINT JDYNALLOY
			JDynAlloyPrinterStage printerStage = new JDynAlloyPrinterStage(aJavaToDynJAlloyTranslator.getModules());
			printerStage.execute();
/**/		printerStage = null;
			// END PRINT JDYNALLOY

			jdynalloy_modules.addAll(aJavaToDynJAlloyTranslator.getModules());

		} else {
			simpleJmlToJDynAlloyContext = null;
		}

		// JDYNALLOY BUILT-IN MODULES
		PrecompiledModules precompiledModules = new PrecompiledModules();
		precompiledModules.execute();
		jdynalloy_modules.addAll(precompiledModules.getModules());		
		// END JDYNALLOY BUILT-IN MODULES

		// JDYNALLOY STATIC FIELDS CLASS
		JDynAlloyModule staticFieldsModule = precompiledModules.generateStaticFieldsModule();
		jdynalloy_modules.add(staticFieldsModule);
/**/	staticFieldsModule = null;	
		// END JDYNALLOY STATIC FIELDS CLASS

		// JDYNALLOY PARSING
		if (TacoConfigurator.getInstance().getBoolean(TacoConfigurator.JDYNALLOY_PARSER_ENABLED, TacoConfigurator.JDYNALLOY_PARSER_ENABLED_DEFAULT)) {
			log.info("****** START: Parsing JDynAlloy files ****** ");
			JDynAlloyParsingStage jDynAlloyParser = new JDynAlloyParsingStage(jdynalloy_modules);
			jDynAlloyParser.execute();
			jdynalloy_modules.addAll(jDynAlloyParser.getParsedModules());
/**/		jDynAlloyParser = null;	
			log.info("****** END: Parsing JDynAlloy files ****** ");
		} else {
			log.info("****** INFO: Parsing JDynAlloy is disabled (hint enablet it using 'jdynalloy.parser.enabled') ****** ");
		}
		// END JDYNALLOY PARSING

		// JDYNALLOY TO DYNALLOY TRANSLATION
		JDynAlloyStage dynJAlloyToDynAlloyTranslator = new JDynAlloyStage(jdynalloy_modules);
/**/	jdynalloy_modules = null;
		dynJAlloyToDynAlloyTranslator.execute();
		// BEGIN JDYNALLOY TO DYNALLOY TRANSLATION

		AlloyAnalysisResult alloy_analysis_result = null;
		DynalloyStage dynalloyToAlloy = null;

		// DYNALLOY TO ALLOY TRANSLATION
		if (TacoConfigurator.getInstance().getBoolean(TacoConfigurator.DYNALLOY_TO_ALLOY_ENABLE)) {

			dynalloyToAlloy = new DynalloyStage(dynJAlloyToDynAlloyTranslator.getOutputFileNames());
			dynalloyToAlloy.setSourceJDynAlloy(dynJAlloyToDynAlloyTranslator.getPrunedModules());
/**/		dynJAlloyToDynAlloyTranslator = null;
			dynalloyToAlloy.execute();
			// DYNALLOY TO ALLOY TRANSLATION

			log.info("****** Transformation process finished ****** ");

			if (TacoConfigurator.getInstance().getNoVerify() == false) {
				// Starts dynalloy to alloy tranlation and alloy verification

				AlloyStage alloy_stage = new AlloyStage(dynalloyToAlloy.get_alloy_filename());
/**/			dynalloyToAlloy = null;
				
				alloy_stage.execute();

				alloy_analysis_result = alloy_stage.get_analysis_result();
/**/			alloy_stage = null;
			}
		}
		
		
	
		
		TacoAnalysisResult tacoAnalysisResult = new TacoAnalysisResult(alloy_analysis_result);
				
				
				
				
		String junitFile = null;
		
		if (TacoConfigurator.getInstance().getGenerateUnitTestCase() || TacoConfigurator.getInstance().getAttemptToCorrectBug()) {
			// Begin JUNIT Generation Stage
			methodToCheck = overridingProperties.getProperty(TacoConfigurator.METHOD_TO_CHECK_FIELD);

			SnapshotStage snapshotStage = new SnapshotStage(compilation_units, tacoAnalysisResult, classToCheck, methodToCheck);
			snapshotStage.execute();

			RecoveredInformation recoveredInformation = snapshotStage.getRecoveredInformation();
			recoveredInformation.setFileNameSuffix(StrykerStage.fileSuffix);
			JUnitStage jUnitStage = new JUnitStage(recoveredInformation);
			jUnitStage.execute();
			junitFile = jUnitStage.getJunitFileName();
//			StrykerStage.fileSuffix++;
			// End JUNIT Generation Stage
		} else {
			log.info("****** JUnit with counterexample values will not be generated. ******* ");
			if (!TacoConfigurator.getInstance().getGenerateUnitTestCase()) {
				log.info("****** generateUnitTestCase=false ******* ");
			}

		}
				
				
		if (TacoConfigurator.getInstance().getBuildJavaTrace()) {
			if (tacoAnalysisResult.get_alloy_analysis_result().isSAT()) {
				log.info("****** START: Java Trace Generation ****** ");
				DynAlloyCompiler compiler = dynalloyToAlloy.getDynAlloyCompiler();
				JavaTraceStage javaTraceStage = new JavaTraceStage(compiler.getSpecContext(), alloy_analysis_result, false);
				javaTraceStage.execute();
//				DynAlloySolution dynAlloySolution = javaTraceStage.getDynAlloySolution();
//				List<TraceStep> trace = dynAlloySolution.getTrace();
				
				log.info("****** FINISH: Java Trace Generation ****** ");
			}
		} else {
			log.info("****** Java Trace will not be generated. ******* ");
			log.info("****** generateJavaTrace=false ******* ");
		}

		if (TacoConfigurator.getInstance().getAttemptToCorrectBug()) {
			if (tacoAnalysisResult.get_alloy_analysis_result().isSAT() &&
					tacoAnalysisResult.get_alloy_analysis_result().getAlloy_solution().getOriginalCommand().startsWith("Check")) {
				log.info("****** START: Stryker ****** ");
				methodToCheck = overridingProperties.getProperty(TacoConfigurator.METHOD_TO_CHECK_FIELD);
				String sourceRootDir = TacoConfigurator.getInstance().getString(
						TacoConfigurator.JMLPARSER_SOURCE_PATH_STR);
				StrykerStage strykerStage = new StrykerStage(compilation_units, sourceRootDir, classToCheck, 
						methodToCheck, configFile, overridingProperties, 
						TacoConfigurator.getInstance().getMaxStrykerMethodsForFile());
				StrykerStage.junitInputs = new Class<?>[50];
                StrykerStage.junitFiles = new String[50];
				

				try {
					String currentJunit = null;
					
					String tempFilename = junitFile.substring(0, junitFile.lastIndexOf(FILE_SEP)+1) /*+ FILE_SEP*/;	
					String packageToWrite = "ar.edu.output.junit";
					String fileClasspath = tempFilename.substring(0, tempFilename.lastIndexOf(new String("ar.edu.generated.junit").replaceAll("\\.", FILE_SEP)));
					fileClasspath = fileClasspath.replaceFirst("generated", "output");
//					String currentClasspath = System.getProperty("java.class.path")+PATH_SEP+fileClasspath/*+PATH_SEP+System.getProperty("user.dir")+FILE_SEP+"generated"*/;
					currentJunit = editTestFileToCompile(junitFile, classToCheck, packageToWrite, methodToCheck);

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
					
					
					
					
					
///*mfrias*/		int compilationResult =	javaCompiler.run(null, null, null /*new NullOutputStream()*/, new String[]{"-classpath", currentClasspath, currentJunit});
///**/				javaCompiler = null;
//					if(compilationResult == 0) {
					log.warn("junit counterexample compilation succeded");
					ClassLoader cl = ClassLoader.getSystemClassLoader();
					@SuppressWarnings("resource")
					ClassLoader cl2 = new URLClassLoader(new URL[]{new File(fileClasspath).toURI().toURL()}, cl);
//						ClassLoaderTools.addFile(fileClasspath);
					Class<?> clazz = cl2.loadClass(packageToWrite+"."+obtainClassNameFromFileName(junitFile));
//						Method[] meth = clazz.getMethods();
//						log.info("preparing to add a class containing a test input to the pool... "+packageToWrite+"."+MuJavaController.obtainClassNameFromFileName(junitFile));
//						Result result = null;
//						final Object oToRun = clazz.newInstance();
					StrykerStage.junitInputs[StrykerStage.indexToLastJUnitInput] = clazz;
					StrykerStage.junitFiles[StrykerStage.indexToLastJUnitInput] = junitFile;
					StrykerStage.indexToLastJUnitInput++;
					cl = null;
					cl2 = null;

//					
//					} else {
//						log.warn("compilation failed");
//					}
//							File originalFile = new File(tempFilename);
//							originalFile.delete();

				} catch (ClassNotFoundException e) {
//							e.printStackTrace();
				} catch (IOException e) {
//							e.printStackTrace();
				} catch (IllegalArgumentException e) {
//							e.printStackTrace();
				} catch (Exception e) {
//							e.printStackTrace();
				}
				
				strykerStage.execute();
				
				log.info("****** FINISH: Stryker ****** ");
			}
		} else {
			log.info("****** BugFix will not be generated. ******* ");
			log.info("****** attemptToCorrectBug=false ******* ");		
		}

		return tacoAnalysisResult;
	}

	/**
	 * 
	 */
	private static String getManifestAttribute(Name name) {
		String manifestAttributeValue = "Undefined";
		try {

			String jarFileName = System.getProperty("java.class.path").split(System.getProperty("path.separator"))[0];
			JarFile jar = new JarFile(jarFileName);
			Manifest manifest = jar.getManifest();

			Attributes mainAttributes = manifest.getMainAttributes();
			manifestAttributeValue = mainAttributes.getValue(name);
			jar.close();
		} catch (IOException e) {
		}

		return manifestAttributeValue;
	}
	
	
	
	public static String editTestFileToCompile(String junitFile, String sourceClassName, String classPackage, String methodName) {
		String tmpDir = junitFile.substring(0, junitFile.lastIndexOf(FILE_SEP));
		tmpDir = tmpDir.replaceAll("generated", "output");
		File destFile = new File(tmpDir,obtainClassNameFromFileName(junitFile)+ /*"_temp" +*/ ".java");
		String packageSentence = "package "+classPackage+";\n";
		int posLastUnderscore = methodName.lastIndexOf("_");
		methodName = methodName.substring(0, posLastUnderscore);
		try {
			destFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(destFile);
			boolean packageAlreadyWritten = false;
			Scanner scan = new Scanner(new File(junitFile));
			scan.useDelimiter("\n");
			boolean nextToTest = false;
			String str = null;
			while(scan.hasNext()){
				str = scan.next();
				if( nextToTest ) {
					str = str.replace("()","(String fileClasspath, String className, String methodName) throws IllegalAccessException, InvocationTargetException, ClassNotFoundException, InstantiationException, MalformedURLException");
					fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
					nextToTest = false;
//				} else if (str.contains("public class")){
//					int posOpeningBrace = str.indexOf("{");
//					str = str.substring(0, posOpeningBrace-1);
//					str = str + "_temp {";
//					fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
				} else if( str.contains("package") && !packageAlreadyWritten){
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
					fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
					packageAlreadyWritten = true;
				} else if (str.contains("import") && !packageAlreadyWritten) {
					fos.write(packageSentence.getBytes(Charset.forName("UTF-8")));
					fos.write((scan.next() + "\n").getBytes(Charset.forName("UTF-8")));
					packageAlreadyWritten = true;
		        } else if (str.contains("new " + sourceClassName+"(")){
//		          str = "        try {";
//		          fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));

		          str = "           String[] classpaths = fileClasspath.split(System.getProperty(\"path.separator\"));";
		          fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
		          str = "           URL[] urls = new URL[classpaths.length];";
		          fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
		          str = "           for (int i = 0 ; i < classpaths.length ; ++i) {";
		          fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
		          str = "           urls[i] = new File(classpaths[i]).toURI().toURL();";
		          fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
		          str = "           }";
		          fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
		          str = "           ClassLoader cl2 = new URLClassLoader(urls);";
		          fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));

		          
//		          str = "           ClassLoaderTools.addFile(fileClasspath);";
//		          fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
		          str = "           Class<?> clazz = cl2.loadClass(className);"; 				
					fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
					str = "           Object instance = clazz.newInstance();";
					fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
					str = "           cl2 = null;";
					fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));

				} else if (str.contains("Class<?> clazz;")) {	
				} else if (str.contains("} catch (ClassNotFoundException e) {")) {
					str = str.replace("ClassNotFoundException", "Exception");
					fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
				} else if( str.matches(".*(?i)[\\.a-z0-9\\_]*"+sourceClassName+"(?=[^a-z0-9\\_\\.]).*")){
					str = str.replaceAll("(?i)[\\.a-z0-9\\_]*"+sourceClassName+"(?=[^a-z0-9\\_\\.])", /*classPackage+"."+*/sourceClassName);
					str = str.replace("\""+methodName+"\"", "methodName");
					str = str.replace("\""+sourceClassName+"\"", "clazz");
//					str = str.replace("(", "(fileClasspath, ");
					fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
				} else if (str.contains("e.printStackTrace();")) {
//					fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
					fos.write(("           throw(new java.lang.RuntimeException(e));" + "\n").getBytes(Charset.forName("UTF-8")));
//					fos.write(("throw e;" + "\n").getBytes(Charset.forName("UTF-8")));
				} else if (str.contains("private Method getAccessibleMethod")) {
					str = str.replace("(String className, ", "(Class<?> clazz, ");
//					str = str.replace(") {", ") throws MalformedURLException {");
					fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
				} else if (str.contains("method.invoke(instance,")) {
					fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
					str = "           instance = null;";
					fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
					str = "           method = null;";
					fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));

				} else if (str.contains("methodToCheck = clazz.getDeclaredMethod(methodName, parameterTypes);")) {
					fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
				} else if (str.contains("clazz = Class.forName(className);")) {
//					str = "           ClassLoader cl = ClassLoader.getSystemClassLoader();";
//					fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
//					str = "           final ClassLoader cl2 = new URLClassLoader(new URL[]{new File(fileClasspath).toURI().toURL()}, cl);";
//					fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
//					str = "           clazz = cl2.loadClass(className);";					
//					fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
//					str = "           System.out.println(\"actual class inside method: \"+clazz.getName());";
//					fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
				} else {
					if (str.contains("@Test")) {
						nextToTest = true;
					}
//					if (!scan.hasNext()){
//						String s = "        } catch (ClassNotFoundException e){";
//						fos.write((s + "\n").getBytes(Charset.forName("UTF-8")));
//						s = "        } catch (InstantiationException e){}";
//						fos.write((s + "\n").getBytes(Charset.forName("UTF-8")));
//					}
					fos.write((str + "\n").getBytes(Charset.forName("UTF-8")));
				}
			}
			fos.close();
			scan.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFile.toString();
		
	}
	
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
	
	
	
	
	
	
	
	
	
	



	
}


