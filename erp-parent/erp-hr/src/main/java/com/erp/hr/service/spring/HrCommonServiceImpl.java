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
package com.erp.hr.service.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.hr.dao.HrDepartmentDao;
import com.erp.hr.dao.HrStaffDao;
import com.erp.hr.dao.model.HrDepartment;
import com.erp.hr.dao.model.HrStaff;
import com.erp.hr.dao.model.HrStaffInfoRO;
import com.erp.hr.service.HrCommonService;
import com.framework.annotation.Cache;
import com.framework.annotation.Cache.CacheType;

/**
 * @description
 * @date 2020年7月15日
 * @author dongbin
 * 
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class HrCommonServiceImpl implements HrCommonService {
    
    //注入Dao
    @Autowired
    private HrStaffDao hrStaffDao;
    @Autowired
    private HrDepartmentDao hrDepartmentDao;

    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200)
    public HrStaffInfoRO getStaffInfo(String username) {
        return this.hrStaffDao.getHrStaffInfoRO(username);
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200)
    public HrDepartment getHrDepartment(String departmentCode) {
        return this.hrDepartmentDao.getDataObject(departmentCode);
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200)
    public HrStaff getHrStaff(String staffCode) {
        return this.hrStaffDao.getDataObject(staffCode);
    }

}
