package ar.edu.taco.junit;

import static java.io.File.separator;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import ar.edu.taco.TacoException;
import ar.edu.taco.junit.RecoveredInformation.StaticFieldInformation;

public class UnitTestBuilder {

	private static final String THIZ_0 = "thiz_0";

	private static Logger log = Logger.getLogger(UnitTestBuilder.class);

	static final private String FILE_SEPARATOR = File.separator;
	static final private String OUTPUT_DIR = "generated" + FILE_SEPARATOR;
	static final private String OUTPUT_SIMPLIFIED_JAVA_EXTENSION = ".java";

	private static final String PACKAGE_NAME = "ar.edu.generated.junit";

	private RecoveredInformation recoveredInformation;
	private ClassLoader loader = Thread.currentThread().getContextClassLoader();
	private String outputPath = "";
	private String staticFieldNameFilter = "";

	// Keep the variables and objects that has already been created. 
	// We use the identityHashCode of each object as the Key and the created variable name as Value
	private Map<Integer, String> createdInstances = new HashMap<Integer, String>();
	private Set<String> imports;
	
	private Map<Object, Integer> instancesIndex = new HashMap<Object, Integer>();
	private List<String> parameterInstanceNames = new ArrayList<String>();
	
	public UnitTestBuilder(RecoveredInformation recoveredInformation/*, TacoAnalysisResult tacoAnalysisResult*/) {
		this.recoveredInformation = recoveredInformation;
		

	}



	/**
	 * 
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public void createUnitTest() throws IllegalArgumentException, IllegalAccessException, InstantiationException {
		if (!this.recoveredInformation.isValidInformation()) {
			return;
		}

		String className = recoveredInformation.getClassToCheck().substring(recoveredInformation.getClassToCheck().lastIndexOf(".") + 1) + "Test";
		String methodName = recoveredInformation.getMethodToCheck();

		Class<?> clazz;
		try {
			clazz = Class.forName(recoveredInformation.getClassToCheck(), true, loader);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("DYNJALLOY ERROR! " + e.getMessage());
		}

		Method methodToCheck = null;
		try {
			// Gets parameters types
			Class<?>[] parameterTypes = null;
			for (Method aMethod : clazz.getDeclaredMethods()) {
				if (aMethod.getName().equals(recoveredInformation.getMethodToCheck())) {
					parameterTypes = aMethod.getParameterTypes();
				}
			}

			methodToCheck = clazz.getDeclaredMethod(recoveredInformation.getMethodToCheck(), parameterTypes);
		} catch (SecurityException e) {
			throw new RuntimeException("DYNJALLOY ERROR! " + e.getMessage());
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("DYNJALLOY ERROR! " + e.getMessage());
		}

		// Get Initialized this
		Object thizInstance = null;
		try {
			thizInstance = clazz.newInstance();
			thizInstance = recoveredInformation.getSnapshot().get(THIZ_0);
		} catch (InstantiationException e) {
			throw new RuntimeException("DYNJALLOY ERROR! " + e.getMessage());
		} catch (IllegalAccessException e) {
			throw new RuntimeException("DYNJALLOY ERROR! " + e.getMessage());
		}

		imports = new HashSet<String>();
		imports.add("org.junit.Test");
		imports.add("java.lang.reflect.Field");

		List<String> statements = new ArrayList<String>();
		statements.add(recoveredInformation.getClassToCheck() + " instance = new " + recoveredInformation.getClassToCheck() + "();");

		// relate the Object got from Thiz_0 to the variable instance;
		this.createdInstances.put(System.identityHashCode(thizInstance), "instance");

		// Static Fields initialization
		List<String> staticFieldsInitializationStatements = getStaticFieldsInitializationStatements(clazz, "instance");
		statements.addAll(staticFieldsInitializationStatements);

		// Fields initialization
		if (thizInstance != null) {
			List<String> fieldsInitializationStatements = getfieldsInitializationStatements(clazz, thizInstance, "instance");
			statements.addAll(fieldsInitializationStatements);
		}

		// Parameters Initialization
		List<String> parametersInitializationStatements = getParametersInitializationStatements(clazz);
		statements.addAll(parametersInitializationStatements);

		// Method invocation
		List<String> methodInvocationStatements = getMethodInvocationStatements(clazz, methodToCheck);
		statements.addAll(methodInvocationStatements);

		// Write JUnit to File
		String outputClassName = className + "_" + methodName;
		writeToFile(outputClassName, methodName, imports, statements, methodToCheck.isAccessible());

		log.info("****** JUnit generation finished. Produced JUnit: '" + PACKAGE_NAME + "." + outputClassName + "' on 'generated' source folder ******");

	}
	

	public void setLoader(ClassLoader loader) {
		this.loader = loader;
	}
	
	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}
	
	public void setStaticFieldNameFilter(String filter) {
		this.staticFieldNameFilter = filter;
	}

	/**
	 * Generate the statements with the initialization of each static field
	 * 
	 * @param clazz
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	private List<String> getStaticFieldsInitializationStatements(Class<?> clazz, String buildVariableName) throws IllegalArgumentException, IllegalAccessException {
		List<String> statements = new ArrayList<String>();
		

		List<StaticFieldInformation> staticFields = recoveredInformation.getStaticFieldsNameForClass(recoveredInformation.getClassToCheck());
		List<String> shortFieldNames = new ArrayList<String>();
		String moduleName = getModuleName(clazz);
		for (StaticFieldInformation staticField : staticFields) {
			if (staticField.getFieldName().matches("(roops_goal|myRoopsArray).*")) // XXX: Hack in order to ignore goals added by fajita
				continue;
			String shortFieldName = staticField.getFieldName().replace(moduleName + "_", "");
			if (!shortFieldName.matches(staticFieldNameFilter))
				shortFieldNames.add(shortFieldName);
		}
		
		if (!shortFieldNames.isEmpty()) {
			statements.add("");
			statements.add("// Statics Fields Initialization");

			for (String shortFieldName : shortFieldNames) {
				Field field = null;
				try {
					field = clazz.getDeclaredField(shortFieldName);
				} catch (SecurityException e) {
					throw new RuntimeException("DYNJALLOY ERROR! " + e.getMessage());
				} catch (NoSuchFieldException e) {
					throw new RuntimeException("DYNJALLOY ERROR! " + e.getMessage());
				}

				if (field.getType().isPrimitive()) {
					String value = getValueForPrimitiveTypeField(field, null);
					String statementToAdd = "updateValue(instance, \"" + shortFieldName + "\", " + value + ");";
					statements.add(statementToAdd);
				} else if (field.getType().isArray()) {
					Class<?> componentType = field.getType().getComponentType();
					field.setAccessible(true);
					Object fieldValue = field.get(null);

					String values = getValueForArray(componentType, fieldValue, statements, buildVariableName);

					statements.add("updateValue(instance, \"" + shortFieldName + "\", new " + field.getType().getCanonicalName() + values + ");");

				} else if (List.class.isAssignableFrom(field.getType())) {
					imports.add("java.util.List");
					imports.add("java.util.ArrayList");

					
					Object fieldValue = field.get(null);
					//DPD VAR NAME fix;
					//String buildVariable = buildVariableName + "_" + shortFieldName;					
					String buildVariable = generateVariableName(buildVariableName,  fieldValue);
					
					if (fieldValue == null) {
						statements.add("updateValue(" + buildVariableName + ", \"" + shortFieldName + "\", null);");
					} else {
						// If this instance was already created, then use the created variable
						if (this.createdInstances.containsKey(System.identityHashCode(fieldValue))) {
							//String createdVariable = this.createdInstances.get(System.identityHashCode(fieldValue));
//							String buildStatement = field.getType().getCanonicalName() + " " + buildVariable + " = " + createdVariable + ";";
//							statements.add(buildStatement);
						} else {

							String buildStatement = field.getType().getCanonicalName() + " " + buildVariable + " = new java.util.ArrayList();";
							this.createdInstances.put(System.identityHashCode(fieldValue), buildVariable);
	
							statements.add("");
							statements.add(buildStatement);
							
							List<String> initializationStatements = getStatementsForCollection(buildVariable, fieldValue);
							statements.addAll(initializationStatements);
						}
						
						statements.add("updateValue(" + buildVariableName + ", \"" + shortFieldName + "\", " + buildVariable + ");");						
					}
					
				} else if (Set.class.isAssignableFrom(field.getType())) {
					imports.add("java.util.Set");
					imports.add("kodkod.util.collections.IdentityHashSet");
					
					
					Object fieldValue = field.get(null);
					//DPD VAR NAME fix;
					//String buildVariable = buildVariableName + "_" + shortFieldName;
					String buildVariable = generateVariableName(buildVariableName,  fieldValue);
					

					if (fieldValue == null) {
						statements.add("updateValue(" + buildVariableName + ", \"" + shortFieldName + "\", null);");
					} else {
						// If this instance was already created, then use the created variable
						if (this.createdInstances.containsKey(System.identityHashCode(fieldValue))) {
							String createdVariable = this.createdInstances.get(System.identityHashCode(fieldValue));
//							String buildStatement = field.getType().getCanonicalName() + " " + buildVariable + " = " + createdVariable + ";";
//							statements.add(buildStatement);
						} else {
							String generatedVariableName = generateVariableName(buildVariableName, fieldValue);
							
							String buildStatement = field.getType().getCanonicalName() + " " + generatedVariableName + " = new kodkod.util.collections.IdentityHashSet();";
							this.createdInstances.put(System.identityHashCode(fieldValue), generatedVariableName);
	
							statements.add("");
							statements.add(buildStatement);
							
							List<String> initializationStatements = getStatementsForCollection(generatedVariableName, fieldValue);
							statements.addAll(initializationStatements);
						}
						statements.add("updateValue(" + buildVariableName + ", \"" + shortFieldName + "\", " + buildVariable + ");");
					}
				} else if (Map.class.isAssignableFrom(field.getType())) {
					imports.add("java.util.Map");
					//imports.add("java.util.HashMap");
					imports.add("java.util.IdentityHashMap");
					
					Object fieldValue = field.get(null);
					//DPD VAR NAME fix;
					//String buildVariable = buildVariableName + "_" + shortFieldName;
					String buildVariable = generateVariableName(buildVariableName,  fieldValue);

					if (fieldValue == null) {
						statements.add("updateValue(" + buildVariableName + ", \"" + shortFieldName + "\", null);");
					} else {
						// If this instance was already created, then use the created variable
						if (this.createdInstances.containsKey(System.identityHashCode(fieldValue))) {
							//String createdVariable = this.createdInstances.get(System.identityHashCode(fieldValue));
//							String buildStatement = field.getType().getCanonicalName() + " " + buildVariable + " = " + createdVariable + ";";
//							statements.add(buildStatement);
						} else {
							//DPD
							
							String buildStatement = /*field.getType().getCanonicalName()*/ "Map" + " " + buildVariable + " = new java.util.IdentityHashMap();";
							this.createdInstances.put(System.identityHashCode(fieldValue), buildVariable);
							
