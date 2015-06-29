package icse.binomialheap.set5;

import ar.edu.taco.regresion.CollectionTestBase;
import ar.uba.dc.rfm.dynalloy.visualization.VizException;

public class StrykerBinomialHeapExtractMin5Bug7Dx8Dx13Ix21Dx27DTest extends CollectionTestBase {

	@Override
	protected String getClassToCheck() {
		return "icse.binomialheap.set5.BinomialHeapExtractMin5Bug7Dx8Dx13Ix21Dx27D";
	}
			
	public void test_extractMinTest() throws VizException {
        setConfigKeyRelevantClasses("icse.binomialheap.set5.BinomialHeapExtractMin5Bug7Dx8Dx13Ix21Dx27D,icse.binomialheap.BinomialHeapNode");
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
        setConfigKeyTypeScopes("icse.binomialheap.set5.BinomialHeapExtractMin5Bug7Dx8Dx13Ix21Dx27D:1,icse.binomialheap.BinomialHeapNode:5");
        check(GENERIC_PROPERTIES, "extractMin_0", false);
	}

}
