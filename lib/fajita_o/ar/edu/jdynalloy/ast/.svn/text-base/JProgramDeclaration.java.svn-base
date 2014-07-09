package ar.edu.jdynalloy.ast;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ar.edu.jdynalloy.factory.JExpressionFactory;

public final class JProgramDeclaration implements JDynAlloyASTNode {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isAbstract ? 1231 : 1237);
		result = prime * result + ((parameters == null) ? 0 : parameters.hashCode());
		result = prime * result + ((programId == null) ? 0 : programId.hashCode());
		result = prime * result + ((signatureId == null) ? 0 : signatureId.hashCode());
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
		JProgramDeclaration other = (JProgramDeclaration) obj;
		if (isAbstract != other.isAbstract)
			return false;
		if (parameters == null) {
			if (other.parameters != null)
				return false;
		} else if (!parameters.equals(other.parameters))
			return false;
		if (programId == null) {
			if (other.programId != null)
				return false;
		} else if (!programId.equals(other.programId))
			return false;
		if (signatureId == null) {
			if (other.signatureId != null)
				return false;
		} else if (!signatureId.equals(other.signatureId))
			return false;
		return true;
	}

	public boolean isAbstract() {
		return isAbstract;
	}

	public void setSpecCases(List<JSpecCase> specCases) {
		this.specCases = specCases;
	}

	public List<JSpecCase> getSpecCases() {
		return specCases;
	}

	private final String programId;

	private final List<JVariableDeclaration> parameters;

	private final JStatement body;

	private final String signatureId;

	private final boolean isAbstract;

	private List<JSpecCase> specCases;

	public static JProgramDeclaration buildJProgramDeclaration(
			boolean isAbstract, String signatureId, String programId,
			List<JVariableDeclaration> parameters, Set<JPrecondition> requires,
			Set<JModifies> modifies, Set<JPostcondition> ensures, JStatement body) {

		List<JSpecCase> specCasesList;
		if (requires.isEmpty() && ensures.isEmpty()) {
			specCasesList = new LinkedList<JSpecCase>();
		} else {

			JSpecCase specCase = new JSpecCase(new LinkedList<JPrecondition>(
					requires), new LinkedList<JPostcondition>(ensures),
					new LinkedList<JModifies>(modifies));
			specCasesList = Collections.singletonList(specCase);
		}
		return new JProgramDeclaration(isAbstract, signatureId, programId,
				parameters, specCasesList, body);

	}

	public JProgramDeclaration(boolean isAbstract, String signatureId,
			String programId, List<JVariableDeclaration> parameters,
			List<JSpecCase> specCases, JStatement body) {
		super();
		this.isAbstract = isAbstract;
		this.signatureId = signatureId;
		this.programId = programId;
		this.parameters = parameters;
		this.specCases = specCases;
		this.body = body;
	}

	public String getProgramId() {
		return programId;
	}

	public List<JVariableDeclaration> getParameters() {
		return parameters;
	}

	public JStatement getBody() {
		return body;
	}

	public Object accept(IJDynAlloyVisitor visitor) {
		return visitor.visit(this);
	}

	public String getSignatureId() {
		return signatureId;
	}

	public boolean isVirtual() {
		return isAbstract;
	}

	@Override
	public String toString() {
		return this.signatureId + "::" + this.programId + "["
				+ this.parameters.toString() + "]" + "{...}";
	}

	public boolean isStatic() {
	    if (this.parameters.get(0).getVariable().equals(JExpressionFactory.THROW_VARIABLE)) {
		return true;
	    }
	    return false;
	}

}
