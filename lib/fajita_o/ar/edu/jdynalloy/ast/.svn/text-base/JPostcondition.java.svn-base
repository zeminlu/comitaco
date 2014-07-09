package ar.edu.jdynalloy.ast;

import ar.uba.dc.rfm.alloy.ast.formulas.AlloyFormula;

public class JPostcondition {

	public JPostcondition(AlloyFormula formula) {
		super();
		this.formula = formula;
	}

	private final AlloyFormula formula;

	public AlloyFormula getFormula() {
		return formula;
	}

	public Object accept(IJDynAlloyVisitor visitor) {
		return visitor.visit(this);
	}
	
	
}
