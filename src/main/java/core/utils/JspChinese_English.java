package core.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JspChinese_English {

	private static int count = 0;

	public static void main(String[] args) throws Exception {
		String path = "D:/work/product/mng-payment3.5.0/mng-payment-front/mng-payment-front-admin/src/main/webapp/WEB-INF/pages";
		String pathProperties = "D:/work/product/mng-payment3.5.0/mng-payment-front/mng-payment-front-admin/src/main/resources/messages/message_zh_CN.properties";
		String fileType = "*.jsp";
		List fileList = new ArrayList<>();
		List<String> ch_enlist = new ArrayList<>();
		fileList = findJsp(path, fileType);
		for (Object string : fileList) {
			File file = (File) string;
			ch_enlist.addAll(readFile(file));
			// System.out.println(file);
		}
		if (count > 0) {
			List<String> propertiesKey = readProperties(pathProperties);
			System.out.println(checkExist(ch_enlist, propertiesKey).size());
		}
	}

	public static List<String> checkExist(List<String> ch_enlist, List<String> propertiesKey) {

		List<String> notExist=new ArrayList<>();
		for (String cn_en : ch_enlist) {
			int count = 0;
			for (String string : propertiesKey) {
				String[] value = string.split("=");
				if (value != null && value.length == 2) {
					if (cn_en.indexOf(value[1]) != -1) {
						count++;
					}
				}
			}
			if (count == 0) {
				notExist.add(cn_en);
				System.out.println(cn_en);
			}
		}
		return notExist;
	}

	public static List<String> readProperties(String path) throws Exception {

		List<String> result = new ArrayList<>();
		File file = RemoveDuplicates.getFile(path);
		BufferedReader reader = null;
		if (file != null) {
			try {
				reader = new BufferedReader(new FileReader(file));
				String content = null;
				while ((content = reader.readLine()) != null) {
					if (content.length() != 0) {
						result.add(content);
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static List<String> readFile(File file) throws Exception {

		BufferedReader reader = null;
		List<String> result = new ArrayList<>();
		if (file != null) {
			reader = new BufferedReader(new FileReader(file));
			String content = null;
			while ((content = reader.readLine()) != null) {
				if (content.length() != 0) {
					if (content.indexOf("/*") == -1 && content.indexOf("//") == -1 && content.indexOf("<%--") == -1
							&& content.indexOf("<!--") == -1) {
						content = content.trim();
						if (isContainChinese(content)) {
							// System.out.println(file);
							// System.out.println(content);
							result.add(string2Unicode(content));
							// System.out.println(string2Unicode(content));
							count++;
						}
					}
				}
			}
		}
		return result;
	}

	public static String string2Unicode(String string) {
		StringBuffer unicode = new StringBuffer();
		for (int i = 0; i < string.length(); i++) {
			// 取出每一个字符
			char c = string.charAt(i);
			if (isContainChinese(new String(new char[] { c }))) {
				// 转换为unicode
				unicode.append("\\u" + Integer.toHexString(c));
			} else {
				unicode.append(c);
			}
		}
		return unicode.toString();
	}

	public static boolean isContainChinese(String str) {

		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		if (m.find()) {
			return true;
		}
		return false;
	}

	public static List<String> findJsp(String path, String fileType) {

		List<String> jspList = new ArrayList<>();
		findFiles(path, fileType, jspList);
		return jspList;
	}

	/**
	 * 递归查找文件
	 * 
	 * @param baseDirName
	 *            查找的文件夹路径
	 * @param targetFileName
	 *            需要查找的文件名
	 * @param fileList
	 *            查找到的文件集合
	 */
	public static void findFiles(String baseDirName, String targetFileName, List fileList) {

		File baseDir = new File(baseDirName); // 创建一个File对象
		if (!baseDir.exists() || !baseDir.isDirectory()) { // 判断目录是否存在
			System.out.println("文件查找失败：" + baseDirName + "不是一个目录！");
		}
		String tempName = null;
		// 判断目录是否存在
		File tempFile;
		File[] files = baseDir.listFiles();
		for (int i = 0; i < files.length; i++) {
			tempFile = files[i];
			if (tempFile.isDirectory()) {
				findFiles(tempFile.getAbsolutePath(), targetFileName, fileList);
			} else if (tempFile.isFile()) {
				tempName = tempFile.getName();
				if (wildcardMatch(targetFileName, tempName)) {
					// 匹配成功，将文件名添加到结果集
					fileList.add(tempFile.getAbsoluteFile());
				}
			}
		}
	}

	/**
	 * 通配符匹配
	 * 
	 * @param pattern
	 *            通配符模式
	 * @param str
	 *            待匹配的字符串
	 * @return 匹配成功则返回true，否则返回false
	 */
	private static boolean wildcardMatch(String pattern, String str) {
		int patternLength = pattern.length();
		int strLength = str.length();
		int strIndex = 0;
		char ch;
		for (int patternIndex = 0; patternIndex < patternLength; patternIndex++) {
			ch = pattern.charAt(patternIndex);
			if (ch == '*') {
				// 通配符星号*表示可以匹配任意多个字符
				while (strIndex < strLength) {
					if (wildcardMatch(pattern.substring(patternIndex + 1), str.substring(strIndex))) {
						return true;
					}
					strIndex++;
				}
			} else if (ch == '?') {
				// 通配符问号?表示匹配任意一个字符
				strIndex++;
				if (strIndex > strLength) {
					// 表示str中已经没有字符匹配?了。
					return false;
				}
			} else {
				if ((strIndex >= strLength) || (ch != str.charAt(strIndex))) {
					return false;
				}
				strIndex++;
			}
		}
		return (strIndex == strLength);
	}
}
