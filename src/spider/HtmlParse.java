package spider;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class HtmlParse {

	public static void main(String[] args) throws Exception {
		try {
/*
			String path_url = "http://184.154.178.130/thread0806.php?fid=8&search=&type=1&page=5";
			URL url = new URL(path_url);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(100 * 1000);
			Parser myParser = new Parser(conn);
			myParser.setEncoding("GBK");*/
			
			
			String context = "";
			Parser myParser = new Parser(context);
			String filterStr = "a";// 这里析取得是标签为table的元素
			
			NodeFilter filter = new TagNameFilter(filterStr);// 过滤这个标签
			NodeList nodeList = myParser.extractAllNodesThatMatch(filter);// 抽取所有table列表
			for (int i = 0; i < nodeList.size(); i++) {
				//TableTag tabletag = (TableTag) nodeList.elementAt(i);
				//nodeList.elementAt(i);
				LinkTag tag = (LinkTag)nodeList.elementAt(i);
				System.out.println(tag.extractLink());// 打印出来
			}

		} catch (ParserException e) {
			e.printStackTrace();
		}
	}
}
