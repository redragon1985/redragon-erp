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
package com.erp.hr.dao.hibernate;

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
import com.erp.hr.dao.HrDepartmentDao;
import com.erp.hr.dao.model.HrDepartment;
import com.erp.hr.dao.model.HrDepartmentCO;

@Repository
public class HrDepartmentDaoImpl implements HrDepartmentDao{ 

    //注入DaoSupport工具类
    @Autowired
    private DaoSupport daoSupport;
    
    @Override
    public void insertDataObject(HrDepartment obj) {
        this.daoSupport.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(HrDepartment obj) {
        this.daoSupport.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(HrDepartment obj) {
        this.daoSupport.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(HrDepartment obj) {
        this.daoSupport.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<HrDepartment> getDataObjects() {
        return this.daoSupport.getDataAllObject(HrDepartment.class);
    }

    @Override
    public HrDepartment getDataObject(int id) {
        return (HrDepartment)this.daoSupport.getDataObject(HrDepartment.class, id);
    }
    
    @Override
    public HrDepartment getDataObject(String code) {
        String sql = "select d.* from hr_department d where d.department_code = :code";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", code);
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("d", HrDepartment.class);
        
        List<HrDepartment> list = this.daoSupport.selectDataSql(sql, entity, args);
        if(list.size()>0) {
            return list.get(0);
        }
        
        return null;
    }
    
    @Override
    public List<HrDepartment> getDataObjects(HrDepartmentCO paramObj) {
        String sql = "select d.* from hr_department d where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
//        sql = sql + DaoUtil.getSQLCondition(paramObj, "", "and d.", args);
        
        sql = sql + " order by d.segment_code";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("d", HrDepartment.class);
        
        return this.daoSupport.selectDataSql(sql, entity, args);
    }
    
    @Override
    public List<HrDepartment> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<HrDepartment> getDataObjects(Pages pages, HrDepartmentCO paramObj) {
        return null;
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, HrDepartmentCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.OWN)
    public List<HrDepartment> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, HrDepartmentCO paramObj) {
        return null;
    }
    
    @Override
    public int getHrChildDepartmentNum(Integer departmentId) {
        String sql = "select count(*) from hr_department where parent_department_code = (select d.department_code from hr_department d where d.department_id = :departmentId)";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("departmentId", departmentId);
        
        List list = this.daoSupport.selectDataSqlCount(sql, args);
        if(list.size()>0) {
            return this.daoSupport.convertSQLCount(list.get(0));
        }
        
        return 0;
    }
    
    @Override
    public List<HrDepartment> getHrDepartmentChildList(String departmentCode) {
        String sql = "select d.* from hr_department d where d.segment_code like (select concat(segment_code,'%') from hr_department where department_code = :departmentCode) order by department_id";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("departmentCode", departmentCode);
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("d", HrDepartment.class);
        
        return this.daoSupport.selectDataSql(sql, entity, args);
    }
    
}
