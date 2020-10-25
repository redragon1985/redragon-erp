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
import com.erp.masterdata.customer.dao.model.MdCustomerLicense;
import com.erp.masterdata.vendor.dao.MdVendorLicenseDao;
import com.erp.masterdata.vendor.dao.model.MdVendorLicense;
import com.erp.masterdata.vendor.dao.model.MdVendorLicenseCO;

@Repository
public class MdVendorLicenseDaoImpl implements MdVendorLicenseDao{ 

    //注入DaoSupport工具类
    @Autowired
    private DaoSupport daoSupport;
    
    @Override
    public void insertDataObject(MdVendorLicense obj) {
        this.daoSupport.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(MdVendorLicense obj) {
        this.daoSupport.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(MdVendorLicense obj) {
        this.daoSupport.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(MdVendorLicense obj) {
        this.daoSupport.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<MdVendorLicense> getDataObjects() {
        return this.daoSupport.getDataAllObject(MdVendorLicense.class);
    }

    @Override
    public MdVendorLicense getDataObject(int id) {
        return (MdVendorLicense)this.daoSupport.getDataObject(MdVendorLicense.class, id);
    }
    
    @Override
    public MdVendorLicense getDataObject(String code) {
        return null;
    }
    
    @Override
    public List<MdVendorLicense> getDataObjects(MdVendorLicenseCO paramObj) {
        String sql = "select c.* from md_vendor_license c where vendor_code=:code";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", paramObj.getVendorCode());
        
        sql = sql + " order by c.license_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("c", MdVendorLicense.class);
        
        return this.daoSupport.selectDataSql(sql, entity, args);
    }
    
    @Override
    public List<MdVendorLicense> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<MdVendorLicense> getDataObjects(Pages pages, MdVendorLicenseCO paramObj) {
        return null;
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, MdVendorLicenseCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.OWN)
    public List<MdVendorLicense> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, MdVendorLicenseCO paramObj) {
        return null;
    }
    
    @Override
    public int getLicenseCountByVendorCode(String vendorCode) {
        String sql = "select count(1) from md_vendor_license l where l.vendor_code = :vendorCode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("vendorCode", vendorCode);
        
        List list = this.daoSupport.selectDataSqlCount(sql, args);
        if(list.size()>0) {
            return Integer.parseInt(String.valueOf(list.get(0)));
        }
        
        return 0;
    }
    
    @Override
    public MdVendorLicense getLicenseListByVendorCode(MdVendorLicense paramObj) {
        String sql = "select l.* from md_vendor_license l where vendor_code=:code";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", paramObj.getVendorCode());
        
        sql = sql + " order by l.license_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("l", MdVendorLicense.class);
        
        List list = this.daoSupport.selectDataSql(sql, entity, args);
        if(list.size()>0) {
            return (MdVendorLicense)list.get(0);
        }
        
        return null;
    }
    
}
