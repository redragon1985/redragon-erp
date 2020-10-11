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
package com.erp.permission.dao.hibernate;

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
import com.erp.permission.dao.SysAuthDao;
import com.erp.permission.dao.model.SysAuth;
import com.erp.permission.dao.model.SysAuthCO;

@Repository
public class SysAuthDaoImpl implements SysAuthDao{ 

    //注入DaoSupport工具类
    @Autowired
    private DaoSupport daoSupport;
    
    @Override
    public void insertDataObject(SysAuth obj) {
        this.daoSupport.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(SysAuth obj) {
        this.daoSupport.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(SysAuth obj) {
        this.daoSupport.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(SysAuth obj) {
        this.daoSupport.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<SysAuth> getDataObjects() {
        return this.daoSupport.getDataAllObject(SysAuth.class);
    }

    @Override
    public SysAuth getDataObject(int id) {
        return (SysAuth)this.daoSupport.getDataObject(SysAuth.class, id);
    }
    
    @Override
    public SysAuth getDataObject(String code) {
        return null;
    }
    
    @Override
    public List<SysAuth> getDataObjects(SysAuthCO paramObj) {
        return null;
    }
    
    @Override
    public List<SysAuth> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<SysAuth> getDataObjects(Pages pages, SysAuthCO paramObj) {
        String sql = "select a.* from sys_auth a where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.getSQLCondition(paramObj, "authCode", "and a.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "authName", "and a.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "authType", "and a.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "status", "and a.", args);
        sql = sql + " order by a.auth_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("a", SysAuth.class);
        
        return this.daoSupport.getDataSqlByPage(sql, entity, args, pages);
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, SysAuthCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.OWN)
    public List<SysAuth> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, SysAuthCO paramObj) {
        return null;
    }
    
    @Override
    public List<SysAuth> getSysAuthListByStatus(String status) {
        String sql = "select a.* from sys_auth a where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        if(status!=null) {
            sql = sql + " and a.status = :status";
            args.put("status", status);
        }

        sql = sql + " order by a.auth_code";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("a", SysAuth.class);
        
        return this.daoSupport.selectDataSql(sql, entity, args);
    }
    
    @Override
    public List<SysAuth> getRelateSysAuthListByRoleCode(String roleCode) {
        String sql = "select a.* from sys_role_auth_r ra,sys_auth a where ra.auth_code=a.auth_code and ra.role_code=:roleCode order by a.auth_code";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("roleCode", roleCode);
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("a", SysAuth.class);
        
        return this.daoSupport.selectDataSql(sql, entity, args);
    }
    
    @Override
    public boolean isExistRelateDataForSysAuth(String authCode) {
        String sql = "select count(*) from sys_role_auth_r ra where ra.auth_code = :authCode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("authCode", authCode);
        
        List list = this.daoSupport.selectDataSqlCount(sql, args);
        if(list.size()>0) {
            int num = this.daoSupport.convertSQLCount(list.get(0));
            if(num==0) {
                return false;
            }
        }
        
        return true;
    }
    
}
