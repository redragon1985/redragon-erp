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

import org.apache.commons.lang.StringUtils;
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
import com.erp.hr.dao.HrStaffDao;
import com.erp.hr.dao.model.HrStaff;
import com.erp.hr.dao.model.HrStaffCO;
import com.erp.hr.service.HrStaffService;

@Service
@Transactional(rollbackFor=Exception.class)
public class HrStaffServiceImpl implements HrStaffService {

    //注入Dao
    @Autowired
    private HrStaffDao hrStaffDao;
    
    @Override
    public void insertDataObject(HrStaff obj) {
        this.hrStaffDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(HrStaff obj) {
        this.hrStaffDao.updateDataObject(obj);
        //清除缓存
        EhcacheUtil.clearBatch("*getStaffInfo*"+obj.getUsername().hashCode()+"*");
        RedisUtil.clearBatch("*getStaffInfo*"+obj.getUsername().hashCode()+"*");
        //清除缓存
        EhcacheUtil.clearBatch("*getHrStaff*"+obj.getStaffCode().hashCode()+"*");
        RedisUtil.clearBatch("*getHrStaff*"+obj.getStaffCode().hashCode()+"*");
    }
    
    @Override
    public void insertOrUpdateDataObject(HrStaff obj) {
        this.hrStaffDao.insertOrUpdateDataObject(obj);
        //清除缓存
        EhcacheUtil.clearBatch("*getStaffInfo*"+obj.getUsername().hashCode()+"*");
        RedisUtil.clearBatch("*getStaffInfo*"+obj.getUsername().hashCode()+"*");
        //清除缓存
        EhcacheUtil.clearBatch("*getHrStaff*"+obj.getStaffCode().hashCode()+"*");
        RedisUtil.clearBatch("*getHrStaff*"+obj.getStaffCode().hashCode()+"*");
    }

    @Override
    public void deleteDataObject(HrStaff obj) {
        this.hrStaffDao.deleteDataObject(obj);
        if(StringUtils.isNotBlank(obj.getUsername())) {
            //清除缓存
            EhcacheUtil.clearBatch("*getStaffInfo*"+obj.getUsername().hashCode()+"*");
            RedisUtil.clearBatch("*getStaffInfo*"+obj.getUsername().hashCode()+"*");
            //清除缓存
            EhcacheUtil.clearBatch("*getHrStaff*"+obj.getStaffCode().hashCode()+"*");
            RedisUtil.clearBatch("*getHrStaff*"+obj.getStaffCode().hashCode()+"*");
        }
    }

    @Override
    public List<HrStaff> getDataObjects() {
        return this.hrStaffDao.getDataObjects();
    }

    @Override
    public HrStaff getDataObject(int id) {
        return this.hrStaffDao.getDataObject(id);
    }

    @Override
    public HrStaff getDataObject(String code) {
        return this.hrStaffDao.getDataObject(code);
    }

    @Override
    public List<HrStaff> getDataObjects(HrStaffCO paramObj) {
        return this.hrStaffDao.getDataObjects(paramObj);
    }

    @Override
    public List<HrStaff> getDataObjects(Pages pages) {
        return this.hrStaffDao.getDataObjects(pages);
    }
    
    @Override
    public List<HrStaff> getDataObjects(Pages pages, HrStaffCO paramObj) {
        return this.hrStaffDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, HrStaffCO paramObj) {
        return this.hrStaffDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<HrStaff> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, HrStaffCO paramObj) {
        return this.hrStaffDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public boolean isExistRelateDataForHrStaff(String staffCode) {
        return this.hrStaffDao.isExistRelateDataForHrStaff(staffCode);
    }
    
    @Override
    public String getUsernameByStaffCode(String staffCode) {
        return this.hrStaffDao.getUsernameByStaffCode(staffCode);
    }
    
}