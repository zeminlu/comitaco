package parser.declarations;

import parser.JavaCodeParser;

public class EnumDeclaration implements Declaration {

	public static final String ENUM = "enum";
	
	private String enumDeclaration;
	
	public EnumDeclaration(JavaCodeParser javaParser) {
		System.err.println("Coverage inside enums is not supported.");
		javaParser.continueTo('{');
		javaParser.continueTo('}');
		enumDeclaration = javaParser.flush();
	}
	
	@Override
	public String translate(int unroll) {
		// FIXME: There may be loops and conditions inside the enums,
		//	but its difficult to parse because of the comma separated identifiers.
		return enumDeclaration;
	}

}
