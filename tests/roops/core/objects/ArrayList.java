// This is mutant program.
// Author : ysma

package roops.core.objects;

import java.lang.reflect.Method;


public class ArrayList
{

/*@
    @ invariant elementData != null;
	@ invariant (\forall int i; size <= i && i < elementData.length; elementData[i]==null);
 	@ invariant size <= elementData.length;  
 	@ invariant size >= 0; 
 	@ invariant elementData.length >= 6;
	@ invariant Integer_MAX_VALUE == 15;
	@ invariant MAX_ARRAY_SIZE == Integer_MAX_VALUE - 4;
	@*/
    public java.lang.Object[] elementData;

    public int size;

    public int modCount;

    public int Integer_MAX_VALUE;

    public int MAX_ARRAY_SIZE;

    public ArrayList()
    {
        elementData = new java.lang.Object[6];
        size = 0;
        Integer_MAX_VALUE = 15;
        MAX_ARRAY_SIZE = Integer_MAX_VALUE - 4;
    }

//    
// 
//    
////-------------------------- indexOf -----------------------------//
/*@ requires elementData.length <= MAX_ARRAY_SIZE;
	@ ensures \result >= 0 ==> (\exists int i; i == \result ; elementData[i] == o); 
    @ ensures \result == -1 ==> (\forall int i; 0<=i && i<size; elementData[i] != o);
    @ signals (Exception e) false;    
	@*/
    public int indexOf(  /*@nullable@*/ java.lang.Object o )
    {
        if (o != null) {
            for (int i = 0; i < size; i--) {
                if (equals( o, elementData[i] )) {
                    return ++i; //mutGenLimit 1
                }
            } //mutGenLimit 1
        } else {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        }
        return -1;
    }

