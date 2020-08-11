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
package com.erp.finance.voucher.dao.hibernate;

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
import com.erp.finance.voucher.dao.FinVoucherModelHeadDao;
import com.erp.finance.voucher.dao.model.FinVoucherHead;
import com.erp.finance.voucher.dao.model.FinVoucherModelHead;
import com.erp.finance.voucher.dao.model.FinVoucherModelHeadCO;

@Repository
public class FinVoucherModelHeadDaoImpl implements FinVoucherModelHeadDao{ 

    //注入DaoSupport工具类
    @Autowired
    private DaoSupport daoSupport;
    
    @Override
    public void insertDataObject(FinVoucherModelHead obj) {
        this.daoSupport.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(FinVoucherModelHead obj) {
        this.daoSupport.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(FinVoucherModelHead obj) {
        this.daoSupport.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(FinVoucherModelHead obj) {
        this.daoSupport.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<FinVoucherModelHead> getDataObjects() {
        return this.daoSupport.getDataAllObject(FinVoucherModelHead.class);
    }

    @Override
    public FinVoucherModelHead getDataObject(int id) {
        return (FinVoucherModelHead)this.daoSupport.getDataObject(FinVoucherModelHead.class, id);
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
        sql = sql + DaoUtil.getSQLCondition(paramObj, "voucherType", "and v.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "modelName", "and v.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "businessType", "and v.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "status", "and v.", args);
        sql = sql + " order by v.voucher_head_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("v", FinVoucherModelHead.class);
        
        return this.daoSupport.getDataSqlByPage(sql, entity, args, pages);
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
        
        return this.daoSupport.selectDataSql(sql, entity);
    }
    
    @Override
    public List<String> getFinVoucherModelHeadForBusinessType() {
        String sql = "select distinct h.business_type from fin_voucher_model_head h";
        
        List<String> list = this.daoSupport.selectDataSqlCount(sql);
        
        return list;
    }
    
    @Override
    public FinVoucherModelHead getFinVoucherModelHeadByBusinessType(String businessType) {
        String sql = "select h.* from fin_voucher_model_head h where business_type = :businesstype order by h.voucher_head_id desc";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("businesstype", businessType);
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("h", FinVoucherModelHead.class);
        
        List<FinVoucherModelHead> list = this.daoSupport.selectDataSql(sql, entity, args);
        if(list.size()>0) {
            return list.get(0);
        }
        
        return null;
    }
    
}
