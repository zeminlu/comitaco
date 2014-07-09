package ar.uba.dc.rfm.fajita.translation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import recoder.CrossReferenceServiceConfiguration;
import recoder.ProgramFactory;
import recoder.java.CompilationUnit;
import recoder.java.Identifier;
import recoder.java.NonTerminalProgramElement;
import recoder.java.PrettyPrinter;
import recoder.java.declaration.ClassDeclaration;
import recoder.java.declaration.MethodDeclaration;
import recoder.java.declaration.TypeDeclaration;
import recoder.java.declaration.TypeDeclarationContainer;
import recoder.java.expression.literal.BooleanLiteral;
import recoder.java.expression.operator.CopyAssignment;
import recoder.java.reference.UncollatedReferenceQualifier;
import recoder.kit.Transformation;

import ar.uba.dc.rfm.fajita.FajitaConfiguration;
import ar.uba.dc.rfm.fajita.FajitaException;
import ar.uba.dc.rfm.tools.FileTools;


/**
 * This class is in charge of translating the input java code in the required
 * way in order to instrument the different coverage techniques. It acts as a
 * factory for the actual <code>Transformation</code> needed for the required
 * coverage criteria.<br/>
 * 
 * This class also holds common logic of all the different transformations.
 * 
 * @see GoalTransformation
 *
 */
public class FajitaJavaCodeTranslator {
	
	///////////////////////////////////////////////////////////////////////////
	//           COMMON CONSTANTS USED BY ALL THE TRANSFORMATIONS            //
	///////////////////////////////////////////////////////////////////////////
	
	public static final String ROOPS_ANNOTATION_NROFGOALS = "NrOfGoals";
	public static final String ROOPS_ANNOTATION_BENCHMARK_METHOD = "BenchmarkMethod";
	public static final String ROOPS_ANNOTATION_BENCHMARK_CLASS = "BenchmarkClass";
	public static final String ROOPS_REACHED_METHOD = "reached";
	public static final String ROOPS_VERDICT_UNREACHABLE = "UNREACHABLE";
	
	public static final String KIASAN_REPOK_METHOD = "repOK";
	
	public static /*final*/ String FAJITA_GOAL_TAG = "roops_goal";
	public static final String FAJITA_LOOP_EXHAUSTION_TAG = "loop_exhaustion";
	public static final String FAJITA_INITIALIZATION_METHOD = "fajita_roopsGoal_initialization";
	public static final String FAJITA_BENCHMARK_METHOD_TAG = "/** @Modifies_Everything\n\t * @Ensures false;\n\t */";
	
	
	/** Fajita runtime configuration. */
	private final FajitaConfiguration configuration;
	
	
	/**
	 * Constructor of a <code>FajitaJavaCodeTranslator</code>.
	 * 
	 * @param configuration a <code>FajitaConfiguration</code> with the
	 *	necessary data to choose the right transformation and translate
	 *	the input code.
	 */
	public FajitaJavaCodeTranslator(FajitaConfiguration configuration) {
		this.configuration = configuration;
	}
	
	/**
	 * This method translates the input java code and writes the transformation
	 * result into a file. The output file contains the java code with the
	 * instrumentation of the chosen coverage criteria, ready to be introduced
	 * in the Taco->DynAlloy->AlloyCli pipeline.
	 * 
	 * @param inFile the name (or path) of the input file.
	 * 
	 * @param outFile the name (or path) of the output file.
	 * 
	 */
	public void translate(String inFile, String outFile) {
		try {
			
			String contents = FileTools.readFile(inFile);
	
			CrossReferenceServiceConfiguration recoder =
				new CrossReferenceServiceConfiguration();
			
			CompilationUnit compilationUnit =
				recoder.getProgramFactory().parseCompilationUnit(contents);
			
			Transformation transformation;
			
			switch (configuration.getCoverageCriteria()) {
				case GOAL_COVERAGE:
					transformation = new GoalTransformation(
						configuration, recoder, compilationUnit);
					break;
				case CLASS_COVERAGE:
					transformation = new ClassTransformation(
						configuration, recoder, compilationUnit);
					break;
				case BRANCH_COVERAGE:
					transformation = new BranchTransformation(
						configuration, recoder, compilationUnit);
					break;
				case DUAL_CLASS_BRANCH_COVERAGE:
					transformation = new DualClassBranchTransformation(
						configuration, recoder, compilationUnit);
					break;
				default:
					throw new FajitaException(
						"Java code translation not implemented for the coverage criteria: " +
						configuration.getCoverageCriteria().toString()
					);
			}
			
			transformation.execute();

			FajitaPrettyPrinter.print(outFile, compilationUnit);
			
		} catch (Exception e) {
			throw new FajitaException(
				"Unable to translate parsed java code. " +
				"Due to the following error: " + e.getMessage(),
				e
			);
		}
	}
	
