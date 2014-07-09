package ar.uba.dc.rfm.fajita.translation;

import static ar.uba.dc.rfm.fajita.translation.FajitaJavaCodeTranslator.*;

import ar.uba.dc.rfm.fajita.FajitaConfiguration;
import recoder.CrossReferenceServiceConfiguration;
import recoder.java.CompilationUnit;
import recoder.kit.ProblemReport;


/**
 * This class holds the logic for instrumenting the dual class branch coverage criteria.
 * Given a parsed compilation unit this class will, through the method execute,
 * transform the input AST removing the roops goal syntax and adding the necessary
 * constructions to perform boolean method coverage using Taco.
 */
public class DualClassBranchTransformation extends FajitaSourceTransformation {

	/** Special name to distinguish class coverage goals from branch goals. */
	public static final String FAJITA_BRANCH_GOAL_TAG = "fajita_branch_goal";
	
	
	/**
	 * Constructor for a <code>DualClassBranchTransformation</code>.
	 * 
	 * @param configuration a <code>FajitaConfiguration</code> with the
	 *	necessary data to transform the input code.
	 *
	 * @param recoder a class from the recoder project that acts as starting point
	 *	of the library and has different possible configurations.
	 * 
	 * @param compilationUnit the compilation unit parsed as an AST to be
	 *	transformed upon execution.
	 * 
	 */
	public DualClassBranchTransformation(
			FajitaConfiguration configuration,
			CrossReferenceServiceConfiguration recoder,
			CompilationUnit compilationUnit)
	{
		super(configuration, recoder, compilationUnit);
	}
	
	
	/**
	 * This method actually performs the transformation of the input code.
	 * 
	 * @return a <code>ProblemrReport</code> that fulfills the interface required
	 *	by the <code>recoder.Transformation</code> base class.
	 *
	 */
	@Override
	public ProblemReport execute() {
		String fajitaGoalTag = FAJITA_GOAL_TAG;
		int discoveredGoals = 0;

		if (configuration.getDualClassBranchIteration() == 0) {
			ClassTransformation classTransformation = new ClassTransformation(
				configuration, getServiceConfiguration(), compilationUnit);
			classTransformation.execute();
			discoveredGoals = configuration.getDiscoveredGoals();
			configuration.setDiscoveredGoals(configuration.getDualDiscoveredBranches());
			FAJITA_GOAL_TAG = FAJITA_BRANCH_GOAL_TAG;
		}
		
		ClassTransformation.resetClassCoverageMethod(configuration);
		
		BranchTransformation branchTransformation = new BranchTransformation(
			configuration, getServiceConfiguration(), compilationUnit);
		branchTransformation.execute();
		
		if (configuration.getDualClassBranchIteration() == 0) {
			ClassTransformation.setClassCoverageMethod(configuration);
			configuration.setDualDiscoveredBranches(configuration.getDiscoveredGoals());
			configuration.setDiscoveredGoals(discoveredGoals);
			FAJITA_GOAL_TAG = fajitaGoalTag;
		}
		
		return NO_PROBLEM;
	}
	
	
}
