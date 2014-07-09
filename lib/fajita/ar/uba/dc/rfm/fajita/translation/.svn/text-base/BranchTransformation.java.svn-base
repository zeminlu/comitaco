package ar.uba.dc.rfm.fajita.translation;

import static ar.uba.dc.rfm.fajita.translation.FajitaJavaCodeTranslator.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import recoder.CrossReferenceServiceConfiguration;
import recoder.ProgramFactory;
import recoder.convenience.TreeWalker;
import recoder.java.CompilationUnit;
import recoder.java.Expression;
import recoder.java.Identifier;
import recoder.java.PackageSpecification;
import recoder.java.SourceVisitor;
import recoder.java.Statement;
import recoder.java.StatementBlock;
import recoder.java.declaration.DeclarationSpecifier;
import recoder.java.declaration.LocalVariableDeclaration;
import recoder.java.declaration.MethodDeclaration;
import recoder.java.expression.literal.BooleanLiteral;
import recoder.java.expression.operator.CopyAssignment;
import recoder.java.reference.MethodReference;
import recoder.java.reference.TypeReference;
import recoder.java.reference.UncollatedReferenceQualifier;
import recoder.java.statement.Case;
import recoder.java.statement.Default;
import recoder.java.statement.Else;
import recoder.java.statement.EnhancedFor;
import recoder.java.statement.For;
import recoder.java.statement.If;
import recoder.java.statement.While;
import recoder.kit.ProblemReport;
import recoder.list.generic.ASTArrayList;
import recoder.list.generic.ASTList;
import ar.uba.dc.rfm.fajita.FajitaConfiguration;


/**
 * This class holds the logic for instrumenting the branch coverage criteria.
 * Given a parsed compilation unit this class will, through the method execute,
 * transform the input AST removing the roops goal syntax and adding the necessary
 * constructions to perform branch coverage using Taco.
 */
public class BranchTransformation extends FajitaSourceTransformation {

	
	/**
	 * Constructor for a <code>BranchTransformation</code>.
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
	public BranchTransformation(
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
		FajitaBranchDiscoveryVisitor eraserVisitor =
			new FajitaBranchDiscoveryVisitor(configuration, this, getReachableMethods());
		
		TreeWalker treeWalker = new TreeWalker(compilationUnit);
		while (treeWalker.next())
			treeWalker.getProgramElement().accept(eraserVisitor);
		
		return execute(
			new FajitaSourceTransformVisitor<BranchTransformation>(
				configuration, this)
		);
	}
	
	
	/** Returns a set with the names of the methods in the class to check. */
	private Set<String> getClassMethods() {
		Set<String> classMethods = new HashSet<String>();
		
		FajitaClassMethodDiscoveryVisitor methodDiscoveryVisitor =
			new FajitaClassMethodDiscoveryVisitor(
				configuration.getClassToCheck(), classMethods);
		TreeWalker treeWalker = new TreeWalker(compilationUnit);
		while (treeWalker.next())
			treeWalker.getProgramElement().accept(methodDiscoveryVisitor);
		
		return classMethods;
	}
	
	
	/** Returns the set of reachable methods from the method under test.  */
	private Set<String> getReachableMethods() {
		Set<String> reachableMethods = new HashSet<String>();
		
		Map<String, Set<String>> referencedMethods = new HashMap<String, Set<String>>();
		FajitaReachableMethodDiscoveryVisitor reachableMethodDiscoveryVisitor =
			new FajitaReachableMethodDiscoveryVisitor(getClassMethods(), referencedMethods);
		TreeWalker treeWalker = new TreeWalker(compilationUnit);
		while (treeWalker.next())
			treeWalker.getProgramElement().accept(reachableMethodDiscoveryVisitor);

		Set<String> remainingMethods = new HashSet<String>();
		remainingMethods.add(configuration.getMethodToCheck());
		
		while (!remainingMethods.isEmpty()) {
			String method = remainingMethods.iterator().next();
			remainingMethods.remove(method);
			if (reachableMethods.add(method) && referencedMethods.containsKey(method))
				remainingMethods.addAll(referencedMethods.get(method));
		}
		
		return reachableMethods;
	}
	
	
	
	/**
	 * This private class holds the logic to discover the amount of methods
	 * the class to check has defined.
	 */
	private static class FajitaClassMethodDiscoveryVisitor extends SourceVisitor {
		
