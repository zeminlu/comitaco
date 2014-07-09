package ar.uba.dc.rfm.fajita.translation;

import static ar.uba.dc.rfm.fajita.translation.FajitaJavaCodeTranslator.*;

import recoder.CrossReferenceServiceConfiguration;
import recoder.ProgramFactory;
import recoder.convenience.TreeWalker;
import recoder.java.CompilationUnit;
import recoder.java.Expression;
import recoder.java.PackageSpecification;
import recoder.java.SourceVisitor;
import recoder.java.declaration.AnnotationElementValuePair;
import recoder.java.declaration.AnnotationUseSpecification;
import recoder.java.expression.literal.IntLiteral;
import recoder.java.expression.operator.CopyAssignment;
import recoder.java.reference.MethodReference;
import recoder.java.reference.UncollatedReferenceQualifier;
import recoder.kit.ProblemReport;

import ar.uba.dc.rfm.fajita.FajitaConfiguration;


/**
 * This class holds the logic for instrumenting the goal coverage criteria.
 * Given a parsed compilation unit this class will, through the method execute,
 * transform the input AST removing the roops goal syntax and adding the necessary
 * constructions to perform goal coverage using Taco.
 */
public class GoalTransformation extends FajitaSourceTransformation {
	
	/**
	 * Constructor for a <code>GoalTransformation</code>.
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
	public GoalTransformation(
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
		FajitaGoalDiscoveryVisitor discoveryVisitor =
			new FajitaGoalDiscoveryVisitor(configuration);
		
		TreeWalker treeWalker = new TreeWalker(compilationUnit);
		while (treeWalker.next())
			treeWalker.getProgramElement().accept(discoveryVisitor);
		
		return super.execute(new FajitaGoalTransformVisitor(configuration, this));
	}
	
	
	
	/**
	 * This private class holds the logic to discover the amount of goals to
	 * be covered analyzing the different roops annotations.
	 */
	private static class FajitaGoalDiscoveryVisitor extends SourceVisitor {
		
		/** Fajita runtime configuration. */
		private final FajitaConfiguration configuration;
		
		/** The file package name if any. */
		private String packageName = "";

		
		/**
		 * Constructor for a <code>FajitaGoalDiscoveryVisitor</code>
		 * 
		 * @param configuration a <code>FajitaConfiguration</code> with the
		 *	necessary data to discover the target goals.
		 */
		public FajitaGoalDiscoveryVisitor(FajitaConfiguration configuration) {
			this.configuration = configuration;
		}
		
		
		/**
		 * This method is executed for each package specification in the analyzed
		 * compilation unit. The package name is stored in order to be able to
		 * determine the full name of the classes declared inside the compilation
		 * unit.
		 */
		@Override
		public void visitPackageSpecification(PackageSpecification x) {
			packageName = x.getPackageReference().toSource().trim() + ".";
		}
		
		
		/**
		 * This method is executed for each annotation used in the analyzed
		 * compilation unit. Checks if the annotation belongs to the method
		 * under test, and if so, stores the number of goals declared.
		 */
		@Override
		public void visitAnnotationUse(AnnotationUseSpecification a) {
			if (a.getTypeReference().getName().equals(ROOPS_ANNOTATION_NROFGOALS)) {
				String containingClass = getContainingClass(a);
				String containingMethod = getContainingMethod(a);
				
				if (configuration.getClassToCheck().equals(packageName + containingClass) &&
					configuration.getMethodToCheck().equals(containingMethod))
				{
					AnnotationElementValuePair element = a.getElementValuePairs().get(0);
					String value = ((IntLiteral)element.getElementValue()).getValue();
					configuration.setDiscoveredGoals(Integer.parseInt(value));
				}
			}
		}
		
		
		/**
		 * This method is executed for each method referenced in the analyzed
		 * compilation unit. Checks if it is the roops.reched method with the
		 * UNREACHABLE value, in which case automatically considers the goal
		 * covered in order to avoid iterating to cover it. 
		 */
		@Override
		public void visitMethodReference(MethodReference x) {
			if (x.getName().equals(ROOPS_REACHED_METHOD) 
				&& x.getArguments().size() > 1 &&
				configuration.getMethodToCheck().equals(getContainingMethod(x)) &&
				configuration.getClassToCheck().equals(packageName + getContainingClass(x)))
			{
				Expression arg1 = x.getArguments().get(1);
				UncollatedReferenceQualifier enumConstant = 
					(UncollatedReferenceQualifier)arg1;
				String constantName = enumConstant.getName();
				if (constantName.equals(ROOPS_VERDICT_UNREACHABLE)) {
					int goalId = Integer.parseInt(
						((IntLiteral)x.getArguments().get(0)).getValue());
					configuration.getCoveredGoals().add(goalId);
					// XXX: Considering unreachable goals as already covered allows us to
					// consider goalIds to be consecutive, even when there are unreachable goals
					// interleaved. If we stop counting them some things might stop working.
					// We may need to identify the unreachable goals in the future.
				}
			}
		}
		
	}

	
	
