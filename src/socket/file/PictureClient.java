package socket.file;

/*
 上传图片

 图片为字节数据，不能用字符流读取
 */

import java.io.File;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/*
 客户端：
 1. 建立客户端点
 2，读取客户端已有图片数据
 3，通过socket输出流将数据发给服务端
 4，读取服务端反馈信息
 5，关闭流
 */
class PictureClient {
	public static void main(String[] args) throws Exception {
		// 对客户端上传的图片进行限制
		
		/*File file = new File("D:\\Picture\\love.jpg");

		Socket s = new Socket("127.0.0.1", 4456);
		// 读图片字节数据
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
				file));

		// 写到socket输出流
		PrintStream out = new PrintStream(s.getOutputStream(), true);

		byte[] buf = new byte[1024];
		int len = 0;
		while ((len = bis.read(buf)) != -1) {
			out.write(buf, 0, len);
		}
		
		s.shutdownOutput();// 向socket输入流写入流结束标记

		// 读取服务端返回的信息
		BufferedReader bufr = new BufferedReader(new InputStreamReader(s.getInputStream()));
		String str = bufr.readLine();
		System.out.println(str);

		s.close();
		bis.close();*/
		
		Socket socket = new Socket("127.0.0.1", 4456);
		SendFileBean send = new SendFileBean();
		File file = new File("D:\\Picture\\love.jpg");
		send.setFile(file);
		
		OutputStream out = socket.getOutputStream();		
		ObjectOutputStream objout = new ObjectOutputStream(out);
		objout.writeObject(send);
		
		socket.shutdownOutput();
		socket.close();
		
	}
}

/*
 * 服务端： 1，建立服务端点 2，通过socket输入流读取客户端上传的数据 3，通过字节输出流将数据写入到新的图片文件 4，反馈信息给客户端 5，关闭流
 */

/*
 * 这个服务端有个局限性，当A客户端连接上以后，被服务端获取到 服务端执行具体流程。这时，B客户连接，只有等待A客户执行完
 * 因为服务端暂时还没有处理完A客户端的请求， 还没有循环回来执行下次accept方法 所以，暂时获取不到B客户端对象
 * 
 * 为了可以让多个客户端同时并发访问服务端 那么服务端最好就是将每个客户端封装到一个单独的线程中 这样，就可以同时处理多个客户端的请求
 * 
 * class PictureServer { public static void main(String[] args) throws Exception
 * { ServerSocket ss = new ServerSocket(4456); while(true) { Socket s =
 * ss.accept(); //获取客户端IP并显示连接成功 String ip =
 * s.getInetAddress().getHostAddress(); System.out.println(ip+"......连接成功");
 * 
 * //字节流读取socket输入流 BufferedInputStream bis = new
 * BufferedInputStream(s.getInputStream());
 * 
 * //将字节数据写入到一个新的图片文件中 PrintStream out = new PrintStream(new
 * FileOutputStream("e:/mypic.jpg"),true);
 * 
 * byte[] buf = new byte[1024]; int len = 0; while((len=bis.read(buf))!=-1) {
 * out.write(buf,0,len); }
 * 
 * //反馈信息给客户端 PrintWriter pw = new PrintWriter(s.getOutputStream(),true);
 * pw.println("图片上传成功");
 * 
 * s.close(); out.close(); } ss.close(); } }
 */

