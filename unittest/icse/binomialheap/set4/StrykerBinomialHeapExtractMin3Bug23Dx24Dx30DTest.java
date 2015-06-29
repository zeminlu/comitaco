package icse.binomialheap.set4;

import ar.edu.taco.regresion.CollectionTestBase;
import ar.uba.dc.rfm.dynalloy.visualization.VizException;

public class StrykerBinomialHeapExtractMin3Bug23Dx24Dx30DTest extends CollectionTestBase {

	@Override
	protected String getClassToCheck() {
		return "icse.binomialheap.set4.BinomialHeapExtractMin3Bug23Dx24Dx30D";
	}
			
	public void test_extractMinTest() throws VizException {
        setConfigKeyRelevantClasses("icse.binomialheap.set4.BinomialHeapExtractMin3Bug23Dx24Dx30D,icse.binomialheap.BinomialHeapNode");
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
        setConfigKeyTypeScopes("icse.binomialheap.set4.BinomialHeapExtractMin3Bug23Dx24Dx30D:1,icse.binomialheap.BinomialHeapNode:5");
        check(GENERIC_PROPERTIES, "extractMin_0", false);
	}

}
