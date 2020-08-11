package com.erp.hr.dao.model;

import com.erp.hr.dao.model.HrStaffDepartmentR;

public class HrStaffDepartmentRCO extends HrStaffDepartmentR implements java.io.Serializable {
	
	// serialVersionUID
	private static final long serialVersionUID = 1L;
	
	//部门id
	private Integer departmentId = -1;

	
	
	/*
	 * setter,getter方法
	 */
    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
	
}