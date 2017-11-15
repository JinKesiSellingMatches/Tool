package core.hibernate;

import java.util.Map;

import org.bouncycastle.jce.provider.symmetric.AES.OFB;
import org.hibernate.event.spi.PostDeleteEvent;
import org.hibernate.event.spi.PostDeleteEventListener;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Component;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

import core.Tool.rocketEQ.RocketEQSend;
import core.Tool.rocketEQ.POJO.RocketEQContentPOJO;
import core.utils.ObjectUtil;



@Component
public class OperListener implements PostInsertEventListener,PostUpdateEventListener, PostDeleteEventListener {

	private static final long serialVersionUID = 5815991091783798586L;
	
	private static RocketEQSend rocketEQSend=new RocketEQSend();
	
	private static int zero=0;
	
	private static int one=1;
	
	private static int two=2;
	

	@Override
	public void onPostDelete(PostDeleteEvent event) {
		try {
			rocketEQSend.sendRocket(objToPojo(event.getEntity(),two));
		} catch (MQClientException e) {
			e.printStackTrace();
		} catch (RemotingException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onPostUpdate(PostUpdateEvent event) {
		try {
			rocketEQSend.sendRocket(objToPojo(event.getEntity(),one));
		} catch (MQClientException e) {
			e.printStackTrace();
		} catch (RemotingException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onPostInsert(PostInsertEvent event) {
		try {
			rocketEQSend.sendRocket(objToPojo(event.getEntity(),zero));
		} catch (MQClientException e) {
			e.printStackTrace();
		} catch (RemotingException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean requiresPostCommitHanding(EntityPersister persister) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private RocketEQContentPOJO objToPojo(Object object,int type){
		RocketEQContentPOJO eqContent=new RocketEQContentPOJO();
		
		if (object!=null) {
			String id=(String) ObjectUtil.getValueByKey(object, "id");
			String clasz=object.getClass().toString();
			eqContent.setId(id);
			eqContent.setTime(System.currentTimeMillis());
			eqContent.setClassName(clasz);
			eqContent.setType(type);
			eqContent.setCreateUser(ObjectUtil.getValueByKey(object, "id").toString());
		}
		return eqContent;
	}



}