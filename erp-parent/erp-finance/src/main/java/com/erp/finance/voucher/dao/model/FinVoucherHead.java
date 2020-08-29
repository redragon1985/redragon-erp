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

import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import redragon.util.math.MoneyUtil;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="fin_voucher_head", schema="erp")
@DynamicInsert(true)
@DynamicUpdate(true)
public class FinVoucherHead implements java.io.Serializable {

    //serialVersionUID
    private static final long serialVersionUID = 1L;

    //Constructors
    public FinVoucherHead() {
    }
    
    //Fields
    
    //凭证头id
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "voucher_head_id", unique = true, nullable = false)
    private Integer voucherHeadId;
    
    public Integer getVoucherHeadId() {
        return this.voucherHeadId;
    }
    public void setVoucherHeadId(Integer voucherHeadId) {
        this.voucherHeadId = voucherHeadId;
    }
    
    //凭证头编码
    @Column(name = "voucher_head_code", unique = true, nullable = false, length = 45)
    private String voucherHeadCode;
    
    public String getVoucherHeadCode() {
        return this.voucherHeadCode;
    }
    public void setVoucherHeadCode(String voucherHeadCode) {
        this.voucherHeadCode = voucherHeadCode;
    }
    
    //凭证字
    @NotBlank(message="凭证字不能为空")
    @Column(name = "voucher_type", unique = false, nullable = false, length = 10)
    private String voucherType;
    
    public String getVoucherType() {
        return this.voucherType;
    }
    public void setVoucherType(String voucherType) {
        this.voucherType = voucherType;
    }
    
    //凭证号
    //@NotBlank(message="凭证号不能为空")
    @Column(name = "voucher_number", unique = false, nullable = false, length = 45)
    private String voucherNumber;
    
    public String getVoucherNumber() {
        return this.voucherNumber;
    }
    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
    }
    
    //凭证日期
    @NotNull(message="凭证日期不能为空")
    @Column(name = "voucher_date", unique = false, nullable = false)
    private Date voucherDate;
    
    public Date getVoucherDate() {
        return this.voucherDate;
    }
    public void setVoucherDate(Date voucherDate) {
        this.voucherDate = voucherDate;
    }
    
    //单据数量
    @NotNull(message="单据数量不能为空")
    @Column(name = "bill_num", unique = false, nullable = false)
    private Integer billNum;
    
    public Integer getBillNum() {
        return this.billNum;
    }
    public void setBillNum(Integer billNum) {
        this.billNum = billNum;
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
    
    //审批状态
    @Column(name = "approve_status", unique = false, nullable = true, length = 10)
    private String approveStatus;
    
    public String getApproveStatus() {
        return this.approveStatus;
    }
    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }
    
    //制单人
    @Column(name = "staff_code", unique = false, nullable = false, length = 45)
    private String staffCode;
    
    public String getStaffCode() {
        return this.staffCode;
    }
    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }
    
    //制单部门
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
    private BigDecimal amount;
    @Transient
    private String amountDesc;
    @Transient
    private Double drAmount;
    @Transient
    private Double crAmount;
    @Transient
    private String[] drAmountArray;
    @Transient
    private String[] crAmountArray;

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
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public String[] getDrAmountArray() {
        if(this.drAmount!=null) {
            if(this.drAmount!=0) {
                DecimalFormat df = new DecimalFormat("#.00");
                String temp = df.format(this.drAmount).replace(".", "");
                char[] tempChar = temp.toCharArray();
                
                int x = 0;
                String[] array = new String[11];
                for(int i=0;i<11;i++) {
                    int index = 11-temp.length();
                    if(i>=index) {
                        array[i] = String.valueOf(tempChar[x]);
                        x++;
                    }else {
                        array[i] = "";
                    }
                }
                return array;
            }else {
                String[] array = new String[11];
                for(int i=0;i<11;i++) {
                    array[i] = "";
                }
                return array;
            }
        }else {
            return new String[11];
        }
    }
    public void setDrAmountArray(String[] drAmountArray) {
        this.drAmountArray = drAmountArray;
    }
    public String[] getCrAmountArray() {
        if(this.crAmount!=null) {
            if(this.crAmount!=0) {
                DecimalFormat df = new DecimalFormat("#.00");
                String temp = df.format(this.crAmount).replace(".", "");
                char[] tempChar = temp.toCharArray();
                
                int x = 0;
                String[] array = new String[11];
                for(int i=0;i<11;i++) {
                    int index = 11-temp.length();
                    if(i>=index) {
                        array[i] = String.valueOf(tempChar[x]);
                        x++;
                    }else {
                        array[i] = "";
                    }
                }
                return array;
            }else {
                String[] array = new String[11];
                for(int i=0;i<11;i++) {
                    array[i] = "";
                }
                return array;
            }
        }else {
            return new String[11];
        }
        
    }
    public void setCrAmountArray(String[] crAmountArray) {
        this.crAmountArray = crAmountArray;
    }
    public String getAmountDesc() {
        try {
            String desc = MoneyUtil.convert(this.amount.doubleValue());
            return desc;
        }catch(Exception e) {
            return "";
        }
    }
    public void setAmountDesc(String amountDesc) {
        this.amountDesc = amountDesc;
    }
    public Double getDrAmount() {
        return drAmount;
    }
    public void setDrAmount(Double drAmount) {
        this.drAmount = drAmount;
    }
    public Double getCrAmount() {
        return crAmount;
    }
    public void setCrAmount(Double crAmount) {
        this.crAmount = crAmount;
    }
    
}