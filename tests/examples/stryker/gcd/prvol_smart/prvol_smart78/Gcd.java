// This is mutant program.
// Author : ysma

package examples.stryker.gcd.prvol_smart.prvol_smart78;


public class Gcd
{

/*@
@ requires a >= 0 && b >= 0;
@ ensures \result >= 0;
@ ensures (\old(a) % \result == 0); 
@ ensures (\old(b) % \result == 0); 
@ ensures (\forall int x; x>0 && x<=\old(a) && x<=\old(b) && (\old(a) % x == 0) && (\old(b) % x == 0); x<= \result);
@ signals (Exception e) false; 
	@*/    public  int gcd( int a, int b )
    {
        if (a == 0) {
            return b;  
        } else {
            while (b != 0) {
                if (a > b) {
                    b = a - b; //mutGenLimit 1
                } else {
                    b = b - a;  
                }  
            }  
            return a;  
        }  
    }

}
