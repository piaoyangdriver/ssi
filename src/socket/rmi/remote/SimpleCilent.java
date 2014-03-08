package socket.rmi.remote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;

public class SimpleCilent {
	
	public static void showRemoteObjects(Context namingContext) throws Exception{
		NamingEnumeration<NameClassPair> e = namingContext.list("rmi:");
		while(e.hasMore()){
			System.out.println(e.next().getName());
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String url = "rmi://localhost/";
		try{
			Context namingContext = new InitialContext();
			
			HeService service1 = (HeService)namingContext.lookup(url + "HeService1");
			HeService service2 = (HeService)namingContext.lookup(url + "HeService2");
			
			Class stubClass = service1.getClass();
			System.out.println("service1 is subclass of " + stubClass.getName());
			Class[] interfaces = stubClass.getInterfaces();
			for(Class inte : interfaces){
				System.out.println("implements :" + inte.getName());
			}
			
			System.out.println(service1.echo("hello"));
			System.out.println(service1.getTime());
			
			System.out.println(service2.echo("hello"));
			System.out.println(service2.getTime());
			
			
			showRemoteObjects(namingContext);
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
