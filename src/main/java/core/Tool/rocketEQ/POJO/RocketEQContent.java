package core.Tool.rocketEQ.POJO;


/**
 * 消息队列发送/接收消息标准格式
 * 只对 insert,update,deleted有效
 * 
 * @author hutao
 *
 */
public class RocketEQContent {
	
	/**
	 * 目标ID
	 */
	private String id;
	
	/**
	 * 目标对象
	 */
	private String className;
	
	/**
	 * 发起时间
	 */
	private long time;

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

}
