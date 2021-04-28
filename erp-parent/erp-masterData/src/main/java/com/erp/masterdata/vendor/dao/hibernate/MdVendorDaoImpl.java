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
package com.erp.masterdata.vendor.dao.hibernate;

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
import com.erp.masterdata.customer.dao.model.MdCustomer;
import com.erp.masterdata.vendor.dao.MdVendorDao;
import com.erp.masterdata.vendor.dao.model.MdVendor;
import com.erp.masterdata.vendor.dao.model.MdVendorCO;

@Repository
public class MdVendorDaoImpl implements MdVendorDao{ 

    //注入basicDao工具类
    @Autowired
    private BasicDao basicDao;
    
    @Override
    public void insertDataObject(MdVendor obj) {
        this.basicDao.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(MdVendor obj) {
        this.basicDao.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(MdVendor obj) {
        this.basicDao.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(MdVendor obj) {
        this.basicDao.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<MdVendor> getDataObjects() {
        return this.basicDao.getDataAllObject(MdVendor.class);
    }

    @Override
    public MdVendor getDataObject(int id) {
        return (MdVendor)this.basicDao.getDataObject(MdVendor.class, id);
    }
    
    @Override
    public MdVendor getDataObject(String code) {
        String sql = "select c.* from md_vendor c where c.vendor_code = :code";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", code);
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("c", MdVendor.class);
        
        List list = this.basicDao.selectData(sql, entity, args);
        
        if(list.size()>0) {
            return (MdVendor)list.get(0);
        }
        
        return null;
    }
    
    @Override
    public List<MdVendor> getDataObjects(MdVendorCO paramObj) {
        String sql = "select c.* from md_vendor c where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.settleParam(paramObj, "approveStatus", "and c.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "status", "and c.", args);
        sql = sql + " order by c.vendor_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("c", MdVendor.class);
        
        return this.basicDao.selectData(sql, entity, args);
    }
    
    @Override
    public List<MdVendor> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<MdVendor> getDataObjects(Pages pages, MdVendorCO paramObj) {
        String sql = "select c.* from md_vendor c where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.settleParam(paramObj, "vendorCode", "and c.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "vendorName", "and c.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "vendorType", "and c.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "vendorCity", "and c.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "status", "and c.", args);
        sql = sql + " order by c.vendor_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("c", MdVendor.class);
        
        return this.basicDao.getDataSql(sql, entity, args, pages);
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, MdVendorCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.OWN)
    public List<MdVendor> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, MdVendorCO paramObj) {
        return null;
    }
    
    @Override
    public List<MdVendor> getMdVendorListForOwn() {
        String sql = "select c.* from md_vendor c where c.own_flag = 'Y' and c.status = 'Y' and c.approve_status = 'APPROVE' order by c.vendor_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("c", MdVendor.class);
        
        return this.basicDao.selectData(sql, entity);
    }
    
    @Override
    public void updateApproveStatus(String code, String approveStatus) {
        String sql = "update md_vendor set approve_status = :approveStatus where vendor_code = :code";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", code);
        args.put("approveStatus", approveStatus);
        
        this.basicDao.executeSQLTransaction(sql, args);
    }
    
    @Override
    public int getVendorNum() {
        String sql = "select count(*) from md_vendor";
        
        List list = this.basicDao.selectDataSqlCount(sql);
        if(list.size()>0) {
            return this.basicDao.convertSQLCount(list.get(0));
        }
        
        return 0;
    }
    
}
