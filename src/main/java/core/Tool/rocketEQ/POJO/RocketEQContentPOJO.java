package core.Tool.rocketEQ.POJO;


/**
 * 消息队列发送/接收消息标准格式
 * 只对 insert,update,deleted有效
 * 
 * @author hutao
 *
 */
public class RocketEQContentPOJO {
	
	/**
	 * 目标ID
	 */
	private String tableId;
	
	/**
	 * 目标对象
	 */
	private String className;
	
	/**
	 * 发起时间
	 */
	private long time;
	
	/**
	 * 类型
	 */
	private int type;
	
	/**
	 * 创建人
	 */
	private String operatingUser;
	
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
