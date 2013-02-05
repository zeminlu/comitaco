package ar.edu.taco.reachTest;

public class reachTest {

	public reachTest field1;
	
	public reachTest field2;
	
	/*@ invariant \reach(this, reachTest, field1 + field2).has(this)==true; @*/
	
	/*@ requires true; 
	  @ ensures true; 
	  @*/
	public void testReachMethod(){}
	
}
