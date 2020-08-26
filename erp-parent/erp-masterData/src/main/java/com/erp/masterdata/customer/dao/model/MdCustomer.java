package com.erp.masterdata.customer.dao.model;

import java.util.Date;
import java.util.List;

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
@Table(name="md_customer", schema="erp")
@DynamicInsert(true)
@DynamicUpdate(true)
public class MdCustomer implements java.io.Serializable {

    //serialVersionUID
    private static final long serialVersionUID = 1L;

    //Constructors
    public MdCustomer() {
    }
    
    //Fields
    
    //主键
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "customer_id", unique = true, nullable = false)
    private Integer customerId;
    
    public Integer getCustomerId() {
        return this.customerId;
    }
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    
    //客户编码
    @NotBlank(message="{code.NotBlank}")
    @Column(name = "customer_code", unique = true, nullable = false, length = 45)
    private String customerCode;
    
    public String getCustomerCode() {
        return this.customerCode;
    }
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
    
    //客户名称
    @NotBlank(message="{name.NotBlank}")
    @Column(name = "customer_name", unique = true, nullable = false, length = 45)
    private String customerName;
    
    public String getCustomerName() {
        return this.customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    //客户类型（个人、公司）
    @Column(name = "customer_type", unique = false, nullable = false, length = 45)
    private String customerType;
    
    public String getCustomerType() {
        return this.customerType;
    }
    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }
    
    //客户地址
    @Column(name = "customer_address", unique = false, nullable = true, length = 100)
    private String customerAddress;
    
    public String getCustomerAddress() {
        return this.customerAddress;
    }
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
    
    //客户电话
    @Column(name = "customer_telephone", unique = false, nullable = true, length = 45)
    private String customerTelephone;
    
    public String getCustomerTelephone() {
        return this.customerTelephone;
    }
    public void setCustomerTelephone(String customerTelephone) {
        this.customerTelephone = customerTelephone;
    }
    
    //客户国家
    @NotBlank(message="客户国家必填")
    @Column(name = "customer_country", unique = false, nullable = false, length = 45)
    private String customerCountry;
    
    public String getCustomerCountry() {
        return this.customerCountry;
    }
    public void setCustomerCountry(String customerCountry) {
        this.customerCountry = customerCountry;
    }
    
    //客户城市
    @Column(name = "customer_city", unique = false, nullable = true, length = 45)
    private String customerCity;
    
    public String getCustomerCity() {
        return this.customerCity;
    }
    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }
    
    //客户类型
    @Column(name = "customer_category", unique = false, nullable = true, length = 45)
    private String customerCategory;
    
    public String getCustomerCategory() {
        return this.customerCategory;
    }
    public void setCustomerCategory(String customerCategory) {
        this.customerCategory = customerCategory;
    }
    
    //客户标签
    @Column(name = "customer_label", unique = false, nullable = true, length = 100)
    private String customerLabel;
    
    public String getCustomerLabel() {
        return this.customerLabel;
    }
    public void setCustomerLabel(String customerLabel) {
        this.customerLabel = customerLabel;
    }
    
    //本公司标识
    @Column(name = "own_flag", unique = false, nullable = false, length = 1)
    private String ownFlag;
    
    public String getOwnFlag() {
        return ownFlag;
    }
    public void setOwnFlag(String ownFlag) {
        this.ownFlag = ownFlag;
    }

    //客户状态
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
    
    
    
    //显示字段
    //客户联系人
    @Transient
    private List<MdCustomerContact> mdCustomerContactList;

    public List<MdCustomerContact> getMdCustomerContactList() {
        return mdCustomerContactList;
    }
    public void setMdCustomerContactList(List<MdCustomerContact> mdCustomerContactList) {
        this.mdCustomerContactList = mdCustomerContactList;
    }
    
}