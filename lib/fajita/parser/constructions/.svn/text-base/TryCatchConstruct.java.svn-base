package parser.constructions;

import java.util.Map;
import java.util.Set;

import parser.JavaCodeParser;

public class TryCatchConstruct implements Construct {
	
	public static final String TRY = "try";
	public static final String CATCH = "catch";
	public static final String FINALLY = "finally";

	private String tryDeclaration;
	private String catchDeclaration;
	private String finallyDeclaration;
	private Construct tryBody;
	private Construct catchBody;
	private Construct finallyBody;
	
	@Override
	public Construct parse(JavaCodeParser javaParser) {
		tryDeclaration = javaParser.flush();
		tryBody = new BlockConstruct().parse(javaParser);
		
		boolean mandatoryFinally = false;
		
		String token = javaParser.nextToken();
		if (!token.matches(CATCH)) {
			catchDeclaration = javaParser.flush();
			catchBody = new BlockConstruct().parse(javaParser);
			token = javaParser.nextToken();
		} else {
			mandatoryFinally = true;
		}
		
		if (token.matches(FINALLY)) {
			finallyDeclaration = javaParser.flush();
			finallyBody = new BlockConstruct().parse(javaParser);
		} else {
			javaParser.goBackYourSelf(token);
			if (mandatoryFinally)
				System.err.println("Expected finally.");
		}
		
		return this;
	}

	@Override
	public String translate(int unroll) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(tryDeclaration);
		stringBuilder.append(tryBody.translate(unroll));
		if (catchDeclaration != null) {
			stringBuilder.append(catchDeclaration);
			stringBuilder.append(catchBody.translate(unroll));
		}
		if (finallyDeclaration != null) {
			stringBuilder.append(finallyDeclaration);
			stringBuilder.append(finallyBody.translate(unroll));
		}
		return stringBuilder.toString();
	}

	@Override
	public boolean getUncoveredFrontier(
			Map<Integer, Set<Boolean>> covered,
			Map<Integer, Set<Boolean>> frontier) {
		tryBody.getUncoveredFrontier(covered, frontier);
		if (catchBody != null)
			catchBody.getUncoveredFrontier(covered, frontier);
		if (finallyBody != null)
			finallyBody.getUncoveredFrontier(covered, frontier);
		return false; // FIXME: If I add a stop condition because of a throw the catch may interrupt
	}
	
	@Override
	public Object clone() {
		TryCatchConstruct tryCatchConstruct = new TryCatchConstruct();
		tryCatchConstruct.tryDeclaration = this.tryDeclaration;
		tryCatchConstruct.tryBody = (Construct)this.tryBody.clone();
		if (catchDeclaration != null) {
			tryCatchConstruct.catchDeclaration = this.catchDeclaration;
			tryCatchConstruct.catchBody = (Construct)this.catchBody.clone();
		}
		if (finallyDeclaration != null) {
			tryCatchConstruct.finallyDeclaration = this.finallyDeclaration;
			tryCatchConstruct.finallyBody = (Construct)this.finallyBody.clone();
		}
		return tryCatchConstruct;
	}
	
	@Override
	public int getNumberOfBranches() {
		int branches = tryBody.getNumberOfBranches();
		if (catchBody != null)
			branches += catchBody.getNumberOfBranches();
		if (finallyBody != null)
			branches += finallyBody.getNumberOfBranches();
		return branches;
	}
	
	@Override
	public int getNumberOfPaths(int unroll) {
		int paths = tryBody.getNumberOfPaths(unroll);
		if (catchBody != null)
			paths *= catchBody.getNumberOfPaths(unroll);
		if (finallyBody != null)
			paths *= finallyBody.getNumberOfPaths(unroll);
		return paths;
	}

}
