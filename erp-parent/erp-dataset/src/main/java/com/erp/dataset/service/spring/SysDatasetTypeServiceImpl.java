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
package com.erp.dataset.service.spring;

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
import com.erp.dataset.dao.SysDatasetTypeDao;
import com.erp.dataset.dao.model.SysDatasetType;
import com.erp.dataset.dao.model.SysDatasetTypeCO;
import com.erp.dataset.service.SysDatasetTypeService;

@Service
@Transactional(rollbackFor=Exception.class)
public class SysDatasetTypeServiceImpl implements SysDatasetTypeService {

    //注入Dao
    @Autowired
    private SysDatasetTypeDao sysDatasetTypeDao;
    
    @Override
    public void insertDataObject(SysDatasetType obj) {
        this.sysDatasetTypeDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(SysDatasetType obj) {
        this.sysDatasetTypeDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(SysDatasetType obj) {
        this.sysDatasetTypeDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(SysDatasetType obj) {
        this.sysDatasetTypeDao.deleteDataObject(obj);
    }

    @Override
    public List<SysDatasetType> getDataObjects() {
        return this.sysDatasetTypeDao.getDataObjects();
    }

    @Override
    public SysDatasetType getDataObject(int id) {
        return this.sysDatasetTypeDao.getDataObject(id);
    }

    @Override
    public SysDatasetType getDataObject(String code) {
        return this.sysDatasetTypeDao.getDataObject(code);
    }

    @Override
    public List<SysDatasetType> getDataObjects(SysDatasetTypeCO paramObj) {
        return this.sysDatasetTypeDao.getDataObjects(paramObj);
    }

    @Override
    public List<SysDatasetType> getDataObjects(Pages pages) {
        return this.sysDatasetTypeDao.getDataObjects(pages);
    }
    
    @Override
    public List<SysDatasetType> getDataObjects(Pages pages, SysDatasetTypeCO paramObj) {
        return this.sysDatasetTypeDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, SysDatasetTypeCO paramObj) {
        return this.sysDatasetTypeDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<SysDatasetType> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, SysDatasetTypeCO paramObj) {
        return this.sysDatasetTypeDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public boolean isExistRelateDataForSysDatasetType(String datasetTypeCode) {
        return this.sysDatasetTypeDao.isExistRelateDataForSysDatasetType(datasetTypeCode);
    }
    
}