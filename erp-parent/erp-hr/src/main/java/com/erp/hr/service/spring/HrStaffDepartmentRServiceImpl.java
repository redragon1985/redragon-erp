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
import com.erp.hr.dao.HrStaffDepartmentRDao;
import com.erp.hr.dao.model.HrDepartmentCO;
import com.erp.hr.dao.model.HrStaffDepartmentR;
import com.erp.hr.dao.model.HrStaffDepartmentRCO;
import com.erp.hr.service.HrStaffDepartmentRService;
import com.erp.hr.service.HrStaffService;

@Service
@Transactional(rollbackFor=Exception.class)
public class HrStaffDepartmentRServiceImpl implements HrStaffDepartmentRService {

    //注入Dao
    @Autowired
    private HrStaffDepartmentRDao hrStaffDepartmentRDao;
    @Autowired
    private HrStaffService hrStaffService;
    
    @Override
    public void insertDataObject(HrStaffDepartmentR obj) {
        this.hrStaffDepartmentRDao.insertDataObject(obj);
        
        //清除缓存
        if(StringUtils.isNotBlank(obj.getStaffCode())) {
            //获取职员的username
            String username = this.hrStaffService.getUsernameByStaffCode(obj.getStaffCode());
            //如果取到username清除缓存
            if(StringUtils.isNotBlank(username)) {
                EhcacheUtil.clearBatch("*getStaffInfo*"+username.hashCode()+"*");
                RedisUtil.clearBatch("*getStaffInfo*"+username.hashCode()+"*");
            }
        }
        
    }

    @Override
    public void updateDataObject(HrStaffDepartmentR obj) {
        this.hrStaffDepartmentRDao.updateDataObject(obj);
        
        //清除缓存
        if(StringUtils.isNotBlank(obj.getStaffCode())) {
            //获取职员的username
            String username = this.hrStaffService.getUsernameByStaffCode(obj.getStaffCode());
            //如果取到username清除缓存
            if(StringUtils.isNotBlank(username)) {
                EhcacheUtil.clearBatch("*getStaffInfo*"+username.hashCode()+"*");
                RedisUtil.clearBatch("*getStaffInfo*"+username.hashCode()+"*");
            }
        }
    }
    
    @Override
    public void insertOrUpdateDataObject(HrStaffDepartmentR obj) {
        this.hrStaffDepartmentRDao.insertOrUpdateDataObject(obj);
        
        //清除缓存
        if(StringUtils.isNotBlank(obj.getStaffCode())) {
            //获取职员的username
            String username = this.hrStaffService.getUsernameByStaffCode(obj.getStaffCode());
            //如果取到username清除缓存
            if(StringUtils.isNotBlank(username)) {
                EhcacheUtil.clearBatch("*getStaffInfo*"+username.hashCode()+"*");
                RedisUtil.clearBatch("*getStaffInfo*"+username.hashCode()+"*");
            }
        }
    }

    @Override
    public void deleteDataObject(HrStaffDepartmentR obj) {
        this.hrStaffDepartmentRDao.deleteDataObject(obj);
        
        //清除缓存
        if(StringUtils.isNotBlank(obj.getStaffCode())) {
            //获取职员的username
            String username = this.hrStaffService.getUsernameByStaffCode(obj.getStaffCode());
            //如果取到username清除缓存
            if(StringUtils.isNotBlank(username)) {
                EhcacheUtil.clearBatch("*getStaffInfo*"+username.hashCode()+"*");
                RedisUtil.clearBatch("*getStaffInfo*"+username.hashCode()+"*");
            }
        }
    }

    @Override
    public List<HrStaffDepartmentR> getDataObjects() {
        return this.hrStaffDepartmentRDao.getDataObjects();
    }

    @Override
    public HrStaffDepartmentR getDataObject(int id) {
        return this.hrStaffDepartmentRDao.getDataObject(id);
    }

    @Override
    public HrStaffDepartmentR getDataObject(String code) {
        return this.hrStaffDepartmentRDao.getDataObject(code);
    }

    @Override
    public List<HrStaffDepartmentR> getDataObjects(HrStaffDepartmentRCO paramObj) {
        return this.hrStaffDepartmentRDao.getDataObjects(paramObj);
    }

    @Override
    public List<HrStaffDepartmentR> getDataObjects(Pages pages) {
        return this.hrStaffDepartmentRDao.getDataObjects(pages);
    }
    
    @Override
    public List<HrStaffDepartmentR> getDataObjects(Pages pages, HrStaffDepartmentRCO paramObj) {
        return this.hrStaffDepartmentRDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, HrStaffDepartmentRCO paramObj) {
        return this.hrStaffDepartmentRDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<HrStaffDepartmentR> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, HrStaffDepartmentRCO paramObj) {
        return this.hrStaffDepartmentRDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public List getHrStaffDepartmentRList(Pages pages, HrStaffDepartmentRCO paramObj) {
        return this.hrStaffDepartmentRDao.getHrStaffDepartmentRList(pages, paramObj);
    }
    
}