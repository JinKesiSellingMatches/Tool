package data.common.manager;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import data.common.page.Condition;
import data.common.page.PageInfo;
import data.common.query.QueryParams;


/**
 * hibernate baseDao
 * (无添加，修改，查询。 且不支持sql（sql 只能用来查询）)
 * @Description:
 * @author rqf
 * @date 2016年8月18日 上午10:17:35 @v1.0
 */
public interface BaseGaiaDao {

	/**
	 * hql语句查询记录数
	 * 
	 * @param hql
	 *            查询语句
	 * @return
	 */
	public Long count(String hql);

	/**
	 * hql语句带条件查询记录数
	 * 
	 * @param hql
	 *            查询语句
	 * @param params
	 *            条件参数
	 * @return
	 */
	public Long count(String hql, Map<String, Object> params);

	/**
	 * sql查询记录数
	 * 
	 * @param sql
	 *            查询语句
	 * @return
	 */
	public Long countBySql(String sql);

	/**
	 * sql带条件查询记录数
	 * 
	 * @param sql
	 *            查询语句
	 * @param params
	 *            条件参数
	 * @return
	 */
	public Long countBySql(String sql, Map<String, Object> params);





	/**
	 * 根据hql查询
	 * 
	 * @param hql
	 * @return
	 */
	public <T> List<T> find(String hql);
	
   /**
     * 根据hql查询
     * 
     * @param hql
     * @return
     * @param calzz
     *        转换的类
     */
    public <T> List<T> find(String hql,Class<T> clazz);

	/**
	 * hql语句分页查询实体集合
	 * 
	 * @param hql
	 *            查询语句
	 * @param page
	 *            当前页号
	 * @param rows
	 *            行数
	 * @return
	 */
	public <T> List<T> find(String hql, int page, int rows);

	/**
	 * hql语句分页查询实体集合
	 * @param hql
	 * @param page
	 * @param rows
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public <T> List<T> find(String hql, int page, int rows, Class<T> clazz);

	/**
	 * hql语句带条件查询实体集合
	 * 
	 * @param hql
	 *            查询语句
	 * @param params
	 *            条件参数
	 * @return
	 */
	public <T> List<T> find(String hql, Map<String, Object> params);
    /**
     * hql语句带条件查询实体集合
     * 
     * @param hql
     *            查询语句
     * @param params
     *            条件参数
     * @param clazz    
     *            转换的类
     * @return
     */
	public <T> List<T> find(String hql, Map<String, Object> params, Class<T> clazz);

	/**
	 * hql语句带条件分页查询实体集合
	 * 
	 * @param hql
	 *            查询语句
	 * @param params
	 *            条件参数
	 * @param page
	 *            当前页号
	 * @param rows
	 *            行数
	 * @return
	 */
	public <T> List<T> find(String hql, Map<String, Object> params, int page, int rows);

	/**
	 * hql 传对应DTO 分页
	 * 
	 * @param hql
	 * @param params
	 * @param page
	 * @param rows
	 * @param clazz
	 * @return
	 */
	public <T> List<T> find(String hql, Map<String, Object> params, int page, int rows, Class<T> clazz);

	public <T> List<T> findByCriteria(DetachedCriteria criteria);

	public List findByExample(Object example);

	/**
	 * sql查询实体结果集
	 * 
	 * @param sql
	 *            查询语句
	 * @param clazz
	 *            类对象
	 * @return
	 */
	public <T> List<T> findBySql(String sql);

	/**
	 * sql查询实体结果集
	 * 
	 * @param sql
	 *            查询语句
	 * @param clazz
	 *            类对象
	 * @return
	 */
	public <T> List<T> findBySql(String sql, Class<T> clazz);

	/**
	 * sql分页查询结果集
	 * 
	 * @param sql
	 *            查询语句
	 * @param page
	 *            当前页
	 * @param rows
	 *            行数
	 * @param clazz
	 *            类对象
	 * @return
	 */
	public <T> List<T> findBySql(String sql, int page, int rows, Class<T> clazz);

	/**
	 * sql带条件查询实体结果集
	 * 
	 * @param sql
	 *            查询语句
	 * @param params
	 *            条件参数
	 * @param clazz
	 *            类对象
	 * @return
	 */
	public <T> List<T> findBySql(String sql, Map<String, Object> params, Class<T> clazz);

