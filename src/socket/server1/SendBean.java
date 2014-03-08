package socket.server1;

import java.io.Serializable;

public class SendBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6644448658458191290L;

	private String name;
	
	private int leve;
	
	private boolean flag;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLeve() {
		return leve;
	}

	public void setLeve(int leve) {
		this.leve = leve;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}
