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
import org.hibernate.validator.constraints.Length;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@MappedSuperclass
public class SoAgreementLineBase {

    //Constructors
    public SoAgreementLineBase() {
    }
    
    //Fields
    
    //销售协议行id
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "so_line_id", unique = true, nullable = false)
    private Integer soLineId;
    
    public Integer getSoLineId() {
        return this.soLineId;
    }
    public void setSoLineId(Integer soLineId) {
        this.soLineId = soLineId;
    }
    
    //销售协议行编码
    @NotBlank(message="{code.NotBlank}")
    @Column(name = "so_line_code", unique = true, nullable = false, length = 45)
    private String soLineCode;
    
    public String getSoLineCode() {
        return this.soLineCode;
    }
    public void setSoLineCode(String soLineCode) {
        this.soLineCode = soLineCode;
    }
    
    //销售协议头编码
    @NotBlank(message="头编码不能为空")
    @Column(name = "so_head_code", unique = false, nullable = false, length = 45)
    private String soHeadCode;
    
    public String getSoHeadCode() {
        return this.soHeadCode;
    }
    public void setSoHeadCode(String soHeadCode) {
        this.soHeadCode = soHeadCode;
    }
    
    //物料编码
    @NotBlank(message="物料不能为空")
    @Column(name = "material_code", unique = false, nullable = false, length = 45)
    private String materialCode;
    
    public String getMaterialCode() {
        return this.materialCode;
    }
    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }
    
    //数量
    @Column(name = "quantity", unique = false, nullable = true)
    private Double quantity;
    
    public Double getQuantity() {
        return this.quantity;
    }
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
    
    //单价
    @Column(name = "price", unique = false, nullable = true)
    private Double price;
    
    public Double getPrice() {
        return this.price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    
    //金额
    @Column(name = "amount", unique = false, nullable = true)
    private Double amount;
    
    public Double getAmount() {
        return this.amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
    //单位
    @NotBlank(message="单位不能为空")
    @Column(name = "unit", unique = false, nullable = false, length = 45)
    private String unit;
    
    public String getUnit() {
        return this.unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    //摘要
    @Column(name = "memo", unique = false, nullable = true, length = 500)
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
    
    
    
    //用于显示的字段
    //出库数量
    @Transient
    private Double outputQuantity;
    //协议完成行数量
    @Transient
    private Double agreementFinishQuantity;

    public Double getOutputQuantity() {
        return outputQuantity;
    }
    public void setOutputQuantity(Double outputQuantity) {
        this.outputQuantity = outputQuantity;
    }
    public Double getAgreementFinishQuantity() {
        return agreementFinishQuantity;
    }
    public void setAgreementFinishQuantity(Double agreementFinishQuantity) {
        this.agreementFinishQuantity = agreementFinishQuantity;
    }
    
}