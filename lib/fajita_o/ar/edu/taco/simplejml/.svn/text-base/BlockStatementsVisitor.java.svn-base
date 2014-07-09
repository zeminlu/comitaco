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

import java.util.Collections;
import java.util.Stack;

import org.apache.log4j.Logger;
import org.jmlspecs.checker.JmlAssertStatement;
import org.jmlspecs.checker.JmlAssignmentStatement;
import org.jmlspecs.checker.JmlAssumeStatement;
import org.jmlspecs.checker.JmlLoopInvariant;
import org.jmlspecs.checker.JmlLoopStatement;
import org.jmlspecs.checker.JmlSetStatement;
import org.multijava.mjc.CType;
import org.multijava.mjc.Constants;
import org.multijava.mjc.JAddExpression;
import org.multijava.mjc.JArrayAccessExpression;
import org.multijava.mjc.JAssignmentExpression;
import org.multijava.mjc.JCatchClause;
import org.multijava.mjc.JCompoundAssignmentExpression;
import org.multijava.mjc.JDivideExpression;
import org.multijava.mjc.JExpressionStatement;
import org.multijava.mjc.JIfStatement;
import org.multijava.mjc.JMethodCallExpression;
import org.multijava.mjc.JMinusExpression;
import org.multijava.mjc.JModuloExpression;
import org.multijava.mjc.JMultExpression;
import org.multijava.mjc.JNewArrayExpression;
import org.multijava.mjc.JNewObjectExpression;
import org.multijava.mjc.JPostfixExpression;
import org.multijava.mjc.JReturnStatement;
import org.multijava.mjc.JThrowStatement;
import org.multijava.mjc.JTryCatchStatement;
import org.multijava.mjc.JTryFinallyStatement;
import org.multijava.mjc.JVariableDefinition;
import org.multijava.mjc.JWhileStatement;

import ar.edu.jdynalloy.ast.JAssert;
import ar.edu.jdynalloy.ast.JAssignment;
import ar.edu.jdynalloy.ast.JAssume;
import ar.edu.jdynalloy.ast.JIfThenElse;
import ar.edu.jdynalloy.ast.JLoopInvariant;
import ar.edu.jdynalloy.ast.JSkip;
import ar.edu.jdynalloy.ast.JStatement;
import ar.edu.jdynalloy.ast.JVariableDeclaration;
import ar.edu.jdynalloy.ast.JavaPrimitiveIntValueArrayFactory;
import ar.edu.jdynalloy.factory.JExpressionFactory;
import ar.edu.jdynalloy.factory.JPredicateFactory;
import ar.edu.jdynalloy.factory.JSignatureFactory;
import ar.edu.jdynalloy.xlator.JType;
import ar.edu.taco.TacoConfigurator;
import ar.edu.taco.TacoException;
import ar.edu.taco.jml.utils.LabelUtils;
import ar.edu.taco.simplejml.JmlBaseExpressionVisitor.Instant;
import ar.edu.taco.simplejml.builtin.AuxiliaryConstantsFactory;
import ar.edu.taco.simplejml.builtin.JavaPrimitiveIntegerValue;
import ar.edu.taco.simplejml.builtin.AuxiliaryConstantsFactory.AddAuxiliaryConstants;
import ar.edu.taco.simplejml.builtin.AuxiliaryConstantsFactory.DivAuxiliaryConstants;
import ar.edu.taco.simplejml.builtin.AuxiliaryConstantsFactory.MinusAuxiliaryConstants;
import ar.edu.taco.simplejml.builtin.AuxiliaryConstantsFactory.MultAuxiliaryConstants;
import ar.edu.taco.simplejml.helpers.BlockStatementSolver;
import ar.edu.taco.simplejml.helpers.CTypeAdapter;
import ar.edu.taco.simplejml.helpers.ExpressionSolver;
import ar.edu.taco.simplejml.helpers.JavaOperatorSolver;
import ar.uba.dc.rfm.alloy.AlloyVariable;
import ar.uba.dc.rfm.alloy.ast.expressions.AlloyExpression;
import ar.uba.dc.rfm.alloy.ast.expressions.ExprIfCondition;
import ar.uba.dc.rfm.alloy.ast.expressions.ExprIntLiteral;
import ar.uba.dc.rfm.alloy.ast.expressions.ExprJoin;
import ar.uba.dc.rfm.alloy.ast.expressions.ExprVariable;
import ar.uba.dc.rfm.alloy.ast.formulas.AlloyFormula;
import ar.uba.dc.rfm.alloy.ast.formulas.AndFormula;
import ar.uba.dc.rfm.alloy.ast.formulas.EqualsFormula;
import ar.uba.dc.rfm.alloy.ast.formulas.NotFormula;

public class BlockStatementsVisitor extends JDynAlloyASTVisitor {

	private Stack<AlloyExpression> expressions = new Stack<AlloyExpression>();

	int ifLabelCount = 10000;
	// int whileLabelCount = 10000;
	private static Logger log = Logger.getLogger(BlockStatementsVisitor.class);

	/**
	 * @return a JStatement that represent a JAlloyProgram.
	 */
	public JStatement getJAlloyProgram() {
		if (this.programBuffer.toJAlloyProgram() != null) {
			return this.programBuffer.toJAlloyProgram();
		} else {
			return new JSkip();
		}
	}

