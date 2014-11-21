// This is mutant program.
// Author : ysma

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
<<<<<<< HEAD
<<<<<<< HEAD

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
=======
    /*@
        @ requires value_param != null;
        @ ensures (\exists SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.value==value_param)
        @     <==> (\result==true);
        @ signals (RuntimeException e) false;
        @*/
>>>>>>> falta unroll
=======

//-------------------- contains -------------------------//

    /** @Modifies_Everything
     * @Ensures false;
     */
>>>>>>> loop unroll, on the floor
    public boolean contains (  /*@nullable@*/ java.lang.Object value_param) {
        try {
>>>>>>> como te cabe mi picadura
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
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
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
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
=======
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> working loop =)
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
=======
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> working loop =)
							"roops.core.objects.SinglyLinkedListNode current; //lineNumber=35\n");
=======
							"roops.core.objects.SinglyLinkedListNode current; //lineNumber=36\n");
>>>>>>> falta unroll
		} catch (IOException ioexception) {
		}
		roops.core.objects.SinglyLinkedListNode current;
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"BugLineMarker dummy; //lineNumber=37\n");
		} catch (IOException ioexception) {
		}
		BugLineMarker dummy;
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"boolean result; //lineNumber=38\n");
		} catch (IOException ioexception) {
		}
		boolean result;
        try {
>>>>>>> como te cabe mi picadura
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"current=this.header; //lineNumber=40\n");
		} catch (IOException ioexception) {
		}
		current = this.header;
        try {
			FileUtils
					.appendToFile(
<<<<<<< HEAD
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
=======
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
>>>>>>> working loop =)
							"__marker__.mark(); //lineNumber=42\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark();
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"result=false; //lineNumber=43\n");
>>>>>>> como te cabe mi picadura
		} catch (IOException ioexception) {
		}
		result = false;
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
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
        // current = this.header.next;
=======
							"result=false; //lineNumber=42\n");
		} catch (IOException ioexception) {
		}
		result = false;
