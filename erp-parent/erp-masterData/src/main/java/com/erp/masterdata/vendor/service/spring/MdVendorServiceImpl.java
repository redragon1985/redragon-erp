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
        this.clearCache();
    }

    @Override
    public void updateDataObject(MdVendor obj) {
        this.mdVendorDao.updateDataObject(obj);
        //清除缓存
        this.clearCache();
    }
    
    @Override
    public void insertOrUpdateDataObject(MdVendor obj) {
        this.mdVendorDao.insertOrUpdateDataObject(obj);
        //清除缓存
        this.clearCache();
    }

    @Override
    public void deleteDataObject(MdVendor obj) {
        this.mdVendorDao.deleteDataObject(obj);
        //清除缓存
        this.clearCache();
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
    
    //清除缓存
    private void clearCache() {
        EhcacheUtil.clear(MasterDataParam.VENDOR_CACHE_KEY);
        RedisUtil.clear(MasterDataParam.VENDOR_CACHE_KEY);
        EhcacheUtil.clear(MasterDataParam.VENDOR_OWN_CACHE_KEY);
        RedisUtil.clear(MasterDataParam.VENDOR_OWN_CACHE_KEY);
        EhcacheUtil.clearBatch("*getMdVendorInfoCache*");
        RedisUtil.clearBatch("*getMdVendorInfoCache*");
    }
    
}