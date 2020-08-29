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
package com.erp.hr.service.spring;

import java.util.List;

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
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200)
    public List<HrDepartment> getHrDepartmentChildList(String departmentCode) {
        return this.hrDepartmentDao.getHrDepartmentChildList(departmentCode);
    }

}
