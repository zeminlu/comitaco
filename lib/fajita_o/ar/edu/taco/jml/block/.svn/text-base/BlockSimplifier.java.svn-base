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
package ar.edu.taco.jml.block;

import org.jmlspecs.checker.JmlLoopStatement;
import org.multijava.mjc.JBlock;
import org.multijava.mjc.JConstructorBlock;
import org.multijava.mjc.JForStatement;
import org.multijava.mjc.JIfStatement;
import org.multijava.mjc.JStatement;
import org.multijava.mjc.JWhileStatement;

import ar.edu.taco.jml.utils.ASTUtils;
import ar.edu.taco.utils.jml.JmlAstClonerStatementVisitor;

/**
 * The simplifier motivation is avoid implement AST Visitor for Method Scope and
 * is used , as precondition, in ConditionSimplifier, that all statement are
 * eveloped by a block
 * 
 * @author diegodob
 * 
 */
public class BlockSimplifier extends JmlAstClonerStatementVisitor {
	
	private int forVariableCount = 0;

	/** Visits the given while statement. */
	public void visitWhileStatement(/* @non_null */JWhileStatement self) {
		
		self.body().accept(this);
		JBlock newBody;
		if (self.body() instanceof JBlock) {
			newBody = (JBlock) this.getStack().pop();
		} else {
			newBody = ASTUtils.createBlockStatement( (JStatement) this.getStack().pop() );
		}
		JWhileStatement newSelf = new JWhileStatement(self.getTokenReference(), self.cond(), ASTUtils.createBlockStatement(newBody), self.getComments());
		this.getStack().push(newSelf);
	}

	/** Visits the given if statement. */
	public void visitIfStatement(/* @non_null */JIfStatement self) {
		this.getStack().push(self);
		self.thenClause().accept(this);
		JStatement newThen = (JStatement) this.getStack().pop();
		JStatement newElse = null;
		if (self.elseClause() != null) {
			self.elseClause().accept(this);
			newElse = (JStatement) this.getStack().pop();
		}

		this.getStack().push(
				new JIfStatement(self.getTokenReference(), self.cond(), ASTUtils.createBlockStatement(newThen), ASTUtils.createBlockStatement(newElse), self
						.getComments()));
	}
	
	/** Visits the given for statement. */
	public void visitForStatement(/* @non_null */JForStatement self) {
		
		JStatement newInit = null;
//		if (self.init() instanceof JVariableDeclarationStatement) {
//			JVariableDeclarationStatement variableDeclaration = (JVariableDeclarationStatement) self.init();
//			List<JVariableDefinition> variableDefinitions = new ArrayList<JVariableDefinition>();
//			for (JVariableDefinition vd : variableDeclaration.getVars()) {
//				String newIdent = "bs_var_" + vd.ident() + "_" + this.forVariableCount;
//				this.forVariableCount++;
//				JVariableDefinition newDefinition = new JVariableDefinition(vd.getTokenReference(), vd.modifiers(), vd.getType(), newIdent, vd.expr());
//				variableDefinitions.add(newDefinition);
//			}
//			
//			
//			newInit = new JVariableDeclarationStatement(variableDeclaration.getTokenReference(), 
//													variableDefinitions.toArray(new JVariableDefinition[variableDefinitions.size()]), 
//													variableDeclaration.getComments());
//		} else {
			self.init().accept(this);
			newInit = (JStatement) this.getStack().pop();
//		}
		
		JStatement newIncr;
		if (self.incr() == null) {
			newIncr = null;
		} else {
			self.incr().accept(this);
			newIncr = (JStatement) this.getStack().pop();
		}

		self.body().accept(this);
		JStatement newBody = (JStatement) this.getStack().pop();

		this.getStack().push(
				new JForStatement(self.getTokenReference(), newInit, self.cond(), newIncr, ASTUtils.createBlockStatement(newBody), self.getComments()));
	}
	
	@Override
	public void visitConstructorBlock(JConstructorBlock self) {	    
	    super.visitConstructorBlock(self);
	    JConstructorBlock visitedSelf = (JConstructorBlock) this.getStack().pop();
	    JBlock block = ASTUtils.createBlockStatement(visitedSelf.body());
	    JConstructorBlock newSelf = new JConstructorBlock(self.getTokenReference(), new JStatement[] {block});
	    this.getStack().push(newSelf);
	    
	}

}