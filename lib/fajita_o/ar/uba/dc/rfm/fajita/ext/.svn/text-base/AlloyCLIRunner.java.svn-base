package ar.uba.dc.rfm.fajita.ext;

import kodkod.ast.Relation;

import rfm.alloy.CLI;

import ar.uba.dc.rfm.fajita.FajitaConfiguration;
import ar.uba.dc.rfm.fajita.FajitaException;
import ar.uba.dc.rfm.fajita.ext.junit.JUnitGeneratorCLICallback;


/**
 * This class is used to encapsulate the details of the interface with the
 * AlloyCli project. It implements the <code>Runnable</code> interface, its
 * run method launches the execution of AlloyCli.
 * 
 */
public class AlloyCLIRunner implements Runnable {

	/** Fajita runtime configuration. */
	private final FajitaConfiguration configuration;
	
	
	/**
	 * Constructor for a <code>AlloyCLIRunner</code>.
	 * 
	 * @param configuration a <code>FajitaConfiguration</code> with the
	 *	necessary data to launch AlloyCLI.
	 * 
	 */
	public AlloyCLIRunner(FajitaConfiguration configuration) {
		this.configuration = configuration;
	}
	
	/**
	 * This method implements the <code>Runnable</code> interface. Invoking it
	 * launches the execution of AlloyCLI taking the required parameters from
	 * the fajita configuration.
	 * 
	 */
	@Override
	public void run() {
		try {
			String clicoverage = "goal";
			
			String[] args = new String[] {
				"-C",
				"-s", configuration.getSolver(),
				"-r", clicoverage,
				"-e",
				"-B",
				"-Q",
				"-x", configuration.getDynalloyOutputXMLPrefix(),
				configuration.getDynalloyOutputFilename()
			};
			CLI.coverageClauseCallback =
				new JUnitGeneratorCLICallback(configuration);
			CLI.loopExhaustionId = configuration.getDiscoveredGoals();
			
			CLI.run(args);
			
			Relation.clearBooleanQueries();
		
		} catch (Exception e) {
			throw new FajitaException(e.getMessage(), e);
		}
	}
	
}
