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
package com.erp.inv.check.dao.model;

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
@Table(name="inv_stock_check_line", schema="erp")
@DynamicInsert(true)
@DynamicUpdate(true)
public class InvStockCheckLine implements java.io.Serializable {

    //serialVersionUID
    private static final long serialVersionUID = 1L;

    //Constructors
    public InvStockCheckLine() {
    }
    
    //Fields
    
    //主键
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "check_line_id", unique = true, nullable = false)
    private Integer checkLineId;
    
    public Integer getCheckLineId() {
        return this.checkLineId;
    }
    public void setCheckLineId(Integer checkLineId) {
        this.checkLineId = checkLineId;
    }
    
    //盘点行编码
    @Column(name = "check_line_code", unique = true, nullable = false, length = 45)
    private String checkLineCode;
    
    public String getCheckLineCode() {
        return this.checkLineCode;
    }
    public void setCheckLineCode(String checkLineCode) {
        this.checkLineCode = checkLineCode;
    }
    
    //盘点头编码
    @Column(name = "check_head_code", unique = false, nullable = false, length = 45)
    private String checkHeadCode;
    
    public String getCheckHeadCode() {
        return this.checkHeadCode;
    }
    public void setCheckHeadCode(String checkHeadCode) {
        this.checkHeadCode = checkHeadCode;
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
    
    //盘点前数量
    @NotNull(message="盘点前数量不能为空")
    @Column(name = "check_before_quantity", unique = false, nullable = false)
    private Double checkBeforeQuantity;
    
    public Double getCheckBeforeQuantity() {
        return this.checkBeforeQuantity;
    }
    public void setCheckBeforeQuantity(Double checkBeforeQuantity) {
        this.checkBeforeQuantity = checkBeforeQuantity;
    }
    
    //盘点后数量
    @NotNull(message="盘点后数量不能为空")
    @Column(name = "check_after_quantity", unique = false, nullable = false)
    private Double checkAfterQuantity;
    
    public Double getCheckAfterQuantity() {
        return this.checkAfterQuantity;
    }
    public void setCheckAfterQuantity(Double checkAfterQuantity) {
        this.checkAfterQuantity = checkAfterQuantity;
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
    @Column(name = "status", unique = false, nullable = false, length = 45)
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