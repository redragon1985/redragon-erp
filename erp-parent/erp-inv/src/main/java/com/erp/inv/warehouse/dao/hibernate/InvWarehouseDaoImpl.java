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
package com.erp.inv.warehouse.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.framework.annotation.Cache;
import com.framework.annotation.Permissions;
import com.framework.annotation.Permissions.PermissionType;
import com.framework.annotation.SqlParam;
import com.framework.dao.DaoSupport;
import com.framework.dao.model.Pages;
import com.framework.util.DaoUtil;
import com.erp.inv.check.dao.model.InvStockCheckHead;
import com.erp.inv.warehouse.dao.InvWarehouseDao;
import com.erp.inv.warehouse.dao.model.InvWarehouse;
import com.erp.inv.warehouse.dao.model.InvWarehouseCO;

@Repository
public class InvWarehouseDaoImpl implements InvWarehouseDao{ 

    //注入DaoSupport工具类
    @Autowired
    private DaoSupport daoSupport;
    
    @Override
    public void insertDataObject(InvWarehouse obj) {
        this.daoSupport.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(InvWarehouse obj) {
        this.daoSupport.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(InvWarehouse obj) {
        this.daoSupport.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(InvWarehouse obj) {
        this.daoSupport.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<InvWarehouse> getDataObjects() {
        return this.daoSupport.getDataAllObject(InvWarehouse.class);
    }

    @Override
    public InvWarehouse getDataObject(int id) {
        return (InvWarehouse)this.daoSupport.getDataObject(InvWarehouse.class, id);
    }
    
    @Override
    public InvWarehouse getDataObject(String code) {
        String sql = "select h.* from inv_warehouse h where h.warehouse_code = :code";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", code);
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("h", InvWarehouse.class);
        
        List<InvWarehouse> list = this.daoSupport.selectDataSql(sql, entity, args);
        if(list.size()>0) {
            return list.get(0);
        }
        
        return null;
    }
    
    @Override
    public List<InvWarehouse> getDataObjects(InvWarehouseCO paramObj) {
        return null;
    }
    
    @Override
    public List<InvWarehouse> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<InvWarehouse> getDataObjects(Pages pages, InvWarehouseCO paramObj) {
        String sql = "select w.* from inv_warehouse w where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.getSQLCondition(paramObj, "warehouseName", "and w.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "status", "and w.", args);
        sql = sql + " order by w.warehouse_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("w", InvWarehouse.class);
        
        return this.daoSupport.getDataSqlByPage(sql, entity, args, pages);
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, InvWarehouseCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.DATA_AUTH)
    public List<InvWarehouse> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, InvWarehouseCO paramObj) {
        return null;
    }
    
    @Override
    public boolean isExistRelateDataForInvWarehouse(String warehouseCode) {
        String sql = "select count(*) from inv_stock where warehouse_code = :warehouseCode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("warehouseCode", warehouseCode);
        
        List list = this.daoSupport.selectDataSqlCount(sql, args);
        if(list.size()>0) {
            int num = this.daoSupport.convertSQLCount(list.get(0));
            if(num==0) {
                return false;
            }
        }
        
        return true;
    }
    
}
