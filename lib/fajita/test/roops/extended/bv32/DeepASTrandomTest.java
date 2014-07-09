package roops.extended.bv32;

import org.junit.Test;

import roops.RoopsTest;

public class DeepASTrandomTest extends RoopsTest {

	@Override
	protected String getBenchmarkClass() {
		return "roops_extended_bv32_DeepASTrandom";
	}

	@Test
	public void Method1() {
		run_fajita("Method1");
	}
}
