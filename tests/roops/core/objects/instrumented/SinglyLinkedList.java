// This is mutant program.
// Author : ysma

package roops.core.objects;


import roops.core.objects.SinglyLinkedListNode;
import roops.core.objects.BugLineMarker;
import java.io.IOException;
import ar.edu.taco.utils.FileUtils;
import java.util.NoSuchElementException;


/*@ nullable_by_default @*/
public class SinglyLinkedList {

    /*@
        @ invariant (\forall SinglyLinkedListNode n; \reach(header, SinglyLinkedListNode, next).has(n); \reach(n.next, SinglyLinkedListNode, next).has(n)==false);
        @*/
    public SinglyLinkedListNode header;

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

    /*@
        @ ensures (\exists SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.value==valueParam)
        @     <==> (\result==true);
        @ signals (RuntimeException e) false;
        @*/
    public boolean contains (  /*@nullable@*/ java.lang.Object valueParam) {
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"fajita_roopsGoal_initialization();\n");
		} catch (IOException ioexception) {
		}
		fajita_roopsGoal_initialization();
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"BugLineMarker __marker__=new BugLineMarker(); //lineNumber=38\n");
		} catch (IOException ioexception) {
		}
		BugLineMarker __marker__ = new BugLineMarker();
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"__marker__.mark(37); //lineNumber=39\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(37);
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"SinglyLinkedListNode current; //lineNumber=40\n");
		} catch (IOException ioexception) {
		}
		SinglyLinkedListNode current;
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"__marker__.mark(38); //lineNumber=41\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(38);
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"boolean result; //lineNumber=42\n");
		} catch (IOException ioexception) {
		}
		boolean result;
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"__marker__.mark(39); //lineNumber=43\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(39);
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"current=this.header; //lineNumber=44\n");
		} catch (IOException ioexception) {
		}
		current = this.header;
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"__marker__.mark(40); //lineNumber=45\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(40);
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"result=false; //lineNumber=46\n");
		} catch (IOException ioexception) {
		}
		result = false;
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"__marker__.mark(41); //lineNumber=47\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(41);
        {
            try {
				FileUtils
						.appendToFile(
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
								"boolean fajita_cicle_0=false; //lineNumber=49\n");
			} catch (IOException ioexception) {
			}
			boolean fajita_cicle_0 = false;
            while ( result != false && current != null ) {
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"if(!(result != false && current != null)){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"fajita_cicle_0=true; //lineNumber=51\n");
				} catch (IOException ioexception) {
				}
				fajita_cicle_0 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"roops_goal_0=true; //lineNumber=52\n");
				} catch (IOException ioexception) {
				}
				roops_goal_0 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(42); //lineNumber=53\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(42);
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"boolean equalVal; //lineNumber=54\n");
				} catch (IOException ioexception) {
				}
				boolean equalVal;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(43); //lineNumber=55\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(43);
                if ( valueParam == null && current.value == null ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(!(valueParam == null && current.value == null)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_2=true; //lineNumber=57\n");
					} catch (IOException ioexception) {
					}
					roops_goal_2 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(44); //lineNumber=58\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(44);
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"equalVal=true; //lineNumber=59\n");
					} catch (IOException ioexception) {
					}
					equalVal = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(45); //lineNumber=60\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(45);
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(valueParam == null && current.value == null){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_3=true; //lineNumber=62\n");
					} catch (IOException ioexception) {
					}
					roops_goal_3 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(46); //lineNumber=63\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(46);
                    if ( valueParam != null ) {
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"if(!(valueParam != null)){throw new RuntimeException();}\n");
						} catch (IOException ioexception) {
						}
						try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"roops_goal_4=true; //lineNumber=65\n");
						} catch (IOException ioexception) {
						}
						roops_goal_4 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(47); //lineNumber=66\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark(47);
                        if ( valueParam == current.value ) {
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"if(!(valueParam == current.value)){throw new RuntimeException();}\n");
							} catch (IOException ioexception) {
							}
							try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"roops_goal_6=true; //lineNumber=68\n");
							} catch (IOException ioexception) {
							}
							roops_goal_6 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(48); //lineNumber=69\n");
							} catch (IOException ioexception) {
							}
							__marker__.mark(48);
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=true; //lineNumber=70\n");
							} catch (IOException ioexception) {
							}
							equalVal = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(49); //lineNumber=71\n");
							} catch (IOException ioexception) {
							}
							__marker__.mark(49);
                        } else {
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"if(valueParam == current.value){throw new RuntimeException();}\n");
							} catch (IOException ioexception) {
							}
							try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"roops_goal_7=true; //lineNumber=73\n");
							} catch (IOException ioexception) {
							}
							roops_goal_7 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(50); //lineNumber=74\n");
							} catch (IOException ioexception) {
							}
							__marker__.mark(50);
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=false; //lineNumber=75\n");
							} catch (IOException ioexception) {
							}
							equalVal = false;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(51); //lineNumber=76\n");
							} catch (IOException ioexception) {
							}
							__marker__.mark(51);
                        }
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(52); //lineNumber=78\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark(52);
                    } else {
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"if(valueParam != null){throw new RuntimeException();}\n");
						} catch (IOException ioexception) {
						}
						try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"roops_goal_5=true; //lineNumber=80\n");
						} catch (IOException ioexception) {
						}
						roops_goal_5 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(53); //lineNumber=81\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark(53);
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"equalVal=false; //lineNumber=82\n");
						} catch (IOException ioexception) {
						}
						equalVal = false;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(54); //lineNumber=83\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark(54);
                    }
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(55); //lineNumber=85\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(55);
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(56); //lineNumber=87\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(56);
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
										"roops_goal_8=true; //lineNumber=89\n");
					} catch (IOException ioexception) {
					}
					roops_goal_8 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(57); //lineNumber=90\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(57);
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"result=true; //lineNumber=91\n");
					} catch (IOException ioexception) {
					}
					result = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(58); //lineNumber=92\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(58);
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
										"roops_goal_9=true; //lineNumber=94\n");
					} catch (IOException ioexception) {
					}
					roops_goal_9 = true;
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(59); //lineNumber=96\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(59);
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"current=current.next; //lineNumber=97\n");
				} catch (IOException ioexception) {
				}
				current = current.next;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(60); //lineNumber=98\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(60);
            }
			try {
				FileUtils
						.appendToFile(
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
								"if(result != false && current != null){throw new RuntimeException();}\n");
			} catch (IOException ioexception) {
			}
            if ( ! fajita_cicle_0 ) {
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"if(!(!fajita_cicle_0)){throw new RuntimeException();}\n");
				} catch (IOException ioexception) {
				}
				try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"roops_goal_1=true; //lineNumber=101\n");
				} catch (IOException ioexception) {
				}
				roops_goal_1 = true;
            }
        }
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"__marker__.mark(61); //lineNumber=104\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(61);
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"return result; //lineNumber=105\n");
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
    public SinglyLinkedListNode getNode ( int index) {
        SinglyLinkedListNode current = header;
        SinglyLinkedListNode result = null;
        int current_index = 0;
        while ( result == null && current != null ) {
            if ( index == current_index ) {
                result = current;
            } else {
            }
            current_index = current_index;
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
    void insertBack ( java.lang.Object data, SinglyLinkedListNode freshNode) {
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
