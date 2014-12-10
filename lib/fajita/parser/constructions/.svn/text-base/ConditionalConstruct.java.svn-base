package parser.constructions;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import parser.JavaCodeParser;

public class ConditionalConstruct implements Construct {

	public static final String IF = "if";
	public static final String ELSE = "else";
	
	protected String conditionalStatement;
	protected Construct conditionalBody;
	protected Construct elseBody;
	protected int condition;
	
	public Construct parse(JavaCodeParser javaParser) {
		condition = javaParser.addCondition(this);
		
		String token = javaParser.nextToken();
		if (!token.equals("(")) System.err.println("Expected (");
		javaParser.continueTo(')');
		conditionalStatement = javaParser.flush();
		
		conditionalBody = new BlockConstruct().parse(javaParser);
		
		token = javaParser.nextToken();
		if (token.matches(ELSE)) {
			javaParser.flush();
			elseBody = new BlockConstruct().parse(javaParser);
		} else {
			javaParser.goBackYourSelf(token);
		}
		return this;
	}
	
	@Override
	public Object clone() {
		ConditionalConstruct result = new ConditionalConstruct();
		result.conditionalStatement = this.conditionalStatement;
		result.conditionalBody = (Construct)this.conditionalBody.clone();
		if (this.elseBody != null)
			result.elseBody = (Construct)this.elseBody.clone();
		result.condition = this.condition;
		return result;
	}
	
	@Override
	public String translate(int unroll) {
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append(conditionalStatement);
		stringBuilder.append('{');
		stringBuilder.append(JavaCodeParser.CONDITION + condition + " = true;");
		stringBuilder.append(conditionalBody.translate(unroll));
		stringBuilder.append('}');
		
		stringBuilder.append(ELSE);
		stringBuilder.append('{');
		stringBuilder.append(JavaCodeParser.CONDITION + condition + " = false;");
		if (elseBody != null)
			stringBuilder.append(elseBody.translate(unroll));
		stringBuilder.append('}');
		
		return stringBuilder.toString();
	}
	
	@Override
	public boolean getUncoveredFrontier(
			Map<Integer, Set<Boolean>> covered,
			Map<Integer, Set<Boolean>> frontier) {
		
		boolean stop = true;
		
		Set<Boolean> localFrontier = new TreeSet<Boolean>();
		Set<Boolean> branch = covered.get(condition);
		
		if (elseBody == null) {
			
			if (branch == null) {
				localFrontier.add(true);
				localFrontier.add(false);
			} else if (branch.contains(true) && branch.contains(false)) {
				conditionalBody.getUncoveredFrontier(covered, frontier);
				stop = false;
			} else if (branch.contains(true) && !branch.contains(false)) {
				localFrontier.add(false);
				stop = conditionalBody.getUncoveredFrontier(covered, frontier);
			} else {
				localFrontier.add(true);
				stop = false;
			}
			
		} else {
			
			if (branch == null) {
				localFrontier.add(true);
				localFrontier.add(false);
			} else if (branch.contains(true) && branch.contains(false)) {
				stop =
					conditionalBody.getUncoveredFrontier(covered, frontier) &&
					elseBody.getUncoveredFrontier(covered, frontier);
			} else if (branch.contains(true) && !branch.contains(false)) {
				localFrontier.add(false);
				stop = conditionalBody.getUncoveredFrontier(covered, frontier);
			} else {
				localFrontier.add(true);
				stop = elseBody.getUncoveredFrontier(covered, frontier);
			}
			
		}
		
		if (!localFrontier.isEmpty()) {
			Set<Boolean> previous = frontier.get(condition);
			if (previous != null)
				localFrontier.addAll(previous);
			frontier.put(condition, localFrontier);
		}
		
		return stop;
	}
	
	@Override
	public int getNumberOfBranches() {
		int branches = 2;
		branches += conditionalBody.getNumberOfBranches();
		if (elseBody != null)
			branches += elseBody.getNumberOfBranches();
		return branches;
	}
	
	@Override
	public int getNumberOfPaths(int unroll) {
		int paths = conditionalBody.getNumberOfPaths(unroll);
		paths += elseBody != null ? elseBody.getNumberOfPaths(unroll) : 1;
		return paths;
	}
	
	public static class DummyConditionalConstruct extends ConditionalConstruct {
		
		public DummyConditionalConstruct(JavaCodeParser javaParser) {
			conditionalStatement = "if(true)";
			conditionalBody = new BlockConstruct();
			condition = javaParser.getNumberOfConditions();
			javaParser.addCondition(this);
		}
		
	}
	
}
