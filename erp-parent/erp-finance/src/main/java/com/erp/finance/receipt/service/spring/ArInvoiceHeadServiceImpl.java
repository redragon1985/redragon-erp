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
import com.erp.finance.receipt.dao.ArInvoiceHeadDao;
import com.erp.finance.receipt.dao.model.ArInvoiceHead;
import com.erp.finance.receipt.dao.model.ArInvoiceHeadCO;
import com.erp.finance.receipt.service.ArInvoiceHeadService;
import com.erp.finance.receipt.service.ArInvoiceLineService;

@Service
@Transactional(rollbackFor=Exception.class)
public class ArInvoiceHeadServiceImpl implements ArInvoiceHeadService {

    //注入Dao
    @Autowired
    private ArInvoiceHeadDao receiptHeadDao;
    @Autowired
    private ArInvoiceLineService receiptLineService;
    
    @Override
    public void insertDataObject(ArInvoiceHead obj) {
        this.receiptHeadDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(ArInvoiceHead obj) {
        this.receiptHeadDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(ArInvoiceHead obj) {
        this.receiptHeadDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(ArInvoiceHead obj) {
        this.receiptHeadDao.deleteDataObject(obj);
        this.receiptLineService.deleteLineByHeadCode(obj.getInvoiceHeadCode());
    }

    @Override
    public List<ArInvoiceHead> getDataObjects() {
        return this.receiptHeadDao.getDataObjects();
    }

    @Override
    public ArInvoiceHead getDataObject(int id) {
        return this.receiptHeadDao.getDataObject(id);
    }

    @Override
    public ArInvoiceHead getDataObject(String code) {
        return this.receiptHeadDao.getDataObject(code);
    }

    @Override
    public List<ArInvoiceHead> getDataObjects(ArInvoiceHeadCO paramObj) {
        return this.receiptHeadDao.getDataObjects(paramObj);
    }

    @Override
    public List<ArInvoiceHead> getDataObjects(Pages pages) {
        return this.receiptHeadDao.getDataObjects(pages);
    }
    
    @Override
    public List<ArInvoiceHead> getDataObjects(Pages pages, ArInvoiceHeadCO paramObj) {
        return this.receiptHeadDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, ArInvoiceHeadCO paramObj) {
        return this.receiptHeadDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<ArInvoiceHead> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, ArInvoiceHeadCO paramObj) {
        return this.receiptHeadDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public List<ArInvoiceHead> getArInvoiceHeadListForNotCreateVoucher(Pages pages, ArInvoiceHeadCO paramObj) {
        return this.receiptHeadDao.getArInvoiceHeadListForNotCreateVoucher(pages, paramObj);
    }
    
    @Override
    public void updateApproveStatus(String code, String approveStatus) {
        this.receiptHeadDao.updateApproveStatus(code, approveStatus);
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200)
    public int getArInvoiceHeadNum(String startDate, String endDate) {
        return this.receiptHeadDao.getArInvoiceHeadNum(startDate, endDate);
    }
    
}