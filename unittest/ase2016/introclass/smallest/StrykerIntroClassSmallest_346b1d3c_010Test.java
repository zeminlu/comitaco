package ase2016.introclass.smallest;

import ar.edu.taco.regresion.CollectionTestBase;
import ar.uba.dc.rfm.dynalloy.visualization.VizException;

public class StrykerIntroClassSmallest_346b1d3c_010Test extends CollectionTestBase {

	@Override
	protected String getClassToCheck() {
		return "ase2016.introclass.smallest.introclass_346b1d3c_010";
	}

			
	public void test_smallestTest() throws VizException { //el bug es un error en una cadena, el bug se pierde en la traduccion
		setConfigKeyRelevantClasses("ase2016.introclass.smallest.introclass_346b1d3c_010");
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
		setConfigKeyTypeScopes("ase2016.introclass.smallest.introclass_346b1d3c_010:1");
		check(GENERIC_PROPERTIES,"smallest_0",true);
	}



}
