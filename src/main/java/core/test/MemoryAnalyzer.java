package core.test;

import java.util.ArrayList;
import java.util.List;

public class MemoryAnalyzer {

	static class Analyer{
		int A;

		public int getA() {
			return A;
		}

		public void setA(int a) {
			A = a;
		}
	};
	
	public static void main(String[] args) {
		List<Analyer> analyers=new ArrayList<MemoryAnalyzer.Analyer>();
		int count=0;
		while(true){
			Analyer analyer=new Analyer();
			analyer.setA(count++);
			System.out.println(count);
			analyers.add(analyer);
		}
		
	}
}
