package ar.edu.taco.sbp;

public class DataNextList {

	public static class Data {
		
	}
	
	public static class Node {
		public /*@ nullable @*/ Node next;
		public /*@ nullable @*/ Data value;
	}
	
	public /*@ nullable @*/ Node header;
	
	//@ ensures \result==true;
	public boolean showInstance() {
		if (this.header==null)
			return true;
		
		if (this.header.next==null)
			return true;

		if (this.header.value==null)
			return true;
		
		return false;
	}
	
}
