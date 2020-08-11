package com.erp.masterdata.customer.dao.model;

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
@Table(name="md_customer_bank", schema="erp")
@DynamicInsert(true)
@DynamicUpdate(true)
public class MdCustomerBank implements java.io.Serializable {

    //serialVersionUID
    private static final long serialVersionUID = 1L;

    //Constructors
    public MdCustomerBank() {
    }
    
    //Fields
    
    //主键
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "bank_id", unique = true, nullable = false)
    private Integer bankId;
    
    public Integer getBankId() {
        return this.bankId;
    }
    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }
    
    //客户编码
    @Column(name = "customer_code", unique = false, nullable = false, length = 45)
    private String customerCode;
    
    public String getCustomerCode() {
        return this.customerCode;
    }
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
    
    //银行编码
    @NotBlank(message="客户银行必填")
    @Column(name = "bank_code", unique = false, nullable = false, length = 45)
    private String bankCode;
    
    public String getBankCode() {
        return this.bankCode;
    }
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }
    
    //分行编码
    @Column(name = "sub_bank_code", unique = false, nullable = true, length = 45)
    private String subBankCode;
    
    public String getSubBankCode() {
        return this.subBankCode;
    }
    public void setSubBankCode(String subBankCode) {
        this.subBankCode = subBankCode;
    }
    
    //银行账户
    @NotBlank(message="银行账户必填")
    @Column(name = "bank_account", unique = false, nullable = false, length = 45)
    private String bankAccount;
    
    public String getBankAccount() {
        return this.bankAccount;
    }
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
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