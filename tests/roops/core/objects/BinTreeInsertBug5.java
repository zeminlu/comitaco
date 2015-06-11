package roops.core.objects;

import roops.core.objects.BinTreeNode;
import roops.core.objects.BugLineMarker;

public class BinTreeInsertBug5 {


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

    public /*@nullable@*/ BinTreeNode root;
    public int size;

    public BinTreeInsertBug5 () {
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
    public boolean contains (int k) {
        BinTreeNode current = root;
        while (  current != null ) {
            if ( k < current.key ) {
                current = current.left;
            } else if ( k > current.key ) {
                current = current.right;
            } else {
                return true;
            }
        }
        return false;
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
    public boolean insert (int k) {
        fajita_roopsGoal_initialization ();
        BugLineMarker __marker__ = new BugLineMarker ();
        __marker__.mark88 ();
        BinTreeNode y = null;
        __marker__.mark89 ();
        BinTreeNode x = root;
        __marker__.mark90 ();
        {
            boolean fajita_cicle_0 = false;
            while (  x != null ) {
                fajita_cicle_0 = true;
                roops_goal_0 = true;
                __marker__.mark91 ();
                y = x;
                __marker__.mark92 ();
                if ( k > x.key ) {
                    roops_goal_2 = true; //mutGenLimit 1
                    __marker__.mark93 ();
                    x = x.left;
                    __marker__.mark94 ();
                } else {
                    roops_goal_3 = true;
                    __marker__.mark95 ();
                    if ( k > x.key ) {
                        roops_goal_4 = true;
                        __marker__.mark96 ();
                        x = x.right;
                        __marker__.mark97 ();
                    } else {
                        roops_goal_5 = true;
                        __marker__.mark98 ();
                        return false;
                    }
                    __marker__.mark100 ();
                }
                __marker__.mark101 ();
            }
            if ( ! fajita_cicle_0 ) {
                roops_goal_1 = true;
            }
        }
        __marker__.mark102 ();
        x = new BinTreeNode ();
        __marker__.mark103 ();
        x.key = k;
        __marker__.mark104 ();
        if ( y == null ) {
            roops_goal_6 = true;
            __marker__.mark105 ();
            root = x;
            __marker__.mark106 ();
        } else {
            roops_goal_7 = true;
            __marker__.mark107 ();
            if ( k < y.key ) {
                roops_goal_8 = true;
                __marker__.mark108 ();
                y.left = x;
                __marker__.mark109 ();
            } else {
                roops_goal_9 = true;
                __marker__.mark110 ();
                y.right = x;
                __marker__.mark111 ();
            }
            __marker__.mark112 ();
        }
        __marker__.mark113 ();
        x.parent = y;
        __marker__.mark114 ();
        size += 1;
        __marker__.mark115 ();
        return true;
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
    public boolean remove (int element) {
        BinTreeNode node = root;
        while (  node != null && node.key != element ) {
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
                while (  predecessor.right != null ) {
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

        size ++; //mutGenLimit 1
        return true;
    }

    public static boolean roops_goal_0;

    public static boolean roops_goal_1;

    public static boolean roops_goal_2;

    public static boolean roops_goal_3;

    public static boolean roops_goal_4;

    public static boolean roops_goal_5;

    public static boolean roops_goal_6;

    public static boolean roops_goal_7;

    public static boolean roops_goal_8;

    public static boolean roops_goal_9;

    public static void fajita_roopsGoal_initialization () {
        roops_goal_0 = false;
        roops_goal_1 = false;
        roops_goal_2 = false;
        roops_goal_3 = false;
        roops_goal_4 = false;
        roops_goal_5 = false;
        roops_goal_6 = false;
        roops_goal_7 = false;
        roops_goal_8 = false;
        roops_goal_9 = false;
    }
}