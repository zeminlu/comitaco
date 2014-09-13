package roops.core.objectsInstrumented;

//@ model import org.jmlspecs.lang.*;


public /*@ nullable_by_default @*/ class SinglyLinkedList extends java.lang.Object {

  public roops.core.objectsInstrumented.SinglyLinkedListNode roops_core_objectsInstrumented_SinglyLinkedList_header;
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
  /*@ invariant (\forall roops.core.objectsInstrumented.SinglyLinkedListNode n; \reach(this.roops_core_objectsInstrumented_SinglyLinkedList_header, roops.core.objectsInstrumented.SinglyLinkedListNode, roops_core_objectsInstrumented_SinglyLinkedListNode_next).has(((java.lang.Object)(n))); \reach(n.roops_core_objectsInstrumented_SinglyLinkedListNode_next, roops.core.objectsInstrumented.SinglyLinkedListNode, roops_core_objectsInstrumented_SinglyLinkedListNode_next).has(((java.lang.Object)(n)))  ==  false);
    @*/
<<<<<<< HEAD
=======
  public class BugLineMarker extends java.lang.Object {


    public BugLineMarker() {
      {
      }
    }


    public void mark() {
    }

  }
>>>>>>> unsat error lines

  public SinglyLinkedList() {
    this.roops_core_objectsInstrumented_SinglyLinkedList_header = ((roops.core.objectsInstrumented.SinglyLinkedListNode)(null));
    {
    }
  }


  /*@ 
    @ requires \reach(this.roops_core_objectsInstrumented_SinglyLinkedList_header, roops.core.objectsInstrumented.SinglyLinkedListNode, roops_core_objectsInstrumented_SinglyLinkedListNode_next).int_size()  ==  100;
    @ ensures \result  ==  false;
    @*/
  public boolean showInstance() {
    return true;
  }
/** @Modifies_Everything
 @Ensures false;
*/

  public boolean contains(/*@ nullable @*/ java.lang.Object value_param) {
<<<<<<< HEAD
    fajita_roopsGoal_initialization();
    roops.core.objectsInstrumented.SinglyLinkedListNode var_1_current;
    boolean var_2_result;

    var_1_current = this.roops_core_objectsInstrumented_SinglyLinkedList_header;
    var_2_result = false;
    {
      boolean t_1;
      boolean t_2;
      boolean t_3;
      boolean t_13;
      boolean var_3_fajita_cicle_0 = false;
      boolean var_4_ws_1;

      t_2 = var_2_result  ==  false;
      t_3 = var_1_current  !=  null;

      if (t_2) {
        {
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> static-field-not-found
          {
            if (t_3) {
              {
                {
                  t_1 = true;
                }
              }
            } else {
              {
                {
                  t_1 = false;
                }
              }
<<<<<<< HEAD
=======
          if (t_3) {
            {
              t_1 = true;
            }
          } else {
            {
              t_1 = false;
>>>>>>> a
=======
>>>>>>> static-field-not-found
=======
    roops.core.objectsInstrumented.SinglyLinkedList.BugLineMarker t_1;

    fajita_roopsGoal_initialization();
    t_1 = new roops.core.objectsInstrumented.SinglyLinkedList.BugLineMarker();
    roops.core.objectsInstrumented.SinglyLinkedList.BugLineMarker var_1___marker__ = t_1;
    roops.core.objectsInstrumented.SinglyLinkedListNode var_2_current;
    boolean var_3_result;

    var_2_current = this.roops_core_objectsInstrumented_SinglyLinkedList_header;
    var_3_result = false;
    {
      boolean t_2;
      boolean t_3;
      boolean t_4;
      boolean t_14;
      boolean var_4_fajita_cicle_0 = false;
      boolean var_5_ws_1;

      t_3 = var_3_result  ==  false;
      t_4 = var_2_current  !=  null;

      if (t_3) {
        {
          if (t_4) {
            {
              t_2 = true;
            }
          } else {
            {
              t_2 = false;
>>>>>>> unsat error lines
            }
          }
        }
      } else {
        {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
          {
            t_1 = false;
          }
=======
          t_1 = false;
>>>>>>> a
=======
          {
            t_1 = false;
          }
>>>>>>> static-field-not-found
        }
      }
      var_4_ws_1 = t_1;
      while (var_4_ws_1) {
        boolean t_6;
=======
          t_2 = false;
        }
      }
      var_5_ws_1 = t_2;
      while (var_5_ws_1) {
>>>>>>> unsat error lines
        boolean t_7;
        boolean t_8;
        boolean t_9;
        boolean t_10;
        boolean t_11;
        boolean t_12;
<<<<<<< HEAD

        var_3_fajita_cicle_0 = true;
        roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_0 = true;
        boolean var_5_equalVal;

        t_7 = value_param  ==  null;
        t_8 = var_1_current.roops_core_objectsInstrumented_SinglyLinkedListNode_value  ==  null;

        if (t_7) {
          {
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> static-field-not-found
            {
              if (t_8) {
                {
                  {
                    t_6 = true;
                  }
                }
              } else {
                {
                  {
                    t_6 = false;
                  }
                }
<<<<<<< HEAD
=======
            if (t_8) {
              {
                t_6 = true;
              }
            } else {
              {
                t_6 = false;
>>>>>>> a
=======
>>>>>>> static-field-not-found
=======
        boolean t_13;

        var_4_fajita_cicle_0 = true;
        roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_0 = true;
        boolean var_6_equalVal;

        t_8 = value_param  ==  null;
        t_9 = var_2_current.roops_core_objectsInstrumented_SinglyLinkedListNode_value  ==  null;

        if (t_8) {
          {
            if (t_9) {
              {
                t_7 = true;
              }
            } else {
              {
                t_7 = false;
>>>>>>> unsat error lines
              }
            }
          }
        } else {
          {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
            {
              t_6 = false;
            }
=======
            t_6 = false;
>>>>>>> a
=======
            {
              t_6 = false;
            }
>>>>>>> static-field-not-found
          }
        }

        if (t_6) {
=======
            t_7 = false;
          }
        }

        if (t_7) {
>>>>>>> unsat error lines
          {
            {
              {
                {
                  {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> static-field-not-found
                    {
                      roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_2 = true;
                      var_5_equalVal = true;
                    }
<<<<<<< HEAD
=======
                    roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_2 = true;
                    var_5_equalVal = true;
>>>>>>> a
=======
>>>>>>> static-field-not-found
=======
                    roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_2 = true;
                    var_6_equalVal = true;
>>>>>>> unsat error lines
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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
                    {
                      boolean t_5;

                      roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_3 = true;
                      t_5 = value_param  !=  null;
                      if (t_5) {
=======
                    boolean t_5;

                    roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_3 = true;
                    t_5 = value_param  !=  null;
                    if (t_5) {
                      {
>>>>>>> a
=======
                    {
                      boolean t_5;

                      roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_3 = true;
                      t_5 = value_param  !=  null;
                      if (t_5) {
>>>>>>> static-field-not-found
=======
                    boolean t_6;

                    roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_3 = true;
                    t_6 = value_param  !=  null;
                    if (t_6) {
                      {
>>>>>>> unsat error lines
                        {
                          {
                            {
                              {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
                                {
                                  {
                                    boolean t_4;

                                    roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_4 = true;
                                    t_4 = value_param  ==  var_1_current.roops_core_objectsInstrumented_SinglyLinkedListNode_value;
                                    if (t_4) {
                                      {
                                        {
                                          {
                                            {
                                              {
                                                {
                                                  roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_6 = true;
                                                  var_5_equalVal = true;
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
                                                {
                                                  roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_7 = true;
                                                  var_5_equalVal = false;
                                                }
                                              }
                                            }
=======
                                boolean t_4;

                                roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_4 = true;
                                t_4 = value_param  ==  var_1_current.roops_core_objectsInstrumented_SinglyLinkedListNode_value;
                                if (t_4) {
=======
                                {
>>>>>>> static-field-not-found
                                  {
                                    boolean t_4;

                                    roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_4 = true;
                                    t_4 = value_param  ==  var_1_current.roops_core_objectsInstrumented_SinglyLinkedListNode_value;
                                    if (t_4) {
                                      {
                                        {
                                          {
                                            {
                                              {
                                                {
                                                  roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_6 = true;
                                                  var_5_equalVal = true;
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
<<<<<<< HEAD
                                            roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_7 = true;
                                            var_5_equalVal = false;
>>>>>>> a
=======
                                            {
                                              {
                                                {
                                                  roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_7 = true;
                                                  var_5_equalVal = false;
                                                }
                                              }
                                            }
>>>>>>> static-field-not-found
=======
                                boolean t_5;

                                roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_4 = true;
                                t_5 = value_param  ==  var_2_current.roops_core_objectsInstrumented_SinglyLinkedListNode_value;
                                if (t_5) {
                                  {
                                    {
                                      {
                                        {
                                          {
                                            roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_6 = true;
                                            var_6_equalVal = true;
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
                                            roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_7 = true;
                                            var_6_equalVal = false;
>>>>>>> unsat error lines
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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
                      } else {
=======
                      }
                    } else {
                      {
>>>>>>> a
=======
                      } else {
>>>>>>> static-field-not-found
=======
                      }
                    } else {
                      {
>>>>>>> unsat error lines
                        {
                          {
                            {
                              {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> static-field-not-found
                                {
                                  {
                                    roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_5 = true;
                                    var_5_equalVal = false;
                                  }
                                }
<<<<<<< HEAD
=======
                                roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_5 = true;
                                var_5_equalVal = false;
>>>>>>> a
=======
>>>>>>> static-field-not-found
=======
                                roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_5 = true;
                                var_6_equalVal = false;
>>>>>>> unsat error lines
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
<<<<<<< HEAD
        t_9 = var_5_equalVal  ==  true;

        if (t_9) {
=======
        t_10 = var_6_equalVal  ==  true;

        if (t_10) {
>>>>>>> unsat error lines
          {
            {
              {
                {
                  {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> static-field-not-found
                    {
                      roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_8 = true;
                      var_2_result = true;
                    }
<<<<<<< HEAD
=======
                    roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_8 = true;
                    var_2_result = true;
>>>>>>> a
=======
>>>>>>> static-field-not-found
=======
                    roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_8 = true;
                    var_3_result = true;
>>>>>>> unsat error lines
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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
                    {
                      roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_9 = true;
                    }
=======
                    roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_9 = true;
>>>>>>> a
=======
                    {
                      roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_9 = true;
                    }
>>>>>>> static-field-not-found
=======
                    roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_9 = true;
>>>>>>> unsat error lines
                  }
                }
              }
            }
          }
        }
<<<<<<< HEAD
        var_1_current = var_1_current.roops_core_objectsInstrumented_SinglyLinkedListNode_next.roops_core_objectsInstrumented_SinglyLinkedListNode_next;
        t_11 = var_2_result  ==  false;
        t_12 = var_1_current  !=  null;

        if (t_11) {
          {
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> static-field-not-found
            {
              if (t_12) {
                {
                  {
                    t_10 = true;
                  }
                }
              } else {
                {
                  {
                    t_10 = false;
                  }
                }
<<<<<<< HEAD
=======
            if (t_12) {
              {
                t_10 = true;
              }
            } else {
              {
                t_10 = false;
>>>>>>> a
=======
>>>>>>> static-field-not-found
=======
        var_2_current = var_2_current.roops_core_objectsInstrumented_SinglyLinkedListNode_next.roops_core_objectsInstrumented_SinglyLinkedListNode_next;
        t_12 = var_3_result  ==  false;
        t_13 = var_2_current  !=  null;

        if (t_12) {
          {
            if (t_13) {
              {
                t_11 = true;
              }
            } else {
              {
                t_11 = false;
>>>>>>> unsat error lines
              }
            }
          }
        } else {
          {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
            {
              t_10 = false;
            }
=======
            t_10 = false;
>>>>>>> a
=======
            {
              t_10 = false;
            }
>>>>>>> static-field-not-found
          }
        }
        var_4_ws_1 = t_10;
      }
      t_13 = ! var_3_fajita_cicle_0;
      if (t_13) {
=======
            t_11 = false;
          }
        }
        var_5_ws_1 = t_11;
      }
      t_14 = ! var_4_fajita_cicle_0;
      if (t_14) {
>>>>>>> unsat error lines
        {
          {
            {
              {
                {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
                  {
                    roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_1 = true;
                  }
=======
                  roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_1 = true;
>>>>>>> a
=======
                  {
                    roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_1 = true;
                  }
>>>>>>> static-field-not-found
=======
                  roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_1 = true;
>>>>>>> unsat error lines
                }
              }
            }
          }
        }
      }
    }

<<<<<<< HEAD
    return var_2_result;
=======
    return var_3_result;
>>>>>>> unsat error lines
  }


  /*@ 
    @ requires index  >=  0 && index  <  \reach(this.roops_core_objectsInstrumented_SinglyLinkedList_header, roops.core.objectsInstrumented.SinglyLinkedListNode, roops_core_objectsInstrumented_SinglyLinkedListNode_next).int_size();
    @ ensures \reach(this.roops_core_objectsInstrumented_SinglyLinkedList_header, roops.core.objectsInstrumented.SinglyLinkedListNode, roops_core_objectsInstrumented_SinglyLinkedListNode_next).has(((java.lang.Object)(\result)))  ==  true;
    @ ensures \reach(\result, roops.core.objectsInstrumented.SinglyLinkedListNode, roops_core_objectsInstrumented_SinglyLinkedListNode_next).int_size()  ==  \reach(this.roops_core_objectsInstrumented_SinglyLinkedList_header, roops.core.objectsInstrumented.SinglyLinkedListNode, roops_core_objectsInstrumented_SinglyLinkedListNode_next).int_size() - index;
    @ signals (java.lang.Exception e) false;
    @*/
  public roops.core.objectsInstrumented.SinglyLinkedListNode getNode(int index) {
<<<<<<< HEAD
    boolean t_14;
    boolean t_15;
    boolean t_16;
    roops.core.objectsInstrumented.SinglyLinkedListNode var_6_current = this.roops_core_objectsInstrumented_SinglyLinkedList_header;
    roops.core.objectsInstrumented.SinglyLinkedListNode var_7_result = ((roops.core.objectsInstrumented.SinglyLinkedListNode)(null));
    int var_8_current_index = 0;
    boolean var_9_ws_2;

    t_15 = var_7_result  ==  null;
    t_16 = var_6_current  !=  null;

    if (t_15) {
      {
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> static-field-not-found
        {
          if (t_16) {
            {
              {
                t_14 = true;
              }
            }
          } else {
            {
              {
                t_14 = false;
              }
            }
<<<<<<< HEAD
=======
        if (t_16) {
          {
            t_14 = true;
          }
        } else {
          {
            t_14 = false;
>>>>>>> a
=======
>>>>>>> static-field-not-found
=======
    boolean t_15;
    boolean t_16;
    boolean t_17;
    roops.core.objectsInstrumented.SinglyLinkedListNode var_7_current = this.roops_core_objectsInstrumented_SinglyLinkedList_header;
    roops.core.objectsInstrumented.SinglyLinkedListNode var_8_result = ((roops.core.objectsInstrumented.SinglyLinkedListNode)(null));
    int var_9_current_index = 0;
    boolean var_10_ws_2;

    t_16 = var_8_result  ==  null;
    t_17 = var_7_current  !=  null;

    if (t_16) {
      {
        if (t_17) {
          {
            t_15 = true;
          }
        } else {
          {
            t_15 = false;
>>>>>>> unsat error lines
          }
        }
      }
    } else {
      {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        {
          t_14 = false;
        }
=======
        t_14 = false;
>>>>>>> a
=======
        {
          t_14 = false;
        }
>>>>>>> static-field-not-found
      }
    }
    var_9_ws_2 = t_14;
    while (var_9_ws_2) {
      boolean t_17;
      boolean t_18;
      boolean t_19;
      boolean t_20;

      t_17 = index  ==  var_8_current_index;

      if (t_17) {
=======
        t_15 = false;
      }
    }
    var_10_ws_2 = t_15;
    while (var_10_ws_2) {
      boolean t_18;
      boolean t_19;
      boolean t_20;
      boolean t_21;

      t_18 = index  ==  var_9_current_index;

      if (t_18) {
>>>>>>> unsat error lines
        {
          {
            {
              {
                {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
                  {
                    var_7_result = var_6_current;
                  }
=======
                  var_7_result = var_6_current;
>>>>>>> a
=======
                  {
                    var_7_result = var_6_current;
                  }
>>>>>>> static-field-not-found
=======
                  var_8_result = var_7_current;
>>>>>>> unsat error lines
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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
                  {
                  }
=======
>>>>>>> a
=======
                  {
                  }
>>>>>>> static-field-not-found
=======
>>>>>>> unsat error lines
                }
              }
            }
          }
        }
      }
<<<<<<< HEAD
      var_8_current_index = var_8_current_index + 1;
      var_6_current = var_6_current.roops_core_objectsInstrumented_SinglyLinkedListNode_next;
      t_19 = var_7_result  ==  null;
      t_20 = var_6_current  !=  null;

      if (t_19) {
        {
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> static-field-not-found
          {
            if (t_20) {
              {
                {
                  t_18 = true;
                }
              }
            } else {
              {
                {
                  t_18 = false;
                }
              }
<<<<<<< HEAD
=======
          if (t_20) {
            {
              t_18 = true;
            }
          } else {
            {
              t_18 = false;
>>>>>>> a
=======
>>>>>>> static-field-not-found
=======
      var_9_current_index = var_9_current_index + 1;
      var_7_current = var_7_current.roops_core_objectsInstrumented_SinglyLinkedListNode_next;
      t_20 = var_8_result  ==  null;
      t_21 = var_7_current  !=  null;

      if (t_20) {
        {
          if (t_21) {
            {
              t_19 = true;
            }
          } else {
            {
              t_19 = false;
>>>>>>> unsat error lines
            }
          }
        }
      } else {
        {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
          {
            t_18 = false;
          }
=======
          t_18 = false;
>>>>>>> a
=======
          {
            t_18 = false;
          }
>>>>>>> static-field-not-found
        }
      }
      var_9_ws_2 = t_18;
    }

    return var_7_result;
=======
          t_19 = false;
        }
      }
      var_10_ws_2 = t_19;
    }

    return var_8_result;
>>>>>>> unsat error lines
  }


  /*@ 
    @ requires freshNode  !=  null;
    @ requires \reach(this.roops_core_objectsInstrumented_SinglyLinkedList_header, roops.core.objectsInstrumented.SinglyLinkedListNode, roops_core_objectsInstrumented_SinglyLinkedListNode_next).has(((java.lang.Object)(freshNode)))  ==  false;
    @ ensures \reach(this.roops_core_objectsInstrumented_SinglyLinkedList_header, roops.core.objectsInstrumented.SinglyLinkedListNode, roops_core_objectsInstrumented_SinglyLinkedListNode_next).int_size()  ==  \old(\reach(this.roops_core_objectsInstrumented_SinglyLinkedList_header, roops.core.objectsInstrumented.SinglyLinkedListNode, roops_core_objectsInstrumented_SinglyLinkedListNode_next)).int_size() + 1;
    @ ensures (\forall roops.core.objectsInstrumented.SinglyLinkedListNode n; \old(\reach(this.roops_core_objectsInstrumented_SinglyLinkedList_header, roops.core.objectsInstrumented.SinglyLinkedListNode, roops_core_objectsInstrumented_SinglyLinkedListNode_next)).has(((java.lang.Object)(n))); \reach(this.roops_core_objectsInstrumented_SinglyLinkedList_header, roops.core.objectsInstrumented.SinglyLinkedListNode, roops_core_objectsInstrumented_SinglyLinkedListNode_next).has(((java.lang.Object)(n)))  ==  true);
    @ ensures (\exists roops.core.objectsInstrumented.SinglyLinkedListNode n; \reach(this.roops_core_objectsInstrumented_SinglyLinkedList_header, roops.core.objectsInstrumented.SinglyLinkedListNode, roops_core_objectsInstrumented_SinglyLinkedListNode_next).has(((java.lang.Object)(n))); n.roops_core_objectsInstrumented_SinglyLinkedListNode_next  ==  null && n.roops_core_objectsInstrumented_SinglyLinkedListNode_value  ==  data);
    @ signals (java.lang.Exception e) false;
    @*/
  void insertBack(java.lang.Object data, roops.core.objectsInstrumented.SinglyLinkedListNode freshNode) {
<<<<<<< HEAD
    boolean t_21;

    freshNode.roops_core_objectsInstrumented_SinglyLinkedListNode_value = data;
    freshNode.roops_core_objectsInstrumented_SinglyLinkedListNode_next = ((roops.core.objectsInstrumented.SinglyLinkedListNode)(null));
    t_21 = this.roops_core_objectsInstrumented_SinglyLinkedList_header  ==  null;
    if (t_21) {
=======
    boolean t_22;

    freshNode.roops_core_objectsInstrumented_SinglyLinkedListNode_value = data;
    freshNode.roops_core_objectsInstrumented_SinglyLinkedListNode_next = ((roops.core.objectsInstrumented.SinglyLinkedListNode)(null));
    t_22 = this.roops_core_objectsInstrumented_SinglyLinkedList_header  ==  null;
    if (t_22) {
>>>>>>> unsat error lines
      {
        {
          {
            {
              {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
                {
                  this.roops_core_objectsInstrumented_SinglyLinkedList_header = freshNode;
                }
=======
                this.roops_core_objectsInstrumented_SinglyLinkedList_header = freshNode;
>>>>>>> a
=======
                {
                  this.roops_core_objectsInstrumented_SinglyLinkedList_header = freshNode;
                }
>>>>>>> static-field-not-found
=======
                this.roops_core_objectsInstrumented_SinglyLinkedList_header = freshNode;
>>>>>>> unsat error lines
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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
                {
                  roops.core.objectsInstrumented.SinglyLinkedListNode var_10_current = this.roops_core_objectsInstrumented_SinglyLinkedList_header;
                  boolean var_11_ws_3;

                  var_11_ws_3 = var_10_current.roops_core_objectsInstrumented_SinglyLinkedListNode_next  !=  null;
                  while (var_11_ws_3) {
                    var_10_current = var_10_current.roops_core_objectsInstrumented_SinglyLinkedListNode_next;
                    var_11_ws_3 = var_10_current.roops_core_objectsInstrumented_SinglyLinkedListNode_next  !=  null;
                  }
                  var_10_current.roops_core_objectsInstrumented_SinglyLinkedListNode_next = freshNode;
                }
=======
                roops.core.objectsInstrumented.SinglyLinkedListNode var_10_current = this.roops_core_objectsInstrumented_SinglyLinkedList_header;
                boolean var_11_ws_3;
=======
                {
                  roops.core.objectsInstrumented.SinglyLinkedListNode var_10_current = this.roops_core_objectsInstrumented_SinglyLinkedList_header;
                  boolean var_11_ws_3;
>>>>>>> static-field-not-found

                  var_11_ws_3 = var_10_current.roops_core_objectsInstrumented_SinglyLinkedListNode_next  !=  null;
                  while (var_11_ws_3) {
                    var_10_current = var_10_current.roops_core_objectsInstrumented_SinglyLinkedListNode_next;
                    var_11_ws_3 = var_10_current.roops_core_objectsInstrumented_SinglyLinkedListNode_next  !=  null;
                  }
                  var_10_current.roops_core_objectsInstrumented_SinglyLinkedListNode_next = freshNode;
                }
<<<<<<< HEAD
                var_10_current.roops_core_objectsInstrumented_SinglyLinkedListNode_next = freshNode;
>>>>>>> a
=======
>>>>>>> static-field-not-found
=======
                roops.core.objectsInstrumented.SinglyLinkedListNode var_11_current = this.roops_core_objectsInstrumented_SinglyLinkedList_header;
                boolean var_12_ws_3;

                var_12_ws_3 = var_11_current.roops_core_objectsInstrumented_SinglyLinkedListNode_next  !=  null;
                while (var_12_ws_3) {
                  var_11_current = var_11_current.roops_core_objectsInstrumented_SinglyLinkedListNode_next;
                  var_12_ws_3 = var_11_current.roops_core_objectsInstrumented_SinglyLinkedListNode_next  !=  null;
                }
                var_11_current.roops_core_objectsInstrumented_SinglyLinkedListNode_next = freshNode;
>>>>>>> unsat error lines
              }
            }
          }
        }
      }
    }
  }


  public static void fajita_roopsGoal_initialization() {
    roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_0 = false;
    roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_1 = false;
    roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_2 = false;
    roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_3 = false;
    roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_4 = false;
    roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_5 = false;
    roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_6 = false;
    roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_7 = false;
    roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_8 = false;
    roops.core.objectsInstrumented.SinglyLinkedList.roops_goal_9 = false;
  }

}
