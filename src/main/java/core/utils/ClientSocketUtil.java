package core.utils;

import java.net.InetAddress;
import java.net.Socket;

import javax.servlet.ServletContext;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class ClientSocketUtil {
	
	protected static Socket server;  
	WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
	ServletContext application = webApplicationContext.getServletContext();
	public ClientSocketUtil(int port){//链接自己，本机上测试的时候用  
		try {  
			server = new Socket(InetAddress.getLocalHost(), port);  
			application.setAttribute("RocketMqStates", true);
		} catch (Exception e) {  
			application.setAttribute("RocketMqStates", false);
		}   
	}
	
	public static void main(String[] args){
		try {
			server=new Socket("47.94.15.232",9876);
			System.out.println("成功");
		} catch (Exception e) {
			System.out.println("失败");
			// TODO: handle exception
		}
		
	}
}
