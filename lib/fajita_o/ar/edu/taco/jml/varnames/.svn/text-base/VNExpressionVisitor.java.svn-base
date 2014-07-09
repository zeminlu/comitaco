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
package ar.edu.taco.jml.varnames;

import java.util.Map;

import org.multijava.mjc.JFormalParameter;
import org.multijava.mjc.JGeneratedLocalVariable;
import org.multijava.mjc.JLocalVariable;
import org.multijava.mjc.JLocalVariableExpression;
import org.multijava.mjc.JVariableDefinition;

import ar.edu.taco.TacoException;
import ar.edu.taco.utils.jml.JmlAstClonerExpressionVisitor;

/**
 * Implementa la simplificacion de la regla 22
 * 
 * @author diegodob
 * 
 */
public class VNExpressionVisitor extends JmlAstClonerExpressionVisitor {

	private Map<String, String> variableMapping;

	public VNExpressionVisitor(Map<String, String> variableMapping) {
		this.variableMapping = variableMapping;
	}

	/** Visits the given local variable expression. */
	public void visitLocalVariableExpression(/* @non_null */JLocalVariableExpression self) {
		JLocalVariable variable = self.variable();
		JLocalVariable localVariable = renameVariable(variable);
		//localVariable = new JLocalVariable(self.variable().getTokenReference(), localVariable.getDescription(), localVariable.getType(), localVariable.expr());
		//JFormalParameter
		//JGeneratedLocalVariable
		//JVariableDefinition
		
		JLocalVariableExpression newSelf = new JLocalVariableExpression(self.getTokenReference(), localVariable);
		this.getArrayStack().push(newSelf);
	}
	
	

	private JLocalVariable renameVariable(JLocalVariable variable) {
		String newIden;
		if (variableMapping.containsKey(variable.ident())) {
			newIden = variableMapping.get(variable.ident());
		} else {
			newIden = variable.ident();
		}

		JLocalVariable localVariable;
		if (variable instanceof JFormalParameter) {
			JFormalParameter jFormalParameter = (JFormalParameter) variable; 
			localVariable = new JFormalParameter(jFormalParameter.getTokenReference(), jFormalParameter.modifiers(),jFormalParameter.getDescription(), jFormalParameter.specializedType() , newIden);
		} else if (variable instanceof JGeneratedLocalVariable) {
			JGeneratedLocalVariable jGeneratedLocalVariable = (JGeneratedLocalVariable) variable;
			localVariable = new JGeneratedLocalVariable(jGeneratedLocalVariable.getTokenReference(), jGeneratedLocalVariable.modifiers(), jGeneratedLocalVariable.getType() , newIden, jGeneratedLocalVariable.getValue());
		} else if (variable instanceof JVariableDefinition) {
//			public JVariableDefinition( TokenReference where,
//					long modifiers, 
//					CType type,
//					String ident,
//					JExpression initializer )
			JVariableDefinition jVariableDefinition = (JVariableDefinition) variable;
			localVariable = new JVariableDefinition(jVariableDefinition.getTokenReference(), jVariableDefinition.modifiers(),jVariableDefinition.getType(),newIden,jVariableDefinition.expr());
			
//			long modifiers, 
//			CType type,
//			String ident,
//			JExpression initializer ) {			
		} else {
			throw new TacoException("invalid JLocalVariable type not supported");
		}
		return localVariable;
	}


//	/** Visits the given assignment expression. */
//	public void visitAssignmentExpression(/* @non_null */JAssignmentExpression self) {
//		self.right().accept(this);
//
//		// this will does not work for recursive case! (ej: a[i]=b[j]=1)
//		isInLeftSizeOfAssignament = true;
//		self.left().accept(this);
//		isInLeftSizeOfAssignament = false;
//
//		JAssignmentExpression newSelf = new JAssignmentExpression(self.getTokenReference(), this.getArrayStack().pop(), this.getArrayStack().pop());
//		this.getArrayStack().push(newSelf);
//	}

}
