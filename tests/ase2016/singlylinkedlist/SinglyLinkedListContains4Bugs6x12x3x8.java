package ase2016.singlylinkedlist;

/**
* SinglyLinkedListContains4Bugs6x12x3 is an implementation of singly linked lists with four bugs
* injected in lines 6, 12, 3 and 8 of method contains. First bug replaces:
* while (result == false && current != null) 
* with
* while (result == false && current.next != null) 
* Second bug replaces:
* if (valueParam == current.value)
* with
* if (valueParam == current)  
* Third bug replaces:
* current = this.header
* with 
* current = this.header.next
* Fourth bug replaces:
* if (valueParam == null && current.next == null)
* with
* if (valueParam == null && current.value == null)
* The bugs to be inserted and the affected lines were randomly chosen, from a set of
* real programming mistakes in linked list implementations. These particular ones appear in:
* http://cslibrary.stanford.edu/105/LinkedListProblems.pdf
* http://giridhar-mb.blogspot.com.ar/2012/11/linked-list-implementation-in-java.html
* http://www.geeksforgeeks.org/write-a-function-to-get-the-intersection-point-of-two-linked-lists/
*/


public class SinglyLinkedListContains4Bugs6x12x3x8 {

    /*@
    @ invariant (\forall SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); \reach(n.next, SinglyLinkedListNode, next).has(n)==false);
    @*/
    public /*@nullable@*/ase2016.singlylinkedlist.SinglyLinkedListNode header;

    public SinglyLinkedListContains4Bugs6x12x3x8() {
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
        current = this.header.next; //mutGenLimit 1
        result = false; //mutGenLimit 0
        //@decreasing \reach(current, SinglyLinkedListNode, next).int_size();
        while (result == false && current.next != null) { //mutGenLimit 1
            boolean equalVal;
            if (valueParam == null && current.next == null) { //mutGenLimit 1
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

}
