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

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.jmlspecs.checker.JmlAssertStatement;
import org.jmlspecs.checker.JmlAssignableClause;
import org.jmlspecs.checker.JmlAssumeStatement;
import org.jmlspecs.checker.JmlConstraint;
import org.jmlspecs.checker.JmlEnsuresClause;
import org.jmlspecs.checker.JmlExceptionalBehaviorSpec;
import org.jmlspecs.checker.JmlExceptionalSpecBody;
import org.jmlspecs.checker.JmlExceptionalSpecCase;
import org.jmlspecs.checker.JmlFieldDeclaration;
import org.jmlspecs.checker.JmlGenericSpecBody;
import org.jmlspecs.checker.JmlGenericSpecCase;
import org.jmlspecs.checker.JmlInformalExpression;
import org.jmlspecs.checker.JmlInvariant;
import org.jmlspecs.checker.JmlMapsIntoClause;
import org.jmlspecs.checker.JmlNormalBehaviorSpec;
import org.jmlspecs.checker.JmlNormalSpecBody;
import org.jmlspecs.checker.JmlNormalSpecCase;
import org.jmlspecs.checker.JmlOldExpression;
import org.jmlspecs.checker.JmlPredicate;
import org.jmlspecs.checker.JmlReachExpression;
import org.jmlspecs.checker.JmlRelationalExpression;
import org.jmlspecs.checker.JmlRepresentsDecl;
import org.jmlspecs.checker.JmlRequiresClause;
import org.jmlspecs.checker.JmlResultExpression;
import org.jmlspecs.checker.JmlSignalsClause;
import org.jmlspecs.checker.JmlSignalsOnlyClause;
import org.jmlspecs.checker.JmlSpecBodyClause;
import org.jmlspecs.checker.JmlSpecExpression;
import org.jmlspecs.checker.JmlSpecQuantifiedExpression;
import org.jmlspecs.checker.JmlStoreRef;
import org.jmlspecs.checker.JmlStoreRefExpression;
import org.jmlspecs.checker.JmlStoreRefKeyword;
import org.jmlspecs.checker.JmlTypeExpression;
import org.multijava.mjc.CClassType;
import org.multijava.mjc.CField;
import org.multijava.mjc.CType;
import org.multijava.mjc.JArrayAccessExpression;
import org.multijava.mjc.JArrayLengthExpression;
import org.multijava.mjc.JClassFieldExpression;
import org.multijava.mjc.JEqualityExpression;
import org.multijava.mjc.JExpression;
import org.multijava.mjc.JLocalVariableExpression;
import org.multijava.mjc.JMethodCallExpression;
import org.multijava.mjc.JNameExpression;
import org.multijava.mjc.JThisExpression;
import org.multijava.mjc.JUnaryExpression;
import org.multijava.mjc.JVariableDefinition;

import ar.edu.jdynalloy.JDynAlloyConfig;
import ar.edu.jdynalloy.ast.AlloyIntArrayFactory;
import ar.edu.jdynalloy.ast.JModifies;
import ar.edu.jdynalloy.ast.JPostcondition;
import ar.edu.jdynalloy.ast.JPrecondition;
import ar.edu.jdynalloy.ast.JSpecCase;
import ar.edu.jdynalloy.factory.JExpressionFactory;
import ar.edu.jdynalloy.factory.JPredicateFactory;
import ar.edu.jdynalloy.xlator.JType;
import ar.edu.jdynalloy.xlator.JTypeHelper;
import ar.edu.taco.TacoConfigurator;
import ar.edu.taco.TacoException;
import ar.edu.taco.TacoNotImplementedYetException;
import ar.edu.taco.simplejml.helpers.CTypeAdapter;
import ar.edu.taco.simplejml.helpers.JavaClassNameNormalizer;
import ar.edu.taco.simplejml.helpers.JmlExpressionSolver;
import ar.uba.dc.rfm.alloy.AlloyVariable;
import ar.uba.dc.rfm.alloy.ast.expressions.AlloyExpression;
import ar.uba.dc.rfm.alloy.ast.expressions.ExprConstant;
import ar.uba.dc.rfm.alloy.ast.expressions.ExprJoin;
import ar.uba.dc.rfm.alloy.ast.expressions.ExprSum;
import ar.uba.dc.rfm.alloy.ast.expressions.ExprVariable;
import ar.uba.dc.rfm.alloy.ast.formulas.AlloyFormula;
import ar.uba.dc.rfm.alloy.ast.formulas.AndFormula;
import ar.uba.dc.rfm.alloy.ast.formulas.EqualsFormula;
import ar.uba.dc.rfm.alloy.ast.formulas.ImpliesFormula;
import ar.uba.dc.rfm.alloy.ast.formulas.NotFormula;
import ar.uba.dc.rfm.alloy.ast.formulas.OrFormula;
import ar.uba.dc.rfm.alloy.ast.formulas.QuantifiedFormula;
import ar.uba.dc.rfm.alloy.ast.formulas.QuantifiedFormula.Operator;

/**
 * @author elgaby
 * 
 */
public class JmlExpressionVisitor extends JmlBaseExpressionVisitor {

	private static Logger log = Logger.getLogger(BlockStatementsVisitor.class);

	private Instant instant;

	private boolean hasAssignableClause;

	private List<AlloyFormula> ensuresRedundantly = new ArrayList<AlloyFormula>();

	public JmlExpressionVisitor() {
		this(Instant.PRE_INSTANT);
	}

	public Instant getInstant() {
		return instant;
	}

	public void setInstant(Instant aInstant) {
		this.instant = aInstant;
	}

