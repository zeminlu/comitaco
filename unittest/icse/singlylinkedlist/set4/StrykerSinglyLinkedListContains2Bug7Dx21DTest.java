package icse.singlylinkedlist.set4;

import ar.edu.taco.regresion.CollectionTestBase;
import ar.uba.dc.rfm.dynalloy.visualization.VizException;

public class StrykerSinglyLinkedListContains2Bug7Dx21DTest extends CollectionTestBase {

	@Override
	protected String getClassToCheck() {
		return "icse.singlylinkedlist.set4.SinglyLinkedListContains2Bug7Dx21D";
	}

			
	public void test_containsTest() throws VizException {
		setConfigKeyRelevantClasses("icse.singlylinkedlist.set4.SinglyLinkedListContains2Bug7Dx21D,icse.singlylinkedlist.SinglyLinkedListNode");
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
		setConfigKeyTypeScopes("icse.singlylinkedlist.set4.SinglyLinkedListContains2Bug7Dx21D:1,icse.singlylinkedlist.SinglyLinkedListNode:3");
		check(GENERIC_PROPERTIES,"contains_0",true);
	}



}
