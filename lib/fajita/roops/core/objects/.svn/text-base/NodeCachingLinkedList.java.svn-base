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
import roops.util.RoopsArray; @roops.util.BenchmarkClass
/**
 * @Invariant 
 *		( this.header!=null ) &&
 *		( this.header.next!=null ) &&
 *		( this.header.previous!=null ) &&
 *		( this.size==#(this.header.*next @- null)-1 ) &&
 *		( this.size>=0 ) &&
 *		( this.cacheSize <= this.maximumCacheSize ) &&
 *		( this.DEFAULT_MAXIMUM_CACHE_SIZE == 20 ) &&
 *		( this.cacheSize == #(this.firstCachedNode.*next @- null) ) &&
 *		(all m: LinkedListNode | ( m in this.firstCachedNode.*next @- null ) => (
 *				  m !in m.next.*next @- null &&
 *				  m.previous==null &&
 *				  m.object_value==null )) &&
 *		(all n: LinkedListNode | ( n in this.header.*next @- null ) => (
 *				  n!=null &&
 *				  n.previous!=null &&
 *				  n.previous.next==n &&
 *				  n.next!=null &&
 *				  n.next.previous==n )) ; 
 *
 */
public class NodeCachingLinkedList {

	@roops.util.NrOfGoals(4)
	@roops.util.BenchmarkMethod static
	public void addLastTest(NodeCachingLinkedList list, Object o) {
		
		if (list!=null && list.repOK()) {
		  boolean ret_val = list.addLast(o);
		}
	}

	@roops.util.NrOfGoals(4)
	@roops.util.BenchmarkMethod static
	public void containsTest(NodeCachingLinkedList list, Object arg) {
		
		if (list!=null && list.repOK()) {
		  boolean ret_val = list.contains(arg);
		}
	}

	@roops.util.NrOfGoals(11)
	@roops.util.BenchmarkMethod static
	public void removeIndexTest(NodeCachingLinkedList list, int index) {
		
		if (list!=null && list.repOK()) {
		  list.removeIndex(index);
		}
	}

	@roops.util.NrOfGoals(5)
	@roops.util.BenchmarkMethod static
	public void setMaximumCacheSizeTest(NodeCachingLinkedList list, int new_maximumCacheSize) {
		
		if (list!=null && list.repOK()) {
		  list.setMaximumCacheSize(new_maximumCacheSize);
		}
	}
	
	public /*@ nullable @*/ LinkedListNode header;
	public int size;
	public int modCount;
	public int DEFAULT_MAXIMUM_CACHE_SIZE;
	public /*@ nullable @*/ LinkedListNode firstCachedNode;
	public int cacheSize;
	public int maximumCacheSize;

	//-----------------------------------------------------------------------

	/**
	 * Checks whether the cache is full.
	 * 
	 * @return true if the cache is full
	 */
	private boolean isCacheFull() {
		//return cacheSize >= maximumCacheSize; 
		return cacheSize > maximumCacheSize; //<- BUG SEEDED
	}

	

	private void addNodeToCache(LinkedListNode node) {
		if (isCacheFull()) {
			{roops.util.Goals.reached(7, roops.util.Verdict.REACHABLE);} // UNREACHABLE due to bug-seeded
			// don't cache the node.
			return;
		}
                
		// clear the node's contents and add it to the cache.
		LinkedListNode nextCachedNode = firstCachedNode;
		node.previous = null;
		node.next = nextCachedNode;
		node.object_value = null;
		firstCachedNode = node;
		int t = cacheSize;
		t=t+1;
		cacheSize=t;
		{roops.util.Goals.reached(8, roops.util.Verdict.REACHABLE);}
	}

	/**
	 * Removes the specified node from the list.
	 *
	 * @param node  the node to remove
	 * @throws NullPointerException if <code>node</code> is null
	 */
	private void super_removeNode(LinkedListNode node) {
		node.previous.next = node.next;
		node.next.previous = node.previous;
		int t = size;
		t = t -1;
		size=t;
		t = modCount;
		t = t +1;
		modCount=t;
	}

	

	private void removeNode(LinkedListNode node) {
		super_removeNode(node);
		addNodeToCache(node);
	}

