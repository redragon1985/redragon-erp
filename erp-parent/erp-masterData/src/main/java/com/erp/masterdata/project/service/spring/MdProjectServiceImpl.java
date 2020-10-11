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
        //清除缓存
        EhcacheUtil.clear(MasterDataParam.PROJECT_CACHE_KEY);
        RedisUtil.clear(MasterDataParam.PROJECT_CACHE_KEY);
    }
    
}