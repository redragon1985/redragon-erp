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
package com.erp.finance.ar.receipt.dao.hibernate;

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
import com.erp.finance.ap.pay.dao.model.ApPayHead;
import com.erp.finance.ar.invoice.dao.model.ArInvoiceHead;
import com.erp.finance.ar.receipt.dao.ArReceiptHeadDao;
import com.erp.finance.ar.receipt.dao.model.ArReceiptHead;
import com.erp.finance.ar.receipt.dao.model.ArReceiptHeadCO;

@Repository
public class ArReceiptHeadDaoImpl implements ArReceiptHeadDao{ 

    //注入basicDao工具类
    @Autowired
    private BasicDao basicDao;
    
    @Override
    public void insertDataObject(ArReceiptHead obj) {
        this.basicDao.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(ArReceiptHead obj) {
        this.basicDao.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(ArReceiptHead obj) {
        this.basicDao.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(ArReceiptHead obj) {
        this.basicDao.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<ArReceiptHead> getDataObjects() {
        return this.basicDao.getDataAllObject(ArReceiptHead.class);
    }

    @Override
    public ArReceiptHead getDataObject(int id) {
        return (ArReceiptHead)this.basicDao.getDataObject(ArReceiptHead.class, id);
    }
    
    @Override
    public ArReceiptHead getDataObject(String code) {
        String sql = "select h.* from ar_receipt_head h where h.receipt_head_code = :code";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", code);
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("h", ArReceiptHead.class);
        
        List<ArReceiptHead> list = this.basicDao.selectData(sql, entity, args);
        if(list.size()>0) {
            return list.get(0);
        }
        
        return null;
    }
    
    @Override
    public List<ArReceiptHead> getDataObjects(ArReceiptHeadCO paramObj) {
        return null;
    }
    
    @Override
    public List<ArReceiptHead> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<ArReceiptHead> getDataObjects(Pages pages, ArReceiptHeadCO paramObj) {
        String sql = "select h.* from ar_receipt_head h where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.settleParam(paramObj, "receiptHeadCode", "and h.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "customerCode", "and h.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "amount", "and h.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "receiptDate", "and h.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "status", "and h.", args);
        sql = sql + " order by h.receipt_head_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("h", ArReceiptHead.class);
        
        return this.basicDao.getDataSql(sql, entity, args, pages);
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, ArReceiptHeadCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.DATA_AUTH)
    public List<ArReceiptHead> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, ArReceiptHeadCO paramObj) {
        return null;
    }
    
    @Override
    public void updateApproveStatus(String code, String approveStatus) {
        String sql = "update ar_receipt_head set approve_status = :approveStatus";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", code);
        args.put("approveStatus", approveStatus);
        
        if(approveStatus.equals("APPROVE")) {
            sql = sql + " ,status = 'CONFIRM'";
        }else if(approveStatus.equals("UNSUBMIT")) {
            sql = sql + " ,status = 'ALTER'";
        }
        
        sql = sql + " where receipt_head_code = :code";
        
        this.basicDao.executeSQLTransaction(sql, args);
    }
    
    @Override
    public List<ArReceiptHead> getArReceiptHeadListForNotCreateVoucher(Pages pages, ArReceiptHeadCO paramObj) {
        String sql = "select p.* from ar_receipt_head p where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.settleParam(paramObj, "receiptHeadCode", "and p.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "customerCode", "and p.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "amount", "and p.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "receiptDate", "and p.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "status", "and p.", args);
        
        sql = sql + " and not exists (select 1 from fin_voucher_bill_r where bill_type = 'RECEIPT' and bill_head_code = p.receipt_head_code)";
        sql = sql + " order by p.receipt_head_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("p", ArReceiptHead.class);
        
        return this.basicDao.getDataSql(sql, entity, args, pages);
    }
    
}
