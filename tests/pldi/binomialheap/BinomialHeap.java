package pldi.binomialheap;


import pldi.binomialheap.BinomialHeapNode;


public class BinomialHeap {

    /*@
     @ invariant (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling + child).has(n); n.parent != null ==> n.key >= n.parent.key );
     @ invariant (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling + child).has(n); n.sibling != null ==> \reach(n.sibling, BinomialHeapNode, sibling + child).has(n) == false );  
     @ invariant (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling + child).has(n); n.child != null ==> \reach(n.child, BinomialHeapNode, sibling + child).has(n) == false );
     @ invariant (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling + child).has(n); ( n.child != null && n.sibling != null ) ==> 
     @                                                  (\forall BinomialHeapNode m; \reach(n.child, BinomialHeapNode, child + sibling).has(m); \reach(n.sibling, BinomialHeapNode, child + sibling).has(m) == false) );
     @ invariant (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling + child).has(n); ( n.child != null && n.sibling != null ) ==> 
     @                                                  (\forall BinomialHeapNode m; \reach(n.sibling, BinomialHeapNode, child + sibling).has(m); \reach(n.child, BinomialHeapNode, child + sibling).has(m) == false) ); 
     @ invariant (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling + child).has(n); n.degree >= 0 ); 
     @ invariant (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling + child).has(n); n.child == null ==> n.degree == 0 ); 
     @ invariant (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling + child).has(n); n.child != null ==> n.degree == \reach(n.child, BinomialHeapNode, sibling).int_size() );
     @ invariant (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling + child).has(n); (n.child != null && n.sibling != null) ==> \reach(n.child.child, BinomialHeapNode, child + sibling).int_size() == \reach(n.child.sibling, BinomialHeapNode, child + sibling).int_size() ); 
     @ invariant (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling + child).has(n); n.child != null ==> 
     @                                                  ( \forall BinomialHeapNode m; \reach(n.child, BinomialHeapNode, sibling).has(m); m.parent == n ) ); 
     @ invariant (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling + child).has(n); ( n.sibling != null && n.parent != null ) ==> n.degree > n.sibling.degree ); 
     @ 
     @ invariant this.size == \reach(Nodes, BinomialHeapNode, sibling + child).int_size();
     @
     @ invariant ( \forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling).has(n); (n.sibling != null ==> n.degree < n.sibling.degree) && (n.parent == null) );
     @
     @ invariant ( \forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling).has(n); n.key >= 0 );
     @
     @*/    




    public /*@ nullable @*/pldi.binomialheap.BinomialHeapNode Nodes;

    public int size;

    public BinomialHeap () {
    }

  /*@
    @ ensures (\exists BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, child + sibling).has(n); n.key == \result);
    @ ensures (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, child + sibling).has(n); \result <= n.key);
    @*/
    public int findMinimum() {
        BinomialHeapNode x = Nodes.child; //mutGenLimit 1
        BinomialHeapNode y = Nodes;
        int min = x.key;
        while (x != null) {
            if (x.key < min) {
                y = x;
                min = x.key;
            }
            x = x.sibling;
        }
        return y.key;
    }
}
