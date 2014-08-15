package tests.roops.core.objects.instrumented.ar.edu.itba.linedetector;


import roops.core.objects.SinglyLinkedListNode;
import java.io.IOException;
import ar.edu.taco.utils.FileUtils;


/*@ nullable_by_default @*/
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



    public  boolean roops_goal_0;

    public  boolean roops_goal_1;

    public  boolean roops_goal_2;

    public  boolean roops_goal_3;

    public  boolean roops_goal_4;

    public  boolean roops_goal_5;

    public  boolean roops_goal_6;

    public  boolean roops_goal_7;

    public  boolean roops_goal_8;

    public  boolean roops_goal_9;

    public  void fajita_roopsGoal_initialization () {
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

/*@
    @ invariant (\forall SinglyLinkedListNode n; reach(header, SinglyLinkedListNode.class," next").has(n);  reach(n.next, SinglyLinkedListNode.class," next").has(n)==false); 
    @*/
    public SinglyLinkedListNode header;

    public SinglyLinkedList ()
     {
    }

//----------------- showInstance --------------------//
/*@ requires reach(this.header, SinglyLinkedListNode.class," next").int_size() == 100; 
    @ ensures \result == false;
    @*/
    public boolean showInstance ()
     {
        return true;
    }

    /** @Modifies_Everything
   * @Ensures false;
   */
    public boolean contains (  /*@nullable@*/ java.lang.Object value_param) {
        try {
			FileUtils
					.appendToFile(
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"fajita_roopsGoal_initialization();\n");
		} catch (IOException ioexception) {
		}
		fajita_roopsGoal_initialization();
        try {
			FileUtils
					.appendToFile(
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
=======
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> generating weird sequential code
=======
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> UNSAT 0 variables con marks
=======
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> static-field-not-found
							"SinglyLinkedListNode current;\n");
		} catch (IOException ioexception) {
		}
		SinglyLinkedListNode current;
        try {
			FileUtils
					.appendToFile(
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
=======
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> generating weird sequential code
=======
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> UNSAT 0 variables con marks
=======
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> static-field-not-found
							"boolean result;\n");
		} catch (IOException ioexception) {
		}
		boolean result;
<<<<<<< HEAD
<<<<<<< HEAD
        try {
			FileUtils
					.appendToFile(
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"current=this.header;\n");
		} catch (IOException ioexception) {
		}
		current = this.header;
try {
			FileUtils
					.appendToFile(
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
=======
try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"//mutID 0\ncurrent=this.header.next; //mutGenLimit 1\n");
=======
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"current=this.header;\n");
>>>>>>> UNSAT 0 variables con marks
		} catch (IOException ioexception) {
		}
		current = this.header.next;
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
>>>>>>> generating weird sequential code
=======
>>>>>>> UNSAT 0 variables con marks
=======
>>>>>>> static-field-not-found
							"result=false;\n");
		} catch (IOException ioexception) {
		}
		result = false;
        {
            try {
				FileUtils
						.appendToFile(
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
								"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
=======
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> generating weird sequential code
=======
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> UNSAT 0 variables con marks
								"if(!(result == false && current != null)){throw new RuntimeException();}\n");
=======
								"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
								"boolean fajita_cicle_0=false;\n");
>>>>>>> static-field-not-found
			} catch (IOException ioexception) {
			}
			boolean fajita_cicle_0 = false;
            while ( result == false && current != null )
            { try {
				FileUtils
						.appendToFile(
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
								"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
=======
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> generating weird sequential code
=======
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> UNSAT 0 variables con marks
								"boolean equalVal;\n");
			} catch (IOException ioexception) {
			}
			boolean equalVal;
            if (value_param == null && current.value == null) {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
            	try {
					FileUtils
							.appendToFile(
									"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"//mutID 0\nequalVal=true; //                equalVal = false; //mutGenLimit 1\n");
				} catch (IOException ioexception) {
				}
				equalVal = true;
=======
try {
=======
            	try {
>>>>>>> negate post
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"//mutID 1\nequalVal=true; //                equalVal = false; //mutGenLimit 1\n");
				} catch (IOException ioexception) {
				}
<<<<<<< HEAD
				equalVal = false;
>>>>>>> generating weird sequential code
=======
				equalVal = true;
>>>>>>> negate post
=======
              try {
=======
								"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
								"if(!(result == false && current != null)){throw new RuntimeException();}\n");
			} catch (IOException ioexception) {
			}
			try {
>>>>>>> static-field-not-found
				FileUtils
						.appendToFile(
								"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
								"fajita_cicle_0=true;\n");
			} catch (IOException ioexception) {
			}
<<<<<<< HEAD
			equalVal = true;
>>>>>>> UNSAT 0 variables con marks
            } else {
                try {
					FileUtils
							.appendToFile(
<<<<<<< HEAD
<<<<<<< HEAD
									"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
=======
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> generating weird sequential code
=======
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> UNSAT 0 variables con marks
									"if(value_param == null && current.value == null){throw new RuntimeException();}\n");
=======
			fajita_cicle_0 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"roops_goal_0=true;\n");
				} catch (IOException ioexception) {
				}
				roops_goal_0 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"boolean equalVal;\n");
