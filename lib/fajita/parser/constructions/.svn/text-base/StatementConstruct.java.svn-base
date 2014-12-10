package parser.constructions;

import java.util.Map;
import java.util.Set;

import parser.JavaCodeParser;

public class StatementConstruct implements Construct {

	private String statement;
	
	public StatementConstruct() {}
	
	public StatementConstruct(String statement) {
		this.statement = statement;
	}
	
	public Construct parse(JavaCodeParser javaParser) {
		statement = javaParser.flush();
		if (!statement.endsWith(";")) {
			javaParser.continueTo(';');
			statement += javaParser.flush();
		}
		if (statement.contains("roops.util.Goal")) // FIXME: Quick fix to avoid having to include roops.utils in sample compilation (avoids binding error 5001)
			statement = "";
		return this;
	}
	
	@Override
	public Object clone() {
		StatementConstruct result = new StatementConstruct();
		result.statement = this.statement;
		return result;
	}
	
	@Override
	public String translate(int unroll) {
		return statement;
	}

	@Override
	public boolean getUncoveredFrontier(
			Map<Integer, Set<Boolean>> covered,
			Map<Integer, Set<Boolean>> frontier) {
		return false;
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
