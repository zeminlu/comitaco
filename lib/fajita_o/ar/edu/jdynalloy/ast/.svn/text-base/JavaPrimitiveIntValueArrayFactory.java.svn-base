package ar.edu.jdynalloy.ast;

import java.util.ArrayList;
import java.util.List;

import ar.edu.jdynalloy.factory.JExpressionFactory;
import ar.edu.jdynalloy.factory.JSignatureFactory;
import ar.edu.jdynalloy.xlator.JType;
import ar.uba.dc.rfm.alloy.ast.expressions.AlloyExpression;
import ar.uba.dc.rfm.alloy.ast.expressions.ExprJoin;
import ar.uba.dc.rfm.alloy.ast.expressions.ExprVariable;

public class JavaPrimitiveIntValueArrayFactory {

	public static AlloyExpression array_length(JType array_type,
			AlloyExpression array) {

		if (array_type.equals(JSignatureFactory.INT_ARRAY_TYPE)) {
			return ExprJoin.join(array, JExpressionFactory.INT_ARRAY_LENGTH);
		} else if (array_type.equals(JSignatureFactory.OBJECT_ARRAY_TYPE)) {
			return ExprJoin.join(array, JExpressionFactory.OBJECT_ARRAY_LENGTH);
		} else
			throw new RuntimeException("Unsupported array type length");
	}

	public static AlloyExpression array_access(JType array_type,
			AlloyExpression array, AlloyExpression index) {
		if (array_type.equals(JSignatureFactory.INT_ARRAY_TYPE)) {
			return ExprJoin.join(index, ExprJoin.join(array,
					JExpressionFactory.INT_ARRAY_CONTENTS_EXPRESSION));
		} else if (array_type.equals(JSignatureFactory.OBJECT_ARRAY_TYPE)) {
			return ExprJoin.join(index, ExprJoin.join(array,
					JExpressionFactory.OBJECT_ARRAY_CONTENTS_EXPRESSION));
		} else
			throw new RuntimeException("Unsupported read array type :"
					+ array_type);
	}

	public static JStatement array_write_stmt(AlloyExpression array_access,
			AlloyExpression new_value) {
		// array_index.(array_expr.array_contents) := expr

		AlloyExpression array_index = getArrayIndex(array_access);
		AlloyExpression array_expr = getArrayExpression(array_access);
		ExprVariable array_contents = getArrayContents(array_access);

		if (array_contents
				.equals(JExpressionFactory.INT_ARRAY_CONTENTS_EXPRESSION)) {

			List<AlloyExpression> argumentsList = new ArrayList<AlloyExpression>();
			argumentsList.add(array_expr);
			argumentsList.add(JExpressionFactory.THROW_EXPRESSION);
			argumentsList.add(new_value);
			argumentsList.add(array_index);

			String programId = "int_array_write";
			JProgramCall programCall = new JProgramCall(false, programId,
					argumentsList);

			return programCall;
		} else if (array_contents
				.equals(JExpressionFactory.OBJECT_ARRAY_CONTENTS_EXPRESSION)) {

			List<AlloyExpression> argumentsList = new ArrayList<AlloyExpression>();
			argumentsList.add(array_expr);
			argumentsList.add(JExpressionFactory.THROW_EXPRESSION);
			argumentsList.add(new_value);
			argumentsList.add(array_index);

			String programId = "object_array_write";
			JProgramCall programCall = new JProgramCall(false, programId,
					argumentsList);

			return programCall;
		} else
			throw new RuntimeException("not supported array write: "
					+ array_contents);

	}

	public static JStatement array_read_stmt(AlloyExpression lvalue,
			AlloyExpression array_access) {

		AlloyExpression array_index = getArrayIndex(array_access);
		AlloyExpression array_expr = getArrayExpression(array_access);
		ExprVariable array_contents = getArrayContents(array_access);

		if (array_contents
				.equals(JExpressionFactory.INT_ARRAY_CONTENTS_EXPRESSION)) {

			List<AlloyExpression> argumentsList = new ArrayList<AlloyExpression>();
			argumentsList.add(array_expr);
			argumentsList.add(JExpressionFactory.THROW_EXPRESSION);
			argumentsList.add(lvalue);
			argumentsList.add(array_index);

			String programId = "int_array_read";
			JProgramCall programCall = new JProgramCall(false, programId,
					argumentsList);

			return programCall;
		} else if (array_contents
				.equals(JExpressionFactory.OBJECT_ARRAY_CONTENTS_EXPRESSION)) {

			List<AlloyExpression> argumentsList = new ArrayList<AlloyExpression>();
			argumentsList.add(array_expr);
			argumentsList.add(JExpressionFactory.THROW_EXPRESSION);
			argumentsList.add(lvalue);
			argumentsList.add(array_index);

			String programId = "object_array_read";
			JProgramCall programCall = new JProgramCall(false, programId,
					argumentsList);

			return programCall;
		} else

			throw new RuntimeException("not supported array write: "
					+ array_contents);

	}

	private static ExprVariable getArrayContents(AlloyExpression array_access) {
		ExprJoin index_join = (ExprJoin) array_access;
		ExprJoin array_join = (ExprJoin) index_join.getRight();
		ExprVariable array_contents = (ExprVariable) array_join.getRight();
		return array_contents;
	}

	private static AlloyExpression getArrayExpression(
			AlloyExpression array_access) {
		ExprJoin index_join = (ExprJoin) array_access;
		ExprJoin array_join = (ExprJoin) index_join.getRight();
		return array_join.getLeft();
	}

	private static AlloyExpression getArrayIndex(AlloyExpression array_access) {
		ExprJoin index_join = (ExprJoin) array_access;
		return index_join.getLeft();
	}

}
