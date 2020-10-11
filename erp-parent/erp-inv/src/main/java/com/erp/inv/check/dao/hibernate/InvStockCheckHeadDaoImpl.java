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
package com.erp.inv.check.dao.hibernate;

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
import com.erp.inv.check.dao.InvStockCheckHeadDao;
import com.erp.inv.check.dao.model.InvStockCheckHead;
import com.erp.inv.check.dao.model.InvStockCheckHeadCO;
import com.erp.inv.check.dao.model.InvStockData;

@Repository
public class InvStockCheckHeadDaoImpl implements InvStockCheckHeadDao{ 

    //注入DaoSupport工具类
    @Autowired
    private DaoSupport daoSupport;
    
    @Override
    public void insertDataObject(InvStockCheckHead obj) {
        this.daoSupport.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(InvStockCheckHead obj) {
        this.daoSupport.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(InvStockCheckHead obj) {
        this.daoSupport.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(InvStockCheckHead obj) {
        this.daoSupport.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<InvStockCheckHead> getDataObjects() {
        return this.daoSupport.getDataAllObject(InvStockCheckHead.class);
    }

    @Override
    public InvStockCheckHead getDataObject(int id) {
        return (InvStockCheckHead)this.daoSupport.getDataObject(InvStockCheckHead.class, id);
    }
    
    @Override
    public InvStockCheckHead getDataObject(String code) {
        String sql = "select h.* from inv_stock_check_head h where h.check_head_code = :code";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", code);
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("h", InvStockCheckHead.class);
        
        List<InvStockCheckHead> list = this.daoSupport.selectDataSql(sql, entity, args);
        if(list.size()>0) {
            return list.get(0);
        }
        
        return null;
    }
    
    @Override
    public List<InvStockCheckHead> getDataObjects(InvStockCheckHeadCO paramObj) {
        return null;
    }
    
    @Override
    public List<InvStockCheckHead> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<InvStockCheckHead> getDataObjects(Pages pages, InvStockCheckHeadCO paramObj) {
        String sql = "select h.* from inv_stock_check_head h where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.getSQLCondition(paramObj, "checkHeadCode", "and h.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "checkName", "and h.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "warehouseCode", "and h.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "checkDate", "and h.", args);
        sql = sql + " order by h.check_head_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("h", InvStockCheckHead.class);
        
        return this.daoSupport.getDataSqlByPage(sql, entity, args, pages);
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, InvStockCheckHeadCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.DATA_AUTH)
    public List<InvStockCheckHead> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, InvStockCheckHeadCO paramObj) {
        return null;
    }
    
    @Override
    public void updateApproveStatus(String code, String approveStatus) {
        String sql = "update inv_stock_check_head set approve_status = :approveStatus";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", code);
        args.put("approveStatus", approveStatus);
        
        if(approveStatus.equals("APPROVE")) {
            sql = sql + " ,status = 'CONFIRM'";
        }else if(approveStatus.equals("UNSUBMIT")) {
            sql = sql + " ,status = 'ALTER'";
        }
        
        sql = sql + " where check_head_code = :code";
        
        this.daoSupport.executeSQLTransaction(sql, args);
    }
    
    @Override
    public List<InvStockData> getInvStockDataForCheck(String warehouseCode) {
        String sql = "select s.material_code as materialCode,sum(stock_number) as stockNumber "
                   + "from inv_stock s where warehouse_code = :warehouseCode group by s.material_code";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("warehouseCode", warehouseCode);
        
        return this.daoSupport.selectDataSql(sql, InvStockData.class, args);
    }
    
}