							statements.add("");
							statements.add(buildStatement);
							
							List<String> initializationStatements = getStatementsForMap(buildVariable, fieldValue);
							statements.addAll(initializationStatements);							
						}

						statements.add("updateValue(" + buildVariableName + ", \"" + shortFieldName + "\", " + buildVariable + ");");
					}
				} else if (Object.class.isAssignableFrom(field.getType().getClass())) {
					
					Object fieldValue = field.get(null);
					//DPD VAR NAME fix;
					//String buildVariable = buildVariableName + "_" + shortFieldName;
					String buildVariable = generateVariableName(buildVariableName,  fieldValue);

					if (fieldValue == null) {
						statements.add("updateValue(" + buildVariableName + ", \"" + shortFieldName + "\", null);");
					} else {
						// If this instance was already created, then use the created variable
						if (this.createdInstances.containsKey(System.identityHashCode(fieldValue))) {
							//String createdVariable = this.createdInstances.get(System.identityHashCode(fieldValue));
//							String buildStatement = fieldValue.getClass().getCanonicalName() + " " + buildVariable + " = " + createdVariable + ";";
//							statements.add(buildStatement);
						} else {
							String buildStatement = fieldValue.getClass().getCanonicalName() + " " + buildVariable + " = new " + fieldValue.getClass().getCanonicalName() + "();";
							this.createdInstances.put(System.identityHashCode(fieldValue), buildVariable);
							List<String> initializationStatements = getfieldsInitializationStatements(field.getType(), fieldValue, buildVariableName);
							
							statements.add("");
							statements.add(buildStatement);
							statements.addAll(initializationStatements);
						}
						statements.add("updateValue(" + buildVariableName + ", \"" + shortFieldName + "\", " + buildVariable + ");");
					}
				}
			}
		}

		return statements;
	}

	/**
	 * @param buildVariableName
	 * @param value
	 * @return
	 */
	private String generateVariableName(String buildVariableName, Object value) {
		if (value == null) {
			return "null_0";
		}
		
		if (this.createdInstances.containsKey(System.identityHashCode(value))) {
			return this.createdInstances.get(System.identityHashCode(value));
		}
			
		int index;
		if (instancesIndex.containsKey(value.getClass())) {
			index = instancesIndex.get(value.getClass());
		} else {
			index = 0;
		}
		index++;
		instancesIndex.put(value.getClass(), index);
		
		//String retValue = buildVariableName + "_" + className + "_" + index;
		String instanceName = getAKeyforValue(value);		
		// remove "_NNN" from "xxxx_NNN".
		int lastUnderscore = instanceName.lastIndexOf("_");
		if (lastUnderscore >= 0) {
			String stringBeforeLastUnderscore = instanceName.substring(lastUnderscore + 1);
			if (stringBeforeLastUnderscore.matches("[0-9]+")) {
				instanceName = instanceName.substring(0, lastUnderscore);
			}
		}
		
		String className = value.getClass().getSimpleName();
		className = className.replaceAll("\\[\\]", "\\_array");
		String retValue = instanceName + "_" + className + "_" + index;
		
		return retValue;
	}



	private String getAKeyforValue(Object value) {
		SortedSet<String> candidates = new TreeSet<String>();
		for(Entry<String, Object> entry : this.recoveredInformation.getSnapshot().entrySet()) {
			if(System.identityHashCode(entry.getValue()) == System.identityHashCode(value)) {
				candidates.add(entry.getKey());
			}
		}
		if (candidates.isEmpty()) {
			return "";
		} else {
			String retValue = candidates.first();
			return retValue;
		}
		
	}



	/**
	 * 
	 * @param clazz
	 * @param instance
	 * @param buildVariableName
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private List<String> getfieldsInitializationStatements(Class<?> clazz, Object instance, String buildName) throws IllegalArgumentException,
			IllegalAccessException {
		List<String> statements = new ArrayList<String>();
		//System.out.println("clazz_+ instance" + clazz + ":" + instance); 
		
		if (clazz.getDeclaredFields().length > 0) {
			String instanceGeneratedVariableName = generateVariableName(buildName, instance);
			statements.add("// Fields Initialization for '" + instanceGeneratedVariableName + "'");
		
			for (Field field : clazz.getDeclaredFields()) {
				field.setAccessible(true);

				if (!Modifier.isStatic(field.getModifiers())) {
					String shortFieldName = field.getName();

					if (field.getType().isPrimitive()) {
						String value = getValueForPrimitiveTypeField(field, instance);
						String statementToAdd = "updateValue(" + instanceGeneratedVariableName + ", \"" + shortFieldName + "\", " + value + ");";
						statements.add(statementToAdd);

					} else if (field.getType().isArray()) {
						Class<?> componentType = field.getType().getComponentType();
						Object fieldValue = field.get(instance);
						//DPD
						if (fieldValue != null) {
							String values = getValueForArray(componentType, fieldValue, statements, buildName);
							statements.add("updateValue(" + instanceGeneratedVariableName + ", \"" + shortFieldName + "\", new " + field.getType().getCanonicalName() + values
								+ ");");
						}
						
					} else if (List.class.isAssignableFrom(field.getType())) {
						imports.add("java.util.List");
						imports.add("java.util.ArrayList");
						
						Object fieldValue = field.get(instance);
						//DPD VAR NAME fix;
						//String buildVariable = buildVariableName + "_" + shortFieldName;						
						String buildVariable = generateVariableName(instanceGeneratedVariableName,  fieldValue);
						
						// If this instance was already created, then use the created variable
						if (this.createdInstances.containsKey(System.identityHashCode(fieldValue))) {
							String createdVariable = this.createdInstances.get(System.identityHashCode(fieldValue));
//							String buildStatement = field.getType().getCanonicalName() + " " + buildVariable + " = " + createdVariable + ";";
//							statements.add(buildStatement);
						} else {

							String buildStatement = field.getType().getCanonicalName() + " " + buildVariable + " = new java.util.ArrayList();";
							this.createdInstances.put(System.identityHashCode(fieldValue), buildVariable);
	
							statements.add("");
							statements.add(buildStatement);
							
							List<String> initializationStatements = getStatementsForCollection(buildVariable, fieldValue);
							statements.addAll(initializationStatements);
						}
						
						statements.add("updateValue(" + instanceGeneratedVariableName + ", \"" + shortFieldName + "\", " + buildVariable + ");");
						
					} else if (Set.class.isAssignableFrom(field.getType())) {
						imports.add("java.util.Set");
						imports.add("kodkod.util.collections.IdentityHashSet");
						
						Object fieldValue = field.get(instance);
						//DPD VAR NAME fix;
						//String buildVariable = buildVariableName + "_" + shortFieldName;						
						String buildVariable = generateVariableName(buildName,  fieldValue);

						if (fieldValue == null) {
							statements.add("updateValue(" + instanceGeneratedVariableName + ", \"" + shortFieldName + "\", null);");
						} else {
							// If this instance was already created, then use the created variable
							if (this.createdInstances.containsKey(System.identityHashCode(fieldValue))) {
								//String createdVariable = this.createdInstances.get(System.identityHashCode(fieldValue));
//								String buildStatement = field.getType().getCanonicalName() + " " + buildVariable + " = " + createdVariable + ";";
//								statements.add(buildStatement);
							} else {

								String buildStatement = field.getType().getCanonicalName() + " " + buildVariable + " = new kodkod.util.collections.IdentityHashSet();";
								this.createdInstances.put(System.identityHashCode(fieldValue), buildVariable);
								statements.add("");
								statements.add(buildStatement);
								
								List<String> initializationStatements = getStatementsForCollection(buildVariable, fieldValue);
								statements.addAll(initializationStatements);
							}
							statements.add("updateValue(" + instanceGeneratedVariableName + ", \"" + shortFieldName + "\", " + buildVariable + ");");
						}
					} else if (Map.class.isAssignableFrom(field.getType())) {
						imports.add("java.util.Map");
						imports.add("java.util.IdentityHashMap");
						
						Object fieldValue = field.get(instance);
						//DPD VAR NAME fix;
						//String buildVariable = buildVariableName + "_" + shortFieldName;						
						String buildVariable = generateVariableName(buildName,  fieldValue);

						if (fieldValue == null) {
							statements.add("updateValue(" + instanceGeneratedVariableName + ", \"" + shortFieldName + "\", null);");
						} else {
							// If this instance was already created, then use the created variable
							if (this.createdInstances.containsKey(System.identityHashCode(fieldValue))) {
								//String createdVariable = this.createdInstances.get(System.identityHashCode(fieldValue));
//								String buildStatement = field.getType().getCanonicalName() + " " + buildVariable + " = " + createdVariable + ";";
//								statements.add(buildStatement);
							} else {

								String buildStatement = /*field.getType().getCanonicalName()*/ "Map" + " " + buildVariable + " = new java.util.IdentityHashMap();";
								this.createdInstances.put(System.identityHashCode(fieldValue), buildVariable);
	
								statements.add("");
								statements.add(buildStatement);
								
								List<String> initializationStatements = getStatementsForMap(buildVariable, fieldValue);
								statements.addAll(initializationStatements);
							}
							statements.add("updateValue(" + instanceGeneratedVariableName + ", \"" + shortFieldName + "\", " + buildVariable + ");");
						}
					} else if (Object.class.isAssignableFrom(field.getType())) {
						
						Object fieldValue = field.get(instance);
						//DPD VAR NAME fix;
						//String buildVariable = buildVariableName + "_" + shortFieldName;						
						String buildVariable = generateVariableName(buildName,  fieldValue);
						

						if (fieldValue == null) {
							statements.add("updateValue(" + instanceGeneratedVariableName + ", \"" + shortFieldName + "\", null);");
						} else {
							// If this instance was already created, then use the created variable
							if (this.createdInstances.containsKey(System.identityHashCode(fieldValue))) {
								//String createdVariable = this.createdInstances.get(System.identityHashCode(fieldValue));
//								String buildStatement = fieldValue.getClass().getCanonicalName() + " " + buildVariable + " = " + createdVariable + ";";
//								statements.add(buildStatement);
							} else {
								String buildStatement = fieldValue.getClass().getCanonicalName() + " " + buildVariable + " = new " + fieldValue.getClass().getCanonicalName() + "();";
								this.createdInstances.put(System.identityHashCode(fieldValue), buildVariable);
								List<String> initializationStatements = getfieldsInitializationStatements(field.getType(), fieldValue, buildVariable);

								statements.add("");
								statements.add(buildStatement);
								statements.addAll(initializationStatements);
							}
							statements.add("updateValue(" + instanceGeneratedVariableName + ", \"" + shortFieldName + "\", " + buildVariable + ");");
						}
					}
				}

			}

		}

		return statements;
	}


	/**
	 * 
	 * @param clazz
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	private List<String> getParametersInitializationStatements(Class<?> clazz) throws InstantiationException, IllegalAccessException {
		List<String> statements = new ArrayList<String>();
		
		if (recoveredInformation.getMethodParametersNames().size() > 0) {
			statements.add("");
			statements.add("// Parameter Initialization");

			// Gets parameters types
			Class<?>[] parameterTypes = new Class<?>[recoveredInformation.getMethodParametersNames().size()];
			for (Method aMethod : clazz.getDeclaredMethods()) {
				if (aMethod.getName().equals(recoveredInformation.getMethodToCheck())) {
					parameterTypes = aMethod.getParameterTypes();
				}
			}

			int index = 0;
			for (String aParameterName : recoveredInformation.getMethodParametersNames()) {
				Class<?> parameterType = parameterTypes[index];
				
				Object parameterInstance;
				if (recoveredInformation.getSnapshot().containsKey(aParameterName + "_0")) {
					parameterInstance = recoveredInformation.getSnapshot().get(aParameterName + "_0");
				} else {
					parameterInstance = defaultValue(parameterType);
					
					//parameterInstance = clazz.newInstance();
				}
				
				//String generatedVariableName = generateVariableName(aParameterName, parameterInstance);
				statements.addAll(createStatementsForParameter(parameterType, aParameterName, parameterInstance));
				
				
				String instanceName = createdInstances.get(System.identityHashCode(parameterInstance));
				parameterInstanceNames.add(instanceName);
				
				//createdInstances.put(System.identityHashCode(parameterInstance), aParameterName);
				
				index++;
			}

		}

		return statements;

	}

	private Object defaultValue(Class<?> clazz) throws InstantiationException, IllegalAccessException {
		Object value;
		if (clazz.isPrimitive()) {
			String typeSimpleName = clazz.getSimpleName();

			if (typeSimpleName.equals("boolean")) {
				value = false;
			} else if (typeSimpleName.endsWith("byte")) {
				value = 0;
			} else if (typeSimpleName.endsWith("char")) {
				value = "'a'";
			} else if (typeSimpleName.endsWith("double")) {
				value = 0;
			} else if (typeSimpleName.endsWith("float")) {
				value = 0;
			} else if (typeSimpleName.endsWith("int")) {
				value = 0;
			} else if (typeSimpleName.endsWith("long")) {
				value = 0L;
			} else if (typeSimpleName.endsWith("short")) {
				value = 0;
			} else {
				throw new TacoException("ERROR: No difinida");
			}
		} else {
			String name = clazz.getName();
			if (name.equals("java.lang.Boolean")) {
				value = false;
			} else if (name.endsWith("java.lang.Byte")) {
				value = 0;
			} else if (name.endsWith("java.lang.Character")) {
				value = "'a'";
			} else if (name.endsWith("java.lang.Double")) {
				value = 0;
			} else if (name.endsWith("java.lang.Float")) {
				value = 0;
			} else if (name.endsWith("java.lang.Integer")) {
				value = 0;
			} else if (name.endsWith("java.lang.Long")) {
				value = 0;
			} else if (name.endsWith("java.lang.Short")) {
				value = 0;
			} else {
				value = clazz.newInstance();
			}
		}
		return value;
	}

	/**
	 * 
	 * @param clazz
	 * @param instance
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InstantiationException 
	 */
	private List<String> createStatementsForParameter(Class<?> clazz, String parameterName, Object instance) throws IllegalArgumentException,
			IllegalAccessException, InstantiationException {
		List<String> statements = new ArrayList<String>();
		String generatedVariableName = generateVariableName(parameterName, instance);

		if (this.createdInstances.containsKey(System.identityHashCode(instance))) {
			//String createdVariable = this.createdInstances.get(System.identityHashCode(instance));
			return statements;
		}
		
		Object parameterValue = this.recoveredInformation.getSnapshot().get(parameterName + "_0");

		if (clazz.isPrimitive() || isAutoboxingClass(clazz)) {
			String value;
			if (parameterValue == null) {
				value = String.valueOf(defaultValue(clazz));
			} else {
				if (Character.class.isAssignableFrom(clazz)) {
					value = "'" + String.valueOf(parameterValue) + "'";
				} else {
					if (parameterValue instanceof Long) {
						value = String.valueOf(parameterValue) + "L";
					} else if (parameterValue instanceof Float) {
						value = String.valueOf(parameterValue) + "f";
					} else {
					   value = String.valueOf(parameterValue);
					}
				}
					
			}
			
			//DPD VAR NAME fix
			//statements.add(clazz.getCanonicalName() + " " + parameterName + " = " + value + ";");
			statements.add(clazz.getCanonicalName() + " " + generatedVariableName + " = " + value + ";");
			this.createdInstances.put(System.identityHashCode(instance), generatedVariableName);
			
		} else if (parameterValue == null) {
			//DPD NULL CASE
			//String statement = clazz.getCanonicalName() + " " + parameterName + " = null;";
			String statement = clazz.getCanonicalName() + " " + generatedVariableName + " = null;";
			this.createdInstances.put(System.identityHashCode(instance), generatedVariableName);
			statements.add(statement);
			
		} else if (clazz.isArray()) {
			Class<?> componentType = clazz.getComponentType();
			log.debug(clazz + ":"  + parameterName + ":" + instance);
			
			this.createdInstances.put(System.identityHashCode(instance), generatedVariableName);
			String values = getValueForArray(componentType, parameterValue, statements, parameterName);
			
			//DPD VAR NAME fix
			//String statement = clazz.getCanonicalName() + " " + parameterName + " = new " + clazz.getCanonicalName() + values + ";";
			String statement = clazz.getCanonicalName() + " " + generatedVariableName + " = new " + clazz.getCanonicalName() + values + ";";
			statements.add(statement);
			
		} else if (List.class.isAssignableFrom(clazz)) {
			imports.add("java.util.List");
			imports.add("java.util.ArrayList");

			Object fieldValue = instance;

			if (fieldValue == null) {
				//DPD VAR NAME fix
				//statements.add(clazz.getCanonicalName() + " " + parameterName + " = null;");
				statements.add(clazz.getCanonicalName() + " " + generatedVariableName + " = null;");
				this.createdInstances.put(System.identityHashCode(instance), generatedVariableName);
			} else {
				//DPD VAR NAME fix				
				//String buildStatement = clazz.getCanonicalName() + " " + parameterName + " = new java.util.ArrayList();";
				String buildStatement = clazz.getCanonicalName() + " " + generatedVariableName + " = new java.util.ArrayList();";
				this.createdInstances.put(System.identityHashCode(instance), generatedVariableName);
				statements.add("");
				statements.add(buildStatement);
				
				//DPD VAR NAME fix				
				//List<String> initializationStatements = getStatementsForCollection(parameterName, fieldValue);
				List<String> initializationStatements = getStatementsForCollection(generatedVariableName, fieldValue);
				statements.addAll(initializationStatements);				
			}
			
		} else if (Set.class.isAssignableFrom(clazz)) {
			imports.add("java.util.Set");
			imports.add("kodkod.util.collections.IdentityHashSet");
			//String buildVariable = parameterName;
			Object fieldValue = instance;

			if (fieldValue == null) {
				//DPD VAR NAME fix		
				//statements.add(clazz.getCanonicalName() + " " + parameterName + " = null;");
				statements.add(clazz.getCanonicalName() + " " + generatedVariableName + " = null;");
				this.createdInstances.put(System.identityHashCode(instance), generatedVariableName);
			} else {
				//DPD VAR NAME fix		
				//String buildStatement = clazz.getCanonicalName() + " " + parameterName + " = new kodkod.util.collections.IdentityHashSet();";
				String buildStatement = clazz.getCanonicalName() + " " + generatedVariableName + " = new kodkod.util.collections.IdentityHashSet();";
				this.createdInstances.put(System.identityHashCode(instance), generatedVariableName);
				//this.createdInstances.put(System.identityHashCode(instance), parameterName);
				statements.add("");
				statements.add(buildStatement);
				
				List<String> initializationStatements = getStatementsForCollection(generatedVariableName, fieldValue);
				statements.addAll(initializationStatements);
			}
		} else if (Map.class.isAssignableFrom(clazz)) {
			imports.add("java.util.Map");
			imports.add("java.util.IdentityHashMap");
			//String buildVariable = parameterName;
			Object fieldValue = instance;

			if (fieldValue == null) {
				//DPD VAR NAME fix		
				//statements.add(clazz.getCanonicalName() + " " + parameterName + " = null;");
				statements.add(clazz.getCanonicalName() + " " + generatedVariableName + " = null;");
				this.createdInstances.put(System.identityHashCode(instance), generatedVariableName);
			} else {
//				if (this.createdInstances.containsKey(System.identityHashCode(instance))) {
//					String createdVariable = this.createdInstances.get(System.identityHashCode(instance));
////					String buildStatement = clazz.getCanonicalName() + " " + parameterName + " = (" + clazz.getCanonicalName() + ") " + createdVariable + ";";
////					statements.add(buildStatement);
//					
//				} else {
					//DPD VAR NAME fix	
					//String buildStatement = /*clazz.getCanonicalName() +*/ "Map " + parameterName + " = new java.util.IdentityHashMap();";
					String buildStatement = /*clazz.getCanonicalName() +*/ "Map " + generatedVariableName + " = new java.util.IdentityHashMap();";
					this.createdInstances.put(System.identityHashCode(instance), generatedVariableName);
					statements.add("");
					statements.add(buildStatement);
					
					List<String> initializationStatements = getStatementsForMap(generatedVariableName, fieldValue);
					statements.addAll(initializationStatements);					
					statements.add("");
//				}
			}
		} else if (Object.class.isAssignableFrom(clazz)) {
			if (!hasDefaultConstructor(clazz)) {
				throw new RuntimeException("DYNJALLOY ERROR!: Type: " + clazz.getCanonicalName() + " has no default Constructor.");

			}

			statements.add("");			
//			if (this.createdInstances.containsKey(System.identityHashCode(instance))) {
//				//DPD BEGIN
//				String createdVariable = this.createdInstances.get(System.identityHashCode(instance));
////				String buildStatement = clazz.getCanonicalName() + " " + parameterName + " = " + createdVariable + ";";
////				statements.add(buildStatement);
//				//DPD END
//			} else {
				//DPD VAR NAME fix	
				//String buildStatement = clazz.getCanonicalName() + " " + parameterName + " = new " + clazz.getCanonicalName() + "();";
				String buildStatement = clazz.getCanonicalName() + " " + generatedVariableName + " = new " + clazz.getCanonicalName() + "();";
				this.createdInstances.put(System.identityHashCode(instance), generatedVariableName);
//				this.createdInstances.put(System.identityHashCode(instance), parameterName);

				List<String> initializationStatements = getfieldsInitializationStatements(clazz, instance, parameterName);

				statements.add(buildStatement);
				statements.addAll(initializationStatements);
				
//			}


		} else {
			statements.add("// Initialization for parameter '" + parameterName + "' not yet implemented. Type: " + clazz);
		}
//		if (instance != null) {
//			this.createdInstances.put(System.identityHashCode(instance), parameterName);
//		}
		return statements;
	}
	
	/**
	 * 
	 * @param fieldValue
	 * @return
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	private List<String> getStatementsForCollection(String variableName, Object fieldValue) throws IllegalArgumentException, IllegalAccessException {
		List<String> statements = new ArrayList<String>();
		
		Collection<?> listFieldValue = (Collection<?>) fieldValue;
		int index = 0;
		for (Object value : listFieldValue) {
			statements.add("");
			//Class<?> clazz = value.getClass();
			Class<?> clazz;
			if (value == null) {
				clazz = null;
			} else {
				clazz = value.getClass();
			}
			
			if (value == null) {
				statements.add(variableName + ".add(null);");
			} else if (isAutoboxingClass(clazz)) {
				String contentValue;
				if (Character.class.isAssignableFrom(value.getClass())) {
					contentValue = "'" + String.valueOf(value) + "'";
				} else {
					contentValue = String.valueOf(value);
				}
				
				statements.add(variableName + ".add(" + contentValue + ");");
				
			} else if (clazz.isArray()) {
				Class<?> componentType = clazz.getComponentType();

				
								
				//DPD VAR NAME fix;
				//String variableToCreate = variableName + "_" + index;
				String variableToCreate = generateVariableName(variableName,  value);				
				if (!this.createdInstances.containsKey(System.identityHashCode(value))) {
					this.createdInstances.put(System.identityHashCode(value), variableToCreate);
					String values = getValueForArray(componentType, value, statements, variableName);
					String statement = clazz.getCanonicalName() + " " + variableToCreate + " = new " + clazz.getCanonicalName() + values + ";";
				
					statements.add(statement);
				}				
				statements.add(variableName + ".add(" + variableToCreate + ");");

			} else if (List.class.isAssignableFrom(clazz)) {
				imports.add("java.util.List");
				imports.add("java.util.ArrayList");
				
				//DPD VAR NAME fix;
				//String variableToCreate = variableName + "_" + index;						
				String variableToCreate = generateVariableName(variableName,  value);
				if (!this.createdInstances.containsKey(System.identityHashCode(value))) {
					String buildStatement = clazz.getCanonicalName() + " " + variableToCreate + " = new java.util.ArrayList();";
					this.createdInstances.put(System.identityHashCode(value), variableToCreate);
					statements.add("");
					statements.add(buildStatement);
				
					List<String> initializationStatements = getStatementsForCollection(variableToCreate, value);
					statements.addAll(initializationStatements);
				}
				statements.add(variableName + ".add(" + variableToCreate + ");");
				
			} else if (Set.class.isAssignableFrom(clazz)) {
				imports.add("java.util.Set");
				imports.add("kodkod.util.collections.IdentityHashSet");
				
				//DPD VAR NAME fix;
				//String variableToCreate = variableName + "_" + index;						
				String variableToCreate = generateVariableName(variableName,  value);
				
				if (!this.createdInstances.containsKey(System.identityHashCode(value))) {
					String buildStatement = value.getClass().getCanonicalName() + " " + variableToCreate + " = new kodkod.util.collections.IdentityHashSet();";
					this.createdInstances.put(System.identityHashCode(value), variableToCreate);
					statements.add("");
					statements.add(buildStatement);
				
					List<String> initializationStatements = getStatementsForCollection(variableToCreate, value);
	
					statements.addAll(initializationStatements);
				}

				statements.add(variableName + ".add(" + variableToCreate + ");");

			} else if (Map.class.isAssignableFrom(clazz)) {
				imports.add("java.util.Map");
				imports.add("java.util.IdentityHashMap");
				
				//DPD VAR NAME fix;
				//String variableToCreate = variableName + "_" + index;						
				String variableToCreate = generateVariableName(variableName,  value);
				

				if (this.createdInstances.containsKey(System.identityHashCode(value))) {
					//String createdVariable = this.createdInstances.get(System.identityHashCode(value));
//					String buildStatement = clazz.getCanonicalName() + " " + variableToCreate + " = (" + clazz.getCanonicalName() + ") " + createdVariable + ";";
//					statements.add(buildStatement);
					
				} else {
					String buildStatement = /*clazz.getCanonicalName() +*/ "Map " + variableToCreate + " = new java.util.IdentityHashMap();";
					this.createdInstances.put(System.identityHashCode(value), variableToCreate);
					statements.add("");
					statements.add(buildStatement);
					
					List<String> initializationStatements = getStatementsForMap(variableToCreate, value);
					statements.addAll(initializationStatements);					
					statements.add("");
				}

				statements.add(variableName + ".add(" + variableToCreate + ");");
				
			} else {
				if (!hasDefaultConstructor(value.getClass())) {
					throw new RuntimeException("DYNJALLOY ERROR!: Type: " + value.getClass().getCanonicalName() + " has no default Constructor.");
				}
				
				//DPD VAR NAME fix;
				//String createdVariable = variableName + "_" + value.getClass().getSimpleName() + "_" + index;						
				String createdVariable = generateVariableName(variableName,  value);

				if (this.createdInstances.containsKey(System.identityHashCode(value))) {
					//DPD BEGIN
					//String previousCreatedVariable = this.createdInstances.get(System.identityHashCode(value));
//					String buildStatement = value.getClass().getCanonicalName() + " " + createdVariable + " = " + previousCreatedVariable + ";";
//					statements.add(buildStatement);
					//DPD END
				} else {

					String buildStatement = value.getClass().getCanonicalName() + " " + createdVariable + " = new " + value.getClass().getCanonicalName() + "();";
					this.createdInstances.put(System.identityHashCode(value), createdVariable);
				
					//List<String> initializationStatements = getfieldsInitializationStatements(value.getClass(), value, createdVariable);
					List<String> initializationStatements = getfieldsInitializationStatements(value.getClass(), value, variableName);
				
					statements.add(buildStatement);
					statements.addAll(initializationStatements);
				}
				statements.add(variableName + ".add(" + createdVariable + ");");

			}
			index++;
		}
		
		return statements;
	}

	/**
	 * 
	 * @param buildVariable
	 * @param fieldValue
	 * @return
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	private List<String> getStatementsForMap(String variableName, Object fieldValue) throws IllegalArgumentException, IllegalAccessException {
		List<String> statements = new ArrayList<String>();
		
		Map<?, ?> mapFieldValue = (Map<?, ?>) fieldValue;
		int index = 0;
		for (Entry<?, ?> anEntry : mapFieldValue.entrySet()) {
			statements.add("");
			// Analyze the key
			String keyString = null;
			Object keyValue = anEntry.getKey();
			
			Class<?> clazz;
			if (keyValue == null) {
				clazz = null;
			} else {
				clazz = keyValue.getClass();
			}
			
			if (keyValue == null) {
				keyString = "null";
			} else if (isAutoboxingClass(keyValue.getClass())) {
				if (Character.class.isAssignableFrom(keyValue.getClass())) {
					keyString = "'" + String.valueOf(keyValue) + "'";
				} else {
					keyString = String.valueOf(keyValue);
				}

				
				
				
			} else if (clazz.isArray()) {
				Class<?> componentType = clazz.getComponentType();

				
				
				//DPD VAR NAME fix;
				//String variableToCreate = variableName + "_" + index;
				String variableToCreate = generateVariableName(variableName,  keyValue);
				if (!this.createdInstances.containsKey(System.identityHashCode(keyValue))) {				
					this.createdInstances.put(System.identityHashCode(keyValue), variableToCreate);
					String values = getValueForArray(componentType, keyValue, statements, variableName);

					String statement = clazz.getCanonicalName() + " " + variableToCreate + " = new " + clazz.getCanonicalName() + values + ";";
					statements.add(statement);
				}
				keyString = variableToCreate;

			} else if (List.class.isAssignableFrom(clazz)) {
				imports.add("java.util.List");
				imports.add("java.util.ArrayList");
				//DPD VAR NAME fix;
				//String variableToCreate = variableName + "_" + index;						
				String variableToCreate = generateVariableName(variableName,  keyValue);
				if (!this.createdInstances.containsKey(System.identityHashCode(keyValue))) {
					String buildStatement = clazz.getCanonicalName() + " " + variableToCreate + " = new java.util.ArrayList();";
					this.createdInstances.put(System.identityHashCode(keyValue), variableToCreate);
					statements.add("");
					statements.add(buildStatement);
				
					List<String> initializationStatements = getStatementsForCollection(variableToCreate, keyValue);
					statements.addAll(initializationStatements);
				}
				
				keyString = variableToCreate;
				
			} else if (Set.class.isAssignableFrom(clazz)) {
				imports.add("java.util.Set");
				imports.add("kodkod.util.collections.IdentityHashSet");
				//DPD VAR NAME fix;
				//String variableToCreate = variableName + "_" + index;						
				String variableToCreate = generateVariableName(variableName,  keyValue);
				if (!this.createdInstances.containsKey(System.identityHashCode(keyValue))) {
					String buildStatement = clazz.getCanonicalName() + " " + variableToCreate + " = new kodkod.util.collections.IdentityHashSet();";
					this.createdInstances.put(System.identityHashCode(keyValue), variableToCreate);
					statements.add("");
					statements.add(buildStatement);
				
					List<String> initializationStatements = getStatementsForCollection(variableToCreate, keyValue);
					statements.addAll(initializationStatements);
				}

				keyString = variableToCreate;

			} else if (Map.class.isAssignableFrom(clazz)) {
				imports.add("java.util.Map");
				imports.add("java.util.IdentityHashMap");
				//DPD VAR NAME fix;
				//String variableToCreate = variableName + "_" + index;						
				String variableToCreate = generateVariableName(variableName,  keyValue);

				if (this.createdInstances.containsKey(System.identityHashCode(keyValue))) {
//					String createdVariable = this.createdInstances.get(System.identityHashCode(keyValue));
//					String buildStatement = clazz.getCanonicalName() + " " + variableToCreate + " = (" + clazz.getCanonicalName() + ") " + createdVariable + ";";
//					statements.add(buildStatement);
					
				} else {
					String buildStatement = /*clazz.getCanonicalName() +*/ "Map " + variableToCreate + " = new java.util.IdentityHashMap();";
					this.createdInstances.put(System.identityHashCode(keyValue), variableToCreate);
					statements.add("");
					statements.add(buildStatement);
					
					List<String> initializationStatements = getStatementsForMap(variableToCreate, keyValue);
					statements.addAll(initializationStatements);					
					statements.add("");
				}

				keyString = variableToCreate;
				
			} else {
				if (!hasDefaultConstructor(keyValue.getClass())) {
					throw new RuntimeException("DYNJALLOY ERROR!: Type: " + keyValue.getClass().getCanonicalName() + " has no default Constructor.");
				}
				//DPD VAR NAME fix;
				//	String createdVariable = variableName + "_" + keyValue.getClass().getSimpleName() + "_" + index + "_" + "key";						
				String createdVariable = generateVariableName(variableName,  keyValue);

				if (this.createdInstances.containsKey(System.identityHashCode(keyValue))) {
					//DPD BEGIN
//					String previousCreatedVariable = this.createdInstances.get(System.identityHashCode(keyValue));
//					String buildStatement = keyValue.getClass().getCanonicalName() + " " + createdVariable + " = (" + keyValue.getClass().getCanonicalName() + ") " + previousCreatedVariable + ";";
//					statements.add(buildStatement);
					//DPD END
				} else {
				
					String buildStatement = keyValue.getClass().getCanonicalName() + " " + createdVariable + " = new " + keyValue.getClass().getCanonicalName() + "();";
					this.createdInstances.put(System.identityHashCode(keyValue), createdVariable);
				
					List<String> initializationStatements = getfieldsInitializationStatements(keyValue.getClass(), keyValue, variableName);
				
					statements.add(buildStatement);
					statements.addAll(initializationStatements);
				}
				
				keyString = createdVariable;
			}

			
			// Analyze the Value
			String valueString = null;
			Object value = anEntry.getValue();
			
			
			if (value == null) {
				clazz = null;
			} else {
				clazz = value.getClass();
			}
			
			if (value == null) {
				valueString = "null";
			} else if (isAutoboxingClass(clazz)) {
					//valueString = String.valueOf(value);
				if (Character.class.isAssignableFrom(value.getClass())) {
					valueString = "'" + String.valueOf(value) + "'";
				} else {
					valueString = String.valueOf(value);
				}
				
				
				
				
			} else if (clazz.isArray()) {
				Class<?> componentType = clazz.getComponentType();

				//DPD VAR NAME fix;
				//	String variableToCreate = variableName + "_" + index;						
				String variableToCreate = generateVariableName(variableName,  value);
				
				if (!this.createdInstances.containsKey(System.identityHashCode(value))) {
					this.createdInstances.put(System.identityHashCode(value), variableToCreate);
					String values = getValueForArray(componentType, value, statements, variableName);
					String statement = clazz.getCanonicalName() + " " + variableToCreate + " = new " + clazz.getCanonicalName() + values + ";";
					statements.add(statement);
				}
				
				valueString = variableToCreate;

			} else if (List.class.isAssignableFrom(clazz)) {
				imports.add("java.util.List");
				imports.add("java.util.ArrayList");
				//DPD VAR NAME fix;
				//String variableToCreate = variableName + "_" + index;						
				String variableToCreate = generateVariableName(variableName,  value);
				if (!this.createdInstances.containsKey(System.identityHashCode(value))) {
					String buildStatement = clazz.getCanonicalName() + " " + variableToCreate + " = new java.util.ArrayList();";
					this.createdInstances.put(System.identityHashCode(value), variableToCreate);
					statements.add("");
					statements.add(buildStatement);
				
					List<String> initializationStatements = getStatementsForCollection(variableToCreate, value);
					statements.addAll(initializationStatements);
				}
				
				valueString = variableToCreate;
				
			} else if (Set.class.isAssignableFrom(clazz)) {
				imports.add("java.util.Set");
				imports.add("kodkod.util.collections.IdentityHashSet");
				//DPD VAR NAME fix;
				//String variableToCreate = variableName + "_" + index;						
				String variableToCreate = generateVariableName(variableName,  value);
				if (!this.createdInstances.containsKey(System.identityHashCode(value))) {
					String buildStatement = clazz.getCanonicalName() + " " + variableToCreate + " = new kodkod.util.collections.IdentityHashSet();";
					this.createdInstances.put(System.identityHashCode(value), variableToCreate);
					statements.add("");
					statements.add(buildStatement);
					
					List<String> initializationStatements = getStatementsForCollection(variableToCreate, value);
					statements.addAll(initializationStatements);
				}
				valueString = variableToCreate;

			} else if (Map.class.isAssignableFrom(clazz)) {
				imports.add("java.util.Map");
				imports.add("java.util.IdentityHashMap");
				//DPD VAR NAME fix;
				//String variableToCreate = variableName + "_" + index;						
				String variableToCreate = generateVariableName(variableName,  fieldValue);

				if (this.createdInstances.containsKey(System.identityHashCode(value))) {
//					String createdVariable = this.createdInstances.get(System.identityHashCode(value));
//					String buildStatement = clazz.getCanonicalName() + " " + variableToCreate + " = (" + clazz.getCanonicalName() + ") " + createdVariable + ";";
//					statements.add(buildStatement);
					
				} else {
					String buildStatement = /*clazz.getCanonicalName() +*/ "Map " + variableToCreate + " = new java.util.IdentityHashMap();";
					this.createdInstances.put(System.identityHashCode(value), variableToCreate);
					statements.add("");
					statements.add(buildStatement);
					
					List<String> initializationStatements = getStatementsForMap(variableToCreate, value);
					statements.addAll(initializationStatements);					
					statements.add("");
				}

				valueString = variableToCreate;
				
			} else {
				if (!hasDefaultConstructor(value.getClass())) {
					throw new RuntimeException("DYNJALLOY ERROR!: Type: " + value.getClass().getCanonicalName() + " has no default Constructor.");
				}
				
				
				//DPD VAR NAME fix;
				//String createdVariable = variableName + "_" + value.getClass().getSimpleName() + "_" + index + "_" + "value";						
				String createdVariable = generateVariableName(variableName,  fieldValue);

				if (this.createdInstances.containsKey(System.identityHashCode(value))) {
					//DPD BEGIN
//					String previousCreatedVariable = this.createdInstances.get(System.identityHashCode(value));
//					String buildStatement = value.getClass().getCanonicalName() + " " + createdVariable + " = (" + value.getClass().getCanonicalName() + ")" + previousCreatedVariable + ";";
//					statements.add(buildStatement);
					//DPD END
				} else {
					
					String buildStatement = value.getClass().getCanonicalName() + " " + createdVariable + " = new " + value.getClass().getCanonicalName() + "();";
					this.createdInstances.put(System.identityHashCode(value), createdVariable);
					
					List<String> initializationStatements = getfieldsInitializationStatements(value.getClass(), value, variableName);
					
					statements.add(buildStatement);
					statements.addAll(initializationStatements);
				}
				valueString = createdVariable;
			}
			
			statements.add(variableName + ".put(" + keyString + ", " + valueString + ");");
			
			index++;
		}
		
		return statements;
	}

