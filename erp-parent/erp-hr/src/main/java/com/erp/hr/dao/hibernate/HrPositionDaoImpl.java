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
import com.erp.hr.dao.HrPositionDao;
import com.erp.hr.dao.model.HrPosition;
import com.erp.hr.dao.model.HrPositionCO;

@Repository
public class HrPositionDaoImpl implements HrPositionDao{ 

    //注入DaoSupport工具类
    @Autowired
    private DaoSupport daoSupport;
    
    @Override
    public void insertDataObject(HrPosition obj) {
        this.daoSupport.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(HrPosition obj) {
        this.daoSupport.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(HrPosition obj) {
        this.daoSupport.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(HrPosition obj) {
        this.daoSupport.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<HrPosition> getDataObjects() {
        return this.daoSupport.getDataAllObject(HrPosition.class);
    }

    @Override
    public HrPosition getDataObject(int id) {
        return (HrPosition)this.daoSupport.getDataObject(HrPosition.class, id);
    }
    
    @Override
    public HrPosition getDataObject(String code) {
        return null;
    }
    
    @Override
    public List<HrPosition> getDataObjects(HrPositionCO paramObj) {
        return null;
    }
    
    @Override
    public List<HrPosition> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<HrPosition> getDataObjects(Pages pages, HrPositionCO paramObj) {
        String sql = "select p.* from hr_position p where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.getSQLCondition(paramObj, "positionCode", "and p.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "positionName", "and p.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "positionType", "and p.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "status", "and p.", args);
        sql = sql + " order by p.position_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("p", HrPosition.class);
        
        return this.daoSupport.getDataSqlByPage(sql, entity, args, pages);
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, HrPositionCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.OWN)
    public List<HrPosition> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, HrPositionCO paramObj) {
        return null;
    }
    
    @Override
    public boolean isExistRelateDataForHrPosition(String positionCode) {
        String sql = "select count(*) from hr_staff_department_r sd where sd.position_code = :positionCode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("positionCode", positionCode);
        
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
