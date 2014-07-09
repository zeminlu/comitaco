package ar.uba.dc.rfm.fajita;

import static org.junit.Assert.*;

import static java.io.File.separator;

import java.io.IOException;

import org.junit.Test;

import ar.uba.dc.rfm.tools.FileSystemTools;
import ar.uba.dc.rfm.tools.FileTools;


/**
 * This class holds tests for the <code>FajitaJavaCodeDecorator</code> class.
 */
public class FajitaJavaCodeDecoratorTest {
	
	private static final String FAJITA_JAVA_CODE_DECORATOR_TEST_DIR =
		"UnitTestResults" + separator + "FajitaJavaCodeDecoratorTest";
	private static final String UNIT_TEST_SRC =
		FAJITA_JAVA_CODE_DECORATOR_TEST_DIR + separator + "src";
	private static final String UNIT_TEST_OUT =
		FAJITA_JAVA_CODE_DECORATOR_TEST_DIR + separator + FajitaConfiguration.FAJITA_OUT;
	
	
	/**
	 * Compares two strings avoiding non-printable characters, useful for comparing
	 * code with different indentations.
	 * 
	 * @param a <code>String</code> to be compared.
	 * @param b <code>String</code> to be compared.
	 * 
	 * @return true if the strings are equals except for non-printable characters,
	 * false otherwise.
	 * 
	 */
	private static boolean equalsIgnoreWhites(String a, String b) {
		return a.replaceAll("\\s", "").equals(b.replaceAll("\\s", ""));
	}
	
	
	/////////////////////
	///  TEST CASE 1  ///
	/////////////////////
	
	private static final String TEST_1_INPUT =
		"@roops.util.BenchmarkClass\n" +
		"public class FajitaJavaCodeDecoratorTest1Input {\n" +
		"	@roops.util.NrOfGoals(1)\n" +
		"	@roops.util.BenchmarkMethod\n" +
		"	static public void test(Object arg0) {\n" +
		"		roops.util.Goals.reached(0, roops.util.Verdict.REACHABLE);\n" +
		"	}\n" +
		"}\n";
	
	private static final String TEST_1_OUTPUT =
		"public class FajitaJavaCodeDecoratorTest1Input {\n" +
		"	/**\n" +
		"	 * @Modifies_Everything\n" +
		"	 * @Ensures false;\n" +
		"	 */\n" +
		"	static public void test(Object arg0) {\n" +
		"		fajita_roopsGoal_initialization();\n" +
		"		roops_goal_0 = true;\n" +
		"	}\n" +
		"	static boolean roops_goal_0;\n" +
		"	static void fajita_roopsGoal_initialization() {\n" +
		"		roops_goal_0 = false;\n" +
		"	}\n" +
		"}\n";

	
	@Test
	public final void run_test_1() throws IOException {
		// CREATE THE FOLDERS AND FILES REQUIRED BY THE LOGIC UNDER TEST 
		FileSystemTools.createDir(UNIT_TEST_SRC);
		FileTools.writeFile(UNIT_TEST_SRC + separator +
			"FajitaJavaCodeDecoratorTest1Input.java", TEST_1_INPUT);
		
		// SET THE NECESSARY FAJITA CONFIGURATION TO EXECUTE THE TEST
		FajitaConfiguration configuration = new FajitaConfiguration();
		configuration.setClasspath(UNIT_TEST_SRC);
		configuration.setCoverageCriteria("goal");
		configuration.setClassToCheck("FajitaJavaCodeDecoratorTest1Input");
		configuration.setMethodToCheck("test");
		configuration.setResultPath(FAJITA_JAVA_CODE_DECORATOR_TEST_DIR);
		
		// CREATE A CODE DECORATOR AND START THE TEST
		FajitaJavaCodeDecorator codeDecorator = new FajitaJavaCodeDecorator(configuration);
		codeDecorator.run();
		
		// READ THE OUTPUT FILE AND COMPARE THEM WITH THE EXPECTED RESULTS
		String output = FileTools.readFile(
			UNIT_TEST_OUT + separator +
			"FajitaJavaCodeDecoratorTest1Input" + separator +
			"FajitaJavaCodeDecoratorTest1Input.java"
		);
		
		assertTrue(equalsIgnoreWhites(output, TEST_1_OUTPUT));
	}
	
	
	/////////////////////
	///  TEST CASE 2  ///
	/////////////////////
	
