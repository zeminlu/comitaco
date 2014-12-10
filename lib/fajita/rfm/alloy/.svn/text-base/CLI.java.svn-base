package rfm.alloy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import rfm.alloy.CoverageClauseAdder.SolutionCoverage;
import edu.mit.csail.sdg.alloy4.Err;
import edu.mit.csail.sdg.alloy4compiler.ast.Command;
import edu.mit.csail.sdg.alloy4compiler.ast.Module;
import edu.mit.csail.sdg.alloy4compiler.parser.CompUtil;
import edu.mit.csail.sdg.alloy4compiler.translator.A4Options;
import edu.mit.csail.sdg.alloy4compiler.translator.A4Solution;
import edu.mit.csail.sdg.alloy4compiler.translator.TranslateAlloyToKodkod;

public class CLI {

	public static final String ALLOY_CLI_VERSION = "AlloyCli 1.5.8";
	
	public static Reporter reporter = null;

	public static long solvingTime = -1;

	public static A4Solution answer = null;

	///////////////////////////////////////////////////////////////////////////
	// The following attributes are needed in order to do path coverage      //
	///////////////////////////////////////////////////////////////////////////
	public static Module world;
	public static Command command;

	public static Integer loopExhaustionId = null;
	///////////////////////////////////////////////////////////////////////////
	
