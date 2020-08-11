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