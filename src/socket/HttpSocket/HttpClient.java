package socket.HttpSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class HttpClient {
	
	private Socket socket;
	
	public HttpClient(String host, int port) throws UnknownHostException, IOException{
		this.socket = new Socket(host, port);
	}

	public void send() throws InterruptedException{
		try{
			OutputStream out = socket.getOutputStream();
			
			//发送http请求
			StringBuffer sb = new StringBuffer("GET index.html HTTP/1.1\r\n");
			sb.append("Accept: */*\r\n");
			sb.append("Accept-language: gzip deflate\r\n");
			sb.append("User-Agent: HTTPClient\r\n");
			sb.append("Host: localhost:7788\r\n");
			sb.append("Connection: Keep-Alive\r\n\r\n");			
			out.write(sb.toString().getBytes());
			
			Thread.sleep(2000);
			
			//接收响应结果
			InputStream in = socket.getInputStream();
			int size = in.available();
			
			int max_zise = 4;
			byte[] buffer = new byte[max_zise];
			
			int count = 0;
			while((count = in.read(buffer)) != -1){
				System.out.println(buffer[0] + " " +  buffer[1]);
			}
			
			in.read(buffer);
			System.out.println(new String(buffer));
			
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		try {
			new HttpClient("localhost", 7788).send();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
