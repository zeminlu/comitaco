package ase2016.introclass.grade;

import ar.edu.taco.regresion.CollectionTestBase;
import ar.uba.dc.rfm.dynalloy.visualization.VizException;

public class StrykerIntroClassGrade_fe9d5fb9_003Test extends CollectionTestBase {

	@Override
	protected String getClassToCheck() {
		return "ase2016.introclass.grade.introclass_fe9d5fb9_003";
	}

			
	public void test_gradeTest() throws VizException { //string bug lost in translation
		setConfigKeyRelevantClasses("ase2016.introclass.grade.introclass_fe9d5fb9_003");
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
		setConfigKeyTypeScopes("ase2016.introclass.grade.introclass_fe9d5fb9_003:1");
		check(GENERIC_PROPERTIES,"grade_0",true);
	}



}
