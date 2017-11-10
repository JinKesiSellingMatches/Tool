package data.module.manager;

import java.util.List;
import java.util.Map;

import clojure.string__init;
import core.Tool.rocketEQ.POJO.RocketEQContent;
import core.result.ResultHelper;
import data.common.manager.BaseDao;
import data.module.entity.DataBaseModule;
import data.module.pojo.DataBaseModulePOJO;
import data.module.pojo.DataBaseModuleSearchPOJO;

/**
 * 数据源
 * @author hutao
 *
 */
public interface DataBaseModuleManger extends BaseDao {
	
	
	/**
	 * 根据类名称查询
	 * @param clientName 类名称， "class xxxx"
	 * 查询条件deleted=0
	 * @return
	 */
	public DataBaseModule getByClassName(String className);
	

	/**
	 * 保存接口
	 * @param pojo
	 */
	public void saveDateBaseModule(DataBaseModulePOJO pojo);
	
	/**
	 * 根据名称查询
	 * @param name
	 * @return
	 */
	public DataBaseModule getByName(String name);
	
	/**
	 * 根据编码查询
	 * @param code
	 * @return
	 */
	public DataBaseModule getByCode(String code);
	
	/**
	 * 根据编码查询
	 * 多个编码获取多个对象
	 * @param codes
	 * @return
	 */
	public List<DataBaseModule> findByCode(List<String> codes);
	
	/**
	 * 获取要存入Lucene 的对象
	 * @param sql
	 * @param id
	 * @return
	 */
	public DataBaseModuleSearchPOJO findModuleSearchInfo(String sql,String id);
	
	/**
	 * 这里调用Lucene的接口
	 * @param ro
	 * @return
	 */
	public ResultHelper sendLucene(RocketEQContent rocketEQContent) throws Exception;
	
}