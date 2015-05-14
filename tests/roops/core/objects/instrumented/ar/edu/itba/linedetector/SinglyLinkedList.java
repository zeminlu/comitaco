package tests.roops.core.objects.instrumented.ar.edu.itba.linedetector;


import roops.core.objects.SinglyLinkedListNode;
import roops.core.objects.BugLineMarker;
import java.io.IOException;
import ar.edu.taco.utils.FileUtils;
import java.util.NoSuchElementException;


public class SinglyLinkedList 
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
    @ invariant (\forall SinglyLinkedListNode n; reach(this.header, SinglyLinkedListNode.class," next").has(n);  reach(n.next, SinglyLinkedListNode.class," next").has(n)==false); 
    @*/    public /*@nullable@*/roops.core.objects.SinglyLinkedListNode header;

    public SinglyLinkedList () {
    }

    /*@
    @ requires true;
    @ ensures (\exists SinglyLinkedListNode n; reach(this.header, SinglyLinkedListNode.class," next").has(n); n.value==valueParam) ==> (\result==true); 
    @ ensures (\result == true) ==> (\exists SinglyLinkedListNode n; reach(this.header, SinglyLinkedListNode.class," next").has(n); n.value==valueParam); 
    @ signals (RuntimeException e) false;
    @
    @*/
    public boolean contains ( /*@nullable@*/java.lang.Object valueParam) {
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"fajita_roopsGoal_initialization();\n");
		} catch (IOException ioexception) {
		}
		fajita_roopsGoal_initialization();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"BugLineMarker __marker__=new BugLineMarker(); //lineNumber=25\n");
		} catch (IOException ioexception) {
		}
		BugLineMarker __marker__ = new BugLineMarker();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"__marker__.mark(25); //lineNumber=26\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(25);
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"SinglyLinkedListNode current; //lineNumber=27\n");
		} catch (IOException ioexception) {
		}
		SinglyLinkedListNode current;
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"__marker__.mark(26); //lineNumber=28\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(26);
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"boolean result; //lineNumber=29\n");
		} catch (IOException ioexception) {
		}
		boolean result;
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"__marker__.mark(27); //lineNumber=30\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(27);
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"current=this.header; //lineNumber=31\n");
		} catch (IOException ioexception) {
		}
		current = this.header;
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"__marker__.mark(28); //lineNumber=32\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(28);
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"result=false; //lineNumber=33\n");
		} catch (IOException ioexception) {
		}
		result = false;
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"__marker__.mark(29); //lineNumber=34\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(29);
        {
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
								"boolean fajita_cicle_0=false; //lineNumber=36\n");
			} catch (IOException ioexception) {
			}
			boolean fajita_cicle_0 = false;
            while ( result == false && current != null ) {
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"if(!(result == false && current != null)){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"fajita_cicle_0=true; //lineNumber=38\n");
				} catch (IOException ioexception) {
				}
				fajita_cicle_0 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"roops_goal_0=true; //lineNumber=39\n");
				} catch (IOException ioexception) {
				}
				roops_goal_0 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(30); //lineNumber=40\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(30);
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"boolean equalVal; //lineNumber=41\n");
				} catch (IOException ioexception) {
				}
				boolean equalVal;
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(31); //lineNumber=42\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(31);
                if ( valueParam == null && current.value == null ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(!(valueParam == null && current.value == null)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_2=true; //lineNumber=44\n");
					} catch (IOException ioexception) {
					}
					roops_goal_2 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(32); //lineNumber=45\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(32);
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"equalVal=true; //lineNumber=46\n");
					} catch (IOException ioexception) {
					}
					equalVal = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(33); //lineNumber=47\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(33);
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(valueParam == null && current.value == null){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_3=true; //lineNumber=49\n");
					} catch (IOException ioexception) {
					}
					roops_goal_3 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(34); //lineNumber=50\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(34);
                    if ( valueParam != null ) {
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"if(!(valueParam != null)){throw new RuntimeException();}\n");
						} catch (IOException ioexception) {
						}
						try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"roops_goal_4=true; //lineNumber=52\n");
						} catch (IOException ioexception) {
						}
						roops_goal_4 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(35); //lineNumber=53\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark(35);
                        if ( valueParam == current.value ) {
                            try {
								FileUtils
										.appendToFile(
												"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"if(!(valueParam == current.value)){throw new RuntimeException();}\n");
							} catch (IOException ioexception) {
							}
							try {
								FileUtils
										.appendToFile(
												"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"roops_goal_6=true; //lineNumber=55\n");
							} catch (IOException ioexception) {
							}
							roops_goal_6 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(36); //lineNumber=56\n");
							} catch (IOException ioexception) {
							}
							__marker__.mark(36);
                            try {
								FileUtils
										.appendToFile(
												"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=true; //lineNumber=57\n");
							} catch (IOException ioexception) {
							}
							equalVal = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(37); //lineNumber=58\n");
							} catch (IOException ioexception) {
							}
							__marker__.mark(37);
                        } else {
                            try {
								FileUtils
										.appendToFile(
												"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"if(valueParam == current.value){throw new RuntimeException();}\n");
							} catch (IOException ioexception) {
							}
							try {
								FileUtils
										.appendToFile(
												"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"roops_goal_7=true; //lineNumber=60\n");
							} catch (IOException ioexception) {
							}
							roops_goal_7 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(38); //lineNumber=61\n");
							} catch (IOException ioexception) {
							}
							__marker__.mark(38);
                            try {
								FileUtils
										.appendToFile(
												"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=false; //lineNumber=62\n");
							} catch (IOException ioexception) {
							}
							equalVal = false;
                            try {
								FileUtils
										.appendToFile(
												"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(39); //lineNumber=63\n");
							} catch (IOException ioexception) {
							}
							__marker__.mark(39);
                        }
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(40); //lineNumber=65\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark(40);
                    } else {
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"if(valueParam != null){throw new RuntimeException();}\n");
						} catch (IOException ioexception) {
						}
						try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"roops_goal_5=true; //lineNumber=67\n");
						} catch (IOException ioexception) {
						}
						roops_goal_5 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(41); //lineNumber=68\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark(41);
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"equalVal=false; //lineNumber=69\n");
						} catch (IOException ioexception) {
						}
						equalVal = false;
                        try {
							FileUtils
									.appendToFile(
											"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(42); //lineNumber=70\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark(42);
                    }
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(43); //lineNumber=72\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(43);
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(44); //lineNumber=74\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(44);
                if ( equalVal == true ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(!(equalVal == true)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_8=true; //lineNumber=76\n");
					} catch (IOException ioexception) {
					}
					roops_goal_8 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(45); //lineNumber=77\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(45);
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"result=false; //lineNumber=78\n");
					} catch (IOException ioexception) {
					}
					result = false;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(46); //lineNumber=79\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(46);
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(equalVal == true){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_9=true; //lineNumber=81\n");
					} catch (IOException ioexception) {
					}
					roops_goal_9 = true;
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(47); //lineNumber=83\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(47);
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"current=current.next; //lineNumber=84\n");
				} catch (IOException ioexception) {
				}
				current = current.next;
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(48); //lineNumber=85\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(48);
            }
			try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
								"if(result == false && current != null){throw new RuntimeException();}\n");
			} catch (IOException ioexception) {
			}
            if ( ! fajita_cicle_0 ) {
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"if(!(!fajita_cicle_0)){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"roops_goal_1=true; //lineNumber=88\n");
				} catch (IOException ioexception) {
				}
				roops_goal_1 = true;
            }
        }
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"__marker__.mark(49); //lineNumber=91\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(49);
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"return result; //lineNumber=92\n");
		} catch (IOException ioexception) {
		}
		return result;
    }
    /*@
    @ requires index>=0 && index<reach(this.header, SinglyLinkedListNode.class," next").int_size(); 
    @
    @ ensures reach(this.header, SinglyLinkedListNode.class," next").has(\result)==true; 
    @ ensures reach(\result, SinglyLinkedListNode.class," next").int_size() ==  reach(this.header, SinglyLinkedListNode.class," next").int_size()-index; 
    @ signals (RuntimeException e) false;
    @*/    public SinglyLinkedListNode getNode ( int index) {
        SinglyLinkedListNode current = this.header;
        SinglyLinkedListNode result = null;
        int current_index = 0;
        while ( result == null && current != null ) {
            if ( index == current_index ) {
                result = current;
            } else {
            }
            current_index = current_index + 1;
            current = current.next;
        }
        return result;
    }

    /*@ requires true;
      @ ensures (\exists SinglyLinkedListNode n; reach(this.header, SinglyLinkedListNode.class," next").has(n); n.value == arg && n.next == null); 
      @ ensures (\forall SinglyLinkedListNode n; reach(this.header, SinglyLinkedListNode.class," next").has(n); n.next != null ==> \old( reach(this.header, SinglyLinkedListNode.class," next")).has(n)); 
      @ ensures (\forall SinglyLinkedListNode n; \old(reach(this.header, SinglyLinkedListNode.class," next")).has(n);  reach(this.header, SinglyLinkedListNode.class," next").has(n) && n.next != null); 
      @*/    public void insertBack ( java.lang.Object arg) {
        roops.core.objects.SinglyLinkedListNode freshNode = new roops.core.objects.SinglyLinkedListNode ();
        freshNode.value = arg;
        freshNode.next = null;
        if ( this.header == null ) {
            this.header = freshNode;
        } else {
            roops.core.objects.SinglyLinkedListNode current;
            current = this.header;
            while (  current.next != null ) {
                current = current.next;
            }
            current.next = freshNode;
        }
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
