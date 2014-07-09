package ar.edu.jdynalloy.ast;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import ar.edu.jdynalloy.xlator.JType;
import ar.uba.dc.rfm.alloy.AlloyVariable;
import ar.uba.dc.rfm.alloy.ast.expressions.AlloyExpression;
import ar.uba.dc.rfm.alloy.ast.expressions.ExprConstant;
import ar.uba.dc.rfm.alloy.ast.expressions.ExprVariable;
import ar.uba.dc.rfm.alloy.ast.formulas.AlloyFormula;

final public class JAlloyProgramBuffer {

	private static class PartialAST {

		private abstract static class Context {

			public AlloyFormula condition;

			public List<JStatement> currBlock = new LinkedList<JStatement>();

			public Context(AlloyFormula condition) {
				super();
				this.condition = condition;
			}

			private String branchId = null;

			public void setBranchId(String branchId) {
				this.branchId = branchId;

			}

		}

		private static final class IfThenElseContext extends Context {
			public List<JStatement> prevBlock = null;

			public IfThenElseContext(AlloyFormula condition) {
				super(condition);
			}

		}

		private static final class WhileContext extends Context {
			private JLoopInvariant annotation = null;

			public WhileContext(AlloyFormula condition,
					JLoopInvariant annotation) {
				super(condition);
				this.annotation = annotation;
			}
		}

		private Stack<Context> context = new Stack<Context>();

		private List<JStatement> initialBody = new LinkedList<JStatement>();

		public void newIfThenElseState(AlloyFormula condition) {
			newIfThenElseState(condition, null);
		}

		public void newIfThenElseState(AlloyFormula condition, String branchId) {
			IfThenElseContext ifThenElseContext = new IfThenElseContext(
					condition);
			if (branchId != null)
				ifThenElseContext.setBranchId(branchId);

			context.push(ifThenElseContext);
		}

		public void newWhileState(AlloyFormula condition,
				JLoopInvariant annotation, String branchId) {
			WhileContext whileContext = new WhileContext(condition, annotation);
			whileContext.setBranchId(branchId);
			context.push(whileContext);
		}

		public List<JStatement> getPrevBlockIfAvailable() {
			if (onIfElseState())
				return ((IfThenElseContext) context.peek()).prevBlock;
			else
				return null;
		}

		public List<JStatement> getCurrBlock() {
			if (onInitialState())
				return initialBody;
			else
				return context.peek().currBlock;
		}

		public JLoopInvariant getAnnotation() {
			Context ctx = context.peek();
			WhileContext whileContex = (WhileContext) ctx;
			return whileContex.annotation;
		}

		public AlloyFormula getCurrCondition() {
			if (!onInitialState())
				return context.peek().condition;
			else
				throw new IllegalStateException();
		}

		public String getCurrBranchId() {
			if (!onInitialState())
				return context.peek().branchId;
			else
				throw new IllegalStateException();

		}

		public boolean onIfElseState() {
			return !context.isEmpty()
					&& (context.peek().getClass()
							.equals(IfThenElseContext.class))
					&& ((IfThenElseContext) context.peek()).prevBlock != null;
		}

		public boolean onIfThenState() {
			return !context.isEmpty()
					&& (context.peek().getClass()
							.equals(IfThenElseContext.class))
					&& ((IfThenElseContext) context.peek()).prevBlock == null;
		}

		public boolean onInitialState() {
			return context.isEmpty();
		}

		public boolean onWhileState() {
			return !context.isEmpty()
					&& context.peek().getClass().equals(WhileContext.class);
		}

		public void switchToElseState() {
			if (onIfThenState()) {
				IfThenElseContext c = (IfThenElseContext) context.peek();
				c.prevBlock = c.currBlock;
				c.currBlock = new LinkedList<JStatement>();
			} else
				throw new IllegalStateException();
		}

		public void leaveState() {
			if (!context.isEmpty())
				context.pop();
			else
				throw new IllegalStateException();
		}

	}

	private PartialAST ast = new PartialAST();

	public void assign(AlloyVariable lvalue, String constantId) {
		addAssignment(lvalue, new ExprConstant(null, constantId));
	}

	public void assign(AlloyExpression lvalue, String constantId) {
		addAssignment(lvalue, new ExprConstant(null, constantId));
	}

