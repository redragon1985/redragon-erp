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