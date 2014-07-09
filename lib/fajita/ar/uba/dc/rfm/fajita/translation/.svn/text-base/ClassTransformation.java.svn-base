package ar.uba.dc.rfm.fajita.translation;

import static ar.uba.dc.rfm.fajita.translation.FajitaJavaCodeTranslator.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import recoder.CrossReferenceServiceConfiguration;
import recoder.ProgramFactory;
import recoder.convenience.TreeWalker;
import recoder.java.Comment;
import recoder.java.CompilationUnit;
import recoder.java.DocComment;
import recoder.java.Expression;
import recoder.java.Identifier;
import recoder.java.SourceVisitor;
import recoder.java.Statement;
import recoder.java.StatementBlock;
import recoder.java.declaration.ClassDeclaration;
import recoder.java.declaration.DeclarationSpecifier;
import recoder.java.declaration.FieldDeclaration;
import recoder.java.declaration.MethodDeclaration;
import recoder.java.declaration.ParameterDeclaration;
import recoder.java.declaration.Throws;
import recoder.java.declaration.modifier.Public;
import recoder.java.declaration.modifier.Static;
import recoder.java.expression.operator.CopyAssignment;
import recoder.java.reference.MethodReference;
import recoder.java.reference.TypeReference;
import recoder.java.reference.UncollatedReferenceQualifier;
import recoder.java.statement.If;
import recoder.java.statement.Then;
import recoder.kit.ProblemReport;
import recoder.list.generic.ASTArrayList;
import recoder.list.generic.ASTList;

import ar.uba.dc.rfm.fajita.FajitaConfiguration;
import ar.uba.dc.rfm.fajita.FajitaConfiguration.CoverageCriteria;


/**
 * This class holds the logic for instrumenting the boolean method coverage criteria.
 * Given a parsed compilation unit this class will, through the method execute,
 * transform the input AST removing the roops goal syntax and adding the necessary
 * constructions to perform boolean method coverage using Taco.
 */
public class ClassTransformation extends FajitaSourceTransformation {

	
	/** Sets a special method as the method under test. */
	public static void setClassCoverageMethod(FajitaConfiguration configuration) {
		resetClassCoverageMethod(configuration);
		configuration.setMethodToCheck("fajita_class_coverage_method_" + configuration.getMethodToCheck());
	}
	
	
	/** Restores the method under test to the original. */
	public static void resetClassCoverageMethod(FajitaConfiguration configuration) {
		configuration.setMethodToCheck(configuration.getMethodToCheck().replace("fajita_class_coverage_method_", ""));
	}

	
	/**
	 * Constructor for a <code>ClassTransformation</code>.
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
	public ClassTransformation(
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
		ClassTransformation.setClassCoverageMethod(configuration);
		
		Map<String, List<TypeReference>> booleanMethods =
			new HashMap<String, List<TypeReference>>();
		
		FajitaBooleanMethodDiscoveryVisitor discoveryVisitor =
			new FajitaBooleanMethodDiscoveryVisitor(configuration, booleanMethods);
		
		TreeWalker treeWalker = new TreeWalker(compilationUnit);
		while (treeWalker.next())
			treeWalker.getProgramElement().accept(discoveryVisitor);
		
		ProblemReport report = execute(
			new FajitaClassTransformVisitor(configuration, this, booleanMethods));
		
		return report;
	}

	
	
	/**
	 * This private class holds the logic to discover the boolean methods
	 * the class to check has defined.
	 */
	private static class FajitaBooleanMethodDiscoveryVisitor extends SourceVisitor {
		
		/** Fajita runtime configuration. */
		private final FajitaConfiguration configuration;
		
