package socket.server2;

import java.util.concurrent.LinkedBlockingQueue;

import socket.server1.SendBean;

public class DataProvider2 {

	/**
	 * RECEIVE_QUEUE
	 */
	public static LinkedBlockingQueue<SendBean> SENDBEAN_DATA = new LinkedBlockingQueue<SendBean>();
	
	public static int SERVER2_PORT = 58000;

	//Send_Queue
	public static LinkedBlockingQueue<SendBean> SEND_SERVER1_BEAN = new LinkedBlockingQueue<SendBean>();
}
