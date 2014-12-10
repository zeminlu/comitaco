package mujava.op;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import mujava.api.Api;
import mujava.api.Mutant;
import mujava.api.MutantsInformationHolder;
import mujava.openjava.extension.ExtendedClosedEnvironment;
import openjava.mop.CannotAlterException;
import openjava.mop.ClosedEnvironment;
import openjava.mop.Environment;
import openjava.mop.FileEnvironment;
import openjava.mop.OJClass;
import openjava.mop.OJClassNotFoundException;
import openjava.mop.OJField;
import openjava.mop.OJMethod;
import openjava.ptree.AssignmentExpression;
import openjava.ptree.BinaryExpression;
import openjava.ptree.ClassDeclaration;
import openjava.ptree.CompilationUnit;
import openjava.ptree.Expression;
import openjava.ptree.FieldAccess;
import openjava.ptree.NonLeaf;
import openjava.ptree.ParseTreeException;
import openjava.ptree.ParseTreeObject;
import openjava.ptree.ReturnStatement;
import openjava.ptree.Statement;
import openjava.ptree.Variable;
import openjava.ptree.VariableDeclaration;

/**
 * <p>
 * Generate PRVV (Reference assignment with other compatible variable for Values only) mutants --
 * change operands of a reference assignment to be assigned to objects of
 * subclasses
 * </p>
 * <p>
 * <i>Example</i>: Object obj; String s = "Hello"; Integer i = new Integer(4);
 * obj = s; is mutated to <br/>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp
 * ;&nbsp;&nbsp; Object obj; String s = "Hello"; Integer i = new Integer(4); obj
 * = i;
 * </p>
 * 
 * Please note this mutant reuses PRVWriter for writing mutants. 
 * 
 * @author Pablo Alejandro Giorgi
 * @author Nicolás Magni
 * @author Santiago José Samra
 * @author Matías Williams
 * @version 1.0
 */
public class PRVV extends mujava.op.util.Mutator {

	private int mutationsLeft = -1;
	ParseTreeObject parent = null;

	private Set<String> fieldVars = new HashSet<String>();
	
	public PRVV(FileEnvironment file_env, ClassDeclaration cdecl,
			CompilationUnit comp_unit) {
		super(file_env, comp_unit);
	}
	
	/**
	 * Retrieve variable to be mutated
	 * 
	 * @return variable's name, otherwise return null
	 * @throws ParseTreeException
	 */
	@SuppressWarnings("unchecked")
	private String[] getAccessibleVariables() throws ParseTreeException {
		HashSet<String> var_set = new HashSet<String>();

		if (env instanceof ClosedEnvironment) {
			ExtendedClosedEnvironment mujava_env = new ExtendedClosedEnvironment(env);
			var_set.addAll(mujava_env.getAccessibleVariables());
			mujava_env = null;
		}

		for (int i = (env_nest.size() - 1); i >= 0; i--) {
			Environment temp_env = env_nest.get(i);
			if (temp_env instanceof ClosedEnvironment) {
				ExtendedClosedEnvironment mujava_env = new ExtendedClosedEnvironment(temp_env);
				var_set.addAll(mujava_env.getAccessibleVariables());
				mujava_env = null;
			}
		}

		OJClass clazz = getSelfType();
		OJField[] fs = clazz.getDeclaredFields();
		if (fs != null) {
			for (int k = 0; k < fs.length; k++) {
				var_set.add(fs[k].getName());
			}
		}
		
		try {
			OJMethod[] methods = clazz.getMethods();
			OJMethod method = null;
			for(OJMethod m : methods) {
				if(m.getName().equalsIgnoreCase(Api.getMethodUnderConsideration())) {
					method = m;
				}
			}
			if(method != null) {
				Enumeration<Statement> enumeration = method.getBody().elements();
				
				while(enumeration.hasMoreElements()){
					Statement s = enumeration.nextElement();
					if( openjava.ptree.VariableDeclaration.class.getName().equals(s.getClass().getName()) ){
						VariableDeclaration var = (VariableDeclaration) s;
						var_set.add(var.getVariable());
						getEnvironment().bindVariable(var.getVariable(), OJClass.forName(var.getTypeSpecifier().toString()));
					}
				}
			}
		} catch (CannotAlterException e) {
			throw new ParseTreeException(e);
		} catch (OJClassNotFoundException e) {
			throw new ParseTreeException(e);
		}
		
		Set<String> fieldVars = new HashSet<String>();
		Set<String> methodVars = new HashSet<String>();
		/* Field access */
		for(String var: var_set){
			
			OJClass type = getType(new Variable(var));
			OJField[] fields = type.getFields();
			OJMethod[] methods = type.getMethods();
			
			for(OJField field: fields){
				String fieldAccessName = var + "." + field.getName();
				getEnvironment().bindVariable(fieldAccessName, field.getType());
				fieldVars.add(fieldAccessName);
			}
			
			for(OJMethod method: methods){
				/* Only consider methods with no parameters: i.e.: getters*/
				if( method.getParameterTypes().length == 0 ){
					String methodAccessName = var + "." + method.getName() + "()";
					getEnvironment().bindVariable(methodAccessName, method.getReturnType());
					methodVars.add(methodAccessName);
				}
			}
		}
		
		this.fieldVars = fieldVars;
		var_set.addAll(fieldVars);
		var_set.addAll(methodVars);
		
		Set<String> unwantedObjectVariables = new HashSet<String>();
		for(String var: var_set){
			OJClass type = getType(new Variable(var));
			if( !type.isPrimitive() && !type.isPrimitiveWrapper() ){
				unwantedObjectVariables.add(var);
			}
		}
		
		var_set.removeAll(unwantedObjectVariables);
		fieldVars.removeAll(unwantedObjectVariables);

		return var_set.toArray(new String[var_set.size()]);
	}