	public JmlExpressionVisitor(Instant instant) {
		this.instant = instant;
	}

	@Override
	public void visitArrayAccessExpression(
			JArrayAccessExpression jArrayAccessExpression) {

		jArrayAccessExpression.accept(prettyPrint);
		log.debug("Visiting: " + jArrayAccessExpression.getClass().getName());
		log.debug("Statement: " + prettyPrint.getPrettyPrint());

		jArrayAccessExpression.prefix().accept(this);
		AlloyExpression variableReference = this.getAlloyExpression();

		jArrayAccessExpression.accessor().accept(this);

		AlloyExpression arrayIndex = this.getAlloyExpression();

		if (this.getInstant() == Instant.PRE_INSTANT) {
			super.getStack().push(
					AlloyIntArrayFactory.arrayAccess(variableReference,
							arrayIndex));
		} else {
			super.getStack().push(
					AlloyIntArrayFactory.primedArrayAccess(variableReference,
							arrayIndex));
		}

	}

	@Override
	public void visitArrayLengthExpression(
			JArrayLengthExpression jArrayLengthExpression) {
		jArrayLengthExpression.prefix().accept(this);
		AlloyExpression alloyExpression = this.getAlloyExpression();
		if (this.getInstant() == Instant.PRE_INSTANT) {
			super.getStack().push(
					AlloyIntArrayFactory.arrayLength(alloyExpression));
		} else {
			super.getStack().push(
					AlloyIntArrayFactory.primedArrayLength(alloyExpression));
		}
	}

	@Override
	public void visitEqualityExpression(JEqualityExpression jEqualityExpression) {
		JExpression left = jEqualityExpression.left();
		JExpression right = jEqualityExpression.right();

		Instant oldInstant = this.getInstant();

		if (right instanceof JmlOldExpression
				&& ((JmlOldExpression) right).specExpression().expression() instanceof JMethodCallExpression) {
			this.setInstant(Instant.PRE_INSTANT);
			right = ((JmlOldExpression) right).specExpression().expression();
		} else if (left instanceof JmlOldExpression
				&& ((JmlOldExpression) left).specExpression().expression() instanceof JMethodCallExpression) {
			this.setInstant(Instant.PRE_INSTANT);
			left = ((JmlOldExpression) right).specExpression().expression();
		}

		super.visitEqualityExpression(jEqualityExpression);

		this.setInstant(oldInstant);
	}

	@Override
	public void visitFieldExpression(JClassFieldExpression jClassFieldExpression) {
		jClassFieldExpression.prefix().accept(this);
		AlloyExpression e1 = null;
		AlloyExpression e2 = null;
		if (this.isAlloyExpression()) {
			e1 = this.getAlloyExpression();
		} else {
			// if the prefix is a formula is because is a method call, so that
			// formula MUST be a QualifiedAlloyFormula
			QuantifiedFormula quantifiedAlloyFormula = (QuantifiedFormula) this
					.getAlloyFormula();

			AlloyVariable qualifiedVariable = new AlloyVariable(
					quantifiedAlloyFormula.getNames().get(0));

			this.getStack().push(quantifiedAlloyFormula);
			e1 = ExprVariable.buildExprVariable(qualifiedVariable);
		}
		if (jClassFieldExpression.getField().isStatic()) {
			AlloyVariable ident = null;
			if (this.instant == Instant.PRE_INSTANT) {
				ident = new AlloyVariable(e1.toString() + "_"
						+ jClassFieldExpression.ident());
			} else {
				ident = new AlloyVariable(e1.toString() + "_"
						+ jClassFieldExpression.ident(), true);
			}

			e2 = ExprVariable.buildExprVariable(ident);
			e1 = ar.edu.jdynalloy.factory.JExpressionFactory.CLASS_FIELDS;
		} else {
			AlloyVariable ident = null;
			if (this.instant == Instant.PRE_INSTANT) {
				ident = new AlloyVariable(jClassFieldExpression.ident());
			} else {
				ident = new AlloyVariable(jClassFieldExpression.ident(), true);
			}

			e2 = ExprVariable.buildExprVariable(ident);
		}

		ExprJoin exprJoin = ExprJoin.join(e1, e2);
		this.getStack().push(exprJoin);
	}

	@Override
	public void visitJmlAssertStatement(JmlAssertStatement jmlAssertStatement) {
		jmlAssertStatement.predicate().accept(this);
	}

