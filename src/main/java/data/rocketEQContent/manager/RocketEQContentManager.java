package data.rocketEQContent.manager;

import java.util.List;

import core.Tool.rocketEQ.RocketEQContentPOJO;
import core.result.ResultHelper;
import data.common.manager.BaseDao;
import data.rocketEQContent.entity.RocketEQContent;

public interface RocketEQContentManager extends BaseDao {
	
	
	/**
	 * 添加
	 * 在端口不通  和在发送失败时加入
	 * @return
	 */
	public ResultHelper addRocketEQContent(RocketEQContentPOJO pojo);
	
	/**
	 * 默认批量更新
	 * @return
	 */
	public ResultHelper updateRocketEQContent();
	
	/**
	 * 根据是否有效查询
	 * @param effective
	 * @return
	 */
	public List<RocketEQContent> findByEffective(int effective);
}
