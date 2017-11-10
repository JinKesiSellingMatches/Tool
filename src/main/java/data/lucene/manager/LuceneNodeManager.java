package data.lucene.manager;

import core.result.ResultHelper;
import data.common.manager.BaseDao;
import data.lucene.pojo.SaveLuceneRelationshipPOJO;
import data.module.pojo.DataBaseModuleSearchPOJO;

public interface LuceneNodeManager extends BaseDao {
	
	
	/**
	 * 添加接口
	 * @param pojos
	 */
	public void addLucene(SaveLuceneRelationshipPOJO pojos);
	
	/**
	 * 对外唯一接口
	 * @param pojo
	 */
	public ResultHelper CenterProcess(DataBaseModuleSearchPOJO pojo);

}
