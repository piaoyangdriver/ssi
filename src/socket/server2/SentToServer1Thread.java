package socket.server2;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import socket.server1.SendBean;

public class SentToServer1Thread implements Runnable {
	private Socket socket;
	public SentToServer1Thread(){
		try {
			socket = new Socket("127.0.0.1", 58001);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(true){
			doSend();
		}
	}
	
	private void doSend(){
		try {
			SendBean bean = DataProvider2.SEND_SERVER1_BEAN.take();
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(bean);
			socket.shutdownOutput();
			if(socket != null){
				socket.close();
			}
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}

}
