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
package com.erp.finance.ap.pay.dao.hibernate;

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
import com.erp.finance.ap.invoice.dao.model.ApInvoiceHead;
import com.erp.finance.ap.pay.dao.ApPayHeadDao;
import com.erp.finance.ap.pay.dao.model.ApPayHead;
import com.erp.finance.ap.pay.dao.model.ApPayHeadCO;

@Repository
public class ApPayHeadDaoImpl implements ApPayHeadDao{ 

    //注入basicDao工具类
    @Autowired
    private BasicDao basicDao;
    
    @Override
    public void insertDataObject(ApPayHead obj) {
        this.basicDao.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(ApPayHead obj) {
        this.basicDao.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(ApPayHead obj) {
        this.basicDao.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(ApPayHead obj) {
        this.basicDao.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<ApPayHead> getDataObjects() {
        return this.basicDao.getDataAllObject(ApPayHead.class);
    }

    @Override
    public ApPayHead getDataObject(int id) {
        return (ApPayHead)this.basicDao.getDataObject(ApPayHead.class, id);
    }
    
    @Override
    public ApPayHead getDataObject(String code) {
        String sql = "select h.* from ap_pay_head h where h.pay_head_code = :code";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", code);
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("h", ApPayHead.class);
        
        List<ApPayHead> list = this.basicDao.selectData(sql, entity, args);
        if(list.size()>0) {
            return list.get(0);
        }
        
        return null;
    }
    
    @Override
    public List<ApPayHead> getDataObjects(ApPayHeadCO paramObj) {
        return null;
    }
    
    @Override
    public List<ApPayHead> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<ApPayHead> getDataObjects(Pages pages, ApPayHeadCO paramObj) {
        String sql = "select h.* from ap_pay_head h where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.settleParam(paramObj, "payHeadCode", "and h.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "vendorCode", "and h.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "amount", "and h.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "payDate", "and h.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "status", "and h.", args);
        sql = sql + " order by h.pay_head_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("h", ApPayHead.class);
        
        return this.basicDao.getDataSql(sql, entity, args, pages);
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, ApPayHeadCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.DATA_AUTH)
    public List<ApPayHead> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, ApPayHeadCO paramObj) {
        return null;
    }
    
    @Override
    public void updateApproveStatus(String code, String approveStatus) {
        String sql = "update ap_pay_head set approve_status = :approveStatus";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", code);
        args.put("approveStatus", approveStatus);
        
        if(approveStatus.equals("APPROVE")) {
            sql = sql + " ,status = 'CONFIRM'";
        }else if(approveStatus.equals("UNSUBMIT")) {
            sql = sql + " ,status = 'ALTER'";
        }
        
        sql = sql + " where pay_head_code = :code";
        
        this.basicDao.executeSQLTransaction(sql, args);
    }
    
    @Override
    public List<ApPayHead> getApPayHeadListForNotCreateVoucher(Pages pages, ApPayHeadCO paramObj) {
        String sql = "select p.* from ap_pay_head p where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.settleParam(paramObj, "payHeadCode", "and p.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "vendorCode", "and p.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "amount", "and p.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "payDate", "and p.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "status", "and p.", args);
        
        sql = sql + " and not exists (select 1 from fin_voucher_bill_r where bill_type = 'PAY' and bill_head_code = p.pay_head_code)";
        sql = sql + " order by p.pay_head_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("p", ApPayHead.class);
        
        return this.basicDao.getDataSql(sql, entity, args, pages);
    }
    
}
