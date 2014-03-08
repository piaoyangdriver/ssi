package test.string;

import java.io.UnsupportedEncodingException;

public class EncodeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 将一个中文汉字赋值给一个char变量
		/*
		 * char a = '中'; char b = '文'; char c = '测'; char d = '试'; char e = '成';
		 * char f = '功'; System.out.print(a + b + c + d + e + f);
		 */
		String en = "A";
		String ch = "人";

		// 计算一个英文字母在各种编码下的字节数
		System.out.println("英文字母：" + en);
		EncodeTest.printByteLength(en, "GB2312");
		EncodeTest.printByteLength(en, "GBK");
		EncodeTest.printByteLength(en, "GB18030");
		EncodeTest.printByteLength(en, "ISO-8859-1");
		EncodeTest.printByteLength(en, "UTF-8");
		EncodeTest.printByteLength(en, "UTF-16");
		EncodeTest.printByteLength(en, "UTF-16BE");
		EncodeTest.printByteLength(en, "UTF-16LE");

		System.out.println();

		// 计算一个中文汉字在各种编码下的字节数
		System.out.println("中文汉字：" + ch);
		EncodeTest.printByteLength(ch, "GB2312");
		EncodeTest.printByteLength(ch, "GBK");
		EncodeTest.printByteLength(ch, "GB18030");
		EncodeTest.printByteLength(ch, "ISO-8859-1");
		EncodeTest.printByteLength(ch, "UTF-8");
		EncodeTest.printByteLength(ch, "UTF-16");
		EncodeTest.printByteLength(ch, "UTF-16BE");
		EncodeTest.printByteLength(ch, "UTF-16LE");
		
	}

	/**
	 * 打印字符串在指定编码下的字节数和编码名称到控制台
	 * 
	 * @param s
	 * @param encodingName
	 */
	public static void printByteLength(String s, String encodingName) {
		System.out.print("字节数：");
		try {
			System.out.print(s.getBytes(encodingName).length);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(";编码：" + encodingName);
	}

	static boolean isChineseChar(char c) throws UnsupportedEncodingException {
		return String.valueOf(c).getBytes("GBK").length > 1;
	}

	public static String substring(String orignal, int count)
			throws UnsupportedEncodingException {
		if (orignal != null && !"".equals(orignal)) {
			// 将原始字符串转换为GBK编码格式
			orignal = new String(orignal.getBytes(), "GBK");
			if (count > 0 && count < orignal.getBytes("GBK").length) {
				StringBuffer buff = new StringBuffer();
				char c;
				for (int i = 0; i < count; i++) {
					c = orignal.charAt(i);
					buff.append(c);
					if (isChineseChar(c)) {
						// 遇到中文汉字，截取字节总数减1
						--count;
					}
				}
				return buff.toString();
			}
		}
		return orignal;
	}
}
