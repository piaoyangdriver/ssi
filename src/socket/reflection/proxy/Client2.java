package socket.reflection.proxy;

import java.lang.reflect.InvocationTargetException;

public class Client2 {

	/**
	 * @param args
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		HelloService helloService = new HelloServiceImpl();

		HelloService helloServiceProxy = HelloServiceProxyFactory
				.getHelloServiceProxy(helloService);
		System.out
				.println("动态代理类的名字:" + helloServiceProxy.getClass().getName());

		System.out.println(helloServiceProxy.echo("hello"));
	}

}
