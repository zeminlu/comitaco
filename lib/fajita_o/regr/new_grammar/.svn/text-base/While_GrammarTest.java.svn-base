package regr.new_grammar;

import java.io.IOException;
import java.util.Collections;

import org.junit.Test;

import antlr.RecognitionException;
import antlr.TokenStreamException;
import ar.uba.dc.rfm.dynalloy.DynAlloyCompiler;
import ar.uba.dc.rfm.dynalloy.DynAlloyOptions;
import ar.uba.dc.rfm.dynalloy.parser.AssertionNotFound;

public class While_GrammarTest {

	@Test
	public void while_grammar_test() throws RecognitionException, TokenStreamException, IOException, AssertionNotFound {
		DynAlloyCompiler c = new DynAlloyCompiler();
		DynAlloyOptions options = new DynAlloyOptions();
		options.setAssertionToCheck(null);
		options.setLoopUnroll(3);
		options.setStrictUnroll(false);
		options.setRemoveQuantifier(false);
		options.setRunAlloyAnalyzer(false);
		options.setBuildDynAlloyTrace(false);

		c.compile("test/regr/new_grammar/while_grammar.dals", "test/regr/new_grammar/while_grammar.als", options);

	}

}