>>>>>>> falta unroll
        {
            try {
>>>>>>> como te cabe mi picadura
				FileUtils
						.appendToFile(
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
								"boolean terminatesInTime=false; //lineNumber=44\n");
			} catch (IOException ioexception) {
			}
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
			equalVal = true;
>>>>>>> UNSAT 0 variables con marks
=======
			boolean terminatesInTime = false;
            if ( result == false && current != null ) {
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"if(!(result == false && current != null)){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"roops_goal_0=true; //lineNumber=46\n");
				} catch (IOException ioexception) {
				}
				roops_goal_0 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"boolean equalVal; //lineNumber=47\n");
				} catch (IOException ioexception) {
				}
				boolean equalVal;
                if ( value_param != null && current.value == null ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(!(value_param != null && current.value == null)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_2=true; //lineNumber=49\n");
					} catch (IOException ioexception) {
					}
					roops_goal_2 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"equalVal=true; //lineNumber=51\n");
					} catch (IOException ioexception) {
					}
					equalVal = true;
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(value_param != null && current.value == null){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_3=true; //lineNumber=54\n");
					} catch (IOException ioexception) {
					}
					roops_goal_3 = true;
                    if ( value_param != null ) {
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"if(!(value_param != null)){throw new RuntimeException();}\n");
						} catch (IOException ioexception) {
						}
						try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"roops_goal_4=true; //lineNumber=56\n");
						} catch (IOException ioexception) {
						}
						roops_goal_4 = true;
                        if ( value_param == current.value ) {
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"if(!(value_param == current.value)){throw new RuntimeException();}\n");
							} catch (IOException ioexception) {
							}
							try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"roops_goal_6=true; //lineNumber=58\n");
							} catch (IOException ioexception) {
							}
							roops_goal_6 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=true; //lineNumber=59\n");
							} catch (IOException ioexception) {
							}
							equalVal = true;
                        } else {
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"if(value_param == current.value){throw new RuntimeException();}\n");
							} catch (IOException ioexception) {
							}
							try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"roops_goal_7=true; //lineNumber=61\n");
							} catch (IOException ioexception) {
							}
							roops_goal_7 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=false; //lineNumber=62\n");
							} catch (IOException ioexception) {
							}
							equalVal = false;
                        }
                    } else {
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"if(value_param != null){throw new RuntimeException();}\n");
						} catch (IOException ioexception) {
						}
						try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"roops_goal_5=true; //lineNumber=65\n");
						} catch (IOException ioexception) {
						}
						roops_goal_5 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"equalVal=false; //lineNumber=66\n");
						} catch (IOException ioexception) {
						}
						equalVal = false;
                    }
                }
                if ( equalVal == true ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(!(equalVal == true)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_8=true; //lineNumber=70\n");
					} catch (IOException ioexception) {
					}
					roops_goal_8 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"result=false; //lineNumber=71\n");
					} catch (IOException ioexception) {
					}
					result = false;
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(equalVal == true){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_9=true; //lineNumber=73\n");
					} catch (IOException ioexception) {
					}
					roops_goal_9 = true;
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"current=current.next; //lineNumber=76\n");
				} catch (IOException ioexception) {
				}
				current = current.next;
            } else {
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"if(result == false && current != null){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"roops_goal_1=true; //lineNumber=79\n");
				} catch (IOException ioexception) {
				}
				roops_goal_1 = true;
            }
            if ( result == false && current != null ) {
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"if(!(result == false && current != null)){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"roops_goal_10=true; //lineNumber=82\n");
				} catch (IOException ioexception) {
				}
				roops_goal_10 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"boolean equalVal; //lineNumber=83\n");
				} catch (IOException ioexception) {
				}
				boolean equalVal;
                if ( value_param != null && current.value == null ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(!(value_param != null && current.value == null)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_12=true; //lineNumber=85\n");
					} catch (IOException ioexception) {
					}
					roops_goal_12 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"equalVal=true; //lineNumber=87\n");
					} catch (IOException ioexception) {
					}
					equalVal = true;
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(value_param != null && current.value == null){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_13=true; //lineNumber=90\n");
					} catch (IOException ioexception) {
					}
					roops_goal_13 = true;
                    if ( value_param != null ) {
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"if(!(value_param != null)){throw new RuntimeException();}\n");
						} catch (IOException ioexception) {
						}
						try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"roops_goal_14=true; //lineNumber=92\n");
						} catch (IOException ioexception) {
						}
						roops_goal_14 = true;
                        if ( value_param == current.value ) {
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"if(!(value_param == current.value)){throw new RuntimeException();}\n");
							} catch (IOException ioexception) {
							}
							try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"roops_goal_16=true; //lineNumber=94\n");
							} catch (IOException ioexception) {
							}
							roops_goal_16 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=true; //lineNumber=95\n");
							} catch (IOException ioexception) {
							}
							equalVal = true;
                        } else {
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"if(value_param == current.value){throw new RuntimeException();}\n");
							} catch (IOException ioexception) {
							}
							try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"roops_goal_17=true; //lineNumber=97\n");
							} catch (IOException ioexception) {
							}
							roops_goal_17 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=false; //lineNumber=98\n");
							} catch (IOException ioexception) {
							}
							equalVal = false;
                        }
                    } else {
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"if(value_param != null){throw new RuntimeException();}\n");
						} catch (IOException ioexception) {
						}
						try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"roops_goal_15=true; //lineNumber=101\n");
						} catch (IOException ioexception) {
						}
						roops_goal_15 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"equalVal=false; //lineNumber=102\n");
						} catch (IOException ioexception) {
						}
						equalVal = false;
                    }
                }
                if ( equalVal == true ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(!(equalVal == true)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_18=true; //lineNumber=106\n");
					} catch (IOException ioexception) {
					}
					roops_goal_18 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"result=false; //lineNumber=107\n");
					} catch (IOException ioexception) {
					}
					result = false;
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(equalVal == true){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_19=true; //lineNumber=109\n");
					} catch (IOException ioexception) {
					}
					roops_goal_19 = true;
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"current=current.next; //lineNumber=112\n");
				} catch (IOException ioexception) {
				}
				current = current.next;
            } else {
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"if(result == false && current != null){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"roops_goal_11=true; //lineNumber=115\n");
				} catch (IOException ioexception) {
				}
				roops_goal_11 = true;
            }
            if ( result == false && current != null ) {
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"if(!(result == false && current != null)){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"roops_goal_20=true; //lineNumber=118\n");
				} catch (IOException ioexception) {
				}
				roops_goal_20 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"boolean equalVal; //lineNumber=119\n");
				} catch (IOException ioexception) {
				}
				boolean equalVal;
                if ( value_param != null && current.value == null ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(!(value_param != null && current.value == null)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_22=true; //lineNumber=121\n");
					} catch (IOException ioexception) {
					}
					roops_goal_22 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"equalVal=true; //lineNumber=123\n");
					} catch (IOException ioexception) {
					}
					equalVal = true;
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(value_param != null && current.value == null){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_23=true; //lineNumber=126\n");
					} catch (IOException ioexception) {
					}
					roops_goal_23 = true;
                    if ( value_param != null ) {
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"if(!(value_param != null)){throw new RuntimeException();}\n");
						} catch (IOException ioexception) {
						}
						try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"roops_goal_24=true; //lineNumber=128\n");
						} catch (IOException ioexception) {
						}
						roops_goal_24 = true;
                        if ( value_param == current.value ) {
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"if(!(value_param == current.value)){throw new RuntimeException();}\n");
							} catch (IOException ioexception) {
							}
							try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"roops_goal_26=true; //lineNumber=130\n");
							} catch (IOException ioexception) {
							}
							roops_goal_26 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=true; //lineNumber=131\n");
							} catch (IOException ioexception) {
							}
							equalVal = true;
                        } else {
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"if(value_param == current.value){throw new RuntimeException();}\n");
							} catch (IOException ioexception) {
							}
							try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"roops_goal_27=true; //lineNumber=133\n");
							} catch (IOException ioexception) {
							}
							roops_goal_27 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=false; //lineNumber=134\n");
							} catch (IOException ioexception) {
							}
							equalVal = false;
                        }
                    } else {
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"if(value_param != null){throw new RuntimeException();}\n");
						} catch (IOException ioexception) {
						}
						try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"roops_goal_25=true; //lineNumber=137\n");
						} catch (IOException ioexception) {
						}
						roops_goal_25 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"equalVal=false; //lineNumber=138\n");
						} catch (IOException ioexception) {
						}
						equalVal = false;
                    }
                }
                if ( equalVal == true ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(!(equalVal == true)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_28=true; //lineNumber=142\n");
					} catch (IOException ioexception) {
					}
					roops_goal_28 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"result=false; //lineNumber=143\n");
					} catch (IOException ioexception) {
					}
					result = false;
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(equalVal == true){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_29=true; //lineNumber=145\n");
					} catch (IOException ioexception) {
					}
					roops_goal_29 = true;
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"current=current.next; //lineNumber=148\n");
				} catch (IOException ioexception) {
				}
				current = current.next;
            } else {
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"if(result == false && current != null){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"roops_goal_21=true; //lineNumber=151\n");
				} catch (IOException ioexception) {
				}
				roops_goal_21 = true;
            }
            if ( result == false && current != null ) {
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"if(!(result == false && current != null)){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"roops_goal_30=true; //lineNumber=154\n");
				} catch (IOException ioexception) {
				}
				roops_goal_30 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"boolean equalVal; //lineNumber=155\n");
				} catch (IOException ioexception) {
				}
				boolean equalVal;
                if ( value_param != null && current.value == null ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(!(value_param != null && current.value == null)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_32=true; //lineNumber=157\n");
					} catch (IOException ioexception) {
					}
					roops_goal_32 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"equalVal=true; //lineNumber=159\n");
					} catch (IOException ioexception) {
					}
					equalVal = true;
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(value_param != null && current.value == null){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_33=true; //lineNumber=162\n");
					} catch (IOException ioexception) {
					}
					roops_goal_33 = true;
                    if ( value_param != null ) {
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"if(!(value_param != null)){throw new RuntimeException();}\n");
						} catch (IOException ioexception) {
						}
						try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"roops_goal_34=true; //lineNumber=164\n");
						} catch (IOException ioexception) {
						}
						roops_goal_34 = true;
                        if ( value_param == current.value ) {
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"if(!(value_param == current.value)){throw new RuntimeException();}\n");
							} catch (IOException ioexception) {
							}
							try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"roops_goal_36=true; //lineNumber=166\n");
							} catch (IOException ioexception) {
							}
							roops_goal_36 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=true; //lineNumber=167\n");
							} catch (IOException ioexception) {
							}
							equalVal = true;
                        } else {
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"if(value_param == current.value){throw new RuntimeException();}\n");
							} catch (IOException ioexception) {
							}
							try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"roops_goal_37=true; //lineNumber=169\n");
							} catch (IOException ioexception) {
							}
							roops_goal_37 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=false; //lineNumber=170\n");
							} catch (IOException ioexception) {
							}
							equalVal = false;
                        }
                    } else {
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"if(value_param != null){throw new RuntimeException();}\n");
						} catch (IOException ioexception) {
						}
						try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"roops_goal_35=true; //lineNumber=173\n");
						} catch (IOException ioexception) {
						}
						roops_goal_35 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"equalVal=false; //lineNumber=174\n");
						} catch (IOException ioexception) {
						}
						equalVal = false;
                    }
                }
                if ( equalVal == true ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(!(equalVal == true)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_38=true; //lineNumber=178\n");
					} catch (IOException ioexception) {
					}
					roops_goal_38 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"result=false; //lineNumber=179\n");
					} catch (IOException ioexception) {
					}
					result = false;
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(equalVal == true){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_39=true; //lineNumber=181\n");
					} catch (IOException ioexception) {
					}
					roops_goal_39 = true;
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"current=current.next; //lineNumber=184\n");
				} catch (IOException ioexception) {
				}
				current = current.next;
            } else {
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"if(result == false && current != null){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"roops_goal_31=true; //lineNumber=187\n");
				} catch (IOException ioexception) {
				}
				roops_goal_31 = true;
            }
            if ( result == false && current != null ) {
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"if(!(result == false && current != null)){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"roops_goal_40=true; //lineNumber=190\n");
				} catch (IOException ioexception) {
				}
				roops_goal_40 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"boolean equalVal; //lineNumber=191\n");
				} catch (IOException ioexception) {
				}
				boolean equalVal;
                if ( value_param != null && current.value == null ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(!(value_param != null && current.value == null)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_42=true; //lineNumber=193\n");
					} catch (IOException ioexception) {
					}
					roops_goal_42 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"equalVal=true; //lineNumber=195\n");
					} catch (IOException ioexception) {
					}
					equalVal = true;
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(value_param != null && current.value == null){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_43=true; //lineNumber=198\n");
					} catch (IOException ioexception) {
					}
					roops_goal_43 = true;
                    if ( value_param != null ) {
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"if(!(value_param != null)){throw new RuntimeException();}\n");
						} catch (IOException ioexception) {
						}
						try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"roops_goal_44=true; //lineNumber=200\n");
						} catch (IOException ioexception) {
						}
						roops_goal_44 = true;
                        if ( value_param == current.value ) {
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"if(!(value_param == current.value)){throw new RuntimeException();}\n");
							} catch (IOException ioexception) {
							}
							try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"roops_goal_46=true; //lineNumber=202\n");
							} catch (IOException ioexception) {
							}
							roops_goal_46 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=true; //lineNumber=203\n");
							} catch (IOException ioexception) {
							}
							equalVal = true;
                        } else {
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"if(value_param == current.value){throw new RuntimeException();}\n");
							} catch (IOException ioexception) {
							}
							try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"roops_goal_47=true; //lineNumber=205\n");
							} catch (IOException ioexception) {
							}
							roops_goal_47 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=false; //lineNumber=206\n");
							} catch (IOException ioexception) {
							}
							equalVal = false;
                        }
                    } else {
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"if(value_param != null){throw new RuntimeException();}\n");
						} catch (IOException ioexception) {
						}
						try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"roops_goal_45=true; //lineNumber=209\n");
						} catch (IOException ioexception) {
						}
						roops_goal_45 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"equalVal=false; //lineNumber=210\n");
						} catch (IOException ioexception) {
						}
						equalVal = false;
                    }
                }
                if ( equalVal == true ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(!(equalVal == true)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_48=true; //lineNumber=214\n");
					} catch (IOException ioexception) {
					}
					roops_goal_48 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"result=false; //lineNumber=215\n");
					} catch (IOException ioexception) {
					}
					result = false;
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(equalVal == true){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_49=true; //lineNumber=217\n");
					} catch (IOException ioexception) {
					}
					roops_goal_49 = true;
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"current=current.next; //lineNumber=220\n");
				} catch (IOException ioexception) {
				}
				current = current.next;
