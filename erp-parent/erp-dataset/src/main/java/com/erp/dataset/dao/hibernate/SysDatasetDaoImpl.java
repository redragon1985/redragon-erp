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
import com.erp.dataset.dao.SysDatasetDao;
import com.erp.dataset.dao.model.SysDataset;
import com.erp.dataset.dao.model.SysDatasetCO;

@Repository
public class SysDatasetDaoImpl implements SysDatasetDao{ 

    //注入DaoSupport工具类
    @Autowired
    private DaoSupport daoSupport;
    
    @Override
    public void insertDataObject(SysDataset obj) {
        this.daoSupport.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(SysDataset obj) {
        this.daoSupport.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(SysDataset obj) {
        this.daoSupport.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(SysDataset obj) {
        this.daoSupport.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<SysDataset> getDataObjects() {
        return this.daoSupport.getDataAllObject(SysDataset.class);
    }

    @Override
    public SysDataset getDataObject(int id) {
        return (SysDataset)this.daoSupport.getDataObject(SysDataset.class, id);
    }
    
    @Override
    public SysDataset getDataObject(String code) {
        return null;
    }
    
    @Override
    public List<SysDataset> getDataObjects(SysDatasetCO paramObj) {
        return null;
    }
    
    @Override
    public List<SysDataset> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<SysDataset> getDataObjects(Pages pages, SysDatasetCO paramObj) {
        String sql = "select d.* from sys_dataset d where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.getSQLCondition(paramObj, "datasetCode", "and d.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "datasetName", "and d.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "datasetTypeCode", "and d.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "status", "and d.", args);
        sql = sql + " order by d.dataset_code";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("d", SysDataset.class);
        
        return this.daoSupport.getDataSqlByPage(sql, entity, args, pages);
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, SysDatasetCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.OWN)
    public List<SysDataset> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, SysDatasetCO paramObj) {
        return null;
    }
    
    @Override
    public Map<String, String> getDatasetMap(String datasetTypeCode) {
        String sql = "select d.* from sys_dataset d where d.dataset_type_code = :datasetTypeCode and d.status = 'Y' order by d.dataset_code";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("datasetTypeCode", datasetTypeCode);
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("d", SysDataset.class);
        
        Map<String, String> map = new LinkedHashMap<String, String>();
        
        List<SysDataset> list = this.daoSupport.selectDataSql(sql, entity, args);
        for(SysDataset sysDataset: list) {
            map.put(sysDataset.getDatasetCode(), sysDataset.getDatasetName());
        }
        
        return map;
    }
    
}
