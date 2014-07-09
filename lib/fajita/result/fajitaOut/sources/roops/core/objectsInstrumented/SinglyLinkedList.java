package roops.core.objectsInstrumented;


//Authors: Marcelo Frias
import roops.utilInstrumented.RoopsArray;
/**
 * @Invariant all n: SinglyLinkedListNode | ( ( n in this.header.*next @- null ) => ( n !in n.next.*next @- null ) ) ;
 */
public class SinglyLinkedList {

    /** @Modifies_Everything
	 * @Ensures false;
	 */ static
     public void containsTest (SinglyLinkedList list, Object value_param) {
        fajita_roopsGoal_initialization ();
        boolean ret_val;
        if ( list != null && list.repOK () )
        {
            roops_goal_0 = true;
            ret_val = list.contains (value_param);
        }
        else
        {
            roops_goal_1 = true;
        }
    } static
     public void insertBackTest (SinglyLinkedList list, Object arg) {
        if ( list != null && list.repOK () ) {
            list.insertBack (arg);
        }
        else
        {}
    } static
     public void removeTest (SinglyLinkedList list, int index) {
        if ( list != null && list.repOK () ) {
            list.remove (index);
        }
        else
        {}
    }


    public /*@ nullable @*/SinglyLinkedListNode header;



    public boolean contains (Object value_param) {
        SinglyLinkedListNode current;
        boolean result;

        current = this.header;
        result = false;
        {
            boolean fajita_cicle_2 = false;
            while ( result == false && current != null )
            { fajita_cicle_2 = true;
                roops_goal_2 = true;
                {}

                boolean equalVal;

                if ( value_param == null && current.value == null )
                {
                    roops_goal_4 = true;
                    {}
                    equalVal = true;
                } else if ( value_param != null )
                    {
                        roops_goal_5 = true;

                        if ( value_param == current.value )
                        {
                            roops_goal_7 = true;
                            {}
                            equalVal = true;
                        } else
                        {
                            roops_goal_8 = true;
                            {}
                            equalVal = false;
                        }
                    } else
                    {
                        roops_goal_6 = true;
                        {}
                        equalVal = false;
                    }

                if ( equalVal == true )
                {
                    roops_goal_9 = true;
                    {}
                    result = true;
                }
                else
                {
                    roops_goal_10 = true;
                }
                current = current.next;
            }
            if ( ! fajita_cicle_2 )
                roops_goal_3 = true;
        }
        {}
        return result;
    }





    public void remove (int index) {

        if ( index < 0 ) {
            {}
            throw new RuntimeException ();
        }
        else
        {}

        SinglyLinkedListNode current;
        current = this.header;
        SinglyLinkedListNode previous;
        previous = null;
        int current_index;
        current_index = 0;

        boolean found = false;

        while ( found == false && current != null ) {
            {}

            if ( index == current_index ) {
                {}
                found = true;
            } else {
                {}
                current_index = current_index + 1;
                previous = current;
                current = current.next;
            }
        }

        if ( found == false ) {
            {}
            throw new RuntimeException ();
        }
        else
        {}

        if ( previous == null )
        {
            {}
            this.header = current.next;
        } else {
            {}
            previous.next = current.next;
        }
    }



    public void insertBack (Object arg) {
        SinglyLinkedListNode freshNode = new SinglyLinkedListNode ();
        freshNode.value = arg;
        freshNode.next = null;

        if ( this.header == null ) {
            {}
            this.header = freshNode;
        } else {
            {}
            SinglyLinkedListNode current;
            current = this.header;
            while ( current.next != null ) {
                {}
                current = current.next;
            }
            current.next = freshNode;
        }
        {}
    }

    public SinglyLinkedList () {}

        //*************************************************************************
        //************** From now on repOK()  *************************************
        //*************************************************************************

        public boolean repOK () {
        return true;
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

    public static RoopsArray myRoopsArray;

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
        myRoopsArray = null;
    }
}
/* end roops.core.objects */
