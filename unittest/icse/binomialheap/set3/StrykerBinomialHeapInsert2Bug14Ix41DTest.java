package icse.binomialheap.set3;

import ar.edu.taco.regresion.CollectionTestBase;
import ar.uba.dc.rfm.dynalloy.visualization.VizException;

public class StrykerBinomialHeapInsert2Bug14Ix41DTest extends CollectionTestBase {

	@Override
	protected String getClassToCheck() {
		return "icse.binomialheap.set3.BinomialHeapInsert2Bug14Ix41D";
	}
			
	public void test_insertTest() throws VizException {
        setConfigKeyRelevantClasses("icse.binomialheap.set3.BinomialHeapInsert2Bug14Ix41D,icse.binomialheap.BinomialHeapNode");
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
        setConfigKeyTypeScopes("icse.binomialheap.set3.BinomialHeapInsert2Bug14Ix41D:1,icse.binomialheap.BinomialHeapNode:5");
        check(GENERIC_PROPERTIES, "insert_0", false);
	}

}
