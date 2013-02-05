package ar.edu.taco.dynalloy;

import ar.uba.dc.rfm.alloy.ast.expressions.AlloyExpression;
import ar.uba.dc.rfm.alloy.ast.expressions.ExprFunction;
import ar.uba.dc.rfm.alloy.ast.expressions.ExprVariable;
import ar.uba.dc.rfm.alloy.ast.expressions.ExpressionVisitor;
import ar.uba.dc.rfm.alloy.ast.formulas.FormulaVisitor;
import ar.uba.dc.rfm.dynalloy.ast.programs.InvokeAction;
import ar.uba.dc.rfm.dynalloy.util.DfsProgramVisitor;

class ArithmeticOpCollector extends DfsProgramVisitor {

	// arithmetic op counter
	private ArithmeticOpCounter arithmetic_op_counter = new ArithmeticOpCounter();

	private class ArithmeticOpExprCollector extends ExpressionVisitor {

		@Override
		public Object visit(ExprFunction n) {

			// integer operations
			if (n.getFunctionId().equals("fun_java_primitive_integer_value_add")) {
				arithmetic_op_counter.integerOpCounter.inc_add();
			} else if (n.getFunctionId().equals("fun_java_primitive_integer_value_sub")) {
				arithmetic_op_counter.integerOpCounter.inc_sub();
			} else if (n.getFunctionId().equals("fun_java_primitive_integer_value_sshr")) {
				arithmetic_op_counter.integerOpCounter.inc_sshr();
			}

			// long operations
			if (n.getFunctionId().equals("fun_java_primitive_long_value_add")) {
				arithmetic_op_counter.longOpCounter.inc_java_primitive_long_value_add();
			} else if (n.getFunctionId().equals("fun_java_primitive_long_value_sub")) {
				arithmetic_op_counter.longOpCounter.inc_java_primitive_long_value_sub();
			}

			return super.visit(n);
		}

	}

	private ArithmeticOpExprCollector arithmeticOpExprCollector;

	public ArithmeticOpCollector() {
		super(new FormulaVisitor());
		arithmeticOpExprCollector = new ArithmeticOpExprCollector();
		this.getDfsFormulaVisitor().setExpressionVisitor(arithmeticOpExprCollector);
	}

	@Override
	public Object visit(InvokeAction u) {
		if (u.getActionId().equals("havocVariable")) {
			if (u.getActualParameters().size() == 1) {
				AlloyExpression actual_parameter = u.getActualParameters().get(0);
				if (actual_parameter instanceof ExprVariable) {
					ExprVariable expr_variable_parameter = (ExprVariable) actual_parameter;
					String expr_variable_str = expr_variable_parameter.getVariable().toString();

					// integer operations
					if (expr_variable_str.contains("pred_java_primitive_integer_value_mul_ARG_result_")) {
						arithmetic_op_counter.integerOpCounter.inc_mul();
					} else if (expr_variable_str.contains("pred_java_primitive_integer_value_div_rem_ARG_result_")) {
						arithmetic_op_counter.integerOpCounter.inc_div_rem();
					}

					// long operations
					if (expr_variable_str.contains("pred_java_primitive_long_value_mul_ARG_result_")) {
						arithmetic_op_counter.longOpCounter.inc_java_primitive_long_value_mul();
					} else if (expr_variable_str.contains("pred_java_primitive_long_value_div_rem_ARG_result_")) {
						arithmetic_op_counter.longOpCounter.inc_java_primitive_long_value_div_rem();
					}

					// float operations
					if (expr_variable_str.contains("pred_java_primitive_float_value_mul_ARG_result_")) {
						arithmetic_op_counter.floatOpCounter.inc_java_primitive_float_value_mul();
					} else if (expr_variable_str.contains("pred_java_primitive_float_value_div_ARG_result_")) {
						arithmetic_op_counter.floatOpCounter.inc_java_primitive_float_value_div();
					} else if (expr_variable_str.contains("pred_java_primitive_float_value_add_ARG_result_")) {
						arithmetic_op_counter.floatOpCounter.inc_java_primitive_float_value_add();
					} else if (expr_variable_str.contains("pred_java_primitive_float_value_sub_ARG_result_")) {
						arithmetic_op_counter.floatOpCounter.inc_java_primitive_float_value_sub();
					}

				}
			}

		}
		return super.visit(u);
	}

	public ArithmeticOpCounter getArithmeticOpCounter() {
		return this.arithmetic_op_counter;
	}
}
