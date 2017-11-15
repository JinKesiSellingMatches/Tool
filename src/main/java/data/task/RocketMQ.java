package data.task;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.rocketmq.client.exception.MQClientException;

import core.Tool.rocketEQ.Consumer3;
import core.utils.ClientSocketUtil;
import data.demo.manager.DemoManager;

@Component
public class RocketMQ {
	
	WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
	ServletContext application = webApplicationContext.getServletContext();
	
//	@Resource
//	private DemoManager demoManager;
//	
//	@Scheduled(cron = "0/3 * * * * ?")
//	public void save(){
//		demoManager.doSave();
//	}
//
//	@Scheduled(cron = "0/5 * * * * ?")
//	public void read() throws MQClientException{
//		Consumer3 consumer3=new Consumer3();
//		consumer3.MesConsumer();
//	}
	
	@Scheduled(cron = "0/5 * * * * ?")
	public void RocketMqStates(){
		ClientSocketUtil clientSocketUtil=new ClientSocketUtil(9876);
		System.out.println(application.getAttribute("RocketMqStates"));
	}
}
