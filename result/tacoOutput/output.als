/* 
 * DynAlloy translator options 
 * --------------------------- 
 * assertionId= check_roops_core_objectsInstrumented_SinglyLinkedList_contains_0
 * loopUnroll= 1
 * removeQuantifiers= true
 * strictUnrolling= false
 * build_dynalloy_trace= false
 */ 

//-------------- prelude--------------//
module moduleId
open util/integer
open util/sequniv as sequniv
one sig null {}
fun fun_reach[h: univ, 
              type: set univ, 
              field: univ -> univ
]: set univ { 
  h.*(field & type->(type+null)) & type 
}

one
sig AssertionFailureLit extends java_lang_Throwable {}
abstract sig boolean {}
one sig true extends boolean {}
one sig false extends boolean {}
abstract sig char {}
pred TruePred[] {} 
pred FalsePred[] { not TruePred[] } 
pred equ[l,r:univ] {l=r} 
pred neq[l,r:univ] {l!=r} 

fun shl[l,r: Int]: Int { l << r } 
fun sshr[l,r: Int]: Int { l >> r } 
fun ushr[l,r: Int]: Int { l >>> r } 

fun fun_univ_equals[
  l:univ, 
  r: univ 
]: boolean { 
  (equ[l,r]) => true else false 
} 

fun fun_set_add[
  l: set univ,
  e: univ
]: set univ { 
  l+e 
} 

