package ar.uba.dc.rfm.fajita;

import java.util.Arrays;

import ar.uba.dc.rfm.fajita.FajitaConfiguration.CoverageCriteria;
import ar.uba.dc.rfm.fajita.ext.AlloyCLIRunner;
import ar.uba.dc.rfm.fajita.ext.TacoRunner;

import ar.uba.dc.rfm.fajita.output.FajitaOutputProcessor;


/**
 * This class represents the workflow of the Fajita project.
 * Its only responsibility is to guide the execution of the project following
 * the given configuration, invoking the different steps in a pipeline.
 * However, it does not hold specific information about each step, since the
 * steps belong to different projects and it's preferable to separate the
 * required logic.<br />
 * 
 */
public class FajitaRunner implements Runnable {

	
	/** The configuration for the project. */
	private final FajitaConfiguration configuration;
	
	
	/**
	 * Constructor for a <code>FajitaRunner</code>.
	 * 
	 * @param configuration a <code>FajitaConfiguration</code> holding the
	 * attributes that will drive Fajita execution.
	 * 
	 */
	public FajitaRunner(FajitaConfiguration configuration) {
		this.configuration = configuration;
	}
	
	
	/**
	 * This method implements the <code>Runnable</code> interface.
	 * It starts the execution of the Fajita project.
	 * 
	 */
	@Override
	public void run() {
		
		initExplorationBounds();
		
		long timer = System.currentTimeMillis();
		
		// ITERATE THROUGH ALL THE POSSIBLE OBJECT SCOPES
		while (configuration.isIncrementalLoopUnroll() ||
			configuration.getObjectScope() <= configuration.getMaximumObjectScope())
		{
			
			// FOR EACH OBJECT SCOPE ITERATE THROUGH ALL THE LOOP UNROLLS
			// UNTIL NO LOOP EXHAUSTION IS ENCOUNTERED
			{
				FajitaRunner.printStep("FAJITA: Decorating Java code for " +
					configuration.getObjectScope() + " scope and " +
					configuration.getLoopUnroll() + " unroll");
				runFajitaCodeDecorator();
				
				FajitaRunner.printStep("FAJITA: Translating Java -> Alloy");
				runTaco();
				
				if (configuration.isOnlyTranslateToAlloy()) {
					System.out.println("Translation to Alloy completed."); 
					return;
				}
				
				if (configuration.getDiscoveredGoals() == 0) {
					System.out.println("No goals found for the chosen test selection criterion.");
					return;
				}
					
				FajitaRunner.printStep("FAJITA: Enumerating Solutions using AlloyCLI");
				runAlloyCli();
				
				FajitaRunner.printStep("Reporting Coverage");
				FajitaOutputProcessor.newProcessor(configuration).getCoverage();
				
///				boolean loopExhaustionEncountered =	configuration.getCoveredGoals().removeAll(
///					configuration.getLoopExhaustionIncarnations());

				System.out.println((System.currentTimeMillis() - timer) / 1000 + " s");
				
				// CHECK STOP CONDITIONS
				if (configuration.getCoveredGoals().size() == configuration.getDiscoveredGoals() ||
					configuration.getCoverageCriteria() == CoverageCriteria.CLASS_COVERAGE ||
					(configuration.getCoverageCriteria() == CoverageCriteria.DUAL_CLASS_BRANCH_COVERAGE &&
					(configuration.getDualClassBranchIteration() == 0 ||
					configuration.getCoveredGoals().size() == configuration.getDualDiscoveredBranches())))
				{
					return;
				}
				
///				if (!loopExhaustionEncountered) break;
			}
			
			if (!configuration.isInfiniteScope()) {
				System.out.println("Finite scope exhausted.");
				break;
			}
			
			configuration.setObjectScope(configuration.getObjectScope() + 1);
			configuration.setLoopUnroll(configuration.getObjectScope() / 2); 
		}
	}
	
	
	/** This method sets the initial values for the exploration bounds. */
	private void initExplorationBounds() {
		switch (configuration.getCoverageCriteria()) {
			case GOAL_COVERAGE:
			case BRANCH_COVERAGE:
				configuration.setObjectScope(configuration.isIncrementalLoopUnroll() ?
					FajitaConfiguration.INITIAL_INCREMENTAL_OBJECT_SCOPE :
					configuration.getMaximumObjectScope()
				);
				configuration.setLoopUnroll(configuration.isIncrementalLoopUnroll() ?
					FajitaConfiguration.INITIAL_INCREMENAL_LOOP_UNROLL :
					configuration.getMaximumLoopUnroll()
				);
				break;
			case CLASS_COVERAGE:
			case DUAL_CLASS_BRANCH_COVERAGE:
				configuration.setObjectScope(configuration.getMaximumObjectScope());
				configuration.setLoopUnroll(configuration.getMaximumLoopUnroll());
				break;
		}
	}
	
	
	/**
	 * This method invokes the <code>FajitaJavaCodeDecorator</code> in order
	 * to apply the necessary modifications to the input java code to be able
	 * to pursue the desired coverage criteria.
	 * 
	 */
	private void runFajitaCodeDecorator() {
		FajitaJavaCodeDecorator decorator = new FajitaJavaCodeDecorator(configuration);
		decorator.run();
	}
	
	
	/**
	 * This method invokes the Taco project in order to translate the input java
	 * code in a DynAlloy model.
	 * 
	 */
	private void runTaco() {		
		TacoRunner taco = new TacoRunner(configuration);
		taco.run();
	}
	
	
	/**
	 * This method invokes the AlloyCli project, which using an incremental
	 * SAT solver will produce multiple counter-examples for the model obtained
	 * from the input java code. Each counter-example evokes a distinctive
	 * instance of the class under test.<br />
	 * 
	 * For each counter-example found a unit test method will be produced.
	 * Eventually generating a unit test suite that shows the maximum coverage
	 * achieved by the tool with the given configuration.
	 * 
	 */
	private void runAlloyCli() {
		AlloyCLIRunner alloyRunner = new AlloyCLIRunner(configuration);

		long timeout = configuration.getTimeoutSecs() * 1000;
		
		Thread alloyThread = new Thread(alloyRunner);
		alloyThread.start();
		
		long startTime = System.currentTimeMillis();
		
		synchronized (alloyThread) {
			try { alloyThread.wait(timeout); }
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		long endTime = System.currentTimeMillis();
		if (timeout != 0)
			configuration.setTimeoutSecs((int)((timeout - (endTime - startTime)) / 1000));
		
		if (alloyThread.isAlive()) {
			System.out.println("TIMEOUT WAS REACHED");
			System.exit(0); // TODO: Add mechanisms to do some house keeping before exiting
		}
	}
	
	
	/**
	 * Auxiliary method for pretty printing information about the current
	 * state of the Fajita workflow. 
	 *  
	 * @param stepDescription a <code>String</code> with the information to be
	 * shown to the user.
	 * 
	 */
	private static void printStep(String stepDescription) {
		char[] separatorLine = new char[stepDescription.length()];
		Arrays.fill(separatorLine, '-');
		System.out.println();
		System.out.println(separatorLine);
		System.out.println(stepDescription);
		System.out.println(separatorLine);
	}
	
}
