package catchpicture;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import spider.DataConfig;

/**
 * 从对列中读出图片地址
 * @author hp
 *
 */
public class WritePictureConsumer implements Runnable {

	@Override
	public void run() {
		while (true) {
			doExecute();
		}
	}

	private void doExecute() {
		String picUrl = null;

		try {
			picUrl = DataProvider.PIC_RECEIVE_QUEUE.take();
			System.out.println("write image: " + picUrl + " by the " + Thread.currentThread().getName());
			
			
			URL url = new URL(picUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// 设置请求方式，方式被屏蔽
			conn.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

			// 设置请求方式为"GET"
			conn.setRequestMethod("GET");
			// 超时响应时间为5秒
			conn.setConnectTimeout(100 * 1000);
			// 通过输入流获取图片数据
			InputStream inStream = conn.getInputStream();

			// 得到图片的二进制数据，以二进制封装得到数据，具有通用性
			byte[] data = readInputStream(inStream);
			// new一个文件对象用来保存图片，默认保存当前工程根目录
			long time = System.currentTimeMillis();

			File imageFile = new File(DataConfig.picLocation + String.valueOf(time) + ".jpg");
			// 创建输出流
			FileOutputStream outStream = new FileOutputStream(imageFile);
			// 写入数据
			outStream.write(data);
			// 关闭输出流
			outStream.close();
			
			

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static byte[] readInputStream(InputStream inStream)
			throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		// 创建一个Buffer字符串
		byte[] buffer = new byte[1024];
		// 每次读取的字符串长度，如果为-1，代表全部读取完毕
		int len = 0;
		// 使用一个输入流从buffer里把数据读取出来
		while ((len = inStream.read(buffer)) != -1) {
			// 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
			outStream.write(buffer, 0, len);
		}
		// 关闭输入流
		inStream.close();
		// 把outStream里的数据写入内存
		return outStream.toByteArray();
	}
}
