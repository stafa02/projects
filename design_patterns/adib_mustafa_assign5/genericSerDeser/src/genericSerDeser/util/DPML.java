package genericSerDeser.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import genericSerDeser.strategy.SerStrategy;

public class DPML implements SerStrategy {

	@Override
	public String serialize(Object o) {
		Class cls = o.getClass();
		Field[] fields = cls.getDeclaredFields();
		String serializedObject = "<fqn:" + cls.getName() + ">\n";
		for (Field field : fields) {
			Class[] signature = new Class[0];
			Object[] params = new Object[0];
			String fieldName = field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
			String methodName = "get" + fieldName;
			Class type = field.getType();
			String typeName = type.getName();
			if (typeName.contains("java.lang.String")) {
				typeName = "String";
			}
			Object val = null;
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
				val = meth.invoke(o, params);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				System.err.println("Error in method invocation");
				e.printStackTrace();
				System.exit(1);
			}
			serializedObject += "<type=" + typeName + ", var=" + fieldName + ", value=" + val + ">\n";
		}
		serializedObject += "</fqn:" + cls.getName() + ">\n";
		return serializedObject;
	}

}
