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
import com.erp.permission.dao.SysRoleDao;
import com.erp.permission.dao.model.SysRole;
import com.erp.permission.dao.model.SysRoleCO;

@Repository
public class SysRoleDaoImpl implements SysRoleDao{ 

    //注入basicDao工具类
    @Autowired
    private BasicDao basicDao;
    
    @Override
    public void insertDataObject(SysRole obj) {
        this.basicDao.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(SysRole obj) {
        this.basicDao.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(SysRole obj) {
        this.basicDao.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(SysRole obj) {
        this.basicDao.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<SysRole> getDataObjects() {
        return this.basicDao.getDataAllObject(SysRole.class);
    }

    @Override
    public SysRole getDataObject(int id) {
        return (SysRole)this.basicDao.getDataObject(SysRole.class, id);
    }
    
    @Override
    public SysRole getDataObject(String code) {
        return null;
    }
    
    @Override
    public List<SysRole> getDataObjects(SysRoleCO paramObj) {
        return null;
    }
    
    @Override
    public List<SysRole> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<SysRole> getDataObjects(Pages pages, SysRoleCO paramObj) {
        String sql = "select r.* from sys_role r where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.settleParam(paramObj, "roleCode", "and r.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "roleName", "and r.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "status", "and r.", args);
        sql = sql + " order by r.role_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("r", SysRole.class);
        
        return this.basicDao.getDataSql(sql, entity, args, pages);
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, SysRoleCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.OWN)
    public List<SysRole> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, SysRoleCO paramObj) {
        return null;
    }
    
    @Override
    public List<SysRole> getSysRoleListByStatus(String status) {
        String sql = "select r.* from sys_role r where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        if(status!=null) {
            sql = sql + " and r.status = :status";
            args.put("status", status);
        }

        sql = sql + " order by r.role_code";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("r", SysRole.class);
        
        return this.basicDao.selectData(sql, entity, args);
    }
    
    @Override
    public List<SysRole> getRelateSysRoleListByUsername(String username) {
        String sql = "select r.* from sys_user_role_r ur,sys_role r where ur.role_code=r.role_code and ur.username=:username order by r.role_code";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("username", username);
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("r", SysRole.class);
        
        return this.basicDao.selectData(sql, entity, args);
    }
    
    @Override
    public boolean isExistRelateDataForSysRole(String roleCode) {
        String sql = "select (select count(*) from sys_role_auth_r ra where ra.role_code = :roleCode)+(select count(*) from sys_user_role_r ur where ur.role_code = :roleCode) as num from dual";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("roleCode", roleCode);
        
        List list = this.basicDao.selectDataSqlCount(sql, args);
        if(list.size()>0) {
            int num = this.basicDao.convertSQLCount(list.get(0));
            if(num==0) {
                return false;
            }
        }
        
        return true;
    }
    
}