	@Override
	public void visitJmlAssignableClause(JmlAssignableClause jmlAssignableClause) {
		hasAssignableClause = true;
		if (jmlAssignableClause.storeRefs() != null) {
			for (JmlStoreRef storeRef : jmlAssignableClause.storeRefs()) {
				if (storeRef instanceof JmlStoreRefKeyword) {
					if (((JmlStoreRefKeyword) storeRef).isNothing()) {
						jmlMethodDeclarationResult.modifiables.clear();
					} else if (((JmlStoreRefKeyword) storeRef).isEverything()) {
						jmlMethodDeclarationResult.modifiables.add(JModifies
								.buildModifiesEverything());
					} else if (((JmlStoreRefKeyword) storeRef).isNotSpecified()) {
						throw new TacoNotImplementedYetException(
								"Everything keyword is not implemented yet");
					}
				} else if (storeRef instanceof JmlStoreRefExpression) {
					JmlStoreRefExpression storeRefExpression = (JmlStoreRefExpression) storeRef;
					if (storeRefExpression.getName()
							.equals(OBJECT_STATE_STRING)) {
						List<AlloyVariable> variablesInGroupClause = super
								.getBuffer().getInGroupClauses()
								.get(OBJECT_STATE_STRING);
						if (variablesInGroupClause != null) {
							for (AlloyVariable alloyVariable : variablesInGroupClause) {
								AlloyExpression variable = new ExprJoin(
										JExpressionFactory.THIS_EXPRESSION,
										new ExprVariable(alloyVariable));
								jmlMethodDeclarationResult.modifiables
										.add(new JModifies(variable));
							}
						}
					} else if (storeRefExpression.getName().contains(
							OBJECT_STATE_STRING)) {

						String classJavaName = ((JClassFieldExpression) storeRefExpression
								.expression()).prefix().getType().getCClass()
								.getJavaName();
						JavaClassNameNormalizer classNameNormalizer = new JavaClassNameNormalizer(
								classJavaName);

						String normalizedClassName = classNameNormalizer
								.getQualifiedClassName();

						if (super.getModulesObjectState().containsKey(
								normalizedClassName)) {
							AlloyExpression prefixVariable = null;

							JExpression prefix = ((JClassFieldExpression) storeRefExpression
									.expression()).prefix();
							if (prefix != null) {
								prefix.accept(this);
								prefixVariable = this.getAlloyExpression();
							}

							List<String> fieldsNames = this
									.getModulesObjectState().get(
											normalizedClassName);
							for (String fieldName : fieldsNames) {
								AlloyExpression fieldVariable = ExprVariable
										.buildExprVariable(fieldName);
								AlloyExpression variable = new ExprJoin(
										prefixVariable, fieldVariable);

								jmlMethodDeclarationResult.modifiables
										.add(new JModifies(variable));
							}

						} else {
							throw new TacoException(
									"Class "
											+ classJavaName
											+ " must be added in config as a relevant class");
						}
					} else if (storeRefExpression.getName().contains(
							WIDLCARD_STRING)
							&& !(storeRefExpression.expression() instanceof JArrayAccessExpression)) {
						String classJavaName = null;
						AlloyExpression prefixVariable = null;
						if (storeRefExpression.expression() instanceof JLocalVariableExpression) {
							classJavaName = ((JLocalVariableExpression) storeRefExpression
									.expression()).getType().getCClass()
									.getJavaName();
							JExpression prefix = (JLocalVariableExpression) storeRefExpression
									.expression();

							if (prefix != null) {
								prefix.accept(this);
								prefixVariable = this.getAlloyExpression();
							}
						} else if (storeRefExpression.expression() instanceof JThisExpression) {
							classJavaName = ((JThisExpression) storeRefExpression
									.expression()).getType().getCClass()
									.getJavaName();

							prefixVariable = JExpressionFactory.THIS_EXPRESSION;
						} else {
							classJavaName = ((JClassFieldExpression) storeRefExpression
									.expression()).getType().getCClass()
									.getJavaName();
							JExpression prefix = ((JClassFieldExpression) storeRefExpression
									.expression()).prefix();

							if (prefix != null) {
								prefix.accept(this);
								prefixVariable = this.getAlloyExpression();
							}

							JNameExpression sourceName = ((JClassFieldExpression) storeRefExpression
									.expression()).sourceName();
							if (sourceName != null) {
								sourceName.accept(this);
								AlloyExpression sourceExpression = this
										.getAlloyExpression();
								prefixVariable = new ExprJoin(prefixVariable,
										sourceExpression);
							}
						}

						JavaClassNameNormalizer classNameNormalizer = new JavaClassNameNormalizer(
								classJavaName);

						String normalizedClassName = classNameNormalizer
								.getQualifiedClassName();

						if (super.getModulesNoStaticFields().containsKey(
								normalizedClassName)) {
							List<String> fieldsNames = this
									.getModulesNoStaticFields().get(
											normalizedClassName);
							for (String fieldName : fieldsNames) {
								AlloyExpression variable = ExprVariable
										.buildExprVariable(fieldName);
								variable = new ExprJoin(prefixVariable,
										variable);

								jmlMethodDeclarationResult.modifiables
										.add(new JModifies(variable));
							}

						} else {
							throw new TacoException(
									"Class "
											+ classJavaName
											+ " must be added in config as a relevant class");
						}
					} else {
						storeRef.accept(this);
						AlloyExpression storeRefExpr = this
								.getAlloyExpression();

						jmlMethodDeclarationResult.modifiables
								.add(new JModifies(storeRefExpr));
					}
				} else {
					throw new UnsupportedOperationException(
							"Operation: Assignable on " + storeRef.getClass()
									+ " is not supported.");
				}
			}
		}
	}

	@Override
	public void visitJmlAssumeStatement(JmlAssumeStatement jmlAssumeStatement) {
		jmlAssumeStatement.predicate().accept(this);
	}

	@Override
	public void visitJmlConstraint(JmlConstraint jmlConstraint) {
		JmlPredicate jmlPredicate = jmlConstraint.predicate();
		JmlSpecExpression jmlSpecExpression = jmlPredicate.specExpression();
		JExpression jExpression = jmlSpecExpression.expression();

		jExpression.accept(this);
		this.setInstant(instant);
		AlloyFormula constraint = this.getAlloyFormula();

		if (jmlConstraint.isStatic()) {
			jmlClassDeclarationResult.staticConstraints.add(constraint);
		} else {
			jmlClassDeclarationResult.constraints.add(constraint);
		}
	}

