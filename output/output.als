/* 
 * DynAlloy translator options 
 * --------------------------- 
 * assertionId= check_roops_core_objects_SinglyLinkedList_contains_0
 * loopUnroll= 7
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
//-------------- roops_core_objects_SinglyLinkedList--------------//
sig roops_core_objects_SinglyLinkedList extends java_lang_Object {}
{}




pred roops_core_objects_SinglyLinkedListCondition10[
  t_174:univ
]{
   t_174=true

}

pred roops_core_objects_SinglyLinkedListCondition11[
  t_174:univ
]{
   not (
     t_174=true)

}

pred roops_core_objects_SinglyLinkedListCondition13[
  t_171:univ
]{
   not (
     t_171=true)

}

pred roops_core_objects_SinglyLinkedListCondition12[
  t_171:univ
]{
   t_171=true

}

pred roops_core_objects_SinglyLinkedListCondition14[
  t_172:univ
]{
   t_172=true

}

pred roops_core_objects_SinglyLinkedListCondition15[
  t_172:univ
]{
   not (
     t_172=true)

}

pred roops_core_objects_SinglyLinkedListCondition7[
  t_169:univ
]{
   not (
     t_169=true)

}

pred roops_core_objects_SinglyLinkedListCondition6[
  t_169:univ
]{
   t_169=true

}

pred roops_core_objects_SinglyLinkedList_ensures[
  return':univ,
  roops_core_objects_SinglyLinkedListNode_next':univ->univ,
  roops_core_objects_SinglyLinkedListNode_value':univ->univ,
  roops_core_objects_SinglyLinkedList_header':univ->univ,
  thiz':univ,
  throw':univ,
  value_param':univ
]{
   (
     instanceOf[throw',
               java_lang_RuntimeException]
     implies 
             liftExpression[false]
   )
   and 
   (
     (
       throw'=null)
     implies 
             (
               (
                 (
                   some n:null+roops_core_objects_SinglyLinkedListNode | {
                     liftExpression[fun_set_contains[fun_reach[thiz'.roops_core_objects_SinglyLinkedList_header',roops_core_objects_SinglyLinkedListNode,roops_core_objects_SinglyLinkedListNode_next'],n]]
                     and 
                     equ[n.roops_core_objects_SinglyLinkedListNode_value',
                        value_param']
                   
                   }
                 )
                 implies 
                         equ[return',
                            true]
               )
               and 
               (
                 equ[return',
                    true]
                 implies 
                         (
                           some n:null+roops_core_objects_SinglyLinkedListNode | {
                             liftExpression[fun_set_contains[fun_reach[thiz'.roops_core_objects_SinglyLinkedList_header',roops_core_objects_SinglyLinkedListNode,roops_core_objects_SinglyLinkedListNode_next'],n]]
                             and 
                             equ[n.roops_core_objects_SinglyLinkedListNode_value',
                                value_param']
                           
                           }
                         )
               )
             )
   )

}

pred roops_core_objects_SinglyLinkedListCondition3[
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

pred roops_core_objects_SinglyLinkedListCondition2[
  exit_stmt_reached:univ,
  throw:univ
]{
   (
     throw=null)
   and 
   (
     exit_stmt_reached=false)

}

pred roops_core_objects_SinglyLinkedListCondition5[
  t_170:univ
]{
   not (
     t_170=true)

}

pred roops_core_objects_SinglyLinkedListCondition24[
  t_178:univ
]{
   t_178=true

}

pred roops_core_objects_SinglyLinkedListCondition25[
  t_178:univ
]{
   not (
     t_178=true)

}

pred roops_core_objects_SinglyLinkedListCondition22[
  t_179:univ
]{
   t_179=true

}

pred roops_core_objects_SinglyLinkedListCondition23[
  t_179:univ
]{
   not (
     t_179=true)

}

pred roops_core_objects_SinglyLinkedListCondition4[
  t_170:univ
]{
   t_170=true

}

pred postcondition_roops_core_objects_SinglyLinkedList_contains_0[
  return':univ,
  roops_core_objects_SinglyLinkedListNode_next':univ->univ,
  roops_core_objects_SinglyLinkedListNode_value':univ->univ,
  roops_core_objects_SinglyLinkedList_header':univ->univ,
  thiz':univ,
  throw':univ,
  value_param':univ
]{
   roops_core_objects_SinglyLinkedList_ensures[return',
                                              roops_core_objects_SinglyLinkedListNode_next',
                                              roops_core_objects_SinglyLinkedListNode_value',
                                              roops_core_objects_SinglyLinkedList_header',
                                              thiz',
                                              throw',
                                              value_param']
   and 
   (
     not (
       throw'=AssertionFailureLit)
   )

}

pred roops_core_objects_SinglyLinkedListCondition21[
  var_81_current:univ
]{
   not (
     isEmptyOrNull[var_81_current])

}

pred roops_core_objects_SinglyLinkedListCondition0[
  thiz:univ
]{
   isEmptyOrNull[thiz]

}

pred roops_core_objects_SinglyLinkedListCondition20[
  var_81_current:univ
]{
   isEmptyOrNull[var_81_current]

}

pred roops_core_objects_SinglyLinkedList_object_invariant[
  roops_core_objects_SinglyLinkedListNode_next:univ->univ,
  roops_core_objects_SinglyLinkedList_header:univ->univ,
  thiz:univ
]{
   all n:null+roops_core_objects_SinglyLinkedListNode | {
     liftExpression[fun_set_contains[fun_reach[thiz.roops_core_objects_SinglyLinkedList_header,roops_core_objects_SinglyLinkedListNode,roops_core_objects_SinglyLinkedListNode_next],n]]
     implies 
             equ[fun_set_contains[fun_reach[n.roops_core_objects_SinglyLinkedListNode_next,roops_core_objects_SinglyLinkedListNode,roops_core_objects_SinglyLinkedListNode_next],n],
                false]
   
   }

}

pred roops_core_objects_SinglyLinkedListCondition1[
  thiz:univ
]{
   not (
     isEmptyOrNull[thiz])

}

pred roops_core_objects_SinglyLinkedListCondition19[
  t_176:univ
]{
   not (
     t_176=true)

}

pred roops_core_objects_SinglyLinkedListCondition18[
  t_176:univ
]{
   t_176=true

}

pred roops_core_objects_SinglyLinkedListCondition26[
  exit_stmt_reached:univ,
  throw:univ,
  var_83_ws_25:univ
]{
   liftExpression[var_83_ws_25]
   and 
   (
     throw=null)
   and 
   (
     exit_stmt_reached=false)

}

pred roops_core_objects_SinglyLinkedListCondition17[
  t_173:univ
]{
   not (
     t_173=true)

}

pred precondition_roops_core_objects_SinglyLinkedList_contains_0[
  roops_core_objects_SinglyLinkedListNode_next:univ->univ,
  roops_core_objects_SinglyLinkedList_header:univ->univ,
  thiz:univ,
  throw:univ
]{
   roops_core_objects_SinglyLinkedList_object_invariant[roops_core_objects_SinglyLinkedListNode_next,
                                                       roops_core_objects_SinglyLinkedList_header,
                                                       thiz]
   and 
   equ[throw,
      null]

}

pred roops_core_objects_SinglyLinkedListCondition16[
  t_173:univ
]{
   t_173=true

}

pred roops_core_objects_SinglyLinkedListCondition9[
  t_175:univ
]{
   not (
     t_175=true)

}

pred roops_core_objects_SinglyLinkedListCondition8[
  t_175:univ
]{
   t_175=true

}
//-------------- java_lang_RuntimeException--------------//
abstract sig java_lang_RuntimeException extends java_lang_Exception {}
{}



one
sig java_lang_RuntimeExceptionLit extends java_lang_RuntimeException {}
{}

//-------------- java_lang_Exception--------------//
abstract sig java_lang_Exception extends java_lang_Throwable {}
{}



one
sig java_lang_ExceptionLit extends java_lang_Exception {}
{}

//-------------- java_lang_Throwable--------------//
abstract sig java_lang_Throwable extends java_lang_Object {}
{}



one
sig java_lang_ThrowableLit extends java_lang_Throwable {}
{}

//-------------- java_lang_Object--------------//
abstract sig java_lang_Object {}
{}




//-------------- roops_core_objects_SinglyLinkedListNode--------------//
sig roops_core_objects_SinglyLinkedListNode extends java_lang_Object {}
{}




//-------------- java_lang_NullPointerException--------------//
abstract one sig java_lang_NullPointerException extends java_lang_RuntimeException {}
{}



one
sig java_lang_NullPointerExceptionLit extends java_lang_NullPointerException {}
{}
check check_roops_core_objects_SinglyLinkedList_contains_0  for 0 but 1 roops_core_objects_SinglyLinkedList,7 roops_core_objects_SinglyLinkedListNode,8 java_lang_Object,4 int



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


pred roops_core_objects_SinglyLinkedList_contains_0[
  thiz_0: roops_core_objects_SinglyLinkedList,
  throw_1: java_lang_Throwable + null,
  throw_2: java_lang_Throwable + null,
  throw_3: java_lang_Throwable + null,
  throw_4: java_lang_Throwable + null,
  throw_5: java_lang_Throwable + null,
  throw_6: java_lang_Throwable + null,
  throw_7: java_lang_Throwable + null,
  throw_8: java_lang_Throwable + null,
  throw_9: java_lang_Throwable + null,
  return_0: boolean,
  return_1: boolean,
  value_param_0: java_lang_Object + null,
  roops_core_objects_SinglyLinkedListNode_next_0: ( roops_core_objects_SinglyLinkedListNode ) -> one ( null + roops_core_objects_SinglyLinkedListNode ),
  roops_core_objects_SinglyLinkedListNode_value_0: ( roops_core_objects_SinglyLinkedListNode ) -> one ( java_lang_Object + null ),
  roops_core_objects_SinglyLinkedList_header_0: ( roops_core_objects_SinglyLinkedList ) -> one ( null + roops_core_objects_SinglyLinkedListNode ),
  var_84_equalVal_0: boolean,
  var_84_equalVal_1: boolean,
  var_84_equalVal_2: boolean,
  var_84_equalVal_3: boolean,
  var_84_equalVal_4: boolean,
  var_84_equalVal_5: boolean,
  var_84_equalVal_6: boolean,
  var_84_equalVal_7: boolean,
  var_81_current_0: null + roops_core_objects_SinglyLinkedListNode,
  var_81_current_1: null + roops_core_objects_SinglyLinkedListNode,
  var_81_current_2: null + roops_core_objects_SinglyLinkedListNode,
  var_81_current_3: null + roops_core_objects_SinglyLinkedListNode,
  var_81_current_4: null + roops_core_objects_SinglyLinkedListNode,
  var_81_current_5: null + roops_core_objects_SinglyLinkedListNode,
  var_81_current_6: null + roops_core_objects_SinglyLinkedListNode,
  var_81_current_7: null + roops_core_objects_SinglyLinkedListNode,
  var_81_current_8: null + roops_core_objects_SinglyLinkedListNode,
  t_171_0: boolean,
  t_171_1: boolean,
  t_171_2: boolean,
  t_171_3: boolean,
  t_171_4: boolean,
  t_171_5: boolean,
  t_171_6: boolean,
  t_171_7: boolean,
  var_83_ws_25_0: boolean,
  var_83_ws_25_1: boolean,
  var_83_ws_25_2: boolean,
  var_83_ws_25_3: boolean,
  var_83_ws_25_4: boolean,
  var_83_ws_25_5: boolean,
  var_83_ws_25_6: boolean,
  var_83_ws_25_7: boolean,
  var_83_ws_25_8: boolean,
  t_170_0: boolean,
  t_170_1: boolean,
  t_168_0: boolean,
  t_168_1: boolean,
  exit_stmt_reached_1: boolean,
  exit_stmt_reached_2: boolean,
  var_82_result_0: boolean,
  var_82_result_1: boolean,
  var_82_result_2: boolean,
  var_82_result_3: boolean,
  var_82_result_4: boolean,
  var_82_result_5: boolean,
  var_82_result_6: boolean,
  var_82_result_7: boolean,
  var_82_result_8: boolean,
  t_173_0: boolean,
  t_173_1: boolean,
  t_173_2: boolean,
  t_173_3: boolean,
  t_173_4: boolean,
  t_173_5: boolean,
  t_173_6: boolean,
  t_173_7: boolean,
  t_172_0: boolean,
  t_172_1: boolean,
  t_172_2: boolean,
  t_172_3: boolean,
  t_172_4: boolean,
  t_172_5: boolean,
  t_172_6: boolean,
  t_172_7: boolean,
  t_175_0: boolean,
  t_175_1: boolean,
  t_175_2: boolean,
  t_175_3: boolean,
  t_175_4: boolean,
  t_175_5: boolean,
  t_175_6: boolean,
  t_175_7: boolean,
  t_174_0: boolean,
  t_174_1: boolean,
  t_174_2: boolean,
  t_174_3: boolean,
  t_174_4: boolean,
  t_174_5: boolean,
  t_174_6: boolean,
  t_174_7: boolean,
  t_177_0: boolean,
  t_177_1: boolean,
  t_177_2: boolean,
  t_177_3: boolean,
  t_177_4: boolean,
  t_177_5: boolean,
  t_177_6: boolean,
  t_177_7: boolean,
  t_176_0: boolean,
  t_176_1: boolean,
  t_176_2: boolean,
  t_176_3: boolean,
  t_176_4: boolean,
  t_176_5: boolean,
  t_176_6: boolean,
  t_176_7: boolean,
  t_179_0: boolean,
  t_179_1: boolean,
  t_179_2: boolean,
  t_179_3: boolean,
  t_179_4: boolean,
  t_179_5: boolean,
  t_179_6: boolean,
  t_179_7: boolean,
  t_169_0: boolean,
  t_169_1: boolean,
  t_178_0: boolean,
  t_178_1: boolean,
  t_178_2: boolean,
  t_178_3: boolean,
  t_178_4: boolean,
  t_178_5: boolean,
  t_178_6: boolean,
  t_178_7: boolean
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
      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                   throw_1]
      and 
      (
        (
          roops_core_objects_SinglyLinkedListCondition0[thiz_0]
          and 
          (
            throw_2=java_lang_NullPointerExceptionLit)
          and 
          (
            var_81_current_0=var_81_current_1)
        )
        or 
        (
          (
            not (
              roops_core_objects_SinglyLinkedListCondition0[thiz_0])
          )
          and 
          (
            var_81_current_1=thiz_0.roops_core_objects_SinglyLinkedList_header_0)
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
          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                       throw_1]
        )
      )
      and 
      TruePred[]
      and 
      (
        var_81_current_0=var_81_current_1)
      and 
      (
        throw_1=throw_2)
    )
  )
  and 
  (
    (
      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                   throw_2]
      and 
      (
        var_82_result_1=false)
    )
    or 
    (
      (
        not (
          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                       throw_2]
        )
      )
      and 
      TruePred[]
      and 
      (
        var_82_result_0=var_82_result_1)
    )
  )
  and 
  TruePred[]
  and 
  (
    (
      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                   throw_2]
      and 
      (
        t_169_1=(equ[var_82_result_1,
           false]=>(true)else(false))
      )
    )
    or 
    (
      (
        not (
          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                       throw_2]
        )
      )
      and 
      TruePred[]
      and 
      (
        t_169_0=t_169_1)
    )
  )
  and 
  (
    (
      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                   throw_2]
      and 
      (
        t_170_1=(neq[var_81_current_1,
           null]=>(true)else(false))
      )
    )
    or 
    (
      (
        not (
          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                       throw_2]
        )
      )
      and 
      TruePred[]
      and 
      (
        t_170_0=t_170_1)
    )
  )
  and 
  (
    (
      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                   throw_2]
      and 
      (
        (
          roops_core_objects_SinglyLinkedListCondition6[t_169_1]
          and 
          (
            (
              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                           throw_2]
              and 
              (
                (
                  roops_core_objects_SinglyLinkedListCondition4[t_170_1]
                  and 
                  (
                    (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_2]
                      and 
                      (
                        t_168_1=true)
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_2]
                        )
                      )
                      and 
                      TruePred[]
                      and 
                      (
                        t_168_0=t_168_1)
                    )
                  )
                )
                or 
                (
                  (
                    not (
                      roops_core_objects_SinglyLinkedListCondition4[t_170_1])
                  )
                  and 
                  (
                    (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_2]
                      and 
                      (
                        t_168_1=false)
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_2]
                        )
                      )
                      and 
                      TruePred[]
                      and 
                      (
                        t_168_0=t_168_1)
                    )
                  )
                )
              )
            )
            or 
            (
              (
                not (
                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_2]
                )
              )
              and 
              TruePred[]
              and 
              (
                t_168_0=t_168_1)
            )
          )
        )
        or 
        (
          (
            not (
              roops_core_objects_SinglyLinkedListCondition6[t_169_1])
          )
          and 
          (
            (
              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                           throw_2]
              and 
              (
                t_168_1=false)
            )
            or 
            (
              (
                not (
                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_2]
                )
              )
              and 
              TruePred[]
              and 
              (
                t_168_0=t_168_1)
            )
          )
        )
      )
    )
    or 
    (
      (
        not (
          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                       throw_2]
        )
      )
      and 
      TruePred[]
      and 
      (
        t_168_0=t_168_1)
    )
  )
  and 
  (
    (
      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                   throw_2]
      and 
      (
        var_83_ws_25_1=t_168_1)
    )
    or 
    (
      (
        not (
          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                       throw_2]
        )
      )
      and 
      TruePred[]
      and 
      (
        var_83_ws_25_0=var_83_ws_25_1)
    )
  )
  and 
  (
    (
      roops_core_objects_SinglyLinkedListCondition26[exit_stmt_reached_1,
                                                    throw_2,
                                                    var_83_ws_25_1]
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
      TruePred[]
      and 
      (
        (
          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                       throw_2]
          and 
          (
            t_174_1=(equ[value_param_0,
               null]=>(true)else(false))
          )
        )
        or 
        (
          (
            not (
              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                           throw_2]
            )
          )
          and 
          TruePred[]
          and 
          (
            t_174_0=t_174_1)
        )
      )
      and 
      (
        (
          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                       throw_2]
          and 
          (
            t_175_1=(equ[var_81_current_1.roops_core_objects_SinglyLinkedListNode_value_0,
               null]=>(true)else(false))
          )
        )
        or 
        (
          (
            not (
              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                           throw_2]
            )
          )
          and 
          TruePred[]
          and 
          (
            t_175_0=t_175_1)
        )
      )
      and 
      (
        (
          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                       throw_2]
          and 
          (
            (
              roops_core_objects_SinglyLinkedListCondition10[t_174_1]
              and 
              (
                (
                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_2]
                  and 
                  (
                    (
                      roops_core_objects_SinglyLinkedListCondition8[t_175_1]
                      and 
                      (
                        (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_2]
                          and 
                          (
                            t_173_1=true)
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_2]
                            )
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            t_173_0=t_173_1)
                        )
                      )
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objects_SinglyLinkedListCondition8[t_175_1])
                      )
                      and 
                      (
                        (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_2]
                          and 
                          (
                            t_173_1=false)
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_2]
                            )
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            t_173_0=t_173_1)
                        )
                      )
                    )
                  )
                )
                or 
                (
                  (
                    not (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_2]
                    )
                  )
                  and 
                  TruePred[]
                  and 
                  (
                    t_173_0=t_173_1)
                )
              )
            )
            or 
            (
              (
                not (
                  roops_core_objects_SinglyLinkedListCondition10[t_174_1])
              )
              and 
              (
                (
                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_2]
                  and 
                  (
                    t_173_1=false)
                )
                or 
                (
                  (
                    not (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_2]
                    )
                  )
                  and 
                  TruePred[]
                  and 
                  (
                    t_173_0=t_173_1)
                )
              )
            )
          )
        )
        or 
        (
          (
            not (
              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                           throw_2]
            )
          )
          and 
          TruePred[]
          and 
          (
            t_173_0=t_173_1)
        )
      )
      and 
      (
        (
          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                       throw_2]
          and 
          (
            (
              roops_core_objects_SinglyLinkedListCondition16[t_173_1]
              and 
              (
                (
                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_2]
                  and 
                  (
                    var_84_equalVal_1=true)
                )
                or 
                (
                  (
                    not (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_2]
                    )
                  )
                  and 
                  TruePred[]
                  and 
                  (
                    var_84_equalVal_0=var_84_equalVal_1)
                )
              )
              and 
              (
                t_172_0=t_172_1)
              and 
              (
                t_171_0=t_171_1)
            )
            or 
            (
              (
                not (
                  roops_core_objects_SinglyLinkedListCondition16[t_173_1])
              )
              and 
              TruePred[]
              and 
              (
                (
                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_2]
                  and 
                  (
                    t_172_1=(neq[value_param_0,
                       null]=>(true)else(false))
                  )
                )
                or 
                (
                  (
                    not (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_2]
                    )
                  )
                  and 
                  TruePred[]
                  and 
                  (
                    t_172_0=t_172_1)
                )
              )
              and 
              (
                (
                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_2]
                  and 
                  (
                    (
                      roops_core_objects_SinglyLinkedListCondition14[t_172_1]
                      and 
                      TruePred[]
                      and 
                      (
                        (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_2]
                          and 
                          (
                            t_171_1=(equ[value_param_0,
                               var_81_current_1.roops_core_objects_SinglyLinkedListNode_value_0]=>(true)else(false))
                          )
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_2]
                            )
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            t_171_0=t_171_1)
                        )
                      )
                      and 
                      (
                        (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_2]
                          and 
                          (
                            (
                              roops_core_objects_SinglyLinkedListCondition12[t_171_1]
                              and 
                              (
                                (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_2]
                                  and 
                                  (
                                    var_84_equalVal_1=true)
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_2]
                                    )
                                  )
                                  and 
                                  TruePred[]
                                  and 
                                  (
                                    var_84_equalVal_0=var_84_equalVal_1)
                                )
                              )
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objects_SinglyLinkedListCondition12[t_171_1])
                              )
                              and 
                              (
                                (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_2]
                                  and 
                                  (
                                    var_84_equalVal_1=false)
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_2]
                                    )
                                  )
                                  and 
                                  TruePred[]
                                  and 
                                  (
                                    var_84_equalVal_0=var_84_equalVal_1)
                                )
                              )
                            )
                          )
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_2]
                            )
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            var_84_equalVal_0=var_84_equalVal_1)
                        )
                      )
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objects_SinglyLinkedListCondition14[t_172_1])
                      )
                      and 
                      (
                        (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_2]
                          and 
                          (
                            var_84_equalVal_1=false)
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_2]
                            )
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            var_84_equalVal_0=var_84_equalVal_1)
                        )
                      )
                      and 
                      (
                        t_171_0=t_171_1)
                    )
                  )
                )
                or 
                (
                  (
                    not (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_2]
                    )
                  )
                  and 
                  TruePred[]
                  and 
                  (
                    t_171_0=t_171_1)
                  and 
                  (
                    var_84_equalVal_0=var_84_equalVal_1)
                )
              )
            )
          )
        )
        or 
        (
          (
            not (
              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                           throw_2]
            )
          )
          and 
          TruePred[]
          and 
          (
            t_172_0=t_172_1)
          and 
          (
            t_171_0=t_171_1)
          and 
          (
            var_84_equalVal_0=var_84_equalVal_1)
        )
      )
      and 
      (
        (
          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                       throw_2]
          and 
          (
            t_176_1=(equ[var_84_equalVal_1,
               true]=>(true)else(false))
          )
        )
        or 
        (
          (
            not (
              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                           throw_2]
            )
          )
          and 
          TruePred[]
          and 
          (
            t_176_0=t_176_1)
        )
      )
      and 
      (
        (
          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                       throw_2]
          and 
          (
            (
              roops_core_objects_SinglyLinkedListCondition18[t_176_1]
              and 
              (
                (
                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_2]
                  and 
                  (
                    var_82_result_2=true)
                )
                or 
                (
                  (
                    not (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_2]
                    )
                  )
                  and 
                  TruePred[]
                  and 
                  (
                    var_82_result_1=var_82_result_2)
                )
              )
            )
            or 
            (
              (
                not (
                  roops_core_objects_SinglyLinkedListCondition18[t_176_1])
              )
              and 
              TruePred[]
              and 
              (
                var_82_result_1=var_82_result_2)
            )
          )
        )
        or 
        (
          (
            not (
              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                           throw_2]
            )
          )
          and 
          TruePred[]
          and 
          (
            var_82_result_1=var_82_result_2)
        )
      )
      and 
      (
        (
          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                       throw_2]
          and 
          (
            (
              roops_core_objects_SinglyLinkedListCondition20[var_81_current_1]
              and 
              (
                throw_3=java_lang_NullPointerExceptionLit)
              and 
              (
                var_81_current_1=var_81_current_2)
            )
            or 
            (
              (
                not (
                  roops_core_objects_SinglyLinkedListCondition20[var_81_current_1])
              )
              and 
              (
                var_81_current_2=var_81_current_1.roops_core_objects_SinglyLinkedListNode_next_0)
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
              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                           throw_2]
            )
          )
          and 
          TruePred[]
          and 
          (
            var_81_current_1=var_81_current_2)
          and 
          (
            throw_2=throw_3)
        )
      )
      and 
      (
        (
          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                       throw_3]
          and 
          (
            t_178_1=(equ[var_82_result_2,
               false]=>(true)else(false))
          )
        )
        or 
        (
          (
            not (
              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                           throw_3]
            )
          )
          and 
          TruePred[]
          and 
          (
            t_178_0=t_178_1)
        )
      )
      and 
      (
        (
          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                       throw_3]
          and 
          (
            t_179_1=(neq[var_81_current_2,
               null]=>(true)else(false))
          )
        )
        or 
        (
          (
            not (
              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                           throw_3]
            )
          )
          and 
          TruePred[]
          and 
          (
            t_179_0=t_179_1)
        )
      )
      and 
      (
        (
          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                       throw_3]
          and 
          (
            (
              roops_core_objects_SinglyLinkedListCondition24[t_178_1]
              and 
              (
                (
                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_3]
                  and 
                  (
                    (
                      roops_core_objects_SinglyLinkedListCondition22[t_179_1]
                      and 
                      (
                        (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_3]
                          and 
                          (
                            t_177_1=true)
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_3]
                            )
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            t_177_0=t_177_1)
                        )
                      )
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objects_SinglyLinkedListCondition22[t_179_1])
                      )
                      and 
                      (
                        (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_3]
                          and 
                          (
                            t_177_1=false)
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_3]
                            )
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            t_177_0=t_177_1)
                        )
                      )
                    )
                  )
                )
                or 
                (
                  (
                    not (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_3]
                    )
                  )
                  and 
                  TruePred[]
                  and 
                  (
                    t_177_0=t_177_1)
                )
              )
            )
            or 
            (
              (
                not (
                  roops_core_objects_SinglyLinkedListCondition24[t_178_1])
              )
              and 
              (
                (
                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_3]
                  and 
                  (
                    t_177_1=false)
                )
                or 
                (
                  (
                    not (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_3]
                    )
                  )
                  and 
                  TruePred[]
                  and 
                  (
                    t_177_0=t_177_1)
                )
              )
            )
          )
        )
        or 
        (
          (
            not (
              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                           throw_3]
            )
          )
          and 
          TruePred[]
          and 
          (
            t_177_0=t_177_1)
        )
      )
      and 
      (
        (
          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                       throw_3]
          and 
          (
            var_83_ws_25_2=t_177_1)
        )
        or 
        (
          (
            not (
              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                           throw_3]
            )
          )
          and 
          TruePred[]
          and 
          (
            var_83_ws_25_1=var_83_ws_25_2)
        )
      )
      and 
      (
        (
          roops_core_objects_SinglyLinkedListCondition26[exit_stmt_reached_1,
                                                        throw_3,
                                                        var_83_ws_25_2]
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
          TruePred[]
          and 
          (
            (
              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                           throw_3]
              and 
              (
                t_174_2=(equ[value_param_0,
                   null]=>(true)else(false))
              )
            )
            or 
            (
              (
                not (
                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_3]
                )
              )
              and 
              TruePred[]
              and 
              (
                t_174_1=t_174_2)
            )
          )
          and 
          (
            (
              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                           throw_3]
              and 
              (
                t_175_2=(equ[var_81_current_2.roops_core_objects_SinglyLinkedListNode_value_0,
                   null]=>(true)else(false))
              )
            )
            or 
            (
              (
                not (
                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_3]
                )
              )
              and 
              TruePred[]
              and 
              (
                t_175_1=t_175_2)
            )
          )
          and 
          (
            (
              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                           throw_3]
              and 
              (
                (
                  roops_core_objects_SinglyLinkedListCondition10[t_174_2]
                  and 
                  (
                    (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_3]
                      and 
                      (
                        (
                          roops_core_objects_SinglyLinkedListCondition8[t_175_2]
                          and 
                          (
                            (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_3]
                              and 
                              (
                                t_173_2=true)
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_3]
                                )
                              )
                              and 
                              TruePred[]
                              and 
                              (
                                t_173_1=t_173_2)
                            )
                          )
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objects_SinglyLinkedListCondition8[t_175_2])
                          )
                          and 
                          (
                            (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_3]
                              and 
                              (
                                t_173_2=false)
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_3]
                                )
                              )
                              and 
                              TruePred[]
                              and 
                              (
                                t_173_1=t_173_2)
                            )
                          )
                        )
                      )
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_3]
                        )
                      )
                      and 
                      TruePred[]
                      and 
                      (
                        t_173_1=t_173_2)
                    )
                  )
                )
                or 
                (
                  (
                    not (
                      roops_core_objects_SinglyLinkedListCondition10[t_174_2])
                  )
                  and 
                  (
                    (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_3]
                      and 
                      (
                        t_173_2=false)
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_3]
                        )
                      )
                      and 
                      TruePred[]
                      and 
                      (
                        t_173_1=t_173_2)
                    )
                  )
                )
              )
            )
            or 
            (
              (
                not (
                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_3]
                )
              )
              and 
              TruePred[]
              and 
              (
                t_173_1=t_173_2)
            )
          )
          and 
          (
            (
              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                           throw_3]
              and 
              (
                (
                  roops_core_objects_SinglyLinkedListCondition16[t_173_2]
                  and 
                  (
                    (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_3]
                      and 
                      (
                        var_84_equalVal_2=true)
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_3]
                        )
                      )
                      and 
                      TruePred[]
                      and 
                      (
                        var_84_equalVal_1=var_84_equalVal_2)
                    )
                  )
                  and 
                  (
                    t_172_1=t_172_2)
                  and 
                  (
                    t_171_1=t_171_2)
                )
                or 
                (
                  (
                    not (
                      roops_core_objects_SinglyLinkedListCondition16[t_173_2])
                  )
                  and 
                  TruePred[]
                  and 
                  (
                    (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_3]
                      and 
                      (
                        t_172_2=(neq[value_param_0,
                           null]=>(true)else(false))
                      )
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_3]
                        )
                      )
                      and 
                      TruePred[]
                      and 
                      (
                        t_172_1=t_172_2)
                    )
                  )
                  and 
                  (
                    (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_3]
                      and 
                      (
                        (
                          roops_core_objects_SinglyLinkedListCondition14[t_172_2]
                          and 
                          TruePred[]
                          and 
                          (
                            (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_3]
                              and 
                              (
                                t_171_2=(equ[value_param_0,
                                   var_81_current_2.roops_core_objects_SinglyLinkedListNode_value_0]=>(true)else(false))
                              )
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_3]
                                )
                              )
                              and 
                              TruePred[]
                              and 
                              (
                                t_171_1=t_171_2)
                            )
                          )
                          and 
                          (
                            (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_3]
                              and 
                              (
                                (
                                  roops_core_objects_SinglyLinkedListCondition12[t_171_2]
                                  and 
                                  (
                                    (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_3]
                                      and 
                                      (
                                        var_84_equalVal_2=true)
                                    )
                                    or 
                                    (
                                      (
                                        not (
                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_3]
                                        )
                                      )
                                      and 
                                      TruePred[]
                                      and 
                                      (
                                        var_84_equalVal_1=var_84_equalVal_2)
                                    )
                                  )
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objects_SinglyLinkedListCondition12[t_171_2])
                                  )
                                  and 
                                  (
                                    (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_3]
                                      and 
                                      (
                                        var_84_equalVal_2=false)
                                    )
                                    or 
                                    (
                                      (
                                        not (
                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_3]
                                        )
                                      )
                                      and 
                                      TruePred[]
                                      and 
                                      (
                                        var_84_equalVal_1=var_84_equalVal_2)
                                    )
                                  )
                                )
                              )
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_3]
                                )
                              )
                              and 
                              TruePred[]
                              and 
                              (
                                var_84_equalVal_1=var_84_equalVal_2)
                            )
                          )
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objects_SinglyLinkedListCondition14[t_172_2])
                          )
                          and 
                          (
                            (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_3]
                              and 
                              (
                                var_84_equalVal_2=false)
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_3]
                                )
                              )
                              and 
                              TruePred[]
                              and 
                              (
                                var_84_equalVal_1=var_84_equalVal_2)
                            )
                          )
                          and 
                          (
                            t_171_1=t_171_2)
                        )
                      )
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_3]
                        )
                      )
                      and 
                      TruePred[]
                      and 
                      (
                        t_171_1=t_171_2)
                      and 
                      (
                        var_84_equalVal_1=var_84_equalVal_2)
                    )
                  )
                )
              )
            )
            or 
            (
              (
                not (
                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_3]
                )
              )
              and 
              TruePred[]
              and 
              (
                t_172_1=t_172_2)
              and 
              (
                t_171_1=t_171_2)
              and 
              (
                var_84_equalVal_1=var_84_equalVal_2)
            )
          )
          and 
          (
            (
              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                           throw_3]
              and 
              (
                t_176_2=(equ[var_84_equalVal_2,
                   true]=>(true)else(false))
              )
            )
            or 
            (
              (
                not (
                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_3]
                )
              )
              and 
              TruePred[]
              and 
              (
                t_176_1=t_176_2)
            )
          )
          and 
          (
            (
              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                           throw_3]
              and 
              (
                (
                  roops_core_objects_SinglyLinkedListCondition18[t_176_2]
                  and 
                  (
                    (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_3]
                      and 
                      (
                        var_82_result_3=true)
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_3]
                        )
                      )
                      and 
                      TruePred[]
                      and 
                      (
                        var_82_result_2=var_82_result_3)
                    )
                  )
                )
                or 
                (
                  (
                    not (
                      roops_core_objects_SinglyLinkedListCondition18[t_176_2])
                  )
                  and 
                  TruePred[]
                  and 
                  (
                    var_82_result_2=var_82_result_3)
                )
              )
            )
            or 
            (
              (
                not (
                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_3]
                )
              )
              and 
              TruePred[]
              and 
              (
                var_82_result_2=var_82_result_3)
            )
          )
          and 
          (
            (
              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                           throw_3]
              and 
              (
                (
                  roops_core_objects_SinglyLinkedListCondition20[var_81_current_2]
                  and 
                  (
                    throw_4=java_lang_NullPointerExceptionLit)
                  and 
                  (
                    var_81_current_2=var_81_current_3)
                )
                or 
                (
                  (
                    not (
                      roops_core_objects_SinglyLinkedListCondition20[var_81_current_2])
                  )
                  and 
                  (
                    var_81_current_3=var_81_current_2.roops_core_objects_SinglyLinkedListNode_next_0)
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
                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_3]
                )
              )
              and 
              TruePred[]
              and 
              (
                var_81_current_2=var_81_current_3)
              and 
              (
                throw_3=throw_4)
            )
          )
          and 
          (
            (
              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                           throw_4]
              and 
              (
                t_178_2=(equ[var_82_result_3,
                   false]=>(true)else(false))
              )
            )
            or 
            (
              (
                not (
                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_4]
                )
              )
              and 
              TruePred[]
              and 
              (
                t_178_1=t_178_2)
            )
          )
          and 
          (
            (
              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                           throw_4]
              and 
              (
                t_179_2=(neq[var_81_current_3,
                   null]=>(true)else(false))
              )
            )
            or 
            (
              (
                not (
                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_4]
                )
              )
              and 
              TruePred[]
              and 
              (
                t_179_1=t_179_2)
            )
          )
          and 
          (
            (
              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                           throw_4]
              and 
              (
                (
                  roops_core_objects_SinglyLinkedListCondition24[t_178_2]
                  and 
                  (
                    (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_4]
                      and 
                      (
                        (
                          roops_core_objects_SinglyLinkedListCondition22[t_179_2]
                          and 
                          (
                            (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_4]
                              and 
                              (
                                t_177_2=true)
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_4]
                                )
                              )
                              and 
                              TruePred[]
                              and 
                              (
                                t_177_1=t_177_2)
                            )
                          )
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objects_SinglyLinkedListCondition22[t_179_2])
                          )
                          and 
                          (
                            (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_4]
                              and 
                              (
                                t_177_2=false)
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_4]
                                )
                              )
                              and 
                              TruePred[]
                              and 
                              (
                                t_177_1=t_177_2)
                            )
                          )
                        )
                      )
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_4]
                        )
                      )
                      and 
                      TruePred[]
                      and 
                      (
                        t_177_1=t_177_2)
                    )
                  )
                )
                or 
                (
                  (
                    not (
                      roops_core_objects_SinglyLinkedListCondition24[t_178_2])
                  )
                  and 
                  (
                    (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_4]
                      and 
                      (
                        t_177_2=false)
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_4]
                        )
                      )
                      and 
                      TruePred[]
                      and 
                      (
                        t_177_1=t_177_2)
                    )
                  )
                )
              )
            )
            or 
            (
              (
                not (
                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_4]
                )
              )
              and 
              TruePred[]
              and 
              (
                t_177_1=t_177_2)
            )
          )
          and 
          (
            (
              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                           throw_4]
              and 
              (
                var_83_ws_25_3=t_177_2)
            )
            or 
            (
              (
                not (
                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_4]
                )
              )
              and 
              TruePred[]
              and 
              (
                var_83_ws_25_2=var_83_ws_25_3)
            )
          )
          and 
          (
            (
              roops_core_objects_SinglyLinkedListCondition26[exit_stmt_reached_1,
                                                            throw_4,
                                                            var_83_ws_25_3]
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
              TruePred[]
              and 
              (
                (
                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_4]
                  and 
                  (
                    t_174_3=(equ[value_param_0,
                       null]=>(true)else(false))
                  )
                )
                or 
                (
                  (
                    not (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_4]
                    )
                  )
                  and 
                  TruePred[]
                  and 
                  (
                    t_174_2=t_174_3)
                )
              )
              and 
              (
                (
                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_4]
                  and 
                  (
                    t_175_3=(equ[var_81_current_3.roops_core_objects_SinglyLinkedListNode_value_0,
                       null]=>(true)else(false))
                  )
                )
                or 
                (
                  (
                    not (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_4]
                    )
                  )
                  and 
                  TruePred[]
                  and 
                  (
                    t_175_2=t_175_3)
                )
              )
              and 
              (
                (
                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_4]
                  and 
                  (
                    (
                      roops_core_objects_SinglyLinkedListCondition10[t_174_3]
                      and 
                      (
                        (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_4]
                          and 
                          (
                            (
                              roops_core_objects_SinglyLinkedListCondition8[t_175_3]
                              and 
                              (
                                (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_4]
                                  and 
                                  (
                                    t_173_3=true)
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_4]
                                    )
                                  )
                                  and 
                                  TruePred[]
                                  and 
                                  (
                                    t_173_2=t_173_3)
                                )
                              )
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objects_SinglyLinkedListCondition8[t_175_3])
                              )
                              and 
                              (
                                (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_4]
                                  and 
                                  (
                                    t_173_3=false)
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_4]
                                    )
                                  )
                                  and 
                                  TruePred[]
                                  and 
                                  (
                                    t_173_2=t_173_3)
                                )
                              )
                            )
                          )
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_4]
                            )
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            t_173_2=t_173_3)
                        )
                      )
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objects_SinglyLinkedListCondition10[t_174_3])
                      )
                      and 
                      (
                        (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_4]
                          and 
                          (
                            t_173_3=false)
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_4]
                            )
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            t_173_2=t_173_3)
                        )
                      )
                    )
                  )
                )
                or 
                (
                  (
                    not (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_4]
                    )
                  )
                  and 
                  TruePred[]
                  and 
                  (
                    t_173_2=t_173_3)
                )
              )
              and 
              (
                (
                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_4]
                  and 
                  (
                    (
                      roops_core_objects_SinglyLinkedListCondition16[t_173_3]
                      and 
                      (
                        (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_4]
                          and 
                          (
                            var_84_equalVal_3=true)
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_4]
                            )
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            var_84_equalVal_2=var_84_equalVal_3)
                        )
                      )
                      and 
                      (
                        t_172_2=t_172_3)
                      and 
                      (
                        t_171_2=t_171_3)
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objects_SinglyLinkedListCondition16[t_173_3])
                      )
                      and 
                      TruePred[]
                      and 
                      (
                        (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_4]
                          and 
                          (
                            t_172_3=(neq[value_param_0,
                               null]=>(true)else(false))
                          )
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_4]
                            )
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            t_172_2=t_172_3)
                        )
                      )
                      and 
                      (
                        (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_4]
                          and 
                          (
                            (
                              roops_core_objects_SinglyLinkedListCondition14[t_172_3]
                              and 
                              TruePred[]
                              and 
                              (
                                (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_4]
                                  and 
                                  (
                                    t_171_3=(equ[value_param_0,
                                       var_81_current_3.roops_core_objects_SinglyLinkedListNode_value_0]=>(true)else(false))
                                  )
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_4]
                                    )
                                  )
                                  and 
                                  TruePred[]
                                  and 
                                  (
                                    t_171_2=t_171_3)
                                )
                              )
                              and 
                              (
                                (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_4]
                                  and 
                                  (
                                    (
                                      roops_core_objects_SinglyLinkedListCondition12[t_171_3]
                                      and 
                                      (
                                        (
                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_4]
                                          and 
                                          (
                                            var_84_equalVal_3=true)
                                        )
                                        or 
                                        (
                                          (
                                            not (
                                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                           throw_4]
                                            )
                                          )
                                          and 
                                          TruePred[]
                                          and 
                                          (
                                            var_84_equalVal_2=var_84_equalVal_3)
                                        )
                                      )
                                    )
                                    or 
                                    (
                                      (
                                        not (
                                          roops_core_objects_SinglyLinkedListCondition12[t_171_3])
                                      )
                                      and 
                                      (
                                        (
                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_4]
                                          and 
                                          (
                                            var_84_equalVal_3=false)
                                        )
                                        or 
                                        (
                                          (
                                            not (
                                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                           throw_4]
                                            )
                                          )
                                          and 
                                          TruePred[]
                                          and 
                                          (
                                            var_84_equalVal_2=var_84_equalVal_3)
                                        )
                                      )
                                    )
                                  )
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_4]
                                    )
                                  )
                                  and 
                                  TruePred[]
                                  and 
                                  (
                                    var_84_equalVal_2=var_84_equalVal_3)
                                )
                              )
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objects_SinglyLinkedListCondition14[t_172_3])
                              )
                              and 
                              (
                                (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_4]
                                  and 
                                  (
                                    var_84_equalVal_3=false)
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_4]
                                    )
                                  )
                                  and 
                                  TruePred[]
                                  and 
                                  (
                                    var_84_equalVal_2=var_84_equalVal_3)
                                )
                              )
                              and 
                              (
                                t_171_2=t_171_3)
                            )
                          )
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_4]
                            )
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            t_171_2=t_171_3)
                          and 
                          (
                            var_84_equalVal_2=var_84_equalVal_3)
                        )
                      )
                    )
                  )
                )
                or 
                (
                  (
                    not (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_4]
                    )
                  )
                  and 
                  TruePred[]
                  and 
                  (
                    t_172_2=t_172_3)
                  and 
                  (
                    t_171_2=t_171_3)
                  and 
                  (
                    var_84_equalVal_2=var_84_equalVal_3)
                )
              )
              and 
              (
                (
                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_4]
                  and 
                  (
                    t_176_3=(equ[var_84_equalVal_3,
                       true]=>(true)else(false))
                  )
                )
                or 
                (
                  (
                    not (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_4]
                    )
                  )
                  and 
                  TruePred[]
                  and 
                  (
                    t_176_2=t_176_3)
                )
              )
              and 
              (
                (
                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_4]
                  and 
                  (
                    (
                      roops_core_objects_SinglyLinkedListCondition18[t_176_3]
                      and 
                      (
                        (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_4]
                          and 
                          (
                            var_82_result_4=true)
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_4]
                            )
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            var_82_result_3=var_82_result_4)
                        )
                      )
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objects_SinglyLinkedListCondition18[t_176_3])
                      )
                      and 
                      TruePred[]
                      and 
                      (
                        var_82_result_3=var_82_result_4)
                    )
                  )
                )
                or 
                (
                  (
                    not (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_4]
                    )
                  )
                  and 
                  TruePred[]
                  and 
                  (
                    var_82_result_3=var_82_result_4)
                )
              )
              and 
              (
                (
                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_4]
                  and 
                  (
                    (
                      roops_core_objects_SinglyLinkedListCondition20[var_81_current_3]
                      and 
                      (
                        throw_5=java_lang_NullPointerExceptionLit)
                      and 
                      (
                        var_81_current_3=var_81_current_4)
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objects_SinglyLinkedListCondition20[var_81_current_3])
                      )
                      and 
                      (
                        var_81_current_4=var_81_current_3.roops_core_objects_SinglyLinkedListNode_next_0)
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
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_4]
                    )
                  )
                  and 
                  TruePred[]
                  and 
                  (
                    var_81_current_3=var_81_current_4)
                  and 
                  (
                    throw_4=throw_5)
                )
              )
              and 
              (
                (
                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_5]
                  and 
                  (
                    t_178_3=(equ[var_82_result_4,
                       false]=>(true)else(false))
                  )
                )
                or 
                (
                  (
                    not (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_5]
                    )
                  )
                  and 
                  TruePred[]
                  and 
                  (
                    t_178_2=t_178_3)
                )
              )
              and 
              (
                (
                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_5]
                  and 
                  (
                    t_179_3=(neq[var_81_current_4,
                       null]=>(true)else(false))
                  )
                )
                or 
                (
                  (
                    not (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_5]
                    )
                  )
                  and 
                  TruePred[]
                  and 
                  (
                    t_179_2=t_179_3)
                )
              )
              and 
              (
                (
                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_5]
                  and 
                  (
                    (
                      roops_core_objects_SinglyLinkedListCondition24[t_178_3]
                      and 
                      (
                        (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_5]
                          and 
                          (
                            (
                              roops_core_objects_SinglyLinkedListCondition22[t_179_3]
                              and 
                              (
                                (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_5]
                                  and 
                                  (
                                    t_177_3=true)
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_5]
                                    )
                                  )
                                  and 
                                  TruePred[]
                                  and 
                                  (
                                    t_177_2=t_177_3)
                                )
                              )
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objects_SinglyLinkedListCondition22[t_179_3])
                              )
                              and 
                              (
                                (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_5]
                                  and 
                                  (
                                    t_177_3=false)
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_5]
                                    )
                                  )
                                  and 
                                  TruePred[]
                                  and 
                                  (
                                    t_177_2=t_177_3)
                                )
                              )
                            )
                          )
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_5]
                            )
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            t_177_2=t_177_3)
                        )
                      )
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objects_SinglyLinkedListCondition24[t_178_3])
                      )
                      and 
                      (
                        (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_5]
                          and 
                          (
                            t_177_3=false)
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_5]
                            )
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            t_177_2=t_177_3)
                        )
                      )
                    )
                  )
                )
                or 
                (
                  (
                    not (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_5]
                    )
                  )
                  and 
                  TruePred[]
                  and 
                  (
                    t_177_2=t_177_3)
                )
              )
              and 
              (
                (
                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                               throw_5]
                  and 
                  (
                    var_83_ws_25_4=t_177_3)
                )
                or 
                (
                  (
                    not (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_5]
                    )
                  )
                  and 
                  TruePred[]
                  and 
                  (
                    var_83_ws_25_3=var_83_ws_25_4)
                )
              )
              and 
              (
                (
                  roops_core_objects_SinglyLinkedListCondition26[exit_stmt_reached_1,
                                                                throw_5,
                                                                var_83_ws_25_4]
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
                  TruePred[]
                  and 
                  (
                    (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_5]
                      and 
                      (
                        t_174_4=(equ[value_param_0,
                           null]=>(true)else(false))
                      )
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_5]
                        )
                      )
                      and 
                      TruePred[]
                      and 
                      (
                        t_174_3=t_174_4)
                    )
                  )
                  and 
                  (
                    (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_5]
                      and 
                      (
                        t_175_4=(equ[var_81_current_4.roops_core_objects_SinglyLinkedListNode_value_0,
                           null]=>(true)else(false))
                      )
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_5]
                        )
                      )
                      and 
                      TruePred[]
                      and 
                      (
                        t_175_3=t_175_4)
                    )
                  )
                  and 
                  (
                    (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_5]
                      and 
                      (
                        (
                          roops_core_objects_SinglyLinkedListCondition10[t_174_4]
                          and 
                          (
                            (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_5]
                              and 
                              (
                                (
                                  roops_core_objects_SinglyLinkedListCondition8[t_175_4]
                                  and 
                                  (
                                    (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_5]
                                      and 
                                      (
                                        t_173_4=true)
                                    )
                                    or 
                                    (
                                      (
                                        not (
                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_5]
                                        )
                                      )
                                      and 
                                      TruePred[]
                                      and 
                                      (
                                        t_173_3=t_173_4)
                                    )
                                  )
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objects_SinglyLinkedListCondition8[t_175_4])
                                  )
                                  and 
                                  (
                                    (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_5]
                                      and 
                                      (
                                        t_173_4=false)
                                    )
                                    or 
                                    (
                                      (
                                        not (
                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_5]
                                        )
                                      )
                                      and 
                                      TruePred[]
                                      and 
                                      (
                                        t_173_3=t_173_4)
                                    )
                                  )
                                )
                              )
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_5]
                                )
                              )
                              and 
                              TruePred[]
                              and 
                              (
                                t_173_3=t_173_4)
                            )
                          )
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objects_SinglyLinkedListCondition10[t_174_4])
                          )
                          and 
                          (
                            (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_5]
                              and 
                              (
                                t_173_4=false)
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_5]
                                )
                              )
                              and 
                              TruePred[]
                              and 
                              (
                                t_173_3=t_173_4)
                            )
                          )
                        )
                      )
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_5]
                        )
                      )
                      and 
                      TruePred[]
                      and 
                      (
                        t_173_3=t_173_4)
                    )
                  )
                  and 
                  (
                    (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_5]
                      and 
                      (
                        (
                          roops_core_objects_SinglyLinkedListCondition16[t_173_4]
                          and 
                          (
                            (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_5]
                              and 
                              (
                                var_84_equalVal_4=true)
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_5]
                                )
                              )
                              and 
                              TruePred[]
                              and 
                              (
                                var_84_equalVal_3=var_84_equalVal_4)
                            )
                          )
                          and 
                          (
                            t_172_3=t_172_4)
                          and 
                          (
                            t_171_3=t_171_4)
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objects_SinglyLinkedListCondition16[t_173_4])
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_5]
                              and 
                              (
                                t_172_4=(neq[value_param_0,
                                   null]=>(true)else(false))
                              )
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_5]
                                )
                              )
                              and 
                              TruePred[]
                              and 
                              (
                                t_172_3=t_172_4)
                            )
                          )
                          and 
                          (
                            (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_5]
                              and 
                              (
                                (
                                  roops_core_objects_SinglyLinkedListCondition14[t_172_4]
                                  and 
                                  TruePred[]
                                  and 
                                  (
                                    (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_5]
                                      and 
                                      (
                                        t_171_4=(equ[value_param_0,
                                           var_81_current_4.roops_core_objects_SinglyLinkedListNode_value_0]=>(true)else(false))
                                      )
                                    )
                                    or 
                                    (
                                      (
                                        not (
                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_5]
                                        )
                                      )
                                      and 
                                      TruePred[]
                                      and 
                                      (
                                        t_171_3=t_171_4)
                                    )
                                  )
                                  and 
                                  (
                                    (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_5]
                                      and 
                                      (
                                        (
                                          roops_core_objects_SinglyLinkedListCondition12[t_171_4]
                                          and 
                                          (
                                            (
                                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                           throw_5]
                                              and 
                                              (
                                                var_84_equalVal_4=true)
                                            )
                                            or 
                                            (
                                              (
                                                not (
                                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                               throw_5]
                                                )
                                              )
                                              and 
                                              TruePred[]
                                              and 
                                              (
                                                var_84_equalVal_3=var_84_equalVal_4)
                                            )
                                          )
                                        )
                                        or 
                                        (
                                          (
                                            not (
                                              roops_core_objects_SinglyLinkedListCondition12[t_171_4])
                                          )
                                          and 
                                          (
                                            (
                                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                           throw_5]
                                              and 
                                              (
                                                var_84_equalVal_4=false)
                                            )
                                            or 
                                            (
                                              (
                                                not (
                                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                               throw_5]
                                                )
                                              )
                                              and 
                                              TruePred[]
                                              and 
                                              (
                                                var_84_equalVal_3=var_84_equalVal_4)
                                            )
                                          )
                                        )
                                      )
                                    )
                                    or 
                                    (
                                      (
                                        not (
                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_5]
                                        )
                                      )
                                      and 
                                      TruePred[]
                                      and 
                                      (
                                        var_84_equalVal_3=var_84_equalVal_4)
                                    )
                                  )
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objects_SinglyLinkedListCondition14[t_172_4])
                                  )
                                  and 
                                  (
                                    (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_5]
                                      and 
                                      (
                                        var_84_equalVal_4=false)
                                    )
                                    or 
                                    (
                                      (
                                        not (
                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_5]
                                        )
                                      )
                                      and 
                                      TruePred[]
                                      and 
                                      (
                                        var_84_equalVal_3=var_84_equalVal_4)
                                    )
                                  )
                                  and 
                                  (
                                    t_171_3=t_171_4)
                                )
                              )
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_5]
                                )
                              )
                              and 
                              TruePred[]
                              and 
                              (
                                t_171_3=t_171_4)
                              and 
                              (
                                var_84_equalVal_3=var_84_equalVal_4)
                            )
                          )
                        )
                      )
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_5]
                        )
                      )
                      and 
                      TruePred[]
                      and 
                      (
                        t_172_3=t_172_4)
                      and 
                      (
                        t_171_3=t_171_4)
                      and 
                      (
                        var_84_equalVal_3=var_84_equalVal_4)
                    )
                  )
                  and 
                  (
                    (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_5]
                      and 
                      (
                        t_176_4=(equ[var_84_equalVal_4,
                           true]=>(true)else(false))
                      )
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_5]
                        )
                      )
                      and 
                      TruePred[]
                      and 
                      (
                        t_176_3=t_176_4)
                    )
                  )
                  and 
                  (
                    (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_5]
                      and 
                      (
                        (
                          roops_core_objects_SinglyLinkedListCondition18[t_176_4]
                          and 
                          (
                            (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_5]
                              and 
                              (
                                var_82_result_5=true)
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_5]
                                )
                              )
                              and 
                              TruePred[]
                              and 
                              (
                                var_82_result_4=var_82_result_5)
                            )
                          )
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objects_SinglyLinkedListCondition18[t_176_4])
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            var_82_result_4=var_82_result_5)
                        )
                      )
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_5]
                        )
                      )
                      and 
                      TruePred[]
                      and 
                      (
                        var_82_result_4=var_82_result_5)
                    )
                  )
                  and 
                  (
                    (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_5]
                      and 
                      (
                        (
                          roops_core_objects_SinglyLinkedListCondition20[var_81_current_4]
                          and 
                          (
                            throw_6=java_lang_NullPointerExceptionLit)
                          and 
                          (
                            var_81_current_4=var_81_current_5)
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objects_SinglyLinkedListCondition20[var_81_current_4])
                          )
                          and 
                          (
                            var_81_current_5=var_81_current_4.roops_core_objects_SinglyLinkedListNode_next_0)
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
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_5]
                        )
                      )
                      and 
                      TruePred[]
                      and 
                      (
                        var_81_current_4=var_81_current_5)
                      and 
                      (
                        throw_5=throw_6)
                    )
                  )
                  and 
                  (
                    (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_6]
                      and 
                      (
                        t_178_4=(equ[var_82_result_5,
                           false]=>(true)else(false))
                      )
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_6]
                        )
                      )
                      and 
                      TruePred[]
                      and 
                      (
                        t_178_3=t_178_4)
                    )
                  )
                  and 
                  (
                    (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_6]
                      and 
                      (
                        t_179_4=(neq[var_81_current_5,
                           null]=>(true)else(false))
                      )
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_6]
                        )
                      )
                      and 
                      TruePred[]
                      and 
                      (
                        t_179_3=t_179_4)
                    )
                  )
                  and 
                  (
                    (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_6]
                      and 
                      (
                        (
                          roops_core_objects_SinglyLinkedListCondition24[t_178_4]
                          and 
                          (
                            (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_6]
                              and 
                              (
                                (
                                  roops_core_objects_SinglyLinkedListCondition22[t_179_4]
                                  and 
                                  (
                                    (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_6]
                                      and 
                                      (
                                        t_177_4=true)
                                    )
                                    or 
                                    (
                                      (
                                        not (
                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_6]
                                        )
                                      )
                                      and 
                                      TruePred[]
                                      and 
                                      (
                                        t_177_3=t_177_4)
                                    )
                                  )
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objects_SinglyLinkedListCondition22[t_179_4])
                                  )
                                  and 
                                  (
                                    (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_6]
                                      and 
                                      (
                                        t_177_4=false)
                                    )
                                    or 
                                    (
                                      (
                                        not (
                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_6]
                                        )
                                      )
                                      and 
                                      TruePred[]
                                      and 
                                      (
                                        t_177_3=t_177_4)
                                    )
                                  )
                                )
                              )
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_6]
                                )
                              )
                              and 
                              TruePred[]
                              and 
                              (
                                t_177_3=t_177_4)
                            )
                          )
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objects_SinglyLinkedListCondition24[t_178_4])
                          )
                          and 
                          (
                            (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_6]
                              and 
                              (
                                t_177_4=false)
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_6]
                                )
                              )
                              and 
                              TruePred[]
                              and 
                              (
                                t_177_3=t_177_4)
                            )
                          )
                        )
                      )
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_6]
                        )
                      )
                      and 
                      TruePred[]
                      and 
                      (
                        t_177_3=t_177_4)
                    )
                  )
                  and 
                  (
                    (
                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                   throw_6]
                      and 
                      (
                        var_83_ws_25_5=t_177_4)
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_6]
                        )
                      )
                      and 
                      TruePred[]
                      and 
                      (
                        var_83_ws_25_4=var_83_ws_25_5)
                    )
                  )
                  and 
                  (
                    (
                      roops_core_objects_SinglyLinkedListCondition26[exit_stmt_reached_1,
                                                                    throw_6,
                                                                    var_83_ws_25_5]
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
                      TruePred[]
                      and 
                      (
                        (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_6]
                          and 
                          (
                            t_174_5=(equ[value_param_0,
                               null]=>(true)else(false))
                          )
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_6]
                            )
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            t_174_4=t_174_5)
                        )
                      )
                      and 
                      (
                        (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_6]
                          and 
                          (
                            t_175_5=(equ[var_81_current_5.roops_core_objects_SinglyLinkedListNode_value_0,
                               null]=>(true)else(false))
                          )
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_6]
                            )
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            t_175_4=t_175_5)
                        )
                      )
                      and 
                      (
                        (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_6]
                          and 
                          (
                            (
                              roops_core_objects_SinglyLinkedListCondition10[t_174_5]
                              and 
                              (
                                (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_6]
                                  and 
                                  (
                                    (
                                      roops_core_objects_SinglyLinkedListCondition8[t_175_5]
                                      and 
                                      (
                                        (
                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_6]
                                          and 
                                          (
                                            t_173_5=true)
                                        )
                                        or 
                                        (
                                          (
                                            not (
                                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                           throw_6]
                                            )
                                          )
                                          and 
                                          TruePred[]
                                          and 
                                          (
                                            t_173_4=t_173_5)
                                        )
                                      )
                                    )
                                    or 
                                    (
                                      (
                                        not (
                                          roops_core_objects_SinglyLinkedListCondition8[t_175_5])
                                      )
                                      and 
                                      (
                                        (
                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_6]
                                          and 
                                          (
                                            t_173_5=false)
                                        )
                                        or 
                                        (
                                          (
                                            not (
                                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                           throw_6]
                                            )
                                          )
                                          and 
                                          TruePred[]
                                          and 
                                          (
                                            t_173_4=t_173_5)
                                        )
                                      )
                                    )
                                  )
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_6]
                                    )
                                  )
                                  and 
                                  TruePred[]
                                  and 
                                  (
                                    t_173_4=t_173_5)
                                )
                              )
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objects_SinglyLinkedListCondition10[t_174_5])
                              )
                              and 
                              (
                                (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_6]
                                  and 
                                  (
                                    t_173_5=false)
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_6]
                                    )
                                  )
                                  and 
                                  TruePred[]
                                  and 
                                  (
                                    t_173_4=t_173_5)
                                )
                              )
                            )
                          )
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_6]
                            )
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            t_173_4=t_173_5)
                        )
                      )
                      and 
                      (
                        (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_6]
                          and 
                          (
                            (
                              roops_core_objects_SinglyLinkedListCondition16[t_173_5]
                              and 
                              (
                                (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_6]
                                  and 
                                  (
                                    var_84_equalVal_5=true)
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_6]
                                    )
                                  )
                                  and 
                                  TruePred[]
                                  and 
                                  (
                                    var_84_equalVal_4=var_84_equalVal_5)
                                )
                              )
                              and 
                              (
                                t_172_4=t_172_5)
                              and 
                              (
                                t_171_4=t_171_5)
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objects_SinglyLinkedListCondition16[t_173_5])
                              )
                              and 
                              TruePred[]
                              and 
                              (
                                (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_6]
                                  and 
                                  (
                                    t_172_5=(neq[value_param_0,
                                       null]=>(true)else(false))
                                  )
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_6]
                                    )
                                  )
                                  and 
                                  TruePred[]
                                  and 
                                  (
                                    t_172_4=t_172_5)
                                )
                              )
                              and 
                              (
                                (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_6]
                                  and 
                                  (
                                    (
                                      roops_core_objects_SinglyLinkedListCondition14[t_172_5]
                                      and 
                                      TruePred[]
                                      and 
                                      (
                                        (
                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_6]
                                          and 
                                          (
                                            t_171_5=(equ[value_param_0,
                                               var_81_current_5.roops_core_objects_SinglyLinkedListNode_value_0]=>(true)else(false))
                                          )
                                        )
                                        or 
                                        (
                                          (
                                            not (
                                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                           throw_6]
                                            )
                                          )
                                          and 
                                          TruePred[]
                                          and 
                                          (
                                            t_171_4=t_171_5)
                                        )
                                      )
                                      and 
                                      (
                                        (
                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_6]
                                          and 
                                          (
                                            (
                                              roops_core_objects_SinglyLinkedListCondition12[t_171_5]
                                              and 
                                              (
                                                (
                                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                               throw_6]
                                                  and 
                                                  (
                                                    var_84_equalVal_5=true)
                                                )
                                                or 
                                                (
                                                  (
                                                    not (
                                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                                   throw_6]
                                                    )
                                                  )
                                                  and 
                                                  TruePred[]
                                                  and 
                                                  (
                                                    var_84_equalVal_4=var_84_equalVal_5)
                                                )
                                              )
                                            )
                                            or 
                                            (
                                              (
                                                not (
                                                  roops_core_objects_SinglyLinkedListCondition12[t_171_5])
                                              )
                                              and 
                                              (
                                                (
                                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                               throw_6]
                                                  and 
                                                  (
                                                    var_84_equalVal_5=false)
                                                )
                                                or 
                                                (
                                                  (
                                                    not (
                                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                                   throw_6]
                                                    )
                                                  )
                                                  and 
                                                  TruePred[]
                                                  and 
                                                  (
                                                    var_84_equalVal_4=var_84_equalVal_5)
                                                )
                                              )
                                            )
                                          )
                                        )
                                        or 
                                        (
                                          (
                                            not (
                                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                           throw_6]
                                            )
                                          )
                                          and 
                                          TruePred[]
                                          and 
                                          (
                                            var_84_equalVal_4=var_84_equalVal_5)
                                        )
                                      )
                                    )
                                    or 
                                    (
                                      (
                                        not (
                                          roops_core_objects_SinglyLinkedListCondition14[t_172_5])
                                      )
                                      and 
                                      (
                                        (
                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_6]
                                          and 
                                          (
                                            var_84_equalVal_5=false)
                                        )
                                        or 
                                        (
                                          (
                                            not (
                                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                           throw_6]
                                            )
                                          )
                                          and 
                                          TruePred[]
                                          and 
                                          (
                                            var_84_equalVal_4=var_84_equalVal_5)
                                        )
                                      )
                                      and 
                                      (
                                        t_171_4=t_171_5)
                                    )
                                  )
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_6]
                                    )
                                  )
                                  and 
                                  TruePred[]
                                  and 
                                  (
                                    t_171_4=t_171_5)
                                  and 
                                  (
                                    var_84_equalVal_4=var_84_equalVal_5)
                                )
                              )
                            )
                          )
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_6]
                            )
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            t_172_4=t_172_5)
                          and 
                          (
                            t_171_4=t_171_5)
                          and 
                          (
                            var_84_equalVal_4=var_84_equalVal_5)
                        )
                      )
                      and 
                      (
                        (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_6]
                          and 
                          (
                            t_176_5=(equ[var_84_equalVal_5,
                               true]=>(true)else(false))
                          )
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_6]
                            )
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            t_176_4=t_176_5)
                        )
                      )
                      and 
                      (
                        (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_6]
                          and 
                          (
                            (
                              roops_core_objects_SinglyLinkedListCondition18[t_176_5]
                              and 
                              (
                                (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_6]
                                  and 
                                  (
                                    var_82_result_6=true)
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_6]
                                    )
                                  )
                                  and 
                                  TruePred[]
                                  and 
                                  (
                                    var_82_result_5=var_82_result_6)
                                )
                              )
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objects_SinglyLinkedListCondition18[t_176_5])
                              )
                              and 
                              TruePred[]
                              and 
                              (
                                var_82_result_5=var_82_result_6)
                            )
                          )
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_6]
                            )
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            var_82_result_5=var_82_result_6)
                        )
                      )
                      and 
                      (
                        (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_6]
                          and 
                          (
                            (
                              roops_core_objects_SinglyLinkedListCondition20[var_81_current_5]
                              and 
                              (
                                throw_7=java_lang_NullPointerExceptionLit)
                              and 
                              (
                                var_81_current_5=var_81_current_6)
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objects_SinglyLinkedListCondition20[var_81_current_5])
                              )
                              and 
                              (
                                var_81_current_6=var_81_current_5.roops_core_objects_SinglyLinkedListNode_next_0)
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
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_6]
                            )
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            var_81_current_5=var_81_current_6)
                          and 
                          (
                            throw_6=throw_7)
                        )
                      )
                      and 
                      (
                        (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_7]
                          and 
                          (
                            t_178_5=(equ[var_82_result_6,
                               false]=>(true)else(false))
                          )
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_7]
                            )
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            t_178_4=t_178_5)
                        )
                      )
                      and 
                      (
                        (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_7]
                          and 
                          (
                            t_179_5=(neq[var_81_current_6,
                               null]=>(true)else(false))
                          )
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_7]
                            )
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            t_179_4=t_179_5)
                        )
                      )
                      and 
                      (
                        (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_7]
                          and 
                          (
                            (
                              roops_core_objects_SinglyLinkedListCondition24[t_178_5]
                              and 
                              (
                                (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_7]
                                  and 
                                  (
                                    (
                                      roops_core_objects_SinglyLinkedListCondition22[t_179_5]
                                      and 
                                      (
                                        (
                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_7]
                                          and 
                                          (
                                            t_177_5=true)
                                        )
                                        or 
                                        (
                                          (
                                            not (
                                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                           throw_7]
                                            )
                                          )
                                          and 
                                          TruePred[]
                                          and 
                                          (
                                            t_177_4=t_177_5)
                                        )
                                      )
                                    )
                                    or 
                                    (
                                      (
                                        not (
                                          roops_core_objects_SinglyLinkedListCondition22[t_179_5])
                                      )
                                      and 
                                      (
                                        (
                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_7]
                                          and 
                                          (
                                            t_177_5=false)
                                        )
                                        or 
                                        (
                                          (
                                            not (
                                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                           throw_7]
                                            )
                                          )
                                          and 
                                          TruePred[]
                                          and 
                                          (
                                            t_177_4=t_177_5)
                                        )
                                      )
                                    )
                                  )
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_7]
                                    )
                                  )
                                  and 
                                  TruePred[]
                                  and 
                                  (
                                    t_177_4=t_177_5)
                                )
                              )
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objects_SinglyLinkedListCondition24[t_178_5])
                              )
                              and 
                              (
                                (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_7]
                                  and 
                                  (
                                    t_177_5=false)
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_7]
                                    )
                                  )
                                  and 
                                  TruePred[]
                                  and 
                                  (
                                    t_177_4=t_177_5)
                                )
                              )
                            )
                          )
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_7]
                            )
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            t_177_4=t_177_5)
                        )
                      )
                      and 
                      (
                        (
                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                       throw_7]
                          and 
                          (
                            var_83_ws_25_6=t_177_5)
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_7]
                            )
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            var_83_ws_25_5=var_83_ws_25_6)
                        )
                      )
                      and 
                      (
                        (
                          roops_core_objects_SinglyLinkedListCondition26[exit_stmt_reached_1,
                                                                        throw_7,
                                                                        var_83_ws_25_6]
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
                          TruePred[]
                          and 
                          (
                            (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_7]
                              and 
                              (
                                t_174_6=(equ[value_param_0,
                                   null]=>(true)else(false))
                              )
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_7]
                                )
                              )
                              and 
                              TruePred[]
                              and 
                              (
                                t_174_5=t_174_6)
                            )
                          )
                          and 
                          (
                            (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_7]
                              and 
                              (
                                t_175_6=(equ[var_81_current_6.roops_core_objects_SinglyLinkedListNode_value_0,
                                   null]=>(true)else(false))
                              )
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_7]
                                )
                              )
                              and 
                              TruePred[]
                              and 
                              (
                                t_175_5=t_175_6)
                            )
                          )
                          and 
                          (
                            (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_7]
                              and 
                              (
                                (
                                  roops_core_objects_SinglyLinkedListCondition10[t_174_6]
                                  and 
                                  (
                                    (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_7]
                                      and 
                                      (
                                        (
                                          roops_core_objects_SinglyLinkedListCondition8[t_175_6]
                                          and 
                                          (
                                            (
                                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                           throw_7]
                                              and 
                                              (
                                                t_173_6=true)
                                            )
                                            or 
                                            (
                                              (
                                                not (
                                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                               throw_7]
                                                )
                                              )
                                              and 
                                              TruePred[]
                                              and 
                                              (
                                                t_173_5=t_173_6)
                                            )
                                          )
                                        )
                                        or 
                                        (
                                          (
                                            not (
                                              roops_core_objects_SinglyLinkedListCondition8[t_175_6])
                                          )
                                          and 
                                          (
                                            (
                                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                           throw_7]
                                              and 
                                              (
                                                t_173_6=false)
                                            )
                                            or 
                                            (
                                              (
                                                not (
                                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                               throw_7]
                                                )
                                              )
                                              and 
                                              TruePred[]
                                              and 
                                              (
                                                t_173_5=t_173_6)
                                            )
                                          )
                                        )
                                      )
                                    )
                                    or 
                                    (
                                      (
                                        not (
                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_7]
                                        )
                                      )
                                      and 
                                      TruePred[]
                                      and 
                                      (
                                        t_173_5=t_173_6)
                                    )
                                  )
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objects_SinglyLinkedListCondition10[t_174_6])
                                  )
                                  and 
                                  (
                                    (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_7]
                                      and 
                                      (
                                        t_173_6=false)
                                    )
                                    or 
                                    (
                                      (
                                        not (
                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_7]
                                        )
                                      )
                                      and 
                                      TruePred[]
                                      and 
                                      (
                                        t_173_5=t_173_6)
                                    )
                                  )
                                )
                              )
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_7]
                                )
                              )
                              and 
                              TruePred[]
                              and 
                              (
                                t_173_5=t_173_6)
                            )
                          )
                          and 
                          (
                            (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_7]
                              and 
                              (
                                (
                                  roops_core_objects_SinglyLinkedListCondition16[t_173_6]
                                  and 
                                  (
                                    (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_7]
                                      and 
                                      (
                                        var_84_equalVal_6=true)
                                    )
                                    or 
                                    (
                                      (
                                        not (
                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_7]
                                        )
                                      )
                                      and 
                                      TruePred[]
                                      and 
                                      (
                                        var_84_equalVal_5=var_84_equalVal_6)
                                    )
                                  )
                                  and 
                                  (
                                    t_172_5=t_172_6)
                                  and 
                                  (
                                    t_171_5=t_171_6)
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objects_SinglyLinkedListCondition16[t_173_6])
                                  )
                                  and 
                                  TruePred[]
                                  and 
                                  (
                                    (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_7]
                                      and 
                                      (
                                        t_172_6=(neq[value_param_0,
                                           null]=>(true)else(false))
                                      )
                                    )
                                    or 
                                    (
                                      (
                                        not (
                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_7]
                                        )
                                      )
                                      and 
                                      TruePred[]
                                      and 
                                      (
                                        t_172_5=t_172_6)
                                    )
                                  )
                                  and 
                                  (
                                    (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_7]
                                      and 
                                      (
                                        (
                                          roops_core_objects_SinglyLinkedListCondition14[t_172_6]
                                          and 
                                          TruePred[]
                                          and 
                                          (
                                            (
                                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                           throw_7]
                                              and 
                                              (
                                                t_171_6=(equ[value_param_0,
                                                   var_81_current_6.roops_core_objects_SinglyLinkedListNode_value_0]=>(true)else(false))
                                              )
                                            )
                                            or 
                                            (
                                              (
                                                not (
                                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                               throw_7]
                                                )
                                              )
                                              and 
                                              TruePred[]
                                              and 
                                              (
                                                t_171_5=t_171_6)
                                            )
                                          )
                                          and 
                                          (
                                            (
                                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                           throw_7]
                                              and 
                                              (
                                                (
                                                  roops_core_objects_SinglyLinkedListCondition12[t_171_6]
                                                  and 
                                                  (
                                                    (
                                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                                   throw_7]
                                                      and 
                                                      (
                                                        var_84_equalVal_6=true)
                                                    )
                                                    or 
                                                    (
                                                      (
                                                        not (
                                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                                       throw_7]
                                                        )
                                                      )
                                                      and 
                                                      TruePred[]
                                                      and 
                                                      (
                                                        var_84_equalVal_5=var_84_equalVal_6)
                                                    )
                                                  )
                                                )
                                                or 
                                                (
                                                  (
                                                    not (
                                                      roops_core_objects_SinglyLinkedListCondition12[t_171_6])
                                                  )
                                                  and 
                                                  (
                                                    (
                                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                                   throw_7]
                                                      and 
                                                      (
                                                        var_84_equalVal_6=false)
                                                    )
                                                    or 
                                                    (
                                                      (
                                                        not (
                                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                                       throw_7]
                                                        )
                                                      )
                                                      and 
                                                      TruePred[]
                                                      and 
                                                      (
                                                        var_84_equalVal_5=var_84_equalVal_6)
                                                    )
                                                  )
                                                )
                                              )
                                            )
                                            or 
                                            (
                                              (
                                                not (
                                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                               throw_7]
                                                )
                                              )
                                              and 
                                              TruePred[]
                                              and 
                                              (
                                                var_84_equalVal_5=var_84_equalVal_6)
                                            )
                                          )
                                        )
                                        or 
                                        (
                                          (
                                            not (
                                              roops_core_objects_SinglyLinkedListCondition14[t_172_6])
                                          )
                                          and 
                                          (
                                            (
                                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                           throw_7]
                                              and 
                                              (
                                                var_84_equalVal_6=false)
                                            )
                                            or 
                                            (
                                              (
                                                not (
                                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                               throw_7]
                                                )
                                              )
                                              and 
                                              TruePred[]
                                              and 
                                              (
                                                var_84_equalVal_5=var_84_equalVal_6)
                                            )
                                          )
                                          and 
                                          (
                                            t_171_5=t_171_6)
                                        )
                                      )
                                    )
                                    or 
                                    (
                                      (
                                        not (
                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_7]
                                        )
                                      )
                                      and 
                                      TruePred[]
                                      and 
                                      (
                                        t_171_5=t_171_6)
                                      and 
                                      (
                                        var_84_equalVal_5=var_84_equalVal_6)
                                    )
                                  )
                                )
                              )
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_7]
                                )
                              )
                              and 
                              TruePred[]
                              and 
                              (
                                t_172_5=t_172_6)
                              and 
                              (
                                t_171_5=t_171_6)
                              and 
                              (
                                var_84_equalVal_5=var_84_equalVal_6)
                            )
                          )
                          and 
                          (
                            (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_7]
                              and 
                              (
                                t_176_6=(equ[var_84_equalVal_6,
                                   true]=>(true)else(false))
                              )
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_7]
                                )
                              )
                              and 
                              TruePred[]
                              and 
                              (
                                t_176_5=t_176_6)
                            )
                          )
                          and 
                          (
                            (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_7]
                              and 
                              (
                                (
                                  roops_core_objects_SinglyLinkedListCondition18[t_176_6]
                                  and 
                                  (
                                    (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_7]
                                      and 
                                      (
                                        var_82_result_7=true)
                                    )
                                    or 
                                    (
                                      (
                                        not (
                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_7]
                                        )
                                      )
                                      and 
                                      TruePred[]
                                      and 
                                      (
                                        var_82_result_6=var_82_result_7)
                                    )
                                  )
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objects_SinglyLinkedListCondition18[t_176_6])
                                  )
                                  and 
                                  TruePred[]
                                  and 
                                  (
                                    var_82_result_6=var_82_result_7)
                                )
                              )
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_7]
                                )
                              )
                              and 
                              TruePred[]
                              and 
                              (
                                var_82_result_6=var_82_result_7)
                            )
                          )
                          and 
                          (
                            (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_7]
                              and 
                              (
                                (
                                  roops_core_objects_SinglyLinkedListCondition20[var_81_current_6]
                                  and 
                                  (
                                    throw_8=java_lang_NullPointerExceptionLit)
                                  and 
                                  (
                                    var_81_current_6=var_81_current_7)
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objects_SinglyLinkedListCondition20[var_81_current_6])
                                  )
                                  and 
                                  (
                                    var_81_current_7=var_81_current_6.roops_core_objects_SinglyLinkedListNode_next_0)
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
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_7]
                                )
                              )
                              and 
                              TruePred[]
                              and 
                              (
                                var_81_current_6=var_81_current_7)
                              and 
                              (
                                throw_7=throw_8)
                            )
                          )
                          and 
                          (
                            (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_8]
                              and 
                              (
                                t_178_6=(equ[var_82_result_7,
                                   false]=>(true)else(false))
                              )
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_8]
                                )
                              )
                              and 
                              TruePred[]
                              and 
                              (
                                t_178_5=t_178_6)
                            )
                          )
                          and 
                          (
                            (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_8]
                              and 
                              (
                                t_179_6=(neq[var_81_current_7,
                                   null]=>(true)else(false))
                              )
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_8]
                                )
                              )
                              and 
                              TruePred[]
                              and 
                              (
                                t_179_5=t_179_6)
                            )
                          )
                          and 
                          (
                            (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_8]
                              and 
                              (
                                (
                                  roops_core_objects_SinglyLinkedListCondition24[t_178_6]
                                  and 
                                  (
                                    (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_8]
                                      and 
                                      (
                                        (
                                          roops_core_objects_SinglyLinkedListCondition22[t_179_6]
                                          and 
                                          (
                                            (
                                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                           throw_8]
                                              and 
                                              (
                                                t_177_6=true)
                                            )
                                            or 
                                            (
                                              (
                                                not (
                                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                               throw_8]
                                                )
                                              )
                                              and 
                                              TruePred[]
                                              and 
                                              (
                                                t_177_5=t_177_6)
                                            )
                                          )
                                        )
                                        or 
                                        (
                                          (
                                            not (
                                              roops_core_objects_SinglyLinkedListCondition22[t_179_6])
                                          )
                                          and 
                                          (
                                            (
                                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                           throw_8]
                                              and 
                                              (
                                                t_177_6=false)
                                            )
                                            or 
                                            (
                                              (
                                                not (
                                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                               throw_8]
                                                )
                                              )
                                              and 
                                              TruePred[]
                                              and 
                                              (
                                                t_177_5=t_177_6)
                                            )
                                          )
                                        )
                                      )
                                    )
                                    or 
                                    (
                                      (
                                        not (
                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_8]
                                        )
                                      )
                                      and 
                                      TruePred[]
                                      and 
                                      (
                                        t_177_5=t_177_6)
                                    )
                                  )
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objects_SinglyLinkedListCondition24[t_178_6])
                                  )
                                  and 
                                  (
                                    (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_8]
                                      and 
                                      (
                                        t_177_6=false)
                                    )
                                    or 
                                    (
                                      (
                                        not (
                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_8]
                                        )
                                      )
                                      and 
                                      TruePred[]
                                      and 
                                      (
                                        t_177_5=t_177_6)
                                    )
                                  )
                                )
                              )
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_8]
                                )
                              )
                              and 
                              TruePred[]
                              and 
                              (
                                t_177_5=t_177_6)
                            )
                          )
                          and 
                          (
                            (
                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                           throw_8]
                              and 
                              (
                                var_83_ws_25_7=t_177_6)
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_8]
                                )
                              )
                              and 
                              TruePred[]
                              and 
                              (
                                var_83_ws_25_6=var_83_ws_25_7)
                            )
                          )
                          and 
                          (
                            (
                              roops_core_objects_SinglyLinkedListCondition26[exit_stmt_reached_1,
                                                                            throw_8,
                                                                            var_83_ws_25_7]
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
                              TruePred[]
                              and 
                              (
                                (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_8]
                                  and 
                                  (
                                    t_174_7=(equ[value_param_0,
                                       null]=>(true)else(false))
                                  )
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_8]
                                    )
                                  )
                                  and 
                                  TruePred[]
                                  and 
                                  (
                                    t_174_6=t_174_7)
                                )
                              )
                              and 
                              (
                                (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_8]
                                  and 
                                  (
                                    t_175_7=(equ[var_81_current_7.roops_core_objects_SinglyLinkedListNode_value_0,
                                       null]=>(true)else(false))
                                  )
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_8]
                                    )
                                  )
                                  and 
                                  TruePred[]
                                  and 
                                  (
                                    t_175_6=t_175_7)
                                )
                              )
                              and 
                              (
                                (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_8]
                                  and 
                                  (
                                    (
                                      roops_core_objects_SinglyLinkedListCondition10[t_174_7]
                                      and 
                                      (
                                        (
                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_8]
                                          and 
                                          (
                                            (
                                              roops_core_objects_SinglyLinkedListCondition8[t_175_7]
                                              and 
                                              (
                                                (
                                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                               throw_8]
                                                  and 
                                                  (
                                                    t_173_7=true)
                                                )
                                                or 
                                                (
                                                  (
                                                    not (
                                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                                   throw_8]
                                                    )
                                                  )
                                                  and 
                                                  TruePred[]
                                                  and 
                                                  (
                                                    t_173_6=t_173_7)
                                                )
                                              )
                                            )
                                            or 
                                            (
                                              (
                                                not (
                                                  roops_core_objects_SinglyLinkedListCondition8[t_175_7])
                                              )
                                              and 
                                              (
                                                (
                                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                               throw_8]
                                                  and 
                                                  (
                                                    t_173_7=false)
                                                )
                                                or 
                                                (
                                                  (
                                                    not (
                                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                                   throw_8]
                                                    )
                                                  )
                                                  and 
                                                  TruePred[]
                                                  and 
                                                  (
                                                    t_173_6=t_173_7)
                                                )
                                              )
                                            )
                                          )
                                        )
                                        or 
                                        (
                                          (
                                            not (
                                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                           throw_8]
                                            )
                                          )
                                          and 
                                          TruePred[]
                                          and 
                                          (
                                            t_173_6=t_173_7)
                                        )
                                      )
                                    )
                                    or 
                                    (
                                      (
                                        not (
                                          roops_core_objects_SinglyLinkedListCondition10[t_174_7])
                                      )
                                      and 
                                      (
                                        (
                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_8]
                                          and 
                                          (
                                            t_173_7=false)
                                        )
                                        or 
                                        (
                                          (
                                            not (
                                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                           throw_8]
                                            )
                                          )
                                          and 
                                          TruePred[]
                                          and 
                                          (
                                            t_173_6=t_173_7)
                                        )
                                      )
                                    )
                                  )
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_8]
                                    )
                                  )
                                  and 
                                  TruePred[]
                                  and 
                                  (
                                    t_173_6=t_173_7)
                                )
                              )
                              and 
                              (
                                (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_8]
                                  and 
                                  (
                                    (
                                      roops_core_objects_SinglyLinkedListCondition16[t_173_7]
                                      and 
                                      (
                                        (
                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_8]
                                          and 
                                          (
                                            var_84_equalVal_7=true)
                                        )
                                        or 
                                        (
                                          (
                                            not (
                                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                           throw_8]
                                            )
                                          )
                                          and 
                                          TruePred[]
                                          and 
                                          (
                                            var_84_equalVal_6=var_84_equalVal_7)
                                        )
                                      )
                                      and 
                                      (
                                        t_172_6=t_172_7)
                                      and 
                                      (
                                        t_171_6=t_171_7)
                                    )
                                    or 
                                    (
                                      (
                                        not (
                                          roops_core_objects_SinglyLinkedListCondition16[t_173_7])
                                      )
                                      and 
                                      TruePred[]
                                      and 
                                      (
                                        (
                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_8]
                                          and 
                                          (
                                            t_172_7=(neq[value_param_0,
                                               null]=>(true)else(false))
                                          )
                                        )
                                        or 
                                        (
                                          (
                                            not (
                                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                           throw_8]
                                            )
                                          )
                                          and 
                                          TruePred[]
                                          and 
                                          (
                                            t_172_6=t_172_7)
                                        )
                                      )
                                      and 
                                      (
                                        (
                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_8]
                                          and 
                                          (
                                            (
                                              roops_core_objects_SinglyLinkedListCondition14[t_172_7]
                                              and 
                                              TruePred[]
                                              and 
                                              (
                                                (
                                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                               throw_8]
                                                  and 
                                                  (
                                                    t_171_7=(equ[value_param_0,
                                                       var_81_current_7.roops_core_objects_SinglyLinkedListNode_value_0]=>(true)else(false))
                                                  )
                                                )
                                                or 
                                                (
                                                  (
                                                    not (
                                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                                   throw_8]
                                                    )
                                                  )
                                                  and 
                                                  TruePred[]
                                                  and 
                                                  (
                                                    t_171_6=t_171_7)
                                                )
                                              )
                                              and 
                                              (
                                                (
                                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                               throw_8]
                                                  and 
                                                  (
                                                    (
                                                      roops_core_objects_SinglyLinkedListCondition12[t_171_7]
                                                      and 
                                                      (
                                                        (
                                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                                       throw_8]
                                                          and 
                                                          (
                                                            var_84_equalVal_7=true)
                                                        )
                                                        or 
                                                        (
                                                          (
                                                            not (
                                                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                                           throw_8]
                                                            )
                                                          )
                                                          and 
                                                          TruePred[]
                                                          and 
                                                          (
                                                            var_84_equalVal_6=var_84_equalVal_7)
                                                        )
                                                      )
                                                    )
                                                    or 
                                                    (
                                                      (
                                                        not (
                                                          roops_core_objects_SinglyLinkedListCondition12[t_171_7])
                                                      )
                                                      and 
                                                      (
                                                        (
                                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                                       throw_8]
                                                          and 
                                                          (
                                                            var_84_equalVal_7=false)
                                                        )
                                                        or 
                                                        (
                                                          (
                                                            not (
                                                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                                           throw_8]
                                                            )
                                                          )
                                                          and 
                                                          TruePred[]
                                                          and 
                                                          (
                                                            var_84_equalVal_6=var_84_equalVal_7)
                                                        )
                                                      )
                                                    )
                                                  )
                                                )
                                                or 
                                                (
                                                  (
                                                    not (
                                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                                   throw_8]
                                                    )
                                                  )
                                                  and 
                                                  TruePred[]
                                                  and 
                                                  (
                                                    var_84_equalVal_6=var_84_equalVal_7)
                                                )
                                              )
                                            )
                                            or 
                                            (
                                              (
                                                not (
                                                  roops_core_objects_SinglyLinkedListCondition14[t_172_7])
                                              )
                                              and 
                                              (
                                                (
                                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                               throw_8]
                                                  and 
                                                  (
                                                    var_84_equalVal_7=false)
                                                )
                                                or 
                                                (
                                                  (
                                                    not (
                                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                                   throw_8]
                                                    )
                                                  )
                                                  and 
                                                  TruePred[]
                                                  and 
                                                  (
                                                    var_84_equalVal_6=var_84_equalVal_7)
                                                )
                                              )
                                              and 
                                              (
                                                t_171_6=t_171_7)
                                            )
                                          )
                                        )
                                        or 
                                        (
                                          (
                                            not (
                                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                           throw_8]
                                            )
                                          )
                                          and 
                                          TruePred[]
                                          and 
                                          (
                                            t_171_6=t_171_7)
                                          and 
                                          (
                                            var_84_equalVal_6=var_84_equalVal_7)
                                        )
                                      )
                                    )
                                  )
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_8]
                                    )
                                  )
                                  and 
                                  TruePred[]
                                  and 
                                  (
                                    t_172_6=t_172_7)
                                  and 
                                  (
                                    t_171_6=t_171_7)
                                  and 
                                  (
                                    var_84_equalVal_6=var_84_equalVal_7)
                                )
                              )
                              and 
                              (
                                (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_8]
                                  and 
                                  (
                                    t_176_7=(equ[var_84_equalVal_7,
                                       true]=>(true)else(false))
                                  )
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_8]
                                    )
                                  )
                                  and 
                                  TruePred[]
                                  and 
                                  (
                                    t_176_6=t_176_7)
                                )
                              )
                              and 
                              (
                                (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_8]
                                  and 
                                  (
                                    (
                                      roops_core_objects_SinglyLinkedListCondition18[t_176_7]
                                      and 
                                      (
                                        (
                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_8]
                                          and 
                                          (
                                            var_82_result_8=true)
                                        )
                                        or 
                                        (
                                          (
                                            not (
                                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                           throw_8]
                                            )
                                          )
                                          and 
                                          TruePred[]
                                          and 
                                          (
                                            var_82_result_7=var_82_result_8)
                                        )
                                      )
                                    )
                                    or 
                                    (
                                      (
                                        not (
                                          roops_core_objects_SinglyLinkedListCondition18[t_176_7])
                                      )
                                      and 
                                      TruePred[]
                                      and 
                                      (
                                        var_82_result_7=var_82_result_8)
                                    )
                                  )
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_8]
                                    )
                                  )
                                  and 
                                  TruePred[]
                                  and 
                                  (
                                    var_82_result_7=var_82_result_8)
                                )
                              )
                              and 
                              (
                                (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_8]
                                  and 
                                  (
                                    (
                                      roops_core_objects_SinglyLinkedListCondition20[var_81_current_7]
                                      and 
                                      (
                                        throw_9=java_lang_NullPointerExceptionLit)
                                      and 
                                      (
                                        var_81_current_7=var_81_current_8)
                                    )
                                    or 
                                    (
                                      (
                                        not (
                                          roops_core_objects_SinglyLinkedListCondition20[var_81_current_7])
                                      )
                                      and 
                                      (
                                        var_81_current_8=var_81_current_7.roops_core_objects_SinglyLinkedListNode_next_0)
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
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_8]
                                    )
                                  )
                                  and 
                                  TruePred[]
                                  and 
                                  (
                                    var_81_current_7=var_81_current_8)
                                  and 
                                  (
                                    throw_8=throw_9)
                                )
                              )
                              and 
                              (
                                (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_9]
                                  and 
                                  (
                                    t_178_7=(equ[var_82_result_8,
                                       false]=>(true)else(false))
                                  )
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_9]
                                    )
                                  )
                                  and 
                                  TruePred[]
                                  and 
                                  (
                                    t_178_6=t_178_7)
                                )
                              )
                              and 
                              (
                                (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_9]
                                  and 
                                  (
                                    t_179_7=(neq[var_81_current_8,
                                       null]=>(true)else(false))
                                  )
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_9]
                                    )
                                  )
                                  and 
                                  TruePred[]
                                  and 
                                  (
                                    t_179_6=t_179_7)
                                )
                              )
                              and 
                              (
                                (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_9]
                                  and 
                                  (
                                    (
                                      roops_core_objects_SinglyLinkedListCondition24[t_178_7]
                                      and 
                                      (
                                        (
                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_9]
                                          and 
                                          (
                                            (
                                              roops_core_objects_SinglyLinkedListCondition22[t_179_7]
                                              and 
                                              (
                                                (
                                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                               throw_9]
                                                  and 
                                                  (
                                                    t_177_7=true)
                                                )
                                                or 
                                                (
                                                  (
                                                    not (
                                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                                   throw_9]
                                                    )
                                                  )
                                                  and 
                                                  TruePred[]
                                                  and 
                                                  (
                                                    t_177_6=t_177_7)
                                                )
                                              )
                                            )
                                            or 
                                            (
                                              (
                                                not (
                                                  roops_core_objects_SinglyLinkedListCondition22[t_179_7])
                                              )
                                              and 
                                              (
                                                (
                                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                               throw_9]
                                                  and 
                                                  (
                                                    t_177_7=false)
                                                )
                                                or 
                                                (
                                                  (
                                                    not (
                                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                                   throw_9]
                                                    )
                                                  )
                                                  and 
                                                  TruePred[]
                                                  and 
                                                  (
                                                    t_177_6=t_177_7)
                                                )
                                              )
                                            )
                                          )
                                        )
                                        or 
                                        (
                                          (
                                            not (
                                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                           throw_9]
                                            )
                                          )
                                          and 
                                          TruePred[]
                                          and 
                                          (
                                            t_177_6=t_177_7)
                                        )
                                      )
                                    )
                                    or 
                                    (
                                      (
                                        not (
                                          roops_core_objects_SinglyLinkedListCondition24[t_178_7])
                                      )
                                      and 
                                      (
                                        (
                                          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                       throw_9]
                                          and 
                                          (
                                            t_177_7=false)
                                        )
                                        or 
                                        (
                                          (
                                            not (
                                              roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                           throw_9]
                                            )
                                          )
                                          and 
                                          TruePred[]
                                          and 
                                          (
                                            t_177_6=t_177_7)
                                        )
                                      )
                                    )
                                  )
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_9]
                                    )
                                  )
                                  and 
                                  TruePred[]
                                  and 
                                  (
                                    t_177_6=t_177_7)
                                )
                              )
                              and 
                              (
                                (
                                  roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                               throw_9]
                                  and 
                                  (
                                    var_83_ws_25_8=t_177_7)
                                )
                                or 
                                (
                                  (
                                    not (
                                      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                                                   throw_9]
                                    )
                                  )
                                  and 
                                  TruePred[]
                                  and 
                                  (
                                    var_83_ws_25_7=var_83_ws_25_8)
                                )
                              )
                              and 
                              TruePred[]
                            )
                            or 
                            (
                              (
                                not (
                                  roops_core_objects_SinglyLinkedListCondition26[exit_stmt_reached_1,
                                                                                throw_8,
                                                                                var_83_ws_25_7]
                                )
                              )
                              and 
                              TruePred[]
                              and 
                              (
                                var_81_current_7=var_81_current_8)
                              and 
                              (
                                var_84_equalVal_6=var_84_equalVal_7)
                              and 
                              (
                                t_179_6=t_179_7)
                              and 
                              (
                                var_82_result_7=var_82_result_8)
                              and 
                              (
                                t_172_6=t_172_7)
                              and 
                              (
                                t_171_6=t_171_7)
                              and 
                              (
                                t_174_6=t_174_7)
                              and 
                              (
                                t_173_6=t_173_7)
                              and 
                              (
                                t_176_6=t_176_7)
                              and 
                              (
                                t_175_6=t_175_7)
                              and 
                              (
                                t_178_6=t_178_7)
                              and 
                              (
                                t_177_6=t_177_7)
                              and 
                              (
                                throw_8=throw_9)
                              and 
                              (
                                var_83_ws_25_7=var_83_ws_25_8)
                            )
                          )
                        )
                        or 
                        (
                          (
                            not (
                              roops_core_objects_SinglyLinkedListCondition26[exit_stmt_reached_1,
                                                                            throw_7,
                                                                            var_83_ws_25_6]
                            )
                          )
                          and 
                          TruePred[]
                          and 
                          (
                            var_81_current_6=var_81_current_8)
                          and 
                          (
                            var_84_equalVal_5=var_84_equalVal_7)
                          and 
                          (
                            t_179_5=t_179_7)
                          and 
                          (
                            var_82_result_6=var_82_result_8)
                          and 
                          (
                            t_172_5=t_172_7)
                          and 
                          (
                            t_171_5=t_171_7)
                          and 
                          (
                            t_174_5=t_174_7)
                          and 
                          (
                            t_173_5=t_173_7)
                          and 
                          (
                            t_176_5=t_176_7)
                          and 
                          (
                            t_175_5=t_175_7)
                          and 
                          (
                            t_178_5=t_178_7)
                          and 
                          (
                            t_177_5=t_177_7)
                          and 
                          (
                            throw_7=throw_9)
                          and 
                          (
                            var_83_ws_25_6=var_83_ws_25_8)
                        )
                      )
                    )
                    or 
                    (
                      (
                        not (
                          roops_core_objects_SinglyLinkedListCondition26[exit_stmt_reached_1,
                                                                        throw_6,
                                                                        var_83_ws_25_5]
                        )
                      )
                      and 
                      TruePred[]
                      and 
                      (
                        var_81_current_5=var_81_current_8)
                      and 
                      (
                        var_84_equalVal_4=var_84_equalVal_7)
                      and 
                      (
                        t_179_4=t_179_7)
                      and 
                      (
                        var_82_result_5=var_82_result_8)
                      and 
                      (
                        t_172_4=t_172_7)
                      and 
                      (
                        t_171_4=t_171_7)
                      and 
                      (
                        t_174_4=t_174_7)
                      and 
                      (
                        t_173_4=t_173_7)
                      and 
                      (
                        t_176_4=t_176_7)
                      and 
                      (
                        t_175_4=t_175_7)
                      and 
                      (
                        t_178_4=t_178_7)
                      and 
                      (
                        t_177_4=t_177_7)
                      and 
                      (
                        throw_6=throw_9)
                      and 
                      (
                        var_83_ws_25_5=var_83_ws_25_8)
                    )
                  )
                )
                or 
                (
                  (
                    not (
                      roops_core_objects_SinglyLinkedListCondition26[exit_stmt_reached_1,
                                                                    throw_5,
                                                                    var_83_ws_25_4]
                    )
                  )
                  and 
                  TruePred[]
                  and 
                  (
                    var_81_current_4=var_81_current_8)
                  and 
                  (
                    var_84_equalVal_3=var_84_equalVal_7)
                  and 
                  (
                    t_179_3=t_179_7)
                  and 
                  (
                    var_82_result_4=var_82_result_8)
                  and 
                  (
                    t_172_3=t_172_7)
                  and 
                  (
                    t_171_3=t_171_7)
                  and 
                  (
                    t_174_3=t_174_7)
                  and 
                  (
                    t_173_3=t_173_7)
                  and 
                  (
                    t_176_3=t_176_7)
                  and 
                  (
                    t_175_3=t_175_7)
                  and 
                  (
                    t_178_3=t_178_7)
                  and 
                  (
                    t_177_3=t_177_7)
                  and 
                  (
                    throw_5=throw_9)
                  and 
                  (
                    var_83_ws_25_4=var_83_ws_25_8)
                )
              )
            )
            or 
            (
              (
                not (
                  roops_core_objects_SinglyLinkedListCondition26[exit_stmt_reached_1,
                                                                throw_4,
                                                                var_83_ws_25_3]
                )
              )
              and 
              TruePred[]
              and 
              (
                var_81_current_3=var_81_current_8)
              and 
              (
                var_84_equalVal_2=var_84_equalVal_7)
              and 
              (
                t_179_2=t_179_7)
              and 
              (
                var_82_result_3=var_82_result_8)
              and 
              (
                t_172_2=t_172_7)
              and 
              (
                t_171_2=t_171_7)
              and 
              (
                t_174_2=t_174_7)
              and 
              (
                t_173_2=t_173_7)
              and 
              (
                t_176_2=t_176_7)
              and 
              (
                t_175_2=t_175_7)
              and 
              (
                t_178_2=t_178_7)
              and 
              (
                t_177_2=t_177_7)
              and 
              (
                throw_4=throw_9)
              and 
              (
                var_83_ws_25_3=var_83_ws_25_8)
            )
          )
        )
        or 
        (
          (
            not (
              roops_core_objects_SinglyLinkedListCondition26[exit_stmt_reached_1,
                                                            throw_3,
                                                            var_83_ws_25_2]
            )
          )
          and 
          TruePred[]
          and 
          (
            var_81_current_2=var_81_current_8)
          and 
          (
            var_84_equalVal_1=var_84_equalVal_7)
          and 
          (
            t_179_1=t_179_7)
          and 
          (
            var_82_result_2=var_82_result_8)
          and 
          (
            t_172_1=t_172_7)
          and 
          (
            t_171_1=t_171_7)
          and 
          (
            t_174_1=t_174_7)
          and 
          (
            t_173_1=t_173_7)
          and 
          (
            t_176_1=t_176_7)
          and 
          (
            t_175_1=t_175_7)
          and 
          (
            t_178_1=t_178_7)
          and 
          (
            t_177_1=t_177_7)
          and 
          (
            throw_3=throw_9)
          and 
          (
            var_83_ws_25_2=var_83_ws_25_8)
        )
      )
    )
    or 
    (
      (
        not (
          roops_core_objects_SinglyLinkedListCondition26[exit_stmt_reached_1,
                                                        throw_2,
                                                        var_83_ws_25_1]
        )
      )
      and 
      TruePred[]
      and 
      (
        var_81_current_1=var_81_current_8)
      and 
      (
        var_84_equalVal_0=var_84_equalVal_7)
      and 
      (
        t_179_0=t_179_7)
      and 
      (
        var_82_result_1=var_82_result_8)
      and 
      (
        t_172_0=t_172_7)
      and 
      (
        t_171_0=t_171_7)
      and 
      (
        t_174_0=t_174_7)
      and 
      (
        t_173_0=t_173_7)
      and 
      (
        t_176_0=t_176_7)
      and 
      (
        t_175_0=t_175_7)
      and 
      (
        t_178_0=t_178_7)
      and 
      (
        t_177_0=t_177_7)
      and 
      (
        throw_2=throw_9)
      and 
      (
        var_83_ws_25_1=var_83_ws_25_8)
    )
  )
  and 
  (
    not (
      roops_core_objects_SinglyLinkedListCondition26[exit_stmt_reached_1,
                                                    throw_9,
                                                    var_83_ws_25_8]
    )
  )
  and 
  (
    (
      roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                   throw_9]
      and 
      (
        return_1=var_82_result_8)
      and 
      (
        exit_stmt_reached_2=true)
    )
    or 
    (
      (
        not (
          roops_core_objects_SinglyLinkedListCondition2[exit_stmt_reached_1,
                                                       throw_9]
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

//-------------SMB sigs-------------// 
one sig roops_core_objects_SinglyLinkedList_0 extends roops_core_objects_SinglyLinkedList {} 

one sig roops_core_objects_SinglyLinkedListNode_0, roops_core_objects_SinglyLinkedListNode_1, roops_core_objects_SinglyLinkedListNode_2, roops_core_objects_SinglyLinkedListNode_3, roops_core_objects_SinglyLinkedListNode_4, roops_core_objects_SinglyLinkedListNode_5, roops_core_objects_SinglyLinkedListNode_6 extends roops_core_objects_SinglyLinkedListNode {} 

one sig java_lang_Object_0 extends java_lang_Object {} 

fact {
  no ( QF.froops_core_objects_SinglyLinkedListNode_next_0.univ & QF.broops_core_objects_SinglyLinkedListNode_next_0.univ ) and 
  roops_core_objects_SinglyLinkedListNode = QF.froops_core_objects_SinglyLinkedListNode_next_0.univ + QF.broops_core_objects_SinglyLinkedListNode_next_0.univ 
}
//-----SMB: local_ordering()-----//
fun next_roops_core_objects_SinglyLinkedList [] : roops_core_objects_SinglyLinkedList -> lone roops_core_objects_SinglyLinkedList { 
none -> none 
}
fun min_roops_core_objects_SinglyLinkedList [os: set roops_core_objects_SinglyLinkedList] : lone roops_core_objects_SinglyLinkedList {
  os - os.^(next_roops_core_objects_SinglyLinkedList[]) 
}
fun prevs_roops_core_objects_SinglyLinkedList[o : roops_core_objects_SinglyLinkedList] : set roops_core_objects_SinglyLinkedList {
  o.^(~next_roops_core_objects_SinglyLinkedList[]) 
}
fun next_roops_core_objects_SinglyLinkedListNode [] : roops_core_objects_SinglyLinkedListNode -> lone roops_core_objects_SinglyLinkedListNode { 
  roops_core_objects_SinglyLinkedListNode_0 -> roops_core_objects_SinglyLinkedListNode_1 +
  roops_core_objects_SinglyLinkedListNode_1 -> roops_core_objects_SinglyLinkedListNode_2 +
  roops_core_objects_SinglyLinkedListNode_2 -> roops_core_objects_SinglyLinkedListNode_3 +
  roops_core_objects_SinglyLinkedListNode_3 -> roops_core_objects_SinglyLinkedListNode_4 +
  roops_core_objects_SinglyLinkedListNode_4 -> roops_core_objects_SinglyLinkedListNode_5 +
  roops_core_objects_SinglyLinkedListNode_5 -> roops_core_objects_SinglyLinkedListNode_6 
}
fun min_roops_core_objects_SinglyLinkedListNode [os: set roops_core_objects_SinglyLinkedListNode] : lone roops_core_objects_SinglyLinkedListNode {
  os - os.^(next_roops_core_objects_SinglyLinkedListNode[]) 
}
fun prevs_roops_core_objects_SinglyLinkedListNode[o : roops_core_objects_SinglyLinkedListNode] : set roops_core_objects_SinglyLinkedListNode {
  o.^(~next_roops_core_objects_SinglyLinkedListNode[]) 
}
fun next_java_lang_Object [] : java_lang_Object -> lone java_lang_Object { 
none -> none 
}
fun min_java_lang_Object [os: set java_lang_Object] : lone java_lang_Object {
  os - os.^(next_java_lang_Object[]) 
}
fun prevs_java_lang_Object[o : java_lang_Object] : set java_lang_Object {
  o.^(~next_java_lang_Object[]) 
}
//-----SMB: global_ordering()-----//
fun globalNext[]: java_lang_Object -> lone java_lang_Object {
roops_core_objects_SinglyLinkedList_0 -> roops_core_objects_SinglyLinkedListNode_0  +  roops_core_objects_SinglyLinkedListNode_0 -> roops_core_objects_SinglyLinkedListNode_1  +  roops_core_objects_SinglyLinkedListNode_1 -> roops_core_objects_SinglyLinkedListNode_2  +  roops_core_objects_SinglyLinkedListNode_2 -> roops_core_objects_SinglyLinkedListNode_3  +  roops_core_objects_SinglyLinkedListNode_3 -> roops_core_objects_SinglyLinkedListNode_4  +  roops_core_objects_SinglyLinkedListNode_4 -> roops_core_objects_SinglyLinkedListNode_5  +  roops_core_objects_SinglyLinkedListNode_5 -> roops_core_objects_SinglyLinkedListNode_6  +  roops_core_objects_SinglyLinkedListNode_6 -> java_lang_Object_0
}
fun globalMin[s : set java_lang_Object] : lone java_lang_Object {
s - s.^globalNext[] 
}
//-----SMB: define_min_parent()-----//
fun minP_roops_core_objects_SinglyLinkedListNode [o : roops_core_objects_SinglyLinkedListNode] : java_lang_Object {
  globalMin[(QF.roops_core_objects_SinglyLinkedList_header_0 + QF.froops_core_objects_SinglyLinkedListNode_next_0).o]
}
fun minP_java_lang_Object [o : java_lang_Object] : java_lang_Object {
  globalMin[(QF.roops_core_objects_SinglyLinkedListNode_value_0).o]
}
//-----SMB: define_freach()-----//
fun FReach[] : set java_lang_Object {
(QF.thiz_0 + QF.value_param_0).*(QF.roops_core_objects_SinglyLinkedList_header_0 + QF.roops_core_objects_SinglyLinkedListNode_value_0 + QF.froops_core_objects_SinglyLinkedListNode_next_0) - null
}
//-----SMB: order_root_nodes()-----//
//-----SMB: root_is_minimum()-----//
fact {
((QF.thiz_0 != null) implies QF.thiz_0 = roops_core_objects_SinglyLinkedList_0 )
}
//-----SMB: order_same_min_parent()-----//
//-----SMB: order_same_min_parent_type()-----//
fact {
 all disj o1, o2:roops_core_objects_SinglyLinkedListNode |
  let p1=minP_roops_core_objects_SinglyLinkedListNode[o1]|
  let p2=minP_roops_core_objects_SinglyLinkedListNode[o2]|
  (o1 + o2 in FReach[] and
  some p1 and some p2 and
  p1!=p2 and p1+p2 in roops_core_objects_SinglyLinkedListNode and p1 in prevs_roops_core_objects_SinglyLinkedListNode[p2] )
  implies o1 in prevs_roops_core_objects_SinglyLinkedListNode[o2]
}
fact {
 all disj o1, o2:roops_core_objects_SinglyLinkedListNode |
  let p1=minP_roops_core_objects_SinglyLinkedListNode[o1]|
  let p2=minP_roops_core_objects_SinglyLinkedListNode[o2]|
  (o1 + o2 in FReach[] and
  some p1 and some p2 and
  p1!=p2 and p1+p2 in java_lang_Object and p1 in prevs_java_lang_Object[p2] )
  implies o1 in prevs_roops_core_objects_SinglyLinkedListNode[o2]
}
//-----SMB: order_diff_min_parent_type()-----//
fact {
 all disj o1, o2:roops_core_objects_SinglyLinkedListNode |
  let p1=minP_roops_core_objects_SinglyLinkedListNode[o1]|
  let p2=minP_roops_core_objects_SinglyLinkedListNode[o2]|
  ( o1+o2 in FReach[] and
 some p1 and some p2 and
p1 in roops_core_objects_SinglyLinkedList and p2 in roops_core_objects_SinglyLinkedListNode )
implies o1 in prevs_roops_core_objects_SinglyLinkedListNode[o2]
}
//-----SMB: avoid_holes()-----//
fact {
 all o : roops_core_objects_SinglyLinkedList | 
  o in FReach[] implies
   prevs_roops_core_objects_SinglyLinkedList[o] in FReach[]
}
fact {
 all o : roops_core_objects_SinglyLinkedListNode | 
  o in FReach[] implies
   prevs_roops_core_objects_SinglyLinkedListNode[o] in FReach[]
}
fact {
 all o : java_lang_Object | 
  o in FReach[] implies
   prevs_java_lang_Object[o] in FReach[]
}
/*
type ordering:
==============
1) roops_core_objects_SinglyLinkedList
2) roops_core_objects_SinglyLinkedListNode
3) java_lang_Object

root nodes ordering:
====================
1) thiz:roops_core_objects_SinglyLinkedList
2) value_param:java_lang_Object+null

recursive field ordering:
=========================
1) roops_core_objects_SinglyLinkedListNode_next:(roops_core_objects_SinglyLinkedListNode)->one(null+roops_core_objects_SinglyLinkedListNode)

non-recursive field ordering:
=============================
1) roops_core_objects_SinglyLinkedList_header:(roops_core_objects_SinglyLinkedList)->one(null+roops_core_objects_SinglyLinkedListNode)
2) roops_core_objects_SinglyLinkedListNode_value:(roops_core_objects_SinglyLinkedListNode)->one(java_lang_Object+null)
*/
//-----UPPER BOUND -----//
fact {
  (QF).(broops_core_objects_SinglyLinkedListNode_next_0) in 
none->none
}
//-----UPPER BOUND -----//
fact {
  (QF).(froops_core_objects_SinglyLinkedListNode_next_0) in 
roops_core_objects_SinglyLinkedListNode_0->null
+roops_core_objects_SinglyLinkedListNode_0->roops_core_objects_SinglyLinkedListNode_1
+roops_core_objects_SinglyLinkedListNode_1->null
+roops_core_objects_SinglyLinkedListNode_1->roops_core_objects_SinglyLinkedListNode_2
+roops_core_objects_SinglyLinkedListNode_2->null
+roops_core_objects_SinglyLinkedListNode_2->roops_core_objects_SinglyLinkedListNode_3
+roops_core_objects_SinglyLinkedListNode_3->null
+roops_core_objects_SinglyLinkedListNode_3->roops_core_objects_SinglyLinkedListNode_4
+roops_core_objects_SinglyLinkedListNode_4->null
+roops_core_objects_SinglyLinkedListNode_4->roops_core_objects_SinglyLinkedListNode_5
+roops_core_objects_SinglyLinkedListNode_5->null
+roops_core_objects_SinglyLinkedListNode_5->roops_core_objects_SinglyLinkedListNode_6
+roops_core_objects_SinglyLinkedListNode_6->null
}
one sig QF {
  broops_core_objects_SinglyLinkedListNode_next_0:(roops_core_objects_SinglyLinkedListNode) -> lone((roops_core_objects_SinglyLinkedListNode)),
  froops_core_objects_SinglyLinkedListNode_next_0:(roops_core_objects_SinglyLinkedListNode) -> lone((roops_core_objects_SinglyLinkedListNode + null)),
  l0_exit_stmt_reached_1: boolean,
  l0_exit_stmt_reached_2: boolean,
  l0_t_168_0: boolean,
  l0_t_168_1: boolean,
  l0_t_169_0: boolean,
  l0_t_169_1: boolean,
  l0_t_170_0: boolean,
  l0_t_170_1: boolean,
  l0_t_171_0: boolean,
  l0_t_171_1: boolean,
  l0_t_171_2: boolean,
  l0_t_171_3: boolean,
  l0_t_171_4: boolean,
  l0_t_171_5: boolean,
  l0_t_171_6: boolean,
  l0_t_171_7: boolean,
  l0_t_172_0: boolean,
  l0_t_172_1: boolean,
  l0_t_172_2: boolean,
  l0_t_172_3: boolean,
  l0_t_172_4: boolean,
  l0_t_172_5: boolean,
  l0_t_172_6: boolean,
  l0_t_172_7: boolean,
  l0_t_173_0: boolean,
  l0_t_173_1: boolean,
  l0_t_173_2: boolean,
  l0_t_173_3: boolean,
  l0_t_173_4: boolean,
  l0_t_173_5: boolean,
  l0_t_173_6: boolean,
  l0_t_173_7: boolean,
  l0_t_174_0: boolean,
  l0_t_174_1: boolean,
  l0_t_174_2: boolean,
  l0_t_174_3: boolean,
  l0_t_174_4: boolean,
  l0_t_174_5: boolean,
  l0_t_174_6: boolean,
  l0_t_174_7: boolean,
  l0_t_175_0: boolean,
  l0_t_175_1: boolean,
  l0_t_175_2: boolean,
  l0_t_175_3: boolean,
  l0_t_175_4: boolean,
  l0_t_175_5: boolean,
  l0_t_175_6: boolean,
  l0_t_175_7: boolean,
  l0_t_176_0: boolean,
  l0_t_176_1: boolean,
  l0_t_176_2: boolean,
  l0_t_176_3: boolean,
  l0_t_176_4: boolean,
  l0_t_176_5: boolean,
  l0_t_176_6: boolean,
  l0_t_176_7: boolean,
  l0_t_177_0: boolean,
  l0_t_177_1: boolean,
  l0_t_177_2: boolean,
  l0_t_177_3: boolean,
  l0_t_177_4: boolean,
  l0_t_177_5: boolean,
  l0_t_177_6: boolean,
  l0_t_177_7: boolean,
  l0_t_178_0: boolean,
  l0_t_178_1: boolean,
  l0_t_178_2: boolean,
  l0_t_178_3: boolean,
  l0_t_178_4: boolean,
  l0_t_178_5: boolean,
  l0_t_178_6: boolean,
  l0_t_178_7: boolean,
  l0_t_179_0: boolean,
  l0_t_179_1: boolean,
  l0_t_179_2: boolean,
  l0_t_179_3: boolean,
  l0_t_179_4: boolean,
  l0_t_179_5: boolean,
  l0_t_179_6: boolean,
  l0_t_179_7: boolean,
  l0_var_81_current_0: null + roops_core_objects_SinglyLinkedListNode,
  l0_var_81_current_1: null + roops_core_objects_SinglyLinkedListNode,
  l0_var_81_current_2: null + roops_core_objects_SinglyLinkedListNode,
  l0_var_81_current_3: null + roops_core_objects_SinglyLinkedListNode,
  l0_var_81_current_4: null + roops_core_objects_SinglyLinkedListNode,
  l0_var_81_current_5: null + roops_core_objects_SinglyLinkedListNode,
  l0_var_81_current_6: null + roops_core_objects_SinglyLinkedListNode,
  l0_var_81_current_7: null + roops_core_objects_SinglyLinkedListNode,
  l0_var_81_current_8: null + roops_core_objects_SinglyLinkedListNode,
  l0_var_82_result_0: boolean,
  l0_var_82_result_1: boolean,
  l0_var_82_result_2: boolean,
  l0_var_82_result_3: boolean,
  l0_var_82_result_4: boolean,
  l0_var_82_result_5: boolean,
  l0_var_82_result_6: boolean,
  l0_var_82_result_7: boolean,
  l0_var_82_result_8: boolean,
  l0_var_83_ws_25_0: boolean,
  l0_var_83_ws_25_1: boolean,
  l0_var_83_ws_25_2: boolean,
  l0_var_83_ws_25_3: boolean,
  l0_var_83_ws_25_4: boolean,
  l0_var_83_ws_25_5: boolean,
  l0_var_83_ws_25_6: boolean,
  l0_var_83_ws_25_7: boolean,
  l0_var_83_ws_25_8: boolean,
  l0_var_84_equalVal_0: boolean,
  l0_var_84_equalVal_1: boolean,
  l0_var_84_equalVal_2: boolean,
  l0_var_84_equalVal_3: boolean,
  l0_var_84_equalVal_4: boolean,
  l0_var_84_equalVal_5: boolean,
  l0_var_84_equalVal_6: boolean,
  l0_var_84_equalVal_7: boolean,
  return_0: boolean,
  return_1: boolean,
  roops_core_objects_SinglyLinkedListNode_value_0: ( roops_core_objects_SinglyLinkedListNode ) -> one ( java_lang_Object + null ),
  roops_core_objects_SinglyLinkedList_header_0: ( roops_core_objects_SinglyLinkedList ) -> one ( null + roops_core_objects_SinglyLinkedListNode ),
  thiz_0: roops_core_objects_SinglyLinkedList,
  throw_0: java_lang_Throwable + null,
  throw_1: java_lang_Throwable + null,
  throw_2: java_lang_Throwable + null,
  throw_3: java_lang_Throwable + null,
  throw_4: java_lang_Throwable + null,
  throw_5: java_lang_Throwable + null,
  throw_6: java_lang_Throwable + null,
  throw_7: java_lang_Throwable + null,
  throw_8: java_lang_Throwable + null,
  throw_9: java_lang_Throwable + null,
  value_param_0: java_lang_Object + null
}


