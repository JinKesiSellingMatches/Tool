package core.Tool.rocketEQ;

import java.util.Date;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendCallback;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

import core.Tool.rocketEQ.POJO.RocketEQContent;
import core.utils.RaceUtils;
import data.lucene.entity.LuceneNode;


public class RocketEQSend {
	
	public static void main(String[] args) throws Exception {
		
		RocketEQContent node=new RocketEQContent();
		node.setClassEntity("demo");
		
		RocketEQSend rocketEQ=new RocketEQSend();
		rocketEQ.sendRocket(node);
	}
	
	
	//发送数据
	public void sendRocket(RocketEQContent rocketEQContent) throws MQClientException, RemotingException, InterruptedException{
		
		DefaultMQProducer producer=new DefaultMQProducer("Lucene");
		
		producer.setNamesrvAddr("127.0.0.1:9876");
		
		producer.start();
		
		byte[] body=RaceUtils.writeKryoObject(rocketEQContent);
		
		Message msgTobroker=new Message("default",body);
		
		producer.send(msgTobroker,new SendCallback() {
			
			@Override
			public void onSuccess(SendResult sendResult) {
				System.out.println(sendResult.getMsgId());
			}
			
			@Override
			public void onException(Throwable e) {
				
			}
		});
		producer.shutdown();
	}
}
