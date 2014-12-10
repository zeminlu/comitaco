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
import openjava.mop.*;
import openjava.ptree.*;



/**
 * <p>
 * Generate PRVO (Reference assignment with other compatible variable for Objects only) mutants --
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
 * @author Nicol√°s Magni
 * @author Santiago Jos√© Samra
 * @author Mat√≠as Williams
 * @version 1.0
 */
public class PRVO extends mujava.op.util.Mutator {
	
	private int mutationsLeft = -1;
	ParseTreeObject parent = null;
	
	private Set<String> fieldVars = new HashSet<String>(); // FIXME: Remove when the refactoring is ready. It's preserved only for historical
	                                                       // reasons, since it is used by "oldVisit" to mutate left hand side of assignment.

	private Set<OJField> fields = new HashSet<OJField>(); // Collection of ALL fields (not the local ones, which are variables)

	private Set<OJMethod> methods = new HashSet<OJMethod>(); // Collection of ALL parameterless methods, including local p'less methods
	
	private Set<Variable> variables = new HashSet<Variable>(); // Collection of ALL variables (local fields, parameters and variables in the
	                                                           // method of interest, i.e., that to be mutated)
	
	private Set<Object[]> compositeValidExpressions = new HashSet<Object[]>();
	
	private boolean computedAccessibleVariables = false;
	
	public PRVO(FileEnvironment file_env, ClassDeclaration cdecl,
			CompilationUnit comp_unit) {
		super(file_env, comp_unit);
	}
	
	/**
	 * Calculates the variables local to a statement list. As a side effect, variables are bound to their corresponding types in the environment.
	 * @param st is the statement list for which the local variables are calculates
	 * @return the list of all local variables declared in the statement list
	 * @throws ParseTreeException
	 */
	private String[] getLocalVariables(StatementList statList) throws ParseTreeException {
		Enumeration st = statList.elements();
		HashSet<String> result = new HashSet<String>();
		try {
			while (st.hasMoreElements()) {
				Object current = st.nextElement();
				if (VariableDeclaration.class.getName().equals(current.getClass().getName()) ){
					VariableDeclaration var = (VariableDeclaration) current;
					result.add(var.getVariable());
					getEnvironment().bindVariable(var.getVariable(), OJClass.forName(var.getTypeSpecifier().toString()));
				}
				else if (ForStatement.class.getName().equals(current.getClass().getName())) {
					ForStatement f = (ForStatement) current;
					if (f.getInitDeclType()!=null) {
						VariableDeclarator[] vars = f.getInitDecls();
						for (int i=0; i<vars.length; i++) {
							result.add(vars[i].getVariable());
							getEnvironment().bindVariable(vars[i].getVariable(), OJClass.forName(f.getInitDeclType().toString()));
						}
					}
					String[] blockLocalVars = getLocalVariables(f.getStatements());
					for (int i=0; i<blockLocalVars.length; i++) {
						result.add(blockLocalVars[i]);
					}
				}
				else if (WhileStatement.class.getName().equals(current.getClass().getName())) {
					WhileStatement f = (WhileStatement) current;
					String[] blockLocalVars = getLocalVariables(f.getStatements());
					for (int i=0; i<blockLocalVars.length; i++) {
						result.add(blockLocalVars[i]);
					}
				}
				else if (DoWhileStatement.class.getName().equals(current.getClass().getName())) {
					DoWhileStatement f = (DoWhileStatement) current;
					String[] blockLocalVars = getLocalVariables(f.getStatements());
					for (int i=0; i<blockLocalVars.length; i++) {
						result.add(blockLocalVars[i]);
					}
				}
				else if (IfStatement.class.getName().equals(current.getClass().getName())) {
					IfStatement f = (IfStatement) current;
					String[] blockLocalVars = getLocalVariables(f.getStatements());
					for (int i=0; i<blockLocalVars.length; i++) {
						result.add(blockLocalVars[i]);
					}
					blockLocalVars = getLocalVariables(f.getElseStatements());
					for (int i=0; i<blockLocalVars.length; i++) {
						result.add(blockLocalVars[i]);
					}
				}
				else if (SwitchStatement.class.getName().equals(current.getClass().getName())) {
					SwitchStatement f = (SwitchStatement) current;
					CaseGroupList list = f.getCaseGroupList();
					for (int x=0; x<list.size(); x++) {
						String[] blockLocalVars = getLocalVariables(list.get(x).getStatements());
						for (int i=0; i<blockLocalVars.length; i++) {
							result.add(blockLocalVars[i]);
						}
					}
				}
				else if (TryStatement.class.getName().equals(current.getClass().getName())) {
					TryStatement f = (TryStatement) current;
					String[] blockLocalVars = getLocalVariables(f.getBody());
					for (int i=0; i<blockLocalVars.length; i++) {
						result.add(blockLocalVars[i]);
					}
					CatchList list = f.getCatchList();
					for (int x=0; x<list.size(); x++) {
						blockLocalVars = getLocalVariables(list.get(x).getBody());
						for (int i=0; i<blockLocalVars.length; i++) {
							result.add(blockLocalVars[i]);
						}
					}
					blockLocalVars = getLocalVariables(f.getFinallyBody());
					for (int i=0; i<blockLocalVars.length; i++) {
						result.add(blockLocalVars[i]);
					}
				}
				


			}
		}
		catch (OJClassNotFoundException e) {
			throw new ParseTreeException(e);
		}


		return result.toArray(new String[result.size()]);

	}

	
	/**
	 * Retrieves all the variables available in the class, its superclasses, attributes, and variables local to the method under consideration. 
	 * These variables are used for mutating expressions (e.g., right hand side of assignments). As side effects, every variable is bound to
	 * its corresponding type in the environment. Also, fields are stored in attribute this.fields, parameterless methods are stored in 
	 * this.methods, and variables (all of them, local to method, parameters, etc.) are stored in attribute this.variables.
	 * Finally, if these expressions have been computed previously, they are reproduced from this.fields, this.variables and this.methods, as
	 * opposed to recalculating them.
	 * @return an array containing all the variables names (only the names, as strings, not their types).
	 * @throws ParseTreeException
	 */
	@SuppressWarnings("unchecked")
	private String[] getAccessibleVariables() throws ParseTreeException {
		HashSet<String> var_set = new HashSet<String>();

		if (!computedAccessibleVariables) {

			computedAccessibleVariables = true;

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

			// add all fields of the class to var_set
			OJClass clazz = getSelfType();
			OJField[] fs = clazz.getDeclaredFields();
			if (fs != null) {
				for (int k = 0; k < fs.length; k++) {
					var_set.add(fs[k].getName());
				}
			}

			try {
				// obtain method under consideration (method)
				// It's used to get all variables locally declared in the method
				OJMethod[] methods = clazz.getMethods();
				OJMethod method = null;
				for(OJMethod m : methods) {
					if(m.getName().equalsIgnoreCase(Api.getMethodUnderConsideration())) {
						method = m;
						//FIXME: there should be a break here!
					}
				}
				if(method != null) {
					// add variables locally declared in method under consideration, to var_set
					
					//get method parameters and do the bind stuff
					String param;
					OJClass type;
					int m = 0;
					while (m < method.getParameterVariables().size()) {
						param = method.getParameters()[m];
						type = method.getParameterTypes()[m];
						var_set.add(method.getParameterVariables().get(m).toString());
						getEnvironment().bindVariable(param, type);
						m++;
					}
					//-----------------------------------------------------
					
					String[] methodVars = getLocalVariables(method.getBody());
					for (int i=0; i<methodVars.length; i++) {
						var_set.add(methodVars[i]);
					}
				}
			} catch (CannotAlterException e) {
				throw new ParseTreeException(e);
			} 


			Set<String> fieldVars = new HashSet<String>();
			Set<String> methodVars = new HashSet<String>();
			/* Field access */
			for(String var: var_set){
				// add every variable in var_set, as an expression, to this.variables
				Variable v = new Variable(var);
				variables.add(v);


				// for every variable already in var_set, compute
				// fields and parameterless methods, and put in fieldVars and methodVars, resp.
				OJClass type = getType(v);
				boolean isArray = type.isArray();
				OJField[] fields = type.getFields();
				OJMethod[] methods = isArray?new OJMethod[]{}:type.getMethods();
				
				

				for(OJField field: fields){
					this.fields.add(field);
					String fieldAccessName = var + "." + field.getName();
					getEnvironment().bindVariable(fieldAccessName, field.getType());
					fieldVars.add(fieldAccessName);
				}

				for(OJMethod method: methods){
					/* Only consider methods with no parameters: i.e.: getters*/
					if( method.getParameterTypes().length == 0 ){
						this.methods.add(method);
						String methodAccessName = var + "." + method.getName() + "()";
						getEnvironment().bindVariable(methodAccessName, method.getReturnType());
						methodVars.add(methodAccessName);
					}
				}
			}

			// add all local parameterless methods to this.methods
			for (OJMethod m: getSelfType().getDeclaredMethods()) {
				if (m.getParameterTypes().length==0) {
					this.methods.add(m);
				}
			}

			// add all fields and parameterless methods of variables in var_set, to var_set
			var_set.addAll(fieldVars);
			var_set.addAll(methodVars);


			Set<String> unwantedPrimitiveVariables = new HashSet<String>();
			for(String var: var_set){
				Variable v = new Variable(var);
				OJClass type = getType(v);
				if( type.isPrimitive() || type.isPrimitiveWrapper() ){
					unwantedPrimitiveVariables.add(var);
					variables.remove(v);
				}
			}

			var_set.removeAll(unwantedPrimitiveVariables);
			fieldVars.removeAll(unwantedPrimitiveVariables);

			this.fieldVars = fieldVars; // FIXME: Remove when refactoring is ready
			return var_set.toArray(new String[var_set.size()]);
		}
		else {
			for (Variable v: variables) {
				var_set.add(v.toString());
			}
			for (OJMethod m: methods) {
				var_set.add(m.toString());
			}
			for (OJField f: fields) {
				var_set.add(f.toString());
			}
		}
		return var_set.toArray(new String[var_set.size()]);
	}
		

