package data.clinet.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import data.common.entity.CommonEntity;


/**
 * 这里保存了索引的关系
 * @author hutao
 *
 */
@Entity
@Table(name = "TB_TEST_PRODUCT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = { "createUser", "lastModifyUser" })
public class Product extends CommonEntity {
	
	/**
     * 名称
     */
    @Column(name = "code")
    private String code;
    
    /**
     * 名称
     */
    @Column(name = "NAME")
    private String name;
    
    /**
     * 客户ID
     */
    @Column(name = "client_id")
    private String clientId;

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

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
}