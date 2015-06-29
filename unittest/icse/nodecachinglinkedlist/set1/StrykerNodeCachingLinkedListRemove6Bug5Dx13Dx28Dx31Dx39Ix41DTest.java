package icse.nodecachinglinkedlist.set1;

import ar.edu.taco.regresion.CollectionTestBase;
import ar.uba.dc.rfm.dynalloy.visualization.VizException;

public class StrykerNodeCachingLinkedListRemove6Bug5Dx13Dx28Dx31Dx39Ix41DTest extends CollectionTestBase {

	@Override
	protected String getClassToCheck() {
		return "roops.core.objects.SinglyLinkedListGeneric";
	}
			
	public void test_removeTest() throws VizException {
        setConfigKeyRelevantClasses("roops.core.objects.NodeCachingLinkedListRemove6Bug5Dx13Dx28Dx31Dx39Ix41D,roops.core.objects.LinkedListNode");
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
        setConfigKeyTypeScopes("roops.core.objects.NodeCachingLinkedListRemove6Bug5Dx13Dx28Dx31Dx39Ix41D:1,roops.core.objects.LinkedListNode:4");
        check(GENERIC_PROPERTIES,"remove_0",true);
	}

}
