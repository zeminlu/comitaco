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
							"BugLineMarker __marker__=new BugLineMarker(); //lineNumber=131\n");
		} catch (IOException ioexception) {
		}
		BugLineMarker __marker__ = new BugLineMarker();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
							"__marker__.mark88(); //lineNumber=132\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark88();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
							"BinTreeNode y=null; //lineNumber=133\n");
		} catch (IOException ioexception) {
		}
		BinTreeNode y = null;
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
							"__marker__.mark89(); //lineNumber=134\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark89();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
							"BinTreeNode x=root; //lineNumber=135\n");
		} catch (IOException ioexception) {
		}
		BinTreeNode x = root;
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
							"__marker__.mark90(); //lineNumber=136\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark90();
        {
            if ( x != null ) {
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
									"roops_goal_0=true; //lineNumber=139\n");
				} catch (IOException ioexception) {
				}
				roops_goal_0 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"__marker__.mark91(); //lineNumber=140\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark91();
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"y=x; //lineNumber=141\n");
				} catch (IOException ioexception) {
				}
				y = x;
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"__marker__.mark92(); //lineNumber=142\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark92();
                if ( k > x.key ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"\nroops_goal_2=true; //mutGenLimit 1 //lineNumber=144\n");
					} catch (IOException ioexception) {
					}
					roops_goal_2 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"__marker__.mark93(); //lineNumber=145\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark93();
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"x=x.left; //lineNumber=146\n");
					} catch (IOException ioexception) {
					}
					x = x.left;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"__marker__.mark94(); //lineNumber=147\n");
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
										"roops_goal_3=true; //lineNumber=149\n");
					} catch (IOException ioexception) {
					}
					roops_goal_3 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"__marker__.mark95(); //lineNumber=150\n");
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
											"roops_goal_4=true; //lineNumber=152\n");
						} catch (IOException ioexception) {
						}
						roops_goal_4 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
											"__marker__.mark96(); //lineNumber=153\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark96();
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
											"x=x.right; //lineNumber=154\n");
						} catch (IOException ioexception) {
						}
						x = x.right;
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
											"__marker__.mark97(); //lineNumber=155\n");
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
											"roops_goal_5=true; //lineNumber=157\n");
						} catch (IOException ioexception) {
						}
						roops_goal_5 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
											"__marker__.mark98(); //lineNumber=158\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark98();
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
											"return false; //lineNumber=159\n");
						} catch (IOException ioexception) {
						}
						return false;
                    }
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"__marker__.mark100(); //lineNumber=161\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark100();
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"__marker__.mark101(); //lineNumber=163\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark101();
            } else {
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"if(x != null){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"roops_goal_1=true; //lineNumber=165\n");
				} catch (IOException ioexception) {
				}
				roops_goal_1 = true;
            }
            if ( x != null ) {
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
									"roops_goal_6=true; //lineNumber=168\n");
				} catch (IOException ioexception) {
				}
				roops_goal_6 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"__marker__.mark91(); //lineNumber=169\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark91();
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"y=x; //lineNumber=170\n");
				} catch (IOException ioexception) {
				}
				y = x;
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"__marker__.mark92(); //lineNumber=171\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark92();
                if ( k > x.key ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"\nroops_goal_8=true; //mutGenLimit 1 //lineNumber=173\n");
					} catch (IOException ioexception) {
					}
					roops_goal_8 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"__marker__.mark93(); //lineNumber=174\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark93();
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"x=x.left; //lineNumber=175\n");
					} catch (IOException ioexception) {
					}
					x = x.left;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"__marker__.mark94(); //lineNumber=176\n");
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
										"roops_goal_9=true; //lineNumber=178\n");
					} catch (IOException ioexception) {
					}
					roops_goal_9 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"__marker__.mark95(); //lineNumber=179\n");
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
											"roops_goal_10=true; //lineNumber=181\n");
						} catch (IOException ioexception) {
						}
						roops_goal_10 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
											"__marker__.mark96(); //lineNumber=182\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark96();
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
											"x=x.right; //lineNumber=183\n");
						} catch (IOException ioexception) {
						}
						x = x.right;
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
											"__marker__.mark97(); //lineNumber=184\n");
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
											"roops_goal_11=true; //lineNumber=186\n");
						} catch (IOException ioexception) {
						}
						roops_goal_11 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
											"__marker__.mark98(); //lineNumber=187\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark98();
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
											"return false; //lineNumber=188\n");
						} catch (IOException ioexception) {
						}
						return false;
                    }
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"__marker__.mark100(); //lineNumber=190\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark100();
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"__marker__.mark101(); //lineNumber=192\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark101();
            } else {
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"if(x != null){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"roops_goal_7=true; //lineNumber=194\n");
				} catch (IOException ioexception) {
				}
				roops_goal_7 = true;
            }
            if ( x != null ) {
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
									"roops_goal_12=true; //lineNumber=197\n");
				} catch (IOException ioexception) {
				}
				roops_goal_12 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"__marker__.mark91(); //lineNumber=198\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark91();
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"y=x; //lineNumber=199\n");
				} catch (IOException ioexception) {
				}
				y = x;
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"__marker__.mark92(); //lineNumber=200\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark92();
                if ( k > x.key ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"\nroops_goal_14=true; //mutGenLimit 1 //lineNumber=202\n");
					} catch (IOException ioexception) {
					}
					roops_goal_14 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"__marker__.mark93(); //lineNumber=203\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark93();
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"x=x.left; //lineNumber=204\n");
					} catch (IOException ioexception) {
					}
					x = x.left;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"__marker__.mark94(); //lineNumber=205\n");
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
										"roops_goal_15=true; //lineNumber=207\n");
					} catch (IOException ioexception) {
					}
					roops_goal_15 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"__marker__.mark95(); //lineNumber=208\n");
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
											"roops_goal_16=true; //lineNumber=210\n");
						} catch (IOException ioexception) {
						}
						roops_goal_16 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
											"__marker__.mark96(); //lineNumber=211\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark96();
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
											"x=x.right; //lineNumber=212\n");
						} catch (IOException ioexception) {
						}
						x = x.right;
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
											"__marker__.mark97(); //lineNumber=213\n");
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
											"roops_goal_17=true; //lineNumber=215\n");
						} catch (IOException ioexception) {
						}
						roops_goal_17 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
											"__marker__.mark98(); //lineNumber=216\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark98();
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
											"return false; //lineNumber=217\n");
						} catch (IOException ioexception) {
						}
						return false;
                    }
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"__marker__.mark100(); //lineNumber=219\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark100();
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"__marker__.mark101(); //lineNumber=221\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark101();
            } else {
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"if(x != null){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"roops_goal_13=true; //lineNumber=223\n");
				} catch (IOException ioexception) {
				}
				roops_goal_13 = true;
            }
            if ( x != null ) {
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
									"roops_goal_18=true; //lineNumber=226\n");
				} catch (IOException ioexception) {
				}
				roops_goal_18 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"__marker__.mark91(); //lineNumber=227\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark91();
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"y=x; //lineNumber=228\n");
				} catch (IOException ioexception) {
				}
				y = x;
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"__marker__.mark92(); //lineNumber=229\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark92();
                if ( k > x.key ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"\nroops_goal_20=true; //mutGenLimit 1 //lineNumber=231\n");
					} catch (IOException ioexception) {
					}
					roops_goal_20 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"__marker__.mark93(); //lineNumber=232\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark93();
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"x=x.left; //lineNumber=233\n");
					} catch (IOException ioexception) {
					}
					x = x.left;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"__marker__.mark94(); //lineNumber=234\n");
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
										"roops_goal_21=true; //lineNumber=236\n");
					} catch (IOException ioexception) {
					}
					roops_goal_21 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"__marker__.mark95(); //lineNumber=237\n");
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
											"roops_goal_22=true; //lineNumber=239\n");
						} catch (IOException ioexception) {
						}
						roops_goal_22 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
											"__marker__.mark96(); //lineNumber=240\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark96();
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
											"x=x.right; //lineNumber=241\n");
						} catch (IOException ioexception) {
						}
						x = x.right;
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
											"__marker__.mark97(); //lineNumber=242\n");
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
											"roops_goal_23=true; //lineNumber=244\n");
						} catch (IOException ioexception) {
						}
						roops_goal_23 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
											"__marker__.mark98(); //lineNumber=245\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark98();
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
											"return false; //lineNumber=246\n");
						} catch (IOException ioexception) {
						}
						return false;
                    }
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"__marker__.mark100(); //lineNumber=248\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark100();
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"__marker__.mark101(); //lineNumber=250\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark101();
            } else {
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"if(x != null){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"roops_goal_19=true; //lineNumber=252\n");
				} catch (IOException ioexception) {
				}
				roops_goal_19 = true;
            }
            if ( x != null ) {
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
									"roops_goal_24=true; //lineNumber=255\n");
				} catch (IOException ioexception) {
				}
				roops_goal_24 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"__marker__.mark91(); //lineNumber=256\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark91();
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"y=x; //lineNumber=257\n");
				} catch (IOException ioexception) {
				}
				y = x;
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"__marker__.mark92(); //lineNumber=258\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark92();
                if ( k > x.key ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"\nroops_goal_26=true; //mutGenLimit 1 //lineNumber=260\n");
					} catch (IOException ioexception) {
					}
					roops_goal_26 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"__marker__.mark93(); //lineNumber=261\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark93();
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"x=x.left; //lineNumber=262\n");
					} catch (IOException ioexception) {
					}
					x = x.left;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"__marker__.mark94(); //lineNumber=263\n");
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
										"roops_goal_27=true; //lineNumber=265\n");
					} catch (IOException ioexception) {
					}
					roops_goal_27 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"__marker__.mark95(); //lineNumber=266\n");
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
											"roops_goal_28=true; //lineNumber=268\n");
						} catch (IOException ioexception) {
						}
						roops_goal_28 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
											"__marker__.mark96(); //lineNumber=269\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark96();
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
											"x=x.right; //lineNumber=270\n");
						} catch (IOException ioexception) {
						}
						x = x.right;
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
											"__marker__.mark97(); //lineNumber=271\n");
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
											"roops_goal_29=true; //lineNumber=273\n");
						} catch (IOException ioexception) {
						}
						roops_goal_29 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
											"__marker__.mark98(); //lineNumber=274\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark98();
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
											"return false; //lineNumber=275\n");
						} catch (IOException ioexception) {
						}
						return false;
                    }
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
										"__marker__.mark100(); //lineNumber=277\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark100();
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"__marker__.mark101(); //lineNumber=279\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark101();
            } else {
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"if(x != null){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"roops_goal_25=true; //lineNumber=281\n");
				} catch (IOException ioexception) {
				}
				roops_goal_25 = true;
            }
        }
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
							"__marker__.mark102(); //lineNumber=284\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark102();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
							"x=new BinTreeNode(); //lineNumber=285\n");
		} catch (IOException ioexception) {
		}
		x = new BinTreeNode();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
							"__marker__.mark103(); //lineNumber=286\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark103();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
							"x.key=k; //lineNumber=287\n");
		} catch (IOException ioexception) {
		}
		x.key = k;
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
							"__marker__.mark104(); //lineNumber=288\n");
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
								"roops_goal_30=true; //lineNumber=290\n");
			} catch (IOException ioexception) {
			}
			roops_goal_30 = true;
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
								"__marker__.mark105(); //lineNumber=291\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark105();
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
								"root=x; //lineNumber=292\n");
			} catch (IOException ioexception) {
			}
			root = x;
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
								"__marker__.mark106(); //lineNumber=293\n");
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
								"roops_goal_31=true; //lineNumber=295\n");
			} catch (IOException ioexception) {
			}
			roops_goal_31 = true;
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
								"__marker__.mark107(); //lineNumber=296\n");
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
									"roops_goal_32=true; //lineNumber=298\n");
				} catch (IOException ioexception) {
				}
				roops_goal_32 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"__marker__.mark108(); //lineNumber=299\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark108();
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"y.left=x; //lineNumber=300\n");
				} catch (IOException ioexception) {
				}
				y.left = x;
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"__marker__.mark109(); //lineNumber=301\n");
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
									"roops_goal_33=true; //lineNumber=303\n");
				} catch (IOException ioexception) {
				}
				roops_goal_33 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"__marker__.mark110(); //lineNumber=304\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark110();
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"y.right=x; //lineNumber=305\n");
				} catch (IOException ioexception) {
				}
				y.right = x;
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
									"__marker__.mark111(); //lineNumber=306\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark111();
            }
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
								"__marker__.mark112(); //lineNumber=308\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark112();
        }
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
							"__marker__.mark113(); //lineNumber=310\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark113();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
							"x.parent=y; //lineNumber=311\n");
		} catch (IOException ioexception) {
		}
		x.parent = y;
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
							"__marker__.mark114(); //lineNumber=312\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark114();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
							"size+=1; //lineNumber=313\n");
		} catch (IOException ioexception) {
		}
		size += 1;
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
							"__marker__.mark115(); //lineNumber=314\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark115();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/BinTreeInsertBug5.java_insert",
							"return true; //lineNumber=315\n");
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
