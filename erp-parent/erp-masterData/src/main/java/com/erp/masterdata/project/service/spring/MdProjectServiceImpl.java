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
package com.erp.masterdata.project.service.spring;

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
import com.erp.masterdata.project.dao.MdProjectDao;
import com.erp.masterdata.project.dao.model.MdProject;
import com.erp.masterdata.project.dao.model.MdProjectCO;
import com.erp.masterdata.project.service.MdProjectService;

@Service
@Transactional(rollbackFor=Exception.class)
public class MdProjectServiceImpl implements MdProjectService {

    //注入Dao
    @Autowired
    private MdProjectDao mdProjectDao;
    
    @Override
    public void insertDataObject(MdProject obj) {
        this.mdProjectDao.insertDataObject(obj);
        //清除缓存
        EhcacheUtil.clear(MasterDataParam.PROJECT_CACHE_KEY);
        RedisUtil.clear(MasterDataParam.PROJECT_CACHE_KEY);
    }

    @Override
    public void updateDataObject(MdProject obj) {
        this.mdProjectDao.updateDataObject(obj);
        //清除缓存
        EhcacheUtil.clear(MasterDataParam.PROJECT_CACHE_KEY);
        RedisUtil.clear(MasterDataParam.PROJECT_CACHE_KEY);
    }
    
    @Override
    public void insertOrUpdateDataObject(MdProject obj) {
        this.mdProjectDao.insertOrUpdateDataObject(obj);
        //清除缓存
        EhcacheUtil.clear(MasterDataParam.PROJECT_CACHE_KEY);
        RedisUtil.clear(MasterDataParam.PROJECT_CACHE_KEY);
    }

    @Override
    public void deleteDataObject(MdProject obj) {
        this.mdProjectDao.deleteDataObject(obj);
        //清除缓存
        EhcacheUtil.clear(MasterDataParam.PROJECT_CACHE_KEY);
        RedisUtil.clear(MasterDataParam.PROJECT_CACHE_KEY);
    }

    @Override
    public List<MdProject> getDataObjects() {
        return this.mdProjectDao.getDataObjects();
    }

    @Override
    public MdProject getDataObject(int id) {
        return this.mdProjectDao.getDataObject(id);
    }

    @Override
    public MdProject getDataObject(String code) {
        return this.mdProjectDao.getDataObject(code);
    }

    @Override
    public List<MdProject> getDataObjects(MdProjectCO paramObj) {
        return this.mdProjectDao.getDataObjects(paramObj);
    }

    @Override
    public List<MdProject> getDataObjects(Pages pages) {
        return this.mdProjectDao.getDataObjects(pages);
    }
    
    @Override
    public List<MdProject> getDataObjects(Pages pages, MdProjectCO paramObj) {
        return this.mdProjectDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, MdProjectCO paramObj) {
        return this.mdProjectDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<MdProject> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, MdProjectCO paramObj) {
        return this.mdProjectDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public void updateApproveStatus(int id, String approveStatus) {
        this.mdProjectDao.updateApproveStatus(id, approveStatus);
    }
    
}