	@Override
	public void visitAssignmentExpression(JAssignmentExpression jAssignmentExpression) {
		jAssignmentExpression.accept(prettyPrint);
		log.debug("Visiting: " + jAssignmentExpression.getClass().getName());
		log.debug("Statement: " + prettyPrint.getPrettyPrint());

		ExpressionVisitor expressionVisitor = new ExpressionVisitor();

		jAssignmentExpression.left().accept(expressionVisitor);
		AlloyExpression leftSide = expressionVisitor.getAlloyExpression();
		expressionVisitor.setLeftAssignmentExpression(leftSide);

		// If the variable which is going to be modified is an instance
		// variable, then it should be added into de modified variables list
		if (leftSide instanceof ExprJoin && ((ExprJoin) leftSide).getLeft().equals(JExpressionFactory.THIS_EXPRESSION)) {
			this.instanceModifiedVariables.add(leftSide);
		}

		Object rightSide = null;

		if (jAssignmentExpression.right() instanceof JMinusExpression) {

			JMinusExpression minusExpression = (JMinusExpression) jAssignmentExpression.right();

			this.visitMinusExpression(minusExpression);

			rightSide = expressions.pop();
		} else if (jAssignmentExpression.right() instanceof JAddExpression) {

			JAddExpression addExpression = (JAddExpression) jAssignmentExpression.right();

			this.visitAddExpression(addExpression);

			rightSide = expressions.pop();
		} else if (jAssignmentExpression.right() instanceof JDivideExpression) {

			JDivideExpression divExpression = (JDivideExpression) jAssignmentExpression.right();

			this.visitDivideExpression(divExpression);

			rightSide = expressions.pop();

		} else if (jAssignmentExpression.right() instanceof JMultExpression) {

			JMultExpression multExpression = (JMultExpression) jAssignmentExpression.right();

			this.visitMultExpression(multExpression);

			rightSide = expressions.pop();

		} else if (jAssignmentExpression.right() instanceof JModuloExpression) {

			JModuloExpression moduloExpression = (JModuloExpression) jAssignmentExpression.right();

			this.visitModuloExpression(moduloExpression);

			rightSide = expressions.pop();

		} else {

			jAssignmentExpression.right().accept(expressionVisitor);
			if ((jAssignmentExpression.right() instanceof JMethodCallExpression) || jAssignmentExpression.right() instanceof JNewObjectExpression
					|| jAssignmentExpression.right() instanceof JNewArrayExpression) {
				rightSide = expressionVisitor.getAlloyProgram();
			} else {
				rightSide = expressionVisitor.getAlloyExpression();
			}
		}

		JStatement jStatement;

		if ((TacoConfigurator.getInstance().getUseJavaArithmetic() == true)
				&& (jAssignmentExpression.left() instanceof JArrayAccessExpression || jAssignmentExpression.right() instanceof JArrayAccessExpression)) {

			AlloyExpression left_side_expr = (AlloyExpression) leftSide;
			AlloyExpression right_side_expr = (AlloyExpression) rightSide;
			if (jAssignmentExpression.left() instanceof JArrayAccessExpression) {

				jStatement = JavaPrimitiveIntValueArrayFactory.array_write_stmt(left_side_expr, right_side_expr);
			} else {

				jStatement = JavaPrimitiveIntValueArrayFactory.array_read_stmt(left_side_expr, right_side_expr);

			}
		} else {

			if (rightSide instanceof AlloyExpression) {
				jStatement = new JAssignment(leftSide, (AlloyExpression) rightSide);
			} else if (rightSide instanceof AlloyFormula) {
				jStatement = (new JIfThenElse((AlloyFormula) rightSide, new JAssignment(leftSide, JExpressionFactory.TRUE_EXPRESSION), new JAssignment(
						leftSide, JExpressionFactory.FALSE_EXPRESSION), LabelUtils.nextIfLabel()));
			} else if (rightSide instanceof JStatement /* AlloyProgram */) {
				jStatement = (JStatement) rightSide;
			} else
				throw new RuntimeException("Illegal condition");
		}

		if (this.isTryCatchBlock) {
			programBuffer.openIf(BlockStatementSolver.getTryCatchSurrounderCondition());
		}

		programBuffer.appendProgram(jStatement);

		if (this.isTryCatchBlock) {
			programBuffer.closeIf();
		}
	}

	@Override
	public void visitCatchClause(JCatchClause jCatchClause) {
		AlloyExpression throwAlloyExpression = JExpressionFactory.THROW_EXPRESSION;

		// CTypeAdapter cTypeAdapter = new CTypeAdapter();
		// JType jType =
		// cTypeAdapter.translate(jCatchClause.exception().getType());
		// String signatureId = jType.toString();

		ExpressionVisitor expressionVisitor = new ExpressionVisitor();
		jCatchClause.exception().accept(expressionVisitor);

		JVariableDeclaration declaredVariable = (JVariableDeclaration) expressionVisitor.getAlloyProgram();
		JType jType = declaredVariable.getType();
		String signatureId = jType.toString();

		AlloyFormula alloyFormula = JPredicateFactory.instanceOf(throwAlloyExpression, signatureId);
		programBuffer.openIf(alloyFormula);

		declaredVariable.getType();
		programBuffer.appendProgram(declaredVariable);
		programBuffer.assign(declaredVariable.getVariable(), throwAlloyExpression);
		programBuffer.assign(JExpressionFactory.THROW_EXPRESSION, JExpressionFactory.NULL_EXPRESSION);

		BlockStatementsVisitor catchBlockScopeTranslator = new BlockStatementsVisitor();
		for (org.multijava.mjc.JStatement aStatement : jCatchClause.body().body()) {
			aStatement.accept(catchBlockScopeTranslator);
		}

		programBuffer.appendProgram(catchBlockScopeTranslator.getJAlloyProgram());

		programBuffer.closeIf();
	}

