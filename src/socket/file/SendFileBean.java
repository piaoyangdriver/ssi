package socket.file;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

public class SendFileBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String fileName;
	
	private File file;
	
	private byte[] fileByte;

	public String getFileName() {
		if(file != null){
			this.fileName = file.getName();
		}
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	public byte[] getFileByte() throws IOException {
		FileInputStream in = new FileInputStream(file);  
        ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
        byte[] b = new byte[1024];
        int n = 0;
        while((n = in.read(b)) != -1){
        	out.write(b, 0 ,n);
        }
        in.close();  
        out.close();  
        fileByte = out.toByteArray();        
		return fileByte;
	}

	public void setFileByte(byte[] fileByte) {
		this.fileByte = fileByte;
	}

}
