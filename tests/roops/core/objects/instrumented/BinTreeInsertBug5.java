package roops.core.objects;

import roops.core.objects.BinTreeNode;
import roops.core.objects.BugLineMarker;
import java.io.IOException;
import ar.edu.taco.utils.FileUtils;
import java.util.NoSuchElementException;

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
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
							"fajita_roopsGoal_initialization();\n");
		} catch (IOException ioexception) {
		}
		fajita_roopsGoal_initialization();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
							"BugLineMarker __marker__=new BugLineMarker(); //lineNumber=88\n");
		} catch (IOException ioexception) {
		}
		BugLineMarker __marker__ = new BugLineMarker();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
							"__marker__.mark88(); //lineNumber=89\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark88();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
							"BinTreeNode y=null; //lineNumber=90\n");
		} catch (IOException ioexception) {
		}
		BinTreeNode y = null;
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
							"__marker__.mark89(); //lineNumber=91\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark89();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
							"BinTreeNode x=root; //lineNumber=92\n");
		} catch (IOException ioexception) {
		}
		BinTreeNode x = root;
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
							"__marker__.mark90(); //lineNumber=93\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark90();
        {
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
								"boolean fajita_cicle_0=false; //lineNumber=95\n");
			} catch (IOException ioexception) {
			}
			boolean fajita_cicle_0 = false;
            while (  x != null ) {
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"if(!(x != null)){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"fajita_cicle_0=true; //lineNumber=97\n");
				} catch (IOException ioexception) {
				}
				fajita_cicle_0 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"roops_goal_0=true; //lineNumber=98\n");
				} catch (IOException ioexception) {
				}
				roops_goal_0 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"__marker__.mark91(); //lineNumber=99\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark91();
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"y=x; //lineNumber=100\n");
				} catch (IOException ioexception) {
				}
				y = x;
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"__marker__.mark92(); //lineNumber=101\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark92();
                if ( k > x.key ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"\nroops_goal_2=true; //mutGenLimit 1 //lineNumber=103\n");
					} catch (IOException ioexception) {
					}
					roops_goal_2 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"__marker__.mark93(); //lineNumber=104\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark93();
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"x=x.left; //lineNumber=105\n");
					} catch (IOException ioexception) {
					}
					x = x.left;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"__marker__.mark94(); //lineNumber=106\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark94();
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"if(k > x.key){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"roops_goal_3=true; //lineNumber=108\n");
					} catch (IOException ioexception) {
					}
					roops_goal_3 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"__marker__.mark95(); //lineNumber=109\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark95();
                    if ( k > x.key ) {
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
											"if(!(k > x.key)){throw new RuntimeException();}\n");
						} catch (IOException ioexception) {
						}
						try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
											"roops_goal_4=true; //lineNumber=111\n");
						} catch (IOException ioexception) {
						}
						roops_goal_4 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
											"__marker__.mark96(); //lineNumber=112\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark96();
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
											"x=x.right; //lineNumber=113\n");
						} catch (IOException ioexception) {
						}
						x = x.right;
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
											"__marker__.mark97(); //lineNumber=114\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark97();
                    } else {
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
											"if(k > x.key){throw new RuntimeException();}\n");
						} catch (IOException ioexception) {
						}
						try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
											"roops_goal_5=true; //lineNumber=116\n");
						} catch (IOException ioexception) {
						}
						roops_goal_5 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
											"__marker__.mark98(); //lineNumber=117\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark98();
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
											"return false; //lineNumber=118\n");
						} catch (IOException ioexception) {
						}
						return false;
                    }
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"__marker__.mark100(); //lineNumber=120\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark100();
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"__marker__.mark101(); //lineNumber=122\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark101();
            }
			try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
								"if(x != null){throw new RuntimeException();}\n");
			} catch (IOException ioexception) {
			}
            if ( ! fajita_cicle_0 ) {
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"if(!(!fajita_cicle_0)){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"roops_goal_1=true; //lineNumber=125\n");
				} catch (IOException ioexception) {
				}
				roops_goal_1 = true;
            }
        }
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
							"__marker__.mark102(); //lineNumber=128\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark102();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
							"x=new BinTreeNode(); //lineNumber=129\n");
		} catch (IOException ioexception) {
		}
		x = new BinTreeNode();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
							"__marker__.mark103(); //lineNumber=130\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark103();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
							"x.key=k; //lineNumber=131\n");
		} catch (IOException ioexception) {
		}
		x.key = k;
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
							"__marker__.mark104(); //lineNumber=132\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark104();
        if ( y == null ) {
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
								"if(!(y == null)){throw new RuntimeException();}\n");
			} catch (IOException ioexception) {
			}
			try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
								"roops_goal_6=true; //lineNumber=134\n");
			} catch (IOException ioexception) {
			}
			roops_goal_6 = true;
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
								"__marker__.mark105(); //lineNumber=135\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark105();
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
								"root=x; //lineNumber=136\n");
			} catch (IOException ioexception) {
			}
			root = x;
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
								"__marker__.mark106(); //lineNumber=137\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark106();
        } else {
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
								"if(y == null){throw new RuntimeException();}\n");
			} catch (IOException ioexception) {
			}
			try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
								"roops_goal_7=true; //lineNumber=139\n");
			} catch (IOException ioexception) {
			}
			roops_goal_7 = true;
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
								"__marker__.mark107(); //lineNumber=140\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark107();
            if ( k < y.key ) {
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"if(!(k < y.key)){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"roops_goal_8=true; //lineNumber=142\n");
				} catch (IOException ioexception) {
				}
				roops_goal_8 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"__marker__.mark108(); //lineNumber=143\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark108();
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"y.left=x; //lineNumber=144\n");
				} catch (IOException ioexception) {
				}
				y.left = x;
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"__marker__.mark109(); //lineNumber=145\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark109();
            } else {
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"if(k < y.key){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"roops_goal_9=true; //lineNumber=147\n");
				} catch (IOException ioexception) {
				}
				roops_goal_9 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"__marker__.mark110(); //lineNumber=148\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark110();
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"y.right=x; //lineNumber=149\n");
				} catch (IOException ioexception) {
				}
				y.right = x;
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"__marker__.mark111(); //lineNumber=150\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark111();
            }
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
								"__marker__.mark112(); //lineNumber=152\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark112();
        }
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
							"__marker__.mark113(); //lineNumber=154\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark113();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
							"x.parent=y; //lineNumber=155\n");
		} catch (IOException ioexception) {
		}
		x.parent = y;
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
							"__marker__.mark114(); //lineNumber=156\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark114();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
							"size+=1; //lineNumber=157\n");
		} catch (IOException ioexception) {
		}
		size += 1;
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
							"__marker__.mark115(); //lineNumber=158\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark115();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
							"return true; //lineNumber=159\n");
		} catch (IOException ioexception) {
		}
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