	@Override
	public void visitCompoundAssignmentExpression(JCompoundAssignmentExpression jCompoundAssignmentExpression) {
		jCompoundAssignmentExpression.accept(prettyPrint);
		log.debug("Visiting: " + jCompoundAssignmentExpression.getClass().getName());
		log.debug("Statement: " + prettyPrint.getPrettyPrint());

		ExpressionVisitor expressionVisitor = new ExpressionVisitor();

		// Get the left side of the expression
		jCompoundAssignmentExpression.left().accept(expressionVisitor);
		AlloyExpression leftSide = expressionVisitor.getAlloyExpression();

		jCompoundAssignmentExpression.right().accept(expressionVisitor);
		AlloyExpression rightSide = expressionVisitor.getAlloyExpression();

		// Create an array of alloy expression types

		CType left_type = jCompoundAssignmentExpression.left().getType();
		CType right_type = jCompoundAssignmentExpression.right().getType();

		CTypeAdapter ctype_adapter = new CTypeAdapter();

		JType left_alloy_type = ctype_adapter.translate(left_type);
		JType right_alloy_type = ctype_adapter.translate(right_type);

		JType[] expression_types = new JType[] { left_alloy_type, right_alloy_type };

		// Create an array of AlloyExpression
		AlloyExpression[] alloyExpressions = new AlloyExpression[] { leftSide, rightSide };

		AlloyExpression finalAlloyExpression = (AlloyExpression) JavaOperatorSolver.getAlloyBinaryExpression(expression_types, alloyExpressions,
				jCompoundAssignmentExpression.oper());

		JStatement jStatement = new JAssignment(leftSide, finalAlloyExpression);
		jStatement = BlockStatementSolver.getSurroundedStatement(jStatement, this.isTryCatchBlock);
		programBuffer.appendProgram(jStatement);

	}

	@Override
	public void visitIfStatement(JIfStatement jIfStatement) {
		jIfStatement.accept(prettyPrint);
		log.debug("Visiting: " + jIfStatement.getClass().getName());
		log.debug("Statement:\n" + prettyPrint.getPrettyPrint());

		if (this.isTryCatchBlock) {
			programBuffer.openIf(BlockStatementSolver.getTryCatchSurrounderCondition());
		}

		ExpressionVisitor expressionVisitor = new ExpressionVisitor();
		AlloyFormula alloyFormula = ExpressionSolver.getConditionAsAlloyFormula(expressionVisitor, jIfStatement.cond());
		programBuffer.openIf(alloyFormula);

		jIfStatement.thenClause().accept(this);
		programBuffer.switchToElseIf();
		if (jIfStatement.elseClause() != null) {
			jIfStatement.elseClause().accept(this);
		} else {
			programBuffer.skip();
		}

		programBuffer.closeIf();

		if (this.isTryCatchBlock) {
			programBuffer.closeIf();
		}
	}

	@Override
	public void visitJmlAssertStatement(JmlAssertStatement jmlAssertStatement) {
		jmlAssertStatement.accept(prettyPrint);
		log.debug("Visiting: " + jmlAssertStatement.getClass().getName());
		log.debug("Statement: " + prettyPrint.getPrettyPrint());

		JmlExpressionVisitor jmlExpressionVisitor = new JmlExpressionVisitor(Instant.PRE_INSTANT);
		jmlAssertStatement.accept(jmlExpressionVisitor);

		AlloyFormula assertFormula = jmlExpressionVisitor.getAlloyFormula();
		JAssert jAssert = new JAssert(assertFormula);

		JStatement jStatement = BlockStatementSolver.getSurroundedStatement(jAssert, this.isTryCatchBlock);

		programBuffer.appendProgram(jStatement);

	}

	@Override
	public void visitJmlAssignmentStatement(JmlAssignmentStatement jmlAssignamentStatement) {
		jmlAssignamentStatement.accept(prettyPrint);
		log.debug("Visiting: " + jmlAssignamentStatement.getClass().getName());
		log.debug("Statement: " + prettyPrint.getPrettyPrint());

		JExpressionStatement expressionStatement = jmlAssignamentStatement.assignmentStatement();
		JAssignmentExpression assignmentExpression = (JAssignmentExpression) expressionStatement.getExpression();

		assignmentExpression.accept(this);
	}

	@Override
	public void visitJmlAssumeStatement(JmlAssumeStatement jmlAssumeStatement) {
		jmlAssumeStatement.accept(prettyPrint);
		log.debug("Visiting: " + jmlAssumeStatement.getClass().getName());
		log.debug("Statement: " + prettyPrint.getPrettyPrint());

		JmlExpressionVisitor jmlExpressionVisitor = new JmlExpressionVisitor(Instant.PRE_INSTANT);
		jmlAssumeStatement.accept(jmlExpressionVisitor);

		AlloyFormula assumeFormula = jmlExpressionVisitor.getAlloyFormula();
		JAssume jAssume = new JAssume(assumeFormula);

		JStatement jStatement = BlockStatementSolver.getSurroundedStatement(jAssume, this.isTryCatchBlock);

		programBuffer.appendProgram(jStatement);
	}

