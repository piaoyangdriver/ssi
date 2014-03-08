package socket.reflection;

import java.lang.reflect.Method;

public class DumpMethods {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String className = "java.util.Stack";

		try {
			Class cl = Class.forName(className);
			Method[] methods = cl.getMethods();
			// 获得类的所有方法
			for (Method method : methods) {
				System.out.println("Method:" + method.toString());
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
