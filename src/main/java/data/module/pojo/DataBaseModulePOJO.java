package data.module.pojo;

/**
 * 用来保存的pojo
 * @author hutao
 *
 */
public class DataBaseModulePOJO {
	
	/**
	 * 显示名称（唯一）
	 */
	private String name;
	
	/**
	 * sql
	 */
	private String sql;
	
	/**
	 * 类名称（唯一）
	 */
	private String className;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}
