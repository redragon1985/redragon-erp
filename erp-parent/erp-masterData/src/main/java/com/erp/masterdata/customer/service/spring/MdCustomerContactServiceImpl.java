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
import com.erp.masterdata.customer.dao.MdCustomerContactDao;
import com.erp.masterdata.customer.dao.model.MdCustomerBank;
import com.erp.masterdata.customer.dao.model.MdCustomerContact;
import com.erp.masterdata.customer.dao.model.MdCustomerContactCO;
import com.erp.masterdata.customer.service.MdCustomerContactService;

@Service
@Transactional(rollbackFor=Exception.class)
public class MdCustomerContactServiceImpl implements MdCustomerContactService {

    //注入Dao
    @Autowired
    private MdCustomerContactDao mdCustomerContactDao;
    
    @Override
    public void insertDataObject(MdCustomerContact obj) {
        this.mdCustomerContactDao.insertDataObject(obj);
        //清除缓存
        this.clearCache();
    }

    @Override
    public void updateDataObject(MdCustomerContact obj) {
        this.mdCustomerContactDao.updateDataObject(obj);
        //清除缓存
        this.clearCache();
    }
    
    @Override
    public void insertOrUpdateDataObject(MdCustomerContact obj) {
        this.mdCustomerContactDao.insertOrUpdateDataObject(obj);
        //清除缓存
        this.clearCache();
    }

    @Override
    public void deleteDataObject(MdCustomerContact obj) {
        this.mdCustomerContactDao.deleteDataObject(obj);
        //清除缓存
        this.clearCache();
    }

    @Override
    public List<MdCustomerContact> getDataObjects() {
        return this.mdCustomerContactDao.getDataObjects();
    }

    @Override
    public MdCustomerContact getDataObject(int id) {
        return this.mdCustomerContactDao.getDataObject(id);
    }

    @Override
    public MdCustomerContact getDataObject(String code) {
        return this.mdCustomerContactDao.getDataObject(code);
    }

    @Override
    public List<MdCustomerContact> getDataObjects(MdCustomerContactCO paramObj) {
        return this.mdCustomerContactDao.getDataObjects(paramObj);
    }

    @Override
    public List<MdCustomerContact> getDataObjects(Pages pages) {
        return this.mdCustomerContactDao.getDataObjects(pages);
    }
    
    @Override
    public List<MdCustomerContact> getDataObjects(Pages pages, MdCustomerContactCO paramObj) {
        return this.mdCustomerContactDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, MdCustomerContactCO paramObj) {
        return this.mdCustomerContactDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<MdCustomerContact> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, MdCustomerContactCO paramObj) {
        return this.mdCustomerContactDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public int getContactCountByCustomerCode(String customerCode) {
        return this.mdCustomerContactDao.getContactCountByCustomerCode(customerCode);
    }
    
    @Override
    public List<MdCustomerContact> getContactListByCustomerCode(Pages pages, MdCustomerContactCO paramObj) {
        return this.mdCustomerContactDao.getContactListByCustomerCode(pages, paramObj);
    }
    
    //清除缓存
    private void clearCache() {
        //清除缓存
        EhcacheUtil.clearBatch("*getMdCustomerInfoCache*");
        RedisUtil.clearBatch("*getMdCustomerInfoCache*");
    }
    
}