>>>>>>> loop unroll, on the floor
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
=======
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"if(result == false && current != null){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"roops_goal_41=true; //lineNumber=223\n");
				} catch (IOException ioexception) {
				}
				roops_goal_41 = true;
            }
            if ( result == false && current != null ) {
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"if(!(result == false && current != null)){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"roops_goal_50=true; //lineNumber=226\n");
				} catch (IOException ioexception) {
				}
				roops_goal_50 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"boolean equalVal; //lineNumber=227\n");
				} catch (IOException ioexception) {
				}
				boolean equalVal;
                if ( value_param != null && current.value == null ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(!(value_param != null && current.value == null)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_52=true; //lineNumber=229\n");
					} catch (IOException ioexception) {
					}
					roops_goal_52 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"equalVal=true; //lineNumber=231\n");
					} catch (IOException ioexception) {
					}
					equalVal = true;
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(value_param != null && current.value == null){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_53=true; //lineNumber=234\n");
					} catch (IOException ioexception) {
					}
					roops_goal_53 = true;
                    if ( value_param != null ) {
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"if(!(value_param != null)){throw new RuntimeException();}\n");
						} catch (IOException ioexception) {
						}
						try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"roops_goal_54=true; //lineNumber=236\n");
						} catch (IOException ioexception) {
						}
						roops_goal_54 = true;
                        if ( value_param == current.value ) {
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"if(!(value_param == current.value)){throw new RuntimeException();}\n");
							} catch (IOException ioexception) {
							}
							try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"roops_goal_56=true; //lineNumber=238\n");
							} catch (IOException ioexception) {
							}
							roops_goal_56 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=true; //lineNumber=239\n");
							} catch (IOException ioexception) {
							}
							equalVal = true;
                        } else {
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"if(value_param == current.value){throw new RuntimeException();}\n");
							} catch (IOException ioexception) {
							}
							try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"roops_goal_57=true; //lineNumber=241\n");
							} catch (IOException ioexception) {
							}
							roops_goal_57 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=false; //lineNumber=242\n");
							} catch (IOException ioexception) {
							}
							equalVal = false;
                        }
                    } else {
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"if(value_param != null){throw new RuntimeException();}\n");
						} catch (IOException ioexception) {
						}
						try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"roops_goal_55=true; //lineNumber=245\n");
						} catch (IOException ioexception) {
						}
						roops_goal_55 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"equalVal=false; //lineNumber=246\n");
						} catch (IOException ioexception) {
						}
						equalVal = false;
                    }
                }
                if ( equalVal == true ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(!(equalVal == true)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_58=true; //lineNumber=250\n");
					} catch (IOException ioexception) {
					}
					roops_goal_58 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"result=false; //lineNumber=251\n");
					} catch (IOException ioexception) {
					}
					result = false;
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(equalVal == true){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_59=true; //lineNumber=253\n");
					} catch (IOException ioexception) {
					}
					roops_goal_59 = true;
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"current=current.next; //lineNumber=256\n");
				} catch (IOException ioexception) {
				}
				current = current.next;
            } else {
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"if(result == false && current != null){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"roops_goal_51=true; //lineNumber=259\n");
				} catch (IOException ioexception) {
				}
				roops_goal_51 = true;
            }
            if ( result == false && current != null ) {
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"if(!(result == false && current != null)){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"roops_goal_60=true; //lineNumber=262\n");
				} catch (IOException ioexception) {
				}
				roops_goal_60 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"boolean equalVal; //lineNumber=263\n");
				} catch (IOException ioexception) {
				}
				boolean equalVal;
                if ( value_param != null && current.value == null ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(!(value_param != null && current.value == null)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_62=true; //lineNumber=265\n");
					} catch (IOException ioexception) {
					}
					roops_goal_62 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"equalVal=true; //lineNumber=267\n");
					} catch (IOException ioexception) {
					}
					equalVal = true;
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(value_param != null && current.value == null){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_63=true; //lineNumber=270\n");
					} catch (IOException ioexception) {
					}
					roops_goal_63 = true;
                    if ( value_param != null ) {
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"if(!(value_param != null)){throw new RuntimeException();}\n");
						} catch (IOException ioexception) {
						}
						try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"roops_goal_64=true; //lineNumber=272\n");
						} catch (IOException ioexception) {
						}
						roops_goal_64 = true;
                        if ( value_param == current.value ) {
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"if(!(value_param == current.value)){throw new RuntimeException();}\n");
							} catch (IOException ioexception) {
							}
							try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"roops_goal_66=true; //lineNumber=274\n");
							} catch (IOException ioexception) {
							}
							roops_goal_66 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=true; //lineNumber=275\n");
							} catch (IOException ioexception) {
							}
							equalVal = true;
                        } else {
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"if(value_param == current.value){throw new RuntimeException();}\n");
							} catch (IOException ioexception) {
							}
							try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"roops_goal_67=true; //lineNumber=277\n");
							} catch (IOException ioexception) {
							}
							roops_goal_67 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=false; //lineNumber=278\n");
							} catch (IOException ioexception) {
							}
							equalVal = false;
                        }
                    } else {
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"if(value_param != null){throw new RuntimeException();}\n");
						} catch (IOException ioexception) {
						}
						try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"roops_goal_65=true; //lineNumber=281\n");
						} catch (IOException ioexception) {
						}
						roops_goal_65 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"equalVal=false; //lineNumber=282\n");
						} catch (IOException ioexception) {
						}
						equalVal = false;
                    }
                }
                if ( equalVal == true ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(!(equalVal == true)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_68=true; //lineNumber=286\n");
					} catch (IOException ioexception) {
					}
					roops_goal_68 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"result=false; //lineNumber=287\n");
					} catch (IOException ioexception) {
					}
					result = false;
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(equalVal == true){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_69=true; //lineNumber=289\n");
					} catch (IOException ioexception) {
					}
					roops_goal_69 = true;
                }
