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