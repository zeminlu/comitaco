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
        fajita_roopsGoal_initialization ();
        BugLineMarker __marker__ = new BugLineMarker ();
        __marker__.mark72 ();
        LinkedListNode node = null;
        __marker__.mark73 ();
        if ( index < 0 ) {
            roops_goal_0 = true;
            __marker__.mark74 ();
            throw new java.lang.RuntimeException ();
        } else {
            roops_goal_1 = true;
        }
        __marker__.mark76 ();
        if ( index == this.size ) {
            roops_goal_2 = true; //mutGenLimit 1
            __marker__.mark77 ();
            throw new java.lang.RuntimeException ();
        } else {
            roops_goal_3 = true;
        }
        __marker__.mark79 ();
        if ( index > this.size ) {
            roops_goal_4 = true; //mutGenLimit 1
            __marker__.mark80 ();
            throw new java.lang.IndexOutOfBoundsException ();
        } else {
            roops_goal_5 = true;
        }
        __marker__.mark82 ();
        boolean terminatesInTime = false;
        if ( index < this.size / 2 ) {
            roops_goal_6 = true;
            __marker__.mark83 ();
            node = this.header.next;
            __marker__.mark84 ();
            int currentIndex = 0;
            __marker__.mark85 ();
            if ( currentIndex < index ) {
                roops_goal_8 = true;
                __marker__.mark86 ();
                node = node.next;
                __marker__.mark87 ();
                currentIndex ++;
                __marker__.mark88 ();
            } else {
                roops_goal_9 = true;
            }
            if ( currentIndex < index ) {
                roops_goal_10 = true;
                __marker__.mark86 ();
                node = node.next;
                __marker__.mark87 ();
                currentIndex ++;
                __marker__.mark88 ();
            } else {
                roops_goal_11 = true;
            }
            if ( currentIndex < index ) {
                roops_goal_12 = true;
                __marker__.mark86 ();
                node = node.next;
                __marker__.mark87 ();
                currentIndex ++;
                __marker__.mark88 ();
            } else {
                roops_goal_13 = true;
            }
            if ( currentIndex < index ) {
                roops_goal_14 = true;
                __marker__.mark86 ();
                node = node.next;
                __marker__.mark87 ();
                currentIndex ++;
                __marker__.mark88 ();
            } else {
                roops_goal_15 = true;
            }
            if ( currentIndex < index ) {
                roops_goal_16 = true;
                __marker__.mark86 ();
                node = node.next;
                __marker__.mark87 ();
                currentIndex ++;
                __marker__.mark88 ();
            } else {
                roops_goal_17 = true;
            }
            if ( currentIndex < index ) {
                roops_goal_18 = true;
                terminatesInTime = true;
            } else {
                roops_goal_19 = true;
            }
            __marker__.mark89 ();
        } else {
            roops_goal_7 = true;
            __marker__.mark90 ();
            node = this.header;
            __marker__.mark91 ();
            int currentIndex = this.size;
            __marker__.mark92 ();
            if ( currentIndex > index ) {
                roops_goal_20 = true;
                __marker__.mark93 ();
                node = node.previous;
                __marker__.mark94 ();
                currentIndex = currentIndex + 2; //mutGenLimit 1
                __marker__.
                mark95 ();
            } else {
                roops_goal_21 = true;
            }
            if ( currentIndex > index ) {
                roops_goal_22 = true;
                __marker__.mark93 ();
                node = node.previous;
                __marker__.mark94 ();
                currentIndex = currentIndex + 2; //mutGenLimit 1
                __marker__.
                mark95 ();
            } else {
                roops_goal_23 = true;
            }
            if ( currentIndex > index ) {
                roops_goal_24 = true;
                __marker__.mark93 ();
                node = node.previous;
                __marker__.mark94 ();
                currentIndex = currentIndex + 2; //mutGenLimit 1
                __marker__.
                mark95 ();
            } else {
                roops_goal_25 = true;
            }
            if ( currentIndex > index ) {
                roops_goal_26 = true;
                __marker__.mark93 ();
                node = node.previous;
                __marker__.mark94 ();
                currentIndex = currentIndex + 2; //mutGenLimit 1
                __marker__.
                mark95 ();
            } else {
                roops_goal_27 = true;
            }
            if ( currentIndex > index ) {
                roops_goal_28 = true;
                __marker__.mark93 ();
                node = node.previous;
                __marker__.mark94 ();
                currentIndex = currentIndex + 2; //mutGenLimit 1
                __marker__.
                mark95 ();
            } else {
                roops_goal_29 = true;
            }
            if ( currentIndex > index ) {
                roops_goal_30 = true;
                terminatesInTime = true;
            } else {
                roops_goal_31 = true;
            }
            __marker__.mark96 ();
        }
        __marker__.mark97 ();
        java.lang.Object oldValue;
        __marker__.mark98 ();
        oldValue = node.value;
        __marker__.mark99 ();
        node.previous.next = node.next;
        __marker__.mark100 ();
        node.next.previous = node.previous;
        __marker__.mark101 ();
        this.size = this.size - 1;
        __marker__.mark102 ();
        this.modCount = this.modCount + 1;
        __marker__.mark103 ();
        if ( this.cacheSize < this.maximumCacheSize ) {
            roops_goal_32 = true;
            __marker__.mark104 ();
            roops.core.objects.LinkedListNode nextCachedNode;
            __marker__.mark105 ();
            nextCachedNode = this.firstCachedNode;
            __marker__.mark106 ();
            node.previous = null;
            __marker__.mark107 ();
            node.next = nextCachedNode;
            __marker__.mark108 ();
            node.value = null;
            __marker__.mark109 ();
            this.firstCachedNode = node;
            __marker__.mark110 ();
            this.cacheSize = this.cacheSize + 1;
            __marker__.mark111 ();
        } else {
            roops_goal_33 = true;
        }
        __marker__.mark112 ();
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
    public boolean contains ( /*@ nullable @*/java.lang.Object arg) {
        LinkedListNode node = this.header.next; //mutGenLimit 0
        {
            boolean terminatesInTime = false;
            if ( node != this.header ) { //mutGenLimit 0
                if ( node.value == arg ) { //mutGenLimit 0
                    return true; //mutGenLimit 0
                } else {
                }
                node = node.next; //mutGenLimit 0
            } else {
            }
            if ( node != this.header ) { //mutGenLimit 0
                if ( node.value == arg ) { //mutGenLimit 0
                    return true; //mutGenLimit 0
                } else {
                }
                node = node.next; //mutGenLimit 0
            } else {
            }
            if ( node != this.header ) { //mutGenLimit 0
                if ( node.value == arg ) { //mutGenLimit 0
                    return true; //mutGenLimit 0
                } else {
                }
                node = node.next; //mutGenLimit 0
            } else {
            }
            if ( node != this.header ) { //mutGenLimit 0
                if ( node.value == arg ) { //mutGenLimit 0
                    return true; //mutGenLimit 0
                } else {
                }
                node = node.next; //mutGenLimit 0
            } else {
            }
            if ( node != this.header ) { //mutGenLimit 0
                if ( node.value == arg ) { //mutGenLimit 0
                    return true; //mutGenLimit 0
                } else {
                }
                node = node.next; //mutGenLimit 0
            } else {
            }
            if ( node != this.header ) {
                terminatesInTime = true;
            } else {
            }
        }
        return false; //mutGenLimit 0
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

    public static boolean roops_goal_22;

    public static boolean roops_goal_23;

    public static boolean roops_goal_24;

    public static boolean roops_goal_25;

    public static boolean roops_goal_26;

    public static boolean roops_goal_27;

    public static boolean roops_goal_28;

    public static boolean roops_goal_29;

    public static boolean roops_goal_30;

    public static boolean roops_goal_31;

    public static boolean roops_goal_32;

    public static boolean roops_goal_33;

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
        roops_goal_22 = false;
        roops_goal_23 = false;
        roops_goal_24 = false;
        roops_goal_25 = false;
        roops_goal_26 = false;
        roops_goal_27 = false;
        roops_goal_28 = false;
        roops_goal_29 = false;
        roops_goal_30 = false;
        roops_goal_31 = false;
        roops_goal_32 = false;
        roops_goal_33 = false;
    }
}