package socket.rmi.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface HeService extends Remote {
	
	public String echo(String msg) throws RemoteException;
	
	public Date getTime() throws RemoteException;

}
