package parser.declarations;

import parser.JavaCodeParser;

public class ImportDeclaration implements Declaration {
	
	public static final String IMPORT = "import";
	
	private String importDeclaration;
	
	public ImportDeclaration(JavaCodeParser javaParser) {
		javaParser.continueTo(';');
		importDeclaration = javaParser.flush();
	}
	
	@Override
	public String translate(int unroll) {
		return importDeclaration;
	}

}
