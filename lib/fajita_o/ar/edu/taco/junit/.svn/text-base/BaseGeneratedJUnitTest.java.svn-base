package ar.edu.taco.junit;

import java.lang.reflect.Field;

import junit.framework.TestCase;

public class BaseGeneratedJUnitTest extends TestCase {
	
	public void updateValue(Object instance, String fieldName, Object value) {
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
}
