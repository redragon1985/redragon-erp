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
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.framework.annotation.Cache;
import com.framework.annotation.Cache.CacheType;
import com.framework.annotation.Log;
import com.framework.dao.model.Pages;
import com.framework.util.EhcacheUtil;
import com.framework.util.RedisUtil;
import com.erp.hr.dao.HrDepartmentDao;
import com.erp.hr.dao.model.HrDepartment;
import com.erp.hr.dao.model.HrDepartmentCO;
import com.erp.hr.service.HrDepartmentService;

@Service
@Transactional(rollbackFor=Exception.class)
public class HrDepartmentServiceImpl implements HrDepartmentService {

    //注入Dao
    @Autowired
    private HrDepartmentDao hrDepartmentDao;
    
    @Override
    public void insertDataObject(HrDepartment obj) {
        this.hrDepartmentDao.insertDataObject(obj);
        //清除缓存
        EhcacheUtil.clearBatch("*getHrDepartment*"+obj.getDepartmentCode().hashCode()+"*");
        RedisUtil.clearBatch("*getHrDepartment*"+obj.getDepartmentCode().hashCode()+"*");
    }

    @Override
    public void updateDataObject(HrDepartment obj) {
        this.hrDepartmentDao.updateDataObject(obj);
        //清除缓存
        EhcacheUtil.clearBatch("*getHrDepartment*"+obj.getDepartmentCode().hashCode()+"*");
        RedisUtil.clearBatch("*getHrDepartment*"+obj.getDepartmentCode().hashCode()+"*");
    }
    
    @Override
    public void insertOrUpdateDataObject(HrDepartment obj) {
        this.hrDepartmentDao.insertOrUpdateDataObject(obj);
        //清除缓存
        EhcacheUtil.clearBatch("*getHrDepartment*"+obj.getDepartmentCode().hashCode()+"*");
        RedisUtil.clearBatch("*getHrDepartment*"+obj.getDepartmentCode().hashCode()+"*");
    }

    @Override
    public void deleteDataObject(HrDepartment obj) {
        this.hrDepartmentDao.deleteDataObject(obj);
        //清除缓存
        String departmentCode = "";
        if(obj.getDepartmentCode()!=null) {
            departmentCode = obj.getDepartmentCode();
        }
        EhcacheUtil.clearBatch("*getHrDepartment*"+departmentCode.hashCode()+"*");
        RedisUtil.clearBatch("*getHrDepartment*"+departmentCode.hashCode()+"*");
    }

    @Override
    public List<HrDepartment> getDataObjects() {
        return this.hrDepartmentDao.getDataObjects();
    }

    @Override
    public HrDepartment getDataObject(int id) {
        return this.hrDepartmentDao.getDataObject(id);
    }

    @Override
    public HrDepartment getDataObject(String code) {
        return this.hrDepartmentDao.getDataObject(code);
    }

    @Override
    public List<HrDepartment> getDataObjects(HrDepartmentCO paramObj) {
        return this.hrDepartmentDao.getDataObjects(paramObj);
    }

    @Override
    public List<HrDepartment> getDataObjects(Pages pages) {
        return this.hrDepartmentDao.getDataObjects(pages);
    }
    
    @Override
    public List<HrDepartment> getDataObjects(Pages pages, HrDepartmentCO paramObj) {
        return this.hrDepartmentDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, HrDepartmentCO paramObj) {
        return this.hrDepartmentDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<HrDepartment> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, HrDepartmentCO paramObj) {
        return this.hrDepartmentDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public int getHrChildDepartmentNum(Integer departmentId) {
        return this.hrDepartmentDao.getHrChildDepartmentNum(departmentId);
    }
    
}