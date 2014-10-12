package roops.core.objects;


import roops.core.objects.SinglyLinkedListNode;
import roops.core.objects.BugLineMarker;
import java.io.IOException;
import ar.edu.taco.utils.FileUtils;


/*@ nullable_by_default @*/
public class SinglyLinkedList {

    /*@
        @ invariant (\forall SinglyLinkedListNode n; \reach(header, SinglyLinkedListNode, next).has(n); \reach(n.next, SinglyLinkedListNode, next).has(n)==false);
        @*/
    public roops.core.objects.SinglyLinkedListNode header;

    public SinglyLinkedList () {
    }

//----------------- showInstance --------------------//
    /*@ requires \reach(this.header, SinglyLinkedListNode, next).int_size() == 100;
        @ ensures \result == false;
        @*/
    public boolean showInstance () {
        return true;
    }

//-------------------- contains -------------------------//

<<<<<<< HEAD
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
	 * @ invariant (\forall SinglyLinkedListNode n; \reach(header, SinglyLinkedListNode, next).has(n); \reach(n.next, SinglyLinkedListNode, next).has(n)==false);
	 * @
	 */
	public SinglyLinkedListNode header;

	public SinglyLinkedList() {
	}

	// ----------------- showInstance --------------------//
	/*
	 * @ requires \reach(this.header, SinglyLinkedListNode, next).int_size() == 100;
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
<<<<<<< HEAD
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
    @ ensures (\exists SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.value==value_param) <==> (\result==true);
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
=======
=======
    /** @Modifies_Everything
     * @Ensures false;
     */
    public boolean contains (  /*@nullable@*/ java.lang.Object value_param) {
        try {
>>>>>>> como te cabe mi picadura
			FileUtils
					.appendToFile(
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"fajita_roopsGoal_initialization();\n");
		} catch (IOException ioexception) {
		}
		fajita_roopsGoal_initialization();
<<<<<<< HEAD
>>>>>>> more
		try {
>>>>>>> instru
=======
        try {
			FileUtils
					.appendToFile(
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"BugLineMarker __marker__=new BugLineMarker(); //lineNumber=33\n");
		} catch (IOException ioexception) {
		}
		BugLineMarker __marker__ = new BugLineMarker();
        try {
>>>>>>> como te cabe mi picadura
			FileUtils
					.appendToFile(
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
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
=======
							"SinglyLinkedListNode current;\n //lineNumber=69");
>>>>>>> more
=======
							"SinglyLinkedListNode current; //lineNumber=69\n");
>>>>>>> lulafix
=======
							"__marker__.mark(); //lineNumber=34\n");
>>>>>>> como te cabe mi picadura
		} catch (IOException ioexception) {
		}
		__marker__.mark();
        try {
			FileUtils
					.appendToFile(
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
=======
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
>>>>>>> generating weird sequential code
=======
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> UNSAT 0 variables con marks
=======
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
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
=======
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
							"boolean result;\n //lineNumber=70");
>>>>>>> more
=======
							"boolean result; //lineNumber=70\n");
>>>>>>> lulafix
		} catch (IOException ioexception) {
		}
		boolean result;
		try {
<<<<<<< HEAD
>>>>>>> instru
=======
>>>>>>> more
=======
							"roops.core.objects.SinglyLinkedListNode current; //lineNumber=35\n");
		} catch (IOException ioexception) {
		}
		roops.core.objects.SinglyLinkedListNode current;
        try {
			FileUtils
					.appendToFile(
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"__marker__.mark(); //lineNumber=36\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark();
        try {
			FileUtils
					.appendToFile(
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"boolean result; //lineNumber=37\n");
		} catch (IOException ioexception) {
		}
		boolean result;
        try {
>>>>>>> como te cabe mi picadura
			FileUtils
					.appendToFile(
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"__marker__.mark(); //lineNumber=38\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark();
        try {
			FileUtils
					.appendToFile(
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"current=this.header; //lineNumber=40\n");
		} catch (IOException ioexception) {
		}
		current = this.header;
        try {
			FileUtils
					.appendToFile(
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
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
<<<<<<< HEAD
>>>>>>> generating weird sequential code
=======
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
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
=======
							"result=false;\n //lineNumber=72");
=======
							"result=false; //lineNumber=72\n");
>>>>>>> lulafix
=======
							"__marker__.mark(); //lineNumber=42\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark();
        try {
			FileUtils
					.appendToFile(
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"result=false; //lineNumber=43\n");
>>>>>>> como te cabe mi picadura
		} catch (IOException ioexception) {
		}
		result = false;
        try {
			FileUtils
					.appendToFile(
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"__marker__.mark(); //lineNumber=44\n");
		} catch (IOException ioexception) {
		}
<<<<<<< HEAD
		BugLineMarker dummy;
		{
>>>>>>> more
			try {
>>>>>>> static-field-not-found
=======
		__marker__.mark();
        try {
			FileUtils
					.appendToFile(
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"current=this.header.next; //lineNumber=45\n");
		} catch (IOException ioexception) {
		}
		current = this.header.next;
        try {
			FileUtils
					.appendToFile(
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"__marker__.mark(); //lineNumber=46\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark();
        {
            try {
>>>>>>> como te cabe mi picadura
				FileUtils
						.appendToFile(
								"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
								"boolean fajita_cicle_0=false; //lineNumber=48\n");
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
					FileUtils
							.appendToFile(
									"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"roops_goal_0=true;\n");
>>>>>>> static-field-not-found
=======
			boolean fajita_cicle_0 = false;

            while ( result == false && current != null ) {
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
									"fajita_cicle_0=true; //lineNumber=51\n");
				} catch (IOException ioexception) {
				}
				fajita_cicle_0 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
									"roops_goal_0=true;\n //lineNumber=87");
>>>>>>> instru
=======
									"roops_goal_0=true;\n //lineNumber=78");
>>>>>>> more
=======
									"roops_goal_0=true; //lineNumber=78\n");
>>>>>>> lulafix
=======
									"roops_goal_0=true; //lineNumber=52\n");
>>>>>>> como te cabe mi picadura
				} catch (IOException ioexception) {
				}
				roops_goal_0 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(); //lineNumber=53\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark();
                try {
					FileUtils
							.appendToFile(
									"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"boolean equalVal; //lineNumber=54\n");
				} catch (IOException ioexception) {
				}
				boolean equalVal;
                try {
					FileUtils
							.appendToFile(
									"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(); //lineNumber=55\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark();
                if ( value_param == null && current.value == null ) {
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
										"roops_goal_2=true; //lineNumber=57\n");
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
<<<<<<< HEAD
<<<<<<< HEAD
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
<<<<<<< HEAD
										"//mutID 0\nequalVal=true; //              equalVal = false; //mutGenLimit 1\n");
>>>>>>> static-field-not-found
=======
										"equalVal=true;\n //lineNumber=95");
>>>>>>> instru
=======
										"equalVal=true;\n //lineNumber=82");
>>>>>>> more
=======
										"equalVal=true; //lineNumber=82\n");
>>>>>>> lulafix
=======
										"__marker__.mark(); //lineNumber=58\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark();
                    try {
						FileUtils
								.appendToFile(
										"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"equalVal=true; //lineNumber=60\n");
>>>>>>> como te cabe mi picadura
					} catch (IOException ioexception) {
					}
					equalVal = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(); //lineNumber=62\n");
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
										"roops_goal_3=true; //lineNumber=64\n");
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
										"__marker__.mark(); //lineNumber=65\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark();
                    if ( value_param != null ) {
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
											"roops_goal_4=true; //lineNumber=67\n");
						} catch (IOException ioexception) {
						}
						roops_goal_4 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(); //lineNumber=68\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark();
                        if ( value_param == current.value ) {
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
												"roops_goal_6=true; //lineNumber=70\n");
							} catch (IOException ioexception) {
							}
							roops_goal_6 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(); //lineNumber=71\n");
							} catch (IOException ioexception) {
							}
							__marker__.mark();
                            try {
								FileUtils
										.appendToFile(
												"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=true; //lineNumber=72\n");
							} catch (IOException ioexception) {
							}
							equalVal = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(); //lineNumber=73\n");
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
												"roops_goal_7=true; //lineNumber=75\n");
							} catch (IOException ioexception) {
							}
							roops_goal_7 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(); //lineNumber=76\n");
							} catch (IOException ioexception) {
							}
							__marker__.mark();
                            try {
								FileUtils
										.appendToFile(
												"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=false; //lineNumber=77\n");
							} catch (IOException ioexception) {
							}
							equalVal = false;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
                        }
                    } else
                    {
=======
                            try {
								FileUtils
										.appendToFile(
												"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(); //lineNumber=78\n");
							} catch (IOException ioexception) {
							}
							__marker__.mark();
                        }
