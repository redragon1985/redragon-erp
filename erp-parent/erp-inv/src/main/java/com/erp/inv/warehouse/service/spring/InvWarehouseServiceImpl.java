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
package com.erp.inv.warehouse.service.spring;

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
import com.erp.inv.warehouse.dao.InvWarehouseDao;
import com.erp.inv.warehouse.dao.model.InvWarehouse;
import com.erp.inv.warehouse.dao.model.InvWarehouseCO;
import com.erp.inv.warehouse.service.InvWarehouseService;

@Service
@Transactional(rollbackFor=Exception.class)
public class InvWarehouseServiceImpl implements InvWarehouseService {

    //注入Dao
    @Autowired
    private InvWarehouseDao invWarehouseDao;
    
    @Override
    public void insertDataObject(InvWarehouse obj) {
        this.invWarehouseDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(InvWarehouse obj) {
        this.invWarehouseDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(InvWarehouse obj) {
        this.invWarehouseDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(InvWarehouse obj) {
        this.invWarehouseDao.deleteDataObject(obj);
    }

    @Override
    public List<InvWarehouse> getDataObjects() {
        return this.invWarehouseDao.getDataObjects();
    }

    @Override
    public InvWarehouse getDataObject(int id) {
        return this.invWarehouseDao.getDataObject(id);
    }

    @Override
    public InvWarehouse getDataObject(String code) {
        return this.invWarehouseDao.getDataObject(code);
    }

    @Override
    public List<InvWarehouse> getDataObjects(InvWarehouseCO paramObj) {
        return this.invWarehouseDao.getDataObjects(paramObj);
    }

    @Override
    public List<InvWarehouse> getDataObjects(Pages pages) {
        return this.invWarehouseDao.getDataObjects(pages);
    }
    
    @Override
    public List<InvWarehouse> getDataObjects(Pages pages, InvWarehouseCO paramObj) {
        return this.invWarehouseDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, InvWarehouseCO paramObj) {
        return this.invWarehouseDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<InvWarehouse> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, InvWarehouseCO paramObj) {
        return this.invWarehouseDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public boolean isExistRelateDataForInvWarehouse(String warehouseCode) {
        return this.invWarehouseDao.isExistRelateDataForInvWarehouse(warehouseCode);
    }
    
}