package roops.core.objects;


import roops.core.objects.SinglyLinkedListNode;
import roops.core.objects.BugLineMarker;


public class SinglyLinkedList {

    /*@
    @ invariant (\forall SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); \reach(n.next, SinglyLinkedListNode, next).has(n)==false);
    @*/    public /*@nullable@*/roops.core.objects.SinglyLinkedListNode header;

    public SinglyLinkedList () {
    }

    /*@
    @ requires true;
    @ ensures (\exists SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.value==valueParam) ==> (\result==true);
    @ ensures (\result == true) ==> (\exists SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.value==valueParam);
    @ signals (RuntimeException e) false;
    @
    @*/
    public boolean contains ( /*@nullable@*/java.lang.Object valueParam) {fajita_roopsGoal_initialization();
BugLineMarker __marker__=new BugLineMarker(); //lineNumber=25
__marker__.mark(25); //lineNumber=26
SinglyLinkedListNode current; //lineNumber=27
__marker__.mark(26); //lineNumber=28
boolean result; //lineNumber=29
__marker__.mark(27); //lineNumber=30
current=this.header; //lineNumber=31
__marker__.mark(28); //lineNumber=32
result=false; //lineNumber=33
__marker__.mark(29); //lineNumber=34
boolean fajita_cicle_0=false; //lineNumber=36
if(!(result == false && current != null)){throw new RuntimeException();}
fajita_cicle_0=true; //lineNumber=38
roops_goal_0=true; //lineNumber=39
__marker__.mark(30); //lineNumber=40
boolean equalVal; //lineNumber=41
__marker__.mark(31); //lineNumber=42
if(valueParam == null && current.value == null){throw new RuntimeException();}
roops_goal_3=true; //lineNumber=49
__marker__.mark(34); //lineNumber=50
if(!(valueParam != null)){throw new RuntimeException();}
roops_goal_4=true; //lineNumber=52
__marker__.mark(35); //lineNumber=53
if(valueParam == current.value){throw new RuntimeException();}
roops_goal_7=true; //lineNumber=60
__marker__.mark(38); //lineNumber=61
equalVal=false; //lineNumber=62
__marker__.mark(39); //lineNumber=63
__marker__.mark(40); //lineNumber=65
__marker__.mark(43); //lineNumber=72
__marker__.mark(44); //lineNumber=74
if(equalVal == true){throw new RuntimeException();}
roops_goal_9=true; //lineNumber=81
__marker__.mark(47); //lineNumber=83
current=current.next; //lineNumber=84
__marker__.mark(48); //lineNumber=85
if(!(result == false && current != null)){throw new RuntimeException();}
fajita_cicle_0=true; //lineNumber=38
roops_goal_0=true; //lineNumber=39
__marker__.mark(30); //lineNumber=40
__marker__.mark(31); //lineNumber=42
if(valueParam == null && current.value == null){throw new RuntimeException();}
roops_goal_3=true; //lineNumber=49
__marker__.mark(34); //lineNumber=50
if(!(valueParam != null)){throw new RuntimeException();}
roops_goal_4=true; //lineNumber=52
__marker__.mark(35); //lineNumber=53
if(!(valueParam == current.value)){throw new RuntimeException();}
roops_goal_6=true; //lineNumber=55
__marker__.mark(36); //lineNumber=56
equalVal=true; //lineNumber=57
__marker__.mark(37); //lineNumber=58
__marker__.mark(40); //lineNumber=65
__marker__.mark(43); //lineNumber=72
__marker__.mark(44); //lineNumber=74
if(!(equalVal == true)){throw new RuntimeException();}
roops_goal_8=true; //lineNumber=76
__marker__.mark(45); //lineNumber=77
result=false; //lineNumber=78
__marker__.mark(46); //lineNumber=79
__marker__.mark(47); //lineNumber=83
current=current.next; //lineNumber=84
__marker__.mark(48); //lineNumber=85
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark(49); //lineNumber=91
return result; //lineNumber=92
}
    /*@
    @ requires index>=0 && index<\reach(this.header, SinglyLinkedListNode, next).int_size();
    @
    @ ensures \reach(this.header, SinglyLinkedListNode, next).has(\result)==true;
    @ ensures \reach(\result, SinglyLinkedListNode, next).int_size() == \reach(this.header, SinglyLinkedListNode, next).int_size()-index;
    @ signals (RuntimeException e) false;
    @*/    public SinglyLinkedListNode getNode ( int index) {
        SinglyLinkedListNode current = this.header;
        SinglyLinkedListNode result = null;
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

    /*@ requires true;
      @ ensures (\exists SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.value == arg && n.next == null);
      @ ensures (\forall SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.next != null ==> \old(\reach(this.header, SinglyLinkedListNode, next)).has(n));
      @ ensures (\forall SinglyLinkedListNode n; \old(\reach(this.header, SinglyLinkedListNode, next)).has(n); \reach(this.header, SinglyLinkedListNode, next).has(n) && n.next != null);
      @*/    public void insertBack ( java.lang.Object arg) {
        roops.core.objects.SinglyLinkedListNode freshNode = new roops.core.objects.SinglyLinkedListNode ();
        freshNode.value = arg;
        freshNode.next = null;
        if ( this.header == null ) {
            this.header = freshNode;
        } else {
            roops.core.objects.SinglyLinkedListNode current;
            current = this.header;
            while (  current.next != null ) {
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
