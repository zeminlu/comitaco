package ar.edu.taco.stryker.api.impl.input;

import java.util.Collection;
import java.util.Properties;

import mujava.api.Mutant;


public class MuJavaInput {
	
	private String filename;
	
	private String method;
	
	private String junitFile;
	
	private Collection<Mutant> mutantsToApply;
	
	private int qtyOfGenerations;
	
	private String configFile;
	
	private Properties overridingProperties;
	
	private String originalFilename;
	
	private Object syncObject;
	
	/**
	 * Creates a MuJavaInput.
	 * 
	 * @param filename The filename that contains the class that has failed.
	 * @param method The method that has failed.
	 * @param junitFile The file that contains all the statements that will make the class fail.
	 * @param mutantsToApply The list of mutants to apply.
	 * @param qtyOfGenerations The quantity of generations that must be created.
	 * @param configFile The TACO configuration file.
	 * @param overridingProperties The TACO overriding properties
	 * @param originalFilename The original filename with the bug to solve
	 */
	public MuJavaInput(String filename, String method, String junitFile,
			Collection<Mutant> mutantsToApply, int qtyOfGenerations, String configFile, 
			Properties overridingProperties, String originalFilename, Object syncObject) {
		super();
		this.filename = filename;
		this.method = method;
		this.junitFile = junitFile;
		this.mutantsToApply = mutantsToApply;
		this.qtyOfGenerations = qtyOfGenerations;
		this.configFile = configFile;
		this.overridingProperties = overridingProperties;
		this.originalFilename = originalFilename;
		this.syncObject = syncObject;
	}
	
	/**
	 * @return The filename of the class that has failed
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @return The method that failed
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @return The file that contains all the statements that will make the class fail.
	 */
	public String getJunitFile() {
		return junitFile;
	}

	/**
	 * @return All the mutants that must be applied
	 */
	public Collection<Mutant> getMutantsToApply() {
		return mutantsToApply;
	}

	/**
	 * @return The quantity of generations to create
	 */
	public int getQtyOfGenerations() {
		return qtyOfGenerations;
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

	public String getOriginalFilename() {
		return originalFilename;
	}

	/**
	 * @return the syncObject
	 */
	public Object getSyncObject() {
		return syncObject;
	}
	
}
