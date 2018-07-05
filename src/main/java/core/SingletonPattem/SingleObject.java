package core.SingletonPattem;

public class SingleObject {
	
	private static  SingleObject instance;
	
	private SingleObject(){};
	
	public static synchronized SingleObject getInstance(){
		if (instance == null) { 
			 instance=new SingleObject();
		}
		return instance;
	}
	
	public void showMessage(){
		System.out.println("Hello World!");
	}
	
}
