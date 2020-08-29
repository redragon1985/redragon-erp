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
package com.erp.inv.stock.service.spring;

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
import com.erp.inv.stock.dao.InvStockDao;
import com.erp.inv.stock.dao.model.InvStock;
import com.erp.inv.stock.dao.model.InvStockCO;
import com.erp.inv.stock.service.InvStockService;

@Service
@Transactional(rollbackFor=Exception.class)
public class InvStockServiceImpl implements InvStockService {

    //注入Dao
    @Autowired
    private InvStockDao invStockDao;
    
    @Override
    public void insertDataObject(InvStock obj) {
        this.invStockDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(InvStock obj) {
        this.invStockDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(InvStock obj) {
        this.invStockDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(InvStock obj) {
        this.invStockDao.deleteDataObject(obj);
    }

    @Override
    public List<InvStock> getDataObjects() {
        return this.invStockDao.getDataObjects();
    }

    @Override
    public InvStock getDataObject(int id) {
        return this.invStockDao.getDataObject(id);
    }

    @Override
    public InvStock getDataObject(String code) {
        return this.invStockDao.getDataObject(code);
    }

    @Override
    public List<InvStock> getDataObjects(InvStockCO paramObj) {
        return this.invStockDao.getDataObjects(paramObj);
    }

    @Override
    public List<InvStock> getDataObjects(Pages pages) {
        return this.invStockDao.getDataObjects(pages);
    }
    
    @Override
    public List<InvStock> getDataObjects(Pages pages, InvStockCO paramObj) {
        return this.invStockDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, InvStockCO paramObj) {
        return this.invStockDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<InvStock> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, InvStockCO paramObj) {
        return this.invStockDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public boolean isExistRelateDataForInvStock(String warehouseCode, String materialCode) {
        return this.invStockDao.isExistRelateDataForInvStock(warehouseCode, materialCode);
    }
    
    @Override
    public boolean isExistInitDataForInvStock(String warehouseCode, String materialCode) {
        return this.invStockDao.isExistInitDataForInvStock(warehouseCode, materialCode);
    }
    
    @Override
    public void deleteInvStockByBillHeadCode(String billType, String billHeadCode) {
        this.invStockDao.deleteInvStockByBillHeadCode(billType, billHeadCode);
    }
    
    @Override
    public void deleteInvStockByBillLineCode(String billType, String billHeadCode, String billLineCode) {
        this.invStockDao.deleteInvStockByBillLineCode(billType, billHeadCode, billLineCode);
    }
    
    @Override
    public Double getStockNumberByMaterialCode(String materialCode) {
        return this.invStockDao.getStockNumberByMaterialCode(materialCode);
    }
    
    @Override
    public List<InvStock> getInitInvStockList(Pages pages, InvStockCO paramObj) {
        return this.invStockDao.getInitInvStockList(pages, paramObj);
    }
    
}