>>>>>>> como te cabe mi picadura
                        try {
							FileUtils
									.appendToFile(
											"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
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
=======
>>>>>>> more
=======
											"__marker__.mark(); //lineNumber=80\n");
						} catch (IOException ioexception) {
>>>>>>> como te cabe mi picadura
						}
						__marker__.mark();
                    } else {
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
											"if(value_param != null){throw new RuntimeException();}\n");
						} catch (IOException ioexception) {
						}
						try {
							FileUtils
									.appendToFile(
											"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"roops_goal_5=true; //lineNumber=82\n");
						} catch (IOException ioexception) {
						}
						roops_goal_5 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(); //lineNumber=83\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark();
                        try {
							FileUtils
									.appendToFile(
											"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
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
=======
											"equalVal=false;\n //lineNumber=96");
=======
											"equalVal=false; //lineNumber=96\n");
>>>>>>> lulafix
						} catch (IOException ioexception) {
						}
						equalVal = false;
>>>>>>> more
=======
											"equalVal=false; //lineNumber=84\n");
						} catch (IOException ioexception) {
						}
						equalVal = false;
                        try {
							FileUtils
									.appendToFile(
											"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(); //lineNumber=85\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark();
                    }
                    try {
						FileUtils
								.appendToFile(
										"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(); //lineNumber=87\n");
					} catch (IOException ioexception) {
>>>>>>> como te cabe mi picadura
					}
					__marker__.mark();
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(); //lineNumber=89\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark();
                if ( equalVal == true ) {
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
										"if(!(equalVal == true)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_8=true; //lineNumber=91\n");
					} catch (IOException ioexception) {
					}
					roops_goal_8 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
										"result=true;\n");
