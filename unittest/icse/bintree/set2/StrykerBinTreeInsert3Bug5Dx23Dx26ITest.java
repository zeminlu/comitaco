package icse.bintree.set2;

import ar.edu.taco.regresion.CollectionTestBase;
import ar.uba.dc.rfm.dynalloy.visualization.VizException;

public class StrykerBinTreeInsert3Bug5Dx23Dx26ITest extends CollectionTestBase {

	@Override
	protected String getClassToCheck() {
		return "icse.bintree.set2.BinTreeInsert3Bug5Dx23Dx26I";
	}

	public void test_containsTest() throws VizException {
		setConfigKeyRelevantClasses("icse.bintree.set2.BinTreeInsert3Bug5Dx23Dx26I,icse.bintree.BinTreeNode");
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
		setConfigKeyTypeScopes("icse.bintree.set2.BinTreeInsert3Bug5Dx23Dx26I:1,icse.bintree.BinTreeNode:3");
		check(GENERIC_PROPERTIES,"insert_0",true);
	}

}
