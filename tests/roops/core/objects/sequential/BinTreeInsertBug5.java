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
    public boolean insert (int k) {fajita_roopsGoal_initialization();
BugLineMarker __marker__=new BugLineMarker(); //lineNumber=88
__marker__.mark88(); //lineNumber=89
BinTreeNode y=null; //lineNumber=90
__marker__.mark89(); //lineNumber=91
BinTreeNode x=root; //lineNumber=92
__marker__.mark90(); //lineNumber=93
boolean fajita_cicle_0=false; //lineNumber=95
if(!(x != null)){throw new RuntimeException();}
fajita_cicle_0=true; //lineNumber=97
roops_goal_0=true; //lineNumber=98
__marker__.mark91(); //lineNumber=99
y=x; //lineNumber=100
__marker__.mark92(); //lineNumber=101

roops_goal_2=true; //mutGenLimit 1 //lineNumber=103
__marker__.mark93(); //lineNumber=104
x=x.left; //lineNumber=105
__marker__.mark94(); //lineNumber=106
__marker__.mark101(); //lineNumber=122
if(!(x != null)){throw new RuntimeException();}
fajita_cicle_0=true; //lineNumber=97
roops_goal_0=true; //lineNumber=98
__marker__.mark91(); //lineNumber=99
y=x; //lineNumber=100
__marker__.mark92(); //lineNumber=101

roops_goal_2=true; //mutGenLimit 1 //lineNumber=103
__marker__.mark93(); //lineNumber=104
x=x.left; //lineNumber=105
__marker__.mark94(); //lineNumber=106
__marker__.mark101(); //lineNumber=122
if(x != null){throw new RuntimeException();}
__marker__.mark102(); //lineNumber=128
x=new BinTreeNode(); //lineNumber=129
__marker__.mark103(); //lineNumber=130
x.key=k; //lineNumber=131
__marker__.mark104(); //lineNumber=132
if(y == null){throw new RuntimeException();}
roops_goal_7=true; //lineNumber=139
__marker__.mark107(); //lineNumber=140
if(k < y.key){throw new RuntimeException();}
roops_goal_9=true; //lineNumber=147
__marker__.mark110(); //lineNumber=148
y.right=x; //lineNumber=149
__marker__.mark111(); //lineNumber=150
__marker__.mark112(); //lineNumber=152
__marker__.mark113(); //lineNumber=154
x.parent=y; //lineNumber=155
__marker__.mark114(); //lineNumber=156
size+=1; //lineNumber=157
__marker__.mark115(); //lineNumber=158
return true; //lineNumber=159
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
