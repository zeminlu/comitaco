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
        while ( result == false && current != null ) {
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
    public roops.core.objects.SinglyLinkedListNode getNode ( int index) {fajita_roopsGoal_initialization();
BugLineMarker __marker__=new BugLineMarker(); //lineNumber=74
__marker__.mark(72); //lineNumber=75
roops.core.objects.SinglyLinkedListNode current=header; //lineNumber=76
__marker__.mark(73); //lineNumber=77
roops.core.objects.SinglyLinkedListNode result=null; //lineNumber=78
__marker__.mark(74); //lineNumber=79
int current_index=0; //lineNumber=80
__marker__.mark(75); //lineNumber=81
boolean fajita_cicle_0=false; //lineNumber=83
if(!(result == null && current != null)){throw new RuntimeException();}
fajita_cicle_0=true; //lineNumber=85
roops_goal_0=true; //lineNumber=86
__marker__.mark(76); //lineNumber=87
if(index == current_index){throw new RuntimeException();}
roops_goal_3=true; //lineNumber=94
__marker__.mark(79); //lineNumber=96
current_index=current_index + 2; //lineNumber=97
__marker__.mark(80); //lineNumber=98
current=current.next; //lineNumber=99
__marker__.mark(81); //lineNumber=100
if(!(result == null && current != null)){throw new RuntimeException();}
fajita_cicle_0=true; //lineNumber=85
roops_goal_0=true; //lineNumber=86
__marker__.mark(76); //lineNumber=87
if(index == current_index){throw new RuntimeException();}
roops_goal_3=true; //lineNumber=94
__marker__.mark(79); //lineNumber=96
current_index=current_index + 2; //lineNumber=97
__marker__.mark(80); //lineNumber=98
current=current.next; //lineNumber=99
__marker__.mark(81); //lineNumber=100
if(result == null && current != null){throw new RuntimeException();}
__marker__.mark(82); //lineNumber=106
return result; //lineNumber=107
}
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

    public static boolean roops_goal_0;

    public static boolean roops_goal_1;

    public static boolean roops_goal_2;

    public static boolean roops_goal_3;

    public static void fajita_roopsGoal_initialization () {
        roops_goal_0 = false;
        roops_goal_1 = false;
        roops_goal_2 = false;
        roops_goal_3 = false;
    }
}
