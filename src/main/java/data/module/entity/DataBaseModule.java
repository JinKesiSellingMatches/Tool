package data.module.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import data.common.entity.CommonEntity;

/**
 * 模型表
 * 用来保存lucene 的表
 * table 不允许重复
 * @author hutao
 *
 */
@Entity
@Table(name = "tb_lucene_data_base_module")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = { "createUser", "lastModifyUser" })
public class DataBaseModule extends CommonEntity{
	
	/**
	 * 编码
	 */
	@Column(name="code")
	private String code;
	
	/**
	 * 显示名称（唯一）
	 * 不允许存在空格
	 */
	@Column(name="name")
	private String name;
	
	/**
	 * sql
	 * 注意 sql 返回字段未固定字段！！！
	 * 返回ID,CreateDate,title,content,search
	 * id ,创建时间，标题,内容，搜索
	 */
	@Column(name="sql_content")
	private String sqlContent;
	
	/**
	 * 类名称（唯一）
	 * 不允许存在空格
	 */
	@Column(name="class_name")
	private String className;
	
	/**
	 * 链接地址
	 */
	@Column(name="url")
	private String url;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSqlContent() {
		return sqlContent;
	}

	public void setSqlContent(String sqlContent) {
		this.sqlContent = sqlContent;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}
