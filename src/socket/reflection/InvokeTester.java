package socket.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InvokeTester {

	public int add(int arg1, int arg2) {
		return arg1 + arg2;
	}

	public String echo(String string) {
		return "echo:" + string;
	}

	/**
	 * @param args
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Class classType = InvokeTester.class;
		Object object = classType.newInstance();
		
		Method methodInt = classType.getMethod("add", new Class[] { int.class,
				int.class });
		Method methodString = classType.getMethod("echo", new Class[] {String.class});
		
		int i = (int)methodInt.invoke(object, new Object[]{100,200});
		
		String s = (String)methodString.invoke(object, new Object[]{"hello"});
		
		System.out.println(i + " " + s);
	}
}
