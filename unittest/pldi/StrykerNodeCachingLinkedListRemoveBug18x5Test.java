package pldi;

import ar.edu.taco.regresion.CollectionTestBase;
import ar.uba.dc.rfm.dynalloy.visualization.VizException;

public class StrykerNodeCachingLinkedListRemoveBug18x5Test extends CollectionTestBase {

	@Override
	protected String getClassToCheck() {
		return "pldi.nodecachinglinkedlist.NodeCachingLinkedListRemoveBug18x5";
	}

	public void test_containsTest() throws VizException {
		setConfigKeyRelevantClasses("pldi.nodecachinglinkedlist.NodeCachingLinkedListRemoveBug18x5,pldi.nodecachinglinkedlist.LinkedListNode");
		setConfigKeyRelevancyAnalysis(true);
		setConfigKeyCheckNullDereference(true);
		setConfigKeyUseJavaArithmetic(false);
		setConfigKeyInferScope(true);
		setConfigKeyObjectScope(0);
		setConfigKeyIntBithwidth(4);
        setConfigKeyLoopUnroll(4);
		setConfigKeySkolemizeInstanceInvariant(true);
		setConfigKeySkolemizeInstanceAbstraction(false);
		setConfigKeyGenerateUnitTestCase(true);
		setConfigKeyAttemptToCorrectBug(true);
		setConfigKeyMaxStrykerMethodsPerFile(1);
		setConfigKeyRemoveQuantifiers(true);
		setConfigKeyUseJavaSBP(true);
		setConfigKeyUseTightUpperBounds(true);
		setConfigKeyTypeScopes("pldi.nodecachinglinkedlist.NodeCachingLinkedListRemoveBug18x5:1,pldi.nodecachinglinkedlist.LinkedListNode:4");
		check(GENERIC_PROPERTIES,"remove_0",false);
	}
	
}