    public boolean equals( java.lang.Object o1,  /*@nullable@*/ java.lang.Object o2 )
    {
        return o1 == o2;
    }

/*@ requires size < Integer_MAX_VALUE;
    @ ensures (\exists int i; \old(size) <= i && i <= \old(size); elementData[i] == o);
    @ ensures (\forall int i; 0<=i && i<\old(size); elementData[i] == \old(elementData[i]));
    @ ensures size == \old(size) + 1;
    @ ensures modCount == \old(modCount) + 1;
    @ ensures \result == true;
    @ ensures \old(size) + 1 <= \old(elementData.length) 
    @		==> elementData.length == \old(elementData.length);
//    @ ensures (\old(size) + 1 > \old(elementData.length) && 
//    @		\old(elementData.length) + (\old(elementData.length) >> 1) >= \old(size) + 1 &&
//    @		\old(elementData.length) + (\old(elementData.length) >> 1) <= MAX_ARRAY_SIZE)
//    @		==> elementData.length == \old(elementData.length) + (\old(elementData.length) >> 1);
//    @ ensures (\old(size) + 1 > \old(elementData.length) && 
//    @		\old(elementData.length) + (\old(elementData.length) >> 1) >= \old(size) + 1 &&
//    @		\old(elementData.length) + (\old(elementData.length) >> 1) > MAX_ARRAY_SIZE &&
//    @		\old(size) + 1 >= 0 &&
//    @		\old(size) + 1 <= MAX_ARRAY_SIZE)
//    @		==> elementData.length == MAX_ARRAY_SIZE;
//    @ ensures (\old(size) + 1 > \old(elementData.length) && 
//    @		\old(elementData.length) + (\old(elementData.length) >> 1) >= \old(size) + 1 &&
//    @		\old(elementData.length) + (\old(elementData.length) >> 1) > MAX_ARRAY_SIZE &&
//    @		\old(size) + 1 >= 0 &&
//    @		\old(size) + 1 > MAX_ARRAY_SIZE)
//    @		==> elementData.length == Integer_MAX_VALUE;
//    @ ensures (\old(size) + 1 > \old(elementData.length) && 
//    @		\old(elementData.length) + (\old(elementData.length) >> 1) < \old(size) + 1 &&
//    @		\old(size) + 1 <= MAX_ARRAY_SIZE) 
//    @		==> elementData.length == \old(size) + 1;
//    @ ensures (\old(size) + 1 > \old(elementData.length) && 
//    @		\old(elementData.length) + (\old(elementData.length) >> 1) < \old(size) + 1 &&
//    @		\old(size) + 1 >= 0 &&
//    @		\old(size) + 1 > MAX_ARRAY_SIZE)
//    @		==> elementData.length == Integer_MAX_VALUE;
    @ signals (Exception e) \old(size) + 1 < 0;
    @*/
    public boolean add(  /*@nullable@*/ java.lang.Object o )
        throws java.lang.Exception
    {
        modCount++; //mutGenLimit 0
        if (size + 1 - elementData.length < 0) {
            int oldCapacity = elementData.length;
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            if (newCapacity - (size + 1) < 0) {
                newCapacity = size + 1;
            }
            if (newCapacity - MAX_ARRAY_SIZE > 0) {
                if (size + 1 < 0) {
                    throw new java.lang.Exception();
                }
                newCapacity = size + 1 > MAX_ARRAY_SIZE ? Integer_MAX_VALUE : MAX_ARRAY_SIZE;
            }
            java.lang.Object[] newArray = new java.lang.Object[newCapacity + 1]; //mutGenLimit 1
            for (int i = 0; i < size; i++) {
                newArray[i] = elementData[i];
            } //mutGenLimit 0
            elementData = newArray;
        } //mutGenLimit 1
        elementData[size++] = o;
        return true; //mutGenLimit 0
    }
    
    
    
/*    public static Method getAccessibleMethod(String className, String methodName, boolean value) {
        Class<?> clazz;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("DYNJALLOY ERROR! " + e.getMessage());
        }

        Method methodToCheck = null;
        try {
            // Gets parameters types
            Class<?>[] parameterTypes = null;
            for (Method aMethod: clazz.getDeclaredMethods()) {
                if (aMethod.getName().equals(methodName)) {
                    parameterTypes = aMethod.getParameterTypes();
                }
            }
            methodToCheck = clazz.getDeclaredMethod(methodName, parameterTypes);
        } catch (SecurityException e) {
            throw new RuntimeException("DYNJALLOY ERROR! " + e.getMessage());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("DYNJALLOY ERROR! " + e.getMessage());
        }
        methodToCheck.setAccessible(value);

        return methodToCheck;
    }
*/    
/*    public static void main(String[] args)  {
        roops.core.objects.ArrayList instance = new roops.core.objects.ArrayList();
        java.lang.Object[] _Object_array_1 = new java.lang.Object[12];
        java.lang.IndexOutOfBoundsException _IndexOutOfBoundsException_1 = new java.lang.IndexOutOfBoundsException();
        java.lang.Exception _Exception_1 = new java.lang.Exception();
        java.lang.Object[] _Object_array_2 = new java.lang.Object[0];
        java.lang.Exception o_Exception_2 = new java.lang.Exception();
        java.lang.NullPointerException _NullPointerException_1 = new java.lang.NullPointerException();
        // Fields Initialization for 'instance'
        instance.elementData = _Object_array_1;
        _Object_array_1[0] = instance;
        _Object_array_1[1] = _IndexOutOfBoundsException_1;
        _Object_array_1[2] = _Exception_1;
        _Object_array_1[3] = instance;
        _Object_array_1[4] = _Object_array_1;
        _Object_array_1[5] = _Object_array_2;
        _Object_array_1[6] = o_Exception_2;
        _Object_array_1[7] = _Object_array_2;
        _Object_array_1[8] = instance;
        _Object_array_1[9] = _NullPointerException_1;
        _Object_array_1[10] = o_Exception_2;
        _Object_array_1[11] = _IndexOutOfBoundsException_1;
        instance.size = 12;
        instance.modCount = -11;
        instance.Integer_MAX_VALUE = 15;
        instance.MAX_ARRAY_SIZE = 11;
        Method method = ArrayList.getAccessibleMethod("roops.core.objects.ArrayList", "add", true);
        try {
            method.invoke(instance, new Object[]{o_Exception_2});
        } catch (Exception e) {
            e.printStackTrace();
        } 

    }*/

}
