package data.task;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.rocketmq.client.exception.MQClientException;

import core.Tool.rocketEQ.Consumer3;
import data.demo.manager.DemoManager;

@Component
public class RocketMQ {
	
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
}
