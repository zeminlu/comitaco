<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
// This is mutant program.
// Author : ysma

>>>>>>> falta unroll
package roops.core.objects;

<<<<<<< HEAD
<<<<<<< HEAD
=======
package roops.core.objects.sequential;
>>>>>>> instru

=======
package roops.core.objects;


>>>>>>> unsat error lines
=======
package roops.core.objects;

>>>>>>> more
=======

>>>>>>> como te cabe mi picadura
import roops.core.objects.SinglyLinkedListNode;
import roops.core.objects.BugLineMarker;


/*@ nullable_by_default @*/
public class SinglyLinkedList {

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
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

<<<<<<< HEAD
/*@
    @ ensures (\exists SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.value==value_param) <==> (\result==true);
    @ signals (Exception e) true;
    @*/
<<<<<<< HEAD
<<<<<<< HEAD
    public boolean contains(  /*@nullable@*/ java.lang.Object value_param )
<<<<<<< HEAD
<<<<<<< HEAD
    {roops.core.objects.SinglyLinkedListNode current;
=======
    {roops.core.objects.SinglyLinkedListNode _SinglyLinkedListNode_1 = new roops.core.objects.SinglyLinkedListNode();
roops.core.objects.SinglyLinkedListNode _SinglyLinkedListNode_2 = new roops.core.objects.SinglyLinkedListNode();
roops.core.objects.SinglyLinkedListNode _SinglyLinkedListNode_3 = new roops.core.objects.SinglyLinkedListNode();
roops.core.objects.SinglyLinkedListNode _SinglyLinkedListNode_4 = new roops.core.objects.SinglyLinkedListNode();
roops.core.objects.SinglyLinkedListNode _SinglyLinkedListNode_5 = new roops.core.objects.SinglyLinkedListNode();
java.lang.Object value_param_Object_1 = new java.lang.Object();
_SinglyLinkedListNode_3.next = null;
_SinglyLinkedListNode_3.value = _SinglyLinkedListNode_4;
_SinglyLinkedListNode_2.next = _SinglyLinkedListNode_3;
_SinglyLinkedListNode_2.value = _SinglyLinkedListNode_5;
_SinglyLinkedListNode_1.next = _SinglyLinkedListNode_2;
_SinglyLinkedListNode_1.value = value_param_Object_1;
this.header = _SinglyLinkedListNode_1;
value_param = value_param_Object_1;
roops.core.objects.SinglyLinkedListNode current;
>>>>>>> generating weird sequential code
=======
    {roops.core.objects.SinglyLinkedListNode current;
>>>>>>> negate post
boolean result;
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
<<<<<<< HEAD
=======
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
current=this.header;
result=false;
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
current=this.header;
result=false;
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
=======
    public boolean contains(  /*@nullable@*/ java.lang.Object value_param ) {
=======
    public boolean contains(  /*@nullable@*/ java.lang.Object value_param ) {roops.core.objects.SinglyLinkedListNode _SinglyLinkedListNode_1 = new roops.core.objects.SinglyLinkedListNode();
>>>>>>> unsat error lines
BugLineMarker __marker__ = new BugLineMarker();
__marker__.mark();
roops.core.objects.SinglyLinkedListNode _SinglyLinkedListNode_2 = new roops.core.objects.SinglyLinkedListNode();
__marker__.mark();
roops.core.objects.BugLineMarker value_param_BugLineMarker_1 = new roops.core.objects.BugLineMarker();
__marker__.mark();
java.lang.Exception _Exception_1 = new java.lang.Exception();
__marker__.mark();
_SinglyLinkedListNode_2.next = null;
__marker__.mark();
_SinglyLinkedListNode_2.value = value_param_BugLineMarker_1;
__marker__.mark();
_SinglyLinkedListNode_1.next = _SinglyLinkedListNode_2;
__marker__.mark();
_SinglyLinkedListNode_1.value = _Exception_1;
__marker__.mark();
this.header = _SinglyLinkedListNode_1;
__marker__.mark();
value_param = value_param_BugLineMarker_1;
__marker__.mark();
BugLineMarker dummy=new BugLineMarker();
__marker__.mark();
SinglyLinkedListNode current;
__marker__.mark();
boolean res;
__marker__.mark();
>>>>>>> UNSAT 0 variables con marks
current=this.header;
__marker__.mark();
res=false;
__marker__.mark();
if(!(res == false && current != null)){throw new RuntimeException();}
__marker__.mark();
boolean equalVal;
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(!(value_param != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == current.value){throw new RuntimeException();}
=======
=======
>>>>>>> more
	public static boolean roops_goal_0;

	public static boolean roops_goal_1;

	public static boolean roops_goal_2;

	public static boolean roops_goal_3;

	public static boolean roops_goal_4;

	public static boolean roops_goal_5;
=======
    /*@
        @ invariant (\forall SinglyLinkedListNode n; \reach(header, SinglyLinkedListNode, next).has(n); \reach(n.next, SinglyLinkedListNode, next).has(n)==false);
        @*/
    public roops.core.objects.SinglyLinkedListNode header;
>>>>>>> como te cabe mi picadura

    public SinglyLinkedList () {
    }

//----------------- showInstance --------------------//
    /*@ requires \reach(this.header, SinglyLinkedListNode, next).int_size() == 100;
        @ ensures \result == false;
        @*/
    public boolean showInstance () {
        return true;
    }
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> loop unroll, on the floor

//-------------------- contains -------------------------//

    /** @Modifies_Everything
     * @Ensures false;
     */
<<<<<<< HEAD
<<<<<<< HEAD
    public boolean contains (  /*@nullable@*/ java.lang.Object value_param) {roops.core.objects.SinglyLinkedListNode _SinglyLinkedListNode_1 = new roops.core.objects.SinglyLinkedListNode();
<<<<<<< HEAD
BugLineMarker __marker__ = new BugLineMarker();
<<<<<<< HEAD
__marker__.mark();
this.header = null;
__marker__.mark();
value_param = value_param_BugLineMarker_1;
__marker__.mark();
SinglyLinkedListNode current;
__marker__.mark();
boolean result;
__marker__.mark();
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
boolean equalVal;
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
//mutID 0
equalVal=false; //mutGenLimit 1
__marker__.mark();
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
//mutID 0
equalVal=false; //mutGenLimit 1
__marker__.mark();
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
//mutID 0
equalVal=false; //mutGenLimit 1
__marker__.mark();
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
//mutID 0
equalVal=false; //mutGenLimit 1
__marker__.mark();
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
>>>>>>> instru
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
current=current.next; //          current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next; //          current = current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
>>>>>>> generating weird sequential code
current=this.header;
result=false;
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
<<<<<<< HEAD
return result; //        return !result; //mutGenLimit 1
<<<<<<< HEAD
=======
return result; //                return !result; //mutGenLimit 1
>>>>>>> generating weird sequential code
current=this.header;
result=false;
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
<<<<<<< HEAD
return result; //        return !result; //mutGenLimit 1
=======
return result; //                return !result; //mutGenLimit 1
>>>>>>> generating weird sequential code
current=this.header;
result=false;
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
<<<<<<< HEAD
return result; //        return !result; //mutGenLimit 1
=======
return result; //                return !result; //mutGenLimit 1
>>>>>>> generating weird sequential code
current=this.header;
result=false;
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
<<<<<<< HEAD
return result; //        return !result; //mutGenLimit 1
=======
return result; //                return !result; //mutGenLimit 1
>>>>>>> generating weird sequential code
current=this.header;
result=false;
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
<<<<<<< HEAD
return result; //        return !result; //mutGenLimit 1
=======
return result; //                return !result; //mutGenLimit 1
>>>>>>> generating weird sequential code
current=this.header;
result=false;
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
<<<<<<< HEAD
return result; //        return !result; //mutGenLimit 1
=======
return result; //                return !result; //mutGenLimit 1
>>>>>>> generating weird sequential code
current=this.header;
result=false;
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
<<<<<<< HEAD
return result; //        return !result; //mutGenLimit 1
=======
return result; //                return !result; //mutGenLimit 1
>>>>>>> generating weird sequential code
current=this.header;
result=false;
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
<<<<<<< HEAD
return result; //        return !result; //mutGenLimit 1
=======
return result; //                return !result; //mutGenLimit 1
>>>>>>> generating weird sequential code
current=this.header;
result=false;
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
<<<<<<< HEAD
return result; //        return !result; //mutGenLimit 1
=======
return result; //                return !result; //mutGenLimit 1
>>>>>>> generating weird sequential code
current=this.header;
result=false;
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
<<<<<<< HEAD
return result; //        return !result; //mutGenLimit 1
=======
return result; //                return !result; //mutGenLimit 1
>>>>>>> generating weird sequential code
current=this.header;
result=false;
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
<<<<<<< HEAD
return result; //        return !result; //mutGenLimit 1
=======
return result; //                return !result; //mutGenLimit 1
>>>>>>> generating weird sequential code
current=this.header;
result=false;
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
<<<<<<< HEAD
return result; //        return !result; //mutGenLimit 1
=======
return result; //                return !result; //mutGenLimit 1
>>>>>>> generating weird sequential code
current=this.header;
result=false;
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
<<<<<<< HEAD
=======
current=this.header;
result=false;
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
//mutID 0
equalVal=false; //mutGenLimit 1
//mutID 1
current=current.next; //          current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next; //          current = current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
//mutID 0
equalVal=false; //mutGenLimit 1
//mutID 1
current=current.next; //          current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next; //          current = current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
//mutID 0
equalVal=false; //mutGenLimit 1
//mutID 1
current=current.next; //          current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next; //          current = current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
//mutID 0
equalVal=false; //mutGenLimit 1
//mutID 1
current=current.next; //          current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next; //          current = current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 3
return result; //                return !result; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 3
return result; //                return !result; //mutGenLimit 1
<<<<<<< HEAD
>>>>>>> generating weird sequential code
=======
//mutID 0
current=this.header.next; //mutGenLimit 1
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 3
return result; //                return !result; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 3
return result; //                return !result; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 3
return result; //                return !result; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 3
return result; //                return !result; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
=======
current=current.next; //            current = current.next.next; //mutGenLimit 1
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
//mutID 0
equalVal=false; //mutGenLimit 1
__marker__.mark();
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
//mutID 0
equalVal=false; //mutGenLimit 1
__marker__.mark();
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
//mutID 0
equalVal=false; //mutGenLimit 1
__marker__.mark();
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
//mutID 0
equalVal=false; //mutGenLimit 1
__marker__.mark();
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
//mutID 0
equalVal=false; //mutGenLimit 1
__marker__.mark();
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
//mutID 0
equalVal=false; //mutGenLimit 1
__marker__.mark();
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(!(value_param != null)){throw new RuntimeException();}
__marker__.mark();
if(!(value_param == current.value)){throw new RuntimeException();}
__marker__.mark();
equalVal=true;
__marker__.mark();
if(!(equalVal == true)){throw new RuntimeException();}
__marker__.mark();
result=true;
__marker__.mark();
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
>>>>>>> instru
//mutID 3
return result; //                return !result; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
<<<<<<< HEAD
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
=======
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(!(value_param != null)){throw new RuntimeException();}
__marker__.mark();
if(!(value_param == current.value)){throw new RuntimeException();}
__marker__.mark();
equalVal=true;
__marker__.mark();
if(!(equalVal == true)){throw new RuntimeException();}
__marker__.mark();
result=true;
__marker__.mark();
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
>>>>>>> instru
//mutID 3
return result; //                return !result; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
<<<<<<< HEAD
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
=======
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(!(value_param != null)){throw new RuntimeException();}
__marker__.mark();
if(!(value_param == current.value)){throw new RuntimeException();}
__marker__.mark();
equalVal=true;
__marker__.mark();
if(!(equalVal == true)){throw new RuntimeException();}
__marker__.mark();
result=true;
__marker__.mark();
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
>>>>>>> instru
//mutID 3
return result; //                return !result; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
<<<<<<< HEAD
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
=======
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(!(value_param != null)){throw new RuntimeException();}
__marker__.mark();
if(!(value_param == current.value)){throw new RuntimeException();}
__marker__.mark();
equalVal=true;
__marker__.mark();
if(!(equalVal == true)){throw new RuntimeException();}
__marker__.mark();
result=true;
__marker__.mark();
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
>>>>>>> instru
//mutID 3
return result; //                return !result; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
<<<<<<< HEAD
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
=======
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(!(value_param != null)){throw new RuntimeException();}
__marker__.mark();
if(!(value_param == current.value)){throw new RuntimeException();}
__marker__.mark();
equalVal=true;
__marker__.mark();
if(!(equalVal == true)){throw new RuntimeException();}
__marker__.mark();
result=true;
__marker__.mark();
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
>>>>>>> instru
//mutID 3
return result; //                return !result; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
<<<<<<< HEAD
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
=======
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(!(value_param != null)){throw new RuntimeException();}
__marker__.mark();
if(!(value_param == current.value)){throw new RuntimeException();}
__marker__.mark();
equalVal=true;
__marker__.mark();
if(!(equalVal == true)){throw new RuntimeException();}
__marker__.mark();
result=true;
__marker__.mark();
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
>>>>>>> instru
//mutID 3
return result; //                return !result; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
<<<<<<< HEAD
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
=======
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(!(value_param != null)){throw new RuntimeException();}
__marker__.mark();
if(!(value_param == current.value)){throw new RuntimeException();}
__marker__.mark();
equalVal=true;
__marker__.mark();
if(!(equalVal == true)){throw new RuntimeException();}
__marker__.mark();
result=true;
__marker__.mark();
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
>>>>>>> instru
//mutID 3
return result; //                return !result; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
<<<<<<< HEAD
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
=======
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(!(value_param != null)){throw new RuntimeException();}
__marker__.mark();
if(!(value_param == current.value)){throw new RuntimeException();}
__marker__.mark();
equalVal=true;
__marker__.mark();
if(!(equalVal == true)){throw new RuntimeException();}
__marker__.mark();
result=true;
__marker__.mark();
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 3
return result; //                return !result; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(!(value_param != null)){throw new RuntimeException();}
__marker__.mark();
if(!(value_param == current.value)){throw new RuntimeException();}
__marker__.mark();
equalVal=true;
__marker__.mark();
if(!(equalVal == true)){throw new RuntimeException();}
__marker__.mark();
result=true;
__marker__.mark();
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
>>>>>>> instru
//mutID 3
return result; //                return !result; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
<<<<<<< HEAD
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 3
return result; //                return !result; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 3
return result; //                return !result; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 3
return result; //                return !result; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
=======
__marker__.mark();
>>>>>>> UNSAT 0 variables con marks
current=current.next; //            current = current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
<<<<<<< HEAD
//mutID 3
return result; //                return !result; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 3
return result; //                return !result; //mutGenLimit 1
>>>>>>> negate post
=======
>>>>>>> faj
=======
__marker__.mark();
//mutID 2
__marker__.mark();
return result; //                return !result; //mutGenLimit 1
>>>>>>> UNSAT 0 variables con marks
}

//--------------------------- getNode ----------------------------//
=======
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
//              equalVal = false; //mutGenLimit 1
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
//            current = current.next;
                else
                {
                    roops_goal_9 = true;
                }
                current = current.next.next; //mutGenLimit 1
            }
            if ( ! fajita_cicle_0 )
                roops_goal_1 = true;
        }
        return result;
//                return !result; //mutGenLimit 1
    }
>>>>>>> static-field-not-found
=======
__marker__.mark();
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(res == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
__marker__.mark();
return res; //                return !result; //mutGenLimit 1
}

//--------------------------- getNode ----------------------------//
>>>>>>> unsat error lines
/*@
    @ requires index>=0 && index<\reach(this.header, SinglyLinkedListNode, next).int_size();
    @ ensures \reach(this.header, SinglyLinkedListNode, next).has(\result)==true;
    @ ensures \reach(\result, SinglyLinkedListNode, next).int_size() == \reach(this.header, SinglyLinkedListNode, next).int_size()-index;
    @ signals (Exception e) false;
    @*/
<<<<<<< HEAD
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
=======
    public SinglyLinkedListNode getNode( int index )
    {
        SinglyLinkedListNode current = header;
        SinglyLinkedListNode result = null;
        int current_index = 0;
        while (result == null && current != null) {
            if (index == current_index) {
                result = current;
            }
>>>>>>> unsat error lines
            current_index = current_index + 1;
            current = current.next;
        }
        return result;
    }

<<<<<<< HEAD
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
<<<<<<< HEAD
    void insertBack ( java.lang.Object data, SinglyLinkedListNode freshNode)
     {
        freshNode.value = data;
        freshNode.next = null;
        if ( this.header == null ) {
            this.header = freshNode;
        } else {
            SinglyLinkedListNode current = this.header;
            while ( current.next != null ) {
=======
    void insertBack( java.lang.Object data, SinglyLinkedListNode freshNode )
    {
        freshNode.value = data;
        freshNode.next = null;
        if (this.header == null) {
            this.header = freshNode;
        } else {
            SinglyLinkedListNode current = this.header;
            while (current.next != null) {
>>>>>>> unsat error lines
                current = current.next;
            }
            current.next = freshNode;
        }
    }
<<<<<<< HEAD
=======
   
>>>>>>> unsat error lines
=======
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(!(value_param != null)){throw new RuntimeException();}
__marker__.mark();
if(!(value_param == current.value)){throw new RuntimeException();}
__marker__.mark();
equalVal=true;
__marker__.mark();
if(!(equalVal == true)){throw new RuntimeException();}
__marker__.mark();
result=true;
__marker__.mark();
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 3
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
if(value_param == null && current.value == null){throw new RuntimeException();}
__marker__.mark();
if(value_param != null){throw new RuntimeException();}
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
fajita_roopsGoal_initialization();
__marker__.mark();
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
boolean fajita_cicle_0=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
fajita_cicle_0=true;
__marker__.mark();
roops_goal_0=true;
__marker__.mark();
if(!(value_param == null && current.value == null)){throw new RuntimeException();}
__marker__.mark();
roops_goal_2=true;
__marker__.mark();
//mutID 0
equalVal=true; //              equalVal = false; //mutGenLimit 1
__marker__.mark();
if(!(equalVal == true)){throw new RuntimeException();}
__marker__.mark();
roops_goal_8=true;
__marker__.mark();
result=true;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
roops_goal_1=true;
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
fajita_roopsGoal_initialization();
__marker__.mark();
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
fajita_cicle_0=true;
__marker__.mark();
roops_goal_0=true;
__marker__.mark();
if(!(value_param == null && current.value == null)){throw new RuntimeException();}
__marker__.mark();
roops_goal_2=true;
__marker__.mark();
//mutID 0
equalVal=true; //              equalVal = false; //mutGenLimit 1
__marker__.mark();
if(!(equalVal == true)){throw new RuntimeException();}
__marker__.mark();
roops_goal_8=true;
__marker__.mark();
result=true;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
roops_goal_1=true;
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
fajita_roopsGoal_initialization();
__marker__.mark();
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
fajita_cicle_0=true;
__marker__.mark();
roops_goal_0=true;
__marker__.mark();
if(!(value_param == null && current.value == null)){throw new RuntimeException();}
__marker__.mark();
roops_goal_2=true;
__marker__.mark();
//mutID 0
equalVal=true; //              equalVal = false; //mutGenLimit 1
__marker__.mark();
if(!(equalVal == true)){throw new RuntimeException();}
__marker__.mark();
roops_goal_8=true;
__marker__.mark();
result=true;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
roops_goal_1=true;
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
fajita_roopsGoal_initialization();
__marker__.mark();
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
fajita_cicle_0=true;
__marker__.mark();
roops_goal_0=true;
__marker__.mark();
if(!(value_param == null && current.value == null)){throw new RuntimeException();}
__marker__.mark();
roops_goal_2=true;
__marker__.mark();
//mutID 0
equalVal=true; //              equalVal = false; //mutGenLimit 1
__marker__.mark();
if(!(equalVal == true)){throw new RuntimeException();}
__marker__.mark();
roops_goal_8=true;
__marker__.mark();
result=true;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
roops_goal_1=true;
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
fajita_roopsGoal_initialization();
__marker__.mark();
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
fajita_cicle_0=true;
__marker__.mark();
roops_goal_0=true;
__marker__.mark();
if(!(value_param == null && current.value == null)){throw new RuntimeException();}
__marker__.mark();
roops_goal_2=true;
__marker__.mark();
//mutID 0
equalVal=true; //              equalVal = false; //mutGenLimit 1
__marker__.mark();
if(!(equalVal == true)){throw new RuntimeException();}
__marker__.mark();
roops_goal_8=true;
__marker__.mark();
result=true;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
roops_goal_1=true;
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
fajita_roopsGoal_initialization();
__marker__.mark();
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
fajita_cicle_0=true;
__marker__.mark();
roops_goal_0=true;
__marker__.mark();
if(!(value_param == null && current.value == null)){throw new RuntimeException();}
__marker__.mark();
roops_goal_2=true;
__marker__.mark();
//mutID 0
equalVal=true; //              equalVal = false; //mutGenLimit 1
__marker__.mark();
if(!(equalVal == true)){throw new RuntimeException();}
__marker__.mark();
roops_goal_8=true;
__marker__.mark();
result=true;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
roops_goal_1=true;
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
fajita_roopsGoal_initialization();
__marker__.mark();
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
fajita_cicle_0=true;
__marker__.mark();
roops_goal_0=true;
__marker__.mark();
if(!(value_param == null && current.value == null)){throw new RuntimeException();}
__marker__.mark();
roops_goal_2=true;
__marker__.mark();
//mutID 0
equalVal=true; //              equalVal = false; //mutGenLimit 1
__marker__.mark();
if(!(equalVal == true)){throw new RuntimeException();}
__marker__.mark();
roops_goal_8=true;
__marker__.mark();
result=true;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
roops_goal_1=true;
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
fajita_roopsGoal_initialization();
__marker__.mark();
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
fajita_cicle_0=true;
__marker__.mark();
roops_goal_0=true;
__marker__.mark();
if(!(value_param == null && current.value == null)){throw new RuntimeException();}
__marker__.mark();
roops_goal_2=true;
__marker__.mark();
//mutID 0
equalVal=true; //              equalVal = false; //mutGenLimit 1
__marker__.mark();
if(!(equalVal == true)){throw new RuntimeException();}
__marker__.mark();
roops_goal_8=true;
__marker__.mark();
result=true;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
roops_goal_1=true;
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
fajita_roopsGoal_initialization();
__marker__.mark();
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
fajita_cicle_0=true;
__marker__.mark();
roops_goal_0=true;
__marker__.mark();
if(!(value_param == null && current.value == null)){throw new RuntimeException();}
__marker__.mark();
roops_goal_2=true;
__marker__.mark();
//mutID 0
equalVal=true; //              equalVal = false; //mutGenLimit 1
__marker__.mark();
if(!(equalVal == true)){throw new RuntimeException();}
__marker__.mark();
roops_goal_8=true;
__marker__.mark();
result=true;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
roops_goal_1=true;
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
fajita_roopsGoal_initialization();
__marker__.mark();
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
fajita_cicle_0=true;
__marker__.mark();
roops_goal_0=true;
__marker__.mark();
if(!(value_param == null && current.value == null)){throw new RuntimeException();}
__marker__.mark();
roops_goal_2=true;
__marker__.mark();
//mutID 0
equalVal=true; //              equalVal = false; //mutGenLimit 1
__marker__.mark();
if(!(equalVal == true)){throw new RuntimeException();}
__marker__.mark();
roops_goal_8=true;
__marker__.mark();
result=true;
__marker__.mark();
//mutID 1
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
roops_goal_1=true;
__marker__.mark();
//mutID 2
return result; //                return !result; //mutGenLimit 1
fajita_roopsGoal_initialization();
__marker__.mark();
current=this.header;
__marker__.mark();
result=false;
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
=======
>>>>>>> more
__marker__.mark();
=======
>>>>>>> chica pura chica pura
roops.core.objects.SinglyLinkedListNode _SinglyLinkedListNode_2 = new roops.core.objects.SinglyLinkedListNode();
roops.core.objects.BugLineMarker _BugLineMarker_1 = new roops.core.objects.BugLineMarker();
roops.core.objects.BugLineMarker _BugLineMarker_2 = new roops.core.objects.BugLineMarker();
java.lang.Object value_param_BugLineMarker_3 = new java.lang.Object();
_SinglyLinkedListNode_2.next = null;
_SinglyLinkedListNode_2.value = _BugLineMarker_1;
_SinglyLinkedListNode_1.next = _SinglyLinkedListNode_2;
_SinglyLinkedListNode_1.value = _BugLineMarker_2;
this.header = _SinglyLinkedListNode_1;
value_param = value_param_BugLineMarker_3;
=======
//-------------------- contains -------------------------//

    /** @Modifies_Everything
     * @Ensures false;
     */
    public boolean contains (  /*@nullable@*/ java.lang.Object value_param) {java.lang.Object value_param_BugLineMarker_1 = new java.lang.Object();
this.header = null;
value_param = value_param_BugLineMarker_1;
>>>>>>> lula map inverse
=======
    public boolean contains (  /*@nullable@*/ java.lang.Object value_param) {java.lang.Object value_param_Object_1 = new java.lang.Object();
this.header = null;
value_param = value_param_Object_1;
>>>>>>> working loop =)
=======
    /*@
        @ requires value_param != null;
        @ ensures (\exists SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.value==value_param)
        @     <==> (\result==true);
        @ signals (RuntimeException e) false;
        @*/
=======
>>>>>>> loop unroll, on the floor
    public boolean contains (  /*@nullable@*/ java.lang.Object value_param) {roops.core.objects.SinglyLinkedListNode _SinglyLinkedListNode_1 = new roops.core.objects.SinglyLinkedListNode();
BugLineMarker __marker__ = new BugLineMarker();
__marker__.mark();
_SinglyLinkedListNode_1.next = null;
__marker__.mark();
_SinglyLinkedListNode_1.value = instance;
__marker__.mark();
this.header = _SinglyLinkedListNode_1;
__marker__.mark();
<<<<<<< HEAD
value_param = value_param_BugLineMarker_1;
__marker__.mark();
>>>>>>> falta unroll
=======
>>>>>>> loop unroll, on the floor
fajita_roopsGoal_initialization();
__marker__.mark();
roops.core.objects.SinglyLinkedListNode current; //lineNumber=36
__marker__.mark();
BugLineMarker dummy; //lineNumber=37
__marker__.mark();
boolean result; //lineNumber=38
__marker__.mark();
current=this.header; //lineNumber=40
__marker__.mark();
result=false; //lineNumber=42
__marker__.mark();
boolean terminatesInTime=false; //lineNumber=44
__marker__.mark();
if(!(result == false && current != null)){throw new RuntimeException();}
__marker__.mark();
roops_goal_0=true; //lineNumber=46
__marker__.mark();
boolean equalVal; //lineNumber=47
__marker__.mark();
if(value_param != null && current.value == null){throw new RuntimeException();}
__marker__.mark();
roops_goal_3=true; //lineNumber=54
__marker__.mark();
if(!(value_param != null)){throw new RuntimeException();}
__marker__.mark();
roops_goal_4=true; //lineNumber=56
__marker__.mark();
if(!(value_param == current.value)){throw new RuntimeException();}
__marker__.mark();
roops_goal_6=true; //lineNumber=58
__marker__.mark();
equalVal=true; //lineNumber=59
__marker__.mark();
if(!(equalVal == true)){throw new RuntimeException();}
__marker__.mark();
roops_goal_8=true; //lineNumber=70
__marker__.mark();
result=false; //lineNumber=71
__marker__.mark();
current=current.next; //lineNumber=76
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
roops_goal_11=true; //lineNumber=115
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
roops_goal_21=true; //lineNumber=151
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
roops_goal_31=true; //lineNumber=187
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
roops_goal_41=true; //lineNumber=223
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
roops_goal_51=true; //lineNumber=259
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
roops_goal_61=true; //lineNumber=295
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
roops_goal_71=true; //lineNumber=331
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
roops_goal_81=true; //lineNumber=337
__marker__.mark();
return result; //lineNumber=340
}
<<<<<<< HEAD

	/*
	 * @
	 * 
	 * @ requires index>=0 && index<\reach(this.header, SinglyLinkedListNode, next).int_size();
	 * 
	 * @ ensures \reach(this.header, SinglyLinkedListNode, next).has(\result)==true;
	 * 
	 * @ ensures \reach(\result, SinglyLinkedListNode, next).int_size() == \reach(this.header, SinglyLinkedListNode, next).int_size()-index;
	 * 
	 * @ signals (Exception e) false;
	 * 
	 * @
	 */
	public SinglyLinkedListNode getNode(int index) {
		SinglyLinkedListNode current = header;
		SinglyLinkedListNode result = null;
		int current_index = 0;
		while (result == null && current != null) {
			if (index == current_index) {
				result = current;
			} else {
			}
			current_index = current_index + 1;
			current = current.next;
		}
		return result;
	}

	// ------------------------ insertBack --------------------------//
	// Due to jml4c the ensures clauses must be in that order :(
	/*
	 * @
	 * 
	 * @ requires freshNode!=null;
	 * 
	 * @ requires \reach(header, SinglyLinkedListNode, next).has(freshNode)==false;
	 * 
	 * @ ensures \reach(header, SinglyLinkedListNode, next).int_size()==\old(\reach(header, SinglyLinkedListNode, next)).int_size()+1;
	 * 
	 * @ ensures (\forall SinglyLinkedListNode n;
	 * 
	 * @ \old(\reach(header, SinglyLinkedListNode, next)).has(n);
	 * 
	 * @ \reach(header, SinglyLinkedListNode, next).has(n)==true
	 * 
	 * @ );
	 * 
	 * @ ensures (\exists SinglyLinkedListNode n;
	 * 
	 * @ \reach(header, SinglyLinkedListNode, next).has(n);
	 * 
	 * @ n.next==null && n.value==data);
	 * 
	 * @ signals (Exception e) false;
	 * 
	 * @
	 */
	void insertBack(java.lang.Object data, SinglyLinkedListNode freshNode) {
		freshNode.value = data;
		freshNode.next = null;
		if (this.header == null) {
			this.header = freshNode;
		} else {
			SinglyLinkedListNode current = this.header;
			while (current.next != null) {
				current = current.next;
			}
			current.next = freshNode;
		}
	}
<<<<<<< HEAD
>>>>>>> instru
=======
>>>>>>> more
=======
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
>>>>>>> como te cabe mi picadura
}
