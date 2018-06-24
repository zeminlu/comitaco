package ase2016.bintree;

/**
* BinTreeInsert1Bug6 is an implementation of binary search trees with four bugs
* injected in lines 6 and 4 and 21 and 7 of method insert. First bug replaces:
* if (x.key > k) 
* with
* if (x.key < k) 
* Second bug replaces:
* while (x != null)
* with
* while (root != null)
* * Third bug replaces:
* if (k < y.key)
* with 
* if (k > y.key)
* Fourth bug replaces:
* x = x.left;
* with
* x = x.right;
* The bugs to be inserted and the affected lines were randomly chosen, from a set of
* real programming mistakes in binary tree implementations. These particular ones appear in:
* https://www.quora.com/Why-am-I-getting-null-pointer-exception-when-I-try-to-implement-my-own-Tree-class-in-Java
* http://www.geeksforgeeks.org/a-program-to-check-if-a-binary-tree-is-bst-or-not/
* http://www.geeksforgeeks.org/write-an-efficient-c-function-to-convert-a-tree-into-its-mirror-tree/
*/

import ase2016.bintree.BinTreeNode;


public class BinTreeInsert4Bugs6x4x21x7 {

    /*@
    @ invariant (\forall BinTreeNode n;
    @     \reach(root, BinTreeNode, left + right).has(n) == true;
    @     \reach(n.right, BinTreeNode, right + left).has(n) == false &&
    @     \reach(n.left, BinTreeNode, left + right).has(n) == false);
    @
    @ invariant (\forall BinTreeNode n;
    @     \reach(root, BinTreeNode, left + right).has(n) == true;
    @     (\forall BinTreeNode m;
    @     \reach(n.left, BinTreeNode, left + right).has(m) == true;
    @     m.key <= n.key) &&
    @     (\forall BinTreeNode m;
    @     \reach(n.right, BinTreeNode, left + right).has(m) == true;
    @     m.key > n.key));
    @
    @ invariant size == \reach(root, BinTreeNode, left + right).int_size();
    @
    @ invariant (\forall BinTreeNode n;
    @	  \reach(root, BinTreeNode, left + right).has(n) == true;
    @	  (n.left != null ==> n.left.parent == n) && (n.right != null ==> n.right.parent == n));
    @
    @ invariant root != null ==> root.parent == null;
    @*/
    public /*@nullable@*/ase2016.bintree.BinTreeNode root;

    public int size;

    public BinTreeInsert4Bugs6x4x21x7() {
    }

    /*@
    @ requires true;
    @
    @ ensures (\exists BinTreeNode n;
    @		\old(\reach(root, BinTreeNode, left + right)).has(n) == true;
    @  	n.key == k) ==> size == \old(size);
    @
    @	ensures (\forall BinTreeNode n;
    @		\old(\reach(root, BinTreeNode, left + right)).has(n) == true;
    @  	n.key != k) ==> size == \old(size) + 1;
    @
    @ ensures (\exists BinTreeNode n;
    @     \reach(root, BinTreeNode, left + right).has(n) == true;
    @		n.key == k);
    @
    @ signals (RuntimeException e) false;
    @*/
    public boolean insert( int k ) {
        ase2016.bintree.BinTreeNode y = null; //mutGenLimit 0
        ase2016.bintree.BinTreeNode x = this.root; //mutGenLimit 0
        //@decreasing \reach(x, BinTreeNode, left+right).int_size();
        while (root != null) { //mutGenLimit 1
            y = x; //mutGenLimit 0
            if (x.key < k) { //mutGenLimit 1
                x = x.right; //mutGenLimit 1
            } else {
                if (k > x.key) { //mutGenLimit 0
                    x = x.right; //mutGenLimit 0
                } else {
                    return false; //mutGenLimit 0
                }
            }
        }
        x = new ase2016.bintree.BinTreeNode(); //mutGenLimit 0
        x.key = k; //mutGenLimit 0
        if (y == null) { //mutGenLimit 0
            root = x; //mutGenLimit 0
        } else {
            if (k > y.key) { //mutGenLimit 1
                y.left = x; //mutGenLimit 0
            } else {
                y.right = x; //mutGenLimit 0
            }
        }
        x.parent = y; //mutGenLimit 0
        size += 1; //mutGenLimit 0
        return true; //mutGenLimit 0
    }

}