>>>>>>> static-field-not-found
=======
										"result=true;\n //lineNumber=133");
>>>>>>> instru
=======
										"result=true;\n //lineNumber=101");
>>>>>>> more
=======
										"result=true; //lineNumber=101\n");
>>>>>>> lulafix
=======
										"__marker__.mark(); //lineNumber=92\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark();
                    try {
						FileUtils
								.appendToFile(
										"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"result=true; //lineNumber=93\n");
>>>>>>> como te cabe mi picadura
					} catch (IOException ioexception) {
					}
					result = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(); //lineNumber=94\n");
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
										"roops_goal_9=true; //lineNumber=96\n");
					} catch (IOException ioexception) {
					}
					roops_goal_9 = true;
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(); //lineNumber=98\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark();
                try {
					FileUtils
							.appendToFile(
<<<<<<< HEAD
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
=======
									"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"current=current.next; //lineNumber=100\n");
				} catch (IOException ioexception) {
				}
<<<<<<< HEAD
				current = current.next.next;
>>>>>>> more
			}
>>>>>>> instru
=======
				current = current.next;
                try {
					FileUtils
							.appendToFile(
									"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(); //lineNumber=102\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark();
            }
>>>>>>> como te cabe mi picadura
			try {
				FileUtils
						.appendToFile(
								"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
								"if(result == false && current != null){throw new RuntimeException();}\n");
			} catch (IOException ioexception) {
			}
<<<<<<< HEAD
<<<<<<< HEAD
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"__marker__.mark();\n //lineNumber=144");
} catch (IOException ioexception) {
}
__marker__.mark();
=======
>>>>>>> more
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
								"roops_goal_1=true; //lineNumber=108");
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
<<<<<<< HEAD
try {
	FileUtils
			.appendToFile(
					"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
					"__marker__.mark();\n //lineNumber=147");
} catch (IOException ioexception) {
}
__marker__.mark();
=======
>>>>>>> more
=======
            if ( ! fajita_cicle_0 ) {
                try {
					FileUtils
							.appendToFile(
									"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"if(!(!fajita_cicle_0)){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"roops_goal_1=true; //lineNumber=105\n");
				} catch (IOException ioexception) {
				}
				roops_goal_1 = true;
            }
        }
        try {
			FileUtils
					.appendToFile(
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"__marker__.mark(); //lineNumber=108\n");
		} catch (IOException ioexception) {
>>>>>>> como te cabe mi picadura
		}
		__marker__.mark();
        try {
			FileUtils
					.appendToFile(
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
<<<<<<< HEAD
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
    @ requires index>=0 && index<\reach(this.header, SinglyLinkedListNode, next).int_size();
    @ ensures \reach(this.header, SinglyLinkedListNode, next).has(\result)==true;
    @ ensures \reach(\result, SinglyLinkedListNode, next).int_size() == \reach(this.header, SinglyLinkedListNode, next).int_size()-index;
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
    @ requires \reach(header, SinglyLinkedListNode, next).has(freshNode)==false;
    @ ensures \reach(header, SinglyLinkedListNode, next).int_size()==\old(\reach(header, SinglyLinkedListNode, next)).int_size()+1;
    @ ensures (\forall SinglyLinkedListNode n;
    @            \old(\reach(header, SinglyLinkedListNode, next)).has(n);
    @      \reach(header, SinglyLinkedListNode, next).has(n)==true
    @         );
    @ ensures (\exists SinglyLinkedListNode n;
    @            \reach(header, SinglyLinkedListNode, next).has(n);
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
=======
							"return result;\n //lineNumber=110");
>>>>>>> more
=======
							"return result; //lineNumber=110\n");
>>>>>>> lulafix
		} catch (IOException ioexception) {
		}
		return result;
    }
    /*@
        @ requires index>=0 && index<\reach(this.header, SinglyLinkedListNode, next).int_size();
        @ ensures \reach(this.header, SinglyLinkedListNode, next).has(\result)==true;
        @ ensures \reach(\result, SinglyLinkedListNode, next).int_size() == \reach(this.header, SinglyLinkedListNode, next).int_size()-index;
        @ signals (Exception e) false;
        @*/
    public roops.core.objects.SinglyLinkedListNode getNode ( int index) {
        roops.core.objects.SinglyLinkedListNode current = header;
        roops.core.objects.SinglyLinkedListNode result = null;
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

//------------------------ insertBack --------------------------//
//Due to jml4c the ensures clauses must be in that order :(
    /*@
        @ requires freshNode!=null;
        @ requires \reach(header, SinglyLinkedListNode, next).has(freshNode)==false;
        @ ensures \reach(header, SinglyLinkedListNode, next).int_size()==\old(\reach(header, SinglyLinkedListNode, next)).int_size()+1;
        @ ensures (\forall SinglyLinkedListNode n;
        @            \old(\reach(header, SinglyLinkedListNode, next)).has(n);
        @      \reach(header, SinglyLinkedListNode, next).has(n)==true
        @         );
        @ ensures (\exists SinglyLinkedListNode n;
        @            \reach(header, SinglyLinkedListNode, next).has(n);
        @            n.next==null && n.value==data);
        @ signals (Exception e) false;
        @*/
    void insertBack ( java.lang.Object data, roops.core.objects.SinglyLinkedListNode freshNode) {
        freshNode.value = data;
        freshNode.next = null;
        if ( this.header == null ) {
            this.header = freshNode;
        } else {
            roops.core.objects.SinglyLinkedListNode current = this.header;
            while ( current.next != null ) {
                current = current.next;
            }
            current.next = freshNode;
        }
    }

<<<<<<< HEAD
	// ------------------------ insertBack --------------------------//
	// Due to jml4c the ensures clauses must be in that order :(
	/*
	 * @
	 * 
	 * @ requires freshNode!=null;
	 * 
	 * @ requires \reach(header, SinglyLinkedListNode, next).has(freshNode)==false;
	 * 
	 * @ ensures \reach(header, SinglyLinkedListNode, next).int_size()==\old(\reach(header, SinglyLinkedListNode, next)).int_size()+1;
	 * 
	 * @ ensures (\forall SinglyLinkedListNode n;
	 * 
	 * @ \old(\reach(header, SinglyLinkedListNode, next)).has(n);
	 * 
	 * @ \reach(header, SinglyLinkedListNode, next).has(n)==true
	 * 
	 * @ );
	 * 
	 * @ ensures (\exists SinglyLinkedListNode n;
	 * 
	 * @ \reach(header, SinglyLinkedListNode, next).has(n);
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
<<<<<<< HEAD
>>>>>>> instru
=======
>>>>>>> more
=======
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
>>>>>>> como te cabe mi picadura
}