	//-----------------------------------------------------------------------

	/**
	 * @Modifies_Everything
	 */
	public Object removeIndex(int index) {

		LinkedListNode node = getNode(index, false);
		Object oldValue = node.object_value;
		removeNode(node);

		if (maximumCacheSize==DEFAULT_MAXIMUM_CACHE_SIZE) {
			{roops.util.Goals.reached(9, roops.util.Verdict.REACHABLE);}

			if (cacheSize>maximumCacheSize) {
				{roops.util.Goals.reached(10, roops.util.Verdict.REACHABLE);}
			}
		} 
		return oldValue;
	}

	//-----------------------------------------------------------------------
	private LinkedListNode getNode(int index, boolean endMarkerAllowed)
			throws RuntimeException {
		// Check the index is within the bounds
		if (index < 0) {
			{roops.util.Goals.reached(0, roops.util.Verdict.REACHABLE);}

			throw new RuntimeException();
		}
		if (!endMarkerAllowed && index == size) {
			{roops.util.Goals.reached(1, roops.util.Verdict.REACHABLE);}

			throw new RuntimeException();
		}
		if (index > size) {
			{roops.util.Goals.reached(2, roops.util.Verdict.REACHABLE);}

			throw new IndexOutOfBoundsException();
		}
		// Search the list and get the node
		LinkedListNode node;
		int size_div_2 = size/2;
		
		if (index < size_div_2) {
			{roops.util.Goals.reached(3, roops.util.Verdict.REACHABLE);}

			// Search forwards
			node = header.next;
			for (int currentIndex = 0; currentIndex < index; currentIndex++) {
				{roops.util.Goals.reached(4, roops.util.Verdict.REACHABLE);}
				node = node.next;
			}
		} else {
			{roops.util.Goals.reached(5, roops.util.Verdict.REACHABLE);}

			// Search backwards
			node = header;
			for (int currentIndex = size; currentIndex > index; currentIndex--) {
				{roops.util.Goals.reached(6, roops.util.Verdict.REACHABLE);}
				node = node.previous;
			}
		}
		return node;
	}
	//-----------------------------------------------------------------------

	private LinkedListNode getNodeFromCache_addLast() {
		if (cacheSize==0){ 
			return null;
		}
		LinkedListNode cachedNode = firstCachedNode;
		firstCachedNode = cachedNode.next;
		cachedNode.next = null;
		cacheSize--;
		return cachedNode;
	}
	
	private LinkedListNode super_createNode(Object new_value) {
		LinkedListNode ret = new LinkedListNode();
		ret.object_value = new_value;
		ret.next = ret;
		ret.previous = ret;
		return ret;
	}
	
	private LinkedListNode createNode(Object new_value) {
		LinkedListNode cachedNode = getNodeFromCache_addLast();
		if (cachedNode==null) {
			
			{roops.util.Goals.reached(0, roops.util.Verdict.REACHABLE);}
			return super_createNode(new_value);
		}
		
		{roops.util.Goals.reached(1, roops.util.Verdict.REACHABLE);}
		cachedNode.object_value = new_value;
		return cachedNode;
	}
	
	
	public boolean addLast(Object o) {
		addNodeBefore(header, o);
		
		{roops.util.Goals.reached(3, roops.util.Verdict.REACHABLE);}
		return true;
	}
	
	private void addNodeBefore(LinkedListNode node, Object new_value) {
		LinkedListNode newNode = createNode(new_value);
		addNode(newNode, node);
	}
	
	private void addNode(LinkedListNode nodeToInsert,
			LinkedListNode insertBeforeNode) {
		nodeToInsert.next = insertBeforeNode;
		nodeToInsert.previous = insertBeforeNode.previous;
		insertBeforeNode.previous.next = nodeToInsert;
		insertBeforeNode.previous = nodeToInsert;
		int t = size;
		t = t+1;
		size=t;
		t = modCount;
		t = t+1;
		modCount=t;
		
		{roops.util.Goals.reached(2, roops.util.Verdict.REACHABLE);}
	}



