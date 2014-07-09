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

import static ar.edu.jdynalloy.factory.JExpressionFactory.THIS_EXPRESSION;
import static ar.edu.jdynalloy.factory.JSignatureFactory.buildClass;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ar.edu.jdynalloy.ast.JDynAlloyModule;
import ar.edu.jdynalloy.ast.JAssume;
import ar.edu.jdynalloy.ast.JClassConstraint;
import ar.edu.jdynalloy.ast.JClassInvariant;
import ar.edu.jdynalloy.ast.JField;
import ar.edu.jdynalloy.ast.JObjectConstraint;
import ar.edu.jdynalloy.ast.JObjectInvariant;
import ar.edu.jdynalloy.ast.JProgramDeclaration;
import ar.edu.jdynalloy.ast.JRepresents;
import ar.edu.jdynalloy.ast.JSignature;
import ar.edu.jdynalloy.ast.JSpecCase;
import ar.edu.jdynalloy.ast.JStatement;
import ar.edu.jdynalloy.ast.JVariableDeclaration;
import ar.edu.jdynalloy.factory.JDynAlloyFactory;
import ar.edu.jdynalloy.factory.DynalloyFactory;
import ar.edu.jdynalloy.factory.JExpressionFactory;
import ar.edu.jdynalloy.factory.JPredicateFactory;
import ar.edu.jdynalloy.xlator.JType;
import ar.edu.taco.simplejml.helpers.ArgEncoder;
import ar.uba.dc.rfm.alloy.AlloyVariable;
import ar.uba.dc.rfm.alloy.ast.expressions.ExprFunction;
import ar.uba.dc.rfm.alloy.ast.expressions.ExprVariable;

public class JSystemArray implements IBuiltInModule {
	private final JDynAlloyModule module;

	private JSystemArray() {
		JSignature signature = buildClass("java_lang_SystemArray", "java_lang_Object");

		JSignature classSignature;
//		if (DynJAlloyConfig.getInstance().getUseClassSingletons() == true) {
//			classSignature = buildClassSingleton(signature.getSignatureId());
//		} else {
			classSignature = null;
//		}

		// Create the fields of the JSystemArray class
		JField object_array_field = new JField(DynalloyFactory.OBJECT_ARRAY_EXPRESSION.getVariable(), JType.parse("java_lang_SystemArray->(seq univ)"));
		
		List<JField> fields = new LinkedList<JField>();
		fields.add(object_array_field);		

		// Create the constructor of JSystemArray
		JProgramDeclaration systemArrayConstructor = buildConstructor();

		Set<JProgramDeclaration> programs = new HashSet<JProgramDeclaration>();
		programs.add(systemArrayConstructor);

		module = new JDynAlloyModule("java_lang_SystemArray", signature, classSignature, null, fields, 
				Collections.<JClassInvariant> emptySet(), Collections.<JClassConstraint> emptySet(), 
				Collections.<JObjectInvariant> emptySet(), Collections.<JObjectConstraint> emptySet(), Collections.<JRepresents> emptySet(), programs);
	}
	
	private JProgramDeclaration buildConstructor() {

		JVariableDeclaration thisDeclaration = new JVariableDeclaration(JExpressionFactory.THIS_VARIABLE, JType.parse("java_lang_SystemArray"));

		final AlloyVariable lenght = new AlloyVariable("length");
		final ExprVariable lenghtExpr = new ExprVariable(lenght);

		JVariableDeclaration valueDeclaration = new JVariableDeclaration(lenght, JType.parse("Int"));

		ArgEncoder encoder = new ArgEncoder(false, true, false, 1);
		List<JVariableDeclaration> ps = encoder.encode(thisDeclaration, JDynAlloyFactory.THROW_DECLARATION,	null, Collections.<JVariableDeclaration> singletonList(valueDeclaration));

		JStatement body;
		//if (DynJAlloyConfig.getInstance().getDynamicJavaLangFields() == true) {
		//	body = new JAssignment(this_intValue, valueExpr);
		//} else
		
		ExprFunction func = ExprFunction.buildExprFunction("arrayLength", DynalloyFactory.OBJECT_ARRAY_EXPRESSION, THIS_EXPRESSION);
				
		body = new JAssume(JPredicateFactory.eq(func, lenghtExpr));

		JStatement constructor = JDynAlloyFactory.block(JDynAlloyFactory.initializeThrow(), body);

		JProgramDeclaration constructorInteger = new JProgramDeclaration(false,	"java_lang_SystemArray", "Constructor", ps, Collections
						.<JSpecCase> emptyList(), constructor);

		return constructorInteger;
	}
	

	private static JSystemArray instance = null;

	public static JSystemArray getInstance() {
		if (instance == null)
			instance = new JSystemArray();

		return instance;
	}

	@Override
	public JDynAlloyModule getModule() {
		return module;
	}

//	@Override
//	public Map<JBindingKey, JProgramDeclaration> getProgramBindings() {
//		return new HashMap<JBindingKey, JProgramDeclaration>();
//	}

}