	private static final String TEST_2_INPUT =
		"package dummypackage;\n" +
		"@roops.util.BenchmarkClass\n" +
		"public class FajitaJavaCodeDecoratorTest2Input {\n" +
		"	public FajitaJavaCodeDecoratorTest2Input() {}\n" +
		"	@roops.util.NrOfGoals(1)\n" +
		"	@roops.util.BenchmarkMethod\n" +
		"	static public void test1(Object arg0) {\n" +
		"		roops.util.Goals.reached(0, roops.util.Verdict.REACHABLE);\n" +
		"	}\n" +
		"	@roops.util.NrOfGoals(2)\n" +
		"	@roops.util.BenchmarkMethod\n" +
		"	static public void test2(Object arg0) {\n" +
		"		roops.util.Goals.reached(0, roops.util.Verdict.REACHABLE);\n" +
		"		if (false)\n" +
		"			roops.util.Goals.reached(1, roops.util.Verdict.UNREACHABLE);\n" +
		"	}\n" +
		"	private class InternalClass {\n" +
		"		public void auxiliar() {}\n" +
		"	}\n" +
		"}\n";
	
	private static final String TEST_2_OUTPUT =
		"package dummypackage;\n" +
		"public class FajitaJavaCodeDecoratorTest2Input {\n" +
		"	public FajitaJavaCodeDecoratorTest2Input() {}\n" +
		"	/**\n" +
		"	 * @Modifies_Everything\n" +
		"	 * @Ensures false;\n" +
		"	 */\n" +
		"	static public void test1(Object arg0) {\n" +
		"		fajita_roopsGoal_initialization();\n" +
		"		roops_goal_0 = true;\n" +
		"	}\n" +
		"	/**\n" +
		"	 * @Modifies_Everything\n" +
		"	 * @Ensures false;\n" + 
		"	 */\n" +
		"	static public void test2(Object arg0) {\n" +
		"		fajita_roopsGoal_initialization();\n" +
		"		roops_goal_0 = true;\n" +
		"		if (false);\n" +
		"	}\n" +
		"	public class InternalClass {\n" +
		"		public void auxiliar() {}\n" +
		"	}\n" +
		"	static boolean roops_goal_0;\n" +
		"	static boolean roops_goal_1;\n" +
		"	static void fajita_roopsGoal_initialization() {\n" +
		"		roops_goal_0 = false;\n" +
		"		roops_goal_1 = false;\n" +
		"	}\n" + 
		"}\n";
	
	
	@Test
	public final void run_test_2() throws IOException {
		// CREATE THE FOLDERS AND FILES REQUIRED BY THE LOGIC UNDER TEST 
		FileSystemTools.createDir(UNIT_TEST_SRC + separator + "dummypackage");
		FileTools.writeFile(UNIT_TEST_SRC + separator + "dummypackage" + separator +
			"FajitaJavaCodeDecoratorTest2Input.java", TEST_2_INPUT);
		
		// SET THE NECESSARY FAJITA CONFIGURATION TO EXECUTE THE TEST
		FajitaConfiguration configuration = new FajitaConfiguration();
		configuration.setClasspath(UNIT_TEST_SRC);
		configuration.setCoverageCriteria("goal");
		configuration.setClassToCheck("dummypackage.FajitaJavaCodeDecoratorTest2Input");
		configuration.setMethodToCheck("test");
		configuration.setResultPath(FAJITA_JAVA_CODE_DECORATOR_TEST_DIR);
		
		// CREATE A CODE DECORATOR AND START THE TEST
		FajitaJavaCodeDecorator codeDecorator = new FajitaJavaCodeDecorator(configuration);
		codeDecorator.run();
		
		// READ THE OUTPUT FILE AND COMPARE THEM WITH THE EXPECTED RESULTS
		String output = FileTools.readFile(
			UNIT_TEST_OUT + separator +
			"dummypackage_FajitaJavaCodeDecoratorTest2Input" + separator +
			"dummypackage" + separator + "FajitaJavaCodeDecoratorTest2Input.java"
		);
		
		assertTrue(equalsIgnoreWhites(output, TEST_2_OUTPUT));
	}

}
