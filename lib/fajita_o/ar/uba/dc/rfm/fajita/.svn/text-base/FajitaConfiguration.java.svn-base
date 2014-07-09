package ar.uba.dc.rfm.fajita;

import static ar.uba.dc.rfm.tools.FileSystemTools.separator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import recoder.java.Statement;
import recoder.java.declaration.ParameterDeclaration;
import recoder.list.generic.ASTList;


/**
 * This class represents the configuration of the Fajita project.
 * It contains a set of attributes that direct the behavior of the project.
 * Optional attributes have their default values set here.<br />
 * 
 * These attributes are read and written by Fajita in order to pass the whole
 * configuration information to the different modules.<br />
 * 
 * To find how to set this attributes from the command line or with a config
 * file refer to the <code>FajitaOptionsParser</code> class.<br />
 * 
 * @see FajitaOptionsParser
 *
 */
public class FajitaConfiguration {

	/** Fjita's output directory. */
	public static final String FAJITA_OUT = "fajitaOut";
	
	public static final int DEFAULT_LOOP_UNROLL = 3;
	
	public static final int DEFAULT_OBJECT_SCOPE = 3;
	
	public static final int INITIAL_INCREMENAL_LOOP_UNROLL = 1;
	
	public static final int INITIAL_INCREMENTAL_OBJECT_SCOPE = 1;
	
	/** A mapping of descriptions to a coverage criteria enumeration. */
	private static final Map<String, CoverageCriteria> values =
		new HashMap<String, CoverageCriteria>();
	
	/**
	 * This enumeration holds the different coverage criteria supported by Fajita. 
	 */
	public enum CoverageCriteria {
		GOAL_COVERAGE("goal"),
		CLASS_COVERAGE("class"),
		BRANCH_COVERAGE("branch"),
		DUAL_CLASS_BRANCH_COVERAGE("dual");
		
		private String value;

		private CoverageCriteria(String value) {
			this.value = value;
			values.put(this.value, this);
		}
		
		@Override
		public String toString() {
			return value;
		}
		
	}

	// no-default values
	private String classpath;
	private String classToCheck;
	private String methodToCheck;
	private String tacoPropertiesTemplate;
	private String resultPath;
	
	// default values
	private int maximumLoopUnroll = DEFAULT_LOOP_UNROLL;
	private int maximumObjectScope = DEFAULT_OBJECT_SCOPE;
	private boolean incrementalLoopUnroll = false;
	private int intBitwidth = 4;
	private CoverageCriteria coverageCriteria = CoverageCriteria.GOAL_COVERAGE;
	private boolean onlyTranslateToAlloy = false;
	private Integer timeoutSecs = 0;
	private String relevantClasses = "";
	private boolean appendBoundAvailable = true;
	private boolean generateJUnit = true;
	private String solver = "minisat";
	
	// tool pipelining interface values
	private int loopUnroll = maximumLoopUnroll;
	private int objectScope = maximumObjectScope;
	private String compiledClassToCheckPath = null;
	private String goalTagFilter = null;
	private String tacoOutputDirectory = null;
	private String tacoOutputFilename = null;
	private String tacoOutputXMLPrefix = null;
	private int discoveredGoals = 0;
	private Set<Integer> coveredGoals = new HashSet<Integer>();
	private boolean infiniteScope = false;
///	private Integer loopExhaustionId = null;
	private Set<Integer> loopExhaustionIncarnations = new HashSet<Integer>();
	private ArrayList<String> partitionPerGoal = null;
	private ASTList<ParameterDeclaration> methodToCheckParameters;
	private int dualClassBranchIteration = 0;
	private ASTList<Statement> dualInitializationStatements;
	private int dualDiscoveredBranches;

	
	/**
	 * This method checks whether the given configuration has all the mandatory
	 * attributes properly set.
	 * 
	 * @throws FajitaException if there is a missing attribute.
	 */
	public void assertValidity() {
		if (classpath == null)
			throw new FajitaException("Missing required argument classpath.");
		if (classToCheck == null)
			throw new FajitaException("Missing required argument classToCheck.");
		if (methodToCheck == null)
			throw new FajitaException("Missing required argument methodToCheck.");
		if (tacoPropertiesTemplate == null)
			throw new FajitaException("Missing required argument tacoPropertiesTemplate.");
		if (resultPath == null)
			throw new FajitaException("Missing required argument resultPath.");
	}
	
