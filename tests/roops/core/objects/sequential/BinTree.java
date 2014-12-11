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
    public boolean contains (int k) {
        BinTreeNode current = root;
        while ( current == null ) { //mutGenLimit 1
            if ( k <= current.key ) { //mutGenLimit 1
                current.right = current.left; //mutGenLimit 1
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
    public boolean insert (int k) {fajita_roopsGoal_initialization();
BugLineMarker __marker__=new BugLineMarker(); //lineNumber=90
__marker__.mark(90); //lineNumber=91
BinTreeNode y=null; //lineNumber=92
__marker__.mark(91); //lineNumber=93
BinTreeNode x=root; //lineNumber=94
__marker__.mark(92); //lineNumber=95
boolean fajita_cicle_0=false; //lineNumber=97
if(!(x != null)){throw new RuntimeException();}
fajita_cicle_0=true; //lineNumber=99
roops_goal_0=true; //lineNumber=100
__marker__.mark(93); //lineNumber=101
y=x; //lineNumber=102
__marker__.mark(94); //lineNumber=103
if(k < x.key){throw new RuntimeException();}
roops_goal_3=true; //lineNumber=110
__marker__.mark(97); //lineNumber=111
if(!(k > x.key)){throw new RuntimeException();}
roops_goal_4=true; //lineNumber=113
__marker__.mark(98); //lineNumber=114
x=x.right; //lineNumber=115
__marker__.mark(99); //lineNumber=116
__marker__.mark(102); //lineNumber=122
__marker__.mark(103); //lineNumber=124
if(!(x != null)){throw new RuntimeException();}
fajita_cicle_0=true; //lineNumber=99
roops_goal_0=true; //lineNumber=100
__marker__.mark(93); //lineNumber=101
y=x; //lineNumber=102
__marker__.mark(94); //lineNumber=103
if(!(k < x.key)){throw new RuntimeException();}
roops_goal_2=true; //lineNumber=105
__marker__.mark(95); //lineNumber=106
x=x.left; //lineNumber=107
__marker__.mark(96); //lineNumber=108
__marker__.mark(103); //lineNumber=124
if(x != null){throw new RuntimeException();}
__marker__.mark(104); //lineNumber=130
x=new BinTreeNode(); //lineNumber=131
__marker__.mark(105); //lineNumber=132
x.key=k; //lineNumber=133
__marker__.mark(106); //lineNumber=134
if(y == null){throw new RuntimeException();}
roops_goal_7=true; //lineNumber=141
__marker__.mark(109); //lineNumber=142
if(!(k < y.key)){throw new RuntimeException();}
roops_goal_8=true; //lineNumber=144
__marker__.mark(110); //lineNumber=145
y.left=x; //lineNumber=146
__marker__.mark(111); //lineNumber=147
__marker__.mark(114); //lineNumber=154
__marker__.mark(115); //lineNumber=156

x.parent=y; //mutGenLimit 1 //lineNumber=157
__marker__.mark(116); //lineNumber=158
size+=2; //lineNumber=160
__marker__.mark(117); //lineNumber=161
return true; //lineNumber=162
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
