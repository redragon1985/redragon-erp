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
package com.erp.order.soa.dao.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@MappedSuperclass
public class SoAgreementHeadBase {

    //Constructors
    public SoAgreementHeadBase() {
    }
    
    //Fields
    
    //销售协议头id
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "so_head_id", unique = true, nullable = false)
    private Integer soHeadId;
    
    public Integer getSoHeadId() {
        return this.soHeadId;
    }
    public void setSoHeadId(Integer soHeadId) {
        this.soHeadId = soHeadId;
    }
    
    //销售协议头编码
    @NotBlank(message="{code.NotBlank}")
    @Column(name = "so_head_code", unique = true, nullable = false, length = 45)
    private String soHeadCode;
    
    public String getSoHeadCode() {
        return this.soHeadCode;
    }
    public void setSoHeadCode(String soHeadCode) {
        this.soHeadCode = soHeadCode;
    }
    
    //销售协议类型
    @NotBlank(message="销售协议类型不能为空")
    @Column(name = "so_type", unique = false, nullable = false, length = 45)
    private String soType;
    
    public String getSoType() {
        return this.soType;
    }
    public void setSoType(String soType) {
        this.soType = soType;
    }
    
    //销售协议名称
    @NotBlank(message="{name.NotBlank}")
    @Column(name = "so_name", unique = true, nullable = false, length = 45)
    private String soName;
    
    public String getSoName() {
        return this.soName;
    }
    public void setSoName(String soName) {
        this.soName = soName;
    }
    
    //销售协议描述
    @Column(name = "so_desc", unique = false, nullable = true, length = 500)
    private String soDesc;
    
    public String getSoDesc() {
        return this.soDesc;
    }
    public void setSoDesc(String soDesc) {
        this.soDesc = soDesc;
    }
    
    //项目编码
    @Column(name = "project_code", unique = false, nullable = true, length = 45)
    private String projectCode;
    
    public String getProjectCode() {
        return this.projectCode;
    }
    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }
    
    //客户编码
    @NotBlank(message="客户不能为空")
    @Column(name = "customer_code", unique = false, nullable = false, length = 45)
    private String customerCode;
    
    public String getCustomerCode() {
        return this.customerCode;
    }
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
    
    //销售协议币种
    @NotBlank(message="币种不能为空")
    @Column(name = "currency_code", unique = false, nullable = false, length = 45)
    private String currencyCode;
    
    public String getCurrencyCode() {
        return this.currencyCode;
    }
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
    
    //销售协议金额
    @Column(name = "soa_amount", unique = false, nullable = false)
    private Double soaAmount;
    
    public Double getSoaAmount() {
        return this.soaAmount;
    }
    public void setSoaAmount(Double soaAmount) {
        this.soaAmount = soaAmount;
    }
    
    //销售协议预收款金额
    @Column(name = "pre_receipt_amount", unique = false, nullable = false)
    private Double preReceiptAmount;
    
    public Double getPreReceiptAmount() {
        return this.preReceiptAmount;
    }
    public void setPreReceiptAmount(Double preReceiptAmount) {
        this.preReceiptAmount = preReceiptAmount;
    }
    
    //销售协议开始日期
    @Column(name = "start_date", unique = false, nullable = true)
    private Date startDate;
    
    public Date getStartDate() {
        return this.startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    //销售协议结束日期
    @Column(name = "end_date", unique = false, nullable = true)
    private Date endDate;
    
    public Date getEndDate() {
        return this.endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    //销售协议签订日期
    @NotNull(message="签订日期不能为空")
    @Column(name = "sign_date", unique = false, nullable = false)
    private Date signDate;
    
    public Date getSignDate() {
        return this.signDate;
    }
    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }
    
    //计税类型
    @Column(name = "tax_type", unique = false, nullable = true, length = 45)
    private String taxType;
    
    public String getTaxType() {
        return this.taxType;
    }
    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }
    
    //计税比率
    @Column(name = "tax_percent", unique = false, nullable = true)
    private Double taxPercent;
    
    public Double getTaxPercent() {
        return this.taxPercent;
    }
    public void setTaxPercent(Double taxPercent) {
        this.taxPercent = taxPercent;
    }
    
    //版本
    @Column(name = "version", unique = false, nullable = false)
    private Integer version;
    
    public Integer getVersion() {
        return this.version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }
    
    //状态（新建NEW，确认CONFIRM，取消CANCEL）
    @NotBlank(message="状态不能为空")
    @Column(name = "status", unique = false, nullable = false, length = 10)
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
        return this.approveStatus;
    }
    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }
    
    //发运状态（未出库N，已出库Y，部分出库PART）
    @Column(name = "shipment_status", unique = false, nullable = true, length = 10)
    private String shipmentStatus;
    
    public String getShipmentStatus() {
        return this.shipmentStatus;
    }
    public void setShipmentStatus(String shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }
    
    //收款状态（未收款N，已收款Y，部分收款PART）
    @Column(name = "receipt_status", unique = false, nullable = true, length = 10)
    private String receiptStatus;
    
    public String getReceiptStatus() {
        return this.receiptStatus;
    }
    public void setReceiptStatus(String receiptStatus) {
        this.receiptStatus = receiptStatus;
    }
    
    //销售员
    @Column(name = "staff_code", unique = false, nullable = false, length = 45)
    private String staffCode;
    
    public String getStaffCode() {
        return this.staffCode;
    }
    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }
    
    //销售部门
    @Column(name = "department_code", unique = false, nullable = false, length = 45)
    private String departmentCode;
    
    public String getDepartmentCode() {
        return this.departmentCode;
    }
    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
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
    
    
    
    /*
         * 用于展示的字段
     */
    @Transient
    private String staffName;
    @Transient
    private String departmentName;
    @Transient
    private Double amount;
    @Transient
    private String customerContactDesc;

    public String getStaffName() {
        return staffName;
    }
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
    public String getDepartmentName() {
        return departmentName;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public String getCustomerContactDesc() {
        return customerContactDesc;
    }
    public void setCustomerContactDesc(String customerContactDesc) {
        this.customerContactDesc = customerContactDesc;
    }
    
}