package com.erp.masterdata.vendor.dao.model;

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
@Table(name="md_vendor_contact", schema="erp")
@DynamicInsert(true)
@DynamicUpdate(true)
public class MdVendorContact implements java.io.Serializable {

    //serialVersionUID
    private static final long serialVersionUID = 1L;

    //Constructors
    public MdVendorContact() {
    }
    
    //Fields
    
    //主键
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "contact_id", unique = true, nullable = false)
    private Integer contactId;
    
    public Integer getContactId() {
        return this.contactId;
    }
    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }
    
    //供应商编码
    @Column(name = "vendor_code", unique = false, nullable = false, length = 45)
    private String vendorCode;
    
    public String getVendorCode() {
        return this.vendorCode;
    }
    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }
    
    //联系人
    @NotBlank(message="联系人不能为空")
    @Column(name = "contact_name", unique = false, nullable = false, length = 45)
    private String contactName;
    
    public String getContactName() {
        return this.contactName;
    }
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    
    //联系人电话
    @NotBlank(message="联系电话不能为空")
    @Column(name = "contact_telephone", unique = false, nullable = false, length = 45)
    private String contactTelephone;
    
    public String getContactTelephone() {
        return this.contactTelephone;
    }
    public void setContactTelephone(String contactTelephone) {
        this.contactTelephone = contactTelephone;
    }
    
    //联系人岗位
    @Column(name = "contact_position", unique = false, nullable = true, length = 45)
    private String contactPosition;
    
    public String getContactPosition() {
        return this.contactPosition;
    }
    public void setContactPosition(String contactPosition) {
        this.contactPosition = contactPosition;
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