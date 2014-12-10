package mujava.api;

import java.io.File;
import java.io.PrintWriter;
import java.util.Set;

import mujava.MutationSystem;
import mujava.NotDirBasedMutantsGenerator;
import mujava.OpenJavaException;
import mujava.util.Debug;
import openjava.ptree.CompilationUnit;
import openjava.ptree.ParseTreeException;

/**
 * This is an API added to muJava's implementation so as to be able to obtain
 * mutants programmatically without having them outputed in the structured
 * directory created by muJava.
 * 
 * In order to avoid changing a huge amount of muJava's codebase this API is
 * created (with horrible code inside) so as to minimize code intervention
 * (but some minor changes in muJava's codebase had to be done nonetheless).
 */
/*
 * CODE-READER'S BEWARE: THIS CODE IS HORRIBLE.
 * 
 * In order to avoid massive intervention in muJava's original
 * codebase some ugly stuff had to be done.
 */
public class Api {

	private static boolean usingApi = false;
	
	private static String methodToConsider;
	
	private static boolean outputComments = true;
	
	/**
	 * Generates mutants for the given java file, considering only the method
	 * name and mutant operators passed as parameters. It is important to note
	 * that all compiled classed have to be in the CLASSPATH. For example, the
	 * "bin" directory containing the compiled java file as well as other
	 * necessary classes should be in the CLASSPATH.
	 * 
	 * @param javaFile the .java file to generate mutants from.
	 * @param className the name of the class to consider.
	 * @param methodToConsider the method to consider. Mutant operators will
	 *     be only applied to expressions within this given method
	 * @param mutOps the mutant operators to apply if possible
	 * @return a MutantsInformationHolder object that holds the necessary
	 *     information that identifies each mutant generated from the given
	 *     java file
	 * @see MutantsInformationHolder
	 * @throws OpenJavaException if some exception occurs while parsing the
	 *     given java file
	 */
	public static MutantsInformationHolder generateMutants(File javaFile,
			String className, String methodToConsider, Set<Mutant> mutOps)
			throws OpenJavaException {
		usingApi = true;
		Api.methodToConsider = methodToConsider;
		Debug.setDebugLevel(0);
		NotDirBasedMutantsGenerator gen = new NotDirBasedMutantsGenerator(javaFile, mutOps);
		MutationSystem.CLASS_NAME = className;
		gen.makeMutants();
		MutantsInformationHolder ret = MutantsInformationHolder.mainHolder();
		MutantsInformationHolder.resetMainHolder();
		return ret;
	}
	
	/**
	 * Writes the given mutant into the given output. Will generate a clean
	 * java file that is the result of applying the given mutant identifier to
	 * the given source.
	 * 
	 * @param source the base file compilation unit. The given mutant will be
	 *     applied to this source
	 * @param mutant the information that uniquely identifies a mutant
	 * @param output the writer where to output the resulting java file
	 * @return the number of the line mutated
	 * @throws ParseTreeException
	 */
	public static int writeMutant(CompilationUnit source, MutantIdentifier mutant,
			PrintWriter output) throws ParseTreeException{
		usingApi = true;
		MutantIdentifierWriter writer = new MutantIdentifierWriter(source, output);
		return writer.write(mutant);
	}

	public static boolean usingApi() {
		return usingApi;
	}

	public static String getMethodUnderConsideration() {
		return methodToConsider;
	}

	
	public static boolean outputComments() {
		return outputComments;
	}

	/**
	 * If it is set to true then all comments of the original source file will
	 * be outputed when writing mutants.
	 */
	public static void setOutputComments(boolean value) {
		outputComments = value;
	}

}
