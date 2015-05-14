package roops.core.objects;


import roops.core.objects.SinglyLinkedListNode;
import roops.core.objects.BugLineMarker;
import java.io.IOException;
import ar.edu.taco.utils.FileUtils;
import java.util.NoSuchElementException;

public class SinglyLinkedListContainsBug7 {

    /*@
    @ invariant (\forall SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); \reach(n.next, SinglyLinkedListNode, next).has(n)==false);
    @*/
    public /*@nullable@*/roops.core.objects.SinglyLinkedListNode header;

    public SinglyLinkedListContainsBug7 () {
    }

    /*@
    @ requires true;
    @ ensures (\exists SinglyLinkedListNode n; \old(\reach(this.header, SinglyLinkedListNode, next)).has(n); n.value==valueParam) ==> (\result==true);
    @ ensures (\result == true) ==> (\exists SinglyLinkedListNode n; \old(\reach(this.header, SinglyLinkedListNode, next).has(n)); n.value==valueParam);
    @ ensures (\forall SinglyLinkedListNode n; \old(\reach(this.header, SinglyLinkedListNode, next).has(n)); \old(n.value) == n.value);
    @ signals (RuntimeException e) false;
    @
    @*/
    public boolean contains ( /*@nullable@*/java.lang.Object valueParam) {
        SinglyLinkedListNode current;
        boolean result;
        current = this.header;
        result = false;
        {
            boolean terminatesInTime = false;
            if ( result == false && current != null ) {
                boolean equalVal;
                if ( valueParam != null && current.value == null ) { //mutGenLimit 1
                    equalVal = true;
                } else {
                    if ( valueParam != null ) {
                        if ( valueParam == current.value ) {
                            equalVal = true;
                        } else {
                            equalVal = false;
                        }
                    } else {
                        equalVal = false;
                    }
                }
                if ( equalVal == true ) {
                    result = true;
                } else {
                }
                current = current.next;
            } else {
            }
            if ( result == false && current != null ) {
                boolean equalVal;
                if ( valueParam != null && current.value == null ) { //mutGenLimit 1
                    equalVal = true;
                } else {
                    if ( valueParam != null ) {
                        if ( valueParam == current.value ) {
                            equalVal = true;
                        } else {
                            equalVal = false;
                        }
                    } else {
                        equalVal = false;
                    }
                }
                if ( equalVal == true ) {
                    result = true;
                } else {
                }
                current = current.next;
            } else {
            }
            if ( result == false && current != null ) {
                boolean equalVal;
                if ( valueParam != null && current.value == null ) { //mutGenLimit 1
                    equalVal = true;
                } else {
                    if ( valueParam != null ) {
                        if ( valueParam == current.value ) {
                            equalVal = true;
                        } else {
                            equalVal = false;
                        }
                    } else {
                        equalVal = false;
                    }
                }
                if ( equalVal == true ) {
                    result = true;
                } else {
                }
                current = current.next;
            } else {
            }
            if ( result == false && current != null ) {
                boolean equalVal;
                if ( valueParam != null && current.value == null ) { //mutGenLimit 1
                    equalVal = true;
                } else {
                    if ( valueParam != null ) {
                        if ( valueParam == current.value ) {
                            equalVal = true;
                        } else {
                            equalVal = false;
                        }
                    } else {
                        equalVal = false;
                    }
                }
                if ( equalVal == true ) {
                    result = true;
                } else {
                }
                current = current.next;
            } else {
            }
            if ( result == false && current != null ) {
                boolean equalVal;
                if ( valueParam != null && current.value == null ) { //mutGenLimit 1
                    equalVal = true;
                } else {
                    if ( valueParam != null ) {
                        if ( valueParam == current.value ) {
                            equalVal = true;
                        } else {
                            equalVal = false;
                        }
                    } else {
                        equalVal = false;
                    }
                }
                if ( equalVal == true ) {
                    result = true;
                } else {
                }
                current = current.next;
            } else {
            }
            if ( result == false && current != null ) {
                terminatesInTime = true;
            } else {
            }
        }
        return result;
    }