>>>>>>> loop unroll, on the floor
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"current=current.next; //lineNumber=292\n");
				} catch (IOException ioexception) {
				}
				current = current.next;
            } else {
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"if(result == false && current != null){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"roops_goal_61=true; //lineNumber=295\n");
				} catch (IOException ioexception) {
				}
				roops_goal_61 = true;
            }
            if ( result == false && current != null ) {
                try {
					FileUtils
							.appendToFile(
<<<<<<< HEAD
									"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
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
=======
=======
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> working loop =)
									"roops_goal_0=true; //lineNumber=51\n");
>>>>>>> lula map inverse
=======
									"roops_goal_0=true; //lineNumber=48\n");
>>>>>>> falta unroll
=======
									"if(!(result == false && current != null)){throw new RuntimeException();}\n");
>>>>>>> loop unroll, on the floor
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"roops_goal_70=true; //lineNumber=298\n");
				} catch (IOException ioexception) {
				}
				roops_goal_70 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"boolean equalVal; //lineNumber=299\n");
				} catch (IOException ioexception) {
				}
				boolean equalVal;
                if ( value_param != null && current.value == null ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(!(value_param != null && current.value == null)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_72=true; //lineNumber=301\n");
					} catch (IOException ioexception) {
					}
					roops_goal_72 = true;
                    try {
						FileUtils
								.appendToFile(
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
										"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
<<<<<<< HEAD
=======
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
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
=======
=======
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> working loop =)
										"__marker__.mark(); //lineNumber=57\n");
>>>>>>> lula map inverse
					} catch (IOException ioexception) {
					}
					__marker__.mark();
                    try {
						FileUtils
								.appendToFile(
<<<<<<< HEAD
										"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
										"equalVal=true; //lineNumber=60\n");
>>>>>>> como te cabe mi picadura
=======
										"equalVal=true; //lineNumber=59\n");
