package core.download.twitter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import core.download.util.MyX509TrustManager;
import core.utils.RemoveDuplicates;

public class readFile {

	public static void main(String[] args) throws Exception {
		
	}
	
	public static void downloadfile(String filePaht)throws Exception{
		String requestMethod="GET";
		String content=readFile(getFile(filePaht));
		//截取
		String min_position=content.substring(17, 35);
		List<String> urList=getUrl(content);
		urList=RemoveDuplicates.remove(urList);
		for (String string : urList) {
			System.out.println(string);
			MyX509TrustManager.httpsRequest(string, requestMethod, null, null,"D:/twitter/img/",string.split("/")[4].substring(0, 19));
		}
		if (checkNext(min_position)) {
			download.goTwitter(min_position);
		}
	}
	
	public static boolean checkNext(String content){
		
		boolean flag=true;
		try {
			new BigDecimal(content);
		} catch (Exception e) {
			flag=false;
		}
		return flag;
	}

	public static File getFile(String path) {
		return new File(path);
	}

	public static String readFile(File file) throws Exception {

		BufferedReader reader = null;
		StringBuffer sBuffer = new StringBuffer();
		if (file != null) {
			reader = new BufferedReader(new FileReader(file));
			String content = null;
			while ((content = reader.readLine()) != null) {
				if (content.length() != 0) {
					sBuffer.append(content);
				}
			}
		}
		return sBuffer.toString();
	}

	public static List<String> getUrl(String content) {

		List<String> urls = new ArrayList<>();
		String[] temps = content.split("pbs\\.twimg\\.com");
		for (String string : temps) {
			String img = string.split("\\.jpg")[0];
			if (img.length()==24) {
				img="https://pbs.twimg.com"+img+".jpg:large";
				urls.add(img.replaceAll("\\\\",""));
			}
		}
		return urls;
	}

}
