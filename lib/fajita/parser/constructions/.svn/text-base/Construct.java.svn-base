package parser.constructions;

import java.util.Map;
import java.util.Set;

import parser.JavaCodeParser;

public interface Construct extends Cloneable {

	public Construct parse(JavaCodeParser javaParser);
	
	public Object clone();
	
	public String translate(int unroll);
	
	public boolean getUncoveredFrontier(Map<Integer, Set<Boolean>> covered, Map<Integer, Set<Boolean>> frontier);
	
	public int getNumberOfBranches();
	
	public int getNumberOfPaths(int unroll);
	
}
