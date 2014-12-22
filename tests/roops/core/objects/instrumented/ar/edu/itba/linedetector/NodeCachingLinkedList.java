package tests.roops.core.objects.instrumented.ar.edu.itba.linedetector;


import roops.core.objects.LinkedListNode;
import java.io.IOException;
import ar.edu.taco.utils.FileUtils;
import java.util.NoSuchElementException;


/**
 * @j2daType
 */
/*@ nullable_by_default @*/public class NodeCachingLinkedList 
{
    protected static ar.edu.taco.stryker.api.impl.ReachSet reach(Object o, Class<?> clazz, String str) {
        String fieldsToMoveThroughAsStrings[] = str.replaceAll(" ", "").split("\\+");
        ar.edu.taco.stryker.api.impl.ReachSet objectSet = new ar.edu.taco.stryker.api.impl.ReachSet();
        java.util.IdentityHashMap<Object,Object> visitedObjects = new java.util.IdentityHashMap<Object,Object>();
        java.util.Queue<Object> queue = new java.util.LinkedList<Object>();
        
		 if(o == null) {
        	return objectSet;
		 }
        visitedObjects.put(o, o);
        queue.add(o);
		 objectSet.add(o);
        while(!queue.isEmpty()) {
            Object currentObject = queue.poll();
            
            java.util.List<java.lang.reflect.Field> fieldsToMoveThrough = new java.util.ArrayList<java.lang.reflect.Field>();
            for(int i = 0; i < fieldsToMoveThroughAsStrings.length; i++) {
                    String fieldAsString = fieldsToMoveThroughAsStrings[i];
                try {
                    java.lang.reflect.Field field = currentObject.getClass().getDeclaredField(fieldAsString);
                    fieldsToMoveThrough.add(field);
                } catch (NoSuchFieldException e) {
                e.printStackTrace();
                } catch (SecurityException e) {
                     e.printStackTrace();
                }
            }
            
            for(java.lang.reflect.Field field : fieldsToMoveThrough) {
                field.setAccessible(true);
                try {
                    Object accessedObject = field.get(currentObject);
                    if(accessedObject != null && visitedObjects.containsKey(accessedObject) == false) {
                        visitedObjects.put(accessedObject, accessedObject);
                        queue.add(accessedObject);
                        if(clazz.isInstance(accessedObject)) {
                            objectSet.add(accessedObject);
                        }
                    }
                } catch (IllegalArgumentException e) {
                e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return objectSet;
    }



    public LinkedListNode header;

    public LinkedListNode firstCachedNode;

    public int maximumCacheSize;

    public int cacheSize;

    public int size;

    public int DEFAULT_MAXIMUM_CACHE_SIZE;

    public int modCount;

    public NodeCachingLinkedList () {
        this.header = new LinkedListNode ();
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
      @           (\forall LinkedListNode n; reach(this.header,LinkedListNode.class,"next").has(n); n!=null && n.previous!=null && n.previous.next==n && n.next!=null && n.next.previous==n ) && 
      @
      @           this.size + 1 == reach(this.header,LinkedListNode.class,"next").int_size() && 
      @           this.size>=0;
      @
      @ invariant (\forall LinkedListNode m; reach(this.firstCachedNode, LinkedListNode.class," next").has(m); 
      @                                   reach(m.next, LinkedListNode.class," next").has(m)==false && 
      @                                   m.previous==null
      @                                   );
      @
      @ invariant this.cacheSize <= this.maximumCacheSize;
      @
      @ invariant this.DEFAULT_MAXIMUM_CACHE_SIZE == 3;
      @
      @ invariant this.cacheSize == reach(this.firstCachedNode, LinkedListNode.class," next").int_size(); 
      @*/
    /*@
          @  requires index>=0 && index<this.size;
          @  requires this.maximumCacheSize == this.DEFAULT_MAXIMUM_CACHE_SIZE;
          @  ensures this.size == \old(this.size) - 1;
          @  ensures \old(cacheSize) < maximumCacheSize ==> cacheSize == \old(cacheSize) + 1;
          @  ensures this.modCount == \old(this.modCount) + 1;
          @  ensures (index == 0 && size > 0) ==> \result == \old(this.header.next.value);
          @  ensures (index == 1 && size > 1) ==> \result == \old(this.header.next.next.value);
          @  ensures (index == 2 && size > 2) ==> \result == \old(this.header.next.next.next.value);
          @  ensures (\forall LinkedListNode n; reach(header, LinkedListNode.class," next").has(n); \old( reach(header, LinkedListNode.class," next")).has(n)); 
          @  ensures (\exists LinkedListNode n; \old(reach(header, LinkedListNode.class," next")).has(n);  reach(header, LinkedListNode.class," next").has(n) == false); 
          @  ensures (\forall LinkedListNode n; \old(reach(firstCachedNode, LinkedListNode.class," next")).has(n);  reach(firstCachedNode, LinkedListNode.class," next").has(n)); 
          @  ensures (\forall LinkedListNode n; \old(reach(firstCachedNode, LinkedListNode.class," next")).has(n); n.previous == null); 
          @  ensures this.maximumCacheSize == this.DEFAULT_MAXIMUM_CACHE_SIZE;
          @  signals (RuntimeException e) false;
          @*/
    public /*@nullable@*/java.lang.Object remove ( final int index) {
        LinkedListNode node = null;
        if ( index < 0 ) {
            throw new java.lang.RuntimeException ();
        } else {
        }
        if ( index == size ) {
            throw new java.lang.RuntimeException ();
        } else {
        }
        if ( index > size ) {
            throw new java.lang.IndexOutOfBoundsException ();
        } else {
        }
        if ( index < size / 2 ) {
            node = header.next;
            for ( int currentIndex = 0; currentIndex < index; currentIndex ++ ) {
                node = node.next;
            }
        } else {
            node = header;
            for ( int currentIndex = size; currentIndex > index; currentIndex -- ) {
                node = node.previous;
            }
        }
        java.lang.Object oldValue;
        oldValue = node.value;
        node.previous.next = node.next;
        node.next.previous = node.previous;
        this.size = this.size - 1;
        this.modCount = this.modCount + 1;
        if ( this.cacheSize < this.maximumCacheSize ) {
            LinkedListNode nextCachedNode;
            nextCachedNode = this.firstCachedNode;
            node.previous = firstCachedNode; //mutGenLimit 1
            node
            .next = nextCachedNode;
            node.value = null;
            this.firstCachedNode = node;
            this.cacheSize = this.cacheSize - 1; //mutGenLimit 1
        } else {
        }
        return oldValue;
    }

    /*@ requires true;
      @ ensures size == \old(size) + 1;
      @ ensures modCount == \old(modCount) + 1;
      @ ensures ( \forall LinkedListNode n; \old(reach(header, LinkedListNode.class," next")).has(n);  reach(header, LinkedListNode.class," next").has(n)); 
      @ ensures ( \forall LinkedListNode n; reach(header, LinkedListNode.class," next").has(n) && n != header.next; \old( reach(header, LinkedListNode.class," next")).has(n) ); 
      @ ensures ( header.next.value == o );
      @ ensures \result == true;
      @*/
    public boolean addFirst ( java.lang.Object o) {
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"fajita_roopsGoal_initialization();\n");
		} catch (IOException ioexception) {
		}
		fajita_roopsGoal_initialization();
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"BugLineMarker __marker__=new BugLineMarker(); //lineNumber=129\n");
		} catch (IOException ioexception) {
		}
		BugLineMarker __marker__ = new BugLineMarker();
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"__marker__.mark(122); //lineNumber=130\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(122);
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"LinkedListNode newNode=new LinkedListNode(); //lineNumber=131\n");
		} catch (IOException ioexception) {
		}
		LinkedListNode newNode = new LinkedListNode();
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"__marker__.mark(123); //lineNumber=132\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(123);
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"newNode.value=o; //lineNumber=133\n");
		} catch (IOException ioexception) {
		}
		newNode.value = o;
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"__marker__.mark(124); //lineNumber=134\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(124);
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"LinkedListNode insertBeforeNode=header.next; //lineNumber=135\n");
		} catch (IOException ioexception) {
		}
		LinkedListNode insertBeforeNode = header.next;
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"__marker__.mark(125); //lineNumber=136\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(125);
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"newNode.next=insertBeforeNode; //lineNumber=137\n");
		} catch (IOException ioexception) {
		}
		newNode.next = insertBeforeNode;
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"__marker__.mark(126); //lineNumber=138\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(126);
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"newNode.previous=insertBeforeNode.previous; //lineNumber=139\n");
		} catch (IOException ioexception) {
		}
		newNode.previous = insertBeforeNode.previous;
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"__marker__.mark(127); //lineNumber=140\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(127);
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"insertBeforeNode.previous.next=newNode; //lineNumber=141\n");
		} catch (IOException ioexception) {
		}
		insertBeforeNode.previous.next = newNode;
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"__marker__.mark(128); //lineNumber=142\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(128);
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"insertBeforeNode.previous=newNode; //lineNumber=143\n");
		} catch (IOException ioexception) {
		}
		insertBeforeNode.previous = newNode;
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"__marker__.mark(129); //lineNumber=144\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(129);
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"size--; //lineNumber=145\n");
		} catch (IOException ioexception) {
		}
		size--;
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"__marker__.mark(130); //lineNumber=146\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(130);
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"modCount++; //lineNumber=147\n");
		} catch (IOException ioexception) {
		}
		modCount++;
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"__marker__.mark(131); //lineNumber=148\n");
		} catch (IOException ioexception) {
		}
		__marker__.mark(131);
        try {
			FileUtils
					.appendToFile(
							"/Users/framundo/ITBA/comitaco/tests/roops/core/objects/sequential/NodeCachingLinkedList.java_addFirst",
							"return true; //lineNumber=149\n");
		} catch (IOException ioexception) {
		}
		return true;
    }

    /*@
      @ requires true;
      @ ensures \result == true <==> (\exists LinkedListNode n; reach(header, LinkedListNode.class," next").has(n) && n != header; n.value == arg); 
      @*/
    public /*@ pure @*/boolean contains ( /*@ nullable @*/java.lang.Object arg) {
        for ( LinkedListNode node = header.next; ! (node != header.next); node = node.next ) { //mutGenLimit 0
            if ( node.value == arg ) {
                return true;
            } else {
            }
        }
        return false;
    }

    public static void fajita_roopsGoal_initialization () {}
}
