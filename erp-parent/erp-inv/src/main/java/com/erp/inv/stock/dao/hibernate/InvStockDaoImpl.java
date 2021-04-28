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
package com.erp.inv.stock.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.dao.BasicDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.framework.annotation.Cache;
import com.framework.annotation.Permissions;
import com.framework.annotation.Permissions.PermissionType;
import com.framework.annotation.SqlParam;
import com.framework.dao.model.Pages;
import com.framework.util.DaoUtil;
import com.erp.inv.stock.dao.InvStockDao;
import com.erp.inv.stock.dao.model.InvStock;
import com.erp.inv.stock.dao.model.InvStockCO;

@Repository
public class InvStockDaoImpl implements InvStockDao{ 

    //注入basicDao工具类
    @Autowired
    private BasicDao basicDao;
    
    @Override
    public void insertDataObject(InvStock obj) {
        this.basicDao.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(InvStock obj) {
        this.basicDao.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(InvStock obj) {
        this.basicDao.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(InvStock obj) {
        this.basicDao.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<InvStock> getDataObjects() {
        return this.basicDao.getDataAllObject(InvStock.class);
    }

    @Override
    public InvStock getDataObject(int id) {
        return (InvStock)this.basicDao.getDataObject(InvStock.class, id);
    }
    
    @Override
    public InvStock getDataObject(String code) {
        return null;
    }
    
    @Override
    public List<InvStock> getDataObjects(InvStockCO paramObj) {
        return null;
    }
    
    @Override
    public List<InvStock> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<InvStock> getDataObjects(Pages pages, InvStockCO paramObj) {
        String sql = "select s.* from inv_stock s where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.settleParam(paramObj, "warehouseCode", "and s.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "materialCode", "and s.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "retainFlag", "and s.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "billType", "and s.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "billHeadCode", "and s.", args);
        sql = sql + " order by s.stock_id";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("s", InvStock.class);
        
        return this.basicDao.getDataSql(sql, entity, args, pages);
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, InvStockCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.DATA_AUTH)
    public List<InvStock> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, InvStockCO paramObj) {
        return null;
    }
    
    @Override
    public boolean isExistRelateDataForInvStock(String warehouseCode, String materialCode) {
        String sql = "select count(*) from inv_stock where warehouse_code = :warehouseCode and material_code = :materialCode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("warehouseCode", warehouseCode);
        args.put("materialCode", materialCode);
        
        List list = this.basicDao.selectDataSqlCount(sql, args);
        
        if(list.size()>0) {
            int num = this.basicDao.convertSQLCount(list.get(0));
            if(num<=1) {
                return false;
            }
        }
        
        return true;
    }
    
    @Override
    public boolean isExistInitDataForInvStock(String warehouseCode, String materialCode) {
        String sql = "select count(*) from inv_stock where warehouse_code = :warehouseCode and material_code = :materialCode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("warehouseCode", warehouseCode);
        args.put("materialCode", materialCode);
        
        List list = this.basicDao.selectDataSqlCount(sql, args);
        
        if(list.size()>0) {
            int num = this.basicDao.convertSQLCount(list.get(0));
            if(num==0) {
                return false;
            }
        }
        
        return true;
    }
    
    @Override
    public void deleteInvStockByBillHeadCode(String billType, String billHeadCode) {
        String sql = "delete from inv_stock where bill_type = :billType and bill_head_code = :billHeadCode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("billType", billType);
        args.put("billHeadCode", billHeadCode);
        
        this.basicDao.executeSQLTransaction(sql, args);
    }
    
    @Override
    public void deleteInvStockByBillLineCode(String billType, String billHeadCode, String billLineCode) {
        String sql = "delete from inv_stock where bill_type = :billType and bill_head_code = :billHeadCode and bill_line_code = :billLineCode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("billType", billType);
        args.put("billHeadCode", billHeadCode);
        args.put("billLineCode", billLineCode);
        
        this.basicDao.executeSQLTransaction(sql, args);
    }
    
    @Override
    public Double getStockNumberByMaterialCode(String materialCode) {
        String sql = "select sum(stock_number) from inv_stock where material_code = :materialCode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("materialCode", materialCode);
        
        List list = this.basicDao.selectDataSqlCount(sql, args);
        if(list.size()>0) {
            try {
                return Double.valueOf(list.get(0).toString());
            }catch(Exception e) {}
        }
        
        return 0D;
    }
    
    @Override
    public List<InvStock> getInitInvStockList(Pages pages, InvStockCO paramObj) {
        String sql = "select s.* from inv_stock s where bill_head_code is null";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.settleParam(paramObj, "warehouseCode", "and s.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "materialCode", "and s.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "retainFlag", "and s.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "billType", "and s.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "billHeadCode", "and s.", args);
        sql = sql + " order by s.stock_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("s", InvStock.class);
        
        return this.basicDao.getDataSql(sql, entity, args, pages);
    }
    
}
