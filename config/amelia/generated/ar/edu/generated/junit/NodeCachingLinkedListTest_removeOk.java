package ar.edu.generated.junit;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import org.junit.Test;

public class NodeCachingLinkedListTest_removeOk {

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
    public void testremoveOk_0() {
        roops.core.objects.NodeCachingLinkedList instance = new roops.core.objects.NodeCachingLinkedList();
        // Fields Initialization for 'instance'
        
        roops.core.objects.LinkedListNode _LinkedListNode_1 = new roops.core.objects.LinkedListNode();
        // Fields Initialization for '_LinkedListNode_1'
        
        roops.core.objects.LinkedListNode _LinkedListNode_2 = new roops.core.objects.LinkedListNode();
        // Fields Initialization for '_LinkedListNode_2'
        
        roops.core.objects.LinkedListNode _LinkedListNode_3 = new roops.core.objects.LinkedListNode();
        // Fields Initialization for '_LinkedListNode_3'
        
        roops.core.objects.LinkedListNode _LinkedListNode_4 = new roops.core.objects.LinkedListNode();
        // Fields Initialization for '_LinkedListNode_4'
        updateValue(_LinkedListNode_4, "previous", _LinkedListNode_1);
        updateValue(_LinkedListNode_4, "next", _LinkedListNode_3);
        updateValue(_LinkedListNode_4, "value", _LinkedListNode_3);
        updateValue(_LinkedListNode_3, "previous", _LinkedListNode_4);
        updateValue(_LinkedListNode_3, "next", _LinkedListNode_2);
        
        roops.core.objects.LinkedListNode _LinkedListNode_5 = new roops.core.objects.LinkedListNode();
        updateValue(_LinkedListNode_3, "value", _LinkedListNode_5);
        updateValue(_LinkedListNode_2, "previous", _LinkedListNode_3);
        updateValue(_LinkedListNode_2, "next", _LinkedListNode_1);
        
        roops.core.objects.LinkedListNode _LinkedListNode_6 = new roops.core.objects.LinkedListNode();
        updateValue(_LinkedListNode_2, "value", _LinkedListNode_6);
        updateValue(_LinkedListNode_1, "previous", _LinkedListNode_2);
        updateValue(_LinkedListNode_1, "next", _LinkedListNode_4);
        updateValue(_LinkedListNode_1, "value", null);
        updateValue(instance, "header", _LinkedListNode_1);
        updateValue(instance, "firstCachedNode", _LinkedListNode_5);
        updateValue(instance, "maximumCacheSize", 3);
        updateValue(instance, "cacheSize", 1);
        updateValue(instance, "size", 3);
        updateValue(instance, "DEFAULT_MAXIMUM_CACHE_SIZE", 3);
        updateValue(instance, "modCount", 3);
        
        // Parameter Initialization
        int index_Integer_1 = 0;
        
        // Method Invocation
        Method method = getAccessibleMethod("roops.core.objects.NodeCachingLinkedList", "removeOk", true);
        try {
            method.invoke(instance, index_Integer_1);
        } catch (Exception e) {
            e.printStackTrace();
        } 

    }
}