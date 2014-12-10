package parser.constructions;

import java.util.Map;
import java.util.Set;

import parser.JavaCodeParser;

public class ReturnConstruct implements Construct {

	public static final String RETURN = "return";
	
	private String returnStatement;
	
	public Construct parse(JavaCodeParser javaParser) {
		javaParser.continueTo(';');
		returnStatement = javaParser.flush();
		return this;
	}
	
	@Override
	public Object clone() {
		ReturnConstruct result = new ReturnConstruct();
		result.returnStatement = this.returnStatement;
		return result;
	}
	
	@Override
	public String translate(int unroll) {
		return returnStatement;
	}
	
	@Override
	public boolean getUncoveredFrontier(
			Map<Integer, Set<Boolean>> covered,
			Map<Integer, Set<Boolean>> frontier) {
		return true;
	}
	
	@Override
	public int getNumberOfBranches() {
		return 0;
	}
	
	@Override
	public int getNumberOfPaths(int unroll) {
		return 1;
	}
	
}
