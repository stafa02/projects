package genericSerDeser.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class PopulateObjects {
	List<Object> objects;

	public PopulateObjects() {
		objects = new ArrayList<Object>();
		Logger.writeMessage("In constructor for populate objects \n", Logger.DebugLevel.CONSTRUCTOR);
	}

	/**
	 * 
	 * @param lines
	 */
	public void populate(List<String> lines) {
		String clsName = lines.get(0).split(":")[1];
		Class cls = null;
		Object obj = null;
		try {
			cls = Class.forName(clsName);
			obj = cls.newInstance();
		} catch (Exception e) {
			System.err.println("Error in deserialization");
			e.printStackTrace();
			System.exit(1);
		} finally {
		}
		lines.remove(0);
		for (String line : lines) {
			Class[] signature = new Class[1];
			Object[] params = new Object[1];
			String[] elements = line.split(",");
			try {
				setTypeAndValue(elements[0].split("=")[1].trim(), elements[2].split("=")[1].trim(), signature, params);
			} catch (ArrayIndexOutOfBoundsException e) {
				if (elements.length == 4) {
					setTypeAndValue(elements[0].split("=")[1].trim(), "," + elements[3].trim(), signature, params);
				} else {
					setTypeAndValue(elements[0].split("=")[1].trim(), ",", signature, params);
				}
			}
			String methodName = "set" + elements[1].split("=")[1];
			Method meth = null;
			try {
				meth = cls.getMethod(methodName, signature);
			} catch (NoSuchMethodException | SecurityException e) {
				System.err.println("Error in getting method");
				e.printStackTrace();
				System.exit(1);
			} finally {
			}

			try {
				meth.invoke(obj, params);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				System.err.println("Error in method invocation");
				e.printStackTrace();
				System.exit(1);
			}
		}
		Logger.writeMessage("Object deserialized\n", Logger.DebugLevel.DESERIALIZED_OBJECT);
		addElement(obj);
	}

	/**
	 * 
	 * @param obj
	 */
	private void addElement(Object obj) {
		objects.add(obj);
	}

	/**
	 * 
	 * @param element
	 * @param elementVal
	 * @param signature
	 * @param params
	 */
	private void setTypeAndValue(String element, String elementVal, Object[] signature, Object[] params) {
		Class type = null;
		Object value = null;
		switch (element) {
		case "byte":
			type = Byte.TYPE;
			value = Byte.parseByte(elementVal);
			break;
		case "short":
			type = Short.TYPE;
			value = Short.parseShort(elementVal);
			break;
		case "long":
			type = Long.TYPE;
			value = Long.parseLong(elementVal);
			break;
		case "int":
			type = Integer.TYPE;
			value = Integer.parseInt(elementVal);
			break;
		case "float":
			type = Float.TYPE;
			value = Float.parseFloat(elementVal);
			break;
		case "double":
			type = Double.TYPE;
			value = Double.parseDouble(elementVal);
			break;
		case "boolean":
			type = Boolean.TYPE;
			value = Boolean.parseBoolean(elementVal);
			break;
		case "char":
			type = Character.TYPE;
			value = elementVal.charAt(0);
			break;
		case "String":
			type = String.class;
			value = elementVal;
			break;
		}
		signature[0] = type;
		params[0] = value;
	}

	public List<Object> getobjects() {
		return objects;
	}
}