fun fun_set_remove[
  l: set univ,
  e: univ
]: set univ {
  l-e
}
fun fun_set_contains[
  l: set univ,
  e: univ
]: boolean {
  (e in l) => true else false 
} 
pred isSubset[
  l: set univ,
  r: set univ
] {
  (l in r) 
} 
pred isNotSubset[
  l: set univ,
  r: set univ
] {
  (l !in r) 
} 
fun fun_set_size[s: set univ]: Int { #s } 

fun fun_not_empty_set[s: set univ]: boolean { (no s) => false else true } 

fun fun_set_is_empty[s: set univ]: boolean { (no s) => true else false } 

pred pred_empty_set[l: set univ] { (no l) } 

pred pred_set_some[l: set univ] { some l } 

pred pred_set_one[l: set univ] { one l } 

pred pred_set_lone[l: set univ] { lone l } 

pred pred_Object_subset[
  s: set univ
] {
  s in java_lang_Object+null
}

fun fun_set_intersection[
  l: set univ,
  r: set univ
]: set univ {
  l & r 
} 
fun fun_set_difference[
  l: set univ,
  r: set univ
]: set univ {
  l - r 
} 
fun fun_rel_difference[ 
  rel: univ -> univ, 
  l: univ,
  r: univ
]: univ->univ {
 rel - (l->r) 
}
fun fun_rel_add[ 
  rel: univ -> univ, 
  l: univ,
  r: univ
]: univ->univ {
 rel + (l->r) 
}
fun fun_set_sum[
  s: set Int
]: Int {
  sum s 
} 
pred pred_empty_list[l: seq univ] { (no l) } 

fun fun_list_add[
  l: seq univ,
  e: univ
]: seq univ {
  sequniv/add[l,e] 
} 

fun fun_list_get[
  l: seq univ, 
  index: Int
]: univ { 
  index.l 
} 

fun fun_list_contains[
  l: seq univ, 
  e: univ
]: boolean { 
  (e in Int.l) => true else false 
} 

fun fun_list_remove[
  l: seq univ, 
  index: Int
]: seq univ { 
  sequniv/delete[l,index] 
} 

fun fun_list_size[s: seq univ]: Int { #s } 

fun fun_list_equals[
  s1:seq univ, 
  s2: seq univ
]: boolean { 
  (s1=s2) => true else false 
} 

fun fun_list_empty[s: seq univ]: boolean { (#s = 0) => true else false } 

pred pred_empty_map[map: univ -> univ] { (no map) } 

fun fun_map_put[
  map: univ->univ, 
  k: univ, 
  v: univ
]: univ-> univ { 
  map ++ (k->v) 
}

fun fun_map_contains_key[
  map: univ -> univ, 
  k: univ
]: boolean { 
  (some k.map) => true else false 
}

fun fun_map_remove[
  map: univ -> univ, 
  k: univ
]: univ->univ {
  map - (k->univ) 
} 

fun fun_map_get[
  map: univ -> univ, 
  k: univ
]: univ { 
  (some k.map) => k.map else null 
} 

fun fun_map_is_empty[
  map: univ -> univ, 
]: boolean { 
  (some map) => false else true 
}

fun fun_map_clear[
  mapEntries1: univ -> univ -> univ, 
  map: univ
]: univ -> univ -> univ { 
  mapEntries1 - (map -> univ -> univ)
}

fun fun_map_size[
  map: univ -> univ, 
]: univ {
  #map 
} 

pred isEmptyOrNull[u: univ] { u in null } 
fun fun_closure[
  rel: univ -> univ 
]: univ -> univ {
  ^rel 
} 

fun fun_reflexive_closure[
  rel: univ -> univ 
]: univ -> univ {
  *rel 
} 

fun fun_transpose[
  rel: univ -> univ 
]: univ -> univ {
  ~rel 
} 

pred liftExpression[
  expr: univ 
] {
  expr=true 
} 

fun rel_override[
  r:univ->univ,
  k:univ, 
  v:univ
]: univ->univ { 
  r - (k->univ) + (k->v) 
} 

fun Not[a: boolean]: boolean {
    (a==true) => false else true 
}
fun Or[a: boolean, b: boolean]: boolean {
    (a==true or b==true) => true else false
}
fun And[a: boolean, b: boolean]: boolean {
    (a==true and b==true) => true else false
}
fun Xor[a: boolean, b: boolean]: boolean {
    ((a==true and b==false) or (a==false and b==true)) => true else false
}
fun AdderCarry[a: boolean, b: boolean, cin: boolean]: boolean {
    Or[ And[a,b], And[cin, Xor[a,b]]] 
}
fun AdderSum[a: boolean, b: boolean, cin: boolean]: boolean {
    Xor[Xor[a, b], cin]
}
pred updateFieldPost[
  f1:univ->univ,
  f0:univ->univ,
  l:univ,
  r:univ
]{ 
  (r=none) => f1=f0-(l->univ) else f1 = f0 ++ (l->r) 
} 

pred havocVarPost[u:univ]{} 
pred havocVariable2Post[u:univ->univ]{}
pred havocVariable3Post[u:univ->(seq univ)]{}
pred havocFieldPost[f0,f1: univ->univ, u:univ]{ 
  u<:f0 = u<:f1 
  some u.f1  
} 

pred havocArrayContentsPost[array:  univ,
                            domain: set univ,
                            Object_Array_0: univ -> (seq univ),
                            Object_Array_1: univ -> (seq univ)
                           ] {
  Object_Array_1 - (array->(domain->univ)) = Object_Array_0 - (array->(domain->univ))
  (array.Object_Array_1).univ = (array.Object_Array_0).univ
}
pred havocFieldContentsPost[target: univ, 
                            field_0: univ -> univ, 
                            field_1: univ -> univ] { 
  field_1 - (target->univ) = field_0 - (target->univ) 
}
pred pred_in[n: univ, t: set univ] { n in t } 

pred instanceOf[n: univ, t: set univ] { n in t } 

pred isCasteableTo[n: univ, t: set univ] { (n in t) or (n = null) } 

pred getUnusedObjectPost[
  usedObjects1:set java_lang_Object, 
  usedObjects0:set java_lang_Object,
  n1: java_lang_Object+null
]{ 
  n1 !in usedObjects0 
  usedObjects1 = usedObjects0 + (n1)
}
//-------------- ClassFields--------------//
one
sig ClassFields {}
{}




//-------------- java_lang_RuntimeException--------------//
abstract sig java_lang_RuntimeException extends java_lang_Exception {}
{}



one
sig java_lang_RuntimeExceptionLit extends java_lang_RuntimeException {}
{}

//-------------- java_lang_Exception--------------//
abstract sig java_lang_Exception extends java_lang_Throwable {}
{}




//-------------- java_lang_Throwable--------------//
abstract sig java_lang_Throwable {}
{}




//-------------- java_lang_Object--------------//
sig java_lang_Object {}
{}




//-------------- roops_core_objectsInstrumented_SinglyLinkedListNode--------------//
sig roops_core_objectsInstrumented_SinglyLinkedListNode extends java_lang_Object {}
{}




//-------------- java_lang_NullPointerException--------------//
abstract one sig java_lang_NullPointerException extends java_lang_RuntimeException {}
{}



one
sig java_lang_NullPointerExceptionLit extends java_lang_NullPointerException {}
{}

//-------------- roops_core_objectsInstrumented_SinglyLinkedList--------------//
sig roops_core_objectsInstrumented_SinglyLinkedList extends java_lang_Object {}
{}




pred roops_core_objectsInstrumented_SinglyLinkedList_ensures[
  throw':univ
]{
   liftExpression[false]
   and 
   equ[throw',
      null]

}

pred roops_core_objectsInstrumented_SinglyLinkedListCondition14[
  t_4:univ
]{
   t_4=true

}

pred roops_core_objectsInstrumented_SinglyLinkedListCondition10[
  t_8:univ
]{
   t_8=true

}

pred roops_core_objectsInstrumented_SinglyLinkedListCondition15[
  t_4:univ
]{
   not (
     t_4=true)

}

pred roops_core_objectsInstrumented_SinglyLinkedListCondition5[
  thiz:univ
]{
   not (
     isEmptyOrNull[thiz])

}

pred roops_core_objectsInstrumented_SinglyLinkedListCondition11[
  t_8:univ
]{
   not (
     t_8=true)

}

pred roops_core_objectsInstrumented_SinglyLinkedListCondition4[
  thiz:univ
]{
   isEmptyOrNull[thiz]

}

pred roops_core_objectsInstrumented_SinglyLinkedList_object_invariant[
  roops_core_objectsInstrumented_SinglyLinkedListNode_next:univ->univ,
  roops_core_objectsInstrumented_SinglyLinkedList_header:univ->univ,
  thiz:univ
]{
   all n:null+roops_core_objectsInstrumented_SinglyLinkedListNode | {
     liftExpression[fun_set_contains[fun_reach[thiz.roops_core_objectsInstrumented_SinglyLinkedList_header,roops_core_objectsInstrumented_SinglyLinkedListNode,roops_core_objectsInstrumented_SinglyLinkedListNode_next],n]]
     implies 
             equ[fun_set_contains[fun_reach[n.roops_core_objectsInstrumented_SinglyLinkedListNode_next,roops_core_objectsInstrumented_SinglyLinkedListNode,roops_core_objectsInstrumented_SinglyLinkedListNode_next],n],
                false]
   
   }

}

pred roops_core_objectsInstrumented_SinglyLinkedListCondition12[
  t_7:univ
]{
   t_7=true

}

pred roops_core_objectsInstrumented_SinglyLinkedListCondition26[
  t_11:univ
]{
   t_11=true

}

pred roops_core_objectsInstrumented_SinglyLinkedListCondition8[
  t_2:univ
]{
   t_2=true

}

pred roops_core_objectsInstrumented_SinglyLinkedListCondition28[
  exit_stmt_reached:univ,
  throw:univ,
  var_4_ws_1:univ
]{
   liftExpression[var_4_ws_1]
   and 
   (
     throw=null)
   and 
   (
     exit_stmt_reached=false)

}

pred roops_core_objectsInstrumented_SinglyLinkedListCondition6[
  t_3:univ
]{
   t_3=true

}

pred roops_core_objectsInstrumented_SinglyLinkedListCondition9[
  t_2:univ
]{
   not (
     t_2=true)

}

pred roops_core_objectsInstrumented_SinglyLinkedListCondition7[
  t_3:univ
]{
   not (
     t_3=true)

}

pred roops_core_objectsInstrumented_SinglyLinkedListCondition1[
]{
   not (
     isEmptyOrNull[ClassFields])

}

pred roops_core_objectsInstrumented_SinglyLinkedListCondition23[
  var_1_current:univ
]{
   not (
     isEmptyOrNull[var_1_current])

}

pred roops_core_objectsInstrumented_SinglyLinkedListCondition22[
  var_1_current:univ
]{
   isEmptyOrNull[var_1_current]

}

pred postcondition_roops_core_objectsInstrumented_SinglyLinkedList_contains_0[
  throw':univ
]{
   roops_core_objectsInstrumented_SinglyLinkedList_ensures[throw']
   and 
   (
     not (
       throw'=AssertionFailureLit)
   )

}

pred roops_core_objectsInstrumented_SinglyLinkedListCondition0[
]{
   isEmptyOrNull[ClassFields]

}

pred roops_core_objectsInstrumented_SinglyLinkedListCondition20[
  t_9:univ
]{
   t_9=true

}

pred roops_core_objectsInstrumented_SinglyLinkedListCondition30[
  t_13:univ
]{
   not (
     t_13=true)

}

pred roops_core_objectsInstrumented_SinglyLinkedListCondition21[
  t_9:univ
]{
   not (
     t_9=true)

}

pred roops_core_objectsInstrumented_SinglyLinkedListCondition19[
  t_6:univ
]{
   not (
     t_6=true)

}

pred roops_core_objectsInstrumented_SinglyLinkedListCondition17[
  t_5:univ
]{
   not (
     t_5=true)

}

pred roops_core_objectsInstrumented_SinglyLinkedListCondition18[
  t_6:univ
]{
   t_6=true

}

pred roops_core_objectsInstrumented_SinglyLinkedListCondition29[
  t_13:univ
]{
   t_13=true

}

pred roops_core_objectsInstrumented_SinglyLinkedListCondition16[
  t_5:univ
]{
   t_5=true

}

pred roops_core_objectsInstrumented_SinglyLinkedListCondition13[
  t_7:univ
]{
   not (
     t_7=true)

}

pred roops_core_objectsInstrumented_SinglyLinkedListCondition27[
  t_11:univ
]{
   not (
     t_11=true)

}

pred precondition_roops_core_objectsInstrumented_SinglyLinkedList_contains_0[
  roops_core_objectsInstrumented_SinglyLinkedListNode_next:univ->univ,
  roops_core_objectsInstrumented_SinglyLinkedList_header:univ->univ,
  throw:univ
]{
   equ[throw,
      null]
   and 
   (
     all objx:roops_core_objectsInstrumented_SinglyLinkedList | {
       roops_core_objectsInstrumented_SinglyLinkedList_object_invariant[roops_core_objectsInstrumented_SinglyLinkedListNode_next,
                                                                       roops_core_objectsInstrumented_SinglyLinkedList_header,
                                                                       objx]
     
     }
   )

}

pred roops_core_objectsInstrumented_SinglyLinkedListCondition24[
  t_12:univ
]{
   t_12=true

}

pred roops_core_objectsInstrumented_SinglyLinkedListCondition2[
  exit_stmt_reached:univ,
  throw:univ
]{
   (
     throw=null)
   and 
   (
     exit_stmt_reached=false)

}

pred roops_core_objectsInstrumented_SinglyLinkedListCondition25[
  t_12:univ
]{
   not (
     t_12=true)

}

pred roops_core_objectsInstrumented_SinglyLinkedListCondition3[
  exit_stmt_reached:univ,
  throw:univ
]{
   not (
     (
       throw=null)
     and 
     (
       exit_stmt_reached=false)
   )

}
check check_roops_core_objectsInstrumented_SinglyLinkedList_contains_0 for 3 but 4 int, 7 seq



pred updateVariable[
  l_1: univ,
  r_0: univ
]{
  TruePred[]
  and 
  equ[l_1,
     r_0]
}


pred getUnusedObject[
  n_1: java_lang_Object + null,
  usedObjects_0: set java_lang_Object,
  usedObjects_1: set java_lang_Object
]{
  TruePred[]
  and 
  getUnusedObjectPost[usedObjects_1,
                     usedObjects_0,
                     n_1]
}


pred havocVariable[
  v_1: univ
]{
  TruePred[]
  and 
  havocVarPost[v_1]
}


pred havocFieldContents[
  target_0: univ,
  field_0: univ -> univ,
  field_1: univ -> univ
]{
  TruePred[]
  and 
  havocFieldContentsPost[target_0,
                        field_0,
                        field_1]
}


pred havocVariable3[
  u_1: univ -> ( seq univ )
]{
  TruePred[]
  and 
  havocVariable3Post[u_1]
}


pred havocVariable2[
  u_1: univ -> univ
]{
  TruePred[]
  and 
  havocVariable2Post[u_1]
}


pred havocField[
  f_0: univ -> univ,
  f_1: univ -> univ,
  u_0: univ
]{
  TruePred[]
  and 
  havocFieldPost[f_0,
                f_1,
                u_0]
}


pred updateField[
  l_0: univ,
  f_0: univ -> univ,
  f_1: univ -> univ,
  r_0: univ
]{
  TruePred[]
  and 
  updateFieldPost[f_1,
                 f_0,
                 l_0,
                 r_0]
}


pred havocArrayContents[
  array_0: univ,
  domain_0: set univ,
  Object_Array_0: univ -> ( seq univ ),
  Object_Array_1: univ -> ( seq univ )
]{
  TruePred[]
  and 
  havocArrayContentsPost[array_0,
                        domain_0,
                        Object_Array_0,
                        Object_Array_1]
}


pred roops_core_objectsInstrumented_SinglyLinkedList_contains_0[
  thiz_0: roops_core_objectsInstrumented_SinglyLinkedList,
  throw_1: java_lang_Throwable + null,
  throw_2: java_lang_Throwable + null,
  throw_3: java_lang_Throwable + null,
  throw_4: java_lang_Throwable + null,
  throw_5: java_lang_Throwable + null,
  throw_6: java_lang_Throwable + null,
  throw_7: java_lang_Throwable + null,
  throw_8: java_lang_Throwable + null,
  throw_9: java_lang_Throwable + null,
  throw_10: java_lang_Throwable + null,
  throw_11: java_lang_Throwable + null,
  throw_12: java_lang_Throwable + null,
  throw_13: java_lang_Throwable + null,
  throw_14: java_lang_Throwable + null,
  throw_15: java_lang_Throwable + null,
  throw_16: java_lang_Throwable + null,
  throw_17: java_lang_Throwable + null,
  throw_18: java_lang_Throwable + null,
  throw_19: java_lang_Throwable + null,
  throw_20: java_lang_Throwable + null,
  return_0: boolean,
  return_1: boolean,
  value_param_0: java_lang_Object + null,
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_0_0: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_0_1: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_0_2: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_header_0: ( roops_core_objectsInstrumented_SinglyLinkedList ) -> one ( null + roops_core_objectsInstrumented_SinglyLinkedListNode ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_1_0: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_1_1: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_1_2: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedListNode_next_0: ( roops_core_objectsInstrumented_SinglyLinkedListNode ) -> one ( null + roops_core_objectsInstrumented_SinglyLinkedListNode ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_4_0: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_4_1: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_4_2: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedListNode_value_0: ( roops_core_objectsInstrumented_SinglyLinkedListNode ) -> one ( java_lang_Object + null ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_5_0: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_5_1: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_5_2: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_2_0: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_2_1: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_2_2: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_3_0: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_3_1: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_3_2: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_8_0: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_8_1: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_8_2: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_9_0: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_9_1: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_9_2: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_0: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_1: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_2: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_0: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_1: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_2: ( ClassFields ) -> one ( boolean ),
  t_13_0: boolean,
  t_13_1: boolean,
  t_11_0: boolean,
  t_11_1: boolean,
  var_3_fajita_cicle_0_0: boolean,
  var_3_fajita_cicle_0_1: boolean,
  var_3_fajita_cicle_0_2: boolean,
  var_4_ws_1_0: boolean,
  var_4_ws_1_1: boolean,
  var_4_ws_1_2: boolean,
  t_12_0: boolean,
  t_12_1: boolean,
  t_10_0: boolean,
  t_10_1: boolean,
  t_2_0: boolean,
  t_2_1: boolean,
  t_3_0: boolean,
  t_3_1: boolean,
  exit_stmt_reached_1: boolean,
  exit_stmt_reached_2: boolean,
  t_1_0: boolean,
  t_1_1: boolean,
  var_1_current_0: null + roops_core_objectsInstrumented_SinglyLinkedListNode,
  var_1_current_1: null + roops_core_objectsInstrumented_SinglyLinkedListNode,
  var_1_current_2: null + roops_core_objectsInstrumented_SinglyLinkedListNode,
  var_2_result_0: boolean,
  var_2_result_1: boolean,
  var_2_result_2: boolean,
  t_8_0: boolean,
  t_8_1: boolean,
  t_9_0: boolean,
  t_9_1: boolean,
  t_6_0: boolean,
  t_6_1: boolean,
  t_7_0: boolean,
  t_7_1: boolean,
  t_4_0: boolean,
  t_4_1: boolean,
  var_5_equalVal_0: boolean,
  var_5_equalVal_1: boolean,
  t_5_0: boolean,
  t_5_1: boolean,
  l0_exit_stmt_reached_1: boolean
]{
  TruePred[]
  and 
  (
    throw_1=null)
  and 
  TruePred[]
  and 
  (
    exit_stmt_reached_1=false)
  and 
  roops_core_objectsInstrumented_SinglyLinkedList_fajita_roopsGoal_initialization_0[throw_2,
                                                                                   throw_3,
                                                                                   throw_4,
                                                                                   throw_5,
                                                                                   throw_6,
                                                                                   throw_7,
                                                                                   throw_8,
                                                                                   throw_9,
                                                                                   throw_10,
                                                                                   throw_11,
                                                                                   throw_12,
                                                                                   roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_0_0,
                                                                                   roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_0_1,
                                                                                   roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_1_0,
                                                                                   roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_1_1,
                                                                                   roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_4_0,
                                                                                   roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_4_1,
                                                                                   roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_5_0,
                                                                                   roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_5_1,
                                                                                   roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_2_0,
                                                                                   roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_2_1,
                                                                                   roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_3_0,
                                                                                   roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_3_1,
                                                                                   roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_8_0,
                                                                                   roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_8_1,
                                                                                   roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_9_0,
                                                                                   roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_9_1,
                                                                                   roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_0,
                                                                                   roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_1,
                                                                                   roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_0,
                                                                                   roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_1,
                                                                                   l0_exit_stmt_reached_1]
  and 
  TruePred[]
  and 
  TruePred[]
  and 
  (
    (
      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_12]
      and 
      (
        (
          roops_core_objectsInstrumented_SinglyLinkedListCondition4[thiz_0]
          and 
          (
            throw_13=java_lang_NullPointerExceptionLit)
          and 
          (
            var_1_current_0=var_1_current_1)
        )
        or 
        (
          (
            not (
              roops_core_objectsInstrumented_SinglyLinkedListCondition4[thiz_0])
          )
          and 
          (
            var_1_current_1=thiz_0.roops_core_objectsInstrumented_SinglyLinkedList_header_0)
          and 
          (
            throw_12=throw_13)
        )
      )
    )
    or 
    (
      (
        not (
          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_12]
        )
      )
      and 
      TruePred[]
      and 
      (
        var_1_current_0=var_1_current_1)
      and 
      (
        throw_12=throw_13)
    )
  )
  and 
  (
    (
      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_13]
      and 
      (
        var_2_result_1=false)
    )
    or 
    (
      (
        not (
          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_13]
        )
      )
      and 
      TruePred[]
      and 
      (
        var_2_result_0=var_2_result_1)
    )
  )
  and 
  TruePred[]
  and 
  TruePred[]
  and 
  TruePred[]
  and 
  TruePred[]
  and 
  TruePred[]
  and 
  (
    (
      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_13]
      and 
      (
        var_3_fajita_cicle_0_1=false)
    )
    or 
    (
      (
        not (
          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_13]
        )
      )
      and 
      TruePred[]
      and 
      (
        var_3_fajita_cicle_0_0=var_3_fajita_cicle_0_1)
    )
  )
  and 
  TruePred[]
  and 
  (
    (
      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_13]
      and 
      (
        t_2_1=(equ[var_2_result_1,
           false]=>(true)else(false))
      )
    )
    or 
    (
      (
        not (
          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_13]
        )
      )
      and 
      TruePred[]
      and 
      (
        t_2_0=t_2_1)
    )
  )
  and 
  (
    (
      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_13]
      and 
      (
        t_3_1=(neq[var_1_current_1,
           null]=>(true)else(false))
      )
    )
    or 
    (
      (
        not (
          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_13]
        )
      )
      and 
      TruePred[]
      and 
      (
        t_3_0=t_3_1)
    )
  )
  and 
  (
    (
      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_13]
      and 
      (
        (
          roops_core_objectsInstrumented_SinglyLinkedListCondition8[t_2_1]
          and 
          (
            (
              roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_13]
              and 
              (
                (
                  roops_core_objectsInstrumented_SinglyLinkedListCondition6[t_3_1]
                  and 
                  (
                    (
                      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_13]
                      and 
                      (
                        t_1_1=true)
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_13]
                        )
                      )
                      and 
                      TruePred[]
                      and 
                      (
                        t_1_0=t_1_1)
                    )
                  )
                )
                or 
                (
                  (
                    not (
                      roops_core_objectsInstrumented_SinglyLinkedListCondition6[t_3_1])
                  )
                  and 
                  (
                    (
                      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_13]
                      and 
                      (
                        t_1_1=false)
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_13]
                        )
                      )
                      and 
                      TruePred[]
                      and 
                      (
                        t_1_0=t_1_1)
                    )
                  )
                )
              )
            )
            or 
            (
              (
                not (
                  roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_13]
                )
              )
              and 
              TruePred[]
              and 
              (
                t_1_0=t_1_1)
            )
          )
        )
        or 
        (
          (
            not (
              roops_core_objectsInstrumented_SinglyLinkedListCondition8[t_2_1])
          )
          and 
          (
            (
              roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_13]
              and 
              (
                t_1_1=false)
            )
            or 
            (
              (
                not (
                  roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_13]
                )
              )
              and 
              TruePred[]
              and 
              (
                t_1_0=t_1_1)
            )
          )
        )
      )
    )
    or 
    (
      (
        not (
          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_13]
        )
      )
      and 
      TruePred[]
      and 
      (
        t_1_0=t_1_1)
    )
  )
  and 
  (
    (
      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_13]
      and 
      (
        var_4_ws_1_1=t_1_1)
    )
    or 
    (
      (
        not (
          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_13]
        )
      )
      and 
      TruePred[]
      and 
      (
        var_4_ws_1_0=var_4_ws_1_1)
    )
  )
  and 
  (
    (
      roops_core_objectsInstrumented_SinglyLinkedListCondition28[exit_stmt_reached_1,
                                                                throw_13,
                                                                var_4_ws_1_1]
      and 
      TruePred[]
      and 
      TruePred[]
      and 
      TruePred[]
      and 
      TruePred[]
      and 
      TruePred[]
      and 
      TruePred[]
      and 
      TruePred[]
      and 
      (
        (
          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_13]
          and 
          (
            var_3_fajita_cicle_0_2=true)
        )
        or 
        (
          (
            not (
              roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_13]
            )
          )
          and 
          TruePred[]
          and 
          (
            var_3_fajita_cicle_0_1=var_3_fajita_cicle_0_2)
        )
      )
      and 
      (
        (
          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_13]
          and 
          (
            (
              roops_core_objectsInstrumented_SinglyLinkedListCondition0[]
              and 
              (
                throw_14=java_lang_NullPointerExceptionLit)
              and 
              (
                roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_0_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_0_2)
            )
            or 
            (
              (
                not (
                  roops_core_objectsInstrumented_SinglyLinkedListCondition0[])
              )
              and 
              (
                roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_0_2=(roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_0_1)++((ClassFields)->(true)))
              and 
              (
                throw_13=throw_14)
            )
          )
        )
        or 
        (
          (
            not (
              roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_13]
            )
          )
          and 
          TruePred[]
          and 
          (
            roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_0_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_0_2)
          and 
          (
            throw_13=throw_14)
        )
      )
      and 
      TruePred[]
      and 
      (
        (
          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_14]
          and 
          (
            t_7_1=(equ[value_param_0,
               null]=>(true)else(false))
          )
        )
        or 
        (
          (
            not (
              roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_14]
            )
          )
          and 
          TruePred[]
          and 
          (
            t_7_0=t_7_1)
        )
      )
      and 
      (
        (
          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_14]
          and 
          (
            t_8_1=(equ[var_1_current_1.roops_core_objectsInstrumented_SinglyLinkedListNode_value_0,
               null]=>(true)else(false))
          )
        )
        or 
        (
          (
            not (
              roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_14]
            )
          )
          and 
          TruePred[]
          and 
          (
            t_8_0=t_8_1)
        )
      )
      and 
      (
        (
          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_14]
          and 
          (
            (
              roops_core_objectsInstrumented_SinglyLinkedListCondition12[t_7_1]
              and 
              (
                (
                  roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_14]
                  and 
                  (
                    (
                      roops_core_objectsInstrumented_SinglyLinkedListCondition10[t_8_1]
                      and 
                      (
                        (
                          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_14]
                          and 
                          (
                            t_6_1=true)
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_14]
                            )
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            t_6_0=t_6_1)
                        )
                      )
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objectsInstrumented_SinglyLinkedListCondition10[t_8_1])
                      )
                      and 
                      (
                        (
                          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_14]
                          and 
                          (
                            t_6_1=false)
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_14]
                            )
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            t_6_0=t_6_1)
                        )
                      )
                    )
                  )
                )
                or 
                (
                  (
                    not (
                      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_14]
                    )
                  )
                  and 
                  TruePred[]
                  and 
                  (
                    t_6_0=t_6_1)
                )
              )
            )
            or 
            (
              (
                not (
                  roops_core_objectsInstrumented_SinglyLinkedListCondition12[t_7_1])
              )
              and 
              (
                (
                  roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_14]
                  and 
                  (
                    t_6_1=false)
                )
                or 
                (
                  (
                    not (
                      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_14]
                    )
                  )
                  and 
                  TruePred[]
                  and 
                  (
                    t_6_0=t_6_1)
                )
              )
            )
          )
        )
        or 
        (
          (
            not (
              roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_14]
            )
          )
          and 
          TruePred[]
          and 
          (
            t_6_0=t_6_1)
        )
      )
      and 
      (
        (
          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_14]
          and 
          (
            (
              roops_core_objectsInstrumented_SinglyLinkedListCondition18[t_6_1]
              and 
              (
                (
                  roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_14]
                  and 
                  (
                    (
                      roops_core_objectsInstrumented_SinglyLinkedListCondition0[]
                      and 
                      (
                        throw_17=java_lang_NullPointerExceptionLit)
                      and 
                      (
                        roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_2_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_2_2)
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objectsInstrumented_SinglyLinkedListCondition0[])
                      )
                      and 
                      (
                        roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_2_2=(roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_2_1)++((ClassFields)->(true)))
                      and 
                      (
                        throw_14=throw_17)
                    )
                  )
                )
                or 
                (
                  (
                    not (
                      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_14]
                    )
                  )
                  and 
                  TruePred[]
                  and 
                  (
                    roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_2_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_2_2)
                  and 
                  (
                    throw_14=throw_17)
                )
              )
              and 
              (
                (
                  roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_17]
                  and 
                  (
                    var_5_equalVal_1=true)
                )
                or 
                (
                  (
                    not (
                      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_17]
                    )
                  )
                  and 
                  TruePred[]
                  and 
                  (
                    var_5_equalVal_0=var_5_equalVal_1)
                )
              )
              and 
              (
                roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_3_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_3_2)
              and 
              (
                roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_4_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_4_2)
              and 
              (
                roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_2)
              and 
              (
                roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_5_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_5_2)
              and 
              (
                roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_2)
              and 
              (
                t_5_0=t_5_1)
              and 
              (
                t_4_0=t_4_1)
            )
            or 
            (
              (
                not (
                  roops_core_objectsInstrumented_SinglyLinkedListCondition18[t_6_1])
              )
              and 
              TruePred[]
              and 
              (
                (
                  roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_14]
                  and 
                  (
                    (
                      roops_core_objectsInstrumented_SinglyLinkedListCondition0[]
                      and 
                      (
                        throw_15=java_lang_NullPointerExceptionLit)
                      and 
                      (
                        roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_3_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_3_2)
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objectsInstrumented_SinglyLinkedListCondition0[])
                      )
                      and 
                      (
                        roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_3_2=(roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_3_1)++((ClassFields)->(true)))
                      and 
                      (
                        throw_14=throw_15)
                    )
                  )
                )
                or 
                (
                  (
                    not (
                      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_14]
                    )
                  )
                  and 
                  TruePred[]
                  and 
                  (
                    roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_3_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_3_2)
                  and 
                  (
                    throw_14=throw_15)
                )
              )
              and 
              (
                (
                  roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_15]
                  and 
                  (
                    t_5_1=(neq[value_param_0,
                       null]=>(true)else(false))
                  )
                )
                or 
                (
                  (
                    not (
                      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_15]
                    )
                  )
                  and 
                  TruePred[]
                  and 
                  (
                    t_5_0=t_5_1)
                )
              )
              and 
              (
                (
                  roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_15]
                  and 
                  (
                    (
                      roops_core_objectsInstrumented_SinglyLinkedListCondition16[t_5_1]
                      and 
                      TruePred[]
                      and 
                      (
                        (
                          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_15]
                          and 
                          (
                            (
                              roops_core_objectsInstrumented_SinglyLinkedListCondition0[]
                              and 
                              (
                                throw_16=java_lang_NullPointerExceptionLit)
                              and 
                              (
                                roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_4_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_4_2)
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objectsInstrumented_SinglyLinkedListCondition0[])
                              )
                              and 
                              (
                                roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_4_2=(roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_4_1)++((ClassFields)->(true)))
                              and 
                              (
                                throw_15=throw_16)
                            )
                          )
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_15]
                            )
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_4_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_4_2)
                          and 
                          (
                            throw_15=throw_16)
                        )
                      )
                      and 
                      (
                        (
                          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_16]
                          and 
                          (
                            t_4_1=(equ[value_param_0,
                               var_1_current_1.roops_core_objectsInstrumented_SinglyLinkedListNode_value_0]=>(true)else(false))
                          )
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_16]
                            )
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            t_4_0=t_4_1)
                        )
                      )
                      and 
                      (
                        (
                          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_16]
                          and 
                          (
                            (
                              roops_core_objectsInstrumented_SinglyLinkedListCondition14[t_4_1]
                              and 
                              (
                                (
                                  roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                           throw_16]
                                  and 
                                  (
                                    (
                                      roops_core_objectsInstrumented_SinglyLinkedListCondition0[]
                                      and 
                                      (
                                        throw_17=java_lang_NullPointerExceptionLit)
                                      and 
                                      (
                                        roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_2)
                                    )
                                    or 
                                    (
                                      (
                                        not (
                                          roops_core_objectsInstrumented_SinglyLinkedListCondition0[])
                                      )
                                      and 
                                      (
                                        roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_2=(roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_1)++((ClassFields)->(true)))
                                      and 
                                      (
                                        throw_16=throw_17)
                                    )
                                  )
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                               throw_16]
                                    )
                                  )
                                  and 
                                  TruePred[]
                                  and 
                                  (
                                    roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_2)
                                  and 
                                  (
                                    throw_16=throw_17)
                                )
                              )
                              and 
                              (
                                (
                                  roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                           throw_17]
                                  and 
                                  (
                                    var_5_equalVal_1=true)
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                               throw_17]
                                    )
                                  )
                                  and 
                                  TruePred[]
                                  and 
                                  (
                                    var_5_equalVal_0=var_5_equalVal_1)
                                )
                              )
                              and 
                              (
                                roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_2)
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objectsInstrumented_SinglyLinkedListCondition14[t_4_1])
                              )
                              and 
                              (
                                (
                                  roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                           throw_16]
                                  and 
                                  (
                                    (
                                      roops_core_objectsInstrumented_SinglyLinkedListCondition0[]
                                      and 
                                      (
                                        throw_17=java_lang_NullPointerExceptionLit)
                                      and 
                                      (
                                        roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_2)
                                    )
                                    or 
                                    (
                                      (
                                        not (
                                          roops_core_objectsInstrumented_SinglyLinkedListCondition0[])
                                      )
                                      and 
                                      (
                                        roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_2=(roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_1)++((ClassFields)->(true)))
                                      and 
                                      (
                                        throw_16=throw_17)
                                    )
                                  )
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                               throw_16]
                                    )
                                  )
                                  and 
                                  TruePred[]
                                  and 
                                  (
                                    roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_2)
                                  and 
                                  (
                                    throw_16=throw_17)
                                )
                              )
                              and 
                              (
                                (
                                  roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                           throw_17]
                                  and 
                                  (
                                    var_5_equalVal_1=false)
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                               throw_17]
                                    )
                                  )
                                  and 
                                  TruePred[]
                                  and 
                                  (
                                    var_5_equalVal_0=var_5_equalVal_1)
                                )
                              )
                              and 
                              (
                                roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_2)
                            )
                          )
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_16]
                            )
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_2)
                          and 
                          (
                            roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_2)
                          and 
                          (
                            var_5_equalVal_0=var_5_equalVal_1)
                          and 
                          (
                            throw_16=throw_17)
                        )
                      )
                      and 
                      (
                        roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_5_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_5_2)
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objectsInstrumented_SinglyLinkedListCondition16[t_5_1])
                      )
                      and 
                      (
                        (
                          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_15]
                          and 
                          (
                            (
                              roops_core_objectsInstrumented_SinglyLinkedListCondition0[]
                              and 
                              (
                                throw_17=java_lang_NullPointerExceptionLit)
                              and 
                              (
                                roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_5_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_5_2)
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objectsInstrumented_SinglyLinkedListCondition0[])
                              )
                              and 
                              (
                                roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_5_2=(roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_5_1)++((ClassFields)->(true)))
                              and 
                              (
                                throw_15=throw_17)
                            )
                          )
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_15]
                            )
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_5_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_5_2)
                          and 
                          (
                            throw_15=throw_17)
                        )
                      )
                      and 
                      (
                        (
                          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_17]
                          and 
                          (
                            var_5_equalVal_1=false)
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_17]
                            )
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            var_5_equalVal_0=var_5_equalVal_1)
                        )
                      )
                      and 
                      (
                        roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_4_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_4_2)
                      and 
                      (
                        roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_2)
                      and 
                      (
                        roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_2)
                      and 
                      (
                        t_4_0=t_4_1)
                    )
                  )
                )
                or 
                (
                  (
                    not (
                      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_15]
                    )
                  )
                  and 
                  TruePred[]
                  and 
                  (
                    roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_4_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_4_2)
                  and 
                  (
                    roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_2)
                  and 
                  (
                    roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_5_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_5_2)
                  and 
                  (
                    roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_2)
                  and 
                  (
                    var_5_equalVal_0=var_5_equalVal_1)
                  and 
                  (
                    t_4_0=t_4_1)
                  and 
                  (
                    throw_15=throw_17)
                )
              )
              and 
              (
                roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_2_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_2_2)
            )
          )
        )
        or 
        (
          (
            not (
              roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_14]
            )
          )
          and 
          TruePred[]
          and 
          (
            roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_3_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_3_2)
          and 
          (
            roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_4_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_4_2)
          and 
          (
            roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_2_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_2_2)
          and 
          (
            roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_2)
          and 
          (
            roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_5_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_5_2)
          and 
          (
            roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_2)
          and 
          (
            t_5_0=t_5_1)
          and 
          (
            var_5_equalVal_0=var_5_equalVal_1)
          and 
          (
            t_4_0=t_4_1)
          and 
          (
            throw_14=throw_17)
        )
      )
      and 
      (
        (
          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_17]
          and 
          (
            t_9_1=(equ[var_5_equalVal_1,
               true]=>(true)else(false))
          )
        )
        or 
        (
          (
            not (
              roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_17]
            )
          )
          and 
          TruePred[]
          and 
          (
            t_9_0=t_9_1)
        )
      )
      and 
      (
        (
          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_17]
          and 
          (
            (
              roops_core_objectsInstrumented_SinglyLinkedListCondition20[t_9_1]
              and 
              (
                (
                  roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_17]
                  and 
                  (
                    (
                      roops_core_objectsInstrumented_SinglyLinkedListCondition0[]
                      and 
                      (
                        throw_18=java_lang_NullPointerExceptionLit)
                      and 
                      (
                        roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_8_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_8_2)
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objectsInstrumented_SinglyLinkedListCondition0[])
                      )
                      and 
                      (
                        roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_8_2=(roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_8_1)++((ClassFields)->(true)))
                      and 
                      (
                        throw_17=throw_18)
                    )
                  )
                )
                or 
                (
                  (
                    not (
                      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_17]
                    )
                  )
                  and 
                  TruePred[]
                  and 
                  (
                    roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_8_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_8_2)
                  and 
                  (
                    throw_17=throw_18)
                )
              )
              and 
              (
                (
                  roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_18]
                  and 
                  (
                    var_2_result_2=true)
                )
                or 
                (
                  (
                    not (
                      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_18]
                    )
                  )
                  and 
                  TruePred[]
                  and 
                  (
                    var_2_result_1=var_2_result_2)
                )
              )
              and 
              (
                roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_9_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_9_2)
            )
            or 
            (
              (
                not (
                  roops_core_objectsInstrumented_SinglyLinkedListCondition20[t_9_1])
              )
              and 
              (
                (
                  roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_17]
                  and 
                  (
                    (
                      roops_core_objectsInstrumented_SinglyLinkedListCondition0[]
                      and 
                      (
                        throw_18=java_lang_NullPointerExceptionLit)
                      and 
                      (
                        roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_9_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_9_2)
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objectsInstrumented_SinglyLinkedListCondition0[])
                      )
                      and 
                      (
                        roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_9_2=(roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_9_1)++((ClassFields)->(true)))
                      and 
                      (
                        throw_17=throw_18)
                    )
                  )
                )
                or 
                (
                  (
                    not (
                      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_17]
                    )
                  )
                  and 
                  TruePred[]
                  and 
                  (
                    roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_9_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_9_2)
                  and 
                  (
                    throw_17=throw_18)
                )
              )
              and 
              (
                var_2_result_1=var_2_result_2)
              and 
              (
                roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_8_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_8_2)
            )
          )
        )
        or 
        (
          (
            not (
              roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_17]
            )
          )
          and 
          TruePred[]
          and 
          (
            var_2_result_1=var_2_result_2)
          and 
          (
            roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_8_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_8_2)
          and 
          (
            roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_9_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_9_2)
          and 
          (
            throw_17=throw_18)
        )
      )
      and 
      (
        (
          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_18]
          and 
          (
            (
              roops_core_objectsInstrumented_SinglyLinkedListCondition22[var_1_current_1]
              and 
              (
                throw_19=java_lang_NullPointerExceptionLit)
              and 
              (
                var_1_current_1=var_1_current_2)
            )
            or 
            (
              (
                not (
                  roops_core_objectsInstrumented_SinglyLinkedListCondition22[var_1_current_1])
              )
              and 
              (
                var_1_current_2=var_1_current_1.roops_core_objectsInstrumented_SinglyLinkedListNode_next_0)
              and 
              (
                throw_18=throw_19)
            )
          )
        )
        or 
        (
          (
            not (
              roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_18]
            )
          )
          and 
          TruePred[]
          and 
          (
            var_1_current_1=var_1_current_2)
          and 
          (
            throw_18=throw_19)
        )
      )
      and 
      (
        (
          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_19]
          and 
          (
            t_11_1=(equ[var_2_result_2,
               false]=>(true)else(false))
          )
        )
        or 
        (
          (
            not (
              roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_19]
            )
          )
          and 
          TruePred[]
          and 
          (
            t_11_0=t_11_1)
        )
      )
      and 
      (
        (
          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_19]
          and 
          (
            t_12_1=(neq[var_1_current_2,
               null]=>(true)else(false))
          )
        )
        or 
        (
          (
            not (
              roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_19]
            )
          )
          and 
          TruePred[]
          and 
          (
            t_12_0=t_12_1)
        )
      )
      and 
      (
        (
          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_19]
          and 
          (
            (
              roops_core_objectsInstrumented_SinglyLinkedListCondition26[t_11_1]
              and 
              (
                (
                  roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_19]
                  and 
                  (
                    (
                      roops_core_objectsInstrumented_SinglyLinkedListCondition24[t_12_1]
                      and 
                      (
                        (
                          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_19]
                          and 
                          (
                            t_10_1=true)
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_19]
                            )
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            t_10_0=t_10_1)
                        )
                      )
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objectsInstrumented_SinglyLinkedListCondition24[t_12_1])
                      )
                      and 
                      (
                        (
                          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_19]
                          and 
                          (
                            t_10_1=false)
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_19]
                            )
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            t_10_0=t_10_1)
                        )
                      )
                    )
                  )
                )
                or 
                (
                  (
                    not (
                      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_19]
                    )
                  )
                  and 
                  TruePred[]
                  and 
                  (
                    t_10_0=t_10_1)
                )
              )
            )
            or 
            (
              (
                not (
                  roops_core_objectsInstrumented_SinglyLinkedListCondition26[t_11_1])
              )
              and 
              (
                (
                  roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_19]
                  and 
                  (
                    t_10_1=false)
                )
                or 
                (
                  (
                    not (
                      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_19]
                    )
                  )
                  and 
                  TruePred[]
                  and 
                  (
                    t_10_0=t_10_1)
                )
              )
            )
          )
        )
        or 
        (
          (
            not (
              roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_19]
            )
          )
          and 
          TruePred[]
          and 
          (
            t_10_0=t_10_1)
        )
      )
      and 
      (
        (
          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_19]
          and 
          (
            var_4_ws_1_2=t_10_1)
        )
        or 
        (
          (
            not (
              roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_19]
            )
          )
          and 
          TruePred[]
          and 
          (
            var_4_ws_1_1=var_4_ws_1_2)
        )
      )
      and 
      TruePred[]
    )
    or 
    (
      (
        not (
          roops_core_objectsInstrumented_SinglyLinkedListCondition28[exit_stmt_reached_1,
                                                                    throw_13,
                                                                    var_4_ws_1_1]
        )
      )
      and 
      TruePred[]
      and 
      (
        roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_0_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_0_2)
      and 
      (
        t_12_0=t_12_1)
      and 
      (
        t_10_0=t_10_1)
      and 
      (
        t_11_0=t_11_1)
      and 
      (
        var_3_fajita_cicle_0_1=var_3_fajita_cicle_0_2)
      and 
      (
        var_5_equalVal_0=var_5_equalVal_1)
      and 
      (
        roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_3_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_3_2)
      and 
      (
        roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_4_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_4_2)
      and 
      (
        roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_2_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_2_2)
      and 
      (
        roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_2)
      and 
      (
        roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_8_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_8_2)
      and 
      (
        roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_5_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_5_2)
      and 
      (
        roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_2)
      and 
      (
        roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_9_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_9_2)
      and 
      (
        var_2_result_1=var_2_result_2)
      and 
      (
        var_1_current_1=var_1_current_2)
      and 
      (
        t_9_0=t_9_1)
      and 
      (
        t_7_0=t_7_1)
      and 
      (
        t_8_0=t_8_1)
      and 
      (
        t_5_0=t_5_1)
      and 
      (
        t_6_0=t_6_1)
      and 
      (
        t_4_0=t_4_1)
      and 
      (
        throw_13=throw_19)
      and 
      (
        var_4_ws_1_1=var_4_ws_1_2)
    )
  )
  and 
  (
    not (
      roops_core_objectsInstrumented_SinglyLinkedListCondition28[exit_stmt_reached_1,
                                                                throw_19,
                                                                var_4_ws_1_2]
    )
  )
  and 
  (
    (
      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_19]
      and 
      (
        t_13_1=Not[var_3_fajita_cicle_0_2])
    )
    or 
    (
      (
        not (
          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_19]
        )
      )
      and 
      TruePred[]
      and 
      (
        t_13_0=t_13_1)
    )
  )
  and 
  (
    (
      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_19]
      and 
      (
        (
          roops_core_objectsInstrumented_SinglyLinkedListCondition29[t_13_1]
          and 
          (
            (
              roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_19]
              and 
              (
                (
                  roops_core_objectsInstrumented_SinglyLinkedListCondition0[]
                  and 
                  (
                    throw_20=java_lang_NullPointerExceptionLit)
                  and 
                  (
                    roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_1_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_1_2)
                )
                or 
                (
                  (
                    not (
                      roops_core_objectsInstrumented_SinglyLinkedListCondition0[])
                  )
                  and 
                  (
                    roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_1_2=(roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_1_1)++((ClassFields)->(true)))
                  and 
                  (
                    throw_19=throw_20)
                )
              )
            )
            or 
            (
              (
                not (
                  roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_19]
                )
              )
              and 
              TruePred[]
              and 
              (
                roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_1_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_1_2)
              and 
              (
                throw_19=throw_20)
            )
          )
        )
        or 
        (
          (
            not (
              roops_core_objectsInstrumented_SinglyLinkedListCondition29[t_13_1])
          )
          and 
          TruePred[]
          and 
          (
            throw_19=throw_20)
          and 
          (
            roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_1_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_1_2)
        )
      )
    )
    or 
    (
      (
        not (
          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_19]
        )
      )
      and 
      TruePred[]
      and 
      (
        roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_1_1=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_1_2)
      and 
      (
        throw_19=throw_20)
    )
  )
  and 
  (
    (
      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_20]
      and 
      (
        return_1=var_2_result_2)
      and 
      (
        exit_stmt_reached_2=true)
    )
    or 
    (
      (
        not (
          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_20]
        )
      )
      and 
      TruePred[]
      and 
      (
        return_0=return_1)
      and 
      (
        exit_stmt_reached_1=exit_stmt_reached_2)
    )
  )
  and 
  TruePred[]

}



