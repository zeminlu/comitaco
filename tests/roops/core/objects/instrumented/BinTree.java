package roops.core.objects;

import roops.core.objects.BugLineMarker;

import roops.core.objects.BinTreeNode;
import java.io.IOException;
import ar.edu.taco.utils.FileUtils;
import java.util.NoSuchElementException;

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
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_contains",
							"fajita_roopsGoal_initialization();\n");
		} catch (IOException ioexception) {
		}
		fajita_roopsGoal_initialization();
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_contains",
							"BugLineMarker __marker__=new BugLineMarker(); //lineNumber=58\n");
		} catch (IOException ioexception) {
		}
		BugLineMarker __marker__ = new BugLineMarker();
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_contains",
							"__marker__.mark(58); //lineNumber=59\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(58);
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_contains",
							"BinTreeNode current=null; //lineNumber=60\n");
		} catch (IOException ioexception) {
		}
		BinTreeNode current = null;
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_contains",
							"__marker__.mark(59); //lineNumber=61\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(59);
        {
            try {
				FileUtils
						.appendToFile(
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_contains",
								"boolean fajita_cicle_0=false; //lineNumber=63\n");
			} catch (IOException ioexception) {
			}
			boolean fajita_cicle_0 = false;
            while ( current != null ) {
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_contains",
									"if(!(current != null)){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_contains",
									"fajita_cicle_0=true; //lineNumber=65\n");
				} catch (IOException ioexception) {
				}
				fajita_cicle_0 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_contains",
									"\nroops_goal_0=true; //mutGenLimit 1 //lineNumber=66\n");
				} catch (IOException ioexception) {
				}
				roops_goal_0 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_contains",
									"__marker__.mark(60); //lineNumber=67\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(60);
                if ( k < current.key ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_contains",
										"\nroops_goal_2=true; //mutGenLimit 1 //lineNumber=69\n");
					} catch (IOException ioexception) {
					}
					roops_goal_2 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_contains",
										"__marker__.mark(61); //lineNumber=70\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(61);
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_contains",
										"\ncurrent=current.left; //mutGenLimit 1 //lineNumber=71\n");
					} catch (IOException ioexception) {
					}
					current = current.left;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_contains",
										"__marker__.mark(62); //lineNumber=72\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(62);
                } else try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_contains",
									"if(k < current.key){throw new RuntimeException()}\n");
				} catch (IOException ioexception) {
				}
				if (k > current.key) {
					roops_goal_3 = true;
					__marker__.mark(63);
					current = current.right;
					__marker__.mark(64);
				} else {
					roops_goal_4 = true;
					__marker__.mark(65);
					return true;
				}
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_contains",
									"__marker__.mark(67); //lineNumber=84\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(67);
            }
			try {
				FileUtils
						.appendToFile(
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_contains",
								"if(current != null){throw new RuntimeException();}\n");
			} catch (IOException ioexception) {
			}
            if ( ! fajita_cicle_0 ) {
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_contains",
									"if(!(!fajita_cicle_0)){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_contains",
									"roops_goal_1=true; //lineNumber=87\n");
				} catch (IOException ioexception) {
				}
				roops_goal_1 = true;
            }
        }
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_contains",
							"__marker__.mark(68); //lineNumber=90\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(68);
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_contains",
							"return false; //lineNumber=91\n");
		} catch (IOException ioexception) {
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
