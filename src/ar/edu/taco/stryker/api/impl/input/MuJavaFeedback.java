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
    private List<Integer> mutableLines = null;
    private boolean fatherable;
    private boolean mutateRight;
    private boolean getSibling = true;
    private boolean UNSAT = false;
    
    public MuJavaFeedback(Integer[] lineMutationIndexes, MutantIdentifier[][] lineMutatorsList, List<Integer> lastMutatedLines, List<Integer> mutableLines) {
        super();
        if (lineMutationIndexes.length > lineMutatorsList.length) {
            System.out.println("PROBLEMMMM");
        }
        this.lineMutationIndexes = lineMutationIndexes;
        this.lineMutatorsList = lineMutatorsList;
        this.fatherable = true;
        this.lastMutatedLines = lastMutatedLines;
        this.mutableLines = mutableLines;
    }

    public List<Integer> getMutableLines() {
        return mutableLines;
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
    
    public boolean isUNSAT() {
        return UNSAT;
    }
    
    public void setUNSAT(boolean uNSAT) {
        UNSAT = uNSAT;
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
    
    public void setLastMutatedLines(List<Integer> lastMutatedLines) {
        this.lastMutatedLines = lastMutatedLines;
    }
    
    public boolean isMutateRight() {
        return mutateRight;
    }
    
    public void setMutateRight(boolean mutateRight) {
        this.mutateRight = mutateRight;
    }
    
    public boolean isGetSibling() {
        return getSibling;
    }
    
    public void setGetSibling(boolean getSibling) {
        this.getSibling = getSibling;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + fatherIndex;
        result = prime * result + ((lastMutatedLines == null) ? 0 : lastMutatedLines.hashCode());
        result = prime * result + Arrays.hashCode(lineMutationIndexes);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        MuJavaFeedback other = (MuJavaFeedback) obj;
        if (fatherIndex != other.fatherIndex) return false;
        if (lastMutatedLines == null) {
            if (other.lastMutatedLines != null) return false;
        } else if (!lastMutatedLines.equals(other.lastMutatedLines)) return false;
        if (!Arrays.equals(lineMutationIndexes, other.lineMutationIndexes)) return false;
        return true;
    }

}
