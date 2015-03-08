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
    public boolean contains (  /*@nullable@*/ java.lang.Object valueParam) {
        fajita_roopsGoal_initialization ();
        BugLineMarker __marker__ = new BugLineMarker ();
        __marker__.mark (37);
        SinglyLinkedListNode current;
        __marker__.mark (38);
        boolean result;
        __marker__.mark (39);
        current = this.header;
        __marker__.mark (40);
        result = false;
        __marker__.mark (41);
        {
            boolean fajita_cicle_0 = false;
            while ( result != false && current != null ) {
                fajita_cicle_0 = true;
                roops_goal_0 = true;
                __marker__.mark (42);
                boolean equalVal;
                __marker__.mark (43);
                if ( valueParam == null && current.value == null ) {
                    roops_goal_2 = true;
                    __marker__.mark (44);
                    equalVal = true;
                    __marker__.mark (45);
                } else {
                    roops_goal_3 = true;
                    __marker__.mark (46);
                    if ( valueParam != null ) {
                        roops_goal_4 = true;
                        __marker__.mark (47);
                        if ( valueParam == current.value ) {
                            roops_goal_6 = true;
                            __marker__.mark (48);
                            equalVal = true;
                            __marker__.mark (49);
                        } else {
                            roops_goal_7 = true;
                            __marker__.mark (50);
                            equalVal = false;
                            __marker__.mark (51);
                        }
                        __marker__.mark (52);
                    } else {
                        roops_goal_5 = true;
                        __marker__.mark (53);
                        equalVal = false;
                        __marker__.mark (54);
                    }
                    __marker__.mark (55);
                }
                __marker__.mark (56);
                if ( equalVal == true ) {
                    roops_goal_8 = true;
                    __marker__.mark (57);
                    result = true;
                    __marker__.mark (58);
                } else {
                    roops_goal_9 = true;
                }
                __marker__.mark (59);
                current = current.next;
                __marker__.mark (60);
            }
            if ( ! fajita_cicle_0 ) {
                roops_goal_1 = true;
            }
        }
        __marker__.mark (61);
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
