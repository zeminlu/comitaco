/*
 * TACO: Translation of Annotated COde
 * Copyright (c) 2010 Universidad de Buenos Aires
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA,
 * 02110-1301, USA
 */
package ar.edu.taco.simplejml;

import static ar.uba.dc.rfm.alloy.AlloyVariable.buildAlloyVariable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jmlspecs.checker.JmlClassDeclaration;
import org.jmlspecs.checker.JmlCompilationUnit;
import org.jmlspecs.checker.JmlConstraint;
import org.jmlspecs.checker.JmlConstructorDeclaration;
import org.jmlspecs.checker.JmlFieldDeclaration;
import org.jmlspecs.checker.JmlInGroupClause;
import org.jmlspecs.checker.JmlInterfaceDeclaration;
import org.jmlspecs.checker.JmlInvariant;
import org.jmlspecs.checker.JmlMethodDeclaration;
import org.jmlspecs.checker.JmlRepresentsDecl;
import org.jmlspecs.checker.JmlStoreRefExpression;
import org.jmlspecs.checker.JmlTypeDeclaration;
import org.jmlspecs.jmlrac.JavaAndJmlPrettyPrint2;
import org.multijava.mjc.CClassType;
import org.multijava.mjc.CStdType;
import org.multijava.mjc.CVoidType;
import org.multijava.mjc.JCompilationUnit;
import org.multijava.mjc.JCompilationUnitType;
import org.multijava.mjc.JFieldDeclarationType;
import org.multijava.mjc.JTypeDeclarationType;

import ar.edu.jdynalloy.ast.JDynAlloyModule;
import ar.edu.jdynalloy.ast.JBlock;
import ar.edu.jdynalloy.ast.JModifies;
import ar.edu.jdynalloy.ast.JPostcondition;
import ar.edu.jdynalloy.ast.JPrecondition;
import ar.edu.jdynalloy.ast.JProgramDeclaration;
import ar.edu.jdynalloy.ast.JSkip;
import ar.edu.jdynalloy.ast.JSpecCase;
import ar.edu.jdynalloy.ast.JStatement;
import ar.edu.jdynalloy.ast.JVariableDeclaration;
import ar.edu.jdynalloy.buffer.DynJMLAlloyModuleBuffer;
import ar.edu.jdynalloy.buffer.Represents;
import ar.edu.jdynalloy.factory.JDynAlloyFactory;
import ar.edu.jdynalloy.factory.JExpressionFactory;
import ar.edu.jdynalloy.factory.JPredicateFactory;
import ar.edu.jdynalloy.xlator.JDynAlloyTyping;
import ar.edu.jdynalloy.xlator.JType;
import ar.edu.taco.TacoConfigurator;
import ar.edu.taco.simplejml.JmlBaseExpressionVisitor.Instant;
import ar.edu.taco.simplejml.JmlBaseExpressionVisitor.JmlClassDeclarationResult;
import ar.edu.taco.simplejml.JmlBaseExpressionVisitor.JmlRepresentsData;
import ar.edu.taco.simplejml.builtin.JObject;
import ar.edu.taco.simplejml.helpers.CTypeAdapter;
import ar.edu.taco.simplejml.helpers.JavaClassNameNormalizer;
import ar.edu.taco.simplejml.helpers.MethodDeclarationSolver;
import ar.edu.taco.simplejml.helpers.NullDerefSolver;
import ar.edu.taco.utils.jml.JmlAstTransverseStatementVisitor;
import ar.uba.dc.rfm.alloy.AlloyVariable;
import ar.uba.dc.rfm.alloy.ast.expressions.AlloyExpression;
import ar.uba.dc.rfm.alloy.ast.expressions.ExprJoin;
import ar.uba.dc.rfm.alloy.ast.expressions.ExprVariable;
import ar.uba.dc.rfm.alloy.ast.formulas.AlloyFormula;
import ar.uba.dc.rfm.alloy.ast.formulas.EqualsFormula;
import ar.uba.dc.rfm.alloy.ast.formulas.NotFormula;

public class JDynAlloyASTVisitor extends JmlAstTransverseStatementVisitor {

	private static Logger log = Logger.getLogger(JDynAlloyASTVisitor.class);

