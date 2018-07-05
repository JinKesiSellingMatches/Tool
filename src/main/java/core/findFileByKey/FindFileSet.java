package core.findFileByKey;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FindFileSet {

	public static void main(String[] args) {

		File file = new File("D:\\work\\product\\mng-payment3.5.0\\gopayAdapter\\infrastructure\\shared\\src\\main\\java\\com\\eigpay\\gopayadapter\\infra\\shared\\util");
		List<String> resultFileName = ergodic(file, new ArrayList<>(), "java");
		System.out.println(resultFileName.size());
		try {
			int count = 0;
			for (String string : resultFileName) {
				File javaFile = new File(string);
				String previousRow = null;
				BufferedReader reader = new BufferedReader(new FileReader(javaFile));
				String content = null;
				List<String> setlist = new ArrayList<String>();
				List<String> setExcludelist = new ArrayList<String>();
				while ((content = reader.readLine()) != null) {
					String line = content.trim();
					int star = line.indexOf("set");
					int end = line.lastIndexOf("(");
					if (star != -1 && end != -1 && end > star) {
						line = line.substring(line.indexOf("set"), line.lastIndexOf("("));
						//System.out.println(previousRow);
						if (previousRow != null && previousRow.equals("@JsonProperty")) {
							setExcludelist.add(line);
						}else{
							setlist.add(line);
						}
					}
					int co = 0;
					for (String string2 : setlist) {
						if (string2.equals(line)) {
							co++;
						}
					}
					if (co > 1) {
						boolean flag=true;
						for (String string2 : setExcludelist) {
							if (line.equals(string2)) {
								flag=false;
							}
						}
						if (flag) {
							System.out.println(string);
							System.out.println(line);
						}
					}
					previousRow = line;
				}
			}
			System.out.println(count);
		} catch (Exception e) {
			System.out.println(e);
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
