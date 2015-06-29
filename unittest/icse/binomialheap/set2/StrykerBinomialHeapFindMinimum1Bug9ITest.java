package icse.binomialheap.set2;

import ar.edu.taco.regresion.CollectionTestBase;
import ar.uba.dc.rfm.dynalloy.visualization.VizException;

public class StrykerBinomialHeapFindMinimum1Bug9ITest extends CollectionTestBase {

	@Override
	protected String getClassToCheck() {
		return "icse.binomialheap.set2.BinomialHeapFindMinimum1Bug9I";
	}
			
	public void test_findMinimumTest() throws VizException {
        setConfigKeyRelevantClasses("icse.binomialheap.set2.BinomialHeapFindMinimum1Bug9I,icse.binomialheap.BinomialHeapNode");
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
        setConfigKeyTypeScopes("icse.binomialheap.set2.BinomialHeapFindMinimum1Bug9I:1,icse.binomialheap.BinomialHeapNode:5");
        check(GENERIC_PROPERTIES, "findMinimum_0", false);
	}

}