pred roops_core_objectsInstrumented_SinglyLinkedList_fajita_roopsGoal_initialization_0[
  throw_1: java_lang_Throwable + null,
  throw_2: java_lang_Throwable + null,
  throw_3: java_lang_Throwable + null,
  throw_4: java_lang_Throwable + null,
  throw_5: java_lang_Throwable + null,
  throw_6: java_lang_Throwable + null,
  throw_7: java_lang_Throwable + null,
  throw_8: java_lang_Throwable + null,
  throw_9: java_lang_Throwable + null,
  throw_10: java_lang_Throwable + null,
  throw_11: java_lang_Throwable + null,
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_0_0: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_0_1: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_1_0: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_1_1: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_4_0: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_4_1: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_5_0: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_5_1: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_2_0: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_2_1: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_3_0: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_3_1: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_8_0: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_8_1: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_9_0: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_9_1: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_0: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_1: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_0: ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_1: ( ClassFields ) -> one ( boolean ),
  exit_stmt_reached_1: boolean
]{
  TruePred[]
  and 
  (
    throw_1=null)
  and 
  TruePred[]
  and 
  (
    exit_stmt_reached_1=false)
  and 
  (
    (
      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_1]
      and 
      (
        (
          roops_core_objectsInstrumented_SinglyLinkedListCondition0[]
          and 
          (
            throw_2=java_lang_NullPointerExceptionLit)
          and 
          (
            roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_0_0=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_0_1)
        )
        or 
        (
          (
            not (
              roops_core_objectsInstrumented_SinglyLinkedListCondition0[])
          )
          and 
          (
            roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_0_1=(roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_0_0)++((ClassFields)->(false)))
          and 
          (
            throw_1=throw_2)
        )
      )
    )
    or 
    (
      (
        not (
          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_1]
        )
      )
      and 
      TruePred[]
      and 
      (
        roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_0_0=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_0_1)
      and 
      (
        throw_1=throw_2)
    )
  )
  and 
  (
    (
      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_2]
      and 
      (
        (
          roops_core_objectsInstrumented_SinglyLinkedListCondition0[]
          and 
          (
            throw_3=java_lang_NullPointerExceptionLit)
          and 
          (
            roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_1_0=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_1_1)
        )
        or 
        (
          (
            not (
              roops_core_objectsInstrumented_SinglyLinkedListCondition0[])
          )
          and 
          (
            roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_1_1=(roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_1_0)++((ClassFields)->(false)))
          and 
          (
            throw_2=throw_3)
        )
      )
    )
    or 
    (
      (
        not (
          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_2]
        )
      )
      and 
      TruePred[]
      and 
      (
        roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_1_0=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_1_1)
      and 
      (
        throw_2=throw_3)
    )
  )
  and 
  (
    (
      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_3]
      and 
      (
        (
          roops_core_objectsInstrumented_SinglyLinkedListCondition0[]
          and 
          (
            throw_4=java_lang_NullPointerExceptionLit)
          and 
          (
            roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_2_0=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_2_1)
        )
        or 
        (
          (
            not (
              roops_core_objectsInstrumented_SinglyLinkedListCondition0[])
          )
          and 
          (
            roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_2_1=(roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_2_0)++((ClassFields)->(false)))
          and 
          (
            throw_3=throw_4)
        )
      )
    )
    or 
    (
      (
        not (
          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_3]
        )
      )
      and 
      TruePred[]
      and 
      (
        roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_2_0=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_2_1)
      and 
      (
        throw_3=throw_4)
    )
  )
  and 
  (
    (
      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_4]
      and 
      (
        (
          roops_core_objectsInstrumented_SinglyLinkedListCondition0[]
          and 
          (
            throw_5=java_lang_NullPointerExceptionLit)
          and 
          (
            roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_3_0=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_3_1)
        )
        or 
        (
          (
            not (
              roops_core_objectsInstrumented_SinglyLinkedListCondition0[])
          )
          and 
          (
            roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_3_1=(roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_3_0)++((ClassFields)->(false)))
          and 
          (
            throw_4=throw_5)
        )
      )
    )
    or 
    (
      (
        not (
          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_4]
        )
      )
      and 
      TruePred[]
      and 
      (
        roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_3_0=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_3_1)
      and 
      (
        throw_4=throw_5)
    )
  )
  and 
  (
    (
      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_5]
      and 
      (
        (
          roops_core_objectsInstrumented_SinglyLinkedListCondition0[]
          and 
          (
            throw_6=java_lang_NullPointerExceptionLit)
          and 
          (
            roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_4_0=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_4_1)
        )
        or 
        (
          (
            not (
              roops_core_objectsInstrumented_SinglyLinkedListCondition0[])
          )
          and 
          (
            roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_4_1=(roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_4_0)++((ClassFields)->(false)))
          and 
          (
            throw_5=throw_6)
        )
      )
    )
    or 
    (
      (
        not (
          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_5]
        )
      )
      and 
      TruePred[]
      and 
      (
        roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_4_0=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_4_1)
      and 
      (
        throw_5=throw_6)
    )
  )
  and 
  (
    (
      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_6]
      and 
      (
        (
          roops_core_objectsInstrumented_SinglyLinkedListCondition0[]
          and 
          (
            throw_7=java_lang_NullPointerExceptionLit)
          and 
          (
            roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_5_0=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_5_1)
        )
        or 
        (
          (
            not (
              roops_core_objectsInstrumented_SinglyLinkedListCondition0[])
          )
          and 
          (
            roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_5_1=(roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_5_0)++((ClassFields)->(false)))
          and 
          (
            throw_6=throw_7)
        )
      )
    )
    or 
    (
      (
        not (
          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_6]
        )
      )
      and 
      TruePred[]
      and 
      (
        roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_5_0=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_5_1)
      and 
      (
        throw_6=throw_7)
    )
  )
  and 
  (
    (
      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_7]
      and 
      (
        (
          roops_core_objectsInstrumented_SinglyLinkedListCondition0[]
          and 
          (
            throw_8=java_lang_NullPointerExceptionLit)
          and 
          (
            roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_0=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_1)
        )
        or 
        (
          (
            not (
              roops_core_objectsInstrumented_SinglyLinkedListCondition0[])
          )
          and 
          (
            roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_1=(roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_0)++((ClassFields)->(false)))
          and 
          (
            throw_7=throw_8)
        )
      )
    )
    or 
    (
      (
        not (
          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_7]
        )
      )
      and 
      TruePred[]
      and 
      (
        roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_0=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_1)
      and 
      (
        throw_7=throw_8)
    )
  )
  and 
  (
    (
      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_8]
      and 
      (
        (
          roops_core_objectsInstrumented_SinglyLinkedListCondition0[]
          and 
          (
            throw_9=java_lang_NullPointerExceptionLit)
          and 
          (
            roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_0=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_1)
        )
        or 
        (
          (
            not (
              roops_core_objectsInstrumented_SinglyLinkedListCondition0[])
          )
          and 
          (
            roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_1=(roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_0)++((ClassFields)->(false)))
          and 
          (
            throw_8=throw_9)
        )
      )
    )
    or 
    (
      (
        not (
          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_8]
        )
      )
      and 
      TruePred[]
      and 
      (
        roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_0=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_1)
      and 
      (
        throw_8=throw_9)
    )
  )
  and 
  (
    (
      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_9]
      and 
      (
        (
          roops_core_objectsInstrumented_SinglyLinkedListCondition0[]
          and 
          (
            throw_10=java_lang_NullPointerExceptionLit)
          and 
          (
            roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_8_0=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_8_1)
        )
        or 
        (
          (
            not (
              roops_core_objectsInstrumented_SinglyLinkedListCondition0[])
          )
          and 
          (
            roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_8_1=(roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_8_0)++((ClassFields)->(false)))
          and 
          (
            throw_9=throw_10)
        )
      )
    )
    or 
    (
      (
        not (
          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_9]
        )
      )
      and 
      TruePred[]
      and 
      (
        roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_8_0=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_8_1)
      and 
      (
        throw_9=throw_10)
    )
  )
  and 
  (
    (
      roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_10]
      and 
      (
        (
          roops_core_objectsInstrumented_SinglyLinkedListCondition0[]
          and 
          (
            throw_11=java_lang_NullPointerExceptionLit)
          and 
          (
            roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_9_0=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_9_1)
        )
        or 
        (
          (
            not (
              roops_core_objectsInstrumented_SinglyLinkedListCondition0[])
          )
          and 
          (
            roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_9_1=(roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_9_0)++((ClassFields)->(false)))
          and 
          (
            throw_10=throw_11)
        )
      )
    )
    or 
    (
      (
        not (
          roops_core_objectsInstrumented_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_10]
        )
      )
      and 
      TruePred[]
      and 
      (
        roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_9_0=roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_9_1)
      and 
      (
        throw_10=throw_11)
    )
  )
  and 
  TruePred[]

}

