package core.utils;

import java.io.File;

public class FileUtils {
	
	/**
	 * 文件夹为空返回true
	 * @param url
	 * @return
	 */
	public boolean isEmptyFolder(String url){
		boolean flag=false;
		File file = new File(url);
        if (file.isDirectory()) {
            String[] files = file.list();
            if (files.length == 0) {
               flag=true;
            }
        }
		return flag;
	}
}
