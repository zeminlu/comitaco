<<<<<<< HEAD
// This is mutant program.
// Author : ysma

package roops.core.objects;


import roops.core.objects.SinglyLinkedListNode;
import roops.core.objects.BugLineMarker;


/*@ nullable_by_default @*/
public class SinglyLinkedList {

    /*@
        @ invariant (\forall SinglyLinkedListNode n; \reach(header, SinglyLinkedListNode, next).has(n); \reach(n.next, SinglyLinkedListNode, next).has(n)==false);
        @*/
    public roops.core.objects.SinglyLinkedListNode header;

    public SinglyLinkedList () {
    }

//----------------- showInstance --------------------//
    /*@ requires \reach(this.header, SinglyLinkedListNode, next).int_size() == 100;
        @ ensures \result == false;
        @*/
    public boolean showInstance () {
        return true;
    }

//-------------------- contains -------------------------//

    /*@
        @ ensures (\exists SinglyLinkedListNode n; \reach(this.header, SinglyLinkedListNode, next).has(n); n.value==value_param)
        @     <==> (\result==true);
        @ signals (RuntimeException e) false;
        @*/
    public boolean contains (  /*@nullable@*/ java.lang.Object value_param) {
        fajita_roopsGoal_initialization ();
        BugLineMarker dummy;
        roops.core.objects.SinglyLinkedListNode current;
        boolean result;
        // current = this.header.next; //mutGenLimit 1
        current
            = this.header;
        result = false;
        {
            boolean terminatesInTime = false;
            if ( result == false && current != null ) {
                roops_goal_0 = true;
                boolean equalVal;
                if ( value_param == null && current.value == null ) {
                    roops_goal_2 = true;
                    equalVal = false; //mutGenLimit 1
                    // equalVal = true;
                } else {
                    roops_goal_3 = true;
                    if ( value_param != null ) {
                        roops_goal_4 = true;
                        if ( value_param == current.value ) {
                            roops_goal_6 = true;
                            equalVal = true;
                        } else {
                            roops_goal_7 = true;
                            equalVal = false;
                        }
                    } else {
                        roops_goal_5 = true;
                        equalVal = false;
                    }
                }
                if ( equalVal == true ) {
                    roops_goal_8 = true;
                    result = true;
                } else {
                    roops_goal_9 = true;
                }
                // current = current.next.next; //mutGenLimit 1
                current
                    = current.next;
            } else {
                roops_goal_1 = true;
            }
            if ( result == false && current != null ) {
                roops_goal_10 = true;
                boolean equalVal;
                if ( value_param == null && current.value == null ) {
                    roops_goal_12 = true;
                    equalVal = false; //mutGenLimit 1
                    // equalVal = true;
                } else {
                    roops_goal_13 = true;
                    if ( value_param != null ) {
                        roops_goal_14 = true;
                        if ( value_param == current.value ) {
                            roops_goal_16 = true;
                            equalVal = true;
                        } else {
                            roops_goal_17 = true;
                            equalVal = false;
                        }
                    } else {
                        roops_goal_15 = true;
                        equalVal = false;
                    }
                }
                if ( equalVal == true ) {
                    roops_goal_18 = true;
                    result = true;
                } else {
                    roops_goal_19 = true;
                }
                // current = current.next.next; //mutGenLimit 1
                current
                    = current.next;
            } else {
                roops_goal_11 = true;
            }
            if ( result == false && current != null ) {
                roops_goal_20 = true;
                boolean equalVal;
                if ( value_param == null && current.value == null ) {
                    roops_goal_22 = true;
                    equalVal = false; //mutGenLimit 1
                    // equalVal = true;
                } else {
                    roops_goal_23 = true;
                    if ( value_param != null ) {
                        roops_goal_24 = true;
                        if ( value_param == current.value ) {
                            roops_goal_26 = true;
                            equalVal = true;
                        } else {
                            roops_goal_27 = true;
                            equalVal = false;
                        }
                    } else {
                        roops_goal_25 = true;
                        equalVal = false;
                    }
                }
                if ( equalVal == true ) {
                    roops_goal_28 = true;
                    result = true;
                } else {
                    roops_goal_29 = true;
                }
                // current = current.next.next; //mutGenLimit 1
                current
                    = current.next;
            } else {
                roops_goal_21 = true;
            }
            if ( result == false && current != null ) {
                roops_goal_30 = true;
                boolean equalVal;
                if ( value_param == null && current.value == null ) {
                    roops_goal_32 = true;
                    equalVal = false; //mutGenLimit 1
                    // equalVal = true;
                } else {
                    roops_goal_33 = true;
                    if ( value_param != null ) {
                        roops_goal_34 = true;
                        if ( value_param == current.value ) {
                            roops_goal_36 = true;
                            equalVal = true;
                        } else {
                            roops_goal_37 = true;
                            equalVal = false;
                        }
                    } else {
                        roops_goal_35 = true;
                        equalVal = false;
                    }
                }
                if ( equalVal == true ) {
                    roops_goal_38 = true;
                    result = true;
                } else {
                    roops_goal_39 = true;
                }
                // current = current.next.next; //mutGenLimit 1
                current
                    = current.next;
            } else {
                roops_goal_31 = true;
            }
            if ( result == false && current != null ) {
                roops_goal_40 = true;
                boolean equalVal;
                if ( value_param == null && current.value == null ) {
                    roops_goal_42 = true;
                    equalVal = false; //mutGenLimit 1
                    // equalVal = true;
                } else {
                    roops_goal_43 = true;
                    if ( value_param != null ) {
                        roops_goal_44 = true;
                        if ( value_param == current.value ) {
                            roops_goal_46 = true;
                            equalVal = true;
                        } else {
                            roops_goal_47 = true;
                            equalVal = false;
                        }
                    } else {
                        roops_goal_45 = true;
                        equalVal = false;
                    }
                }
                if ( equalVal == true ) {
                    roops_goal_48 = true;
                    result = true;
                } else {
                    roops_goal_49 = true;
                }
                // current = current.next.next; //mutGenLimit 1
                current
                    = current.next;
            } else {
                roops_goal_41 = true;
            }
            if ( result == false && current != null ) {
                roops_goal_50 = true;
                boolean equalVal;
                if ( value_param == null && current.value == null ) {
                    roops_goal_52 = true;
                    equalVal = false; //mutGenLimit 1
                    // equalVal = true;
                } else {
                    roops_goal_53 = true;
                    if ( value_param != null ) {
                        roops_goal_54 = true;
                        if ( value_param == current.value ) {
                            roops_goal_56 = true;
                            equalVal = true;
                        } else {
                            roops_goal_57 = true;
                            equalVal = false;
                        }
                    } else {
                        roops_goal_55 = true;
                        equalVal = false;
                    }
                }
                if ( equalVal == true ) {
                    roops_goal_58 = true;
                    result = true;
                } else {
                    roops_goal_59 = true;
                }
                // current = current.next.next; //mutGenLimit 1
                current
                    = current.next;
            } else {
                roops_goal_51 = true;
            }
            if ( result == false && current != null ) {
                roops_goal_60 = true;
                boolean equalVal;
                if ( value_param == null && current.value == null ) {
                    roops_goal_62 = true;
                    equalVal = false; //mutGenLimit 1
                    // equalVal = true;
                } else {
                    roops_goal_63 = true;
                    if ( value_param != null ) {
                        roops_goal_64 = true;
                        if ( value_param == current.value ) {
                            roops_goal_66 = true;
                            equalVal = true;
                        } else {
                            roops_goal_67 = true;
                            equalVal = false;
                        }
                    } else {
                        roops_goal_65 = true;
                        equalVal = false;
                    }
                }
                if ( equalVal == true ) {
                    roops_goal_68 = true;
                    result = true;
                } else {
                    roops_goal_69 = true;
                }
                // current = current.next.next; //mutGenLimit 1
                current
                    = current.next;
            } else {
                roops_goal_61 = true;
            }
            if ( result == false && current != null ) {
                roops_goal_70 = true;
                boolean equalVal;
                if ( value_param == null && current.value == null ) {
                    roops_goal_72 = true;
                    equalVal = false; //mutGenLimit 1
                    // equalVal = true;
                } else {
                    roops_goal_73 = true;
                    if ( value_param != null ) {
                        roops_goal_74 = true;
                        if ( value_param == current.value ) {
                            roops_goal_76 = true;
                            equalVal = true;
                        } else {
                            roops_goal_77 = true;
                            equalVal = false;
                        }
                    } else {
                        roops_goal_75 = true;
                        equalVal = false;
                    }
                }
                if ( equalVal == true ) {
                    roops_goal_78 = true;
                    result = true;
                } else {
                    roops_goal_79 = true;
                }
                // current = current.next.next; //mutGenLimit 1
                current
                    = current.next;
            } else {
                roops_goal_71 = true;
            }
            if ( result == false && current != null ) {
                roops_goal_80 = true;
                terminatesInTime = true;
            } else {
                roops_goal_81 = true;
            }
        }
        return result;
    }
    /*@
        @ requires index>=0 && index<\reach(this.header, SinglyLinkedListNode, next).int_size();
        @ ensures \reach(this.header, SinglyLinkedListNode, next).has(\result)==true;
        @ ensures \reach(\result, SinglyLinkedListNode, next).int_size() == \reach(this.header, SinglyLinkedListNode, next).int_size()-index;
        @ signals (Exception e) false;
        @*/
    public roops.core.objects.SinglyLinkedListNode getNode ( int index) {
        roops.core.objects.SinglyLinkedListNode current = header;
        roops.core.objects.SinglyLinkedListNode result = null;
        int current_index = 0;
        {
            boolean terminatesInTime = false;
            if ( result == null && current != null ) {
                if ( index == current_index ) {
                    result = current;
                } else {
                }
                current_index = current_index + 1;
                current = current.next;
            } else {
            }
            if ( result == null && current != null ) {
                if ( index == current_index ) {
                    result = current;
                } else {
                }
                current_index = current_index + 1;
                current = current.next;
            } else {
            }
            if ( result == null && current != null ) {
                if ( index == current_index ) {
                    result = current;
                } else {
                }
                current_index = current_index + 1;
                current = current.next;
            } else {
            }
            if ( result == null && current != null ) {
                if ( index == current_index ) {
                    result = current;
                } else {
                }
                current_index = current_index + 1;
                current = current.next;
            } else {
            }
            if ( result == null && current != null ) {
                if ( index == current_index ) {
                    result = current;
                } else {
                }
                current_index = current_index + 1;
                current = current.next;
            } else {
            }
            if ( result == null && current != null ) {
                if ( index == current_index ) {
                    result = current;
                } else {
                }
                current_index = current_index + 1;
                current = current.next;
            } else {
            }
            if ( result == null && current != null ) {
                if ( index == current_index ) {
                    result = current;
                } else {
                }
                current_index = current_index + 1;
                current = current.next;
            } else {
            }
            if ( result == null && current != null ) {
                if ( index == current_index ) {
                    result = current;
                } else {
                }
                current_index = current_index + 1;
                current = current.next;
            } else {
            }
            if ( result == null && current != null ) {
                terminatesInTime = true;
            } else {
            }
        }
        return result;
    }

//------------------------ insertBack --------------------------//
//Due to jml4c the ensures clauses must be in that order :(
    /*@
        @ requires freshNode!=null;
        @ requires \reach(header, SinglyLinkedListNode, next).has(freshNode)==false;
        @ ensures \reach(header, SinglyLinkedListNode, next).int_size()==\old(\reach(header, SinglyLinkedListNode, next)).int_size()+1;
        @ ensures (\forall SinglyLinkedListNode n;
        @            \old(\reach(header, SinglyLinkedListNode, next)).has(n);
        @      \reach(header, SinglyLinkedListNode, next).has(n)==true
        @         );
        @ ensures (\exists SinglyLinkedListNode n;
        @            \reach(header, SinglyLinkedListNode, next).has(n);
        @            n.next==null && n.value==data);
        @ signals (Exception e) false;
        @*/
    void insertBack ( java.lang.Object data, roops.core.objects.SinglyLinkedListNode freshNode) {
        freshNode.value = data;
        freshNode.next = null;
        if ( this.header == null ) {
            this.header = freshNode;
        } else {
            roops.core.objects.SinglyLinkedListNode current = this.header;
            {
                boolean terminatesInTime = false;
                if ( current.next != null ) {
                    current = current.next;
                } else {
                }
                if ( current.next != null ) {
                    current = current.next;
                } else {
                }
                if ( current.next != null ) {
                    current = current.next;
                } else {
                }
                if ( current.next != null ) {
                    current = current.next;
                } else {
                }
                if ( current.next != null ) {
                    current = current.next;
                } else {
                }
                if ( current.next != null ) {
                    current = current.next;
                } else {
                }
                if ( current.next != null ) {
                    current = current.next;
                } else {
                }
                if ( current.next != null ) {
                    current = current.next;
                } else {
                }
                if ( current.next != null ) {
                    terminatesInTime = true;
                } else {
                }
            }
            current.next = freshNode;
        }
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

    public static boolean roops_goal_34;

    public static boolean roops_goal_35;

    public static boolean roops_goal_36;

    public static boolean roops_goal_37;

    public static boolean roops_goal_38;

    public static boolean roops_goal_39;

    public static boolean roops_goal_40;

    public static boolean roops_goal_41;

    public static boolean roops_goal_42;

    public static boolean roops_goal_43;

    public static boolean roops_goal_44;

    public static boolean roops_goal_45;

    public static boolean roops_goal_46;

    public static boolean roops_goal_47;

    public static boolean roops_goal_48;

    public static boolean roops_goal_49;

    public static boolean roops_goal_50;

    public static boolean roops_goal_51;

    public static boolean roops_goal_52;

    public static boolean roops_goal_53;

    public static boolean roops_goal_54;

    public static boolean roops_goal_55;

    public static boolean roops_goal_56;

    public static boolean roops_goal_57;

    public static boolean roops_goal_58;

    public static boolean roops_goal_59;

    public static boolean roops_goal_60;

    public static boolean roops_goal_61;

    public static boolean roops_goal_62;

    public static boolean roops_goal_63;

    public static boolean roops_goal_64;

    public static boolean roops_goal_65;

    public static boolean roops_goal_66;

    public static boolean roops_goal_67;

    public static boolean roops_goal_68;

    public static boolean roops_goal_69;

    public static boolean roops_goal_70;

    public static boolean roops_goal_71;

    public static boolean roops_goal_72;

    public static boolean roops_goal_73;

    public static boolean roops_goal_74;

    public static boolean roops_goal_75;

    public static boolean roops_goal_76;

    public static boolean roops_goal_77;

    public static boolean roops_goal_78;

    public static boolean roops_goal_79;

    public static boolean roops_goal_80;

    public static boolean roops_goal_81;

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
        roops_goal_34 = false;
        roops_goal_35 = false;
        roops_goal_36 = false;
        roops_goal_37 = false;
        roops_goal_38 = false;
        roops_goal_39 = false;
        roops_goal_40 = false;
        roops_goal_41 = false;
        roops_goal_42 = false;
        roops_goal_43 = false;
        roops_goal_44 = false;
        roops_goal_45 = false;
        roops_goal_46 = false;
        roops_goal_47 = false;
        roops_goal_48 = false;
        roops_goal_49 = false;
        roops_goal_50 = false;
        roops_goal_51 = false;
        roops_goal_52 = false;
        roops_goal_53 = false;
        roops_goal_54 = false;
        roops_goal_55 = false;
        roops_goal_56 = false;
        roops_goal_57 = false;
        roops_goal_58 = false;
        roops_goal_59 = false;
        roops_goal_60 = false;
        roops_goal_61 = false;
        roops_goal_62 = false;
        roops_goal_63 = false;
        roops_goal_64 = false;
        roops_goal_65 = false;
        roops_goal_66 = false;
        roops_goal_67 = false;
        roops_goal_68 = false;
        roops_goal_69 = false;
        roops_goal_70 = false;
        roops_goal_71 = false;
        roops_goal_72 = false;
        roops_goal_73 = false;
        roops_goal_74 = false;
        roops_goal_75 = false;
        roops_goal_76 = false;
        roops_goal_77 = false;
        roops_goal_78 = false;
        roops_goal_79 = false;
        roops_goal_80 = false;
        roops_goal_81 = false;
    }
}
=======
>>>>>>> ffe88bb... they see me unrollin, they hatin