	protected static final String OBJECT_STATE_STRING = "objectState";
	protected static final String WIDLCARD_STRING = "*";

	protected JavaAndJmlPrettyPrint2 prettyPrint = new JavaAndJmlPrettyPrint2();

	private List<JDynAlloyModule> modules = new ArrayList<JDynAlloyModule>();
	private Map<String, List<String>> modulesObjectState;
	private Map<String, List<String>> modulesNoStaticFields;
	private DynJMLAlloyModuleBuffer buffer = new DynJMLAlloyModuleBuffer();

	private final SimpleJmlToJDynAlloyContext simpleJmlToJDynAlloyContext;
	private static String currentModuleName;
	
	
	private static class FieldTypes {
		private List<String> keys = new LinkedList<String>();
		private List<JType> values =new LinkedList<JType>();

		public JType get(String key) {
			if (keys.contains(key)) {
			  int index = keys.indexOf(key);
			  return values.get(index);
			}
			return null;
		}
		
		public void put(String field_name, JType type) {
			if (keys.contains(field_name)) {
				  int index = keys.indexOf(field_name);
				  values.remove(index);
				  values.add(index, type);
				}
		}
		
	}
	
	private static FieldTypes currentModuleFieldsTypes;

	public JDynAlloyASTVisitor() {
		simpleJmlToJDynAlloyContext = null;
	}

	public JDynAlloyASTVisitor(Map<String, List<String>> modulesObjectState, Map<String, List<String>> modulesNoStaticFields,
			SimpleJmlToJDynAlloyContext simpleJmlToJDynAlloyContext) {

		this.modulesObjectState = modulesObjectState;
		this.modulesNoStaticFields = modulesNoStaticFields;
		this.simpleJmlToJDynAlloyContext = simpleJmlToJDynAlloyContext;
	}

	public List<JDynAlloyModule> getModules() {
		return this.modules;
	}

	public static String getCurrentModuleName() {
		return currentModuleName;
	}

	public static JType getVariableTypeOnCurrentModule(String varName) {
		return currentModuleFieldsTypes.get(varName);
	}

	/**
	 * @return the buffer
	 */
	protected DynJMLAlloyModuleBuffer getBuffer() {
		return buffer;
	}

	/**
	 * @param buffer
	 *            the buffer to set
	 */
	public void setBuffer(DynJMLAlloyModuleBuffer buffer) {
		this.buffer = buffer;
	}

	/**
	 * @return the modulesObjectState
	 */
	public Map<String, List<String>> getModulesObjectState() {
		return modulesObjectState;
	}

	/**
	 * @param modulesObjectState
	 *            the modulesObjectState to set
	 */
	public void setModulesObjectState(Map<String, List<String>> modulesObjectState) {
		this.modulesObjectState = modulesObjectState;
	}

	/**
	 * @return the modulesNoStaticFields
	 */
	public Map<String, List<String>> getModulesNoStaticFields() {
		return modulesNoStaticFields;
	}

	/**
	 * @param modulesNoStaticFields
	 *            the modulesNoStaticFields to set
	 */
	public void setModulesNoStaticFields(Map<String, List<String>> modulesNoStaticFields) {
		this.modulesNoStaticFields = modulesNoStaticFields;
	}

