package roops.core.objects;

//@ model import org.jmlspecs.lang.*;

/** @Invariant 
		( this.header!=null ) &&
		( this.header.next!=null ) &&
		( this.header.previous!=null ) &&
		( this.size==#(this.header.*next @- null)-1 ) &&
		( this.size>=0 ) &&
		(all n: LinkedListNode | ( n in this.header.*next @- null ) => (
				  n!=null &&
				  n.previous!=null &&
				  n.previous.next==n &&
				  n.next!=null &&
				  n.next.previous==n )) ; 
*/
public class LinkedList extends java.lang.Object {

  public /*@ nullable @*/ roops.core.objects.LinkedListNode roops_core_objects_LinkedList_header;
  public int roops_core_objects_LinkedList_size;
  public int roops_core_objects_LinkedList_modCount;/** @Modifies_Everything;
 
 @Ensures false;
*/

  public static void addLastTest(/*@ nullable @*/ roops.core.objects.LinkedList list, /*@ nullable @*/ java.lang.Object o) {
    boolean t_2;

    t_2 = list  !=  null;
    if (t_2) {
      {
        {
          {
            {
              {
                boolean t_1;

                t_1 = list.addLast(o);
                boolean var_1_ret_val = t_1;
              }
            }
          }
        }
      }
    }
  }
/** @Modifies_Everything;
 
 @Ensures false;
*/

  public static void containsTest(/*@ nullable @*/ roops.core.objects.LinkedList list, /*@ nullable @*/ java.lang.Object arg) {
    boolean t_4;

    t_4 = list  !=  null;
    if (t_4) {
      {
        {
          {
            {
              {
                boolean t_3;

                t_3 = list.contains(arg);
                boolean var_2_ret_val = t_3;
              }
            }
          }
        }
      }
    }
  }
/** @Modifies_Everything;
 
 @Ensures false;
*/

  public static void removeIndexTest(/*@ nullable @*/ roops.core.objects.LinkedList list, int index) {
    boolean t_6;

    t_6 = list  !=  null;
    if (t_6) {
      {
        {
          {
            {
              {
                java.lang.Object t_5;

                t_5 = list.removeIndex(index);
                java.lang.Object var_3_ret_val = t_5;
              }
            }
          }
        }
      }
    }
  }


  private void init() {
    roops.core.objects.LinkedListNode t_7;

    t_7 = this.createHeaderNode();
    this.roops_core_objects_LinkedList_header = t_7;
  }


  private int indexOf(java.lang.Object value) {
    int var_4_i = 0;

    {
      roops.core.objects.LinkedListNode var_5_node = this.roops_core_objects_LinkedList_header.roops_core_objects_LinkedListNode_next;
      boolean var_6_ws_1;

      var_6_ws_1 = var_5_node  !=  this.roops_core_objects_LinkedList_header;
      while (var_6_ws_1) {
        boolean t_8;
        int t_9;

        t_8 = this.isEqualValue(var_5_node.roops_core_objects_LinkedListNode_value, value);

        if (t_8) {
          {
            {
              {
                {
                  {
                    return var_4_i;
                  }
                }
              }
            }
          }
        }
        t_9 = var_4_i;
        var_4_i = var_4_i + (byte)1;
        var_5_node = var_5_node.roops_core_objects_LinkedListNode_next;
        var_6_ws_1 = var_5_node  !=  this.roops_core_objects_LinkedList_header;
      }
    }

    return -1;
  }


  public boolean contains(java.lang.Object arg) {
    int t_10;
    boolean t_11;

    t_10 = this.indexOf(arg);
    t_11 = t_10  !=  -1;

    return t_11;
  }


  public java.lang.Object removeIndex(int index) {
    roops.core.objects.LinkedListNode t_12;

    t_12 = this.getNode(index, false);
    roops.core.objects.LinkedListNode var_7_node = t_12;
    java.lang.Object var_8_oldValue = var_7_node.roops_core_objects_LinkedListNode_value;

    this.removeNode(var_7_node);

    return var_8_oldValue;
  }


