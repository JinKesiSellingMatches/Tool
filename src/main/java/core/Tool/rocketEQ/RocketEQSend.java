package core.Tool.rocketEQ;

import java.util.Date;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendCallback;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

import core.Tool.rocketEQ.POJO.RocketEQContentPOJO;
import core.result.ResultHelper;
import core.utils.RaceUtils;
import data.lucene.entity.LuceneNode;


public class RocketEQSend{
	
	//发送数据
	public void sendRocket(RocketEQContentPOJO rocketEQContent) throws MQClientException, RemotingException, InterruptedException{
		
		ResultHelper result=new ResultHelper();
		
		result=checkSend(rocketEQContent);
		
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
				//TODO 这里需要日志记录失败
			}
		});
		producer.shutdown();
	}
	
	private ResultHelper checkSend(RocketEQContentPOJO rocketEQContent){
		
		ResultHelper result=new ResultHelper();
		
		return result;
	}
	
	public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException {
		RocketEQSend rocketEQSend=new RocketEQSend();
		rocketEQSend.sendRocket(new RocketEQContentPOJO());
	}


}
