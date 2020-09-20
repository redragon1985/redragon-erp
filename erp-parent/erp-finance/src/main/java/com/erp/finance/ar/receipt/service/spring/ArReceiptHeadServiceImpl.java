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
import com.erp.finance.ar.receipt.dao.ArReceiptHeadDao;
import com.erp.finance.ar.receipt.dao.model.ArReceiptHead;
import com.erp.finance.ar.receipt.dao.model.ArReceiptHeadCO;
import com.erp.finance.ar.receipt.service.ArReceiptHeadService;
import com.erp.finance.ar.receipt.service.ArReceiptLineService;

@Service
@Transactional(rollbackFor=Exception.class)
public class ArReceiptHeadServiceImpl implements ArReceiptHeadService {

    //注入Dao
    @Autowired
    private ArReceiptHeadDao arReceiptHeadDao;
    @Autowired
    private ArReceiptLineService arReceiptLineService;
    
    @Override
    public void insertDataObject(ArReceiptHead obj) {
        this.arReceiptHeadDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(ArReceiptHead obj) {
        this.arReceiptHeadDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(ArReceiptHead obj) {
        this.arReceiptHeadDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(ArReceiptHead obj) {
        this.arReceiptHeadDao.deleteDataObject(obj);
        this.arReceiptLineService.deleteLineByHeadCode(obj.getReceiptHeadCode());
    }

    @Override
    public List<ArReceiptHead> getDataObjects() {
        return this.arReceiptHeadDao.getDataObjects();
    }

    @Override
    public ArReceiptHead getDataObject(int id) {
        return this.arReceiptHeadDao.getDataObject(id);
    }

    @Override
    public ArReceiptHead getDataObject(String code) {
        return this.arReceiptHeadDao.getDataObject(code);
    }

    @Override
    public List<ArReceiptHead> getDataObjects(ArReceiptHeadCO paramObj) {
        return this.arReceiptHeadDao.getDataObjects(paramObj);
    }

    @Override
    public List<ArReceiptHead> getDataObjects(Pages pages) {
        return this.arReceiptHeadDao.getDataObjects(pages);
    }
    
    @Override
    public List<ArReceiptHead> getDataObjects(Pages pages, ArReceiptHeadCO paramObj) {
        return this.arReceiptHeadDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, ArReceiptHeadCO paramObj) {
        return this.arReceiptHeadDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<ArReceiptHead> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, ArReceiptHeadCO paramObj) {
        return this.arReceiptHeadDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public void updateApproveStatus(String code, String approveStatus) {
        this.arReceiptHeadDao.updateApproveStatus(code, approveStatus);
    }
    
}