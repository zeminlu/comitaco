package icse.singlylinkedlist.set2;


import icse.singlylinkedlist.SinglyLinkedListNode;


/**
* SinglyLinkedListInsertBackBug9x12Ix8I
*
*/
public class SinglyLinkedListContains4Bug7Dx10Dx11Dx20D {

    /*@
    @ invariant (\forall SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); \reach(n.next, SinglyLinkedListNode, next).has(n)==false);
    @*/
    public /*@nullable@*/icse.singlylinkedlist.SinglyLinkedListNode header;

    public SinglyLinkedListContains4Bug7Dx10Dx11Dx20D () {
    }

    /*@
    @ requires true;
    @ ensures (\exists SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.value==valueParam) ==> (\result==true);
    @ ensures (\result == true) ==> (\exists SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.value==valueParam);
    @ signals (RuntimeException e) false;
    @
    @*/
    public boolean contains ( /*@nullable@*/java.lang.Object valueParam, boolean customvar_0, boolean customvar_1, boolean customvar_2, boolean customvar_3) {
        icse.singlylinkedlist.SinglyLinkedListNode current;
        boolean result;
        current = this.header;
        result = false;
        {
            if ( result == false && current != null ) {
                boolean equalVal;
                if ( valueParam == null && current.value != null ) { //mutGenLimit 1 mutID 1
                    equalVal = true;
                } else {
                    if ( valueParam == current ) { //mutGenLimit 0 mutID 2
                        if ( current == current ) { //mutGenLimit 0 mutID 3
                            equalVal = true;
                        } else {
                            equalVal = false;
                        }
                    } else {
                        equalVal = false;
                    }
                }
                if ( customvar_0 ) { //mutGenLimit 0 mutID 4
                    result = true;
                }
                current = current.next;
            }
            if ( result == false && current != null ) {
                boolean equalVal;
                if ( valueParam == null && current.value != null ) { //mutGenLimit 1 mutID 1
                    equalVal = true;
                } else {
                    if ( valueParam == current ) { //mutGenLimit 0 mutID 2
                        if ( current == current ) { //mutGenLimit 0 mutID 3
                            equalVal = true;
                        } else {
                            equalVal = false;
                        }
                    } else {
                        equalVal = false;
                    }
                }
                if ( customvar_1 ) { //mutGenLimit 0 mutID 4
                    result = true;
                }
                current = current.next;
            }
            if ( result == false && current != null ) {
                boolean equalVal;
                if ( valueParam == null && current.value != null ) { //mutGenLimit 1 mutID 1
                    equalVal = true;
                } else {
                    if ( valueParam == current ) { //mutGenLimit 0 mutID 2
                        if ( current == current ) { //mutGenLimit 0 mutID 3
                            equalVal = true;
                        } else {
                            equalVal = false;
                        }
                    } else {
                        equalVal = false;
                    }
                }
                if ( customvar_2 ) { //mutGenLimit 0 mutID 4
                    result = true;
                }
                current = current.next;
            }
            if ( result == false && current != null ) {
                boolean equalVal;
                if ( valueParam == null && current.value != null ) { //mutGenLimit 1 mutID 1
                    equalVal = true;
                } else {
                    if ( valueParam == current ) { //mutGenLimit 0 mutID 2
                        if ( current == current ) { //mutGenLimit 0 mutID 3
                            equalVal = true;
                        } else {
                            equalVal = false;
                        }
                    } else {
                        equalVal = false;
                    }
                }
                if ( customvar_3 ) { //mutGenLimit 0 mutID 4
                    result = true;
                }
                current = current.next;
            }
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
    public icse.singlylinkedlist.SinglyLinkedListNode getNode ( int index) {
        icse.singlylinkedlist.SinglyLinkedListNode current = this.header;
        icse.singlylinkedlist.SinglyLinkedListNode result = null;
        int current_index = 0;
        while ( result == null && current != null ) {
            if ( index == current_index ) {
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
    @ ensures (\forall SinglyLinkedListNode n; \old(\reach(this.header, SinglyLinkedListNode, next)).has(n); \reach(this.header, SinglyLinkedListNode, next).has(n) && n.next != null);
    @ signals (Exception e) false;
    @*/
    public void insertBack ( java.lang.Object arg) {
        icse.singlylinkedlist.SinglyLinkedListNode freshNode = new icse.singlylinkedlist.SinglyLinkedListNode ();
        freshNode.value = arg;
        freshNode.next = null;
        if ( this.header == null ) {
            this.header = freshNode;
        } else {
            icse.singlylinkedlist.SinglyLinkedListNode current;
            current = this.header;
            while ( current.next != null ) {
                current = current.next;
            }
            current.next = freshNode;
        }
    }
}