	public void visit(AssignmentExpression p) throws ParseTreeException {
		Expression lexp = p.getLeft();
		Expression rexp = p.getRight();

		if ((lexp instanceof Variable || lexp instanceof FieldAccess)
				&& (rexp instanceof Variable || rexp instanceof FieldAccess)) {
			
			OJClass type = getType(lexp);
			String[] vars = getAccessibleVariables();
			
			OJClass var_type;
			for (int i = 0; i < vars.length; i++) {
				if (!(vars[i].equals(lexp.toString())) && !(vars[i].equals(rexp.toString()))) {
					var_type = getType(new Variable(vars[i]));
					
					if (compatibleAssignType(type, var_type)) {
						outputToFile(p, vars[i]);
					}
				}
			}
			
			/* Modifying left hand side of the assignment */
			if( lexp instanceof FieldAccess ){
				FieldAccess fa = (FieldAccess)lexp;
				OJClass lexpType = getType(lexp);
				OJClass lexpRefType = getType(fa.getReferenceExpr());
				
				if( lexpType.equals(lexpRefType) && (lexpType.isPrimitive() || lexpType.isPrimitiveWrapper())){
					outputToFileLeft(p, fa.getReferenceExpr().toString());
				}
				
			}
			
			for(String s: fieldVars){
				if( getType(new Variable(s)).equals(getType(lexp))){
					outputToFileLeft(p, s);
				}
			}
		}
	}
	
	public void visit(BinaryExpression p) throws ParseTreeException{
		Expression lexp = p.getLeft();
		lexp.accept(this);
		Expression rexp = p.getRight();
		rexp.accept(this);
		
		if( lexp instanceof FieldAccess ){
			FieldAccess fa = (FieldAccess)lexp;
			OJClass lexpType = getType(lexp);
			OJClass lexpRefType = getType(fa.getReferenceExpr());
			
			if( lexpType.equals(lexpRefType) && (lexpType.isPrimitive() || lexpType.isPrimitiveWrapper())){
				outputToFileLeft(p, fa.getReferenceExpr().toString());
			}
		}
		
		if( rexp instanceof FieldAccess ){
			FieldAccess fa = (FieldAccess)rexp;
			OJClass rexpType = getType(rexp);
			OJClass rexpRefType = getType(fa.getReferenceExpr());
			
			if( rexpType.equals(rexpRefType) && (rexpType.isPrimitive() || rexpType.isPrimitiveWrapper())){
				outputToFile(p, fa.getReferenceExpr().toString());
			}
		}
		
	}
	
	
	
