package sohu.thread;

public class Souhu {
	
	public static void main(String[] args) throws Exception {
		/*final Object obj = new Object();
		
		Thread t1 = new Thread(){
			public void run(){
				synchronized(obj){
					try {
						obj.wait();
						System.out.println("dddd");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		t1.start();
		
		Thread.sleep(1000);
		
		Thread t2 = new Thread(){
			public void run(){
				synchronized(obj){
					obj.notifyAll();
					System.out.println("notify");
				}
			}
		};
		
		t2.start();*/
		
		/*try{
			return;
		}finally{
			System.out.println("Finally");
		}*/
		
		//不能为long double float 
		/*String i = "0";
		switch(i){
		case "0" :
			break;
		default :
			break;
		}*/
		
		//System.out.println(take());
		
		System.out.println(10.4 + " 4.2");
		
		int i = Integer.decode("10");
		i = new Integer("10");
		try{
			i = Integer.getInteger("10");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//.sleep(3000);
		System.out.println("1");
	}
	
	/*public static int take() throws Exception {
		try{
			System.out.println(1/0);
		}catch(Exception e){
			//e.printStackTrace();
			throw e;
		}finally{
			return 1;
		}
	}*/

	
	
}
