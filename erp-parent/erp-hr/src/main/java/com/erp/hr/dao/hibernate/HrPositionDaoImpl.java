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
import com.erp.hr.dao.HrPositionDao;
import com.erp.hr.dao.model.HrPosition;
import com.erp.hr.dao.model.HrPositionCO;

@Repository
public class HrPositionDaoImpl implements HrPositionDao{ 

    //注入basicDao工具类
    @Autowired
    private BasicDao basicDao;
    
    @Override
    public void insertDataObject(HrPosition obj) {
        this.basicDao.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(HrPosition obj) {
        this.basicDao.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(HrPosition obj) {
        this.basicDao.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(HrPosition obj) {
        this.basicDao.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<HrPosition> getDataObjects() {
        return this.basicDao.getDataAllObject(HrPosition.class);
    }

    @Override
    public HrPosition getDataObject(int id) {
        return (HrPosition)this.basicDao.getDataObject(HrPosition.class, id);
    }
    
    @Override
    public HrPosition getDataObject(String code) {
        return null;
    }
    
    @Override
    public List<HrPosition> getDataObjects(HrPositionCO paramObj) {
        String sql = "select p.* from hr_position p where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.settleParam(paramObj, "status", "and p.", args);
        sql = sql + " order by p.position_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("p", HrPosition.class);
        
        return this.basicDao.selectData(sql, entity, args);
    }
    
    @Override
    public List<HrPosition> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<HrPosition> getDataObjects(Pages pages, HrPositionCO paramObj) {
        String sql = "select p.* from hr_position p where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.settleParam(paramObj, "positionCode", "and p.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "positionName", "and p.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "positionType", "and p.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "status", "and p.", args);
        sql = sql + " order by p.position_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("p", HrPosition.class);
        
        return this.basicDao.getDataSql(sql, entity, args, pages);
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
