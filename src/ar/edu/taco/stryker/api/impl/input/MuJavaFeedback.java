package ar.edu.taco.stryker.api.impl.input;

import java.util.Arrays;
import java.util.List;

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
    
    private Integer skipUntilMutID = null;
    private Integer[] lineMutationIndexes;
    private List<Integer> lastMutatedLines;
    private MutantIdentifier[][] lineMutatorsList;
    private int fatherIndex;
    private MutantsInformationHolder mutantsInformationHolder;
    private Mutator mut;
    private boolean fatherable;
    private boolean mutateRight;
    
    public MuJavaFeedback(Integer[] lineMutationIndexes, MutantIdentifier[][] lineMutatorsList, List<Integer> lastMutatedLines) {
        super();
        if (lineMutationIndexes.length > lineMutatorsList.length) {
            System.out.println("PROBLEMMMM");
        }
        this.lineMutationIndexes = lineMutationIndexes;
        this.lineMutatorsList = lineMutatorsList;
        this.fatherable = true;
        this.lastMutatedLines = lastMutatedLines;
    }

    public Integer getSkipUntilMutID() {
        return skipUntilMutID;
    }
    
    public void setSkipUntilMutID(Integer skipUntilMutID) {
        this.skipUntilMutID = skipUntilMutID;
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
    
    public List<Integer> getLastMutatedLines() {
        return lastMutatedLines;
    }
    
    public boolean isMutateRight() {
        return mutateRight;
    }
    
    public void setMutateRight(boolean mutateRight) {
        this.mutateRight = mutateRight;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + fatherIndex;
        result = prime * result + (fatherable ? 1231 : 1237);
        result = prime * result + ((lastMutatedLines == null) ? 0 : lastMutatedLines.hashCode());
        result = prime * result + Arrays.hashCode(lineMutationIndexes);
        result = prime * result + Arrays.hashCode(lineMutatorsList);
        result = prime * result + (mutateRight ? 1231 : 1237);
        result = prime * result + ((skipUntilMutID == null) ? 0 : skipUntilMutID.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        MuJavaFeedback other = (MuJavaFeedback) obj;
        if (fatherIndex != other.fatherIndex) return false;
        if (fatherable != other.fatherable) return false;
        if (lastMutatedLines == null) {
            if (other.lastMutatedLines != null) return false;
        } else if (!lastMutatedLines.equals(other.lastMutatedLines)) return false;
        if (!Arrays.equals(lineMutationIndexes, other.lineMutationIndexes)) return false;
        if (!Arrays.deepEquals(lineMutatorsList, other.lineMutatorsList)) return false;
        if (mutateRight != other.mutateRight) return false;
        if (skipUntilMutID == null) {
            if (other.skipUntilMutID != null) return false;
        } else if (!skipUntilMutID.equals(other.skipUntilMutID)) return false;
        return true;
    }
    
}
