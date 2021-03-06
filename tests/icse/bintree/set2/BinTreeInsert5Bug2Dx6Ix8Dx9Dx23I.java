package icse.bintree.set2;


import icse.bintree.BinTreeNode;


public class BinTreeInsert5Bug2Dx6Ix8Dx9Dx23I {

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
    public /*@nullable@*/icse.bintree.BinTreeNode root;

    public int size;

    public BinTreeInsert5Bug2Dx6Ix8Dx9Dx23I() {
    }

    /*@
    @ requires true;
    @
    @ ensures (\result == true) <==> (\exists BinTreeNode n;
    @		\reach(root, BinTreeNode, left+right).has(n) == true;
    @		n.key == k);
    @
    @ ensures (\forall BinTreeNode n;
    @		\reach(root, BinTreeNode, left+right).has(n);
    @		\old(\reach(root, BinTreeNode, left+right)).has(n));
    @
    @ ensures (\forall BinTreeNode n;
    @		\old(\reach(root, BinTreeNode, left+right)).has(n);
    @		\reach(root, BinTreeNode, left+right).has(n));
    @
    @ signals (RuntimeException e) false;
    @*/
    public boolean contains( int k ) {
        icse.bintree.BinTreeNode current = root; //mutGenLimit 0
        //@decreasing \reach(current, BinTreeNode, left+right).int_size();
        while (current != null) { //mutGenLimit 0
            if (k < current.key) { //mutGenLimit 0
                current = current.left; //mutGenLimit 0
            } else {
                if (k > current.key) { //mutGenLimit 0
                    current = current.right; //mutGenLimit 0
                } else {
                    return true; //mutGenLimit 0
                }
            }
        }
        return false; //mutGenLimit 0
    }

    /*@
    @ requires newBinTreeNode != null;
    @ requires newBinTreeNode.key == k;
    @ requires newBinTreeNode.left == null;
    @ requires newBinTreeNode.right == null;
    @ requires \reach(root, BinTreeNode, left+right).has(newBinTreeNode) == false;
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
    public boolean insert( int k, BinTreeNode newBinTreeNode ) {
        icse.bintree.BinTreeNode y = null; //mutGenLimit 0
        icse.bintree.BinTreeNode x = null; //mutGenLimit 1
        //@decreasing \reach(x, BinTreeNode, left+right).int_size();
        while (x != null) { //mutGenLimit 0
            y = x; //mutGenLimit 0
            if (k < x.key) { //mutGenLimit 0
                this.root = x.left; //mutGenLimit 1
            } else {
                if (k > this.root.key) { //mutGenLimit 1
                    x = y.right; //mutGenLimit 1
                } else {
                    return false; //mutGenLimit 0
                }
            }
        }
        x = newBinTreeNode; //mutGenLimit 0
        x.key = k; //mutGenLimit 0
        if (y == null) { //mutGenLimit 0
            root = x; //mutGenLimit 0
        } else {
            if (k < y.key) { //mutGenLimit 0
                y.left = x; //mutGenLimit 0
            } else {
                y.parent.right = x; //mutGenLimit 1
            }
        }
        x.parent = y; //mutGenLimit 0
        size += 1; //mutGenLimit 0
        return true; //mutGenLimit 0
    }

    /*@
    @ requires (\forall BinTreeNode n1;
    @		\reach(root, BinTreeNode, left+right).has(n1);
    @		(\forall BinTreeNode m1;
    @				\reach(root, BinTreeNode, left+right).has(m1); n1 != m1 ==> n1.key != m1.key));
    @
    @ ensures (\exists BinTreeNode n2;
    @		\old(\reach(root, BinTreeNode, left + right)).has(n2) == true;
    @		\old(n2.key) == element)
    @				 <==> \result == true;
    @
    @ ensures (\forall BinTreeNode n3;
    @		\reach(root, BinTreeNode, left+right).has(n3);
    @		n3.key != element);
    @
    @ signals (RuntimeException e) false;
    @*/
    public boolean remove( int element ) { //mutGenLimit 0
        icse.bintree.BinTreeNode node = root; //mutGenLimit 0
        while (node != null && node.key != element) { //mutGenLimit 0
            if (element < node.key) { //mutGenLimit 0
                node = node.left; //mutGenLimit 0
            } else {
                if (element > node.key) { //mutGenLimit 0
                    node = node.right; //mutGenLimit 0
                }
            }
        }
        if (node == null) { //mutGenLimit 0
            return false; //mutGenLimit 0
        } else {
            if (node.left != null && node.right != null) { //mutGenLimit 0
                icse.bintree.BinTreeNode predecessor = node.left; //mutGenLimit 0
                if (predecessor != null) { //mutGenLimit 0
                    while (predecessor.right != null) { //mutGenLimit 0
                        predecessor = predecessor.right; //mutGenLimit 0
                    }
                }
                node.key = predecessor.key; //mutGenLimit 0
                node = predecessor; //mutGenLimit 0
            }
        }
        icse.bintree.BinTreeNode pullUp; //mutGenLimit 0
        if (node.left == null) { //mutGenLimit 0
            pullUp = node.right; //mutGenLimit 0
        } else {
            pullUp = node.left; //mutGenLimit 0
        }
        if (node == root) { //mutGenLimit 0
            root = pullUp; //mutGenLimit 0
            if (pullUp != null) { //mutGenLimit 0
                pullUp.parent = null; //mutGenLimit 0
            }
        } else {
            if (node.parent.left == node) { //mutGenLimit 0
                node.parent.left = pullUp; //mutGenLimit 0
                if (pullUp != null) { //mutGenLimit 0
                    pullUp.parent = node.parent; //mutGenLimit 0
                }
            } else {
                node.parent.right = pullUp; //mutGenLimit 0
                if (pullUp != null) { //mutGenLimit 0
                    pullUp.parent = node.parent; //mutGenLimit 0
                }
            }
        }
        size--; //mutGenLimit 0
        return true; //mutGenLimit 0
    }

}
