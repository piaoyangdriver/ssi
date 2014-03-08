package socket.file;

import java.net.ServerSocket;
import java.net.Socket;

class PictureServer 
{
	public static void main(String[] args) throws Exception
	{
		ServerSocket ss = new ServerSocket(4456);
		while(true)
		{
			Socket s = ss.accept();	//循环侦听服务器端口是否有访问该端口的进程，有则接收
			
			ServerThread st= new ServerThread(s);//将客户端socket传入服务端

			new Thread(st).start();//开启服务端线程为客户端服务
		}
		//ss.close();
	}
}
