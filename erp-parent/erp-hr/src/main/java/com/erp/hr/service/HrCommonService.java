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
package com.erp.hr.service;

import java.util.List;

import com.erp.hr.dao.model.HrDepartment;
import com.erp.hr.dao.model.HrStaff;
import com.erp.hr.dao.model.HrStaffInfoRO;

/**
 * @description
 * @date 2020年7月15日
 * @author dongbin
 * 
 */
public interface HrCommonService {
    
    //获取职员信息（根据用户名）
    public abstract HrStaffInfoRO getStaffInfo(String username);
    
    //获取职员信息（根据职员编码）
    public abstract HrStaff getHrStaff(String staffCode);
    
    //获取组织信息
    public abstract HrDepartment getHrDepartment(String departmentCode);
    
    //获取子部门列表
    public abstract List<HrDepartment> getHrDepartmentChildList(String departmentCode);
    
}
