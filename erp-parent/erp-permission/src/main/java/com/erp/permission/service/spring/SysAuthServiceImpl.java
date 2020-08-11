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