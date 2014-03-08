package socket.reflection.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 创建一个实现了HelloService接口的动态代理类的实例 参数helloService引用被代理的HelloService实例
 * 
 * @author hp
 * 
 */
public class HelloServiceProxyFactory {

	public static HelloService getHelloServiceProxy(
			final HelloService helloService) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		// 创建一个实现了InvocationHandler接口的匿名类的实例
		InvocationHandler handler = new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Exception {
				System.out.println("before calling " + method);
				Object result = method.invoke(helloService, args);

				// 调用被代理的HelloService实例的方法
				System.out.println("after calling" + method);
				return result;
			}
		};

		Class classType = HelloService.class;
		// 方式1
		/*
		 * Class classType2 = Proxy.getProxyClass(classType.getClassLoader(),
		 * new Class[] { HelloService.class });
		 * 
		 * HelloService hs = (HelloService) classType2.getConstructor( new
		 * Class[] { handler.getClass() }).newInstance( new Object[] { handler
		 * });
		 */

		// 方式2
		return (HelloService) Proxy.newProxyInstance(
				classType.getClassLoader(), new Class[] { classType }, handler);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
