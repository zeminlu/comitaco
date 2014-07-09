package roops.extended.bv64;

import org.junit.Test;

import roops.RoopsTest;

public class LongArithmeticsTest extends RoopsTest {

	@Override
	protected String getBenchmarkClass() {
		return "roops_extended_bv64_LongArithmetics";
	}
	
	@Test
	public void Method1() {
		run_fajita("Method1");
	}

	@Test
	public void Method5() {
		run_fajita("Method5");
	}
}
