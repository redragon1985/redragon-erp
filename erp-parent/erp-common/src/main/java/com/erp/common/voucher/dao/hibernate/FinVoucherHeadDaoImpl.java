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
package com.erp.common.voucher.dao.hibernate;

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
import com.erp.common.voucher.dao.FinVoucherHeadDao;
import com.erp.common.voucher.dao.model.FinVoucherHead;
import com.erp.common.voucher.dao.model.FinVoucherHeadCO;

@Repository("finVoucherHeadDaoCommon")
public class FinVoucherHeadDaoImpl implements FinVoucherHeadDao{ 

    //注入DaoSupport工具类
    @Autowired
    private DaoSupport daoSupport;
    
    @Override
    public void insertDataObject(FinVoucherHead obj) {
        this.daoSupport.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(FinVoucherHead obj) {
        this.daoSupport.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(FinVoucherHead obj) {
        this.daoSupport.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(FinVoucherHead obj) {
        this.daoSupport.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<FinVoucherHead> getDataObjects() {
        return this.daoSupport.getDataAllObject(FinVoucherHead.class);
    }

    @Override
    public FinVoucherHead getDataObject(int id) {
        return (FinVoucherHead)this.daoSupport.getDataObject(FinVoucherHead.class, id);
    }
    
    @Override
    public FinVoucherHead getDataObject(String code) {
        return null;
    }
    
    @Override
    public List<FinVoucherHead> getDataObjects(FinVoucherHeadCO paramObj) {
        return null;
    }
    
    @Override
    public List<FinVoucherHead> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<FinVoucherHead> getDataObjects(Pages pages, FinVoucherHeadCO paramObj) {
        String sql = "select v.* from fin_voucher_head v where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.getSQLCondition(paramObj, "voucherType", "and v.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "voucherNumber", "and v.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "voucherDate", "and v.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "status", "and v.", args);
        sql = sql + " order by v.voucher_head_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("v", FinVoucherHead.class);
        
        return this.daoSupport.getDataSqlByPage(sql, entity, args, pages);
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, FinVoucherHeadCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.DATA_AUTH)
    public List<FinVoucherHead> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, FinVoucherHeadCO paramObj) {
        String sql = "select v.* from fin_voucher_head v where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.getSQLCondition(paramObj, "voucherType", "and v.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "voucherNumber", "and v.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "voucherDate", "and v.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "status", "and v.", args);
        sql = sql + DaoUtil.getDataAuthSQL(dataAuthSQL, "v.", "v.");
        sql = sql + " order by v.voucher_head_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("v", FinVoucherHead.class);
        
        return this.daoSupport.getDataSqlByPage(sql, entity, args, pages);
    }
    
    @Override
    public void deleteFinVoucherHeadByVoucherHeadCode(String voucherHeadCode) {
        String sql = "delete from fin_voucher_head where voucher_head_code = :voucherHeadCode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("voucherHeadCode", voucherHeadCode);
        
        this.daoSupport.executeSQLTransaction(sql, args);
    }
    
}
