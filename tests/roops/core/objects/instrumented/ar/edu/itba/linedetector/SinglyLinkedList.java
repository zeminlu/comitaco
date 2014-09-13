package tests.roops.core.objects.instrumented.ar.edu.itba.linedetector;

import roops.core.objects.SinglyLinkedListNode;
import roops.core.objects.BugLineMarker;
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

	public static void fajita_roopsGoal_initialization() {
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

	/*
	 * @
	 * @ invariant (\forall SinglyLinkedListNode n; reach(header, SinglyLinkedListNode.class," next").has(n);  reach(n.next, SinglyLinkedListNode.class," next").has(n)==false); 
	 * @
	 */
	public SinglyLinkedListNode header;

	public SinglyLinkedList() {
	}

	// ----------------- showInstance --------------------//
	/*
	 * @ requires reach(this.header, SinglyLinkedListNode.class," next").int_size() == 100; 
	 * 
	 * @ ensures \result == false;
	 * 
	 * @
	 */
	public boolean showInstance() {
		return true;
	}

<<<<<<< HEAD
<<<<<<< HEAD
    /** @Modifies_Everything
   * @Ensures false;
   */
    public boolean contains (  /*@nullable@*/ java.lang.Object value_param) {
        try {
=======
	/**
	 * @Modifies_Everything
	 * @Ensures false;
	 */
	public boolean contains( /* @nullable@ */java.lang.Object value_param) {
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"BugLineMarker __marker__=new BugLineMarker();\n");
} catch (IOException ioexception) {
}
BugLineMarker __marker__ = new BugLineMarker();
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"__marker__.mark();\n //lineNumber=68");
} catch (IOException ioexception) {
}
__marker__.mark();
		try {
>>>>>>> instru
			FileUtils
					.appendToFile(
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"fajita_roopsGoal_initialization();\n //lineNumber=69");
		} catch (IOException ioexception) {
		}
		fajita_roopsGoal_initialization();
<<<<<<< HEAD
=======
/*@
    @ ensures (\exists SinglyLinkedListNode n; reach(this.header, SinglyLinkedListNode.class," next").has(n); n.value==value_param) <==> (\result==true); 
    @ signals (Exception e) true;
    @*/
    public boolean contains(  /*@nullable@*/ java.lang.Object value_param ) {
try {
	FileUtils
			.appendToFile(
					"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"BugLineMarker dummy=new BugLineMarker();\n");
} catch (IOException ioexception) {
}
BugLineMarker dummy = new BugLineMarker();
>>>>>>> unsat error lines
        try {
=======
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"__marker__.mark();\n //lineNumber=70");
} catch (IOException ioexception) {
}
__marker__.mark();
		try {
>>>>>>> instru
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
<<<<<<< HEAD
>>>>>>> static-field-not-found
							"SinglyLinkedListNode current;\n");
=======
							"SinglyLinkedListNode current;\n //lineNumber=71");
>>>>>>> instru
		} catch (IOException ioexception) {
		}
		SinglyLinkedListNode current;
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"__marker__.mark();\n //lineNumber=72");
} catch (IOException ioexception) {
}
__marker__.mark();
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
<<<<<<< HEAD
>>>>>>> UNSAT 0 variables con marks
=======
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
>>>>>>> static-field-not-found
							"boolean result;\n");
		} catch (IOException ioexception) {
		}
		boolean result;
<<<<<<< HEAD
<<<<<<< HEAD
=======
							"boolean res;\n");
		} catch (IOException ioexception) {
		}
		boolean res;
>>>>>>> unsat error lines
        try {
=======
							"boolean result;\n //lineNumber=73");
		} catch (IOException ioexception) {
		}
		boolean result;
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"__marker__.mark();\n //lineNumber=74");
} catch (IOException ioexception) {
}
__marker__.mark();
		try {
>>>>>>> instru
			FileUtils
					.appendToFile(
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"current=this.header;\n //lineNumber=75");
		} catch (IOException ioexception) {
		}
		current = this.header;
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"__marker__.mark();\n //lineNumber=76");
} catch (IOException ioexception) {
}
__marker__.mark();
		try {
			FileUtils
					.appendToFile(
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
<<<<<<< HEAD
=======
try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
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
=======
							"res=false;\n");
		} catch (IOException ioexception) {
		}
		res = false;
        while (res == false && current != null) {
>>>>>>> unsat error lines
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
<<<<<<< HEAD
>>>>>>> UNSAT 0 variables con marks
								"if(!(result == false && current != null)){throw new RuntimeException();}\n");
