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
package com.erp.finance.voucher.dao.hibernate;

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
import com.erp.finance.voucher.dao.FinVoucherModelHeadDao;
import com.erp.finance.voucher.dao.model.FinVoucherHead;
import com.erp.finance.voucher.dao.model.FinVoucherModelHead;
import com.erp.finance.voucher.dao.model.FinVoucherModelHeadCO;

@Repository
public class FinVoucherModelHeadDaoImpl implements FinVoucherModelHeadDao{ 

    //注入basicDao工具类
    @Autowired
    private BasicDao basicDao;
    
    @Override
    public void insertDataObject(FinVoucherModelHead obj) {
        this.basicDao.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(FinVoucherModelHead obj) {
        this.basicDao.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(FinVoucherModelHead obj) {
        this.basicDao.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(FinVoucherModelHead obj) {
        this.basicDao.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<FinVoucherModelHead> getDataObjects() {
        return this.basicDao.getDataAllObject(FinVoucherModelHead.class);
    }

    @Override
    public FinVoucherModelHead getDataObject(int id) {
        return (FinVoucherModelHead)this.basicDao.getDataObject(FinVoucherModelHead.class, id);
    }
    
    @Override
    public FinVoucherModelHead getDataObject(String code) {
        return null;
    }
    
    @Override
    public List<FinVoucherModelHead> getDataObjects(FinVoucherModelHeadCO paramObj) {
        return null;
    }
    
    @Override
    public List<FinVoucherModelHead> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<FinVoucherModelHead> getDataObjects(Pages pages, FinVoucherModelHeadCO paramObj) {
        String sql = "select v.* from fin_voucher_model_head v where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.settleParam(paramObj, "voucherType", "and v.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "modelName", "and v.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "businessType", "and v.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "status", "and v.", args);
        sql = sql + " order by v.voucher_head_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("v", FinVoucherModelHead.class);
        
        return this.basicDao.getDataSql(sql, entity, args, pages);
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, FinVoucherModelHeadCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.OWN)
    public List<FinVoucherModelHead> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, FinVoucherModelHeadCO paramObj) {
        return null;
    }
    
    @Override
    public List<FinVoucherModelHead> getFinVoucherModelHeadListForCustom() {
        String sql = "select h.* from fin_voucher_model_head h where h.status = 'Y' "
                   + "and h.business_type = 'CUSTOM' "
                   + "order by h.model_name";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("h", FinVoucherModelHead.class);
        
        return this.basicDao.selectData(sql, entity);
    }
    
    @Override
    public List<String> getFinVoucherModelHeadForBusinessType() {
        String sql = "select distinct h.business_type from fin_voucher_model_head h";
        
        List<String> list = this.basicDao.selectDataSqlCount(sql);
        
        return list;
    }
    
    @Override
    public FinVoucherModelHead getFinVoucherModelHeadByBusinessType(String businessType) {
        String sql = "select h.* from fin_voucher_model_head h where business_type = :businesstype order by h.voucher_head_id desc";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("businesstype", businessType);
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("h", FinVoucherModelHead.class);
        
        List<FinVoucherModelHead> list = this.basicDao.selectData(sql, entity, args);
        if(list.size()>0) {
            return list.get(0);
        }
        
        return null;
    }
    
}
