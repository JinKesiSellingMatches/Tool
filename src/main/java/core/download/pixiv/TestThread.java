package core.download.pixiv;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import core.download.util.MyX509TrustManager;

//线程类
	public  class TestThread extends Thread {
	    public void run() {
	        while(true){
	            try {
	                sleep(6*1000);
	                try {
						MyX509TrustManager.httpsRequest("https://www.pixiv.net/bookmark.php?type=user&rest=hide", download.requestMethod, null, download.getRequestHeaderData(), null, null);
					} catch (KeyManagementException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchAlgorithmException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                System.out.println("一分钟运行一次");
	            } catch (InterruptedException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	             
	        }
	    }
	}
