package roops.core.bv32.linear.noex.gods;

import org.junit.Test;

import roops.RoopsTest;

public class LinearWithoutOverflowTest extends RoopsTest {

	@Override
	protected String getBenchmarkClass() {
		return "roops_core_bv32_linear_noex_gods_LinearWithoutOverflow";
	}

	@Test
	public void MinusYEqualsC() {
		run_fajita("MinusYEqualsC");
	}
	
	@Test
	public void XPlusYUnequalsZ() {
		run_fajita("XPlusYUnequalsZ");
	}
	
	@Test
	public void XTimesCUnequalsZ() {
		run_fajita("XTimesCUnequalsZ");
	}
	
}
