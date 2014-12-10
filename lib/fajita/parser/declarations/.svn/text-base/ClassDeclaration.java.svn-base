package parser.declarations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parser.JavaCodeParser;


public class ClassDeclaration implements Declaration {
	
	public static final String CLASS = "class|interface";
	
	private String classDeclaration;
	private String className;
	private Map<String, ClassDeclaration> innerClassDeclarations;
	private Map<String, List<MethodDeclaration>> methodsDeclarations;
	private List<AttributeDeclaration> attributeDeclarations;
	private List<StaticInitializationDeclaration> staticDeclarations;
	private List<EnumDeclaration> enumDeclarations;
	
	public ClassDeclaration(JavaCodeParser javaParser, String testMethod) {
		innerClassDeclarations = new HashMap<String, ClassDeclaration>();
		methodsDeclarations = new HashMap<String, List<MethodDeclaration>>();
		attributeDeclarations = new ArrayList<AttributeDeclaration>();
		staticDeclarations = new ArrayList<StaticInitializationDeclaration>();
		enumDeclarations = new ArrayList<EnumDeclaration>();
		
		className = javaParser.nextToken();
		javaParser.continueTo('{');
		classDeclaration = javaParser.flush();
		classDeclaration = classDeclaration.substring(0, classDeclaration.length() - 1);
		
		String lastToken = null;
		String token;
		while (!(token = javaParser.nextToken()).equals("}")) {
			if (token.matches(CLASS)) {
				ClassDeclaration innerClassDeclaration = new ClassDeclaration(javaParser, testMethod);
				innerClassDeclarations.put(innerClassDeclaration.getName(), innerClassDeclaration);
			} else if (token.matches(EnumDeclaration.ENUM)) {
				EnumDeclaration enumDeclaration = new EnumDeclaration(javaParser);
				enumDeclarations.add(enumDeclaration);
			} else if (token.matches(StaticInitializationDeclaration.STATIC)) {
				token = javaParser.nextToken();
				javaParser.goBackYourSelf(token);
				if (token.equals("{"))
					staticDeclarations.add(new StaticInitializationDeclaration(javaParser));
			}  else if (token.matches("=|;")) {
				attributeDeclarations.add(new AttributeDeclaration(javaParser));
			} else if (token.equals("(")) {
				if (!methodsDeclarations.containsKey(lastToken))
					methodsDeclarations.put(lastToken, new ArrayList<MethodDeclaration>());
				methodsDeclarations.get(lastToken).add(new MethodDeclaration(javaParser));
			}
			lastToken = token;
		}
		javaParser.flush();
	}
	
	public String getName() {
		return className;
	}
	
	public void addBooleanQueries(JavaCodeParser javaParser, String testMethod) {
		MethodDeclaration method = getMethodDeclaration(testMethod);
		if (method != null) {
			method.addMethodSpec("/**@Ensures false;*/");
			if (javaParser.getNumberOfConditions() == 0)
				method.addDummyCondition(javaParser);
		}
		
		for (int i = 0; i < javaParser.getNumberOfConditions(); i++)
			attributeDeclarations.add(new AttributeDeclaration.BQDeclaration(i));
	}
	
	@Override
	public String translate(int unroll) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(classDeclaration);
		stringBuilder.append("{\n");
		
		for (StaticInitializationDeclaration declaration : staticDeclarations)
			stringBuilder.append(declaration.translate(unroll));
		
		for (Declaration declaration : attributeDeclarations)
			stringBuilder.append(declaration.translate(unroll));
		
		for (String methodName : methodsDeclarations.keySet()) {
			for (MethodDeclaration methodDeclaration : methodsDeclarations.get(methodName))
				stringBuilder.append(methodDeclaration.translate(unroll));
		}
		
		for (Declaration declaration : innerClassDeclarations.values())
			stringBuilder.append(declaration.translate(unroll));
		
		for (Declaration declaration : enumDeclarations) {
			stringBuilder.append(declaration.translate(unroll));
		}
		
		stringBuilder.append("}\n");
		return stringBuilder.toString();
	}
	
	public MethodDeclaration getMethodDeclaration(String name) {
		MethodDeclaration result = null;
		int indexOfDot = name.indexOf('.');
		if (indexOfDot != -1) {
			String innerClassName = name.substring(0, indexOfDot);
			name = name.substring(indexOfDot + 1);
			ClassDeclaration innerClassDeclaration = innerClassDeclarations.get(innerClassName);
			if (innerClassDeclaration != null)
				result = innerClassDeclaration.getMethodDeclaration(name);
		} else {
			List<MethodDeclaration> overloadedMethods = methodsDeclarations.get(name);
			if (overloadedMethods != null)
				result = overloadedMethods.get(0); // FIXME: Consider method overloading.
		}
		return result;
	}
	
}
