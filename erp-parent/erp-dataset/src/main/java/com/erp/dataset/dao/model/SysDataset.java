package com.erp.dataset.dao.model;

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
@Table(name="sys_dataset", schema="erp")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysDataset implements java.io.Serializable {

    //serialVersionUID
    private static final long serialVersionUID = 1L;

    //Constructors
    public SysDataset() {
    }
    
    //Fields
    
    //主键
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "dataset_id", unique = true, nullable = false)
    private Integer datasetId;
    
    public Integer getDatasetId() {
        return this.datasetId;
    }
    public void setDatasetId(Integer datasetId) {
        this.datasetId = datasetId;
    }
    
    //值集编码
    @NotBlank(message="{code.NotBlank}")
    @Column(name = "dataset_code", unique = false, nullable = false, length = 45)
    private String datasetCode;
    
    public String getDatasetCode() {
        return this.datasetCode;
    }
    public void setDatasetCode(String datasetCode) {
        this.datasetCode = datasetCode;
    }
    
    //值集名称
    @NotBlank(message="{name.NotBlank}")
    @Column(name = "dataset_name", unique = false, nullable = false, length = 45)
    private String datasetName;
    
    public String getDatasetName() {
        return this.datasetName;
    }
    public void setDatasetName(String datasetName) {
        this.datasetName = datasetName;
    }
    
    //值集类型编码
    @NotBlank(message="值集类型不能为空")
    @Column(name = "dataset_type_code", unique = false, nullable = false, length = 45)
    private String datasetTypeCode;
    
    public String getDatasetTypeCode() {
        return this.datasetTypeCode;
    }
    public void setDatasetTypeCode(String datasetTypeCode) {
        this.datasetTypeCode = datasetTypeCode;
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