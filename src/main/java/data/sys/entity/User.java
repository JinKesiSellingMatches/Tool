package data.sys.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import data.common.entity.CommonEntity;

/**
 * 
 * @Description: 系统用户表信息
 * @author rqf
 * @date 2016年8月19日 上午10:06:16 @v1.0
 */
@Entity
@Table(name = "TB_USER")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value = { "createUser", "lastModifyUser" })
public class User extends CommonEntity {

    /**
     *
     */  
    private static final long serialVersionUID = 6642426598224266537L;

    /**
     * 用户登录名
     */
    @Column(name = "USER_CODE", length = 100)
    private String userCode;

    /**
     * 用户登录密码
     */
    @Column(name = "USER_PASSWORD", length = 100)
    private String userPassword;

    /**
     * 用户真实姓名
     */
    @Column(name = "REAL_NAME", length = 100)
    private String realName;

    /**
     * 用户email
     */
    @Column(name = "EMAIL", length = 100)
    private String email;

    /**
     * 移动电话
     */
    @Column(name = "MOBILE", length = 100)
    private String mobile;

    /**
     * 用户类型 数据字典类型：userType 数据字典值：{'0001':'超级管理员','0002':'普通用户'}
     */
    @Column(name = "USER_TYPE", length = 100)
    private String userType;

    /**
     * 用户状态 数据字典类型：userStatus 数据字典值：{'0001':'启用','0002':'禁用'}
     */
    @Column(name = "USER_STATUS", length = 100)
    private String userStatus;

    /**
     * 角色
     */
    @Column(name = "ROLEID", length = 100)
    private String roleId;

    /**
     * 大区管理者标记 1：是
     */
    @Column(name = "AREA_MANAGER_SIGN", length = 100)
    private String areaManagerSign;
    
    /**
     * 部门
     */
    @Column(name="department",length = 64)
    private String department;
    
    /**
     * 钉钉ID
     */
    @Column(name = "dingId", length = 100)
    private String dingId;
    
    /**
     * 钉钉openId
     */
    @Column(name = "DING_OPEN_ID", length = 100)
    private String dingOpenId;
    
    /**
     * 钉钉unionid
     */
    @Column(name = "DING_UNION_ID", length = 100)
    private String dingUnionId;

    /**
     * 最后登录时间
     */
    @Column(name = "LAST_LOGIN_DATE", length = 100)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLoginDate;
    
    public String getUserCode() {

        return userCode;
    }

    public void setUserCode(String userCode) {

        this.userCode = userCode;
    }

    public String getUserPassword() {

        return userPassword;
    }

    public void setUserPassword(String userPassword) {

        this.userPassword = userPassword;
    }

    public String getRealName() {

        return realName;
    }

    public void setRealName(String realName) {

        this.realName = realName;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getMobile() {

        return mobile;
    }

    public void setMobile(String mobile) {

        this.mobile = mobile;
    }

    public String getUserType() {

        return userType;
    }

    public void setUserType(String userType) {

        this.userType = userType;
    }

    public String getUserStatus() {

        return userStatus;
    }

    public void setUserStatus(String userStatus) {

        this.userStatus = userStatus;
    }

    public String getRoleId() {

        return roleId;
    }

    public void setRoleId(String roleId) {

        this.roleId = roleId;
    }

    public String getAreaManagerSign() {

        return areaManagerSign;
    }

    public void setAreaManagerSign(String areaManagerSign) {

        this.areaManagerSign = areaManagerSign;
    }

    
    public String getDingId() {
    
        return dingId;
    }

    
    public void setDingId(String dingId) {
    
        this.dingId = dingId;
    }
    
    public Date getLastLoginDate() {
    
        return lastLoginDate;
    }

    
    public void setLastLoginDate(Date lastLoginDate) {
    
        this.lastLoginDate = lastLoginDate;
    }

    
    public String getDingOpenId() {
    
        return dingOpenId;
    }

    
    public void setDingOpenId(String dingOpenId) {
    
        this.dingOpenId = dingOpenId;
    }

    
    
    public String getDepartment() {
    
        return department;
    }

    
    public void setDepartment(String department) {
    
        this.department = department;
    }

    public String getDingUnionId() {
    
        return dingUnionId;
    }

    
    public void setDingUnionId(String dingUnionId) {
    
        this.dingUnionId = dingUnionId;
    }

}
