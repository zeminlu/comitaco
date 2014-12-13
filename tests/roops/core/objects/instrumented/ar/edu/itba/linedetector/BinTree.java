package tests.roops.core.objects.instrumented.ar.edu.itba.linedetector;

import roops.core.objects.BugLineMarker;

import roops.core.objects.BinTreeNode;
import java.io.IOException;
import ar.edu.taco.utils.FileUtils;
import java.util.NoSuchElementException;

public class BinTree 
{
    protected static ar.edu.taco.stryker.api.impl.ReachSet reach(Object o, Class<?> clazz, String str) {
        String fieldsToMoveThroughAsStrings[] = str.replaceAll(" ", "").split("\\+");
        ar.edu.taco.stryker.api.impl.ReachSet objectSet = new ar.edu.taco.stryker.api.impl.ReachSet();
        java.util.IdentityHashMap<Object,Object> visitedObjects = new java.util.IdentityHashMap<Object,Object>();
        java.util.Queue<Object> queue = new java.util.LinkedList<Object>();
        
		 if(o == null) {
        	return objectSet;
		 }
        visitedObjects.put(o, o);
        queue.add(o);
		 objectSet.add(o);
        while(!queue.isEmpty()) {
            Object currentObject = queue.poll();
            
            java.util.List<java.lang.reflect.Field> fieldsToMoveThrough = new java.util.ArrayList<java.lang.reflect.Field>();
            for(int i = 0; i < fieldsToMoveThroughAsStrings.length; i++) {
                    String fieldAsString = fieldsToMoveThroughAsStrings[i];
                try {
                    java.lang.reflect.Field field = currentObject.getClass().getDeclaredField(fieldAsString);
                    fieldsToMoveThrough.add(field);
                } catch (NoSuchFieldException e) {
                e.printStackTrace();
                } catch (SecurityException e) {
                     e.printStackTrace();
                }
            }
            
            for(java.lang.reflect.Field field : fieldsToMoveThrough) {
                field.setAccessible(true);
                try {
                    Object accessedObject = field.get(currentObject);
                    if(accessedObject != null && visitedObjects.containsKey(accessedObject) == false) {
                        visitedObjects.put(accessedObject, accessedObject);
                        queue.add(accessedObject);
                        if(clazz.isInstance(accessedObject)) {
                            objectSet.add(accessedObject);
                        }
                    }
                } catch (IllegalArgumentException e) {
                e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return objectSet;
    }




    /*@
      @ invariant (\forall BinTreeNode n;
      @     reach(root, BinTreeNode.class," left + right").has(n) == true; 
      @     reach(n.right, BinTreeNode.class," right + left").has(n) == false && 
      @     reach(n.left, BinTreeNode.class," left + right").has(n) == false); 
      @
      @ invariant (\forall BinTreeNode n;
      @     reach(root, BinTreeNode.class," left + right").has(n) == true; 
      @     (\forall BinTreeNode m;
      @     reach(n.left, BinTreeNode.class," left + right").has(m) == true; 
      @     m.key <= n.key) &&
      @     (\forall BinTreeNode m;
      @     reach(n.right, BinTreeNode.class," left + right").has(m) == true; 
      @     m.key > n.key));
      @
      @ invariant size == reach(root, BinTreeNode.class," left + right").int_size(); 
      @
      @ invariant (\forall BinTreeNode n;
      @   reach(root, BinTreeNode.class," left + right").has(n) == true; 
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
      @   reach(root, BinTreeNode.class," left+right").has(n) == true; 
      @   n.key == k);
      @
      @ ensures (\forall BinTreeNode n;
      @   reach(root, BinTreeNode.class," left+right").has(n); 
      @   \old(reach(root, BinTreeNode.class," left+right")).has(n)); 
      @
      @ ensures (\forall BinTreeNode n;
      @   \old(reach(root, BinTreeNode.class," left+right")).has(n); 
      @   reach(root, BinTreeNode.class," left+right").has(n)); 
      @
      @ signals (RuntimeException e) false;
      @*/
    public boolean contains (int k) {
        BinTreeNode current = null;
        while ( current != null ) { //mutGenLimit 1
            if ( k < current.key ) { //mutGenLimit 1
                current = current.left; //mutGenLimit 1
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
      @   \old(reach(root, BinTreeNode.class," left + right")).has(n) == true; 
      @   n.key == k) ==> size == \old(size);
      @
      @ ensures (\forall BinTreeNode n;
      @   \old(reach(root, BinTreeNode.class," left + right")).has(n) == true; 
      @   n.key != k) ==> size == \old(size) + 1;
      @
      @ ensures (\exists BinTreeNode n;
      @     reach(root, BinTreeNode.class," left + right").has(n) == true; 
      @   n.key == k);
      @
      @ signals (RuntimeException e) false;
      @*/
    public boolean insert (int k) {
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
							"fajita_roopsGoal_initialization();\n");
		} catch (IOException ioexception) {
		}
		fajita_roopsGoal_initialization();
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
							"BugLineMarker __marker__=new BugLineMarker(); //lineNumber=90\n");
		} catch (IOException ioexception) {
		}
		BugLineMarker __marker__ = new BugLineMarker();
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
							"__marker__.mark(90); //lineNumber=91\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(90);
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
							"BinTreeNode y=null; //lineNumber=92\n");
		} catch (IOException ioexception) {
		}
		BinTreeNode y = null;
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
							"__marker__.mark(91); //lineNumber=93\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(91);
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
							"BinTreeNode x=root; //lineNumber=94\n");
		} catch (IOException ioexception) {
		}
		BinTreeNode x = root;
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
							"__marker__.mark(92); //lineNumber=95\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(92);
        {
            try {
				FileUtils
						.appendToFile(
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
								"boolean fajita_cicle_0=false; //lineNumber=97\n");
			} catch (IOException ioexception) {
			}
			boolean fajita_cicle_0 = false;
            while ( x != null ) {
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
									"if(!(x != null)){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
									"fajita_cicle_0=true; //lineNumber=99\n");
				} catch (IOException ioexception) {
				}
				fajita_cicle_0 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
									"roops_goal_0=true; //lineNumber=100\n");
				} catch (IOException ioexception) {
				}
				roops_goal_0 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
									"__marker__.mark(93); //lineNumber=101\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(93);
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
									"y=x; //lineNumber=102\n");
				} catch (IOException ioexception) {
				}
				y = x;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
									"__marker__.mark(94); //lineNumber=103\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(94);
                if ( k < x.key ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
										"if(!(k < x.key)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
										"roops_goal_2=true; //lineNumber=105\n");
					} catch (IOException ioexception) {
					}
					roops_goal_2 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
										"__marker__.mark(95); //lineNumber=106\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(95);
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
										"x=x.left; //lineNumber=107\n");
					} catch (IOException ioexception) {
					}
					x = x.left;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
										"__marker__.mark(96); //lineNumber=108\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(96);
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
										"if(k < x.key){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
										"roops_goal_3=true; //lineNumber=110\n");
					} catch (IOException ioexception) {
					}
					roops_goal_3 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
										"__marker__.mark(97); //lineNumber=111\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(97);
                    if ( k > x.key ) {
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
											"if(!(k > x.key)){throw new RuntimeException();}\n");
						} catch (IOException ioexception) {
						}
						try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
											"roops_goal_4=true; //lineNumber=113\n");
						} catch (IOException ioexception) {
						}
						roops_goal_4 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
											"__marker__.mark(98); //lineNumber=114\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark(98);
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
											"x=x.right; //lineNumber=115\n");
						} catch (IOException ioexception) {
						}
						x = x.right;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
											"__marker__.mark(99); //lineNumber=116\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark(99);
                    } else {
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
											"if(k > x.key){throw new RuntimeException();}\n");
						} catch (IOException ioexception) {
						}
						try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
											"roops_goal_5=true; //lineNumber=118\n");
						} catch (IOException ioexception) {
						}
						roops_goal_5 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
											"__marker__.mark(100); //lineNumber=119\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark(100);
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
											"return false; //lineNumber=120\n");
						} catch (IOException ioexception) {
						}
						return false;
                    }
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
										"__marker__.mark(102); //lineNumber=122\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(102);
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
									"__marker__.mark(103); //lineNumber=124\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(103);
            }
			try {
				FileUtils
						.appendToFile(
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
								"if(x != null){throw new RuntimeException();}\n");
			} catch (IOException ioexception) {
			}
            if ( ! fajita_cicle_0 ) {
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
									"if(!(!fajita_cicle_0)){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
									"roops_goal_1=true; //lineNumber=127\n");
				} catch (IOException ioexception) {
				}
				roops_goal_1 = true;
            }
        }
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
							"__marker__.mark(104); //lineNumber=130\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(104);
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
							"x=new BinTreeNode(); //lineNumber=131\n");
		} catch (IOException ioexception) {
		}
		x = new BinTreeNode();
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
							"__marker__.mark(105); //lineNumber=132\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(105);
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
							"x.key=k; //lineNumber=133\n");
		} catch (IOException ioexception) {
		}
		x.key = k;
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
							"__marker__.mark(106); //lineNumber=134\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(106);
        if ( y == null ) {
            try {
				FileUtils
						.appendToFile(
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
								"if(!(y == null)){throw new RuntimeException();}\n");
			} catch (IOException ioexception) {
			}
			try {
				FileUtils
						.appendToFile(
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
								"roops_goal_6=true; //lineNumber=136\n");
			} catch (IOException ioexception) {
			}
			roops_goal_6 = true;
            try {
				FileUtils
						.appendToFile(
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
								"__marker__.mark(107); //lineNumber=137\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark(107);
            try {
				FileUtils
						.appendToFile(
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
								"root=x; //lineNumber=138\n");
			} catch (IOException ioexception) {
			}
			root = x;
            try {
				FileUtils
						.appendToFile(
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
								"__marker__.mark(108); //lineNumber=139\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark(108);
        } else {
            try {
				FileUtils
						.appendToFile(
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
								"if(y == null){throw new RuntimeException();}\n");
			} catch (IOException ioexception) {
			}
			try {
				FileUtils
						.appendToFile(
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
								"roops_goal_7=true; //lineNumber=141\n");
			} catch (IOException ioexception) {
			}
			roops_goal_7 = true;
            try {
				FileUtils
						.appendToFile(
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
								"__marker__.mark(109); //lineNumber=142\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark(109);
            if ( k < y.key ) {
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
									"if(!(k < y.key)){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
									"roops_goal_8=true; //lineNumber=144\n");
				} catch (IOException ioexception) {
				}
				roops_goal_8 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
									"__marker__.mark(110); //lineNumber=145\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(110);
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
									"y.right=x; //lineNumber=146\n");
				} catch (IOException ioexception) {
				}
				y.right = x;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
									"__marker__.mark(111); //lineNumber=147\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(111);
            } else {
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
									"if(k < y.key){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
									"roops_goal_9=true; //lineNumber=149\n");
				} catch (IOException ioexception) {
				}
				roops_goal_9 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
									"__marker__.mark(112); //lineNumber=150\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(112);
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
									"y.right=x; //lineNumber=151\n");
				} catch (IOException ioexception) {
				}
				y.right = x;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
									"__marker__.mark(113); //lineNumber=152\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(113);
            }
            try {
				FileUtils
						.appendToFile(
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
								"__marker__.mark(114); //lineNumber=154\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark(114);
        }
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
							"__marker__.mark(115); //lineNumber=156\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(115);
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
							"\nx.parent=x; //mutGenLimit 1 //lineNumber=157\n");
		} catch (IOException ioexception) {
		}
		x.parent = x;
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
							"__marker__.mark(116); //lineNumber=158\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(116);
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
							"size+=1; //lineNumber=160\n");
		} catch (IOException ioexception) {
		}
		size += 1;
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
							"__marker__.mark(117); //lineNumber=161\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(117);
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/BinTree.java_insert",
							"return true; //lineNumber=162\n");
		} catch (IOException ioexception) {
		}
		return true;
    }


    /*@
      @ requires (\forall BinTreeNode n1;
      @   reach(root, BinTreeNode.class," left+right").has(n1); 
      @   (\forall BinTreeNode m1;
      @       reach(root, BinTreeNode.class," left+right").has(m1); n1 != m1 ==> n1.key != m1.key)); 
      @
      @ ensures (\exists BinTreeNode n2;
      @   \old(reach(root, BinTreeNode.class," left + right")).has(n2) == true; 
      @   \old(n2.key) == element)
      @        <==> \result == true;
      @
      @ ensures (\forall BinTreeNode n3;
      @   reach(root, BinTreeNode.class," left+right").has(n3); 
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
