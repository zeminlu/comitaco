package icse.bintree.set3;

import ar.edu.taco.regresion.CollectionTestBase;
import ar.uba.dc.rfm.dynalloy.visualization.VizException;

public class StrykerBinTreeInsert4Bug9Dx16Dx20Dx27DTest extends CollectionTestBase {

	@Override
	protected String getClassToCheck() {
		return "icse.bintree.set3.BinTreeInsert4Bug9Dx16Dx20Dx27D";
	}
			
	public void test_insertTest() throws VizException {
        setConfigKeyRelevantClasses("icse.bintree.set3.BinTreeInsert4Bug9Dx16Dx20Dx27D,icse.bintree.BinTreeNode");
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
        setConfigKeyTypeScopes("icse.bintree.set3.BinTreeInsert4Bug9Dx16Dx20Dx27D:1,icse.bintree.BinTreeNode:3");
        check(GENERIC_PROPERTIES,"insert_0",true);
	}

}
