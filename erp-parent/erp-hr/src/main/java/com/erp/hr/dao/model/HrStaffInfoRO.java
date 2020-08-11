/**
 * @description HrStaffInfoRO.java
 * @author dongbin
 * @version 
 * @copyright
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
