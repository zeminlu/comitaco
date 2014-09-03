package examples.stryker.gcd;

public class Gcd {
	
	/*@
	@ requires aa >= 0 && bb >= 0;
	@ ensures \result >= 0;
	@ ensures (aa % \result == 0); 
	@ ensures (bb % \result == 0 || bb == \result); 
	@ signals (Exception e) false; 
	@*/
	public static int gcd(final int aa, final int bb) {
      int a = aa;
      int b = bb;
	  if (a == 0) {
		  return b;
	  } 
	  else
	  {
	    while (b != 0) {
	      if (a > b) {
	        a = a - b; 
	      } else {
	        b = b - a; 
	      } 
	    } 
	    return a;
	  } 
	} 	
}
