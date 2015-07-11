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
        {
            if ( current != null ) {
                if ( k < current.key ) {
                    current = current.left;
                } else if ( k > current.key ) {
                    current = current.right;
                } else {
                    return true;
                }
            } else {
            }
            if ( current != null ) {
                if ( k < current.key ) {
                    current = current.left;
                } else if ( k > current.key ) {
                    current = current.right;
                } else {
                    return true;
                }
            } else {
            }
            if ( current != null ) {
                if ( k < current.key ) {
                    current = current.left;
                } else if ( k > current.key ) {
                    current = current.right;
                } else {
                    return true;
                }
            } else {
            }
            if ( current != null ) {
                if ( k < current.key ) {
                    current = current.left;
                } else if ( k > current.key ) {
                    current = current.right;
                } else {
                    return true;
                }
            } else {
            }
            if ( current != null ) {
                if ( k < current.key ) {
                    current = current.left;
                } else if ( k > current.key ) {
                    current = current.right;
                } else {
                    return true;
                }
            } else {
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
BugLineMarker __marker__=new BugLineMarker(); //lineNumber=131
__marker__.mark88(); //lineNumber=132
BinTreeNode y=null; //lineNumber=133
__marker__.mark89(); //lineNumber=134
BinTreeNode x=root; //lineNumber=135
__marker__.mark90(); //lineNumber=136
if(!(x != null)){throw new RuntimeException();}
roops_goal_0=true; //lineNumber=139
__marker__.mark91(); //lineNumber=140
y=x; //lineNumber=141
__marker__.mark92(); //lineNumber=142

roops_goal_2=true; //mutGenLimit 1 //lineNumber=144
__marker__.mark93(); //lineNumber=145
x=x.left; //lineNumber=146
__marker__.mark94(); //lineNumber=147
__marker__.mark101(); //lineNumber=163
if(x != null){throw new RuntimeException();}
roops_goal_7=true; //lineNumber=194
if(x != null){throw new RuntimeException();}
roops_goal_13=true; //lineNumber=223
if(x != null){throw new RuntimeException();}
roops_goal_19=true; //lineNumber=252
if(x != null){throw new RuntimeException();}
roops_goal_25=true; //lineNumber=281
__marker__.mark102(); //lineNumber=284
x=new BinTreeNode(); //lineNumber=285
__marker__.mark103(); //lineNumber=286
x.key=k; //lineNumber=287
__marker__.mark104(); //lineNumber=288
if(y == null){throw new RuntimeException();}
roops_goal_31=true; //lineNumber=295
__marker__.mark107(); //lineNumber=296
if(k < y.key){throw new RuntimeException();}
roops_goal_33=true; //lineNumber=303
__marker__.mark110(); //lineNumber=304
y.right=x; //lineNumber=305
__marker__.mark111(); //lineNumber=306
__marker__.mark112(); //lineNumber=308
__marker__.mark113(); //lineNumber=310
x.parent=y; //lineNumber=311
__marker__.mark114(); //lineNumber=312
size+=1; //lineNumber=313
__marker__.mark115(); //lineNumber=314
return true; //lineNumber=315
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
        {
            if ( node != null && node.key != element ) {
                if ( element < node.key ) {
                    node = node.left;
                } else {
                    if ( element > node.key ) {
                        node = node.right;
                    } else {
                    }
                }
            } else {
            }
            if ( node != null && node.key != element ) {
                if ( element < node.key ) {
                    node = node.left;
                } else {
                    if ( element > node.key ) {
                        node = node.right;
                    } else {
                    }
                }
            } else {
            }
            if ( node != null && node.key != element ) {
                if ( element < node.key ) {
                    node = node.left;
                } else {
                    if ( element > node.key ) {
                        node = node.right;
                    } else {
                    }
                }
            } else {
            }
            if ( node != null && node.key != element ) {
                if ( element < node.key ) {
                    node = node.left;
                } else {
                    if ( element > node.key ) {
                        node = node.right;
                    } else {
                    }
                }
            } else {
            }
            if ( node != null && node.key != element ) {
                if ( element < node.key ) {
                    node = node.left;
                } else {
                    if ( element > node.key ) {
                        node = node.right;
                    } else {
                    }
                }
            } else {
            }
        }
        if ( node == null ) {
            return false;
        } else if ( node.left != null && node.right != null ) {
            BinTreeNode predecessor = node.left;
            if ( predecessor != null ) {
                {
                    if ( predecessor.right != null ) {
                        predecessor = predecessor.right;
                    } else {
                    }
                    if ( predecessor.right != null ) {
                        predecessor = predecessor.right;
                    } else {
                    }
                    if ( predecessor.right != null ) {
                        predecessor = predecessor.right;
                    } else {
                    }
                    if ( predecessor.right != null ) {
                        predecessor = predecessor.right;
                    } else {
                    }
                    if ( predecessor.right != null ) {
                        predecessor = predecessor.right;
                    } else {
                    }
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

    public static boolean roops_goal_10;

    public static boolean roops_goal_11;

    public static boolean roops_goal_12;

    public static boolean roops_goal_13;

    public static boolean roops_goal_14;

    public static boolean roops_goal_15;

    public static boolean roops_goal_16;

    public static boolean roops_goal_17;

    public static boolean roops_goal_18;

    public static boolean roops_goal_19;

    public static boolean roops_goal_20;

    public static boolean roops_goal_21;

    public static boolean roops_goal_22;

    public static boolean roops_goal_23;

    public static boolean roops_goal_24;

    public static boolean roops_goal_25;

    public static boolean roops_goal_26;

    public static boolean roops_goal_27;

    public static boolean roops_goal_28;

    public static boolean roops_goal_29;

    public static boolean roops_goal_30;

    public static boolean roops_goal_31;

    public static boolean roops_goal_32;

    public static boolean roops_goal_33;

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
        roops_goal_10 = false;
        roops_goal_11 = false;
        roops_goal_12 = false;
        roops_goal_13 = false;
        roops_goal_14 = false;
        roops_goal_15 = false;
        roops_goal_16 = false;
        roops_goal_17 = false;
        roops_goal_18 = false;
        roops_goal_19 = false;
        roops_goal_20 = false;
        roops_goal_21 = false;
        roops_goal_22 = false;
        roops_goal_23 = false;
        roops_goal_24 = false;
        roops_goal_25 = false;
        roops_goal_26 = false;
        roops_goal_27 = false;
        roops_goal_28 = false;
        roops_goal_29 = false;
        roops_goal_30 = false;
        roops_goal_31 = false;
        roops_goal_32 = false;
        roops_goal_33 = false;
    }
}
