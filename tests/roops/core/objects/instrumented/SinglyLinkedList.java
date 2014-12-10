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
							"roops.core.objects.SinglyLinkedListNode current; //lineNumber=40\n");
		} catch (IOException ioexception) {
		}
		roops.core.objects.SinglyLinkedListNode current;
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
								"boolean terminatesInTime=false; //lineNumber=49\n");
			} catch (IOException ioexception) {
			}
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
									"roops_goal_0=true; //lineNumber=51\n");
				} catch (IOException ioexception) {
				}
				roops_goal_0 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(42); //lineNumber=52\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(42);
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"boolean equalVal; //lineNumber=53\n");
				} catch (IOException ioexception) {
				}
				boolean equalVal;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(43); //lineNumber=54\n");
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
										"roops_goal_2=true; //lineNumber=56\n");
					} catch (IOException ioexception) {
					}
					roops_goal_2 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(44); //lineNumber=57\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(44);
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"equalVal=true; //lineNumber=58\n");
					} catch (IOException ioexception) {
					}
					equalVal = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(45); //lineNumber=59\n");
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
										"roops_goal_3=true; //lineNumber=61\n");
					} catch (IOException ioexception) {
					}
					roops_goal_3 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(46); //lineNumber=62\n");
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
											"roops_goal_4=true; //lineNumber=64\n");
						} catch (IOException ioexception) {
						}
						roops_goal_4 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(47); //lineNumber=65\n");
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
												"roops_goal_6=true; //lineNumber=67\n");
							} catch (IOException ioexception) {
							}
							roops_goal_6 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(48); //lineNumber=68\n");
							} catch (IOException ioexception) {
							}
							__marker__.mark(48);
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=true; //lineNumber=69\n");
							} catch (IOException ioexception) {
							}
							equalVal = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(49); //lineNumber=70\n");
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
												"roops_goal_7=true; //lineNumber=72\n");
							} catch (IOException ioexception) {
							}
							roops_goal_7 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(50); //lineNumber=73\n");
							} catch (IOException ioexception) {
							}
							__marker__.mark(50);
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=true; //lineNumber=74\n");
							} catch (IOException ioexception) {
							}
							equalVal = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(51); //lineNumber=75\n");
							} catch (IOException ioexception) {
							}
							__marker__.mark(51);
                        }
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(52); //lineNumber=77\n");
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
											"roops_goal_5=true; //lineNumber=79\n");
						} catch (IOException ioexception) {
						}
						roops_goal_5 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(53); //lineNumber=80\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark(53);
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"equalVal=false; //lineNumber=81\n");
						} catch (IOException ioexception) {
						}
						equalVal = false;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(54); //lineNumber=82\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark(54);
                    }
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(55); //lineNumber=84\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(55);
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(56); //lineNumber=86\n");
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
										"roops_goal_8=true; //lineNumber=88\n");
					} catch (IOException ioexception) {
					}
					roops_goal_8 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(57); //lineNumber=89\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(57);
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"result=true; //lineNumber=90\n");
					} catch (IOException ioexception) {
					}
					result = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(58); //lineNumber=91\n");
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
										"roops_goal_9=true; //lineNumber=93\n");
					} catch (IOException ioexception) {
					}
					roops_goal_9 = true;
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(59); //lineNumber=95\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(59);
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"current=current.next; //lineNumber=96\n");
				} catch (IOException ioexception) {
				}
				current = current.next;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(60); //lineNumber=97\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(60);
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
									"roops_goal_1=true; //lineNumber=99\n");
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
									"roops_goal_10=true; //lineNumber=102\n");
				} catch (IOException ioexception) {
				}
				roops_goal_10 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(42); //lineNumber=103\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(42);
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"boolean equalVal; //lineNumber=104\n");
				} catch (IOException ioexception) {
				}
				boolean equalVal;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(43); //lineNumber=105\n");
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
										"roops_goal_12=true; //lineNumber=107\n");
					} catch (IOException ioexception) {
					}
					roops_goal_12 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(44); //lineNumber=108\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(44);
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"equalVal=true; //lineNumber=109\n");
					} catch (IOException ioexception) {
					}
					equalVal = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(45); //lineNumber=110\n");
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
										"roops_goal_13=true; //lineNumber=112\n");
					} catch (IOException ioexception) {
					}
					roops_goal_13 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(46); //lineNumber=113\n");
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
											"roops_goal_14=true; //lineNumber=115\n");
						} catch (IOException ioexception) {
						}
						roops_goal_14 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(47); //lineNumber=116\n");
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
												"roops_goal_16=true; //lineNumber=118\n");
							} catch (IOException ioexception) {
							}
							roops_goal_16 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(48); //lineNumber=119\n");
							} catch (IOException ioexception) {
							}
							__marker__.mark(48);
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=true; //lineNumber=120\n");
							} catch (IOException ioexception) {
							}
							equalVal = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(49); //lineNumber=121\n");
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
												"roops_goal_17=true; //lineNumber=123\n");
							} catch (IOException ioexception) {
							}
							roops_goal_17 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(50); //lineNumber=124\n");
							} catch (IOException ioexception) {
							}
							__marker__.mark(50);
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=true; //lineNumber=125\n");
							} catch (IOException ioexception) {
							}
							equalVal = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(51); //lineNumber=126\n");
							} catch (IOException ioexception) {
							}
							__marker__.mark(51);
                        }
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(52); //lineNumber=128\n");
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
											"roops_goal_15=true; //lineNumber=130\n");
						} catch (IOException ioexception) {
						}
						roops_goal_15 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(53); //lineNumber=131\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark(53);
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"equalVal=false; //lineNumber=132\n");
						} catch (IOException ioexception) {
						}
						equalVal = false;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(54); //lineNumber=133\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark(54);
                    }
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(55); //lineNumber=135\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(55);
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(56); //lineNumber=137\n");
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
										"roops_goal_18=true; //lineNumber=139\n");
					} catch (IOException ioexception) {
					}
					roops_goal_18 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(57); //lineNumber=140\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(57);
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"result=true; //lineNumber=141\n");
					} catch (IOException ioexception) {
					}
					result = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(58); //lineNumber=142\n");
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
										"roops_goal_19=true; //lineNumber=144\n");
					} catch (IOException ioexception) {
					}
					roops_goal_19 = true;
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(59); //lineNumber=146\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(59);
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"current=current.next; //lineNumber=147\n");
				} catch (IOException ioexception) {
				}
				current = current.next;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(60); //lineNumber=148\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(60);
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
									"roops_goal_11=true; //lineNumber=150\n");
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
									"roops_goal_20=true; //lineNumber=153\n");
				} catch (IOException ioexception) {
				}
				roops_goal_20 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(42); //lineNumber=154\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(42);
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"boolean equalVal; //lineNumber=155\n");
				} catch (IOException ioexception) {
				}
				boolean equalVal;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(43); //lineNumber=156\n");
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
										"roops_goal_22=true; //lineNumber=158\n");
					} catch (IOException ioexception) {
					}
					roops_goal_22 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(44); //lineNumber=159\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(44);
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"equalVal=true; //lineNumber=160\n");
					} catch (IOException ioexception) {
					}
					equalVal = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(45); //lineNumber=161\n");
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
										"roops_goal_23=true; //lineNumber=163\n");
					} catch (IOException ioexception) {
					}
					roops_goal_23 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(46); //lineNumber=164\n");
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
											"roops_goal_24=true; //lineNumber=166\n");
						} catch (IOException ioexception) {
						}
						roops_goal_24 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(47); //lineNumber=167\n");
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
												"roops_goal_26=true; //lineNumber=169\n");
							} catch (IOException ioexception) {
							}
							roops_goal_26 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(48); //lineNumber=170\n");
							} catch (IOException ioexception) {
							}
							__marker__.mark(48);
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=true; //lineNumber=171\n");
							} catch (IOException ioexception) {
							}
							equalVal = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(49); //lineNumber=172\n");
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
												"roops_goal_27=true; //lineNumber=174\n");
							} catch (IOException ioexception) {
							}
							roops_goal_27 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(50); //lineNumber=175\n");
							} catch (IOException ioexception) {
							}
							__marker__.mark(50);
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=true; //lineNumber=176\n");
							} catch (IOException ioexception) {
							}
							equalVal = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(51); //lineNumber=177\n");
							} catch (IOException ioexception) {
							}
							__marker__.mark(51);
                        }
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(52); //lineNumber=179\n");
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
											"roops_goal_25=true; //lineNumber=181\n");
						} catch (IOException ioexception) {
						}
						roops_goal_25 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(53); //lineNumber=182\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark(53);
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"equalVal=false; //lineNumber=183\n");
						} catch (IOException ioexception) {
						}
						equalVal = false;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(54); //lineNumber=184\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark(54);
                    }
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(55); //lineNumber=186\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(55);
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(56); //lineNumber=188\n");
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
										"roops_goal_28=true; //lineNumber=190\n");
					} catch (IOException ioexception) {
					}
					roops_goal_28 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(57); //lineNumber=191\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(57);
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"result=true; //lineNumber=192\n");
					} catch (IOException ioexception) {
					}
					result = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(58); //lineNumber=193\n");
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
										"roops_goal_29=true; //lineNumber=195\n");
					} catch (IOException ioexception) {
					}
					roops_goal_29 = true;
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(59); //lineNumber=197\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(59);
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"current=current.next; //lineNumber=198\n");
				} catch (IOException ioexception) {
				}
				current = current.next;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(60); //lineNumber=199\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(60);
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
									"roops_goal_21=true; //lineNumber=201\n");
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
									"roops_goal_30=true; //lineNumber=204\n");
				} catch (IOException ioexception) {
				}
				roops_goal_30 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(42); //lineNumber=205\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(42);
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"boolean equalVal; //lineNumber=206\n");
				} catch (IOException ioexception) {
				}
				boolean equalVal;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(43); //lineNumber=207\n");
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
										"roops_goal_32=true; //lineNumber=209\n");
					} catch (IOException ioexception) {
					}
					roops_goal_32 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(44); //lineNumber=210\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(44);
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"equalVal=true; //lineNumber=211\n");
					} catch (IOException ioexception) {
					}
					equalVal = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(45); //lineNumber=212\n");
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
										"roops_goal_33=true; //lineNumber=214\n");
					} catch (IOException ioexception) {
					}
					roops_goal_33 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(46); //lineNumber=215\n");
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
											"roops_goal_34=true; //lineNumber=217\n");
						} catch (IOException ioexception) {
						}
						roops_goal_34 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(47); //lineNumber=218\n");
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
												"roops_goal_36=true; //lineNumber=220\n");
							} catch (IOException ioexception) {
							}
							roops_goal_36 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(48); //lineNumber=221\n");
							} catch (IOException ioexception) {
							}
							__marker__.mark(48);
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=true; //lineNumber=222\n");
							} catch (IOException ioexception) {
							}
							equalVal = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(49); //lineNumber=223\n");
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
												"roops_goal_37=true; //lineNumber=225\n");
							} catch (IOException ioexception) {
							}
							roops_goal_37 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(50); //lineNumber=226\n");
							} catch (IOException ioexception) {
							}
							__marker__.mark(50);
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=true; //lineNumber=227\n");
							} catch (IOException ioexception) {
							}
							equalVal = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(51); //lineNumber=228\n");
							} catch (IOException ioexception) {
							}
							__marker__.mark(51);
                        }
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(52); //lineNumber=230\n");
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
											"roops_goal_35=true; //lineNumber=232\n");
						} catch (IOException ioexception) {
						}
						roops_goal_35 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(53); //lineNumber=233\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark(53);
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"equalVal=false; //lineNumber=234\n");
						} catch (IOException ioexception) {
						}
						equalVal = false;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(54); //lineNumber=235\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark(54);
                    }
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(55); //lineNumber=237\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(55);
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(56); //lineNumber=239\n");
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
										"roops_goal_38=true; //lineNumber=241\n");
					} catch (IOException ioexception) {
					}
					roops_goal_38 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(57); //lineNumber=242\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(57);
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"result=true; //lineNumber=243\n");
					} catch (IOException ioexception) {
					}
					result = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(58); //lineNumber=244\n");
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
										"roops_goal_39=true; //lineNumber=246\n");
					} catch (IOException ioexception) {
					}
					roops_goal_39 = true;
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(59); //lineNumber=248\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(59);
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"current=current.next; //lineNumber=249\n");
				} catch (IOException ioexception) {
				}
				current = current.next;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(60); //lineNumber=250\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(60);
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
									"roops_goal_31=true; //lineNumber=252\n");
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
									"roops_goal_40=true; //lineNumber=255\n");
				} catch (IOException ioexception) {
				}
				roops_goal_40 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(42); //lineNumber=256\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(42);
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"boolean equalVal; //lineNumber=257\n");
				} catch (IOException ioexception) {
				}
				boolean equalVal;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(43); //lineNumber=258\n");
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
										"roops_goal_42=true; //lineNumber=260\n");
					} catch (IOException ioexception) {
					}
					roops_goal_42 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(44); //lineNumber=261\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(44);
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"equalVal=true; //lineNumber=262\n");
					} catch (IOException ioexception) {
					}
					equalVal = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(45); //lineNumber=263\n");
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
										"roops_goal_43=true; //lineNumber=265\n");
					} catch (IOException ioexception) {
					}
					roops_goal_43 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(46); //lineNumber=266\n");
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
											"roops_goal_44=true; //lineNumber=268\n");
						} catch (IOException ioexception) {
						}
						roops_goal_44 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(47); //lineNumber=269\n");
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
												"roops_goal_46=true; //lineNumber=271\n");
							} catch (IOException ioexception) {
							}
							roops_goal_46 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(48); //lineNumber=272\n");
							} catch (IOException ioexception) {
							}
							__marker__.mark(48);
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=true; //lineNumber=273\n");
							} catch (IOException ioexception) {
							}
							equalVal = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(49); //lineNumber=274\n");
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
												"roops_goal_47=true; //lineNumber=276\n");
							} catch (IOException ioexception) {
							}
							roops_goal_47 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(50); //lineNumber=277\n");
							} catch (IOException ioexception) {
							}
							__marker__.mark(50);
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=true; //lineNumber=278\n");
							} catch (IOException ioexception) {
							}
							equalVal = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(51); //lineNumber=279\n");
							} catch (IOException ioexception) {
							}
							__marker__.mark(51);
                        }
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(52); //lineNumber=281\n");
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
											"roops_goal_45=true; //lineNumber=283\n");
						} catch (IOException ioexception) {
						}
						roops_goal_45 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(53); //lineNumber=284\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark(53);
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"equalVal=false; //lineNumber=285\n");
						} catch (IOException ioexception) {
						}
						equalVal = false;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(54); //lineNumber=286\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark(54);
                    }
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(55); //lineNumber=288\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(55);
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(56); //lineNumber=290\n");
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
										"roops_goal_48=true; //lineNumber=292\n");
					} catch (IOException ioexception) {
					}
					roops_goal_48 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(57); //lineNumber=293\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(57);
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"result=true; //lineNumber=294\n");
					} catch (IOException ioexception) {
					}
					result = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(58); //lineNumber=295\n");
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
										"roops_goal_49=true; //lineNumber=297\n");
					} catch (IOException ioexception) {
					}
					roops_goal_49 = true;
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(59); //lineNumber=299\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(59);
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"current=current.next; //lineNumber=300\n");
				} catch (IOException ioexception) {
				}
				current = current.next;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(60); //lineNumber=301\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(60);
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
									"roops_goal_41=true; //lineNumber=303\n");
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
									"roops_goal_50=true; //lineNumber=306\n");
				} catch (IOException ioexception) {
				}
				roops_goal_50 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(42); //lineNumber=307\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(42);
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"boolean equalVal; //lineNumber=308\n");
				} catch (IOException ioexception) {
				}
				boolean equalVal;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(43); //lineNumber=309\n");
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
										"roops_goal_52=true; //lineNumber=311\n");
					} catch (IOException ioexception) {
					}
					roops_goal_52 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(44); //lineNumber=312\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(44);
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"equalVal=true; //lineNumber=313\n");
					} catch (IOException ioexception) {
					}
					equalVal = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(45); //lineNumber=314\n");
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
										"roops_goal_53=true; //lineNumber=316\n");
					} catch (IOException ioexception) {
					}
					roops_goal_53 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(46); //lineNumber=317\n");
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
											"roops_goal_54=true; //lineNumber=319\n");
						} catch (IOException ioexception) {
						}
						roops_goal_54 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(47); //lineNumber=320\n");
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
												"roops_goal_56=true; //lineNumber=322\n");
							} catch (IOException ioexception) {
							}
							roops_goal_56 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(48); //lineNumber=323\n");
							} catch (IOException ioexception) {
							}
							__marker__.mark(48);
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=true; //lineNumber=324\n");
							} catch (IOException ioexception) {
							}
							equalVal = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(49); //lineNumber=325\n");
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
												"roops_goal_57=true; //lineNumber=327\n");
							} catch (IOException ioexception) {
							}
							roops_goal_57 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(50); //lineNumber=328\n");
							} catch (IOException ioexception) {
							}
							__marker__.mark(50);
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=true; //lineNumber=329\n");
							} catch (IOException ioexception) {
							}
							equalVal = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(51); //lineNumber=330\n");
							} catch (IOException ioexception) {
							}
							__marker__.mark(51);
                        }
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(52); //lineNumber=332\n");
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
											"roops_goal_55=true; //lineNumber=334\n");
						} catch (IOException ioexception) {
						}
						roops_goal_55 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(53); //lineNumber=335\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark(53);
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"equalVal=false; //lineNumber=336\n");
						} catch (IOException ioexception) {
						}
						equalVal = false;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(54); //lineNumber=337\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark(54);
                    }
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(55); //lineNumber=339\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(55);
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(56); //lineNumber=341\n");
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
										"roops_goal_58=true; //lineNumber=343\n");
					} catch (IOException ioexception) {
					}
					roops_goal_58 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(57); //lineNumber=344\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(57);
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"result=true; //lineNumber=345\n");
					} catch (IOException ioexception) {
					}
					result = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(58); //lineNumber=346\n");
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
										"roops_goal_59=true; //lineNumber=348\n");
					} catch (IOException ioexception) {
					}
					roops_goal_59 = true;
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(59); //lineNumber=350\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(59);
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"current=current.next; //lineNumber=351\n");
				} catch (IOException ioexception) {
				}
				current = current.next;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(60); //lineNumber=352\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(60);
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
									"roops_goal_51=true; //lineNumber=354\n");
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
									"roops_goal_60=true; //lineNumber=357\n");
				} catch (IOException ioexception) {
				}
				roops_goal_60 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(42); //lineNumber=358\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(42);
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"boolean equalVal; //lineNumber=359\n");
				} catch (IOException ioexception) {
				}
				boolean equalVal;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(43); //lineNumber=360\n");
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
										"roops_goal_62=true; //lineNumber=362\n");
					} catch (IOException ioexception) {
					}
					roops_goal_62 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(44); //lineNumber=363\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(44);
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"equalVal=true; //lineNumber=364\n");
					} catch (IOException ioexception) {
					}
					equalVal = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(45); //lineNumber=365\n");
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
										"roops_goal_63=true; //lineNumber=367\n");
					} catch (IOException ioexception) {
					}
					roops_goal_63 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(46); //lineNumber=368\n");
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
											"roops_goal_64=true; //lineNumber=370\n");
						} catch (IOException ioexception) {
						}
						roops_goal_64 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(47); //lineNumber=371\n");
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
												"roops_goal_66=true; //lineNumber=373\n");
							} catch (IOException ioexception) {
							}
							roops_goal_66 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(48); //lineNumber=374\n");
							} catch (IOException ioexception) {
							}
							__marker__.mark(48);
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=true; //lineNumber=375\n");
							} catch (IOException ioexception) {
							}
							equalVal = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(49); //lineNumber=376\n");
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
												"roops_goal_67=true; //lineNumber=378\n");
							} catch (IOException ioexception) {
							}
							roops_goal_67 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(50); //lineNumber=379\n");
							} catch (IOException ioexception) {
							}
							__marker__.mark(50);
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=true; //lineNumber=380\n");
							} catch (IOException ioexception) {
							}
							equalVal = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(51); //lineNumber=381\n");
							} catch (IOException ioexception) {
							}
							__marker__.mark(51);
                        }
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(52); //lineNumber=383\n");
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
											"roops_goal_65=true; //lineNumber=385\n");
						} catch (IOException ioexception) {
						}
						roops_goal_65 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(53); //lineNumber=386\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark(53);
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"equalVal=false; //lineNumber=387\n");
						} catch (IOException ioexception) {
						}
						equalVal = false;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(54); //lineNumber=388\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark(54);
                    }
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(55); //lineNumber=390\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(55);
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(56); //lineNumber=392\n");
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
										"roops_goal_68=true; //lineNumber=394\n");
					} catch (IOException ioexception) {
					}
					roops_goal_68 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(57); //lineNumber=395\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(57);
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"result=true; //lineNumber=396\n");
					} catch (IOException ioexception) {
					}
					result = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(58); //lineNumber=397\n");
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
										"roops_goal_69=true; //lineNumber=399\n");
					} catch (IOException ioexception) {
					}
					roops_goal_69 = true;
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(59); //lineNumber=401\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(59);
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"current=current.next; //lineNumber=402\n");
				} catch (IOException ioexception) {
				}
				current = current.next;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(60); //lineNumber=403\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(60);
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
									"roops_goal_61=true; //lineNumber=405\n");
				} catch (IOException ioexception) {
				}
				roops_goal_61 = true;
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
									"roops_goal_70=true; //lineNumber=408\n");
				} catch (IOException ioexception) {
				}
				roops_goal_70 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(42); //lineNumber=409\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(42);
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"boolean equalVal; //lineNumber=410\n");
				} catch (IOException ioexception) {
				}
				boolean equalVal;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(43); //lineNumber=411\n");
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
										"roops_goal_72=true; //lineNumber=413\n");
					} catch (IOException ioexception) {
					}
					roops_goal_72 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(44); //lineNumber=414\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(44);
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"equalVal=true; //lineNumber=415\n");
					} catch (IOException ioexception) {
					}
					equalVal = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(45); //lineNumber=416\n");
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
										"roops_goal_73=true; //lineNumber=418\n");
					} catch (IOException ioexception) {
					}
					roops_goal_73 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(46); //lineNumber=419\n");
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
											"roops_goal_74=true; //lineNumber=421\n");
						} catch (IOException ioexception) {
						}
						roops_goal_74 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(47); //lineNumber=422\n");
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
												"roops_goal_76=true; //lineNumber=424\n");
							} catch (IOException ioexception) {
							}
							roops_goal_76 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(48); //lineNumber=425\n");
							} catch (IOException ioexception) {
							}
							__marker__.mark(48);
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=true; //lineNumber=426\n");
							} catch (IOException ioexception) {
							}
							equalVal = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(49); //lineNumber=427\n");
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
												"roops_goal_77=true; //lineNumber=429\n");
							} catch (IOException ioexception) {
							}
							roops_goal_77 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(50); //lineNumber=430\n");
							} catch (IOException ioexception) {
							}
							__marker__.mark(50);
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=true; //lineNumber=431\n");
							} catch (IOException ioexception) {
							}
							equalVal = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"__marker__.mark(51); //lineNumber=432\n");
							} catch (IOException ioexception) {
							}
							__marker__.mark(51);
                        }
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(52); //lineNumber=434\n");
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
											"roops_goal_75=true; //lineNumber=436\n");
						} catch (IOException ioexception) {
						}
						roops_goal_75 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(53); //lineNumber=437\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark(53);
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"equalVal=false; //lineNumber=438\n");
						} catch (IOException ioexception) {
						}
						equalVal = false;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"__marker__.mark(54); //lineNumber=439\n");
						} catch (IOException ioexception) {
						}
						__marker__.mark(54);
                    }
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(55); //lineNumber=441\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(55);
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(56); //lineNumber=443\n");
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
										"roops_goal_78=true; //lineNumber=445\n");
					} catch (IOException ioexception) {
					}
					roops_goal_78 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(57); //lineNumber=446\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(57);
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"result=true; //lineNumber=447\n");
					} catch (IOException ioexception) {
					}
					result = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"__marker__.mark(58); //lineNumber=448\n");
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
										"roops_goal_79=true; //lineNumber=450\n");
					} catch (IOException ioexception) {
					}
					roops_goal_79 = true;
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(59); //lineNumber=452\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(59);
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"current=current.next; //lineNumber=453\n");
				} catch (IOException ioexception) {
				}
				current = current.next;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"__marker__.mark(60); //lineNumber=454\n");
				} catch (IOException ioexception) {
				}
				__marker__.mark(60);
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
									"roops_goal_71=true; //lineNumber=456\n");
				} catch (IOException ioexception) {
				}
				roops_goal_71 = true;
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
									"roops_goal_80=true; //lineNumber=459\n");
				} catch (IOException ioexception) {
				}
				roops_goal_80 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"terminatesInTime=true; //lineNumber=460\n");
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
									"roops_goal_81=true; //lineNumber=462\n");
				} catch (IOException ioexception) {
				}
				roops_goal_81 = true;
            }
        }
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"__marker__.mark(61); //lineNumber=465\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(61);
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"return result; //lineNumber=466\n");
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
}
