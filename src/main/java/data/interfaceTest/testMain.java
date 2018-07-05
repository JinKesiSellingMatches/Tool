package data.interfaceTest;

import javax.annotation.Resource;

public class testMain {
	
	public static void main(String[] args) {
		testInfImpl testInf=new testInfImpl();
		System.out.println(testInf.test().test2());
	}
}
