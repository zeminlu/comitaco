package ar.edu.taco.stryker.api.impl.input;

import java.util.Collection;
import java.util.Properties;

import mujava.api.Mutant;


public class OpenJMLInput {

    private String filename;

    private String method;

    private String configFile;

    private Properties overridingProperties;

    private String originalFilename;

    private MuJavaFeedback feedback;

    private Collection<Mutant> mutantsToApply;

    private Object syncObject;

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
    public OpenJMLInput(String filename, String method, String configFile, 
            Properties overridingProperties, String originalFilename, MuJavaFeedback feedback, Collection<Mutant> mutantsToApply, Object syncObject) {
        super();
        this.filename = filename;
        this.method = method;
        this.configFile = configFile;
        this.overridingProperties = overridingProperties;
        this.originalFilename = originalFilename;
        this.feedback = feedback;
        this.mutantsToApply = mutantsToApply;
        this.syncObject = syncObject;
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

    public MuJavaFeedback getFeedback() {
        return feedback;
    }

    public void setFeedback(MuJavaFeedback feedback) {
        this.feedback = feedback;
    }

    public Collection<Mutant> getMutantsToApply() {
        return mutantsToApply;
    }

    public void setMutantsToApply(Collection<Mutant> mutantsToApply) {
        this.mutantsToApply = mutantsToApply;
    }

    public Object getSyncObject() {
        return syncObject;
    }

    public void setSyncObject(Object syncObject) {
        this.syncObject = syncObject;
    }
    
}
