package roops.core.objects;

//@ model import org.jmlspecs.lang.*;


public /*@ nullable_by_default @*/ class SinglyLinkedList extends java.lang.Object {

  public roops.core.objects.SinglyLinkedListNode roops_core_objects_SinglyLinkedList_header;
  /*@ invariant (\forall roops.core.objects.SinglyLinkedListNode n; \reach(this.roops_core_objects_SinglyLinkedList_header, roops.core.objects.SinglyLinkedListNode, roops_core_objects_SinglyLinkedListNode_next).has(((java.lang.Object)(n))); \reach(n.roops_core_objects_SinglyLinkedListNode_next, roops.core.objects.SinglyLinkedListNode, roops_core_objects_SinglyLinkedListNode_next).has(((java.lang.Object)(n)))  ==  false);
    @*/

  public SinglyLinkedList() {
    this.roops_core_objects_SinglyLinkedList_header = ((roops.core.objects.SinglyLinkedListNode)(null));
    {
    }
  }


  /*@ 
    @ requires \reach(this.roops_core_objects_SinglyLinkedList_header, roops.core.objects.SinglyLinkedListNode, roops_core_objects_SinglyLinkedListNode_next).int_size()  ==  100;
    @ ensures \result  ==  false;
    @*/
  public boolean showInstance() {
    return true;
  }


  /*@ 
    @ ensures (\exists roops.core.objects.SinglyLinkedListNode n; \reach(this.roops_core_objects_SinglyLinkedList_header, roops.core.objects.SinglyLinkedListNode, roops_core_objects_SinglyLinkedListNode_next).has(((java.lang.Object)(n))); n.roops_core_objects_SinglyLinkedListNode_value  ==  value_param) <==> (\result  ==  true);
    @ signals (java.lang.RuntimeException e) false;
    @*/
  public boolean contains(/*@ nullable @*/ java.lang.Object value_param) {
    boolean t_168;
    boolean t_169;
    boolean t_170;
    roops.core.objects.SinglyLinkedListNode var_81_current;
    boolean var_82_result;

    var_81_current = this.roops_core_objects_SinglyLinkedList_header;
    var_82_result = false;
    boolean var_83_ws_25;

    t_169 = var_82_result  ==  false;
    t_170 = var_81_current  !=  null;

    if (t_169) {
      {
        if (t_170) {
          {
            t_168 = true;
          }
        } else {
          {
            t_168 = false;
          }
        }
      }
    } else {
      {
        t_168 = false;
      }
    }
    var_83_ws_25 = t_168;
    while (var_83_ws_25) {
      boolean t_173;
      boolean t_174;
      boolean t_175;
      boolean t_176;
      boolean t_177;
      boolean t_178;
      boolean t_179;
      boolean var_84_equalVal;

      t_174 = value_param  ==  null;
      t_175 = var_81_current.roops_core_objects_SinglyLinkedListNode_value  ==  null;

      if (t_174) {
        {
          if (t_175) {
            {
              t_173 = true;
            }
          } else {
            {
              t_173 = false;
            }
          }
        }
      } else {
        {
          t_173 = false;
        }
      }

      if (t_173) {
        {
          {
            {
              {
                {
                  var_84_equalVal = true;
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
                  boolean t_172;

                  t_172 = value_param  !=  null;
                  if (t_172) {
                    {
                      {
                        {
                          {
                            {
                              boolean t_171;

                              t_171 = value_param  ==  var_81_current.roops_core_objects_SinglyLinkedListNode_value;
                              if (t_171) {
                                {
                                  {
                                    {
                                      {
                                        {
                                          var_84_equalVal = true;
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
                                          var_84_equalVal = false;
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
                              var_84_equalVal = false;
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
      t_176 = var_84_equalVal  ==  true;

      if (t_176) {
        {
          {
            {
              {
                {
                  var_82_result = true;
                }
              }
            }
          }
        }
      }
      var_81_current = var_81_current.roops_core_objects_SinglyLinkedListNode_next;
      t_178 = var_82_result  ==  false;
      t_179 = var_81_current  !=  null;

      if (t_178) {
        {
          if (t_179) {
            {
              t_177 = true;
            }
          } else {
            {
              t_177 = false;
            }
          }
        }
      } else {
        {
          t_177 = false;
        }
      }
      var_83_ws_25 = t_177;
    }

    return var_82_result;
  }


  /*@ 
    @ requires index  >=  0 && index  <  \reach(this.roops_core_objects_SinglyLinkedList_header, roops.core.objects.SinglyLinkedListNode, roops_core_objects_SinglyLinkedListNode_next).int_size();
    @ ensures \reach(this.roops_core_objects_SinglyLinkedList_header, roops.core.objects.SinglyLinkedListNode, roops_core_objects_SinglyLinkedListNode_next).has(((java.lang.Object)(\result)))  ==  true;
    @ ensures \reach(\result, roops.core.objects.SinglyLinkedListNode, roops_core_objects_SinglyLinkedListNode_next).int_size()  ==  \reach(this.roops_core_objects_SinglyLinkedList_header, roops.core.objects.SinglyLinkedListNode, roops_core_objects_SinglyLinkedListNode_next).int_size() - index;
    @ signals (java.lang.Exception e) false;
    @*/
  public roops.core.objects.SinglyLinkedListNode getNode(int index) {
    boolean t_180;
    boolean t_181;
    boolean t_182;
    roops.core.objects.SinglyLinkedListNode var_85_current = this.roops_core_objects_SinglyLinkedList_header;
    roops.core.objects.SinglyLinkedListNode var_86_result = ((roops.core.objects.SinglyLinkedListNode)(null));
    int var_87_current_index = 0;
    boolean var_88_ws_26;

    t_181 = var_86_result  ==  null;
    t_182 = var_85_current  !=  null;

    if (t_181) {
      {
        if (t_182) {
          {
            t_180 = true;
          }
        } else {
          {
            t_180 = false;
          }
        }
      }
    } else {
      {
        t_180 = false;
      }
    }
    var_88_ws_26 = t_180;
    while (var_88_ws_26) {
      boolean t_183;
      boolean t_184;
      boolean t_185;
      boolean t_186;

      t_183 = index  ==  var_87_current_index;

      if (t_183) {
        {
          {
            {
              {
                {
                  var_86_result = var_85_current;
                }
              }
            }
          }
        }
      }
      var_87_current_index = var_87_current_index + 1;
      var_85_current = var_85_current.roops_core_objects_SinglyLinkedListNode_next;
      t_185 = var_86_result  ==  null;
      t_186 = var_85_current  !=  null;

      if (t_185) {
        {
          if (t_186) {
            {
              t_184 = true;
            }
          } else {
            {
              t_184 = false;
            }
          }
        }
      } else {
        {
          t_184 = false;
        }
      }
      var_88_ws_26 = t_184;
    }

    return var_86_result;
  }


  /*@ 
    @ requires freshNode  !=  null;
    @ requires \reach(this.roops_core_objects_SinglyLinkedList_header, roops.core.objects.SinglyLinkedListNode, roops_core_objects_SinglyLinkedListNode_next).has(((java.lang.Object)(freshNode)))  ==  false;
    @ ensures \reach(this.roops_core_objects_SinglyLinkedList_header, roops.core.objects.SinglyLinkedListNode, roops_core_objects_SinglyLinkedListNode_next).int_size()  ==  \old(\reach(this.roops_core_objects_SinglyLinkedList_header, roops.core.objects.SinglyLinkedListNode, roops_core_objects_SinglyLinkedListNode_next)).int_size() + 1;
    @ ensures (\forall roops.core.objects.SinglyLinkedListNode n; \old(\reach(this.roops_core_objects_SinglyLinkedList_header, roops.core.objects.SinglyLinkedListNode, roops_core_objects_SinglyLinkedListNode_next)).has(((java.lang.Object)(n))); \reach(this.roops_core_objects_SinglyLinkedList_header, roops.core.objects.SinglyLinkedListNode, roops_core_objects_SinglyLinkedListNode_next).has(((java.lang.Object)(n)))  ==  true);
    @ ensures (\exists roops.core.objects.SinglyLinkedListNode n; \reach(this.roops_core_objects_SinglyLinkedList_header, roops.core.objects.SinglyLinkedListNode, roops_core_objects_SinglyLinkedListNode_next).has(((java.lang.Object)(n))); n.roops_core_objects_SinglyLinkedListNode_next  ==  null && n.roops_core_objects_SinglyLinkedListNode_value  ==  data);
    @ signals (java.lang.Exception e) false;
    @*/
  void insertBack(java.lang.Object data, roops.core.objects.SinglyLinkedListNode freshNode) {
    boolean t_187;

    freshNode.roops_core_objects_SinglyLinkedListNode_value = data;
    freshNode.roops_core_objects_SinglyLinkedListNode_next = ((roops.core.objects.SinglyLinkedListNode)(null));
    t_187 = this.roops_core_objects_SinglyLinkedList_header  ==  null;
    if (t_187) {
      {
        {
          {
            {
              {
                this.roops_core_objects_SinglyLinkedList_header = freshNode;
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
                roops.core.objects.SinglyLinkedListNode var_89_current = this.roops_core_objects_SinglyLinkedList_header;
                boolean var_90_ws_27;

                var_90_ws_27 = var_89_current.roops_core_objects_SinglyLinkedListNode_next  !=  null;
                while (var_90_ws_27) {
                  var_89_current = var_89_current.roops_core_objects_SinglyLinkedListNode_next;
                  var_90_ws_27 = var_89_current.roops_core_objects_SinglyLinkedListNode_next  !=  null;
                }
                var_89_current.roops_core_objects_SinglyLinkedListNode_next = freshNode;
              }
            }
          }
        }
      }
    }
  }

}
