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
package ar.edu.taco.regresion.taco.avltree;

import ar.edu.taco.regresion.CollectionTestBase;
import ar.uba.dc.rfm.dynalloy.visualization.VizException;
/*
 * TODO: all test fail because in the source class the methods are commented. Check with the "Old" instead of "base" version of the source files
 */
public class RoopsCollectionsAvlTreeTest extends CollectionTestBase {

	@Override
	protected String getClassToCheck() {
		return "roops.core.objects.avltree.base.AvlTree";
	}
	
	public void test_findNodeTest() throws VizException {
		setConfigKeyRelevantClasses("roops.core.objects.avltree.base.AvlTree,roops.core.objects.avltree.base.AvlNode");
		setConfigKeyRelevancyAnalysis(true);
		setConfigKeyCheckNullDereference(true);
		setConfigKeyUseJavaArithmetic(false);
		setConfigKeyNestedLoopUnroll(true);
		setConfigKeyInferScope(false);
		setConfigKeyUseJavaSBP(true);
		setConfigKeyUseTightUpperBounds(false);
		setConfigKeySkolemizeInstanceInvariant(true);
		setConfigKeySkolemizeInstanceAbstraction(true);
		setConfigKeyLoopUnroll(5);
		setConfigKeyRemoveQuantifiers(true);
		setConfigKeyGenerateUnitTestCase(true);
		setConfigKeyNoVerify(false);
		this.setConfigKeyTypeScopes("roops.core.objects.avltree.base.AvlTree:1,roops.core.objects.avltree.base.AvlNode:5");
		check(GENERIC_PROPERTIES,"find_0", false);
	}
	
	public void test_findMinTest() throws VizException {
		setConfigKeyRelevantClasses("roops.core.objects.avltree.base.AvlTree,roops.core.objects.avltree.base.AvlNode");
		setConfigKeyRelevancyAnalysis(true);
		setConfigKeyCheckNullDereference(true);
		setConfigKeyUseJavaArithmetic(false);
		setConfigKeyNestedLoopUnroll(true);
		setConfigKeyInferScope(false);
		setConfigKeyUseJavaSBP(false);
		setConfigKeyUseTightUpperBounds(false);
		setConfigKeySkolemizeInstanceInvariant(true);
		setConfigKeySkolemizeInstanceAbstraction(true);
		setConfigKeyLoopUnroll(5);
		setConfigKeyRemoveQuantifiers(true);
		setConfigKeyGenerateUnitTestCase(true);
		setConfigKeyNoVerify(false);
		this.setConfigKeyTypeScopes("roops.core.objects.avltree.base.AvlTree:1,roops.core.objects.avltree.base.AvlNode:7");
		check(GENERIC_PROPERTIES,"findMin_0", false);
	}
	
	public void test_insertTest() throws VizException {
		setConfigKeyRelevantClasses("roops.core.objects.avltree.base.AvlTree,roops.core.objects.avltree.base.AvlNode");
		setConfigKeyRelevancyAnalysis(true);
		setConfigKeyCheckNullDereference(true);
		setConfigKeyUseJavaArithmetic(false);
		setConfigKeyNestedLoopUnroll(true);
		setConfigKeyInferScope(false);
		setConfigKeyUseJavaSBP(false);
		setConfigKeyUseTightUpperBounds(false);
		setConfigKeySkolemizeInstanceInvariant(true);
		setConfigKeySkolemizeInstanceAbstraction(true);
		setConfigKeyLoopUnroll(3);
		setConfigKeyRemoveQuantifiers(true);
		setConfigKeyGenerateUnitTestCase(true);
		setConfigKeyNoVerify(false);
		this.setConfigKeyTypeScopes("roops.core.objects.avltree.base.AvlTree:1,roops.core.objects.avltree.base.AvlNode:7");
		runAndCheck(GENERIC_PROPERTIES,"insert_0", true);
	}
	
	
}
