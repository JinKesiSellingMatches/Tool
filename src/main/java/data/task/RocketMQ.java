package data.task;

import javax.servlet.ServletContext;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;


import core.utils.ClientSocketUtil;

//@Component
public class RocketMQ {
	
	WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
	ServletContext application = webApplicationContext.getServletContext();
	
	
	@Scheduled(cron = "0/5 * * * * ?")
	public void RocketMqStates(){
		new ClientSocketUtil(9876);
		System.out.println(application.getAttribute("RocketMqStates"));
	}
}
