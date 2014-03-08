package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	Socket client;
	PrintWriter pw;

	public Client() throws UnknownHostException, IOException {
		client = new Socket("127.0.0.1", 7777);
		pw = new PrintWriter(client.getOutputStream(),true);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String msg = null;
		while((msg = br.readLine()) != null){
			pw.write(msg);
		}
		
		pw.close();
		br.close();
	}

	public static void main(String[] args) {
		try {
			new Client();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