	/**
	 * This private class is the one actually in charge of instrumenting the
	 * goal coverage technique for each java construction. To achieve this
	 * it needs to know the number of goals being analyzed, which is computed
	 * by the <code>FajitaGoalDiscoveryVisitor</code>.
	 */
	private static class FajitaGoalTransformVisitor extends FajitaSourceTransformVisitor<GoalTransformation> {
		
		
		/**
		 * Constructor for a <code>FajitaGoalTransformVisitor</code>.
		 * 
		 * @param configuration a <code>FajitaConfiguration</code> with the
		 *	necessary data to transform the target code.
		 * 
		 * @param transformation a <code>Transformation</code> with the necessary
		 *	logic to apply the recoder transformations to the visited code.
		 * 
		 */
		public FajitaGoalTransformVisitor(
				FajitaConfiguration configuration,
				GoalTransformation transformation)
		{
			super(configuration, transformation);
		}
		
		
		/**
		 * This method is executed for each method referenced in the analyzed
		 * compilation unit. If it is an invocation to the roops.reached method
		 * it's replaced for a fajita goal or simply removed if the goal has
		 * already been covered.
		 */
		@Override
		public void visitMethodReference(MethodReference x) {
			boolean isGoalTarget = x.getName().equals(ROOPS_REACHED_METHOD);
			
			// CHECK IF THIS METHOD REFERENCE DENOTES A GOAL WITH REACHABILITY INFORMATION
			if (isGoalTarget && x.getArguments().size() > 1) {
				Expression arg1 = x.getArguments().get(1);
				UncollatedReferenceQualifier enumConstant = 
					(UncollatedReferenceQualifier)arg1;
				String constantName = enumConstant.getName();
				if (constantName.equals(ROOPS_VERDICT_UNREACHABLE)) {
					transformation.detach(x);
					isGoalTarget = false;
				}
			}
			
			// IF THE METHOD REFERENCE TURNED OUT TO BE A REACHABLE GOAL WE NEED TO:
			//	- REPLACE IT WITH FAJITA'S GOAL NOTATION IF IT REMAINS UNCOVERED
			//	- SIMPLY REMOVE IT IF IT HAS BEEN COVERED IN A PREVIOUS ITERATION
			if (isGoalTarget) {
				int goalId = Integer.parseInt(
					((IntLiteral)x.getArguments().get(0)).getValue());
				
				if (!configuration.getCoveredGoals().contains(goalId) &&
					goalId < configuration.getDiscoveredGoals())
				{
					ProgramFactory programFactory = transformation.getProgramFactory();
					CopyAssignment goalReachedStatement = createFajitaGoal(
						programFactory, goalId, true);
					transformation.replace(x, goalReachedStatement);
				}
				else
				{
					transformation.detach(x);
				}
			}
		}
		
	}
	
}
