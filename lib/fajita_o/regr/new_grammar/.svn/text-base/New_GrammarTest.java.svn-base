package regr.new_grammar;

import java.io.IOException;
import java.util.Collections;

import org.junit.Test;

import antlr.RecognitionException;
import antlr.TokenStreamException;
import ar.uba.dc.rfm.dynalloy.DynAlloyCompiler;
import ar.uba.dc.rfm.dynalloy.DynAlloyOptions;
import ar.uba.dc.rfm.dynalloy.parser.AssertionNotFound;

public class New_GrammarTest {

	@Test
	public void grammar_ext_test() throws RecognitionException,
			TokenStreamException, IOException, AssertionNotFound {
		DynAlloyCompiler c = new DynAlloyCompiler();
		DynAlloyOptions options = new DynAlloyOptions();
		options.setAssertionToCheck("grammarExtAssert");
		options.setLoopUnroll(3);
		options.setStrictUnroll(false);
		options.setRemoveQuantifier(false);
		options.setRunAlloyAnalyzer(false);
		options.setBuildDynAlloyTrace(false);

		c.compile("test/regr/new_grammar/grammar_ext.dals",
				"test/regr/new_grammar/grammar_ext.als", options);

	}

	@Test
	public void new_gramma_test() throws RecognitionException,
			TokenStreamException, IOException, AssertionNotFound {
		DynAlloyCompiler c = new DynAlloyCompiler();
		DynAlloyOptions options = new DynAlloyOptions();
		
		options.setAssertionToCheck("newGrammarAssert");
		options.setLoopUnroll(3);
		options.setStrictUnroll(false);
		options.setRemoveQuantifier(false);
		options.setRunAlloyAnalyzer(false);
		options.setBuildDynAlloyTrace(false);
		
		c.compile("test/regr/new_grammar/new_grammar.dals",
				"test/regr/new_grammar/new_grammar.als", options);

	}

	@Test
	public void old_grammar_test() throws RecognitionException,
			TokenStreamException, IOException, AssertionNotFound {
		DynAlloyCompiler c = new DynAlloyCompiler();
		DynAlloyOptions options = new DynAlloyOptions();
		options.setAssertionToCheck(null);
		options.setLoopUnroll(3);
		options.setStrictUnroll(false);
		options.setRemoveQuantifier(false);
		options.setRunAlloyAnalyzer(false);
		options.setBuildDynAlloyTrace(false);
		
				
		c.compile("test/regr/new_grammar/old_grammar.dals",
				"test/regr/new_grammar/old_grammar.als", options);

	}

}
