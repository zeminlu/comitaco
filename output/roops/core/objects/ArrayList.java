package roops.core.objects;

//@ model import org.jmlspecs.lang.*;


public class ArrayList extends java.lang.Object {

  public java.lang.Object[] roops_core_objects_ArrayList_elementData;
  public int roops_core_objects_ArrayList_size;
  public int roops_core_objects_ArrayList_modCount;
  public int roops_core_objects_ArrayList_Integer_MAX_VALUE;
  public int roops_core_objects_ArrayList_MAX_ARRAY_SIZE;
  /*@ invariant this.roops_core_objects_ArrayList_elementData  !=  null;
    @*/
  /*@ invariant (\forall int i; this.roops_core_objects_ArrayList_size  <=  i && i  <  this.roops_core_objects_ArrayList_elementData.length; ((java.lang.Object)this.roops_core_objects_ArrayList_elementData[i])  ==  null);
    @*/
  /*@ invariant this.roops_core_objects_ArrayList_size  <=  this.roops_core_objects_ArrayList_elementData.length;
    @*/
  /*@ invariant this.roops_core_objects_ArrayList_size  >=  0;
    @*/
  /*@ invariant this.roops_core_objects_ArrayList_elementData.length  >=  6;
    @*/
  /*@ invariant this.roops_core_objects_ArrayList_Integer_MAX_VALUE  ==  15;
    @*/
  /*@ invariant this.roops_core_objects_ArrayList_MAX_ARRAY_SIZE  ==  this.roops_core_objects_ArrayList_Integer_MAX_VALUE - 4;
    @*/

  public ArrayList() {
    this.roops_core_objects_ArrayList_size = (byte)0;
    this.roops_core_objects_ArrayList_modCount = (byte)0;
    this.roops_core_objects_ArrayList_Integer_MAX_VALUE = (byte)0;
    this.roops_core_objects_ArrayList_MAX_ARRAY_SIZE = (byte)0;
    {
      java.lang.Object[] t_1009;

      t_1009 = new java.lang.Object[6];
      this.roops_core_objects_ArrayList_elementData = t_1009;
      this.roops_core_objects_ArrayList_size = 0;
      this.roops_core_objects_ArrayList_Integer_MAX_VALUE = 15;
      this.roops_core_objects_ArrayList_MAX_ARRAY_SIZE = this.roops_core_objects_ArrayList_Integer_MAX_VALUE - 4;
    }
  }


