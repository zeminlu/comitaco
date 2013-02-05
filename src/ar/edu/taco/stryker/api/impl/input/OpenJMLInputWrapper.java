package ar.edu.taco.stryker.api.impl.input;

import java.util.Map;
import java.util.Properties;


public class OpenJMLInputWrapper {

	private Map<String, OpenJMLInput> map;
	
	private String filename;

	private Class<?>[] junitInputs;
	
	private String configFile;
	
	private String method;
	
	private Properties overridingProperties;
	
	/**
	 * Creates a OpenJMLInput.
	 * 
	 * @param filename The filename that contains the class that has failed.
	 * @param junitFile The file that contains all the statements that will make the class fail.
	 * @param configFile The TACO configuration file.
	 * @param overridingProperties The overriding properties
	 */	
	public OpenJMLInputWrapper(String filename, Class<?>[] junitInputs, String configFile, 
			Properties overridingProperties, String method, Map<String,OpenJMLInput> map) {
		super();
		this.filename = filename;
		this.junitInputs = junitInputs;
		this.configFile = configFile;
		this.overridingProperties = overridingProperties;
		this.method = method;
		this.map = map;
	}

	/**
	 * @return The file that contains all the statements that will make the class fail.
	 */
	public Class<?>[] getJunitInputs() {
		return junitInputs;
	}

	/**
	 * @return The filename that contains the class that has failed.
	 */
	public String getFilename() {
		return filename;
	}
	
	/**
	 * @return The TACO configuration file.
	 */
	public String getConfigurationFile() {
		return configFile;
	}

	public Properties getOverridingProperties() {
		return overridingProperties;
	}
	
	public Map<String, OpenJMLInput> getMap() {
		return map;
	}
	
	public String getMethod() {
		return method;
	}

}
