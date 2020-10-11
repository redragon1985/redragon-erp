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
package com.erp.finance.ar.receipt.service.spring;

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
import com.erp.finance.ar.receipt.dao.ArReceiptLineDao;
import com.erp.finance.ar.receipt.dao.model.ArReceiptLine;
import com.erp.finance.ar.receipt.dao.model.ArReceiptLineCO;
import com.erp.finance.ar.receipt.service.ArReceiptLineService;

@Service
@Transactional(rollbackFor=Exception.class)
public class ArReceiptLineServiceImpl implements ArReceiptLineService {

    //注入Dao
    @Autowired
    private ArReceiptLineDao arReceiptLineDao;
    
    @Override
    public void insertDataObject(ArReceiptLine obj) {
        this.arReceiptLineDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(ArReceiptLine obj) {
        this.arReceiptLineDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(ArReceiptLine obj) {
        this.arReceiptLineDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(ArReceiptLine obj) {
        this.arReceiptLineDao.deleteDataObject(obj);
    }

    @Override
    public List<ArReceiptLine> getDataObjects() {
        return this.arReceiptLineDao.getDataObjects();
    }

    @Override
    public ArReceiptLine getDataObject(int id) {
        return this.arReceiptLineDao.getDataObject(id);
    }

    @Override
    public ArReceiptLine getDataObject(String code) {
        return this.arReceiptLineDao.getDataObject(code);
    }

    @Override
    public List<ArReceiptLine> getDataObjects(ArReceiptLineCO paramObj) {
        return this.arReceiptLineDao.getDataObjects(paramObj);
    }

    @Override
    public List<ArReceiptLine> getDataObjects(Pages pages) {
        return this.arReceiptLineDao.getDataObjects(pages);
    }
    
    @Override
    public List<ArReceiptLine> getDataObjects(Pages pages, ArReceiptLineCO paramObj) {
        return this.arReceiptLineDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, ArReceiptLineCO paramObj) {
        return this.arReceiptLineDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<ArReceiptLine> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, ArReceiptLineCO paramObj) {
        return this.arReceiptLineDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public List<ArReceiptLine> getReceiptLineListByHeadCode(Pages pages, ArReceiptLineCO paramObj) {
        return this.arReceiptLineDao.getReceiptLineListByHeadCode(pages, paramObj);
    }
    
    @Override
    public void deleteLineByHeadCode(String headCode) {
        this.arReceiptLineDao.deleteLineByHeadCode(headCode);
    }
    
    @Override
    public List<ArReceiptLine> getArReceiptLineListByHeadCode(String headCode) {
        return this.arReceiptLineDao.getArReceiptLineListByHeadCode(headCode);
    }
    
    @Override
    public Double getInvoiceReceiveAmount(String invoiceHeadCode, Integer receiptLineId) {
        return this.arReceiptLineDao.getInvoiceReceiveAmount(invoiceHeadCode, receiptLineId);
    }
    
}