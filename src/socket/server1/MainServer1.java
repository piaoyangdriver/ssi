package socket.server1;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import socket.server2.DataProvider2;

/**
 * webServer11端
 * 
 * @author hp
 * 
 */
public class MainServer1 {

	public static void main(String[] args) throws IOException {
		// 1 发送对象到server2
		Socket socket = new Socket("127.0.0.1", DataProvider2.SERVER2_PORT);
		ObjectOutputStream out = new ObjectOutputStream(
				socket.getOutputStream());
		SendBean sendBean = new SendBean();
		sendBean.setName("Tom");

		out.writeObject(sendBean);
		socket.shutdownOutput();

		// input
		BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
		byte b[] = new byte[100]; // 创建合适文件大小的数组
		int readLength = bis.read(b);
		String response = new String(b, 0, readLength, "UTF-8");
		if ("OK".equals(response)) {
			//System.out.println("server1 get the connection");
		}

		if (socket != null) {
			socket.close();
		}
	}

}
