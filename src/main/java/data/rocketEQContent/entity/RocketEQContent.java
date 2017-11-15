package data.rocketEQContent.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 这个是RocketMQ 挂掉后 向数据库中保存数据
 * 
 * @author hutao
 *
 */
@Entity
@Table(name = "tb_sys_rocket_eq_content")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RocketEQContent {
	
	/**
	 * 目标ID
	 */
	@Column(name="id")
	private String id;
	
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
	 * 创建人
	 */
	@Column(name="create_user")
	private String createUser;
	
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

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public int getIsEffective() {
		return isEffective;
	}

	public void setIsEffective(int isEffective) {
		this.isEffective = isEffective;
	}

}