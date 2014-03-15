package sohu.thread.score;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Main {

	public static void main(String[] args) throws Exception {
		String scoreFile = "D:\\score.txt";
		String info = "D:\\info.txt";

		File f1 = new File(scoreFile);
		File f2 = new File(info);

		/*
		 * BufferedReader bf = new BufferedReader(new InputStreamReader(new
		 * FileInputStream(f2), "GBK")); String line = null; while( (line =
		 * bf.readLine()) != null){ //System.out.println(line); String[]
		 * lineArray = line.split(" "); //Score s = new Score();
		 * System.out.println(lineArray[0] + " " + lineArray[1] + " " +
		 * lineArray[2]); //Score s = new Score(Integer.decode(lineArray[0]),
		 * lineArray[1], Integer.parseInt(lineArray[2])); }
		 * 
		 * bf.close();
		 */

		/*
		 * Info info1 = new Info(1001,"计算机学院 ","网络技术","陈悦"); Info info3 = new
		 * Info(1003,"计算机学院 ","网络技术","陈悦2"); Info info2 = new
		 * Info(1002,"服装学院  ","服装设计 ","张明"); List<Info> list = new
		 * ArrayList<Info>(); list.add(info1); list.add(info2); list.add(info3);
		 * Collections.sort(list);
		 */
		// 学院 /学号/科目/分数
		Map<String, HashMap<String, HashMap<String, Integer>>> map = new HashMap<String, HashMap<String, HashMap<String, Integer>>>();
		BufferedReader bf = new BufferedReader(new InputStreamReader(
				new FileInputStream(f2), "GBK"));
		String line = null;
		while ((line = bf.readLine()) != null) {
			String[] lineArray = line.split(" ");
			//System.out.println(lineArray[0] + " " + lineArray[1] + " "
			//		+ lineArray[2] + " " + lineArray[3]);
			String college = lineArray[1];// 学院
			String id = lineArray[0];// 学号
			if (null == map.get(college)) {// 为空第一次加入
				HashMap<String, HashMap<String, Integer>> value1 = new HashMap<String, HashMap<String, Integer>>();
				
				value1.put(id, getMap(id, f1));// 科目和分数不放进去
				map.put(college, value1);

			} else {// 键不为空
				HashMap<String, HashMap<String, Integer>> mbyCollege = map
						.get(college);
				mbyCollege.put(id, getMap(id, f1));
			}

		}
		bf.close();
		System.out.println(map);
		
		Iterator<Entry<String, HashMap<String, HashMap<String, Integer>>>> it = map.entrySet().iterator();
		

	}

	static HashMap<String, Integer> getMap(String studentId, File f1)
			throws Exception {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		// 查找科目和分数
		BufferedReader bf1 = new BufferedReader(new InputStreamReader(
				new FileInputStream(f1), "GBK"));
		String line1 = null;
		while ((line1 = bf1.readLine()) != null) {
			String[] lineArray = line1.split(" ");
			//System.out.println(lineArray[0] + " " + lineArray[1] + " " + lineArray[2]);
			String id = lineArray[0];
			String subject = lineArray[1];
			int score = Integer.parseInt(lineArray[2]);
			if(studentId.equals(id)){
				if(map.get(id) == null){
					map.put(subject, score);
				}
			}
			
		}
		bf1.close();
		
		return map;
	}

}
class CountScore{
	public String sid;
	
	public int sscore;
}
