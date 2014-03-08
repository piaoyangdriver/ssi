package socket.server2;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import socket.server1.SendBean;

/**
 * 负责接受Server1传过来的SendBean
 * 将SendBean放到队列中
 * @author hp
 *
 */
public class Server2ProducerThread implements Runnable {
	
	private ServerSocket socketServer;
	
	public Server2ProducerThread(){
		try {
			this.socketServer = new ServerSocket(DataProvider2.SERVER2_PORT);
			System.out.println("the server2 is start");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(true){
			doListening();
		}
	}
	
	private void doListening(){
		Socket socket = null;
		ObjectInputStream objInput = null;
		BufferedOutputStream bos = null;
		try{
			//while(true){
				socket = socketServer.accept();
				objInput = new ObjectInputStream(socket.getInputStream());
				Object obj = objInput.readObject();
				if(obj instanceof SendBean){
					//放入队列中
					SendBean sendBean = (SendBean)obj;
					DataProvider2.SENDBEAN_DATA.put(sendBean);
				}
				socket.shutdownInput();
				
				//output
				bos = new BufferedOutputStream(socket.getOutputStream());
				String response = "OK";
				bos.write(response.getBytes("UTF-8"));
				bos.flush();
				socket.shutdownOutput();
			//}
		}catch (ClassNotFoundException | IOException | InterruptedException e) {
			e.printStackTrace();
		}finally{
			if(socket != null){
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
