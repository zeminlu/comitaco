package roops.extended.bv32.floats;

import org.junit.Test;

import roops.RoopsTest;

public class FloatArithmeticTest extends RoopsTest {

	@Override
	protected String getBenchmarkClass() {
		return "roops_extended_bv32_floats_FloatArithmetic";
	}

	@Test
	public void Method1() {
		run_fajita("Method1");
	}
	
	@Test
	public void Method10() {
		run_fajita("Method10");
	}

}
