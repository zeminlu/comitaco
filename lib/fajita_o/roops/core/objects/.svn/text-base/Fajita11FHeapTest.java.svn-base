package roops.core.objects;

import ar.edu.taco.regresion.CollectionTestBase;
import ar.edu.taco.regresion.GenericTestBase;
import ar.uba.dc.rfm.dynalloy.visualization.VizException;

public class Fajita11FHeapTest extends CollectionTestBase {

	@Override
	protected String getClassToCheck() {
		return "roops.core.objects.FibHeap";
	}
	
	public void test_minimumTest() throws VizException {
		setConfigKeyRelevantClasses("roops.core.objects.FibHeap,roops.core.objects.FibHeapNode");
		setConfigKeyRelevancyAnalysis(true);
		setConfigKeyCheckNullDereference(true);
		setConfigKeyUseJavaArithmetic(true);
		setConfigKeySkolemizeInstanceInvariant(false);
		setConfigKeySkolemizeInstanceAbstraction(false);
		setConfigKeyRemoveQuantifiers(true);
		// Infer-Scope
		setConfigKeyInferScope(true);
		setConfigKeyObjectScope(1);
		setConfigKeyLoopUnroll(1);
		// SBP+BOUND
		setConfigKeyUseJavaSBP(true);
		setConfigKeyUseTightUpperBounds(true);
		// JUNIT
		setConfigKeyGenerateUnitTestCase(true);

		runAndCheck(GENERIC_PROPERTIES,"minimumTest_0", true);
	}
	
	public void test_removeMinTest() throws VizException {
		setConfigKeyRelevantClasses("roops.core.objects.FibHeap,roops.core.objects.FibHeapNode");
		setConfigKeyRelevancyAnalysis(true);
		setConfigKeyCheckNullDereference(true);
		setConfigKeyUseJavaArithmetic(true);
		setConfigKeySkolemizeInstanceInvariant(false);
		setConfigKeySkolemizeInstanceAbstraction(false);
		setConfigKeyRemoveQuantifiers(true);
		// Infer-Scope
		setConfigKeyInferScope(true);
		setConfigKeyObjectScope(1);
		setConfigKeyLoopUnroll(1);
		// SBP+BOUND
		setConfigKeyUseJavaSBP(true);
		setConfigKeyUseTightUpperBounds(true);
		// JUNIT
		setConfigKeyGenerateUnitTestCase(true);

		runAndCheck(GENERIC_PROPERTIES,"removeMinTest_0", true);
	}
	
	public void test_insertNodeTest() throws VizException {
		setConfigKeyRelevantClasses("roops.core.objects.FibHeap,roops.core.objects.FibHeapNode");
		setConfigKeyRelevancyAnalysis(true);
		setConfigKeyCheckNullDereference(true);
		setConfigKeyUseJavaArithmetic(true);
		setConfigKeySkolemizeInstanceInvariant(false);
		setConfigKeySkolemizeInstanceAbstraction(false);
		setConfigKeyRemoveQuantifiers(true);
		// Infer-Scope
		setConfigKeyInferScope(true);
		setConfigKeyObjectScope(1);
		setConfigKeyLoopUnroll(1);
		// SBP+BOUND
		setConfigKeyUseJavaSBP(true);
		setConfigKeyUseTightUpperBounds(true);
		// JUNIT
		setConfigKeyGenerateUnitTestCase(true);

		runAndCheck(GENERIC_PROPERTIES,"insertNodeTest_0", true);
	}
	

}

