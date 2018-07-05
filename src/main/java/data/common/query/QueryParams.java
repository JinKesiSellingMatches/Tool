package data.common.query;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Created by ChenShuai on 2017/5/4.
 */
public abstract class QueryParams {
    /**
     * 查询语句
     */
    protected StringBuilder query;

    /**
     * 参数
     */
    protected Map<String, Object> params;

    /**
     * 返回对象类型
     */
    protected Class<?> clazz;

    /**
     * 无参构造函数
     */
    public QueryParams() {
        query = new StringBuilder();
        params = new HashMap<>();
    }

    /**
     * 构造函数
     * @param sqlOrHql sql or hql
     */
    public QueryParams(String sqlOrHql) {
        appendQuery(sqlOrHql);
    }

    /**
     * 添加参数
     * @param key 键
     * @param value 值
     */
    public void addParams(String key, Object value) {
        params.put(key, value);
    }

    /**
     * 添加查询语句及参数
     * @param query
     * @param value
     */
    public void addQueryAndParams(String query, Object value) {
        appendQuery(query);
        addParams(getKey(query), value);
    }

    /**
     * 添加查询语句及参数（value为null或empty时不添加）
     * @param query
     * @param value
     */
    public void tryAddQueryAndParams(String query, Object value) {
        if (value != null) {
            appendQuery(query);
            addParams(getKey(query), value);
        }
    }

    public String getKey(String query) {
        Pattern pattern = Pattern.compile(":([a-zA-Z0-9]*)");
        Matcher matcher = pattern.matcher(query);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return null;
        }
    }

    /**
     * 添加sql或hql
     * @param query 添加sql或hql
     */
    public void appendQuery(String query) {
        this.query.append(" ");
        this.query.append(query);
    }

    @Override
    public String toString() {
        return getQuery();
    }

    /**
     * 获取参数
     * @return
     */
    public Map<String, Object> getParams() {
        return params;
    }

    /**
     * 获取查询语句
     * @return
     */
    public String getQuery() {
        return query.toString();
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }
}
