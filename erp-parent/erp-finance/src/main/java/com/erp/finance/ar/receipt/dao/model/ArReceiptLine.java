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
package com.erp.finance.ar.receipt.dao.model;

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
@Table(name="ar_receipt_line", schema="erp")
@DynamicInsert(true)
@DynamicUpdate(true)
public class ArReceiptLine implements java.io.Serializable {

    //serialVersionUID
    private static final long serialVersionUID = 1L;

    //Constructors
    public ArReceiptLine() {
    }
    
    //Fields
    
    //收款行id
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "receipt_line_id", unique = true, nullable = false)
    private Integer receiptLineId;
    
    public Integer getReceiptLineId() {
        return this.receiptLineId;
    }
    public void setReceiptLineId(Integer receiptLineId) {
        this.receiptLineId = receiptLineId;
    }
    
    //收款行code
    @Column(name = "receipt_line_code", unique = true, nullable = false, length = 45)
    private String receiptLineCode;
    
    public String getReceiptLineCode() {
        return this.receiptLineCode;
    }
    public void setReceiptLineCode(String receiptLineCode) {
        this.receiptLineCode = receiptLineCode;
    }
    
    //收款头code
    @Column(name = "receipt_head_code", unique = false, nullable = false, length = 45)
    private String receiptHeadCode;
    
    public String getReceiptHeadCode() {
        return this.receiptHeadCode;
    }
    public void setReceiptHeadCode(String receiptHeadCode) {
        this.receiptHeadCode = receiptHeadCode;
    }
    
    //发票头code
    @NotBlank(message="发票头编码不能为空")
    @Column(name = "invoice_head_code", unique = false, nullable = false, length = 45)
    private String invoiceHeadCode;
    
    public String getInvoiceHeadCode() {
        return this.invoiceHeadCode;
    }
    public void setInvoiceHeadCode(String invoiceHeadCode) {
        this.invoiceHeadCode = invoiceHeadCode;
    }
    
    //核销金额
    @NotNull(message="核销金额不能为空")
    @Column(name = "invoice_receipt_amount", unique = false, nullable = false)
    private Double invoiceReceiptAmount;
    
    public Double getInvoiceReceiptAmount() {
        return this.invoiceReceiptAmount;
    }
    public void setInvoiceReceiptAmount(Double invoiceReceiptAmount) {
        this.invoiceReceiptAmount = invoiceReceiptAmount;
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
    
    
    
    //用于显示的字段
    @Transient
    private Double invoiceAmount;
    @Transient
    private Double invoiceReceivedAmount;
    @Transient
    private Double taxAmount;
    @Transient
    private String soHeadCode;
    @Transient
    private String referenceNumber;
    @Transient
    private Date invoiceDate;

    public Double getInvoiceAmount() {
        return invoiceAmount;
    }
    public void setInvoiceAmount(Double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }
    public Double getTaxAmount() {
        return taxAmount;
    }
    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }
    public String getSoHeadCode() {
        return soHeadCode;
    }
    public void setSoHeadCode(String soHeadCode) {
        this.soHeadCode = soHeadCode;
    }
    public String getReferenceNumber() {
        return referenceNumber;
    }
    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }
    public Date getInvoiceDate() {
        return invoiceDate;
    }
    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
    public Double getInvoiceReceivedAmount() {
        return invoiceReceivedAmount;
    }
    public void setInvoiceReceivedAmount(Double invoiceReceivedAmount) {
        this.invoiceReceivedAmount = invoiceReceivedAmount;
    }
    
}