    /*@
    @ requires index>=0 && index<\reach(this.header, SinglyLinkedListNode, next).int_size();
    @
    @ ensures \reach(this.header, SinglyLinkedListNode, next).has(\result)==true;
    @ ensures \reach(\result, SinglyLinkedListNode, next).int_size() == \reach(this.header, SinglyLinkedListNode, next).int_size()-index;
    @ signals (RuntimeException e) false;
    @*/
    public roops.core.objects.SinglyLinkedListNode getNode ( int index) {
        roops.core.objects.SinglyLinkedListNode current = this.header;
        roops.core.objects.SinglyLinkedListNode result = null;
        int current_index = 0;
        {
            boolean terminatesInTime = false;
            if ( result == null && current != null ) { //mutGenLimit 2
                if ( index == current_index ) { //mutGenLimit 2
                    result = current; //mutGenLimit 2
                } else {
                }
                current_index = current_index + 1; //mutGenLimit 2
                current
                    = current.next; //mutGenLimit 2
            } else {
            }
            if ( result == null && current != null ) { //mutGenLimit 2
                if ( index == current_index ) { //mutGenLimit 2
                    result = current; //mutGenLimit 2
                } else {
                }
                current_index = current_index + 1; //mutGenLimit 2
                current
                    = current.next; //mutGenLimit 2
            } else {
            }
            if ( result == null && current != null ) { //mutGenLimit 2
                if ( index == current_index ) { //mutGenLimit 2
                    result = current; //mutGenLimit 2
                } else {
                }
                current_index = current_index + 1; //mutGenLimit 2
                current
                    = current.next; //mutGenLimit 2
            } else {
            }
            if ( result == null && current != null ) { //mutGenLimit 2
                if ( index == current_index ) { //mutGenLimit 2
                    result = current; //mutGenLimit 2
                } else {
                }
                current_index = current_index + 1; //mutGenLimit 2
                current
                    = current.next; //mutGenLimit 2
            } else {
            }
            if ( result == null && current != null ) { //mutGenLimit 2
                if ( index == current_index ) { //mutGenLimit 2
                    result = current; //mutGenLimit 2
                } else {
                }
                current_index = current_index + 1; //mutGenLimit 2
                current
                    = current.next; //mutGenLimit 2
            } else {
            }
            if ( result == null && current != null ) {
                terminatesInTime = true;
            } else {
            }
        }
        return result;
    }

