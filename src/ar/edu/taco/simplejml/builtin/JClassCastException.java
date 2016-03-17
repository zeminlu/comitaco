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
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import ar.edu.jdynalloy.JDynAlloyConfig;
import ar.edu.jdynalloy.ast.JDynAlloyModule;
import ar.edu.jdynalloy.ast.JClassConstraint;
import ar.edu.jdynalloy.ast.JClassInvariant;
import ar.edu.jdynalloy.ast.JField;
import ar.edu.jdynalloy.ast.JModifies;
import ar.edu.jdynalloy.ast.JObjectConstraint;
import ar.edu.jdynalloy.ast.JObjectInvariant;
import ar.edu.jdynalloy.ast.JPostcondition;
import ar.edu.jdynalloy.ast.JPrecondition;
import ar.edu.jdynalloy.ast.JProgramDeclaration;
import ar.edu.jdynalloy.ast.JRepresents;
import ar.edu.jdynalloy.ast.JSignature;
import ar.edu.jdynalloy.ast.JSkip;
import ar.edu.jdynalloy.ast.JVariableDeclaration;
import ar.edu.jdynalloy.factory.JDynAlloyFactory;
import ar.edu.jdynalloy.factory.JExpressionFactory;
import ar.edu.jdynalloy.xlator.JDynAlloyTyping;
import ar.edu.jdynalloy.xlator.JType;
import ar.edu.taco.simplejml.helpers.ArgEncoder;
import ar.uba.dc.rfm.alloy.AlloyTyping;
import ar.uba.dc.rfm.alloy.ast.formulas.AlloyFormula;

public class JClassCastException implements IBuiltInModule {

	private static JClassCastException instance;

	public static JClassCastException getInstance() {
		if (instance == null)
			instance = new JClassCastException();
		return instance;
	}

	private final JDynAlloyModule module;

	private JClassCastException() {

		final boolean signatureIsAbstract;
		if (JDynAlloyConfig.getInstance().getNewExceptionsAreLiterals() == true) {
			signatureIsAbstract = true;
		} else
			signatureIsAbstract = false;

		JSignature signature = new JSignature(true, signatureIsAbstract,
				"java_lang_ClassCastException", new JDynAlloyTyping(), false,
				"java_lang_RuntimeException", null, Collections
						.<String> emptySet(), Collections
						.<AlloyFormula> emptySet() /* facts */, Collections
						.<String> emptyList(), Collections.<String> emptyList());

		JSignature classSignature;
		if (JDynAlloyConfig.getInstance().getUseClassSingletons() == true)
			classSignature = new JSignature(true, false,
					"java_lang_ClassCastExceptionClass", new JDynAlloyTyping(),
					false, "Class", null, Collections.<String> emptySet(),
					Collections.<AlloyFormula> emptySet() /* facts */,
					Collections.<String> emptyList(), Collections
							.<String> emptyList());
		else
			classSignature = null;

		JVariableDeclaration thisDeclaration = new JVariableDeclaration(
				JExpressionFactory.THIS_VARIABLE, JType
						.parse("java_lang_ClassCastException"));

		ArgEncoder encoder = new ArgEncoder(false, true, false, 0);
		List<JVariableDeclaration> parameters = encoder.encode(thisDeclaration,
				JDynAlloyFactory.THROW_DECLARATION, null, Collections
						.<JVariableDeclaration> emptyList());
		JProgramDeclaration constructor = JProgramDeclaration
				.buildJProgramDeclaration(false, true, false,
						"java_lang_ClassCastException", "Constructor",
						parameters, Collections.<JPrecondition> emptySet(),
						Collections.<JModifies> emptySet(), Collections
								.<JPostcondition> emptySet(), new JSkip(), new AlloyTyping(), new ArrayList<AlloyFormula>());

		this.module = new JDynAlloyModule("java_lang_ClassCastException",
				signature, classSignature, null, Collections
						.<JField> emptyList(), Collections
						.<JClassInvariant> emptySet(), Collections
						.<JClassConstraint> emptySet(), Collections
						.<JObjectInvariant> emptySet(), Collections
						.<JObjectConstraint> emptySet(), Collections
						.<JRepresents> emptySet(), Collections
						.<JProgramDeclaration> singleton(constructor), 
						new AlloyTyping(), 
						new ArrayList<AlloyFormula>() {
							
							@Override
							public <T> T[] toArray(T[] a) {
								// TODO Auto-generated method stub
								return null;
							}
							
							@Override
							public Object[] toArray() {
								// TODO Auto-generated method stub
								return null;
							}
							
							@Override
							public List<AlloyFormula> subList(int fromIndex, int toIndex) {
								// TODO Auto-generated method stub
								return null;
							}
							
							@Override
							public int size() {
								// TODO Auto-generated method stub
								return 0;
							}
							
							@Override
							public AlloyFormula set(int index, AlloyFormula element) {
								// TODO Auto-generated method stub
								return null;
							}
							
							@Override
							public boolean retainAll(Collection<?> c) {
								// TODO Auto-generated method stub
								return false;
							}
							
							@Override
							public boolean removeAll(Collection<?> c) {
								// TODO Auto-generated method stub
								return false;
							}
							
							@Override
							public AlloyFormula remove(int index) {
								// TODO Auto-generated method stub
								return null;
							}
							
							@Override
							public boolean remove(Object o) {
								// TODO Auto-generated method stub
								return false;
							}
							
							@Override
							public ListIterator<AlloyFormula> listIterator(int index) {
								// TODO Auto-generated method stub
								return null;
							}
							
							@Override
							public ListIterator<AlloyFormula> listIterator() {
								// TODO Auto-generated method stub
								return null;
							}
							
							@Override
							public int lastIndexOf(Object o) {
								// TODO Auto-generated method stub
								return 0;
							}
							
							@Override
							public Iterator<AlloyFormula> iterator() {
								// TODO Auto-generated method stub
								return null;
							}
							
							@Override
							public boolean isEmpty() {
								// TODO Auto-generated method stub
								return false;
							}
							
							@Override
							public int indexOf(Object o) {
								// TODO Auto-generated method stub
								return 0;
							}
							
							@Override
							public AlloyFormula get(int index) {
								// TODO Auto-generated method stub
								return null;
							}
							
							@Override
							public boolean containsAll(Collection<?> c) {
								// TODO Auto-generated method stub
								return false;
							}
							
							@Override
							public boolean contains(Object o) {
								// TODO Auto-generated method stub
								return false;
							}
							
							@Override
							public void clear() {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public boolean addAll(int index, Collection<? extends AlloyFormula> c) {
								// TODO Auto-generated method stub
								return false;
							}
							
							@Override
							public boolean addAll(Collection<? extends AlloyFormula> c) {
								// TODO Auto-generated method stub
								return false;
							}
							
							@Override
							public void add(int index, AlloyFormula element) {
								// TODO Auto-generated method stub
								
							}
							
							@Override
							public boolean add(AlloyFormula e) {
								// TODO Auto-generated method stub
								return false;
							}
						});

		if (JDynAlloyConfig.getInstance().getNewExceptionsAreLiterals() == true) {

			JSignature literalSingleton = buildLiteralSingleton("java_lang_ClassCastException");
			module.setLiteralSingleton(literalSingleton);
		}

		// this.bindings = new HashMap<JBindingKey, JProgramDeclaration>();
		// this.bindings.put(INDEX_OUT_OF_BOUND_EXCEPTION_CONSTRUCTOR_KEY,
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
