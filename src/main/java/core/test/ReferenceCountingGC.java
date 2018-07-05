package core.test;

import java.lang.ref.SoftReference;

public class ReferenceCountingGC {
	
	public Object instance=null;
	
	private static final int _1MB=1024*1024;
	
	private byte[] bigSize=new byte[2*_1MB];
	
	public static void testGC(){
		
		ReferenceCountingGC objA=new ReferenceCountingGC();
		
		ReferenceCountingGC objB=new ReferenceCountingGC();
		
		objA.instance=objB;
		
		objB.instance=objA;
		
		objA=null;
		
		objB=null;
		
		System.gc();
	}
	
	public static void main(String[] args) {
		//testGC();
		
		ReferenceCountingGC aRef = new  ReferenceCountingGC();
		System.out.println(aRef);
		aRef=null;
		System.gc();
		SoftReference aSoftRef=new SoftReference(aRef);
		System.out.println(aSoftRef.get());
	}

}
