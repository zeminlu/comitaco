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
package ar.edu.taco.simplejml.helpers;


import ar.edu.jdynalloy.ast.JIfThenElse;
import ar.edu.jdynalloy.ast.JSkip;
import ar.edu.jdynalloy.ast.JStatement;
import ar.edu.jdynalloy.factory.JExpressionFactory;
import ar.edu.taco.jml.utils.LabelUtils;
import ar.uba.dc.rfm.alloy.ast.formulas.AlloyFormula;
import ar.uba.dc.rfm.alloy.ast.formulas.AndFormula;
import ar.uba.dc.rfm.alloy.ast.formulas.EqualsFormula;

/**
 * @author ggasser
 * 
 */
public class BlockStatementSolver {
	public static JStatement getSurroundedStatement(JStatement jStatement,
			boolean isTryCatchBlock) {
		JStatement returnStatement = jStatement;
		AlloyFormula alloyFormula = getTryCatchSurrounderCondition();
		if (isTryCatchBlock) {
			returnStatement = new JIfThenElse(alloyFormula, jStatement,
					new JSkip(), LabelUtils.nextIfLabel());
		}
		return returnStatement;
	}

	public static AlloyFormula getTryCatchSurrounderCondition() {
		AlloyFormula nullThrowFormula = new EqualsFormula(
				JExpressionFactory.THROW_EXPRESSION,
				JExpressionFactory.NULL_EXPRESSION);
		//		
		// AlloyFormula alloyFormula = nullThrowFormula;
		// if (methodReturnValue) {
		// AlloyFormula returnReachedFormula = new
		// EqualsFormula(JExpressionFactory.EXIT_REACHED_EXPRESSION,
		// JExpressionFactory.FALSE_EXPRESSION);
		// alloyFormula = new AndFormula(nullThrowFormula,
		// returnReachedFormula);
		// }
		// return alloyFormula;
		AlloyFormula exitReachedFormula = new EqualsFormula(
				JExpressionFactory.EXIT_REACHED_EXPRESSION,
				JExpressionFactory.FALSE_EXPRESSION);

		exitReachedFormula = new AndFormula(nullThrowFormula,
				exitReachedFormula);
		return exitReachedFormula;
	}

	public static AlloyFormula getReturnSurrounderCondition() {
		AlloyFormula nullReturnFormula = new EqualsFormula(
				JExpressionFactory.RETURN_EXPRESSION,
				JExpressionFactory.NULL_EXPRESSION);

		return nullReturnFormula;
	}
}
