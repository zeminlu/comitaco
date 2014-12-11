// This is mutant program.
// Author : ysma

package roops.core.objects;


import roops.core.objects.SinglyLinkedListNode;
import roops.core.objects.BugLineMarker;


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
        roops.core.objects.SinglyLinkedListNode current;
        boolean result;
        current = this.header;
        result = false;
        {
            boolean terminatesInTime = false;
            if ( result == false && current != null ) {
                boolean equalVal;
                if ( valueParam == null && current.value == null ) {
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
                if ( valueParam == null && current.value == null ) {
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
                if ( valueParam == null && current.value == null ) {
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
                if ( valueParam == null && current.value == null ) {
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
                if ( valueParam == null && current.value == null ) {
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
                if ( valueParam == null && current.value == null ) {
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
                if ( valueParam == null && current.value == null ) {
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
                if ( valueParam == null && current.value == null ) {
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

//--------------------------- getNode ----------------------------//
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
    void insertBack ( java.lang.Object data, roops.core.objects.SinglyLinkedListNode freshNode) {fajita_roopsGoal_initialization();
BugLineMarker __marker__=new BugLineMarker(); //lineNumber=337
__marker__.mark(101); //lineNumber=338
freshNode.value=null; //lineNumber=339
__marker__.mark(102); //lineNumber=340
freshNode.next=null; //lineNumber=341
__marker__.mark(103); //lineNumber=342
if(this.header == null){throw new RuntimeException();}
roops_goal_1=true; //lineNumber=349
__marker__.mark(106); //lineNumber=350
roops.core.objects.SinglyLinkedListNode current=this.header; //lineNumber=351
__marker__.mark(107); //lineNumber=352
boolean terminatesInTime=false; //lineNumber=354
if(current.next != null){throw new RuntimeException();}
roops_goal_3=true; //lineNumber=361
if(current.next != null){throw new RuntimeException();}
roops_goal_5=true; //lineNumber=369
if(current.next != null){throw new RuntimeException();}
roops_goal_7=true; //lineNumber=377
if(current.next != null){throw new RuntimeException();}
roops_goal_9=true; //lineNumber=385
if(current.next != null){throw new RuntimeException();}
roops_goal_11=true; //lineNumber=393
if(current.next != null){throw new RuntimeException();}
roops_goal_13=true; //lineNumber=401
if(current.next != null){throw new RuntimeException();}
roops_goal_15=true; //lineNumber=409
if(current.next != null){throw new RuntimeException();}
roops_goal_17=true; //lineNumber=417
if(current.next != null){throw new RuntimeException();}
roops_goal_19=true; //lineNumber=423
__marker__.mark(110); //lineNumber=426
current.next=freshNode; //lineNumber=427
__marker__.mark(111); //lineNumber=428
__marker__.mark(112);
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
    }
}
