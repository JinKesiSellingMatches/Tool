package data.common.manager.impl;

import org.hibernate.*;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.Transformers;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;

import core.utils.StringUtil;
import data.common.manager.BaseGaiaDao;
import data.common.page.Condition;
import data.common.page.PageInfo;
import data.common.query.HqlQueryParams;
import data.common.query.QueryParams;
import data.common.query.SqlQueryParams;
import data.common.support.GaiaDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * hibernate baseDaoImpl
 * 
 * @Description:
 * @author rqf
 * @date 2016年8月18日 上午10:30:20 @v1.0
 */
@Repository("baseGaiaDao")
public class BaseGaiaDaoImpl extends GaiaDaoSupport  implements BaseGaiaDao {


	@Resource
	private DataSourceTransactionManager txManager;

	@Override
	public Long count(String hql) {

		Query query = this.getCurrentSession().createQuery(hql);
		return (Long) query.uniqueResult();
	}

	@Override
	public Long count(String hql, Map<String, Object> params) {

		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				Object obj = params.get(key);
				if (obj instanceof Collection<?>) {
					query.setParameterList(key, (Collection<?>) obj);
				} else if (obj instanceof Object[]) {
					query.setParameterList(key, (Object[]) obj);
				} else {
					query.setParameter(key, obj);
				}
			}
		}
		return (Long) query.uniqueResult();
	}

	@Override
	public Long countBySql(String sql) {

		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		return ((BigInteger) sqlQuery.uniqueResult()).longValue();
	}

	@Override
	public Long countBySql(String sql, Map<String, Object> params) {

		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				Object obj = params.get(key);
				if (obj instanceof Collection<?>) {
					sqlQuery.setParameterList(key, (Collection<?>) obj);
				} else if (obj instanceof Object[]) {
					sqlQuery.setParameterList(key, (Object[]) obj);
				} else {
					sqlQuery.setParameter(key, obj);
				}
			}
		}
		return Long.valueOf(sqlQuery.uniqueResult().toString());
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> find(String hql) {

		Query query = this.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> find(String hql, Class<T> clazz) {

		Query query = this.getCurrentSession().createQuery(hql);
		return query.setResultTransformer(Transformers.aliasToBean(clazz)).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> find(String hql, int page, int rows) {

		Query query = this.getCurrentSession().createQuery(hql);
		return query.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> find(String hql, int page, int rows, Class<T> clazz) {
		Query query = this.getCurrentSession().createQuery(hql);
		return query.setResultTransformer(Transformers.aliasToBean(clazz)).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> find(String hql, Map<String, Object> params) {

		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				Object obj = params.get(key);
				if (obj instanceof Collection<?>) {
					query.setParameterList(key, (Collection<?>) obj);
				} else if (obj instanceof Object[]) {
					query.setParameterList(key, (Object[]) obj);
				} else {
					query.setParameter(key, obj);
				}
			}
		}
		return query.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> find(String hql, Map<String, Object> params, Class<T> clazz) {

		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				Object obj = params.get(key);
				if (obj instanceof Collection<?>) {
					query.setParameterList(key, (Collection<?>) obj);
				} else if (obj instanceof Object[]) {
					query.setParameterList(key, (Object[]) obj);
				} else {
					query.setParameter(key, obj);
				}
			}
		}
		return query.setResultTransformer(Transformers.aliasToBean(clazz)).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> find(String hql, Map<String, Object> params, int page, int rows) {

		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				Object obj = params.get(key);
				if (obj instanceof Collection<?>) {
					query.setParameterList(key, (Collection<?>) obj);
				} else if (obj instanceof Object[]) {
					query.setParameterList(key, (Object[]) obj);
				} else {
					query.setParameter(key, obj);
				}
			}
		}
		return query.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> find(String hql, Map<String, Object> params, int page, int rows, Class<T> clazz) {

		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				Object obj = params.get(key);
				if (obj instanceof Collection<?>) {
					query.setParameterList(key, (Collection<?>) obj);
				} else if (obj instanceof Object[]) {
					query.setParameterList(key, (Object[]) obj);
				} else {
					query.setParameter(key, obj);
				}
			}
		}
		return query.setFirstResult((page - 1) * rows).setMaxResults(rows)
				.setResultTransformer(Transformers.aliasToBean(clazz)).list();
	}

	@Override
	public <T> List<T> findByCriteria(DetachedCriteria criteria) {

		return criteria.getExecutableCriteria(this.getCurrentSession()).setProjection(null)
				.setResultTransformer(CriteriaSpecification.ROOT_ENTITY).list();
	}

	@Override
	public List findByExample(Object example) {

		return this.getCurrentSession().createCriteria(example.getClass()).add(Example.create(example)).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> findBySql(String sql) {

		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		return sqlQuery.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> findBySql(String sql, Class<T> clazz) {

		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(clazz));
		return sqlQuery.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> findBySql(String sql, int page, int rows, Class<T> clazz) {

		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		if (clazz != null)
			sqlQuery.setResultTransformer(Transformers.aliasToBean(clazz));
		// return sqlQuery.list();
		return sqlQuery.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> findBySql(String sql, Map<String, Object> params, Class<T> clazz) {

		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				Object obj = params.get(key);
				if (obj instanceof Collection<?>) {
					sqlQuery.setParameterList(key, (Collection<?>) obj);
				} else if (obj instanceof Object[]) {
					sqlQuery.setParameterList(key, (Object[]) obj);
				} else {
					sqlQuery.setParameter(key, obj);
				}
			}
		}
		if (clazz != null)
			sqlQuery.setResultTransformer(Transformers.aliasToBean(clazz));
		return sqlQuery.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> findBySql(String sql, Map<String, Object> params, int page, int rows, Class<T> clazz) {

		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				Object obj = params.get(key);
				if (obj instanceof Collection<?>) {
					sqlQuery.setParameterList(key, (Collection<?>) obj);
				} else if (obj instanceof Object[]) {
					sqlQuery.setParameterList(key, (Object[]) obj);
				} else {
					sqlQuery.setParameter(key, obj);
				}
			}
		}
		if (clazz != null)
			sqlQuery.setResultTransformer(Transformers.aliasToBean(clazz));
		return sqlQuery.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> findBySql(String sql, String countStr, Map<String, Object> params, PageInfo pageInfo,
			Class clazz) {

		if (StringUtil.isEmpty(countStr))
			countStr = "count(*)";
		String countSql = "select " + countStr + " from (" + sql + ") as table_alias";// .substring(sql.toLowerCase().indexOf("from"));
		int count = this.countBySql(countSql).intValue();
		pageInfo.setCount(count);
		return this.findBySql(sql, params, pageInfo.getPageNum(), pageInfo.getPageSize(), clazz);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> findBySql(String sql, String countStr, PageInfo pageInfo, Class clazz) {

		if (StringUtil.isEmpty(countStr))
			countStr = "count(*)";
		String countSql = "select " + countStr + " from (" + sql + ") as table_alias";// .substring(sql.toLowerCase().indexOf("from"));
		int count = this.countBySql(countSql).intValue();
		pageInfo.setCount(count);
		return this.findBySql(sql, pageInfo.getPageNum(), pageInfo.getPageSize(), clazz);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> clazz, Serializable id) {

		return (T) this.getCurrentSession().get(clazz, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T get(String hql) {

		Query query = this.getCurrentSession().createQuery(hql);
		List<T> ls = query.list();
		if (ls != null && ls.size() > 0) {
			return ls.get(0);
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T get(String hql, Map<String, Object> params) {

		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		List<T> ls = query.list();
		if (ls != null && ls.size() > 0) {
			return ls.get(0);
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T get(String hql, Map<String, Object> params, Class<T> clazz) {

		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		List<T> ls = query.setResultTransformer(Transformers.aliasToBean(clazz)).list();
		if (ls != null && ls.size() > 0) {
			return ls.get(0);
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T get(String hql, Class<T> clazz) {
		Query query = this.getCurrentSession().createQuery(hql);
		List<T> ls = query.setResultTransformer(Transformers.aliasToBean(clazz)).list();
		if (ls != null && ls.size() > 0) {
			return ls.get(0);
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getBySql(String sql) {

		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		List<T> ls = sqlQuery.list();
		if (ls != null && ls.size() > 0) {
			return ls.get(0);
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getBySql(String sql, Map<String, Object> params) {

		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				Object obj = params.get(key);
				if (obj instanceof Collection<?>) {
					sqlQuery.setParameterList(key, (Collection<?>) obj);
				} else if (obj instanceof Object[]) {
					sqlQuery.setParameterList(key, (Object[]) obj);
				} else {
					sqlQuery.setParameter(key, obj);
				}
			}
		}
		List<T> ls = sqlQuery.list();
		if (ls != null && ls.size() > 0) {
			return ls.get(0);
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getBySql(String sql, Map<String, Object> params, Class<T> clazz) {

		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				Object obj = params.get(key);
				if (obj instanceof Collection<?>) {
					sqlQuery.setParameterList(key, (Collection<?>) obj);
				} else if (obj instanceof Object[]) {
					sqlQuery.setParameterList(key, (Object[]) obj);
				} else {
					sqlQuery.setParameter(key, obj);
				}
			}
		}
		if (clazz != null)
			sqlQuery.setResultTransformer(Transformers.aliasToBean(clazz));
		List<T> ls = sqlQuery.list();
		if (ls != null && ls.size() > 0) {
			return ls.get(0);
		}
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getBySql(String sql, Class<T> clazz) {
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.setResultTransformer(Transformers.aliasToBean(clazz));
		List<T> ls = sqlQuery.list();
		if (ls != null && ls.size() > 0) {
			return ls.get(0);
		}
		return null;
	}

	@Override
	public int getCountByCriteria(DetachedCriteria criteria) {

		return ((Long) criteria.getExecutableCriteria(this.getCurrentSession()).setProjection(Projections.rowCount())
				.uniqueResult()).intValue();
	}

	@Override
	public List<?> getListByCriteria(DetachedCriteria criteria, Integer startPage, Integer pageSize) {

		if (startPage != null && pageSize != null) {
			return criteria.getExecutableCriteria(this.getCurrentSession()).setProjection(null)
					.setResultTransformer(CriteriaSpecification.ROOT_ENTITY).setFirstResult(startPage)
					.setMaxResults(pageSize).list();
		} else {
			return criteria.getExecutableCriteria(this.getCurrentSession()).setProjection(null)
					.setResultTransformer(CriteriaSpecification.ROOT_ENTITY).list();
		}
	}

	@Override
	public List<?> getListByCriteria(DetachedCriteria criteria, PageInfo page) {

		if (page == null) {
			return criteria.getExecutableCriteria(this.getCurrentSession()).setProjection(null)
					.setResultTransformer(CriteriaSpecification.ROOT_ENTITY).list();
		} else {
			return criteria.getExecutableCriteria(this.getCurrentSession()).setProjection(null)
					.setResultTransformer(CriteriaSpecification.ROOT_ENTITY)
					.setFirstResult((page.getPageNum() - 1) * page.getPageSize()).setMaxResults(page.getPageSize())
					.list();
		}
	}

	@Override
	public List getPageMapByHql(String hql, Map<String, Object> params) {

		Query query = this.getCurrentSession().createQuery(hql);
		query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		return query.list();
	}

	@Override
	public List getPageMapByHql(String hql, Map<String, Object> params, int page, int rows) {

		Query query = this.getCurrentSession().createQuery(hql);
		query.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				Object obj = params.get(key);
				if (obj instanceof Collection<?>) {
					query.setParameterList(key, (Collection<?>) obj);
				} else if (obj instanceof Object[]) {
					query.setParameterList(key, (Object[]) obj);
				} else {
					query.setParameter(key, obj);
				}
			}
		}
		return query.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> list(Class<T> clazz) {

		Criteria ct = this.getCurrentSession().createCriteria(clazz);
		return ct.list();
	}

	@Override
	public <T> List<T> findByQueryParams(QueryParams queryParams) {
		String query = queryParams.getQuery();
		Map<String, Object> params = queryParams.getParams();
		Class<?> clazz = queryParams.getClazz();

		List<?> result = new ArrayList<>();

		if (queryParams instanceof HqlQueryParams) {
			if (clazz == null) {
				result = find(query, params);
			} else {
				result = find(query, params, clazz);
			}
		} else if (queryParams instanceof SqlQueryParams) {
			if (clazz == null) {
				result = findBySql(query);
			} else {
				result = findBySql(query, params, clazz);
			}
		}
		return (List<T>) result;
	}

	@Override
	public Long countByQueryParams(QueryParams queryParams) {
		String query = queryParams.getQuery();
		query = query.replaceFirst("(?i)([\\d\\D]*?)?FROM", "SELECT COUNT(*) FROM");

		Map<String, Object> params = queryParams.getParams();

		Long result = 0L;

		if (queryParams instanceof HqlQueryParams) {
			if (params.isEmpty()) {
				result = count(query);
			} else {
				result = count(query, params);
			}
		} else if (queryParams instanceof SqlQueryParams) {
			if (params.isEmpty()) {
				result = countBySql(query);
			} else {
				result = countBySql(query, params);
			}
		}
		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> findByQueryParams(QueryParams queryParams, int page, int rows) {
		String query = queryParams.getQuery();
		Map<String, Object> params = queryParams.getParams();
		Class<?> clazz = queryParams.getClazz();

		List<T> result = new ArrayList<>();

		if (queryParams instanceof HqlQueryParams) {
			if (params.isEmpty()) {
				if (clazz == null) {
					result = find(query, page, rows);
				} else {
					result = (List<T>) find(query, page, rows, clazz);
				}
			} else {
				if (clazz == null) {
					result = find(query, params, page, rows);
				} else {
					result = (List<T>) find(query, params, page, rows, clazz);
				}
			}
		} else if (queryParams instanceof SqlQueryParams) {
			if (params.isEmpty()) {
				result = (List<T>) findBySql(query, page, rows, clazz);
			} else {
				result = (List<T>) findBySql(query, params, page, rows, clazz);
			}
		}
		return result;
	}

	@Override
	public <T> List<T> findByQueryParams(QueryParams queryParams, Condition condition) {
		Integer pageNum = condition.getPageInfo().getPageNum();
		Integer pageSize = condition.getPageInfo().getPageSize();

		condition.getPageInfo().setCount(countByQueryParams(queryParams).intValue());

		return findByQueryParams(queryParams, pageNum, pageSize);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getByQueryParams(QueryParams queryParams) {
		String query = queryParams.getQuery();
		Map<String, Object> params = queryParams.getParams();
		Class<?> clazz = queryParams.getClazz();

		T result = null;

		if (queryParams instanceof HqlQueryParams) {
			if (params.isEmpty()) {
				if (clazz == null) {
					result = get(query);
				} else {
					result = (T) get(query, clazz);
				}
			} else {
				if (clazz == null) {
					result = get(query, params);
				} else {
					result = (T) get(query, params, clazz);
				}
			}
		} else if (queryParams instanceof SqlQueryParams) {
			if (params.isEmpty()) {
				if (clazz == null) {
					result = getBySql(query);
				} else {
				}
			} else {
				if (clazz == null) {
					result = getBySql(query, params);
				} else {
					result = (T) getBySql(query, clazz);
				}
			}
		}
		return result;
	}

	@Override
	public void test(String a) {
		System.out.println("BaseGaiaDao");
	}

}