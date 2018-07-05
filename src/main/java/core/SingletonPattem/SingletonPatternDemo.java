package core.SingletonPattem;


public class SingletonPatternDemo {

	public static void main(String[] args) {
		// SingleObject singleObject = new SingleObject();
		Runnable runnable = new Runnable() {
		@Override
		public void run() {
			SingleObject object = SingleObject.getInstance();
				object.showMessage();
				System.out.println(object);
			}
		};
		
		for (int i = 0; i < 10; i++) {
			new Thread(runnable).start();
		}
	}
}