=======
								"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
								"boolean fajita_cicle_0=false;\n");
>>>>>>> static-field-not-found
=======
								"if(!(res == false && current != null)){throw new RuntimeException();}\n");
>>>>>>> unsat error lines
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
=======
							"result=false;\n //lineNumber=77");
		} catch (IOException ioexception) {
		}
		result = false;
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"__marker__.mark();\n //lineNumber=78");
} catch (IOException ioexception) {
}
__marker__.mark();
		{
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"__marker__.mark();\n //lineNumber=80");
} catch (IOException ioexception) {
}
__marker__.mark();
>>>>>>> instru
			try {
>>>>>>> static-field-not-found
				FileUtils
						.appendToFile(
								"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
								"boolean fajita_cicle_0=false;\n //lineNumber=81");
			} catch (IOException ioexception) {
			}
<<<<<<< HEAD
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
=======
			boolean fajita_cicle_0 = false;
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"__marker__.mark();\n //lineNumber=82");
} catch (IOException ioexception) {
}
__marker__.mark();
			while (result == false && current != null) {
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"if(!(result == false && current != null)){throw new RuntimeException();}\n");
} catch (IOException ioexception) {
}
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"__marker__.mark();\n //lineNumber=84");
} catch (IOException ioexception) {
}
__marker__.mark();
				try {
					FileUtils
							.appendToFile(
									"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"fajita_cicle_0=true;\n //lineNumber=85");
				} catch (IOException ioexception) {
				}
				fajita_cicle_0 = true;
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"__marker__.mark();\n //lineNumber=86");
} catch (IOException ioexception) {
}
__marker__.mark();
				try {
>>>>>>> instru
					FileUtils
							.appendToFile(
									"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"roops_goal_0=true;\n //lineNumber=87");
				} catch (IOException ioexception) {
				}
				roops_goal_0 = true;
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"__marker__.mark();\n //lineNumber=88");
} catch (IOException ioexception) {
}
__marker__.mark();
				try {
					FileUtils
							.appendToFile(
									"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
									"boolean equalVal;\n");
>>>>>>> static-field-not-found
=======
									"boolean equalVal;\n //lineNumber=89");
>>>>>>> instru
				} catch (IOException ioexception) {
				}
				boolean equalVal;
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"__marker__.mark();\n //lineNumber=90");
} catch (IOException ioexception) {
}
__marker__.mark();
				if (value_param == null && current.value == null) {
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
					"__marker__.mark();\n //lineNumber=92");
} catch (IOException ioexception) {
}
__marker__.mark();
					try {
						FileUtils
								.appendToFile(
										"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_2=true;\n //lineNumber=93");
					} catch (IOException ioexception) {
					}
					roops_goal_2 = true;
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"__marker__.mark();\n //lineNumber=94");
} catch (IOException ioexception) {
}
__marker__.mark();
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
<<<<<<< HEAD
										"//mutID 0\nequalVal=true; //              equalVal = false; //mutGenLimit 1\n");
>>>>>>> static-field-not-found
=======
										"equalVal=true;\n //lineNumber=95");
>>>>>>> instru
					} catch (IOException ioexception) {
					}
					equalVal = true;
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"__marker__.mark();\n //lineNumber=96");
} catch (IOException ioexception) {
}
__marker__.mark();
				} else {
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
					"__marker__.mark();\n //lineNumber=98");
} catch (IOException ioexception) {
}
__marker__.mark();
					try {
						FileUtils
								.appendToFile(
										"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_3=true;\n //lineNumber=99");
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
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"__marker__.mark();\n //lineNumber=100");
} catch (IOException ioexception) {
}
__marker__.mark();
					if (value_param != null) {
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
					"__marker__.mark();\n //lineNumber=102");
} catch (IOException ioexception) {
}
__marker__.mark();
						try {
							FileUtils
									.appendToFile(
											"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"roops_goal_4=true;\n //lineNumber=103");
						} catch (IOException ioexception) {
						}
						roops_goal_4 = true;
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"__marker__.mark();\n //lineNumber=104");
} catch (IOException ioexception) {
}
__marker__.mark();
						if (value_param == current.value) {
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
					"__marker__.mark();\n //lineNumber=106");
} catch (IOException ioexception) {
}
__marker__.mark();
							try {
								FileUtils
										.appendToFile(
												"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"roops_goal_6=true;\n //lineNumber=107");
							} catch (IOException ioexception) {
							}
							roops_goal_6 = true;
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"__marker__.mark();\n //lineNumber=108");
} catch (IOException ioexception) {
}
__marker__.mark();
							try {
								FileUtils
										.appendToFile(
												"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=true;\n //lineNumber=109");
							} catch (IOException ioexception) {
							}
							equalVal = true;
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"__marker__.mark();\n //lineNumber=110");
} catch (IOException ioexception) {
}
__marker__.mark();
						} else {
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
					"__marker__.mark();\n //lineNumber=112");
} catch (IOException ioexception) {
}
__marker__.mark();
							try {
								FileUtils
										.appendToFile(
												"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"roops_goal_7=true;\n //lineNumber=113");
							} catch (IOException ioexception) {
							}
							roops_goal_7 = true;
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"__marker__.mark();\n //lineNumber=114");
} catch (IOException ioexception) {
}
__marker__.mark();
							try {
								FileUtils
										.appendToFile(
												"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=false;\n //lineNumber=115");
							} catch (IOException ioexception) {
							}
							equalVal = false;
