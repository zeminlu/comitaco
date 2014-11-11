package pldi.singlylinkedlist;


import pldi.singlylinkedlist.SinglyLinkedListNode;


public class SinglyLinkedList {

	/*@
    @ invariant (\forall SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); \reach(n.next, SinglyLinkedListNode, next).has(n)==false);
    @*/    

	public /*@nullable@*/SinglyLinkedListNode header;

	public SinglyLinkedList() {
	}

	/*@ 
      @ requires true;
      @ ensures (\exists SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.value==valueParam) ==> (\result==true);
      @ ensures (\result == true) ==> (\exists SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.value==valueParam);
      @ signals (RuntimeException e) false;
      @ 
      @*/    public boolean contains( /*@nullable@*/java.lang.Object valueParam ) {
    	  SinglyLinkedListNode current;
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
      @*/    

      public SinglyLinkedListNode getNode( int index ) {
    	  SinglyLinkedListNode current = this.header;
    	  SinglyLinkedListNode result = null;
    	  int current_index = 0;
    	  while (result == null && current == null) { //mutGenLimit 1
    		  if (index < current_index) { //mutGenLimit 1
    			  current.next = current; //mutGenLimit 1
    		  }
    		  current_index = current_index * 1; //mutGenLimit 1
    		  current.next = current.next; //mutGenLimit 1
    	  }
    	  return result;   
      }

      /*@ requires true;
      @ ensures (\exists SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.value == arg && n.next == null);
      @ ensures (\forall SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.next != null ==> \old(\reach(this.header, SinglyLinkedListNode, next)).has(n));
      @*/    public void insertBack( java.lang.Object arg ) {
    	  SinglyLinkedListNode freshNode = new SinglyLinkedListNode();
    	  freshNode.value = arg;
    	  freshNode.next = null;
    	  if (this.header == null) {
    		  this.header = freshNode;
    	  } else {
    		  SinglyLinkedListNode current;
    		  current = this.header;
    		  while (current.next != null) {
    			  current = current.next;
    		  }
    		  current.next = freshNode;
    	  }
      }

}
