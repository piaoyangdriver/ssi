package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	private int port = 7788;;

	private ServerSocket server;

	public EchoServer() throws IOException {
		this.server = new ServerSocket(port);
	}

	public void service() {

		while (true) {
			Socket socket = null;
			try {
				socket = server.accept();
				System.out.println("New connection accepted "
						+ socket.getInetAddress() + ":" + socket.getPort());
				InputStream input = socket.getInputStream();
				BufferedReader bf = new BufferedReader(new InputStreamReader(
						input));

				PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
				
				String msg = null;
				while ((msg = bf.readLine()) != null) {
					System.out.println(msg);
					pw.println("echo:" + msg);
					if (msg.equals("bye")) {
						break;
					}
				}
				//InetSocketAddress
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}
	}

	public static void main(String args[]) throws IOException {
		new EchoServer().service();
	}

}
