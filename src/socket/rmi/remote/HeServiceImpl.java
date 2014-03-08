package socket.rmi.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class HeServiceImpl extends UnicastRemoteObject implements HeService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	public HeServiceImpl(String name) throws RemoteException {
		this.name = name;
	}

	@Override
	public String echo(String msg) throws RemoteException {
		System.out.println(name + ":调用echo()方法");
		return "echo:" + msg + "from" + name;
	}

	@Override
	public Date getTime() throws RemoteException {
		System.out.println(name + ":调用getTime()方法");
		return new Date();
	}
}
