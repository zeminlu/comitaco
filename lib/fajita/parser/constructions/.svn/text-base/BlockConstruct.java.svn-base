package parser.constructions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parser.JavaCodeParser;


public class BlockConstruct implements Construct {

	private boolean emptyBlock;
	private List<Construct> block;
	
	public BlockConstruct() {
		emptyBlock = true;
		block = new ArrayList<Construct>();
	}
	
	public Construct parse(JavaCodeParser javaParser) {
		String token = javaParser.nextToken();
		emptyBlock = token.matches(";");
		boolean singleStatementBlock = !token.equals("{");
		
		if (!emptyBlock) {
			if (!singleStatementBlock) {
				javaParser.flush();
				token = javaParser.nextToken();
			}
			
			while (!token.equals("}")) {
				if (token.matches(ConditionalConstruct.IF)) {
					block.add(new ConditionalConstruct().parse(javaParser));
				} else if (token.matches(LoopConstruct.LOOP)) {
					block.add(new LoopConstruct().parse(javaParser));
				} else if (token.matches(ReturnConstruct.RETURN)) {
					block.add(new ReturnConstruct().parse(javaParser));
				} else if (token.matches(TryCatchConstruct.TRY)) {
					block.add(new TryCatchConstruct().parse(javaParser));
				} else if (token.matches(SynchronizedConstruct.SYNCHRONIZED)) {
					block.add(new SynchronizedConstruct().parse(javaParser));
				} else if (token.matches(SelectConstruct.SWITCH)) {
					System.err.println("Coverage for switch blocks is not supported.");
					block.add(new SelectConstruct().parse(javaParser));
				} else if (token.matches(SelectConstruct.CaseConstruct.CASE)) {
					block.add(new SelectConstruct.CaseConstruct().parse(javaParser));
				} else if (token.equals("{")) {
					javaParser.goBackYourSelf(token);
					block.add(new BlockConstruct().parse(javaParser));
				} else {
					block.add(new StatementConstruct().parse(javaParser));
					// XXX: This includes break, continue, throw and assert. However, all of them should be treated differently.
					// The problem is that right now our theoretical model ignores them, so do we.
				}
				
				if (singleStatementBlock)
					break;
	
				token = javaParser.nextToken();
			}
		}
		
		javaParser.flush();
		return this;
	}
	
	public void addFirst(Construct construct) {
		add(0, construct);
	}
	
	public void addLast(Construct construct) {
		add(block.size(), construct);
	}
	
	private void add(int index, Construct construct) {
		block.add(index, construct);
	}
	
	@Override
	public Object clone() {
		BlockConstruct result = new BlockConstruct();
		result.emptyBlock = this.emptyBlock;
		for (Construct construct : this.block)
			result.block.add((Construct)construct.clone());
		return result;
	}
	
	@Override
	public String translate(int unroll) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{\n");
		if (!emptyBlock)
			for (Construct construct : block)
				stringBuilder.append(construct.translate(unroll));
		stringBuilder.append("}\n");
		return stringBuilder.toString();
	}
	
	@Override
	public boolean getUncoveredFrontier(
			Map<Integer, Set<Boolean>> covered,
			Map<Integer, Set<Boolean>> frontier) {
		boolean stop = false;
		for (Construct construct : block) {
			stop = construct.getUncoveredFrontier(covered, frontier);
			if (stop) break;
		}
		return stop;
	}
	
	@Override
	public int getNumberOfBranches() {
		int branches = 0;
		for (Construct construct : block)
			branches += construct.getNumberOfBranches();
		return branches;
	}
	
	@Override
	public int getNumberOfPaths(int unroll) {
		int paths = 0;
		for (Construct construct : block)
			paths *= construct.getNumberOfPaths(unroll);
		return paths;
	}
	
}
