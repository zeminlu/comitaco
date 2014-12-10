package mujava.api;

import openjava.ptree.ParseTreeObject;

/**
 * Uniquely identifies a mutation operation
 */
public class MutantIdentifier {

	// The mutant operator
	private Mutant mutOp;

	// The original object from the parse tree to be mutated, the mutant and
	// some additional information that is necessary for some mutant operators
	private Object original, mutant, additionalInfo;

	public MutantIdentifier(Mutant mutOp, Object original, Object mutant, Object additionalInfo) {
		this.mutOp = mutOp;
		this.original = original;
		this.mutant = mutant;
		this.additionalInfo = additionalInfo;
	}

	public MutantIdentifier(Mutant mutOp, Object original, Object mutant) {
		this(mutOp, original, mutant, null);
	}

	public MutantIdentifier(Mutant mutOp, ParseTreeObject original) {
		this(mutOp, original, null, null);
	}

	public Mutant getMutOp() {
		return mutOp;
	}

	public Object getOriginal() {
		return original;
	}

	public Object getMutant() {
		return mutant;
	}

	public Object getAdditionalInfo() {
		return additionalInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((additionalInfo == null) ? 0 : additionalInfo.hashCode());
		result = prime * result + ((mutOp == null) ? 0 : mutOp.hashCode());
		result = prime * result + ((mutant == null) ? 0 : mutant.hashCode());
		result = prime * result
				+ ((original == null) ? 0 : original.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MutantIdentifier other = (MutantIdentifier) obj;
		if (additionalInfo == null) {
			if (other.additionalInfo != null)
				return false;
		} else if (!additionalInfo.equals(other.additionalInfo))
			return false;
		if (mutOp == null) {
			if (other.mutOp != null)
				return false;
		} else if (!mutOp.equals(other.mutOp))
			return false;
		if (mutant == null) {
			if (other.mutant != null)
				return false;
		} else if (!mutant.equals(other.mutant))
			return false;
		if (original == null) {
			if (other.original != null)
				return false;
		} else if (!original.equals(other.original))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "(" + mutOp + ", " + original + ", " + mutant + ")";
	}
}
