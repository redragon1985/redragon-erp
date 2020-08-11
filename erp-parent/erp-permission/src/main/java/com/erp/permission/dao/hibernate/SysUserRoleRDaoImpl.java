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
import com.erp.permission.dao.SysUserRoleRDao;
import com.erp.permission.dao.model.SysUserRoleR;
import com.erp.permission.dao.model.SysUserRoleRCO;

@Repository
public class SysUserRoleRDaoImpl implements SysUserRoleRDao{ 

    //注入DaoSupport工具类
    @Autowired
    private DaoSupport daoSupport;
    
    @Override
    public void insertDataObject(SysUserRoleR obj) {
        this.daoSupport.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(SysUserRoleR obj) {
        this.daoSupport.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(SysUserRoleR obj) {
        this.daoSupport.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(SysUserRoleR obj) {
        this.daoSupport.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<SysUserRoleR> getDataObjects() {
        return this.daoSupport.getDataAllObject(SysUserRoleR.class);
    }

    @Override
    public SysUserRoleR getDataObject(int id) {
        return (SysUserRoleR)this.daoSupport.getDataObject(SysUserRoleR.class, id);
    }
    
    @Override
    public SysUserRoleR getDataObject(String code) {
        return null;
    }
    
    @Override
    public List<SysUserRoleR> getDataObjects(SysUserRoleRCO paramObj) {
        return null;
    }
    
    @Override
    public List<SysUserRoleR> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<SysUserRoleR> getDataObjects(Pages pages, SysUserRoleRCO paramObj) {
        return null;
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, SysUserRoleRCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.OWN)
    public List<SysUserRoleR> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, SysUserRoleRCO paramObj) {
        return null;
    }
    
    @Override
    public void deleteSysUserRoleRByUsername(String username) {
        String sql = "delete from sys_user_role_r where username=:username";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("username", username);
        
        this.daoSupport.executeSQLTransaction(sql, args);
    }
    
}
