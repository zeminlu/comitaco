// This is mutant program.
// Author : ysma

package roops.core.objectsInstrumented;


import roops.core.objectsInstrumented.SinglyLinkedListNode;


/*@ nullable_by_default @*/
public class SinglyLinkedList {

/*@
    @ invariant (\forall SinglyLinkedListNode n; \reach(header, SinglyLinkedListNode, next).has(n); \reach(n.next, SinglyLinkedListNode, next).has(n)==false);
    @*/
    public SinglyLinkedListNode header;

    public SinglyLinkedList ()
     {
    }

//----------------- showInstance --------------------//
/*@ requires \reach(this.header, SinglyLinkedListNode, next).int_size() == 100;    
    @ ensures \result == false;
    @*/
    public boolean showInstance ()
     {
        return true;
    }

    /** @Modifies_Everything
	 * @Ensures false;
	 */
    public boolean contains (  /*@nullable@*/ java.lang.Object value_param) {
        fajita_roopsGoal_initialization ();
        SinglyLinkedListNode current;
        boolean result;
        current = this.header;
//        current = this.header.next; //mutGenLimit 1
        result
         = false;
        {
            boolean fajita_cicle_0 = false;
            while ( result == false && current != null )
            { fajita_cicle_0 = true;
                roops_goal_0 = true;
                boolean equalVal;
                if ( value_param == null && current.value == null )
                {
                    roops_goal_2 = true;
                    equalVal = true;
//                equalVal = false; //mutGenLimit 1
                } else
                {
                    roops_goal_3 = true;
                    if ( value_param != null )
                    {
                        roops_goal_4 = true;
                        if ( value_param == current.value )
                        {
                            roops_goal_6 = true;
                            equalVal = true;
                        } else
                        {
                            roops_goal_7 = true;
                            equalVal = false;
                        }
                    } else
                    {
                        roops_goal_5 = true;
                        equalVal = false;
                    }
                }
                if ( equalVal == true )
                {
                    roops_goal_8 = true;
                    result = true;
                }
                else
                {
                    roops_goal_9 = true;
                }
                current = current.next;
            //            current = current.next.next; //mutGenLimit 1
            }
            if ( ! fajita_cicle_0 )
                roops_goal_1 = true;
        }
        return result;
//                return !result; //mutGenLimit 1
    }
/*@
    @ requires index>=0 && index<\reach(this.header, SinglyLinkedListNode, next).int_size();
    @ ensures \reach(this.header, SinglyLinkedListNode, next).has(\result)==true; 
    @ ensures \reach(\result, SinglyLinkedListNode, next).int_size() == \reach(this.header, SinglyLinkedListNode, next).int_size()-index;
    @ signals (Exception e) false;
    @*/
    public SinglyLinkedListNode getNode ( int index)
     {
        SinglyLinkedListNode current = header;
        SinglyLinkedListNode result = null;
        int current_index = 0;
        while ( result == null && current != null ) {
            if ( index == current_index ) {
                result = current;
            }
            else
            {}
            current_index = current_index + 1;
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
    void insertBack ( java.lang.Object data, SinglyLinkedListNode freshNode)
     {
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
