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
    }

    @Override
    public void updateDataObject(MdVendorContact obj) {
        this.mdVendorContactDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(MdVendorContact obj) {
        this.mdVendorContactDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(MdVendorContact obj) {
        this.mdVendorContactDao.deleteDataObject(obj);
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
    
}