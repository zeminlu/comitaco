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
    public /*@nullable@*/java.lang.Object remove ( final int index) {fajita_roopsGoal_initialization();
BugLineMarker __marker__=new BugLineMarker(); //lineNumber=75
__marker__.mark72(); //lineNumber=76
LinkedListNode node=null; //lineNumber=77
__marker__.mark73(); //lineNumber=78
if(index < 0){throw new RuntimeException();}
roops_goal_1=true; //lineNumber=84
__marker__.mark76(); //lineNumber=86
if(index > this.size){throw new RuntimeException();}
roops_goal_3=true; //lineNumber=92
__marker__.mark79(); //lineNumber=94
if(index > this.size){throw new RuntimeException();}
roops_goal_5=true; //lineNumber=100
__marker__.mark82(); //lineNumber=102
if(index < this.size / 2){throw new RuntimeException();}
roops_goal_7=true; //lineNumber=164
__marker__.mark90(); //lineNumber=165
node=this.header; //lineNumber=166
__marker__.mark91(); //lineNumber=167
int currentIndex=this.size; //lineNumber=168
__marker__.mark92(); //lineNumber=169
if(!(currentIndex > index)){throw new RuntimeException();}
roops_goal_18=true; //lineNumber=172
__marker__.mark93(); //lineNumber=173

node=node.previous; //mutGenLimit 1 //lineNumber=174
__marker__.mark94(); //lineNumber=175
currentIndex=currentIndex - 1; //lineNumber=177
__marker__.mark95(); //lineNumber=178
if(!(currentIndex > index)){throw new RuntimeException();}
roops_goal_20=true; //lineNumber=183
__marker__.mark93(); //lineNumber=184

node=node.previous; //mutGenLimit 1 //lineNumber=185
__marker__.mark94(); //lineNumber=186
currentIndex=currentIndex - 1; //lineNumber=188
__marker__.mark95(); //lineNumber=189
if(currentIndex > index){throw new RuntimeException();}
roops_goal_23=true; //lineNumber=202
if(currentIndex > index){throw new RuntimeException();}
roops_goal_25=true; //lineNumber=213
if(currentIndex > index){throw new RuntimeException();}
roops_goal_27=true; //lineNumber=224
__marker__.mark96(); //lineNumber=227
__marker__.mark97(); //lineNumber=229
java.lang.Object oldValue; //lineNumber=230
__marker__.mark98(); //lineNumber=231
oldValue=node.value; //lineNumber=232
__marker__.mark99(); //lineNumber=233
node.previous.next=node.next; //lineNumber=234
__marker__.mark100(); //lineNumber=235
node.next.previous=node.previous; //lineNumber=236
__marker__.mark101(); //lineNumber=237
this.size=this.size - 1; //lineNumber=238
__marker__.mark102(); //lineNumber=239
this.modCount=this.modCount + 1; //lineNumber=240
__marker__.mark103(); //lineNumber=241
if(!(this.cacheSize < this.maximumCacheSize)){throw new RuntimeException();}
roops_goal_28=true; //lineNumber=243
__marker__.mark104(); //lineNumber=244
roops.core.objects.LinkedListNode nextCachedNode; //lineNumber=245
__marker__.mark105(); //lineNumber=246
nextCachedNode=this.firstCachedNode; //lineNumber=247
__marker__.mark106(); //lineNumber=248
node.previous=null; //lineNumber=249
__marker__.mark107(); //lineNumber=250
node.next=nextCachedNode; //lineNumber=251
__marker__.mark108(); //lineNumber=252
node.value=null; //lineNumber=253
__marker__.mark109(); //lineNumber=254
this.firstCachedNode=node; //lineNumber=255
__marker__.mark110(); //lineNumber=256
this.cacheSize=this.cacheSize - 1; //lineNumber=257
__marker__.mark111(); //lineNumber=258
__marker__.mark112(); //lineNumber=262
return oldValue; //lineNumber=263
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
    }
}
