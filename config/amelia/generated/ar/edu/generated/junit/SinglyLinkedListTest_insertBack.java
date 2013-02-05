package ar.edu.generated.junit;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import org.junit.Test;

public class SinglyLinkedListTest_insertBack {

    /**
     * Auxiliar function that embed awful reflection code
     * 
     * @param instance
     * @param fieldName
     * @param value
     */
    private void updateValue(Object instance, String fieldName, Object value) {
        for (Field aField : instance.getClass().getDeclaredFields()) {
            if (aField.getName().equals(fieldName)) {
                try {
                    aField.setAccessible(true);
                    if (aField.getType().isPrimitive()) {
                        String typeSimpleName = aField.getType().getSimpleName();
                        if (typeSimpleName.equals("boolean")) {
                            aField.setBoolean(instance, (Boolean) value);
                        } else if (typeSimpleName.endsWith("byte")) {
                            aField.setByte(instance, (Byte) value);
                        } else if (typeSimpleName.endsWith("char")) {
                            aField.setChar(instance, (Character) value);
                        } else if (typeSimpleName.endsWith("double")) {
                            aField.setDouble(instance, (Double) value);
                        } else if (typeSimpleName.endsWith("float")) {
                            aField.setFloat(instance, (Float) value);
                        } else if (typeSimpleName.endsWith("int")) {
                            aField.setInt(instance, (Integer) value);
                        } else if (typeSimpleName.endsWith("long")) {
                            aField.setLong(instance, (Long) value);
                        } else if (typeSimpleName.endsWith("short")) {
                            aField.setShort(instance, (Short) value);
                        } else {
                            System.out.println("ERROR: No difinida");
                        }
                    } else {
                        aField.set(instance, value);
                    };

                    aField.setAccessible(false);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Auxiliar function that embed awful reflection code
     * 
     * @param className
     * @param methodName
     * @param value
     */
    private Method getAccessibleMethod(String className, String methodName, boolean value) {
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

    @Test
    public void testinsertBack_0() {
        roops.core.objects.SinglyLinkedList instance = new roops.core.objects.SinglyLinkedList();
        // Fields Initialization for 'instance'
        updateValue(instance, "header", null);
        
        // Parameter Initialization
        
        java.lang.Object data_SinglyLinkedListNode_1 = new java.lang.Object();
        
        roops.core.objects.SinglyLinkedListNode freshNode_SinglyLinkedListNode_2 = new roops.core.objects.SinglyLinkedListNode();
        // Fields Initialization for 'freshNode_SinglyLinkedListNode_2'
        
        roops.core.objects.SinglyLinkedListNode _SinglyLinkedListNode_3 = new roops.core.objects.SinglyLinkedListNode();
        // Fields Initialization for '_SinglyLinkedListNode_3'
        updateValue(_SinglyLinkedListNode_3, "next", null);
        
        java.lang.Object _Object_1 = new java.lang.Object();
        updateValue(_SinglyLinkedListNode_3, "value", _Object_1);
        updateValue(freshNode_SinglyLinkedListNode_2, "next", _SinglyLinkedListNode_3);
        
        roops.core.objects.SinglyLinkedListNode _SinglyLinkedListNode_4 = new roops.core.objects.SinglyLinkedListNode();
        updateValue(freshNode_SinglyLinkedListNode_2, "value", _SinglyLinkedListNode_4);
        
        // Method Invocation
        Method method = getAccessibleMethod("roops.core.objects.SinglyLinkedList", "insertBack", true);
        try {
            method.invoke(instance, data_SinglyLinkedListNode_1, freshNode_SinglyLinkedListNode_2);
        } catch (Exception e) {
            e.printStackTrace();
        } 

    }
}