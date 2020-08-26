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
package com.erp.inv.stock.dao.hibernate;

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
import com.erp.inv.stock.dao.InvStockDao;
import com.erp.inv.stock.dao.model.InvStock;
import com.erp.inv.stock.dao.model.InvStockCO;

@Repository
public class InvStockDaoImpl implements InvStockDao{ 

    //注入DaoSupport工具类
    @Autowired
    private DaoSupport daoSupport;
    
    @Override
    public void insertDataObject(InvStock obj) {
        this.daoSupport.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(InvStock obj) {
        this.daoSupport.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(InvStock obj) {
        this.daoSupport.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(InvStock obj) {
        this.daoSupport.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<InvStock> getDataObjects() {
        return this.daoSupport.getDataAllObject(InvStock.class);
    }

    @Override
    public InvStock getDataObject(int id) {
        return (InvStock)this.daoSupport.getDataObject(InvStock.class, id);
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
        sql = sql + DaoUtil.getSQLCondition(paramObj, "warehouseCode", "and s.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "materialCode", "and s.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "retainFlag", "and s.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "billType", "and s.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "billHeadCode", "and s.", args);
        sql = sql + " order by s.stock_id";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("s", InvStock.class);
        
        return this.daoSupport.getDataSqlByPage(sql, entity, args, pages);
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
        
        List list = this.daoSupport.selectDataSqlCount(sql, args);
        
        if(list.size()>0) {
            int num = this.daoSupport.convertSQLCount(list.get(0));
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
        
        List list = this.daoSupport.selectDataSqlCount(sql, args);
        
        if(list.size()>0) {
            int num = this.daoSupport.convertSQLCount(list.get(0));
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
        
        this.daoSupport.executeSQLTransaction(sql, args);
    }
    
    @Override
    public void deleteInvStockByBillLineCode(String billType, String billHeadCode, String billLineCode) {
        String sql = "delete from inv_stock where bill_type = :billType and bill_head_code = :billHeadCode and bill_line_code = :billLineCode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("billType", billType);
        args.put("billHeadCode", billHeadCode);
        args.put("billLineCode", billLineCode);
        
        this.daoSupport.executeSQLTransaction(sql, args);
    }
    
    @Override
    public Double getStockNumberByMaterialCode(String materialCode) {
        String sql = "select sum(stock_number) from inv_stock where material_code = :materialCode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("materialCode", materialCode);
        
        List list = this.daoSupport.selectDataSqlCount(sql, args);
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
        sql = sql + DaoUtil.getSQLCondition(paramObj, "warehouseCode", "and s.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "materialCode", "and s.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "retainFlag", "and s.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "billType", "and s.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "billHeadCode", "and s.", args);
        sql = sql + " order by s.stock_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("s", InvStock.class);
        
        return this.daoSupport.getDataSqlByPage(sql, entity, args, pages);
    }
    
}