	private void computeCompositeExpressions() throws ParseTreeException {
		if (!compositeValidExpressions.isEmpty()) {
			compositeValidExpressions = new HashSet<Object[]>();
		}
		for (Variable var: variables) {
			OJClass varType = getType(var);
			if (varType != null) {
				// variable is defined in the current scope
				OJField[] fields = varType.getFields();
				OJMethod[] methods = varType.getMethods();
				for (OJField f: fields) {
					//OJClass srcTypeField = f.getDeclaringClass();
					//boolean validArrayField = this.arrayFields.contains(f)?(varType.isArray()):true;
					//if (/*validArrayField && */varType.equals(srcTypeField)) {
						Object[] elem = new Object[2];
						elem[0] = var;
						elem[1] = f;
						compositeValidExpressions.add(elem);
					//}
				}

				for (OJMethod m: methods) {
					if (m.getParameterTypes().length == 0) {
						Object[] elem = new Object[2];
						elem[0] = var;
						elem[1] = m;
						compositeValidExpressions.add(elem);
					}
				}			

			}
		}

		for (OJField f1: fields) {
			OJClass varType = f1.getType();
			OJField[] fields = varType.getFields();
			for (OJField f2: fields) {

				//OJClass srcTypeField = f2.getDeclaringClass();
				//if (varType.equals(srcTypeField)) {
					Object[] elem = new Object[2];
					elem[0] = f1;
					elem[1] = f2;
					compositeValidExpressions.add(elem);
				//}
			}
			OJMethod[] methods = varType.getMethods();
			for (OJMethod m: methods) {
				if (m.getParameterTypes().length == 0) {
					Object[] elem = new Object[2];
					elem[0] = f1;
					elem[1] = m;
					compositeValidExpressions.add(elem);
				}
			}			
		}		
		
		for (OJMethod m: methods) {
			if (m.getParameterTypes().length != 0) {
				continue;
			}
			OJClass varType = m.getReturnType();
			OJField[] fields = varType.getFields();
			OJMethod[] methods = varType.getMethods();
			for (OJField f: fields) {

				//OJClass srcTypeField = f.getDeclaringClass();
				//if (varType.equals(srcTypeField)) {
					Object[] elem = new Object[2];
					elem[0] = m;
					elem[1] = f;
					compositeValidExpressions.add(elem);
				//}
			}

			for (OJMethod m2: methods) {
				if (m2.getParameterTypes().length != 0) {
					continue;
				}
				//OJClass srcTypeMethod = m2.getDeclaringClass();
				//if (varType.equals(srcTypeMethod)) {
					Object[] elem = new Object[2];
					elem[0] = m;
					elem[1] = m2;
					compositeValidExpressions.add(elem);
				//}
			}			
		}
	}
	
	private String compositeExpressionToString(Object[] elem) {
		Object first = elem[0];
		Object second = elem[1];
		String s1, s2;
		if (first instanceof Variable) {
			s1 = ((Variable) first).toString();
		}
		else if (first instanceof OJField) {
			s1 = ((OJField) first).getName();
		}
		else {
			s1 = ((OJMethod) first).getName()+"()";
		}
		
		if (second instanceof Variable) {
			s2 = ((Variable) second).toString();
		}
		else if (second instanceof OJField) {
			s2 = ((OJField) second).getName();
		}
		else {
			s2 = ((OJMethod) second).getName()+"()";
		}
		
		return s1+"."+s2;
	}

	private OJClass compositeExpressionSourceType(Object[] elem) throws ParseTreeException {
		Object first = elem[0];
		if (first instanceof Variable) {
			return getSelfType();
		}
		else if (first instanceof OJField) {
			return ((OJField) first).getDeclaringClass();
		}
		else {
			return ((OJMethod) first).getDeclaringClass();
		}		
	}
	
	private OJClass compositeExpressionTargetType(Object[] elem) {
		Object second = elem[1];
		if (second instanceof OJField) {
			return ((OJField) second).getType();
		}
		else {
			return ((OJMethod) second).getReturnType();
		}		
	}
	
	private OJClass compositeExpressionMiddleType(Object[] elem) {
		Object second = elem[1];
		if (second instanceof OJField) {
			return ((OJField) second).getDeclaringClass();
		}
		else {
			return ((OJMethod) second).getDeclaringClass();
		}		
	}
	
