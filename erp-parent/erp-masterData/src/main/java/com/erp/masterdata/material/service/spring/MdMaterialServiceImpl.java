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
package com.erp.masterdata.material.service.spring;

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
import com.erp.masterdata.common.param.MasterDataParam;
import com.erp.masterdata.material.dao.MdMaterialDao;
import com.erp.masterdata.material.dao.model.MdMaterial;
import com.erp.masterdata.material.dao.model.MdMaterialCO;
import com.erp.masterdata.material.service.MdMaterialService;

@Service
@Transactional(rollbackFor=Exception.class)
public class MdMaterialServiceImpl implements MdMaterialService {

    //注入Dao
    @Autowired
    private MdMaterialDao mdMaterialDao;
    
    @Override
    public void insertDataObject(MdMaterial obj) {
        this.mdMaterialDao.insertDataObject(obj);
        //清除缓存
        this.clearMdMaterialCache();
    }

    @Override
    public void updateDataObject(MdMaterial obj) {
        this.mdMaterialDao.updateDataObject(obj);
        //清除缓存
        this.clearMdMaterialCache();
    }
    
    @Override
    public void insertOrUpdateDataObject(MdMaterial obj) {
        this.mdMaterialDao.insertOrUpdateDataObject(obj);
        //清除缓存
        this.clearMdMaterialCache();
    }

    @Override
    public void deleteDataObject(MdMaterial obj) {
        this.mdMaterialDao.deleteDataObject(obj);
        //清除缓存
        this.clearMdMaterialCache();
    }

    @Override
    public List<MdMaterial> getDataObjects() {
        return this.mdMaterialDao.getDataObjects();
    }

    @Override
    public MdMaterial getDataObject(int id) {
        return this.mdMaterialDao.getDataObject(id);
    }

    @Override
    public MdMaterial getDataObject(String code) {
        return this.mdMaterialDao.getDataObject(code);
    }

    @Override
    public List<MdMaterial> getDataObjects(MdMaterialCO paramObj) {
        return this.mdMaterialDao.getDataObjects(paramObj);
    }

    @Override
    public List<MdMaterial> getDataObjects(Pages pages) {
        return this.mdMaterialDao.getDataObjects(pages);
    }
    
    @Override
    public List<MdMaterial> getDataObjects(Pages pages, MdMaterialCO paramObj) {
        return this.mdMaterialDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, MdMaterialCO paramObj) {
        return this.mdMaterialDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<MdMaterial> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, MdMaterialCO paramObj) {
        return this.mdMaterialDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public void updateApproveStatus(int id, String approveStatus) {
        this.mdMaterialDao.updateApproveStatus(id, approveStatus);
    }
    
    //清理物料的所有缓存
    private void clearMdMaterialCache() {
        EhcacheUtil.clear(MasterDataParam.MATERIAL_CACHE_KEY);
        RedisUtil.clear(MasterDataParam.MATERIAL_CACHE_KEY);
        
        EhcacheUtil.clearBatch("*getMdMaterialInfoCache*");
        RedisUtil.clearBatch("*getMdMaterialInfoCache*");
    }
    
}