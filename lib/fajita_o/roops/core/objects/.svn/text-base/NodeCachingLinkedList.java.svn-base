/****************************************************************************
Author: Juan Pablo Galeotti and Marcelo Frias, Relational Formal Methods 
Group, University of Buenos Aires and Buenos Aires Institute of Technology,
Buenos Aires, Argentina.

ROOPS class implementing the apache.commons.collections class NodeCachingLinkedList.
It uses an auxiliary class LinkedListNode. Method removeIndex has been modified by
adding a goal that requires the cache list to be full to be covered. This means that
22 nodes are required in the cache part of the structure. 
A bug has been seeded in method isCacheFull. The bug allows to remove a node from the
NodeCachingLinkedList using method removeIndex and end up with an overflown cache. 
This state is captured by goal 10. The input NodeCachingLinkedList must have 23 nodes
in its cache linked list.

The class has annotations in JFSL [1] given as ROOPS comments. In particular, a class
invariant is provided.

[1] http://sdg.csail.mit.edu/forge/plugin.html
****************************************************************************/


package roops.core.objects;

import roops.core.objects.LinkedListNode;


/**
 * @j2daType
 */
/*@ nullable_by_default @*/
public class NodeCachingLinkedList {

	public NodeCachingLinkedList(){
		this.header = new LinkedListNode();
		this.header.next = this.header;
		this.header.previous = this.header;
		this.firstCachedNode = null;
		this.size = 0;
		this.cacheSize = 0;
		this.DEFAULT_MAXIMUM_CACHE_SIZE = 3;
		this.maximumCacheSize = 3;
		this.modCount = 0;
		this.node = null;
		this.node1 = null;
	}


	/**
	 * @j2daField
	 */
	public LinkedListNode header;

	/**
	 * @j2daField
	 */
	public LinkedListNode firstCachedNode;

	/**
	 * @j2daField
	 */
	private LinkedListNode node;
	
	/**
	 * @j2daField
	 */
	private IndexOutOfBoundsException exception;
	
	/**
	 * @j2daField
	 */
	private LinkedListNode node1;

	/**
	 * @j2daField
	 */
	private Object oldValue;
	
	/**
	 * @j2daField
	 */
	private LinkedListNode nextCachedNode;
	/**
	 * @j2daField
	 */
	public int maximumCacheSize;

	/**
	 * @j2daField
	 */
	public int cacheSize;

	/**
	 * @j2daField
	 */
	public int size;

	/**
	 * @j2daField
	 */
	public int DEFAULT_MAXIMUM_CACHE_SIZE;

	/**
	 * @j2daField
	 */
	public int modCount;
	
	



	
    private /*@helper@*/ boolean lt( int i, int j )
    {
        return i < j;
    }

    private /*@helper@*/ boolean gt( int i, int j )
    {
        return i > j;
    }

    private /*@helper@*/ int dec( int i )
    {
        return i - 1;
    }

    private /*@helper@*/int inc( int i )
    {
        return i + 1;
    }

    private /*@helper@*/ int div( int a, int b )
    {
        return a / b;
    }

    private /*@helper@*/ boolean eq( int a, int b )
    {
        return a == b;
    }
    
//    private boolean neq( boolean a, boolean b )
//    {
//        return a != b;
//    }


//    private int add( int a, int b )
//    {
//        return a + b;
//    }

    
    
    private /*@helper@*/ void assignNode(LinkedListNode n){
    	this.node = n;
    }

    private /*@helper@*/ void assignNode1(LinkedListNode n){
    	this.node1 = n;
    }

    
    /*@
	  @ public invariant this.header!=null &&
	  @           (\forall LinkedListNode n; \reach(this.header,LinkedListNode,next).has(n)==true; 
	  @					n.previous!=null && n.previous.next==n && n.next!=null && n.next.previous==n ) &&
	  @           this.size == \reach(this.header,LinkedListNode,next).int_size() - 1;
	  @
	  @ public invariant (\forall LinkedListNode n; 
	  @          \reach(this.header, LinkedListNode, next).has(n)==true; 
	  @          n!=this.header ==> n.value!=null); 
	  @
	  @ public invariant (\forall LinkedListNode m; \reach(this.firstCachedNode, LinkedListNode, next).has(m)==true;
	  @                                   \reach(m.next, LinkedListNode, next).has(m)==false &&
	  @                                   m.previous==null 
	  @                                   );
	  @
	  @ public invariant this.cacheSize <= this.maximumCacheSize;
	  @	  
	  @ public invariant this.maximumCacheSize == this.DEFAULT_MAXIMUM_CACHE_SIZE;
	  @
	  @ public invariant this.header.value == null;
	  @
	  @ public invariant this.DEFAULT_MAXIMUM_CACHE_SIZE == 3;
	  @
	  @ public invariant this.cacheSize == \reach(this.firstCachedNode, LinkedListNode, next).int_size();
	  @*/
    
/*@
	  @  requires index>=0;
	  @  requires index<this.size;
	  @  ensures this.size == \old(this.size) - 1;
	  @  ensures (\old(this.cacheSize) < this.maximumCacheSize) ==> (this.cacheSize == \old(this.cacheSize) + 1);
	  @  ensures this.modCount == \old(this.modCount) + 1;
	  @  ensures (index==0 ==> \result==\old(this.header.next.value));
	  @  ensures (index==1 ==> \result==\old(this.header.next.next.value));
	  @  ensures (index==2 ==> \result==\old(this.header.next.next.next.value));
	  @  ensures (index==3 ==> \result==\old(this.header.next.next.next.next.value));
	  @  ensures (index==4 ==> \result==\old(this.header.next.next.next.next.next.value));
	  @  ensures (index==5 ==> \result==\old(this.header.next.next.next.next.next.next.value));
	  @  ensures (index==6 ==> \result==\old(this.header.next.next.next.next.next.next.next.value));
	  @  signals (Exception) false;
	  @*/
    
	/**
	 * @j2daMethod
	 */
	public Object removeOk(int index) {
		assignNode(null);
		exception = null;
		if (lt(index,0)) {
			exception = new IndexOutOfBoundsException();
			throw exception;
		}
		if (eq(index,this.size)) {
			exception = new IndexOutOfBoundsException();
			throw exception;
		}
		if (gt(index,this.size)) {
			exception = new IndexOutOfBoundsException();
			throw exception;
		}

		assignNode1(null);
		if (lt(index,div(this.size,2))) {
			assignNode1(this.header.next);
			int currentIndex;
			currentIndex = 0;
			while (lt(currentIndex,index)) {
				assignNode1(this.node1.next);
				currentIndex = inc(currentIndex);
			}

		} else {
			assignNode1(this.header);
			int currentIndex = this.size;
			while (gt(currentIndex,index)) {
//ACA es donde hace fruta				
				LinkedListNode n = this.node1.previous;
				assignNode1(n);
				currentIndex = dec(currentIndex);
			}
		}
		
//BUG: debe ser this.node = this.node1;
		this.node = this.node1.next;

		oldValue = node.value;	
		node.previous.next = node.next; 
		node.next.previous = node.previous;		
		this.size = this.size - 1;
		this.modCount = this.modCount + 1;
		if (this.cacheSize < this.maximumCacheSize) {
			nextCachedNode = this.firstCachedNode;
			node.previous = null;
			node.next = nextCachedNode;
			node.value = null;
			this.firstCachedNode = node;
			this.cacheSize = this.cacheSize + 1;
		}
		

		return oldValue;
	}
	
	
	
	 

	  
}
