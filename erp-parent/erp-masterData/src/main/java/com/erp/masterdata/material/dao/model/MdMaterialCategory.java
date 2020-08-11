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
package com.erp.masterdata.material.dao.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="md_material_category", schema="erp")
@DynamicInsert(true)
@DynamicUpdate(true)
public class MdMaterialCategory implements java.io.Serializable {

    //serialVersionUID
    private static final long serialVersionUID = 1L;

    //Constructors
    public MdMaterialCategory() {
    }
    
    //Fields
    
    //主键
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "category_id", unique = true, nullable = false)
    private Integer categoryId;
    
    public Integer getCategoryId() {
        return this.categoryId;
    }
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    
    //类别编码
    @NotBlank(message="{code.NotBlank}")
    @Column(name = "category_code", unique = true, nullable = false, length = 45)
    private String categoryCode;
    
    public String getCategoryCode() {
        return this.categoryCode;
    }
    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }
    
    //类别名称
    @NotBlank(message="{name.NotBlank}")
    @Column(name = "category_name", unique = true, nullable = false, length = 45)
    private String categoryName;
    
    public String getCategoryName() {
        return this.categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    //父类别编码
    @Column(name = "parent_category_code", unique = false, nullable = true, length = 45)
    private String parentCategoryCode;
    
    public String getParentCategoryCode() {
        return this.parentCategoryCode;
    }
    public void setParentCategoryCode(String parentCategoryCode) {
        this.parentCategoryCode = parentCategoryCode;
    }
    
    //类别段值编码
    @Column(name = "segment_code", unique = true, nullable = false, length = 200)
    private String segmentCode;
    
    public String getSegmentCode() {
        return this.segmentCode;
    }
    public void setSegmentCode(String segmentCode) {
        this.segmentCode = segmentCode;
    }
    
    //类别段值描述
    @Column(name = "segment_desc", unique = true, nullable = false, length = 200)
    private String segmentDesc;
    
    public String getSegmentDesc() {
        return this.segmentDesc;
    }
    public void setSegmentDesc(String segmentDesc) {
        this.segmentDesc = segmentDesc;
    }
    
    //排序
    @Column(name = "sort", unique = false, nullable = false)
    private Integer sort;
    
    public Integer getSort() {
        return this.sort;
    }
    public void setSort(Integer sort) {
        this.sort = sort;
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