package serializable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * 序列化,反序列化
 * 
 * @author hp
 * 
 */
public class ObjectSaver {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		// 序列化写入文件
		/**
		 * 1.创建对象输出流 
		 * 2.通过对象输出流writeObject()方法写入对象
		 */
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
				"D:\\obj.obj"));
		String obj1 = "test for  object";
		Date obj2 = new Date();
		Customer obj3 = new Customer("jack", 21);
		out.writeObject(obj1);
		out.writeObject(obj2);
		out.writeObject(obj3);
		out.writeInt(10);
		out.close();
		
		
		//反序列化
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("D:\\obj.obj"));		
		String obj11 = (String)in.readObject();
		System.out.println("obj11:" + obj11);
		System.out.println("obj11==obj1 " + (obj11 == obj1));
		
		Date obj22 = (Date)in.readObject();
		System.out.println("obj22:" + obj22);
		System.out.println("obj22==obj2 " + (obj22 == obj2));
		
		Customer obj33 = (Customer)in.readObject();
		System.out.println("obj33:" + obj33);
		System.out.println("obj33==obj3 " + (obj33 == obj3));
		
		int var = in.readInt();
		System.out.println("var:" + var);
		
		in.close();
	}

}

class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private int age;
	
	public Customer(String name, int age){
		this.name = name;
		this.age = age;
	}
	
	public String toString(){
		return "name:" + name +",age:" + age;
	}
}
