package socket.reflection.proxy;

public class Client1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HelloService helloServiceImpl = new HelloServiceImpl();
		
		HelloService helloServiceProxy = new HelloServiceProxy(helloServiceImpl);
		
		System.out.println(helloServiceProxy.getTime());
	}

}