	@Override
	public void visitJmlEnsuresClause(JmlEnsuresClause jmlEnsuresClause) {
		if (jmlEnsuresClause.isNotSpecified()) {
			throw new IllegalArgumentException(
					"Ensures clause is not specified.");
		}

		AlloyFormula finalFormula = null;
		// if (!jmlEnsuresClause.isRedundantly()) {
		JmlExpressionVisitor jmlExpressionVisitor = new JmlExpressionVisitor(
				Instant.POST_INSTANT);
		jmlEnsuresClause.predOrNot().accept(jmlExpressionVisitor);
		AlloyFormula ensuresFormula = jmlExpressionVisitor.getAlloyFormula();

		if (this.isNormalBehavior()) {
			AlloyFormula throwFormula = new EqualsFormula(
					JExpressionFactory.PRIMED_THROW_EXPRESSION,
					JExpressionFactory.NULL_EXPRESSION);

			finalFormula = new ImpliesFormula(throwFormula, ensuresFormula);
		} else {
			AlloyFormula throwFormula = new NotFormula(new EqualsFormula(
					JExpressionFactory.PRIMED_THROW_EXPRESSION,
					JExpressionFactory.NULL_EXPRESSION));

			finalFormula = new ImpliesFormula(throwFormula, ensuresFormula);
		}
		// } else {
		// finalFormula =
		// JPredicateFactory.eq(JExpressionFactory.TRUE_EXPRESSION,
		// JExpressionFactory.TRUE_EXPRESSION);
		// }

		if (jmlEnsuresClause.isRedundantly()) {
			ensuresRedundantly.add(finalFormula);
			finalFormula = JPredicateFactory.eq(
					JExpressionFactory.TRUE_EXPRESSION,
					JExpressionFactory.TRUE_EXPRESSION);
		}

		super.getStack().push(finalFormula);

	}

	@Override
	public void visitJmlExceptionalBehaviorSpec(
			JmlExceptionalBehaviorSpec jmlExceptionalBehaviorSpec) {
		this.setNormalBehavior(false);

		jmlExceptionalBehaviorSpec.specCase().accept(this);
	}

	public void visitJmlExceptionalSpecBody(
			JmlExceptionalSpecBody jmlExceptionalSpecBody) {
		if (jmlExceptionalSpecBody.isSpecClauses()) {
			for (JmlSpecBodyClause jmlSpecBodyClause : jmlExceptionalSpecBody
					.specClauses()) {
				jmlSpecBodyClause.accept(this);
			}
		}
	}

	public void visitJmlExceptionalSpecCase(
			JmlExceptionalSpecCase jmlExceptionalSpecCase) {
		List<JPrecondition> requires = new ArrayList<JPrecondition>();
		List<JPostcondition> ensures = new ArrayList<JPostcondition>();
		List<JModifies> modifies = new ArrayList<JModifies>();

		if (jmlExceptionalSpecCase.hasSpecHeader()) {
			for (JmlRequiresClause jmlRequiresClause : jmlExceptionalSpecCase
					.specHeader()) {
				jmlRequiresClause.accept(this);
				AlloyFormula alloyFormula = this.getAlloyFormula();
				JPrecondition jPrecondition = new JPrecondition(alloyFormula);
				requires.add(jPrecondition);
			}
		}

		if (jmlExceptionalSpecCase.hasSpecBody()) {
			jmlExceptionalSpecCase.specBody().accept(this);
			while (!super.getStack().isEmpty()) {
				AlloyFormula alloyFormula = this.getAlloyFormula();
				JPostcondition jPostcondition = new JPostcondition(alloyFormula);
				ensures.add(jPostcondition);
			}
		}

		JSpecCase specCase = new JSpecCase(requires, ensures, modifies);

		this.jmlMethodDeclarationResult.jSpecCases.add(specCase);
	}

	@Override
	public void visitJmlFieldDeclaration(JmlFieldDeclaration jmlFieldDeclaration) {
		if (jmlFieldDeclaration.isModel()) {
			CField field = jmlFieldDeclaration.getField();

			String ident = field.getIdent();
			CTypeAdapter adapter = new CTypeAdapter();
			JType jType = adapter.translate(field.getType());

			if (field.isStatic()) {
				jmlClassDeclarationResult.staticModelFields.put(
						new AlloyVariable(ident), jType);
			} else {
				jmlClassDeclarationResult.modelFields.put(new AlloyVariable(
						ident), jType);
			}
		}
	}

	@Override
	public void visitJmlGenericSpecBody(JmlGenericSpecBody jmlGenericSpecBody) {

		if (jmlGenericSpecBody.isSpecClauses()) {
			for (JmlSpecBodyClause jmlSpecBodyClause : jmlGenericSpecBody
					.specClauses()) {
				jmlSpecBodyClause.accept(this);
			}
		}
	}

	@Override
	public void visitJmlGenericSpecCase(JmlGenericSpecCase jmlGenericSpecCase) {
		this.hasAssignableClause = false;
		List<JPrecondition> requires = new ArrayList<JPrecondition>();
		List<JPostcondition> ensures = new ArrayList<JPostcondition>();
		List<JModifies> modifies = new ArrayList<JModifies>();

		if (jmlGenericSpecCase.hasSpecHeader()) {
			for (JmlRequiresClause jmlRequiresClause : jmlGenericSpecCase
					.specHeader()) {
				jmlRequiresClause.accept(this);
				AlloyFormula alloyFormula = this.getAlloyFormula();
				JPrecondition jPrecondition = new JPrecondition(alloyFormula);
				requires.add(jPrecondition);
			}
		}

		if (jmlGenericSpecCase.hasSpecBody()) {
			jmlGenericSpecCase.specBody().accept(this);
			while (!super.getStack().isEmpty()) {
				AlloyFormula alloyFormula = this.getAlloyFormula();
				JPostcondition jPostcondition = new JPostcondition(alloyFormula);
				ensures.add(jPostcondition);
			}
		} else {
			AlloyFormula throwFormula = new EqualsFormula(
					JExpressionFactory.PRIMED_THROW_EXPRESSION,
					JExpressionFactory.NULL_EXPRESSION);
			JPostcondition jPostcondition = new JPostcondition(throwFormula);
			ensures.add(jPostcondition);
		}

		// DPD: Default behavior for Assignable
		if (!this.hasAssignableClause) {
			jmlMethodDeclarationResult.modifiables.add(JModifies
					.buildModifiesEverything());
		}

		modifies.addAll(jmlMethodDeclarationResult.modifiables);

		JSpecCase specCase = new JSpecCase(requires, ensures, modifies);

		this.jmlMethodDeclarationResult.jSpecCases.add(specCase);
	}