	@Override
	public void visitJmlConstructorDeclaration(JmlConstructorDeclaration jmlConstructorDeclaration) {
		jmlConstructorDeclaration.accept(prettyPrint);
		log.debug("Visiting: " + jmlConstructorDeclaration.getClass().getName());
		log.debug("Constructor: \n" + this.prettyPrint.getPrettyPrint());

		JProgramDeclaration programDeclaration = MethodDeclarationSolver.getConstructorDeclaration(jmlConstructorDeclaration, this.buffer,
				this.modulesObjectState, this.modulesNoStaticFields);

		JStatement initializeThrow = JDynAlloyFactory.initializeThrow();
		// Declare exit statement used to indicate that a Throw or Return
		// statement was reached in the code.
		CTypeAdapter cTypeAdapter = new CTypeAdapter();
		JType variableType = cTypeAdapter.translate(CStdType.Boolean);
		JStatement exitStmtReachedDeclaration = new JVariableDeclaration(JExpressionFactory.EXIT_REACHED_VARIABLE, variableType);
		JStatement exitStmtReachedExpression = JDynAlloyFactory.initializeExitStatementReached();

		BlockStatementsVisitor blockScopeTranslator = new BlockStatementsVisitor();
		jmlConstructorDeclaration.body().accept(blockScopeTranslator);
		JStatement programBody = blockScopeTranslator.getJAlloyProgram();
		JStatement program = JDynAlloyFactory.block(initializeThrow, exitStmtReachedDeclaration, exitStmtReachedExpression, programBody);

		if (TacoConfigurator.getInstance().getBoolean(TacoConfigurator.CHECK_NULL_DE_REFERENCE_FIELD) == true) {
			List<JStatement> nullDerefHeader = NullDerefSolver.buildNullDerefHeader();
			JStatement nullDeRefBody = NullDerefSolver.buildNullDerefBody(program);
			JStatement nullDeRefTail = NullDerefSolver.buildNullDerefTail();

			List<JStatement> stmtList = new LinkedList<JStatement>();
			stmtList.addAll(nullDerefHeader);
			stmtList.add(nullDeRefBody);
			stmtList.add(nullDeRefTail);

			program = new JBlock(stmtList);

		}

		programDeclaration = new JProgramDeclaration(programDeclaration.isAbstract(), programDeclaration.getSignatureId(), programDeclaration.getProgramId(),
				programDeclaration.getParameters(), programDeclaration.getSpecCases(), program);

		buffer.getPrograms().add(programDeclaration);

		this.simpleJmlToJDynAlloyContext.record_simpleJml_to_JDynAlloy_mapping(jmlConstructorDeclaration, programDeclaration);
	}

	@Override
	public void visitJmlClassDeclaration(
	/* @non_null */JmlClassDeclaration jmlClassDeclaration) {
		jmlClassDeclaration.accept(prettyPrint);
		log.debug("Visiting: " + jmlClassDeclaration.getClass().getName());
		log.debug("Class: \n" + this.prettyPrint.getPrettyPrint());

		translateTypeDeclaration(jmlClassDeclaration);

		JDynAlloyModule module = this.buffer.getModule();
		this.modules.add(module);
		this.simpleJmlToJDynAlloyContext.record_simpleJml_to_JDynAlloy_mapping(jmlClassDeclaration, module);
	}

	@Override
	public void visitJmlFieldDeclaration(JmlFieldDeclaration jmlFieldDeclaration) {
		jmlFieldDeclaration.accept(prettyPrint);
		log.debug("Visiting: " + jmlFieldDeclaration.getClass().getName());
		log.debug("Field: " + this.prettyPrint.getPrettyPrint());

		// Create an AlloyVariable from variable name
		AlloyVariable alloyVariable = buildAlloyVariable(jmlFieldDeclaration.ident());

		// extract the variable type and convert it to and Alloy variable type.
		CTypeAdapter cTypeAdapter = new CTypeAdapter();
		JType variableType = cTypeAdapter.translate(jmlFieldDeclaration.getType());

		// if (jmlFieldDeclaration.getField().isDeclaredNonNull()) {
		// variableType.setAsNonNull();
		// }

		JDynAlloyASTVisitor.currentModuleFieldsTypes.put(alloyVariable.getVariableId().getString(), variableType);

		if (jmlFieldDeclaration.getField().isFieldStatic()) {
			this.buffer.getStaticFields().put(alloyVariable, variableType);
		} else {
			this.buffer.getFields().put(alloyVariable, variableType);
		}

		// Here we are going to assing each variable into it corresponding
		// groupClause
		for (JmlInGroupClause jmlInGroupClause : jmlFieldDeclaration.getCombinedInGroupClauses()) {
			for (JmlStoreRefExpression jmlStoreRefExpression : jmlInGroupClause.groupList()) {
				String groupName = jmlStoreRefExpression.getName();
				if (!this.buffer.getInGroupClauses().containsKey(groupName)) {
					this.buffer.getInGroupClauses().put(groupName, new ArrayList<AlloyVariable>());
				}
				this.buffer.getInGroupClauses().get(groupName).add(alloyVariable);
			}
		}
	}

