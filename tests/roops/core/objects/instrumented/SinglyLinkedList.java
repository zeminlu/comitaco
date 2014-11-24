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
        @ ensures (\exists SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.value==value_param)
        @     <==> (\result==true);
        @ signals (RuntimeException e) false;
        @*/
    public boolean contains (  /*@nullable@*/ java.lang.Object value_param) {
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
							"BugLineMarker dummy; //lineNumber=38\n");
		} catch (IOException ioexception) {
		}
		BugLineMarker dummy;
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"roops.core.objects.SinglyLinkedListNode current; //lineNumber=39\n");
		} catch (IOException ioexception) {
		}
		roops.core.objects.SinglyLinkedListNode current;
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"boolean result; //lineNumber=40\n");
		} catch (IOException ioexception) {
		}
		boolean result;
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"current=this.header; //lineNumber=42\n");
		} catch (IOException ioexception) {
		}
		current = this.header;
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"result=false; //lineNumber=44\n");
		} catch (IOException ioexception) {
		}
		result = false;
        {
            try {
				FileUtils
						.appendToFile(
								"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
								"boolean terminatesInTime=false; //lineNumber=46\n");
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
									"roops_goal_0=true; //lineNumber=48\n");
				} catch (IOException ioexception) {
				}
				roops_goal_0 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"boolean equalVal; //lineNumber=49\n");
				} catch (IOException ioexception) {
				}
				boolean equalVal;
                if ( value_param == null && current.value == null ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(!(value_param == null && current.value == null)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_2=true; //lineNumber=51\n");
					} catch (IOException ioexception) {
					}
					roops_goal_2 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"//mutID 0\nequalVal=false; //mutGenLimit 1 //lineNumber=52\n");
					} catch (IOException ioexception) {
					}
					equalVal = false;
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(value_param == null && current.value == null){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_3=true; //lineNumber=55\n");
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
											"roops_goal_4=true; //lineNumber=57\n");
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
												"roops_goal_6=true; //lineNumber=59\n");
							} catch (IOException ioexception) {
							}
							roops_goal_6 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=true; //lineNumber=60\n");
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
												"roops_goal_7=true; //lineNumber=62\n");
							} catch (IOException ioexception) {
							}
							roops_goal_7 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=false; //lineNumber=63\n");
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
											"roops_goal_5=true; //lineNumber=66\n");
						} catch (IOException ioexception) {
						}
						roops_goal_5 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"equalVal=false; //lineNumber=67\n");
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
										"roops_goal_8=true; //lineNumber=71\n");
					} catch (IOException ioexception) {
					}
					roops_goal_8 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"result=true; //lineNumber=72\n");
					} catch (IOException ioexception) {
					}
					result = true;
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
										"roops_goal_9=true; //lineNumber=74\n");
					} catch (IOException ioexception) {
					}
					roops_goal_9 = true;
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"current=current.next; //lineNumber=77\n");
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
									"roops_goal_1=true; //lineNumber=80\n");
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
									"roops_goal_10=true; //lineNumber=83\n");
				} catch (IOException ioexception) {
				}
				roops_goal_10 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"boolean equalVal; //lineNumber=84\n");
				} catch (IOException ioexception) {
				}
				boolean equalVal;
                if ( value_param == null && current.value == null ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(!(value_param == null && current.value == null)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_12=true; //lineNumber=86\n");
					} catch (IOException ioexception) {
					}
					roops_goal_12 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"//mutID 1\nequalVal=false; //mutGenLimit 1 //lineNumber=87\n");
					} catch (IOException ioexception) {
					}
					equalVal = false;
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(value_param == null && current.value == null){throw new RuntimeException();}\n");
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
										"result=true; //lineNumber=107\n");
					} catch (IOException ioexception) {
					}
					result = true;
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
                if ( value_param == null && current.value == null ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(!(value_param == null && current.value == null)){throw new RuntimeException();}\n");
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
										"//mutID 2\nequalVal=false; //mutGenLimit 1 //lineNumber=122\n");
					} catch (IOException ioexception) {
					}
					equalVal = false;
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(value_param == null && current.value == null){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_23=true; //lineNumber=125\n");
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
											"roops_goal_24=true; //lineNumber=127\n");
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
												"roops_goal_26=true; //lineNumber=129\n");
							} catch (IOException ioexception) {
							}
							roops_goal_26 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=true; //lineNumber=130\n");
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
												"roops_goal_27=true; //lineNumber=132\n");
							} catch (IOException ioexception) {
							}
							roops_goal_27 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=false; //lineNumber=133\n");
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
											"roops_goal_25=true; //lineNumber=136\n");
						} catch (IOException ioexception) {
						}
						roops_goal_25 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"equalVal=false; //lineNumber=137\n");
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
										"roops_goal_28=true; //lineNumber=141\n");
					} catch (IOException ioexception) {
					}
					roops_goal_28 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"result=true; //lineNumber=142\n");
					} catch (IOException ioexception) {
					}
					result = true;
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
										"roops_goal_29=true; //lineNumber=144\n");
					} catch (IOException ioexception) {
					}
					roops_goal_29 = true;
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"current=current.next; //lineNumber=147\n");
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
									"roops_goal_21=true; //lineNumber=150\n");
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
									"roops_goal_30=true; //lineNumber=153\n");
				} catch (IOException ioexception) {
				}
				roops_goal_30 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"boolean equalVal; //lineNumber=154\n");
				} catch (IOException ioexception) {
				}
				boolean equalVal;
                if ( value_param == null && current.value == null ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(!(value_param == null && current.value == null)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_32=true; //lineNumber=156\n");
					} catch (IOException ioexception) {
					}
					roops_goal_32 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"//mutID 3\nequalVal=false; //mutGenLimit 1 //lineNumber=157\n");
					} catch (IOException ioexception) {
					}
					equalVal = false;
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(value_param == null && current.value == null){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_33=true; //lineNumber=160\n");
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
											"roops_goal_34=true; //lineNumber=162\n");
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
												"roops_goal_36=true; //lineNumber=164\n");
							} catch (IOException ioexception) {
							}
							roops_goal_36 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=true; //lineNumber=165\n");
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
												"roops_goal_37=true; //lineNumber=167\n");
							} catch (IOException ioexception) {
							}
							roops_goal_37 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=false; //lineNumber=168\n");
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
											"roops_goal_35=true; //lineNumber=171\n");
						} catch (IOException ioexception) {
						}
						roops_goal_35 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"equalVal=false; //lineNumber=172\n");
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
										"roops_goal_38=true; //lineNumber=176\n");
					} catch (IOException ioexception) {
					}
					roops_goal_38 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"result=true; //lineNumber=177\n");
					} catch (IOException ioexception) {
					}
					result = true;
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
										"roops_goal_39=true; //lineNumber=179\n");
					} catch (IOException ioexception) {
					}
					roops_goal_39 = true;
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"current=current.next; //lineNumber=182\n");
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
									"roops_goal_31=true; //lineNumber=185\n");
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
									"roops_goal_40=true; //lineNumber=188\n");
				} catch (IOException ioexception) {
				}
				roops_goal_40 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"boolean equalVal; //lineNumber=189\n");
				} catch (IOException ioexception) {
				}
				boolean equalVal;
                if ( value_param == null && current.value == null ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(!(value_param == null && current.value == null)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_42=true; //lineNumber=191\n");
					} catch (IOException ioexception) {
					}
					roops_goal_42 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"//mutID 4\nequalVal=false; //mutGenLimit 1 //lineNumber=192\n");
					} catch (IOException ioexception) {
					}
					equalVal = false;
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(value_param == null && current.value == null){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_43=true; //lineNumber=195\n");
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
											"roops_goal_44=true; //lineNumber=197\n");
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
												"roops_goal_46=true; //lineNumber=199\n");
							} catch (IOException ioexception) {
							}
							roops_goal_46 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=true; //lineNumber=200\n");
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
												"roops_goal_47=true; //lineNumber=202\n");
							} catch (IOException ioexception) {
							}
							roops_goal_47 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=false; //lineNumber=203\n");
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
											"roops_goal_45=true; //lineNumber=206\n");
						} catch (IOException ioexception) {
						}
						roops_goal_45 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"equalVal=false; //lineNumber=207\n");
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
										"roops_goal_48=true; //lineNumber=211\n");
					} catch (IOException ioexception) {
					}
					roops_goal_48 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"result=true; //lineNumber=212\n");
					} catch (IOException ioexception) {
					}
					result = true;
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
										"roops_goal_49=true; //lineNumber=214\n");
					} catch (IOException ioexception) {
					}
					roops_goal_49 = true;
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"current=current.next; //lineNumber=217\n");
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
									"roops_goal_41=true; //lineNumber=220\n");
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
									"roops_goal_50=true; //lineNumber=223\n");
				} catch (IOException ioexception) {
				}
				roops_goal_50 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"boolean equalVal; //lineNumber=224\n");
				} catch (IOException ioexception) {
				}
				boolean equalVal;
                if ( value_param == null && current.value == null ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(!(value_param == null && current.value == null)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_52=true; //lineNumber=226\n");
					} catch (IOException ioexception) {
					}
					roops_goal_52 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"//mutID 5\nequalVal=false; //mutGenLimit 1 //lineNumber=227\n");
					} catch (IOException ioexception) {
					}
					equalVal = false;
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(value_param == null && current.value == null){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_53=true; //lineNumber=230\n");
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
											"roops_goal_54=true; //lineNumber=232\n");
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
												"roops_goal_56=true; //lineNumber=234\n");
							} catch (IOException ioexception) {
							}
							roops_goal_56 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=true; //lineNumber=235\n");
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
												"roops_goal_57=true; //lineNumber=237\n");
							} catch (IOException ioexception) {
							}
							roops_goal_57 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=false; //lineNumber=238\n");
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
											"roops_goal_55=true; //lineNumber=241\n");
						} catch (IOException ioexception) {
						}
						roops_goal_55 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"equalVal=false; //lineNumber=242\n");
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
										"roops_goal_58=true; //lineNumber=246\n");
					} catch (IOException ioexception) {
					}
					roops_goal_58 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"result=true; //lineNumber=247\n");
					} catch (IOException ioexception) {
					}
					result = true;
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
										"roops_goal_59=true; //lineNumber=249\n");
					} catch (IOException ioexception) {
					}
					roops_goal_59 = true;
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"current=current.next; //lineNumber=252\n");
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
									"roops_goal_51=true; //lineNumber=255\n");
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
									"roops_goal_60=true; //lineNumber=258\n");
				} catch (IOException ioexception) {
				}
				roops_goal_60 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"boolean equalVal; //lineNumber=259\n");
				} catch (IOException ioexception) {
				}
				boolean equalVal;
                if ( value_param == null && current.value == null ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(!(value_param == null && current.value == null)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_62=true; //lineNumber=261\n");
					} catch (IOException ioexception) {
					}
					roops_goal_62 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"//mutID 6\nequalVal=false; //mutGenLimit 1 //lineNumber=262\n");
					} catch (IOException ioexception) {
					}
					equalVal = false;
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(value_param == null && current.value == null){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_63=true; //lineNumber=265\n");
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
											"roops_goal_64=true; //lineNumber=267\n");
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
												"roops_goal_66=true; //lineNumber=269\n");
							} catch (IOException ioexception) {
							}
							roops_goal_66 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=true; //lineNumber=270\n");
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
												"roops_goal_67=true; //lineNumber=272\n");
							} catch (IOException ioexception) {
							}
							roops_goal_67 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=false; //lineNumber=273\n");
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
											"roops_goal_65=true; //lineNumber=276\n");
						} catch (IOException ioexception) {
						}
						roops_goal_65 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"equalVal=false; //lineNumber=277\n");
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
										"roops_goal_68=true; //lineNumber=281\n");
					} catch (IOException ioexception) {
					}
					roops_goal_68 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"result=true; //lineNumber=282\n");
					} catch (IOException ioexception) {
					}
					result = true;
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
										"roops_goal_69=true; //lineNumber=284\n");
					} catch (IOException ioexception) {
					}
					roops_goal_69 = true;
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"current=current.next; //lineNumber=287\n");
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
									"roops_goal_61=true; //lineNumber=290\n");
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
									"roops_goal_70=true; //lineNumber=293\n");
				} catch (IOException ioexception) {
				}
				roops_goal_70 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"boolean equalVal; //lineNumber=294\n");
				} catch (IOException ioexception) {
				}
				boolean equalVal;
                if ( value_param == null && current.value == null ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(!(value_param == null && current.value == null)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_72=true; //lineNumber=296\n");
					} catch (IOException ioexception) {
					}
					roops_goal_72 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"//mutID 7\nequalVal=false; //mutGenLimit 1 //lineNumber=297\n");
					} catch (IOException ioexception) {
					}
					equalVal = false;
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"if(value_param == null && current.value == null){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"roops_goal_73=true; //lineNumber=300\n");
					} catch (IOException ioexception) {
					}
					roops_goal_73 = true;
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
											"roops_goal_74=true; //lineNumber=302\n");
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
												"roops_goal_76=true; //lineNumber=304\n");
							} catch (IOException ioexception) {
							}
							roops_goal_76 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=true; //lineNumber=305\n");
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
												"roops_goal_77=true; //lineNumber=307\n");
							} catch (IOException ioexception) {
							}
							roops_goal_77 = true;
                            try {
								FileUtils
										.appendToFile(
												"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
												"equalVal=false; //lineNumber=308\n");
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
											"roops_goal_75=true; //lineNumber=311\n");
						} catch (IOException ioexception) {
						}
						roops_goal_75 = true;
                        try {
							FileUtils
									.appendToFile(
											"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
											"equalVal=false; //lineNumber=312\n");
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
										"roops_goal_78=true; //lineNumber=316\n");
					} catch (IOException ioexception) {
					}
					roops_goal_78 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
										"result=true; //lineNumber=317\n");
					} catch (IOException ioexception) {
					}
					result = true;
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
										"roops_goal_79=true; //lineNumber=319\n");
					} catch (IOException ioexception) {
					}
					roops_goal_79 = true;
                }
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"current=current.next; //lineNumber=322\n");
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
									"roops_goal_71=true; //lineNumber=325\n");
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
									"roops_goal_80=true; //lineNumber=328\n");
				} catch (IOException ioexception) {
				}
				roops_goal_80 = true;
                try {
					FileUtils
							.appendToFile(
									"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
									"terminatesInTime=true; //lineNumber=329\n");
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
									"roops_goal_81=true; //lineNumber=331\n");
				} catch (IOException ioexception) {
				}
				roops_goal_81 = true;
            }
        }
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/SinglyLinkedList.java_contains",
							"return result; //lineNumber=334\n");
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
