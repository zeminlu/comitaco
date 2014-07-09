package roops.extended.bv32.arr.noex;

import org.junit.Test;

import roops.RoopsTest;

public class IntArrayWithoutExceptionsWithArrayParametersTest extends RoopsTest {

	protected String getBenchmarkClass() {
		return "roops_extended_bv32_arr_noex_IntArrayWithoutExceptionsWithArrayParameters";
	}

	@Test
	public void DirectWriteFollowedByDirectRead() {
		run_fajita("DirectWriteFollowedByDirectRead");
	}
	
	@Test
	public void DirectWriteFollowedByIndirectRead() {
		run_fajita("DirectWriteFollowedByIndirectRead");
	}
	
	@Test
	public void IndirectWriteFollowedByIndirectRead() {
		run_fajita("IndirectWriteFollowedByIndirectRead");
	}
	
}
