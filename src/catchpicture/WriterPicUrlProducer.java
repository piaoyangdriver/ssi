package catchpicture;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;

import spider.DataConfig;

/**
 * 从图片网页地址中读取地址
 * 讲里面图片的url写入到图片队列中
 * 
 * @author hp
 *
 */
@SuppressWarnings("deprecation")
public class WriterPicUrlProducer implements Runnable {
	
	@Override
	public void run() {
		while(true){
			doExecute();
		}
	}

	private void doExecute() {
		String imgPageUrl;
		HttpClient httpClient;
		try {
			imgPageUrl = DataProvider.PAGE_RECEIVE_QUEUE.take();
			//System.out.println(imgPageUrl);			
			httpClient = new DefaultHttpClient();
			httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,  60000);//连接时间20s
			httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,  60000*2);//			
			HttpGet httpGet = new HttpGet(imgPageUrl);
			HttpResponse response = httpClient.execute(httpGet);
			
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream in = entity.getContent();
				BufferedReader br = new BufferedReader(new InputStreamReader(in,  "GBK"));
				String s;
				while ((s = br.readLine()) != null) {
					String[] line = s.split("<br>");
					for(String temp : line){
						if(temp.contains(DataConfig.imagePageStartFlag) && temp.contains(DataConfig.imagePageEndFlag)){
							String temp2 = temp.substring(temp.indexOf(DataConfig.imagePageStartFlag) + DataConfig.imagePageStartFlag.length(), temp.indexOf(DataConfig.imagePageEndFlag));
							DataProvider.PIC_RECEIVE_QUEUE.put(temp2);
						}
					}
				}
			}
			//httpClient.getConnectionManager().shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
