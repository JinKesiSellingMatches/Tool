package data.lucene.manager;

import data.common.manager.BaseDao;
import data.lucene.pojo.SaveLuceneRelationshipPOJO;

public interface LuceneNodeManager extends BaseDao {
	
	
	/**
	 * 添加接口
	 * @param pojos
	 */
	public void addLucene(SaveLuceneRelationshipPOJO pojos);

}