	@Override
	public void visitJmlLoopStatement(JmlLoopStatement jmlLoopStatement) {
		jmlLoopStatement.accept(prettyPrint);
		log.debug("Visiting: " + jmlLoopStatement.getClass().getName());
		log.debug("Statement: " + prettyPrint.getPrettyPrint());

		JmlExpressionVisitor jmlExpressionVisitor = new JmlExpressionVisitor(Instant.PRE_INSTANT);

		if (jmlLoopStatement.loopInvariants() != null) {
			for (JmlLoopInvariant jmlLoopInvariant : jmlLoopStatement.loopInvariants()) {
				jmlLoopInvariant.accept(jmlExpressionVisitor);
				this.loopInvariants.push(jmlExpressionVisitor.getAlloyFormula());
			}
		}

		jmlLoopStatement.loopStmt().accept(this);
	}

	@Override
	public void visitJmlSetStatement(JmlSetStatement jmlSetStatement) {
		jmlSetStatement.assignmentExpression().accept(this);
	}

	@Override
	public void visitMethodCallExpression(JMethodCallExpression jMethodCallExpression) {
		jMethodCallExpression.accept(prettyPrint);
		log.debug("Visiting: " + jMethodCallExpression.getClass().getName());
		log.debug("Statement: " + prettyPrint.getPrettyPrint());

		ExpressionVisitor expressionVisitor = new ExpressionVisitor();
		jMethodCallExpression.accept(expressionVisitor);

		programBuffer.appendProgram(expressionVisitor.getAlloyProgram());
	}

	@Override
	public void visitPostfixExpression(JPostfixExpression jPostfixExpression) {
		jPostfixExpression.accept(prettyPrint);
		log.debug("Visiting: " + jPostfixExpression.getClass().getName());
		log.debug("Statement: " + prettyPrint.getPrettyPrint());

		ExpressionVisitor expressionVisitor = new ExpressionVisitor();

		// Get the left side of the expression
		jPostfixExpression.expr().accept(expressionVisitor);
		AlloyExpression leftSide = expressionVisitor.getAlloyExpression();

		// Create an array of AlloyExpression
		AlloyExpression[] alloyExpressions = new AlloyExpression[2];
		alloyExpressions[0] = leftSide;
		if (TacoConfigurator.getInstance().getUseJavaArithmetic() == true) {
			alloyExpressions[1] = JavaPrimitiveIntegerValue.getInstance().toJavaPrimitiveIntegerLiteral(1);
		} else {
			alloyExpressions[1] = new ExprIntLiteral(1);
		}

		AlloyExpression finalAlloyExpression = null;
		switch (jPostfixExpression.oper()) {
		case Constants.OPE_POSTINC: {
			if (TacoConfigurator.getInstance().getUseJavaArithmetic() == true) {
				finalAlloyExpression = JExpressionFactory.fun_java_primitive_integer_value_add(alloyExpressions);
			} else {
				finalAlloyExpression = JExpressionFactory.alloy_int_add(alloyExpressions);
			}
			break;
		}
		case Constants.OPE_POSTDEC:
			if (TacoConfigurator.getInstance().getUseJavaArithmetic() == true) {
				finalAlloyExpression = JExpressionFactory.fun_java_primitive_integer_value_sub(alloyExpressions);
			} else {
				finalAlloyExpression = JExpressionFactory.alloy_int_sub(alloyExpressions);
			}
		}

		JStatement jStatement = new JAssignment(leftSide, finalAlloyExpression);
		jStatement = BlockStatementSolver.getSurroundedStatement(jStatement, this.isTryCatchBlock);
		programBuffer.appendProgram(jStatement);
	}

	@Override
	public void visitReturnStatement(JReturnStatement returnStatement) {
		returnStatement.accept(prettyPrint);
		log.debug("Visiting: " + returnStatement.getClass().getName());
		log.debug("Statement: " + prettyPrint.getPrettyPrint());

		if (returnStatement.expr() != null) {
			ExpressionVisitor expressionVisitor = new ExpressionVisitor();
			returnStatement.expr().accept(expressionVisitor);
			AlloyVariable returnVariable = AlloyVariable.buildAlloyVariable("return");
			AlloyExpression returnValue = null;
			if (expressionVisitor.isAlloyExpression()) {
				returnValue = expressionVisitor.getAlloyExpression();
			} else {
				returnValue = new ExprIfCondition(expressionVisitor.getAlloyFormula(), JExpressionFactory.TRUE_EXPRESSION, JExpressionFactory.FALSE_EXPRESSION);
			}

			if (this.isTryCatchBlock) {
				programBuffer.openIf(BlockStatementSolver.getTryCatchSurrounderCondition());
			}

			programBuffer.assign(returnVariable, returnValue);
			programBuffer.assign(JExpressionFactory.EXIT_REACHED_VARIABLE, JExpressionFactory.TRUE_EXPRESSION);

			if (this.isTryCatchBlock) {
				programBuffer.closeIf();
			}
		}

		// this.isReturnPresent.push(true);
	}

