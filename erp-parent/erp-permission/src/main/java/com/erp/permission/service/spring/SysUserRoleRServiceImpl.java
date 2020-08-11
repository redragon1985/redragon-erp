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