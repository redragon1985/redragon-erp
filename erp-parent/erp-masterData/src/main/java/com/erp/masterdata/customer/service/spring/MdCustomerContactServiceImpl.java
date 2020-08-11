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
    }

    @Override
    public void updateDataObject(MdCustomerContact obj) {
        this.mdCustomerContactDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(MdCustomerContact obj) {
        this.mdCustomerContactDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(MdCustomerContact obj) {
        this.mdCustomerContactDao.deleteDataObject(obj);
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
    
}