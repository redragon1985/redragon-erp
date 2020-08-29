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

import java.math.BigDecimal;
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
import com.erp.finance.receipt.dao.ReceiptLineDao;
import com.erp.finance.receipt.dao.model.ReceiptLine;
import com.erp.finance.receipt.dao.model.ReceiptLineCO;
import com.erp.finance.receipt.service.ReceiptLineService;

@Service
@Transactional(rollbackFor=Exception.class)
public class ReceiptLineServiceImpl implements ReceiptLineService {

    //注入Dao
    @Autowired
    private ReceiptLineDao receiptLineDao;
    
    @Override
    public void insertDataObject(ReceiptLine obj) {
        this.receiptLineDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(ReceiptLine obj) {
        this.receiptLineDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(ReceiptLine obj) {
        this.receiptLineDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(ReceiptLine obj) {
        this.receiptLineDao.deleteDataObject(obj);
    }

    @Override
    public List<ReceiptLine> getDataObjects() {
        return this.receiptLineDao.getDataObjects();
    }

    @Override
    public ReceiptLine getDataObject(int id) {
        return this.receiptLineDao.getDataObject(id);
    }

    @Override
    public ReceiptLine getDataObject(String code) {
        return this.receiptLineDao.getDataObject(code);
    }

    @Override
    public List<ReceiptLine> getDataObjects(ReceiptLineCO paramObj) {
        return this.receiptLineDao.getDataObjects(paramObj);
    }

    @Override
    public List<ReceiptLine> getDataObjects(Pages pages) {
        return this.receiptLineDao.getDataObjects(pages);
    }
    
    @Override
    public List<ReceiptLine> getDataObjects(Pages pages, ReceiptLineCO paramObj) {
        return this.receiptLineDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, ReceiptLineCO paramObj) {
        return this.receiptLineDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<ReceiptLine> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, ReceiptLineCO paramObj) {
        return this.receiptLineDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public void deleteReceiptLineByReceiptHeadCode(String receiptHeadCode) {
        this.receiptLineDao.deleteReceiptLineByReceiptHeadCode(receiptHeadCode);
    }
    
    @Override
    public BigDecimal getHISReceiptAmountForSO(String soHeadCode, String receiptHeadCode) {
        return this.receiptLineDao.getHISReceiptAmountForSO(soHeadCode, receiptHeadCode);
    }
    
    @Override
    public List<ReceiptLine> getReceiptLineListByReceiptHeadCode(Pages pages, ReceiptLineCO paramObj) {
        return this.receiptLineDao.getReceiptLineListByReceiptHeadCode(pages, paramObj);
    }
    
    @Override
    public BigDecimal getReceiptAmountByReceiptHeadCode(String receiptHeadCode) {
        return this.receiptLineDao.getReceiptAmountByReceiptHeadCode(receiptHeadCode);
    }
    
}