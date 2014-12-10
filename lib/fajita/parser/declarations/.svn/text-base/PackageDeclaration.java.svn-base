package parser.declarations;

import parser.JavaCodeParser;

public class PackageDeclaration implements Declaration {
	
	public static final String PACKAGE  = "package";
	
	private String packageDeclaration;
	private String packageName;
	
	public PackageDeclaration(JavaCodeParser javaParser) {
		packageName = javaParser.nextToken();
		if (!javaParser.nextToken().equals(";")) System.err.println("Expected ;");
		packageDeclaration = javaParser.flush();
	}
	
	public String getPackageName() {
		return packageName;
	}
	
	@Override
	public String translate(int unroll) {
		return packageDeclaration;
	}

}
