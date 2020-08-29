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
package com.erp.masterdata.material.dao.model;

import java.util.Date;
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
@Table(name="md_material", schema="erp")
@DynamicInsert(true)
@DynamicUpdate(true)
public class MdMaterial implements java.io.Serializable {

    //serialVersionUID
    private static final long serialVersionUID = 1L;

    //Constructors
    public MdMaterial() {
    }
    
    //Fields
    
    //主键
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "material_id", unique = true, nullable = false)
    private Integer materialId;
    
    public Integer getMaterialId() {
        return this.materialId;
    }
    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }
    
    //物料编码
    @NotBlank(message="{code.NotBlank}")
    @Column(name = "material_code", unique = true, nullable = false, length = 45)
    private String materialCode;
    
    public String getMaterialCode() {
        return this.materialCode;
    }
    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }
    
    //物料名称
    @NotBlank(message="{name.NotBlank}")
    @Column(name = "material_name", unique = true, nullable = false, length = 45)
    private String materialName;
    
    public String getMaterialName() {
        return this.materialName;
    }
    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }
    
    //是物料或事项（MATERIAL、MATTER）
    @NotBlank(message="物料或事项必须选择")
    @Column(name = "material_type", unique = false, nullable = false, length = 45)
    private String materialType;
    
    public String getMaterialType() {
        return materialType;
    }
    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    //类别编码
    @NotBlank(message="物料类别不能为空")
    @Column(name = "category_code", unique = false, nullable = false, length = 45)
    private String categoryCode;
    
    public String getCategoryCode() {
        return this.categoryCode;
    }
    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }
    
    //物料单位
    //@NotBlank(message="物料单位不能为空")
    @Column(name = "material_unit", unique = false, nullable = false, length = 45)
    private String materialUnit;
    
    public String getMaterialUnit() {
        return this.materialUnit;
    }
    public void setMaterialUnit(String materialUnit) {
        this.materialUnit = materialUnit;
    }
    
    //效期（天数）
    @Column(name = "valid_day", unique = false, nullable = true)
    private Integer validDay;
    
    public Integer getValidDay() {
        return this.validDay;
    }
    public void setValidDay(Integer validDay) {
        this.validDay = validDay;
    }
    
    //规格
    //@NotBlank(message="规格不能为空")
    @Column(name = "standard", unique = false, nullable = false, length = 45)
    private String standard;
    
    public String getStandard() {
        return this.standard;
    }
    public void setStandard(String standard) {
        this.standard = standard;
    }
    
    //规格单位
    //@NotBlank(message="规格单位不能为空")
    @Column(name = "standard_unit", unique = false, nullable = false, length = 45)
    private String standardUnit;
    
    public String getStandardUnit() {
        return this.standardUnit;
    }
    public void setStandardUnit(String standardUnit) {
        this.standardUnit = standardUnit;
    }
    
    //包装规格
    @Column(name = "pack_standard", unique = false, nullable = true, length = 45)
    private String packStandard;
    
    public String getPackStandard() {
        return this.packStandard;
    }
    public void setPackStandard(String packStandard) {
        this.packStandard = packStandard;
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
    
    
    
    /*
     * 显示字段
     */
    @Transient
    private String categoryDesc;

    public String getCategoryDesc() {
        return categoryDesc;
    }
    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }
    
}