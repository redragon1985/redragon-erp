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
package com.erp.hr.dao.hibernate;

import java.util.HashMap;
import java.util.LinkedHashMap;
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
import com.erp.hr.dao.HrStaffDepartmentRDao;
import com.erp.hr.dao.model.HrDepartment;
import com.erp.hr.dao.model.HrDepartmentCO;
import com.erp.hr.dao.model.HrPosition;
import com.erp.hr.dao.model.HrStaff;
import com.erp.hr.dao.model.HrStaffDepartmentR;
import com.erp.hr.dao.model.HrStaffDepartmentRCO;

@Repository
public class HrStaffDepartmentRDaoImpl implements HrStaffDepartmentRDao{ 

    //注入DaoSupport工具类
    @Autowired
    private DaoSupport daoSupport;
    
    @Override
    public void insertDataObject(HrStaffDepartmentR obj) {
        this.daoSupport.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(HrStaffDepartmentR obj) {
        this.daoSupport.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(HrStaffDepartmentR obj) {
        this.daoSupport.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(HrStaffDepartmentR obj) {
        this.daoSupport.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<HrStaffDepartmentR> getDataObjects() {
        return this.daoSupport.getDataAllObject(HrStaffDepartmentR.class);
    }

    @Override
    public HrStaffDepartmentR getDataObject(int id) {
        return (HrStaffDepartmentR)this.daoSupport.getDataObject(HrStaffDepartmentR.class, id);
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
        sql = sql + DaoUtil.getSQLCondition(paramObj, "departmentId", "and d.", args);
        sql = sql + " order by s.staff_code";
        
        Map<String, Class<?>> entity = new LinkedHashMap<String, Class<?>>();
        entity.put("r", HrStaffDepartmentR.class);
        entity.put("s", HrStaff.class);
        entity.put("p", HrPosition.class);
        entity.put("d", HrDepartment.class);
        
        return this.daoSupport.getDataSqlByPage(sql, entity, args, pages);
    }
    
}
