package parser.constructions;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import parser.JavaCodeParser;

public class LoopConstruct implements Construct {

	public static final String WHILE = "while";
	public static final String FOR = "for";
	public static final String DO = "do";
	public static final String LOOP = WHILE + "|" + FOR + "|" + DO;
	
	private String loopStatement;
	private String loopKeyword;
	private String loopGuard;
	private Construct loopBody;
	private int condition;
	
	public Construct parse(JavaCodeParser javaParser) {
		Construct result = null;
		loopStatement = javaParser.flush();
		
		condition = javaParser.addCondition(this);
		
		if (loopStatement.endsWith(WHILE)) {
			loopKeyword = WHILE;
			loopStatement = loopStatement.substring(0, loopStatement.length() - loopKeyword.length());
			String token = javaParser.nextToken();
			if (!token.equals("(")) System.err.println("Expected (");
			javaParser.continueTo(')');
			loopGuard = javaParser.flush();
			loopBody = new BlockConstruct().parse(javaParser);
			result = this;
			
		} else if (loopStatement.endsWith(DO)) {
			loopKeyword = DO;
			loopStatement = loopStatement.substring(loopStatement.length() - loopKeyword.length());
			
			BlockConstruct block = new BlockConstruct();
			loopBody = new BlockConstruct().parse(javaParser);
			Construct clone = (Construct)loopBody.clone();
			block.addFirst(clone);
			String token = javaParser.nextToken();
			if (!token.matches(WHILE)) System.err.println("Expected " + WHILE);
			token = javaParser.nextToken();
			if (!token.equals("(")) System.err.println("Expected (");
			javaParser.continueTo(')');
			loopGuard = javaParser.flush();
			token = javaParser.nextToken();
			if (!token.matches(";")) System.err.println("Expected ;");
			block.addLast(this);
			result = block;
			
		} else if (loopStatement.endsWith(FOR)) {
			// TODO: Add support for foreachs
			
			loopKeyword = FOR;
			loopStatement = loopStatement.substring(loopStatement.length() - loopKeyword.length());
			String token = javaParser.nextToken();
			if (!token.equals("(")) System.err.println("Expected (");
			javaParser.flush();
			BlockConstruct block = new BlockConstruct();
			
			Construct forInitialization = new StatementConstruct().parse(javaParser);
			block.addLast(forInitialization);
			javaParser.continueTo(';');
			loopGuard = javaParser.flush();
			loopGuard = loopGuard.substring(0, loopGuard.length()-1);
			javaParser.continueTo(')');
			javaParser.goBackYourSelf(")");
			Construct step = new StatementConstruct(javaParser.flush() + ";");
			token = javaParser.nextToken();
			if (!token.equals(")")) System.err.println("Expected )");
			BlockConstruct forBlock = new BlockConstruct(); 
			forBlock.addFirst(new BlockConstruct().parse(javaParser));
			forBlock.addLast(step);
			loopBody = forBlock;
			result = block;
		}
		
		return result;
	}
	
	@Override
	public Object clone() {
		LoopConstruct result = new LoopConstruct();
		result.loopStatement = this.loopStatement;
		result.loopKeyword = this.loopKeyword;
		result.loopGuard = this.loopGuard;
		result.loopBody = (Construct)this.loopBody.clone();
		result.condition = this.condition;
		return result;
	}

	public String translate(int unroll) {
		StringBuilder result = new StringBuilder();
		String translatedLoop = loopBody.translate(unroll);
		recursiveTranslation(unroll, translatedLoop, result);
		result.append("/*@ assume !");
		result.append(loopGuard);
		result.append(";*/\n");
		return result.toString();
	}
	
	private void recursiveTranslation(int unroll, String translatedLoop, StringBuilder translation) {
		if (unroll > 0) {
			translation.append(loopStatement);
			translation.append(ConditionalConstruct.IF);
			translation.append(loopGuard);
			
			translation.append('{');
			translation.append(JavaCodeParser.CONDITION + condition + " = true;");
			translation.append(translatedLoop);
			recursiveTranslation(unroll-1, translatedLoop, translation);
			translation.append('}');
			
			translation.append(ConditionalConstruct.ELSE);
			translation.append('{');
			for (int i = 0; i < unroll; i++)
				translation.append(JavaCodeParser.CONDITION + condition + " = false;");
			translation.append('}');
		}
	}
	
	@Override
	public boolean getUncoveredFrontier(
			Map<Integer, Set<Boolean>> covered,
			Map<Integer, Set<Boolean>> frontier) {
		
		boolean stop = true;
		
		Set<Boolean> localFrontier = new TreeSet<Boolean>();
		Set<Boolean> branch = covered.get(condition);
		
		if (branch == null) {
			localFrontier.add(true);
			localFrontier.add(false);
		} else if (branch.contains(true) && branch.contains(false)) {
			loopBody.getUncoveredFrontier(covered, frontier);
			stop = false;
		} else if (branch.contains(true) && !branch.contains(false)) {
			localFrontier.add(false);
			stop = loopBody.getUncoveredFrontier(covered, frontier);
		} else {
			localFrontier.add(true);
			stop = false;
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
		return 2 + loopBody.getNumberOfBranches();
	}
	
	@Override
	public int getNumberOfPaths(int unroll) {
		return 1 + unroll * loopBody.getNumberOfPaths(unroll);
	}
	
}
