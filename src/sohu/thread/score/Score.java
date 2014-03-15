package sohu.thread.score;

public class Score {
	
	private int id;
	
	private String subject;
	
	private int value;

	public Score(int id, String subject, int value) {
		super();
		this.id = id;
		this.subject = subject;
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