	@Override
	public void visitJmlInformalExpression(
			JmlInformalExpression jmlInformalExpression) {
		super.getStack().push(
				new EqualsFormula(JExpressionFactory.TRUE_EXPRESSION,
						JExpressionFactory.TRUE_EXPRESSION));
	}

	@Override
	public void visitJmlInvariant(JmlInvariant jmlInvariant) {
		JmlPredicate jmlPredicate = jmlInvariant.predicate();
		JmlSpecExpression jmlSpecExpression = jmlPredicate.specExpression();
		JExpression jExpression = jmlSpecExpression.expression();

		jExpression.accept(this);
		AlloyFormula invariant = this.getAlloyFormula();

		if (jmlInvariant.isStatic()) {
			jmlClassDeclarationResult.staticInvariants.add(invariant);
		} else {
			jmlClassDeclarationResult.invariants.add(invariant);
		}
	}

	@Override
	public void visitJmlMapsIntoClause(JmlMapsIntoClause arg0) {
		// TODO Auto-generated method stub
		super.visitJmlMapsIntoClause(arg0);
	}

	@Override
	public void visitJmlNormalBehaviorSpec(
			JmlNormalBehaviorSpec jmlNormalBehaviorSpec) {
		this.setNormalBehavior(true);

		jmlNormalBehaviorSpec.specCase().accept(this);

	}

	@Override
	public void visitJmlNormalSpecBody(JmlNormalSpecBody jmlNormalSpecBody) {
		if (jmlNormalSpecBody.isSpecClauses()) {
			for (JmlSpecBodyClause jmlSpecBodyClause : jmlNormalSpecBody
					.specClauses()) {
				jmlSpecBodyClause.accept(this);
			}
		}
	}

	public void visitJmlNormalSpecCase(JmlNormalSpecCase jmlNormalSpecCase) {
		List<JPrecondition> requires = new ArrayList<JPrecondition>();
		List<JPostcondition> ensures = new ArrayList<JPostcondition>();
		List<JModifies> modifies = new ArrayList<JModifies>();

		if (jmlNormalSpecCase.hasSpecHeader()) {
			for (JmlRequiresClause jmlRequiresClause : jmlNormalSpecCase
					.specHeader()) {
				jmlRequiresClause.accept(this);
				AlloyFormula alloyFormula = this.getAlloyFormula();
				JPrecondition jPrecondition = new JPrecondition(alloyFormula);
				requires.add(jPrecondition);
			}
		}

		if (jmlNormalSpecCase.hasSpecBody()) {
			jmlNormalSpecCase.specBody().accept(this);
			while (!super.getStack().isEmpty()) {
				AlloyFormula alloyFormula = this.getAlloyFormula();
				JPostcondition jPostcondition = new JPostcondition(alloyFormula);
				ensures.add(jPostcondition);
			}

			// if (ensuresRedundantly.size() > 0) {
			// boolean isFirst = true;
			// AlloyFormula ensuresConjuntion = null;
			// for (int i = 0; i < ensures.size(); i++) {
			// if (isFirst) {
			// ensuresConjuntion = ensures.get(i).getFormula();
			// isFirst = false;
			// } else {
			// ensuresConjuntion = new AndFormula(ensuresConjuntion,
			// ensures.get(i).getFormula());
			// }
			// }
			//
			// isFirst = true;
			// AlloyFormula redundantEnsuresConjuntion = null;
			// for (int i = 0; i < ensuresRedundantly.size(); i++) {
			// if (isFirst) {
			// redundantEnsuresConjuntion = ensuresRedundantly.get(i);
			// isFirst = false;
			// } else {
			// redundantEnsuresConjuntion = new
			// AndFormula(redundantEnsuresConjuntion,
			// ensuresRedundantly.get(i));
			// }
			// }
			//
			// JPostcondition finalPostCondition = new JPostcondition(new
			// ImpliesFormula(ensuresConjuntion, redundantEnsuresConjuntion));
			// ensures.clear();
			// ensures.add(finalPostCondition);
			//
			// ensuresRedundantly.clear();
			// }

		} else {
			AlloyFormula throwFormula = new EqualsFormula(
					JExpressionFactory.PRIMED_THROW_EXPRESSION,
					JExpressionFactory.NULL_EXPRESSION);
			JPostcondition jPostcondition = new JPostcondition(throwFormula);
			ensures.add(jPostcondition);
		}

		modifies.addAll(jmlMethodDeclarationResult.modifiables);

		JSpecCase specCase = new JSpecCase(requires, ensures, modifies);

		this.jmlMethodDeclarationResult.jSpecCases.add(specCase);
	}

	@Override
	public void visitJmlOldExpression(JmlOldExpression jmlOldExpression) {
		this.setInstant(Instant.PRE_INSTANT);
		jmlOldExpression.specExpression().accept(this);
		if (this.isAlloyExpression()) {
			AlloyExpression expr = this.getAlloyExpression();
			super.getStack().push(expr);
		} else {
			AlloyFormula formula = this.getAlloyFormula();
			super.getStack().push(formula);
		}
		this.setInstant(Instant.POST_INSTANT);
	}

