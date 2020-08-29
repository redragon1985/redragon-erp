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
package com.erp.masterdata.vendor.dao.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="md_vendor", schema="erp")
@DynamicInsert(true)
@DynamicUpdate(true)
public class MdVendor implements java.io.Serializable {

    //serialVersionUID
    private static final long serialVersionUID = 1L;

    //Constructors
    public MdVendor() {
    }
    
    //Fields
    
    //主键
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "vendor_id", unique = true, nullable = false)
    private Integer vendorId;
    
    public Integer getVendorId() {
        return this.vendorId;
    }
    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }
    
    //供应商编码
    @NotBlank(message="{code.NotBlank}")
    @Column(name = "vendor_code", unique = true, nullable = false, length = 45)
    private String vendorCode;
    
    public String getVendorCode() {
        return this.vendorCode;
    }
    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }
    
    //供应商名称
    @NotBlank(message="{name.NotBlank}")
    @Column(name = "vendor_name", unique = true, nullable = false, length = 45)
    private String vendorName;
    
    public String getVendorName() {
        return this.vendorName;
    }
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
    
    //供应商类型（公司、个人）
    @NotBlank(message="供应商类型不能为空")
    @Column(name = "vendor_type", unique = false, nullable = false, length = 45)
    private String vendorType;
    
    public String getVendorType() {
        return this.vendorType;
    }
    public void setVendorType(String vendorType) {
        this.vendorType = vendorType;
    }
    
    //供应商地址
    @Column(name = "vendor_address", unique = false, nullable = true, length = 100)
    private String vendorAddress;
    
    public String getVendorAddress() {
        return this.vendorAddress;
    }
    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }
    
    //供应商电话
    @Column(name = "vendor_telephone", unique = false, nullable = true, length = 45)
    private String vendorTelephone;
    
    public String getVendorTelephone() {
        return this.vendorTelephone;
    }
    public void setVendorTelephone(String vendorTelephone) {
        this.vendorTelephone = vendorTelephone;
    }
    
    //供应商国家
    @NotBlank(message="供应商国家不能为空")
    @Column(name = "vendor_country", unique = false, nullable = false, length = 45)
    private String vendorCountry;
    
    public String getVendorCountry() {
        return this.vendorCountry;
    }
    public void setVendorCountry(String vendorCountry) {
        this.vendorCountry = vendorCountry;
    }
    
    //供应商城市
    @Column(name = "vendor_city", unique = false, nullable = true, length = 45)
    private String vendorCity;
    
    public String getVendorCity() {
        return this.vendorCity;
    }
    public void setVendorCity(String vendorCity) {
        this.vendorCity = vendorCity;
    }
    
    //供应商类别
    @Column(name = "vendor_category", unique = false, nullable = true, length = 45)
    private String vendorCategory;
    
    public String getVendorCategory() {
        return this.vendorCategory;
    }
    public void setVendorCategory(String vendorCategory) {
        this.vendorCategory = vendorCategory;
    }
    
    //供应商标签
    @Column(name = "vendor_label", unique = false, nullable = true, length = 100)
    private String vendorLabel;
    
    public String getVendorLabel() {
        return this.vendorLabel;
    }
    public void setVendorLabel(String vendorLabel) {
        this.vendorLabel = vendorLabel;
    }
    
    //本公司标识
    @Column(name = "own_flag", unique = false, nullable = false, length = 1)
    private String ownFlag;
    
    public String getOwnFlag() {
        return ownFlag;
    }
    public void setOwnFlag(String ownFlag) {
        this.ownFlag = ownFlag;
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
    
    //审批状态（未提交UNSUBMIT、已提交SUBMIT、已审批APPROVE、已驳回REJECT）
    @Column(name = "approve_status", unique = false, nullable = false, length = 45)
    private String approveStatus;
    
    public String getApproveStatus() {
        return approveStatus;
    }
    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
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
    
    
    
    //显示字段
    //供应商联系人
    @Transient
    private List<MdVendorContact> mdVendorContactList;

    public List<MdVendorContact> getMdVendorContactList() {
        return mdVendorContactList;
    }
    public void setMdVendorContactList(List<MdVendorContact> mdVendorContactList) {
        this.mdVendorContactList = mdVendorContactList;
    }
    
}