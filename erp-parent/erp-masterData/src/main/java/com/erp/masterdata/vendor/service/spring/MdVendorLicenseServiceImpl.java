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
import com.erp.masterdata.vendor.dao.MdVendorLicenseDao;
import com.erp.masterdata.vendor.dao.model.MdVendorLicense;
import com.erp.masterdata.vendor.dao.model.MdVendorLicenseCO;
import com.erp.masterdata.vendor.service.MdVendorLicenseService;

@Service
@Transactional(rollbackFor=Exception.class)
public class MdVendorLicenseServiceImpl implements MdVendorLicenseService {

    //注入Dao
    @Autowired
    private MdVendorLicenseDao mdVendorLicenseDao;
    
    @Override
    public void insertDataObject(MdVendorLicense obj) {
        this.mdVendorLicenseDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(MdVendorLicense obj) {
        this.mdVendorLicenseDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(MdVendorLicense obj) {
        this.mdVendorLicenseDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(MdVendorLicense obj) {
        this.mdVendorLicenseDao.deleteDataObject(obj);
    }

    @Override
    public List<MdVendorLicense> getDataObjects() {
        return this.mdVendorLicenseDao.getDataObjects();
    }

    @Override
    public MdVendorLicense getDataObject(int id) {
        return this.mdVendorLicenseDao.getDataObject(id);
    }

    @Override
    public MdVendorLicense getDataObject(String code) {
        return this.mdVendorLicenseDao.getDataObject(code);
    }

    @Override
    public List<MdVendorLicense> getDataObjects(MdVendorLicenseCO paramObj) {
        return this.mdVendorLicenseDao.getDataObjects(paramObj);
    }

    @Override
    public List<MdVendorLicense> getDataObjects(Pages pages) {
        return this.mdVendorLicenseDao.getDataObjects(pages);
    }
    
    @Override
    public List<MdVendorLicense> getDataObjects(Pages pages, MdVendorLicenseCO paramObj) {
        return this.mdVendorLicenseDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, MdVendorLicenseCO paramObj) {
        return this.mdVendorLicenseDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<MdVendorLicense> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, MdVendorLicenseCO paramObj) {
        return this.mdVendorLicenseDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public int getLicenseCountByVendorCode(String vendorCode) {
        return this.mdVendorLicenseDao.getLicenseCountByVendorCode(vendorCode);
    }
    
    @Override
    public MdVendorLicense getLicenseListByVendorCode(MdVendorLicense paramObj) {
        return this.mdVendorLicenseDao.getLicenseListByVendorCode(paramObj);
    }
    
}