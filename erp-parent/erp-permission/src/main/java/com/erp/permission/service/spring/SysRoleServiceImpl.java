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
import com.erp.permission.dao.SysRoleDao;
import com.erp.permission.dao.model.SysRole;
import com.erp.permission.dao.model.SysRoleCO;
import com.erp.permission.service.SysRoleService;

@Service
@Transactional(rollbackFor=Exception.class)
public class SysRoleServiceImpl implements SysRoleService {

    //注入Dao
    @Autowired
    private SysRoleDao sysRoleDao;
    
    @Override
    public void insertDataObject(SysRole obj) {
        this.sysRoleDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(SysRole obj) {
        this.sysRoleDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(SysRole obj) {
        this.sysRoleDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(SysRole obj) {
        this.sysRoleDao.deleteDataObject(obj);
    }

    @Override
    public List<SysRole> getDataObjects() {
        return this.sysRoleDao.getDataObjects();
    }

    @Override
    public SysRole getDataObject(int id) {
        return this.sysRoleDao.getDataObject(id);
    }

    @Override
    public SysRole getDataObject(String code) {
        return this.sysRoleDao.getDataObject(code);
    }

    @Override
    public List<SysRole> getDataObjects(SysRoleCO paramObj) {
        return this.sysRoleDao.getDataObjects(paramObj);
    }

    @Override
    public List<SysRole> getDataObjects(Pages pages) {
        return this.sysRoleDao.getDataObjects(pages);
    }
    
    @Override
    public List<SysRole> getDataObjects(Pages pages, SysRoleCO paramObj) {
        return this.sysRoleDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, SysRoleCO paramObj) {
        return this.sysRoleDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<SysRole> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, SysRoleCO paramObj) {
        return this.sysRoleDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public List<SysRole> getSysRoleListByStatus(String status) {
        return this.sysRoleDao.getSysRoleListByStatus(status);
    }
    
    @Override
    public List<SysRole> getRelateSysRoleListByUsername(String username) {
        return this.sysRoleDao.getRelateSysRoleListByUsername(username);
    }
    
    @Override
    public boolean isExistRelateDataForSysRole(String roleCode) {
        return this.sysRoleDao.isExistRelateDataForSysRole(roleCode);
    }
    
}