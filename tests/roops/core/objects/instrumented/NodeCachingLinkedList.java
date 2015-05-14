package roops.core.objects;


import roops.core.objects.LinkedListNode;
import roops.core.objects.BugLineMarker;
import java.io.IOException;
import ar.edu.taco.utils.FileUtils;
import java.util.NoSuchElementException;


/**
 * @j2daType
 */
/*@ nullable_by_default @*/public class NodeCachingLinkedList {

    public roops.core.objects.LinkedListNode header;

    public roops.core.objects.LinkedListNode firstCachedNode;

    public int maximumCacheSize;

    public int cacheSize;

    public int size;

    public int DEFAULT_MAXIMUM_CACHE_SIZE;

    public int modCount;

    public NodeCachingLinkedList () {
        this.header = new roops.core.objects.LinkedListNode ();
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
      @           (\forall LinkedListNode n; \reach(this.header,LinkedListNode,next).has(n); n!=null && n.previous!=null && n.previous.next==n && n.next!=null && n.next.previous==n ) &&
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
      @ invariant this.maximumCacheSize == this.DEFAULT_MAXIMUM_CACHE_SIZE;
      @
      @ invariant this.DEFAULT_MAXIMUM_CACHE_SIZE == 3;
      @
      @ invariant this.cacheSize == \reach(this.firstCachedNode, LinkedListNode, next).int_size();
      @*/



    /*@
     @  requires index>=0 && index<this.size;
     @  ensures this.size == \old(this.size) - 1;
     @  ensures \old(cacheSize) < maximumCacheSize ==> cacheSize == \old(cacheSize) + 1;
     @  ensures this.modCount == \old(this.modCount) + 1;
     @  ensures (index == 0 && size >= 0) ==> \result == \old(this.header.next.value);
     @  ensures (index == 1 && size >= 1) ==> \result == \old(this.header.next.next.value);
     @  ensures (index == 2 && size >= 2) ==> \result == \old(this.header.next.next.next.value);
     @  ensures (index == 3 && size >= 3) ==> \result == \old(this.header.next.next.next.next.value);
     @  ensures (\forall LinkedListNode n; \reach(header, LinkedListNode, next).has(n); \old(\reach(header, LinkedListNode, next)).has(n));
     @  ensures (\exists LinkedListNode n; \old(\reach(header, LinkedListNode, next)).has(n); \reach(header, LinkedListNode, next).has(n) == false);
     @  ensures (\forall LinkedListNode n; \old(\reach(firstCachedNode, LinkedListNode, next)).has(n); \reach(firstCachedNode, LinkedListNode, next).has(n));
     @  signals (RuntimeException e) false;
     @*/
    public /*@nullable@*/java.lang.Object remove ( final int index) {
        LinkedListNode node = null;
        if ( index < 0 ) {
            throw new java.lang.RuntimeException ();
        } else {
        }
        if ( index == this.size ) {
            throw new java.lang.RuntimeException ();
        } else {
        }
        if ( index > this.size ) {
            throw new java.lang.IndexOutOfBoundsException ();
        } else {
        }
        if ( index < this.size / 2 ) {
            node = this.header.next;
            int currentIndex = 0;
            while ( currentIndex < index ) {
                node = node.next;
                currentIndex ++;
            }
        } else {
            node = this.header;
            int currentIndex = this.size;
            while ( currentIndex > index ) {
                node = node.previous;
                currentIndex --;
            }
        }
        java.lang.Object oldValue;
        oldValue = node.value;
        node.previous.next = node.next;
        node.next.previous = node.previous;
        this.size = this.size - 1;
        this.modCount = this.modCount + 1;
        if ( this.cacheSize < this.maximumCacheSize ) {
            roops.core.objects.LinkedListNode nextCachedNode;
            nextCachedNode = this.firstCachedNode;
            node.previous = null;
            node.next = nextCachedNode;
            node.value = null;
            this.firstCachedNode = node;
            this.cacheSize = this.cacheSize + 1;
        } else {
        }
        return oldValue;
    }

    /*@ requires true;
    @ ensures size == \old(size) + 1;
    @ ensures modCount == \old(modCount) + 1;
    @ ensures ( \forall LinkedListNode n; \old(\reach(header, LinkedListNode, next)).has(n); \reach(header, LinkedListNode, next).has(n));
    @ ensures ( \forall LinkedListNode n; \reach(header, LinkedListNode, next).has(n) && n != header.next; \old(\reach(header, LinkedListNode, next)).has(n) );
    @ ensures ( header.next.value == o );
    @ ensures \result == true;
    @*/
    public boolean addFirst ( java.lang.Object o) {
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"fajita_roopsGoal_initialization();\n");
		} catch (IOException ioexception) {
		}
		fajita_roopsGoal_initialization();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"BugLineMarker __marker__=new BugLineMarker(); //lineNumber=136\n");
		} catch (IOException ioexception) {
		}
		BugLineMarker __marker__ = new BugLineMarker();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"__marker__.mark(131); //lineNumber=137\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(131);
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"roops.core.objects.LinkedListNode newNode=new roops.core.objects.LinkedListNode(); //lineNumber=138\n");
		} catch (IOException ioexception) {
		}
		roops.core.objects.LinkedListNode newNode = new roops.core.objects.LinkedListNode();
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"__marker__.mark(132); //lineNumber=139\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(132);
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"newNode.value=o; //lineNumber=140\n");
		} catch (IOException ioexception) {
		}
		newNode.value = o;
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"__marker__.mark(133); //lineNumber=141\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(133);
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"roops.core.objects.LinkedListNode insertBeforeNode=header.next; //lineNumber=142\n");
		} catch (IOException ioexception) {
		}
		roops.core.objects.LinkedListNode insertBeforeNode = header.next;
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"__marker__.mark(134); //lineNumber=143\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(134);
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"newNode.next=insertBeforeNode; //lineNumber=144\n");
		} catch (IOException ioexception) {
		}
		newNode.next = insertBeforeNode;
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"__marker__.mark(135); //lineNumber=145\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(135);
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"newNode.previous=insertBeforeNode.previous; //lineNumber=146\n");
		} catch (IOException ioexception) {
		}
		newNode.previous = insertBeforeNode.previous;
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"__marker__.mark(136); //lineNumber=147\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(136);
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"insertBeforeNode.previous.next=newNode; //lineNumber=148\n");
		} catch (IOException ioexception) {
		}
		insertBeforeNode.previous.next = newNode;
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"__marker__.mark(137); //lineNumber=149\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(137);
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"insertBeforeNode.previous=newNode; //lineNumber=150\n");
		} catch (IOException ioexception) {
		}
		insertBeforeNode.previous = newNode;
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"__marker__.mark(138); //lineNumber=151\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(138);
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"size++; //lineNumber=152\n");
		} catch (IOException ioexception) {
		}
		size++;
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"__marker__.mark(139); //lineNumber=153\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(139);
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"modCount++; //lineNumber=154\n");
		} catch (IOException ioexception) {
		}
		modCount++;
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"__marker__.mark(140); //lineNumber=155\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(140);
        try {
			FileUtils
					.appendToFile(
							"/Users/santi/Documents/Doctorado/conco/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"return false; //lineNumber=156\n");
		} catch (IOException ioexception) {
		}
		return false;
    }


    /*@
      @ requires true;
      @ ensures \result == true <==> (\exists LinkedListNode n; \reach(header, LinkedListNode, next).has(n) && n != header; n.value == arg);
      @ ensures \old(\reach(header, LinkedListNode, next)) == \reach(header, LinkedListNode, next);
      @*/
    public /*@ pure @*/boolean contains ( /*@ nullable @*/java.lang.Object arg) {
        roops.core.objects.LinkedListNode node = header.next;
        int counter = 0;
        while ( node != header && node.value != arg ) {
            node = node.next;
            counter ++;
        }
        if ( node != header && node.value == arg ) {
            return true;
        } else {
        }
        return false;
    }

    public static void fajita_roopsGoal_initialization () {}
}
