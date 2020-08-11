package com.erp.hr.dao.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import com.framework.annotation.NotCountField;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="hr_department", schema="erp")
@DynamicInsert(true)
@DynamicUpdate(true)
public class HrDepartment implements java.io.Serializable {

    //serialVersionUID
    private static final long serialVersionUID = 1L;

    //Constructors
    public HrDepartment() {
    }
    
    //Fields
    
    //主键
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "department_id", unique = true, nullable = false)
    private Integer departmentId;
    
    public Integer getDepartmentId() {
        return this.departmentId;
    }
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
    
    //部门编码
    @NotCountField
    @NotBlank(message="{code.NotBlank}")
    @Column(name = "department_code", unique = true, nullable = false, length = 45)
    private String departmentCode;
    
    public String getDepartmentCode() {
        return this.departmentCode;
    }
    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }
    
    //部门名称
    @NotBlank(message="{name.NotBlank}")
    @Column(name = "department_name", unique = true, nullable = false, length = 45)
    private String departmentName;
    
    public String getDepartmentName() {
        return this.departmentName;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    
    //父部门编码
    @Column(name = "parent_department_code", unique = false, nullable = true, length = 45)
    private String parentDepartmentCode;
    
    public String getParentDepartmentCode() {
        return this.parentDepartmentCode;
    }
    public void setParentDepartmentCode(String parentDepartmentCode) {
        this.parentDepartmentCode = parentDepartmentCode;
    }
    
    //部门段值编码
    @Column(name = "segment_code", unique = true, nullable = false, length = 200)
    private String segmentCode;
    
    public String getSegmentCode() {
        return this.segmentCode;
    }
    public void setSegmentCode(String segmentCode) {
        this.segmentCode = segmentCode;
    }
    
    //部门段值描述
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