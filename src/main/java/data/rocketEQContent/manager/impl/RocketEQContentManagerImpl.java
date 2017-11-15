package data.rocketEQContent.manager.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import core.Tool.rocketEQ.POJO.RocketEQContentPOJO;
import core.result.ResultHelper;
import data.common.manager.impl.BaseDaoImpl;
import data.rocketEQContent.entity.RocketEQContent;
import data.rocketEQContent.manager.RocketEQContentManager;

@Service(value="rocketEQContentManager")
public class RocketEQContentManagerImpl extends BaseDaoImpl implements RocketEQContentManager {
	
	private static int effective=0;

	@Override
	public ResultHelper addRocketEQContent(RocketEQContentPOJO pojo) {
		
		ResultHelper result=new ResultHelper();
		if (pojo!=null) {
			RocketEQContent rocketEQContent=new RocketEQContent();
			BeanUtils.copyProperties(pojo, rocketEQContent);
			rocketEQContent.setIsEffective(effective);
			this.save(rocketEQContent);
		}
		return result;
	}

	@Override
	public ResultHelper updateRocketEQContent() {
		return null;
	}

	@Override
	public List<RocketEQContent> findByEffective(int effective) {
		return null;
	}

}
