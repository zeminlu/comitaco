package ase2016.introclass.median;

/**
 * This class comes from benchmark introclass. In translation to Java performed by 
 * Monperrus et al. a bug is reported that is not an actual bug (result uses capital IS instead of is).
 * This program is correct.
 *
 */

public class median_3b2376ab_006 {

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
		int small;
        if (a <= b) { //mutGenLimit 1
            small = a;
            if (small > c) { //mutGenLimit 1
                return a;
            } else if (c > b) { //mutGenLimit 1
                return b;
            } else {
                return c;
            }
        } else {
            small = b;
            if (small > c) { //mutGenLimit 1
                return b;
            } else if (c > a) { //mutGenLimit 1
                return a;
            } else {
                return c;
            }
        }		
	}
	
}
