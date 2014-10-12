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
    public roops.core.objects.SinglyLinkedListNode header;

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
        @ ensures (\exists SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.value==value_param)
        @     <==> (\result==true);
        @ signals (RuntimeException e) false;
        @*/
    public boolean contains(  /*@nullable@*/ java.lang.Object value_param ) {
BugLineMarker __marker__ = new BugLineMarker();
__marker__.mark();
        roops.core.objects.SinglyLinkedListNode current;
__marker__.mark();
        boolean result;
__marker__.mark();
        // current = this.header.next; //mutGenLimit 1
        current = this.header;
__marker__.mark();
        result = false;
__marker__.mark();
        current = this.header.next;
__marker__.mark();

        while (result == false && current != null) {
__marker__.mark();
            boolean equalVal;
__marker__.mark();
            if (value_param == null && current.value == null) {
__marker__.mark();
                // equalVal = false; //mutGenLimit 1
                equalVal = true;
__marker__.mark();
            } else {
__marker__.mark();
                if (value_param != null) {
__marker__.mark();
                    if (value_param == current.value) {
__marker__.mark();
                        equalVal = true;
__marker__.mark();
                    } else {
__marker__.mark();
                        equalVal = false;
__marker__.mark();
                    }
__marker__.mark();
                } else {
__marker__.mark();
                    equalVal = false;
__marker__.mark();
                }
__marker__.mark();
            }
__marker__.mark();
            if (equalVal == true) {
__marker__.mark();
                result = true;
__marker__.mark();
            }
__marker__.mark();
            // current = current.next.next; //mutGenLimit 1
            current = current.next;
__marker__.mark();
        }
__marker__.mark();
        // return !result; //mutGenLimit 1
        return result;
    }

//--------------------------- getNode ----------------------------//
    /*@
        @ requires index>=0 && index<\reach(this.header, SinglyLinkedListNode, next).int_size();
        @ ensures \reach(this.header, SinglyLinkedListNode, next).has(\result)==true;
        @ ensures \reach(\result, SinglyLinkedListNode, next).int_size() == \reach(this.header, SinglyLinkedListNode, next).int_size()-index;
        @ signals (Exception e) false;
        @*/
    public roops.core.objects.SinglyLinkedListNode getNode( int index ) {
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
    void insertBack( java.lang.Object data, roops.core.objects.SinglyLinkedListNode freshNode ) {
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

}
