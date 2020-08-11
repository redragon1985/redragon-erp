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
package com.erp.masterdata.vendor.service.spring;

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
import com.erp.masterdata.vendor.dao.MdVendorDao;
import com.erp.masterdata.vendor.dao.model.MdVendor;
import com.erp.masterdata.vendor.dao.model.MdVendorCO;
import com.erp.masterdata.vendor.service.MdVendorService;

@Service
@Transactional(rollbackFor=Exception.class)
public class MdVendorServiceImpl implements MdVendorService {

    //注入Dao
    @Autowired
    private MdVendorDao mdVendorDao;
    
    @Override
    public void insertDataObject(MdVendor obj) {
        this.mdVendorDao.insertDataObject(obj);
        //清除缓存
        EhcacheUtil.clear(MasterDataParam.VENDOR_CACHE_KEY);
        RedisUtil.clear(MasterDataParam.VENDOR_CACHE_KEY);
        EhcacheUtil.clear(MasterDataParam.VENDOR_OWN_CACHE_KEY);
        RedisUtil.clear(MasterDataParam.VENDOR_OWN_CACHE_KEY);
    }

    @Override
    public void updateDataObject(MdVendor obj) {
        this.mdVendorDao.updateDataObject(obj);
        //清除缓存
        EhcacheUtil.clear(MasterDataParam.VENDOR_CACHE_KEY);
        RedisUtil.clear(MasterDataParam.VENDOR_CACHE_KEY);
        EhcacheUtil.clear(MasterDataParam.VENDOR_OWN_CACHE_KEY);
        RedisUtil.clear(MasterDataParam.VENDOR_OWN_CACHE_KEY);
    }
    
    @Override
    public void insertOrUpdateDataObject(MdVendor obj) {
        this.mdVendorDao.insertOrUpdateDataObject(obj);
        //清除缓存
        EhcacheUtil.clear(MasterDataParam.VENDOR_CACHE_KEY);
        RedisUtil.clear(MasterDataParam.VENDOR_CACHE_KEY);
        EhcacheUtil.clear(MasterDataParam.VENDOR_OWN_CACHE_KEY);
        RedisUtil.clear(MasterDataParam.VENDOR_OWN_CACHE_KEY);
    }

    @Override
    public void deleteDataObject(MdVendor obj) {
        this.mdVendorDao.deleteDataObject(obj);
        //清除缓存
        EhcacheUtil.clear(MasterDataParam.VENDOR_CACHE_KEY);
        RedisUtil.clear(MasterDataParam.VENDOR_CACHE_KEY);
        EhcacheUtil.clear(MasterDataParam.VENDOR_OWN_CACHE_KEY);
        RedisUtil.clear(MasterDataParam.VENDOR_OWN_CACHE_KEY);
    }

    @Override
    public List<MdVendor> getDataObjects() {
        return this.mdVendorDao.getDataObjects();
    }

    @Override
    public MdVendor getDataObject(int id) {
        return this.mdVendorDao.getDataObject(id);
    }

    @Override
    public MdVendor getDataObject(String code) {
        return this.mdVendorDao.getDataObject(code);
    }

    @Override
    public List<MdVendor> getDataObjects(MdVendorCO paramObj) {
        return this.mdVendorDao.getDataObjects(paramObj);
    }

    @Override
    public List<MdVendor> getDataObjects(Pages pages) {
        return this.mdVendorDao.getDataObjects(pages);
    }
    
    @Override
    public List<MdVendor> getDataObjects(Pages pages, MdVendorCO paramObj) {
        return this.mdVendorDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, MdVendorCO paramObj) {
        return this.mdVendorDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<MdVendor> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, MdVendorCO paramObj) {
        return this.mdVendorDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public List<MdVendor> getMdVendorListForOwn() {
        return this.mdVendorDao.getMdVendorListForOwn();
    }
    
    @Override
    public void updateApproveStatus(String code, String approveStatus) {
        this.mdVendorDao.updateApproveStatus(code, approveStatus);
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200)
    public int getVendorNum() {
        return this.mdVendorDao.getVendorNum();
    }
    
}