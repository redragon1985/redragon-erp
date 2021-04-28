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
import com.erp.permission.dao.SysRoleAuthRDao;
import com.erp.permission.dao.model.SysRoleAuthR;
import com.erp.permission.dao.model.SysRoleAuthRCO;

@Repository
public class SysRoleAuthRDaoImpl implements SysRoleAuthRDao{ 

    //注入basicDao工具类
    @Autowired
    private BasicDao basicDao;
    
    @Override
    public void insertDataObject(SysRoleAuthR obj) {
        this.basicDao.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(SysRoleAuthR obj) {
        this.basicDao.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(SysRoleAuthR obj) {
        this.basicDao.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(SysRoleAuthR obj) {
        this.basicDao.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<SysRoleAuthR> getDataObjects() {
        return this.basicDao.getDataAllObject(SysRoleAuthR.class);
    }

    @Override
    public SysRoleAuthR getDataObject(int id) {
        return (SysRoleAuthR)this.basicDao.getDataObject(SysRoleAuthR.class, id);
    }
    
    @Override
    public SysRoleAuthR getDataObject(String code) {
        return null;
    }
    
    @Override
    public List<SysRoleAuthR> getDataObjects(SysRoleAuthRCO paramObj) {
        return null;
    }
    
    @Override
    public List<SysRoleAuthR> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<SysRoleAuthR> getDataObjects(Pages pages, SysRoleAuthRCO paramObj) {
        return null;
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, SysRoleAuthRCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.OWN)
    public List<SysRoleAuthR> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, SysRoleAuthRCO paramObj) {
        return null;
    }
    
    @Override
    public void deleteSysRoleAuthRByRoleCode(String roleCode) {
        String sql = "delete from sys_role_auth_r where role_code=:roleCode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("roleCode", roleCode);
        
        this.basicDao.executeSQLTransaction(sql, args);
    }
    
}
