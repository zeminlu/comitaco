// This is mutant program.
// Author : ysma

package roops.core.objects;

import roops.core.objects.SinglyLinkedListNode;
/*@ nullable_by_default @*/
public class SinglyLinkedList
{

/*@
    @ invariant (\forall SinglyLinkedListNode n; \reach(header, SinglyLinkedListNode, next).has(n); \reach(n.next, SinglyLinkedListNode, next).has(n)==false);
    @*/
    public roops.core.objects.SinglyLinkedListNode header;

    public SinglyLinkedList()
    {
    }

//----------------- showInstance --------------------//
/*@ requires \reach(this.header, SinglyLinkedListNode, next).int_size() == 100;    
    @ ensures \result == false;
    @*/
    public boolean showInstance()
    {
        return true;
    }

/*@
    @ ensures (((\exists SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.value==value_param) <==> (\result==true)));
    @ signals (RuntimeException e) true;
    @*/
    public boolean contains(  /*@nullable@*/ java.lang.Object value_param, roops.core.objects.SinglyLinkedListNode customvar_0 )
    {roops.core.objects.SinglyLinkedList instance = new roops.core.objects.SinglyLinkedList();
roops.core.objects.SinglyLinkedListNode _SinglyLinkedListNode_1 = new roops.core.objects.SinglyLinkedListNode();
roops.core.objects.SinglyLinkedListNode _SinglyLinkedListNode_2 = new roops.core.objects.SinglyLinkedListNode();
roops.core.objects.SinglyLinkedListNode _SinglyLinkedListNode_3 = new roops.core.objects.SinglyLinkedListNode();
roops.core.objects.SinglyLinkedListNode _SinglyLinkedListNode_4 = new roops.core.objects.SinglyLinkedListNode();
roops.core.objects.SinglyLinkedListNode _SinglyLinkedListNode_5 = new roops.core.objects.SinglyLinkedListNode();
roops.core.objects.SinglyLinkedListNode _SinglyLinkedListNode_6 = new roops.core.objects.SinglyLinkedListNode();
roops.core.objects.SinglyLinkedListNode _SinglyLinkedListNode_7 = new roops.core.objects.SinglyLinkedListNode();
java.lang.Object value_param_Object_1 = new java.lang.Object();
_SinglyLinkedListNode_7.next = null;
_SinglyLinkedListNode_7.value = _SinglyLinkedListNode_7;
_SinglyLinkedListNode_6.next = _SinglyLinkedListNode_7;
_SinglyLinkedListNode_6.value = _SinglyLinkedListNode_7;
_SinglyLinkedListNode_5.next = _SinglyLinkedListNode_6;
_SinglyLinkedListNode_5.value = _SinglyLinkedListNode_7;
_SinglyLinkedListNode_4.next = _SinglyLinkedListNode_5;
_SinglyLinkedListNode_4.value = _SinglyLinkedListNode_7;
_SinglyLinkedListNode_3.next = _SinglyLinkedListNode_4;
_SinglyLinkedListNode_3.value = _SinglyLinkedListNode_7;
_SinglyLinkedListNode_2.next = _SinglyLinkedListNode_3;
_SinglyLinkedListNode_2.value = _SinglyLinkedListNode_7;
_SinglyLinkedListNode_1.next = _SinglyLinkedListNode_2;
_SinglyLinkedListNode_1.value = _SinglyLinkedListNode_7;
instance.header = _SinglyLinkedListNode_1;
if (!(this.equals(instance) && value_param_Object_1.equals(value_param)))
	throw new RuntimeException();
else {roops.core.objects.SinglyLinkedListNode current;
boolean result;
//mutID 0
current=this.header.next; //mutGenLimit 1
result=false;
if(!(result == false && current != null)){throw new RuntimeException();}
boolean equalVal;
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=current.next.next; //mutGenLimit 1
if(!(result == false && current != null)){throw new RuntimeException();}
if(value_param == null && current.value == null){throw new RuntimeException();}
if(!(value_param != null)){throw new RuntimeException();}
if(value_param == current.value){throw new RuntimeException();}
equalVal=false;
//mutID 2
current=customvar_0; //mutGenLimit 1
if(result == false && current != null){throw new RuntimeException();}
//mutID 3
return result; //mutGenLimit 0
}
}

//--------------------------- getNode ----------------------------//    
/*@
    @ requires index>=0 && index<\reach(this.header, SinglyLinkedListNode, next).int_size();
    @ ensures \reach(this.header, SinglyLinkedListNode, next).has(\result)==true; 
    @ ensures \reach(\result, SinglyLinkedListNode, next).int_size() == \reach(this.header, SinglyLinkedListNode, next).int_size()-index;
    @ signals (Exception e) false;
    @*/
    public roops.core.objects.SinglyLinkedListNode getNode( int index )
    {
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
    @			 \reach(header, SinglyLinkedListNode, next).has(n)==true  
    @         );
    @ ensures (\exists SinglyLinkedListNode n; 
    @            \reach(header, SinglyLinkedListNode, next).has(n); 
    @            n.next==null && n.value==data);
    @ signals (Exception e) false;
    @*/
    void insertBack( java.lang.Object data, roops.core.objects.SinglyLinkedListNode freshNode )
    {
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
