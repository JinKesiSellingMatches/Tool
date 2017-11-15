package core.Tool.rocketEQ;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;

import core.Tool.rocketEQ.POJO.RocketEQContentPOJO;
import core.utils.RaceUtils;
import data.lucene.entity.LuceneNode;

import java.util.List;


/**
 * Consumer，订阅消息
 */

/**
 * RocketMq消费组信息我们都会再正式提交代码前告知选手
 */
public class Consumer2 implements Runnable {

	@Override
	public void run() {
		
		
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("Lucene");
		
		/**
		 * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费<br>
		 * 如果非第一次启动，那么按照上次消费的位置继续消费
		 */
		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
		
		//在本地搭建好broker后,记得指定nameServer的地址
		consumer.setNamesrvAddr("127.0.0.1:9876");
		
		
		try {
			consumer.subscribe("default", "*");
		} catch (MQClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		consumer.registerMessageListener(new MessageListenerConcurrently() {
			
			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
					ConsumeConcurrentlyContext context) {
				 try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				for (MessageExt msg : msgs) {
					
					byte [] body = msg.getBody();
					if (body.length == 2 && body[0] == 0 && body[1] == 0) {
						//Info: 生产者停止生成数据, 并不意味着马上结束
						System.out.println("Got the end signal");
						continue;
					}
					
					RocketEQContentPOJO rocketEQContent = RaceUtils.readKryoObject(RocketEQContentPOJO.class, body);
					System.out.println(rocketEQContent.getId());
				 
				}
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});
		
		try {
			consumer.start();
		} catch (MQClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Consumer Started.");

	}
}
