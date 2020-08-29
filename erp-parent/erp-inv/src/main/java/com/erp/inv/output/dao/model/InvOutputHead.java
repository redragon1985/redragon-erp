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
package com.erp.inv.output.dao.model;

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
@Table(name="inv_output_head", schema="erp")
@DynamicInsert(true)
@DynamicUpdate(true)
public class InvOutputHead implements java.io.Serializable {

    //serialVersionUID
    private static final long serialVersionUID = 1L;

    //Constructors
    public InvOutputHead() {
    }
    
    //Fields
    
    //主键
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "output_head_id", unique = true, nullable = false)
    private Integer outputHeadId;
    
    public Integer getOutputHeadId() {
        return this.outputHeadId;
    }
    public void setOutputHeadId(Integer outputHeadId) {
        this.outputHeadId = outputHeadId;
    }
    
    //出库单编码
    @NotBlank(message="出库单编码不能为空")
    @Column(name = "output_head_code", unique = true, nullable = false, length = 45)
    private String outputHeadCode;
    
    public String getOutputHeadCode() {
        return this.outputHeadCode;
    }
    public void setOutputHeadCode(String outputHeadCode) {
        this.outputHeadCode = outputHeadCode;
    }
    
    //出库类型（销售出库SO_OUT、购入退出PO_RETURN、盘点出库CHECK_OUT）
    @NotBlank(message="出库类型不能为空")
    @Column(name = "output_type", unique = false, nullable = false, length = 45)
    private String outputType;
    
    public String getOutputType() {
        return this.outputType;
    }
    public void setOutputType(String outputType) {
        this.outputType = outputType;
    }
    
    //出库来源类型（销售订单SO）
    @NotBlank(message="数据来源类型不能为空")
    @Column(name = "output_source_type", unique = false, nullable = false, length = 45)
    private String outputSourceType;
    
    public String getOutputSourceType() {
        return this.outputSourceType;
    }
    public void setOutputSourceType(String outputSourceType) {
        this.outputSourceType = outputSourceType;
    }
    
    //出库来源头编码（销售订单头编码）
    @NotBlank(message="来源单据编码不能为空")
    @Column(name = "output_source_head_code", unique = false, nullable = false, length = 45)
    private String outputSourceHeadCode;
    
    public String getOutputSourceHeadCode() {
        return this.outputSourceHeadCode;
    }
    public void setOutputSourceHeadCode(String outputSourceHeadCode) {
        this.outputSourceHeadCode = outputSourceHeadCode;
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
    
    //出库日期
    @NotNull(message="出库日期不能为空")
    @Column(name = "output_date", unique = false, nullable = false)
    private Date outputDate;
    
    public Date getOutputDate() {
        return this.outputDate;
    }
    public void setOutputDate(Date outputDate) {
        this.outputDate = outputDate;
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
    private String outputSourceHeadName;
    @Transient
    private String customerName;
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
    public String getWarehouseAddress() {
        return warehouseAddress;
    }
    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }
    public String getOutputSourceHeadName() {
        return outputSourceHeadName;
    }
    public void setOutputSourceHeadName(String outputSourceHeadName) {
        this.outputSourceHeadName = outputSourceHeadName;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getCustomerContactDesc() {
        return customerContactDesc;
    }
    public void setCustomerContactDesc(String customerContactDesc) {
        this.customerContactDesc = customerContactDesc;
    }
    
}