package parser.constructions;

import java.util.Map;
import java.util.Set;

import parser.JavaCodeParser;

public class SelectConstruct implements Construct {

	public static final String SWITCH = "switch";
	
	private String selectDeclaration;
	private Construct selectBody;

	@Override
	public Construct parse(JavaCodeParser javaParser) {
		String token = javaParser.nextToken();
		if (!token.equals("(")) System.err.println("Expected (");
		javaParser.continueTo(')');
		selectDeclaration = javaParser.flush();
		selectBody = new BlockConstruct().parse(javaParser);
		return this;
	}

	@Override
	public String translate(int unroll) {
		return selectDeclaration + selectBody.translate(unroll);
	}

	@Override
	public boolean getUncoveredFrontier(
			Map<Integer, Set<Boolean>> covered,
			Map<Integer, Set<Boolean>> frontier) {
		return selectBody.getUncoveredFrontier(covered, frontier);
	}
	
	@Override
	public Object clone() {
		SelectConstruct selectConstruct = new SelectConstruct();
		selectConstruct.selectDeclaration = this.selectDeclaration;
		selectConstruct.selectBody = (Construct)this.selectBody.clone();
		return selectConstruct;
	}
	
	@Override
	public int getNumberOfBranches() {
		return selectBody.getNumberOfBranches();
	}
	
	@Override
	public int getNumberOfPaths(int unroll) {
		return selectBody.getNumberOfPaths(unroll);
	}
	
	public static class CaseConstruct implements Construct {

		public static final String CASE = "case|default";
		
		private String caseDeclaration;
		
		@Override
		public Construct parse(JavaCodeParser javaParser) {
			javaParser.continueTo(':');
			caseDeclaration = javaParser.flush();
			return this;
		}

		@Override
		public String translate(int unroll) {
			return caseDeclaration;
		}

		@Override
		public boolean getUncoveredFrontier(
				Map<Integer, Set<Boolean>> covered,
				Map<Integer, Set<Boolean>> frontier) {
			return false;
		}
		
		@Override
		public Object clone() {
			CaseConstruct caseConstruct = new CaseConstruct();
			caseConstruct.caseDeclaration = this.caseDeclaration;
			return caseConstruct;
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
	
}