>>>>>>> lula map inverse
=======
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"//mutID 0\nequalVal=false; //mutGenLimit 1 //lineNumber=58\n");
>>>>>>> working loop =)
					} catch (IOException ioexception) {
					}
					equalVal = false;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
										"//mutID 1\n__marker__.mark(); //mutGenLimit 1 //lineNumber=59\n");
=======
										"equalVal=true; //lineNumber=53\n");
>>>>>>> falta unroll
=======
										"equalVal=true; //lineNumber=303\n");
>>>>>>> loop unroll, on the floor
					} catch (IOException ioexception) {
					}
					equalVal = true;
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(value_param != null && current.value == null){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_73=true; //lineNumber=306\n");
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
											"if(value_param == current.value){throw new RuntimeException();}\n");
=======
					roops_goal_3 = true;
=======
					roops_goal_73 = true;
>>>>>>> loop unroll, on the floor
                    if ( value_param != null ) {
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"if(!(value_param != null)){throw new RuntimeException();}\n");
						} catch (IOException ioexception) {
						}
						try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"roops_goal_74=true; //lineNumber=308\n");
						} catch (IOException ioexception) {
						}
						roops_goal_74 = true;
                        if ( value_param == current.value ) {
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"if(!(value_param == current.value)){throw new RuntimeException();}\n");
							} catch (IOException ioexception) {
							}
							try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"roops_goal_76=true; //lineNumber=310\n");
							} catch (IOException ioexception) {
							}
							roops_goal_76 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=true; //lineNumber=311\n");
							} catch (IOException ioexception) {
							}
							equalVal = true;
                        } else {
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"if(value_param == current.value){throw new RuntimeException();}\n");
							} catch (IOException ioexception) {
							}
							try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"roops_goal_77=true; //lineNumber=313\n");
							} catch (IOException ioexception) {
							}
							roops_goal_77 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=false; //lineNumber=314\n");
							} catch (IOException ioexception) {
							}
							equalVal = false;
