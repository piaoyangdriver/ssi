package socket.reflection.proxy;

import java.util.Date;

public class HelloServiceProxy implements HelloService {
	
	private HelloService helloService;

	public HelloServiceProxy(HelloService helloService){
		this.helloService = helloService;
	}
	
	@Override
	public String echo(String msg) {
		System.out.println("before calling echo:");
		String result = helloService.echo(msg);		
		System.out.println("after calling echo:");
		return result;
	}

	@Override
	public Date getTime() {
		System.out.println("before calling getTime:");
		Date date = helloService.getTime();	
		System.out.println("after calling getTime:");
		return date;
	}

}
