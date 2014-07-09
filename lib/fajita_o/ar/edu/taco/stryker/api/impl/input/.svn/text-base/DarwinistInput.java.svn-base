package ar.edu.taco.stryker.api.impl.input;

import java.util.Properties;

public class DarwinistInput {
	
	private String filename;
	
	private String originalFilename;
	
	private String configFile;
	
	private String method;
	
	private Properties overridingProperties;

	private String fullyQualifiedClassName;
	
	/**
	 * Creates a DarwinistInput.
	 * 
	 * @param filename The filename that contains the class that has failed.
	 * @param originalFilename The original filename that first file that was intented to resolve.
	 * @param configFile The TACO configuration file.
	 * @param method The method that has failed.
	 * @param overridingProperties The overriding properties
	 * @param fullyQualifiedClassName the fully qualified class name
	 */
	public DarwinistInput(String filename, String originalFilename, String configFile, String method, Properties overridingProperties, String fullyQualifiedClassName) {
		super();
		this.filename = filename;
		this.originalFilename = originalFilename;
		this.configFile = configFile;
		this.method = method;
		this.overridingProperties = overridingProperties;
		this.fullyQualifiedClassName = fullyQualifiedClassName;
	}

	/**
	 * @return The filename that contains the class that has failed.
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @return The original filename that contains the bug that is intended to solve
	 */
	public String getOriginalFilename() {
		return originalFilename;
	}
	
	/**
	 * @return The TACO configuration file.
	 */
	public String getConfigurationFile() {
		return configFile;
	}

	/**
	 * @return The method that failed
	 */
	public String getMethod() {
		return method;
	}

	public Properties getOverridingProperties() {
		return overridingProperties;
	}

	public String getFullyQualifiedClassName() {
		return fullyQualifiedClassName;
	}	
	

}
