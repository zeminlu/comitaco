package roops.core.objects;


import roops.core.objects.SinglyLinkedListNode;


public class SinglyLinkedList
{
    
    /*@
    @ invariant (\forall SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); \reach(n.next, SinglyLinkedListNode, next).has(n)==false);
    @*/


    public /*@nullable@*/roops.core.objects.SinglyLinkedListNode header;

    public SinglyLinkedList()
    {
    }

    /*@ 
      @ requires true;
      @ ensures (\exists SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.value==valueParam) ==> (\result==true);
      @ ensures (\result == true) ==> (\exists SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.value==valueParam);
      @ signals (RuntimeException e) false;
      @ 
      @*/    
    public boolean contains( /*@nullable@*/java.lang.Object valueParam ) {
        roops.core.objects.SinglyLinkedListNode current;
        boolean result;
        current = this.header.next; //mutGenLimit 1
        result = false;
        while (result == false && current != null) {
            boolean equalVal;
            if (valueParam == null && current.value == null) {
                equalVal = false; //mutGenLimit 1
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
            current = current.next.next; //mutGenLimit 1
        }
        return result;
    }

/*@
      @ requires index>=0 && index<\reach(this.header, SinglyLinkedListNode, next).int_size();
      @
      @ ensures \reach(this.header, SinglyLinkedListNode, next).has(\result)==true; 
      @ ensures \reach(\result, SinglyLinkedListNode, next).int_size() == \reach(this.header, SinglyLinkedListNode, next).int_size()-index;
      @ signals (RuntimeException e) false;
      @*/    public roops.core.objects.SinglyLinkedListNode getNode( int index )
    {
        roops.core.objects.SinglyLinkedListNode current = header;
        roops.core.objects.SinglyLinkedListNode result = null;
        int current_index = 0;
        while (result == null && current != null) {
            if (index == current_index) {
                result = current;
            }
            current_index = current_index + 1;
            current.next = current; //mutGenLimit 2
        }
        return result;
    }

    public void insertBack( java.lang.Object arg )
    {
        roops.core.objects.SinglyLinkedListNode freshNode = new roops.core.objects.SinglyLinkedListNode();
        freshNode.value = arg;
        freshNode.next = null;
        if (this.header == null) {
            this.header = freshNode;
        } else {
            roops.core.objects.SinglyLinkedListNode current;
            current = this.header;
            while (current.next != null) {
                current = current.next;
            }
            current.next = freshNode;
        }
    }

}
