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
package com.erp.inv.output.dao.hibernate;

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
import com.erp.inv.input.dao.model.InvInputLine;
import com.erp.inv.output.dao.InvOutputLineDao;
import com.erp.inv.output.dao.model.InvOutputLine;
import com.erp.inv.output.dao.model.InvOutputLineCO;

@Repository
public class InvOutputLineDaoImpl implements InvOutputLineDao{ 

    //注入DaoSupport工具类
    @Autowired
    private DaoSupport daoSupport;
    
    @Override
    public void insertDataObject(InvOutputLine obj) {
        this.daoSupport.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(InvOutputLine obj) {
        this.daoSupport.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(InvOutputLine obj) {
        this.daoSupport.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(InvOutputLine obj) {
        this.daoSupport.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<InvOutputLine> getDataObjects() {
        return this.daoSupport.getDataAllObject(InvOutputLine.class);
    }

    @Override
    public InvOutputLine getDataObject(int id) {
        return (InvOutputLine)this.daoSupport.getDataObject(InvOutputLine.class, id);
    }
    
    @Override
    public InvOutputLine getDataObject(String code) {
        return null;
    }
    
    @Override
    public List<InvOutputLine> getDataObjects(InvOutputLineCO paramObj) {
        return null;
    }
    
    @Override
    public List<InvOutputLine> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<InvOutputLine> getDataObjects(Pages pages, InvOutputLineCO paramObj) {
        return null;
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, InvOutputLineCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.DATA_AUTH)
    public List<InvOutputLine> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, InvOutputLineCO paramObj) {
        return null;
    }
    
    @Override
    public List<InvOutputLine> getInvOutputLineListByOutputHeadCode(Pages pages, InvOutputLineCO paramObj) {
        String sql = "select l.* from inv_output_line l where output_head_code=:outputheadcode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("outputheadcode", paramObj.getOutputHeadCode());
        sql = sql + " order by l.output_line_id";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("l", InvOutputLine.class);
        
        return this.daoSupport.getDataSqlByPage(sql, entity, args, pages);
    }
    
    @Override
    public void deleteInvOutputLineByOutputHeadCode(String outputHeadCode) {
        String sql = "delete from inv_output_line where output_head_code = :outputheadcode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("outputheadcode", outputHeadCode);
        
        this.daoSupport.executeSQLTransaction(sql, args);
    }
    
}
