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
package com.erp.report.voucher.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class VoucherReportV implements Serializable {
    
    //Constructors
    public VoucherReportV() {
    }
    
    //Fields
    private String voucherHeadCode;
    private String voucherType;
    private String voucherTypeDesc;
    private String voucherNumber;
    private String voucherDate;
    private String billNum;
    private String memo;
    private String subjectCode;
    private String subjectDesc;
    private String drAmount;
    private String crAmount;
    private String billType;
    private String billTypeDesc;


    
    /*
     * setter,getter方法
     */
    public String getVoucherHeadCode() {
        return voucherHeadCode;
    }
    public void setVoucherHeadCode(String voucherHeadCode) {
        this.voucherHeadCode = voucherHeadCode;
    }
    public String getVoucherType() {
        return voucherType;
    }
    public void setVoucherType(String voucherType) {
        this.voucherType = voucherType;
    }
    public String getVoucherTypeDesc() {
        return voucherTypeDesc;
    }
    public void setVoucherTypeDesc(String voucherTypeDesc) {
        this.voucherTypeDesc = voucherTypeDesc;
    }
    public String getVoucherNumber() {
        return voucherNumber;
    }
    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
    }
    public String getVoucherDate() {
        return voucherDate;
    }
    public void setVoucherDate(String voucherDate) {
        this.voucherDate = voucherDate;
    }
    public String getBillNum() {
        return billNum;
    }
    public void setBillNum(String billNum) {
        this.billNum = billNum;
    }
    public String getMemo() {
        return memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
    public String getSubjectCode() {
        return subjectCode;
    }
    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }
    public String getSubjectDesc() {
        return subjectDesc;
    }
    public void setSubjectDesc(String subjectDesc) {
        this.subjectDesc = subjectDesc;
    }
    public String getDrAmount() {
        return drAmount;
    }
    public void setDrAmount(String drAmount) {
        this.drAmount = drAmount;
    }
    public String getCrAmount() {
        return crAmount;
    }
    public void setCrAmount(String crAmount) {
        this.crAmount = crAmount;
    }
    public String getBillType() {
        if(billType==null) {
            billType = "";
        }
        return billType;
    }
    public void setBillType(String billType) {
        this.billType = billType;
    }
    public String getBillTypeDesc() {
        return billTypeDesc;
    }
    public void setBillTypeDesc(String billTypeDesc) {
        this.billTypeDesc = billTypeDesc;
    }
}
