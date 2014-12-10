package parser.declarations;

import parser.JavaCodeParser;

public class AttributeDeclaration implements Declaration {

	protected String attributeDeclaration;
	
	private AttributeDeclaration(){}
	
	public AttributeDeclaration(JavaCodeParser javaParser) {
		attributeDeclaration = javaParser.flush();
		if (!attributeDeclaration.endsWith(";")) {
			javaParser.continueTo(';');
			attributeDeclaration += javaParser.flush();
		}
	}
	
	@Override
	public String translate(int unroll) {
		return attributeDeclaration + "\n";
	}
	
	public static class BQDeclaration extends AttributeDeclaration {
		
		public BQDeclaration(int conditionNumber) {
			attributeDeclaration = "static boolean " + JavaCodeParser.CONDITION + conditionNumber + ";";
		}
		
	}
	
}
