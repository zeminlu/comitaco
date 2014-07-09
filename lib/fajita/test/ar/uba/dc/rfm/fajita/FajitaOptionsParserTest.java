package ar.uba.dc.rfm.fajita;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.uba.dc.rfm.fajita.FajitaConfiguration.CoverageCriteria;

public class FajitaOptionsParserTest {
	
	@Test
	public final void parseCommandLine_test_1() {
		String commandLine =
			" -cp classpath1" +
			" -c dummypackage.classToCheck1" +
			" -m methodToCheck1" +
			" -tf taco.properties.template1" +
			" -rp resultPath1" +
			" -u 4" +
			" -to 10" +
			" -b 16";
		String[] args = commandLine.split(" ");
		
		FajitaConfiguration configuration = new FajitaConfiguration();
		FajitaOptionsParser.parseCommandLine(args, configuration);
		
		assertTrue(configuration.getClasspath().equals("classpath1"));
		assertTrue(configuration.getClassToCheck().equals("dummypackage.classToCheck1"));
		assertTrue(configuration.getMethodToCheck().equals("methodToCheck1"));
		assertTrue(configuration.getTacoPropertiesTemplate().equals("taco.properties.template1"));
		assertTrue(configuration.getResultPath().equals("resultPath1"));
		assertTrue(configuration.getLoopUnroll() == 4);
		assertTrue(configuration.getTimeoutSecs() == 10);
		assertTrue(configuration.getIntBitwidth() == 16);
	}
	
	@Test
	public final void parseCommandLine_test_2() {
		String commandLine =
			" -cp classpath2" +
			" -c dummypackage.classToCheck2" +
			" -m methodToCheck2" +
			" -tf taco.properties.template2" +
			" -rp resultPath2" +
			" -r goal";
		String[] args = commandLine.split(" ");
		
		FajitaConfiguration configuration = new FajitaConfiguration();
		FajitaOptionsParser.parseCommandLine(args, configuration);
		
		assertTrue(configuration.getClasspath().equals("classpath2"));
		assertTrue(configuration.getClassToCheck().equals("dummypackage.classToCheck2"));
		assertTrue(configuration.getMethodToCheck().equals("methodToCheck2"));
		assertTrue(configuration.getTacoPropertiesTemplate().equals("taco.properties.template2"));
		assertTrue(configuration.getResultPath().equals("resultPath2"));
		assertTrue(configuration.getCoverageCriteria() == CoverageCriteria.GOAL_COVERAGE);
	}
	
	@Test
	public final void parseCommandLine_test_3() {
		String commandLine =
			" -cp classpath3" +
			" -c dummypackage.classToCheck3" +
			" -m methodToCheck3" +
			" -tf taco.properties.template3" +
			" -rp resultPath3" +
			" -r class";
		String[] args = commandLine.split(" ");
		
		FajitaConfiguration configuration = new FajitaConfiguration();
		FajitaOptionsParser.parseCommandLine(args, configuration);
		
		assertTrue(configuration.getClasspath().equals("classpath3"));
		assertTrue(configuration.getClassToCheck().equals("dummypackage.classToCheck3"));
		assertTrue(configuration.getMethodToCheck().equals("methodToCheck3"));
		assertTrue(configuration.getTacoPropertiesTemplate().equals("taco.properties.template3"));
		assertTrue(configuration.getResultPath().equals("resultPath3"));
		assertTrue(configuration.getCoverageCriteria() == CoverageCriteria.CLASS_COVERAGE);
	}
	
	@Test
	public final void parseCommandLine_test_4() {
		String commandLine =
			" -cp classpath4" +
			" -c dummypackage.classToCheck4" +
			" -m methodToCheck4" +
			" -tf taco.properties.template4" +
			" -rp resultPath4" +
			" -r branch";
		String[] args = commandLine.split(" ");
		
		FajitaConfiguration configuration = new FajitaConfiguration();
		FajitaOptionsParser.parseCommandLine(args, configuration);
		
		assertTrue(configuration.getClasspath().equals("classpath4"));
		assertTrue(configuration.getClassToCheck().equals("dummypackage.classToCheck4"));
		assertTrue(configuration.getMethodToCheck().equals("methodToCheck4"));
		assertTrue(configuration.getTacoPropertiesTemplate().equals("taco.properties.template4"));
		assertTrue(configuration.getResultPath().equals("resultPath4"));
		assertTrue(configuration.getCoverageCriteria() == CoverageCriteria.BRANCH_COVERAGE);
	}
	
}
