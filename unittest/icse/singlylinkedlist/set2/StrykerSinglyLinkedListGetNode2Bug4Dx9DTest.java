package icse.singlylinkedlist.set2;

import ar.edu.taco.regresion.CollectionTestBase;
import ar.uba.dc.rfm.dynalloy.visualization.VizException;

public class StrykerSinglyLinkedListGetNode2Bug4Dx9DTest extends CollectionTestBase {

	@Override
	protected String getClassToCheck() {
		return "icse.singlylinkedlist.set2.SinglyLinkedListGetNode2Bug4Dx9D";
	}
			
	public void test_getNodeTest() throws VizException {
		setConfigKeyRelevantClasses("icse.singlylinkedlist.set2.SinglyLinkedListGetNode2Bug4Dx9D,icse.singlylinkedlist.SinglyLinkedListNode");
		setConfigKeyRelevancyAnalysis(true);
		setConfigKeyCheckNullDereference(true);
		setConfigKeyUseJavaArithmetic(false);
		setConfigKeyCheckArithmeticException(true);
		setConfigKeyInferScope(true);
		setConfigKeyObjectScope(0);
		setConfigKeyIntBithwidth(4);
        setConfigKeyLoopUnroll(4);
		setConfigKeySkolemizeInstanceInvariant(true);
		setConfigKeySkolemizeInstanceAbstraction(true);
		setConfigKeyGenerateUnitTestCase(true);
		setConfigKeyAttemptToCorrectBug(true);
		setConfigKeyMaxStrykerMethodsPerFile(1);
		setConfigKeyRemoveQuantifiers(true);
		setConfigKeyUseJavaSBP(true);
		setConfigKeyUseTightUpperBounds(true);
		setConfigKeyTypeScopes("icse.singlylinkedlist.set2.SinglyLinkedListGetNode2Bug4Dx9D:1,icse.singlylinkedlist.SinglyLinkedListNode:3");
		check(GENERIC_PROPERTIES,"getNode_0",true);
	}

}