	@Override
	public void visitThrowStatement(JThrowStatement jThrowStatement) {
		jThrowStatement.accept(prettyPrint);
		log.debug("Visiting: " + jThrowStatement.getClass().getName());
		log.debug("Statement: " + prettyPrint.getPrettyPrint());

		ExpressionVisitor expressionVisitor = new ExpressionVisitor();
		AlloyVariable lvalue = JExpressionFactory.THROW_VARIABLE;
		expressionVisitor.setLeftAssignmentExpression(ExprVariable.buildExprVariable(lvalue));

		jThrowStatement.expr().accept(expressionVisitor);
		// if it is an then I need to assign that expression to the Throw
		// variable, otherwise there is a
		// object creation statement, so I need to add the corresponding object
		// creation and constructor call statements to the program.
		if (expressionVisitor.isAlloyExpression()) {
			AlloyExpression rvalue = expressionVisitor.getAlloyExpression();
			programBuffer.assign(lvalue, rvalue);
			programBuffer.assign(JExpressionFactory.EXIT_REACHED_VARIABLE, JExpressionFactory.TRUE_EXPRESSION);
		} else {
			JStatement objectCreationStatements = expressionVisitor.getAlloyProgram();
			programBuffer.appendProgram(objectCreationStatements);
		}
	}

	@Override
	public void visitTryCatchStatement(JTryCatchStatement jTryCatchStatement) {
		jTryCatchStatement.accept(prettyPrint);
		log.debug("Visiting: " + jTryCatchStatement.getClass().getName());
		log.debug("Statement: " + prettyPrint.getPrettyPrint());

		BlockStatementsVisitor blockScopeTranslator = new BlockStatementsVisitor();
		// blockScopeTranslator.isTryCatchBlock = true;
		jTryCatchStatement.tryClause().accept(blockScopeTranslator);
		// blockScopeTranslator.isTryCatchBlock = false;

		programBuffer.appendProgram(blockScopeTranslator.getJAlloyProgram());

		AlloyFormula condition = new EqualsFormula(JExpressionFactory.THROW_EXPRESSION, JExpressionFactory.NULL_EXPRESSION);
		AlloyFormula notCondition = new NotFormula(condition);

		programBuffer.openIf(notCondition);

		for (int x = 0; x < jTryCatchStatement.catchClauses().length; x++) {
			jTryCatchStatement.catchClauses()[x].accept(this);

		}

		programBuffer.closeIf();

	}

	@Override
	public void visitTryFinallyStatement(JTryFinallyStatement jTryFinallyStatement) {
		jTryFinallyStatement.accept(prettyPrint);
		log.debug("Visiting: " + jTryFinallyStatement.getClass().getName());
		log.debug("Statement: " + prettyPrint.getPrettyPrint());

		jTryFinallyStatement.tryClause().accept(this);

		if (jTryFinallyStatement.finallyClause() != null) {
			BlockStatementsVisitor blockStatementsTranslator = new BlockStatementsVisitor();
			jTryFinallyStatement.finallyClause().accept(blockStatementsTranslator);
			programBuffer.appendProgram(blockStatementsTranslator.getJAlloyProgram());
		}
	}

	@Override
	public void visitVariableDefinition(JVariableDefinition jVariableDefinition) {
		jVariableDefinition.accept(prettyPrint);
		log.debug("Visiting: " + jVariableDefinition.getClass().getName());
		log.debug("Statement: " + prettyPrint.getPrettyPrint());

		// Create an AlloyVariable from variable name
		AlloyVariable alloy_variable = buildAlloyVariable(jVariableDefinition.ident());

		// extract the variable type and convert it to and Alloy variable type.
		CTypeAdapter cTypeAdapter = new CTypeAdapter();
		JType variableType = cTypeAdapter.translate(jVariableDefinition.getType());

		// Declare the variable into Alloy program buffer.
		programBuffer.declare(alloy_variable, variableType);

		// If the declared variable has a initial value assigned, the we need to
		// parse this value,
		// for that purpose we use the ExpressionVisitior.
		if (jVariableDefinition.expr() != null) {
			if (this.isTryCatchBlock) {
				programBuffer.openIf(BlockStatementSolver.getTryCatchSurrounderCondition());
			}

			ExpressionVisitor expressionVisitor = new ExpressionVisitor();

			AlloyExpression variableAsExpression = AlloyExpression.asAlloyExpression(Collections.singletonList(alloy_variable)).get(0);
			expressionVisitor.setLeftAssignmentExpression(variableAsExpression);

			// If the variable which is going to be modified is an instance
			// variable, then it should be added into de modified variables list
			if (variableAsExpression instanceof ExprJoin && ((ExprJoin) variableAsExpression).getLeft().equals(JExpressionFactory.THIS_EXPRESSION)) {
				this.instanceModifiedVariables.add(variableAsExpression);
			}

			if (jVariableDefinition.expr() instanceof JMinusExpression) {

				JMinusExpression minusExpression = (JMinusExpression) jVariableDefinition.expr();

				this.visitMinusExpression(minusExpression);

				AlloyExpression initializer = expressions.pop();
				programBuffer.assign(alloy_variable, initializer);

			} else if (jVariableDefinition.expr() instanceof JAddExpression) {

				JAddExpression addExpression = (JAddExpression) jVariableDefinition.expr();

				this.visitAddExpression(addExpression);

				AlloyExpression initializer = expressions.pop();
				programBuffer.assign(alloy_variable, initializer);

			} else if (jVariableDefinition.expr() instanceof JDivideExpression) {

				JDivideExpression divExpression = (JDivideExpression) jVariableDefinition.expr();

				this.visitDivideExpression(divExpression);

				AlloyExpression initializer = expressions.pop();
				programBuffer.assign(alloy_variable, initializer);

			} else if (jVariableDefinition.expr() instanceof JMultExpression) {

				JMultExpression multExpression = (JMultExpression) jVariableDefinition.expr();

				this.visitMultExpression(multExpression);

				AlloyExpression initializer = expressions.pop();
				programBuffer.assign(alloy_variable, initializer);

			} else if (jVariableDefinition.expr() instanceof JModuloExpression) {

				JModuloExpression moduloExpression = (JModuloExpression) jVariableDefinition.expr();

				this.visitModuloExpression(moduloExpression);

				AlloyExpression initializer = expressions.pop();
				programBuffer.assign(alloy_variable, initializer);

			} else {

				jVariableDefinition.expr().accept(expressionVisitor);
				// If the variable was initialized by a program call
				if (jVariableDefinition.expr() instanceof JMethodCallExpression || jVariableDefinition.expr() instanceof JNewObjectExpression
						|| jVariableDefinition.expr() instanceof JNewArrayExpression) {

					JStatement initializer = expressionVisitor.getAlloyProgram();
					programBuffer.appendProgram(initializer);
				} else {
					AlloyExpression initializer = expressionVisitor.getAlloyExpression();
					programBuffer.assign(alloy_variable, initializer);

				}
			}

			if (this.isTryCatchBlock) {
				programBuffer.closeIf();
			}
		}

	}

