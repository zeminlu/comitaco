package ar.edu.jdynalloy.ast;

import static ar.uba.dc.rfm.alloy.ast.expressions.ExprFunction.buildExprFunction;
import ar.edu.jdynalloy.factory.DynalloyFactory;
import ar.uba.dc.rfm.alloy.ast.expressions.AlloyExpression;
import ar.uba.dc.rfm.alloy.ast.expressions.ExprFunction;

public final class AlloyIntArrayFactory {

	public static AlloyExpression arrayAccess(AlloyExpression array,
			AlloyExpression index) {
		return buildExprFunction("arrayAccess",
				DynalloyFactory.OBJECT_ARRAY_EXPRESSION, array, index);
	}

	public static AlloyExpression primedArrayAccess(AlloyExpression array,
			AlloyExpression index) {
		return buildExprFunction("arrayAccess",
				DynalloyFactory.PRIMED_OBJECT_ARRAY_EXPRESSION, array, index);
	}

	public static AlloyExpression arrayLength(AlloyExpression array) {
			return buildExprFunction("arrayLength",
					DynalloyFactory.OBJECT_ARRAY_EXPRESSION, array);
	}

	public static AlloyExpression arrayElements(AlloyExpression array) {
		return buildExprFunction("arrayElements",
				DynalloyFactory.OBJECT_ARRAY_EXPRESSION, array);
	}

	public static AlloyExpression primedArrayLength(AlloyExpression array) {
		return buildExprFunction("arrayLength",
				DynalloyFactory.PRIMED_OBJECT_ARRAY_EXPRESSION, array);
	}

	public static boolean isArrayAccess(AlloyExpression e) {
		return (e.getClass().equals(ExprFunction.class) && (((ExprFunction) e)
				.getFunctionId().equals("arrayAccess")));
	}

	public static AlloyExpression getArray(AlloyExpression e) {
		ExprFunction arrayAccess = toExprFunction(e);
		return arrayAccess.getParameters().get(1);
	}

	public static AlloyExpression getIndex(AlloyExpression e) {
		ExprFunction arrayAccess = toExprFunction(e);
		return arrayAccess.getParameters().get(2);
	}

	private static ExprFunction toExprFunction(AlloyExpression e) {
		if (!isArrayAccess(e))
			throw new IllegalArgumentException(e.toString()
					+ " is not an array access");
		ExprFunction arrayAccess = (ExprFunction) e;
		return arrayAccess;
	}

}
