/*
 * Copyright 2020-2021 redragon.dongbin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
@Table(name="inv_input_line", schema="erp")
@DynamicInsert(true)
@DynamicUpdate(true)
public class InvInputLine implements java.io.Serializable {

    //serialVersionUID
    private static final long serialVersionUID = 1L;

    //Constructors
    public InvInputLine() {
    }
    
    //Fields
    
    //主键
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "input_line_id", unique = true, nullable = false)
    private Integer inputLineId;
    
    public Integer getInputLineId() {
        return this.inputLineId;
    }
    public void setInputLineId(Integer inputLineId) {
        this.inputLineId = inputLineId;
    }
    
    //入库行编码
    @NotBlank(message="入库行编码不能为空")
    @Column(name = "input_line_code", unique = true, nullable = false, length = 45)
    private String inputLineCode;
    
    public String getInputLineCode() {
        return this.inputLineCode;
    }
    public void setInputLineCode(String inputLineCode) {
        this.inputLineCode = inputLineCode;
    }
    
    //入库头编码
    @Column(name = "input_head_code", unique = false, nullable = false, length = 45)
    private String inputHeadCode;
    
    public String getInputHeadCode() {
        return this.inputHeadCode;
    }
    public void setInputHeadCode(String inputHeadCode) {
        this.inputHeadCode = inputHeadCode;
    }
    
    //入库来源行编码（采购订单行编码）
    @NotBlank(message="来源单据行编码不能为空")
    @Column(name = "input_source_line_code", unique = false, nullable = false, length = 45)
    private String inputSourceLineCode;
    
    public String getInputSourceLineCode() {
        return this.inputSourceLineCode;
    }
    public void setInputSourceLineCode(String inputSourceLineCode) {
        this.inputSourceLineCode = inputSourceLineCode;
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
    
    //入库数量
    @NotNull(message="入库数量不能为空")
    @Column(name = "input_quantity", unique = false, nullable = false)
    private Double inputQuantity;
    
    public Double getInputQuantity() {
        return this.inputQuantity;
    }
    public void setInputQuantity(Double inputQuantity) {
        this.inputQuantity = inputQuantity;
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
    private String unit;
    @Transient
    private Double poLineAmount;
    
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
    public Double getPoLineAmount() {
        return poLineAmount;
    }
    public void setPoLineAmount(Double poLineAmount) {
        this.poLineAmount = poLineAmount;
    }

}