	@Override
	public void visitJmlInterfaceDeclaration(JmlInterfaceDeclaration jmlInterfaceDeclaration) {
		jmlInterfaceDeclaration.accept(prettyPrint);
		log.debug("Visiting: " + jmlInterfaceDeclaration.getClass().getName());
		log.debug("Class: \n" + this.prettyPrint.getPrettyPrint());

		translateTypeDeclaration(jmlInterfaceDeclaration);

		JDynAlloyModule module = this.buffer.getModule();
		this.modules.add(module);

		this.simpleJmlToJDynAlloyContext.record_simpleJml_to_JDynAlloy_mapping(jmlInterfaceDeclaration, module);
	}

	@Override
	public void visitJmlMethodDeclaration(
	/* @non_null */JmlMethodDeclaration jmlMethodDeclaration) {
		// jmlMethodDeclaration.accept(prettyPrint);
		// log.debug("Visiting: " + jmlMethodDeclaration.getClass().getName());
		// log.debug("Statement: " + prettyPrint.getPrettyPrint());

		JStatement initializeThrow = JDynAlloyFactory.initializeThrow();

		// Declare exit statement used to indicate that a Throw or Return
		// statement was reached in the code.
		CTypeAdapter cTypeAdapter = new CTypeAdapter();
		JType variableType = cTypeAdapter.translate(CStdType.Boolean);
		JStatement exitStmtReachedDeclaration = new JVariableDeclaration(JExpressionFactory.EXIT_REACHED_VARIABLE, variableType);
		JStatement exitStmtReachedExpression = JDynAlloyFactory.initializeExitStatementReached();

		// Parse method body, if exists.
		JStatement programBody = new JSkip();
		if (jmlMethodDeclaration.body() != null) {
			BlockStatementsVisitor blockScopeTranslator = new BlockStatementsVisitor();
			blockScopeTranslator.methodReturnValue = !(jmlMethodDeclaration.returnType() instanceof CVoidType);
			jmlMethodDeclaration.body().accept(blockScopeTranslator);

			programBody = blockScopeTranslator.getJAlloyProgram();
		}

		JStatement program = null;
		program = JDynAlloyFactory.block(initializeThrow, exitStmtReachedDeclaration, exitStmtReachedExpression, programBody);

		if (TacoConfigurator.getInstance().getBoolean(TacoConfigurator.CHECK_NULL_DE_REFERENCE_FIELD) == true) {

			List<JStatement> nullDerefHeader = NullDerefSolver.buildNullDerefHeader();

			JStatement nullDeRefBody = NullDerefSolver.buildNullDerefBody(program);
			JStatement nullDeRefTail = NullDerefSolver.buildNullDerefTail();

			List<JStatement> stmtList = new LinkedList<JStatement>();
			stmtList.addAll(nullDerefHeader);
			stmtList.add(nullDeRefBody);
			stmtList.add(nullDeRefTail);

			program = new JBlock(stmtList);
		}

		JProgramDeclaration programDeclaration = MethodDeclarationSolver.getMethodDeclaration(jmlMethodDeclaration, this.buffer, this.modulesObjectState,
				this.modulesNoStaticFields);

		programDeclaration = new JProgramDeclaration(programDeclaration.isAbstract(), programDeclaration.getSignatureId(), programDeclaration.getProgramId(),
				programDeclaration.getParameters(), programDeclaration.getSpecCases(), program);

		buffer.getPrograms().add(programDeclaration);

		this.simpleJmlToJDynAlloyContext.record_simpleJml_to_JDynAlloy_mapping(jmlMethodDeclaration, programDeclaration);

	}

