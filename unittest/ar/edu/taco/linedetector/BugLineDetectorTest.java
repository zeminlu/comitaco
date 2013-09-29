package ar.edu.taco.linedetector;

import java.util.Properties;

import ar.edu.taco.regresion.CollectionTestBase;
import ar.uba.dc.rfm.dynalloy.visualization.VizException;

public class BugLineDetectorTest extends CollectionTestBase {
	
	@Override
	protected String getClassToCheck() {
		return "examples.singlylist.SinglyLinkedList";
	}
	
	public void test_contains() throws VizException {
		setConfigKeyRelevantClasses("examples.singlylist.SinglyLinkedList,examples.singlylist.SinglyLinkedListNode");
		setConfigKeyRelevancyAnalysis(true);
		setConfigKeyCheckNullDereference(true);
		setConfigKeyUseJavaArithmetic(false);
		setConfigKeyObjectScope(3);
		setConfigKeyInferScope(false);
		setConfigKeyTypeScopes("examples.singlylist.SinglyLinkedList:1,examples.singlylist.SinglyLinkedListNode:7");
		setConfigKeySkolemizeInstanceInvariant(true);
		setConfigKeySkolemizeInstanceAbstraction(true);
		setConfigKeyGenerateUnitTestCase(false);
	
		Properties newOverProp = getProperties();
		newOverProp.put("generateCheck", "false");
		newOverProp.put("generateRun", "false");
		newOverProp.put("include_simulation_program_declaration", "true");
		
		BugLineDetector main = new BugLineDetector(GENERIC_PROPERTIES, newOverProp, "contains_0");
		System.out.println("Entrando al run...");
		
		main.run(System.getProperty("user.dir") + "/tests/examples/singlylist/SinglyLinkedList.java");
		System.out.println("Salido del run.");
	}
}
