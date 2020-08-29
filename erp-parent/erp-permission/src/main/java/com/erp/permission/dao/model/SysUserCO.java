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