fact {
  precondition_roops_core_objects_SinglyLinkedList_contains_0[(QF.broops_core_objects_SinglyLinkedListNode_next_0)+(QF.froops_core_objects_SinglyLinkedListNode_next_0),
                                                             QF.roops_core_objects_SinglyLinkedList_header_0,
                                                             QF.thiz_0,
                                                             QF.throw_0]

}

fact {
  roops_core_objects_SinglyLinkedList_contains_0[QF.thiz_0,
                                                QF.throw_1,
                                                QF.throw_2,
                                                QF.throw_3,
                                                QF.throw_4,
                                                QF.throw_5,
                                                QF.throw_6,
                                                QF.throw_7,
                                                QF.throw_8,
                                                QF.throw_9,
                                                QF.return_0,
                                                QF.return_1,
                                                QF.value_param_0,
                                                (QF.broops_core_objects_SinglyLinkedListNode_next_0)+(QF.froops_core_objects_SinglyLinkedListNode_next_0),
                                                QF.roops_core_objects_SinglyLinkedListNode_value_0,
                                                QF.roops_core_objects_SinglyLinkedList_header_0,
                                                QF.l0_var_84_equalVal_0,
                                                QF.l0_var_84_equalVal_1,
                                                QF.l0_var_84_equalVal_2,
                                                QF.l0_var_84_equalVal_3,
                                                QF.l0_var_84_equalVal_4,
                                                QF.l0_var_84_equalVal_5,
                                                QF.l0_var_84_equalVal_6,
                                                QF.l0_var_84_equalVal_7,
                                                QF.l0_var_81_current_0,
                                                QF.l0_var_81_current_1,
                                                QF.l0_var_81_current_2,
                                                QF.l0_var_81_current_3,
                                                QF.l0_var_81_current_4,
                                                QF.l0_var_81_current_5,
                                                QF.l0_var_81_current_6,
                                                QF.l0_var_81_current_7,
                                                QF.l0_var_81_current_8,
                                                QF.l0_t_171_0,
                                                QF.l0_t_171_1,
                                                QF.l0_t_171_2,
                                                QF.l0_t_171_3,
                                                QF.l0_t_171_4,
                                                QF.l0_t_171_5,
                                                QF.l0_t_171_6,
                                                QF.l0_t_171_7,
                                                QF.l0_var_83_ws_25_0,
                                                QF.l0_var_83_ws_25_1,
                                                QF.l0_var_83_ws_25_2,
                                                QF.l0_var_83_ws_25_3,
                                                QF.l0_var_83_ws_25_4,
                                                QF.l0_var_83_ws_25_5,
                                                QF.l0_var_83_ws_25_6,
                                                QF.l0_var_83_ws_25_7,
                                                QF.l0_var_83_ws_25_8,
                                                QF.l0_t_170_0,
                                                QF.l0_t_170_1,
                                                QF.l0_t_168_0,
                                                QF.l0_t_168_1,
                                                QF.l0_exit_stmt_reached_1,
                                                QF.l0_exit_stmt_reached_2,
                                                QF.l0_var_82_result_0,
                                                QF.l0_var_82_result_1,
                                                QF.l0_var_82_result_2,
                                                QF.l0_var_82_result_3,
                                                QF.l0_var_82_result_4,
                                                QF.l0_var_82_result_5,
                                                QF.l0_var_82_result_6,
                                                QF.l0_var_82_result_7,
                                                QF.l0_var_82_result_8,
                                                QF.l0_t_173_0,
                                                QF.l0_t_173_1,
                                                QF.l0_t_173_2,
                                                QF.l0_t_173_3,
                                                QF.l0_t_173_4,
                                                QF.l0_t_173_5,
                                                QF.l0_t_173_6,
                                                QF.l0_t_173_7,
                                                QF.l0_t_172_0,
                                                QF.l0_t_172_1,
                                                QF.l0_t_172_2,
                                                QF.l0_t_172_3,
                                                QF.l0_t_172_4,
                                                QF.l0_t_172_5,
                                                QF.l0_t_172_6,
                                                QF.l0_t_172_7,
                                                QF.l0_t_175_0,
                                                QF.l0_t_175_1,
                                                QF.l0_t_175_2,
                                                QF.l0_t_175_3,
                                                QF.l0_t_175_4,
                                                QF.l0_t_175_5,
                                                QF.l0_t_175_6,
                                                QF.l0_t_175_7,
                                                QF.l0_t_174_0,
                                                QF.l0_t_174_1,
                                                QF.l0_t_174_2,
                                                QF.l0_t_174_3,
                                                QF.l0_t_174_4,
                                                QF.l0_t_174_5,
                                                QF.l0_t_174_6,
                                                QF.l0_t_174_7,
                                                QF.l0_t_177_0,
                                                QF.l0_t_177_1,
                                                QF.l0_t_177_2,
                                                QF.l0_t_177_3,
                                                QF.l0_t_177_4,
                                                QF.l0_t_177_5,
                                                QF.l0_t_177_6,
                                                QF.l0_t_177_7,
                                                QF.l0_t_176_0,
                                                QF.l0_t_176_1,
                                                QF.l0_t_176_2,
                                                QF.l0_t_176_3,
                                                QF.l0_t_176_4,
                                                QF.l0_t_176_5,
                                                QF.l0_t_176_6,
                                                QF.l0_t_176_7,
                                                QF.l0_t_179_0,
                                                QF.l0_t_179_1,
                                                QF.l0_t_179_2,
                                                QF.l0_t_179_3,
                                                QF.l0_t_179_4,
                                                QF.l0_t_179_5,
                                                QF.l0_t_179_6,
                                                QF.l0_t_179_7,
                                                QF.l0_t_169_0,
                                                QF.l0_t_169_1,
                                                QF.l0_t_178_0,
                                                QF.l0_t_178_1,
                                                QF.l0_t_178_2,
                                                QF.l0_t_178_3,
                                                QF.l0_t_178_4,
                                                QF.l0_t_178_5,
                                                QF.l0_t_178_6,
                                                QF.l0_t_178_7]

}

assert check_roops_core_objects_SinglyLinkedList_contains_0{
  postcondition_roops_core_objects_SinglyLinkedList_contains_0[QF.return_1,
                                                              (QF.broops_core_objects_SinglyLinkedListNode_next_0)+(QF.froops_core_objects_SinglyLinkedListNode_next_0),
                                                              QF.roops_core_objects_SinglyLinkedListNode_value_0,
                                                              QF.roops_core_objects_SinglyLinkedList_header_0,
                                                              QF.thiz_0,
                                                              QF.throw_9,
                                                              QF.value_param_0]
}
