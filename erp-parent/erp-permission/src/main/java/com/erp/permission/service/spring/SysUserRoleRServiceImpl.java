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
import com.erp.permission.dao.SysUserRoleRDao;
import com.erp.permission.dao.model.SysUserRoleR;
import com.erp.permission.dao.model.SysUserRoleRCO;
import com.erp.permission.service.SysUserRoleRService;

@Service
@Transactional(rollbackFor=Exception.class)
public class SysUserRoleRServiceImpl implements SysUserRoleRService {

    //注入Dao
    @Autowired
    private SysUserRoleRDao sysUserRoleRDao;
    
    @Override
    public void insertDataObject(SysUserRoleR obj) {
        this.sysUserRoleRDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(SysUserRoleR obj) {
        this.sysUserRoleRDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(SysUserRoleR obj) {
        this.sysUserRoleRDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(SysUserRoleR obj) {
        this.sysUserRoleRDao.deleteDataObject(obj);
    }

    @Override
    public List<SysUserRoleR> getDataObjects() {
        return this.sysUserRoleRDao.getDataObjects();
    }

    @Override
    public SysUserRoleR getDataObject(int id) {
        return this.sysUserRoleRDao.getDataObject(id);
    }

    @Override
    public SysUserRoleR getDataObject(String code) {
        return this.sysUserRoleRDao.getDataObject(code);
    }

    @Override
    public List<SysUserRoleR> getDataObjects(SysUserRoleRCO paramObj) {
        return this.sysUserRoleRDao.getDataObjects(paramObj);
    }

    @Override
    public List<SysUserRoleR> getDataObjects(Pages pages) {
        return this.sysUserRoleRDao.getDataObjects(pages);
    }
    
    @Override
    public List<SysUserRoleR> getDataObjects(Pages pages, SysUserRoleRCO paramObj) {
        return this.sysUserRoleRDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, SysUserRoleRCO paramObj) {
        return this.sysUserRoleRDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<SysUserRoleR> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, SysUserRoleRCO paramObj) {
        return this.sysUserRoleRDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public void deleteSysUserRoleRByUsername(String username) {
        this.sysUserRoleRDao.deleteSysUserRoleRByUsername(username);
    }
    
}