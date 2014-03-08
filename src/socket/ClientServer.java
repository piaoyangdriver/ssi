package socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientServer {

	private int port = 7788;

	private String host = "localhost";

	private Socket socket;

	public ClientServer() throws UnknownHostException, IOException {
		this.socket = new Socket(host, port);
	}

	public void talk() {
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			
			BufferedReader localreader = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			
			String msg = null;
			while((msg = localreader.readLine()) != null){
				pw.write(msg);
				System.out.println(bf.readLine());
				if(msg.equals("bye")){
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void main(String args[]) throws IOException {
		new ClientServer().talk();
	}

}