	public void visit(VariableDeclaration p) throws ParseTreeException{
		
		if( p.getVariableDeclarator().getInitializer() == null ){
			super.visit(p);
			return;
		}
		
	   try {
			getEnvironment().bindVariable(p.getVariable(), OJClass.forName(p.getTypeSpecifier().toString()));
	   } catch (OJClassNotFoundException e) {
			System.err.println("Couldn't bind variable " + p.getVariable());
	   }
		
		OJClass type = getType(p.getTypeSpecifier());
		String[] vars = getAccessibleVariables();
		
		OJClass var_type;
		for (int i = 0; i < vars.length; i++) {
			if ( !(vars[i].equals(p.getVariable().toString()))) {
				var_type = getType(new Variable(vars[i]));
				
				if (compatibleAssignType(type, var_type)) {
					outputToFile(p, vars[i]);
				}
			}
		}
	}
	
	public void visit(ReturnStatement p) throws ParseTreeException {
		
		Expression rexp = p.getExpression();
		
		if( rexp == null ){
			super.visit(p);
			return;
		}

		Variable var = null;
		OJClass type = null;

		
		try {
			type = ((ReturnStatement) p).getExpression().getType(getEnvironment());
		}
		catch (Exception e) {
			throw new ParseTreeException("PRVV.visit: problem calculating type of return statement expression");
		}
						
		String[] vars = getAccessibleVariables();
		
		OJClass var_type;
		for (int i = 0; i < vars.length; i++) {
			if ( !(vars[i].equals(p.getExpression().toString()))) {
				var_type = getType(new Variable(vars[i]));
				
				if (compatibleAssignType(type, var_type)) {
					outputToFile(p, vars[i]);
				}
			}
		}
	}

	
	/**
	 * Determines whether two given types are compatible for assignment. 
	 * 
	 * @param p The type on the left side of the assignment
	 * @param c The type on the right side of the assignment
	 * @return true if the types are compatible, false otherwise
	 */
	private boolean compatibleAssignType(OJClass p, OJClass c){
		if( p == null || c == null ){
			return false;
		}
		
		if( "void".equalsIgnoreCase(c.getName())){
			return false;
		}
		
		/* Supporting java auto-boxing */
		if( !p.isArray() && !c.isArray() 
				&& ( (p.isPrimitive() && c.isPrimitiveWrapper()) 
						|| (p.isPrimitiveWrapper() && c.isPrimitive()) ) 
		){
			return (p.unwrappedPrimitive()).isAssignableFrom(c.unwrappedPrimitive());
		}
		return p.isAssignableFrom(c);
	} 
	
	public void outputToFile(VariableDeclaration original, String mutant){
		if (comp_unit == null){
			return;
		}

		parent = original.getMutationLimitParent();
		if (parent != null && ((NonLeaf) parent).getComment()!=null) {
			try {
				String mutations = ((NonLeaf) parent).getComment().substring(14);
				mutationsLeft = Integer.parseInt(mutations);
			}
			catch (Exception e) {
				// comment is not an integer. Do nothing.
			}
		}

		if (!(mutationsLeft>0)) return;


		if (Api.usingApi()) {
			if (parent==null) return; //do not mutate if no mutation generation limit provided!
			MutantsInformationHolder.mainHolder().addMutantIdentifier(
					Mutant.PRVV, original, mutant, "Variable Declaration Right");
			return;
		}
	}

