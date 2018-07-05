package core.serializable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class TestMain {
	
	public static void main(String[] args) {
//		TestVo obj=new TestVo();
//		obj.setTest("A");
//		List<TestVo> testVos=new ArrayList<>();
//		testVos.add(obj);
		String obj=null;
//		Map<String, Object> obj=new HashMap<String,Object>();
//		obj.put("666", "7888");
		byte[] ss=serialize(obj);
		System.out.println(ss);
		Object object=deserialize(ss);
		System.err.println(object);
	}
	
	//序列化为byte[]
	public static byte[] serialize(Object object) {
	    ObjectOutputStream oos = null;
	    ByteArrayOutputStream bos = null;
	    try {
	        bos = new ByteArrayOutputStream();
	        oos = new ObjectOutputStream(bos);
	        oos.writeObject(object);
	        byte[] b = bos.toByteArray();
	        return b;
	    } catch (IOException e) {
	        System.out.println("序列化失败 Exception:" + e.toString());
	        return null;
	    } finally {
	        try {
	            if (oos != null) {
	                oos.close();
	            }
	            if (bos != null) {
	                bos.close();
	            }
	        } catch (IOException ex) {
	            System.out.println("io could not close:" + ex.toString());
	        }
	    }
	}
	
	
	//反序列化为Object
	public static Object deserialize(byte[] bytes) {
	    ByteArrayInputStream bais = null;
	    try {
	        // 反序列化
	        bais = new ByteArrayInputStream(bytes);
	        ObjectInputStream ois = new ObjectInputStream(bais);
	        return ois.readObject();
	    } catch (IOException | ClassNotFoundException e) {
	        System.out.println("bytes Could not deserialize:" + e.toString());
	        return null;
	    } finally {
	        try {
	            if (bais != null) {
	                bais.close();
	            }
	        } catch (IOException ex) {
	            System.out.println("LogManage Could not serialize:" + ex.toString());
	        }
	    }
	}

}
