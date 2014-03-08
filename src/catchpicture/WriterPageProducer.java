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

import spider.DataConfig;

/**
 * 写入到图片页面地址队列中
 * @author hp
 *
 */
@SuppressWarnings("deprecation")
public class WriterPageProducer implements Runnable {

	private String hostName;
	
	private String loginUrl;
	
	
	public WriterPageProducer(String hostName, String loginUrl) {
		super();
		this.hostName = hostName;
		this.loginUrl = loginUrl;
	}

	@Override
	public void run() {
		doExecute();
	}
	
	private void doExecute(){
		HttpClient httpClient;
		HttpGet httpGet = new HttpGet(hostName + loginUrl);
		HttpResponse response;
		try {
			httpClient = new DefaultHttpClient();
			response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream in = entity.getContent();
				BufferedReader br = new BufferedReader(new InputStreamReader(in,"GBK"));
				String s;
				while ((s = br.readLine()) != null) {
					if(s.contains(DataConfig.mainPageStartFlag) && s.contains(DataConfig.mainPageEndFlag)){
						s = s.substring(s.indexOf(DataConfig.mainPageStartFlag) + DataConfig.mainPageStartFlag.length(), s.indexOf(DataConfig.mainPageEndFlag));
						if(!s.contains("index.php\">")){
							//System.out.println(s);
							DataProvider.PAGE_RECEIVE_QUEUE.put(hostName + s);
						}
					}
				}
			}
			//httpClient.getConnectionManager().shutdown();
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
