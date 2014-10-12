package roops.core.objects;


import roops.core.objects.SinglyLinkedListNode;
import roops.core.objects.BugLineMarker;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD

<<<<<<< HEAD
public class SinglyLinkedList {
=======
=======
>>>>>>> marke
=======

>>>>>>> markmaker
/*@ nullable_by_default @*/
public class SinglyLinkedList {

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> markmaker
=======
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

>>>>>>> static-field-not-found
/*@
    @ invariant (\forall SinglyLinkedListNode n; \reach(header, SinglyLinkedListNode, next).has(n); \reach(n.next, SinglyLinkedListNode, next).has(n)==false);
    @*/
    public SinglyLinkedListNode header;
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> executing a check instead of run & adding a singlyLinkedListTest

	/*@
      @ invariant (\forall SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); \reach(n.next, SinglyLinkedListNode, next).has(n)==false);
      @*/    public /*@nullable@*/roops.core.objects.SinglyLinkedListNode header;

    public SinglyLinkedList() {
    }

<<<<<<< HEAD
    /*@ 
      @ requires true;
      @ ensures (\exists SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.value==valueParam) ==> (\result==true);
      @ ensures (\result == true) ==> (\exists SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.value==valueParam);
      @ signals (RuntimeException e) false;
      @ 
      @*/    public boolean contains( /*@nullable@*/java.lang.Object valueParam ) {
    	  roops.core.objects.SinglyLinkedListNode current;
    	  boolean result;
    	  current = this.header.next; //mutGenLimit 1
    	  result = false;
    	  while (result == false && current != null) {
    		  boolean equalVal;
    		  if (valueParam == null && current.value == null) {
    			  equalVal = false; //mutGenLimit 1
    		  } else {
    			  if (valueParam == null) { //mutGenLimit 1
    				  if (valueParam == current) { //mutGenLimit 1
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
    		  current.next = current.next.next; //mutGenLimit 2
    	  }
    	  return result;
      }

    /*@
      @ requires index>=0 && index<\reach(this.header, SinglyLinkedListNode, next).int_size();
      @
      @ ensures \reach(this.header, SinglyLinkedListNode, next).has(\result)==true;
      @ ensures \reach(\result, SinglyLinkedListNode, next).int_size() == \reach(this.header, SinglyLinkedListNode, next).int_size()-index;
      @ signals (RuntimeException e) false;
      @*/    public roops.core.objects.SinglyLinkedListNode getNode( int index ) {
    	  roops.core.objects.SinglyLinkedListNode current = this.header;
    	  roops.core.objects.SinglyLinkedListNode result = null;
    	  int current_index = 0;
    	  while (result == null && current != null) {
    		  if (index == current_index + 1) { //mutGenLimit 1
    			  result = current; 
    		  }
    		  current_index = current_index + 1;
    		  current = current.next;
    	  }
    	  return result;
      }

    /*@ requires true;
      @ ensures (\exists SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.value == arg && n.next == null);
      @ ensures (\forall SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.next != null ==> \old(\reach(this.header, SinglyLinkedListNode, next)).has(n));
      @*/    public void insertBack( java.lang.Object arg ) {
    	  roops.core.objects.SinglyLinkedListNode freshNode = new roops.core.objects.SinglyLinkedListNode();
    	  freshNode.value = arg;
    	  freshNode.next = null;
    	  if (this.header == null) {
    		  this.header = freshNode;
    	  } else {
    		  roops.core.objects.SinglyLinkedListNode current;
    		  current = this.header;
    		  while (current.next != null) {
    			  current = current.next;
    		  }
    		  current.next = freshNode;
    	  }
      }
=======
/*@
    @ ensures (\exists SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.value==value_param) <==> (\result==true);
    @ signals (Exception e) true;
    @*/
    public boolean contains(  /*@nullable@*/ java.lang.Object value_param )
    {
        SinglyLinkedListNode current;
        boolean result;
        current = this.header;
//        current = this.header.next; //mutGenLimit 1
        result = false;
        while (result == false && current != null) {
            boolean equalVal;
            if (value_param == null && current.value == null) {
            	equalVal = true;
//                equalVal = false; //mutGenLimit 1
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
            current = current.next;
            //            current = current.next.next; //mutGenLimit 1
        }
        return result;
//                return !result; //mutGenLimit 1
    }
=======
	/*
	 * @
	 * 
	 * @ invariant (\forall SinglyLinkedListNode n; \reach(header,
	 * SinglyLinkedListNode, next).has(n); \reach(n.next, SinglyLinkedListNode,
	 * next).has(n)==false);
	 * 
	 * @
	 */
	public SinglyLinkedListNode header;
=======
>>>>>>> markmaker
=======
>>>>>>> generating weird sequential code

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
<<<<<<< HEAD
	/*
	 * @
	 * 
	 * @ ensures (\exists SinglyLinkedListNode n; \reach(this.header,
	 * SinglyLinkedListNode, next).has(n); n.value==value_param) <==>
	 * (\result==true);
	 * 
	 * @ signals (Exception e) true;
	 * 
	 * @
	 */
	public boolean contains( /* @nullable@ */java.lang.Object value_param) {
		BugLineMarker __marker__ = new BugLineMarker();
		SinglyLinkedListNode current;
		boolean result;
		current = this.header;
		// current = this.header.next; //mutGenLimit 1
		result = false;
		while (result == false && current != null) {
			boolean equalVal;
			if (value_param == null && current.value == null) {
				equalVal = true;
				// equalVal = false; //mutGenLimit 1
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
			current = current.next;
			// current = current.next.next; //mutGenLimit 1
		}
		return result;
		// return !result; //mutGenLimit 1
	}
>>>>>>> marke
=======
/*@
    @ ensures (\exists SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.value==value_param) <==> (\result==true);
    @ signals (Exception e) true;
    @*/
<<<<<<< HEAD
<<<<<<< HEAD
    public boolean contains(  /*@nullable@*/ java.lang.Object value_param ) {
BugLineMarker __marker__ = new BugLineMarker();
=======
    /** @Modifies_Everything
   * @Ensures false;
   */
    public boolean contains (  /*@nullable@*/ java.lang.Object value_param) {
        fajita_roopsGoal_initialization ();
>>>>>>> static-field-not-found
        SinglyLinkedListNode current;
        boolean result;
        current = this.header;
//        current = this.header.next; //mutGenLimit 1
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
    public boolean contains(  /*@nullable@*/ java.lang.Object value_param )
    {
        SinglyLinkedListNode current;
        boolean result;
//        current = this.header;
        current = this.header.next; //mutGenLimit 1
>>>>>>> generating weird sequential code
=======
__marker__.mark();
__marker__.mark();
__marker__.mark();
__marker__.mark();
__marker__.mark();
__marker__.mark();
__marker__.mark();
>>>>>>> faji
=======
>>>>>>> faj
        result = false;
        while (result == false && current != null) {
            boolean equalVal;
            if (value_param == null && current.value == null) {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
__marker__.mark();
__marker__.mark();
__marker__.mark();
__marker__.mark();
__marker__.mark();
__marker__.mark();
__marker__.mark();
>>>>>>> faji
=======
>>>>>>> faj
              equalVal = true;
<<<<<<< HEAD
//                equalVal = false; //mutGenLimit 1
<<<<<<< HEAD
<<<<<<< HEAD
=======
//            	equalVal = true;
                equalVal = false; //mutGenLimit 1
>>>>>>> generating weird sequential code
=======
            	equalVal = true;
//                equalVal = false; //mutGenLimit 1
>>>>>>> negate post
=======
__marker__.mark();
__marker__.mark();
__marker__.mark();
__marker__.mark();
__marker__.mark();
__marker__.mark();
__marker__.mark();
>>>>>>> faji
=======
>>>>>>> faj
=======
//              equalVal = false; //mutGenLimit 1
>>>>>>> UNSAT 0 variables con marks
            } else {
                if (value_param != null) {
                  if (value_param == current.value) {
=======
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
>>>>>>> static-field-not-found
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
<<<<<<< HEAD
            }
            if (equalVal == true) {
                result = true;
            }
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
__marker__.mark();
__marker__.mark();
__marker__.mark();
__marker__.mark();
__marker__.mark();
__marker__.mark();
__marker__.mark();
>>>>>>> faji
=======
>>>>>>> faj
            current = current.next;
            //            current = current.next.next; //mutGenLimit 1
<<<<<<< HEAD
<<<<<<< HEAD
=======
//            current = current.next;
            current = current.next.next; //mutGenLimit 1
>>>>>>> generating weird sequential code
=======
            current = current.next;
//            current = current.next.next; //mutGenLimit 1
>>>>>>> negate post
=======
__marker__.mark();
__marker__.mark();
__marker__.mark();
__marker__.mark();
__marker__.mark();
__marker__.mark();
__marker__.mark();
>>>>>>> faji
=======
>>>>>>> faj
=======
//            current = current.next;
            current = current.next.next; //mutGenLimit 1
>>>>>>> UNSAT 0 variables con marks
=======
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
>>>>>>> static-field-not-found
        }
        return result;
//                return !result; //mutGenLimit 1
    }
<<<<<<< HEAD
>>>>>>> markmaker
=======
    public boolean contains(  /*@nullable@*/ java.lang.Object value_param ) {roops.core.objects.SinglyLinkedListNode _SinglyLinkedListNode_1 = new roops.core.objects.SinglyLinkedListNode();
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
__marker__.mark();
equalVal=false;
__marker__.mark();
//mutID 1
__marker__.mark();
current=current.next.next; //mutGenLimit 1
__marker__.mark();
if(res == false && current != null){throw new RuntimeException();}
__marker__.mark();
//mutID 2
__marker__.mark();
return res; //                return !result; //mutGenLimit 1
}
>>>>>>> unsat error lines

//--------------------------- getNode ----------------------------//
=======
>>>>>>> static-field-not-found
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

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> markmaker
//------------------------ insertBack --------------------------//    
//Due to jml4c the ensures clauses must be in that order :(      
=======
//------------------------ insertBack --------------------------//
//Due to jml4c the ensures clauses must be in that order :(
>>>>>>> UNSAT 0 variables con marks
/*@
    @ requires freshNode!=null;
    @ requires \reach(header, SinglyLinkedListNode, next).has(freshNode)==false;
    @ ensures \reach(header, SinglyLinkedListNode, next).int_size()==\old(\reach(header, SinglyLinkedListNode, next)).int_size()+1;
    @ ensures (\forall SinglyLinkedListNode n;
    @            \old(\reach(header, SinglyLinkedListNode, next)).has(n);
<<<<<<< HEAD
<<<<<<< HEAD
    @			 \reach(header, SinglyLinkedListNode, next).has(n)==true  
=======
    @      \reach(header, SinglyLinkedListNode, next).has(n)==true  
>>>>>>> markmaker
=======
    @      \reach(header, SinglyLinkedListNode, next).has(n)==true
>>>>>>> UNSAT 0 variables con marks
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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> executing a check instead of run & adding a singlyLinkedListTest
=======
=======
=======

>>>>>>> como te cabe mi picadura
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

>>>>>>> instru
	// ------------------------ insertBack --------------------------//
	// Due to jml4c the ensures clauses must be in that order :(
	/*
	 * @
	 * 
	 * @ requires freshNode!=null;
	 * 
<<<<<<< HEAD
	 * @ requires \reach(header, SinglyLinkedListNode,
	 * next).has(freshNode)==false;
	 * 
	 * @ ensures \reach(header, SinglyLinkedListNode,
	 * next).int_size()==\old(\reach(header, SinglyLinkedListNode,
	 * next)).int_size()+1;
=======
	 * @ requires \reach(header, SinglyLinkedListNode, next).has(freshNode)==false;
	 * 
	 * @ ensures \reach(header, SinglyLinkedListNode, next).int_size()==\old(\reach(header, SinglyLinkedListNode, next)).int_size()+1;
>>>>>>> instru
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
<<<<<<< HEAD
			roops.core.objects.SinglyLinkedListNode current = this.header;
=======
			SinglyLinkedListNode current = this.header;
>>>>>>> instru
			while (current.next != null) {
				current = current.next;
			}
			current.next = freshNode;
		}
	}
<<<<<<< HEAD
>>>>>>> marke
=======
>>>>>>> markmaker

=======
   
>>>>>>> UNSAT 0 variables con marks
=======
>>>>>>> static-field-not-found
=======
>>>>>>> instru
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
>>>>>>> como te cabe mi picadura
}
