package core.findFileByKey;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FindFileByKey {

	public static void main(String[] args) {
		
		File file = new File("D:\\work\\product\\mng-payment3.5.0");
		List<String> resultFileName = ergodic(file, new ArrayList<>(), "java");
		System.out.println(resultFileName.size());
		try {
			int count=0;
			for (String string : resultFileName) {
				File javaFile =new File(string);
				BufferedReader reader = new BufferedReader(new FileReader(javaFile));
				String content=null;
				while ((content = reader.readLine()) != null) {
					if (content.indexOf("jedisTemplate")!=-1&&content.indexOf("Resource")==-1&&content.indexOf("private")==-1) {
						System.out.println(string);
						System.out.println(content.trim());
						System.out.println("-----------");
						count++;
					}
				}
			}
			System.out.println(count);
		} catch (Exception e) {
		}
	}

	private static List<String> ergodic(File file, List<String> resultFileName, String fileType) {
		File[] files = file.listFiles();
		if (files == null)
			return resultFileName;// 判断目录下是不是空的
		for (File f : files) {
			if (f.isDirectory()) {// 判断是否文件夹
				// resultFileName.add(f.getPath());
				ergodic(f, resultFileName, fileType);// 调用自身,查找子目录
			} else if (f.getPath().endsWith(fileType)) {
				resultFileName.add(f.getPath());
			}
		}
		return resultFileName;
	}

}