		/** Mapping from boolean methods declared in the class to test to its lists of argument types. */
		private final Map<String, List<TypeReference>> booleanMethods;
		
		
		/**
		 * Constructor for a <code>FajitaBooleanMethodDiscoveryVisitor</code>.
		 * 
		 * @param configuration a <code>FajitaConfiguration</code> with the
		 *	necessary data to discover the target goals.
		 * 
		 * @param booleanMethods a map where the boolean methods of the class under
		 * 	test and its argument types will be stored.
		 * 
		 */
		private FajitaBooleanMethodDiscoveryVisitor(
				FajitaConfiguration configuration,
				Map<String, List<TypeReference>> booleanMethods)
		{
			this.configuration = configuration;
			this.booleanMethods = booleanMethods;
		}
		
		
		/**
		 * This method is called for each method declared in the analyzed code.
		 * If the declaration belongs to the class under test and its a boolean
		 * argumentless method its added to the mapping.
		 */
		@Override
		public void visitMethodDeclaration(MethodDeclaration x) {
			super.visitMethodDeclaration(x);
			
			TypeReference returnType = x.getTypeReference();
			if (returnType != null && returnType.getName().matches("boolean|Boolean") &&
				!x.getName().startsWith(KIASAN_REPOK_METHOD))
			{
				boolean hasPrecondition = false; // TODO: If a precondition is present we have to consider sub-partitions. To simplify we only keep methods without preconditions.
				ASTList<Comment> comments = x.getComments();
				if (comments != null)
					for (Comment comment : comments) {
						if (comment.toSource().matches(".*@[ \t\n\r]*requires.*")) {
							hasPrecondition = true;
							break;
						}
					}
				
				if (x.getParameterDeclarationCount() == 0 && !hasPrecondition) { // Remove this if when adding boolean queries with arguments
					List<TypeReference> parameters = new ArrayList<TypeReference>();
					
//					if (x.getParameterDeclarationCount() > 0) {
//						ASTList<ParameterDeclaration> declarations = x.getParameters();
//						for (ParameterDeclaration parameterDeclaration : declarations)
//							parameters.add(parameterDeclaration.getTypeReference());
//					}
					
					// TODO: Check that the method does not modify the instance state (we want only constant methods).
					//	Add boolean methods with arguments, adding the arguments as instance members.
					booleanMethods.put(x.getName(), parameters);
				}
			}
			
			if (("fajita_class_coverage_method_" + x.getName()).equals(configuration.getMethodToCheck())) {
				configuration.setMethodToCheckParameters(x.getParameters());
			}
		}
		
	}
	
	
	
	/**
	 * This private class holds the logic to instrument the boolean method criterion.
	 */
	private static class FajitaClassTransformVisitor extends FajitaSourceTransformVisitor<ClassTransformation> {
		
		/** Mapping with the boolean methods of the class under test. */
		private final Map<String, List<TypeReference>> booleanMethods;
		
