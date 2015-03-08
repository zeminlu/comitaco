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
    public boolean contains (  /*@nullable@*/ java.lang.Object valueParam) {fajita_roopsGoal_initialization();
BugLineMarker __marker__=new BugLineMarker(); //lineNumber=38
__marker__.mark(37); //lineNumber=39
SinglyLinkedListNode current; //lineNumber=40
__marker__.mark(38); //lineNumber=41
boolean result; //lineNumber=42
__marker__.mark(39); //lineNumber=43
current=this.header; //lineNumber=44
__marker__.mark(40); //lineNumber=45
result=false; //lineNumber=46
__marker__.mark(41); //lineNumber=47
boolean fajita_cicle_0=false; //lineNumber=49
if(result != false && current != null){throw new RuntimeException();}
if(!(!fajita_cicle_0)){throw new RuntimeException();}
roops_goal_1=true; //lineNumber=101
__marker__.mark(61); //lineNumber=104
return result; //lineNumber=105
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
