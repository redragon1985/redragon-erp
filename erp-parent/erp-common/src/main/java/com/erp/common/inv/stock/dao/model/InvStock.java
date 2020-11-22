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
package com.erp.common.inv.stock.dao.model;

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

@Entity(name="invStockCommon")
@Table(name="inv_stock", schema="erp")
@DynamicInsert(true)
@DynamicUpdate(true)
public class InvStock implements java.io.Serializable {

    //serialVersionUID
    private static final long serialVersionUID = 1L;

    //Constructors
    public InvStock() {
    }
    
    //Fields
    
    //主键
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "stock_id", unique = true, nullable = false)
    private Integer stockId;
    
    public Integer getStockId() {
        return this.stockId;
    }
    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }
    
    //库存记录编码
    @Column(name = "stock_code", unique = true, nullable = false, length = 45)
    private String stockCode;
    
    public String getStockCode() {
        return this.stockCode;
    }
    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }
    
    //仓库编码
    @NotBlank(message="仓库编码不能为空")
    @Column(name = "warehouse_code", unique = false, nullable = false, length = 45)
    private String warehouseCode;
    
    public String getWarehouseCode() {
        return this.warehouseCode;
    }
    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }
    
    //物料编码
    @NotBlank(message="物料编码不能为空")
    @Column(name = "material_code", unique = false, nullable = false, length = 45)
    private String materialCode;
    
    public String getMaterialCode() {
        return this.materialCode;
    }
    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }
    
    //库存数量
    @NotNull(message="数量不能为空")
    @Column(name = "stock_number", unique = false, nullable = false)
    private Double stockNumber;
    
    public Double getStockNumber() {
        return this.stockNumber;
    }
    public void setStockNumber(Double stockNumber) {
        this.stockNumber = stockNumber;
    }
    
    //保留标识（用于出库时的库存保留判定）
    @Column(name = "retain_flag", unique = false, nullable = false, length = 1)
    private String retainFlag;
    
    public String getRetainFlag() {
        return this.retainFlag;
    }
    public void setRetainFlag(String retainFlag) {
        this.retainFlag = retainFlag;
    }
    
    //关联单据类型（入库INPUT、出库OUTPUT）
    @Column(name = "bill_type", unique = false, nullable = true, length = 45)
    private String billType;
    
    public String getBillType() {
        return this.billType;
    }
    public void setBillType(String billType) {
        this.billType = billType;
    }
    
    //关联单据头编码（入库、出库）
    @Column(name = "bill_head_code", unique = false, nullable = true, length = 45)
    private String billHeadCode;
    
    public String getBillHeadCode() {
        return this.billHeadCode;
    }
    public void setBillHeadCode(String billHeadCode) {
        this.billHeadCode = billHeadCode;
    }
    
    //关联单据行编码（入库、出库）
    @Column(name = "bill_line_code", unique = false, nullable = true, length = 45)
    private String billLineCode;
    
    public String getBillLineCode() {
        return this.billLineCode;
    }
    public void setBillLineCode(String billLineCode) {
        this.billLineCode = billLineCode;
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
    
    //状态
    @Column(name = "status", unique = false, nullable = false, length = 1)
    private String status;
    
    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
    //职员编码
    @Column(name = "staff_code", unique = false, nullable = false, length = 45)
    private String staffCode;
    
    public String getStaffCode() {
        return this.staffCode;
    }
    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }
    
    //部门编码
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
    
    
    
    //显示的字段
    @Transient
    private String materialStandard;
    @Transient
    private String materialUnit;
    @Transient
    private String materialName;

    public String getMaterialStandard() {
        return materialStandard;
    }
    public void setMaterialStandard(String materialStandard) {
        this.materialStandard = materialStandard;
    }
    public String getMaterialUnit() {
        return materialUnit;
    }
    public void setMaterialUnit(String materialUnit) {
        this.materialUnit = materialUnit;
    }
    public String getMaterialName() {
        return materialName;
    }
    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }
    
}