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
import com.erp.masterdata.customer.dao.MdCustomerBankDao;
import com.erp.masterdata.customer.dao.model.MdCustomerBank;
import com.erp.masterdata.customer.dao.model.MdCustomerBankCO;
import com.erp.masterdata.customer.service.MdCustomerBankService;

@Service
@Transactional(rollbackFor=Exception.class)
public class MdCustomerBankServiceImpl implements MdCustomerBankService {

    //注入Dao
    @Autowired
    private MdCustomerBankDao mdCustomerBankDao;
    
    @Override
    public void insertDataObject(MdCustomerBank obj) {
        this.mdCustomerBankDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(MdCustomerBank obj) {
        this.mdCustomerBankDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(MdCustomerBank obj) {
        this.mdCustomerBankDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(MdCustomerBank obj) {
        this.mdCustomerBankDao.deleteDataObject(obj);
    }

    @Override
    public List<MdCustomerBank> getDataObjects() {
        return this.mdCustomerBankDao.getDataObjects();
    }

    @Override
    public MdCustomerBank getDataObject(int id) {
        return this.mdCustomerBankDao.getDataObject(id);
    }

    @Override
    public MdCustomerBank getDataObject(String code) {
        return this.mdCustomerBankDao.getDataObject(code);
    }

    @Override
    public List<MdCustomerBank> getDataObjects(MdCustomerBankCO paramObj) {
        return this.mdCustomerBankDao.getDataObjects(paramObj);
    }

    @Override
    public List<MdCustomerBank> getDataObjects(Pages pages) {
        return this.mdCustomerBankDao.getDataObjects(pages);
    }
    
    @Override
    public List<MdCustomerBank> getDataObjects(Pages pages, MdCustomerBankCO paramObj) {
        return this.mdCustomerBankDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, MdCustomerBankCO paramObj) {
        return this.mdCustomerBankDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<MdCustomerBank> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, MdCustomerBankCO paramObj) {
        return this.mdCustomerBankDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public int getBankCountByCustomerCode(String customerCode) {
        return this.mdCustomerBankDao.getBankCountByCustomerCode(customerCode);
    }
    
    @Override
    public List<MdCustomerBank> getBankListByCustomerCode(Pages pages, MdCustomerBankCO paramObj) {
        return this.mdCustomerBankDao.getBankListByCustomerCode(pages, paramObj);
    }
    
}