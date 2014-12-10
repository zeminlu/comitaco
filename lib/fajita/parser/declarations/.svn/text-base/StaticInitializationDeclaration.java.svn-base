package parser.declarations;

import parser.JavaCodeParser;
import parser.constructions.BlockConstruct;
import parser.constructions.Construct;

public class StaticInitializationDeclaration implements Declaration {

	public static final String STATIC = "static";
	
	private String staticDeclaration;
	private Construct staticInitialization;
	
	public StaticInitializationDeclaration(JavaCodeParser javaParser) {
		staticDeclaration = javaParser.flush();
		staticInitialization = new BlockConstruct().parse(javaParser);
	}
	
	@Override
	public String translate(int unroll) {
		return staticDeclaration + staticInitialization.translate(unroll);
	}

}
