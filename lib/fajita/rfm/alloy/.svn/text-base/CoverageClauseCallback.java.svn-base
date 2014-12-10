package rfm.alloy;

import edu.mit.csail.sdg.alloy4compiler.ast.Command;
import edu.mit.csail.sdg.alloy4compiler.ast.Module;
import edu.mit.csail.sdg.alloy4compiler.translator.A4Solution;

public interface CoverageClauseCallback {
	
	public void call(A4Solution a4Solution, Module world, Command command);
	
	public static class DummyCallback implements CoverageClauseCallback {
		@Override
		public void call(A4Solution a4Solution, Module world, Command command) {}
	}

}
