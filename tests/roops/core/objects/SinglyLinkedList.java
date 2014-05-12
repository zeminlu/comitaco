package roops.core.objects;


import roops.core.objects.SinglyLinkedListNode;

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
public class SinglyLinkedList
{

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> markmaker
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

    public SinglyLinkedList()
    {
    }

//----------------- showInstance --------------------//
/*@ requires \reach(this.header, SinglyLinkedListNode, next).int_size() == 100;    
    @ ensures \result == false;
    @*/
    public boolean showInstance()
    {
        return true;
    }

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
    public boolean contains(  /*@nullable@*/ java.lang.Object value_param ) {
        SinglyLinkedListNode current;
        boolean result;
        current = this.header;
//        current = this.header.next; //mutGenLimit 1
=======
    public boolean contains(  /*@nullable@*/ java.lang.Object value_param )
    {
        SinglyLinkedListNode current;
        boolean result;
//        current = this.header;
        current = this.header.next; //mutGenLimit 1
>>>>>>> generating weird sequential code
        result = false;
        while (result == false && current != null) {
            boolean equalVal;
            if (value_param == null && current.value == null) {
<<<<<<< HEAD
              equalVal = true;
//                equalVal = false; //mutGenLimit 1
=======
//            	equalVal = true;
                equalVal = false; //mutGenLimit 1
>>>>>>> generating weird sequential code
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
<<<<<<< HEAD
            current = current.next;
            //            current = current.next.next; //mutGenLimit 1
=======
//            current = current.next;
            current = current.next.next; //mutGenLimit 1
>>>>>>> generating weird sequential code
        }
        return result;
//                return !result; //mutGenLimit 1
    }
>>>>>>> markmaker

//--------------------------- getNode ----------------------------//    
/*@
    @ requires index>=0 && index<\reach(this.header, SinglyLinkedListNode, next).int_size();
    @ ensures \reach(this.header, SinglyLinkedListNode, next).has(\result)==true; 
    @ ensures \reach(\result, SinglyLinkedListNode, next).int_size() == \reach(this.header, SinglyLinkedListNode, next).int_size()-index;
    @ signals (Exception e) false;
    @*/
    public SinglyLinkedListNode getNode( int index )
    {
        roops.core.objects.SinglyLinkedListNode current = header;
        roops.core.objects.SinglyLinkedListNode result = null;
        int current_index = 0;
        while (result == null && current != null) {
            if (index == current_index) {
                result = current;
            }
            current_index = current_index + 1;
            current = current.next;
        }
        return result;
    }

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> markmaker
//------------------------ insertBack --------------------------//    
//Due to jml4c the ensures clauses must be in that order :(      
/*@
    @ requires freshNode!=null;
    @ requires \reach(header, SinglyLinkedListNode, next).has(freshNode)==false; 
    @ ensures \reach(header, SinglyLinkedListNode, next).int_size()==\old(\reach(header, SinglyLinkedListNode, next)).int_size()+1;
    @ ensures (\forall SinglyLinkedListNode n; 
    @            \old(\reach(header, SinglyLinkedListNode, next)).has(n);
<<<<<<< HEAD
    @			 \reach(header, SinglyLinkedListNode, next).has(n)==true  
=======
    @      \reach(header, SinglyLinkedListNode, next).has(n)==true  
>>>>>>> markmaker
    @         );
    @ ensures (\exists SinglyLinkedListNode n; 
    @            \reach(header, SinglyLinkedListNode, next).has(n); 
    @            n.next==null && n.value==data);
    @ signals (Exception e) false;
    @*/
    void insertBack( java.lang.Object data, SinglyLinkedListNode freshNode )
    {
        freshNode.value = data;
        freshNode.next = null;
        if (this.header == null) {
            this.header = freshNode;
        } else {
            roops.core.objects.SinglyLinkedListNode current = this.header;
            while (current.next != null) {
                current = current.next;
            }
            current.next = freshNode;
        }
    }
<<<<<<< HEAD
>>>>>>> executing a check instead of run & adding a singlyLinkedListTest
=======
	// ------------------------ insertBack --------------------------//
	// Due to jml4c the ensures clauses must be in that order :(
	/*
	 * @
	 * 
	 * @ requires freshNode!=null;
	 * 
	 * @ requires \reach(header, SinglyLinkedListNode,
	 * next).has(freshNode)==false;
	 * 
	 * @ ensures \reach(header, SinglyLinkedListNode,
	 * next).int_size()==\old(\reach(header, SinglyLinkedListNode,
	 * next)).int_size()+1;
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
			roops.core.objects.SinglyLinkedListNode current = this.header;
			while (current.next != null) {
				current = current.next;
			}
			current.next = freshNode;
		}
	}
>>>>>>> marke
=======
>>>>>>> markmaker

}
