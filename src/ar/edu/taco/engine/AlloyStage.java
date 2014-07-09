package ar.edu.taco.engine;

import edu.mit.csail.sdg.alloy4.A4Reporter;
import edu.mit.csail.sdg.alloy4compiler.translator.A4Options;
import ar.edu.taco.TacoException;
import ar.uba.dc.rfm.dynalloy.analyzer.AlloyAnalysisException;
import ar.uba.dc.rfm.dynalloy.analyzer.AlloyAnalysisResult;
import ar.uba.dc.rfm.dynalloy.analyzer.AlloyAnalyzer;
import ar.uba.dc.rfm.dynalloy.visualization.VizException;

public class AlloyStage implements ITacoStage {

	private String alloy_filename;
	private AlloyAnalysisResult analysis_result;
	private A4Options options;

	public AlloyStage(String alloy_filename) {
		this.alloy_filename = alloy_filename;
		options = AlloyAnalyzer.build_A4Options();
	}

	public AlloyAnalysisResult get_analysis_result() {
		return this.analysis_result;
	}

	public void setSolver(A4Options.SatSolver solver) {
		options = new A4Options();
		options.solver = solver;
		options.coreMinimization = 0;
//		options.coreGranularity = 3;
	}
	
	@Override
	public void execute() {
		A4Reporter reporter = new A4Reporter();
		AlloyAnalyzer alloyVerificationRunner = new AlloyAnalyzer(alloy_filename, reporter);
		try {
			analysis_result = alloyVerificationRunner.analyzeCommand(null, options);
		} catch (VizException e) {
			throw new TacoException(e.getMessage());
		} catch (AlloyAnalysisException e) {
			throw new TacoException(e.getMessage());
		}

	}

}