	@Override
	public void visitJmlPredicate(JmlPredicate jmlPredicate) {
		jmlPredicate.specExpression().accept(this);
	}

	@Override
	public void visitJmlReachExpression(JmlReachExpression jmlReachExpression) {
		JmlSpecExpression specExpression = jmlReachExpression.specExpression();
		CType referenceType = jmlReachExpression.referenceType();
//		String fieldName = jmlReachExpression.storeRefExpression().getName();
		JmlStoreRefExpression[] refs = jmlReachExpression.storeRefExpressions();
		String[] fieldNames = new String[refs.length];
		for(int i = 0 ; i < refs.length ; i++){
			fieldNames[i] = refs[i].getName();
		}

		specExpression.accept(this);
		AlloyExpression head = super.getAlloyExpression();
		
		CTypeAdapter cTypeAdapter = new CTypeAdapter();
		JType type = cTypeAdapter.translate(referenceType);

		ExprConstant typeConstant = new ExprConstant(null,
				JTypeHelper.getBaseType(type));
//		ExprVariable fieldVariable = JmlExpressionSolver.buildInstantVariable(
//				fieldName, instant);
		
		ExprVariable[] fieldVariables = new ExprVariable[fieldNames.length]; 
		for(int i = 0 ; i < fieldNames.length ; i++){
			fieldVariables[i] = JmlExpressionSolver.buildInstantVariable(
					fieldNames[i], instant);
		}

		AlloyExpression reachExpression;
		if (JDynAlloyConfig.getInstance().getJMLObjectSetToAlloySet()) {
			reachExpression = JExpressionFactory.reach(head, typeConstant,
					fieldVariables);
		} else {
			reachExpression = JExpressionFactory.reach_JMLObjectSet(head,
					typeConstant, fieldVariables);
		}

		super.getStack().push(reachExpression);
	}

	@Override
	public void visitJmlRelationalExpression(
			JmlRelationalExpression jmlRelationalExpression) {
		super.visitRelationalExpression(jmlRelationalExpression);
	}

	@Override
	public void visitJmlRepresentsDecl(JmlRepresentsDecl jmlRepresentsDecl) {
		if (jmlRepresentsDecl.usesAbstractionRelation()) {

			JmlStoreRefExpression storeRef = jmlRepresentsDecl.storeRef();
			JExpression expr = storeRef.expression();
			if (expr == null) {
				throw new IllegalArgumentException(
						"store ref expression not type checked at "
								+ storeRef.getTokenReference());
			}

			expr.accept(this);
			AlloyExpression e = this.getAlloyExpression();

			CType ctype = expr.getType();
			CTypeAdapter typeAdapter = new CTypeAdapter();
			JType exprType = typeAdapter.translate(ctype);

			JmlPredicate predicate = jmlRepresentsDecl.predicate();
			predicate.accept(this);
			AlloyFormula alloyFormula = this.getAlloyFormula();

			jmlClassDeclarationResult.represents.add(new JmlRepresentsData(e,
					exprType, alloyFormula));

		} else if (jmlRepresentsDecl.usesAbstractionFunction()) {
			throw new RuntimeException("function abstraction is not supported.");
		} else {
			throw new RuntimeException("Unknown abstraction represents clause");
		}

	}

	@Override
	public void visitJmlRequiresClause(JmlRequiresClause jmlRequiresClause) {
		if (jmlRequiresClause.isNotSpecified()) {
			throw new IllegalArgumentException(
					"Requires clause is not specified.");
		}

		JmlExpressionVisitor jmlExpressionVisitor = new JmlExpressionVisitor(
				Instant.PRE_INSTANT);
		jmlRequiresClause.predOrNot().accept(jmlExpressionVisitor);

		AlloyFormula requireFormula = null;
		if (jmlExpressionVisitor.isAlloyFormula()) {
			requireFormula = jmlExpressionVisitor.getAlloyFormula();
		} else {
			AlloyExpression alloyExpression = jmlExpressionVisitor
					.getAlloyExpression();
			requireFormula = new EqualsFormula(alloyExpression,
					JExpressionFactory.TRUE_EXPRESSION);
		}

		// AlloyFormula finalFormula = null;
		// if (this.isNormalBehavior()) {
		// AlloyFormula throwFormula = new
		// EqualsFormula(JExpressionFactory.THROW_EXPRESSION,
		// JExpressionFactory.NULL_EXPRESSION);
		//
		// finalFormula = new AndFormula(throwFormula, requireFormula);
		// } else {
		// AlloyFormula throwFormula = new NotFormula(new
		// EqualsFormula(JExpressionFactory.THROW_EXPRESSION,
		// JExpressionFactory.NULL_EXPRESSION));
		//
		// finalFormula = new AndFormula(throwFormula, requireFormula);
		// }

		super.getStack().push(requireFormula);

	}

	@Override
	public void visitJmlResultExpression(JmlResultExpression jmlResultExpression) {
		String returnVarId = JExpressionFactory.RETURN_VARIABLE.toString();
		ExprVariable returnExpr = JmlExpressionSolver.buildInstantVariable(
				returnVarId, instant);

		super.getStack().push(returnExpr);
	}

