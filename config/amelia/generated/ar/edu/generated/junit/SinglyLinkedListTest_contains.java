package a13536933069760.aOpenJMLInWrap8.ar.edu.itba.stryker.mutants;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import org.junit.Test;

public class SinglyLinkedListTest_contains {

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
throw(new java.lang.RuntimeException(e));
                } catch (IllegalAccessException e) {
throw(new java.lang.RuntimeException(e));
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
    public void testcontains_0(String methodName) {
        a13536933069760.aOpenJMLInWrap8.ar.edu.itba.stryker.mutants.SinglyLinkedList instance = new a13536933069760.aOpenJMLInWrap8.ar.edu.itba.stryker.mutants.SinglyLinkedList();
        // Fields Initialization for 'instance'
        // Fields Initialization for '_SinglyLinkedListNode_1'
        // Fields Initialization for '_SinglyLinkedListNode_2'
        updateValue(_SinglyLinkedListNode_2, "next", null);
        updateValue(_SinglyLinkedListNode_2, "value", null);
        updateValue(_SinglyLinkedListNode_1, "next", _SinglyLinkedListNode_2);
        updateValue(_SinglyLinkedListNode_1, "value", value_param_Object_1);
        updateValue(instance, "header", _SinglyLinkedListNode_1);
        
        // Method Invocation
        Method method = getAccessibleMethod("a13536933069760.aOpenJMLInWrap8.ar.edu.itba.stryker.mutants.SinglyLinkedList", methodName, true);
        try {
            method.invoke(instance, new Object[]{value_param_Object_1});
        } catch (Exception e) {
throw(new java.lang.RuntimeException(e));
        } 

    }
}
