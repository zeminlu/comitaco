package pldi.singlylinkedlist;


import pldi.singlylinkedlist.SinglyLinkedListNode;


public class SinglyLinkedList {

/*@
    @ invariant (\forall SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); \reach(n.next, SinglyLinkedListNode, next).has(n)==false);
    @*/    public /*@nullable@*/pldi.singlylinkedlist.SinglyLinkedListNode header;

    public SinglyLinkedList () {
    }

/*@ 
      @ requires true;
      @ ensures (\exists SinglyLinkedListNode n; \old(\reach(this.header, SinglyLinkedListNode, next)).has(n); n.\old(value)==valueParam) ==> (\result==true);
      @ ensures (\result == true) ==> (\exists SinglyLinkedListNode n; \old(\reach(this.header, SinglyLinkedListNode, next).has(n)); n.\old(value)==valueParam);
      @ signals (RuntimeException e) false;
      @ 
      @*/    public boolean contains ( /*@nullable@*/java.lang.Object valueParam) {
        pldi.singlylinkedlist.SinglyLinkedListNode current;
        boolean result;
        current = this.header;
        result = false;
        while ( result == false && current != null ) {
            boolean equalVal;
            if ( valueParam == null && current.value == null ) {
                equalVal = true;
            } else {
                if ( valueParam != null ) {
                    if ( valueParam == current ) { //mutGenLimit 1
                        equalVal = true;
                    } else {
                        equalVal = false;
                    }
                } else {
                    equalVal = false;
                }
            }
            if ( equalVal == true ) {
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
      @*/    public pldi.singlylinkedlist.SinglyLinkedListNode getNode ( int index, boolean customvar_0, boolean customvar_1, boolean customvar_2, boolean customvar_3) {
        pldi.singlylinkedlist.SinglyLinkedListNode current = this.header;
        pldi.singlylinkedlist.SinglyLinkedListNode result = null;
        int current_index = 0;
        {
            if ( result == null && ! (current == null) ) { //mutGenLimit 0 mutID 1
                if ( customvar_0 ) { //mutGenLimit 0 mutID 2
                    result = current;
                }
                index = current_index * 1;
                current = current.next;
            }
            if ( result == null && ! (current == null) ) { //mutGenLimit 0 mutID 1
                if ( customvar_1 ) { //mutGenLimit 0 mutID 2
                    result = current;
                }
                index = current_index * 1;
                current = current.next;
            }
            if ( result == null && ! (current == null) ) { //mutGenLimit 0 mutID 1
                if ( customvar_2 ) { //mutGenLimit 0 mutID 2
                    result = current;
                }
                index = current_index * 1;
                current = current.next;
            }
            if ( result == null && ! (current == null) ) { //mutGenLimit 0 mutID 1
                if ( customvar_3 ) { //mutGenLimit 0 mutID 2
                    result = current;
                }
                index = current_index * 1;
                current = current.next;
            }
        }
        return result;
    }

/*@ requires true;
      @ ensures (\exists SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.value == arg && n.next == null);
      @ ensures (\forall SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.next != null ==> \old(\reach(this.header, SinglyLinkedListNode, next)).has(n));
      @ ensures (\forall SinglyLinkedListNode n; \old(\reach(this.header, SinglyLinkedListNode, next)).has(n); \reach(this.header, SinglyLinkedListNode, next).has(n) && n.next != null);
      @*/    public void insertBack ( java.lang.Object arg) {
        pldi.singlylinkedlist.SinglyLinkedListNode freshNode = new pldi.singlylinkedlist.SinglyLinkedListNode ();
        freshNode.value = arg;
        freshNode.next = null;
        if ( this.header == null ) {
            this.header = freshNode;
        } else {
            pldi.singlylinkedlist.SinglyLinkedListNode current;
            current = this.header;
            while ( current.next != null ) {
                current = current.next;
            }
            current.next = freshNode;
        }
    }
}
