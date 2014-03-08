package socket.server2;

import socket.server1.SendBean;

public class Server2ConsumerThread implements Runnable {

	@Override
	public void run() {
		while(true){
			printSendBean();
		}
	}
	
	private void printSendBean(){
		try {
			SendBean sendBean = DataProvider2.SENDBEAN_DATA.take();
			System.out.println("start to print the sendBean :" + sendBean.getName());
			sendBean.setLeve(1);
			sendBean.setFlag(true);
			
			DataProvider2.SEND_SERVER1_BEAN.put(sendBean);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