<<<<<<< HEAD
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
=======
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"__marker__.mark();\n //lineNumber=116");
} catch (IOException ioexception) {
}
__marker__.mark();
>>>>>>> instru
						}
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"__marker__.mark();\n //lineNumber=118");
} catch (IOException ioexception) {
}
__marker__.mark();
					} else {
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"if(value_param != null){throw new RuntimeException();}\n");
} catch (IOException ioexception) {
}
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"__marker__.mark();\n //lineNumber=120");
} catch (IOException ioexception) {
}
__marker__.mark();
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
											"roops_goal_5=true;\n //lineNumber=121");
						} catch (IOException ioexception) {
						}
						roops_goal_5 = true;
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"__marker__.mark();\n //lineNumber=122");
} catch (IOException ioexception) {
}
__marker__.mark();
						try {
							FileUtils
									.appendToFile(
											"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
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
=======
											"equalVal=false;\n //lineNumber=123");
						} catch (IOException ioexception) {
						}
						equalVal = false;
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"__marker__.mark();\n //lineNumber=124");
} catch (IOException ioexception) {
}
__marker__.mark();
>>>>>>> instru
					}
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"__marker__.mark();\n //lineNumber=126");
} catch (IOException ioexception) {
}
__marker__.mark();
				}
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"__marker__.mark();\n //lineNumber=128");
} catch (IOException ioexception) {
}
__marker__.mark();
				if (equalVal == true) {
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"if(!(equalVal == true)){throw new RuntimeException();}\n");
} catch (IOException ioexception) {
}
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"__marker__.mark();\n //lineNumber=130");
} catch (IOException ioexception) {
}
__marker__.mark();
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
										"roops_goal_8=true;\n //lineNumber=131");
					} catch (IOException ioexception) {
					}
					roops_goal_8 = true;
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"__marker__.mark();\n //lineNumber=132");
} catch (IOException ioexception) {
}
__marker__.mark();
					try {
						FileUtils
								.appendToFile(
										"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
										"result=true;\n");
>>>>>>> static-field-not-found
=======
										"result=true;\n //lineNumber=133");
>>>>>>> instru
					} catch (IOException ioexception) {
					}
					result = true;
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"__marker__.mark();\n //lineNumber=134");
} catch (IOException ioexception) {
}
__marker__.mark();
				} else {
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
					"__marker__.mark();\n //lineNumber=136");
} catch (IOException ioexception) {
}
__marker__.mark();
					try {
						FileUtils
								.appendToFile(
										"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_9=true;\n //lineNumber=137");
					} catch (IOException ioexception) {
					}
					roops_goal_9 = true;
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"__marker__.mark();\n //lineNumber=138");
} catch (IOException ioexception) {
}
__marker__.mark();
				}
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"__marker__.mark();\n //lineNumber=140");
} catch (IOException ioexception) {
}
__marker__.mark();
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
<<<<<<< HEAD
>>>>>>> UNSAT 0 variables con marks
									"result=true;\n");
=======
									"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
									"//mutID 1\ncurrent=current.next.next; //mutGenLimit 1\n");
>>>>>>> static-field-not-found
				} catch (IOException ioexception) {
				}
				current = current.next.next;
=======
									"res=true;\n");
				} catch (IOException ioexception) {
				}
				res = true;