	private void translateTypeDeclaration(JmlTypeDeclaration jmlTypeDeclaration) {

		// Transverse Inner Classes
		if (jmlTypeDeclaration.inners() != null) {
			for (JmlClassDeclaration innerClassDeclaration : (ArrayList<JmlClassDeclaration>) jmlTypeDeclaration.inners()) {
				innerClassDeclaration.accept(this);

			}
		}

		buffer = new DynJMLAlloyModuleBuffer();

		JavaClassNameNormalizer classNameNormalizer = new JavaClassNameNormalizer(jmlTypeDeclaration.getCClass().getJavaName());
		JDynAlloyASTVisitor.currentModuleName = classNameNormalizer.getQualifiedClassName();

		// process supper interfaces
		for (CClassType superInterface : jmlTypeDeclaration.getCClass().getInterfaces()) {
			JavaClassNameNormalizer interfaceNameNormalizer = new JavaClassNameNormalizer(superInterface.getCClass().getJavaName());
			buffer.getSuperInterfaces().add(interfaceNameNormalizer.getQualifiedClassName());
		}

		buffer.setInterface(jmlTypeDeclaration.getCClass().isInterface());

		if (jmlTypeDeclaration.getCClass().getSuperType() == null) {
			buffer.setSuperClassSignatureId(JObject.getInstance().getModule().getSignature().getSignatureId());
		} else {
			JavaClassNameNormalizer classNameNormalizer2 = new JavaClassNameNormalizer(jmlTypeDeclaration.getCClass().getSuperType().getCClass().getJavaName());
			buffer.setSuperClassSignatureId(classNameNormalizer2.getQualifiedClassName());
		}

		String name = classNameNormalizer.getQualifiedClassName();
		buffer.setSignatureId(name);
		buffer.setAbstract(jmlTypeDeclaration.getCClass().isAbstract());
		buffer.setSignatureId(name);
		buffer.setThisType(new JType(name));

		JDynAlloyASTVisitor.currentModuleFieldsTypes = new FieldTypes();

		// Transverse Fields
		if (jmlTypeDeclaration.fields() != null) {
			List<AlloyFormula> nullityFieldsInvariants = new ArrayList<AlloyFormula>();

			List<String> modelFieldList = extractFildsName(jmlTypeDeclaration.getModelFields());
			List<String> objectStateFieldsNames = new ArrayList<String>();
			List<String> noStaticFieldsNames = new ArrayList<String>();
			for (JFieldDeclarationType jFieldDeclarationType : jmlTypeDeclaration.fields()) {
				jFieldDeclarationType.accept(this);

				boolean isModelField = modelFieldList.contains(jFieldDeclarationType.ident());

				if (jFieldDeclarationType.getField().isDeclaredNonNull() && !jFieldDeclarationType.getField().isStatic() && !isModelField) {
					AlloyExpression varExpression = new ExprVariable(new AlloyVariable(jFieldDeclarationType.ident()));
					varExpression = new ExprJoin(JExpressionFactory.THIS_EXPRESSION, varExpression);
					AlloyFormula nullityFormula = new NotFormula(new EqualsFormula(varExpression, JExpressionFactory.NULL_EXPRESSION));
					nullityFieldsInvariants.add(nullityFormula);
				}

				if (jFieldDeclarationType instanceof JmlFieldDeclaration) {
					JmlFieldDeclaration jmlFieldDeclaration = (JmlFieldDeclaration) jFieldDeclarationType;

					if (!jmlFieldDeclaration.getField().isStatic()) {
						noStaticFieldsNames.add(jFieldDeclarationType.ident());
					}

					for (int x = 0; x < jmlFieldDeclaration.getCombinedInGroupClauses().length; x++) {
						if (jmlFieldDeclaration.getCombinedInGroupClauses()[x].equals(OBJECT_STATE_STRING)) {
							objectStateFieldsNames.add(jFieldDeclarationType.ident());
						} else {
							for (JmlStoreRefExpression jmlStoreRefExpression : jmlFieldDeclaration.getCombinedInGroupClauses()[x].groupList()) {
								if (jmlStoreRefExpression.getName().equals(OBJECT_STATE_STRING)) {
									objectStateFieldsNames.add(jFieldDeclarationType.ident());
								}
							}
						}
					}
				}
			}

			this.modulesObjectState.put(name, objectStateFieldsNames);
			this.modulesNoStaticFields.put(name, noStaticFieldsNames);
			if (nullityFieldsInvariants.size() > 0) {
				buffer.getInvariants().addAll(nullityFieldsInvariants);
			}
		}

		// Transverse JML annotations
		translateJmlAnnotations(jmlTypeDeclaration);

		// Transverse Methods
		for (JmlMethodDeclaration methodDeclaration : (ArrayList<JmlMethodDeclaration>) jmlTypeDeclaration.methods()) {
			methodDeclaration.accept(this);
		}
	}

