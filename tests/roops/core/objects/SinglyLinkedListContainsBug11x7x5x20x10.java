package roops.core.objects;


import roops.core.objects.SinglyLinkedListNode;


public class SinglyLinkedListContainsBug11x7x5x20x10 {

/*@
    @ invariant (\forall SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); \reach(n.next, SinglyLinkedListNode, next).has(n)==false);
    @*/    public /*@nullable@*/roops.core.objects.SinglyLinkedListNode header;

    public SinglyLinkedListContainsBug11x7x5x20x10() {
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
    	  current = this.header;
    	  result = false;
    	  while (result == (current == null) && current != null) { //mutGenLimit 1
    		  boolean equalVal;
    		  if (valueParam == null && current.value == current) { //mutGenLimit 1
    			  equalVal = true; 
    		  } else {
    			  if (valueParam != current) { //mutGenLimit 1
    				  if (valueParam != current) { //mutGenLimit 1
    					  equalVal = true;
    				  } else {
    					  equalVal = false;
    				  }
    			  } else {
    				  equalVal = false;
    			  }
    		  }
    		  if (equalVal == (equalVal == result)) { //mutGenLimit 1
    			  result = true;
    		  }
    		  current = current.next;
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
        while (result == null && current != null) { //mutGenLimit 2
            if (index == current_index) { //mutGenLimit 2
                result = current; //mutGenLimit 2
            }
            current_index = current_index + 1; //mutGenLimit 2
            current = current.next; //mutGenLimit 2
        }
        return result;
    }

/*@ requires true;
      @ ensures (\exists SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.value == arg && n.next == null);
      @ ensures (\forall SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.next != null ==> \old(\reach(this.header, SinglyLinkedListNode, next)).has(n));
      @*/    public void insertBack( java.lang.Object arg ) {
        roops.core.objects.SinglyLinkedListNode freshNode = new roops.core.objects.SinglyLinkedListNode();
        freshNode.value = arg; //mutGenLimit 2
        freshNode.next = null; //mutGenLimit 2
        if (this.header == null) {
            this.header = freshNode;
        } else {
        	roops.core.objects.SinglyLinkedListNode current;
            current = this.header; //mutGenLimit 2
            while (current.next != null) { //mutGenLimit 2
                current = current.next;
            }
            current.next = freshNode; //mutGenLimit 2
        }
    }

}
