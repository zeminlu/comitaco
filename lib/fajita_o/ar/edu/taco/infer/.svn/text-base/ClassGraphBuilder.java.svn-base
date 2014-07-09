package ar.edu.taco.infer;

import java.util.List;
import java.util.Set;

import ar.edu.jdynalloy.ast.JDynAlloyModule;
import ar.edu.jdynalloy.ast.JField;
import ar.edu.jdynalloy.ast.JProgramDeclaration;
import ar.edu.jdynalloy.ast.JVariableDeclaration;
import ar.edu.jdynalloy.xlator.JType;
import ar.edu.taco.TacoConfigurator;
import ar.edu.taco.simplejml.builtin.JThrowable;
import ar.edu.taco.simplejml.builtin.JavaPrimitiveFloatValue;
import ar.edu.taco.simplejml.builtin.JavaPrimitiveIntegerValue;
import ar.edu.taco.simplejml.builtin.JavaPrimitiveLongValue;

abstract class ClassGraphBuilder {

	public static Graph buildExtensionTree(List<JDynAlloyModule> src_jdynalloy_modules) {
		Graph tree = new Graph();
		for (JDynAlloyModule jdynalloy_module : src_jdynalloy_modules) {

			if (isJavaPrimitiveIntegerLiteral(jdynalloy_module)) {
				continue;
			}

			if (isJavaPrimitiveLongLiteral(jdynalloy_module)) {
				continue;
			}

			if (isJavaPrimitiveFloatLiteral(jdynalloy_module)) {
				continue;
			}

			if (isThrowableOrDescendant(jdynalloy_module)) {
				continue;
			}

			String node_src;
			if (jdynalloy_module.getSignature().getExtSigId() != null) {
				node_src = jdynalloy_module.getSignature().getExtSigId();
			} else {
				node_src = "$Root$";
			}
			String extension_sig_id = jdynalloy_module.getSignature().getSignatureId();
			tree.addEge(node_src, extension_sig_id, null);
		}
		return tree;
	}

	public static Graph buildClassGraph(List<JDynAlloyModule> src_jdynalloy_modules) {
		Graph graph = new Graph();

		for (JDynAlloyModule jdynalloy_module : src_jdynalloy_modules) {
			if (isJavaPrimitiveIntegerLiteral(jdynalloy_module)) {
				continue;
			}

			if (isJavaPrimitiveLongLiteral(jdynalloy_module)) {
				continue;
			}

			if (isJavaPrimitiveFloatLiteral(jdynalloy_module)) {
				continue;
			}

			if (isThrowableOrDescendant(jdynalloy_module)) {
				continue;
			}

			// Java fields

			String node_src = jdynalloy_module.getSignature().getSignatureId();
			if (node_src.equals("ClassFields")) {
				node_src = "$Root$";
			}

			for (JField field : jdynalloy_module.getFields()) {

				JType field_type = field.getFieldType();
				if (field_type.isBinaryRelation()) {

					
					if (field_type.isBinRelWithSeq()) {
						
						//throw new IllegalStateException("seq univ inference is unsupported.");


					} else {

						Set<String> target_signatures = field_type.to();

						for (String node_target : target_signatures) {
							if (node_target.equals("null")) {
								continue;
							}
							if (node_target.equals("boolean")) {
								continue;
							}
							String label_id = field.getFieldVariable().toString();

							graph.addEge(node_src, node_target, label_id);
						}
					}
				} else if (field_type.isTernaryRelation()) {
					// assume sequence
					String label_id = field.getFieldVariable().toString();
					JType object_array_type = JType.parse("(java_lang_ObjectArray)->((JavaPrimitiveIntegerValue) set -> lone (java_lang_Object+null))");
					if (field_type.equals(object_array_type)) {
						graph.addEge("java_lang_ObjectArray", "JavaPrimitiveIntegerValue", label_id);
						graph.addEge("JavaPrimitiveIntegerValue", "java_lang_Object", label_id);
					}

					JType int_array_type = JType.parse("(java_lang_IntArray)->((JavaPrimitiveIntegerValue) set -> lone (JavaPrimitiveIntegerValue))");
					if (field_type.equals(int_array_type)) {
						graph.addEge("java_lang_IntArray", "JavaPrimitiveIntegerValue", label_id);
						graph.addEge("JavaPrimitiveIntegerValue", "JavaPrimitiveIntegerValue", label_id);
					}

				}
			}

			// root types from arguments
			String class_to_check = TacoConfigurator.getInstance().getClassToCheck();
			String method_to_check = TacoConfigurator.getInstance().getMethodToCheck();
			if (jdynalloy_module.getSignature().getSignatureId().equals(class_to_check)) {
				for (JProgramDeclaration prog_declaration : jdynalloy_module.getPrograms()) {
					String program_id = prog_declaration.getProgramId();
					String qualified_program_id = class_to_check + "_" + program_id + "_";
					if (method_to_check.startsWith(qualified_program_id)) {

						for (JVariableDeclaration var_decl : prog_declaration.getParameters()) {
							String parameter_id = var_decl.getVariable().toString();
							if (parameter_id.equals("throw")) {
								continue;
							}
							if (parameter_id.equals("return")) {
								continue;
							}

							for (String node_target : var_decl.getType().from()) {
								if (node_target.equals("null")) {
									continue;
								}
								if (node_target.equals("boolean")) {
									continue;
								}
								graph.addEge("$Root$", node_target, parameter_id);
							}
						}

					}
				}
			}

		}

		return graph;
	}

	private static boolean isJavaPrimitiveIntegerLiteral(JDynAlloyModule jdynalloy_module) {
		String java_primitive_integer_value_sig = JavaPrimitiveIntegerValue.getInstance().getModule().getSignature().getSignatureId();
		String extSigId = jdynalloy_module.getSignature().getExtSigId();
		if (extSigId != null && extSigId.equals(java_primitive_integer_value_sig))
			return true;
		else
			return false;
	}

	private static boolean isJavaPrimitiveLongLiteral(JDynAlloyModule jdynalloy_module) {
		String java_primitive_long_value_sig = JavaPrimitiveLongValue.getInstance().getModule().getSignature().getSignatureId();
		String extSigId = jdynalloy_module.getSignature().getExtSigId();
		if (extSigId != null && extSigId.equals(java_primitive_long_value_sig))
			return true;
		else
			return false;
	}

	private static boolean isJavaPrimitiveFloatLiteral(JDynAlloyModule jdynalloy_module) {
		String java_primitive_float_value_sig = JavaPrimitiveFloatValue.getInstance().getModule().getSignature().getSignatureId();
		String extSigId = jdynalloy_module.getSignature().getExtSigId();
		if (extSigId != null && extSigId.equals(java_primitive_float_value_sig))
			return true;
		else
			return false;
	}

	private static boolean isThrowableOrDescendant(JDynAlloyModule jdynalloy_module) {
		if (jdynalloy_module.getLiteralSingleton() != null) {
			return true;
		}
		String throwable_sig_id = JThrowable.getInstance().getModule().getSignature().getSignatureId();
		if (jdynalloy_module.getSignature().getSignatureId().equals(throwable_sig_id)) {
			return true;
		}

		return false;
	}

}