<<<<<<< HEAD
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
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(); //lineNumber=77\n");
							} catch (IOException ioexception) {
							}
							__marker__.mark();
                        }
>>>>>>> como te cabe mi picadura
                        try {
							FileUtils
									.appendToFile(
<<<<<<< HEAD
											"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
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
=======
=======
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> working loop =)
											"__marker__.mark(); //lineNumber=79\n");
>>>>>>> lula map inverse
						} catch (IOException ioexception) {
>>>>>>> como te cabe mi picadura
						}
						__marker__.mark();
=======
                        }
>>>>>>> falta unroll
                    } else {
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
=======
											"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
=======
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> working loop =)
											"if(value_param != null){throw new RuntimeException();}\n");
						} catch (IOException ioexception) {
						}
						try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"roops_goal_75=true; //lineNumber=317\n");
						} catch (IOException ioexception) {
						}
						roops_goal_75 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
<<<<<<< HEAD
											"__marker__.mark(); //lineNumber=82\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark();
                        try {
							FileUtils
									.appendToFile(
<<<<<<< HEAD
											"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
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
=======
=======
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> working loop =)
											"equalVal=false; //lineNumber=83\n");
>>>>>>> lula map inverse
=======
											"equalVal=false; //lineNumber=68\n");
>>>>>>> falta unroll
=======
											"equalVal=false; //lineNumber=318\n");
>>>>>>> loop unroll, on the floor
						} catch (IOException ioexception) {
						}
						equalVal = false;
                    }
<<<<<<< HEAD
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(); //lineNumber=86\n");
					} catch (IOException ioexception) {
>>>>>>> como te cabe mi picadura
					}
					__marker__.mark();
