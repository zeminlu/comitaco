package roops.core.objects;


import roops.core.objects.SinglyLinkedListNode;
import roops.core.objects.BugLineMarker;

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
    public void insertBack ( java.lang.Object arg) {fajita_roopsGoal_initialization();
BugLineMarker __marker__=new BugLineMarker(); //lineNumber=226
__marker__.mark80(); //lineNumber=227
roops.core.objects.SinglyLinkedListNode freshNode=new roops.core.objects.SinglyLinkedListNode(); //lineNumber=228
__marker__.mark81(); //lineNumber=229

freshNode.value=arg; //mutGenLimit 2 //lineNumber=230
__marker__.mark82(); //lineNumber=231

freshNode.next=freshNode; //mutGenLimit 2 //lineNumber=233
__marker__.mark83(); //lineNumber=234
if(!(this.header == null)){throw new RuntimeException();}
roops_goal_0=true; //lineNumber=237
__marker__.mark84(); //lineNumber=238
this.header=freshNode; //lineNumber=239
__marker__.mark85(); //lineNumber=240
__marker__.mark93();
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