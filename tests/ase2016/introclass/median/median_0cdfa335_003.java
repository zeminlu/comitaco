package ase2016.introclass.median;

/**
 * This class comes from benchmark introclass.
 *
 */

public class median_0cdfa335_003 {

    /*@
    @ requires true;
    @ ensures ((\result == a) || (\result == b) || (\result == c));
    @ ensures ((a!=b || a!=c) ==> ( ((a==b) ==> (\result == a)) && ((b==c) ==> (\result ==b))));
    @ ensures ((a!=b && a!=c && b!=c) ==> (\exists int n; (n == a) || (n == b) || (n == c); \result>n));
    @ ensures ((a!=b && a!=c && b!=c) ==> (\exists int n; (n == a) || (n == b) || (n == c); \result<n));
    @ signals (RuntimeException e) false;
    @
    @*/
	public static int median(int a, int b, int c) {
		int median; 
        if ((a >= b && a <= c) || (a >= c && a <= b)) { //mutGenLimit 1
            median = a;
        }
        if ((b >= a && b <= c) || (b >= c && b <= a)) { //mutGenLimit 1
            median = b;
        } else {
            median = c;
        }
        return median;
	}
	
}
