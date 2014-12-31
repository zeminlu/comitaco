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

    /*@ requires Nodes != null;
    @ ensures (\exists BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, child + sibling).has(n); n.key == \result);
    @ ensures (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, child + sibling).has(n); \result <= n.key);
    @ ensures (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, child + sibling).has(n); \old(n.key) == n.key);
    @ ensures (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, child + sibling).has(n); \old(n.degree) == n.degree);
    @ ensures (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, child + sibling).has(n); \old(n.parent) == n.parent);
    @ ensures (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, child + sibling).has(n); \old(n.sibling) == n.sibling);
    @ ensures (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, child + sibling).has(n); \old(n.child) == n.child);
    @ signals (Exception e) false;
    @*/
    public int findMinimum() {
        BinomialHeapNode x = Nodes;
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


  /*@
    @ requires true;
    @ ensures (\forall BinomialHeapNode n; \old(\reach(Nodes, BinomialHeapNode, child + sibling)).has(n); \reach(Nodes, BinomialHeapNode, child + sibling).has(n));
    @ ensures value > 0 ==> (\exists BinomialHeapNode n; \old(\reach(Nodes, BinomialHeapNode, child + sibling)).has(n) == false; \reach(Nodes, BinomialHeapNode, child + sibling).has(n) && n.key == value);
    @ ensures (\forall BinomialHeapNode n; \old(\reach(Nodes, BinomialHeapNode, child + sibling)).has(n); (\exists BinomialHeapNode m; \reach(Nodes, BinomialHeapNode, child + sibling).has(m); n.key == m.key));
    @ ensures value > 0 ==> size == \old(size) + 1;
    @  
    @*/
    public void insert(int value) {
        if (value > 0) {
            BinomialHeapNode insertTemp = new BinomialHeapNode();
            insertTemp.key = value;
            if (Nodes == null) {
                Nodes = insertTemp;
                size = 1;
            } else {
                BinomialHeapNode temp1 = Nodes, temp2 = insertTemp;
                while ((temp1 != null) && (temp2 != null)) {
                    if (temp1.degree == temp2.degree) {
                        BinomialHeapNode tmp = temp2;
                        temp2 = temp2.sibling;
                        tmp.sibling = temp1.sibling;
                        temp1.sibling = tmp;
                        temp1 = tmp.sibling;
                    } else {
                        if (temp1.degree < temp2.degree) {
                            if ((temp1.sibling == null) || (temp1.sibling.degree > temp2.degree)) {
                                BinomialHeapNode tmp = temp2;
                                temp2 = temp2.sibling;
                                tmp.sibling = temp1.sibling;
                                temp1.sibling = tmp;
                                temp1 = tmp.sibling;
                            } else {
                                temp1 = temp1.sibling;
                            }
                        } else {
                            BinomialHeapNode tmp = temp1;
                            temp1 = temp2;
                            temp2 = temp2.sibling;
                            temp1.sibling = tmp;
                            if (tmp == Nodes) {
                                Nodes = temp1;
                            }
                        }
                    }
                }

                if (temp1 == null) {
                    temp1 = Nodes;
                    while (temp1.sibling != null) {
                        temp1 = temp1.sibling;
                    }
                    temp1.sibling = temp2;
                }

                BinomialHeapNode prevTemp = null, temp = Nodes, nextTemp = Nodes.sibling;

                while (nextTemp != null) {
                    if ((temp.degree != nextTemp.degree) || ((nextTemp.sibling != null) && (nextTemp.sibling.degree == temp.degree))) {
                        prevTemp = temp;
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
                            temp.parent = nextTemp;
                            temp.sibling = nextTemp.child;
                            nextTemp.child = temp;
                            nextTemp.degree++;
                            temp = nextTemp;
                        }
                    }

                    nextTemp = temp.sibling;
                }
                size++;
            }
        }
    }




}
