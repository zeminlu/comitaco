package ar.edu.taco.arithmetic.simple;

public class C1 {

	//@ ensures \result==true;
	public boolean m1(int x, int y) {
		boolean result;
		if (x+1<y/2) 
			result = true;
		else
			result = false;
		return result;
	}
}
