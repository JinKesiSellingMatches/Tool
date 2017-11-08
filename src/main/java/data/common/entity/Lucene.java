package data.common.entity;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;


/**
 * 不在生成索引/和toDTO 方法 
 * @author hutao
 * 2017/07/31
 */
@MappedSuperclass
public abstract class Lucene {

    /**
     * 主键
     */
    protected String id;

    /**
     * 创建人信息实体
     */
    protected String createUser;

    /**
     * 创建时间
     */
    protected Date createDate;

    /**
     * 最后修改人信息实体
     */
    protected String lastModifyUser;

    /**
     * 最后修改时间
     */
    protected Date lastModifyDate;

    /**
     * 锁版本
     */
    @Version
    protected Long version;

    /**
     * 是否删除标记
     */
    protected Integer deleted;

    public String getId() {

        return id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public Date getCreateDate() {

        return createDate;
    }

    public void setCreateDate(Date createDate) {

        this.createDate = createDate;
    }


    public Date getLastModifyDate() {

        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {

        this.lastModifyDate = lastModifyDate;
    }

    public Long getVersion() {

        return version;
    }

    public void setVersion(Long version) {

        this.version = version;
    }

    /**
     * @return the deleted
     */
    public Integer getDeleted() {

        return deleted;
    }

    /**
     * @param deleted
     *            the deleted to set
     */
    public void setDeleted(Integer deleted) {

        this.deleted = deleted;
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
}
