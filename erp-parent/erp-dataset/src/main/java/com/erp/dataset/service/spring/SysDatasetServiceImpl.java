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
import com.framework.util.EhcacheUtil;
import com.framework.util.RedisUtil;
import com.erp.dataset.dao.SysDatasetDao;
import com.erp.dataset.dao.model.SysDataset;
import com.erp.dataset.dao.model.SysDatasetCO;
import com.erp.dataset.service.SysDatasetService;

@Service
@Transactional(rollbackFor=Exception.class)
public class SysDatasetServiceImpl implements SysDatasetService {

    //注入Dao
    @Autowired
    private SysDatasetDao sysDatasetDao;
    
    @Override
    public void insertDataObject(SysDataset obj) {
        this.sysDatasetDao.insertDataObject(obj);
        //清除缓存
        EhcacheUtil.clearBatch("DATASET_*");
        RedisUtil.clearBatch("DATASET_*");
    }

    @Override
    public void updateDataObject(SysDataset obj) {
        this.sysDatasetDao.updateDataObject(obj);
        //清除缓存
        EhcacheUtil.clearBatch("DATASET_*");
        RedisUtil.clearBatch("DATASET_*");
    }
    
    @Override
    public void insertOrUpdateDataObject(SysDataset obj) {
        this.sysDatasetDao.insertOrUpdateDataObject(obj);
        //清除缓存
        EhcacheUtil.clearBatch("DATASET_*");
        RedisUtil.clearBatch("DATASET_*");
    }

    @Override
    public void deleteDataObject(SysDataset obj) {
        this.sysDatasetDao.deleteDataObject(obj);
        //清除缓存
        EhcacheUtil.clearBatch("DATASET_*");
        RedisUtil.clearBatch("DATASET_*");
    }

    @Override
    public List<SysDataset> getDataObjects() {
        return this.sysDatasetDao.getDataObjects();
    }

    @Override
    public SysDataset getDataObject(int id) {
        return this.sysDatasetDao.getDataObject(id);
    }

    @Override
    public SysDataset getDataObject(String code) {
        return this.sysDatasetDao.getDataObject(code);
    }

    @Override
    public List<SysDataset> getDataObjects(SysDatasetCO paramObj) {
        return this.sysDatasetDao.getDataObjects(paramObj);
    }

    @Override
    public List<SysDataset> getDataObjects(Pages pages) {
        return this.sysDatasetDao.getDataObjects(pages);
    }
    
    @Override
    public List<SysDataset> getDataObjects(Pages pages, SysDatasetCO paramObj) {
        return this.sysDatasetDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, SysDatasetCO paramObj) {
        return this.sysDatasetDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<SysDataset> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, SysDatasetCO paramObj) {
        return this.sysDatasetDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
}