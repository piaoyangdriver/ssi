package catchpicture;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 
 * @author hp
 *
 */
public class DataProvider {
	
	/**
	 * 接收图片网页地址
	 */
	public static BlockingQueue<String> PAGE_RECEIVE_QUEUE = new LinkedBlockingQueue<String>();
	
	
	/**
	 * 接收图片网页中的picture地址
	 */
	public static BlockingQueue<String> PIC_RECEIVE_QUEUE = new LinkedBlockingQueue<String>();

}
