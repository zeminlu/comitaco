package ase2016.singlylinkedlist;


import ase2016.singlylinkedlist.SinglyLinkedListNode;


public class SinglyLinkedListContains3Bugs6x12x3 {

    /*@
    @ invariant (\forall SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); \reach(n.next, SinglyLinkedListNode, next).has(n)==false);
    @*/
    public /*@nullable@*/ase2016.singlylinkedlist.SinglyLinkedListNode header;

    public SinglyLinkedListContains3Bugs6x12x3 () {
    }

    /*@
    @ requires true;
    @ ensures (\exists SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.value==valueParam) ==> (\result==true);
    @ ensures (\result == true) ==> (\exists SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.value==valueParam);
    @ signals (RuntimeException e) false;
    @
    @*/
    public boolean contains ( /*@nullable@*/java.lang.Object valueParam, boolean customvar_0, boolean customvar_1, boolean customvar_2, boolean customvar_3) {
        ase2016.singlylinkedlist.SinglyLinkedListNode current;
        boolean result;
        current = this.header.next; //mutGenLimit 1 mutID 1
        result
         = false;
        {
            if ( result == false && current.next != this.header ) { //mutGenLimit 0 mutID 2
                boolean equalVal;
                if ( valueParam == null && current.value == null ) {
                    equalVal = true;
                } else {
                    if ( valueParam != null ) {
                        if ( customvar_0 ) { //mutGenLimit 1 mutID 3
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
            if ( result == false && current.next != this.header ) { //mutGenLimit 0 mutID 2
                boolean equalVal;
                if ( valueParam == null && current.value == null ) {
                    equalVal = true;
                } else {
                    if ( valueParam != null ) {
                        if ( customvar_1 ) { //mutGenLimit 1 mutID 3
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
            if ( result == false && current.next != this.header ) { //mutGenLimit 0 mutID 2
                boolean equalVal;
                if ( valueParam == null && current.value == null ) {
                    equalVal = true;
                } else {
                    if ( valueParam != null ) {
                        if ( customvar_2 ) { //mutGenLimit 1 mutID 3
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
            if ( result == false && current.next != this.header ) { //mutGenLimit 0 mutID 2
                boolean equalVal;
                if ( valueParam == null && current.value == null ) {
                    equalVal = true;
                } else {
                    if ( valueParam != null ) {
                        if ( customvar_3 ) { //mutGenLimit 1 mutID 3
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
    public ase2016.singlylinkedlist.SinglyLinkedListNode getNode ( int index) {
        ase2016.singlylinkedlist.SinglyLinkedListNode current = this.header;
        ase2016.singlylinkedlist.SinglyLinkedListNode result = null;
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
        ase2016.singlylinkedlist.SinglyLinkedListNode freshNode = new ase2016.singlylinkedlist.SinglyLinkedListNode ();
        freshNode.value = arg;
        freshNode.next = null;
        if ( this.header == null ) {
            this.header = freshNode;
        } else {
            ase2016.singlylinkedlist.SinglyLinkedListNode current;
            current = this.header;
            while ( current.next != null ) {
                current = current.next;
            }
            current.next = freshNode;
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
    public int countNodes () {
        int count = 0;
        if ( header == null ) {
            count = 0;
        } else {
            ase2016.singlylinkedlist.SinglyLinkedListNode temp = header;
            while ( temp.getNext () != null ) { //mutGenLimit 1
                count ++;
                temp = temp.getNext ();
            }
        }
        return count;
    }

    /*@ requires n >= 0;
    @ requires n < \reach(this.header, SinglyLinkedListNode, next).int_size();
    @ ensures \old(\reach(this.header, SinglyLinkedListNode, next)).has(\result);
    @ ensures !\reach(this.header, SinglyLinkedListNode, next).has(\result);
    @ signals (Exception e) false;
    @*/
    public ase2016.singlylinkedlist.SinglyLinkedListNode removeNthFromEnd ( int n) {
        ase2016.singlylinkedlist.SinglyLinkedListNode start = new ase2016.singlylinkedlist.SinglyLinkedListNode ();
        ase2016.singlylinkedlist.SinglyLinkedListNode slow = start;
        ase2016.singlylinkedlist.SinglyLinkedListNode fast = start;
        slow.next = header;
        for ( int i = 1; i <= n + 1; i ++ ) {
            fast = fast.next;
        }
        while ( fast != null ) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return start.next;
    }
}
