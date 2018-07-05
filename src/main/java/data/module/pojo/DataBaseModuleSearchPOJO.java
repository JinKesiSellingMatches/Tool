package data.module.pojo;

import java.math.BigInteger;

/**
 * 这个POJO用来接收sql 返回的字段唯一！！！！
 * @author hutao
 *
 */
public class DataBaseModuleSearchPOJO {
	
	//TODO 缺少模块
	
	/**
	 * 主键Id
	 */
	public String id;
	
	/**
	 * 创建时间
	 */
	public BigInteger createDate;
	
	/**
	 * 标题
	 */
	public String title;
	
	/**
	 * 内容
	 */
	public String content;
	
	/**
	 * 搜索
	 */
	public String search;
	
	/**
	 * 操作类型
	 * 
	 */
	public int type;
	
	/**
	 * 创建人
	 */
	public String createUser;
	
	/**
	 * 模块
	 */
	public String moduleCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public BigInteger getCreateDate() {
		return createDate;
	}

	public void setCreateDate(BigInteger createDate) {
		this.createDate = createDate;
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

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

}