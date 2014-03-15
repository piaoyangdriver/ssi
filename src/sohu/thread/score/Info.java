package sohu.thread.score;

import java.util.List;

public class Info implements Comparable {
	
	private int id;
	
	private String college;
	
	private String major;
	
	private String name;
	
	private int count;
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private List<Score> scoreList;

	public Info(int id, String college, String major, String name) {
		super();
		this.id = id;
		this.college = college;
		this.major = major;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public List<Score> getScoreList() {
		return scoreList;
	}

	public void setScoreList(List<Score> scoreList) {
		this.scoreList = scoreList;
	}

	@Override
	public int compareTo(Object o) {
		if( o == null){
			return -1;
		}
		
		return 0;
	}

}