>>>>>>> static-field-not-found
				} catch (IOException ioexception) {
				}
				boolean equalVal;
                if ( value_param == null && current.value == null )
                {
                    try {
						FileUtils
								.appendToFile(
										"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(!(value_param == null && current.value == null)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_2=true;\n");
					} catch (IOException ioexception) {
					}
					roops_goal_2 = true;
                    try {
						FileUtils
								.appendToFile(
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
										"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
=======
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> generating weird sequential code
										"if(!(value_param != null)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					if (value_param == current.value) {
                        try {
							FileUtils
									.appendToFile(
<<<<<<< HEAD
											"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
=======
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> generating weird sequential code
											"if(!(value_param == current.value)){throw new RuntimeException();}\n");
						} catch (IOException ioexception) {
						}
						try {
							FileUtils
									.appendToFile(
<<<<<<< HEAD
											"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
=======
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> generating weird sequential code
											"equalVal=true;\n");
						} catch (IOException ioexception) {
						}
						equalVal = true;
=======
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(!(value_param != null)){throw new RuntimeException();}\n");
=======
										"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"//mutID 0\nequalVal=true; //              equalVal = false; //mutGenLimit 1\n");
>>>>>>> static-field-not-found
					} catch (IOException ioexception) {
					}
					equalVal = true;
                } else
                {
                    try {
						FileUtils
								.appendToFile(
										"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(value_param == null && current.value == null){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_3=true;\n");
					} catch (IOException ioexception) {
					}
<<<<<<< HEAD
					equalVal = true;
>>>>>>> UNSAT 0 variables con marks
                    } else {
                        try {
							FileUtils
									.appendToFile(
<<<<<<< HEAD
<<<<<<< HEAD
											"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
=======
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> generating weird sequential code
=======
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> UNSAT 0 variables con marks
											"if(value_param == current.value){throw new RuntimeException();}\n");
=======
					roops_goal_3 = true;
                    if ( value_param != null )
                    {
                        try {
							FileUtils
									.appendToFile(
											"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"if(!(value_param != null)){throw new RuntimeException();}\n");
						} catch (IOException ioexception) {
						}
						try {
							FileUtils
									.appendToFile(
											"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"roops_goal_4=true;\n");
						} catch (IOException ioexception) {
						}
						roops_goal_4 = true;
                        if ( value_param == current.value )
                        {
                            try {
								FileUtils
										.appendToFile(
												"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"if(!(value_param == current.value)){throw new RuntimeException();}\n");
							} catch (IOException ioexception) {
							}
							try {
								FileUtils
										.appendToFile(
												"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"roops_goal_6=true;\n");
							} catch (IOException ioexception) {
							}
							roops_goal_6 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=true;\n");
							} catch (IOException ioexception) {
							}
							equalVal = true;
                        } else
                        {
                            try {
								FileUtils
										.appendToFile(
												"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"if(value_param == current.value){throw new RuntimeException();}\n");
							} catch (IOException ioexception) {
							}
							try {
								FileUtils
										.appendToFile(
												"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"roops_goal_7=true;\n");
							} catch (IOException ioexception) {
							}
							roops_goal_7 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=false;\n");
							} catch (IOException ioexception) {
							}
							equalVal = false;
                        }
                    } else
                    {
                        try {
							FileUtils
									.appendToFile(
											"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"if(value_param != null){throw new RuntimeException();}\n");
>>>>>>> static-field-not-found
						} catch (IOException ioexception) {
						}
						try {
							FileUtils
									.appendToFile(
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
											"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
=======
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> generating weird sequential code
=======
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> UNSAT 0 variables con marks
=======
											"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"roops_goal_5=true;\n");
						} catch (IOException ioexception) {
						}
						roops_goal_5 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> static-field-not-found
											"equalVal=false;\n");
						} catch (IOException ioexception) {
						}
						equalVal = false;
                    }
                }
                if ( equalVal == true )
                {
                    try {
						FileUtils
								.appendToFile(
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
										"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
=======
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> generating weird sequential code
=======
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> UNSAT 0 variables con marks
										"if(value_param != null){throw new RuntimeException();}\n");
=======
										"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(!(equalVal == true)){throw new RuntimeException();}\n");
>>>>>>> static-field-not-found
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
										"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
=======
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> generating weird sequential code
=======
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> UNSAT 0 variables con marks
										"equalVal=false;\n");
=======
										"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_8=true;\n");
					} catch (IOException ioexception) {
					}
					roops_goal_8 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"result=true;\n");
>>>>>>> static-field-not-found
					} catch (IOException ioexception) {
					}
					result = true;
                }
//            current = current.next;
                else
                {
                    try {
						FileUtils
								.appendToFile(
										"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(equalVal == true){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_9=true;\n");
					} catch (IOException ioexception) {
					}
					roops_goal_9 = true;
                }
                try {
					FileUtils
							.appendToFile(
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
									"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
=======
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> generating weird sequential code
=======
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> UNSAT 0 variables con marks
									"if(!(equalVal == true)){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
<<<<<<< HEAD
<<<<<<< HEAD
									"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
=======
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> generating weird sequential code
=======
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> UNSAT 0 variables con marks
									"result=true;\n");
=======
									"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"//mutID 1\ncurrent=current.next.next; //mutGenLimit 1\n");
>>>>>>> static-field-not-found
				} catch (IOException ioexception) {
				}
				current = current.next.next;
            }
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
try {
<<<<<<< HEAD
							FileUtils
									.appendToFile(
											"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"//mutID 1\ncurrent=current.next.next; //mutGenLimit 1\n");
						} catch (IOException ioexception) {
						}
						current = current.next.next;
=======
=======
            try {
>>>>>>> negate post
				FileUtils
						.appendToFile(
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
								"//mutID 2\ncurrent=current.next; //            current = current.next.next; //mutGenLimit 1\n");
			} catch (IOException ioexception) {
			}
<<<<<<< HEAD
			current = current.next.next;
>>>>>>> generating weird sequential code
=======
			current = current.next;
>>>>>>> negate post
=======
try {
=======
			try {
				FileUtils
						.appendToFile(
								"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
								"if(result == false && current != null){throw new RuntimeException();}\n");
			} catch (IOException ioexception) {
			}
            if ( ! fajita_cicle_0 ) try {
				FileUtils
						.appendToFile(
								"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
								"if(!(!fajita_cicle_0)){throw new RuntimeException();}\n");
			} catch (IOException ioexception) {
			}
			try {
>>>>>>> static-field-not-found
				FileUtils
						.appendToFile(
								"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
								"roops_goal_1=true;\n");
			} catch (IOException ioexception) {
			}
<<<<<<< HEAD
			current = current.next.next;
>>>>>>> UNSAT 0 variables con marks
        }
		try {
			FileUtils
					.appendToFile(
<<<<<<< HEAD
<<<<<<< HEAD
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
=======
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> generating weird sequential code
=======
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> UNSAT 0 variables con marks
							"if(result == false && current != null){throw new RuntimeException();}\n");
		} catch (IOException ioexception) {
		}
        try {
			FileUtils
					.appendToFile(
<<<<<<< HEAD
<<<<<<< HEAD
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"//mutID 2\nreturn result; //                return !result; //mutGenLimit 1\n");
=======
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"//mutID 3\nreturn result; //                return !result; //mutGenLimit 1\n");
>>>>>>> generating weird sequential code
=======
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
=======
			roops_goal_1 = true;
        }
        try {
			FileUtils
					.appendToFile(
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> static-field-not-found
							"//mutID 2\nreturn result; //                return !result; //mutGenLimit 1\n");
>>>>>>> UNSAT 0 variables con marks
		} catch (IOException ioexception) {
		}
		return result;
    }
