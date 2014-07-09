package ar.uba.dc.rfm.fajita.ext;

import static ar.uba.dc.rfm.tools.FileSystemTools.separator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ar.edu.taco.TacoMain;
import ar.edu.taco.infer.InferredScope;
import ar.edu.taco.infer.IntegerOrInfinity;

import ar.uba.dc.rfm.fajita.FajitaConfiguration;
import ar.uba.dc.rfm.fajita.FajitaException;
import ar.uba.dc.rfm.fajita.translation.FajitaJavaCodeTranslator;
import ar.uba.dc.rfm.tools.FileTools;


/**
 * This class is used to encapsulate the details of the interface with the
 * Taco project. It implements the <code>Runnable</code> interface, its
 * run method launches the execution of Taco.
 *
 */
public class TacoRunner implements Runnable {

	/** Fajita runtime configuration */
	private final FajitaConfiguration configuration;
	
	/** The path to the taco.properties file. */
	private String tacoPropertiesFilename;
	
	
	/**
	 * Constructor for a <code>TacoRunner</code>.
	 * 
	 * @param configuration a <code>FajitaConfiguration</code> with the
	 *	necessary data to launch Taco.
	 *
	 */
	public TacoRunner(FajitaConfiguration configuration) {
		this.configuration = configuration;
		configuration.setTacoOutputDirectory(
			configuration.getResultPath() + separator + "tacoOutput");
		writeTacoProperties();
	}
	
	
	/**
	 * This method implements the <code>Runnable</code> interface. Invoking it
	 * launches the execution of Taco taking the required parameters from
	 * the fajita configuration.
	 * 
	 */
	@Override
	public void run() {
		configuration.setInfiniteScope(false);
		
		TacoMain taco = new TacoMain();
		taco.run(tacoPropertiesFilename);
		
		if (configuration.isIncrementalLoopUnroll()) {
			InferredScope scope = InferredScope.getInstance();
			
			for (String signature : scope.inferred_signature_set()) {
				IntegerOrInfinity inferredIntpuScope = scope.getInferredInputScope(signature);
				if (inferredIntpuScope == IntegerOrInfinity.INFINITY) {
					configuration.setInfiniteScope(true);
					break;
				}
			}
		}
		
		String tacoOutputDirectory = configuration.getTacoOutputDirectory();
		
		String dynalloyOuputXMLPrefix =
			tacoOutputDirectory + separator + configuration.getMethodToCheck();
		configuration.setTacoOutputXMLPrefix(dynalloyOuputXMLPrefix);
		
		String tacoOutputFilename =
			tacoOutputDirectory + separator + "output.als";
		configuration.setTacoOutputFilename(tacoOutputFilename);

		addCoverageInstrumentation(tacoOutputFilename);
	}
	
	
	/**
	 * This method creates a taco.properties file writing in it the
	 * configuration values taken from fajita's configuration. And stores the
	 * path to the file in the private attribute
	 * <code>tacoPropertiesFilename</code>.
	 *   
	 */
	private void writeTacoProperties() {
		tacoPropertiesFilename =
			configuration.getResultPath() + separator + "taco.properties";
		
		try {
			
			String tacoProperties =
				FileTools.readFile(configuration.getTacoPropertiesTemplate());
			
			tacoProperties = tacoProperties.replaceAll(
				"\\$\\{JFAJITA_DIR\\}", configuration.getCompiledClassToCheckPath());
			tacoProperties = tacoProperties.replaceAll(
				"\\$\\{RELEVANT_CLASSES\\}", configuration.getRelevantClasses());
			tacoProperties = tacoProperties.replaceAll(
				"\\$\\{CLASS_TO_CHECK\\}", configuration.getClassToCheck());
			tacoProperties = tacoProperties.replaceAll(
				"\\$\\{METHOD_TO_CHECK\\}", configuration.getMethodToCheck() + "_0");
			tacoProperties = tacoProperties.replaceAll(
				"\\$\\{LOOP_UNROLL\\}", String.valueOf(configuration.getLoopUnroll()));
			tacoProperties = tacoProperties.replaceAll(
				"\\$\\{RESULT_DIR\\}", configuration.getTacoOutputDirectory());
			tacoProperties = tacoProperties.replaceAll(
				"\\$\\{INT_BITWIDTH\\}", String.valueOf(configuration.getIntBitwidth()));

			tacoProperties += "objectScope=" + configuration.getObjectScope() + "\n";
			
			if (configuration.isAppendBoundAvailable()) {
				tacoProperties += "useTightUpperBounds=true\n";
			}
			
			if (configuration.isIncrementalLoopUnroll()) {
				tacoProperties += "inferScope=true\n";
///				tacoProperties += "removeExitWhileGuard=true\n";
			}
			
			FileTools.writeFile(tacoPropertiesFilename, tacoProperties);
			
		} catch (IOException e) {
			throw new FajitaException(
				"FAJITA: Error while generating taco.properties.\n" + e.getMessage(), e);
		}
		
	}
	
	
	/**
	 * This method adds distinguished variables to an alloy model in order to
	 * be able to pursue a coverage criteria with alloyCLI. The distinguished
	 * variables relate the goals set by fajita with the state of the object
	 * being checked.
	 * 
	 * @param alloyFilename a <code>String</code> with the path to the file
	 * containing the alloy model.
	 * 
	 */
	void addCoverageInstrumentation(String alloyFilename) {
		try {
			// READ THE ALLOY MODEL TO TRANSLATE
			String contents = FileTools.readFile(alloyFilename);
			
			// SEARCH FOR THE QF DECLARATION
			final String tagQF = "one sig QF {"; 
			int startQFIndex = contents.indexOf(tagQF) + tagQF.length();
			int endQFIndex = contents.indexOf('}', startQFIndex);
			String declarionQF = contents.substring(startQFIndex, endQFIndex);
			String[] varDeclarationsQF = declarionQF.split(",");
			String[][] variablesQF = new String[varDeclarationsQF.length][];
			for (int i = 0; i < varDeclarationsQF.length; i++)
				variablesQF[i] = varDeclarationsQF[i].split(":");
			
			// SEARCH FOR THE LAST INCARNATION OF EACH GOAL
			final String tagGoal = FajitaJavaCodeTranslator.FAJITA_GOAL_TAG;
///			final String loopExhaustion = "loop_exhaustion";
///			List<String> loopExhaustionIncarnations = new ArrayList<String>();
			Map<Integer, String> goalDeclarations = new HashMap<Integer, String>();
			for (int i = 0; i < varDeclarationsQF.length; i++) {
				String currentVar = variablesQF[i][0].trim();
				int tagGoalIndex = currentVar.indexOf(tagGoal);
				if (tagGoalIndex >= 0) {
					int goalStartIndex = tagGoalIndex + tagGoal.length() + 1;
					Integer goal = Integer.parseInt(currentVar.substring(
						goalStartIndex, currentVar.indexOf("_", goalStartIndex)));
					if (!configuration.getCoveredGoals().contains(goal)) {
						goalDeclarations.put(goal, currentVar);
					}
				}
///				int loopExhaustionIndex = currentVar.indexOf(loopExhaustion);
///				if (loopExhaustionIndex >= 0)
///					loopExhaustionIncarnations.add(currentVar);
			}
			
///			if (!loopExhaustionIncarnations.isEmpty()) {
///				Set<Integer> loopExhaustionIncarnationIds = new HashSet<Integer>();
///				int i = 0;
///				for (String loopExhaustionIncarnation : loopExhaustionIncarnations) {
///					int incarnationId = configuration.getDiscoveredGoals() + i++;
///					loopExhaustionIncarnationIds.add(incarnationId);
///					goalDeclarations.put(incarnationId, loopExhaustionIncarnations.get(
///						loopExhaustionIncarnations.size() - 1));
///				}
///				configuration.setLoopExhaustionIncarnations(loopExhaustionIncarnationIds);
///			}
			
			// CREATE A NEW QF WITH THE DISTINGUISHED VARIABLES
			final String tagDV = "BQ__";
			String newQFDeclaration = "";
			for (Integer goal : goalDeclarations.keySet())
				newQFDeclaration += "\n  " + tagDV + goal + ": boolean,";
			for (String[] varDeclaration : variablesQF)
				newQFDeclaration += varDeclaration[0] + ": " + varDeclaration[1] + ",";
			newQFDeclaration = newQFDeclaration.substring(0, newQFDeclaration.length()-1);
			
			// CREATE A FACT RELATING DISTINGUISHED VARIABLES TO GOALS
			String fact = "fact {\n";
			for (Integer goal : goalDeclarations.keySet())
				fact += "  QF." + tagDV + goal + "=true iff " +
					"ClassFields.(QF." + goalDeclarations.get(goal) + ")=true\n";
			fact += "}\n";
			
			// WRITE THE TRANSLATED MODEL TO THE FILE
			FileTools.writeFile(alloyFilename,
				contents.substring(0, startQFIndex) +
				newQFDeclaration +
				contents.substring(endQFIndex) +
				fact
			);
			
		} catch (IOException e) {
			throw new FajitaException(
				"FAJITA: Error while processing the alloy model.\n" + e.getMessage(), e);
		}
	}

}
