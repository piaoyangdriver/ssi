package socket.rmi.remote;

import java.rmi.RemoteException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class SimpleServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			HeService h1 = new HeServiceImpl("service1");
			HeService h2 = new HeServiceImpl("service2");
			Context namingContext = new InitialContext();
			namingContext.rebind("rmi://localhost:8081/HeService1", h1);
			namingContext.rebind("rmi://localhost:8081/HeService2", h2);
			System.out.println("服务器注册了两个HeService对象");
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

}
