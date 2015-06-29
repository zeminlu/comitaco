package icse.binomialheap.set4;

import ar.edu.taco.regresion.CollectionTestBase;
import ar.uba.dc.rfm.dynalloy.visualization.VizException;

public class StrykerBinomialHeapFindMinimum2Bug4Dx5DTest extends CollectionTestBase {

	@Override
	protected String getClassToCheck() {
		return "icse.binomialheap.set4.BinomialHeapFindMinimum2Bug4Dx5D";
	}
			
	public void test_findMinimumTest() throws VizException {
        setConfigKeyRelevantClasses("icse.binomialheap.set4.BinomialHeapFindMinimum2Bug4Dx5D,icse.binomialheap.BinomialHeapNode");
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
        setConfigKeyTypeScopes("icse.binomialheap.set4.BinomialHeapFindMinimum2Bug4Dx5D:1,icse.binomialheap.BinomialHeapNode:5");
        check(GENERIC_PROPERTIES, "findMinimum_0", false);
	}

}