=======
>>>>>>> falta unroll
                }
                if ( equalVal == true ) {
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
										"equalVal=false;\n");
=======
										"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
=======
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> working loop =)
										"if(!(equalVal == true)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_78=true; //lineNumber=322\n");
					} catch (IOException ioexception) {
					}
					roops_goal_78 = true;
                    try {
						FileUtils
								.appendToFile(
<<<<<<< HEAD
										"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
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
=======
=======
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
>>>>>>> working loop =)
										"__marker__.mark(); //lineNumber=91\n");
>>>>>>> lula map inverse
					} catch (IOException ioexception) {
					}
					__marker__.mark();
                    try {
						FileUtils
								.appendToFile(
<<<<<<< HEAD
										"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
										"result=true; //lineNumber=93\n");
>>>>>>> como te cabe mi picadura
=======
=======
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> working loop =)
										"result=true; //lineNumber=92\n");
>>>>>>> lula map inverse
					} catch (IOException ioexception) {
					}
					result = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
										"__marker__.mark(); //lineNumber=93\n");
=======
										"result=false; //lineNumber=73\n");
>>>>>>> falta unroll
=======
										"result=false; //lineNumber=323\n");
>>>>>>> loop unroll, on the floor
					} catch (IOException ioexception) {
					}
					result = false;
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(equalVal == true){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_79=true; //lineNumber=325\n");
					} catch (IOException ioexception) {
					}
					roops_goal_79 = true;
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
<<<<<<< HEAD
									"__marker__.mark(); //lineNumber=97\n");
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
=======
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
>>>>>>> working loop =)
									"current=current.next; //lineNumber=99\n");
=======
									"current=current.next; //lineNumber=78\n");
>>>>>>> falta unroll
=======
									"current=current.next; //lineNumber=328\n");
>>>>>>> loop unroll, on the floor
				} catch (IOException ioexception) {
				}
<<<<<<< HEAD
				current = current.next.next;
>>>>>>> more
			}
>>>>>>> instru
=======
				current = current.next;
            } else {
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"if(result == false && current != null){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"roops_goal_71=true; //lineNumber=331\n");
				} catch (IOException ioexception) {
				}
				roops_goal_71 = true;
            }
<<<<<<< HEAD
>>>>>>> como te cabe mi picadura
			try {
				FileUtils
						.appendToFile(
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
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
=======
            if ( result == false && current != null ) {
>>>>>>> loop unroll, on the floor
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"if(!(result == false && current != null)){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"roops_goal_80=true; //lineNumber=334\n");
				} catch (IOException ioexception) {
				}
				roops_goal_80 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"terminatesInTime=true; //lineNumber=335\n");
				} catch (IOException ioexception) {
				}
				terminatesInTime = true;
            } else {
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"if(result == false && current != null){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"roops_goal_81=true; //lineNumber=337\n");
				} catch (IOException ioexception) {
				}
				roops_goal_81 = true;
            }
        }
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
<<<<<<< HEAD
							"__marker__.mark(); //lineNumber=107\n");
		} catch (IOException ioexception) {
>>>>>>> como te cabe mi picadura
		}
		__marker__.mark();
try {
			FileUtils
					.appendToFile(
<<<<<<< HEAD
							"/Users/concoMB/pf/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
<<<<<<< HEAD
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
=======
							"//mutID 0\nreturn !result; //mutGenLimit 1 //lineNumber=108\n");
>>>>>>> lula map inverse
=======
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"return result; //lineNumber=109\n");
>>>>>>> working loop =)
=======
							"return result; //lineNumber=86\n");
>>>>>>> falta unroll
=======
							"return result; //lineNumber=340\n");
