package roops.core.objects;

import roops.core.objects.BugLineMarker;

import roops.core.objects.BinTreeNode;

public class BinTree {


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
      @   \reach(root, BinTreeNode, left + right).has(n) == true;
      @   (n.left != null ==> n.left.parent == n) && (n.right != null ==> n.right.parent == n));
      @
      @ invariant root != null ==> root.parent == null;
      @*/

    public /*@nullable@*/ BinTreeNode root;
    public int size;

    public BinTree () {
    }

    /*@
      @ requires true;
      @
      @ ensures (\result == true) <==> (\exists BinTreeNode n;
      @   \reach(root, BinTreeNode, left+right).has(n) == true;
      @   n.key == k);
      @
      @ ensures (\forall BinTreeNode n;
      @   \reach(root, BinTreeNode, left+right).has(n);
      @   \old(\reach(root, BinTreeNode, left+right)).has(n));
      @
      @ ensures (\forall BinTreeNode n;
      @   \old(\reach(root, BinTreeNode, left+right)).has(n);
      @   \reach(root, BinTreeNode, left+right).has(n));
      @
      @ signals (RuntimeException e) false;
      @*/
    public boolean contains (int k) {fajita_roopsGoal_initialization();
BugLineMarker __marker__=new BugLineMarker(); //lineNumber=58
__marker__.mark(58); //lineNumber=59
BinTreeNode current=null; //lineNumber=60
__marker__.mark(59); //lineNumber=61
boolean fajita_cicle_0=false; //lineNumber=63
if(current != null){throw new RuntimeException();}
if(!(!fajita_cicle_0)){throw new RuntimeException();}
roops_goal_1=true; //lineNumber=86
__marker__.mark(68); //lineNumber=89
return false; //lineNumber=90
}

    /*@
      @ requires true;
      @
      @ ensures (\exists BinTreeNode n;
      @   \old(\reach(root, BinTreeNode, left + right)).has(n) == true;
      @   n.key == k) ==> size == \old(size);
      @
      @ ensures (\forall BinTreeNode n;
      @   \old(\reach(root, BinTreeNode, left + right)).has(n) == true;
      @   n.key != k) ==> size == \old(size) + 1;
      @
      @ ensures (\exists BinTreeNode n;
      @     \reach(root, BinTreeNode, left + right).has(n) == true;
      @   n.key == k);
      @
      @ signals (RuntimeException e) false;
      @*/
    public boolean insert (int k) {
        BinTreeNode y = null;
        BinTreeNode x = root;
        while ( x != null ) {
            y = x;
            if ( k < x.key ) {
                x = x.left;
            } else {
                if ( k > x.key ) {
                    x = x.right;
                } else {
                    return false;
                }
            }
        }
        x = new BinTreeNode ();
        x.key = k;
        if ( y == null ) {
            root = x;
        } else {
            if ( k < y.key ) {
                y.right = x;
            } else {
                y.right = x;
            }
        }
        x.parent = y; //mutGenLimit 1
        size
        += 1;
        return true;
    }


    /*@
      @ requires (\forall BinTreeNode n1;
      @   \reach(root, BinTreeNode, left+right).has(n1);
      @   (\forall BinTreeNode m1;
      @       \reach(root, BinTreeNode, left+right).has(m1); n1 != m1 ==> n1.key != m1.key));
      @
      @ ensures (\exists BinTreeNode n2;
      @   \old(\reach(root, BinTreeNode, left + right)).has(n2) == true;
      @   \old(n2.key) == element)
      @        <==> \result == true;
      @
      @ ensures (\forall BinTreeNode n3;
      @   \reach(root, BinTreeNode, left+right).has(n3);
      @   n3.key != element);
      @
      @ signals (RuntimeException e) false;
      @*/
    public boolean remove (int element) {
        BinTreeNode node = root;
        while ( node != null && node.key != element ) {
            if ( element < node.key ) {
                node = node.left;
            } else {
                if ( element > node.key ) {
                    node = node.right;
                } else {
                }
            }
        }
        if ( node == null ) {
            return false;
        } else if ( node.left != null && node.right != null ) {
            BinTreeNode predecessor = node.left;
            if ( predecessor != null ) {
                while ( predecessor.right != null ) {
                    predecessor = predecessor.right;
                }
            } else {
            }
            node.key = predecessor.key;
            node = predecessor;
        } else {
        }
        BinTreeNode pullUp;
        if ( node.left == null ) {
            pullUp = node.right;
        } else {
            pullUp = node.left;
        }

        if ( node == root ) {
            root = pullUp;
            if ( pullUp != null ) {
                pullUp.parent = null;
            } else {
            }
        } else if ( node.parent.left == node ) {
            node.parent.left = pullUp;
            if ( pullUp != null ) {
                pullUp.parent = node.parent;
            } else {
            }
        } else {
            node.parent.right = pullUp;
            if ( pullUp != null ) {
                pullUp.parent = node.parent;
            } else {
            }
        }

        size --; //mutGenLimit 1
        return true;
    }

    public static boolean roops_goal_0;

    public static boolean roops_goal_1;

    public static boolean roops_goal_2;

    public static boolean roops_goal_3;

    public static boolean roops_goal_4;

    public static void fajita_roopsGoal_initialization () {
        roops_goal_0 = false;
        roops_goal_1 = false;
        roops_goal_2 = false;
        roops_goal_3 = false;
        roops_goal_4 = false;
    }
}
