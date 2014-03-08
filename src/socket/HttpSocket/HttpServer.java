package socket.HttpSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

	private ServerSocket server;

	public HttpServer(int port) throws IOException {
		this.server = new ServerSocket(port);
	}

	public void service() throws InterruptedException {
		Socket socket = null;
		try {
			while (true) {
				socket = server.accept();
				System.out.println("new connection stars:"
						+ socket.getLocalPort() + " ");

				// 读取http请求信息
				InputStream in = socket.getInputStream();
				int size = in.available();
				byte[] buffer = new byte[size];
				in.read(buffer);

				String request = new String(buffer);
				System.out.println("request size is: " + request.length()
						+ ", in size is :" + size);
				System.out.println(request);

				// 解析http请求
				String firstLine = request
						.substring(0, request.indexOf("\r\n"));
				String[] parts = firstLine.split(" ");
				String uri = parts[1];
				String contentType = null;
				if (uri.indexOf("html") != -1 || uri.indexOf("htm") != -1) {
					contentType = "text/html";
				}

				// 发送响应
				String responseFirstLine = "HTTP/1.1 200 OK\r\n";
				String responseContent = "Content-Type:" + contentType
						+ "\r\n\r\n";
				String text = "wo kao";

				OutputStream out = socket.getOutputStream();
				out.write(responseFirstLine.getBytes());
				out.write(responseContent.getBytes());
				out.write(text.getBytes());

				//Thread.sleep(1000);
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException,
			IOException {
		new HttpServer(7788).service();
	}

}
