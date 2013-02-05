package roops.core.objects;

//@ model import org.jmlspecs.lang.*;


public /*@ nullable_by_default @*/ class NodeCachingLinkedList extends java.lang.Object {

  public roops.core.objects.LinkedListNode roops_core_objects_NodeCachingLinkedList_header;
  public roops.core.objects.LinkedListNode roops_core_objects_NodeCachingLinkedList_firstCachedNode;
  public int roops_core_objects_NodeCachingLinkedList_maximumCacheSize;
  public int roops_core_objects_NodeCachingLinkedList_cacheSize;
  public int roops_core_objects_NodeCachingLinkedList_size;
  public int roops_core_objects_NodeCachingLinkedList_DEFAULT_MAXIMUM_CACHE_SIZE;
  public int roops_core_objects_NodeCachingLinkedList_modCount;
  /*@ invariant this.roops_core_objects_NodeCachingLinkedList_header  !=  null;
    @*/
  /*@ invariant (\forall roops.core.objects.LinkedListNode n; \reach(this.roops_core_objects_NodeCachingLinkedList_header, roops.core.objects.LinkedListNode, roops_core_objects_LinkedListNode_next).has(((java.lang.Object)(n))); n.roops_core_objects_LinkedListNode_previous  !=  null && n.roops_core_objects_LinkedListNode_previous.roops_core_objects_LinkedListNode_next  ==  n && n.roops_core_objects_LinkedListNode_next  !=  null && n.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_previous  ==  n);
    @*/
  /*@ invariant this.roops_core_objects_NodeCachingLinkedList_size  ==  \reach(this.roops_core_objects_NodeCachingLinkedList_header, roops.core.objects.LinkedListNode, roops_core_objects_LinkedListNode_next).int_size() - 1;
    @*/
  /*@ invariant (\forall roops.core.objects.LinkedListNode n; \reach(this.roops_core_objects_NodeCachingLinkedList_header, roops.core.objects.LinkedListNode, roops_core_objects_LinkedListNode_next).has(((java.lang.Object)(n))); n  !=  this.roops_core_objects_NodeCachingLinkedList_header ==> n.roops_core_objects_LinkedListNode_value  !=  null);
    @*/
  /*@ invariant (\forall roops.core.objects.LinkedListNode m; \reach(this.roops_core_objects_NodeCachingLinkedList_firstCachedNode, roops.core.objects.LinkedListNode, roops_core_objects_LinkedListNode_next).has(((java.lang.Object)(m))); \reach(m.roops_core_objects_LinkedListNode_next, roops.core.objects.LinkedListNode, roops_core_objects_LinkedListNode_next).has(((java.lang.Object)(m)))  ==  false && m.roops_core_objects_LinkedListNode_previous  ==  null);
    @*/
  /*@ invariant this.roops_core_objects_NodeCachingLinkedList_cacheSize  <=  this.roops_core_objects_NodeCachingLinkedList_maximumCacheSize;
    @*/
  /*@ invariant this.roops_core_objects_NodeCachingLinkedList_maximumCacheSize  ==  this.roops_core_objects_NodeCachingLinkedList_DEFAULT_MAXIMUM_CACHE_SIZE;
    @*/
  /*@ invariant this.roops_core_objects_NodeCachingLinkedList_header.roops_core_objects_LinkedListNode_value  ==  null;
    @*/
  /*@ invariant this.roops_core_objects_NodeCachingLinkedList_DEFAULT_MAXIMUM_CACHE_SIZE  ==  3;
    @*/
  /*@ invariant this.roops_core_objects_NodeCachingLinkedList_cacheSize  ==  \reach(this.roops_core_objects_NodeCachingLinkedList_firstCachedNode, roops.core.objects.LinkedListNode, roops_core_objects_LinkedListNode_next).int_size();
    @*/

  public NodeCachingLinkedList() {
    this.roops_core_objects_NodeCachingLinkedList_header = ((roops.core.objects.LinkedListNode)(null));
    this.roops_core_objects_NodeCachingLinkedList_firstCachedNode = ((roops.core.objects.LinkedListNode)(null));
    this.roops_core_objects_NodeCachingLinkedList_maximumCacheSize = (byte)0;
    this.roops_core_objects_NodeCachingLinkedList_cacheSize = (byte)0;
    this.roops_core_objects_NodeCachingLinkedList_size = (byte)0;
    this.roops_core_objects_NodeCachingLinkedList_DEFAULT_MAXIMUM_CACHE_SIZE = (byte)0;
    this.roops_core_objects_NodeCachingLinkedList_modCount = (byte)0;
    {
      roops.core.objects.LinkedListNode t_1;

      t_1 = new roops.core.objects.LinkedListNode();
      this.roops_core_objects_NodeCachingLinkedList_header = t_1;
      this.roops_core_objects_NodeCachingLinkedList_header.roops_core_objects_LinkedListNode_next = this.roops_core_objects_NodeCachingLinkedList_header;
      this.roops_core_objects_NodeCachingLinkedList_header.roops_core_objects_LinkedListNode_previous = this.roops_core_objects_NodeCachingLinkedList_header;
      this.roops_core_objects_NodeCachingLinkedList_firstCachedNode = ((roops.core.objects.LinkedListNode)(null));
      this.roops_core_objects_NodeCachingLinkedList_size = 0;
      this.roops_core_objects_NodeCachingLinkedList_cacheSize = 0;
      this.roops_core_objects_NodeCachingLinkedList_DEFAULT_MAXIMUM_CACHE_SIZE = 3;
      this.roops_core_objects_NodeCachingLinkedList_maximumCacheSize = 3;
      this.roops_core_objects_NodeCachingLinkedList_modCount = 0;
    }
  }


  /*@ 
    @ requires index  >=  0;
    @ requires index  <  this.roops_core_objects_NodeCachingLinkedList_size;
    @ ensures this.roops_core_objects_NodeCachingLinkedList_size  ==  \old(this.roops_core_objects_NodeCachingLinkedList_size) - 1;
    @ ensures (\old(this.roops_core_objects_NodeCachingLinkedList_cacheSize)  <  this.roops_core_objects_NodeCachingLinkedList_maximumCacheSize) ==> (this.roops_core_objects_NodeCachingLinkedList_cacheSize  ==  \old(this.roops_core_objects_NodeCachingLinkedList_cacheSize) + 1);
    @ ensures this.roops_core_objects_NodeCachingLinkedList_modCount  ==  \old(this.roops_core_objects_NodeCachingLinkedList_modCount) + 1;
    @ ensures (index  ==  0 ==> \result  ==  \old(this.roops_core_objects_NodeCachingLinkedList_header.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_value));
    @ ensures (index  ==  1 ==> \result  ==  \old(this.roops_core_objects_NodeCachingLinkedList_header.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_value));
    @ ensures (index  ==  2 ==> \result  ==  \old(this.roops_core_objects_NodeCachingLinkedList_header.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_value));
    @ ensures (index  ==  3 ==> \result  ==  \old(this.roops_core_objects_NodeCachingLinkedList_header.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_value));
    @ ensures (index  ==  4 ==> \result  ==  \old(this.roops_core_objects_NodeCachingLinkedList_header.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_value));
    @ ensures (index  ==  5 ==> \result  ==  \old(this.roops_core_objects_NodeCachingLinkedList_header.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_value));
    @ ensures (index  ==  6 ==> \result  ==  \old(this.roops_core_objects_NodeCachingLinkedList_header.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_value));
    @ signals (java.lang.Exception ) false;
    @*/
  public java.lang.Object removeOk(int index) {
    boolean t_3;
    boolean t_5;
    boolean t_7;
    int t_8;
    boolean t_9;
    boolean t_10;
    roops.core.objects.LinkedListNode var_1_node = ((roops.core.objects.LinkedListNode)(null));
    java.lang.IndexOutOfBoundsException var_2_exception = ((java.lang.IndexOutOfBoundsException)(null));

    t_3 = index  <  0;

    if (t_3) {
      {
        {
          {
            {
              {
                java.lang.IndexOutOfBoundsException t_2;

                t_2 = new java.lang.IndexOutOfBoundsException();
                var_2_exception = t_2;
                throw var_2_exception;
              }
            }
          }
        }
      }
    }
    t_5 = index  ==  this.roops_core_objects_NodeCachingLinkedList_size;

    if (t_5) {
      {
        {
          {
            {
              {
                java.lang.IndexOutOfBoundsException t_4;

                t_4 = new java.lang.IndexOutOfBoundsException();
                var_2_exception = t_4;
                throw var_2_exception;
              }
            }
          }
        }
      }
    }
    t_7 = index  >  this.roops_core_objects_NodeCachingLinkedList_size;

    if (t_7) {
      {
        {
          {
            {
              {
                java.lang.IndexOutOfBoundsException t_6;

                t_6 = new java.lang.IndexOutOfBoundsException();
                var_2_exception = t_6;
                throw var_2_exception;
              }
            }
          }
        }
      }
    }
    roops.core.objects.LinkedListNode var_3_node1 = ((roops.core.objects.LinkedListNode)(null));

    t_8 = this.roops_core_objects_NodeCachingLinkedList_size / 2;
    t_9 = index  <  t_8;

    if (t_9) {
      {
        {
          {
            {
              {
                var_3_node1 = this.roops_core_objects_NodeCachingLinkedList_header.roops_core_objects_LinkedListNode_next;
                int var_4_currentIndex = 0;
                boolean var_5_ws_1;

                var_5_ws_1 = var_4_currentIndex  <  index;
                while (var_5_ws_1) {
                  var_3_node1 = var_3_node1.roops_core_objects_LinkedListNode_next;
                  var_4_currentIndex = var_4_currentIndex + 1;
                  var_5_ws_1 = var_4_currentIndex  <  index;
                }
              }
            }
          }
        }
      }
    } else {
      {
        {
          {
            {
              {
                var_3_node1 = this.roops_core_objects_NodeCachingLinkedList_header;
                int var_6_currentIndex = this.roops_core_objects_NodeCachingLinkedList_size;
                boolean var_7_ws_2;

                var_7_ws_2 = var_6_currentIndex  >  index;
                while (var_7_ws_2) {
                  var_3_node1 = var_3_node1.roops_core_objects_LinkedListNode_previous;
                  var_6_currentIndex = var_6_currentIndex - 1;
                  var_7_ws_2 = var_6_currentIndex  >  index;
                }
              }
            }
          }
        }
      }
    }
    var_1_node = var_3_node1;
    java.lang.Object var_8_oldValue = var_1_node.roops_core_objects_LinkedListNode_value;

    var_1_node.roops_core_objects_LinkedListNode_previous.roops_core_objects_LinkedListNode_next = var_1_node.roops_core_objects_LinkedListNode_next;
    var_1_node.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_previous = var_1_node.roops_core_objects_LinkedListNode_previous;
    this.roops_core_objects_NodeCachingLinkedList_size = this.roops_core_objects_NodeCachingLinkedList_size + 1;
    this.roops_core_objects_NodeCachingLinkedList_modCount = this.roops_core_objects_NodeCachingLinkedList_modCount + 1;
    t_10 = this.roops_core_objects_NodeCachingLinkedList_cacheSize  <  this.roops_core_objects_NodeCachingLinkedList_maximumCacheSize;
    if (t_10) {
      {
        {
          {
            {
              {
                roops.core.objects.LinkedListNode var_9_nextCachedNode = this.roops_core_objects_NodeCachingLinkedList_firstCachedNode;

                var_1_node.roops_core_objects_LinkedListNode_previous = ((roops.core.objects.LinkedListNode)(null));
                var_1_node.roops_core_objects_LinkedListNode_next = var_9_nextCachedNode;
                var_1_node.roops_core_objects_LinkedListNode_value = null;
                this.roops_core_objects_NodeCachingLinkedList_firstCachedNode = var_1_node;
                this.roops_core_objects_NodeCachingLinkedList_cacheSize = this.roops_core_objects_NodeCachingLinkedList_cacheSize + 1;
              }
            }
          }
        }
      }
    }

    return var_8_oldValue;
  }


  /*@ 
    @ requires index  >=  0;
    @ requires index  <  this.roops_core_objects_NodeCachingLinkedList_size;
    @ ensures this.roops_core_objects_NodeCachingLinkedList_size  ==  \old(this.roops_core_objects_NodeCachingLinkedList_size) - 1;
    @ ensures (\old(this.roops_core_objects_NodeCachingLinkedList_cacheSize)  <  this.roops_core_objects_NodeCachingLinkedList_maximumCacheSize) ==> (this.roops_core_objects_NodeCachingLinkedList_cacheSize  ==  \old(this.roops_core_objects_NodeCachingLinkedList_cacheSize) + 1);
    @ ensures this.roops_core_objects_NodeCachingLinkedList_modCount  ==  \old(this.roops_core_objects_NodeCachingLinkedList_modCount) + 1;
    @ ensures (index  ==  0 ==> \result  ==  \old(this.roops_core_objects_NodeCachingLinkedList_header.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_value));
    @ ensures (index  ==  1 ==> \result  ==  \old(this.roops_core_objects_NodeCachingLinkedList_header.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_value));
    @ ensures (index  ==  2 ==> \result  ==  \old(this.roops_core_objects_NodeCachingLinkedList_header.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_value));
    @ ensures (index  ==  3 ==> \result  ==  \old(this.roops_core_objects_NodeCachingLinkedList_header.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_value));
    @ ensures (index  ==  4 ==> \result  ==  \old(this.roops_core_objects_NodeCachingLinkedList_header.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_value));
    @ ensures (index  ==  5 ==> \result  ==  \old(this.roops_core_objects_NodeCachingLinkedList_header.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_value));
    @ ensures (index  ==  6 ==> \result  ==  \old(this.roops_core_objects_NodeCachingLinkedList_header.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_value));
    @ signals (java.lang.Exception ) false;
    @*/
  public java.lang.Object removeOkTest(int index) {
    int t_11;

    t_11 = index + 1;
    int var_10_aux = t_11;


    return null;
  }

}
