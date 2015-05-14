package roops.core.objects;


// Authors: Marcelo Frias and Juan Pablo Galeotti

//@ nullable_by_default;
public class BinomialHeapNode {

    public int key; 

    public int degree; // depth of the binomial tree having the current node as its root

    public /*@ nullable @*/BinomialHeapNode parent; // pointer to the parent of the current node

    public /*@ nullable @*/BinomialHeapNode sibling; // pointer to the next binomial tree in the list

    public /*@ nullable @*/BinomialHeapNode child; // pointer to the first child of the current node

    public BinomialHeapNode () {}

    public BinomialHeapNode reverse(/*@nullable@*/ BinomialHeapNode sibl) {
        BinomialHeapNode ret;
        if (sibling != null)
            ret = sibling.reverse(this);
        else
            ret = this;
        sibling = sibl;
        return ret;
    }


    public BinomialHeapNode findMinNode() {
        BinomialHeapNode x = this, y = this;
        int min = x.key;

        while (x != null) {
            if (x.key < min) {
                y = x;
                min = x.key;
            }
            x = x.sibling;
        }

        return y;
    }


}
