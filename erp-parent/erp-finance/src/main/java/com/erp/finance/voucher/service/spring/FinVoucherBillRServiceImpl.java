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
package com.erp.finance.voucher.service.spring;

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
import com.erp.finance.voucher.dao.FinVoucherBillRDao;
import com.erp.finance.voucher.dao.model.FinVoucherBillR;
import com.erp.finance.voucher.dao.model.FinVoucherBillRCO;
import com.erp.finance.voucher.service.FinVoucherBillRService;

@Service
@Transactional(rollbackFor=Exception.class)
public class FinVoucherBillRServiceImpl implements FinVoucherBillRService {

    //注入Dao
    @Autowired
    private FinVoucherBillRDao finVoucherBillRDao;
    
    @Override
    public void insertDataObject(FinVoucherBillR obj) {
        this.finVoucherBillRDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(FinVoucherBillR obj) {
        this.finVoucherBillRDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(FinVoucherBillR obj) {
        this.finVoucherBillRDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(FinVoucherBillR obj) {
        this.finVoucherBillRDao.deleteDataObject(obj);
    }

    @Override
    public List<FinVoucherBillR> getDataObjects() {
        return this.finVoucherBillRDao.getDataObjects();
    }

    @Override
    public FinVoucherBillR getDataObject(int id) {
        return this.finVoucherBillRDao.getDataObject(id);
    }

    @Override
    public FinVoucherBillR getDataObject(String code) {
        return this.finVoucherBillRDao.getDataObject(code);
    }

    @Override
    public List<FinVoucherBillR> getDataObjects(FinVoucherBillRCO paramObj) {
        return this.finVoucherBillRDao.getDataObjects(paramObj);
    }

    @Override
    public List<FinVoucherBillR> getDataObjects(Pages pages) {
        return this.finVoucherBillRDao.getDataObjects(pages);
    }
    
    @Override
    public List<FinVoucherBillR> getDataObjects(Pages pages, FinVoucherBillRCO paramObj) {
        return this.finVoucherBillRDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, FinVoucherBillRCO paramObj) {
        return this.finVoucherBillRDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<FinVoucherBillR> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, FinVoucherBillRCO paramObj) {
        return this.finVoucherBillRDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public void deleteFinVoucherBillRByVoucherHeadCode(String voucherHeadCode) {
        this.finVoucherBillRDao.deleteFinVoucherBillRByVoucherHeadCode(voucherHeadCode);
    }
    
}