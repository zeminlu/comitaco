// This is mutant program.
// Author : ysma

package examples.stryker.gcd.aois.aois19;


public class Gcd {

/*@
	@ requires a >= 0 && b >= 0;
	@ ensures \result >= 0;
	@ ensures (\old(a) % \result == 0); 
	@ ensures (\old(b) % \result == 0); 
	@ ensures (\forall int x; x>0 && x<=\old(a) && x<=\old(b) && (\old(a) % x == 0) && (\old(b) % x == 0); x<= \result);
	@ signals (Exception e) false; 
	@*/    public int gcd ( int a, int b, int customvar_0, int customvar_1)
     {

        if ( a == 0 ) {
            return customvar_1; //mutGenLimit 0 mutID 1
        } else {
            {
                if ( b != 0 ) {
                    if ( a > b ) {
                        a = a - b;
                    } else {
                        a = b - a;
                    }
                }
                if ( b != 0 ) {
                    if ( a > b ) {
                        a = a - b;
                    } else {
                        a = b - a;
                    }
                }
                if ( b != 0 ) {
                    if ( a > b ) {
                        a = a - b;
                    } else {
                        a = b - a;
                    }
                }
            }
            return a;
        }
    }
}
