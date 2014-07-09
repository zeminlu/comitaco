package ar.edu.jdynalloy.ast;

import java.util.List;
import java.util.Set;

public final class JDynAlloyModule implements JDynAlloyASTNode {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((classSingleton == null) ? 0 : classSingleton.hashCode());
		result = prime * result + ((fields == null) ? 0 : fields.hashCode());
		result = prime
				* result
				+ ((object_invariants == null) ? 0 : object_invariants
						.hashCode());
		result = prime
				* result
				+ ((literalSingleton == null) ? 0 : literalSingleton.hashCode());
		result = prime * result
				+ ((moduleId == null) ? 0 : moduleId.hashCode());
		result = prime * result
				+ ((programs == null) ? 0 : programs.hashCode());
		result = prime * result
				+ ((represents == null) ? 0 : represents.hashCode());
		result = prime * result
				+ ((signature == null) ? 0 : signature.hashCode());
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
		JDynAlloyModule other = (JDynAlloyModule) obj;
		if (classSingleton == null) {
			if (other.classSingleton != null)
				return false;
		} else if (!classSingleton.equals(other.classSingleton))
			return false;
		if (fields == null) {
			if (other.fields != null)
				return false;
		} else if (!fields.equals(other.fields))
			return false;
		if (object_invariants == null) {
			if (other.object_invariants != null)
				return false;
		} else if (!object_invariants.equals(other.object_invariants))
			return false;
		if (literalSingleton == null) {
			if (other.literalSingleton != null)
				return false;
		} else if (!literalSingleton.equals(other.literalSingleton))
			return false;
		if (moduleId == null) {
			if (other.moduleId != null)
				return false;
		} else if (!moduleId.equals(other.moduleId))
			return false;
		if (programs == null) {
			if (other.programs != null)
				return false;
		} else if (!programs.equals(other.programs))
			return false;
		if (represents == null) {
			if (other.represents != null)
				return false;
		} else if (!represents.equals(other.represents))
			return false;
		if (signature == null) {
			if (other.signature != null)
				return false;
		} else if (!signature.equals(other.signature))
			return false;
		return true;
	}

	public JSignature getClassSingleton() {
		return classSingleton;
	}

	public void setClassSingleton(JSignature classSingleton) {
		this.classSingleton = classSingleton;
	}

	public JSignature getLiteralSingleton() {
		return literalSingleton;
	}

	public void setLiteralSingleton(JSignature literalSingleton) {
		this.literalSingleton = literalSingleton;
	}

	@Override
	public String toString() {
		return this.moduleId;
	}

	private final String moduleId;
	private final JSignature signature;
	private final Set<JProgramDeclaration> programs;
	private final List<JField> fields;

	private final Set<JClassInvariant> class_invariants;
	private final Set<JClassConstraint> class_constraints;

	private final Set<JObjectInvariant> object_invariants;
	private final Set<JObjectConstraint> object_constraints;
	private final Set<JRepresents> represents;

	private JSignature classSingleton;
	private JSignature literalSingleton;

	public JDynAlloyModule(String moduleId, 
			JSignature signature,
			JSignature class_singleton, 
			JSignature literal_signature,
			List<JField> fields, 
			Set<JClassInvariant> class_invariants,
			Set<JClassConstraint> class_constraints,
			Set<JObjectInvariant> object_invariants,
			Set<JObjectConstraint> object_constraints,
			Set<JRepresents> represents, 
			Set<JProgramDeclaration> programs) {
		super();
		this.moduleId = moduleId;
		this.signature = signature;
		this.classSingleton = class_singleton;
		this.literalSingleton = literal_signature;
		this.fields = fields;
		this.class_invariants = class_invariants;
		this.class_constraints = class_constraints;
		this.object_invariants = object_invariants;
		this.object_constraints = object_constraints;
		this.represents = represents;
		this.programs = programs;
	}

	public String getModuleId() {
		return moduleId;
	}

	public JSignature getSignature() {
		return signature;
	}

	public Set<JProgramDeclaration> getPrograms() {
		return programs;
	}

	public Object accept(IJDynAlloyVisitor visitor) {
		return visitor.visit(this);
	}

	public List<JField> getFields() {
		return fields;
	}

	public Set<JObjectInvariant> getObjectInvariants() {
		return object_invariants;
	}

	public Set<JObjectConstraint> getObjectConstraints() {
		return object_constraints;
	}

	public Set<JRepresents> getRepresents() {
		return represents;
	}

	public Set<JClassInvariant> getClassInvariants() {
		return class_invariants;
	}

	public Set<JClassConstraint> getClassConstraints() {
		return class_constraints;
	}

}
