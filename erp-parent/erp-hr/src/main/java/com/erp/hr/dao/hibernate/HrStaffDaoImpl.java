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
import com.erp.hr.dao.HrStaffDao;
import com.erp.hr.dao.model.HrStaff;
import com.erp.hr.dao.model.HrStaffCO;
import com.erp.hr.dao.model.HrStaffInfoRO;

@Repository
public class HrStaffDaoImpl implements HrStaffDao{ 

    //注入DaoSupport工具类
    @Autowired
    private DaoSupport daoSupport;
    
    @Override
    public void insertDataObject(HrStaff obj) {
        this.daoSupport.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(HrStaff obj) {
        this.daoSupport.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(HrStaff obj) {
        this.daoSupport.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(HrStaff obj) {
        this.daoSupport.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<HrStaff> getDataObjects() {
        return this.daoSupport.getDataAllObject(HrStaff.class);
    }

    @Override
    public HrStaff getDataObject(int id) {
        return (HrStaff)this.daoSupport.getDataObject(HrStaff.class, id);
    }
    
    @Override
    public HrStaff getDataObject(String code) {
        String sql = "select s.* from hr_staff s where s.staff_code = :code";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", code);
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("s", HrStaff.class);
        
        List<HrStaff> list = this.daoSupport.selectDataSql(sql, entity, args);
        if(list.size()>0) {
            return list.get(0);
        }
        
        return null;
    }
    
    @Override
    public List<HrStaff> getDataObjects(HrStaffCO paramObj) {
        String sql = "select s.* from hr_staff s where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.getSQLCondition(paramObj, "staffStatus", "and s.", args);
        sql = sql + " order by s.staff_code desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("s", HrStaff.class);
        
        return this.daoSupport.selectDataSql(sql, entity, args);
    }
    
    @Override
    public List<HrStaff> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<HrStaff> getDataObjects(Pages pages, HrStaffCO paramObj) {
        String sql = "select s.* from hr_staff s where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.getSQLCondition(paramObj, "staffCode", "and s.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "staffName", "and s.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "staffSex", "and s.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "staffStatus", "and s.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "username", "and s.", args);
        sql = sql + " order by s.staff_code desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("s", HrStaff.class);
        
        return this.daoSupport.getDataSqlByPage(sql, entity, args, pages);
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, HrStaffCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.OWN)
    public List<HrStaff> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, HrStaffCO paramObj) {
        return null;
    }
    
    @Override
    public HrStaffInfoRO getHrStaffInfoRO(String username) {
        String sql = "select s.staff_code as staffCode,s.staff_name as staffName,d.department_code as departmentCode,d.department_name as departmentName,d.segment_code as deaprtmentSegmentCode,d.segment_desc as departmentSegmentDesc,p.position_code as positionCode,p.position_name as positionName " + 
                     "from hr_staff s,hr_staff_department_r r,hr_department d,hr_position p " + 
                     "where s.staff_code=r.staff_code and r.department_code=d.department_code and r.position_code=p.position_code and r.status = 'Y' and s.username=:username and s.staff_status='WORK'";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("username", username);
        
        List<HrStaffInfoRO> list = this.daoSupport.selectDataSql(sql, HrStaffInfoRO.class, args);
        if(list.size()>0) {
            return list.get(0);
        }
        
        return null;
    }
    
    @Override
    public boolean isExistRelateDataForHrStaff(String staffCode) {
        String sql = "select count(*) from hr_staff_department_r sd where sd.staff_code = :staffCode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("staffCode", staffCode);
        
        List list = this.daoSupport.selectDataSqlCount(sql, args);
        if(list.size()>0) {
            int num = this.daoSupport.convertSQLCount(list.get(0));
            if(num==0) {
                return false;
            }
        }
        
        return true;
    }
    
    @Override
    public String getUsernameByStaffCode(String staffCode) {
        String sql = "select s.username from hr_staff s where s.staff_code = :staffCode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("staffCode", staffCode);
        
        List<String> list = this.daoSupport.selectDataSqlCount(sql, args);
        if(list.size()>0) {
            return list.get(0);
        }
        
        return null;
    }
    
}