	public void outputToFile(ReturnStatement original, String mutant){
		if (comp_unit == null){
			return;
		}

		parent = original.getMutationLimitParent();
		if (parent != null && ((NonLeaf) parent).getComment()!=null) {
			try {
				String mutations = ((NonLeaf) parent).getComment().substring(14);
				mutationsLeft = Integer.parseInt(mutations);
			}
			catch (Exception e) {
				// comment is not an integer. Do nothing.
			}
		}

		if (!(mutationsLeft>0)) return;


		if (Api.usingApi()) {
			if (parent==null) return; //do not mutate if no mutation generation limit provided!
			MutantsInformationHolder.mainHolder().addMutantIdentifier(
					Mutant.PRVV, original, mutant, "Return Statement Right");
			return;
		}
	}

	
	
	public void outputToFile(BinaryExpression original, String mutant){
		if (comp_unit == null){
			return;
		}

		parent = original.getMutationLimitParent();
		if (parent != null && ((NonLeaf) parent).getComment()!=null) {
			try {
				String mutations = ((NonLeaf) parent).getComment().substring(14);
				mutationsLeft = Integer.parseInt(mutations);
			}
			catch (Exception e) {
				// comment is not an integer. Do nothing.
			}
		}

		if (!(mutationsLeft>0)) return;


		if (Api.usingApi()) {
			if (parent==null) return; //do not mutate if no mutation generation limit provided!
			MutantsInformationHolder.mainHolder().addMutantIdentifier(
					Mutant.PRVV, original, mutant, "Binary Expression Right");
			return;
		}
	}
	
	public void outputToFileLeft(BinaryExpression original, String mutant){
		if (comp_unit == null){
			return;
		}

		parent = original.getMutationLimitParent();
		if (parent != null && ((NonLeaf) parent).getComment()!=null) {
			try {
				String mutations = ((NonLeaf) parent).getComment().substring(14);
				mutationsLeft = Integer.parseInt(mutations);
			}
			catch (Exception e) {
				// comment is not an integer. Do nothing.
			}
		}

		if (!(mutationsLeft>0)) return;


		if (Api.usingApi()) {
			if (parent==null) return; //do not mutate if no mutation generation limit provided!
			MutantsInformationHolder.mainHolder().addMutantIdentifier(
					Mutant.PRVV, original, mutant, "Binary Expression Left");
			return;
		}
	}
	
	/**
	 * Output PRVV mutants to files
	 * 
	 * @param original
	 * @param mutant
	 */
	public void outputToFileLeft(AssignmentExpression original, String mutant) {
		if (comp_unit == null)
			return;

		parent = original.getMutationLimitParent();
		if (parent != null && ((NonLeaf) parent).getComment()!=null) {
			try {
				String mutations = ((NonLeaf) parent).getComment().substring(14);
				mutationsLeft = Integer.parseInt(mutations);
			}
			catch (Exception e) {
				// comment is not an integer. Do nothing.
			}
		}

		if (!(mutationsLeft>0)) return;


		// ********** MUJAVA++ MODIFICATION **********//
		// ********** date: 7 Feb 2012 **********//
		if (Api.usingApi()) {
			if (parent==null) return; //do not mutate if no mutation generation limit provided!
			MutantsInformationHolder.mainHolder().addMutantIdentifier(
					Mutant.PRVV, original, mutant, "Assignment Expression Left");
			return;
		}
		// *******************************************//
	}
	
	/**
	 * Output PRVV mutants to files
	 * 
	 * @param original
	 * @param mutant
	 */
	public void outputToFile(AssignmentExpression original, String mutant) {
		if (comp_unit == null)
			return;

		parent = original.getMutationLimitParent();
		if (parent != null && ((NonLeaf) parent).getComment()!=null) {
			try {
				String mutations = ((NonLeaf) parent).getComment().substring(14);
				mutationsLeft = Integer.parseInt(mutations);
			}
			catch (Exception e) {
				// comment is not an integer. Do nothing.
			}
		}

		if (!(mutationsLeft>0)) return;


		// ********** MUJAVA++ MODIFICATION **********//
		// ********** date: 7 Feb 2012 **********//
		if (Api.usingApi()) {
			if (parent==null) return; //do not mutate if no mutation generation limit provided!
			MutantsInformationHolder.mainHolder().addMutantIdentifier(
					Mutant.PRVV, original, mutant, "Assignment Expression Right");
			return;
		}
		// *******************************************//
	}
}
