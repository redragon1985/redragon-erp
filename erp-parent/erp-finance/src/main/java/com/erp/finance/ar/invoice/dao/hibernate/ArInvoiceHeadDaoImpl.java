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
package com.erp.finance.ar.invoice.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.finance.ar.invoice.dao.ArInvoiceHeadDao;
import com.erp.finance.ar.invoice.dao.model.ArInvoiceHead;
import com.erp.finance.ar.invoice.dao.model.ArInvoiceHeadCO;
import com.framework.annotation.Permissions;
import com.framework.annotation.Permissions.PermissionType;
import com.framework.annotation.SqlParam;
import com.framework.dao.DaoSupport;
import com.framework.dao.model.Pages;
import com.framework.util.DaoUtil;

@Repository
public class ArInvoiceHeadDaoImpl implements ArInvoiceHeadDao{ 

    //注入DaoSupport工具类
    @Autowired
    private DaoSupport daoSupport;
    
    @Override
    public void insertDataObject(ArInvoiceHead obj) {
        this.daoSupport.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(ArInvoiceHead obj) {
        this.daoSupport.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(ArInvoiceHead obj) {
        this.daoSupport.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(ArInvoiceHead obj) {
        this.daoSupport.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<ArInvoiceHead> getDataObjects() {
        return this.daoSupport.getDataAllObject(ArInvoiceHead.class);
    }

    @Override
    public ArInvoiceHead getDataObject(int id) {
        return (ArInvoiceHead)this.daoSupport.getDataObject(ArInvoiceHead.class, id);
    }
    
    @Override
    public ArInvoiceHead getDataObject(String code) {
        String sql = "select p.* from ar_invoice_head p where p.invoice_head_code = :code";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", code);
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("p", ArInvoiceHead.class);
        
        List<ArInvoiceHead> list = this.daoSupport.selectDataSql(sql, entity, args);
        if(list.size()>0) {
            return list.get(0);
        }
        
        return null;
    }
    
    @Override
    public List<ArInvoiceHead> getDataObjects(ArInvoiceHeadCO paramObj) {
        return null;
    }
    
    @Override
    public List<ArInvoiceHead> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<ArInvoiceHead> getDataObjects(Pages pages, ArInvoiceHeadCO paramObj) {
        String sql = "select p.* from ar_invoice_head p where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.getSQLCondition(paramObj, "invoiceHeadCode", "and p.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "invoiceSourceType", "and p.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "invoiceSourceHeadCode", "and p.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "payer", "and p.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "approveStatus", "and p.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "status", "and p.", args);
        sql = sql + " order by p.invoice_head_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("p", ArInvoiceHead.class);
        
        return this.daoSupport.getDataSqlByPage(sql, entity, args, pages);
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, ArInvoiceHeadCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.DATA_AUTH)
    public List<ArInvoiceHead> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, ArInvoiceHeadCO paramObj) {
        String sql = "select p.* from ar_invoice_head p where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.getSQLCondition(paramObj, "invoiceHeadCode", "and p.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "invoiceSourceType", "and p.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "invoiceSourceHeadCode", "and p.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "payer", "and p.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "approveStatus", "and p.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "status", "and p.", args);
        sql = sql + DaoUtil.getDataAuthSQL(dataAuthSQL, "p.", "p.");
        sql = sql + " order by p.invoice_head_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("p", ArInvoiceHead.class);
        
        return this.daoSupport.getDataSqlByPage(sql, entity, args, pages);
    }
    
    @Override
    public void updateApproveStatus(String code, String approveStatus) {
        String sql = "update ar_invoice_head set approve_status = :approveStatus";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", code);
        args.put("approveStatus", approveStatus);
        
        if(approveStatus.equals("APPROVE")) {
            sql = sql + " ,status = 'CONFIRM'";
        }else if(approveStatus.equals("UNSUBMIT")) {
            sql = sql + " ,status = 'ALTER'";
        }
        
        sql = sql + " where invoice_head_code = :code";
        
        this.daoSupport.executeSQLTransaction(sql, args);
    }
    
    @Override
    public int getArInvoiceHeadNum(String startDate, String endDate) {
        String sql = "select count(*) from ar_invoice_head where created_date between :startDate and :endDate";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("startDate", startDate);
        args.put("endDate", endDate);
        
        List list = this.daoSupport.selectDataSqlCount(sql, args);
        if(list.size()>0) {
            return this.daoSupport.convertSQLCount(list.get(0));
        }
        
        return 0;
    }
    
}
