package roops.core.objects;

/**
* BinomialHeapExtractMin1Bug is an implementation of binomial heaps with a bug
* injected in line 33 of method extractMin. This bug replaces:
* prevBro = fakeNode;
* with
* prevBro = bro;
* The bug to be inserted and the affected line were randomly chosen, from a set of
* real programming mistakes in binomial heap implementations. This particular one appears in:
* https://github.com/eternalStudent/BinomialHeap/commit/d29dfe67bbfc5e98b708e386e1d7fe87544a17ee
*/

import roops.core.objects.BinomialHeapNode;


public class BinomialHeap {

	/*@
	 @ invariant true;
	 @*/
	
    public /*@ nullable @*/roops.core.objects.BinomialHeapNode Nodes;

    public int size;

    public BinomialHeap() {
    }



    // 3. Unite two binomial heaps
    // helper procedure
    private void merge( /* @ nullable @ */roops.core.objects.BinomialHeapNode binHeap ) {
    	roops.core.objects.BinomialHeapNode mix = null;
    	roops.core.objects.BinomialHeapNode current = null;
        roops.core.objects.BinomialHeapNode temp1 = Nodes;
        roops.core.objects.BinomialHeapNode temp2 = binHeap;
        if (temp1 == null) {
        	mix = temp2;        	
        }
        else {
        	if (temp2 == null) {
            	mix = temp1;
        	}
        	else {
        		if (temp1.degree <= temp2.degree) {
        			mix = temp1;
        			temp1 = temp1.sibling;
        		}
        		else {
        			mix = temp2;
        			temp2 = temp2.sibling;
        		}
        		current = mix;
        		while (temp1 != null && temp2 != null) {
                    if (temp1.degree <= temp2.degree) {
                    	current.sibling = temp1;
                    	current = current.sibling;
                    	temp1 = temp1.sibling;
                    } else {
                    	current.sibling = temp2;
                    	current = current.sibling;
                    	temp2 = temp2.sibling;
                    }
        		}
        		while (temp1 != null) {
        			current.sibling = temp1;
        			current = current.sibling;
        			temp1 = temp1.sibling;
        		}
        		while (temp2 != null) {
        			current.sibling = temp2;
        			current = current.sibling;
        			temp2 = temp2.sibling;
        		}
        		current.sibling = null;	
        	}
        }
        Nodes = mix;
    }

