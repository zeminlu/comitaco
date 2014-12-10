package parser;

import java.util.ArrayList;
import java.util.List;

import parser.declarations.ClassDeclaration;
import parser.declarations.Declaration;
import parser.declarations.ImportDeclaration;
import parser.declarations.MethodDeclaration;
import parser.declarations.PackageDeclaration;


public class JavaCodeStructure {
	
	private PackageDeclaration packageDeclaration;
	private List<ImportDeclaration> importDeclarations;
	private ClassDeclaration mainDeclaration;
	
	public JavaCodeStructure(JavaCodeParser javaParser) {
		this(javaParser, "");
	}
	
	public JavaCodeStructure(JavaCodeParser javaParser, String testMethod) {
		importDeclarations = new ArrayList<ImportDeclaration>();
		
		String token = javaParser.nextToken();
		
		if (token.matches(PackageDeclaration.PACKAGE)) {
			packageDeclaration = new PackageDeclaration(javaParser);
			token = javaParser.nextToken();
		}
		
		while (token.matches(ImportDeclaration.IMPORT)) {
			importDeclarations.add(new ImportDeclaration(javaParser));
			token = javaParser.nextToken();
		}
		
		boolean mainDeclaraionFound = false;
		while (!mainDeclaraionFound) {
			if (token.matches(ClassDeclaration.CLASS)) {
				mainDeclaraionFound = true;
				mainDeclaration = new ClassDeclaration(javaParser, testMethod);
				mainDeclaration.addBooleanQueries(javaParser, testMethod);
			} else {
				token = javaParser.nextToken();
			}
		}
	}
	
	public String translate(int unroll) {
		StringBuilder stringBuilder = new StringBuilder();
		
		if (packageDeclaration != null)
			stringBuilder.append(packageDeclaration.translate(unroll));
		
		if (importDeclarations != null)
			for (Declaration declaration : importDeclarations)
				stringBuilder.append(declaration.translate(unroll));
		
		stringBuilder.append(mainDeclaration.translate(unroll));
		
		return stringBuilder.toString();
	}
	
	public MethodDeclaration getMethodDeclaration(String methodName) {
		String qualifiedName = mainDeclaration.getName();
		if (packageDeclaration != null)
			qualifiedName = packageDeclaration.getPackageName() + "." + qualifiedName ;
		if (methodName.startsWith(qualifiedName))
			methodName = methodName.replace(qualifiedName + ".", "");
		return mainDeclaration.getMethodDeclaration(methodName);
	}
	
}