	private List<String> extractFildsName(JFieldDeclarationType[] modelFields) {
		List<String> retValues = new ArrayList<String>();
		for (JFieldDeclarationType fieldDeclarationType : modelFields) {
			retValues.add(fieldDeclarationType.ident());
		}
		return retValues;
	}

	private void translateJmlAnnotations(JmlTypeDeclaration jmlTypeDeclaration) {

		JmlExpressionVisitor jmlExpressionVisitor = new JmlExpressionVisitor(Instant.PRE_INSTANT);
		jmlExpressionVisitor.setClassSpecification(true);

		if (jmlTypeDeclaration.getModelFields() != null) {
			for (JFieldDeclarationType jFieldDeclarationType : jmlTypeDeclaration.getModelFields()) {
				jFieldDeclarationType.accept(this);
			}
		}

		// TODO: aca hay que parsear la definicion de metodos del modelo
		// (metodos definidos en JML)

		if (jmlTypeDeclaration.invariants() != null) {
			for (JmlInvariant jmlInvariant : jmlTypeDeclaration.invariants()) {
				jmlInvariant.accept(jmlExpressionVisitor);
			}
		}
		if (jmlTypeDeclaration.constraints() != null) {
			for (JmlConstraint jmlConstraint : jmlTypeDeclaration.constraints()) {
				jmlConstraint.accept(jmlExpressionVisitor);
			}
		}
		if (jmlTypeDeclaration.representsDecls() != null) {
			for (JmlRepresentsDecl jmlRepresentsDecl : jmlTypeDeclaration.representsDecls()) {
				jmlRepresentsDecl.accept(jmlExpressionVisitor);
			}
		}

		JmlClassDeclarationResult jmlDeclarationResult = jmlExpressionVisitor.getJmlClassDeclarationResult();

		for (Iterator<JDynAlloyTyping.Entry> it = jmlDeclarationResult.modelFields(); it.hasNext();) {
			JDynAlloyTyping.Entry modelField = it.next();
			buffer.getFields().put(modelField.getVariable(), modelField.getType());
		}

		for (Iterator<JDynAlloyTyping.Entry> it = jmlDeclarationResult.staticModelFields(); it.hasNext();) {
			JDynAlloyTyping.Entry staticModelField = it.next();
			buffer.getFields().put(staticModelField.getVariable(), staticModelField.getType());
		}

		for (Iterator<AlloyFormula> it = jmlDeclarationResult.invariants(); it.hasNext();) {
			buffer.getInvariants().add(it.next());
		}

		for (Iterator<AlloyFormula> it = jmlDeclarationResult.staticInvariants(); it.hasNext();) {
			buffer.getStaticInvariants().add(it.next());
		}

		for (Iterator<AlloyFormula> it = jmlDeclarationResult.constraints(); it.hasNext();) {
			buffer.getConstraints().add(it.next());
		}

		for (Iterator<AlloyFormula> it = jmlDeclarationResult.staticConstraints(); it.hasNext();) {
			buffer.getStaticConstraints().add(it.next());
		}

		for (Iterator<JmlRepresentsData> it = jmlDeclarationResult.represents(); it.hasNext();) {
			JmlRepresentsData entry = it.next();
			buffer.getRepresents().add(new Represents(entry.expression, entry.expressionType, entry.formula));
		}
	}

	private void visitCompilationUnitType(JCompilationUnitType n) {
		JTypeDeclarationType[] typeDeclarations = n.typeDeclarations();
		for (int i = 0; i < typeDeclarations.length; i++) {
			JTypeDeclarationType jTypeDeclarationType = typeDeclarations[i];
			jTypeDeclarationType.accept(this);
		}
	}

	@Override
	public void visitCompilationUnit(JCompilationUnit n) {
		visitCompilationUnitType(n);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public void visitJmlCompilationUnit(JmlCompilationUnit n) {
		visitCompilationUnitType(n);
	}

}