    /*@
    @ requires (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling + child).has(n); n.parent != null ==> n.key >= n.parent.key );
    @ requires (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling + child).has(n); n.sibling != null ==> \reach(n.sibling, BinomialHeapNode, sibling + child).has(n) == false );
    @ requires (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling + child).has(n); n.child != null ==> \reach(n.child, BinomialHeapNode, sibling + child).has(n) == false );
    @ requires (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling + child).has(n); ( n.child != null && n.sibling != null ) ==>
    @                                                  (\forall BinomialHeapNode m; \reach(n.child, BinomialHeapNode, child + sibling).has(m); \reach(n.sibling, BinomialHeapNode, child + sibling).has(m) == false) );
    @ requires (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling + child).has(n); ( n.child != null && n.sibling != null ) ==>
    @                                                  (\forall BinomialHeapNode m; \reach(n.sibling, BinomialHeapNode, child + sibling).has(m); \reach(n.child, BinomialHeapNode, child + sibling).has(m) == false) );
    @ requires (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling + child).has(n); n.degree >= 0 );
    @ requires (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling + child).has(n); n.child == null ==> n.degree == 0 );
    @ requires (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling + child).has(n); n.child != null ==> n.degree == \reach(n.child, BinomialHeapNode, sibling).int_size() );
    @ requires (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling + child).has(n); n.child != null ==> \reach(n.child.child, BinomialHeapNode, child + sibling).int_size() == \reach(n.child.sibling, BinomialHeapNode, child + sibling).int_size() );
    @ requires (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling + child).has(n); n.child != null ==>
    @                                                  ( \forall BinomialHeapNode m; \reach(n.child, BinomialHeapNode, sibling).has(m); m.parent == n ) );
    @ requires (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling + child).has(n); ( n.sibling != null && n.parent != null ) ==> n.degree > n.sibling.degree );
    @
    @ requires this.size == \reach(Nodes, BinomialHeapNode, sibling + child).int_size();
    @
    @ requires ( \forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling).has(n); (n.sibling != null ==> n.degree < n.sibling.degree) && (n.parent == null) );
    @
    @ requires ( \forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling).has(n); n.key >= 0 );
    @ requires (\forall BinomialHeapNode n; \reach(binHeap, BinomialHeapNode, sibling + child).has(n); n.parent != null ==> n.key >= n.parent.key );
    @ requires (\forall BinomialHeapNode n; \reach(binHeap, BinomialHeapNode, sibling + child).has(n); n.sibling != null ==> \reach(n.sibling, BinomialHeapNode, sibling + child).has(n) == false );
    @ requires (\forall BinomialHeapNode n; \reach(binHeap, BinomialHeapNode, sibling + child).has(n); n.child != null ==> \reach(n.child, BinomialHeapNode, sibling + child).has(n) == false );
    @ requires (\forall BinomialHeapNode n; \reach(binHeap, BinomialHeapNode, sibling + child).has(n); ( n.child != null && n.sibling != null ) ==>
    @                                                  (\forall BinomialHeapNode m; \reach(n.child, BinomialHeapNode, child + sibling).has(m); \reach(n.sibling, BinomialHeapNode, child + sibling).has(m) == false) );
    @ requires (\forall BinomialHeapNode n; \reach(binHeap, BinomialHeapNode, sibling + child).has(n); ( n.child != null && n.sibling != null ) ==>
    @                                                  (\forall BinomialHeapNode m; \reach(n.sibling, BinomialHeapNode, child + sibling).has(m); \reach(n.child, BinomialHeapNode, child + sibling).has(m) == false) );
    @ requires (\forall BinomialHeapNode n; \reach(binHeap, BinomialHeapNode, sibling + child).has(n); n.degree >= 0 );
    @ requires (\forall BinomialHeapNode n; \reach(binHeap, BinomialHeapNode, sibling + child).has(n); n.child == null ==> n.degree == 0 );
    @ requires (\forall BinomialHeapNode n; \reach(binHeap, BinomialHeapNode, sibling + child).has(n); n.child != null ==> n.degree == \reach(n.child, BinomialHeapNode, sibling).int_size() );
    @ requires (\forall BinomialHeapNode n; \reach(binHeap, BinomialHeapNode, sibling + child).has(n); n.child != null ==> \reach(n.child.child, BinomialHeapNode, child + sibling).int_size() == \reach(n.child.sibling, BinomialHeapNode, child + sibling).int_size() );
    @ requires (\forall BinomialHeapNode n; \reach(binHeap, BinomialHeapNode, sibling + child).has(n); n.child != null ==>
    @                                                  ( \forall BinomialHeapNode m; \reach(n.child, BinomialHeapNode, sibling).has(m); m.parent == n ) );
    @ requires (\forall BinomialHeapNode n; \reach(binHeap, BinomialHeapNode, sibling + child).has(n); ( n.sibling != null && n.parent != null ) ==> n.degree > n.sibling.degree );
    @
    @
    @ requires ( \forall BinomialHeapNode n; \reach(binHeap, BinomialHeapNode, sibling).has(n); (n.sibling != null ==> n.degree < n.sibling.degree) && (n.parent == null) );
    @
    @ requires ( \forall BinomialHeapNode n; \reach(binHeap, BinomialHeapNode, sibling).has(n); n.key >= 0 );
    @ requires ( \forall BinomialHeapNode n; \reach(binHeap, BinomialHeapNode, sibling+child).has(n); !\reach(Nodes, BinomialHeapNode, sibling+child).has(n));
    @ requires ( \forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling+child).has(n); !\reach(binHeap, BinomialHeapNode, sibling+child).has(n));
	@ ensures (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling + child).has(n); n.parent != null ==> n.key >= n.parent.key );
    @ ensures (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling + child).has(n); n.sibling != null ==> \reach(n.sibling, BinomialHeapNode, sibling + child).has(n) == false );
    @ ensures (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling + child).has(n); n.child != null ==> \reach(n.child, BinomialHeapNode, sibling + child).has(n) == false );
    @ ensures (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling + child).has(n); ( n.child != null && n.sibling != null ) ==>
    @                                                  (\forall BinomialHeapNode m; \reach(n.child, BinomialHeapNode, child + sibling).has(m); \reach(n.sibling, BinomialHeapNode, child + sibling).has(m) == false) );
    @ ensures (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling + child).has(n); ( n.child != null && n.sibling != null ) ==>
    @                                                  (\forall BinomialHeapNode m; \reach(n.sibling, BinomialHeapNode, child + sibling).has(m); \reach(n.child, BinomialHeapNode, child + sibling).has(m) == false) );
    @ ensures (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling + child).has(n); n.degree >= 0 );
    @ ensures (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling + child).has(n); n.child == null ==> n.degree == 0 );
    @ ensures (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling + child).has(n); n.child != null ==> n.degree == \reach(n.child, BinomialHeapNode, sibling).int_size() );
    @ ensures (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling + child).has(n); n.child != null ==> \reach(n.child.child, BinomialHeapNode, child + sibling).int_size() == \reach(n.child.sibling, BinomialHeapNode, child + sibling).int_size() );
    @ ensures (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling + child).has(n); n.child != null ==>
    @                                                  ( \forall BinomialHeapNode m; \reach(n.child, BinomialHeapNode, sibling).has(m); m.parent == n ) );
    @ ensures (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling + child).has(n); ( n.sibling != null && n.parent != null ) ==> n.degree > n.sibling.degree );
    @ ensures ( \forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling).has(n); (n.sibling != null ==> n.degree < n.sibling.degree) && (n.parent == null) );
    @ ensures ( \forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling).has(n); n.key >= 0 );
    @ ensures ( \forall BinomialHeapNode n; \old(\reach(Nodes, BinomialHeapNode, sibling + child)).has(n); \reach(Nodes, BinomialHeapNode, sibling + child).has(n));
    @ ensures ( \forall BinomialHeapNode n; \old(\reach(binHeap, BinomialHeapNode, sibling + child)).has(n); \reach(Nodes, BinomialHeapNode, sibling + child).has(n));
    @*/
    public void unionNodes( /* @ nullable @ */roops.core.objects.BinomialHeapNode binHeap ) {
        merge( binHeap );
        roops.core.objects.BinomialHeapNode prevTemp = null;
        roops.core.objects.BinomialHeapNode temp = null; //mutGenLimit 1
        roops.core.objects.BinomialHeapNode nextTemp = Nodes.sibling;
        //@decreasing \reach(temp, BinomialHeapNode, sibling).int_size();
        while (nextTemp != null) {
            if (temp.degree != nextTemp.degree || nextTemp.sibling != null && nextTemp.sibling.degree == temp.degree) {
                prevTemp = null; //mutGenLimit 1
                temp = nextTemp;
            } else {
                if (temp.key <= nextTemp.key) {
                    temp.sibling = nextTemp.sibling;
                    nextTemp.parent = temp;
                    nextTemp.sibling = temp.child;
                    temp.child = nextTemp;
                    temp.degree++;
                } else {
                    if (prevTemp == null) {
                        Nodes = nextTemp;
                    } else {
                        prevTemp.sibling = nextTemp;
                    }
                    temp.parent = prevTemp; //mutGenLimit 1
                    temp.sibling = nextTemp.child;
                    nextTemp.child = temp;
                    nextTemp.degree++;
                    temp = prevTemp; //mutGenLimit 1
                }
            }
            nextTemp = temp.child; 
        }
    }


}
