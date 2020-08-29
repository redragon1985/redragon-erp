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