    /*@ requires true;
    @ ensures (\exists SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.value == arg && n.next == null);
    @ ensures (\forall SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.next != null ==> \old(\reach(this.header, SinglyLinkedListNode, next)).has(n));
    @*/
    public void insertBack ( java.lang.Object arg) {
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
							"fajita_roopsGoal_initialization();\n");
		} catch (IOException ioexception) {
		}
		fajita_roopsGoal_initialization();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
							"BugLineMarker __marker__=new BugLineMarker(); //lineNumber=226\n");
		} catch (IOException ioexception) {
		}
		BugLineMarker __marker__ = new BugLineMarker();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
							"__marker__.mark(80); //lineNumber=227\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(80);
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
							"roops.core.objects.SinglyLinkedListNode freshNode=new roops.core.objects.SinglyLinkedListNode(); //lineNumber=228\n");
		} catch (IOException ioexception) {
		}
		roops.core.objects.SinglyLinkedListNode freshNode = new roops.core.objects.SinglyLinkedListNode();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
							"__marker__.mark(81); //lineNumber=229\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(81);
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
							"\nfreshNode.value=null; //mutGenLimit 2 //lineNumber=230\n");
		} catch (IOException ioexception) {
		}
		freshNode.value = null;
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
							"__marker__.mark(82); //lineNumber=231\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(82);
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
							"\nfreshNode.next=null; //mutGenLimit 2 //lineNumber=233\n");
		} catch (IOException ioexception) {
		}
		freshNode.next = null;
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
							"__marker__.mark(83); //lineNumber=234\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(83);
        if ( this.header == null ) {
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
								"if(!(this.header == null)){throw new RuntimeException();}\n");
			} catch (IOException ioexception) {
			}
			try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
								"roops_goal_0=true; //lineNumber=237\n");
			} catch (IOException ioexception) {
			}
			roops_goal_0 = true;
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
								"__marker__.mark(84); //lineNumber=238\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark(84);
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
								"this.header=freshNode; //lineNumber=239\n");
			} catch (IOException ioexception) {
			}
			this.header = freshNode;
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
								"__marker__.mark(85); //lineNumber=240\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark(85);
        } else {
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
								"if(this.header == null){throw new RuntimeException();}\n");
			} catch (IOException ioexception) {
			}
			try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
								"roops_goal_1=true; //lineNumber=242\n");
			} catch (IOException ioexception) {
			}
			roops_goal_1 = true;
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
								"__marker__.mark(86); //lineNumber=243\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark(86);
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
								"roops.core.objects.SinglyLinkedListNode current; //lineNumber=244\n");
			} catch (IOException ioexception) {
			}
			roops.core.objects.SinglyLinkedListNode current;
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
								"__marker__.mark(87); //lineNumber=245\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark(87);
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
								"\ncurrent=this.header; //mutGenLimit 2 //lineNumber=246\n");
			} catch (IOException ioexception) {
			}
			current = this.header;
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
								"__marker__.mark(88); //lineNumber=247\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark(88);
            {
                try {
					FileUtils
							.appendToFile(
									"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
									"boolean terminatesInTime=false; //lineNumber=250\n");
				} catch (IOException ioexception) {
				}
				boolean terminatesInTime = false;
                if ( current.next != null ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
										"\nroops_goal_2=true; //mutGenLimit 2 //lineNumber=252\n");
					} catch (IOException ioexception) {
					}
					roops_goal_2 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
										"__marker__.mark(89); //lineNumber=253\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(89);
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
										"current=current.next; //lineNumber=254\n");
					} catch (IOException ioexception) {
					}
					current = current.next;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
										"__marker__.mark(90); //lineNumber=255\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(90);
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
										"if(current.next != null){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
										"roops_goal_3=true; //lineNumber=257\n");
					} catch (IOException ioexception) {
					}
					roops_goal_3 = true;
                }
                if ( current.next != null ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
										"\nroops_goal_4=true; //mutGenLimit 2 //lineNumber=260\n");
					} catch (IOException ioexception) {
					}
					roops_goal_4 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
										"__marker__.mark(89); //lineNumber=261\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(89);
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
										"current=current.next; //lineNumber=262\n");
					} catch (IOException ioexception) {
					}
					current = current.next;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
										"__marker__.mark(90); //lineNumber=263\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(90);
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
										"if(current.next != null){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
										"roops_goal_5=true; //lineNumber=265\n");
					} catch (IOException ioexception) {
					}
					roops_goal_5 = true;
                }
                if ( current.next != null ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
										"\nroops_goal_6=true; //mutGenLimit 2 //lineNumber=268\n");
					} catch (IOException ioexception) {
					}
					roops_goal_6 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
										"__marker__.mark(89); //lineNumber=269\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(89);
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
										"current=current.next; //lineNumber=270\n");
					} catch (IOException ioexception) {
					}
					current = current.next;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
										"__marker__.mark(90); //lineNumber=271\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(90);
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
										"if(current.next != null){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
										"roops_goal_7=true; //lineNumber=273\n");
					} catch (IOException ioexception) {
					}
					roops_goal_7 = true;
                }
                if ( current.next != null ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
										"\nroops_goal_8=true; //mutGenLimit 2 //lineNumber=276\n");
					} catch (IOException ioexception) {
					}
					roops_goal_8 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
										"__marker__.mark(89); //lineNumber=277\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(89);
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
										"current=current.next; //lineNumber=278\n");
					} catch (IOException ioexception) {
					}
					current = current.next;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
										"__marker__.mark(90); //lineNumber=279\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(90);
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
										"if(current.next != null){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
										"roops_goal_9=true; //lineNumber=281\n");
					} catch (IOException ioexception) {
					}
					roops_goal_9 = true;
                }
                if ( current.next != null ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
										"\nroops_goal_10=true; //mutGenLimit 2 //lineNumber=284\n");
					} catch (IOException ioexception) {
					}
					roops_goal_10 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
										"__marker__.mark(89); //lineNumber=285\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(89);
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
										"current=current.next; //lineNumber=286\n");
					} catch (IOException ioexception) {
					}
					current = current.next;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
										"__marker__.mark(90); //lineNumber=287\n");
					} catch (IOException ioexception) {
					}
					__marker__.mark(90);
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
										"if(current.next != null){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
										"roops_goal_11=true; //lineNumber=289\n");
					} catch (IOException ioexception) {
					}
					roops_goal_11 = true;
                }
                if ( current.next != null ) {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
										"if(!(current.next != null)){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
										"roops_goal_12=true; //lineNumber=292\n");
					} catch (IOException ioexception) {
					}
					roops_goal_12 = true;
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
										"terminatesInTime=true; //lineNumber=293\n");
					} catch (IOException ioexception) {
					}
					terminatesInTime = true;
                } else {
                    try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
										"if(current.next != null){throw new RuntimeException();}\n");
					} catch (IOException ioexception) {
					}
					try {
						FileUtils
								.appendToFile(
										"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
										"roops_goal_13=true; //lineNumber=295\n");
					} catch (IOException ioexception) {
					}
					roops_goal_13 = true;
                }
            }
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
								"__marker__.mark(91); //lineNumber=298\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark(91);
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
								"\ncurrent.next=freshNode; //mutGenLimit 2 //lineNumber=299\n");
			} catch (IOException ioexception) {
			}
			current.next = freshNode;
            try {
				FileUtils
						.appendToFile(
								"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
								"__marker__.mark(92); //lineNumber=300\n");
			} catch (IOException ioexception) {
			}
			__marker__.mark(92);
        }
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/SinglyLinkedListContainsBug7.java_insertBack",
							"__marker__.mark(93);\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(93);
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
    }
}
