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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.jar.Attributes;
import java.util.jar.Attributes.Name;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

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
import ar.uba.dc.rfm.dynalloy.trace.DynAlloySolution;
import ar.uba.dc.rfm.dynalloy.trace.TraceStep;

public class TacoMain {

	private static Logger log = Logger.getLogger(TacoMain.class);

	private static final String CMD = "Taco";
	private static final String HEADER = "Taco static analysis tool.";
	private static final String FOOTER = "For questions and comments please write to jgaleotti AT dc DOT uba DOT ar";

	/**
	 * @param args
	 */
	@SuppressWarnings({ "static-access", "unchecked" })
	public static void main(String[] args) {
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
			// END JFSL TO JDYNALLOY TRANSLATION

			// PRINT JDYNALLOY
			JDynAlloyPrinterStage printerStage = new JDynAlloyPrinterStage(aJavaToDynJAlloyTranslator.getModules());
			printerStage.execute();
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
		// END JDYNALLOY STATIC FIELDS CLASS

		// JDYNALLOY PARSING
		if (TacoConfigurator.getInstance().getBoolean(TacoConfigurator.JDYNALLOY_PARSER_ENABLED, TacoConfigurator.JDYNALLOY_PARSER_ENABLED_DEFAULT)) {
			log.info("****** START: Parsing JDynAlloy files ****** ");
			JDynAlloyParsingStage jDynAlloyParser = new JDynAlloyParsingStage(jdynalloy_modules);
			jDynAlloyParser.execute();
			jdynalloy_modules.addAll(jDynAlloyParser.getParsedModules());
			log.info("****** END: Parsing JDynAlloy files ****** ");
		} else {
			log.info("****** INFO: Parsing JDynAlloy is disabled (hint enablet it using 'jdynalloy.parser.enabled') ****** ");
		}
		// END JDYNALLOY PARSING

		// JDYNALLOY TO DYNALLOY TRANSLATION
		JDynAlloyStage dynJAlloyToDynAlloyTranslator = new JDynAlloyStage(jdynalloy_modules);
		dynJAlloyToDynAlloyTranslator.execute();
		// BEGIN JDYNALLOY TO DYNALLOY TRANSLATION

		AlloyAnalysisResult alloy_analysis_result = null;
		DynalloyStage dynalloyToAlloy = null;

		// DYNALLOY TO ALLOY TRANSLATION
		if (TacoConfigurator.getInstance().getBoolean(TacoConfigurator.DYNALLOY_TO_ALLOY_ENABLE)) {

			dynalloyToAlloy = new DynalloyStage(dynJAlloyToDynAlloyTranslator.getOutputFileNames());
			dynalloyToAlloy.setSourceJDynAlloy(dynJAlloyToDynAlloyTranslator.getPrunedModules());
			dynalloyToAlloy.execute();
			// DYNALLOY TO ALLOY TRANSLATION

			log.info("****** Transformation process finished ****** ");

			if (TacoConfigurator.getInstance().getNoVerify() == false) {
				// Starts dynalloy to alloy tranlation and alloy verification

				AlloyStage alloy_stage = new AlloyStage(dynalloyToAlloy.get_alloy_filename());

				alloy_stage.execute();
				alloy_analysis_result = alloy_stage.get_analysis_result();
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

			JUnitStage jUnitStage = new JUnitStage(recoveredInformation);
			jUnitStage.execute();
			junitFile = jUnitStage.getJunitFileName();

			// End JUNIT Generation Stage
		} else {
			log.info("****** JUnit with counterejample values will not be generated. ******* ");
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
				DynAlloySolution dynAlloySolution = javaTraceStage.getDynAlloySolution();
				List<TraceStep> trace = dynAlloySolution.getTrace();
				
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
				StrykerStage strykerStage = new StrykerStage(compilation_units, sourceRootDir, classToCheck, methodToCheck, configFile, overridingProperties, junitFile, TacoConfigurator.getInstance().getMaxStrykerMethodsForFile());
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
		} catch (IOException e) {
		}

		return manifestAttributeValue;
	}
}
