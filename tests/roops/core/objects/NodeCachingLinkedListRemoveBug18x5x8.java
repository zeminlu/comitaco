package roops.core.objects;

import roops.core.objects.LinkedListNode;
import roops.core.objects.BugLineMarker;
/**
 * @j2daType
 */
/*@ nullable_by_default @*/public class NodeCachingLinkedListRemoveBug18x5x8 {

    public roops.core.objects.LinkedListNode header;

    public roops.core.objects.LinkedListNode firstCachedNode;

    public int maximumCacheSize;

    public int cacheSize;

    public int size;

    public int DEFAULT_MAXIMUM_CACHE_SIZE;

    public int modCount;

    public NodeCachingLinkedListRemoveBug18x5x8 () {
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
      @ invariant this.DEFAULT_MAXIMUM_CACHE_SIZE == 3;
      @
      @ invariant this.cacheSize == \reach(this.firstCachedNode, LinkedListNode, next).int_size();
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
    	  @  ensures (\forall LinkedListNode n; \reach(header, LinkedListNode, next).has(n); \old(\reach(header, LinkedListNode, next)).has(n));
    	  @  ensures (\exists LinkedListNode n; \old(\reach(header, LinkedListNode, next)).has(n); \reach(header, LinkedListNode, next).has(n) == false);
    	  @  ensures (\forall LinkedListNode n; \old(\reach(firstCachedNode, LinkedListNode, next)).has(n); \reach(firstCachedNode, LinkedListNode, next).has(n));
    	  @  ensures (\forall LinkedListNode n; \old(\reach(firstCachedNode, LinkedListNode, next)).has(n); n.previous == null);
    	  @  ensures this.maximumCacheSize == this.DEFAULT_MAXIMUM_CACHE_SIZE;
    	  @  signals (Exception e) false;
    	  @*/
    public /*@nullable@*/java.lang.Object remove ( final int index) {
        LinkedListNode node = null;
        if ( index < 0 ) {
            throw new java.lang.RuntimeException ();
        } else {
        }
        if ( index < this.size ) { //mutGenLimit 1
            throw new java.lang.RuntimeException ();
        } else {
        }
        if ( index < this.size ) { //mutGenLimit 1
            throw new java.lang.IndexOutOfBoundsException ();
        } else {
        }
        boolean terminatesInTime = false;
        if ( index < this.size / 2 ) {
            node = this.header.next;
            int currentIndex = 0;
            if ( currentIndex < index ) {
                node = node.next;
                currentIndex ++;
            } else {
            }
            if ( currentIndex < index ) {
                node = node.next;
                currentIndex ++;
            } else {
            }
            if ( currentIndex < index ) {
                node = node.next;
                currentIndex ++;
            } else {
            }
            if ( currentIndex < index ) {
                node = node.next;
                currentIndex ++;
            } else {
            }
            if ( currentIndex < index ) {
                node = node.next;
                currentIndex ++;
            } else {
            }
            if ( currentIndex < index ) {
                terminatesInTime = true;
            } else {
            }
        } else {
            node = this.header;
            int currentIndex = this.size;
            if ( currentIndex > index ) {
                node = node.previous;
                //It was currentIndex++;
                currentIndex
                    = currentIndex + 1; //mutGenLimit 1
            } else {
            }
            if ( currentIndex > index ) {
                node = node.previous;
                //It was currentIndex++;
                currentIndex
                    = currentIndex + 1; //mutGenLimit 1
            } else {
            }
            if ( currentIndex > index ) {
                node = node.previous;
                //It was currentIndex++;
                currentIndex
                    = currentIndex + 1; //mutGenLimit 1
            } else {
            }
            if ( currentIndex > index ) {
                node = node.previous;
                //It was currentIndex++;
                currentIndex
                    = currentIndex + 1; //mutGenLimit 1
            } else {
            }
            if ( currentIndex > index ) {
                node = node.previous;
                //It was currentIndex++;
                currentIndex
                    = currentIndex + 1; //mutGenLimit 1
            } else {
            }
            if ( currentIndex > index ) {
                terminatesInTime = true;
            } else {
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
        LinkedListNode newNode = new LinkedListNode (); //mutGenLimit 0
        newNode
        .value = o; //mutGenLimit 0
        LinkedListNode insertBeforeNode = this.header.next; //mutGenLimit 0
        newNode
        .next = insertBeforeNode; //mutGenLimit 0
        newNode
        .previous = insertBeforeNode.previous; //mutGenLimit 0
        insertBeforeNode
        .previous.next = newNode; //mutGenLimit 0
        insertBeforeNode
        .previous = newNode; //mutGenLimit 0
        this
        .size ++; //mutGenLimit 0
        this
        .modCount ++; //mutGenLimit 0
        return true; //mutGenLimit 0
    }

    /*@
    @ requires true;
    @ ensures \result == true <==> (\exists LinkedListNode n; \reach(header, LinkedListNode, next).has(n) && n != header; n.value == arg);
    @*/
    public boolean contains ( /*@ nullable @*/java.lang.Object arg) {fajita_roopsGoal_initialization();
BugLineMarker __marker__=new BugLineMarker(); //lineNumber=215
__marker__.mark(142); //lineNumber=216

LinkedListNode node=this.header; //mutGenLimit 0 //lineNumber=217
__marker__.mark(143); //lineNumber=218
boolean terminatesInTime=false; //lineNumber=221
if(node != this.header){throw new RuntimeException();}
roops_goal_1=true; //lineNumber=237
if(node != this.header){throw new RuntimeException();}
roops_goal_5=true; //lineNumber=254
if(node != this.header){throw new RuntimeException();}
roops_goal_9=true; //lineNumber=271
if(node != this.header){throw new RuntimeException();}
roops_goal_13=true; //lineNumber=288
if(node != this.header){throw new RuntimeException();}
roops_goal_17=true; //lineNumber=305
if(node != this.header){throw new RuntimeException();}
roops_goal_21=true; //lineNumber=311
__marker__.mark(149); //lineNumber=314

return false; //mutGenLimit 0 //lineNumber=315
}

    public static boolean roops_goal_0;

    public static boolean roops_goal_1;

    public static boolean roops_goal_2;

    public static boolean roops_goal_3;

    public static boolean roops_goal_4;

    public static boolean roops_goal_5;

    public static boolean roops_goal_6;

    public static boolean roops_goal_7;

    public static boolean roops_goal_8;

    public static boolean roops_goal_9;

    public static boolean roops_goal_10;

    public static boolean roops_goal_11;

    public static boolean roops_goal_12;

    public static boolean roops_goal_13;

    public static boolean roops_goal_14;

    public static boolean roops_goal_15;

    public static boolean roops_goal_16;

    public static boolean roops_goal_17;

    public static boolean roops_goal_18;

    public static boolean roops_goal_19;

    public static boolean roops_goal_20;

    public static boolean roops_goal_21;

    public static void fajita_roopsGoal_initialization () {
        roops_goal_0 = false;
        roops_goal_1 = false;
        roops_goal_2 = false;
        roops_goal_3 = false;
        roops_goal_4 = false;
        roops_goal_5 = false;
        roops_goal_6 = false;
        roops_goal_7 = false;
        roops_goal_8 = false;
        roops_goal_9 = false;
        roops_goal_10 = false;
        roops_goal_11 = false;
        roops_goal_12 = false;
        roops_goal_13 = false;
        roops_goal_14 = false;
        roops_goal_15 = false;
        roops_goal_16 = false;
        roops_goal_17 = false;
        roops_goal_18 = false;
        roops_goal_19 = false;
        roops_goal_20 = false;
        roops_goal_21 = false;
    }
}
