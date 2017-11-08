package data.lucene.entity;

import java.util.Date;

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
    private Date createDate;

    /**
     * 最后修改人信息实体
     */
    private String lastModifyUser;

    /**
     * 最后修改时间
     */
    private Date lastModifyDate;
	
	/**
	 * 这里保存的是数据库直接的ID
	 */
    private String tableId;
    
    /**
     * 表名
     */
    private String tableName;
    
    /**
     *对应的entity  (必须带有包名)
     */
    private String tableNameEntity;
    
    /**
     * 搜索
     */
    private String serach;
    
    /**
     *所属模块                                                             
     */
    private String module;

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableNameEntity() {
		return tableNameEntity;
	}

	public void setTableNameEntity(String tableNameEntity) {
		this.tableNameEntity = tableNameEntity;
	}

	public String getSerach() {
		return serach;
	}

	public void setSerach(String serach) {
		this.serach = serach;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getLastModifyUser() {
		return lastModifyUser;
	}

	public void setLastModifyUser(String lastModifyUser) {
		this.lastModifyUser = lastModifyUser;
	}

	public Date getLastModifyDate() {
		return lastModifyDate;
	}

	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

}
