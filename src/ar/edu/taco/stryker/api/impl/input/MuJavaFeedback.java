package ar.edu.taco.stryker.api.impl.input;

import java.util.Arrays;

import mujava.app.Mutator;

public class MuJavaFeedback {

    /*
     *  -Vector de cantidad de mutaciones aplicadas por línea. Es la clase de equivalencia de ese input.
     *  -Vector de índice de mutaciones aplicado por línea en este input
     *  -Matriz (vector de vectores) de mutadores por línea para ese input
     *  -Feedback Line Number
     */
    
    private Integer mutateUntilLine = null;
    private int[] lineMutationIndexes;
    private int[] mutationEquivalenceClass;
    private Mutator[][] lineMutatorsList;
    
    public MuJavaFeedback(int[] lineMutationIndexes, int[] mutationEquivalenceClass, Mutator[][] lineMutatorsList) {
        super();
        this.lineMutationIndexes = lineMutationIndexes;
        this.mutationEquivalenceClass = mutationEquivalenceClass;
        this.lineMutatorsList = lineMutatorsList;
    }

    public Integer getMutateUntilLine() {
        return mutateUntilLine;
    }
    
    public void setMutateUntilLine(Integer mutateUntilLine) {
        this.mutateUntilLine = mutateUntilLine;
    }
    
    public int[] getLineMutationIndexes() {
        return lineMutationIndexes;
    }
    
    public Mutator[][] getLineMutatorsList() {
        return lineMutatorsList;
    }
    
    public void setLineMutationIndexes(int[] lineMutationIndexes) {
        this.lineMutationIndexes = lineMutationIndexes;
    }
    
    public void setLineMutatorsList(Mutator[][] lineMutatorsList) {
        this.lineMutatorsList = lineMutatorsList;
    }
    
    public int[] getMutationEquivalenceClass() {
        return mutationEquivalenceClass;
    }
    
    public void setMutationEquivalenceClass(int[] mutationEquivalenceClass) {
        this.mutationEquivalenceClass = mutationEquivalenceClass;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(lineMutationIndexes);
        result = prime * result + Arrays.hashCode(lineMutatorsList);
        result = prime * result + ((mutateUntilLine == null) ? 0 : mutateUntilLine.hashCode());
        result = prime * result + Arrays.hashCode(mutationEquivalenceClass);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        MuJavaFeedback other = (MuJavaFeedback) obj;
        if (!Arrays.equals(lineMutationIndexes, other.lineMutationIndexes)) return false;
        if (!Arrays.deepEquals(lineMutatorsList, other.lineMutatorsList)) return false;
        if (mutateUntilLine == null) {
            if (other.mutateUntilLine != null) return false;
        } else if (!mutateUntilLine.equals(other.mutateUntilLine)) return false;
        if (!Arrays.equals(mutationEquivalenceClass, other.mutationEquivalenceClass)) return false;
        return true;
    }

}
