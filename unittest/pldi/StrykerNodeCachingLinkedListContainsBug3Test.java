package pldi;

import ar.edu.taco.regresion.CollectionTestBase;
import ar.uba.dc.rfm.dynalloy.visualization.VizException;

public class StrykerNodeCachingLinkedListContainsBug3Test extends CollectionTestBase {

	@Override
	protected String getClassToCheck() {
		return "pldi.nodecachinglinkedlist.NodeCachingLinkedListContainsBug3";
	}

	public void test_containsTest() throws VizException {
		setConfigKeyRelevantClasses("pldi.nodecachinglinkedlist.NodeCachingLinkedListContainsBug3,pldi.nodecachinglinkedlist.LinkedListNode");
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
		setConfigKeyTypeScopes("pldi.nodecachinglinkedlist.NodeCachingLinkedListContainsBug3:1,pldi.nodecachinglinkedlist.LinkedListNode:4");
		check(GENERIC_PROPERTIES,"contains_0",true);
	}
	
}