>>>>>>> loop unroll, on the floor
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
        {
            boolean terminatesInTime = false;
            if ( result == null && current != null ) {
                if ( index == current_index ) {
                    result = current;
                } else {
                }
                current_index = current_index + 1;
                current = current.next;
            } else {
            }
            if ( result == null && current != null ) {
                if ( index == current_index ) {
                    result = current;
                } else {
                }
                current_index = current_index + 1;
                current = current.next;
            } else {
            }
            if ( result == null && current != null ) {
                if ( index == current_index ) {
                    result = current;
                } else {
                }
                current_index = current_index + 1;
                current = current.next;
            } else {
            }
            if ( result == null && current != null ) {
                if ( index == current_index ) {
                    result = current;
                } else {
                }
                current_index = current_index + 1;
                current = current.next;
            } else {
            }
            if ( result == null && current != null ) {
                if ( index == current_index ) {
                    result = current;
                } else {
                }
                current_index = current_index + 1;
                current = current.next;
            } else {
            }
            if ( result == null && current != null ) {
                if ( index == current_index ) {
                    result = current;
                } else {
                }
                current_index = current_index + 1;
                current = current.next;
            } else {
            }
            if ( result == null && current != null ) {
                if ( index == current_index ) {
                    result = current;
                } else {
                }
                current_index = current_index + 1;
                current = current.next;
            } else {
            }
            if ( result == null && current != null ) {
                if ( index == current_index ) {
                    result = current;
                } else {
                }
                current_index = current_index + 1;
                current = current.next;
            } else {
            }
            if ( result == null && current != null ) {
                terminatesInTime = true;
            } else {
            }
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
            {
                boolean terminatesInTime = false;
                if ( current.next != null ) {
                    current = current.next;
                } else {
                }
                if ( current.next != null ) {
                    current = current.next;
                } else {
                }
                if ( current.next != null ) {
                    current = current.next;
                } else {
                }
                if ( current.next != null ) {
                    current = current.next;
                } else {
                }
                if ( current.next != null ) {
                    current = current.next;
                } else {
                }
                if ( current.next != null ) {
                    current = current.next;
                } else {
                }
                if ( current.next != null ) {
                    current = current.next;
                } else {
                }
                if ( current.next != null ) {
                    current = current.next;
                } else {
                }
                if ( current.next != null ) {
                    terminatesInTime = true;
                } else {
                }
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

    public static boolean roops_goal_34;

    public static boolean roops_goal_35;

    public static boolean roops_goal_36;

    public static boolean roops_goal_37;

    public static boolean roops_goal_38;

    public static boolean roops_goal_39;

    public static boolean roops_goal_40;

    public static boolean roops_goal_41;

    public static boolean roops_goal_42;

    public static boolean roops_goal_43;

    public static boolean roops_goal_44;

    public static boolean roops_goal_45;

    public static boolean roops_goal_46;

    public static boolean roops_goal_47;

    public static boolean roops_goal_48;

    public static boolean roops_goal_49;

    public static boolean roops_goal_50;

    public static boolean roops_goal_51;

    public static boolean roops_goal_52;

    public static boolean roops_goal_53;

    public static boolean roops_goal_54;

    public static boolean roops_goal_55;

    public static boolean roops_goal_56;

    public static boolean roops_goal_57;

    public static boolean roops_goal_58;

    public static boolean roops_goal_59;

    public static boolean roops_goal_60;

    public static boolean roops_goal_61;

    public static boolean roops_goal_62;

    public static boolean roops_goal_63;

    public static boolean roops_goal_64;

    public static boolean roops_goal_65;

    public static boolean roops_goal_66;

    public static boolean roops_goal_67;

    public static boolean roops_goal_68;

    public static boolean roops_goal_69;

    public static boolean roops_goal_70;

    public static boolean roops_goal_71;

    public static boolean roops_goal_72;

    public static boolean roops_goal_73;

    public static boolean roops_goal_74;

    public static boolean roops_goal_75;

    public static boolean roops_goal_76;

    public static boolean roops_goal_77;

    public static boolean roops_goal_78;

    public static boolean roops_goal_79;

    public static boolean roops_goal_80;

    public static boolean roops_goal_81;

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
        roops_goal_34 = false;
        roops_goal_35 = false;
        roops_goal_36 = false;
        roops_goal_37 = false;
        roops_goal_38 = false;
        roops_goal_39 = false;
        roops_goal_40 = false;
        roops_goal_41 = false;
        roops_goal_42 = false;
        roops_goal_43 = false;
        roops_goal_44 = false;
        roops_goal_45 = false;
        roops_goal_46 = false;
        roops_goal_47 = false;
        roops_goal_48 = false;
        roops_goal_49 = false;
        roops_goal_50 = false;
        roops_goal_51 = false;
        roops_goal_52 = false;
        roops_goal_53 = false;
        roops_goal_54 = false;
        roops_goal_55 = false;
        roops_goal_56 = false;
        roops_goal_57 = false;
        roops_goal_58 = false;
        roops_goal_59 = false;
        roops_goal_60 = false;
        roops_goal_61 = false;
        roops_goal_62 = false;
        roops_goal_63 = false;
        roops_goal_64 = false;
        roops_goal_65 = false;
        roops_goal_66 = false;
        roops_goal_67 = false;
        roops_goal_68 = false;
        roops_goal_69 = false;
        roops_goal_70 = false;
        roops_goal_71 = false;
        roops_goal_72 = false;
        roops_goal_73 = false;
        roops_goal_74 = false;
        roops_goal_75 = false;
        roops_goal_76 = false;
        roops_goal_77 = false;
        roops_goal_78 = false;
        roops_goal_79 = false;
        roops_goal_80 = false;
        roops_goal_81 = false;
    }
>>>>>>> como te cabe mi picadura
}
