package core.SingletonPattem.Thread;

public class Count {

	public void count() {
		int num=0;
		for (int i = 0; i <= 10; i++) {
			num += i;
		}

		System.out.println(Thread.currentThread().getName() + "-" + num);
	}

}
