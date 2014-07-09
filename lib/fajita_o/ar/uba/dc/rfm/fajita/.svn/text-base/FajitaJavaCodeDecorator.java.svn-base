package ar.uba.dc.rfm.fajita;

import static ar.uba.dc.rfm.tools.FileSystemTools.separator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.uba.dc.rfm.fajita.translation.FajitaJavaCodeTranslator;


/**
 * This class contains the logic required for modifying the input java
 * source code in order to be able to instrument the techniques used
 * with the different coverage criteria.
 * 
 */
public class FajitaJavaCodeDecorator {
	
	/** Runtime configuration information. */
	private FajitaConfiguration configuration;
	
	
	/**
	 * Constructor for a <code>FajitaJavaCodeDecorator</code>.
	 * 
	 * @param configuration a <code>FajitaConfiguration</code> with the runtime
	 * configuration of the project.
	 */
	public FajitaJavaCodeDecorator(FajitaConfiguration configuration) {
		this.configuration = configuration;
	}
	
	
	/**
	 * This method implements the <code>Runnable</code> interface.<br />
	 * 
	 * It starts the translation from the java source code inputed by the user
	 * into a java code with the necessary constructs.<br />
	 * 
	 * It also compiles the translated class (and any relevant classes) since
	 * this is needed by other components.
	 * 
	 */
	public void run() {
		List<String> relevantClasses = findRelevantClasses(configuration.getClassToCheck());
		
		configuration.setDiscoveredGoals(0);
		FajitaJavaCodeTranslator translator = new FajitaJavaCodeTranslator(configuration);
		
		List<String> translatedFiles = new ArrayList<String>(relevantClasses.size());
		for (String clazz : relevantClasses) {
			String clazzDesFile = getDesFile(clazz);
			translator.translate(getSrcFile(clazz), clazzDesFile);
			translatedFiles.add(clazzDesFile);
		}
		
		compile(translatedFiles);
		
		configuration.setGoalTagFilter(FajitaJavaCodeTranslator.FAJITA_GOAL_TAG + "_[0-9]+");
		configuration.setCompiledClassToCheckPath(getDesDir());
	}
	
	
	/**
	 * This method finds the relevant classes required by the class to check.
	 * 
	 * @param classToCheck a <code>String</code> with the name of the class
	 * that is being worked with.
	 * 
	 * @return a <code>List &lt String &gt </code> with the name of all the
	 * classes required by <i>classToCheck</i>.
	 * 
	 */
	private List<String> findRelevantClasses(String classToCheck) {
		Set<String> classes = new HashSet<String>();
		classes.add(classToCheck);
		
		if (!configuration.getRelevantClasses().equals("")) {
			String[] configClasses = configuration.getRelevantClasses().split(",");
			for (String clazz : configClasses)
				classes.add(clazz);
		}
		
		List<String> relevantClasses = new ArrayList<String>();
		relevantClasses.addAll(classes);
		
		return relevantClasses;
	}
	
	
	/**
	 * This method returns the path to the source file for the given class name.
	 */
	private String getSrcFile(String className) {
		return configuration.getClasspath() + separator +
			className.replaceAll("\\.", separator) + ".java";
	}
	

	/**
	 * This method returns the destination directory where the translated code
	 * will be stored and compiled.
	 */
	private String getDesDir() {
		return configuration.getResultPath() + separator +
			FajitaConfiguration.FAJITA_OUT + separator +
			configuration.getClassToCheck().replace(".", "_") + separator;
	}
	
	
	/**
	 * This method returns the path to the translated file.
	 */
	private String getDesFile(String className) {
		return getDesDir() + className.replace(".", separator) + ".java";
	}
	
	
	/**
	 * This method uses the system java compiler in order to compile the
	 * relevant classes.
	 * 
	 * @param files a <code>List &lt String &gt</code> with the names of the
	 * relevant classes.
	 * 
	 */
	private void compile(List<String> files) {
		String fileList = files.toString().substring(1).replaceAll("\\.java", "")
			.replaceAll("\\.", separator).replaceAll("[,\\]]", ".java").trim();
		String compileCommand = "javac -d " + getDesDir() + " " + fileList;
		
		try {
			int compileRetCode = Runtime.getRuntime().exec(compileCommand).waitFor();
			
			if (compileRetCode != 0) {
				throw new FajitaException(
					"Unable to compile parsed java code, executing:\n" +
					compileCommand + "\n" +
					"Compiler returned the error code " + compileRetCode);
			}
		} catch (IOException e) {
			throw new FajitaException(
				"Unable to compile parsed java code. " +
				"Due to the following error: " + e.getMessage(),
				e
			);
		} catch (InterruptedException e) {
			throw new FajitaException(
				"Unable to compile parsed java code. " +
				"Due to the following error: " + e.getMessage(),
				e
			);
		}

	}
	
}
