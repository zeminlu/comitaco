////////////////////////////////////////////////////////////////////////////
// Module : PRV.java
// Author : Ma, Yu-Seung
// COPYRIGHT 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED.
////////////////////////////////////////////////////////////////////////////

package mujava.op;

import java.io.IOException;
import java.io.PrintWriter;
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
import openjava.mop.OJSystem;
import openjava.ptree.AssignmentExpression;
import openjava.ptree.BinaryExpression;
import openjava.ptree.ClassDeclaration;
import openjava.ptree.CompilationUnit;
import openjava.ptree.Expression;
import openjava.ptree.ExpressionStatement;
import openjava.ptree.FieldAccess;
import openjava.ptree.NonLeaf;
import openjava.ptree.ParseTreeException;
import openjava.ptree.ParseTreeObject;
import openjava.ptree.Statement;
import openjava.ptree.Variable;
import openjava.ptree.VariableDeclaration;

/**
 * <p>
 * Generate PRV (Reference assignment with other compatible variable) mutants --
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
 * <p>
 * Copyright: Copyright (c) 2005 by Yu-Seung Ma, ALL RIGHTS RESERVED
 * </p>
 * 
 * @author Yu-Seung Ma
 * @version 1.0
 */

public class PRV extends mujava.op.util.Mutator {

	private int mutationsLeft = -1;
	ParseTreeObject parent = null;

	private Set<String> fieldVars = new HashSet<String>();
	
	public PRV(FileEnvironment file_env, ClassDeclaration cdecl,
			CompilationUnit comp_unit) {
		super(file_env, comp_unit);
	}
	
	/**
	 * Determines whether a ParseTreeObject is part of a method or not
	 * @param p The object to analyze
	 * @return true if this object belongs to a method
	 */
	public boolean isPartOfMethod(ParseTreeObject p){
		if( p == null){
			return false;
		}
		
		if( openjava.ptree.MethodDeclaration.class.getName().equals(p.getClass().getName()) ){
			return true;
		}
		
		if( openjava.ptree.ClassDeclaration.class.getName().equals(p.getClass().getName())){
			return false;
		}
		
		return isPartOfMethod(p.getParent());
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
		
		var_set.add("null");
		getEnvironment().bindVariable("null", OJSystem.NULLTYPE);

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
						
						if( var.getInitializer() != null ){
							var_set.add(var.getInitializer().toFlattenString());
							getEnvironment().bindVariable(var.getInitializer().toFlattenString(), OJClass.forName(var.getTypeSpecifier().toString()));
						}
					}
					
					if( openjava.ptree.ExpressionStatement.class.getName().equals(s.getClass().getName())){
						ExpressionStatement es = (ExpressionStatement) s;
						Object [] contents = es.getContents();
						
						for(Object o: contents){
							if( openjava.ptree.AssignmentExpression.class.getName().equals(o.getClass().getName())){
								AssignmentExpression ae = (AssignmentExpression) o;
								var_set.add(ae.getRight().toFlattenString());
								getEnvironment().bindVariable(ae.getRight().toFlattenString(), getType(ae.getLeft()));
							}
						}
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
			String trimmedVar = var.trim();
			OJClass type = getType(new Variable(trimmedVar));
			
			if( OJSystem.NULLTYPE.equals(type) ){
				continue;
			}
			
			OJField[] fields = type.getFields();
			OJMethod[] methods = type.getMethods();
			
			for(OJField field: fields){
				String fieldAccessName = trimmedVar + "." + field.getName();
				getEnvironment().bindVariable(fieldAccessName, field.getType());
				fieldVars.add(fieldAccessName);
			}
			
			for(OJMethod method: methods){
				/* Only consider methods with no parameters: i.e.: getters*/
				if( method.getParameterTypes().length == 0 ){
					String methodAccessName = trimmedVar + "." + method.getName() + "()";
					getEnvironment().bindVariable(methodAccessName, method.getReturnType());
					methodVars.add(methodAccessName);
				}
			}
		}
		
		this.fieldVars = fieldVars;
		var_set.addAll(fieldVars);
		var_set.addAll(methodVars);
		
