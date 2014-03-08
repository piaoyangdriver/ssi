package socket.server2;

/**
 * 程序入口
 * @author hp
 *
 */
public class MainServer2 {
	
	public static void main(String[] args) {
		new Thread(new Server2ProducerThread()).start();
		new Thread(new Server2ConsumerThread()).start();
	}

}
