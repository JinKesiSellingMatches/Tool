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
	
	public static void main(String[] args) {
		ClientSocketUtil clientSocketUtil=new ClientSocketUtil(9876);
	}
}
