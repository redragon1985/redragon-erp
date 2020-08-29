/*
 * Copyright 2020-2021 redragon.dongbin
 *
 * This file is part of redragon-erp/赤龙ERP.

 * redragon-erp/赤龙ERP is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.

 * redragon-erp/赤龙ERP is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with redragon-erp/赤龙ERP.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.erp.hr.dao.model;

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
@Table(name="hr_staff", schema="erp")
@DynamicInsert(true)
@DynamicUpdate(true)
public class HrStaff implements java.io.Serializable {

    //serialVersionUID
    private static final long serialVersionUID = 1L;

    //Constructors
    public HrStaff() {
    }
    
    //Fields
    
    //主键
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "staff_id", unique = true, nullable = false)
    private Integer staffId;
    
    public Integer getStaffId() {
        return this.staffId;
    }
    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }
    
    //职员编码
    @NotBlank(message="{code.NotBlank}")
    @Column(name = "staff_code", unique = true, nullable = false, length = 45)
    private String staffCode;
    
    public String getStaffCode() {
        return this.staffCode;
    }
    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }
    
    //职员工号
    @Column(name = "staff_number", unique = true, nullable = false, length = 45)
    private String staffNumber;
    
    public String getStaffNumber() {
        return this.staffNumber;
    }
    public void setStaffNumber(String staffNumber) {
        this.staffNumber = staffNumber;
    }
    
    //职员名称
    @NotBlank(message="{name.NotBlank}")
    @Column(name = "staff_name", unique = false, nullable = false, length = 45)
    private String staffName;
    
    public String getStaffName() {
        return this.staffName;
    }
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
    
    //性别（MALE、FEMALE）
    @Column(name = "staff_sex", unique = false, nullable = true, length = 10)
    private String staffSex;
    
    public String getStaffSex() {
        return this.staffSex;
    }
    public void setStaffSex(String staffSex) {
        this.staffSex = staffSex;
    }
    
    //入职日期
    @Column(name = "staff_entry_date", unique = false, nullable = true)
    private Date staffEntryDate;
    
    public Date getStaffEntryDate() {
        return this.staffEntryDate;
    }
    public void setStaffEntryDate(Date staffEntryDate) {
        this.staffEntryDate = staffEntryDate;
    }
    
    //职员状态（WORK、LEAVE）
    @Column(name = "staff_status", unique = false, nullable = false, length = 10)
    private String staffStatus;
    
    public String getStaffStatus() {
        return this.staffStatus;
    }
    public void setStaffStatus(String staffStatus) {
        this.staffStatus = staffStatus;
    }
    
    //手机
    @Column(name = "staff_mobile", unique = false, nullable = true, length = 45)
    private String staffMobile;
    
    public String getStaffMobile() {
        return this.staffMobile;
    }
    public void setStaffMobile(String staffMobile) {
        this.staffMobile = staffMobile;
    }
    
    //邮箱
    @Column(name = "staff_email", unique = false, nullable = true, length = 45)
    private String staffEmail;
    
    public String getStaffEmail() {
        return this.staffEmail;
    }
    public void setStaffEmail(String staffEmail) {
        this.staffEmail = staffEmail;
    }
    
    //关联的用户名
    @NotBlank(message="关联的用户名不能为空")
    @Column(name = "username", unique = false, nullable = false, length = 45)
    private String username;
    
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
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
    @Column(name = "created_by", unique = false, nullable = false, length = 45)
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
    @Column(name = "last_updated_by", unique = false, nullable = true, length = 45)
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