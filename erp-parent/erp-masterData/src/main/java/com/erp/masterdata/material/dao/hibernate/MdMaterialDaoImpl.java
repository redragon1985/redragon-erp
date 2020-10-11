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
package com.erp.masterdata.material.dao.hibernate;

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
import com.erp.masterdata.material.dao.MdMaterialDao;
import com.erp.masterdata.material.dao.model.MdMaterial;
import com.erp.masterdata.material.dao.model.MdMaterialCO;

@Repository
public class MdMaterialDaoImpl implements MdMaterialDao{ 

    //注入DaoSupport工具类
    @Autowired
    private DaoSupport daoSupport;
    
    @Override
    public void insertDataObject(MdMaterial obj) {
        this.daoSupport.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(MdMaterial obj) {
        this.daoSupport.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(MdMaterial obj) {
        this.daoSupport.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(MdMaterial obj) {
        this.daoSupport.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<MdMaterial> getDataObjects() {
        return this.daoSupport.getDataAllObject(MdMaterial.class);
    }

    @Override
    public MdMaterial getDataObject(int id) {
        return (MdMaterial)this.daoSupport.getDataObject(MdMaterial.class, id);
    }
    
    @Override
    public MdMaterial getDataObject(String code) {
        String sql = "select m.* from md_material m where m.material_code = :code";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", code);
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("m", MdMaterial.class);
        
        List<MdMaterial> list = this.daoSupport.selectDataSql(sql, entity, args);
        if(list.size()>0) {
            return list.get(0);
        }
        
        return null;
    }
    
    @Override
    public List<MdMaterial> getDataObjects(MdMaterialCO paramObj) {
        String sql = "select m.* from md_material m where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.getSQLCondition(paramObj, "materialCode", "and m.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "materialName", "and m.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "materialType", "and m.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "categoryCode", "and m.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "approveStatus", "and m.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "status", "and m.", args);
        
        sql = sql + " order by m.material_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("m", MdMaterial.class);
        
        return this.daoSupport.selectDataSql(sql, entity, args);
    }
    
    @Override
    public List<MdMaterial> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<MdMaterial> getDataObjects(Pages pages, MdMaterialCO paramObj) {
        String sql = "select m.* from md_material m where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.getSQLCondition(paramObj, "materialCode", "and m.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "materialName", "and m.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "materialType", "and m.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "categoryCode", "and m.", args);
        sql = sql + DaoUtil.getSQLCondition(paramObj, "status", "and m.", args);
        
        sql = sql + " order by m.material_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("m", MdMaterial.class);
        
        return this.daoSupport.getDataSqlByPage(sql, entity, args, pages);
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, MdMaterialCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.OWN)
    public List<MdMaterial> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, MdMaterialCO paramObj) {
        return null;
    }
    
    @Override
    public void updateApproveStatus(int id, String approveStatus) {
        String sql = "update md_material set approve_status = :approveStatus where material_id = :id";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("id", id);
        args.put("approveStatus", approveStatus);
        
        this.daoSupport.executeSQLTransaction(sql, args);
    }
    
}
