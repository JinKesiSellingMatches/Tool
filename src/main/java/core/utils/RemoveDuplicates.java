package core.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RemoveDuplicates {

	public static File getFile(String path) {
		File file = new File(path);
		return file;
	}

	public static List<String> readfile(String path) throws IOException {
		File file = getFile(path);
		BufferedReader reader = null;
		List<String> list = new ArrayList<>();
		if (file != null) {
			try {
				reader = new BufferedReader(new FileReader(file));
				String content = null;
				while ((content = reader.readLine()) != null) {
					if (content.length() != 0) {
						list.add(content);
						// System.out.println(content);
					}

				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public static List<String> remove(List<String> contents) {
		List<String> result = new ArrayList<>();
		for (String string : contents) {
			if (checkDuplicate(result, string)) {
				result.add(string);
			}
		}
		return result;
	}

	public static boolean checkDuplicate(List<String> result, String content) {

		boolean flag = true;
		for (String string : result) {
			if (string.equals(content)) {
				flag = false;
			}
		}
		return flag;
	}

	public static List<String> getMissingConfig(List<String> zhCns, List<String> ens) {
		List<String> result = new ArrayList<>();
		for (String string : zhCns) {
			String zhCnKey = string.split("=")[0];
			String firstZhCnKey = zhCnKey.substring(0, 1);
			if (!"<".equals(firstZhCnKey) && !"//".equals(firstZhCnKey) && !"#".equals(firstZhCnKey)) {
				int count = 0;
				for (String en : ens) {
					String enKey = en.split("=")[0];
					if (zhCnKey.equals(enKey)) {
						count++;
					}
				}
				if (count == 0) {
					result.add(zhCnKey);
				}
			}
		}
		return result;
	}

	public static void main(String[] args) throws IOException {

		String path = "D:/work/product/mng-payment3.5.0/cust-mng/mng-front-admin/src/main/resources/messages/message_zh_CN.properties";
		String pathEn = "D:/work/product/mng-payment3.5.0/cust-mng/mng-front-admin/src/main/resources/messages/message_en.properties";
		List<String> list = readfile(path);
		List<String> listEm = readfile(pathEn);
		List<String> missing = getMissingConfig(list, listEm);
		System.out.println(missing.size());
		for (String string : missing) {
			System.out.println(string);
		}
		// List<String> result=remove(list);
		// System.out.println(result.size());
		// for (String string : result) {
		// System.out.println(string);
		// }

	}

}
