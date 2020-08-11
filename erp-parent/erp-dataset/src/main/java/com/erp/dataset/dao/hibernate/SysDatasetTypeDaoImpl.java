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
package com.erp.dataset.dao.hibernate;

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
import com.erp.dataset.dao.SysDatasetTypeDao;
import com.erp.dataset.dao.model.SysDatasetType;
import com.erp.dataset.dao.model.SysDatasetTypeCO;

@Repository
public class SysDatasetTypeDaoImpl implements SysDatasetTypeDao{ 

    //注入DaoSupport工具类
    @Autowired
    private DaoSupport daoSupport;
    
    @Override
    public void insertDataObject(SysDatasetType obj) {
        this.daoSupport.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(SysDatasetType obj) {
        this.daoSupport.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(SysDatasetType obj) {
        this.daoSupport.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(SysDatasetType obj) {
        this.daoSupport.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<SysDatasetType> getDataObjects() {
        return this.daoSupport.getDataAllObject(SysDatasetType.class);
    }

    @Override
    public SysDatasetType getDataObject(int id) {
        return (SysDatasetType)this.daoSupport.getDataObject(SysDatasetType.class, id);
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
        sql = sql + DaoUtil.getSQLCondition(paramObj, "datasetTypeCode", "and t.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "datasetTypeName", "and t.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "status", "and t.", args);
        sql = sql + " order by t.dataset_type_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("t", SysDatasetType.class);
        
        return this.daoSupport.getDataSqlByPage(sql, entity, args, pages);
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
