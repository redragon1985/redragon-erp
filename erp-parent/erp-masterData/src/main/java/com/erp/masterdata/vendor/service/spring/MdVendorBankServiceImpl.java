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
import com.erp.masterdata.vendor.dao.MdVendorBankDao;
import com.erp.masterdata.vendor.dao.model.MdVendorBank;
import com.erp.masterdata.vendor.dao.model.MdVendorBankCO;
import com.erp.masterdata.vendor.service.MdVendorBankService;

@Service
@Transactional(rollbackFor=Exception.class)
public class MdVendorBankServiceImpl implements MdVendorBankService {

    //注入Dao
    @Autowired
    private MdVendorBankDao mdVendorBankDao;
    
    @Override
    public void insertDataObject(MdVendorBank obj) {
        this.mdVendorBankDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(MdVendorBank obj) {
        this.mdVendorBankDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(MdVendorBank obj) {
        this.mdVendorBankDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(MdVendorBank obj) {
        this.mdVendorBankDao.deleteDataObject(obj);
    }

    @Override
    public List<MdVendorBank> getDataObjects() {
        return this.mdVendorBankDao.getDataObjects();
    }

    @Override
    public MdVendorBank getDataObject(int id) {
        return this.mdVendorBankDao.getDataObject(id);
    }

    @Override
    public MdVendorBank getDataObject(String code) {
        return this.mdVendorBankDao.getDataObject(code);
    }

    @Override
    public List<MdVendorBank> getDataObjects(MdVendorBankCO paramObj) {
        return this.mdVendorBankDao.getDataObjects(paramObj);
    }

    @Override
    public List<MdVendorBank> getDataObjects(Pages pages) {
        return this.mdVendorBankDao.getDataObjects(pages);
    }
    
    @Override
    public List<MdVendorBank> getDataObjects(Pages pages, MdVendorBankCO paramObj) {
        return this.mdVendorBankDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, MdVendorBankCO paramObj) {
        return this.mdVendorBankDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<MdVendorBank> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, MdVendorBankCO paramObj) {
        return this.mdVendorBankDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public int getBankCountByVendorCode(String vendorCode) {
        return this.mdVendorBankDao.getBankCountByVendorCode(vendorCode);
    }
    
    @Override
    public List<MdVendorBank> getBankListByVendorCode(Pages pages, MdVendorBankCO paramObj) {
        return this.mdVendorBankDao.getBankListByVendorCode(pages, paramObj);
    }
    
}