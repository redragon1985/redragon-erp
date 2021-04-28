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
package com.erp.inv.input.dao.hibernate;

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
import com.erp.inv.input.dao.InvInputHeadDao;
import com.erp.inv.input.dao.model.InvInputHead;
import com.erp.inv.input.dao.model.InvInputHeadCO;

@Repository
public class InvInputHeadDaoImpl implements InvInputHeadDao{ 

    //注入basicDao工具类
    @Autowired
    private BasicDao basicDao;
    
    @Override
    public void insertDataObject(InvInputHead obj) {
        this.basicDao.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(InvInputHead obj) {
        this.basicDao.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(InvInputHead obj) {
        this.basicDao.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(InvInputHead obj) {
        this.basicDao.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<InvInputHead> getDataObjects() {
        return this.basicDao.getDataAllObject(InvInputHead.class);
    }

    @Override
    public InvInputHead getDataObject(int id) {
        return (InvInputHead)this.basicDao.getDataObject(InvInputHead.class, id);
    }
    
    @Override
    public InvInputHead getDataObject(String code) {
        String sql = "select i.* from inv_input_head i where i.input_head_code = :code";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", code);
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("i", InvInputHead.class);
        
        List<InvInputHead> list = this.basicDao.selectData(sql, entity, args);
        if(list.size()>0) {
            return list.get(0);
        }
        
        return null;
    }
    
    @Override
    public List<InvInputHead> getDataObjects(InvInputHeadCO paramObj) {
        return null;
    }
    
    @Override
    public List<InvInputHead> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<InvInputHead> getDataObjects(Pages pages, InvInputHeadCO paramObj) {
        String sql = "select i.* from inv_input_head i where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.settleParam(paramObj, "inputHeadCode", "and i.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "inputSourceType", "and i.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "inputSourceHeadCode", "and i.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "inputType", "and i.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "inputDate", "and i.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "status", "and i.", args);
        sql = sql + " order by i.input_head_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("i", InvInputHead.class);
        
        return this.basicDao.getDataSql(sql, entity, args, pages);
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, InvInputHeadCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.DATA_AUTH)
    public List<InvInputHead> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, InvInputHeadCO paramObj) {
        String sql = "select i.* from inv_input_head i where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.settleParam(paramObj, "inputHeadCode", "and i.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "inputSourceType", "and i.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "inputSourceHeadCode", "and i.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "inputType", "and i.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "inputDate", "and i.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "status", "and i.", args);
        sql = sql + DaoUtil.getDataAuthSQL(dataAuthSQL, "i.", "i.");
        sql = sql + " order by i.input_head_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("i", InvInputHead.class);
        
        return this.basicDao.getDataSql(sql, entity, args, pages);
    }
    
    @Override
    public void updateApproveStatus(String code, String approveStatus) {
        String sql = "update inv_input_head set approve_status = :approveStatus";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", code);
        args.put("approveStatus", approveStatus);
        
        if(approveStatus.equals("APPROVE")) {
            sql = sql + " ,status = 'CONFIRM'";
        }else if(approveStatus.equals("UNSUBMIT")) {
            sql = sql + " ,status = 'ALTER'";
        }
        
        sql = sql + " where input_head_code = :code";
        
        this.basicDao.executeSQLTransaction(sql, args);
    }
    
}
