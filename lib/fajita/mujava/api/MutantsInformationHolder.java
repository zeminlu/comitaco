package mujava.api;

import java.util.ArrayList;
import java.util.List;

import openjava.ptree.CompilationUnit;
import openjava.ptree.ParseTreeObject;

/**
 * This is the information returned by the generateMutants method in the Api
 * class. It holds all the  mutation operations applicable to the original
 * source passed as parameter to the generateMutants method
 * @See {@link MutantIdentifier}
 */
public class MutantsInformationHolder {

	private static MutantsInformationHolder mainHolder =
			new MutantsInformationHolder();
	
	private List<MutantIdentifier> mutantsIdentifiers =
			new ArrayList<MutantIdentifier>();
	
	private CompilationUnit compUnit;
	
	public void setCompilationUnit(CompilationUnit compUnit) {
		this.compUnit = compUnit;
	}

	public List<MutantIdentifier> getMutantsIdentifiers() {
		return mutantsIdentifiers;
	}

	public CompilationUnit getCompUnit() {
		return compUnit;
	}

	public void addMutantIdentifier(Mutant mutOp, ParseTreeObject original) {
		mutantsIdentifiers.add(new MutantIdentifier(mutOp, original));
	}

	public void addMutantIdentifier(Mutant mutOp,
			ParseTreeObject original, Object mutant) {
		mutantsIdentifiers.add(new MutantIdentifier(mutOp, original, mutant));
	}
	
	public void addMutantIdentifier(Mutant mutOp,
			ParseTreeObject original, Object mutant, Object additionalInformation) {
		mutantsIdentifiers.add(new MutantIdentifier(mutOp, original, mutant,
				additionalInformation));
	}
	
	public void clear() {
		mutantsIdentifiers = new ArrayList<MutantIdentifier>();
		compUnit = null;
	}
	
	public static MutantsInformationHolder mainHolder() {
		return mainHolder;
	}

	public static void resetMainHolder() {
		mainHolder = new MutantsInformationHolder();
	}

	@Override
	public String toString() {
		return "MutantsInformationHolder [mutantsIdentifiers="
				+ mutantsIdentifiers + ", compUnit=" +
				((compUnit != null) ? compUnit.hashCode() : "null") + "]";
	}
	
}
