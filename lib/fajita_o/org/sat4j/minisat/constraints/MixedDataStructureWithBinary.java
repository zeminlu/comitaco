/*******************************************************************************
* SAT4J: a SATisfiability library for Java Copyright (C) 2004-2008 Daniel Le Berre
*
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Alternatively, the contents of this file may be used under the terms of
* either the GNU Lesser General Public License Version 2.1 or later (the
* "LGPL"), in which case the provisions of the LGPL are applicable instead
* of those above. If you wish to allow use of your version of this file only
* under the terms of the LGPL, and not to allow others to use your version of
* this file under the terms of the EPL, indicate your decision by deleting
* the provisions above and replace them with the notice and other provisions
* required by the LGPL. If you do not delete the provisions above, a recipient
* may use your version of this file under the terms of the EPL or the LGPL.
* 
* Based on the original MiniSat specification from:
* 
* An extensible SAT solver. Niklas Een and Niklas Sorensson. Proceedings of the
* Sixth International Conference on Theory and Applications of Satisfiability
* Testing, LNCS 2919, pp 502-518, 2003.
*
* See www.minisat.se for the original solver in C++.
* 
*******************************************************************************/
package org.sat4j.minisat.constraints;

import org.sat4j.minisat.constraints.cnf.Clauses;
import org.sat4j.minisat.constraints.cnf.LearntBinaryClause;
import org.sat4j.minisat.constraints.cnf.LearntHTClause;
import org.sat4j.minisat.constraints.cnf.Lits2;
import org.sat4j.minisat.constraints.cnf.OriginalHTClause;
import org.sat4j.minisat.constraints.cnf.UnitClause;
import org.sat4j.minisat.core.Constr;
import org.sat4j.minisat.core.ILits2;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.IVecInt;

/**
 * @author leberre To change the template for this generated type comment go to
 *         Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MixedDataStructureWithBinary extends AbstractDataStructureFactory<ILits2> {

    private static final long serialVersionUID = 1L;

    @Override
    public ILits2 createLits() {
        return new Lits2();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.sat4j.minisat.DataStructureFactory#createClause(org.sat4j.datatype.VecInt)
     */
     public Constr createClause(IVecInt literals) throws ContradictionException {
        IVecInt v = Clauses.sanityCheck(literals, lits, solver);
        if (v == null)
            return null;
        if (v.size() == 2) {
            lits.binaryClauses(v.get(0), v.get(1));
            return null;
        }
        return OriginalHTClause.brandNewClause(solver, lits, v);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.sat4j.minisat.DataStructureFactory#learnContraint(org.sat4j.minisat.Constr)
     */
    @Override
    public void learnConstraint(Constr constr) {
        if (constr.size() == 2) {
            lits.binaryClauses(constr.get(0), constr.get(1));
            // solver.getStats().learnedbinaryclauses++;
        } else {
            super.learnConstraint(constr);
        }
    }

    public Constr createUnregisteredClause(IVecInt literals) {
    	if (literals.size()==1) {
    		return new UnitClause(literals.last());
    	}
    	if (literals.size() == 2) {
            return new LearntBinaryClause(literals,getVocabulary());
        }
        return new LearntHTClause(literals, getVocabulary());
    }
}
