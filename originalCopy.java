// This is mutant program.
// Author : ysma

package roops.core.objects;


import roops.core.objects.SinglyLinkedListNode;
import roops.core.objects.BugLineMarker;


/*@ nullable_by_default @*/
public class SinglyLinkedList {

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

<<<<<<< HEAD
<<<<<<< HEAD
    /** @Modifies_Everything
   * @Ensures false;
   */
    public boolean contains (  /*@nullable@*/ java.lang.Object value_param) {roops.core.objects.SinglyLinkedListNode _SinglyLinkedListNode_1 = new roops.core.objects.SinglyLinkedListNode();
roops.core.objects.SinglyLinkedListNode _SinglyLinkedListNode_2 = new roops.core.objects.SinglyLinkedListNode();
java.lang.Throwable _Throwable_1 = new java.lang.Throwable();
java.lang.Object null_0 = null;
_SinglyLinkedListNode_2.next = null;
_SinglyLinkedListNode_2.value = _Throwable_1;
_SinglyLinkedListNode_1.next = _SinglyLinkedListNode_2;
_SinglyLinkedListNode_1.value = null;
this.header = _SinglyLinkedListNode_1;
value_param = null_0;
=======
	/**
	 * @Modifies_Everything
	 * @Ensures false;
	 */
	public boolean contains( /* @nullable@ */java.lang.Object value_param) {java.lang.Object value_param_BugLineMarker_1 = new java.lang.Object();
this.header = null;
value_param = value_param_BugLineMarker_1;
<<<<<<< HEAD
>>>>>>> instru
SinglyLinkedListNode current;
boolean result;
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
boolean equalVal;
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
//mutID 0
equalVal=false; //mutGenLimit 1
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
//mutID 0
equalVal=false; //mutGenLimit 1
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
//mutID 0
equalVal=false; //mutGenLimit 1
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
//mutID 0
equalVal=false; //mutGenLimit 1
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
//mutID 0
equalVal=false; //mutGenLimit 1
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
//mutID 0
equalVal=false; //mutGenLimit 1
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
//mutID 0
equalVal=false; //mutGenLimit 1
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
//mutID 0
equalVal=false; //mutGenLimit 1
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
//mutID 0
equalVal=false; //mutGenLimit 1
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
//mutID 0
equalVal=false; //mutGenLimit 1
//mutID 1
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
//mutID 0
current=this.header.next; //mutGenLimit 1
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(!(value_param == current.value)){throw new RuntimeException();}
equalVal=true;
if(!(equalVal == true)){throw new RuntimeException();}
result=true;
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
if(!(value_param == current.value)){throw new RuntimeException();}
equalVal=true;
if(!(equalVal == true)){throw new RuntimeException();}
result=true;
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
if(!(value_param == current.value)){throw new RuntimeException();}
equalVal=true;
if(!(equalVal == true)){throw new RuntimeException();}
result=true;
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
if(!(value_param == current.value)){throw new RuntimeException();}
equalVal=true;
if(!(equalVal == true)){throw new RuntimeException();}
result=true;
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
if(!(value_param == current.value)){throw new RuntimeException();}
equalVal=true;
if(!(equalVal == true)){throw new RuntimeException();}
result=true;
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
if(!(value_param == current.value)){throw new RuntimeException();}
equalVal=true;
if(!(equalVal == true)){throw new RuntimeException();}
result=true;
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
if(!(value_param == current.value)){throw new RuntimeException();}
equalVal=true;
if(!(equalVal == true)){throw new RuntimeException();}
result=true;
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
if(!(value_param == current.value)){throw new RuntimeException();}
equalVal=true;
if(!(equalVal == true)){throw new RuntimeException();}
result=true;
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
if(!(value_param == current.value)){throw new RuntimeException();}
equalVal=true;
if(!(equalVal == true)){throw new RuntimeException();}
result=true;
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
if(!(value_param == current.value)){throw new RuntimeException();}
equalVal=true;
if(!(equalVal == true)){throw new RuntimeException();}
result=true;
//mutID 2
current=current.next; //            current = current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 3
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(value_param != null){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 2
return result; //                return !result; //mutGenLimit 1
fajita_roopsGoal_initialization();
current=this.header;
result=false;
boolean fajita_cicle_0=false;
if(!(result == false && current != null)){throw new RuntimeException();}
fajita_cicle_0=true;
roops_goal_0=true;
if(!(value_param == null && current.value == null)){throw new RuntimeException();}
roops_goal_2=true;
//mutID 0
equalVal=true; //              equalVal = false; //mutGenLimit 1
if(!(equalVal == true)){throw new RuntimeException();}
roops_goal_8=true;
result=true;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
roops_goal_1=true;
//mutID 2
return result; //                return !result; //mutGenLimit 1
fajita_roopsGoal_initialization();
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
fajita_cicle_0=true;
roops_goal_0=true;
if(!(value_param == null && current.value == null)){throw new RuntimeException();}
roops_goal_2=true;
//mutID 0
equalVal=true; //              equalVal = false; //mutGenLimit 1
if(!(equalVal == true)){throw new RuntimeException();}
roops_goal_8=true;
result=true;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
roops_goal_1=true;
//mutID 2
return result; //                return !result; //mutGenLimit 1
fajita_roopsGoal_initialization();
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
fajita_cicle_0=true;
roops_goal_0=true;
if(!(value_param == null && current.value == null)){throw new RuntimeException();}
roops_goal_2=true;
//mutID 0
equalVal=true; //              equalVal = false; //mutGenLimit 1
if(!(equalVal == true)){throw new RuntimeException();}
roops_goal_8=true;
result=true;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
roops_goal_1=true;
//mutID 2
return result; //                return !result; //mutGenLimit 1
fajita_roopsGoal_initialization();
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
fajita_cicle_0=true;
roops_goal_0=true;
if(!(value_param == null && current.value == null)){throw new RuntimeException();}
roops_goal_2=true;
//mutID 0
equalVal=true; //              equalVal = false; //mutGenLimit 1
if(!(equalVal == true)){throw new RuntimeException();}
roops_goal_8=true;
result=true;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
roops_goal_1=true;
//mutID 2
return result; //                return !result; //mutGenLimit 1
fajita_roopsGoal_initialization();
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
fajita_cicle_0=true;
roops_goal_0=true;
if(!(value_param == null && current.value == null)){throw new RuntimeException();}
roops_goal_2=true;
//mutID 0
equalVal=true; //              equalVal = false; //mutGenLimit 1
if(!(equalVal == true)){throw new RuntimeException();}
roops_goal_8=true;
result=true;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
roops_goal_1=true;
//mutID 2
return result; //                return !result; //mutGenLimit 1
fajita_roopsGoal_initialization();
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
fajita_cicle_0=true;
roops_goal_0=true;
if(!(value_param == null && current.value == null)){throw new RuntimeException();}
roops_goal_2=true;
//mutID 0
equalVal=true; //              equalVal = false; //mutGenLimit 1
if(!(equalVal == true)){throw new RuntimeException();}
roops_goal_8=true;
result=true;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
roops_goal_1=true;
//mutID 2
return result; //                return !result; //mutGenLimit 1
fajita_roopsGoal_initialization();
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
fajita_cicle_0=true;
roops_goal_0=true;
if(!(value_param == null && current.value == null)){throw new RuntimeException();}
roops_goal_2=true;
//mutID 0
equalVal=true; //              equalVal = false; //mutGenLimit 1
if(!(equalVal == true)){throw new RuntimeException();}
roops_goal_8=true;
result=true;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
roops_goal_1=true;
//mutID 2
return result; //                return !result; //mutGenLimit 1
fajita_roopsGoal_initialization();
current=this.header;
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
fajita_cicle_0=true;
roops_goal_0=true;
if(!(value_param == null && current.value == null)){throw new RuntimeException();}
roops_goal_2=true;
//mutID 0
equalVal=true; //              equalVal = false; //mutGenLimit 1
if(!(equalVal == true)){throw new RuntimeException();}
roops_goal_8=true;
result=true;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
roops_goal_1=true;
//mutID 2
return result; //                return !result; //mutGenLimit 1
=======
>>>>>>> more
=======
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
<<<<<<< HEAD
    /*@
        @ requires value_param != null;
        @ ensures (\exists SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.value==value_param)
        @     <==> (\result==true);
        @ signals (RuntimeException e) false;
        @*/
<<<<<<< HEAD
    public boolean contains(  /*@nullable@*/ java.lang.Object value_param ) {
        roops.core.objects.SinglyLinkedListNode current;
        boolean result;
        // current = this.header.next; //mutGenLimit 1
        current = this.header;
        result = false;
        // current = this.header.next;

        while (result == false && current != null) {
            boolean equalVal;
            if (value_param == null && current.value == null) {
                // equalVal = false; //mutGenLimit 1
                equalVal = true;
            } else {
                if (value_param != null) {
                    if (value_param == current.value) {
                        equalVal = true;
                    } else {
                        equalVal = false;
                    }
                } else {
                    equalVal = false;
                }
            }
            if (equalVal == true) {
                result = true;
            }
            // current = current.next.next; //mutGenLimit 1
            current = current.next;
        }
        return !result; //mutGenLimit 1
        // return result;
    }

<<<<<<< HEAD
    /** @Modifies_Everything
     * @Ensures false;
     */
    public boolean contains (  /*@nullable@*/ java.lang.Object value_param) {roops.core.objects.SinglyLinkedListNode _SinglyLinkedListNode_1 = new roops.core.objects.SinglyLinkedListNode();
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
>>>>>>> como te cabe mi picadura
fajita_roopsGoal_initialization();
BugLineMarker __marker__=new BugLineMarker(); //lineNumber=33
__marker__.mark(); //lineNumber=34
roops.core.objects.SinglyLinkedListNode current; //lineNumber=35
__marker__.mark(); //lineNumber=36
boolean result; //lineNumber=37
__marker__.mark(); //lineNumber=38
current=this.header; //lineNumber=40
__marker__.mark(); //lineNumber=42
result=false; //lineNumber=43
__marker__.mark(); //lineNumber=44
current=this.header.next; //lineNumber=45
__marker__.mark(); //lineNumber=46
boolean fajita_cicle_0=false; //lineNumber=48
if(!(result == false && current != null)){throw new RuntimeException();}
fajita_cicle_0=true; //lineNumber=51
roops_goal_0=true; //lineNumber=52
__marker__.mark(); //lineNumber=53
boolean equalVal; //lineNumber=54
__marker__.mark(); //lineNumber=55
if(value_param == null && current.value == null){throw new RuntimeException();}
roops_goal_3=true; //lineNumber=64
__marker__.mark(); //lineNumber=65
if(!(value_param != null)){throw new RuntimeException();}
roops_goal_4=true; //lineNumber=67
__marker__.mark(); //lineNumber=68
if(value_param == current.value){throw new RuntimeException();}
roops_goal_7=true; //lineNumber=75
__marker__.mark(); //lineNumber=76
equalVal=false; //lineNumber=77
__marker__.mark(); //lineNumber=78
__marker__.mark(); //lineNumber=80
__marker__.mark(); //lineNumber=87
__marker__.mark(); //lineNumber=89
if(equalVal == true){throw new RuntimeException();}
roops_goal_9=true; //lineNumber=96
__marker__.mark(); //lineNumber=98
current=current.next; //lineNumber=100
__marker__.mark(); //lineNumber=102
if(result == false && current != null){throw new RuntimeException();}
__marker__.mark(); //lineNumber=108
return result; //lineNumber=110
}
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
/*@
    @ ensures (\exists SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.value==value_param) <==> (\result==true);
    @ signals (Exception e) true;
    @*/
    public boolean contains(  /*@nullable@*/ java.lang.Object value_param ) {roops.core.objects.SinglyLinkedListNode _SinglyLinkedListNode_1 = new roops.core.objects.SinglyLinkedListNode();
roops.core.objects.SinglyLinkedListNode _SinglyLinkedListNode_2 = new roops.core.objects.SinglyLinkedListNode();
roops.core.objects.BugLineMarker value_param_BugLineMarker_1 = new roops.core.objects.BugLineMarker();
java.lang.Exception _Exception_1 = new java.lang.Exception();
_SinglyLinkedListNode_2.next = null;
_SinglyLinkedListNode_2.value = value_param_BugLineMarker_1;
_SinglyLinkedListNode_1.next = _SinglyLinkedListNode_2;
_SinglyLinkedListNode_1.value = _Exception_1;
this.header = _SinglyLinkedListNode_1;
value_param = value_param_BugLineMarker_1;
BugLineMarker dummy=new BugLineMarker();
SinglyLinkedListNode current;
boolean res;
current=this.header;
res=false;
if(!(res == false && current != null)){throw new RuntimeException();}
boolean equalVal;
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 1
current=current.next.next; //mutGenLimit 1
if(res == false && current != null){throw new RuntimeException();}
//mutID 2
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
    public SinglyLinkedListNode getNode( int index )
    {
        roops.core.objects.SinglyLinkedListNode current = header;
        roops.core.objects.SinglyLinkedListNode result = null;
=======
    public SinglyLinkedListNode getNode ( int index)
     {
        SinglyLinkedListNode current = header;
        SinglyLinkedListNode result = null;
>>>>>>> static-field-not-found
=======
=======
//--------------------------- getNode ----------------------------//
>>>>>>> chica pura chica pura
=======
=======

//-------------------- contains -------------------------//

    /** @Modifies_Everything
     * @Ensures false;
     */
>>>>>>> loop unroll, on the floor
    public boolean contains (  /*@nullable@*/ java.lang.Object value_param) {roops.core.objects.SinglyLinkedListNode _SinglyLinkedListNode_1 = new roops.core.objects.SinglyLinkedListNode();
_SinglyLinkedListNode_1.next = null;
_SinglyLinkedListNode_1.value = instance;
this.header = _SinglyLinkedListNode_1;
fajita_roopsGoal_initialization();
roops.core.objects.SinglyLinkedListNode current; //lineNumber=36
BugLineMarker dummy; //lineNumber=37
boolean result; //lineNumber=38
current=this.header; //lineNumber=40
result=false; //lineNumber=42
boolean terminatesInTime=false; //lineNumber=44
if(!(result == false && current != null)){throw new RuntimeException();}
roops_goal_0=true; //lineNumber=46
boolean equalVal; //lineNumber=47
if(value_param != null && current.value == null){throw new RuntimeException();}
roops_goal_3=true; //lineNumber=54
if(!(value_param != null)){throw new RuntimeException();}
roops_goal_4=true; //lineNumber=56
if(!(value_param == current.value)){throw new RuntimeException();}
roops_goal_6=true; //lineNumber=58
equalVal=true; //lineNumber=59
if(!(equalVal == true)){throw new RuntimeException();}
roops_goal_8=true; //lineNumber=70
result=false; //lineNumber=71
current=current.next; //lineNumber=76
if(result == false && current != null){throw new RuntimeException();}
roops_goal_11=true; //lineNumber=115
if(result == false && current != null){throw new RuntimeException();}
roops_goal_21=true; //lineNumber=151
if(result == false && current != null){throw new RuntimeException();}
roops_goal_31=true; //lineNumber=187
if(result == false && current != null){throw new RuntimeException();}
roops_goal_41=true; //lineNumber=223
if(result == false && current != null){throw new RuntimeException();}
roops_goal_51=true; //lineNumber=259
if(result == false && current != null){throw new RuntimeException();}
roops_goal_61=true; //lineNumber=295
if(result == false && current != null){throw new RuntimeException();}
roops_goal_71=true; //lineNumber=331
if(result == false && current != null){throw new RuntimeException();}
roops_goal_81=true; //lineNumber=337
return result; //lineNumber=340
}
>>>>>>> falta unroll
    /*@
        @ requires index>=0 && index<\reach(this.header, SinglyLinkedListNode, next).int_size();
        @ ensures \reach(this.header, SinglyLinkedListNode, next).has(\result)==true;
        @ ensures \reach(\result, SinglyLinkedListNode, next).int_size() == \reach(this.header, SinglyLinkedListNode, next).int_size()-index;
        @ signals (Exception e) false;
        @*/
    public roops.core.objects.SinglyLinkedListNode getNode ( int index) {
        roops.core.objects.SinglyLinkedListNode current = header;
        roops.core.objects.SinglyLinkedListNode result = null;
>>>>>>> como te cabe mi picadura
        int current_index = 0;
<<<<<<< HEAD
        while ( result == null && current != null ) {
            if ( index == current_index ) {
                result = current;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
            } else {
>>>>>>> falta unroll
            }
            else
            {}
=======
=======
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
>>>>>>> loop unroll, on the floor
            } else {
=======
>>>>>>> chica pura chica pura
            }
<<<<<<< HEAD
>>>>>>> como te cabe mi picadura
            current_index = current_index + 1;
            current = current.next;
=======
>>>>>>> loop unroll, on the floor
        }
        return result;
    }

//------------------------ insertBack --------------------------//
//Due to jml4c the ensures clauses must be in that order :(
<<<<<<< HEAD
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
=======
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
<<<<<<< HEAD
    void insertBack ( java.lang.Object data, roops.core.objects.SinglyLinkedListNode freshNode) {
>>>>>>> como te cabe mi picadura
=======
    void insertBack( java.lang.Object data, roops.core.objects.SinglyLinkedListNode freshNode ) {
>>>>>>> chica pura chica pura
=======
    void insertBack ( java.lang.Object data, roops.core.objects.SinglyLinkedListNode freshNode) {
>>>>>>> falta unroll
        freshNode.value = data;
        freshNode.next = null;
        if ( this.header == null ) {
            this.header = freshNode;
        } else {
<<<<<<< HEAD
<<<<<<< HEAD
            roops.core.objects.SinglyLinkedListNode current = this.header;
<<<<<<< HEAD
            while (current.next != null) {
<<<<<<< HEAD
=======
            SinglyLinkedListNode current = this.header;
            while ( current.next != null ) {
>>>>>>> static-field-not-found
=======
            roops.core.objects.SinglyLinkedListNode current = this.header;
<<<<<<< HEAD
            while ( current.next != null ) {
>>>>>>> como te cabe mi picadura
=======
>>>>>>> chica pura chica pura
=======
            while ( current.next != null ) {
>>>>>>> falta unroll
                current = current.next;
=======
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
>>>>>>> loop unroll, on the floor
            }
            current.next = freshNode;
        }
    }
<<<<<<< HEAD
<<<<<<< HEAD
=======
   
>>>>>>> unsat error lines
=======
=======
>>>>>>> more

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

<<<<<<< HEAD
    public static boolean roops_goal_0;

    public static boolean roops_goal_1;

<<<<<<< HEAD
=======
    public static boolean roops_goal_0;

    public static boolean roops_goal_1;

>>>>>>> falta unroll
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
<<<<<<< HEAD
>>>>>>> como te cabe mi picadura
=======
>>>>>>> chica pura chica pura
=======
>>>>>>> falta unroll
}
