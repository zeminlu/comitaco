package ar.edu.taco.regresion.stryker;

import ar.edu.taco.regresion.CollectionTestBase;
import ar.uba.dc.rfm.dynalloy.visualization.VizException;

public class StrykerAVLTreeTest extends CollectionTestBase {

	@Override
	protected String getClassToCheck() {
		return "roops.core.objects.AvlTree";
	}

	public void test_findTest() throws VizException {
		setConfigKeyRelevantClasses("roops.core.objects.AvlTree, roops.core.objects.AvlNode");
		setConfigKeyRelevancyAnalysis(true);
		setConfigKeyCheckNullDereference(true);
		setConfigKeyUseJavaArithmetic(true);
		setConfigKeyInferScope(true);
	    setConfigKeyIntBithwidth(4);
	    setConfigKeyLoopUnroll(4);
	    setConfigKeyObjectScope(0);
		setConfigKeySkolemizeInstanceInvariant(true);
		setConfigKeySkolemizeInstanceAbstraction(true);
		setConfigKeyGenerateUnitTestCase(true);
		setConfigKeyAttemptToCorrectBug(true);
		setConfigKeyRemoveQuantifiers(true);
		setConfigKeyUseJavaSBP(true);
		setConfigKeyUseTightUpperBounds(true);
		setConfigKeyMaxStrykerMethodsPerFile(50);
//		setCondigKeyBuildJavaTrace(true);	
		setConfigKeyTypeScopes("roops.core.objects.AvlTree:1,roops.core.objects.AvlNode:7");
		check(GENERIC_PROPERTIES,"find_0", false);
	}
	
	
	public void test_findMinTest() throws VizException {
		setConfigKeyRelevantClasses("roops.core.objects.AvlTree, roops.core.objects.AvlNode");
		setConfigKeyRelevancyAnalysis(true);
		setConfigKeyCheckNullDereference(true);
		setConfigKeyUseJavaArithmetic(true);
		setConfigKeyInferScope(true);
	    setConfigKeyIntBithwidth(4);
	    setConfigKeyLoopUnroll(4);
	    setConfigKeyObjectScope(0);
		setConfigKeySkolemizeInstanceInvariant(true);
		setConfigKeySkolemizeInstanceAbstraction(true);
		setConfigKeyGenerateUnitTestCase(true);
		setConfigKeyAttemptToCorrectBug(true);
		setConfigKeyRemoveQuantifiers(true);
		setConfigKeyUseJavaSBP(true);
		setConfigKeyUseTightUpperBounds(true);
		setConfigKeyMaxStrykerMethodsPerFile(50);
//		setCondigKeyBuildJavaTrace(true);	
		setConfigKeyTypeScopes("roops.core.objects.AvlTree:1,roops.core.objects.AvlNode:7");
		check(GENERIC_PROPERTIES,"findMin_0", false);
	}

	
	
	public void test_findMaxTest() throws VizException {
		setConfigKeyRelevantClasses("roops.core.objects.AvlTree, roops.core.objects.AvlNode");
		setConfigKeyRelevancyAnalysis(true);
		setConfigKeyCheckNullDereference(true);
		setConfigKeyUseJavaArithmetic(true);// fun_set_size
		setConfigKeyInferScope(true);
	    setConfigKeyIntBithwidth(4);
	    setConfigKeyLoopUnroll(4);
	    setConfigKeyObjectScope(0);
		setConfigKeySkolemizeInstanceInvariant(true);
		setConfigKeySkolemizeInstanceAbstraction(true);
		setConfigKeyGenerateUnitTestCase(true);
		setConfigKeyAttemptToCorrectBug(true);
		setConfigKeyRemoveQuantifiers(true);
		setConfigKeyUseJavaSBP(true);
		setConfigKeyUseTightUpperBounds(true);
		setConfigKeyMaxStrykerMethodsPerFile(50);
//		setCondigKeyBuildJavaTrace(true);	
		setConfigKeyTypeScopes("roops.core.objects.AvlTree:1,roops.core.objects.AvlNode:7");
		check(GENERIC_PROPERTIES,"findMax_0", false);
	}
	
	public void test_privateinsertTest() throws VizException {
		setConfigKeyRelevantClasses("roops.core.objects.AvlTree, roops.core.objects.AvlNode");
		setConfigKeyRelevancyAnalysis(true);
		setConfigKeyCheckNullDereference(true);
		setConfigKeyUseJavaArithmetic(false);
		setConfigKeyInferScope(true);
	    setConfigKeyIntBithwidth(4);
	    setConfigKeyLoopUnroll(5);
	    setConfigKeyObjectScope(0);
		setConfigKeySkolemizeInstanceInvariant(true);
		setConfigKeySkolemizeInstanceAbstraction(true);
		setConfigKeyGenerateUnitTestCase(true);
		setConfigKeyAttemptToCorrectBug(false);
		setConfigKeyRemoveQuantifiers(true);
		setConfigKeyUseJavaSBP(false);
		setConfigKeyUseTightUpperBounds(false);
		setConfigKeyMaxStrykerMethodsPerFile(50);
//		setCondigKeyBuildJavaTrace(true);	
		setConfigKeyTypeScopes("roops.core.objects.AvlTree:1,roops.core.objects.AvlNode:5");
		check(GENERIC_PROPERTIES,"privateinsert_0", false);
	}
	
	public void test_pathToMax() throws VizException {
		setConfigKeyRelevantClasses("roops.core.objects.AvlTree, roops.core.objects.AvlNode, roops.core.objects.SinglyLinkedList, roops.core.objects.SinglyLinkedListNode");
		setConfigKeyRelevancyAnalysis(true);
		setConfigKeyCheckNullDereference(true);
		setConfigKeyUseJavaArithmetic(true);
		setConfigKeyInferScope(true);
	    setConfigKeyIntBithwidth(4);
	    setConfigKeyLoopUnroll(5);
	    setConfigKeyObjectScope(0);
		setConfigKeySkolemizeInstanceInvariant(true);
		setConfigKeySkolemizeInstanceAbstraction(true);
		setConfigKeyGenerateUnitTestCase(true);
		setConfigKeyAttemptToCorrectBug(false);
		setConfigKeyRemoveQuantifiers(true);
		setConfigKeyUseJavaSBP(true);
		setConfigKeyUseTightUpperBounds(true);
		setConfigKeyMaxStrykerMethodsPerFile(50);
//		setCondigKeyBuildJavaTrace(true);	
		setConfigKeyTypeScopes("roops.core.objects.AvlTree:1,roops.core.objects.AvlNode:7,roops.core.objects.SinglyLinkedList:1,roops.core.objects.SinglyLinkedListNode:7");
		check(GENERIC_PROPERTIES,"pathToMax_0", false);
	}
}