		return var_set.toArray(new String[var_set.size()]);
	}

	public void visit(AssignmentExpression p) throws ParseTreeException {
		
		if( !isPartOfMethod(p) ){
			super.visit(p);
			return;
		}
		
		Expression lexp = p.getLeft();
		Expression rexp = p.getRight();

		if ((lexp instanceof Variable || lexp instanceof FieldAccess)
				&& (rexp instanceof Variable || rexp instanceof FieldAccess)) {
			
			OJClass type = getType(lexp);
			String[] vars = getAccessibleVariables();
			
			OJClass var_type;
			for (int i = 0; i < vars.length; i++) {
				String var = vars[i].trim();
				if (!(var.equals(lexp.toString().trim())) && !(var.equals(rexp.toString().trim()))) {
					var_type = getType(new Variable(var));
					if (compatibleAssignType(type, var_type)) {
						outputToFile(p, var);
					}
					
					if( type.equals(var_type) && !type.equals(OJSystem.NULLTYPE) && !var.contains("()") ){
						outputToFileLeft(p, var);
					}
					
					
				}
			}
			
			/* Modifying left hand side of the assignment */
			if( lexp instanceof FieldAccess ){
				FieldAccess fa = (FieldAccess)lexp;
				OJClass lexpType = getType(lexp);
				OJClass lexpRefType = getType(fa.getReferenceExpr());
				
				if( lexpType.equals(lexpRefType) ){
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
		
		if( !isPartOfMethod(p) ){
			super.visit(p);
			return;
		}
		
		Expression lexp = p.getLeft();
		lexp.accept(this);
		Expression rexp = p.getRight();
		rexp.accept(this);
		
		if( lexp instanceof FieldAccess ){
			FieldAccess fa = (FieldAccess)lexp;
			OJClass lexpType = getType(lexp);
			OJClass lexpRefType = getType(fa.getReferenceExpr());
			
			if( lexpType.equals(lexpRefType)){
				outputToFileLeft(p, fa.getReferenceExpr().toString());
			}
		}
		
		if( rexp instanceof FieldAccess ){
			FieldAccess fa = (FieldAccess)rexp;
			OJClass rexpType = getType(rexp);
			OJClass rexpRefType = getType(fa.getReferenceExpr());
			
			if( rexpType.equals(rexpRefType)){
				outputToFile(p, fa.getReferenceExpr().toString());
			}
		}
		
	}
	
	public void visit(VariableDeclaration p) throws ParseTreeException{
		
		if( !isPartOfMethod(p) ){
			super.visit(p);
			return;
		}
		
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
			String var = vars[i].trim();
			if ( !(var.equals(p.getVariable().toString().trim())) 
					&& !(var.equals(p.getVariableDeclarator().getInitializer().toString().trim())) ) {
				var_type = getType(new Variable(var));
				if (compatibleAssignType(type, var_type)) {
					outputToFile(p, var);
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
			catch (NumberFormatException e) {
				// comment is not an integer. Do nothing.
			}
		}

		if (!(mutationsLeft>0)) return;

		if (Api.usingApi()) {
	  		if (parent==null) return; //do not mutate if no mutation generation limit provided!
			MutantsInformationHolder.mainHolder().addMutantIdentifier(
					Mutant.PRV, original, mutant, "Variable Declaration Right");
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
			catch (NumberFormatException e) {
				// comment is not an integer. Do nothing.
			}
		}

		if (!(mutationsLeft>0)) return;

		if (Api.usingApi()) {
	  		if (parent==null) return; //do not mutate if no mutation generation limit provided!			
			MutantsInformationHolder.mainHolder().addMutantIdentifier(
					Mutant.PRV, original, mutant, "Binary Expression Right");
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
			catch (NumberFormatException e) {
				// comment is not an integer. Do nothing.
			}
		}

		if (!(mutationsLeft>0)) return;

		if (Api.usingApi()) {
	  		if (parent==null) return; //do not mutate if no mutation generation limit provided!			
			MutantsInformationHolder.mainHolder().addMutantIdentifier(
					Mutant.PRV, original, mutant, "Binary Expression Left");
			return;
		}
	}
	
	/**
	 * Output PRV mutants to files
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
			catch (NumberFormatException e) {
				// comment is not an integer. Do nothing.
			}
		}

		if (!(mutationsLeft>0)) return;

		// ********** MUJAVA++ MODIFICATION **********//
		// ********** date: 7 Feb 2012 **********//
		if (Api.usingApi()) {
	  		if (parent==null) return; //do not mutate if no mutation generation limit provided!			
			MutantsInformationHolder.mainHolder().addMutantIdentifier(
					Mutant.PRV, original, mutant, "Assignment Expression Left");
			return;
		}
		// *******************************************//
	}
	
	/**
	 * Output PRV mutants to files
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
			catch (NumberFormatException e) {
				// comment is not an integer. Do nothing.
			}
		}

		if (!(mutationsLeft>0)) return;
		
		// ********** MUJAVA++ MODIFICATION **********//
		// ********** date: 7 Feb 2012 **********//
		if (Api.usingApi()) {
	  		if (parent==null) return; //do not mutate if no mutation generation limit provided!			
			MutantsInformationHolder.mainHolder().addMutantIdentifier(
					Mutant.PRV, original, mutant, "Assignment Expression Right");
			return;
		}
		// *******************************************//

		if (mutationsLeft>0) {
			mutationsLeft--;
			((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
		}

		String f_name;
		num++;
		f_name = getSourceName(this);
		String mutant_dir = getMuantID();

		try {
			PrintWriter out = getPrintWriter(f_name);
			PRV_Writer writer = new PRV_Writer(mutant_dir, out);
			writer.setMutant(original, mutant);
			comp_unit.accept(writer);
			out.flush();
			out.close();
		} catch (IOException e) {
			System.err.println("fails to create " + f_name);
		} catch (ParseTreeException e) {
			System.err.println("errors during printing " + f_name);
			e.printStackTrace();
		}
		
		if (mutationsLeft>=0) {
			mutationsLeft++;
			((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
		}

	}
}

