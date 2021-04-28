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
package com.erp.order.poa.dao.hibernate;

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
import com.erp.order.po.dao.model.PoHead;
import com.erp.order.poa.dao.PoAgreementHeadDao;
import com.erp.order.poa.dao.model.PoAgreementHead;
import com.erp.order.poa.dao.model.PoAgreementHeadCO;

@Repository
public class PoAgreementHeadDaoImpl implements PoAgreementHeadDao{ 

    //注入basicDao工具类
    @Autowired
    private BasicDao basicDao;
    
    @Override
    public void insertDataObject(PoAgreementHead obj) {
        this.basicDao.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(PoAgreementHead obj) {
        this.basicDao.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(PoAgreementHead obj) {
        this.basicDao.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(PoAgreementHead obj) {
        this.basicDao.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<PoAgreementHead> getDataObjects() {
        return this.basicDao.getDataAllObject(PoAgreementHead.class);
    }

    @Override
    public PoAgreementHead getDataObject(int id) {
        return (PoAgreementHead)this.basicDao.getDataObject(PoAgreementHead.class, id);
    }
    
    @Override
    public PoAgreementHead getDataObject(String code) {
        String sql = "select p.* from po_agreement_head p where p.po_head_code = :code";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", code);
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("p", PoAgreementHead.class);
        
        List<PoAgreementHead> list = this.basicDao.selectData(sql, entity, args);
        if(list.size()>0) {
            return list.get(0);
        }
        
        return null;
    }
    
    @Override
    public List<PoAgreementHead> getDataObjects(PoAgreementHeadCO paramObj) {
        return null;
    }
    
    @Override
    public List<PoAgreementHead> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<PoAgreementHead> getDataObjects(Pages pages, PoAgreementHeadCO paramObj) {
        String sql = "select p.* from po_agreement_head p where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.settleParam(paramObj, "poHeadCode", "and p.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "poType", "and p.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "poName", "and p.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "vendorCode", "and p.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "projectCode", "and p.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "approveStatus", "and p.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "status", "and p.", args);
        sql = sql + " order by p.po_head_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("p", PoAgreementHead.class);
        
        return this.basicDao.getDataSql(sql, entity, args, pages);
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, PoAgreementHeadCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.DATA_AUTH)
    public List<PoAgreementHead> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, PoAgreementHeadCO paramObj) {
        String sql = "select p.* from po_agreement_head p where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.settleParam(paramObj, "poHeadCode", "and p.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "poType", "and p.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "poName", "and p.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "vendorCode", "and p.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "projectCode", "and p.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "approveStatus", "and p.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "status", "and p.", args);
        sql = sql + DaoUtil.getDataAuthSQL(dataAuthSQL, "p.", "p.");
        sql = sql + " order by p.po_head_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("p", PoAgreementHead.class);
        
        return this.basicDao.getDataSql(sql, entity, args, pages);
    }
    
    @Override
    public void updateApproveStatus(String code, String approveStatus) {
        String sql = "update po_agreement_head set approve_status = :approveStatus";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", code);
        args.put("approveStatus", approveStatus);
        
        if(approveStatus.equals("APPROVE")) {
            sql = sql + " ,status = 'CONFIRM'";
        }else if(approveStatus.equals("UNSUBMIT")) {
            sql = sql + " ,status = 'ALTER',version = version+1";
        }
        
        sql = sql + " where po_head_code = :code";
        
        this.basicDao.executeSQLTransaction(sql, args);
    }
    
}
