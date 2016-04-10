package ase2016.singlylinkedlist;

/**
* SinglyLinkedListContains1Bug6 is an implementation of singly linked lists with a bug
* injected in line 6 of method contains. This bug replaces:
* while (result == false && current != null) 
* with
* while (result == false && current.next != null) 
* The bug to be inserted and the affected line were randomly chosen, from a set of
* real programming mistakes in linked list implementations. This particular one appears in:
* http://cslibrary.stanford.edu/105/LinkedListProblems.pdf
* http://giridhar-mb.blogspot.com.ar/2012/11/linked-list-implementation-in-java.html
*/

import ase2016.singlylinkedlist.SinglyLinkedListNode;

public class SinglyLinkedListContains1Bug6 {

    /*@
    @ invariant (\forall SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); \reach(n.next, SinglyLinkedListNode, next).has(n)==false);
    @*/
    public /*@nullable@*/ase2016.singlylinkedlist.SinglyLinkedListNode header;

    public SinglyLinkedListContains1Bug6() {
    }

    /*@
    @ requires true;
    @ ensures (\exists SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.value==valueParam) ==> (\result==true);
    @ ensures (\result == true) ==> (\exists SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.value==valueParam);
    @ signals (RuntimeException e) false;
    @
    @*/
    public boolean contains( /*@nullable@*/java.lang.Object valueParam ) {
        ase2016.singlylinkedlist.SinglyLinkedListNode current;
        boolean result;
        current = this.header; //mutGenLimit 0
        result = false; //mutGenLimit 0
        //@decreasing \reach(current, SinglyLinkedListNode, next).int_size();
        while (result == false && current.next != null) { //mutGenLimit 1
            boolean equalVal;
            if (valueParam == null && current.value == null) { //mutGenLimit 0
                equalVal = true; //mutGenLimit 0
            } else {
                if (valueParam != null) { //mutGenLimit 0
                    if (valueParam == current) { //mutGenLimit 1
                        equalVal = true; //mutGenLimit 0
                    } else {
                        equalVal = false; //mutGenLimit 0
                    }
                } else {
                    equalVal = false; //mutGenLimit 0
                }
            }
            if (equalVal == true) { //mutGenLimit 0
                result = true; //mutGenLimit 0
            }
            current = current.next; //mutGenLimit 0
        }
        return result; //mutGenLimit 0
    }

    /*@
    @ requires index>=0 && index<\reach(this.header, SinglyLinkedListNode, next).int_size();
    @
    @ ensures \reach(this.header, SinglyLinkedListNode, next).has(\result)==true;
    @ ensures \reach(\result, SinglyLinkedListNode, next).int_size() == \reach(this.header, SinglyLinkedListNode, next).int_size()-index;
    @ signals (RuntimeException e) false;
    @*/
    public ase2016.singlylinkedlist.SinglyLinkedListNode getNode( int index ) {
        ase2016.singlylinkedlist.SinglyLinkedListNode current = this.header; //mutGenLimit 0
        ase2016.singlylinkedlist.SinglyLinkedListNode result = null; //mutGenLimit 0
        int current_index = 0; //mutGenLimit 0
        //@decreasing \reach(current, SinglyLinkedListNode, next).int_size();
        while (result == null && current != null) { //mutGenLimit 0
            if (index == current_index) { //mutGenLimit 0
                result = current; //mutGenLimit 0
            }
            current_index = current_index + 1; //mutGenLimit 0
            current = current.next; //mutGenLimit 0
        }
        return result; //mutGenLimit 0
    }

    /*@ requires true;
    @ ensures (\exists SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.value == arg && n.next == null);
    @ ensures (\forall SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.next != null ==> \old(\reach(this.header, SinglyLinkedListNode, next)).has(n));
    @ ensures (\forall SinglyLinkedListNode n; \old(\reach(this.header, SinglyLinkedListNode, next)).has(n); \reach(this.header, SinglyLinkedListNode, next).has(n) && n.next != null);
    @ signals (Exception e) false;
    @*/
    public void insertBack( java.lang.Object arg ) {
        ase2016.singlylinkedlist.SinglyLinkedListNode freshNode = new ase2016.singlylinkedlist.SinglyLinkedListNode(); //mutGenLimit 0
        freshNode.value = arg; //mutGenLimit 0
        freshNode.next = null; //mutGenLimit 0
        if (this.header == null) { //mutGenLimit 0
            this.header = freshNode; //mutGenLimit 0
        } else {
            ase2016.singlylinkedlist.SinglyLinkedListNode current; //mutGenLimit 0
            current = this.header; //mutGenLimit 0
            //@decreasing \reach(current, SinglyLinkedListNode, next).int_size();
            while (current.next != null) { //mutGenLimit 0
                current = current.next; //mutGenLimit 0
            }
            current.next = freshNode; //mutGenLimit 0
        }
    }


    
    /* The following buggy program has been taken from
     * http://giridhar-mb.blogspot.com.ar/2012/11/linked-list-implementation-in-java.html
     * Buggy program is display, which traverses the list printing the contents
     * of each node. Here it's been made, with the same code, a countNodes
     * method, to return an output and specify the postcondition. */
    
    /*@ requires true;
    @ ensures (\result == \reach(this.header, SinglyLinkedListNode, next).int_size());
    @ signals (Exception e) false;
    @*/
    public int countNodes() {
    	  int count = 0; //mutGenLimit 0
    	  if(header == null) { //mutGenLimit 0
    		  count = 0; //mutGenLimit 0
    	  } else {
    		  ase2016.singlylinkedlist.SinglyLinkedListNode temp = header; //mutGenLimit 0
    		  //@decreasing \reach(temp, SinglyLinkedListNode, next).int_size();
    		  while(temp.getNext() != null) { //mutGenLimit 1
    			  count++; //mutGenLimit 0
    			  temp = temp.getNext(); //mutGenLimit 0
    		  }
    	  }
    	  return count; //mutGenLimit 0
    }
    
    /*@ requires n >= 0;
    @ requires n < \reach(this.header, SinglyLinkedListNode, next).int_size();
    @ ensures \old(\reach(this.header, SinglyLinkedListNode, next)).has(\result);
    @ ensures !\reach(this.header, SinglyLinkedListNode, next).has(\result);
    @ signals (Exception e) false;
    @*/
    public ase2016.singlylinkedlist.SinglyLinkedListNode removeNthFromEnd(int n) {
    	ase2016.singlylinkedlist.SinglyLinkedListNode start = new ase2016.singlylinkedlist.SinglyLinkedListNode(); //mutGenLimit 0
    	ase2016.singlylinkedlist.SinglyLinkedListNode slow = start; //mutGenLimit 0
    	ase2016.singlylinkedlist.SinglyLinkedListNode fast = start; //mutGenLimit 0
        slow.next = header; //mutGenLimit 0
        for(int i=1; i<=n+1; i++) { //mutGenLimit 0
            fast = fast.next; //mutGenLimit 0
        }
        while(fast != null) { //mutGenLimit 0
            slow = slow.next; //mutGenLimit 0
            fast = fast.next; //mutGenLimit 0
        }
        slow.next = slow.next.next; //mutGenLimit 0
        return start.next; //mutGenLimit 0
    }



}
