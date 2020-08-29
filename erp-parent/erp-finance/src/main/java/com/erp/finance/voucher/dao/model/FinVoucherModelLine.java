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
package com.erp.finance.voucher.dao.model;

import java.text.DecimalFormat;
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
@Table(name="fin_voucher_model_line", schema="erp")
@DynamicInsert(true)
@DynamicUpdate(true)
public class FinVoucherModelLine implements java.io.Serializable {

    //serialVersionUID
    private static final long serialVersionUID = 1L;

    //Constructors
    public FinVoucherModelLine() {
    }
    
    //Fields
    
    //凭证行id
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "voucher_line_id", unique = true, nullable = false)
    private Integer voucherLineId;
    
    public Integer getVoucherLineId() {
        return this.voucherLineId;
    }
    public void setVoucherLineId(Integer voucherLineId) {
        this.voucherLineId = voucherLineId;
    }
    
    //凭证行编码
    @Column(name = "voucher_line_code", unique = true, nullable = false, length = 45)
    private String voucherLineCode;
    
    public String getVoucherLineCode() {
        return this.voucherLineCode;
    }
    public void setVoucherLineCode(String voucherLineCode) {
        this.voucherLineCode = voucherLineCode;
    }
    
    //凭证头编码
    @Column(name = "voucher_head_code", unique = false, nullable = false, length = 45)
    private String voucherHeadCode;
    
    public String getVoucherHeadCode() {
        return this.voucherHeadCode;
    }
    public void setVoucherHeadCode(String voucherHeadCode) {
        this.voucherHeadCode = voucherHeadCode;
    }
    
    //摘要
    @NotBlank(message="摘要不能为空")
    @Column(name = "memo", unique = false, nullable = false, length = 200)
    private String memo;
    
    public String getMemo() {
        return this.memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
    
    //科目
    @NotBlank(message="科目不能为空")
    @Column(name = "subject_code", unique = false, nullable = false, length = 45)
    private String subjectCode;
    
    public String getSubjectCode() {
        return this.subjectCode;
    }
    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }
    
    //借方金额
    @Column(name = "dr_amount", unique = false, nullable = true)
    private String drAmount;
    
    public String getDrAmount() {
        return this.drAmount;
    }
    public void setDrAmount(String drAmount) {
        this.drAmount = drAmount;
    }
    
    //贷方金额
    @Column(name = "cr_amount", unique = false, nullable = true)
    private String crAmount;
    
    public String getCrAmount() {
        return this.crAmount;
    }
    public void setCrAmount(String crAmount) {
        this.crAmount = crAmount;
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
    
    
    
    /*
     * 显示的字段
     */
    @Transient
    private String[] drAmountArray;
    @Transient
    private String[] crAmountArray;
    @Transient
    private String subjectDesc;

    public String[] getDrAmountArray() {
        String[] array = new String[11];
        for(int i=0;i<11;i++) {
            array[i] = "";
        }
        return array;
        
    }
    public void setDrAmountArray(String[] drAmountArray) {
        this.drAmountArray = drAmountArray;
    }
    public String[] getCrAmountArray() {
        String[] array = new String[11];
        for(int i=0;i<11;i++) {
            array[i] = "";
        }
        return array;
        
    }
    public void setCrAmountArray(String[] crAmountArray) {
        this.crAmountArray = crAmountArray;
    }
    public String getSubjectDesc() {
        return subjectDesc;
    }
    public void setSubjectDesc(String subjectDesc) {
        this.subjectDesc = subjectDesc;
    }
}