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
package com.erp.inv.input.dao.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="inv_input_head", schema="erp")
@DynamicInsert(true)
@DynamicUpdate(true)
public class InvInputHead implements java.io.Serializable {

    //serialVersionUID
    private static final long serialVersionUID = 1L;

    //Constructors
    public InvInputHead() {
    }
    
    //Fields
    
    //主键
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "input_head_id", unique = true, nullable = false)
    private Integer inputHeadId;
    
    public Integer getInputHeadId() {
        return this.inputHeadId;
    }
    public void setInputHeadId(Integer inputHeadId) {
        this.inputHeadId = inputHeadId;
    }
    
    //入库单编码
    @NotBlank(message="入库单编码不能为空")
    @Column(name = "input_head_code", unique = true, nullable = false, length = 45)
    private String inputHeadCode;
    
    public String getInputHeadCode() {
        return this.inputHeadCode;
    }
    public void setInputHeadCode(String inputHeadCode) {
        this.inputHeadCode = inputHeadCode;
    }
    
    //入库类型（采购入库PO_IN、销售退回SO_RETURN、盘点入库CHECK_IN）
    @NotBlank(message="入库单类型不能为空")
    @Column(name = "input_type", unique = false, nullable = false, length = 45)
    private String inputType;
    
    public String getInputType() {
        return this.inputType;
    }
    public void setInputType(String inputType) {
        this.inputType = inputType;
    }
    
    //入库来源类型（采购订单PO）
    @NotBlank(message="数据来源不能为空")
    @Column(name = "input_source_type", unique = false, nullable = false, length = 45)
    private String inputSourceType;
    
    public String getInputSourceType() {
        return this.inputSourceType;
    }
    public void setInputSourceType(String inputSourceType) {
        this.inputSourceType = inputSourceType;
    }
    
    //入库来源头编码（采购订单头编码）
    @NotBlank(message="来源单据编码不能为空")
    @Column(name = "input_source_head_code", unique = false, nullable = false, length = 45)
    private String inputSourceHeadCode;
    
    public String getInputSourceHeadCode() {
        return this.inputSourceHeadCode;
    }
    public void setInputSourceHeadCode(String inputSourceHeadCode) {
        this.inputSourceHeadCode = inputSourceHeadCode;
    }
    
    //仓库编码
    @NotBlank(message="仓库选择不能为空")
    @Column(name = "warehouse_code", unique = false, nullable = false, length = 45)
    private String warehouseCode;
    
    public String getWarehouseCode() {
        return this.warehouseCode;
    }
    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }
    
    //入库日期
    @NotNull(message="入库日期不能为空")
    @Column(name = "input_date", unique = false, nullable = false)
    private Date inputDate;
    
    public Date getInputDate() {
        return this.inputDate;
    }
    public void setInputDate(Date inputDate) {
        this.inputDate = inputDate;
    }
    
    //备注
    @Column(name = "memo", unique = false, nullable = true, length = 200)
    private String memo;
    
    public String getMemo() {
        return this.memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
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
    @Column(name = "status", unique = false, nullable = false, length = 10)
    private String status;
    
    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
    //审批状态（未提交UNSUBMIT、已提交SUBMIT、已审批APPROVE、已驳回REJECT）
    @Column(name = "approve_status", unique = false, nullable = false, length = 10)
    private String approveStatus;
    
    public String getApproveStatus() {
        return this.approveStatus;
    }
    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }
    
    //制单人
    @NotBlank(message="制单人不能为空")
    @Column(name = "staff_code", unique = false, nullable = false, length = 45)
    private String staffCode;
    
    public String getStaffCode() {
        return this.staffCode;
    }
    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }
    
    //制单部门
    @NotBlank(message="制单部门不能为空")
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
    
    //最后修改日期
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
    @Transient
    private String staffName;
    @Transient
    private String departmentName;
    @Transient
    private String warehouseAddress;
    @Transient
    private String inputSourceHeadName;
    @Transient
    private String vendorName;
    @Transient
    private String vendorContactDesc;

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
    public String getWarehouseAddress() {
        return warehouseAddress;
    }
    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }
    public String getInputSourceHeadName() {
        return inputSourceHeadName;
    }
    public void setInputSourceHeadName(String inputSourceHeadName) {
        this.inputSourceHeadName = inputSourceHeadName;
    }
    public String getVendorName() {
        return vendorName;
    }
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
    public String getVendorContactDesc() {
        return vendorContactDesc;
    }
    public void setVendorContactDesc(String vendorContactDesc) {
        this.vendorContactDesc = vendorContactDesc;
    }
    
}