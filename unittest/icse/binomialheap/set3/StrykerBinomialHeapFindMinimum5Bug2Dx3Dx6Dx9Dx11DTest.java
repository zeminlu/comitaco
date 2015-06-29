package icse.binomialheap.set3;

import ar.edu.taco.regresion.CollectionTestBase;
import ar.uba.dc.rfm.dynalloy.visualization.VizException;

public class StrykerBinomialHeapFindMinimum5Bug2Dx3Dx6Dx9Dx11DTest extends CollectionTestBase {

	@Override
	protected String getClassToCheck() {
		return "icse.binomialheap.set3.BinomialHeapFindMinimum5Bug2Dx3Dx6Dx9Dx11D";
	}
			
	public void test_findMinimumTest() throws VizException {
        setConfigKeyRelevantClasses("icse.binomialheap.set3.BinomialHeapFindMinimum5Bug2Dx3Dx6Dx9Dx11D,icse.binomialheap.BinomialHeapNode");
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
        setConfigKeyTypeScopes("icse.binomialheap.set3.BinomialHeapFindMinimum5Bug2Dx3Dx6Dx9Dx11D:1,icse.binomialheap.BinomialHeapNode:5");
        check(GENERIC_PROPERTIES, "findMinimum_0", false);
	}

}
