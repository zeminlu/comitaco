package roops.core.bv32.arr.noex;

import org.junit.Test;

import roops.RoopsTest;

public class IntArrayWithoutExceptionsTest extends RoopsTest {

	@Override
	protected String getBenchmarkClass() {
		return "roops_core_bv32_arr_noex_IntArrayWithoutExceptions";
	}
	
	@Test
	public void ArrayInitialization() {
		run_fajita("ArrayInitialization");
	}

	@Test
	public void ArrayInitializationVariableSize() {
		run_fajita("ArrayInitializationVariableSize");
	}
	
	@Test
	public void ArrayInitializationVariableSizeVariableIndex() {
		run_fajita("ArrayInitializationVariableSizeVariableIndex");
	}	
	

	@Test
	public void BigArray() {
		run_fajita("BigArray");
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
