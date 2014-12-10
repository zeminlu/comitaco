package parser.constructions;

import java.util.Map;
import java.util.Set;

import parser.JavaCodeParser;

public class SynchronizedConstruct implements Construct {
	
	public static final String SYNCHRONIZED = "synchronized"; 
	
	private String synchronizedDeclaration;
	private String synchronizedCondition;
	private Construct synchronizedBlock;

	@Override
	public Construct parse(JavaCodeParser javaParser) {
		synchronizedDeclaration = javaParser.flush();
		
		String token = javaParser.nextToken();
		if (!token.equals("(")) System.err.println("Expected (");
		javaParser.continueTo(')');
		synchronizedCondition = javaParser.flush();
		
		synchronizedBlock = new BlockConstruct().parse(javaParser);
		
		return this;
	}

	@Override
	public String translate(int unroll) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(synchronizedDeclaration);
		stringBuilder.append(synchronizedCondition);
		stringBuilder.append(synchronizedBlock.translate(unroll));
		return stringBuilder.toString();
	}

	@Override
	public boolean getUncoveredFrontier(Map<Integer, Set<Boolean>> covered,
			Map<Integer, Set<Boolean>> frontier) {
		return synchronizedBlock.getUncoveredFrontier(covered, frontier);
	}

	@Override
	public Object clone() {
		SynchronizedConstruct synchronizedConstruct = new SynchronizedConstruct();
		synchronizedConstruct.synchronizedDeclaration = this.synchronizedDeclaration;
		synchronizedConstruct.synchronizedCondition = this.synchronizedCondition;
		synchronizedConstruct.synchronizedBlock = (Construct)this.synchronizedBlock.clone();
		return synchronizedConstruct;
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
