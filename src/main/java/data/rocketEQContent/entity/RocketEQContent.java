package data.rocketEQContent.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import data.common.entity.CommonEntity;

/**
 * 这个是RocketMQ 挂掉后 向数据库中保存数据
 * 
 * @author hutao
 *
 */
@Entity
@Table(name = "tb_sys_rocket_eq_content")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = { "createUser", "lastModifyUser" })
public class RocketEQContent extends CommonEntity{
	
	/**
	 * 目标ID
	 */
	@Column(name="table_id")
	private String tableId;
	
	/**
	 * 目标对象
	 */
	@Column(name="class_name")
	private String className;
	
	/**
	 * 发起时间
	 */
	@Column(name="time")
	private long time;
	
	/**
	 * 类型
	 */
	@Column(name="type")
	private int type;
	
	/**
	 * 操作
	 */
	@Column(name="operating_user")
	private String operatingUser;
	
	/**
	 * 是否有效 (0有效  1无效)
	 */
	@Column(name="is_effective")
	private int isEffective;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getIsEffective() {
		return isEffective;
	}

	public void setIsEffective(int isEffective) {
		this.isEffective = isEffective;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getOperatingUser() {
		return operatingUser;
	}

	public void setOperatingUser(String operatingUser) {
		this.operatingUser = operatingUser;
	}

}