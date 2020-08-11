package com.erp.permission.dao.model;

import org.apache.commons.lang.StringUtils;

import com.erp.permission.dao.model.SysUser;

public class SysUserCO extends SysUser implements java.io.Serializable {
	
	// serialVersionUID
	private static final long serialVersionUID = 1L;
	
	private String createdDateStart;
	private String createdDateEnd;
	
	
	
	/*
	 * setter,getter方法
	 */
    public String getCreatedDateStart() {
        if(StringUtils.isNotBlank(this.createdDateStart)&&!this.createdDateStart.contains(":")) {
            this.createdDateStart = this.createdDateStart+" 00:00:00";
        }
        return this.createdDateStart;
    }
    public void setCreatedDateStart(String createdDateStart) {
        this.createdDateStart = createdDateStart;
    }
    public String getCreatedDateEnd() {
        if(StringUtils.isNotBlank(this.createdDateEnd)&&!this.createdDateEnd.contains(":")) {
            this.createdDateEnd = this.createdDateEnd+" 23:59:59";
        }
        return this.createdDateEnd;
    }
    public void setCreatedDateEnd(String createdDateEnd) {
        this.createdDateEnd = createdDateEnd;
    }

}