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
import com.erp.masterdata.vendor.dao.MdVendorContactDao;
import com.erp.masterdata.vendor.dao.model.MdVendorContact;
import com.erp.masterdata.vendor.dao.model.MdVendorContactCO;
import com.erp.masterdata.vendor.service.MdVendorContactService;

@Service
@Transactional(rollbackFor=Exception.class)
public class MdVendorContactServiceImpl implements MdVendorContactService {

    //注入Dao
    @Autowired
    private MdVendorContactDao mdVendorContactDao;
    
    @Override
    public void insertDataObject(MdVendorContact obj) {
        this.mdVendorContactDao.insertDataObject(obj);
        //清除缓存
        this.clearCache();
    }

    @Override
    public void updateDataObject(MdVendorContact obj) {
        this.mdVendorContactDao.updateDataObject(obj);
        //清除缓存
        this.clearCache();
    }
    
    @Override
    public void insertOrUpdateDataObject(MdVendorContact obj) {
        this.mdVendorContactDao.insertOrUpdateDataObject(obj);
        //清除缓存
        this.clearCache();
    }

    @Override
    public void deleteDataObject(MdVendorContact obj) {
        this.mdVendorContactDao.deleteDataObject(obj);
        //清除缓存
        this.clearCache();
    }

    @Override
    public List<MdVendorContact> getDataObjects() {
        return this.mdVendorContactDao.getDataObjects();
    }

    @Override
    public MdVendorContact getDataObject(int id) {
        return this.mdVendorContactDao.getDataObject(id);
    }

    @Override
    public MdVendorContact getDataObject(String code) {
        return this.mdVendorContactDao.getDataObject(code);
    }

    @Override
    public List<MdVendorContact> getDataObjects(MdVendorContactCO paramObj) {
        return this.mdVendorContactDao.getDataObjects(paramObj);
    }

    @Override
    public List<MdVendorContact> getDataObjects(Pages pages) {
        return this.mdVendorContactDao.getDataObjects(pages);
    }
    
    @Override
    public List<MdVendorContact> getDataObjects(Pages pages, MdVendorContactCO paramObj) {
        return this.mdVendorContactDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, MdVendorContactCO paramObj) {
        return this.mdVendorContactDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<MdVendorContact> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, MdVendorContactCO paramObj) {
        return this.mdVendorContactDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public int getContactCountByVendorCode(String vendorCode) {
        return this.mdVendorContactDao.getContactCountByVendorCode(vendorCode);
    }
    
    @Override
    public List<MdVendorContact> getContactListByVendorCode(Pages pages, MdVendorContactCO paramObj) {
        return this.mdVendorContactDao.getContactListByVendorCode(pages, paramObj);
    }
    
    //清除缓存
    private void clearCache() {
        EhcacheUtil.clearBatch("*getMdVendorInfoCache*");
        RedisUtil.clearBatch("*getMdVendorInfoCache*");
    }
    
}