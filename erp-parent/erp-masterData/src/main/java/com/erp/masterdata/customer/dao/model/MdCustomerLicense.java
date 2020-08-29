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
package com.erp.masterdata.customer.dao.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="md_customer_license", schema="erp")
@DynamicInsert(true)
@DynamicUpdate(true)
public class MdCustomerLicense implements java.io.Serializable {

    //serialVersionUID
    private static final long serialVersionUID = 1L;

    //Constructors
    public MdCustomerLicense() {
    }
    
    //Fields
    
    //主键
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "license_id", unique = true, nullable = false)
    private Integer licenseId;
    
    public Integer getLicenseId() {
        return this.licenseId;
    }
    public void setLicenseId(Integer licenseId) {
        this.licenseId = licenseId;
    }
    
    //客户编码
    @Column(name = "customer_code", unique = false, nullable = false, length = 45)
    private String customerCode;
    
    public String getCustomerCode() {
        return this.customerCode;
    }
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
    
    //营业执照号
    @NotBlank(message="营业执照号必填")
    @Column(name = "license_number", unique = true, nullable = false, length = 45)
    private String licenseNumber;
    
    public String getLicenseNumber() {
        return this.licenseNumber;
    }
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }
    
    //法人代表
    @NotBlank(message="法人代表必填")
    @Column(name = "legal_person", unique = false, nullable = false, length = 45)
    private String legalPerson;
    
    public String getLegalPerson() {
        return this.legalPerson;
    }
    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }
    
    //公司类型
    @NotBlank(message="公司类型必填")
    @Column(name = "company_type", unique = false, nullable = false, length = 45)
    private String companyType;
    
    public String getCompanyType() {
        return this.companyType;
    }
    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }
    
    //经营范围
    //@NotBlank(message="经营范围必填")
    @Column(name = "business_scope", unique = false, nullable = false, length = 200)
    private String businessScope;
    
    public String getBusinessScope() {
        return this.businessScope;
    }
    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }
    
    //成立日期
    @NotNull(message="成立日期必填")
    @Column(name = "start_date", unique = false, nullable = false)
    private Date startDate;
    
    public Date getStartDate() {
        return this.startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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