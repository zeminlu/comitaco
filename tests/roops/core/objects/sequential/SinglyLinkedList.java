<<<<<<< HEAD
<<<<<<< HEAD
package roops.core.objects;

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
import roops.core.objects.SinglyLinkedListNode;
import roops.core.objects.BugLineMarker;

/*@ nullable_by_default @*/
public class SinglyLinkedList {

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

	public static boolean roops_goal_6;

	public static boolean roops_goal_7;

	public static boolean roops_goal_8;

	public static boolean roops_goal_9;

	public static void fajita_roopsGoal_initialization() {
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

	/*
	 * @
	 * @ invariant (\forall SinglyLinkedListNode n; \reach(header, SinglyLinkedListNode, next).has(n); \reach(n.next, SinglyLinkedListNode, next).has(n)==false);
	 * @
	 */
	public SinglyLinkedListNode header;

	public SinglyLinkedList() {
	}

	// ----------------- showInstance --------------------//
	/*
	 * @ requires \reach(this.header, SinglyLinkedListNode, next).int_size() == 100;
	 * 
	 * @ ensures \result == false;
	 * 
	 * @
	 */
	public boolean showInstance() {
		return true;
	}

	/**
	 * @Modifies_Everything
	 * @Ensures false;
	 */
	public boolean contains( /* @nullable@ */java.lang.Object value_param) {java.lang.Object value_param_BugLineMarker_1 = new java.lang.Object();
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
this.header = null;
__marker__.mark();
value_param = value_param_BugLineMarker_1;
__marker__.mark();
fajita_roopsGoal_initialization();
__marker__.mark();
SinglyLinkedListNode current; //lineNumber=69
__marker__.mark();
boolean result; //lineNumber=70
__marker__.mark();
current=this.header; //lineNumber=71
__marker__.mark();
result=false; //lineNumber=72
__marker__.mark();
BugLineMarker dummy; //lineNumber=73
__marker__.mark();
boolean fajita_cicle_0=false; //lineNumber=75
__marker__.mark();
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark();
if(!(!fajita_cicle_0)){throw new RuntimeException();}
__marker__.mark();
roops_goal_1=true; //lineNumber=108return result; //lineNumber=110
}

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
}