		/** The name of the class being analyzed. */
		private final String className;
		
		/** A set with the discovered methods. */
		private final Set<String> classMethods;
		
		/** The file package name if any. */
		private String packagePrefix = "";
		

		/**
		 * Constructor for a <code>FajitaClassMethodDiscoveryVisitor</code>
		 * 
		 * @param configuration a <code>FajitaConfiguration</code> with the
		 *	necessary data to discover the target goals.
		 */
		FajitaClassMethodDiscoveryVisitor(
				String className,
				Set<String> classMethods)
		{
			this.className = className;
			this.classMethods = classMethods;
		}
		
		
		/**
		 * This method is executed for each package specification in the analyzed
		 * compilation unit. The package name is stored in order to be able to
		 * determine the full name of the classes declared inside the compilation
		 * unit.
		 */
		@Override
		public void visitPackageSpecification(PackageSpecification x) {
			packagePrefix = x.getPackageReference().toSource().trim() + ".";
		}
		
		
		/**
		 * This method is executed for each method declaration in the analyzed
		 * compilation unit. If the declaration belongs to the class under test
		 * it's stored in the discovered methods set.
		 */
		@Override
		public void visitMethodDeclaration(MethodDeclaration x) {
			if (className.equals(packagePrefix + getContainingClass(x)) &&
				!x.getName().startsWith(KIASAN_REPOK_METHOD))
			{
				classMethods.add(x.getName());
			}
		}
		
		
	}
	
	
	
	/**
	 * This private class holds the logic to discover the reachable methods
	 * from the method under test.
	 */
	private static class FajitaReachableMethodDiscoveryVisitor extends SourceVisitor {
		
		/** The set of methods of the class under test. */
		private final Set<String> classMethods;
		
		/** A mapping of which methods are called from other methods. */
		private final Map<String, Set<String>> referencedMethods;
		
		
		/**
		 * Constructor for a <code>FajitaReachableMethodDiscoveryVisitor</code>.
		 * 
		 * @param classMethods the methods declared for the class under test.
		 * 
		 * @param referencedMethods a mapping where to store the invocation schema.
		 * 
		 */
		public FajitaReachableMethodDiscoveryVisitor(
				Set<String> classMethods,
				Map<String, Set<String>> referencedMethods)
		{
			this.classMethods = classMethods;
			this.referencedMethods = referencedMethods;
		}
		
		
		/**
		 * This method is call for each method reference made in the analyzed code.
		 * If the method belongs to the class under test and is being invoked
		 * from inside the class it's added to the invocation mapping.
		 */
		@Override
		public void visitMethodReference(MethodReference x) {
			String containigMethod = getContainingMethod(x);
			if (containigMethod != null &&
				classMethods.contains(containigMethod) &&
				classMethods.contains(x.getName()))
			{
				Set<String> methods = referencedMethods.get(containigMethod);
				if (methods == null) {
					methods = new HashSet<String>();
					referencedMethods.put(containigMethod, methods);
				}
				methods.add(x.getName());
			}
		}
		
	}
	
	
	
	/**
	 * This private class holds the logic to discover all the branches
	 * in the code under test.
	 */
	private static class FajitaBranchDiscoveryVisitor extends SourceVisitor {
		
		/** Fajita runtime configuration. */
		private final FajitaConfiguration configuration;
		
		/** The transformation class invoking this source visitor. */
		private final BranchTransformation transformation;
		
		/** The set of methods reachable from the method under test. */
		private final Set<String> reachableMethods;
		
		/** Flag that indicates AST transversal through a reachable method. */
		private boolean visitingReachableMethod = false;
		