	private int getMutationsLeft(NonLeaf orig) {
		int mutationsLeft = -1;
		if (comp_unit != null) {
			parent = orig.getMutationLimitParent();
			if (parent != null && ((NonLeaf) parent).getComment()!=null) {
				try {
					String mutations = ((NonLeaf) parent).getComment().substring(14);
					mutationsLeft = Integer.parseInt(mutations);
				}
				catch (Exception e) {
					// comment is not an integer. Do nothing.
				}
			}
		}
		return mutationsLeft;
	}
	
	
	private void binaryVisit(NonLeaf orig, Expression e1, Expression e2, boolean lor) throws ParseTreeException {
		// special case for rexp == null
		if ((e1 instanceof Variable || e1 instanceof FieldAccess)
				&& (e2 instanceof Literal && ((Literal) e2).toString()=="null")) {
			getAccessibleVariables();
			OJClass type = getType(!lor?e1:e2);
			// Now modify rhs of assignment with "compatible" atomic expressions
			for (Variable v: variables) {
					// replace right hand side only if x is not replaced by x
					OJClass var_type = getType(v);
					if ((!lor && compatibleAssignType(type, var_type)) || (lor && compatibleAssignType(var_type, null))) {
						if (!lor) outputToFile(orig, v.toString());
						else outputToFileLeft(orig, v.toString());
						
					}
			}
			return;
			
		}
				
		// if rexp is not null ...
		if ((e1 instanceof Variable || e1 instanceof FieldAccess)
				&& (e2 instanceof Variable || e2 instanceof FieldAccess || e2 instanceof MethodCall)) {

			// getAccessibleVariables has to be called to compute atomic and composite expressions
			getAccessibleVariables();
			computeCompositeExpressions();

			OJClass type = getType(!lor?e1:e2);

			// case 1: rexp is a Variable
			// Either change it by another var, or by null, or extend it by one
			if ((!lor && e2 instanceof Variable) || (lor && e1 instanceof Variable)) {
				// Modifying rhs of assignment with atomic variables

				// rhs is a variable, then it has size 1. Reduce it to size 0 (null)
				if (!lor && compatibleAssignType(getType(e1), null)) outputToFile(orig, "null");

				// Now modify rhs of assignment with "compatible" atomic expressions
				for (Variable v: variables) {
					if (!(v.toString().equals(!lor?e2.toString():e1.toString()))) {
						// replace right hand side only if x is not replaced by x
						OJClass var_type = getType(v);

						if ((!lor && compatibleAssignType(type, var_type)) || (lor && compatibleAssignType(var_type, type))) {
							if (!lor) outputToFile(orig, v.toString());
							else outputToFileLeft(orig, v.toString());
						}
					}					
				}

				// finally, modify rhs of assignment with rhs.x, with x a field/var of rhs
				for (Object[] tuple: compositeValidExpressions) {
					Object first = tuple[0];
					Object second = tuple[1];
					if (first instanceof Variable && ((Variable) first).toString().equals(!lor?e2.toString():e1.toString())) {
						// replace right hand side with composite var
						// only of the former is included in the latter
						// and the type is OK.
						OJClass mtype = null;
						if (second instanceof Variable) {
							mtype = getType((Variable)second);
						} else if (second instanceof OJField) {
							mtype = ((OJField)second).getType();
						} else if (second instanceof OJMethod) {
							mtype = !lor?((OJMethod)second).getReturnType():null;
						}
						OJClass otype = getType(!lor?e1:e2);
						if (mtype != null && ((!lor && compatibleAssignType(otype, mtype)) || (lor && compatibleAssignType(mtype, otype)))) {
							
							String s = compositeExpressionToString(tuple);
							if (!lor) outputToFile(orig, s);
							else outputToFileLeft(orig, s);
						}
					}
				}
				
				//The expression could also be replaced by a method call
				//only when mutating right hand side of an AssignmentExpression
				if (!lor) {
					OJClass clazz = getSelfType();
					for (OJMethod m : methods) {
						if (compatibleAssignType(m.getDeclaringClass(),clazz) && m.getParameterTypes().length == 0 && compatibleAssignType(getType(e1), m.getReturnType())) {
							outputToFile(orig, m.getName()+"()");
						}
					}
				}

			}

			// Case 2: rexp is a field access (i.e., size >=2), or a method call of size >=2
			// Remove all "chains" of size 1, in every possible place in the expression
			if (((!lor?e2:e1) instanceof FieldAccess || ((!lor?e2:e1) instanceof MethodCall && (((MethodCall) (!lor?e2:e1)).getReferenceExpr()!=null)))) {
				String suffix = "";
				Expression current = !lor?e2:e1;
				while (current instanceof FieldAccess || ((current instanceof MethodCall && (((MethodCall) current).getReferenceExpr()!=null)))) {
					if (current instanceof FieldAccess) {
						FieldAccess faRexp = (FieldAccess) current;
						OJClass rexpType = getType(current); // "return" type, type of the expression
						Expression rexpRefExp = faRexp.getReferenceExpr();
						OJClass rexpRefType = getType(rexpRefExp);
						boolean isEndedByMethod = rexpRefExp instanceof MethodCall;

						// Shrink expression by one, if types are compatible 
						if( rexpType.equals(rexpRefType) && !rexpType.isPrimitive() && !rexpType.isPrimitiveWrapper()){
							if (suffix=="") { 
								if (!lor) outputToFile(orig, rexpRefExp.toString());
								else if (!isEndedByMethod) outputToFileLeft(orig, rexpRefExp.toString());
							}
							else {
								if (!lor) outputToFile(orig, rexpRefExp.toString()+"."+suffix);
								else if (!isEndedByMethod) outputToFileLeft(orig, rexpRefExp.toString()+"."+suffix);
							}
						}
						if (suffix=="") {
							suffix = faRexp.getName();
						}
						else {
							suffix = faRexp.getName()+"."+suffix;
						}
						current = rexpRefExp;
					}
					if ((current instanceof MethodCall && (((MethodCall) current).getReferenceExpr()!=null))) {
						MethodCall mcRexp = (MethodCall) current;
						if (mcRexp.getArguments().size()==0) {
							OJClass rexpType = getType(current); // "return" type, type of the expression
							Expression rexpRefExp = mcRexp.getReferenceExpr();
							OJClass rexpRefType = getType(rexpRefExp);
							boolean isEndedByMethod = rexpRefExp instanceof FieldAccess;

							// Shrink expression by one, if types are compatible 
							if( rexpType.equals(rexpRefType) && !rexpType.isPrimitive() && !rexpType.isPrimitiveWrapper()){
								if (suffix=="") { 
									if (!lor) outputToFile(orig, rexpRefExp.toString());
									else if (!isEndedByMethod) outputToFileLeft(orig, rexpRefExp.toString());
								}
								else {
									if (!lor) outputToFile(orig, rexpRefExp.toString()+"."+suffix);
									else if (!isEndedByMethod) outputToFileLeft(orig, rexpRefExp.toString()+"."+suffix);
								}
							}
							if (suffix=="") {
								suffix = mcRexp.getName()+"()";
							}
							else {
								suffix = mcRexp.getName()+"()."+suffix;
							}
							current = rexpRefExp;
						}
						else {
							// if method has parameters, it's never dropped!
							// methods with parameters are less likely to be there by mistake
							if (suffix=="") {
								suffix = mcRexp.getName()+"("+mcRexp.getArguments().toString()+")";
							}
							else {
								suffix = mcRexp.getName()+"("+mcRexp.getArguments().toString()+")."+suffix;
							}
							current = mcRexp.getReferenceExpr();
						}

					}
				}
			}

			// Case 3: rexp is a field access (i.e., size >=2), or a method call of size >=2
			// Replace "chains" of size 2 by compatible expressions of size 1
			if (((!lor?e2:e1) instanceof FieldAccess || ((!lor?e2:e1) instanceof MethodCall && (((MethodCall) (!lor?e2:e1)).getReferenceExpr()!=null)))) {
				String suffix = "";
				Expression second = !lor?e2:e1;
				Expression first;
				if (second instanceof FieldAccess) {
					first = ((FieldAccess) second).getReferenceExpr();
				}
				else {
					first = ((MethodCall) second).getReferenceExpr();
				}
				while (first!=null) {
					OJClass sourceType;
					if (first instanceof FieldAccess) {
						sourceType = getType(((FieldAccess) first).getReferenceExpr());
					}
					else if (first instanceof MethodCall) {
						sourceType = getType(((MethodCall) first).getReferenceExpr());
					} else {
						sourceType = getSelfType();
					}

					OJClass targetType = getType(second);

					OJClass middleType = getType(first);

					if (!middleType.equals(sourceType) && !middleType.equals(targetType)) {

						if (sourceType==getSelfType()) {
							for (OJField candidate: getSelfType().getDeclaredFields()) {
								OJClass var_ret_type = candidate.getType();
								if (targetType.equals(var_ret_type)) {
									if (suffix=="") {
										if (!lor) outputToFile(orig, candidate.getName());
										else outputToFileLeft(orig, candidate.getName());
									}
									else {
										if (!lor) outputToFile(orig, candidate.getName()+"."+suffix);
										else outputToFileLeft(orig, candidate.getName()+"."+suffix);
									}								
								}								
							}							
						}
						for (OJField candidate: fields) {
							OJClass var_ret_type = candidate.getType();							
							OJClass var_app_type = candidate.getDeclaringClass();
							if (sourceType.equals(var_app_type) && ((!lor && (compatibleAssignType(getType(e1),var_ret_type)))||(lor && (compatibleAssignType(var_ret_type, getType(e2)))))){//targetType.equals(var_ret_type)) {
								String prevS;
								if (first instanceof FieldAccess) {
									Expression prev = ((FieldAccess) first).getReferenceExpr();
									prevS = prev.toString();
								}	
								else if (first instanceof MethodCall) {
									Expression prev = ((MethodCall) first).getReferenceExpr();
									prevS = prev.toString();
								}
								else {
									prevS = "";
								}
								
								if (suffix=="") {
									if (prevS=="") {
										if (!lor) outputToFile(orig, candidate.getName());
										else outputToFileLeft(orig, candidate.getName());

									}
									else {
										if (!lor) outputToFile(orig, prevS+"."+candidate.getName());
										else outputToFileLeft(orig, prevS+"."+candidate.getName());
									}
								}
								else {
									if (prevS=="") {
										if (!lor) outputToFile(orig, candidate.getName()+"."+suffix);
										else outputToFileLeft(orig, candidate.getName()+"."+suffix);
									}
									else {
										if (!lor) outputToFile(orig, prevS+"."+candidate.getName()+"."+suffix);
										else outputToFileLeft(orig, prevS+"."+candidate.getName()+"."+suffix);	
									}
								}								
								
							}							
																					
						}
						for (OJMethod candidate: methods) {
							OJClass var_ret_type = candidate.getReturnType();							
							OJClass var_app_type = candidate.getDeclaringClass();
							if (sourceType.equals(var_app_type) && targetType.equals(var_ret_type)) {
								String prevS;
								if (first instanceof FieldAccess) {
									Expression prev = ((FieldAccess) first).getReferenceExpr();
									prevS = prev.toString();
								}	
								else if (first instanceof MethodCall) {
									Expression prev = ((MethodCall) first).getReferenceExpr();
									prevS = prev.toString();
								}
								else {
									prevS = "";
								}

								if (suffix=="" && !lor) {
									if (prevS=="") {
										outputToFile(orig, candidate.getName()+"()");
									}
									else {
										outputToFile(orig, prevS+"."+candidate.getName()+"()");
									}
								}
								else {
									if (prevS=="") {
										if (!lor) outputToFile(orig, candidate.getName()+"()."+suffix);
										else outputToFileLeft(orig, candidate.getName()+"()."+suffix);
									}
									else {
										if (!lor) outputToFile(orig, prevS+"."+candidate.getName()+"()."+suffix);
										else outputToFileLeft(orig, prevS+"."+candidate.getName()+"()."+suffix);
									}
								}								
																					
							}	
						}

					}

					if (second instanceof FieldAccess) {
						if (suffix=="") {
							suffix = ((FieldAccess) second).getName();
						}
						else {
							suffix = ((FieldAccess) second).getName()+"."+suffix;
						}
					}
					else {
						if (suffix=="") {
							suffix = ((MethodCall) second).getName()+"("+((MethodCall) second).getArguments().toString()+")";
						}
						else {
							suffix = ((MethodCall) second).getName()+"("+((MethodCall) second).getArguments().toString()+")."+suffix;
						}
					}

					second = first;
					if (first instanceof FieldAccess) {
						first = ((FieldAccess) first).getReferenceExpr();
					}	
					else if (first instanceof MethodCall){
						first = ((MethodCall) first).getReferenceExpr();
					} else {
						first = null;
					}
				}
			}


			// Case 4: rexp is a field access (i.e., size >=2), or a method call of size >=2
			// replace expressions of size 1 by "chains" of size 2 of compatible types
			if (((!lor?e2:e1) instanceof FieldAccess || ((!lor?e2:e1) instanceof MethodCall && (((MethodCall) (!lor?e2:e1)).getReferenceExpr()!=null)))) {
				String suffix = "";
				Expression current = !lor?e2:e1;
				Expression target = null;
				OJClass targetType = null;
				boolean typeOk = true;
				while (current != null) {
					OJClass sourceType;
					OJClass otype = getType(!lor?e1:e2);
					if (current instanceof FieldAccess) {
						sourceType = getType(((FieldAccess) current).getReferenceExpr());
					}
					else if (current instanceof MethodCall) {
						sourceType = getType(((MethodCall) current).getReferenceExpr());
					} else {
						sourceType = getSelfType();
					}

					//OJClass targetType = getType(current);
					

					for (Object[] elem: compositeValidExpressions) {
						
						if (lor && !(elem[1] instanceof OJField)) {
							continue;
						}
						
						
						OJClass var_ret_type = compositeExpressionTargetType(elem);							
						OJClass var_app_type = compositeExpressionSourceType(elem);
						if (target != null) {
							typeOk = targetType.equals(var_app_type);
						}
						

						if (sourceType.equals(var_app_type) && typeOk && ((!lor && compatibleAssignType(otype, var_ret_type)) || (lor && compatibleAssignType(var_ret_type, otype)))){//targetType.equals(var_ret_type)) {
							String prevS;
							if (current instanceof FieldAccess) {
								Expression prev = ((FieldAccess) current).getReferenceExpr();
								prevS = prev.toString();
							}	
							else if (current instanceof MethodCall) {
								Expression prev = ((MethodCall) current).getReferenceExpr();
								prevS = prev.toString();
							}
							else {
								prevS = "";
							}
							
							String s = compositeExpressionToString(elem);
							if (suffix=="") {
								if (prevS=="") {
									if (!lor) outputToFile(orig, s);
									else outputToFileLeft(orig, s);
								}
								else {
									if (!lor) outputToFile(orig, prevS+"."+s);
									else outputToFileLeft(orig, prevS+"."+s);
								}
							}
							else {
								if (prevS=="") {
									if (!lor) outputToFile(orig, s+"."+suffix);
									else outputToFileLeft(orig, s+"."+suffix);
								}
								else {
									if (!lor) outputToFile(orig, prevS+"."+s+"."+suffix);
									else outputToFileLeft(orig, prevS+"."+s+"."+suffix);
								}
							}								
						}

					}
						
					if (current instanceof FieldAccess) {
						if (suffix=="") {
							suffix = ((FieldAccess) current).getName();
						}
						else {
							suffix = ((FieldAccess) current).getName()+"."+suffix;
						}
					}
					else if (!lor && current instanceof MethodCall) {
						if (suffix=="") {
							suffix = ((MethodCall) current).getName()+"("+((MethodCall) current).getArguments().toString()+")";
						}
						else {
							suffix = ((MethodCall) current).getName()+"("+((MethodCall) current).getArguments().toString()+")."+suffix;
						}
					} else if (current instanceof Variable) {
						if (suffix=="") {
							suffix = ((Variable) current).toString();
						}
						else {
							suffix = ((Variable) current).toString()+"."+suffix;
						}						
					} else {
						// current instance of SelfAccess
						if (suffix=="") {
							suffix = "this";
						}
						else {
							suffix = "this."+suffix;
						}
					}

					if (current instanceof FieldAccess) {
						target = current;
						if (target instanceof FieldAccess) {
							targetType = getType(((FieldAccess)target).getReferenceExpr());
						} else if (target instanceof MethodCall) {
							targetType = getType(((MethodCall)target).getReferenceExpr());
						}
						current = ((FieldAccess) current).getReferenceExpr();
					}	
					else if (!lor && current instanceof MethodCall) {
						target = current;
						if (target instanceof FieldAccess) {
							targetType = getType(((FieldAccess)target).getReferenceExpr());
						} else if (target instanceof MethodCall) {
							targetType = getType(((MethodCall)target).getReferenceExpr());
						}
						current = ((MethodCall) current).getReferenceExpr();
					} else {
						current = null;
					}
					
					
				}


			}
			
			// Case 5: Replace "size 1" fields or method calls by "size 1" elements of the respective types
			if (((!lor?e2:e1) instanceof FieldAccess || ((!lor?e2:e1) instanceof MethodCall && (((MethodCall) (!lor?e2:e1)).getReferenceExpr()!=null)))) {
				String suffix = "";
				Expression current = !lor?e2:e1;
				while (current != null) {
					OJClass sourceType;
					if (current instanceof FieldAccess) {
						sourceType = getType(((FieldAccess) current).getReferenceExpr());
					}
					else if (!lor && current instanceof MethodCall) {
						sourceType = getType(((MethodCall) current).getReferenceExpr());
					} else {
						sourceType = getSelfType();
					}

					OJClass targetType = getType(current);
					
					if (sourceType != null && sourceType == getSelfType()) {
						
						for (OJField candidate_i: sourceType.getDeclaredFields()) {
							if (current instanceof FieldAccess && (((FieldAccess)current).getName().toString().compareTo(candidate_i.getName().toString())==0)) {
								continue;
							}
							OJClass var_ret_type = candidate_i.getType();							
							if (var_ret_type.equals(targetType)) {
								if (suffix=="") {
									if (!lor) outputToFile(orig, candidate_i.getName());
									else outputToFileLeft(orig, candidate_i.getName());
								}
								else {
									if (!lor) outputToFile(orig, candidate_i.getName()+"."+suffix);
									else outputToFileLeft(orig, candidate_i.getName()+"."+suffix);
								}								
							}							
						}

						for (Variable v: variables) {
							OJClass var_ret_type = getType(v);							
							if (var_ret_type.equals(targetType)) {
								if (suffix=="") {
									if (!lor) outputToFile(orig, v.toString());
									else outputToFileLeft(orig, v.toString());
								}
								else {
									if (!lor) outputToFile(orig, v.toString()+"."+suffix);
									else outputToFileLeft(orig, v.toString()+"."+suffix);
								}								
							}							
						}

						
					}
					for (OJField f: fields) {
						if (current instanceof FieldAccess && f.getName().compareTo(((FieldAccess)current).getName().toString()) == 0) {
							continue;
						} else if (current instanceof Variable && f.getName().compareTo(current.toString()) == 0) {
							continue;
						}
						
						OJClass var_ret_type = f.getType();							
						OJClass var_app_type = f.getDeclaringClass();
						if (!(current instanceof Variable) && var_app_type.equals(sourceType) && ((!lor && compatibleAssignType(getType(e1),var_ret_type)) || (lor && compatibleAssignType(var_ret_type,getType(e2))))) {
						//if (!(current instanceof Variable) && var_ret_type.equals(targetType) && var_app_type.equals(sourceType)) {
							Expression prev;
							if (current instanceof FieldAccess) {
								prev = ((FieldAccess) current).getReferenceExpr();
							}	
							else {
								prev = ((MethodCall) current).getReferenceExpr();
							}
							if (suffix=="") {
								if (!lor) outputToFile(orig, prev.toString()+"."+f.getName());
								else outputToFileLeft(orig, prev.toString()+"."+f.getName());
							}
							else {
								if (!lor) outputToFile(orig, prev.toString()+"."+f.getName()+"."+suffix);
								else outputToFileLeft(orig, prev.toString()+"."+f.getName()+"."+suffix);
							}								
						}
						
					}
						for (OJMethod candidate_i: methods) {
							OJClass var_ret_type = candidate_i.getReturnType();							
							OJClass var_app_type = candidate_i.getDeclaringClass();
							if (var_ret_type.equals(targetType) && var_app_type.equals(sourceType)) {
								Expression prev;
								if (current instanceof FieldAccess) {
									prev = ((FieldAccess) current).getReferenceExpr();
								}	
								else if (current instanceof MethodCall){
									prev = ((MethodCall) current).getReferenceExpr();
								} else {
									// current is a Variable
									prev = null;
								}
								if (suffix=="" && !lor) {
									if (prev!=null) {
										outputToFile(orig, prev.toString()+"."+candidate_i.getName()+"()");
									}
									else {
										outputToFile(orig, candidate_i.getName()+"()");
									}
								}
								else {
									if (prev!=null) {
										if (!lor) outputToFile(orig, prev.toString()+"."+candidate_i.getName()+"()."+suffix);
										else if (suffix != "") outputToFileLeft(orig, prev.toString()+"."+candidate_i.getName()+"()."+suffix);
									}
									else {
										if (!lor) outputToFile(orig, candidate_i.getName()+"()."+suffix);
										else if (suffix != "") outputToFileLeft(orig, candidate_i.getName()+"()."+suffix);
									}
								}								
							}						
						}

					if (current instanceof FieldAccess) {
						if (suffix=="") {
							suffix = ((FieldAccess) current).getName();
						}
						else {
							suffix = ((FieldAccess) current).getName()+"."+suffix;
						}
					}
					else if (!lor && current instanceof MethodCall) {
						if (suffix=="") {
							suffix = ((MethodCall) current).getName()+"("+((MethodCall) current).getArguments().toString()+")";
						}
						else {
							suffix = ((MethodCall) current).getName()+"("+((MethodCall) current).getArguments().toString()+")."+suffix;
						}
					} else {
						if (suffix=="") {
							suffix = ((Variable) current).toString();
						}
						else if (current instanceof Variable) {
							suffix = ((Variable) current).toString()+"."+suffix;
						}
						else {
							// current instanceof SelfAccess
							if (suffix=="") {
								suffix = "this";
							}
							else {
								suffix = "this."+suffix;
							}
						}
					}

					if (current instanceof FieldAccess) {
						current = ((FieldAccess) current).getReferenceExpr();
					}	
					else if (current instanceof MethodCall) {
						current = ((MethodCall) current).getReferenceExpr();
					} else {
						current = null;
					}
				}
			}
		}		

	}
	
