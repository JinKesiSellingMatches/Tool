package data.lucene.manager;

import core.result.ResultHelper;
import data.common.manager.LuceneDao;
import data.lucene.entity.LuceneNode;
import data.lucene.pojo.SaveLuceneRelationshipPOJO;
import data.module.pojo.DataBaseModuleSearchPOJO;

public interface LuceneNodeManager extends LuceneDao {
	
	
	/**
	 * 添加接口
	 * @param pojos
	 */
	public void addLucene(LuceneNode node) throws Exception;
	
	/**
	 * 对外唯一接口
	 * 处理情况，对索引添加/更新/删除
	 * type字段来确认是什么操作
	 * @param pojo
	 */
	public ResultHelper CenterProcess(DataBaseModuleSearchPOJO pojo);
	
	/**
	 * 修改接口
	 * @param pojo
	 * @throws Exception
	 */
	public void updateLucene(LuceneNode node) throws Exception;
	
	/**
	 * 删除接口
	 * @param pojo
	 * @throws Exception
	 */
	public void deletedLucene(LuceneNode node) throws Exception;
	
	/**
	 * 数据类型转换
	 * @return
	 */
	public LuceneNode dataBaseModuleSearchPOJOToLuceneNode(DataBaseModuleSearchPOJO node);

}
