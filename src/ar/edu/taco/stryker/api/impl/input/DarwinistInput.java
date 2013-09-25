package ar.edu.taco.stryker.api.impl.input;

import java.util.Map;
import java.util.Properties;

public class DarwinistInput {
	
	private String filename;
	
	private String originalFilename;
	
	private String configFile;
	
	private String method;
	
	private Properties overridingProperties;

	private String fullyQualifiedClassName;
	
	private Class<?>[] junitInputs;
	
	private Object[] parametersFromOpenJML;
	
	private Boolean forSeqProcessing;
	
	private String seqFilesPrefix; //Used for instrumentation

	private String seqMethod;

	private String seqMethodInput;
	
	private String seqVariablizedFilename;
	
    private String oldFilename; //Used for instrumentation
	
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
	public DarwinistInput(String filename, String originalFilename, String configFile, String method, 
	        Properties overridingProperties, String fullyQualifiedClassName, Class<?>[] junitInputs, 
	        Object[] parametersFromOpenJML, Boolean forSeqProcessing, String seqMethod, 
	        String seqMethodInput, String seqFilesPrefix, String seqVariablizedFilename, String oldFilename) {
		super();
		this.filename = filename;
		this.originalFilename = originalFilename;
		this.configFile = configFile;
		this.method = method;
		this.overridingProperties = overridingProperties;
		this.fullyQualifiedClassName = fullyQualifiedClassName;
		this.junitInputs = junitInputs;
		this.parametersFromOpenJML = parametersFromOpenJML;
		this.forSeqProcessing = forSeqProcessing;
		this.seqMethod = seqMethod;
		this.seqMethodInput = seqMethodInput;
		this.seqFilesPrefix = seqFilesPrefix;
		this.seqVariablizedFilename = seqVariablizedFilename;
		this.oldFilename = oldFilename;
	}

	/**
	 * Tells DarwinistController that this input is to be processed for sequential code and that after the processing
	 * it should let know MuJavaController which lines aren't worth mutating
	 * @return
	 */
	public Boolean isForSeqProcessing() {
        return forSeqProcessing;
    }

	/**
	 * This is the seq method to be used for seq processing
	 * @return
	 */
	public String getSeqMethod() {
        return seqMethod;
    }
	
	public String getSeqMethodInput() {
        return seqMethodInput;
    }
	
	public String getOldFilename() {
        return oldFilename;
    }
	
	public String getSeqFilesPrefix() {
        return seqFilesPrefix;
    }
	
	public String getSeqVariablizedFilename() {
        return seqVariablizedFilename;
    }
	
	public void setSeqVariablizedFilename(String seqVariablizedFilename) {
        this.seqVariablizedFilename = seqVariablizedFilename;
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
	
	public Class<?>[] getInputs(){
		return this.junitInputs;
	}
	
	public Object[] getParametersFromOpenJML(){
		return this.parametersFromOpenJML;
	}
	

}