	@Override
	public void visitJmlSignalsClause(JmlSignalsClause jmlSignalsClause) {
		AlloyExpression throwExpression = JExpressionFactory.PRIMED_THROW_EXPRESSION;
		CTypeAdapter cTypeAdapter = new CTypeAdapter();
		
		JType jtype = cTypeAdapter.translate(
				jmlSignalsClause.type().getErasure());
		String signatureId = jtype.singletonFrom().toString();
				
		AlloyFormula alloyFormula = JPredicateFactory.instanceOf(
				throwExpression, signatureId);

		if (!jmlSignalsClause.isNotSpecified()) {
			jmlSignalsClause.predOrNot().accept(this);

			AlloyFormula predicate = this.getAlloyFormula();

			alloyFormula = new ImpliesFormula(alloyFormula, predicate);
		}

		super.getStack().push(alloyFormula);

	}

	@Override
	public void visitJmlSignalsOnlyClause(
			JmlSignalsOnlyClause jmlSignalsOnlyClause) {
		// FIXME: Cambiar el instanceOf(Throw', NullPointerException+null) por
		// throw = NullPointerException

		if (jmlSignalsOnlyClause.exceptions() != null) {
			AlloyExpression throwExpression = JExpressionFactory.PRIMED_THROW_EXPRESSION;
			CTypeAdapter cTypeAdapter = new CTypeAdapter();
			AlloyFormula finalOrFormula = null;
			for (CClassType cClassType : jmlSignalsOnlyClause.exceptions()) {
				JType jType = cTypeAdapter.translate(cClassType.getErasure());
				String signatureName = jType.dpdTypeNameExtract();
				String signatureId = JExpressionFactory.buildLiteralSingleton(
						signatureName).getConstantId();

				// String signatureId =
				// cTypeAdapter.translate(cClassType.getErasure()).toString();

				AlloyFormula alloyFormula = JPredicateFactory.instanceOf(
						throwExpression, signatureId);

				if (finalOrFormula == null) {
					finalOrFormula = alloyFormula;
				} else {
					finalOrFormula = new OrFormula(finalOrFormula, alloyFormula);
				}
			}

			AlloyFormula finalFormula = null;
			if (this.isNormalBehavior()) {
				AlloyFormula throwFormula = new EqualsFormula(
						JExpressionFactory.PRIMED_THROW_EXPRESSION,
						JExpressionFactory.NULL_EXPRESSION);

				finalFormula = new AndFormula(throwFormula, finalOrFormula);
			} else {
				AlloyFormula throwFormula = new NotFormula(new EqualsFormula(
						JExpressionFactory.PRIMED_THROW_EXPRESSION,
						JExpressionFactory.NULL_EXPRESSION));

				finalFormula = new AndFormula(throwFormula, finalOrFormula);
			}

			super.getStack().push(finalFormula);
		}
	}

	@Override
	public void visitJmlSpecExpression(JmlSpecExpression jmlSpecExpression) {
		jmlSpecExpression.expression().accept(this);
	}

	@Override
	public void visitJmlSpecQuantifiedExpression(
			JmlSpecQuantifiedExpression jmlSpecQuantifiedExpression) {

		List<String> quantifiedNames = new ArrayList<String>();
		List<AlloyExpression> quantifiedExpressions = new ArrayList<AlloyExpression>();
		for (JVariableDefinition varDef : jmlSpecQuantifiedExpression
				.quantifiedVarDecls()) {
			String ident = varDef.ident();
			quantifiedNames.add(ident);

			CType cType = varDef.getType();
			CTypeAdapter cTypeAdapter = new CTypeAdapter();
			JType jType = cTypeAdapter.translate(cType);

			if (TacoConfigurator.getInstance().getBoolean(
					TacoConfigurator.QUALIFIERS_INCLUDES_NULL) == true)
				quantifiedExpressions.add(jType.toAlloyExpr());
			else {
				JType nonNullType = new JType(JTypeHelper.getBaseType(jType));
				quantifiedExpressions.add(nonNullType.toAlloyExpr());
			}
		}

		this.notAllowsPrimedState.addAll(quantifiedNames);

		AlloyFormula impliesPre = null;

		if (jmlSpecQuantifiedExpression.hasPredicate()) {
			JmlPredicate predicate = jmlSpecQuantifiedExpression.predicate();
			predicate.accept(this);
			impliesPre = super.getAlloyFormula();
		}

		JmlSpecExpression expression = jmlSpecQuantifiedExpression
				.specExpression();
		expression.accept(this);
		if (jmlSpecQuantifiedExpression.isExists()
				|| jmlSpecQuantifiedExpression.isForAll()) {
			AlloyFormula impliesPost = this.getAlloyFormula();

			AlloyFormula formula = null;

			Operator operator;
			if (jmlSpecQuantifiedExpression.isExists()) {
				operator = Operator.EXISTS;
				if (impliesPre == null)
					formula = impliesPost;
				else {
					formula = new AndFormula(impliesPre, impliesPost);
				}
				
			} else if (jmlSpecQuantifiedExpression.isForAll()) {
				operator = Operator.FOR_ALL;

				if (impliesPre == null)
					formula = impliesPost;
				else {
					formula = new ImpliesFormula(impliesPre, impliesPost);
				}

     		} else
				throw new IllegalArgumentException("Quantifier not supported "
						+ jmlSpecQuantifiedExpression.toString());

			QuantifiedFormula quantifiedAlloyFormula = new QuantifiedFormula(
					operator, quantifiedNames, quantifiedExpressions, formula);

			super.getStack().push(quantifiedAlloyFormula);
		} else if (jmlSpecQuantifiedExpression.isSum()) {
			AlloyExpression sumExpression = this.getAlloyExpression();

			ExprSum sum = new ExprSum(quantifiedNames, quantifiedExpressions,
					sumExpression);

			super.getStack().push(sum);

		} else {
			throw new TacoNotImplementedYetException(
					"Quantified operator not supported yet");
		}

		this.notAllowsPrimedState.removeAll(quantifiedNames);
	}

