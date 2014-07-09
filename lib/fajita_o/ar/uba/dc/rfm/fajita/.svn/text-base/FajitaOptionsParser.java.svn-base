package ar.uba.dc.rfm.fajita;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import rfm.alloy.CLI;


/**
 * This class contains the logic for parsing command line arguments and config
 * files and creating a valid <code>FajitaConfiguration</code>s from it.
 * 
 * @see FajitaConfiguration
 * 
 */
public abstract class FajitaOptionsParser {
	
	/** Group of options read from the command line. */
	private static final Options options = new Options();
	
	/** Order in which the options are shown in the usage.*/
	private static String appearanceOrder = "";
	
	/**
	 *  A mapping of command line option tokens with their correspondent
	 * <code>FajitaOption</code>.
	 */
	private static Map<String, FajitaOption> commandLineTokens =
		new HashMap<String, FajitaOption>();
	
	/**
	 * A mapping of config file option tokens with their correspondent
	 * <code>FajitaOption</code>.
	 */
	private static Map<String, FajitaOption> configurationFileTokens =
		new HashMap<String, FajitaOption>();
	
	
	/**
	 * Static initialization of the previous values implicitly invoking the
	 * logic found in the constructor a the <code>FajitaOption</code>s.
	 */
	static { FajitaOption.values(); }
	
	
	/**
	 * This enumeration holds all the possible options, the tokens that
	 * represent them in the different configuration media, their description
	 * and the <code>FajitaConfiguration</code> initialization logic that they
	 * trigger. 
	 * 
	 */
	private static enum FajitaOption {
		CLASSPATH_OPTION(
			"cp", "classpath", "CLASSPATH", true,
			"classpath storing source code to be verified",
			new OptionInitializer() {
				public void initialize(String value, FajitaConfiguration config) {
					config.setClasspath(value);
				}
			}
		),
		CLASS_TO_CHECK_OPTION(
			"c", "class_to_check", "CLASS_TO_CHECK", true,
			"fully qualified class name to verify",
			new OptionInitializer() {
				public void initialize(String value, FajitaConfiguration config) {
					config.setClassToCheck(value);
				}
			}
		),
		METHOD_TO_CHECK_OPTION(
			"m", "method_to_check", "METHOD_TO_CHECK", true,
			"method name to be verified",
			new OptionInitializer() {
				public void initialize(String value, FajitaConfiguration config) {
					config.setMethodToCheck(value);
				}
			}
		),
		PROPERTIES_TEMPLATE_FILE_OPTION(
			"tf", "properties_template_file", "PROPERTIES_TEMPLATE_FILE", true,
			"properties template file with all required TACO settings",
			new OptionInitializer() {
				public void initialize(String value, FajitaConfiguration config) {
					config.setTacoPropertiesTemplate(value);
				}
			}
		),
		RESULT_PATH_OPTION(
			"rp", "result_path", "RESULT_PATH", true,
			"result path for FAJITA output",
			new OptionInitializer() {
				public void initialize(String value, FajitaConfiguration config) {
					config.setResultPath(value);
				}
			}
		),
		COVERAGE_CRITERIA_OPTION(
			"r", "coverage_criteria", "COVERAGE_CRITERIA", true,
			"selects coverage (goal, class, branch or dual).\n Dual does an iteration of " +
			"class coverage to quickly cover easy goals and continues with the missing " +
			"goals for branch coverage (default is goal coverage)",
			new OptionInitializer() {
				public void initialize(String value, FajitaConfiguration config) {
					config.setCoverageCriteria(value);
				}
			}
		),
		LOOP_UNROLL_OPTION(
			"u", "loop_unroll", "LOOP_UNROLL", true,
			"number of unrolls (default is " + FajitaConfiguration.DEFAULT_LOOP_UNROLL + ")",
			new OptionInitializer() {
				public void initialize(String value, FajitaConfiguration config) {
					config.setMaximumLoopUnroll(Math.max(Integer.parseInt(value), 1));
				}
			}
		),
		OBJECT_SCOPE_OPTION(
			"s", "object_scope", "OBJECT_SCOPE", true,
			"size of the object pool to be used (default is " + FajitaConfiguration.DEFAULT_OBJECT_SCOPE + ")",
			new OptionInitializer() {
				public void initialize(String value, FajitaConfiguration config) {
					config.setMaximumObjectScope(Integer.parseInt(value));
				}
			}
		),
		INCREMENTAL_UNROLL_OPTION(
			"iu", "incremental_unroll", "INCREMENTAL_UNROLL", false,
			"turns the unroll-scope exploration heuristic on",
			new OptionInitializer() {
				public void initialize(String value, FajitaConfiguration config) {
					config.setIncrementalLoopUnroll(
						value == null ? true : Boolean.parseBoolean(value));
				}
			}
		),
		IGNORE_GOALS(
			"ig", "ignore_goals", "IGNORE_GOALS", true,
			"Considers the given goals as covered (separated by commas and no whitespaces). " +
			"If non-empty while using dual coverage triggers an execution with branch " +
			"coverage, otherwise it uses class coverage.",
			new OptionInitializer() {
				public void initialize(String value, FajitaConfiguration config) {
					String[] goals = value.split(",");
					Set<Integer> coveredGoals = config.getCoveredGoals();
					for (String goal : goals)
						coveredGoals.add(Integer.parseInt(goal));
					if (!coveredGoals.isEmpty())
						config.setDualClassBranchIteration(1);
				}
			}
		),
		RELEVANT_CLASSES_OPTION(
			"rc", "relevant_classes", "RELEVANT_CLASSES", true,
			"a comma separated list of the classes required to compile to run the one being checked",
			new OptionInitializer() {
				public void initialize(String value, FajitaConfiguration config) {
					config.setRelevantClasses(value);
				}
			}
		),
		TIMEOUT_SECS_OPTION(
			"to", "timeout_secs", "TIMEOUT_SECS", true,
			"seconds to wait for a particular sat solution to be obtained",
			new OptionInitializer() {
				public void initialize(String value, FajitaConfiguration config) {
					config.setTimeoutSecs(Integer.parseInt(value));
				}
			}
		),
		GENERATE_ALLOY_OPTION(
			"a", "only_alloy", "GENERATE_ALLOY", false,
			"don't solve; just translate to Alloy (default is false)",
			new OptionInitializer() {
				public void initialize(String value, FajitaConfiguration config) {
					config.setOnlyTranslateToAlloy(
						value == null ? true : Boolean.parseBoolean(value));
				}
			}
		),
		GENERATE_JUNIT_OPTION(
			"gu", "generate_junit", "GENERATE_JUNIT", true,
			"generate a junit file with the set of tests found",
			new OptionInitializer() {
				public void initialize(String value, FajitaConfiguration config) {
					config.setGenareteJUnit(Boolean.parseBoolean(value));
				}
			}
		),
		INT_BITWIDTH_OPTION(
			"b", "int_bitwidth", "INT_BITWIDTH", true,
			"alloy integer bitwidth (default is 4)",
			new OptionInitializer() {
				public void initialize(String value, FajitaConfiguration config) {
					config.setIntBitwidth(Integer.parseInt(value));
				}
			}
		),
		CHOOSE_SAT_SOLVER(
			"cs", "choose_solver", "CHOOSE_SOLVER", true,
			"sets the SAT solver (default is minisat, we support sat4j)",
			new OptionInitializer() {
				public void initialize(String value, FajitaConfiguration config) {
					config.setSolver(value);
				}
			}
		),
		APPEND_BOUNDS_IF_AVAILABLE_OPTION(
			"o", "append_bounds_if_available", "APPEND_BOUNDS_IF_AVAILABLE", true,
			"if a tight bound is in the repository, add it to the Alloy model (default is true)",
			new OptionInitializer() {
				public void initialize(String value, FajitaConfiguration config) {
					config.setAppendBoundAvailable(
						value == null ? true : Boolean.parseBoolean(value));
				}
			}
		),
		CONFIG_FILE_OPTION(
			"cf", "config_file", "CONFIG_FILE", true,
			"config file with all required FAJITA settings",
			new OptionInitializer() {
				public void initialize(String value, FajitaConfiguration config) {
					FajitaOptionsParser.parseConfigFile(value, config);
				}
			}
		),
		PRINT_VERSION_OPTION(
			"v", "version", "PRINT_VERSION", false,
			"show version information",
			new OptionInitializer() {
				public void initialize(String value, FajitaConfiguration config) {
					System.out.println("Version information:");
					System.out.println("\t" + CLI.ALLOY_CLI_VERSION);
					System.out.println("\t" + FajitaMain.FAJITA_VERSION);
					System.exit(3);
				}
			}
		),
		PRINT_HELP_OPTION(
			"h", "help", "PRINT_HELP", false,
			"display this text",
			new OptionInitializer() {
				public void initialize(String value, FajitaConfiguration config) {
					HelpFormatter formatter = new HelpFormatter();
					formatter.setLeftPadding(2);
					formatter.setLongOptPrefix(" --");
					formatter.setSyntaxPrefix("usage: ");
					formatter.setOptionComparator(new java.util.Comparator<Option>() {
						public int compare(Option o1, Option o2) {
							Integer pos1 = appearanceOrder.indexOf(o1.getOpt() + ' ');
							Integer pos2 = appearanceOrder.indexOf(o2.getOpt() + ' ');
							return pos1.compareTo(pos2);
						}
					});
					formatter.printHelp(
						79,
						"\nfajita.FajitaMan [arguments] \n",
						"",
						options,
						"\ntip: classpath and (config_file or (class_to_check and " +
						"method_to_check)) are mandatory arguments.\n"
					);
					System.exit(2);
				}
			}
		);
		
