package core.download.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;  
  
public class MyX509TrustManager implements X509TrustManager {  
  
    @Override  
    public void checkClientTrusted(X509Certificate[] chain, String authType)  
            throws CertificateException {  
        // TODO Auto-generated method stub  
  
    }  
  
    @Override  
    public void checkServerTrusted(X509Certificate[] chain, String authType)  
            throws CertificateException {  
        // TODO Auto-generated method stub  
  
    }  
  
    @Override  
    public X509Certificate[] getAcceptedIssuers() {  
        // TODO Auto-generated method stub  
        return null;  
    }
    
    /* 
     * 处理https GET/POST请求 
     * 请求地址、请求方法、参数 
     * */  
    public static String httpsRequest(String requestUrl,String requestMethod,String outputStr,Map<String, String> header,String imgPaht,String imgName) throws IOException, NoSuchAlgorithmException, KeyManagementException{  
        StringBuffer buffer=new StringBuffer();  
        Proxy proxy = new Proxy(java.net.Proxy.Type.HTTP,new InetSocketAddress("127.0.0.1", Integer.valueOf("1080")));
        //创建SSLContext  
        SSLContext sslContext=SSLContext.getInstance("SSL");  
        TrustManager[] tm={new MyX509TrustManager()};  
        //初始化  
        sslContext.init(null, tm, new java.security.SecureRandom());;  
        //获取SSLSocketFactory对象  
        SSLSocketFactory ssf=sslContext.getSocketFactory();  
        URL url=new URL(requestUrl);
        HttpsURLConnection conn=(HttpsURLConnection)url.openConnection();
        
        conn.setConnectTimeout(50000);
        conn.setReadTimeout(50000);
        conn.setDoOutput(true);  
        conn.setDoInput(true);  
        conn.setUseCaches(false);  
        conn.setRequestMethod(requestMethod);  
        //设置当前实例使用的SSLSoctetFactory  
        conn.setSSLSocketFactory(ssf);  
     // 设置 header
        if (header != null) {
            Iterator<String> iteratorHeader = header.keySet().iterator();
            while (iteratorHeader.hasNext()) {
                String key = iteratorHeader.next();
                conn.setRequestProperty(key,header.get(key));
            }
        }
        conn.connect(); 
        
        if (imgPaht!=null) {
        	BufferedInputStream bin = new BufferedInputStream(conn.getInputStream());
            String path = imgPaht + imgName;
            File file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            OutputStream out = new FileOutputStream(file);
            int size = 0;
            int len = 0;
            byte[] buf = new byte[1024];
            while ((size = bin.read(buf)) != -1) {
                len += size;
                out.write(buf, 0, size);
                // 打印下载百分比
                // System.out.println("下载了-------> " + len * 100 / fileLength +
                // "%\n");
            }
            bin.close();
            out.close();
		}else{
			//读取服务器端返回的内容  
			InputStream is=conn.getInputStream();  
			InputStreamReader isr=new InputStreamReader(is,"utf-8");  
			BufferedReader br=new BufferedReader(isr);  
			buffer=new StringBuffer();  
			String line=null;  
			while((line=br.readLine())!=null){  
				buffer.append(line+"\r");  
			}  
			//往服务器端写内容  
			if(null!=buffer){  
//				File myFilePath = new File(outputStr);
//				if (!myFilePath.exists()) {
//					myFilePath.createNewFile();
//				}
//				FileWriter resultFile = new FileWriter(myFilePath);
//				PrintWriter myFile = new PrintWriter(resultFile);
//				myFile.println(buffer.toString());
//				resultFile.close();
			}  
		}
        return buffer.toString();  
    }  
}  