//-------------SMB sigs-------------// 
one sig roops_core_objectsInstrumented_SinglyLinkedList_0, roops_core_objectsInstrumented_SinglyLinkedList_1, roops_core_objectsInstrumented_SinglyLinkedList_2 extends roops_core_objectsInstrumented_SinglyLinkedList {} 

one sig roops_core_objectsInstrumented_SinglyLinkedListNode_0, roops_core_objectsInstrumented_SinglyLinkedListNode_1, roops_core_objectsInstrumented_SinglyLinkedListNode_2 extends roops_core_objectsInstrumented_SinglyLinkedListNode {} 

one sig java_lang_Object_0, java_lang_Object_1, java_lang_Object_2 extends java_lang_Object {} 

fact {
  no ( QF.froops_core_objectsInstrumented_SinglyLinkedListNode_next_0.univ & QF.broops_core_objectsInstrumented_SinglyLinkedListNode_next_0.univ ) and 
  roops_core_objectsInstrumented_SinglyLinkedListNode = QF.froops_core_objectsInstrumented_SinglyLinkedListNode_next_0.univ + QF.broops_core_objectsInstrumented_SinglyLinkedListNode_next_0.univ 
}
//-----SMB: local_ordering()-----//
fun next_roops_core_objectsInstrumented_SinglyLinkedList [] : roops_core_objectsInstrumented_SinglyLinkedList -> lone roops_core_objectsInstrumented_SinglyLinkedList { 
  roops_core_objectsInstrumented_SinglyLinkedList_0 -> roops_core_objectsInstrumented_SinglyLinkedList_1 +
  roops_core_objectsInstrumented_SinglyLinkedList_1 -> roops_core_objectsInstrumented_SinglyLinkedList_2 
}
fun min_roops_core_objectsInstrumented_SinglyLinkedList [os: set roops_core_objectsInstrumented_SinglyLinkedList] : lone roops_core_objectsInstrumented_SinglyLinkedList {
  os - os.^(next_roops_core_objectsInstrumented_SinglyLinkedList[]) 
}
fun prevs_roops_core_objectsInstrumented_SinglyLinkedList[o : roops_core_objectsInstrumented_SinglyLinkedList] : set roops_core_objectsInstrumented_SinglyLinkedList {
  o.^(~next_roops_core_objectsInstrumented_SinglyLinkedList[]) 
}
fun next_roops_core_objectsInstrumented_SinglyLinkedListNode [] : roops_core_objectsInstrumented_SinglyLinkedListNode -> lone roops_core_objectsInstrumented_SinglyLinkedListNode { 
  roops_core_objectsInstrumented_SinglyLinkedListNode_0 -> roops_core_objectsInstrumented_SinglyLinkedListNode_1 +
  roops_core_objectsInstrumented_SinglyLinkedListNode_1 -> roops_core_objectsInstrumented_SinglyLinkedListNode_2 
}
fun min_roops_core_objectsInstrumented_SinglyLinkedListNode [os: set roops_core_objectsInstrumented_SinglyLinkedListNode] : lone roops_core_objectsInstrumented_SinglyLinkedListNode {
  os - os.^(next_roops_core_objectsInstrumented_SinglyLinkedListNode[]) 
}
fun prevs_roops_core_objectsInstrumented_SinglyLinkedListNode[o : roops_core_objectsInstrumented_SinglyLinkedListNode] : set roops_core_objectsInstrumented_SinglyLinkedListNode {
  o.^(~next_roops_core_objectsInstrumented_SinglyLinkedListNode[]) 
}
fun next_java_lang_Object [] : java_lang_Object -> lone java_lang_Object { 
  java_lang_Object_0 -> java_lang_Object_1 +
  java_lang_Object_1 -> java_lang_Object_2 
}
fun min_java_lang_Object [os: set java_lang_Object] : lone java_lang_Object {
  os - os.^(next_java_lang_Object[]) 
}
fun prevs_java_lang_Object[o : java_lang_Object] : set java_lang_Object {
  o.^(~next_java_lang_Object[]) 
}
//-----SMB: global_ordering()-----//
fun globalNext[]: java_lang_Object -> lone java_lang_Object {
roops_core_objectsInstrumented_SinglyLinkedList_0 -> roops_core_objectsInstrumented_SinglyLinkedList_1  +  roops_core_objectsInstrumented_SinglyLinkedList_1 -> roops_core_objectsInstrumented_SinglyLinkedList_2  +  roops_core_objectsInstrumented_SinglyLinkedList_2 -> roops_core_objectsInstrumented_SinglyLinkedListNode_0  +  roops_core_objectsInstrumented_SinglyLinkedListNode_0 -> roops_core_objectsInstrumented_SinglyLinkedListNode_1  +  roops_core_objectsInstrumented_SinglyLinkedListNode_1 -> roops_core_objectsInstrumented_SinglyLinkedListNode_2  +  roops_core_objectsInstrumented_SinglyLinkedListNode_2 -> java_lang_Object_0  +  java_lang_Object_0 -> java_lang_Object_1  +  java_lang_Object_1 -> java_lang_Object_2
}
fun globalMin[s : set java_lang_Object] : lone java_lang_Object {
s - s.^globalNext[] 
}
//-----SMB: define_min_parent()-----//
fun minP_roops_core_objectsInstrumented_SinglyLinkedListNode [o : roops_core_objectsInstrumented_SinglyLinkedListNode] : java_lang_Object {
  globalMin[(QF.roops_core_objectsInstrumented_SinglyLinkedList_header_0 + QF.froops_core_objectsInstrumented_SinglyLinkedListNode_next_0).o]
}
fun minP_java_lang_Object [o : java_lang_Object] : java_lang_Object {
  globalMin[(QF.roops_core_objectsInstrumented_SinglyLinkedListNode_value_0).o]
}
//-----SMB: define_freach()-----//
fun FReach[] : set java_lang_Object {
(QF.thiz_0 + QF.value_param_0).*(QF.roops_core_objectsInstrumented_SinglyLinkedList_header_0 + QF.roops_core_objectsInstrumented_SinglyLinkedListNode_value_0 + QF.froops_core_objectsInstrumented_SinglyLinkedListNode_next_0) - null
}
//-----SMB: order_root_nodes()-----//
//-----SMB: root_is_minimum()-----//
fact {
((QF.thiz_0 != null) implies QF.thiz_0 = roops_core_objectsInstrumented_SinglyLinkedList_0 )
}
//-----SMB: order_same_min_parent()-----//
//-----SMB: order_same_min_parent_type()-----//
fact {
 all disj o1, o2:roops_core_objectsInstrumented_SinglyLinkedListNode |
  let p1=minP_roops_core_objectsInstrumented_SinglyLinkedListNode[o1]|
  let p2=minP_roops_core_objectsInstrumented_SinglyLinkedListNode[o2]|
  (o1 + o2 in FReach[] and
  some p1 and some p2 and
  p1!=p2 and p1+p2 in roops_core_objectsInstrumented_SinglyLinkedListNode and p1 in prevs_roops_core_objectsInstrumented_SinglyLinkedListNode[p2] )
  implies o1 in prevs_roops_core_objectsInstrumented_SinglyLinkedListNode[o2]
}
fact {
 all disj o1, o2:roops_core_objectsInstrumented_SinglyLinkedListNode |
  let p1=minP_roops_core_objectsInstrumented_SinglyLinkedListNode[o1]|
  let p2=minP_roops_core_objectsInstrumented_SinglyLinkedListNode[o2]|
  (o1 + o2 in FReach[] and
  some p1 and some p2 and
  p1!=p2 and p1+p2 in java_lang_Object and p1 in prevs_java_lang_Object[p2] )
  implies o1 in prevs_roops_core_objectsInstrumented_SinglyLinkedListNode[o2]
}
//-----SMB: order_diff_min_parent_type()-----//
fact {
 all disj o1, o2:roops_core_objectsInstrumented_SinglyLinkedListNode |
  let p1=minP_roops_core_objectsInstrumented_SinglyLinkedListNode[o1]|
  let p2=minP_roops_core_objectsInstrumented_SinglyLinkedListNode[o2]|
  ( o1+o2 in FReach[] and
 some p1 and some p2 and
p1 in roops_core_objectsInstrumented_SinglyLinkedList and p2 in roops_core_objectsInstrumented_SinglyLinkedListNode )
implies o1 in prevs_roops_core_objectsInstrumented_SinglyLinkedListNode[o2]
}
//-----SMB: avoid_holes()-----//
fact {
 all o : roops_core_objectsInstrumented_SinglyLinkedList | 
  o in FReach[] implies
   prevs_roops_core_objectsInstrumented_SinglyLinkedList[o] in FReach[]
}
fact {
 all o : roops_core_objectsInstrumented_SinglyLinkedListNode | 
  o in FReach[] implies
   prevs_roops_core_objectsInstrumented_SinglyLinkedListNode[o] in FReach[]
}
fact {
 all o : java_lang_Object | 
  o in FReach[] implies
   prevs_java_lang_Object[o] in FReach[]
}
/*
type ordering:
==============
1) roops_core_objectsInstrumented_SinglyLinkedList
2) roops_core_objectsInstrumented_SinglyLinkedListNode
3) java_lang_Object

root nodes ordering:
====================
1) thiz:roops_core_objectsInstrumented_SinglyLinkedList
2) value_param:java_lang_Object+null

recursive field ordering:
=========================
1) roops_core_objectsInstrumented_SinglyLinkedListNode_next:(roops_core_objectsInstrumented_SinglyLinkedListNode)->one(null+roops_core_objectsInstrumented_SinglyLinkedListNode)

non-recursive field ordering:
=============================
1) roops_core_objectsInstrumented_SinglyLinkedList_header:(roops_core_objectsInstrumented_SinglyLinkedList)->one(null+roops_core_objectsInstrumented_SinglyLinkedListNode)
2) roops_core_objectsInstrumented_SinglyLinkedListNode_value:(roops_core_objectsInstrumented_SinglyLinkedListNode)->one(java_lang_Object+null)
*/
one sig QF {
  BQ__0: boolean,
  BQ__1: boolean,
  BQ__2: boolean,
  BQ__3: boolean,
  BQ__4: boolean,
  BQ__5: boolean,
  BQ__6: boolean,
  BQ__7: boolean,
  BQ__8: boolean,
  BQ__9: boolean,
  broops_core_objectsInstrumented_SinglyLinkedListNode_next_0: (roops_core_objectsInstrumented_SinglyLinkedListNode) -> lone((roops_core_objectsInstrumented_SinglyLinkedListNode)),
  froops_core_objectsInstrumented_SinglyLinkedListNode_next_0: (roops_core_objectsInstrumented_SinglyLinkedListNode) -> lone((roops_core_objectsInstrumented_SinglyLinkedListNode + null)),
  l1_exit_stmt_reached_1:  boolean,
  l1_exit_stmt_reached_2:  boolean,
  l1_l0_exit_stmt_reached_1:  boolean,
  l1_t_10_0:  boolean,
  l1_t_10_1:  boolean,
  l1_t_11_0:  boolean,
  l1_t_11_1:  boolean,
  l1_t_12_0:  boolean,
  l1_t_12_1:  boolean,
  l1_t_13_0:  boolean,
  l1_t_13_1:  boolean,
  l1_t_1_0:  boolean,
  l1_t_1_1:  boolean,
  l1_t_2_0:  boolean,
  l1_t_2_1:  boolean,
  l1_t_3_0:  boolean,
  l1_t_3_1:  boolean,
  l1_t_4_0:  boolean,
  l1_t_4_1:  boolean,
  l1_t_5_0:  boolean,
  l1_t_5_1:  boolean,
  l1_t_6_0:  boolean,
  l1_t_6_1:  boolean,
  l1_t_7_0:  boolean,
  l1_t_7_1:  boolean,
  l1_t_8_0:  boolean,
  l1_t_8_1:  boolean,
  l1_t_9_0:  boolean,
  l1_t_9_1:  boolean,
  l1_var_1_current_0:  null + roops_core_objectsInstrumented_SinglyLinkedListNode,
  l1_var_1_current_1:  null + roops_core_objectsInstrumented_SinglyLinkedListNode,
  l1_var_1_current_2:  null + roops_core_objectsInstrumented_SinglyLinkedListNode,
  l1_var_2_result_0:  boolean,
  l1_var_2_result_1:  boolean,
  l1_var_2_result_2:  boolean,
  l1_var_3_fajita_cicle_0_0:  boolean,
  l1_var_3_fajita_cicle_0_1:  boolean,
  l1_var_3_fajita_cicle_0_2:  boolean,
  l1_var_4_ws_1_0:  boolean,
  l1_var_4_ws_1_1:  boolean,
  l1_var_4_ws_1_2:  boolean,
  l1_var_5_equalVal_0:  boolean,
  l1_var_5_equalVal_1:  boolean,
  return_0:  boolean,
  return_1:  boolean,
  roops_core_objectsInstrumented_SinglyLinkedListNode_value_0:  ( roops_core_objectsInstrumented_SinglyLinkedListNode ) -> one ( java_lang_Object + null ),
  roops_core_objectsInstrumented_SinglyLinkedList_header_0:  ( roops_core_objectsInstrumented_SinglyLinkedList ) -> one ( null + roops_core_objectsInstrumented_SinglyLinkedListNode ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_0_0:  ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_0_1:  ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_0_2:  ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_1_0:  ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_1_1:  ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_1_2:  ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_2_0:  ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_2_1:  ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_2_2:  ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_3_0:  ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_3_1:  ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_3_2:  ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_4_0:  ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_4_1:  ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_4_2:  ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_5_0:  ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_5_1:  ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_5_2:  ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_0:  ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_1:  ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_2:  ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_0:  ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_1:  ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_2:  ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_8_0:  ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_8_1:  ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_8_2:  ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_9_0:  ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_9_1:  ( ClassFields ) -> one ( boolean ),
  roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_9_2:  ( ClassFields ) -> one ( boolean ),
  thiz_0:  roops_core_objectsInstrumented_SinglyLinkedList,
  throw_0:  java_lang_Throwable + null,
  throw_1:  java_lang_Throwable + null,
  throw_10:  java_lang_Throwable + null,
  throw_11:  java_lang_Throwable + null,
  throw_12:  java_lang_Throwable + null,
  throw_13:  java_lang_Throwable + null,
  throw_14:  java_lang_Throwable + null,
  throw_15:  java_lang_Throwable + null,
  throw_16:  java_lang_Throwable + null,
  throw_17:  java_lang_Throwable + null,
  throw_18:  java_lang_Throwable + null,
  throw_19:  java_lang_Throwable + null,
  throw_2:  java_lang_Throwable + null,
  throw_20:  java_lang_Throwable + null,
  throw_3:  java_lang_Throwable + null,
  throw_4:  java_lang_Throwable + null,
  throw_5:  java_lang_Throwable + null,
  throw_6:  java_lang_Throwable + null,
  throw_7:  java_lang_Throwable + null,
  throw_8:  java_lang_Throwable + null,
  throw_9:  java_lang_Throwable + null,
  value_param_0:  java_lang_Object + null
}


fact {
  precondition_roops_core_objectsInstrumented_SinglyLinkedList_contains_0[(QF.broops_core_objectsInstrumented_SinglyLinkedListNode_next_0)+(QF.froops_core_objectsInstrumented_SinglyLinkedListNode_next_0),
                                                                         QF.roops_core_objectsInstrumented_SinglyLinkedList_header_0,
                                                                         QF.throw_0]

}

fact {
  roops_core_objectsInstrumented_SinglyLinkedList_contains_0[QF.thiz_0,
                                                            QF.throw_1,
                                                            QF.throw_2,
                                                            QF.throw_3,
                                                            QF.throw_4,
                                                            QF.throw_5,
                                                            QF.throw_6,
                                                            QF.throw_7,
                                                            QF.throw_8,
                                                            QF.throw_9,
                                                            QF.throw_10,
                                                            QF.throw_11,
                                                            QF.throw_12,
                                                            QF.throw_13,
                                                            QF.throw_14,
                                                            QF.throw_15,
                                                            QF.throw_16,
                                                            QF.throw_17,
                                                            QF.throw_18,
                                                            QF.throw_19,
                                                            QF.throw_20,
                                                            QF.return_0,
                                                            QF.return_1,
                                                            QF.value_param_0,
                                                            QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_0_0,
                                                            QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_0_1,
                                                            QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_0_2,
                                                            QF.roops_core_objectsInstrumented_SinglyLinkedList_header_0,
                                                            QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_1_0,
                                                            QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_1_1,
                                                            QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_1_2,
                                                            (QF.broops_core_objectsInstrumented_SinglyLinkedListNode_next_0)+(QF.froops_core_objectsInstrumented_SinglyLinkedListNode_next_0),
                                                            QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_4_0,
                                                            QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_4_1,
                                                            QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_4_2,
                                                            QF.roops_core_objectsInstrumented_SinglyLinkedListNode_value_0,
                                                            QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_5_0,
                                                            QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_5_1,
                                                            QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_5_2,
                                                            QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_2_0,
                                                            QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_2_1,
                                                            QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_2_2,
                                                            QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_3_0,
                                                            QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_3_1,
                                                            QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_3_2,
                                                            QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_8_0,
                                                            QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_8_1,
                                                            QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_8_2,
                                                            QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_9_0,
                                                            QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_9_1,
                                                            QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_9_2,
                                                            QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_0,
                                                            QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_1,
                                                            QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_2,
                                                            QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_0,
                                                            QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_1,
                                                            QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_2,
                                                            QF.l1_t_13_0,
                                                            QF.l1_t_13_1,
                                                            QF.l1_t_11_0,
                                                            QF.l1_t_11_1,
                                                            QF.l1_var_3_fajita_cicle_0_0,
                                                            QF.l1_var_3_fajita_cicle_0_1,
                                                            QF.l1_var_3_fajita_cicle_0_2,
                                                            QF.l1_var_4_ws_1_0,
                                                            QF.l1_var_4_ws_1_1,
                                                            QF.l1_var_4_ws_1_2,
                                                            QF.l1_t_12_0,
                                                            QF.l1_t_12_1,
                                                            QF.l1_t_10_0,
                                                            QF.l1_t_10_1,
                                                            QF.l1_t_2_0,
                                                            QF.l1_t_2_1,
                                                            QF.l1_t_3_0,
                                                            QF.l1_t_3_1,
                                                            QF.l1_exit_stmt_reached_1,
                                                            QF.l1_exit_stmt_reached_2,
                                                            QF.l1_t_1_0,
                                                            QF.l1_t_1_1,
                                                            QF.l1_var_1_current_0,
                                                            QF.l1_var_1_current_1,
                                                            QF.l1_var_1_current_2,
                                                            QF.l1_var_2_result_0,
                                                            QF.l1_var_2_result_1,
                                                            QF.l1_var_2_result_2,
                                                            QF.l1_t_8_0,
                                                            QF.l1_t_8_1,
                                                            QF.l1_t_9_0,
                                                            QF.l1_t_9_1,
                                                            QF.l1_t_6_0,
                                                            QF.l1_t_6_1,
                                                            QF.l1_t_7_0,
                                                            QF.l1_t_7_1,
                                                            QF.l1_t_4_0,
                                                            QF.l1_t_4_1,
                                                            QF.l1_var_5_equalVal_0,
                                                            QF.l1_var_5_equalVal_1,
                                                            QF.l1_t_5_0,
                                                            QF.l1_t_5_1,
                                                            QF.l1_l0_exit_stmt_reached_1]

}

assert check_roops_core_objectsInstrumented_SinglyLinkedList_contains_0{
  postcondition_roops_core_objectsInstrumented_SinglyLinkedList_contains_0[QF.throw_20]}
fact {
  QF.BQ__0=true iff ClassFields.(QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_0_2)=true
  QF.BQ__1=true iff ClassFields.(QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_1_2)=true
  QF.BQ__2=true iff ClassFields.(QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_2_2)=true
  QF.BQ__3=true iff ClassFields.(QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_3_2)=true
  QF.BQ__4=true iff ClassFields.(QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_4_2)=true
  QF.BQ__5=true iff ClassFields.(QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_5_2)=true
  QF.BQ__6=true iff ClassFields.(QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_6_2)=true
  QF.BQ__7=true iff ClassFields.(QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_7_2)=true
  QF.BQ__8=true iff ClassFields.(QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_8_2)=true
  QF.BQ__9=true iff ClassFields.(QF.roops_core_objectsInstrumented_SinglyLinkedList_roops_goal_9_2)=true
}
