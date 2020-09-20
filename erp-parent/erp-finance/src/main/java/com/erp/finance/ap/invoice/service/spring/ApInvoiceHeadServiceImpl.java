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
package com.erp.finance.ap.invoice.service.spring;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.finance.ap.invoice.dao.ApInvoiceHeadDao;
import com.erp.finance.ap.invoice.dao.model.ApInvoiceHead;
import com.erp.finance.ap.invoice.dao.model.ApInvoiceHeadCO;
import com.erp.finance.ap.invoice.service.ApInvoiceHeadService;
import com.erp.finance.ap.invoice.service.ApInvoiceLineService;
import com.framework.annotation.Cache;
import com.framework.annotation.Cache.CacheType;
import com.framework.dao.model.Pages;

@Service
@Transactional(rollbackFor=Exception.class)
public class ApInvoiceHeadServiceImpl implements ApInvoiceHeadService {

    //注入Dao
    @Autowired
    private ApInvoiceHeadDao payHeadDao;
    @Autowired
    private ApInvoiceLineService payLineService;
    
    @Override
    public void insertDataObject(ApInvoiceHead obj) {
        this.payHeadDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(ApInvoiceHead obj) {
        this.payHeadDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(ApInvoiceHead obj) {
        this.payHeadDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(ApInvoiceHead obj) {
        this.payHeadDao.deleteDataObject(obj);
        this.payLineService.deleteLineByHeadCode(obj.getInvoiceHeadCode());
    }

    @Override
    public List<ApInvoiceHead> getDataObjects() {
        return this.payHeadDao.getDataObjects();
    }

    @Override
    public ApInvoiceHead getDataObject(int id) {
        return this.payHeadDao.getDataObject(id);
    }

    @Override
    public ApInvoiceHead getDataObject(String code) {
        return this.payHeadDao.getDataObject(code);
    }

    @Override
    public List<ApInvoiceHead> getDataObjects(ApInvoiceHeadCO paramObj) {
        return this.payHeadDao.getDataObjects(paramObj);
    }

    @Override
    public List<ApInvoiceHead> getDataObjects(Pages pages) {
        return this.payHeadDao.getDataObjects(pages);
    }
    
    @Override
    public List<ApInvoiceHead> getDataObjects(Pages pages, ApInvoiceHeadCO paramObj) {
        return this.payHeadDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, ApInvoiceHeadCO paramObj) {
        return this.payHeadDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<ApInvoiceHead> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, ApInvoiceHeadCO paramObj) {
        return this.payHeadDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public List<ApInvoiceHead> getApInvoiceHeadListForNotCreateVoucher(Pages pages, ApInvoiceHeadCO paramObj) {
        return this.payHeadDao.getApInvoiceHeadListForNotCreateVoucher(pages, paramObj);
    }
    
    @Override
    public void updateApproveStatus(String code, String approveStatus) {
        this.payHeadDao.updateApproveStatus(code, approveStatus);
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200)
    public int getApInvoiceHeadNum(String startDate, String endDate) {
        return this.payHeadDao.getApInvoiceHeadNum(startDate, endDate);
    }
    
}