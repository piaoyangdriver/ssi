package socket.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectTester {

	public Object copy(Object object) throws Exception {
		Class classType = object.getClass();
		System.out.println("Class:" + classType.getName());

		// 通过默认构造方法创建一个新对象
		Object objectCopy = classType.getConstructor(new Class[] {})
				.newInstance(new Object[] {});
		Field fields[] = classType.getDeclaredFields();
		for (Field field : fields) {
			String filedName = field.getName();
			String firstLetter = filedName.substring(0, 1).toUpperCase();

			String getMethodName = "get" + firstLetter + filedName.substring(1);
			String setMethodName = "set" + firstLetter + filedName.substring(1);

			Method getMethod = classType.getMethod(getMethodName,
					new Class[] {});
			Method setMethod = classType.getMethod(setMethodName,
					new Class[] { field.getType() });

			/**
			 * Method 类的invoke方法用于动态执行一个对象的特定方法
			 */
			// 调用源对象的getXXX()方法
			Object value = getMethod.invoke(object, new Object[] {});
			System.out.println(filedName + ":" + value);

			// 调用复制对象的setXXX()方法
			setMethod.invoke(objectCopy, new Object[] { value });

		}
		return objectCopy;
	}

	public static void main(String[] args) throws Exception {
		Customer customer = new Customer("tom", 21);
		customer.setId(1);

		Customer copy = (Customer) new ReflectTester().copy(customer);
		System.out.println("copy information:" + copy.getName() + " "
				+ copy.getAge());

	}

}

class Customer {
	private long id;

	private String name;

	private int age;

	public Customer() {
	};

	public Customer(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}