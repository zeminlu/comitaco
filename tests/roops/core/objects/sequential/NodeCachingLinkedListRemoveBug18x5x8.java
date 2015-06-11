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
if(index == this.size){throw new RuntimeException();}
roops_goal_3=true; //lineNumber=92
__marker__.mark79(); //lineNumber=94
if(index > this.size){throw new RuntimeException();}
roops_goal_5=true; //lineNumber=100
__marker__.mark82(); //lineNumber=102
boolean terminatesInTime=false; //lineNumber=103
if(index < this.size / 2){throw new RuntimeException();}
roops_goal_7=true; //lineNumber=169
__marker__.mark90(); //lineNumber=170
node=this.header; //lineNumber=171
__marker__.mark91(); //lineNumber=172
int currentIndex=this.size; //lineNumber=173
__marker__.mark92(); //lineNumber=174
if(!(currentIndex > index)){throw new RuntimeException();}
roops_goal_20=true; //lineNumber=176
__marker__.mark93(); //lineNumber=177
node=node.previous; //lineNumber=178
__marker__.mark94(); //lineNumber=179

currentIndex=currentIndex + 2; //mutGenLimit 1 //lineNumber=180
__marker__.mark95(); //lineNumber=181
if(!(currentIndex > index)){throw new RuntimeException();}
roops_goal_22=true; //lineNumber=187
__marker__.mark93(); //lineNumber=188
node=node.previous; //lineNumber=189
__marker__.mark94(); //lineNumber=190

currentIndex=currentIndex + 2; //mutGenLimit 1 //lineNumber=191
__marker__.mark95(); //lineNumber=192
if(!(currentIndex > index)){throw new RuntimeException();}
roops_goal_24=true; //lineNumber=198
__marker__.mark93(); //lineNumber=199
node=node.previous; //lineNumber=200
__marker__.mark94(); //lineNumber=201

currentIndex=currentIndex + 2; //mutGenLimit 1 //lineNumber=202
__marker__.mark95(); //lineNumber=203
if(!(currentIndex > index)){throw new RuntimeException();}
roops_goal_26=true; //lineNumber=209
__marker__.mark93(); //lineNumber=210
node=node.previous; //lineNumber=211
__marker__.mark94(); //lineNumber=212

currentIndex=currentIndex + 2; //mutGenLimit 1 //lineNumber=213
__marker__.mark95(); //lineNumber=214
if(!(currentIndex > index)){throw new RuntimeException();}
roops_goal_28=true; //lineNumber=220
__marker__.mark93(); //lineNumber=221
node=node.previous; //lineNumber=222
__marker__.mark94(); //lineNumber=223

currentIndex=currentIndex + 2; //mutGenLimit 1 //lineNumber=224
__marker__.mark95(); //lineNumber=225
if(!(currentIndex > index)){throw new RuntimeException();}
roops_goal_30=true; //lineNumber=231
terminatesInTime=true; //lineNumber=232
__marker__.mark96(); //lineNumber=236
__marker__.mark97(); //lineNumber=238
java.lang.Object oldValue; //lineNumber=239
__marker__.mark98(); //lineNumber=240
oldValue=node.value; //lineNumber=241
__marker__.mark99(); //lineNumber=242
node.previous.next=node.next; //lineNumber=243
__marker__.mark100(); //lineNumber=244
node.next.previous=node.previous; //lineNumber=245
__marker__.mark101(); //lineNumber=246
this.size=this.size - 1; //lineNumber=247
__marker__.mark102(); //lineNumber=248
this.modCount=this.modCount + 1; //lineNumber=249
__marker__.mark103(); //lineNumber=250
if(!(this.cacheSize < this.maximumCacheSize)){throw new RuntimeException();}
roops_goal_32=true; //lineNumber=252
__marker__.mark104(); //lineNumber=253
roops.core.objects.LinkedListNode nextCachedNode; //lineNumber=254
__marker__.mark105(); //lineNumber=255
nextCachedNode=this.firstCachedNode; //lineNumber=256
__marker__.mark106(); //lineNumber=257
node.previous=null; //lineNumber=258
__marker__.mark107(); //lineNumber=259
node.next=nextCachedNode; //lineNumber=260
__marker__.mark108(); //lineNumber=261
node.value=null; //lineNumber=262
__marker__.mark109(); //lineNumber=263
this.firstCachedNode=node; //lineNumber=264
__marker__.mark110(); //lineNumber=265
this.cacheSize=this.cacheSize + 1; //lineNumber=266
__marker__.mark111(); //lineNumber=267
__marker__.mark112(); //lineNumber=271
return oldValue; //lineNumber=272
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