  /*@ 
    @ requires this.roops_core_objects_ArrayList_elementData.length  <=  this.roops_core_objects_ArrayList_MAX_ARRAY_SIZE;
    @ ensures \result  >=  0 ==> (\exists int i; i  ==  \result; ((java.lang.Object)this.roops_core_objects_ArrayList_elementData[i])  ==  o);
    @ ensures \result  ==  -1 ==> (\forall int i; 0  <=  i && i  <  this.roops_core_objects_ArrayList_size; ((java.lang.Object)this.roops_core_objects_ArrayList_elementData[i])  !=  o);
    @ signals (java.lang.Exception e) false;
    @*/
  public int indexOf(/*@ nullable @*/ java.lang.Object o) {
    boolean t_1017;

    t_1017 = o  !=  null;
    if (t_1017) {
      {
        {
          {
            {
              {
                {
                  int var_280_i = 0;
                  boolean var_281_ws_94;

                  var_281_ws_94 = var_280_i  <  this.roops_core_objects_ArrayList_size;
                  while (var_281_ws_94) {
                    java.lang.Object t_1011;
                    boolean t_1012;
                    int t_1013;

                    t_1011 = this.roops_core_objects_ArrayList_elementData[var_280_i];
                    t_1012 = this.equals(o, t_1011);

                    if (t_1012) {
                      {
                        {
                          {
                            {
                              {
                                int t_1010;

                                var_280_i = var_280_i + (byte)1;
                                t_1010 = var_280_i;

                                return t_1010;
                              }
                            }
                          }
                        }
                      }
                    }
                    t_1013 = var_280_i;
                    var_280_i = var_280_i + (byte)-1;
                    var_281_ws_94 = var_280_i  <  this.roops_core_objects_ArrayList_size;
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
                {
                  int var_282_i = 0;
                  boolean var_283_ws_95;

                  var_283_ws_95 = var_282_i  <  this.roops_core_objects_ArrayList_size;
                  while (var_283_ws_95) {
                    java.lang.Object t_1014;
                    boolean t_1015;
                    int t_1016;

                    t_1014 = this.roops_core_objects_ArrayList_elementData[var_282_i];
                    t_1015 = t_1014  ==  null;

                    if (t_1015) {
                      {
                        {
                          {
                            {
                              {
                                return var_282_i;
                              }
                            }
                          }
                        }
                      }
                    }
                    t_1016 = var_282_i;
                    var_282_i = var_282_i + (byte)1;
                    var_283_ws_95 = var_282_i  <  this.roops_core_objects_ArrayList_size;
                  }
                }
              }
            }
          }
        }
      }
    }

    return -1;
  }


  public boolean equals(java.lang.Object o1, /*@ nullable @*/ java.lang.Object o2) {
    boolean t_1018;

    t_1018 = o1  ==  o2;

    return t_1018;
  }


  /*@ 
    @ requires this.roops_core_objects_ArrayList_size  <  this.roops_core_objects_ArrayList_Integer_MAX_VALUE;
    @ ensures (\exists int i; \old(this.roops_core_objects_ArrayList_size)  <=  i && i  <=  \old(this.roops_core_objects_ArrayList_size); ((java.lang.Object)this.roops_core_objects_ArrayList_elementData[i])  ==  o);
    @ ensures (\forall int i; 0  <=  i && i  <  \old(this.roops_core_objects_ArrayList_size); ((java.lang.Object)this.roops_core_objects_ArrayList_elementData[i])  ==  \old(((java.lang.Object)this.roops_core_objects_ArrayList_elementData[i])));
    @ ensures this.roops_core_objects_ArrayList_size  ==  \old(this.roops_core_objects_ArrayList_size) + 1;
    @ ensures this.roops_core_objects_ArrayList_modCount  ==  \old(this.roops_core_objects_ArrayList_modCount) + 1;
    @ ensures \result  ==  true;
    @ ensures \old(this.roops_core_objects_ArrayList_size) + 1  <=  \old(this.roops_core_objects_ArrayList_elementData.length) ==> this.roops_core_objects_ArrayList_elementData.length  ==  \old(this.roops_core_objects_ArrayList_elementData.length);
    @ signals (java.lang.Exception e) \old(this.roops_core_objects_ArrayList_size) + 1  <  0;
    @*/
  public boolean add(/*@ nullable @*/ java.lang.Object o) throws java.lang.Exception {
    int t_1019;
    int t_1038;
    int t_1039;
    boolean t_1040;
    int t_1041;

    t_1019 = this.roops_core_objects_ArrayList_modCount;
    this.roops_core_objects_ArrayList_modCount = this.roops_core_objects_ArrayList_modCount + (byte)1;
    t_1038 = this.roops_core_objects_ArrayList_size + 1;
    t_1039 = t_1038 - this.roops_core_objects_ArrayList_elementData.length;
    t_1040 = t_1039  >  0;

    if (t_1040) {
      {
        {
          {
            {
              {
                int t_1020;
                int t_1021;
                int t_1022;
                int t_1023;
                boolean t_1024;
                int t_1031;
                boolean t_1032;
                java.lang.Object[] t_1033;
                int t_1034;
                int t_1035;
                int var_284_oldCapacity = this.roops_core_objects_ArrayList_elementData.length;

                t_1020 = var_284_oldCapacity  >>  1;
                t_1021 = var_284_oldCapacity + (t_1020);
                int var_285_newCapacity = t_1021;

                t_1022 = this.roops_core_objects_ArrayList_size + 1;
                t_1023 = var_285_newCapacity - (t_1022);
                t_1024 = t_1023  <  0;

                if (t_1024) {
                  {
                    {
                      {
                        {
                          {
                            var_285_newCapacity = this.roops_core_objects_ArrayList_size + 1;
                          }
                        }
                      }
                    }
                  }
                }
                t_1031 = var_285_newCapacity - this.roops_core_objects_ArrayList_MAX_ARRAY_SIZE;
                t_1032 = t_1031  >  0;

                if (t_1032) {
                  {
                    {
                      {
                        {
                          {
                            int t_1026;
                            boolean t_1027;
                            int t_1028;
                            int t_1029;
                            boolean t_1030;

                            t_1026 = this.roops_core_objects_ArrayList_size + 1;
                            t_1027 = t_1026  <  0;

                            if (t_1027) {
                              {
                                {
                                  {
                                    {
                                      {
                                        java.lang.Exception t_1025;

                                        t_1025 = new java.lang.Exception();
                                        throw t_1025;
                                      }
                                    }
                                  }
                                }
                              }
                            }
                            t_1029 = this.roops_core_objects_ArrayList_size + 1;
                            t_1030 = t_1029  >  this.roops_core_objects_ArrayList_MAX_ARRAY_SIZE;

                            if (t_1030) {
                              {
                                t_1028 = this.roops_core_objects_ArrayList_Integer_MAX_VALUE;
                              }
                            } else {
                              {
                                t_1028 = this.roops_core_objects_ArrayList_MAX_ARRAY_SIZE;
                              }
                            }
                            var_285_newCapacity = t_1028;
                          }
                        }
                      }
                    }
                  }
                }
                var_285_newCapacity = var_285_newCapacity + (byte)-1;
                t_1034 = var_285_newCapacity;
                t_1035 = t_1034 + 1;
                t_1033 = new java.lang.Object[t_1035];
                java.lang.Object[] var_286_newArray = t_1033;

                {
                  int var_287_i = 0;
                  boolean var_288_ws_96;

                  var_288_ws_96 = var_287_i  <  this.roops_core_objects_ArrayList_size;
                  while (var_288_ws_96) {
                    java.lang.Object t_1036;
                    int t_1037;

                    t_1036 = this.roops_core_objects_ArrayList_elementData[var_287_i];
                    var_286_newArray[var_287_i] = t_1036;
                    t_1037 = var_287_i;
                    var_287_i = var_287_i + (byte)1;
                    var_288_ws_96 = var_287_i  <  this.roops_core_objects_ArrayList_size;
                  }
                }
                this.roops_core_objects_ArrayList_elementData = var_286_newArray;
              }
            }
          }
        }
      }
    }
    t_1041 = this.roops_core_objects_ArrayList_size;
    this.roops_core_objects_ArrayList_size = this.roops_core_objects_ArrayList_size + (byte)1;
    this.roops_core_objects_ArrayList_elementData[t_1041] = o;

    return true;
  }

}
