package data.lucene.entity;

import java.math.BigInteger;

public class LuceneNode{
	
	/**
     * 主键
     */
	private String id;

    /**
     * 创建人信息实体
     */
    private String createUser;

    /**
     * 创建时间
     */
    private BigInteger createDate;

    /**
     * 最后修改人信息实体
     */
    private String lastModifyUser;

    /**
     * 最后修改时间
     */
    private long lastModifyDate;
	
	/**
	 * 这里保存的是数据库直接的ID
	 */
    private String tableId;
    
    /**
     * 搜索
     */
    private String serach;
    
    /**
     *所属模块                                                             
     */
    private String moduleCode;
    
    /**
     * 标题
     */
    private String title;
    
    /**
     * 内容
     */
    private String content;

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}


	public String getSerach() {
		return serach;
	}

	public void setSerach(String serach) {
		this.serach = serach;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}


	public String getLastModifyUser() {
		return lastModifyUser;
	}

	public void setLastModifyUser(String lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}

	public long getLastModifyDate() {
		return lastModifyDate;
	}

	public void setLastModifyDate(long lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
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

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public BigInteger getCreateDate() {
		return createDate;
	}

	public void setCreateDate(BigInteger createDate) {
		this.createDate = createDate;
	}


}
