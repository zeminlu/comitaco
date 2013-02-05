package ar.edu.generated.junit;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import org.junit.Test;

public class AvlTreeTest_privateinsert {

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
    public void testprivateinsert_0() {
        roops.core.objects.AvlTree instance = new roops.core.objects.AvlTree();
        // Fields Initialization for 'instance'
        
        roops.core.objects.AvlNode n_AvlNode_1 = new roops.core.objects.AvlNode();
        // Fields Initialization for 'n_AvlNode_1'
        updateValue(n_AvlNode_1, "element", 5);
        
        roops.core.objects.AvlNode _AvlNode_2 = new roops.core.objects.AvlNode();
        // Fields Initialization for '_AvlNode_2'
        updateValue(_AvlNode_2, "element", -2);
        updateValue(_AvlNode_2, "left", null);
        updateValue(_AvlNode_2, "right", null);
        updateValue(_AvlNode_2, "height", 0);
        updateValue(n_AvlNode_1, "left", _AvlNode_2);
        updateValue(n_AvlNode_1, "right", null);
        updateValue(n_AvlNode_1, "height", 1);
        updateValue(instance, "root", n_AvlNode_1);
        
        // Parameter Initialization
        int x_Integer_1 = -5;
        
        roops.core.objects.AvlNode aux_AvlNode_3 = new roops.core.objects.AvlNode();
        // Fields Initialization for 'aux_AvlNode_3'
        updateValue(aux_AvlNode_3, "element", -4);
        updateValue(aux_AvlNode_3, "left", n_AvlNode_1);
        
        roops.core.objects.AvlNode _AvlNode_4 = new roops.core.objects.AvlNode();
        // Fields Initialization for '_AvlNode_4'
        updateValue(_AvlNode_4, "element", -4);
        updateValue(_AvlNode_4, "left", _AvlNode_2);
        updateValue(_AvlNode_4, "right", _AvlNode_2);
        updateValue(_AvlNode_4, "height", 0);
        updateValue(aux_AvlNode_3, "right", _AvlNode_4);
        updateValue(aux_AvlNode_3, "height", 0);
        
        // Method Invocation
        Method method = getAccessibleMethod("roops.core.objects.AvlTree", "privateinsert", true);
        try {
            method.invoke(instance, x_Integer_1, n_AvlNode_1, aux_AvlNode_3);
        } catch (Exception e) {
            e.printStackTrace();
        } 

    }
}