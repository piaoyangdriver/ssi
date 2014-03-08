package socket.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

//确定客户端需要执行的代码，封装到多线程的run方法中，
//就可以实现服务端的多线程,客户端也就能实现互不影响的访问服务端。
class ServerThread implements Runnable {
	private Socket s;

	ServerThread(Socket s) {
		this.s = s;
	}

	public void run() {
		BufferedOutputStream stream = null;
		try {
			// 获取客户端IP并显示连接成功
			String ip = s.getInetAddress().getHostAddress();
			System.out.println(ip + "......连接成功");

			ObjectInputStream objin = new ObjectInputStream(s.getInputStream());
			SendFileBean send = (SendFileBean) objin.readObject();
			byte[] b = send.getFileByte();

			File file = new File("D:\\AAA.jpg");
			FileOutputStream fstream = new FileOutputStream(file);
			stream = new BufferedOutputStream(fstream);
			stream.write(b);
		} catch (Exception e) {
			new RuntimeException("上传失败");
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					// log.error("helper:get file from byte process error!");
					e.printStackTrace();
				}
			}
		}
	}
}
