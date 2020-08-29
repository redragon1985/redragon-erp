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
import com.erp.permission.dao.SysAuthDao;
import com.erp.permission.dao.model.SysAuth;
import com.erp.permission.dao.model.SysAuthCO;
import com.erp.permission.service.SysAuthService;

@Service
@Transactional(rollbackFor=Exception.class)
public class SysAuthServiceImpl implements SysAuthService {

    //注入Dao
    @Autowired
    private SysAuthDao sysAuthDao;
    
    @Override
    public void insertDataObject(SysAuth obj) {
        this.sysAuthDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(SysAuth obj) {
        this.sysAuthDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(SysAuth obj) {
        this.sysAuthDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(SysAuth obj) {
        this.sysAuthDao.deleteDataObject(obj);
    }

    @Override
    public List<SysAuth> getDataObjects() {
        return this.sysAuthDao.getDataObjects();
    }

    @Override
    public SysAuth getDataObject(int id) {
        return this.sysAuthDao.getDataObject(id);
    }

    @Override
    public SysAuth getDataObject(String code) {
        return this.sysAuthDao.getDataObject(code);
    }

    @Override
    public List<SysAuth> getDataObjects(SysAuthCO paramObj) {
        return this.sysAuthDao.getDataObjects(paramObj);
    }

    @Override
    public List<SysAuth> getDataObjects(Pages pages) {
        return this.sysAuthDao.getDataObjects(pages);
    }
    
    @Override
    public List<SysAuth> getDataObjects(Pages pages, SysAuthCO paramObj) {
        return this.sysAuthDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, SysAuthCO paramObj) {
        return this.sysAuthDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<SysAuth> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, SysAuthCO paramObj) {
        return this.sysAuthDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public List<SysAuth> getSysAuthListByStatus(String status) {
        return this.sysAuthDao.getSysAuthListByStatus(status);
    }
    
    @Override
    public List<SysAuth> getRelateSysAuthListByRoleCode(String roleCode) {
        return this.sysAuthDao.getRelateSysAuthListByRoleCode(roleCode);
    }
    
    @Override
    public boolean isExistRelateDataForSysAuth(String authCode) {
        return this.sysAuthDao.isExistRelateDataForSysAuth(authCode);
    }
    
}