	@Override
	public void visitWhileStatement(JWhileStatement jWhileStatement) {
		jWhileStatement.accept(prettyPrint);
		log.debug("Visiting: " + JWhileStatement.class);
		log.debug("Statement: \n" + prettyPrint.getPrettyPrint());

		ExpressionVisitor expressionVisitor = new ExpressionVisitor();
		jWhileStatement.cond().accept(expressionVisitor);
		AlloyFormula alloyFormula = expressionVisitor.getAlloyFormula();

		// this will surround the while statement with the try catch block
		// verification
		if (this.isTryCatchBlock) {
			AlloyFormula trySurrounderCondition = BlockStatementSolver.getTryCatchSurrounderCondition();
			alloyFormula = new AndFormula(alloyFormula, trySurrounderCondition);
		}

		AlloyFormula finalLoopInvariant = null;
		if (!this.loopInvariants.empty()) {
			finalLoopInvariant = this.loopInvariants.pop();
			while (!this.loopInvariants.empty()) {
				finalLoopInvariant = new AndFormula(finalLoopInvariant, this.loopInvariants.pop());
			}
			// } else {
			// //QQ DOB::Check with GG and/or JPG. What happens if not exists
			// invariant loop?
			// //if "loop invariant" not exists put a "true" as loop invariant.
			// //This avoid a
			// "null pointer exception in "ar.edu.taco.ast.DynJAlloyMutator.visit(DynJAlloyMutator.java:169)"
			// finalLoopInvariant = new
			// EqualsFormula(JExpressionFactory.TRUE_EXPRESSION,JExpressionFactory.TRUE_EXPRESSION);
		}

		programBuffer.openWhile(alloyFormula, finalLoopInvariant == null ? null : new JLoopInvariant(finalLoopInvariant));
		jWhileStatement.body().accept(this);
		programBuffer.closeWhile();
	}

