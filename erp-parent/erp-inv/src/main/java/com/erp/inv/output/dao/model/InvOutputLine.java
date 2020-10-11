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
@Table(name="inv_output_line", schema="erp")
@DynamicInsert(true)
@DynamicUpdate(true)
public class InvOutputLine implements java.io.Serializable {

    //serialVersionUID
    private static final long serialVersionUID = 1L;

    //Constructors
    public InvOutputLine() {
    }
    
    //Fields
    
    //主键
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "output_line_id", unique = true, nullable = false)
    private Integer outputLineId;
    
    public Integer getOutputLineId() {
        return this.outputLineId;
    }
    public void setOutputLineId(Integer outputLineId) {
        this.outputLineId = outputLineId;
    }
    
    //出库行编码
    @NotBlank(message="出库行编码不能为空")
    @Column(name = "output_line_code", unique = true, nullable = false, length = 45)
    private String outputLineCode;
    
    public String getOutputLineCode() {
        return this.outputLineCode;
    }
    public void setOutputLineCode(String outputLineCode) {
        this.outputLineCode = outputLineCode;
    }
    
    //出库头编码
    @Column(name = "output_head_code", unique = false, nullable = false, length = 45)
    private String outputHeadCode;
    
    public String getOutputHeadCode() {
        return this.outputHeadCode;
    }
    public void setOutputHeadCode(String outputHeadCode) {
        this.outputHeadCode = outputHeadCode;
    }
    
    //出库来源行编码（销售订单行编码）
    @NotBlank(message="来源单据行编码不能为空")
    @Column(name = "output_source_line_code", unique = false, nullable = false, length = 45)
    private String outputSourceLineCode;
    
    public String getOutputSourceLineCode() {
        return this.outputSourceLineCode;
    }
    public void setOutputSourceLineCode(String outputSourceLineCode) {
        this.outputSourceLineCode = outputSourceLineCode;
    }
    
    //物料编码
    @NotBlank(message="物料编码不能为空")
    @Column(name = "material_code", unique = false, nullable = false, length = 45)
    private String materialCode;
    
    public String getMaterialCode() {
        return materialCode;
    }
    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }
    
    //出库数量
    @NotNull(message="出库数量不能为空")
    @Column(name = "output_quantity", unique = false, nullable = false)
    private Double outputQuantity;
    
    public Double getOutputQuantity() {
        return this.outputQuantity;
    }
    public void setOutputQuantity(Double outputQuantity) {
        this.outputQuantity = outputQuantity;
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
    
    //状态
    @Column(name = "status", unique = false, nullable = false, length = 10)
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
    
    
    
    /*
         * 用于显示的字段
     */
    @Transient
    private String materialName;
    @Transient
    private String materialStandard;
    @Transient
    private Double price;
    @Transient
    private Double quantity;
    @Transient
    private Double outputedQuantity;
    @Transient
    private String unit;
    @Transient
    private Double soLineAmount;

    public String getMaterialName() {
        return materialName;
    }
    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }
    public String getMaterialStandard() {
        return materialStandard;
    }
    public void setMaterialStandard(String materialStandard) {
        this.materialStandard = materialStandard;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Double getQuantity() {
        return quantity;
    }
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public Double getSoLineAmount() {
        return soLineAmount;
    }
    public void setSoLineAmount(Double soLineAmount) {
        this.soLineAmount = soLineAmount;
    }
    public Double getOutputedQuantity() {
        return outputedQuantity;
    }
    public void setOutputedQuantity(Double outputedQuantity) {
        this.outputedQuantity = outputedQuantity;
    }

}