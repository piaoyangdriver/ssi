package catchpicture;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import spider.DataConfig;

/*
 * 主程序入口
 */
public class Main {
	
	public static void main(String[] args){
		
		String hostName = DataConfig.hostName;
		String loginUrl = DataConfig.mainPage;
		
		//写入图片网页地址
		for (int i = 3; i < 4; i++) {
			//new Thread(new WriterPageProducer(hostName, loginUrl + i)).start();
			Executor ex = Executors.newFixedThreadPool(5);
			ex.execute(new WriterPageProducer(hostName, loginUrl + i));
		}
		
		//从图片网页地址队列中读取，然后写入图片地址对列		
		new Thread(new WriterPicUrlProducer()).start();
		//Executor ex2 = Executors.newFixedThreadPool(3);
		//ex2.execute(new WriterPicUrlProducer());
		
		//生成图片
		 new Thread(new WritePictureConsumer()).start();
		//Executor ex3 = Executors.newFixedThreadPool(3);
		//ex3.execute(new WriterPicUrlProducer());
	}
}
