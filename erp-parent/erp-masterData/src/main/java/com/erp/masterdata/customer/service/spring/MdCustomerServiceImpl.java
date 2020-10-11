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
package com.erp.masterdata.customer.service.spring;

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
import com.erp.masterdata.customer.dao.MdCustomerDao;
import com.erp.masterdata.customer.dao.model.MdCustomer;
import com.erp.masterdata.customer.dao.model.MdCustomerCO;
import com.erp.masterdata.customer.service.MdCustomerService;

@Service
@Transactional(rollbackFor=Exception.class)
public class MdCustomerServiceImpl implements MdCustomerService {

    //注入Dao
    @Autowired
    private MdCustomerDao mdCustomerDao;
    
    @Override
    public void insertDataObject(MdCustomer obj) {
        this.mdCustomerDao.insertDataObject(obj);
        //清除缓存
        this.clearCache();
    }

    @Override
    public void updateDataObject(MdCustomer obj) {
        this.mdCustomerDao.updateDataObject(obj);
        //清除缓存
        this.clearCache();
    }
    
    @Override
    public void insertOrUpdateDataObject(MdCustomer obj) {
        this.mdCustomerDao.insertOrUpdateDataObject(obj);
        //清除缓存
        this.clearCache();
    }

    @Override
    public void deleteDataObject(MdCustomer obj) {
        this.mdCustomerDao.deleteDataObject(obj);
        //清除缓存
        this.clearCache();
    }

    @Override
    public List<MdCustomer> getDataObjects() {
        return this.mdCustomerDao.getDataObjects();
    }

    @Override
    public MdCustomer getDataObject(int id) {
        return this.mdCustomerDao.getDataObject(id);
    }

    @Override
    public MdCustomer getDataObject(String code) {
        return this.mdCustomerDao.getDataObject(code);
    }

    @Override
    public List<MdCustomer> getDataObjects(MdCustomerCO paramObj) {
        return this.mdCustomerDao.getDataObjects(paramObj);
    }

    @Override
    public List<MdCustomer> getDataObjects(Pages pages) {
        return this.mdCustomerDao.getDataObjects(pages);
    }
    
    @Override
    public List<MdCustomer> getDataObjects(Pages pages, MdCustomerCO paramObj) {
        return this.mdCustomerDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, MdCustomerCO paramObj) {
        return this.mdCustomerDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<MdCustomer> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, MdCustomerCO paramObj) {
        return this.mdCustomerDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public List<MdCustomer> getMdCustomerListForOwn() {
        return this.mdCustomerDao.getMdCustomerListForOwn();
    }
    
    @Override
    public void updateApproveStatus(String code, String approveStatus) {
        this.mdCustomerDao.updateApproveStatus(code, approveStatus);
        //清除缓存
        this.clearCache();
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200)
    public int getCustomerNum() {
        return this.mdCustomerDao.getCustomerNum();
    }
    
    //清除缓存
    private void clearCache() {
        //清除缓存
        EhcacheUtil.clear(MasterDataParam.CUSTOMER_CACHE_KEY);
        RedisUtil.clear(MasterDataParam.CUSTOMER_CACHE_KEY);
        EhcacheUtil.clear(MasterDataParam.CUSTOMER_OWN_CACHE_KEY);
        RedisUtil.clear(MasterDataParam.CUSTOMER_OWN_CACHE_KEY);
        EhcacheUtil.clearBatch("*getMdCustomerInfoCache*");
        RedisUtil.clearBatch("*getMdCustomerInfoCache*");
    }
    
}