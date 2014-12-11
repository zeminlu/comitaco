package ar.edu.taco.linedetector;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import edu.mit.csail.sdg.alloy4.Pair;
import ar.edu.taco.regresion.CollectionTestBase;
import ar.uba.dc.rfm.dynalloy.visualization.VizException;

public class BugLineDetectorTest extends CollectionTestBase {

	private static String testClassPath = "roops.core.objects";
	// private static String testClassPath = "examples.singlylist";
//	private static String[] relevantClasses = {"SinglyLinkedList", "SinglyLinkedListNode", "BugLineMarker"};
//	private static int[] relevantClassesAmounts = {1, 2, 1};
//	private static String classToCheck = "SinglyLinkedList";
//	private static String classToCheckPath = "roops/core/objects/SinglyLinkedList.java";
//	private static String methodToCheck = "contains";
	
	private static String[] relevantClasses = {"ArrayList", "BugLineMarker"};
	private static int[] relevantClassesAmounts = {1, 1};
	private static String classToCheck = "ArrayList";
	private static String classToCheckPath = "roops/core/objects/ArrayList.java";
	private static String methodToCheck = "indexOf";
	
	@Override
	protected String getClassToCheck() {
		return testClassPath + "." + classToCheck;
	}

	public void test_contains() throws VizException {
		StringBuffer relevantClassesStr = new StringBuffer();
		for (int i = 0; i < relevantClasses.length; i++) {
			relevantClassesStr.append(testClassPath).append(".").append(relevantClasses[i]);
			if (i != relevantClasses.length - 1) 
				relevantClassesStr.append(",");
		}
		setConfigKeyRelevantClasses(relevantClassesStr.toString());
		setConfigKeyRelevancyAnalysis(true);
		setConfigKeyCheckNullDereference(false);
		setConfigKeyUseJavaArithmetic(true);
		setConfigKeyObjectScope(6);
		setConfigKeyInferScope(false);

		relevantClassesStr = new StringBuffer();
		for (int i = 0; i < relevantClasses.length; i++) {
			relevantClassesStr.append(testClassPath).append(".").append(relevantClasses[i]).append(":").append(relevantClassesAmounts[i]);
			if (i != relevantClasses.length - 1) 
				relevantClassesStr.append(",");
		}
		setConfigKeyTypeScopes(relevantClassesStr.toString());
		// setConfigKeyTypeScopes("examples.singlylist.SinglyLinkedList:1,examples.singlylist.SinglyLinkedListNode:7");

		setConfigKeySkolemizeInstanceInvariant(true);
		setConfigKeySkolemizeInstanceAbstraction(true);

		Properties newOverProp = getProperties();
		newOverProp.put("generateCheck", "true");
		newOverProp.put("generateRun", "false");
		newOverProp.put("include_simulation_program_declaration", "true");

		BugLineDetector main = new BugLineDetector(GENERIC_PROPERTIES,
				newOverProp, classToCheck, methodToCheck);
		
		System.out.println("Entrando al run...");

		setConfigKeyIntBithwidth(4);
        setConfigKeyLoopUnroll(7);
		setConfigKeyGenerateUnitTestCase(true);
//		setConfigKeyAttemptToCorrectBug(true);
		setConfigKeyMaxStrykerMethodsPerFile(50);
		setConfigKeyRemoveQuantifiers(true);
		setConfigKeyUseJavaSBP(true);
		setConfigKeyUseTightUpperBounds(true);
		
		try {
			FileWriter writer = new FileWriter(new File("bugline.fajita.config"));
			writer.write("RELEVANT_CLASSES=");
			for (int i = 0; i < relevantClasses.length; i++) {
				writer.write(testClassPath + "." + relevantClasses[i]);
				if (i != relevantClasses.length - 1) 
					writer.write(",");
			}
			writer.write("\n\nCLASS_TO_CHECK=" + testClassPath + "." + classToCheck);
			writer.write("\n\nMETHOD_TO_CHECK=" + methodToCheck);
			writer.write("\n\nLOOP_UNROLL=1");
			writer.write("\n\nINT_BITWIDTH=5");
			writer.write("\n\nTIMEOUT_SECS=3600");

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		main.run(classToCheckPath);
		
		// main.run(System.getProperty("user.dir") +
		// "/tests/examples/singlylist/SinglyLinkedList.java");
		System.out.println("Salido del run.");

	}
}
