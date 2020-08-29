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
package com.erp.hr.dao.model;

/**
 * @description 用户信息RO对象
 * @date 2020年7月15日
 * @author dongbin
 * 
 */
public class HrStaffInfoRO implements java.io.Serializable {
    
    private String staffCode;
    private String staffName;
    private String departmentCode;
    private String departmentName;
    private String deaprtmentSegmentCode;
    private String departmentSegmentDesc;
    private String positionCode;
    private String positionName;
    
    
    
    /*
     * setter,getter方法
     */
    public String getStaffCode() {
        return staffCode;
    }
    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }
    public String getStaffName() {
        return staffName;
    }
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }
    public String getDepartmentCode() {
        return departmentCode;
    }
    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }
    public String getDepartmentName() {
        return departmentName;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public String getDeaprtmentSegmentCode() {
        return deaprtmentSegmentCode;
    }
    public void setDeaprtmentSegmentCode(String deaprtmentSegmentCode) {
        this.deaprtmentSegmentCode = deaprtmentSegmentCode;
    }
    public String getDepartmentSegmentDesc() {
        return departmentSegmentDesc;
    }
    public void setDepartmentSegmentDesc(String departmentSegmentDesc) {
        this.departmentSegmentDesc = departmentSegmentDesc;
    }
    public String getPositionCode() {
        return positionCode;
    }
    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }
    public String getPositionName() {
        return positionName;
    }
    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
    
}
