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

import redragon.util.reflect.ReflectUtil;

import com.erp.permission.dao.SysUserDao;
import com.erp.permission.dao.model.SysUser;
import com.erp.permission.dao.model.SysUserCO;
import com.erp.permission.dao.model.SysUserRO;

@Repository
public class SysUserDaoImpl implements SysUserDao{ 

    //注入DaoSupport工具类
    @Autowired
    private DaoSupport daoSupport;
    
    @Override
    public void insertDataObject(SysUser obj) {
        this.daoSupport.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(SysUser obj) {
        this.daoSupport.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(SysUser obj) {
        this.daoSupport.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(SysUser obj) {
        this.daoSupport.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<SysUser> getDataObjects() {
        return this.daoSupport.getDataAllObject(SysUser.class);
    }

    @Override
    public SysUser getDataObject(int id) {
        return (SysUser)this.daoSupport.getDataObject(SysUser.class, id);
    }
    
    @Override
    public SysUser getDataObject(String code) {
        return null;
    }
    
    @Override
    public List<SysUser> getDataObjects(SysUserCO paramObj) {
        return null;
    }
    
    @Override
    public List<SysUser> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<SysUser> getDataObjects(Pages pages, SysUserCO paramObj) {
        String sql = "select u.* from sys_user u where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.getSQLCondition(paramObj, "username", "and u.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "status", "and u.", args);
        sql = sql + DaoUtil.getSQLConditionForDateTime(paramObj, "createdDate", "createdDateStart", "createdDateEnd", "and u.", args);
        sql = sql + " order by u.user_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("u", SysUser.class);
        
        return this.daoSupport.getDataSqlByPage(sql, entity, args, pages);
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, SysUserCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.OWN)
    public List<SysUser> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, SysUserCO paramObj) {
        return null;
    }
    
    @Override
    public List<SysUserRO> getSysUserROListForRelate() {
        String sql = "select u.*,(select count(*) from sys_user_role_r ur where ur.username=u.username) as user_role_num from sys_user u where u.status='Y' order by u.user_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("u", SysUserRO.class);
        
        return this.daoSupport.selectDataSql(sql, entity);
    }
    
    @Override
    public boolean isExistRelateDataForSysUser(String username) {
        String sql = "select (select count(*) from hr_staff s where s.username = :username)+(select count(*) from sys_user_role_r ur where ur.username = :username) as num from dual";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("username", username);
        
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