  /*@ 
    @ ensures false;
    @*/
  public boolean addLast(java.lang.Object o) {
    this.addNodeBefore(this.roops_core_objects_LinkedList_header, o);

    return true;
  }


  private boolean isEqualValue(java.lang.Object value1, java.lang.Object value2) {
    boolean t_15;
    boolean var_9_ret_val;

    t_15 = value1  ==  value2;
    if (t_15) {
      {
        {
          {
            {
              {
                var_9_ret_val = true;
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
                boolean t_14;

                t_14 = value1  ==  null;
                if (t_14) {
                  {
                    {
                      {
                        {
                          {
                            var_9_ret_val = false;
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
                            boolean t_13;

                            t_13 = value1.equals(value2);
                            var_9_ret_val = t_13;
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }

    return var_9_ret_val;
  }
/** Creates a new node with previous, next and element all set to null.
 This implementation creates a new empty Node.
 Subclasses can override this to create a different class.
 
 @return  newly created node
*/

  private roops.core.objects.LinkedListNode createHeaderNode() {
    roops.core.objects.LinkedListNode t_16;

    t_16 = new roops.core.objects.LinkedListNode();
    roops.core.objects.LinkedListNode var_10_linkedListNode = t_16;

    var_10_linkedListNode.roops_core_objects_LinkedListNode_next = var_10_linkedListNode;
    var_10_linkedListNode.roops_core_objects_LinkedListNode_previous = var_10_linkedListNode;

    return var_10_linkedListNode;
  }
/** Creates a new node with the specified properties.
 This implementation creates a new Node with data.
 Subclasses can override this to create a different class.
 
 @param value  value of the new node
*/

  private roops.core.objects.LinkedListNode createNode(java.lang.Object value) {
    roops.core.objects.LinkedListNode t_17;

    t_17 = new roops.core.objects.LinkedListNode();
    roops.core.objects.LinkedListNode var_11_node = t_17;

    var_11_node.roops_core_objects_LinkedListNode_previous = var_11_node;
    var_11_node.roops_core_objects_LinkedListNode_next = var_11_node;
    var_11_node.roops_core_objects_LinkedListNode_value = value;

    return var_11_node;
  }


  private void addNodeBefore(roops.core.objects.LinkedListNode node, java.lang.Object value) {
    roops.core.objects.LinkedListNode t_18;

    t_18 = this.createNode(value);
    roops.core.objects.LinkedListNode var_12_newNode = t_18;

    this.addNode(var_12_newNode, node);
  }


  private void addNode(roops.core.objects.LinkedListNode nodeToInsert, roops.core.objects.LinkedListNode insertBeforeNode) {
    int t_19;
    int t_20;

    nodeToInsert.roops_core_objects_LinkedListNode_next = insertBeforeNode;
    nodeToInsert.roops_core_objects_LinkedListNode_previous = insertBeforeNode.roops_core_objects_LinkedListNode_previous;
    insertBeforeNode.roops_core_objects_LinkedListNode_previous.roops_core_objects_LinkedListNode_next = nodeToInsert;
    insertBeforeNode.roops_core_objects_LinkedListNode_previous = nodeToInsert;
    t_19 = this.roops_core_objects_LinkedList_size;
    this.roops_core_objects_LinkedList_size = this.roops_core_objects_LinkedList_size + (byte)1;
    t_20 = this.roops_core_objects_LinkedList_modCount;
    this.roops_core_objects_LinkedList_modCount = this.roops_core_objects_LinkedList_modCount + (byte)1;
  }


  private void removeNode(roops.core.objects.LinkedListNode node) {
    int t_21;
    int t_22;

    node.roops_core_objects_LinkedListNode_previous.roops_core_objects_LinkedListNode_next = node.roops_core_objects_LinkedListNode_next;
    node.roops_core_objects_LinkedListNode_next.roops_core_objects_LinkedListNode_previous = node.roops_core_objects_LinkedListNode_previous;
    t_21 = this.roops_core_objects_LinkedList_size;
    this.roops_core_objects_LinkedList_size = this.roops_core_objects_LinkedList_size + (byte)-1;
    t_22 = this.roops_core_objects_LinkedList_modCount;
    this.roops_core_objects_LinkedList_modCount = this.roops_core_objects_LinkedList_modCount + (byte)1;
  }


  private roops.core.objects.LinkedListNode getNode(int index, boolean endMarkerAllowed) throws java.lang.IndexOutOfBoundsException {
    boolean t_24;
    boolean t_26;
    boolean t_27;
    boolean t_28;
    boolean t_30;
    int t_31;
    boolean t_34;

    t_24 = index  <  0;

    if (t_24) {
      {
        {
          {
            {
              {
                java.lang.IndexOutOfBoundsException t_23;

                t_23 = new java.lang.IndexOutOfBoundsException();
                throw t_23;
              }
            }
          }
        }
      }
    }
    t_27 = ! endMarkerAllowed;
    t_28 = index  ==  this.roops_core_objects_LinkedList_size;

    if (t_27) {
      {
        if (t_28) {
          {
            t_26 = true;
          }
        } else {
          {
            t_26 = false;
          }
        }
      }
    } else {
      {
        t_26 = false;
      }
    }

    if (t_26) {
      {
        {
          {
            {
              {
                java.lang.IndexOutOfBoundsException t_25;

                t_25 = new java.lang.IndexOutOfBoundsException();
                throw t_25;
              }
            }
          }
        }
      }
    }
    t_30 = index  >  this.roops_core_objects_LinkedList_size;

    if (t_30) {
      {
        {
          {
            {
              {
                java.lang.IndexOutOfBoundsException t_29;

                t_29 = new java.lang.IndexOutOfBoundsException();
                throw t_29;
              }
            }
          }
        }
      }
    }
    roops.core.objects.LinkedListNode var_13_node;

    t_31 = this.roops_core_objects_LinkedList_size  >>  1;
    int var_14_size_div_2 = t_31;

    t_34 = index  <  var_14_size_div_2;
    if (t_34) {
      {
        {
          {
            {
              {
                var_13_node = this.roops_core_objects_LinkedList_header.roops_core_objects_LinkedListNode_next;
                {
                  int var_15_currentIndex = 0;
                  boolean var_16_ws_2;

                  var_16_ws_2 = var_15_currentIndex  <  index;
                  while (var_16_ws_2) {
                    int t_32;

                    var_13_node = var_13_node.roops_core_objects_LinkedListNode_next;
                    t_32 = var_15_currentIndex;
                    var_15_currentIndex = var_15_currentIndex + (byte)1;
                    var_16_ws_2 = var_15_currentIndex  <  index;
                  }
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
                var_13_node = this.roops_core_objects_LinkedList_header;
                {
                  int var_17_currentIndex = this.roops_core_objects_LinkedList_size;
                  boolean var_18_ws_3;

                  var_18_ws_3 = var_17_currentIndex  >  index;
                  while (var_18_ws_3) {
                    int t_33;

                    var_13_node = var_13_node.roops_core_objects_LinkedListNode_previous;
                    t_33 = var_17_currentIndex;
                    var_17_currentIndex = var_17_currentIndex + (byte)-1;
                    var_18_ws_3 = var_17_currentIndex  >  index;
                  }
                }
              }
            }
          }
        }
      }
    }

    return var_13_node;
  }


  public LinkedList() {
    this.roops_core_objects_LinkedList_header = ((roops.core.objects.LinkedListNode)(null));
    this.roops_core_objects_LinkedList_size = (byte)0;
    this.roops_core_objects_LinkedList_modCount = (byte)0;
    {
    }
  }

}
