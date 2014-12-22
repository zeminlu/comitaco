// This is mutant program.
// Author : ysma

package roops.core.objects;


import roops.core.objects.SinglyLinkedListNode;
import roops.core.objects.BugLineMarker;


/*@ nullable_by_default @*/
public class SinglyLinkedList {

    /*@
        @ invariant (\forall SinglyLinkedListNode n; \reach(header, SinglyLinkedListNode, next).has(n); \reach(n.next, SinglyLinkedListNode, next).has(n)==false);
        @*/
    public SinglyLinkedListNode header;

    public SinglyLinkedList() {
    }

//----------------- showInstance --------------------//
    /*@ requires \reach(this.header, SinglyLinkedListNode, next).int_size() == 100;
        @ ensures \result == false;
        @*/
    public boolean showInstance() {
        return true;
    }

//-------------------- contains -------------------------//
/*@
    @ ensures (\exists SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.value==valueParam)
    @     <==> (\result==true);
    @ signals (RuntimeException e) false;
    @*/
    public boolean contains(  /*@nullable@*/ java.lang.Object valueParam ) {
        SinglyLinkedListNode current;
        boolean result;
        current = this.header;
        result = false;
        while (result == false && current != null) {
            boolean equalVal;
            if (valueParam == null && current.value == null) {
                equalVal = true;
            } else {
                if (valueParam != null) {
                    if (valueParam == current.value) {
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
            current = current.next.next;
        }
        return result;
    }

//--------------------------- getNode ----------------------------//
    /*@
        @ requires index>=0 && index<\reach(this.header, SinglyLinkedListNode, next).int_size();
        @ ensures \reach(this.header, SinglyLinkedListNode, next).has(\result)==true;
        @ ensures \reach(\result, SinglyLinkedListNode, next).int_size() == \reach(this.header, SinglyLinkedListNode, next).int_size()-index;
        @ signals (Exception e) false;
        @*/
    public SinglyLinkedListNode getNode( int index ) {
        SinglyLinkedListNode current = header;
        SinglyLinkedListNode result = null;
        int current_index = 0;
        while (result == null && current != null) {
            if (index == current_index) {
                result = current;
            }
            current_index = current_index + 1; // + 2
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
    void insertBack( java.lang.Object data, SinglyLinkedListNode freshNode ) {
        freshNode.value = data;
        freshNode.next = null;
        if (this.header == null) {
            this.header = freshNode;
        } else {
            SinglyLinkedListNode current = this.header;
            while (current.next != null) {
                current = current.next;
            }
            current.next = freshNode;
        }
    }

}
