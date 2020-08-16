/*
 * Copyright 2020-2021 redragon.dongbin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
