package ase2016.singlylinkedlist;

/**
* SinglyLinkedListGetNode3Bugs7x12x3 is an implementation of singly linked lists with 3 bugs
* injected in lines 7 and 12 and 3 of method getNode. First bug replaces:
* result = current;
* with
* result = current.next;
* Second bug replaces:
* return result;
* with
* return current;
* Third bug replaces:
* int current_index = 0;
* with 
* int current_index = 1;
* The bugs to be inserted and the affected lines were randomly chosen, from a set of
* real programming mistakes in linked list implementations. These particular ones appear in:
* http://cslibrary.stanford.edu/105/LinkedListProblems.pdf
* https://github.com/phishman3579/java-algorithms-implementation/commit/e056b7213e8d6121e17af68e641f37cf20f829d4
* http://crunchify.com/how-to-implement-a-linkedlist-class-from-scratch-in-java/
*/

import ase2016.singlylinkedlist.SinglyLinkedListNode;

public class SinglyLinkedListGetNode3Bugs7x12x3 {

    /*@
    @ invariant (\forall SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); \reach(n.next, SinglyLinkedListNode, next).has(n)==false);
    @*/
    public /*@nullable@*/ase2016.singlylinkedlist.SinglyLinkedListNode header;

    public SinglyLinkedListGetNode3Bugs7x12x3() {
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
        int current_index = 1; //mutGenLimit 1
        //@decreasing \reach(current, SinglyLinkedListNode, next).int_size();
        while (result == null && current != null) { //mutGenLimit 0
            if (index == current_index) { //mutGenLimit 0
                result = current.next; //mutGenLimit 1
            }
            current_index = current_index + 1; //mutGenLimit 0
            current = current.next; //mutGenLimit 0
        }
        return current; //mutGenLimit 1
    }

}
