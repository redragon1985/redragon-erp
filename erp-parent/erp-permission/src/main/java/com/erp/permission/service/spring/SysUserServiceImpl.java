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
package com.erp.permission.service.spring;

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
import com.erp.permission.dao.SysUserDao;
import com.erp.permission.dao.model.SysUser;
import com.erp.permission.dao.model.SysUserCO;
import com.erp.permission.dao.model.SysUserRO;
import com.erp.permission.service.SysUserService;

@Service
@Transactional(rollbackFor=Exception.class)
public class SysUserServiceImpl implements SysUserService {

    //注入Dao
    @Autowired
    private SysUserDao sysUserDao;
    
    @Override
    public void insertDataObject(SysUser obj) {
        this.sysUserDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(SysUser obj) {
        this.sysUserDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(SysUser obj) {
        this.sysUserDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(SysUser obj) {
        this.sysUserDao.deleteDataObject(obj);
    }

    @Override
    public List<SysUser> getDataObjects() {
        return this.sysUserDao.getDataObjects();
    }

    @Override
    public SysUser getDataObject(int id) {
        return this.sysUserDao.getDataObject(id);
    }

    @Override
    public SysUser getDataObject(String code) {
        return this.sysUserDao.getDataObject(code);
    }

    @Override
    public List<SysUser> getDataObjects(SysUserCO paramObj) {
        return this.sysUserDao.getDataObjects(paramObj);
    }

    @Override
    public List<SysUser> getDataObjects(Pages pages) {
        return this.sysUserDao.getDataObjects(pages);
    }
    
    @Override
    public List<SysUser> getDataObjects(Pages pages, SysUserCO paramObj) {
        return this.sysUserDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, SysUserCO paramObj) {
        return this.sysUserDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<SysUser> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, SysUserCO paramObj) {
        return this.sysUserDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public List<SysUserRO> getSysUserROListForRelate() {
        return this.sysUserDao.getSysUserROListForRelate();
    }
    
    @Override
    public boolean isExistRelateDataForSysUser(String username) {
        return this.sysUserDao.isExistRelateDataForSysUser(username);
    }
    
}