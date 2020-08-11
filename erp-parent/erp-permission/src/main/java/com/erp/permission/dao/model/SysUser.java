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
import org.springframework.format.annotation.DateTimeFormat;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="sys_user", schema="erp")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysUser implements java.io.Serializable {

    //serialVersionUID
    private static final long serialVersionUID = 1L;

    //Constructors
    public SysUser() {
    }
    
    //Fields
    
    //主键
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    private Integer userId;
    
    public Integer getUserId() {
        return this.userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    //用户名
    @NotBlank(message="{username.NotBlank}")
    @Length(min=3, message="{username.Length}")
    @Column(name = "username", unique = true, nullable = false, length = 30)
    private String username;
    
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
    //密码
    @NotBlank(message="{password.NotBlank}")
    @Length(min=6, message="{password.Length}")
    @Column(name = "password", unique = false, nullable = false, length = 50)
    private String password;
    
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
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
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
    
    //修改时间
    @Column(name = "last_updated_date", unique = false, nullable = true)
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdatedDate;
    
    public Date getLastUpdatedDate() {
        return this.lastUpdatedDate;
    }
    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }
    
    //修改人
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