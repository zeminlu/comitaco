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
package ar.edu.taco.jml.defaultconstructor;

import org.jmlspecs.checker.JmlClassDeclaration;
import org.multijava.mjc.JConstructorDeclarationType;
import org.multijava.mjc.JmlClassDeclarationExtension;

import ar.edu.taco.utils.jml.JmlAstClonerStatementVisitor;

public class DefaultConstructorSimplifier extends JmlAstClonerStatementVisitor {

	@SuppressWarnings("unchecked")
	@Override
	public void visitJmlClassDeclaration(JmlClassDeclaration self) {
		super.visitJmlClassDeclaration(self);
		JmlClassDeclaration newJmlClassDeclaration =  (JmlClassDeclaration) this.getStack().pop();
	
		JmlClassDeclarationExtension jmlClassDeclarationExtension = (JmlClassDeclarationExtension)newJmlClassDeclaration;
		if (!jmlClassDeclarationExtension.hasConstructor()) {
			JConstructorDeclarationType jConstructorDeclarationType = jmlClassDeclarationExtension.getDefaultConstructor();
			newJmlClassDeclaration.methods().add(jConstructorDeclarationType);
		}
		
		this.getStack().push(newJmlClassDeclaration);
		
	}
}