	@Override
	public void visitDivideExpression(JDivideExpression divExpression) {

		AlloyExpression rvalue;
		ExpressionVisitor expressionVisitor = new ExpressionVisitor();
		if (TacoConfigurator.getInstance().getUseJavaArithmetic() == true) {

			divExpression.left().accept(expressionVisitor);
			AlloyExpression left_div_expr = expressionVisitor.getAlloyExpression();

			divExpression.right().accept(expressionVisitor);
			AlloyExpression right_div_expr = expressionVisitor.getAlloyExpression();

			CType left_type = divExpression.left().getType();
			CType right_type = divExpression.right().getType();

			CTypeAdapter type_Adapter = new CTypeAdapter();
			JType left_alloy_type = type_Adapter.translate(left_type);
			JType right_alloy_type = type_Adapter.translate(right_type);

			if ((left_alloy_type.equals(JSignatureFactory.JAVA_PRIMITIVE_INTEGER_VALUE))
					&& (right_alloy_type.equals(JSignatureFactory.JAVA_PRIMITIVE_INTEGER_VALUE))) {

				DivAuxiliaryConstants divAuxiliaryConstants = AuxiliaryConstantsFactory.build_integer_divide_auxiliary_constants(left_div_expr, right_div_expr);

				for (JStatement stmt : divAuxiliaryConstants.statements.getBlock()) {
					programBuffer.appendProgram(stmt);
				}

				rvalue = divAuxiliaryConstants.result_value;
				expressions.push(rvalue);
			} else if ((left_alloy_type.equals(JSignatureFactory.JAVA_PRIMITIVE_LONG_VALUE))
					&& (right_alloy_type.equals(JSignatureFactory.JAVA_PRIMITIVE_LONG_VALUE))) {

				DivAuxiliaryConstants divAuxiliaryConstants = AuxiliaryConstantsFactory.build_long_divide_auxiliary_constants(left_div_expr, right_div_expr);

				for (JStatement stmt : divAuxiliaryConstants.statements.getBlock()) {
					programBuffer.appendProgram(stmt);
				}

				rvalue = divAuxiliaryConstants.result_value;

			} else if ((left_alloy_type.equals(JSignatureFactory.JAVA_PRIMITIVE_FLOAT_VALUE))
					&& (right_alloy_type.equals(JSignatureFactory.JAVA_PRIMITIVE_FLOAT_VALUE))) {

				DivAuxiliaryConstants divAuxiliaryConstants = AuxiliaryConstantsFactory.build_float_divide_auxiliary_constants(left_div_expr, right_div_expr);

				for (JStatement stmt : divAuxiliaryConstants.statements.getBlock()) {
					programBuffer.appendProgram(stmt);
				}

				rvalue = divAuxiliaryConstants.result_value;

			} else {
				throw new TacoException("Cannot multiply elements from types " + left_alloy_type + " and " + right_alloy_type);
			}

		} else {

			divExpression.accept(expressionVisitor);
			rvalue = expressionVisitor.getAlloyExpression();

		}

		this.expressions.push(rvalue);
	}

	@Override
	public void visitModuloExpression(JModuloExpression moduloExpression) {

		AlloyExpression rvalue;
		ExpressionVisitor expressionVisitor = new ExpressionVisitor();
		if (TacoConfigurator.getInstance().getUseJavaArithmetic() == true) {

			moduloExpression.left().accept(expressionVisitor);
			AlloyExpression left_rem_expr = expressionVisitor.getAlloyExpression();

			moduloExpression.right().accept(expressionVisitor);
			AlloyExpression right_rem_expr = expressionVisitor.getAlloyExpression();

			CType left_type = moduloExpression.left().getType();
			CType right_type = moduloExpression.right().getType();

			CTypeAdapter type_Adapter = new CTypeAdapter();
			JType left_alloy_type = type_Adapter.translate(left_type);
			JType right_alloy_type = type_Adapter.translate(right_type);

			if ((left_alloy_type.equals(JSignatureFactory.JAVA_PRIMITIVE_INTEGER_VALUE))
					&& (right_alloy_type.equals(JSignatureFactory.JAVA_PRIMITIVE_INTEGER_VALUE))) {

				DivAuxiliaryConstants remainderAuxiliaryConstants = AuxiliaryConstantsFactory.build_integer_divide_auxiliary_constants(left_rem_expr,
						right_rem_expr);

				for (JStatement stmt : remainderAuxiliaryConstants.statements.getBlock()) {
					programBuffer.appendProgram(stmt);
				}

				rvalue = remainderAuxiliaryConstants.remainder;

			} else if ((left_alloy_type.equals(JSignatureFactory.JAVA_PRIMITIVE_LONG_VALUE))
					&& (right_alloy_type.equals(JSignatureFactory.JAVA_PRIMITIVE_LONG_VALUE))) {

				DivAuxiliaryConstants remainderAuxiliaryConstants = AuxiliaryConstantsFactory.build_long_divide_auxiliary_constants(left_rem_expr,
						right_rem_expr);

				for (JStatement stmt : remainderAuxiliaryConstants.statements.getBlock()) {
					programBuffer.appendProgram(stmt);
				}


				rvalue = remainderAuxiliaryConstants.remainder;

			} else {
				throw new TacoException("Cannot modulo elements from types " + left_alloy_type + " and " + right_alloy_type);

			}
		} else {

			moduloExpression.accept(expressionVisitor);
			rvalue = expressionVisitor.getAlloyExpression();

		}

		this.expressions.push(rvalue);

	}

