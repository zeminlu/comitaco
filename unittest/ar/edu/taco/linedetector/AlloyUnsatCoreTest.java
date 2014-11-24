package ar.edu.taco.linedetector;

import java.util.Set;

import edu.mit.csail.sdg.alloy4.Pair;
import edu.mit.csail.sdg.alloy4.Pos;
import edu.mit.csail.sdg.alloy4compiler.translator.A4Options;
import ar.edu.taco.engine.AlloyStage;
import ar.uba.dc.rfm.dynalloy.analyzer.AlloyAnalysisResult;

public class AlloyUnsatCoreTest {
	
	public static void main(String[] args) {
		AlloyStage stage = new AlloyStage("output/sequentialOutput.als");
		stage.setSolver(A4Options.SatSolver.MiniSatProverJNI);
		stage.execute();
		AlloyAnalysisResult alloyAnalysisResult = stage
				.get_analysis_result();
		Pair<Set<Pos>, Set<Pos>> unsatCore = alloyAnalysisResult.getAlloy_solution().highLevelCore();
		System.out.println(unsatCore);
	}

}