	public boolean contains(Object arg) {
		return indexOf(arg) != -1;
	}

	
	private int indexOf(Object new_value) {
		int i = 0;
		for (LinkedListNode node = header.next; node != header; node = node.next) {
			
		  {roops.util.Goals.reached(0, roops.util.Verdict.REACHABLE);}	
		  if (isEqualValue(node.object_value, new_value)) {
			  
			{roops.util.Goals.reached(1, roops.util.Verdict.REACHABLE);}  
		    return i;
		  }
		  
		  {roops.util.Goals.reached(2, roops.util.Verdict.REACHABLE);}
	      i++;
		}
		
		{roops.util.Goals.reached(3, roops.util.Verdict.REACHABLE);}
		return -1;
	} 
	
	
	private boolean isEqualValue(Object value1, Object value2) {
		boolean ret_val;
		if (value1 == value2) {
			ret_val=true;
		} else {
			if (value1==null) {
				ret_val = false;
			} else {
				ret_val = value1.equals(value2);
			}
		}
		return ret_val;
	}
	
	
	public void setMaximumCacheSize(int new_maximumCacheSize) {
		this.maximumCacheSize = new_maximumCacheSize;
		shrinkCacheToMaximumSize();
	}
	
	private void shrinkCacheToMaximumSize() {
		LinkedListNode node;
		if (cacheSize<=maximumCacheSize) {
			{roops.util.Goals.reached(0, roops.util.Verdict.REACHABLE);}
			return;
		}
		
		while (cacheSize>maximumCacheSize) {
			
			{roops.util.Goals.reached(1, roops.util.Verdict.REACHABLE);}
			node = getNodeFromCache_shrinkCacheToMaximumSize();
		}
		
		{roops.util.Goals.reached(2, roops.util.Verdict.REACHABLE);}
	}
	
	private LinkedListNode getNodeFromCache_shrinkCacheToMaximumSize() {
		LinkedListNode result;
		if (cacheSize==0){ 
	          {roops.util.Goals.reached(3, roops.util.Verdict.UNREACHABLE);}
		  result = null;
		} else {
		  {roops.util.Goals.reached(4, roops.util.Verdict.REACHABLE);}
		  LinkedListNode cachedNode = firstCachedNode;
		  firstCachedNode = cachedNode.next;
		  cachedNode.next = null;
		  int t = cacheSize;
		  t = t -1;
		  cacheSize=t;
		  result = cachedNode;
		}
		return result;
	}
	
	public NodeCachingLinkedList() {
          header = new LinkedListNode();
          header.next = header;
          header.previous = header; 
          DEFAULT_MAXIMUM_CACHE_SIZE = 20;
          maximumCacheSize = DEFAULT_MAXIMUM_CACHE_SIZE;
        }

   //*************************************************************************
   //************** From now on repOK()  *************************************
   //*************************************************************************

    public boolean repOK()
    {

        if (this.header == null)
          return false;

        if (this.header.next == null)
          return false;

        if (this.header.previous == null)
          return false;

        if (this.cacheSize > this.maximumCacheSize)
          return false;

        if (this.DEFAULT_MAXIMUM_CACHE_SIZE != 20)
          return false;

        if (this.size < 0)
          return false;

        int cyclicSize = 0;

        LinkedListNode n = this.header;
        do
        {
            cyclicSize++;

            if (n.previous == null)
              return false;

            if (n.previous.next != n)
              return false;

            if (n.next == null)
              return false;

            if (n.next.previous != n)
              return false;

            if (n != null) {
                n = n.next;
            }
        } while (n != this.header && n != null);

        if (n == null)
          return false;

        if (this.size != cyclicSize - 1)
          return false;

        int acyclicSize = 0;
        LinkedListNode m = this.firstCachedNode;

        RoopsSet visited = new RoopsSet();
        visited.add(this.firstCachedNode);

        while (m != null)
        {
            acyclicSize++;

            if (m.previous != null)
              return false;

            if (m.object_value != null)
              return false;

            m = m.next;

            if (!visited.add(m))
              return false;

        }

        if (this.cacheSize != acyclicSize) {
          return false;
        }

        return true;
    }


}
/*$endcategory roops.core.objects */

