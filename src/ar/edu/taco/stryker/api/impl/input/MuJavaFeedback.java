package ar.edu.taco.stryker.api.impl.input;

import java.util.Arrays;

import mujava.api.MutantIdentifier;
import mujava.api.MutantsInformationHolder;
import mujava.app.Mutator;

public class MuJavaFeedback {

    /*
     *  -Vector de cantidad de mutaciones aplicadas por línea. Es la clase de equivalencia de ese input.
     *  -Vector de índice de mutaciones aplicado por línea en este input
     *  -Matriz (vector de vectores) de mutadores por línea para ese input
     *  -Feedback Line Number
     */
    
    private Integer mutateUntilLine = null;
    private Integer[] lineMutationIndexes;
    private MutantIdentifier[][] lineMutatorsList;
    private int fatherIndex;
    private MutantsInformationHolder mutantsInformationHolder;
    private Mutator mut;
    private boolean fatherable;
    
    public MuJavaFeedback(Integer[] lineMutationIndexes, MutantIdentifier[][] lineMutatorsList) {
        super();
        this.lineMutationIndexes = lineMutationIndexes;
        this.lineMutatorsList = lineMutatorsList;
        this.fatherable = true;
    }

    public Integer getMutateUntilLine() {
        return mutateUntilLine;
    }
    
    public void setMutateUntilLine(Integer mutateUntilLine) {
        this.mutateUntilLine = mutateUntilLine;
    }
    
    public Integer[] getLineMutationIndexes() {
        return lineMutationIndexes;
    }
    
    public MutantIdentifier[][] getLineMutatorsList() {
        return lineMutatorsList;
    }
    
    public void setLineMutationIndexes(Integer[] lineMutationIndexes) {
        this.lineMutationIndexes = lineMutationIndexes;
    }
    
    public void setLineMutatorsList(MutantIdentifier[][] lineMutatorsList) {
        this.lineMutatorsList = lineMutatorsList;
    }
    
    public int getFatherIndex() {
        return fatherIndex;
    }
    
    public void setFatherIndex(int fatherIndex) {
        this.fatherIndex = fatherIndex;
    }
    
    public Mutator getMut() {
        return mut;
    }
    
    public void setMut(Mutator mut) {
        this.mut = mut;
    }
    
    public MutantsInformationHolder getMutantsInformationHolder() {
        return mutantsInformationHolder;
    }
    
    public void setMutantsInformationHolder(MutantsInformationHolder mutantsInformationHolder) {
        this.mutantsInformationHolder = mutantsInformationHolder;
    }

    public boolean isFatherable() {
        return fatherable;
    }
    
    public void setFatherable(boolean fatherable) {
        this.fatherable = fatherable;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + fatherIndex;
        result = prime * result + Arrays.hashCode(lineMutationIndexes);
        result = prime * result + Arrays.hashCode(lineMutatorsList);
        result = prime * result + ((mut == null) ? 0 : mut.hashCode());
        result = prime * result
                + ((mutantsInformationHolder == null) ? 0 : mutantsInformationHolder.hashCode());
        result = prime * result + ((mutateUntilLine == null) ? 0 : mutateUntilLine.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        MuJavaFeedback other = (MuJavaFeedback) obj;
        if (fatherIndex != other.fatherIndex) return false;
        if (!Arrays.equals(lineMutationIndexes, other.lineMutationIndexes)) return false;
        if (!Arrays.deepEquals(lineMutatorsList, other.lineMutatorsList)) return false;
        if (mut == null) {
            if (other.mut != null) return false;
        } else if (!mut.equals(other.mut)) return false;
        if (mutantsInformationHolder == null) {
            if (other.mutantsInformationHolder != null) return false;
        } else if (!mutantsInformationHolder.equals(other.mutantsInformationHolder)) return false;
        if (mutateUntilLine == null) {
            if (other.mutateUntilLine != null) return false;
        } else if (!mutateUntilLine.equals(other.mutateUntilLine)) return false;
        return true;
    }

}
