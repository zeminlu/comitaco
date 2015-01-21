package roops.core.objects;

import roops.core.objects.LinkedListNode;


/*@ nullable_by_default @*/
public class NodeCachingLinkedList {

    public LinkedListNode header;

    public LinkedListNode firstCachedNode;

    public int maximumCacheSize;

    public int cacheSize;

    public int size;

    public int DEFAULT_MAXIMUM_CACHE_SIZE;

    public int modCount;

    
    
    public NodeCachingLinkedList() {
        this.header = new LinkedListNode();
        this.header.next = this.header;
        this.header.previous = this.header;
        this.firstCachedNode = null;
        this.size = 0;
        this.cacheSize = 0;
        this.DEFAULT_MAXIMUM_CACHE_SIZE = 3;
        this.maximumCacheSize = 3;
        this.modCount = 0;
    }

    /*@
	  @ invariant this.header!=null &&
	  @           this.header.next!=null &&
	  @           this.header.previous!=null &&
	  @
	  @           (\forall LinkedListNode n; \reach(this.header,LinkedListNode,next).has(n);  n.previous!=null && n.previous.next==n && n.next!=null && n.next.previous==n ) &&
	  @
	  @           this.size + 1 == \reach(this.header,LinkedListNode,next).int_size() &&
	  @           this.size>=0;
	  @
	  @ invariant (\forall LinkedListNode m; \reach(this.firstCachedNode, LinkedListNode, next).has(m);
	  @                                   \reach(m.next, LinkedListNode, next).has(m)==false &&
	  @                                   m.previous==null 
	  @                                   );
	  @
	  @ invariant this.cacheSize <= this.maximumCacheSize;
	  @
	  @ invariant this.DEFAULT_MAXIMUM_CACHE_SIZE == 3;
	  @
	  @ invariant firstCachedNode != null ==> this.cacheSize == \reach(firstCachedNode, LinkedListNode, next).int_size();
	  @*/
    
    
    /*@ 
      @ requires true;
      @ ensures \result == true <==> (\exists LinkedListNode n; \reach(header, LinkedListNode, next).has(n) && n != header; n.value == arg);
      @ signals (Exception e) false;
      @*/    
      public boolean contains( /*@ nullable @*/java.lang.Object arg ) {
    	  LinkedListNode node = this.header.next; 
          while (node != this.header) { 
              if (node.value != arg) { //mutGenLimit 1
                  return true;
              }
              node = node.next; 
          }
          return false; 
      }

}
