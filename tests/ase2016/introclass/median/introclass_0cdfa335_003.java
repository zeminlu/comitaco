package ase2016.introclass.median;

<<<<<<< HEAD

=======
>>>>>>> db057c2264b26ac930335f60f2a121838cf3f38f
public class introclass_0cdfa335_003 {

    public introclass_0cdfa335_003() {
    }
<<<<<<< HEAD

=======
    
>>>>>>> db057c2264b26ac930335f60f2a121838cf3f38f
    /*@
    @ requires true;
    @ ensures ((\result == \old(a)) || (\result == \old(b)) || (\result == \old(c)));
    @ ensures ((\old(a)!=\old(b) || \old(a)!=\old(c)) ==> ( ((\old(a)==\old(b)) ==> (\result == \old(a))) && ((\old(b)==\old(c)) ==> (\result ==\old(b)))));
    @ ensures ((\old(a)!=\old(b) && \old(a)!=\old(c) && \old(b)!=\old(c)) ==> (\exists int n; (n == \old(a)) || (n == \old(b)) || (n == \old(c)); \result>n));
    @ ensures ((\old(a)!=\old(b) && \old(a)!=\old(c) && \old(b)!=\old(c)) ==> (\exists int n; (n == \old(a)) || (n == \old(b)) || (n == \old(c)); \result<n));
    @ signals (RuntimeException e) false;
    @
    @*/
    public int median( int a, int b, int c ) {
<<<<<<< HEAD
        int median;
        if (a >= b && a <= c || a >= c && a <= b) { //mutGenLimit 1
            median = a; //mutGenLimit 1
        }
        if (b >= a && b <= c || b >= c && b <= a) { //mutGenLimit 1
            median = b; //mutGenLimit 1
        } else {
            median = c; //mutGenLimit 1
        }
        return median; //mutGenLimit 1
    }

=======
        int theMedian;
        if (a >= b && a <= c || a >= c && a <= b) { //mutGenLimit 1
            theMedian = a; //mutGenLimit 1
        }
        if (b >= a && b <= c || b >= c && b <= a) { //mutGenLimit 1
            theMedian = b; //mutGenLimit 1
        } else {
            theMedian = c; //mutGenLimit 1
        }
        return theMedian; //mutGenLimit 1
    }
	
>>>>>>> db057c2264b26ac930335f60f2a121838cf3f38f
}