	/////////////////////////
	// GETTERS AND SETTERS //
	/////////////////////////
	
	public String getClasspath() {
		return classpath;
	}
	public void setClasspath(String classpath) {
		this.classpath = classpath;
	}
	public String getClassToCheck() {
		return classToCheck;
	}
	public void setClassToCheck(String classToCheck) {
		this.classToCheck = classToCheck;
	}
	public String getMethodToCheck() {
		return methodToCheck;
	}
	public void setMethodToCheck(String methodToCheck) {
		this.methodToCheck = methodToCheck;
	}
	public String getRelevantClasses() {
		return relevantClasses;
	}
	public void setRelevantClasses(String relevantClasses) {
		this.relevantClasses = relevantClasses;
	}
	public String getTacoPropertiesTemplate() {
		return tacoPropertiesTemplate;
	}
	public void setTacoPropertiesTemplate(String tacoPropertiesTemplate) {
		this.tacoPropertiesTemplate = tacoPropertiesTemplate;
	}
	public String getUserResultPath() {
		return resultPath;
	}
	public String getResultPath() {
		String resultPath = this.resultPath;
		if (incrementalLoopUnroll)
			resultPath += separator + "unroll_" + loopUnroll;
		return resultPath;
	}
	public void setResultPath(String resultPath) {
		this.resultPath = resultPath;
	}
	public int getMaximumLoopUnroll() {
		return maximumLoopUnroll;
	}
	public void setMaximumLoopUnroll(int maximumLoopUnroll) {
		this.maximumLoopUnroll = maximumLoopUnroll;
	}
	public int getLoopUnroll() {
		return loopUnroll;
	}
	public void setLoopUnroll(int loopUnroll) {
		this.loopUnroll = loopUnroll;
	}
	public int getMaximumObjectScope() {
		return maximumObjectScope;
	}
	public void setMaximumObjectScope(int maximumObjectScope) {
		this.maximumObjectScope = maximumObjectScope;
	}
	public int getObjectScope() {
		return objectScope;
	}
	public void setObjectScope(int objectScope) {
		this.objectScope = objectScope;
	}
	public boolean isIncrementalLoopUnroll() {
		return incrementalLoopUnroll;
	}
	public void setIncrementalLoopUnroll(boolean incrementalLoopUnroll) {
		this.incrementalLoopUnroll = incrementalLoopUnroll;
	}
	public int getIntBitwidth() {
		return intBitwidth;
	}
	public void setIntBitwidth(int intBitwidth) {
		this.intBitwidth = intBitwidth;
	}
	public CoverageCriteria getCoverageCriteria() {
		return coverageCriteria;
	}
	public void setCoverageCriteria(CoverageCriteria coverageCriteria) {
		this.coverageCriteria = coverageCriteria;
	}
	public void setCoverageCriteria(String coverageCriteria) {
		this.coverageCriteria = values.get(coverageCriteria);
		if (this.coverageCriteria == null)
			throw new FajitaException("Invalid coverage criteria: " + coverageCriteria);
	}
	public boolean isOnlyTranslateToAlloy() {
		return onlyTranslateToAlloy;
	}
	public void setOnlyTranslateToAlloy(boolean onlyTranslateToAlloy) {
		this.onlyTranslateToAlloy = onlyTranslateToAlloy;
	}
	public Integer getTimeoutSecs() {
		return timeoutSecs;
	}
	public void setTimeoutSecs(Integer timeoutSecs) {
		this.timeoutSecs = timeoutSecs;
	}
	public boolean isAppendBoundAvailable() {
		return appendBoundAvailable;
	}
	public void setAppendBoundAvailable(boolean appendBoundAvailable) {
		this.appendBoundAvailable = appendBoundAvailable;
	}
	public String getCompiledClassToCheckPath() {
		return compiledClassToCheckPath;
	}
	public void setCompiledClassToCheckPath(String compiledClassToCheckPath) {
		this.compiledClassToCheckPath = compiledClassToCheckPath;
	}
	public String getGoalTagFilter() {
		return goalTagFilter;
	}
	public void setGoalTagFilter(String goalTagFilter) {
		this.goalTagFilter = goalTagFilter;
	}
	public String getTacoOutputDirectory() {
		return tacoOutputDirectory;
	}
	public void setTacoOutputDirectory(String tacoOutputDirectory) {
		this.tacoOutputDirectory = tacoOutputDirectory;
	}
	public String getDynalloyOutputFilename() {
		return tacoOutputFilename;
	}
	public void setTacoOutputFilename(String dynalloyOutputFilename) {
		this.tacoOutputFilename = dynalloyOutputFilename;
	}
	public String getDynalloyOutputXMLPrefix() {
		return tacoOutputXMLPrefix;
	}
	public void setTacoOutputXMLPrefix(String dynalloyOutputXMLPrefix) {
		this.tacoOutputXMLPrefix = dynalloyOutputXMLPrefix;
	}
	public int getDiscoveredGoals() {
		return discoveredGoals;
	}
	public void setDiscoveredGoals(int discoveredGoals) {
		this.discoveredGoals = discoveredGoals;
	}
	public Set<Integer> getCoveredGoals() {
		return coveredGoals;
	}
	public void setCoveredGoals(Set<Integer> coveredGoals) {
		this.coveredGoals = coveredGoals;
	}
	public boolean isInfiniteScope() {
		return infiniteScope;
	}
	public void setInfiniteScope(boolean infiniteScope) {
		this.infiniteScope = infiniteScope;
	}
	public boolean getGenerateJUnit() {
		return generateJUnit;
	}
	public void setGenareteJUnit(boolean generateJUnit) {
		this.generateJUnit = generateJUnit; 
	}
	public Set<Integer> getLoopExhaustionIncarnations() {
		return loopExhaustionIncarnations;
	}
	public void setLoopExhaustionIncarnations(Set<Integer> loopExhaustionIncarnations) {
		this.loopExhaustionIncarnations = loopExhaustionIncarnations;
	}
	public String getSolver() {
		return solver;
	}
	public void setSolver(String solver) {
		this.solver = solver;
	}
	public int getDualClassBranchIteration() {
		return dualClassBranchIteration;
	}
	public void setDualClassBranchIteration(int dualClassBranchIteration) {
		this.dualClassBranchIteration = dualClassBranchIteration;
	}
	public void setPartitionPerGoal(ArrayList<String> partitionPerGoal) {
		this.partitionPerGoal = partitionPerGoal;
	}
	public String getPartitionPerGoal(int goal) {
		return partitionPerGoal.get(goal);
	}
	public ASTList<ParameterDeclaration> getMethodToCheckParameters() {
		return methodToCheckParameters;
	}
	public void setMethodToCheckParameters(ASTList<ParameterDeclaration> methodToCheckParameters) {
		this.methodToCheckParameters = methodToCheckParameters;
	}
	public ASTList<Statement> getDualInitializationStatements() {
		return dualInitializationStatements;
	}
	public void setDualInitializationStatements(ASTList<Statement> dualInitializationStatements) {
		this.dualInitializationStatements = dualInitializationStatements;
	}
	public int getDualDiscoveredBranches() {
		return dualDiscoveredBranches;
	}
	public void setDualDiscoveredBranches(int dualDiscoveredBranches) {
		this.dualDiscoveredBranches = dualDiscoveredBranches;
	}

}
