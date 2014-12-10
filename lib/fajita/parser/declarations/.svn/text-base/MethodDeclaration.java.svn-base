package parser.declarations;

import java.util.Map;
import java.util.Set;

import parser.JavaCodeParser;
import parser.constructions.BlockConstruct;
import parser.constructions.ConditionalConstruct;
import parser.constructions.Construct;

public class MethodDeclaration implements Declaration {

	private String methodDeclaration;
	private Construct methodBody;

	public MethodDeclaration(JavaCodeParser javaParser) {
		javaParser.continueTo(')');
		String token = javaParser.nextToken();
		if (token.equals("throws")) {
			javaParser.continueTo('{');
			javaParser.goBackYourSelf("}");
		} else {
			javaParser.goBackYourSelf(token);
		}
		methodDeclaration = javaParser.flush();
		methodBody = new BlockConstruct().parse(javaParser);
	}
	
	@Override
	public String translate(int unroll) {
		return methodDeclaration + methodBody.translate(unroll);
	}
	
	public void getUncoveredFrontier(Map<Integer, Set<Boolean>> covered, Map<Integer, Set<Boolean>> uncoveredFrontier) {
		methodBody.getUncoveredFrontier(covered, uncoveredFrontier);
	}
	
	public void addMethodSpec(String spec) {
		int specIndex = methodDeclaration.indexOf("/**");
		if (specIndex != -1)
			methodDeclaration = methodDeclaration.substring(specIndex, methodDeclaration.indexOf("*/"));
		methodDeclaration = spec + methodDeclaration;
	}
	
	public void addDummyCondition(JavaCodeParser javaParser) {
		((BlockConstruct)methodBody).addFirst(
			new ConditionalConstruct.DummyConditionalConstruct(javaParser));
	}
	
	public int getNumberOfBranches() {
		return methodBody.getNumberOfBranches();
	}
	
	public int getNumberOfPaths(int unroll) {
		return methodBody.getNumberOfPaths(unroll);
	}
	
}
