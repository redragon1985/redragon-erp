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