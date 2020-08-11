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
import com.erp.masterdata.customer.dao.MdCustomerLicenseDao;
import com.erp.masterdata.customer.dao.model.MdCustomerBank;
import com.erp.masterdata.customer.dao.model.MdCustomerLicense;
import com.erp.masterdata.customer.dao.model.MdCustomerLicenseCO;
import com.erp.masterdata.customer.service.MdCustomerLicenseService;

@Service
@Transactional(rollbackFor=Exception.class)
public class MdCustomerLicenseServiceImpl implements MdCustomerLicenseService {

    //注入Dao
    @Autowired
    private MdCustomerLicenseDao mdCustomerLicenseDao;
    
    @Override
    public void insertDataObject(MdCustomerLicense obj) {
        this.mdCustomerLicenseDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(MdCustomerLicense obj) {
        this.mdCustomerLicenseDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(MdCustomerLicense obj) {
        this.mdCustomerLicenseDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(MdCustomerLicense obj) {
        this.mdCustomerLicenseDao.deleteDataObject(obj);
    }

    @Override
    public List<MdCustomerLicense> getDataObjects() {
        return this.mdCustomerLicenseDao.getDataObjects();
    }

    @Override
    public MdCustomerLicense getDataObject(int id) {
        return this.mdCustomerLicenseDao.getDataObject(id);
    }

    @Override
    public MdCustomerLicense getDataObject(String code) {
        return this.mdCustomerLicenseDao.getDataObject(code);
    }

    @Override
    public List<MdCustomerLicense> getDataObjects(MdCustomerLicenseCO paramObj) {
        return this.mdCustomerLicenseDao.getDataObjects(paramObj);
    }

    @Override
    public List<MdCustomerLicense> getDataObjects(Pages pages) {
        return this.mdCustomerLicenseDao.getDataObjects(pages);
    }
    
    @Override
    public List<MdCustomerLicense> getDataObjects(Pages pages, MdCustomerLicenseCO paramObj) {
        return this.mdCustomerLicenseDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, MdCustomerLicenseCO paramObj) {
        return this.mdCustomerLicenseDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<MdCustomerLicense> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, MdCustomerLicenseCO paramObj) {
        return this.mdCustomerLicenseDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public int getLicenseCountByCustomerCode(String customerCode) {
        return this.mdCustomerLicenseDao.getLicenseCountByCustomerCode(customerCode);
    }
    
    @Override
    public MdCustomerLicense getLicenseListByCustomerCode(MdCustomerLicense paramObj) {
        return this.mdCustomerLicenseDao.getLicenseListByCustomerCode(paramObj);
    }
    
}