	@Override
	public void visitMultExpression(JMultExpression multExpression) {

		AlloyExpression rvalue;
		ExpressionVisitor expressionVisitor = new ExpressionVisitor();
		if (TacoConfigurator.getInstance().getUseJavaArithmetic() == true) {

			multExpression.left().accept(expressionVisitor);
			AlloyExpression left_mul_expr = expressionVisitor.getAlloyExpression();

			multExpression.right().accept(expressionVisitor);
			AlloyExpression right_mul_expr = expressionVisitor.getAlloyExpression();

			CType left_type = multExpression.left().getType();
			CType right_type = multExpression.right().getType();

			CTypeAdapter type_Adapter = new CTypeAdapter();
			JType left_alloy_type = type_Adapter.translate(left_type);
			JType right_alloy_type = type_Adapter.translate(right_type);

			if ((left_alloy_type.equals(JSignatureFactory.JAVA_PRIMITIVE_INTEGER_VALUE))
					&& (right_alloy_type.equals(JSignatureFactory.JAVA_PRIMITIVE_INTEGER_VALUE))) {

				MultAuxiliaryConstants mulAuxiliaryConstants = AuxiliaryConstantsFactory.build_integer_mult_auxiliary_constants(left_mul_expr, right_mul_expr);

				for (JStatement	 stmt : mulAuxiliaryConstants.statements.getBlock()) {
					programBuffer.appendProgram(stmt);
				}
				
				rvalue = mulAuxiliaryConstants.result_value;

			} else if ((left_alloy_type.equals(JSignatureFactory.JAVA_PRIMITIVE_LONG_VALUE))
					&& (right_alloy_type.equals(JSignatureFactory.JAVA_PRIMITIVE_LONG_VALUE))) {

				MultAuxiliaryConstants mulAuxiliaryConstants = AuxiliaryConstantsFactory.build_long_mult_auxiliary_constants(left_mul_expr, right_mul_expr);

				for (JStatement	 stmt : mulAuxiliaryConstants.statements.getBlock()) {
					programBuffer.appendProgram(stmt);
				}

				rvalue = mulAuxiliaryConstants.result_value;

			} else if ((left_alloy_type.equals(JSignatureFactory.JAVA_PRIMITIVE_FLOAT_VALUE))
					&& (right_alloy_type.equals(JSignatureFactory.JAVA_PRIMITIVE_FLOAT_VALUE))) {

				MultAuxiliaryConstants mulAuxiliaryConstants = AuxiliaryConstantsFactory.build_float_mult_auxiliary_constants(left_mul_expr, right_mul_expr);

				for (JStatement	 stmt : mulAuxiliaryConstants.statements.getBlock()) {
					programBuffer.appendProgram(stmt);
				}

				rvalue = mulAuxiliaryConstants.result_value;

			} else {
				throw new TacoException("Cannot multiply elements from types " + left_alloy_type + " and " + right_alloy_type);
			}

		} else {

			multExpression.accept(expressionVisitor);
			rvalue = expressionVisitor.getAlloyExpression();

		}

		this.expressions.push(rvalue);

	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public void visitAddExpression(JAddExpression addExpression) {

		AlloyExpression rvalue;
		ExpressionVisitor expressionVisitor = new ExpressionVisitor();
		if (TacoConfigurator.getInstance().getUseJavaArithmetic() == true) {

			addExpression.left().accept(expressionVisitor);
			AlloyExpression left_mul_expr = expressionVisitor.getAlloyExpression();

			addExpression.right().accept(expressionVisitor);
			AlloyExpression right_mul_expr = expressionVisitor.getAlloyExpression();

			CType left_type = addExpression.left().getType();
			CType right_type = addExpression.right().getType();

			CTypeAdapter type_Adapter = new CTypeAdapter();
			JType left_alloy_type = type_Adapter.translate(left_type);
			JType right_alloy_type = type_Adapter.translate(right_type);

			if ((left_alloy_type.equals(JSignatureFactory.JAVA_PRIMITIVE_FLOAT_VALUE))
					&& (right_alloy_type.equals(JSignatureFactory.JAVA_PRIMITIVE_FLOAT_VALUE))) {

				AddAuxiliaryConstants addAuxiliaryConstants = AuxiliaryConstantsFactory.build_float_add_auxiliary_constants(left_mul_expr, right_mul_expr);

				for (JStatement stmt : addAuxiliaryConstants.statements.getBlock()) {
					programBuffer.appendProgram(stmt);
				}


				rvalue = addAuxiliaryConstants.result_value;

			} else {

				addExpression.accept(expressionVisitor);
				rvalue = expressionVisitor.getAlloyExpression();

			}

		} else {

			addExpression.accept(expressionVisitor);
			rvalue = expressionVisitor.getAlloyExpression();

		}

		this.expressions.push(rvalue);

	}

	@Override
	public void visitMinusExpression(JMinusExpression minusExpression) {

		AlloyExpression rvalue;
		ExpressionVisitor expressionVisitor = new ExpressionVisitor();
		if (TacoConfigurator.getInstance().getUseJavaArithmetic() == true) {

			minusExpression.left().accept(expressionVisitor);
			AlloyExpression left_mul_expr = expressionVisitor.getAlloyExpression();

			minusExpression.right().accept(expressionVisitor);
			AlloyExpression right_mul_expr = expressionVisitor.getAlloyExpression();

			CType left_type = minusExpression.left().getType();
			CType right_type = minusExpression.right().getType();

			CTypeAdapter type_Adapter = new CTypeAdapter();
			JType left_alloy_type = type_Adapter.translate(left_type);
			JType right_alloy_type = type_Adapter.translate(right_type);

			if ((left_alloy_type.equals(JSignatureFactory.JAVA_PRIMITIVE_FLOAT_VALUE))
					&& (right_alloy_type.equals(JSignatureFactory.JAVA_PRIMITIVE_FLOAT_VALUE))) {

				MinusAuxiliaryConstants minusAuxiliaryConstants = AuxiliaryConstantsFactory.build_float_sub_auxiliary_constants(left_mul_expr, right_mul_expr);

				for (JStatement stmt : minusAuxiliaryConstants.statements.getBlock()) {
					programBuffer.appendProgram(stmt);
				}

				rvalue = minusAuxiliaryConstants.result_value;

			} else {

				minusExpression.accept(expressionVisitor);
				rvalue = expressionVisitor.getAlloyExpression();

			}

		} else {

			minusExpression.accept(expressionVisitor);
			rvalue = expressionVisitor.getAlloyExpression();

		}

		this.expressions.push(rvalue);

	}
}