		/**
		 * Constructor for a <code>FajitaClassTransformVisitor</code>.
		 * 
		 * @param configuration a <code>FajitaConfiguration</code> with the
		 *	necessary data to discover the target goals.
		 * 
		 * @param transformation a <code>Transformation</code> with the necessary
		 *	logic to apply the recoder transformations to the visited code.
		 * 
		 * @param booleanMethods mapping with the boolean methods of the class under test.
		 * 
		 */
		FajitaClassTransformVisitor(
			FajitaConfiguration configuration,
			ClassTransformation transformation,
			Map<String, List<TypeReference>> booleanMethods)
		{
			super(configuration, transformation);
			this.booleanMethods = booleanMethods;
		}

		
		/**
		 * This method is called for each class declaration in the analyzed code.
		 * If the class is the class under test methods and attributes are added
		 * in order to instrument the coverage criteria.
		 */
		@Override
		public void visitClassDeclaration(ClassDeclaration x) {
			String fullName = packageName + getFullName(x);
			
			// CHECK IF WE ARE VISITING THE DECLARATION OF CLASS TO CHECK
			if (fullName.equals(configuration.getClassToCheck())) {
				ProgramFactory programFactory = transformation.getProgramFactory();
				
				ASTList<DeclarationSpecifier> initializationSpecifiers =
					new ASTArrayList<DeclarationSpecifier>();
				initializationSpecifiers.add(new Public());
				initializationSpecifiers.add(new Static());
				
				Identifier voidId = programFactory.createIdentifier("void");
				TypeReference voidReference = programFactory.createTypeReference(voidId);
				
				Identifier initializationId =
					programFactory.createIdentifier(FAJITA_INITIALIZATION_METHOD);
				
				// CREATE DISTINGUISHED METHOD STATEMENTS
				ASTList<Statement> methodBody = new ASTArrayList<Statement>();
				
				MethodReference initializationReference =
					programFactory.createMethodReference(initializationId);
				methodBody.add(0, initializationReference);
				
				int goalId = 0;
				
				List<List<Expression>> partitions = getAllPartitions(programFactory, booleanMethods.keySet());
				ArrayList<String> partitionPerGoal = new ArrayList<String>();
				for (List<Expression> partition : partitions) {
					if (!partition.isEmpty()) {
						CopyAssignment thenBranchStatement = createFajitaGoal(programFactory, goalId++, true);
						ASTList<Statement> thenBlock = new ASTArrayList<Statement>();
						thenBlock.add(thenBranchStatement);
						StatementBlock thenBranchBody = programFactory.createStatementBlock(thenBlock);
						Then thenBranch = programFactory.createThen(thenBranchBody);
						
						Expression partitionExpression = foldAnd(programFactory, partition);
						partitionPerGoal.add(partitionExpression.toSource());
						If conditional = programFactory.createIf(partitionExpression, thenBranch);
						methodBody.add(conditional);
					}
				}
				configuration.setPartitionPerGoal(partitionPerGoal);
				
				// XXX: Hack to reuse class coverage code while doing dual-class-branch coverage.
				if (configuration.getCoverageCriteria() == CoverageCriteria.DUAL_CLASS_BRANCH_COVERAGE) {
					resetClassCoverageMethod(configuration);
					Identifier methodToCheckId = programFactory.createIdentifier(configuration.getMethodToCheck());
					ASTList<Expression> args = new ASTArrayList<Expression>();
					if (configuration.getMethodToCheckParameters() != null) {
						for (ParameterDeclaration parameterDeclaration : configuration.getMethodToCheckParameters()) {
							args.add(programFactory.createUncollatedReferenceQualifier(
								parameterDeclaration.getVariableSpecification().getIdentifier()));
						}
					}
					MethodReference methodToCheck = programFactory.createMethodReference(methodToCheckId, args);
					methodBody.add(methodToCheck);
					setClassCoverageMethod(configuration);
				}
				
				StatementBlock methodStatementBlock = programFactory.createStatementBlock(methodBody);
				
				// SET DISCOVERED GOALS
				configuration.setDiscoveredGoals(goalId);
				
				// CREATE THE METHOD THAT REPLACES THE METHOD TO CHECK
				Identifier disinguishedMethodId = programFactory.createIdentifier(configuration.getMethodToCheck());
				ASTList<ParameterDeclaration> distinguishedMethodParameters = new ASTArrayList<ParameterDeclaration>();
				if (configuration.getCoverageCriteria() == CoverageCriteria.DUAL_CLASS_BRANCH_COVERAGE)
					distinguishedMethodParameters = configuration.getMethodToCheckParameters();
				
				Throws distinguishedMethodThrows = programFactory.createThrows();
				ASTList<DeclarationSpecifier> methodSpecifiers = new ASTArrayList<DeclarationSpecifier>();
				methodSpecifiers.add(new Public());
				MethodDeclaration distinguishedMethod = programFactory.createMethodDeclaration(
					methodSpecifiers,
					voidReference, 
					disinguishedMethodId, 
					distinguishedMethodParameters, 
					distinguishedMethodThrows
				);
				distinguishedMethod.setBody(methodStatementBlock);
				
				// ADD FALSE POST CONDITION TO THE METHOD UNDER TEST
				DocComment docComment = programFactory.createDocComment(FAJITA_BENCHMARK_METHOD_TAG);
				ASTList<Comment> comments = new ASTArrayList<Comment>();
				comments.add(docComment);
				distinguishedMethod.setComments(comments);
								
				// ADD THE DISTINGUISHED METHOD TO THE DECLARATION OF THE CLASS
				x.getMembers().add(distinguishedMethod);
				
				// ADD DISTINGUISEHD VARIABLES TO THE CLASS
				for (int goal = 0; goal < configuration.getDiscoveredGoals(); ++goal) {
					Identifier fajitaGoalId = programFactory.createIdentifier(FAJITA_GOAL_TAG + "_" + goal);
					Identifier booleanId = programFactory.createIdentifier("boolean");
					TypeReference booleanReference = programFactory.createTypeReference(booleanId);
					FieldDeclaration fieldDeclaration =	programFactory.createFieldDeclaration(
						initializationSpecifiers, booleanReference, fajitaGoalId, null);
					x.getMembers().add(fieldDeclaration);
				}
				
				// CREATE GOAL INITIALIZATION STATEMENTS
				ASTList<Statement> initializationBody = new ASTArrayList<Statement>();
				for (int i = 0; i < configuration.getDiscoveredGoals(); i++) {
					if (!configuration.getCoveredGoals().contains(i)) {
						CopyAssignment goalInitializationStatement = createFajitaGoal(programFactory, i, false);
						initializationBody.add(goalInitializationStatement);
					}
				}
				
				// XXX: Hack to reuse the class coverage code while doing dual-class-branch coverage.
				if (configuration.getCoverageCriteria() == CoverageCriteria.DUAL_CLASS_BRANCH_COVERAGE) {
					configuration.setDualInitializationStatements(initializationBody);
					return;
				}
				
				if (importsRoopsArray) {
					Identifier roopsArrayVar = programFactory.createIdentifier("myRoopsArray");
					
					// ADD SPECIAL ROOPS ARRAY DECLARATION SO TACO DOES NOT DETECT IT AS UNUSED
					Identifier roopsArrayId = programFactory.createIdentifier("RoopsArray");
					TypeReference roosArrayReference = programFactory.createTypeReference(roopsArrayId);
					FieldDeclaration fieldDeclaration =	programFactory.createFieldDeclaration(
						initializationSpecifiers, roosArrayReference, roopsArrayVar, null);
					x.getMembers().add(fieldDeclaration);

					// USE THE SPECIAL ROOPS ARRAY INSTANCE SO TACO DOES NOT DETECT IT AS UNUSED
					UncollatedReferenceQualifier fajitaGoal =
						programFactory.createUncollatedReferenceQualifier(roopsArrayVar);
					CopyAssignment copyAssignment = programFactory.createCopyAssignment(
						fajitaGoal, programFactory.createNullLiteral());
					initializationBody.add(copyAssignment);
				}
				
				StatementBlock initializationStatementBlock =
					programFactory.createStatementBlock(initializationBody);
			
				// CREATE A GOAL INITIALIZATION METHOD
				ASTList<ParameterDeclaration> initializationParameters = new ASTArrayList<ParameterDeclaration>();
				Throws initializationThrows = programFactory.createThrows();
	
				MethodDeclaration initializaionDeclaration = programFactory.createMethodDeclaration(
					initializationSpecifiers,
					voidReference, 
					initializationId, 
					initializationParameters, 
					initializationThrows
				);
				
				initializaionDeclaration.setBody(initializationStatementBlock);
				
				// ADD THE INITIALIZATION METHOD TO THE DECLARATION OF THE CLASS
				x.getMembers().add(initializaionDeclaration);
				
			}
			
		}
		
		
		/** Returns one expression formed by the conjunction of atoms in a list of expressions. */
		private Expression foldAnd(ProgramFactory factory, List<Expression> booleanMethods) {
			Iterator<Expression> it = booleanMethods.iterator();
			Expression expression = it.next();
			while (it.hasNext())
				expression = factory.createLogicalAnd(expression, it.next());
			return expression;
		}
		
		
		/** Returns the list of all possible partitions from the boolean methods. */
		private List<List<Expression>> getAllPartitions(ProgramFactory factory, Set<String> booleanMethods) {
			List<List<Expression>> partitions = new ArrayList<List<Expression>>();
			
			BigInteger index = new BigInteger("0");
			BigInteger one   = new BigInteger("1");
			BigInteger bound = new BigInteger("0");
			for (int i = 0; i < booleanMethods.size(); ++i)
				bound = bound.setBit(i);
			
			while (index.compareTo(bound) <= 0) {
				List<Expression> partition = new ArrayList<Expression>();
				int i = 0;
				for (String booleanMethod : booleanMethods)
					partition.add(getExpression(factory, booleanMethod, index.testBit(i++)));
				partitions.add(partition);
				index = index.add(one);
			}
			
			return partitions;
		}
		
		
		/**
		 * Returns the boolean expression formed by the invocation to an
		 * argumentless boolean method and the negation operation if appropriate.
		 */
		private Expression getExpression(ProgramFactory factory, String booleanMethod, boolean result) {
			Identifier booleanMethodId = factory.createIdentifier(booleanMethod);
			Expression booleanMethodReference = factory.createMethodReference(booleanMethodId);
			if (!result)
				booleanMethodReference = factory.createLogicalNot(booleanMethodReference);
			return booleanMethodReference;
		}
		
	}
	
}