	@Override
	public void visitJmlStoreRefExpression(
			JmlStoreRefExpression jmlStoreRefExpression) {
		JmlExpressionVisitor jmlExpressionVisitor = new JmlExpressionVisitor(
				Instant.PRE_INSTANT);
		jmlStoreRefExpression.expression().accept(jmlExpressionVisitor);
		AlloyExpression alloyExpression = jmlExpressionVisitor
				.getAlloyExpression();
		super.getStack().push(alloyExpression);
	}

	@Override
	public void visitJmlTypeExpression(JmlTypeExpression jmlTypeExpression) {
		// type-expression ::= \type ( type )
		//
		// The operator \type can be used to introduce literals of type \TYPE in
		// expressions. An expression of the form \type(T), where T is a type
		// name,
		// has the type \TYPE. Since in JML \TYPE is the same as
		// java.lang.Class, an expression of the form \type(T) means the same
		// thing as T.class,
		// if T is a reference type. If T is a primitive type, then \type(T) is
		// equivalent to the value of the TYPE field of the corresponding
		// reference type.
		// Thus \type(boolean) equals Boolean.TYPE.

		// QQ: TYPE: El problema que tenemos aca es que por ejemplo mandan
		// \type(Object), pero no puedo obtener el qualified name
		// para la clase pasada como parametro... que hacemos, un mapping
		// hardcoded?????
		// Ademas, al igual que en \typeof estamos creando una ExprConstant para
		// devolver el tipo.

		// Respuesta: JMLForge le pide que soot le responda el tipo completo,
		// hay que ver si MJC tiene algun mecanismo similar.
		CType ctype = jmlTypeExpression.type();
		CTypeAdapter cTypeAdapter = new CTypeAdapter();
		JType jType = cTypeAdapter.translate(ctype);
		jType.setAsNonNull();

		AlloyExpression alloyExpression = ExprConstant.buildExprConstant(jType
				.toString());

		super.getStack().push(alloyExpression);
	}


	@Override
	public void visitMethodCallExpression(
			JMethodCallExpression jMethodCall) {

		
		String qualified_name = jMethodCall.method().toString();

		jMethodCall.prefix().accept(this);
		AlloyExpression prefix_expression = this.getAlloyExpression();

		Vector<AlloyExpression> args_expression = new Vector<AlloyExpression>();
		JExpression[] args = jMethodCall.args();
		for (int i = 0; i < args.length; i++) {
			JExpression jExpression = args[i];
			jExpression.accept(this);
			args_expression.add(this.getAlloyExpression());
		}

		AlloyExpression fun_expr;
		if (jMethodCall.ident().equals("isEmpty")) {
			if (qualified_name.startsWith("org.jmlspecs.models.JMLObjectSet")) {

				fun_expr = JExpressionFactory.setIsEmpty(prefix_expression);

			} else if (qualified_name
					.startsWith("org.jmlspecs.models.JMLObjectSequence")) {

				fun_expr = JExpressionFactory.listEmpty(prefix_expression);

			} else
				throw new TacoException(
						"method calls within annotations not supported");

		} else if (jMethodCall.ident().equals("int_size")) {
			if (qualified_name.startsWith("org.jmlspecs.models.JMLObjectSet")) {

				fun_expr = JExpressionFactory.setSize(prefix_expression);

			} else if (qualified_name
					.startsWith("org.jmlspecs.models.JMLObjectSequence")) {

				fun_expr = JExpressionFactory.listSize(prefix_expression);

			} else
				throw new TacoException(
						"method calls within annotations not supported");

		} else if (jMethodCall.ident().equals("has")) {

			fun_expr = JExpressionFactory.setContains(prefix_expression,
					args_expression.get(0));

		} else if (jMethodCall.ident().equals("get")) {

			fun_expr = JExpressionFactory.listGet(prefix_expression,
					args_expression.get(0));

		} else
			throw new TacoException(
					"method calls within annotations not supported");

		super.getStack().push(fun_expr);

	}

	@Override
	public void visitLocalVariableExpression(
			JLocalVariableExpression jLocalVariableExpression) {
		if (this.instant == Instant.PRE_INSTANT
				|| this.notAllowsPrimedState.contains(jLocalVariableExpression
						.ident())) {
			super.visitLocalVariableExpression(jLocalVariableExpression);
		} else {
			jLocalVariableExpression.accept(prettyPrint);
			log.debug("Visiting: "
					+ jLocalVariableExpression.getClass().getName());
			log.debug("Statement: " + prettyPrint.getPrettyPrint());

			String identifier = new String(jLocalVariableExpression.ident());
			AlloyVariable primedVariable = new AlloyVariable(identifier, true);
			super.getStack().push(
					ExprVariable.buildExprVariable(primedVariable));

		}
	}

	@Override
	public void visitThisExpression(JThisExpression jThisExpression) {
		if (this.instant == Instant.PRE_INSTANT) {
			super.visitThisExpression(jThisExpression);
		} else {
			this.getStack()
					.push(ExprVariable
							.buildExprVariable(JExpressionFactory.PRIMED_THIS_VARIABLE));
		}
	}

	@Override
	public void visitUnaryExpression(JUnaryExpression jUnaryExpression) {
		if (jUnaryExpression.expr() instanceof JMethodCallExpression) {

			throw new TacoException(
					"Method calls within annotations not supported");

		} else {
			super.visitUnaryExpression(jUnaryExpression);
		}
	}
}
