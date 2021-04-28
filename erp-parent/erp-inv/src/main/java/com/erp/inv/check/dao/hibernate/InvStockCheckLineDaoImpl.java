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
package com.erp.inv.check.dao.hibernate;

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
import com.erp.inv.check.dao.InvStockCheckLineDao;
import com.erp.inv.check.dao.model.InvStockCheckLine;
import com.erp.inv.check.dao.model.InvStockCheckLineCO;
import com.erp.inv.input.dao.model.InvInputLine;

@Repository
public class InvStockCheckLineDaoImpl implements InvStockCheckLineDao{ 

    //注入basicDao工具类
    @Autowired
    private BasicDao basicDao;
    
    @Override
    public void insertDataObject(InvStockCheckLine obj) {
        this.basicDao.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(InvStockCheckLine obj) {
        this.basicDao.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(InvStockCheckLine obj) {
        this.basicDao.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(InvStockCheckLine obj) {
        this.basicDao.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<InvStockCheckLine> getDataObjects() {
        return this.basicDao.getDataAllObject(InvStockCheckLine.class);
    }

    @Override
    public InvStockCheckLine getDataObject(int id) {
        return (InvStockCheckLine)this.basicDao.getDataObject(InvStockCheckLine.class, id);
    }
    
    @Override
    public InvStockCheckLine getDataObject(String code) {
        return null;
    }
    
    @Override
    public List<InvStockCheckLine> getDataObjects(InvStockCheckLineCO paramObj) {
        return null;
    }
    
    @Override
    public List<InvStockCheckLine> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<InvStockCheckLine> getDataObjects(Pages pages, InvStockCheckLineCO paramObj) {
        return null;
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, InvStockCheckLineCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.DATA_AUTH)
    public List<InvStockCheckLine> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, InvStockCheckLineCO paramObj) {
        return null;
    }
    
    @Override
    public List<InvStockCheckLine> getInvStockCheckLineListByHeadCode(String headCode) {
        String sql = "select l.* from inv_stock_check_line l where check_head_code=:headcode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("headcode", headCode);
        sql = sql + " order by l.check_line_id";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("l", InvStockCheckLine.class);
        
        return this.basicDao.selectData(sql, entity, args);
    }
    
    @Override
    public void deleteInvStockCheckLineByHeadCode(String headCode) {
        String sql = "delete from inv_stock_check_line where check_head_code = :headcode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("headcode", headCode);
        
        this.basicDao.executeSQLTransaction(sql, args);
    }
    
}
