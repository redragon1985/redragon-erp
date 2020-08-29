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
package com.erp.masterdata.customer.dao.hibernate;

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
import com.erp.masterdata.customer.dao.MdCustomerDao;
import com.erp.masterdata.customer.dao.model.MdCustomer;
import com.erp.masterdata.customer.dao.model.MdCustomerCO;

@Repository
public class MdCustomerDaoImpl implements MdCustomerDao{ 

    //注入DaoSupport工具类
    @Autowired
    private DaoSupport daoSupport;
    
    @Override
    public void insertDataObject(MdCustomer obj) {
        this.daoSupport.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(MdCustomer obj) {
        this.daoSupport.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(MdCustomer obj) {
        this.daoSupport.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(MdCustomer obj) {
        this.daoSupport.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<MdCustomer> getDataObjects() {
        return this.daoSupport.getDataAllObject(MdCustomer.class);
    }

    @Override
    public MdCustomer getDataObject(int id) {
        return (MdCustomer)this.daoSupport.getDataObject(MdCustomer.class, id);
    }
    
    @Override
    public MdCustomer getDataObject(String code) {
        String sql = "select c.* from md_customer c where c.customer_code = :code";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", code);
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("c", MdCustomer.class);
        
        List list = this.daoSupport.selectDataSql(sql, entity, args);
        if(list.size()>0) {
            return (MdCustomer)list.get(0);
        }
        
        return null;
    }
    
    @Override
    public List<MdCustomer> getDataObjects(MdCustomerCO paramObj) {
        return null;
    }
    
    @Override
    public List<MdCustomer> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<MdCustomer> getDataObjects(Pages pages, MdCustomerCO paramObj) {
        String sql = "select c.* from md_customer c where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.getSQLCondition(paramObj, "customerCode", "and c.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "customerName", "and c.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "customerType", "and c.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "customerCity", "and c.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "status", "and c.", args);
        sql = sql + " order by c.customer_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("c", MdCustomer.class);
        
        return this.daoSupport.getDataSqlByPage(sql, entity, args, pages);
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, MdCustomerCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.OWN)
    public List<MdCustomer> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, MdCustomerCO paramObj) {
        return null;
    }
    
    @Override
    public List<MdCustomer> getMdCustomerListForOwn() {
        String sql = "select c.* from md_customer c where c.own_flag = 'Y' order by c.customer_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("c", MdCustomer.class);
        
        return this.daoSupport.selectDataSql(sql, entity);
    }
    
    @Override
    public void updateApproveStatus(String code, String approveStatus) {
        String sql = "update md_customer set approve_status = :approveStatus where customer_code = :code";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", code);
        args.put("approveStatus", approveStatus);
        
        this.daoSupport.executeSQLTransaction(sql, args);
    }
    
    @Override
    public int getCustomerNum() {
        String sql = "select count(*) from md_customer";
        
        List list = this.daoSupport.selectDataSqlCount(sql);
        if(list.size()>0) {
            return this.daoSupport.convertSQLCount(list.get(0));
        }
        
        return 0;
    }
    
}
