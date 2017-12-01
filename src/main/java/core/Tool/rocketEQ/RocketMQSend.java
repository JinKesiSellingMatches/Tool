package core.Tool.rocketEQ;

import java.util.Date;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import core.Tool.rocketEQ.RocketEQContentPOJO;
import core.result.ResultHelper;
import core.utils.RaceUtils;


public class RocketMQSend{
	
	//发送数据
	public void sendRocket(RocketEQContentPOJO rocketEQContent) throws MQClientException, RemotingException, InterruptedException{
		
		ResultHelper result=new ResultHelper();
		
		result=checkSend(rocketEQContent);
		
		DefaultMQProducer producer=new DefaultMQProducer("Lucene");
		
		producer.setNamesrvAddr("127.0.0.1:9876");
		
		producer.start();
		try {
			
			byte[] body=RaceUtils.writeKryoObject(rocketEQContent);
			
			Message msgTobroker=new Message("default",body);
			
			producer.send(msgTobroker,new SendCallback() {
				
				@Override
				public void onSuccess(SendResult sendResult) {
					System.out.println(sendResult.getMsgId());
				}
				
				public void onException(Throwable e) {
					//TODO 这里需要日志记录失败
				}
			});
			producer.shutdown();
			
		} catch (Exception e) {
			//TODO 日志
			producer.shutdown();
		}
	}
	
	private ResultHelper checkSend(RocketEQContentPOJO rocketEQContent){
		
		ResultHelper result=new ResultHelper();
		
		return result;
	}
	
	public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException {
		RocketMQSend rocketEQSend=new RocketMQSend();
		rocketEQSend.sendRocket(new RocketEQContentPOJO());
	}


}
