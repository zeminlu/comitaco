package ar.uba.dc.rfm.fajita;

import ar.uba.dc.rfm.tools.FileSystemTools;


/**
 * This class holds the entry point for the stand alone Fajita project.<br />
 * 
 * Its only responsibility is to invoke the <code>FajitaRunner</code> class
 * with the proper configuration obtained from the command line parameters and
 * to maintain consistency through multiple invocations.
 *
 * @see FajitaConfiguration
 * @see FajitaRunner
 */
public class FajitaMain {

	
	/** Version information. */
	public static final String FAJITA_VERSION = "Fajita 0.2.0";
	
	
	/**
	 * Entry point for running the project stand alone.
	 * 
	 * @param args a <code>String[]</code> with the command line parameters.
	 * 
	 * @see FajitaOptionsParser <code>FajitaOptionsParser</code> for
	 * possible command line arguments.
	 *  
	 */
	public static void main(String[] args) {
		FajitaConfiguration configuration = new FajitaConfiguration();
		FajitaOptionsParser.parseCommandLine(args, configuration);
		
		FileSystemTools.deleteNotMatching(
			configuration.getUserResultPath(), "generated"); // hard-coded directory name in taco junit generation.
		
		String junitClass = configuration.getClassToCheck();
		int i = junitClass.lastIndexOf('.');
		if (i < 0) i = 0;
		junitClass = junitClass.substring(i) + "Test";
		FileSystemTools.deleteMatching(
			configuration.getUserResultPath(), ".*" + junitClass + ".*"); 
		
		FajitaRunner fajita = new FajitaRunner(configuration);
		fajita.run();
		
		System.exit(0);
	}
	
}
