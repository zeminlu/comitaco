package pldi.binomialheap;

import pldi.binomialheap.BinomialHeapNode;


public class BinomialHeap {


    /*@
 @ invariant (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling + child).has(n); 
 @                  ( n.parent != null  ==> n.key >= n.parent.key ) &&
 @                  ( n.child != null ==> \reach(n.child, BinomialHeapNode, sibling + child).has(n) == false ) && 
 @                  ( n.sibling != null ==> \reach(n.sibling, BinomialHeapNode, sibling + child).has(n) == false ) && 
 @                  ( ( n.child != null && n.sibling != null ) ==> 
 @                      (\forall BinomialHeapNode m; \reach(n.child, BinomialHeapNode, child + sibling).has(m); \reach(n.sibling, BinomialHeapNode, child + sibling).has(m) == false)) && 
 @                  ( ( n.child != null && n.sibling != null ) ==> 
 @                      (\forall BinomialHeapNode m; \reach(n.sibling, BinomialHeapNode, child + sibling).has(m); \reach(n.child, BinomialHeapNode, child + sibling).has(m) == false)) && 
 @                  ( n.degree >= 0 ) && 
 @                  ( n.child == null ==> n.degree == 0 ) && 
 @                  ( n.child != null ==> n.degree == \reach(n.child, BinomialHeapNode, sibling).int_size() )  && 
 @                  ( \reach(n.child.child, BinomialHeapNode, child + sibling).int_size() == \reach(n.child.sibling, BinomialHeapNode, child + sibling).int_size() ) && 
 @                  ( n.child != null ==> ( \forall BinomialHeapNode m; \reach(n.child, BinomialHeapNode, sibling).has(m); m.parent == n )) && 
 @                  ( ( n.sibling != null && n.parent != null ) ==> ( n.degree > n.sibling.degree ) ) 
 @      );
 @ 
 @ invariant this.size == \reach(Nodes, BinomialHeapNode, sibling + child).int_size();
 @
 @ invariant ( \forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling).has(n); (n.sibling != null ==> n.degree < n.sibling.degree) && (n.parent == null) );
 @
 @ invariant ( \forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, sibling).has(n); n.key >= 0 );
 @
 @*/

    public /*@ nullable @*/ BinomialHeapNode Nodes;

    public int size;



    /*@ 
    @ requires value > 0;
    @ ensures (\exists BinomialHeapNode n; \old(\reach(Nodes, BinomialHeapNode, child + sibling)).has(n) == false;
    @          \reach(Nodes, BinomialHeapNode, child + sibling).has(n) && n.key == value);
    @ ensures (\forall BinomialHeapNode n; \old(\reach(Nodes, BinomialHeapNode, child + sibling)).has(n); \reach(Nodes, BinomialHeapNode, child + sibling).has(n));
    @ ensures size == \old(size) + 1;
    @*/
    public void insert(int value) {
        if (value > 0) {

            BinomialHeapNode temp = new BinomialHeapNode();
            temp.key = value;

            if (Nodes == null) {

                Nodes = temp;
                size = 1;
            } else {

                BinomialHeapNode tempMergeInsert1 = Nodes, tempMergeInsert2 = temp;
                while ((tempMergeInsert1 != null) && (tempMergeInsert2 != null)) {

                    if (tempMergeInsert1.degree == tempMergeInsert2.degree) {

                        BinomialHeapNode tmp = tempMergeInsert2;
                        tempMergeInsert2 = tempMergeInsert2.sibling;
                        tmp.sibling = tempMergeInsert1.sibling;
                        tempMergeInsert1.sibling = tmp;
                        tempMergeInsert1 = tmp.sibling;
                    } else {
                        if (tempMergeInsert1.degree < tempMergeInsert2.degree) {
                            if ((tempMergeInsert1.sibling == null)
                                    || (tempMergeInsert1.sibling.degree > tempMergeInsert2.degree)) {
                                BinomialHeapNode tmp = tempMergeInsert2;
                                tempMergeInsert2 = tempMergeInsert2.sibling;
                                tmp.sibling = tempMergeInsert1.sibling;
                                tempMergeInsert1.sibling = tmp;
                                tempMergeInsert1 = tmp.sibling;
                            } else {
                                tempMergeInsert1 = tempMergeInsert1.sibling;
                            }
                        } else {
                            BinomialHeapNode tmp = tempMergeInsert1;
                            tempMergeInsert1 = tempMergeInsert2;
                            tempMergeInsert2 = tempMergeInsert2.sibling;
                            tempMergeInsert1.sibling = tmp;
                            if (tmp == Nodes) {
                                Nodes = tempMergeInsert1;
                            } 
                        }
                    }
                }

                if (tempMergeInsert1 == null) {
                    tempMergeInsert1 = Nodes;
                    while (tempMergeInsert1.sibling != null) {
                        tempMergeInsert1 = tempMergeInsert1.sibling;
                    }
                    tempMergeInsert1.sibling = tempMergeInsert2;
                } 

                BinomialHeapNode prevTemp = null, tempUnionNodesInsert = Nodes , nextTemp = Nodes.sibling;

                while (nextTemp != null) {
                    if ((tempUnionNodesInsert.degree != nextTemp.degree)
                            || ((nextTemp.sibling != null) && (nextTemp.sibling.degree == tempUnionNodesInsert.degree))) {

                        prevTemp = tempUnionNodesInsert;
                        tempUnionNodesInsert = nextTemp;
                    } else {

                        if (tempUnionNodesInsert.key <= nextTemp.key) {

                            tempUnionNodesInsert.sibling = nextTemp.sibling;
                            nextTemp.parent = tempUnionNodesInsert;
                            nextTemp.sibling = tempUnionNodesInsert.child;
                            tempUnionNodesInsert.child = nextTemp;
                            tempUnionNodesInsert.degree++;
                        } else {

                            if (prevTemp == null) {

                                Nodes = nextTemp;
                            } else {
                                prevTemp.sibling = nextTemp;
                            }
                            tempUnionNodesInsert.parent = nextTemp;
                            tempUnionNodesInsert.sibling = nextTemp.child;
                            nextTemp.child = tempUnionNodesInsert;
                            nextTemp.degree++;
                            tempUnionNodesInsert = nextTemp;
                        }
                    }

                    nextTemp = tempUnionNodesInsert.sibling;
                }
                size++;
            }
        } 
    }


    
    
    
    /*@ requires Nodes != null;
      @ ensures (\exists BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, child + sibling).has(n); n.key == \result);
      @ ensures (\forall BinomialHeapNode n; \reach(Nodes, BinomialHeapNode, child + sibling).has(n); \result <= n.key);
      @*/
    public int findMinimum() {
    
        BinomialHeapNode x = Nodes;
        BinomialHeapNode y = Nodes;
        int min = x.key;

        while (x == null) { //mutGenLimit 1

            if (x.key < min) {
                y = x;
                min = x.key;
            }
            x = x.sibling;
        }

        return y.key;
    }



}