	/**
	 * sql分页查询结果集
	 * 
	 * @param sql
	 *            查询语句
	 * @param params
	 *            条件参数
	 * @param page
	 *            当前页
	 * @param rows
	 *            行数
	 * @param clazz
	 *            类对象
	 * @return
	 */
	public <T> List<T> findBySql(String sql, Map<String, Object> params, int page, int rows, Class<T> clazz);

	/**
	 * sql 查询实体结果集
	 * 
	 * @param sql
	 *            查询语句
	 * @param countStr
	 *            总记录数 默认为count(*)
	 * @param params
	 *            查询条件map
	 * @param pageInfo
	 *            分页对象
	 * @param clazz
	 *            返回的对象dto
	 * @return 返回结果集
	 */
	public <T> List<T> findBySql(String sql, String countStr, Map<String, Object> params, PageInfo pageInfo,
			Class clazz);

	/**
	 * sql 查询实体结果集
	 * 
	 * @param sql
	 *            查询语句
	 * @param countStr
	 *            总记录数 默认为count(*)
	 * @param pageInfo
	 *            分页对象
	 * @param clazz
	 *            返回的对象dto
	 * @return 返回结果集
	 */
	public <T> List<T> findBySql(String sql, String countStr, PageInfo pageInfo, Class clazz);



	/**
	 * 根据主键获取对象
	 * 
	 * @param clazz
	 *            所要获取对象的类
	 * @param id
	 *            主键
	 * @return
	 */
	public <T> T get(Class<T> clazz, Serializable id);

	/**
	 * hql语句查询单个实体对象
	 * 
	 * @param hql
	 *            查询语句
	 * @return 实体对象
	 */
	public <T> T get(String hql);

	/**
	 * hql语句带条件查询单个实体对象
	 * 
	 * @param hql
	 *            查询语句
	 * @param params
	 *            条件参数
	 * @return 实体对象
	 */
	public <T> T get(String hql, Map<String, Object> params);

	/**
	 * 
	 * @param hql
	 * @param params
	 * @param clazz要转换的对象
	 * @return
	 */
	public <T> T get(String hql, Map<String, Object> params, Class<T> clazz);

	/**
	 * hql语句带条件查询单个实体对象
	 * @param hql
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public <T> T get(String hql, Class<T> clazz);

	/**
	 * sql查询获取实体对象
	 * 
	 * @param sql
	 *            查询语句
	 * @return
	 */
	public <T> T getBySql(String sql);

	/**
	 * sql带条件查询获取实体对象
	 * 
	 * @param sql
	 *            查询语句
	 * @param params
	 *            条件参数
	 * @return
	 */
	public <T> T getBySql(String sql, Map<String, Object> params);
	
	
	/**
	 * sql带条件查询获取实体对象
	 * 
	 * @param sql
	 *            查询语句
	 * @param params
	 *            条件参数
	 * @param clazz
	 *            转换对象           
	 * @return
	 */
	public <T> T getBySql(String sql, Map<String, Object> params,Class<T> clazz);

	/**
	 * sql带条件查询获取实体对象
	 * @param sql
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public <T> T getBySql(String sql, Class<T> clazz);

	public int getCountByCriteria(DetachedCriteria criteria);

	public List<?> getListByCriteria(DetachedCriteria criteria, Integer startPage, Integer pageSize);

	public List<?> getListByCriteria(DetachedCriteria criteria, PageInfo page);

	public List getPageMapByHql(String hql, Map<String, Object> params);

	public List getPageMapByHql(String hql, Map<String, Object> params, int page, int rows);

	/**
	 * 查询此对象所有数据
	 * 
	 * @param clazz
	 * @return
	 */
	public <T> List<T> list(Class<T> clazz);

	/**
	 * hql或sql查询
	 * @param queryParams
	 * @param <T>
	 * @return
	 */
	public <T> List<T> findByQueryParams(QueryParams queryParams);

	/**
	 * 统计数量
	 * @param queryParams
	 * @return
	 */
	public Long countByQueryParams(QueryParams queryParams);

	/**
	 * 分页查询
	 * @param queryParams
	 * @param page
	 * @param rows
	 * @param <T>
	 * @return
	 */
	public <T> List<T> findByQueryParams(QueryParams queryParams, int page, int rows);

	/**
	 * 分页查询
	 * @param queryParams
	 * @param condition
	 * @param <T>
	 * @return
	 */
	public <T> List<T> findByQueryParams(QueryParams queryParams, Condition condition);

	/**
	 * 获取第一条记录
	 * @param queryParams
	 * @param <T>
	 * @return
	 */
	public <T> T getByQueryParams(QueryParams queryParams);
	
	public void test(String a);
}
