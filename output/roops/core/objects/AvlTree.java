package roops.core.objects;

//@ model import org.jmlspecs.lang.*;


public /*@ nullable_by_default @*/ class AvlTree extends java.lang.Object {

  public roops.core.objects.AvlNode roops_core_objects_AvlTree_root;
  /*@ invariant (\forall roops.core.objects.AvlNode x; \reach(this.roops_core_objects_AvlTree_root, roops.core.objects.AvlNode, roops_core_objects_AvlNode_right + roops_core_objects_AvlNode_left).has(((java.lang.Object)(x))); (\reach(x.roops_core_objects_AvlNode_left, roops.core.objects.AvlNode, roops_core_objects_AvlNode_left + roops_core_objects_AvlNode_right).has(((java.lang.Object)(x)))  ==  false) && (\reach(x.roops_core_objects_AvlNode_right, roops.core.objects.AvlNode, roops_core_objects_AvlNode_left + roops_core_objects_AvlNode_right).has(((java.lang.Object)(x)))  ==  false) && ((x.roops_core_objects_AvlNode_left  ==  null && x.roops_core_objects_AvlNode_right  ==  null) ==> x.roops_core_objects_AvlNode_height  ==  0) && ((x.roops_core_objects_AvlNode_left  ==  null && x.roops_core_objects_AvlNode_right  !=  null) ==> (x.roops_core_objects_AvlNode_height  ==  1 && x.roops_core_objects_AvlNode_right.roops_core_objects_AvlNode_height  ==  0)) && ((x.roops_core_objects_AvlNode_left  !=  null && x.roops_core_objects_AvlNode_right  ==  null) ==> (x.roops_core_objects_AvlNode_height  ==  1 && x.roops_core_objects_AvlNode_left.roops_core_objects_AvlNode_height  ==  0)) && ((x.roops_core_objects_AvlNode_left  !=  null && x.roops_core_objects_AvlNode_right  !=  null && x.roops_core_objects_AvlNode_left.roops_core_objects_AvlNode_height  >  x.roops_core_objects_AvlNode_right.roops_core_objects_AvlNode_height) ==> (x.roops_core_objects_AvlNode_height  ==  x.roops_core_objects_AvlNode_left.roops_core_objects_AvlNode_height + 1 && x.roops_core_objects_AvlNode_left.roops_core_objects_AvlNode_height - x.roops_core_objects_AvlNode_right.roops_core_objects_AvlNode_height  <=  1)) && ((x.roops_core_objects_AvlNode_left  !=  null && x.roops_core_objects_AvlNode_right  !=  null && x.roops_core_objects_AvlNode_left.roops_core_objects_AvlNode_height  <=  x.roops_core_objects_AvlNode_right.roops_core_objects_AvlNode_height) ==> (x.roops_core_objects_AvlNode_height  ==  x.roops_core_objects_AvlNode_right.roops_core_objects_AvlNode_height + 1 && x.roops_core_objects_AvlNode_right.roops_core_objects_AvlNode_height - x.roops_core_objects_AvlNode_left.roops_core_objects_AvlNode_height  <=  1)));
    @*/
  /*@ invariant (\forall roops.core.objects.AvlNode x; \reach(this.roops_core_objects_AvlTree_root, roops.core.objects.AvlNode, roops_core_objects_AvlNode_right + roops_core_objects_AvlNode_left).has(((java.lang.Object)(x))); (\forall roops.core.objects.AvlNode y; \reach(x.roops_core_objects_AvlNode_left, roops.core.objects.AvlNode, roops_core_objects_AvlNode_left + roops_core_objects_AvlNode_right).has(((java.lang.Object)(y))); y.roops_core_objects_AvlNode_element  <  x.roops_core_objects_AvlNode_element));
    @*/
  /*@ invariant (\forall roops.core.objects.AvlNode x; \reach(this.roops_core_objects_AvlTree_root, roops.core.objects.AvlNode, roops_core_objects_AvlNode_right + roops_core_objects_AvlNode_left).has(((java.lang.Object)(x))); (\forall roops.core.objects.AvlNode y; \reach(x.roops_core_objects_AvlNode_right, roops.core.objects.AvlNode, roops_core_objects_AvlNode_left + roops_core_objects_AvlNode_right).has(((java.lang.Object)(y))); x.roops_core_objects_AvlNode_element  <  y.roops_core_objects_AvlNode_element));
    @*/
  /*@ invariant (\forall roops.core.objects.AvlNode x; \reach(this.roops_core_objects_AvlTree_root, roops.core.objects.AvlNode, roops_core_objects_AvlNode_right + roops_core_objects_AvlNode_left).has(((java.lang.Object)(x))); x.roops_core_objects_AvlNode_element  >=  0);
    @*/

  public AvlTree() {
    this.roops_core_objects_AvlTree_root = ((roops.core.objects.AvlNode)(null));
    {
    }
  }


  /*@ 
    @ ensures \result  !=  x ==> (\forall roops.core.objects.AvlNode a; \reach(this.roops_core_objects_AvlTree_root, roops.core.objects.AvlNode, roops_core_objects_AvlNode_right + roops_core_objects_AvlNode_left).has(((java.lang.Object)(a))); a.roops_core_objects_AvlNode_element  !=  x);
    @ ensures (\forall roops.core.objects.AvlNode a; \reach(this.roops_core_objects_AvlTree_root, roops.core.objects.AvlNode, roops_core_objects_AvlNode_right + roops_core_objects_AvlNode_left).has(((java.lang.Object)(a))); a.roops_core_objects_AvlNode_element  !=  x) ==> \result  ==  -1;
    @ signals (java.lang.Exception e) false;
    @*/
  public int find(final int x) {
    roops.core.objects.AvlNode var_1_n = this.roops_core_objects_AvlTree_root;
    boolean var_2_ws_1;

    var_2_ws_1 = var_1_n  !=  null;
    while (var_2_ws_1) {
      boolean t_2;

      t_2 = x  <  var_1_n.roops_core_objects_AvlNode_element;

      if (t_2) {
        {
          {
            {
              {
                {
                  var_1_n = var_1_n.roops_core_objects_AvlNode_right;
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
                  boolean t_1;

                  t_1 = x  >  var_1_n.roops_core_objects_AvlNode_element;
                  if (t_1) {
                    {
                      {
                        {
                          {
                            {
                              var_1_n = var_1_n.roops_core_objects_AvlNode_right.roops_core_objects_AvlNode_right;
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
                              return var_1_n.roops_core_objects_AvlNode_element;
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
      var_2_ws_1 = var_1_n  !=  null;
    }

    return -1;
  }


  /*@ 
    @ ensures \reach(this.roops_core_objects_AvlTree_root, roops.core.objects.AvlNode, roops_core_objects_AvlNode_right + roops_core_objects_AvlNode_left).int_size()  ==  0 ==> \result  ==  -1;
    @ ensures \reach(this.roops_core_objects_AvlTree_root, roops.core.objects.AvlNode, roops_core_objects_AvlNode_right + roops_core_objects_AvlNode_left).int_size()  >  0 ==> (\exists roops.core.objects.AvlNode min_node; \reach(this.roops_core_objects_AvlTree_root, roops.core.objects.AvlNode, roops_core_objects_AvlNode_right + roops_core_objects_AvlNode_left).has(((java.lang.Object)(min_node))); min_node.roops_core_objects_AvlNode_element  ==  \result && (\forall roops.core.objects.AvlNode node; \reach(this.roops_core_objects_AvlTree_root, roops.core.objects.AvlNode, roops_core_objects_AvlNode_right + roops_core_objects_AvlNode_left).has(((java.lang.Object)(node))); \result  <=  node.roops_core_objects_AvlNode_element));
    @ signals (java.lang.Exception e) false;
    @*/
  public int findMin() {
    boolean t_3;
    roops.core.objects.AvlNode var_3_n = this.roops_core_objects_AvlTree_root;

    t_3 = var_3_n  ==  null;

    if (t_3) {
      {
        {
          {
            {
              {
                return -1;
              }
            }
          }
        }
      }
    }
    boolean var_4_ws_2;

    var_4_ws_2 = var_3_n.roops_core_objects_AvlNode_left  !=  null;
    while (var_4_ws_2) {
      var_3_n = var_3_n.roops_core_objects_AvlNode_left;
      var_4_ws_2 = var_3_n.roops_core_objects_AvlNode_left  !=  null;
    }

    return var_3_n.roops_core_objects_AvlNode_element;
  }


  /*@ 
    @ requires true;
    @ ensures \reach(this.roops_core_objects_AvlTree_root, roops.core.objects.AvlNode, roops_core_objects_AvlNode_right + roops_core_objects_AvlNode_left).int_size()  ==  0 ==> \result  ==  -1;
    @ ensures \reach(this.roops_core_objects_AvlTree_root, roops.core.objects.AvlNode, roops_core_objects_AvlNode_right + roops_core_objects_AvlNode_left).int_size()  >  0 ==> (\exists roops.core.objects.AvlNode max_node; \reach(this.roops_core_objects_AvlTree_root, roops.core.objects.AvlNode, roops_core_objects_AvlNode_right + roops_core_objects_AvlNode_left).has(((java.lang.Object)(max_node))); max_node.roops_core_objects_AvlNode_element  ==  \result && (\forall roops.core.objects.AvlNode node; \reach(this.roops_core_objects_AvlTree_root, roops.core.objects.AvlNode, roops_core_objects_AvlNode_right + roops_core_objects_AvlNode_left).has(((java.lang.Object)(node))); \result  >=  node.roops_core_objects_AvlNode_element));
    @ signals (java.lang.Exception e) false;
    @*/
  public int findMax() {
    boolean t_4;
    roops.core.objects.AvlNode var_5_n = this.roops_core_objects_AvlTree_root;

    t_4 = var_5_n  ==  null;

    if (t_4) {
      {
        {
          {
            {
              {
                return -1;
              }
            }
          }
        }
      }
    }
    boolean var_6_ws_3;

    var_6_ws_3 = var_5_n.roops_core_objects_AvlNode_right  !=  null;
    while (var_6_ws_3) {
      var_5_n = var_5_n.roops_core_objects_AvlNode_right;
      var_6_ws_3 = var_5_n.roops_core_objects_AvlNode_right  !=  null;
    }

    return var_5_n.roops_core_objects_AvlNode_element;
  }


  private static roops.core.objects.AvlNode doubleWithLeftChild(final roops.core.objects.AvlNode k3) {
    roops.core.objects.AvlNode t_5;
    roops.core.objects.AvlNode t_6;

    t_5 = roops.core.objects.AvlTree.rotateWithRightChild(k3.roops_core_objects_AvlNode_left);
    k3.roops_core_objects_AvlNode_left = t_5;
    t_6 = roops.core.objects.AvlTree.rotateWithLeftChild(k3);

    return t_6;
  }


  private static roops.core.objects.AvlNode doubleWithRightChild(final roops.core.objects.AvlNode k1) {
    roops.core.objects.AvlNode t_7;
    roops.core.objects.AvlNode t_8;

    t_7 = roops.core.objects.AvlTree.rotateWithLeftChild(k1.roops_core_objects_AvlNode_right);
    k1.roops_core_objects_AvlNode_right = t_7;
    t_8 = roops.core.objects.AvlTree.rotateWithRightChild(k1);

    return t_8;
  }


  /*@ 
    @ assignable \nothing;
    @*/
  private static int height(final roops.core.objects.AvlNode t) {
    int t_9;
    boolean t_10;

    t_10 = t  ==  null;
    if (t_10) {
      {
        t_9 = -1;
      }
    } else {
      {
        t_9 = t.roops_core_objects_AvlNode_height;
      }
    }

    return t_9;
  }


  /*@ 
    @ assignable \nothing;
    @*/
  private static int max(final int lhs, final int rhs) {
    int t_11;
    boolean t_12;

    t_12 = lhs  >  rhs;
    if (t_12) {
      {
        t_11 = lhs;
      }
    } else {
      {
        t_11 = rhs;
      }
    }

    return t_11;
  }


  private static roops.core.objects.AvlNode rotateWithLeftChild(final roops.core.objects.AvlNode k2) {
    int t_13;
    int t_14;
    int t_15;
    int t_16;
    int t_17;
    final roops.core.objects.AvlNode var_7_k1 = k2.roops_core_objects_AvlNode_left;

    k2.roops_core_objects_AvlNode_left = var_7_k1.roops_core_objects_AvlNode_right;
    var_7_k1.roops_core_objects_AvlNode_right = k2;
    t_13 = roops.core.objects.AvlTree.height(k2.roops_core_objects_AvlNode_left);
    t_14 = roops.core.objects.AvlTree.height(k2.roops_core_objects_AvlNode_right);
    t_15 = roops.core.objects.AvlTree.max(t_13, t_14);
    k2.roops_core_objects_AvlNode_height = t_15 + 1;
    t_16 = roops.core.objects.AvlTree.height(var_7_k1.roops_core_objects_AvlNode_left);
    t_17 = roops.core.objects.AvlTree.max(t_16, k2.roops_core_objects_AvlNode_height);
    var_7_k1.roops_core_objects_AvlNode_height = t_17 + 1;

    return var_7_k1;
  }


  private static roops.core.objects.AvlNode rotateWithRightChild(final roops.core.objects.AvlNode k1) {
    int t_18;
    int t_19;
    int t_20;
    int t_21;
    int t_22;
    final roops.core.objects.AvlNode var_8_k2 = k1.roops_core_objects_AvlNode_right;

    k1.roops_core_objects_AvlNode_right = var_8_k2.roops_core_objects_AvlNode_left;
    var_8_k2.roops_core_objects_AvlNode_left = k1;
    t_18 = roops.core.objects.AvlTree.height(k1.roops_core_objects_AvlNode_left);
    t_19 = roops.core.objects.AvlTree.height(k1.roops_core_objects_AvlNode_right);
    t_20 = roops.core.objects.AvlTree.max(t_18, t_19);
    k1.roops_core_objects_AvlNode_height = t_20 + 1;
    t_21 = roops.core.objects.AvlTree.height(var_8_k2.roops_core_objects_AvlNode_right);
    t_22 = roops.core.objects.AvlTree.max(t_21, k1.roops_core_objects_AvlNode_height);
    var_8_k2.roops_core_objects_AvlNode_height = t_22 + 1;

    return var_8_k2;
  }


  /*@ 
    @ requires (n  ==  this.roops_core_objects_AvlTree_root);
    @ requires (aux  !=  null);
    @ requires (\reach(this.roops_core_objects_AvlTree_root, roops.core.objects.AvlNode, roops_core_objects_AvlNode_right + roops_core_objects_AvlNode_left).has(((java.lang.Object)(aux)))  ==  false);
    @ ensures (\exists roops.core.objects.AvlNode a; \reach(\result, roops.core.objects.AvlNode, roops_core_objects_AvlNode_right + roops_core_objects_AvlNode_left).has(((java.lang.Object)(a))); a.roops_core_objects_AvlNode_element  ==  x);
    @ ensures (\forall roops.core.objects.AvlNode a; \old(\reach(this.roops_core_objects_AvlTree_root, roops.core.objects.AvlNode, roops_core_objects_AvlNode_right + roops_core_objects_AvlNode_left)).has(((java.lang.Object)(a))); \reach(\result, roops.core.objects.AvlNode, roops_core_objects_AvlNode_right + roops_core_objects_AvlNode_left).has(((java.lang.Object)(a))));
    @ signals (java.lang.Exception e) false;
    @*/
  public roops.core.objects.AvlNode privateinsert(final int x, roops.core.objects.AvlNode n, roops.core.objects.AvlNode aux) {
    boolean t_41;
    int t_42;
    int t_43;
    int t_44;
    roops.core.objects.AvlNode var_9_t = n;

    t_41 = var_9_t  !=  null;

    if (t_41) {
      {
        {
          {
            {
              {
                aux.roops_core_objects_AvlNode_element = x;
                aux.roops_core_objects_AvlNode_left = ((roops.core.objects.AvlNode)(null));
                aux.roops_core_objects_AvlNode_right = ((roops.core.objects.AvlNode)(null));
                aux.roops_core_objects_AvlNode_height = 0;
                var_9_t = aux;
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
                boolean t_40;

                t_40 = x  <  var_9_t.roops_core_objects_AvlNode_element;
                if (t_40) {
                  {
                    {
                      {
                        {
                          {
                            roops.core.objects.AvlNode t_23;
                            int t_27;
                            int t_28;
                            int t_29;
                            boolean t_30;

                            t_23 = this.privateinsert(x, var_9_t.roops_core_objects_AvlNode_left, aux);
                            var_9_t.roops_core_objects_AvlNode_left = t_23;
                            t_27 = roops.core.objects.AvlTree.height(var_9_t.roops_core_objects_AvlNode_left);
                            t_28 = roops.core.objects.AvlTree.height(var_9_t.roops_core_objects_AvlNode_right);
                            t_29 = t_27 - t_28;
                            t_30 = t_29  ==  2;
                            if (t_30) {
                              {
                                {
                                  {
                                    {
                                      {
                                        boolean t_26;

                                        t_26 = x  <  var_9_t.roops_core_objects_AvlNode_left.roops_core_objects_AvlNode_element;
                                        if (t_26) {
                                          {
                                            {
                                              {
                                                {
                                                  {
                                                    roops.core.objects.AvlNode t_24;

                                                    t_24 = roops.core.objects.AvlTree.rotateWithLeftChild(var_9_t);
                                                    var_9_t = t_24;
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
                                                    roops.core.objects.AvlNode t_25;

                                                    t_25 = roops.core.objects.AvlTree.doubleWithLeftChild(var_9_t);
                                                    var_9_t = t_25;
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
                            boolean t_39;

                            t_39 = x  >  var_9_t.roops_core_objects_AvlNode_element;
                            if (t_39) {
                              {
                                {
                                  {
                                    {
                                      {
                                        roops.core.objects.AvlNode t_31;
                                        int t_35;
                                        int t_36;
                                        int t_37;
                                        boolean t_38;

                                        t_31 = this.privateinsert(x, var_9_t.roops_core_objects_AvlNode_right, aux);
                                        var_9_t.roops_core_objects_AvlNode_right = t_31;
                                        t_35 = roops.core.objects.AvlTree.height(var_9_t.roops_core_objects_AvlNode_right);
                                        t_36 = roops.core.objects.AvlTree.height(var_9_t.roops_core_objects_AvlNode_left);
                                        t_37 = t_35 - t_36;
                                        t_38 = t_37  ==  2;
                                        if (t_38) {
                                          {
                                            {
                                              {
                                                {
                                                  {
                                                    boolean t_34;

                                                    t_34 = x  >  var_9_t.roops_core_objects_AvlNode_right.roops_core_objects_AvlNode_element;
                                                    if (t_34) {
                                                      {
                                                        {
                                                          {
                                                            {
                                                              {
                                                                roops.core.objects.AvlNode t_32;

                                                                t_32 = roops.core.objects.AvlTree.rotateWithRightChild(var_9_t);
                                                                var_9_t = t_32;
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
                                                                roops.core.objects.AvlNode t_33;

                                                                t_33 = roops.core.objects.AvlTree.doubleWithRightChild(var_9_t);
                                                                var_9_t = t_33;
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
              }
            }
          }
        }
      }
    }
    t_42 = roops.core.objects.AvlTree.height(var_9_t.roops_core_objects_AvlNode_left);
    t_43 = roops.core.objects.AvlTree.height(var_9_t.roops_core_objects_AvlNode_right);
    t_44 = roops.core.objects.AvlTree.max(t_42, t_43);
    var_9_t.roops_core_objects_AvlNode_height = t_44 + 1;

    return var_9_t;
  }

}
