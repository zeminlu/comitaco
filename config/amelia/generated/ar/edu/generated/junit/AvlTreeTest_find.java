package ar.edu.generated.junit;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import org.junit.Test;

public class AvlTreeTest_find {

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
    public void testfind_0() {
        roops.core.objects.AvlTree instance = new roops.core.objects.AvlTree();
        // Fields Initialization for 'instance'
        
        roops.core.objects.AvlNode _AvlNode_1 = new roops.core.objects.AvlNode();
        // Fields Initialization for '_AvlNode_1'
        updateValue(_AvlNode_1, "element", 5);
        
        roops.core.objects.AvlNode _AvlNode_2 = new roops.core.objects.AvlNode();
        // Fields Initialization for '_AvlNode_2'
        updateValue(_AvlNode_2, "element", 2);
        
        roops.core.objects.AvlNode _AvlNode_3 = new roops.core.objects.AvlNode();
        // Fields Initialization for '_AvlNode_3'
        updateValue(_AvlNode_3, "element", 1);
        updateValue(_AvlNode_3, "left", null);
        updateValue(_AvlNode_3, "right", null);
        updateValue(_AvlNode_3, "height", 0);
        updateValue(_AvlNode_2, "left", _AvlNode_3);
        
        roops.core.objects.AvlNode _AvlNode_4 = new roops.core.objects.AvlNode();
        // Fields Initialization for '_AvlNode_4'
        updateValue(_AvlNode_4, "element", 3);
        updateValue(_AvlNode_4, "left", null);
        updateValue(_AvlNode_4, "right", null);
        updateValue(_AvlNode_4, "height", 0);
        updateValue(_AvlNode_2, "right", _AvlNode_4);
        updateValue(_AvlNode_2, "height", 1);
        updateValue(_AvlNode_1, "left", _AvlNode_2);
        
        roops.core.objects.AvlNode _AvlNode_5 = new roops.core.objects.AvlNode();
        // Fields Initialization for '_AvlNode_5'
        updateValue(_AvlNode_5, "element", 6);
        updateValue(_AvlNode_5, "left", null);
        
        roops.core.objects.AvlNode _AvlNode_6 = new roops.core.objects.AvlNode();
        // Fields Initialization for '_AvlNode_6'
        updateValue(_AvlNode_6, "element", 7);
        updateValue(_AvlNode_6, "left", null);
        updateValue(_AvlNode_6, "right", null);
        updateValue(_AvlNode_6, "height", 0);
        updateValue(_AvlNode_5, "right", _AvlNode_6);
        updateValue(_AvlNode_5, "height", 1);
        updateValue(_AvlNode_1, "right", _AvlNode_5);
        updateValue(_AvlNode_1, "height", 2);
        updateValue(instance, "root", _AvlNode_1);
        
        // Parameter Initialization
        int x_Integer_1 = 6;
        
        // Method Invocation
        Method method = getAccessibleMethod("roops.core.objects.AvlTree", "find", true);
        try {
            method.invoke(instance, x_Integer_1);
        } catch (Exception e) {
            e.printStackTrace();
        } 

    }
}