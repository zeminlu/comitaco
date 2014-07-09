/*
 * Dynalloy Translator
 * Copyright (c) 2007 Universidad de Buenos Aires
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
package ar.uba.dc.rfm.dynalloy.xlator;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import ar.uba.dc.rfm.alloy.AlloyTyping;
import ar.uba.dc.rfm.alloy.AlloyVariable;
import static ar.uba.dc.rfm.alloy.AlloyVariable.asAlloyVariable;
import ar.uba.dc.rfm.alloy.IdxRange;
import ar.uba.dc.rfm.alloy.IdxRangeMap;
import ar.uba.dc.rfm.alloy.VariableId;
import ar.uba.dc.rfm.alloy.ast.AlloyAssertion;
import ar.uba.dc.rfm.alloy.ast.AlloyFact;
import ar.uba.dc.rfm.alloy.ast.AlloyModule;
import ar.uba.dc.rfm.alloy.ast.AlloySig;
import ar.uba.dc.rfm.alloy.ast.expressions.AlloyExpression;
import ar.uba.dc.rfm.alloy.ast.expressions.ExprVariable;
import static ar.uba.dc.rfm.alloy.ast.expressions.AlloyExpression.asAlloyExpression;
import static ar.uba.dc.rfm.dynalloy.xlator.CollectionUtils.intersect;
import ar.uba.dc.rfm.alloy.ast.formulas.AlloyFormula;
import ar.uba.dc.rfm.alloy.ast.formulas.AndFormula;
import ar.uba.dc.rfm.alloy.ast.formulas.ImpliesFormula;
import ar.uba.dc.rfm.alloy.ast.formulas.PredicateFormula;
import ar.uba.dc.rfm.alloy.util.AlloyBuffer;
import ar.uba.dc.rfm.alloy.util.AlloyPrinter;
import ar.uba.dc.rfm.alloy.util.FormulaBuffer;
import ar.uba.dc.rfm.alloy.util.FormulaMutator;
import ar.uba.dc.rfm.alloy.util.FormulaPrinter;
import ar.uba.dc.rfm.da2a.qremover.QuantifierRemover;
import ar.uba.dc.rfm.da2a.qremover.QuantifierRemover.QuantifierRemoverResult;
import ar.uba.dc.rfm.dynalloy.DynAlloyOptions;
import ar.uba.dc.rfm.dynalloy.ast.ActionDeclaration;
import ar.uba.dc.rfm.dynalloy.ast.AssertionDeclaration;
import ar.uba.dc.rfm.dynalloy.ast.ProgramDeclaration;
import ar.uba.dc.rfm.dynalloy.ast.DynalloyModule;
import ar.uba.dc.rfm.dynalloy.ast.IDynalloyVisitor;
import ar.uba.dc.rfm.dynalloy.ast.programs.InvokeAction;
import ar.uba.dc.rfm.dynalloy.util.DynalloyVisitor;

public class DynalloyXlatorVisitor extends DynalloyVisitor {

	private boolean prettyPrinting = true;

	private final IdxRangeCollectHelper varCollector = new IdxRangeCollectHelper();

	private final ProgramTranslator programTranslator;

	private final SpecContext context;

	private final DynAlloyOptions options;

	public DynalloyXlatorVisitor(SpecContext _specContext, DynAlloyOptions options) {
		super(new ProgramTranslator(_specContext));
		programTranslator = (ProgramTranslator) this.getDfsProgramVisitor();
		context = _specContext;
		this.options = options;
	}

	public AlloyModule visit(DynalloyModule n) {

		// create buffer
		StringBuffer buffer = new StringBuffer();

		// gather alloyStr's
		buffer.append(n.getAlloyStr() + "\n");

		// translate atomic actions
		this.context.switchToModule(n.getModuleId());
		for (ActionDeclaration action : n.getActions()) {
			String actionPredicate = (String) action.accept(this);

			buffer.append("\n");
			buffer.append("\n");
			buffer.append(actionPredicate + "\n");
		}

		// translate programs
		this.context.switchToModule(n.getModuleId());
		for (ProgramDeclaration program : n.getPrograms()) {
			String programPredicate = (String) program.accept(this);

			// <DataFlow>
			//ControlFlowGraph cfg = new ControlFlowGraph(program.getBody());
			// </DataFlow>

			buffer.append("\n");
			buffer.append("\n");
			buffer.append(programPredicate + "\n");
		}

		String compilableA4Spec;
		compilableA4Spec = buffer.toString();

		AlloySig qfSig = null;

		List<AlloyFact> facts = new LinkedList<AlloyFact>();
		Set<AlloyAssertion> assertions = new HashSet<AlloyAssertion>();

		// translate assertions
		this.context.switchToModule(n.getModuleId());
		for (AssertionDeclaration assertion : n.getAssertions()) {

			AlloyAssertion alloyAssertion = (AlloyAssertion) assertion.accept(this);

			if ((options.getRemoveQuantifiers() == true) && (assertion != null && assertion.getAssertionId().equals(options.getAssertionId()))) {

				QuantifierRemoverResult qfResult = removeQuantifiers(alloyAssertion);
				qfSig = qfResult.qfSignature;

				facts.addAll(qfResult.qfFacts);

				alloyAssertion = qfResult.qfAssertion;
			} else {
				alloyAssertion = (AlloyAssertion) assertion.accept(this);
			}
			assertions.add(alloyAssertion);

		}

		AlloyModule alloyAST = new AlloyModule(compilableA4Spec, qfSig, facts, assertions);

		return alloyAST;
	}

	private QuantifierRemoverResult removeQuantifiers(AlloyAssertion assertion) {
		QuantifierRemover remover = new QuantifierRemover();
		QuantifierRemoverResult qfResult = remover.removeQuantifiers(assertion);
		return qfResult;
	}

	private String appendBuiltInPredicates(String strSpec) {
		StringBuffer a4StrBuff = new StringBuffer();
		a4StrBuff.append(strSpec + "\n");
		if (!strSpec.contains("pred " + AlloyPredicates.TRUE_PRED_ID))
			a4StrBuff.append(AlloyPredicates.TRUE_PRED_SPEC + "\n");
		if (!strSpec.contains("pred " + AlloyPredicates.FALSE_PRED_ID))
			a4StrBuff.append(AlloyPredicates.FALSE_PRED_SPEC + "\n");
		String r = a4StrBuff.toString();
		return r;
	}

	private AlloyTyping buildQuantification(AlloyFormula f, AlloyTyping ps) {
		Map<AlloyVariable, String> result = new HashMap<AlloyVariable, String>();
		IdxRangeMap rs = new IdxRangeCollectHelper().collect(f);
		for (VariableId vId : rs.keySet()) {
			IdxRange r = rs.getIdxRange(vId);
			for (int i = r.getBegin(); i <= r.getEnd(); i++) {
				result.put(new AlloyVariable(vId, i), typeOf(vId, ps));
			}
		}
		return new AlloyTyping(result);
	}

	private String typeOf(VariableId id, AlloyTyping ps) {
		for (AlloyVariable v : ps.varSet()) {
			if (v.getVariableId().equals(id))
				return ps.get(v);
		}
		throw new IllegalArgumentException("variable " + id.toString() + " is not a local variable or a declared field");
	}

	public Object visit(AssertionDeclaration n) {
		programTranslator.setNewProgramId(n.getAssertionId());

		AlloyFormula pre = addIdxsToPred(n.getPre());
		AlloyFormulaWithLocals program = (AlloyFormulaWithLocals) n.getProgram().accept(programTranslator);
		AlloyFormula post = addIdxsToPost(n.getPost(), varCollector.collect(new AndFormula(pre, program.getFormula())));

		ImpliesFormula formula = new ImpliesFormula(new AndFormula(pre, program.getFormula()), post);

		AlloyTyping typing = n.getTyping().merge(program.getLocals());

		AlloyTyping typing_2 = buildQuantification(formula, typing);
		AlloyAssertion assertion = new AlloyAssertion(n.getAssertionId(), typing_2, formula);
		if (context.getMapping() != null) {
			context.getMapping().addAssertion(n, assertion);
		}
		return assertion;
	}

	private AlloyFormula addIdxsToPost(PredicateFormula post, IdxRangeMap rs) {
		AlloyFormula f = (AlloyFormula) post.accept(new FormulaMutator(new FirstSubindexer()));
		FormulaBuffer buff = new FormulaBuffer(f);
		IdxRangeMap postRs = varCollector.collect(f);
		for (VariableId vId : intersect(postRs.keySet(), rs.keySet())) {
			if (postRs.getIdxRange(vId).getEnd() == IdxRange.SUBINDEX_1)
				buff.replaceIdx(vId, IdxRange.SUBINDEX_1, rs.getIdxRange(vId).getEnd());
		}

		for (VariableId vId : postRs.keySet()) {
			if (!rs.keySet().contains(vId)) {
				buff.replaceIdx(vId, IdxRange.SUBINDEX_1, IdxRange.SUBINDEX_0);
			}
		}

		return buff.toFormula();
	}

	public Object visit(ActionDeclaration n) {
		String actionId = n.getActionId();
		List<AlloyExpression> actualPs = AlloyExpression.asAlloyExpression(asAlloyVariable(n.getFormalParameters()));
		List<VariableId> formalPs = context.getFormalParams(actionId);

		PredicateFormula pre = VariableIndexer.substituteAndIndex(context.getActionPre(actionId), formalPs, actualPs);
		PredicateFormula post = VariableIndexer.substituteAndIndex(context.getActionPost(actionId), formalPs, actualPs);

		AlloyFormula alloyFormula = new AndFormula(pre, post);
		AlloyTyping typing = buildQuantification(alloyFormula, n.getTyping());

		CallingConvention callingConvention = new CallingConvention(formalPs, Collections.<VariableId> emptyList(), new IdxRangeCollectHelper()
				.collect(alloyFormula), n.getTyping());
		context.putCallingConvention(context.getCurrentModuleId(), n.getActionId(), callingConvention);

		List<AlloyExpression> exprs = callingConvention.instantiate(actualPs);

		StringBuffer buff = new StringBuffer();

		buff.append("pred " + n.getActionId());
		buff.append("[");

		if (prettyPrinting)
			buff.append("\n");

		for (int i = 0; i < exprs.size(); i++) {
			AlloyExpression ex = exprs.get(i);
			if (i != 0) {
				buff.append(",");
				if (prettyPrinting)
					buff.append("\n");
			}
			String parameterDeclaration = ex.toString() + ":" + typing.get(((ExprVariable) ex).getVariable());

			if (prettyPrinting)
				parameterDeclaration = increaseIdentation(parameterDeclaration);

			buff.append(parameterDeclaration);
		}
		if (prettyPrinting)
			buff.append("\n");

		buff.append("]{");

		if (prettyPrinting)
			buff.append("\n");

		FormulaPrinter formulaPrinter = new FormulaPrinter();
		formulaPrinter.setPrettyPrinting(prettyPrinting);
		String formulaString = alloyFormula.accept(formulaPrinter) + "\n";
		buff.append(increaseIdentation(formulaString));
		buff.append("}");

		/* invoke(List<AlloyExpression> actuals) => */
		return buff.toString();
	}

	/**
	 * 
	 * @param f
	 *            a P(x,..x) formula
	 * @return Let m: variable.(plain+primed) |-> variable.(0+1), returns m(f)
	 */
	private PredicateFormula addIdxsToPred(PredicateFormula f) {
		return (PredicateFormula) f.accept(new FormulaMutator(new FirstSubindexer()));
	}

	public Object visit(ProgramDeclaration p) {
		programTranslator.setNewProgramId(p.getProgramId());

		if (!context.isAlreadyTranslated(null, p.getProgramId())) {

			Vector<Object> v = (Vector<Object>) super.visit(p);
			AlloyFormulaWithLocals programFormula = (AlloyFormulaWithLocals) v.get(0);

			List<AlloyExpression> actualPs = AlloyExpression.asAlloyExpression(asAlloyVariable(p.getParameters()));

			AlloyTyping localTyping = new AlloyTyping();
			for (AlloyVariable local : programFormula.getLocals()) {
				AlloyVariable var = new AlloyVariable(local.getVariableId());
				String type = programFormula.getLocals().get(local);
				localTyping.put(var, type);
			}

			AlloyTyping merged = p.getParameterTyping().merge(localTyping);

			AlloyTyping typing = buildQuantification(programFormula.getFormula(), merged);

			IdxRangeMap ranges = new IdxRangeCollectHelper().collect(programFormula.getFormula());

			List<VariableId> allLocals = new LinkedList<VariableId>(p.getLocalVariables());
			for (VariableId vId : ranges.keySet()) {
				if (!allLocals.contains(vId) && !p.getParameters().contains(vId))
					allLocals.add(vId);
			}

			CallingConvention callingConvention = new CallingConvention(p.getParameters(), allLocals, ranges, merged);

			context.putCallingConvention(context.getCurrentModuleId(), p.getProgramId(), callingConvention);

			List<AlloyExpression> exprs = callingConvention.instantiate(actualPs);

			FormulaPrinter printer = new FormulaPrinter();
			printer.setPrettyPrinting(prettyPrinting);

			StringBuffer buff = new StringBuffer();
			buff.append("pred " + p.getProgramId());
			buff.append("[");

			if (prettyPrinting)
				buff.append("\n");

			for (int i = 0; i < exprs.size(); i++) {
				AlloyExpression ex = exprs.get(i);
				if (i != 0) {
					buff.append(",");
					if (prettyPrinting)
						buff.append("\n");
				}
				String parameterDecl = ex.toString() + ":" + typing.get(((ExprVariable) ex).getVariable());

				if (prettyPrinting)
					parameterDecl = increaseIdentation(parameterDecl);

				buff.append(parameterDecl);
			}

			if (prettyPrinting && !exprs.isEmpty())
				buff.append("\n");

			buff.append("]{");

			if (prettyPrinting)
				buff.append("\n");

			// local definitions
			String locals = "";
			for (VariableId tempVariableId : p.getLocalVariables()) {
				IdxRange tempRange = ranges.getIdxRange(tempVariableId);
				if (tempRange != null) {
					String alloyType = p.getParameterTyping().get(new AlloyVariable(tempVariableId));
					for (int i = tempRange.getBegin(); i <= tempRange.getEnd(); i++) {
						if (!locals.equals(""))
							locals += ",";
						locals += new AlloyVariable(tempVariableId, i).toString() + ":" + alloyType;
					}
				}
			}

			String formulaString = (String) programFormula.getFormula().accept(printer);

			if (prettyPrinting)
				formulaString = increaseIdentation(formulaString);

			buff.append(formulaString + "\n");
			buff.append("}");
			buff.append("\n");

			if (context.getMapping() != null) {
				context.getMapping().addRun(p.getProgramId(), programFormula.getFormula());
			}

			context.putTranslation(p.getProgramId(), buff.toString(), exprs);
		}
		String predicateString = context.getTranslation(null, p.getProgramId());
		return predicateString;
	}

	private static String increaseIdentation(String string) {
		StringBuffer buffer = new StringBuffer();
		String[] lines = string.split("\n");

		if (lines.length == 1)
			buffer.append("  " + lines[0]);
		else
			for (String line : lines) {
				buffer.append("  " + line + "\n");
			}
		return buffer.toString();
	}

}
