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
package com.erp.finance.receipt.service.spring;

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
import com.erp.finance.receipt.dao.ReceiptHeadDao;
import com.erp.finance.receipt.dao.model.ReceiptHead;
import com.erp.finance.receipt.dao.model.ReceiptHeadCO;
import com.erp.finance.receipt.service.ReceiptHeadService;
import com.erp.finance.receipt.service.ReceiptLineService;

@Service
@Transactional(rollbackFor=Exception.class)
public class ReceiptHeadServiceImpl implements ReceiptHeadService {

    //注入Dao
    @Autowired
    private ReceiptHeadDao receiptHeadDao;
    @Autowired
    private ReceiptLineService receiptLineService;
    
    @Override
    public void insertDataObject(ReceiptHead obj) {
        this.receiptHeadDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(ReceiptHead obj) {
        this.receiptHeadDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(ReceiptHead obj) {
        this.receiptHeadDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(ReceiptHead obj) {
        this.receiptHeadDao.deleteDataObject(obj);
        this.receiptLineService.deleteReceiptLineByReceiptHeadCode(obj.getReceiptHeadCode());
    }

    @Override
    public List<ReceiptHead> getDataObjects() {
        return this.receiptHeadDao.getDataObjects();
    }

    @Override
    public ReceiptHead getDataObject(int id) {
        return this.receiptHeadDao.getDataObject(id);
    }

    @Override
    public ReceiptHead getDataObject(String code) {
        return this.receiptHeadDao.getDataObject(code);
    }

    @Override
    public List<ReceiptHead> getDataObjects(ReceiptHeadCO paramObj) {
        return this.receiptHeadDao.getDataObjects(paramObj);
    }

    @Override
    public List<ReceiptHead> getDataObjects(Pages pages) {
        return this.receiptHeadDao.getDataObjects(pages);
    }
    
    @Override
    public List<ReceiptHead> getDataObjects(Pages pages, ReceiptHeadCO paramObj) {
        return this.receiptHeadDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, ReceiptHeadCO paramObj) {
        return this.receiptHeadDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<ReceiptHead> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, ReceiptHeadCO paramObj) {
        return this.receiptHeadDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public List<ReceiptHead> getReceiptHeadListForNotCreateVoucher(Pages pages, ReceiptHeadCO paramObj) {
        return this.receiptHeadDao.getReceiptHeadListForNotCreateVoucher(pages, paramObj);
    }
    
    @Override
    public void updateApproveStatus(String code, String approveStatus) {
        this.receiptHeadDao.updateApproveStatus(code, approveStatus);
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200)
    public int getReceiptHeadNum(String startDate, String endDate) {
        return this.receiptHeadDao.getReceiptHeadNum(startDate, endDate);
    }
    
}