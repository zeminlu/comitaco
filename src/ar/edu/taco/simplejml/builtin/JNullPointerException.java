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
package ar.edu.taco.simplejml.builtin;

import static ar.edu.jdynalloy.factory.JSignatureFactory.buildLiteralSingleton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ar.edu.jdynalloy.JDynAlloyConfig;
import ar.edu.jdynalloy.ast.JDynAlloyModule;
import ar.edu.jdynalloy.ast.JClassConstraint;
import ar.edu.jdynalloy.ast.JClassInvariant;
import ar.edu.jdynalloy.ast.JField;
import ar.edu.jdynalloy.ast.JObjectConstraint;
import ar.edu.jdynalloy.ast.JObjectInvariant;
import ar.edu.jdynalloy.ast.JProgramDeclaration;
import ar.edu.jdynalloy.ast.JRepresents;
import ar.edu.jdynalloy.ast.JSignature;
import ar.edu.jdynalloy.ast.JSkip;
import ar.edu.jdynalloy.ast.JSpecCase;
import ar.edu.jdynalloy.ast.JVariableDeclaration;
import ar.edu.jdynalloy.factory.JDynAlloyFactory;
import ar.edu.jdynalloy.factory.JExpressionFactory;
import ar.edu.jdynalloy.xlator.JDynAlloyTyping;
import ar.edu.jdynalloy.xlator.JType;
import ar.edu.taco.simplejml.helpers.ArgEncoder;
import ar.uba.dc.rfm.alloy.AlloyTyping;
import ar.uba.dc.rfm.alloy.ast.formulas.AlloyFormula;

public class JNullPointerException implements IBuiltInModule {

	// public static final JBindingKey NULLPOINTER_EXCEPTION_CONSTRUCTOR_KEY =
	// new JBindingKey(
	// "Ljava/lang/NullPointerException;.()V");

	private static JNullPointerException instance;

	public static JNullPointerException getInstance() {
		if (instance == null)
			instance = new JNullPointerException();
		return instance;
	}

	private final JDynAlloyModule module;

	// private final Map<JBindingKey, JProgramDeclaration> bindings;

	private JNullPointerException() {
		// JSignature signature = buildClass(false,
		// "java_lang_NullPointerException",
		// new DynJAlloyTyping(), "java_lang_RuntimeException", Collections
		// .<String> emptySet());
		final boolean signatureIsAbstract;
		if (JDynAlloyConfig.getInstance().getNewExceptionsAreLiterals() == true) {
			signatureIsAbstract = true;
		} else
			signatureIsAbstract = false;

		JSignature signature = new JSignature(true, signatureIsAbstract,
				"java_lang_NullPointerException", new JDynAlloyTyping(), false,
				"java_lang_RuntimeException", null, Collections
						.<String> emptySet(), Collections
						.<AlloyFormula> emptySet() /* facts */, Collections
						.<String> emptyList(), Collections.<String> emptyList());

		JSignature classSignature;
		// if (DynJAlloyConfig.getInstance().getUseClassSingletons() == true)
		// classSignature = new JSignature(true, false,
		// "NullPointerExceptionClass", new DynJAlloyTyping(), false,
		// "Class", null, Collections.<String> emptySet());
		// else
		classSignature = null;

		JVariableDeclaration thisDeclaration = new JVariableDeclaration(
				JExpressionFactory.THIS_VARIABLE, JType
						.parse("java_lang_NullPointerException"));

		ArgEncoder encoder = new ArgEncoder(false, true, false, 0);
		List<JVariableDeclaration> parameters = encoder.encode(thisDeclaration,
				JDynAlloyFactory.THROW_DECLARATION, null, Collections
						.<JVariableDeclaration> emptyList());
		JProgramDeclaration constructor = new JProgramDeclaration(false,
				"java_lang_NullPointerException", "Constructor", parameters,
				Collections.<JSpecCase> emptyList(), new JSkip(), new AlloyTyping(), new ArrayList<AlloyFormula>());

		this.module = new JDynAlloyModule("java_lang_NullPointerException",
				signature, classSignature, null, Collections
						.<JField> emptyList(), Collections
						.<JClassInvariant> emptySet(), Collections
						.<JClassConstraint> emptySet(), Collections
						.<JObjectInvariant> emptySet(), Collections
						.<JObjectConstraint> emptySet(), Collections
						.<JRepresents> emptySet(), Collections
						.<JProgramDeclaration> singleton(constructor), null, null);

		if (JDynAlloyConfig.getInstance().getNewExceptionsAreLiterals() == true) {
			JSignature literalSingleton = buildLiteralSingleton("java_lang_NullPointerException");
			module.setLiteralSingleton(literalSingleton);
		}

		// this.bindings = new HashMap<JBindingKey, JProgramDeclaration>();
		// this.bindings.put(NULLPOINTER_EXCEPTION_CONSTRUCTOR_KEY,
		// constructor);
	}

	@Override
	public JDynAlloyModule getModule() {
		return module;
	}

	// @Override
	// public Map<JBindingKey, JProgramDeclaration> getProgramBindings() {
	// return bindings;
	// }

}
