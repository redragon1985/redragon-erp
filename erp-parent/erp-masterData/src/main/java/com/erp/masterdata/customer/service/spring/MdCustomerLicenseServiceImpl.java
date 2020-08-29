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