>>>>>>> unsat error lines
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
=======
									"current=current.next.next;\n //lineNumber=141");
				} catch (IOException ioexception) {
				}
				current = current.next.next;
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"__marker__.mark();\n //lineNumber=142");
} catch (IOException ioexception) {
}
__marker__.mark();
			}
>>>>>>> instru
			try {
				FileUtils
						.appendToFile(
								"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
								"if(result == false && current != null){throw new RuntimeException();}\n");
			} catch (IOException ioexception) {
			}
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"__marker__.mark();\n //lineNumber=144");
} catch (IOException ioexception) {
}
__marker__.mark();
			if (!fajita_cicle_0) try {
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
								"roops_goal_1=true;\n //lineNumber=146");
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
<<<<<<< HEAD
>>>>>>> UNSAT 0 variables con marks
							"if(result == false && current != null){throw new RuntimeException();}\n");
=======
							"if(res == false && current != null){throw new RuntimeException();}\n");
>>>>>>> unsat error lines
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
<<<<<<< HEAD
							"//mutID 3\nreturn result; //                return !result; //mutGenLimit 1\n");
>>>>>>> generating weird sequential code
=======
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
=======
			roops_goal_1 = true;
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"__marker__.mark();\n //lineNumber=147");
} catch (IOException ioexception) {
}
__marker__.mark();
		}
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"__marker__.mark();\n //lineNumber=149");
} catch (IOException ioexception) {
}
__marker__.mark();
		try {
			FileUtils
					.appendToFile(
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
>>>>>>> static-field-not-found
							"//mutID 2\nreturn result; //                return !result; //mutGenLimit 1\n");
>>>>>>> UNSAT 0 variables con marks
=======
							"//mutID 2\nreturn res; //                return !result; //mutGenLimit 1\n");
>>>>>>> unsat error lines
		} catch (IOException ioexception) {
		}
		return res;
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
<<<<<<< HEAD
=======
   
>>>>>>> unsat error lines
=======
							"return result;\n //lineNumber=150");
		} catch (IOException ioexception) {
		}
		return result;
	}

	/*
	 * @
	 * 
	 * @ requires index>=0 && index<reach(this.header, SinglyLinkedListNode.class," next").int_size(); 
	 * 
	 * @ ensures reach(this.header, SinglyLinkedListNode.class," next").has(\result)==true; 
	 * 
	 * @ ensures reach(\result, SinglyLinkedListNode.class," next").int_size() ==  reach(this.header, SinglyLinkedListNode.class," next").int_size()-index; 
	 * 
	 * @ signals (Exception e) false;
	 * 
	 * @
	 */
	public SinglyLinkedListNode getNode(int index) {
		SinglyLinkedListNode current = header;
		SinglyLinkedListNode result = null;
		int current_index = 0;
		while (result == null && current != null) {
			if (index == current_index) {
				result = current;
			} else {
			}
			current_index = current_index + 1;
			current = current.next;
		}
		return result;
	}

	// ------------------------ insertBack --------------------------//
	// Due to jml4c the ensures clauses must be in that order :(
	/*
	 * @
	 * 
	 * @ requires freshNode!=null;
	 * 
	 * @ requires reach(header, SinglyLinkedListNode.class," next").has(freshNode)==false; 
	 * 
	 * @ ensures reach(header, SinglyLinkedListNode.class," next").int_size()==\old( reach(header, SinglyLinkedListNode.class," next")).int_size()+1; 
	 * 
	 * @ ensures (\forall SinglyLinkedListNode n;
	 * 
	 * @ \old(reach(header, SinglyLinkedListNode.class," next")).has(n); 
	 * 
	 * @ reach(header, SinglyLinkedListNode.class," next").has(n)==true 
	 * 
	 * @ );
	 * 
	 * @ ensures (\exists SinglyLinkedListNode n;
	 * 
	 * @ reach(header, SinglyLinkedListNode.class," next").has(n); 
	 * 
	 * @ n.next==null && n.value==data);
	 * 
	 * @ signals (Exception e) false;
	 * 
	 * @
	 */
	void insertBack(java.lang.Object data, SinglyLinkedListNode freshNode) {
		freshNode.value = data;
		freshNode.next = null;
		if (this.header == null) {
			this.header = freshNode;
		} else {
			SinglyLinkedListNode current = this.header;
			while (current.next != null) {
				current = current.next;
			}
			current.next = freshNode;
		}
	}
>>>>>>> instru
}