	public static class FajitaPrettyPrinter extends PrettyPrinter {

		protected FajitaPrettyPrinter(FileWriter writer, Properties properties) {
			super(writer, properties);
		}
		
		public static void print(String outputFile, CompilationUnit compilationUnit) throws IOException {
			File file = new File(outputFile);
			if (file.exists()) file.delete();
			file.getParentFile().mkdirs();
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			Properties properties = new Properties();
			properties.put(INDENTATION_AMOUNT, "4");
			FajitaPrettyPrinter printer = new FajitaPrettyPrinter(writer, properties);
			printer.visitCompilationUnit(compilationUnit);
			writer.close();
		}
		
		@Override
		public boolean getBooleanProperty(String key) {
			return false;
		}
		
	}
	
	///////////////////////////////////////////////////////////////////////////
	//           COMMON FUNCGTIONS USED BY ALL THE TRANSFORMATIONS           //
	///////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * This function holds the common logic to create a java statement that is
	 * used to represent a fajita goal. Fajita goals are the distinguished
	 * variables used to instrument the different coverage mechanisms.
	 * 
	 * @param factory a <code>ProgramFactory</code> used to create the AST nodes
	 *	that will conform the goal statement.
	 * 
	 * @param goalId an int with the goal identifier.
	 * 
	 * @param reached a boolean that indicates if this states that the goal is
	 *	being reached at the point where it's included or not.
	 * 
	 * @return a <code>CopyAssignment</code> the java expression used to mark
	 *	the goal. This expression can be easily inserted in the desired java
	 *	statement block.
	 * 
	 */
	public static CopyAssignment createFajitaGoal(
			ProgramFactory factory, int goalId, boolean reached) {
		Identifier fajitaGoalId =
			factory.createIdentifier(FAJITA_GOAL_TAG + "_" + goalId);
		UncollatedReferenceQualifier fajitaGoal =
			factory.createUncollatedReferenceQualifier(fajitaGoalId);
		BooleanLiteral reachedLiteral = factory.createBooleanLiteral(reached);
		CopyAssignment copyAssignment =
			factory.createCopyAssignment(fajitaGoal, reachedLiteral);
		return copyAssignment;
	}
	
	
///	public static CopyAssignment createLoopExhaustion(
///			ProgramFactory factory, boolean reached) {
///		Identifier fajitaGoalId =
///			factory.createIdentifier(FAJITA_LOOP_EXHAUSTION_TAG);
///		UncollatedReferenceQualifier fajitaGoal =
///			factory.createUncollatedReferenceQualifier(fajitaGoalId);
///		BooleanLiteral reachedLiteral = factory.createBooleanLiteral(reached);
///		CopyAssignment copyAssignment =
///			factory.createCopyAssignment(fajitaGoal, reachedLiteral);
///		return copyAssignment;
///	}
	
	
	/**
	 * This function holds the common logic of obtaining the complete name of a
	 * given <code>TypeDeclaration</code>. Such names are composed by the 
	 * package descriptor and the type name.
	 * 
	 * @param x the <code>TypeDeclaration</code> which complete name is being queried.
	 * 
	 * @return a <code>String</code> with the full name of the given type.
	 * 
	 */
	public static String getFullName(TypeDeclaration x) {
		String parentName;
		TypeDeclarationContainer parent = x.getParent();
		parentName = parent instanceof TypeDeclaration ?
			getFullName((TypeDeclaration)parent) + "." : "";
		return parentName + x.getName();
	}
	
	
	/**
	 * This function returns the name of the class containing the given
	 * <code>NonTerminalProgramElement</code> or null if there is no such class.
	 */
	public static String getContainingClass(NonTerminalProgramElement element) {
		String className;
		if (element == null)
			className = null;
		else if (element instanceof ClassDeclaration)
			className = FajitaJavaCodeTranslator.getFullName((TypeDeclaration)element);
		else
			className = getContainingClass(element.getASTParent());
		return className;
	}
	
	
	/**
	 * This function returns the name of the method containing the given
	 * <code>NonTerminalProgramElement</code> or null if there is no such method.
	 */
	public static String getContainingMethod(NonTerminalProgramElement element) {
		String methodName;
		if (element == null)
			methodName = null;
		else if (element instanceof MethodDeclaration)
			methodName = ((MethodDeclaration)element).getName();
		else
			methodName = getContainingMethod(element.getASTParent());
		return methodName;
	}
	
}
