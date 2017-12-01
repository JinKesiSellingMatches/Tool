package core.Tool.rocketEQ;


import core.Tool.rocketEQ.RocketEQContentPOJO;
import core.utils.RaceUtils;
import data.common.factory.DataSourceContext;
import data.module.manager.DataBaseModuleManger;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;


/**
 * Consumer，订阅消息
 */

/**
 * RocketMq消费组信息我们都会再正式提交代码前告知选手
 */
@Component
public class Consumer {
	
	@Resource
	private DataBaseModuleManger dataBaseModuleManger;

	@PostConstruct
    public void MesConsumer() throws MQClientException{
		
    	DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("Lucene");
        /**
         * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费<br>
         * 如果非第一次启动，那么按照上次消费的位置继续消费
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

        //在本地搭建好broker后,记得指定nameServer的地址
        consumer.setNamesrvAddr("127.0.0.1:9876");

        consumer.subscribe("default", "*");

        consumer.setConsumeMessageBatchMaxSize(10);

        consumer.registerMessageListener(new MessageListenerConcurrently() {

            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {

                    byte [] body = msg.getBody();
                    if (body.length == 2 && body[0] == 0 && body[1] == 0) {
                        //Info: 生产者停止生成数据, 并不意味着马上结束
                        System.err.println("Got the end signal");
                        continue;
                    }
                    try {
                    	RocketEQContentPOJO rocketEQContent = RaceUtils.readKryoObject(RocketEQContentPOJO.class, body);
                    	//向Lucene服务 发送数据
                        DataSourceContext dataSourceContext=new DataSourceContext();
            			dataSourceContext.dataSource(rocketEQContent);
					} catch (Exception e) {
						System.out.println(e.getMessage());
						//只有这里允许try——catch
						System.out.println("未成功消费");
						return ConsumeConcurrentlyStatus.RECONSUME_LATER;
					}
                }
                System.out.println("成功消费");
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();

        System.err.println("Consumer Started.");
    }
	
	public static void main(String[] args) throws MQClientException {
		Consumer consumer=new  Consumer();
		consumer.MesConsumer();
	}
}
