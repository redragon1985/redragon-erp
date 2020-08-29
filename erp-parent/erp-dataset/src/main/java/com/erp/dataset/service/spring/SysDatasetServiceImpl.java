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