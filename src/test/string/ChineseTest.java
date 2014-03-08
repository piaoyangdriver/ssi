package test.string;

import java.io.UnsupportedEncodingException;

public class ChineseTest {
	
	public static void main(String[] args) throws UnsupportedEncodingException{
		String s = "I am 君山";
		
		byte[] sbyte = s.getBytes("UTF-8");		
		
		System.out.println("s.length() : " + s.length());
		System.out.println("b.length: " + sbyte.length);
		
		for(byte b : sbyte){
			System.out.print(b + " ");
		}
		
	}

}
