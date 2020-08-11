package com.erp.permission.dao.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="sys_auth", schema="erp")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysAuth implements java.io.Serializable {

    //serialVersionUID
    private static final long serialVersionUID = 1L;

    //Constructors
    public SysAuth() {
    }
    
    //Fields
    
    //主键
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "auth_id", unique = true, nullable = false)
    private Integer authId;
    
    public Integer getAuthId() {
        return this.authId;
    }
    public void setAuthId(Integer authId) {
        this.authId = authId;
    }
    
    //权限编码
    @NotBlank(message="权限编码不能为空")
    @Column(name = "auth_code", unique = true, nullable = false, length = 20)
    private String authCode;
    
    public String getAuthCode() {
        return this.authCode;
    }
    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }
    
    //权限名称
    @NotBlank(message="权限名称不能为空")
    @Column(name = "auth_name", unique = true, nullable = false, length = 30)
    private String authName;
    
    public String getAuthName() {
        return this.authName;
    }
    public void setAuthName(String authName) {
        this.authName = authName;
    }
    
    //权限类型
    @NotBlank(message="权限类型不能为空")
    @Column(name = "auth_type", unique = false, nullable = false, length = 30)
    private String authType;
    
    public String getAuthType() {
        return this.authType;
    }
    public void setAuthType(String authType) {
        this.authType = authType;
    }
    
    //状态
    @Column(name = "status", unique = false, nullable = false, length = 1)
    private String status;
    
    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
    //创建时间
    @Column(name = "created_date", unique = false, nullable = false)
    private Date createdDate;
    
    public Date getCreatedDate() {
        return this.createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
    //创建人
    @Column(name = "created_by", unique = false, nullable = false, length = 30)
    private String createdBy;
    
    public String getCreatedBy() {
        return this.createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    //最后修改时间
    @Column(name = "last_updated_date", unique = false, nullable = true)
    private Date lastUpdatedDate;
    
    public Date getLastUpdatedDate() {
        return this.lastUpdatedDate;
    }
    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }
    
    //最后修改人
    @Column(name = "last_updated_by", unique = false, nullable = true, length = 30)
    private String lastUpdatedBy;
    
    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
    
    //组织机构
    @Column(name = "org_code", unique = false, nullable = false, length = 10)
    private String orgCode;
    
    public String getOrgCode() {
        return this.orgCode;
    }
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
    
    
}