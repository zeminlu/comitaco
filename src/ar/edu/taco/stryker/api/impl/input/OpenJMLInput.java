package ar.edu.taco.stryker.api.impl.input;

import java.util.Properties;


public class OpenJMLInput {

	private String filename;

	private Class<?>[] junitInputs;
	
	private String method;
	
	private String configFile;
	
	private Properties overridingProperties;
	
	private String originalFilename;
	
	/**
	 * Creates a OpenJMLInput.
	 * 
	 * @param filename The filename that contains the class that has failed.
	 * @param method The method that has failed.
	 * @param junitFile The file that contains all the statements that will make the class fail.
	 * @param configFile The TACO configuration file.
	 * @param overridingProperties The overriding properties
	 * @param originalFilename The original filename
	 */	
	public OpenJMLInput(String filename, Class<?>[] junitInputs, String method, String configFile, 
			Properties overridingProperties, String originalFilename) {
		super();
		this.filename = filename;
		this.junitInputs = junitInputs;
		this.method = method;
		this.configFile = configFile;
		this.overridingProperties = overridingProperties;
		this.originalFilename = originalFilename;
	}

	/**
	 * @return The file that contains all the statements that will make the class fail.
	 */
	public Class<?>[] getJunitInputs() {
		return junitInputs;
	}

	/**
	 * @return The method that has failed.
	 */
	public String getMethod() {
		return method;
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

	/**
	 * @return the originalFilename
	 */
	public String getOriginalFilename() {
		return originalFilename;
	}
}
