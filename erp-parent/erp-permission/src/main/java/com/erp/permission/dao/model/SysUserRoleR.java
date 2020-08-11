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
@Table(name="sys_user_role_r", schema="erp")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysUserRoleR implements java.io.Serializable {

    //serialVersionUID
    private static final long serialVersionUID = 1L;

    //Constructors
    public SysUserRoleR() {
    }
    
    //Fields
    
    //主键
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ur_id", unique = true, nullable = false)
    private Integer urId;
    
    public Integer getUrId() {
        return this.urId;
    }
    public void setUrId(Integer urId) {
        this.urId = urId;
    }
    
    //用户名
    @Column(name = "username", unique = false, nullable = false, length = 30)
    private String username;
    
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
    //角色编码
    @Column(name = "role_code", unique = false, nullable = false, length = 20)
    private String roleCode;
    
    public String getRoleCode() {
        return this.roleCode;
    }
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
    
    //
    @Column(name = "created_date", unique = false, nullable = false)
    private Date createdDate;
    
    public Date getCreatedDate() {
        return this.createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
    //
    @Column(name = "created_by", unique = false, nullable = false, length = 30)
    private String createdBy;
    
    public String getCreatedBy() {
        return this.createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    
    //
    @Column(name = "last_updated_date", unique = false, nullable = true)
    private Date lastUpdatedDate;
    
    public Date getLastUpdatedDate() {
        return this.lastUpdatedDate;
    }
    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }
    
    //
    @Column(name = "last_updated_by", unique = false, nullable = true, length = 30)
    private String lastUpdatedBy;
    
    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
    
    
}