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
import java.util.LinkedHashMap;
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
import com.erp.hr.dao.HrStaffDepartmentRDao;
import com.erp.hr.dao.model.HrDepartment;
import com.erp.hr.dao.model.HrDepartmentCO;
import com.erp.hr.dao.model.HrPosition;
import com.erp.hr.dao.model.HrStaff;
import com.erp.hr.dao.model.HrStaffDepartmentR;
import com.erp.hr.dao.model.HrStaffDepartmentRCO;

@Repository
public class HrStaffDepartmentRDaoImpl implements HrStaffDepartmentRDao{ 

    //注入basicDao工具类
    @Autowired
    private BasicDao basicDao;
    
    @Override
    public void insertDataObject(HrStaffDepartmentR obj) {
        this.basicDao.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(HrStaffDepartmentR obj) {
        this.basicDao.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(HrStaffDepartmentR obj) {
        this.basicDao.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(HrStaffDepartmentR obj) {
        this.basicDao.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<HrStaffDepartmentR> getDataObjects() {
        return this.basicDao.getDataAllObject(HrStaffDepartmentR.class);
    }

    @Override
    public HrStaffDepartmentR getDataObject(int id) {
        return (HrStaffDepartmentR)this.basicDao.getDataObject(HrStaffDepartmentR.class, id);
    }
    
    @Override
    public HrStaffDepartmentR getDataObject(String code) {
        return null;
    }
    
    @Override
    public List<HrStaffDepartmentR> getDataObjects(HrStaffDepartmentRCO paramObj) {
        return null;
    }
    
    @Override
    public List<HrStaffDepartmentR> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<HrStaffDepartmentR> getDataObjects(Pages pages, HrStaffDepartmentRCO paramObj) {
        return null;
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, HrStaffDepartmentRCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.OWN)
    public List<HrStaffDepartmentR> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, HrStaffDepartmentRCO paramObj) {
        return null;
    }
    
    @Override
    public List getHrStaffDepartmentRList(Pages pages, HrStaffDepartmentRCO paramObj) {
        String sql = "select r.*,s.*,p.*,d.* from hr_staff_department_r r,hr_staff s,hr_position p,hr_department d "
                + "where r.staff_code = s.staff_code and r.position_code = p.position_code and r.department_code = d.department_code";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.settleParam(paramObj, "departmentId", "and d.", args);
        sql = sql + " order by s.staff_code";
        
        Map<String, Class<?>> entity = new LinkedHashMap<String, Class<?>>();
        entity.put("r", HrStaffDepartmentR.class);
        entity.put("s", HrStaff.class);
        entity.put("p", HrPosition.class);
        entity.put("d", HrDepartment.class);
        
        return this.basicDao.getDataSql(sql, entity, args, pages);
    }
    
}