/*@
    @ requires index>=0 && index<reach(this.header, SinglyLinkedListNode.class," next").int_size(); 
    @ ensures reach(this.header, SinglyLinkedListNode.class," next").has(\result)==true; 
    @ ensures reach(\result, SinglyLinkedListNode.class," next").int_size() ==  reach(this.header, SinglyLinkedListNode.class," next").int_size()-index; 
    @ signals (Exception e) false;
    @*/
    public SinglyLinkedListNode getNode ( int index)
     {
        SinglyLinkedListNode current = header;
        SinglyLinkedListNode result = null;
        int current_index = 0;
        while ( result == null && current != null ) {
            if ( index == current_index ) {
                result = current;
            }
            else
            {}
            current_index = current_index + 1;
            current = current.next;
        }
        return result;
    }

//------------------------ insertBack --------------------------//
//Due to jml4c the ensures clauses must be in that order :(
/*@
    @ requires freshNode!=null;
    @ requires reach(header, SinglyLinkedListNode.class," next").has(freshNode)==false; 
    @ ensures reach(header, SinglyLinkedListNode.class," next").int_size()==\old( reach(header, SinglyLinkedListNode.class," next")).int_size()+1; 
    @ ensures (\forall SinglyLinkedListNode n;
    @            \old(reach(header, SinglyLinkedListNode.class," next")).has(n); 
    @      reach(header, SinglyLinkedListNode.class," next").has(n)==true 
    @         );
    @ ensures (\exists SinglyLinkedListNode n;
    @            reach(header, SinglyLinkedListNode.class," next").has(n); 
    @            n.next==null && n.value==data);
    @ signals (Exception e) false;
    @*/
    void insertBack ( java.lang.Object data, SinglyLinkedListNode freshNode)
     {
        freshNode.value = data;
        freshNode.next = null;
        if ( this.header == null ) {
            this.header = freshNode;
        } else {
            SinglyLinkedListNode current = this.header;
            while ( current.next != null ) {
                current = current.next;
            }
            current.next = freshNode;
        }
    }
}
