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
package com.erp.dataset.dao.hibernate;

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
import com.erp.dataset.dao.SysDatasetTypeDao;
import com.erp.dataset.dao.model.SysDatasetType;
import com.erp.dataset.dao.model.SysDatasetTypeCO;

@Repository
public class SysDatasetTypeDaoImpl implements SysDatasetTypeDao{ 

    //注入basicDao工具类
    @Autowired
    private BasicDao basicDao;
    
    @Override
    public void insertDataObject(SysDatasetType obj) {
        this.basicDao.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(SysDatasetType obj) {
        this.basicDao.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(SysDatasetType obj) {
        this.basicDao.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(SysDatasetType obj) {
        this.basicDao.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<SysDatasetType> getDataObjects() {
        return this.basicDao.getDataAllObject(SysDatasetType.class);
    }

    @Override
    public SysDatasetType getDataObject(int id) {
        return (SysDatasetType)this.basicDao.getDataObject(SysDatasetType.class, id);
    }
    
    @Override
    public SysDatasetType getDataObject(String code) {
        return null;
    }
    
    @Override
    public List<SysDatasetType> getDataObjects(SysDatasetTypeCO paramObj) {
        return null;
    }
    
    @Override
    public List<SysDatasetType> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<SysDatasetType> getDataObjects(Pages pages, SysDatasetTypeCO paramObj) {
        String sql = "select t.* from sys_dataset_type t where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.settleParam(paramObj, "datasetTypeCode", "and t.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "datasetTypeName", "and t.", args);
        sql = sql + DaoUtil.settleParam(paramObj, "status", "and t.", args);
        sql = sql + " order by t.dataset_type_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("t", SysDatasetType.class);
        
        return this.basicDao.getDataSql(sql, entity, args, pages);
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, SysDatasetTypeCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.OWN)
    public List<SysDatasetType> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, SysDatasetTypeCO paramObj) {
        return null;
    }
    
    @Override
    public boolean isExistRelateDataForSysDatasetType(String datasetTypeCode) {
        String sql = "select count(*) from sys_dataset d where d.dataset_type_code = :code";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", datasetTypeCode);
        
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