	private void outputToFileLeft(NonLeaf original, String mutant) {
		//System.out.println("original : "+original.toString()+"|mutación : "+mutant+'\n');
		if (original instanceof AssignmentExpression)
			outputToFileLeft((AssignmentExpression) original, mutant);
		else if (original instanceof BinaryExpression)
			outputToFileLeft((BinaryExpression) original, mutant);
	}

	public void visit(AssignmentExpression p) throws ParseTreeException {
		if (getMutationsLeft(p) > 0) {
			Expression lexp = p.getLeft();
			Expression rexp = p.getRight();
			
			binaryVisit(p, lexp,rexp, false);
			binaryVisit(p, lexp, rexp, true);
		}
	}

	public void visit(BinaryExpression p) throws ParseTreeException {

		Expression lexp = p.getLeft();
		lexp.accept(this);
		Expression rexp = p.getRight();
		rexp.accept(this);

	}

	
	private void unaryVisit(NonLeaf p, Expression rexp) throws ParseTreeException {
		if (rexp==null) throw new IllegalArgumentException("rexp is null in PRVO.unaryVisit invokation.");		
		
		Variable var = null;
		OJClass type = null;

//		if (p instanceof VariableDeclaration) 
//			var = new Variable(((VariableDeclaration) p).getVariable());
//		else 
		if (p instanceof VariableDeclarator) {
			var = new Variable(((VariableDeclarator) p).getVariable());
		 	type = getType(var);
		}
		
//		try {
//			if (p instanceof VariableDeclaration) {
//				getEnvironment().bindVariable(var.toString(), OJClass.forName(((VariableDeclaration) p).getTypeSpecifier().toString()));
//				type = getType(var);
//			}
//		} catch (OJClassNotFoundException e) {
//			System.err.println("Couldn't bind variable " + var);
//		}

		if (p instanceof ReturnStatement) {
			try {
				type = ((ReturnStatement) p).getExpression().getType(getEnvironment());
			}
			catch (Exception e) {
				throw new ParseTreeException("PRVO.unaryVisit: problem calculating type of return statement expression");
			}
		}
		
		
		// Do nothing if variable is of primitive type (handled by PRVV)
		if (type.isPrimitive()) return;
		
		// THIS IS COPIED FROM THE CODE OF VISIT FOR ASSIGNMENT
		// special case for rexp == null
		if ((rexp instanceof Literal && ((Literal) rexp).toString()=="null")) {
			getAccessibleVariables();
			// Now modify rhs of assignment with "compatible" atomic expressions
			for (Variable v: variables) {
					// replace right hand side only if x is not replaced by x
					OJClass var_type = getType(v);

					if (compatibleAssignType(type, var_type)) {
						outputToFile(p, v.toString());
					}
			}
			//The expression could also be replaced by a method call
			//only when mutating right hand side of an AssignmentExpression
			
			OJClass clazz = getSelfType();
			for (OJMethod m : methods) {
				if (compatibleAssignType(m.getDeclaringClass(),clazz) && m.getParameterTypes().length == 0 && compatibleAssignType(type, m.getReturnType())) {
					outputToFile(p, m.getName()+"()");
				}
			}
			return;
			
		}
				
		// if rexp is not null ...
		if (rexp instanceof Variable || rexp instanceof FieldAccess || rexp instanceof MethodCall) {

			// getAccessibleVariables has to be called to compute atomic and composite expressions
			getAccessibleVariables();
			computeCompositeExpressions();

			// case 1: rexp is a Variable
			// Either change it by another var, or by null, or extend it by one
			if (rexp instanceof Variable) {
				// Modifying rhs of assignment with atomic variables

				// rhs is a variable, then it has size 1. Reduce it to size 0 (null)
				outputToFile(p, "null");

				// Now modify rhs of assignment with "compatible" atomic expressions
				for (Variable v: variables) {
					if (!(v.toString().equals(rexp.toString()))) {
						// replace right hand side only if x is not replaced by x
						OJClass var_type = getType(v);

						if (compatibleAssignType(type, var_type)) {
							outputToFile(p, v.toString());
						}
					}					
				}
				
				OJClass clazz = getSelfType();
				for (OJMethod m : methods) {
					if (compatibleAssignType(m.getDeclaringClass(),clazz) && m.getParameterTypes().length == 0 && compatibleAssignType(type, m.getReturnType())) {
						outputToFile(p, m.getName()+"()");
					}
				}

				// finally, modify rhs of assignment with rhs.x, with x a field/var of rhs
				for (Object[] tuple: compositeValidExpressions) {
					Object first = tuple[0];
					if (first instanceof Variable && ((Variable) first).toString().equals(rexp.toString())) {
						// replace right hand side with composite var
						// only of the former is included in the latter
						// and the type is OK.
						if (tuple[1] instanceof FieldAccess) {
							if (!compatibleAssignType(type, getType((FieldAccess)tuple[1]))) {
								continue;
							}
						} else if (tuple[1] instanceof MethodCall) {
							if (!(compatibleAssignType(type, getType((MethodCall)tuple[1])) && ((MethodCall)tuple[1]).getArguments().isEmpty())) {
								continue;
							}
						}
						String s = compositeExpressionToString(tuple);
						outputToFile(p, s);						
					}
				}

			}

			// Case 2: rexp is a field access (i.e., size >=2), or a method call of size >=2
			// Remove all "chains" of size 1, in every possible place in the expression
			if (rexp instanceof FieldAccess || (rexp instanceof MethodCall && (((MethodCall) rexp).getReferenceExpr()!=null))) {
				String suffix = "";
				Expression current = (Expression) rexp;
				while (current instanceof FieldAccess || (current instanceof MethodCall && (((MethodCall) current).getReferenceExpr()!=null))) {
					if (current instanceof FieldAccess) {
						FieldAccess faRexp = (FieldAccess) current;
						OJClass rexpType = getType(current); // "return" type, type of the expression
						Expression rexpRefExp = faRexp.getReferenceExpr();
						OJClass rexpRefType = getType(rexpRefExp);

						// Shrink expression by one, if types are compatible 
						if( rexpType.equals(rexpRefType) && !rexpType.isPrimitive() && !rexpType.isPrimitiveWrapper()){
							if (suffix=="") { 
								outputToFile(p, rexpRefExp.toString());
							}
							else {
								outputToFile(p, rexpRefExp.toString()+"."+suffix);
							}
						}
						if (suffix=="") {
							suffix = faRexp.getName();
						}
						else {
							suffix = faRexp.getName()+"."+suffix;
						}
						current = rexpRefExp;
					}
					if (current instanceof MethodCall && (((MethodCall) current).getReferenceExpr()!=null)) {
						MethodCall mcRexp = (MethodCall) current;
						if (mcRexp.getArguments().size()==0) {
							OJClass rexpType = getType(current); // "return" type, type of the expression
							Expression rexpRefExp = mcRexp.getReferenceExpr();
							OJClass rexpRefType = getType(rexpRefExp);

							// Shrink expression by one, if types are compatible 
							if( rexpType.equals(rexpRefType) && !rexpType.isPrimitive() && !rexpType.isPrimitiveWrapper()){
								if (suffix=="") { 
									outputToFile(p, rexpRefExp.toString());
								}
								else {
									outputToFile(p, rexpRefExp.toString()+"."+suffix);
								}
							}
							if (suffix=="") {
								suffix = mcRexp.getName()+"()";
							}
							else {
								suffix = mcRexp.getName()+"()."+suffix;
							}
							current = rexpRefExp;
						}
						else {
							// if method has parameters, it's never dropped!
							// methods with parameters are less likely to be there by mistake
							if (suffix=="") {
								suffix = mcRexp.getName()+"("+mcRexp.getArguments().toString()+")";
							}
							else {
								suffix = mcRexp.getName()+"("+mcRexp.getArguments().toString()+")."+suffix;
							}
							current = mcRexp.getReferenceExpr();
						}

					}
				}
			}

			// Case 3: rexp is a field access (i.e., size >=2), or a method call of size >=2
			// Replace "chains" of size 2 by compatible expressions of size 1
			if (rexp instanceof FieldAccess || (rexp instanceof MethodCall && (((MethodCall) rexp).getReferenceExpr()!=null))) {
				String suffix = "";
				Expression second = (Expression) rexp;
				Expression first;
				if (second instanceof FieldAccess) {
					first = ((FieldAccess) second).getReferenceExpr();
				}
				else {
					first = ((MethodCall) second).getReferenceExpr();
				}
				while (first!=null) {
					OJClass sourceType;
					if (first instanceof FieldAccess) {
						sourceType = getType(((FieldAccess) first).getReferenceExpr());
					}
					else if (first instanceof MethodCall) {
						sourceType = getType(((MethodCall) first).getReferenceExpr());
					} else {
						sourceType = getSelfType();
					}

					OJClass targetType = getType(second);

					OJClass middleType = getType(first);

					if (!middleType.equals(sourceType) && !middleType.equals(targetType)) {

						if (sourceType==getSelfType()) {
							for (OJField candidate: getSelfType().getDeclaredFields()) {
								OJClass var_ret_type = candidate.getType();
								if (targetType.equals(var_ret_type)) {
									if (suffix=="") {
										outputToFile(p, candidate.getName());
									}
									else {
										outputToFile(p, candidate.getName()+"."+suffix);									
									}								
								}								
							}							
						}
						for (OJField candidate: fields) {
							OJClass var_ret_type = candidate.getType();							
							OJClass var_app_type = candidate.getDeclaringClass();
							if (sourceType.equals(var_app_type) && targetType.equals(var_ret_type)) {
								String prevS;
								if (first instanceof FieldAccess) {
									Expression prev = ((FieldAccess) first).getReferenceExpr();
									prevS = prev.toString();
								}	
								else if (first instanceof MethodCall) {
									Expression prev = ((MethodCall) first).getReferenceExpr();
									prevS = prev.toString();
								}
								else {
									prevS = "";
								}
								
								if (suffix=="") {
									if (prevS=="") {
										outputToFile(p, candidate.getName());

									}
									else {
										outputToFile(p, prevS+"."+candidate.getName());
									}
								}
								else {
									if (prevS=="") {
										outputToFile(p, candidate.getName()+"."+suffix);									
									}
									else {
										outputToFile(p, prevS+"."+candidate.getName()+"."+suffix);									
									}
								}								
								
							}							
																					
						}
						for (OJMethod candidate: methods) {
							OJClass var_ret_type = candidate.getReturnType();							
							OJClass var_app_type = candidate.getDeclaringClass();
							if (sourceType.equals(var_app_type) && targetType.equals(var_ret_type)) {
								String prevS;
								if (first instanceof FieldAccess) {
									Expression prev = ((FieldAccess) first).getReferenceExpr();
									prevS = prev.toString();
								}	
								else if (first instanceof MethodCall) {
									Expression prev = ((MethodCall) first).getReferenceExpr();
									prevS = prev.toString();
								}
								else {
									prevS = "";
								}

								if (suffix=="") {
									if (prevS=="") {
										outputToFile(p, candidate.getName()+"()");
									}
									else {
										outputToFile(p, prevS+"."+candidate.getName()+"()");
									}
								}
								else {
									if (prevS=="") {
										outputToFile(p, candidate.getName()+"()."+suffix);									
									}
									else {
										outputToFile(p, prevS+"."+candidate.getName()+"()."+suffix);									
									}
								}								
																					
							}
							
						}

					}

					if (second instanceof FieldAccess) {
						if (suffix=="") {
							suffix = ((FieldAccess) second).getName();
						}
						else {
							suffix = ((FieldAccess) second).getName()+"."+suffix;
						}
					}
					else {
						if (suffix=="") {
							suffix = ((MethodCall) second).getName()+"("+((MethodCall) second).getArguments().toString()+")";
						}
						else {
							suffix = ((MethodCall) second).getName()+"("+((MethodCall) second).getArguments().toString()+")."+suffix;
						}
					}

					second = first;
					if (first instanceof FieldAccess) {
						first = ((FieldAccess) first).getReferenceExpr();
					}	
					else if (first instanceof MethodCall){
						first = ((MethodCall) first).getReferenceExpr();
					} else {
						first = null;
					}
				}
			}


			// Case 4: rexp is a field access (i.e., size >=2), or a method call of size >=2
			// replace expressions of size 1 by "chains" of size 2 of compatible types
			if (rexp instanceof FieldAccess || (rexp instanceof MethodCall && (((MethodCall) rexp).getReferenceExpr()!=null))) {
				String suffix = "";
				Expression current = (Expression) rexp;
				while (current != null) {
					OJClass sourceType;
					if (current instanceof FieldAccess) {
						sourceType = getType(((FieldAccess) current).getReferenceExpr());
					}
					else if (current instanceof MethodCall) {
						sourceType = getType(((MethodCall) current).getReferenceExpr());
					} else {
						sourceType = getSelfType();
					}

					OJClass targetType = getType(current);

					for (Object[] elem: compositeValidExpressions) {

						OJClass var_ret_type = compositeExpressionTargetType(elem);							
						OJClass var_app_type = compositeExpressionSourceType(elem);;

						if (sourceType.equals(var_app_type) && targetType.equals(var_ret_type)) {
							String prevS;
							if (current instanceof FieldAccess) {
								Expression prev = ((FieldAccess) current).getReferenceExpr();
								prevS = prev.toString();
							}	
							else if (current instanceof MethodCall) {
								Expression prev = ((MethodCall) current).getReferenceExpr();
								prevS = prev.toString();
							}
							else {
								prevS = "";
							}
							
							String s = compositeExpressionToString(elem);
							if (suffix=="") {
								if (prevS=="") {
									outputToFile(p, s);
								}
								else {
									outputToFile(p, prevS+"."+s);
								}
							}
							else {
								if (prevS=="") {
									outputToFile(p, s+"."+suffix);
								}
								else {
									outputToFile(p, prevS+"."+s+"."+suffix);
								}
							}								
						}

					}
						
					if (current instanceof FieldAccess) {
						if (suffix=="") {
							suffix = ((FieldAccess) current).getName();
						}
						else {
							suffix = ((FieldAccess) current).getName()+"."+suffix;
						}
					}
					else if (current instanceof MethodCall) {
						if (suffix=="") {
							suffix = ((MethodCall) current).getName()+"("+((MethodCall) current).getArguments().toString()+")";
						}
						else {
							suffix = ((MethodCall) current).getName()+"("+((MethodCall) current).getArguments().toString()+")."+suffix;
						}
					} else if (current instanceof Variable) {
						if (suffix=="") {
							suffix = ((Variable) current).toString();
						}
						else {
							suffix = ((Variable) current).toString()+"."+suffix;
						}						
					} else {
						// current instance of SelfAccess
						if (suffix=="") {
							suffix = "this";
						}
						else {
							suffix = "this."+suffix;
						}
					}

					if (current instanceof FieldAccess) {
						current = ((FieldAccess) current).getReferenceExpr();
					}	
					else if (current instanceof MethodCall) {
						current = ((MethodCall) current).getReferenceExpr();
					} else {
						current = null;
					}
					
					
				}


			}
			
			// Case 5: Replace "size 1" fields or method calls by "size 1" elements of the respective types
			if (rexp instanceof FieldAccess || (rexp instanceof MethodCall && (((MethodCall) rexp).getReferenceExpr()!=null))) {
				String suffix = "";
				Expression current = (Expression) rexp;
				while (current != null) {
					OJClass sourceType;
					if (current instanceof FieldAccess) {
						sourceType = getType(((FieldAccess) current).getReferenceExpr());
					}
					else if (current instanceof MethodCall) {
						sourceType = getType(((MethodCall) current).getReferenceExpr());
					} else {
						sourceType = getSelfType();
					}

					OJClass targetType = getType(current);
					
					if (sourceType == getSelfType()) {
						for (OJField candidate_i: sourceType.getDeclaredFields()) {
							OJClass var_ret_type = candidate_i.getType();							
							if (var_ret_type.equals(targetType)) {
								if (suffix=="") {
									outputToFile(p, candidate_i.getName());
								}
								else {
									outputToFile(p, candidate_i.getName()+"."+suffix);									
								}								
							}							
						}

						for (Variable v: variables) {
							OJClass var_ret_type = getType(v);							
							if (var_ret_type.equals(targetType)) {
								if (suffix=="") {
									outputToFile(p, v.toString());
								}
								else {
									outputToFile(p, v.toString()+"."+suffix);									
								}								
							}							
						}

						
					}
					
					for (OJField f: fields) {
						OJClass var_ret_type = f.getType();							
						OJClass var_app_type = f.getDeclaringClass();
						if (var_ret_type.equals(targetType) && var_app_type.equals(sourceType)) {
							Expression prev;
							if (current instanceof FieldAccess) {
								prev = ((FieldAccess) current).getReferenceExpr();
							}	
							else {
								prev = ((MethodCall) current).getReferenceExpr();
							}
							if (suffix=="") {
								outputToFile(p, prev.toString()+"."+f.getName());
							}
							else {
								outputToFile(p, prev.toString()+"."+f.getName()+"."+suffix);									
							}								
						}
						
					}

					for (OJMethod candidate_i: methods) {
						OJClass var_ret_type = candidate_i.getReturnType();							
						OJClass var_app_type = candidate_i.getDeclaringClass();
						if (var_ret_type.equals(targetType) && var_app_type.equals(sourceType)) {
							Expression prev;
							if (current instanceof FieldAccess) {
								prev = ((FieldAccess) current).getReferenceExpr();
							}	
							else if (current instanceof MethodCall){
								prev = ((MethodCall) current).getReferenceExpr();
							} else {
								// current is a Variable
								prev = null;
							}
							if (suffix=="") {
								if (prev!=null) {
									outputToFile(p, prev.toString()+"."+candidate_i.getName()+"()");
								}
								else {
									outputToFile(p, candidate_i.getName()+"()");
								}
							}
							else {
								if (prev!=null) {
									outputToFile(p, prev.toString()+"."+candidate_i.getName()+"()."+suffix);
								}
								else {
									outputToFile(p, candidate_i.getName()+"()."+suffix);
								}
							}								
						}						
					}

					if (current instanceof FieldAccess) {
						if (suffix=="") {
							suffix = ((FieldAccess) current).getName();
						}
						else {
							suffix = ((FieldAccess) current).getName()+"."+suffix;
						}
					}
					else if (current instanceof MethodCall) {
						if (suffix=="") {
							suffix = ((MethodCall) current).getName()+"("+((MethodCall) current).getArguments().toString()+")";
						}
						else {
							suffix = ((MethodCall) current).getName()+"("+((MethodCall) current).getArguments().toString()+")."+suffix;
						}
					} else {
						if (suffix=="") {
							suffix = ((Variable) current).toString();
						}
						else if (current instanceof Variable) {
							suffix = ((Variable) current).toString()+"."+suffix;
						}
						else {
							// current instanceof SelfAccess
							if (suffix=="") {
								suffix = "this";
							}
							else {
								suffix = "this."+suffix;
							}
						}
					}

					if (current instanceof FieldAccess) {
						current = ((FieldAccess) current).getReferenceExpr();
					}	
					else if (current instanceof MethodCall) {
						current = ((MethodCall) current).getReferenceExpr();
					} else {
						current = null;
					}
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
		if (getMutationsLeft(p) > 0) unaryVisit(p,rexp);
	}

	
//	public void visit(VariableDeclaration p) throws ParseTreeException {
//		Expression	rexp = (Expression) p.getInitializer();
//		
//				
//		if( rexp == null ){
//			super.visit(p);
//			return;
//		}
//		if (getMutationsLeft(p) > 0) unaryVisit(p,rexp);
//	}

	public void visit(VariableDeclarator p) throws ParseTreeException {
		Expression	rexp = (Expression) p.getInitializer();
		
		if( rexp == null ){
			super.visit(p);
			return;
		}
		if (getMutationsLeft(p) > 0) unaryVisit(p,rexp);
	}
			
	
	/**
	 * Determines whether two given types are compatible for assignment. 
	 * 
	 * @param p The type on the left side of the assignment
	 * @param c The type on the right side of the assignment
	 * @return true if the types are compatible, false otherwise
	 */
	private boolean compatibleAssignType(OJClass p, OJClass c){
		//FIXME: a primitive variable can't be assigned null
		if( p == null){// || c == null ){
			return false;
		}
		
		if (c == null) {
			return p.isPrimitive()?false:true;
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

//	public void outputToFile(VariableDeclaration original, String mutant){
////		if (comp_unit == null){
////			return;
////		}
////
////		parent = original.getMutationLimitParent();
////		if (parent != null && ((NonLeaf) parent).getComment()!=null) {
////			try {
////				String mutations = ((NonLeaf) parent).getComment().substring(14);
////				mutationsLeft = Integer.parseInt(mutations);
////			}
////			catch (Exception e) {
////				// comment is not an integer. Do nothing.
////			}
////		}
//		mutationsLeft = getMutationsLeft(original);
//
//		if (!(mutationsLeft>0)) return;
//		
//		if (Api.usingApi()) {
//			if (parent==null) return; //do not mutate if no mutation generation limit provided!
//			MutantsInformationHolder.mainHolder().addMutantIdentifier(
//					Mutant.PRVO, original, mutant, "Variable Declaration Right");
//			return;
//		}
//		
//		if (mutationsLeft>0) {
//			mutationsLeft--;
//			((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
//		}
//
//		String f_name;
//		num++;
//		f_name = getSourceName(this);
//		String mutant_dir = getMuantID();
//
//		try 
//		{
//			PrintWriter out = getPrintWriter(f_name);
//			PRV_Writer writer = new PRV_Writer( mutant_dir,out );
//			writer.setMutant(original,mutant);
//			comp_unit.accept( writer );
//			out.flush();  
//			out.close();
//		} catch ( IOException e ) {
//			System.err.println( "fails to create " + f_name );
//		} catch ( ParseTreeException e ) {
//			System.err.println( "errors during printing " + f_name );
//			e.printStackTrace();
//		}
//		
//		if (mutationsLeft>=0) {
//			mutationsLeft++;
//			((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
//		}
//	}


	public void outputToFile(VariableDeclarator original, String mutant){
//		if (comp_unit == null){
//			return;
//		}
//
//		parent = original.getMutationLimitParent();
//		if (parent != null && ((NonLeaf) parent).getComment()!=null) {
//			try {
//				String mutations = ((NonLeaf) parent).getComment().substring(14);
//				mutationsLeft = Integer.parseInt(mutations);
//			}
//			catch (Exception e) {
//				// comment is not an integer. Do nothing.
//			}
//		}
		
		mutationsLeft = getMutationsLeft(original);

		if (!(mutationsLeft>0)) return;
		
		if (Api.usingApi()) {
			if (parent==null) return; //do not mutate if no mutation generation limit provided!
			MutantsInformationHolder.mainHolder().addMutantIdentifier(
					Mutant.PRVO, original, mutant, "Variable Declarator Right");
			return;
		}
		
		if (mutationsLeft>0) {
			mutationsLeft--;
			((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
		}

		String f_name;
		num++;
		f_name = getSourceName(this);
		String mutant_dir = getMuantID();

		try 
		{
			PrintWriter out = getPrintWriter(f_name);
			PRV_Writer writer = new PRV_Writer( mutant_dir,out );
			writer.setMutant(original,mutant);
			comp_unit.accept( writer );
			out.flush();  
			out.close();
		} catch ( IOException e ) {
			System.err.println( "fails to create " + f_name );
		} catch ( ParseTreeException e ) {
			System.err.println( "errors during printing " + f_name );
			e.printStackTrace();
		}
		
		if (mutationsLeft>=0) {
			mutationsLeft++;
			((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
		}

	}


	public void outputToFile(ReturnStatement original, String mutant){
//		if (comp_unit == null){
//			return;
//		}
//
//		parent = original.getMutationLimitParent();
//		if (parent != null && ((NonLeaf) parent).getComment()!=null) {
//			try {
//				String mutations = ((NonLeaf) parent).getComment().substring(14);
//				mutationsLeft = Integer.parseInt(mutations);
//			}
//			catch (Exception e) {
//				// comment is not an integer. Do nothing.
//			}
//		}
		
		mutationsLeft = getMutationsLeft(original);

		if (!(mutationsLeft>0)) return;
		
		if (Api.usingApi()) {
			if (parent==null) return; //do not mutate if no mutation generation limit provided!
			MutantsInformationHolder.mainHolder().addMutantIdentifier(
					Mutant.PRVO, original, mutant, "Return Statement Right");
			return;
		}
		
		if (mutationsLeft>0) {
			mutationsLeft--;
			((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
		}

		String f_name;
		num++;
		f_name = getSourceName(this);
		String mutant_dir = getMuantID();

		try 
		{
			PrintWriter out = getPrintWriter(f_name);
			PRV_Writer writer = new PRV_Writer( mutant_dir,out );
			writer.setMutant(original,mutant);
			comp_unit.accept( writer );
			out.flush();  
			out.close();
		} catch ( IOException e ) {
			System.err.println( "fails to create " + f_name );
		} catch ( ParseTreeException e ) {
			System.err.println( "errors during printing " + f_name );
			e.printStackTrace();
		}
		
		if (mutationsLeft>=0) {
			mutationsLeft++;
			((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
		}

	}

	
	
	public void outputToFile(BinaryExpression original, String mutant){
//		if (comp_unit == null){
//			return;
//		}		
//
//		parent = original.getMutationLimitParent();
//		if (parent != null && ((NonLeaf) parent).getComment()!=null) {
//			try {
//				String mutations = ((NonLeaf) parent).getComment().substring(14);
//				mutationsLeft = Integer.parseInt(mutations);
//			}
//			catch (Exception e) {
//				// comment is not an integer. Do nothing.
//			}
//		}
		
		mutationsLeft = getMutationsLeft(original);

		if (!(mutationsLeft>0)) return;

		if (Api.usingApi()) {
			if (parent==null) return; //do not mutate if no mutation generation limit provided!
			MutantsInformationHolder.mainHolder().addMutantIdentifier(
					Mutant.PRVO, original, mutant, "Binary Expression Right");
			return;
		}
		
		if (mutationsLeft>0) {
			mutationsLeft--;
			((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
		}

		String f_name;
		num++;
		f_name = getSourceName(this);
		String mutant_dir = getMuantID();

		try 
		{
			PrintWriter out = getPrintWriter(f_name);
			PRV_Writer writer = new PRV_Writer( mutant_dir,out );
			writer.setMutant(original,mutant);
			comp_unit.accept( writer );
			out.flush();  
			out.close();
		} catch ( IOException e ) {
			System.err.println( "fails to create " + f_name );
		} catch ( ParseTreeException e ) {
			System.err.println( "errors during printing " + f_name );
			e.printStackTrace();
		}
		
		if (mutationsLeft>=0) {
			mutationsLeft++;
			((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
		}

	}

	public void outputToFileLeft(BinaryExpression original, String mutant){
//		if (comp_unit == null){
//			return;
//		}
//
//		parent = original.getMutationLimitParent();
//		if (parent != null && ((NonLeaf) parent).getComment()!=null) {
//			try {
//				String mutations = ((NonLeaf) parent).getComment().substring(14);
//				mutationsLeft = Integer.parseInt(mutations);
//			}
//			catch (Exception e) {
//				// comment is not an integer. Do nothing.
//			}
//		}
		
		mutationsLeft = getMutationsLeft(original);

		if (!(mutationsLeft>0)) return;

		if (Api.usingApi()) {
			if (parent==null) return; //do not mutate if no mutation generation limit provided!
			MutantsInformationHolder.mainHolder().addMutantIdentifier(
					Mutant.PRVO, original, mutant, "Binary Expression Left");
			return;
		}
		
		if (mutationsLeft>0) {
			mutationsLeft--;
			((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
		}

		String f_name;
		num++;
		f_name = getSourceName(this);
		String mutant_dir = getMuantID();

		try 
		{
			PrintWriter out = getPrintWriter(f_name);
			PRV_Writer writer = new PRV_Writer( mutant_dir,out );
			writer.setMutant(original,mutant);
			comp_unit.accept( writer );
			out.flush();  
			out.close();
		} catch ( IOException e ) {
			System.err.println( "fails to create " + f_name );
		} catch ( ParseTreeException e ) {
			System.err.println( "errors during printing " + f_name );
			e.printStackTrace();
		}
		
		if (mutationsLeft>=0) {
			mutationsLeft++;
			((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
		}

	}

	/**
	 * Output PRVO mutants to files
	 * 
	 * @param original
	 * @param mutant
	 */
	public void outputToFileLeft(AssignmentExpression original, String mutant) {
//		if (comp_unit == null)
//			return;
//
//		parent = original.getMutationLimitParent();
//		if (parent != null && ((NonLeaf) parent).getComment()!=null) {
//			try {
//				String mutations = ((NonLeaf) parent).getComment().substring(14);
//				mutationsLeft = Integer.parseInt(mutations);
//			}
//			catch (Exception e) {
//				// comment is not an integer. Do nothing.
//			}
//		}
		
		mutationsLeft = getMutationsLeft(original);

		if (!(mutationsLeft>0)) return;
		
		// ********** MUJAVA++ MODIFICATION **********//
		// ********** date: 7 Feb 2012 **********//
		if (Api.usingApi()) {
			if (parent==null) return; //do not mutate if no mutation generation limit provided!
			MutantsInformationHolder.mainHolder().addMutantIdentifier(
					Mutant.PRVO, original, mutant, "Assignment Expression Left");
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

		try 
		{
			PrintWriter out = getPrintWriter(f_name);
			PRV_Writer writer = new PRV_Writer( mutant_dir,out );
			writer.setMutant(original,mutant);
			comp_unit.accept( writer );
			out.flush();  
			out.close();
		} catch ( IOException e ) {
			System.err.println( "fails to create " + f_name );
		} catch ( ParseTreeException e ) {
			System.err.println( "errors during printing " + f_name );
			e.printStackTrace();
		}
		
		if (mutationsLeft>=0) {
			mutationsLeft++;
			((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
		}

	} 
		
	
	public void outputToFile(NonLeaf original, String mutant) {
		//System.out.println("original : "+original.toString()+"|mutación : "+mutant+'\n');
		if (original instanceof AssignmentExpression)
			outputToFile((AssignmentExpression) original, mutant);
		else if (original instanceof BinaryExpression)
			outputToFile((BinaryExpression) original, mutant);
//		else if (original instanceof VariableDeclaration)
//			outputToFile((VariableDeclaration) original, mutant);
		else if (original instanceof VariableDeclarator)
			outputToFile((VariableDeclarator) original, mutant);
		else if (original instanceof ReturnStatement)
			outputToFile((ReturnStatement) original, mutant);
	}

	/**
	 * Output PRVO mutants to files
	 * 
	 * @param original
	 * @param mutant
	 */
	public void outputToFile(AssignmentExpression original, String mutant) {
//		if (comp_unit == null)
//			return;
//
//		parent = original.getMutationLimitParent();
//		if (parent != null && ((NonLeaf) parent).getComment()!=null) {
//			try {
//				String mutations = ((NonLeaf) parent).getComment().substring(14);
//				mutationsLeft = Integer.parseInt(mutations);
//			}
//			catch (Exception e) {
//				// comment is not an integer. Do nothing.
//			}
//		}
		
		mutationsLeft = getMutationsLeft(original);
		
		if (!(mutationsLeft>0)) return;
		
		// ********** MUJAVA++ MODIFICATION **********//
		// ********** date: 7 Feb 2012 **********//
		if (Api.usingApi()) {
			if (parent==null) return; //do not mutate if no mutation generation limit provided!
			MutantsInformationHolder.mainHolder().addMutantIdentifier(
					Mutant.PRVO, original, mutant, "Assignment Expression Right");
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

		try 
		{
			PrintWriter out = getPrintWriter(f_name);
			PRV_Writer writer = new PRV_Writer( mutant_dir,out );
			writer.setMutant(original,mutant);
			comp_unit.accept( writer );
			out.flush();  
			out.close();
		} catch ( IOException e ) {
			System.err.println( "fails to create " + f_name );
		} catch ( ParseTreeException e ) {
			System.err.println( "errors during printing " + f_name );
			e.printStackTrace();
		}
		
		if (mutationsLeft>=0) {
			mutationsLeft++;
			((NonLeaf) parent).setComment("//mutGenLimit "+mutationsLeft);
		}


	}

	
}
