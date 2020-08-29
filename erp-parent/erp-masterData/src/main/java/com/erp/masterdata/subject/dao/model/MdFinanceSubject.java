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
package com.erp.masterdata.subject.dao.model;

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
@Table(name="md_finance_subject", schema="erp")
@DynamicInsert(true)
@DynamicUpdate(true)
public class MdFinanceSubject implements java.io.Serializable {

    //serialVersionUID
    private static final long serialVersionUID = 1L;

    //Constructors
    public MdFinanceSubject() {
    }
    
    //Fields
    
    //主键
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "subject_id", unique = true, nullable = false)
    private Integer subjectId;
    
    public Integer getSubjectId() {
        return this.subjectId;
    }
    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }
    
    //科目编码
    @NotBlank(message="{code.NotBlank}")
    @Column(name = "subject_code", unique = true, nullable = false, length = 45)
    private String subjectCode;
    
    public String getSubjectCode() {
        return this.subjectCode;
    }
    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }
    
    //科目名称
    @NotBlank(message="{name.NotBlank}")
    @Column(name = "subject_name", unique = true, nullable = false, length = 45)
    private String subjectName;
    
    public String getSubjectName() {
        return this.subjectName;
    }
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    
    //父科目编码
    @Column(name = "parent_subject_code", unique = false, nullable = true, length = 45)
    private String parentSubjectCode;
    
    public String getParentSubjectCode() {
        return this.parentSubjectCode;
    }
    public void setParentSubjectCode(String parentSubjectCode) {
        this.parentSubjectCode = parentSubjectCode;
    }
    
    //科目段值编码
    @Column(name = "segment_code", unique = true, nullable = false, length = 200)
    private String segmentCode;
    
    public String getSegmentCode() {
        return this.segmentCode;
    }
    public void setSegmentCode(String segmentCode) {
        this.segmentCode = segmentCode;
    }
    
    //科目段值描述
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