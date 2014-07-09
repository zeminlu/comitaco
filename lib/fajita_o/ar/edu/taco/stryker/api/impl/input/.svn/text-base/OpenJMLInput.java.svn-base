package ar.edu.taco.stryker.api.impl.input;

import java.util.Properties;


public class OpenJMLInput {

	private String filename;

	private String junitFile;
	
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
	public OpenJMLInput(String filename, String junitFile, String method, String configFile, 
			Properties overridingProperties, String originalFilename) {
		super();
		this.filename = filename;
		this.junitFile = junitFile;
		this.method = method;
		this.configFile = configFile;
		this.overridingProperties = overridingProperties;
		this.originalFilename = originalFilename;
	}

	/**
	 * @return The file that contains all the statements that will make the class fail.
	 */
	public String getJunitFile() {
		return junitFile;
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
