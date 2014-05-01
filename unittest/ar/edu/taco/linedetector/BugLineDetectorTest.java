package ar.edu.taco.linedetector;

import java.util.Properties;

import ar.edu.taco.regresion.CollectionTestBase;
import ar.uba.dc.rfm.dynalloy.visualization.VizException;

public class BugLineDetectorTest extends CollectionTestBase {

	private static String testClassPath = "roops.core.objects";
	// private static String testClassPath = "examples.singlylist";

	@Override
	protected String getClassToCheck() {
		return testClassPath + ".SinglyLinkedList";
	}

	public void test_contains() throws VizException {
		setConfigKeyRelevantClasses(testClassPath + ".SinglyLinkedList,"
				+ testClassPath + ".SinglyLinkedListNode," + testClassPath
				+ ".BugLineMarker");
		setConfigKeyRelevancyAnalysis(true);
		setConfigKeyCheckNullDereference(true);
		setConfigKeyUseJavaArithmetic(false);
		setConfigKeyObjectScope(3);
		setConfigKeyInferScope(false);

		setConfigKeyTypeScopes(testClassPath + ".SinglyLinkedList:1,"
				+ testClassPath + ".SinglyLinkedListNode:2");
		// setConfigKeyTypeScopes("examples.singlylist.SinglyLinkedList:1,examples.singlylist.SinglyLinkedListNode:7");

		setConfigKeySkolemizeInstanceInvariant(true);
		setConfigKeySkolemizeInstanceAbstraction(true);
		setConfigKeyGenerateUnitTestCase(false);

		Properties newOverProp = getProperties();
		newOverProp.put("generateCheck", "true");
		newOverProp.put("generateRun", "false");
		newOverProp.put("include_simulation_program_declaration", "true");

		BugLineDetector main = new BugLineDetector(GENERIC_PROPERTIES,
				newOverProp, "contains_0");
		System.out.println("Entrando al run...");

		main.run("roops/core/objects/SinglyLinkedList.java"/* "examples/singlylist/SinglyLinkedList.java" */);

		// main.run(System.getProperty("user.dir") +
		// "/tests/examples/singlylist/SinglyLinkedList.java");
		System.out.println("Salido del run.");
	}
}