		// DEFINITION OF A FAJITA OPTION FUNCTIONALITY
		
		/** Command line short option token. */
		public final String opt;
		
		/** 
		 * Option object in the options collection referencing to this
		 *  <code>FajitaOption</code>.
		 */
		private Option option;
		
		/** Initialization code to launch when parsing this option. */
		private OptionInitializer initializer;

		
		/**
		 * Constructor for a <code>FajitaOption</code>.
		 * 
		 * @param opt <code>String</code> with the the short command line
		 *	option token.
		 *  
		 * @param longOpt <code>String</code> with the long command line
		 *	option token.
		 *
		 * @param fileOpt <code>String</code> with the config file token
		 *	for this option.
		 *
		 * @param hasArg <code>boolean</code> flag that indicates whether this
		 *	option requires an argument.
		 *
		 * @param description <code>String</code> with the description of the
		 *	option shown in the usage.
		 *
		 * @param initializer <code>OptionInitilizer</code> with the logic
		 *	triggered at parsing this option.
		 * 
		 */
		private FajitaOption(
				String opt, String longOpt, String fileOpt, boolean hasArg,
				String description, OptionInitializer initializer)
		{
			this.opt = opt;
			this.option = new Option(opt, longOpt, hasArg, description);
			this.initializer = initializer;
			FajitaOptionsParser.options.addOption(this.option);
			FajitaOptionsParser.appearanceOrder += opt + ' ';
			FajitaOptionsParser.commandLineTokens.put(opt, this);
			FajitaOptionsParser.configurationFileTokens.put(fileOpt, this);
		}
		
		
		/**
		 * Proxy method that invokes the <code>OptionInitializer</code>
		 * belonging to this instance.
		 * 
		 * @param value parsed when the option was read or null if this option
		 *	doesn't take parameters.
		 * 
		 * @param config the <code>FajitaConfiguration</code> object that is
		 *	updated by this option.
		 * 
		 */
		public void initialize(String value, FajitaConfiguration config) {
			initializer.initialize(value, config);
		}
		
		
		/**
		 * Private interface to be able to generate different initializing
		 * triggers for the different options. 
		 * 
		 */
		private static interface OptionInitializer {
			public void initialize(String value, FajitaConfiguration config);
		}
		
	}
	
	
	/**
	 * This method updates a <code>FajitaConfiguration</code> with parameters
	 * taken from the command line.
	 * 
	 * @param args <code>String[]</code> with the command line parameters.
	 * 
	 * @param config <code>FajitaConfiguration</code> to be updated.
	 * 
	 */
	public static void parseCommandLine(String[] args, FajitaConfiguration config) {
		try {
			
			CommandLine commandLine = new GnuParser().parse(options, args);
			for (Option option : commandLine.getOptions())
				commandLineTokens.get(option.getOpt()).initialize(option.getValue(), config);
			
			config.assertValidity();
			
		} catch (Exception e) {
			System.err.println(
				"Syntax error: " + e.getMessage() + "\n" +
				"Invoke with -" + FajitaOption.PRINT_HELP_OPTION.opt + " to get usage help." + "\n"
			);
			System.exit(1);
		}
	}
	
	
	/**
	 * This method updates a <code>FajitaConfiguration</code> with parameters
	 * taken from a config file.
	 * 
	 * @param filename <code>String</code> with the config file name.
	 * 
	 * @param config <code>FajitaConfiguration</code> to be updated.
	 * 
	 */
	public static void parseConfigFile(String filename, FajitaConfiguration config) {
		try {
			
			BufferedReader fileReader = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = fileReader.readLine()) != null) {
				line = line.trim();
				int commentStartIndex = line.indexOf('#');
				if (commentStartIndex > 0)
					line = line.substring(0, commentStartIndex); // Discards comments
				String[] values = line.split("=");
				if (values.length == 2) {
					FajitaOption option = configurationFileTokens.get(values[0].trim());
					if (option != null)
						option.initialize(values[1].trim(), config);
					else
						System.err.println(
							"Unrecognized option " + values[0] + ", will be ignored.");
				}
			}
			fileReader.close();
			
		} catch (FileNotFoundException e) {
			System.err.println("Configuration file " + filename + " not found.");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Permission denied while reading " + filename);
			System.exit(1);
		} catch (FajitaException e) {
			System.err.println(
				e.getMessage() + "\n" +
				"Invoke with -" + FajitaOption.PRINT_HELP_OPTION.opt + " to get usage help." + "\n"
			);
			System.exit(1);
		}
	}
	
}