	///////////////////////////////////////////////////////////////////////////
	// This is needed to generate unit tests from the fajita project.        //
	// If left untouched does nothing. Fajita will change this propperly.    //
	///////////////////////////////////////////////////////////////////////////
	public static CoverageClauseCallback coverageClauseCallback =
		new CoverageClauseCallback.DummyCallback();
	///////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args) throws Err, IOException {
		int ret_val = run(args);
		System.exit(ret_val);
	}

	public static int run(String[] args) throws Err, IOException {

		CommandLine commandLine = parseCommandLine(args);
		A4Options alloy4Options = xlateCommandLine(commandLine);

		String filename = commandLine.getArgs()[0];

		reporter = new Reporter();
		Reporter log = reporter;

		log.parseAndTypeCheck(filename);
		
		world = CompUtil.parseEverything_fromFile(log, null, filename);
		command = world.getAllCommands().get(0);
		
		log.alloy2kodkod(command);
		answer = TranslateAlloyToKodkod.execute_command(
			log, world.getAllReachableSigs(), command, alloy4Options);

		if (answer == null) {
			log.translationFinished();
			return 30;
		}

		if (commandLine.hasOption("e")) {
			if (answer.satisfiable()) {
				int maxSecs = 0, maxSols = 0;
				boolean ignoreSols = false;
				String fileName = null, xmlPrefix = null;
				if (commandLine.hasOption("x"))
					xmlPrefix = commandLine.getOptionValue("x");
				if (commandLine.hasOption("t"))
					maxSecs = Integer.parseInt(commandLine.getOptionValue("t"));
				if (commandLine.hasOption("n"))
					maxSols = Integer.parseInt(commandLine.getOptionValue("n"));
				if (commandLine.hasOption("f"))
					fileName = commandLine.getOptionValue("f");
				if (commandLine.hasOption("i"))
					ignoreSols = true;

				enumSolutions(log, maxSols, maxSecs, fileName, xmlPrefix,
						ignoreSols);
			} else {
				System.err.println("Error: cannot enumerate if UNSAT!");
			}
		} else if (commandLine.hasOption("x") && answer.satisfiable()) {
			String xmlPath = commandLine.getOptionValue("x");
			answer.writeXML(xmlPath);
			log.resultXML(xmlPath);
		}

		log.analysisFinished();

		if (answer.satisfiable()) {
			return 10;
		} else {
			return 20;
		}

	}
	
	
	public static void solutionObtained() {
		coverageClauseCallback.call(answer, world, command);
	}

	public static A4Options xlateCommandLine(CommandLine commandLine) {

		A4Options alloyOptions = new A4Options();

		// Default solver is MiniSat220JNI if none is specified
		alloyOptions.solver = A4Options.SatSolver.MiniSat220JNI;
		
		if (commandLine.hasOption("Q")) {
			kodkod.ast.Relation.enumerateBooleanQueriesHackEnabled = true;
			CoverageClauseAdder.solution_coverage = SolutionCoverage.CLASS_COVERAGE;
		}

		if (commandLine.hasOption("r")) {
			kodkod.ast.Relation.enumerateBooleanQueriesHackEnabled = true;
			String coverage = commandLine.getOptionValue("r");
			if (coverage.equals("class")) {
				CoverageClauseAdder.solution_coverage = SolutionCoverage.CLASS_COVERAGE;
			} else if (coverage.equals("goal")) {
				CoverageClauseAdder.solution_coverage = SolutionCoverage.GOAL_COVERAGE;
			} else {
				CoverageClauseAdder.solution_coverage = SolutionCoverage.CLASS_COVERAGE;
			}
		}

		if (commandLine.hasOption("C")) {
			TranslateAlloyToKodkod.removeAlloySyntaxChecksHackEnabled = true;
		}

		if (commandLine.hasOption("s")) {
			String solver = commandLine.getOptionValue("s");
			if (solver.equals("minisat220"))
				alloyOptions.solver = A4Options.SatSolver.MiniSat220JNI;
			else if (solver.equals("minisat"))
				alloyOptions.solver = A4Options.SatSolver.MiniSatJNI;
			else if (solver.equals("zchaff"))
				alloyOptions.solver = A4Options.SatSolver.ZChaffJNI;
			else if (solver.equals("sat4j"))
				alloyOptions.solver = A4Options.SatSolver.SAT4J;
			// FIXME: ... else raise an exception (bad solver id)
			// instead of quietly using the default solver
		}

		if (commandLine.hasOption("c")) {
			alloyOptions.solver = A4Options.SatSolver.CNF;
		}

		if (commandLine.hasOption("k")) {
			alloyOptions.solver = A4Options.SatSolver.KK;
		}

		if (commandLine.hasOption("b")) {
			alloyOptions.solver = A4Options.SatSolver.CNF;
			kodkod.engine.fol2sat.Translator.bedHackEnabled = true;
			String basePath = commandLine.getArgs()[0];
			if (basePath.endsWith(".als"))
				basePath = basePath.substring(0, basePath.length() - 4);
			kodkod.engine.fol2sat.Translator.bedHackBasePath = basePath;
		}

		return alloyOptions;
	}

	public static void enumSolutions(Reporter log, int maxSols, int maxSecs,
			String outFile, String xmlPrefix, boolean ignoreSols) throws Err,
			IOException {

		boolean maxSolsEnabled = (maxSols > 0);
		boolean maxTimeEnabled = (maxSecs > 0);
		boolean outFileEnabled = (outFile != null);
		boolean xmlSaveEnabled = (xmlPrefix != null);

		BufferedWriter writer = null;
		if (outFileEnabled)
			writer = new BufferedWriter(new FileWriter(outFile));

		log.enumSolutions(maxSolsEnabled, maxSols, maxTimeEnabled, maxSecs,
				outFileEnabled, outFile);

		int solsFound = 0;

		long startTime = System.currentTimeMillis() - solvingTime;
		long endTime = startTime + maxSecs * 1000;
		long lastTime = startTime, currTime = startTime;

		boolean moreSolsLeft = answer.satisfiable();
		boolean maxSolsReached = false;
		boolean maxTimeReached = false;

		while (moreSolsLeft && !(maxTimeReached || maxSolsReached)) {
			if (outFileEnabled) {
				currTime = System.currentTimeMillis();
				writer.write(String.format(
						"Solution #%d found after %d ms (took %d ms)\n",
						solsFound, currTime - startTime, currTime - lastTime));
				if (!ignoreSols) {
					writer.write(answer.toString());
					writer.write("\n\n");
				}
				writer.flush();
				lastTime = currTime;
			}
			if (xmlSaveEnabled) {
				answer.writeXML(xmlPrefix + ".sol"
						+ Integer.toString(solsFound) + ".xml");
			}
			++solsFound;
			answer = answer.next();
			if (!answer.satisfiable())
				moreSolsLeft = false;
			if (maxTimeEnabled && System.currentTimeMillis() >= endTime)
				maxTimeReached = true;
			if (maxSolsEnabled && solsFound >= maxSols)
				maxSolsReached = true;
		}

		log.enumDone(solsFound, moreSolsLeft, maxSolsReached, maxTimeReached);

		if (outFileEnabled)
			writer.close();

	}

	public static CommandLine parseCommandLine(String[] arguments) {

		CommandLineParser parser = new GnuParser();
		CommandLine commandLine = null;
		Options options = defineCommandLineOptions();

		try {
			commandLine = parser.parse(options, arguments);
		} catch (ParseException exp) {
			System.err.println("Syntax error: " + exp.getMessage());
			System.err.println("Invoke with -h to get usage help.");
			System.exit(1);
		}

		if (commandLine.hasOption("h")) {
			showUsage(options);
			System.exit(2);
		} else if (commandLine.hasOption("v")) {
			showVersion();
			System.exit(3);
		} else if (commandLine.getArgs().length != 1) {
			System.err.println("Please provide a pathname to an Alloy spec.");
			System.err.println("Invoke with -h to get usage help.");
			System.exit(4);
		}

		return commandLine;
	}

	public static Options defineCommandLineOptions() {

		Options options = new Options();

		OptionGroup solv = new OptionGroup();
		solv.addOption(new Option("s", "solver", true,
				"minisat220 (default), minsiat, zchaff, sat4j"));
		solv.addOption(new Option("b", "bed", false,
				"don't solve; just translate to BED"));
		solv.addOption(new Option("c", "cnf", false,
				"don't solve; just translate to CNF"));
		solv.addOption(new Option("k", "kodkod", false,
				"don't solve; just translate to Kodkod"));
		options.addOptionGroup(solv);

		options.addOption("e", "enumerate", false,
				"enumerate (ideally all) solutions found");
		options.addOption("n", "enum-upto", true,
				"stop enumerating if N solutions are found");
		options.addOption("t", "enum-time", true,
				"stop enumerating if T seconds have elapsed");
		options.addOption("f", "enum-file", true,
				"save solutions to file instead of just counting");
		options.addOption("i", "enum-ignoresols", false,
				"save sol-finding times only (not the solutions)");
		options.addOption("x", "xml", true,
				"save solution(s) to arg.xml file (arg.#.xml files)");

		options.addOption("B", "bounds", false,
				"enable bounds hack (GuignaSimplifier)");
		options.addOption("Q", "eboolq", false,
				"enable boolean queries enumeration hack");
		options.addOption("r", "coverage", true,
				"enumerate soluctions using criteria <arg>" + "\n"
						+ "class: class coverage" + "\n"
						+ "goal:  goal coverage" + "\n");

		options.addOption("C", "disable-als-checks", false,
						"disable time-consuming alloy syntax checks (for auto-generated als code only)");

		OptionGroup info = new OptionGroup();
		info.addOption(new Option("v", "version", false,
				"show version information"));
		info.addOption(new Option("h", "help", false, "display this text"));
		options.addOptionGroup(info);

		return options;

	}

	public static void showUsage(Options options) {

		HelpFormatter formatter = new HelpFormatter();
		formatter.setLeftPadding(2);
		formatter.setLongOptPrefix(" --");
		formatter.setSyntaxPrefix("usage: ");
		formatter.setOptionComparator(new java.util.Comparator() {
			private final String appearanceOrder = "sbckentfixBQvh";

			public int compare(Object o1, Object o2) {
				String k1 = ((Option) o1).getOpt();
				String k2 = ((Option) o2).getOpt();
				if (k1.equals(k2))
					return 0;
				int pos1 = appearanceOrder.indexOf(k1);
				int pos2 = appearanceOrder.indexOf(k2);
				return (new Integer(pos1)).compareTo(pos2);
			}
		});
		System.out.println("");
		formatter
				.printHelp(79, "rfm.alloy.CLI [options] specfile.als\n", "",
						options,
						"\ntip: options can be specified either before or after the spec filename.");
		System.out.println("");

	}

	public static void showVersion() {

		System.out.println("");
		System.out
				.println("  ___      _       _              _  _            ___     _       ___  ");
		System.out
				.println(" /   \\    | |     | |     ___    | || |          / __|   | |     |_ _| ");
		System.out
				.println(" | - |    | |     | |    / _ \\    \\_, |    _    | (__    | |__    | |  ");
		System.out
				.println(" |_|_|   _|_|_   _|_|_   \\___/   _|__/   _(_)_   \\___|   |____|  |___| ");
		System.out
				.println("|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_| \"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|");
		System.out
				.println("`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'");
		System.out.println("");
		System.out.println("   Alloy Analyzer version:           "
				+ VERSION_ALLOY);
		System.out.println("   Command line interface version:   "
				+ VERSION_CLI);
		System.out.println("");
		System.out.println("     (based on 1.5.8 + experimental minisat220 driver as of 1.6.0a8)");
		System.out.println("");

	}

	public static final String VERSION_CLI = "1.5.8F_00";
	public static final String VERSION_ALLOY = "4.1.10";

}