		/** The file package name if any. */
		private String packagePrefix = "";
		
		
		/**
		 * Constructor for a <code>FajitaBranchDiscoveryVisitor</code>.
		 * 
		 * @param configuration a <code>FajitaConfiguration</code> with the
		 *	necessary data to discover the target goals.
		 * 
		 * @param transformation a <code>Transformation</code> with the necessary
		 *	logic to apply the recoder transformations to the visited code.
		 * 
		 * @param reachableMethods the set with the names of reachable methods
		 * 	from the method under test.
		 * 
		 */
		public FajitaBranchDiscoveryVisitor(
				FajitaConfiguration configuration,
				BranchTransformation transformation,
				Set<String> reachableMethods)
		{
			this.configuration = configuration;
			this.transformation = transformation;
			this.reachableMethods = reachableMethods;
		}
		
		
		/** Adds a goal to the given statement block. */
		private void addGoal(Statement body) {
			if (visitingReachableMethod) {
				int goalId = configuration.getDiscoveredGoals();
				configuration.setDiscoveredGoals(goalId+1);
				
				if (!configuration.getCoveredGoals().contains(goalId)) {
					ProgramFactory programFactory = transformation.getProgramFactory();
					CopyAssignment goalReachedStatement = createFajitaGoal(
						programFactory, goalId, true);
					
//					/**/
//					if (configuration.getCoverageCriteria() == CoverageCriteria.CLASS_BRANCH_COVERAGE) {
//						ASTList<Expression> exprs = goalReachedStatement.getArguments();
//						Identifier branchGoalId =
//							programFactory.createIdentifier("fajita_branch_goal" + "_" + goalId);
//						UncollatedReferenceQualifier branchGoal =
//							programFactory.createUncollatedReferenceQualifier(branchGoalId);
//						exprs.set(0, branchGoal);
//					}
//					/**/
					
					ASTList<Statement> block = new ASTArrayList<Statement>();
					block.add(goalReachedStatement);
					
					if (body instanceof StatementBlock)
						block.addAll(((StatementBlock)body).getBody());
					else
						block.add(body);
					
					StatementBlock newBody = new StatementBlock(block);
					transformation.replace(body, newBody);
				}
			}
		}
		
		
		/** Adds a goal after the given statement block to introduce goals after loops. */
		private void addGoalInCycle(Statement x, Statement body) {
			if (visitingReachableMethod) {
				ProgramFactory programFactory = transformation.getProgramFactory();
				ASTList<Statement> block = new ASTArrayList<Statement>();
				ASTArrayList<Statement> simpleBlock = new ASTArrayList<Statement>();
				StatementBlock statementBlock = programFactory.createStatementBlock(simpleBlock);
				
				int goalId = configuration.getDiscoveredGoals();
				configuration.setDiscoveredGoals(goalId+1);
				
				if (!configuration.getCoveredGoals().contains(goalId)) {
					CopyAssignment goalReachedStatement = createFajitaGoal(
						programFactory, goalId, true);
					block.add(goalReachedStatement);
				}
				
				goalId = configuration.getDiscoveredGoals();
				configuration.setDiscoveredGoals(goalId+1);
				
				if (!configuration.getCoveredGoals().contains(goalId)) {
					Identifier cicleId = programFactory.createIdentifier("fajita_cicle_" + (goalId-1));
					Identifier booleanId = programFactory.createIdentifier("boolean");
					TypeReference booleanReference = programFactory.createTypeReference(booleanId);
					ASTList<DeclarationSpecifier> declarations = new ASTArrayList<DeclarationSpecifier>();
					BooleanLiteral falseLiteral = programFactory.createBooleanLiteral(false);
					BooleanLiteral trueLiteral = programFactory.createBooleanLiteral(true);
					LocalVariableDeclaration cicleIdDeclaration =
						programFactory.createLocalVariableDeclaration(declarations, booleanReference, cicleId, falseLiteral);
					
					UncollatedReferenceQualifier cicleGoal =
						programFactory.createUncollatedReferenceQualifier(cicleId);
					CopyAssignment cicleReachedStatement =
						programFactory.createCopyAssignment(cicleGoal, trueLiteral);
					
					block.add(0, cicleReachedStatement);					
					if (body instanceof StatementBlock)
						block.addAll(((StatementBlock)body).getBody());
					else
						block.add(body);
					StatementBlock newBody = new StatementBlock(block);
					if (x instanceof For) {
						((For)x).setBody(newBody);
					} else if (x instanceof While) {
						((While)x).setBody(newBody);
					}

					UncollatedReferenceQualifier fajitaGoal =
						programFactory.createUncollatedReferenceQualifier(cicleId);
					Expression expression = programFactory.createLogicalNot(fajitaGoal);
					CopyAssignment goalReachedStatement = createFajitaGoal(
						programFactory, goalId, true);
					If ifStatement = programFactory.createIf(expression, goalReachedStatement);
					
					statementBlock.getBody().add(cicleIdDeclaration);
					statementBlock.getBody().add(x);
					statementBlock.getBody().add(ifStatement);
				} else {
					if (body instanceof StatementBlock)
						block.addAll(((StatementBlock)body).getBody());
					else
						block.add(body);
					StatementBlock newBody = new StatementBlock(block);
					if (x instanceof For) {
						((For)x).setBody(newBody);
					} else if (x instanceof While) {
						((While)x).setBody(newBody);
					}
					statementBlock.getBody().add(x);
				}
				
				transformation.replace(x, statementBlock);
			}
		}
		
		
		/**
		 * This method is executed for each package specification in the analyzed
		 * compilation unit. The package name is stored in order to be able to
		 * determine the full name of the classes declared inside the compilation
		 * unit.
		 */
		@Override
		public void visitPackageSpecification(PackageSpecification x) {
			packagePrefix = x.getPackageReference().toSource().trim() + ".";
		}
		
		
		/**
		 * This method is called for each method declaration and it's used to
		 * determine if we are analyzing a reachable method.
		 */
		@Override
		public void visitMethodDeclaration(MethodDeclaration x) {
			visitingReachableMethod =
				reachableMethods.contains(x.getName()) &&
				configuration.getClassToCheck().equals(
					packagePrefix + getContainingClass(x));
		}
		
		
		/**
		 * This method is called for each if construct in the code.
		 * Goals are added to the true and false branches.
		 */
		@Override
		public void visitIf(If x) {
			addGoal(x.getThen().getBody());
			if (x.getElse() != null) {
				if (! (x.getElse().getBody() instanceof If))
					addGoal(x.getElse().getBody());
			} else {
				ProgramFactory programFactory = transformation.getProgramFactory();
				ASTList<Statement> elseBody = new ASTArrayList<Statement>();
				StatementBlock elseBlock = programFactory.createStatementBlock(elseBody);
				Else elseBranch = programFactory.createElse(elseBlock);
				x.setElse(elseBranch);
				addGoal(elseBranch.getBody());
			}
		}
		
		
		/**
		 * This method is called for each case construct in the code.
		 * A goal is added to make sure each case gets exercised.
		 */
		@Override
		public void visitCase(Case x) {
			ASTList<Statement> body = x.getBody();
			if (body != null) {
				int goalId = configuration.getDiscoveredGoals();
				configuration.setDiscoveredGoals(goalId+1);
				if (!configuration.getCoveredGoals().contains(goalId)) {
					ProgramFactory programFactory = transformation.getProgramFactory();
					CopyAssignment goalReachedStatement = createFajitaGoal(
						programFactory, goalId, true);
					body.add(0, goalReachedStatement);
					transformation.replace(x, x);
				}
			}
		}
		
		
		/**
		 * This method is called for each default case in a switch construct.
		 * A goal is added to make sure the default case is exercised.
		 */
		@Override
		public void visitDefault(Default x) {
			ASTList<Statement> body = x.getBody();
			if (body != null) {
				int goalId = configuration.getDiscoveredGoals();
				configuration.setDiscoveredGoals(goalId+1);
				if (!configuration.getCoveredGoals().contains(goalId)) {
					ProgramFactory programFactory = transformation.getProgramFactory();
					CopyAssignment goalReachedStatement = createFajitaGoal(
						programFactory, goalId, true);
					body.add(0, goalReachedStatement);
					transformation.replace(x, x);
				}
			}
		}
		
		
		/**
		 * This method is called for each while construct in the code.
		 * Goals are added in the loop and after it to make sure an execution
		 * where the loop body is not executed is searched for. 
		 */
		@Override
		public void visitWhile(While x) {
			addGoalInCycle(x, x.getBody());
		}
		
		
		/**
		 * This method is called for each for construct.
		 * Goals are added in the loop and after it to make sure an execution
		 * where the loop body is no executed is searched for.
		 */
		@Override
		public void visitFor(For x) {
			addGoalInCycle(x, x.getBody());
		}
		
		
		/**
		 * This method is call for each enhaced for (i.e. loop built implicitly
		 * on iterators). A goal is added in the loop body. 
		 */
		@Override
		public void visitEnhancedFor(EnhancedFor x) {
			addGoal(x.getBody());
		}
		
	}
	
	
}