//	int tempVarCount = 0;
//	private String createVar() {
//		String s = "tmp_" + tempVarCount++;
//		return s;
//	}
	/**
	 * 
	 * @param aField
	 * @param instance
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	private String getValueForArray(Class<?> componentType, Object fieldValue, List<String> statements, String variableName) throws IllegalArgumentException, IllegalAccessException {
		String ret_value = "{";

		int length = Array.getLength(fieldValue);

		log.debug("getValueForArray");
		log.debug(fieldValue.toString());
		log.debug(componentType);
	
		if (componentType.isPrimitive()) {
			String typeSimpleName = componentType.getSimpleName();

			for (int x = 0; x < length; x++) {
				Object elementAsObject = Array.get(fieldValue, x);
				Class<?> elementClass;
				if (elementAsObject == null) {
					elementClass = null;
				} else {
					elementClass = elementAsObject.getClass();
				}
				if (typeSimpleName.equals("boolean")) {
					if (elementClass != null && Boolean.class.isAssignableFrom( elementClass )) {
						ret_value += Boolean.toString(Array.getBoolean(fieldValue, x));
					} else {
						ret_value += "false";
					}

				} else if (typeSimpleName.endsWith("byte")) {
					if (elementClass != null && Byte.class.isAssignableFrom( elementClass )) {
						ret_value += Byte.toString(Array.getByte(fieldValue, x));
					} else {
						ret_value += 0;
					}
					
					
				} else if (typeSimpleName.endsWith("char")) {
					if (elementClass != null && Character.class.isAssignableFrom( elementClass )) {
						ret_value += "'" + Character.toString(Array.getChar(fieldValue, x)) + "'";
					} else {
						ret_value += 'a';
					}
					
					
				} else if (typeSimpleName.endsWith("double")) {
					if (elementClass != null && Double.class.isAssignableFrom( elementClass )) {
						ret_value += Double.toString(Array.getDouble(fieldValue, x));
					} else {
						ret_value += 0.0;
					}
					
					
				} else if (typeSimpleName.endsWith("float")) {
					if (elementClass != null && Float.class.isAssignableFrom( elementClass )) {
						ret_value += Float.toString(Array.getFloat(fieldValue, x));
					} else {
						ret_value += 0.0;
					}
					
				} else if (typeSimpleName.endsWith("int")) {
					if (elementClass != null && Integer.class.isAssignableFrom( elementClass )) {
						ret_value += Integer.toString(Array.getInt(fieldValue, x));
					} else {
						ret_value += "0";
					}
					
				} else if (typeSimpleName.endsWith("long")) {
					if (elementClass != null && Long.class.isAssignableFrom( elementClass )) {
						ret_value += Long.toString(Array.getLong(fieldValue, x)) + "L";
					} else {
						ret_value += 0L;
					}
					
					
				} else if (typeSimpleName.endsWith("short")) {
					
					if (elementClass != null && Short.class.isAssignableFrom( elementClass )) {
						ret_value += Short.toString(Array.getShort(fieldValue, x));
					} else {
						ret_value += 0;
					}
					
				} else {
					log.error("ERROR: No difinida");
				}

				if (x < length - 1) {
					ret_value += ", ";
				}
			}
		} else {
			//throw new TacoNotImplementedYetException("Type: " + componentType.toString() + "Not Implemented yet");
			for (int x = 0; x < length; x++) {
				
				Object instance = Array.get(fieldValue, x);
				
				if (instance == null) {
					ret_value += "null";
				} else {
					if (this.createdInstances.containsKey(instance)) {
						ret_value += this.createdInstances.get(instance);
					} else {
						//String varName = createVar();
						String generatedName = generateVariableName(variableName,  fieldValue);
						
						ret_value += generatedName;
						
						String initValue;						
						String typeSimpleName = instance.getClass().getSimpleName();
						if (typeSimpleName.equals("boolean") || typeSimpleName.equals("Boolean")) {
							initValue = instance.toString();

						} else if (typeSimpleName.endsWith("byte") || typeSimpleName.endsWith("Integer")) {
							initValue = instance.toString();

						} else if (typeSimpleName.endsWith("char") || typeSimpleName.endsWith("Character")) {
							initValue = "'" + instance + "'";

						} else if (typeSimpleName.endsWith("double") || typeSimpleName.endsWith("Double")) {
							initValue = instance.toString();

						} else if (typeSimpleName.endsWith("float") || typeSimpleName.endsWith("Float")) {
							initValue = instance.toString();

						} else if (typeSimpleName.endsWith("int") || typeSimpleName.endsWith("Integer")) {
							initValue = instance.toString();

						} else if (typeSimpleName.endsWith("long") || typeSimpleName.endsWith("Long")) {
							initValue = instance.toString() + "L";

						} else if (typeSimpleName.endsWith("short") || typeSimpleName.endsWith("Short")) {
							initValue = instance.toString();

						} else if (instance.getClass().isArray()) {
							Class<?> aComponentType2 = instance.getClass().getComponentType();
							int aLength2 = Array.getLength(instance);
							initValue = "new " + aComponentType2.getClass().getCanonicalName() + "[" + aLength2 + "]";
							
						} else {
							initValue = "new " + instance.getClass().getCanonicalName() + "()";
						}
						String buildStatement = /*componentType.getCanonicalName()*/instance.getClass().getCanonicalName() + " " + generatedName + " = " + initValue + ";";
						this.createdInstances.put(System.identityHashCode(instance), generatedName);
						List<String> initializationStatements = getfieldsInitializationStatements(componentType, instance, variableName);
					
						statements.add("");
						statements.add(buildStatement);
						statements.addAll(initializationStatements);
					} 
				}
				
				if (x < length - 1) {
					ret_value += ", ";
				}
				
			}
			
		}

		ret_value += "}";

		return ret_value;
	}

	/**
	 * 
	 * @param aField
	 * @param instance
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	private String getValueForPrimitiveTypeField(Field aField, Object instance) throws IllegalArgumentException, IllegalAccessException {
		String typeSimpleName = aField.getType().getSimpleName();
		String value = null;

		aField.setAccessible(true);

		if (typeSimpleName.equals("boolean")) {
			value = Boolean.toString(aField.getBoolean(instance));
		} else if (typeSimpleName.endsWith("byte")) {
			value = Byte.toString(aField.getByte(instance));
		} else if (typeSimpleName.endsWith("char")) {
			value = "'" + Character.toString(aField.getChar(instance)) + "'";
		} else if (typeSimpleName.endsWith("double")) {
			value = Double.toString(aField.getDouble(instance));
		} else if (typeSimpleName.endsWith("float")) {
			value = Float.toString(aField.getFloat(instance));
		} else if (typeSimpleName.endsWith("int")) {
			value = Integer.toString(aField.getInt(instance));
		} else if (typeSimpleName.endsWith("long")) {
			value = Long.toString(aField.getLong(instance)) + "L";
		} else if (typeSimpleName.endsWith("short")) {
			value = Short.toString(aField.getShort(instance));
		} else {
			System.out.println("ERROR: undefined");
		}
		return value;
	}

	/**
	 * Generate the statements to invoke the method from a JUnit tests
	 * 
	 * @param clazz
	 * @param methodToCheck
	 * @return
	 */
	private List<String> getMethodInvocationStatements(Class<?> clazz, Method methodToCheck) {
		List<String> statements = new ArrayList<String>();

		//String methodParameters = StringUtils.join(recoveredInformation.getMethodParametersNames(), ", ");
		String methodParameters = StringUtils.join(parameterInstanceNames, ", ");

		statements.add("");
		statements.add("// Method Invocation");
		if (!methodToCheck.isAccessible()) {
			statements.add("Method method = getAccessibleMethod(\"" + recoveredInformation.getClassToCheck() + "\", \""
					+ recoveredInformation.getMethodToCheck() + "\", true);");
			statements.add("try {");
			statements.add("    method.invoke(instance, " + ((methodParameters.length() == 0) ? "null" : methodParameters) + ");");
			statements.add("} catch (Exception e) {");
			statements.add("    e.printStackTrace();");
			statements.add("} ");
		} else {
			statements.add("instance." + recoveredInformation.getMethodToCheck() + "(" + methodParameters + ");");
		}

		return statements;
	}
	
	/**
	 * Returns the path of the class that will hold the test for the pair
	 * class-method found in the recoveredInformation. 
	 */
	public String getOutputClassFilename() {
		File file = new File(getFilename(getOutputClassName()));
		return file.getAbsolutePath();
	}
	
	
	/**
	 * Returns the name of the class that will hold the test for the pair
	 * class-method found in the recoveredInformation. 
	 */
	public String getOutputClassName() {
		String className = recoveredInformation.getClassToCheck().substring(recoveredInformation.getClassToCheck().lastIndexOf(".") + 1) + "Test";
		String methodName = recoveredInformation.getMethodToCheck();
		return className + "_" + methodName;
	}
	
	/**
	 * Returns the path to the file where the unit test for the given class will
	 * be written.
	 */
	private String getFilename(String outputClassName) {
		String mySeparator;
		if (separator.equals("\\")) {
			mySeparator = "\\\\";
		} else {
			mySeparator = separator;
		}
	
		return outputPath + OUTPUT_DIR +
			PACKAGE_NAME.replaceAll("\\.", mySeparator) + separator +
			outputClassName + OUTPUT_SIMPLIFIED_JAVA_EXTENSION;
	}

	/**
	 * Deletes the file where the unit test is written.
	 * This method might be used in order to start a fresh run. 
	 */
	public void deleteFile(String outputClassName) {
		File file = new File(getFilename(outputClassName));
		if (file.exists())
			file.delete();
	}

	/**
	 * Write the generated JUnit to the file system
	 * 
	 * @param outputClassName
	 * @param methodName
	 * @param imports
	 * @param statements
	 * @param isAccessible
	 */
	private void writeToFile(String outputClassName, String methodName, Set<String> imports, List<String> statements, boolean isAccessible) {
		JUnitPrettyPrinter jUnitPrettyPrinter = new JUnitPrettyPrinter();
		jUnitPrettyPrinter.setPackageName(PACKAGE_NAME);
		jUnitPrettyPrinter.setClassName(outputClassName);
		jUnitPrettyPrinter.setMethodName(methodName);
		jUnitPrettyPrinter.setImports(imports);
		jUnitPrettyPrinter.setStatements(statements);

		jUnitPrettyPrinter.writeToFile(getFilename(outputClassName), !isAccessible);
	}
	
	/**
	 * 
	 * @param clazz
	 * @return
	 */
	private boolean isAutoboxingClass(Class<?> clazz) {
		boolean ret_Value = false;
		
		ret_Value |= Boolean.class.isAssignableFrom(clazz);
		ret_Value |= Byte.class.isAssignableFrom(clazz);
		ret_Value |= Character.class.isAssignableFrom(clazz);
		ret_Value |= Double.class.isAssignableFrom(clazz);
		ret_Value |= Float.class.isAssignableFrom(clazz);
		ret_Value |= Integer.class.isAssignableFrom(clazz);
		ret_Value |= Long.class.isAssignableFrom(clazz);
		ret_Value |= Short.class.isAssignableFrom(clazz);
		
		return ret_Value;
	}

	/**
	 * 
	 * @param parameterType
	 * @return
	 */
	private boolean hasDefaultConstructor(Class<?> parameterType) {
		boolean ret_val = false;

		// I the class is defined as inner class then, the default constructor
		// contains the containing class at first parameter.
		int constructorParametersAmong = 0;
		if (parameterType.isMemberClass() && !Modifier.isStatic(parameterType.getModifiers())) {
			constructorParametersAmong = 1;
		}

		for (Constructor<?> aConstructor : parameterType.getDeclaredConstructors()) {
			if (aConstructor.getParameterTypes().length == constructorParametersAmong) {
				ret_val = true;
			}
		}

		return ret_val;
	}

	/**
	 * Extract the module name for a given parameter
	 * 
	 * @param parameterType
	 * @return
	 */
	private String getModuleName(Class<?> parameterType) {
		String moduleName = null;
		if (parameterType.isMemberClass()) {
			moduleName = parameterType.getName().replace("$", ".inner.").replace('.', '_');
		} else {
			moduleName = parameterType.getCanonicalName().replace('.', '_');
		}
		return moduleName;
	}
}
