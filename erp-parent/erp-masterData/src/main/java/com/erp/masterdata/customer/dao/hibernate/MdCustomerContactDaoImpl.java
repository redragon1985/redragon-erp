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
import com.erp.masterdata.customer.dao.MdCustomerContactDao;
import com.erp.masterdata.customer.dao.model.MdCustomer;
import com.erp.masterdata.customer.dao.model.MdCustomerBank;
import com.erp.masterdata.customer.dao.model.MdCustomerContact;
import com.erp.masterdata.customer.dao.model.MdCustomerContactCO;

@Repository
public class MdCustomerContactDaoImpl implements MdCustomerContactDao{ 

    //注入basicDao工具类
    @Autowired
    private BasicDao basicDao;
    
    @Override
    public void insertDataObject(MdCustomerContact obj) {
        this.basicDao.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(MdCustomerContact obj) {
        this.basicDao.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(MdCustomerContact obj) {
        this.basicDao.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(MdCustomerContact obj) {
        this.basicDao.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<MdCustomerContact> getDataObjects() {
        return this.basicDao.getDataAllObject(MdCustomerContact.class);
    }

    @Override
    public MdCustomerContact getDataObject(int id) {
        return (MdCustomerContact)this.basicDao.getDataObject(MdCustomerContact.class, id);
    }
    
    @Override
    public MdCustomerContact getDataObject(String code) {
        return null;
    }
    
    @Override
    public List<MdCustomerContact> getDataObjects(MdCustomerContactCO paramObj) {
        String sql = "select c.* from md_customer_contact c where customer_code=:code";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", paramObj.getCustomerCode());
        
        sql = sql + " order by c.contact_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("c", MdCustomerContact.class);
        
        return this.basicDao.selectData(sql, entity, args);
    }
    
    @Override
    public List<MdCustomerContact> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<MdCustomerContact> getDataObjects(Pages pages, MdCustomerContactCO paramObj) {
        return null;
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, MdCustomerContactCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.OWN)
    public List<MdCustomerContact> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, MdCustomerContactCO paramObj) {
        return null;
    }
    
    @Override
    public int getContactCountByCustomerCode(String customerCode) {
        String sql = "select count(1) from md_customer_contact c where c.customer_code = :customerCode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("customerCode", customerCode);
        
        List list = this.basicDao.selectDataSqlCount(sql, args);
        if(list.size()>0) {
            return Integer.parseInt(String.valueOf(list.get(0)));
        }
        
        return 0;
    }
    
    @Override
    public List<MdCustomerContact> getContactListByCustomerCode(Pages pages, MdCustomerContactCO paramObj) {
        String sql = "select c.* from md_customer_contact c where customer_code=:code";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", paramObj.getCustomerCode());
        
        sql = sql + " order by c.contact_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("c", MdCustomerContact.class);
        
        return this.basicDao.getDataSql(sql, entity, args, pages);
    }
    
}
