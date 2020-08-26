package com.erp.order.po.dao.model;

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
@Table(name="po_head", schema="erp")
@DynamicInsert(true)
@DynamicUpdate(true)
public class PoHead implements java.io.Serializable {

    //serialVersionUID
    private static final long serialVersionUID = 1L;

    //Constructors
    public PoHead() {
    }
    
    //Fields
    
    //采购订单头id
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "po_head_id", unique = true, nullable = false)
    private Integer poHeadId;
    
    public Integer getPoHeadId() {
        return this.poHeadId;
    }
    public void setPoHeadId(Integer poHeadId) {
        this.poHeadId = poHeadId;
    }
    
    //采购订单头编码
    @NotBlank(message="{code.NotBlank}")
    @Column(name = "po_head_code", unique = true, nullable = false, length = 45)
    private String poHeadCode;
    
    public String getPoHeadCode() {
        return this.poHeadCode;
    }
    public void setPoHeadCode(String poHeadCode) {
        this.poHeadCode = poHeadCode;
    }
    
    //采购订单类型
    @NotBlank(message="采购订单类型不能为空")
    @Column(name = "po_type", unique = false, nullable = false, length = 45)
    private String poType;
    
    public String getPoType() {
        return this.poType;
    }
    public void setPoType(String poType) {
        this.poType = poType;
    }
    
    //采购订单名称
    @NotBlank(message="{name.NotBlank}")
    @Column(name = "po_name", unique = true, nullable = false, length = 45)
    private String poName;
    
    public String getPoName() {
        return this.poName;
    }
    public void setPoName(String poName) {
        this.poName = poName;
    }
    
    //采购订单描述
    @Column(name = "po_desc", unique = false, nullable = true, length = 500)
    private String poDesc;
    
    public String getPoDesc() {
        return this.poDesc;
    }
    public void setPoDesc(String poDesc) {
        this.poDesc = poDesc;
    }
    
    //项目编码
    @Column(name = "project_code", unique = false, nullable = true, length = 45)
    private String projectCode;
    
    public String getProjectCode() {
        return projectCode;
    }
    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    //供应商编码
    @NotBlank(message="供应商不能为空")
    @Column(name = "vendor_code", unique = false, nullable = false, length = 45)
    private String vendorCode;
    
    public String getVendorCode() {
        return this.vendorCode;
    }
    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }
    
    //采购订单币种
    @NotBlank(message="币种不能为空")
    @Column(name = "currency_code", unique = false, nullable = false, length = 45)
    private String currencyCode;
    
    public String getCurrencyCode() {
        return this.currencyCode;
    }
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
    
    //采购订单预付款金额
    @Column(name = "prepay_amount", unique = false, nullable = false)
    private Double prepayAmount;
    
    public Double getPrepayAmount() {
        return this.prepayAmount;
    }
    public void setPrepayAmount(Double prepayAmount) {
        this.prepayAmount = prepayAmount;
    }
    
    //采购订单开始日期
    @Column(name = "start_date", unique = false, nullable = true)
    private Date startDate;
    
    public Date getStartDate() {
        return this.startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    //采购订单结束日期
    @Column(name = "end_date", unique = false, nullable = true)
    private Date endDate;
    
    public Date getEndDate() {
        return this.endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    //采购订单签订日期
    @NotNull(message="签订日期不能为空")
    @Column(name = "sign_date", unique = false, nullable = false)
    private Date signDate;
    
    public Date getSignDate() {
        return this.signDate;
    }
    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }
    
    //计税类型
    @Column(name = "tax_type", unique = false, nullable = false, length = 45)
    private String taxType;
    
    public String getTaxType() {
        return this.taxType;
    }
    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }
    
    //计税比率
    @Column(name = "tax_percent", unique = false, nullable = false)
    private Double taxPercent;
    
    public Double getTaxPercent() {
        return this.taxPercent;
    }
    public void setTaxPercent(Double taxPercent) {
        this.taxPercent = taxPercent;
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
    
    //状态（新建NEW，确认CONFIRM，取消CANCEL）
    @NotBlank(message="状态不能为空")
    @Column(name = "status", unique = false, nullable = false, length = 10)
    private String status;
    
    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
    //接收状态（未入库N，已入库Y，部分入库PART）
    @NotBlank(message="接收状态不能为空")
    @Column(name = "receive_status", unique = false, nullable = false, length = 10)
    private String receiveStatus;
    
    public String getReceiveStatus() {
        return receiveStatus;
    }
    public void setReceiveStatus(String receiveStatus) {
        this.receiveStatus = receiveStatus;
    }
    
    //付款状态（未付款N，已付款Y，部分付款PART）
    @NotBlank(message="付款状态不能为空")
    @Column(name = "payment_status", unique = false, nullable = false, length = 10)
    private String paymentStatus;

    public String getPaymentStatus() {
        return paymentStatus;
    }
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    //审批状态（未提交UNSUBMIT、已提交SUBMIT、已审批APPROVE、已驳回REJECT）
    @Column(name = "approve_status", unique = false, nullable = false, length = 45)
    private String approveStatus;
    
    public String getApproveStatus() {
        return this.approveStatus;
    }
    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }
    
    //采购员
    @Column(name = "staff_code", unique = false, nullable = false, length = 45)
    private String staffCode;
    
    public String getStaffCode() {
        return this.staffCode;
    }
    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }
    
    //采购部门
    @Column(name = "department_code", unique = false, nullable = false, length = 45)
    private String departmentCode;
    
    public String getDepartmentCode() {
        return this.departmentCode;
    }
    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
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
     * 用于展示的字段
     */
    @Transient
    private String staffName;
    @Transient
    private String departmentName;
    @Transient
    private Double amount;
    @Transient
    private String vendorContactDesc;
    
    public Double getAmount() {
        return this.amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public String getStaffName() {
        return staffName;
    }
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
    public String getDepartmentName() {
        return departmentName;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public String getVendorContactDesc() {
        return vendorContactDesc;
    }
    public void setVendorContactDesc(String vendorContactDesc) {
        this.vendorContactDesc = vendorContactDesc;
    }
    
}