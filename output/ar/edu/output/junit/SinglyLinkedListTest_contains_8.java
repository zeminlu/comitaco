package ar.edu.output.junit;
           import java.net.URL;
           import java.net.URLClassLoader;
           import java.net.MalformedURLException;
           import java.io.File;
           import java.lang.reflect.InvocationTargetException;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import org.junit.Test;

public class SinglyLinkedListTest_contains_8 {

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
    private Method getAccessibleMethod(Class<?> clazz, String methodName, boolean value) {
        try {
        } catch (Exception e) {
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
    public void testcontains_0(String fileClasspath, String className, String methodName) throws IllegalAccessException, InvocationTargetException, ClassNotFoundException, InstantiationException, MalformedURLException {
           ClassLoader cl = ClassLoader.getSystemClassLoader();
           ClassLoader cl2 = new URLClassLoader(new URL[]{new File(fileClasspath).toURI().toURL()}, cl);
           Class<?> clazz = cl2.loadClass(className);
           Object instance = clazz.newInstance();
           cl2 = null;
        roops.core.objects.SinglyLinkedListNode _SinglyLinkedListNode_1 = new roops.core.objects.SinglyLinkedListNode();
        roops.core.objects.SinglyLinkedListNode value_param_SinglyLinkedListNode_2 = new roops.core.objects.SinglyLinkedListNode();
        // Fields Initialization for 'instance'
        // Fields Initialization for '_SinglyLinkedListNode_1'
        updateValue(_SinglyLinkedListNode_1, "next", null);
        updateValue(_SinglyLinkedListNode_1, "value", value_param_SinglyLinkedListNode_2);
        updateValue(instance, "header", _SinglyLinkedListNode_1);
        // Parameter Initialization
        
        // Method Invocation
        Method method = getAccessibleMethod(clazz, methodName, true);
        try {
            method.invoke(instance, new Object[]{value_param_SinglyLinkedListNode_2});
           instance = null;
           method = null;
        } catch (Exception e) {
           throw(new java.lang.RuntimeException(e));
        } 

    }
}