	public void closeIf() {
		JIfThenElse i = null;

		if (ast.onIfThenState()) {
			i = buildIfThen(ast.getCurrCondition(), ast.getCurrBlock(), ast
					.getCurrBranchId());

		} else if (ast.onIfElseState()) {
			i = buildIfThenElse(ast.getCurrCondition(), ast
					.getPrevBlockIfAvailable(), ast.getCurrBlock(), ast
					.getCurrBranchId());

		} else
			throw new IllegalStateException();

		ast.leaveState();
		ast.getCurrBlock().add(i);
	}

	private JIfThenElse buildIfThen(AlloyFormula c, List<JStatement> b,
			String branchId) {
		return buildIfThenElse(c, b, new LinkedList<JStatement>(), branchId);
	}

	private JIfThenElse buildIfThenElse(AlloyFormula c,
			List<JStatement> thenList, List<JStatement> elseList,
			String branchId) {
		return new JIfThenElse(c, buildStmt(thenList), buildStmt(elseList),
				branchId);
	}

	public void assign(AlloyVariable lval, AlloyVariable rval) {
		addAssignment(lval, new ExprVariable(rval));
	}

	public void assign(AlloyVariable lvalue, AlloyExpression rvalue) {
		addAssignment(lvalue, rvalue);

	}

	public void assign(AlloyExpression lvalue, AlloyExpression rvalue) {
		addAssignment(lvalue, rvalue);
	}

	public void assign(AlloyExpression lvalue, AlloyVariable rvalue) {
		addAssignment(lvalue, new ExprVariable(rvalue));

	}

	private void addAssignment(AlloyVariable lvalue, AlloyExpression rvalue) {
		addAssignment(new ExprVariable(lvalue), rvalue);
	}

	private void addAssignment(AlloyExpression lvalue, AlloyExpression rvalue) {
		ast.getCurrBlock().add(new JAssignment(lvalue, rvalue));
	}

	public void closeWhile() {
		if (ast.onWhileState()) {
			JWhile w = buildWhile(ast.getCurrCondition(), ast.getAnnotation(),
					ast.getCurrBlock(), ast.getCurrBranchId());
			ast.leaveState();
			ast.getCurrBlock().add(w);
		} else
			throw new IllegalStateException();
	}

	/**
	 * Closes the JWhile in the current state and returns it.
	 * This method is used when translating loop invariants.
	 * @return
	 */
	public JWhile closeAndDiscardWhile() {
		if (ast.onWhileState()) {
			JWhile w = buildWhile(ast.getCurrCondition(), ast.getAnnotation(),
					ast.getCurrBlock(), null);
			ast.leaveState();
			return w;
		} else
			throw new IllegalStateException();
	}

	private JWhile buildWhile(AlloyFormula condition,
			JLoopInvariant annotation, List<JStatement> body, String branchId) {
		return new JWhile(condition, buildStmt(body), annotation, branchId);
	}

	private JStatement buildStmt(List<JStatement> b) {
		if (b.size() == 0)
			return new JSkip();
		if (b.size() == 1) {
			return b.get(0);
		} else
			return new JBlock(b);
	}

	public JStatement toJAlloyProgram() {
		if (ast.onInitialState()) {
			return buildStmt(ast.getCurrBlock());
		} else
			throw new IllegalStateException(
					"ProgramBuffer haven't return to initial state");
	}

	public void openIf(AlloyFormula condition, String branchId) {
		ast.newIfThenElseState(condition, branchId);
	}

	public void openIf(AlloyFormula condition) {
		openIf(condition, null);
	}

	public void switchToElseIf() {
		if (ast.onIfThenState())
			ast.switchToElseState();
		else
			throw new IllegalStateException();
	}

	public void skip() {
		ast.getCurrBlock().add(new JSkip());
	}

	public void openWhile(AlloyFormula condition, JLoopInvariant annotation) {
		openWhile(condition, annotation, null);
	}

	public void openWhile(AlloyFormula condition, JLoopInvariant annotation,
			String branchId) {
		ast.newWhileState(condition, annotation, branchId);
	}

	public void declare(AlloyVariable variable, JType type) {
		ast.getCurrBlock().add(new JVariableDeclaration(variable, type));

	}

	public void assertion(AlloyFormula f) {
		ast.getCurrBlock().add(new JAssert(f));

	}

	public void assumption(AlloyFormula f) {
		ast.getCurrBlock().add(new JAssume(f));

	}

	public void havoc(AlloyVariable v) {
		ast.getCurrBlock().add(new JHavoc(new ExprVariable(v)));
	}

	public void havoc(AlloyExpression expr) {
		ast.getCurrBlock().add(new JHavoc(expr));
	}

	public void appendProgram(JStatement program) {
		ast.getCurrBlock().add(program);
	}

}
