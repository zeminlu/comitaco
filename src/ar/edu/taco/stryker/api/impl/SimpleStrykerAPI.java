package ar.edu.taco.stryker.api.impl;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

import mujava.api.Mutant;
import ar.edu.taco.stryker.api.StrykerAPI;
import ar.edu.taco.stryker.api.impl.input.MuJavaInput;

public class SimpleStrykerAPI implements StrykerAPI {

	private DarwinistController darwinistController;
	private MuJavaController mujavaController;
	private OpenJMLController openjmlController;
	
	@Override
	public void start() {
		mujavaController = MuJavaController.getInstance();
		openjmlController = OpenJMLController.getInstance();
		darwinistController = DarwinistController.getInstance();
		mujavaController.start();
		openjmlController.start();
		darwinistController.start();
		
	}

	@Override
	public List<String> fixBug(File classToMutate, String classNameToMutate, String methodToMutate, 
			Class<?>[] junitInputs, HashSet<Mutant> mutOps, AtomicInteger generationsWanted, String configFile, 
			Properties overridingProperties, int maxMethodsInFile) {
		mujavaController.setMaxMethodsInFile(maxMethodsInFile);
		mujavaController.enqueueTask(new MuJavaInput(classToMutate.getAbsolutePath(), methodToMutate, 
				junitInputs, mutOps, generationsWanted, configFile, overridingProperties, classToMutate.getAbsolutePath(), 
				new Object()));
		while(!darwinistController.willShutdown.get());
		return darwinistController.getResolvedBugs();
	}

}
