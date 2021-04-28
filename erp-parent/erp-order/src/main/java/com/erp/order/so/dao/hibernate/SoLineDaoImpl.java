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
package com.erp.order.so.dao.hibernate;

import java.math.BigDecimal;
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
import com.erp.order.po.dao.model.PoLine;
import com.erp.order.so.dao.SoLineDao;
import com.erp.order.so.dao.model.SoLine;
import com.erp.order.so.dao.model.SoLineCO;

@Repository
public class SoLineDaoImpl implements SoLineDao{ 

    //注入basicDao工具类
    @Autowired
    private BasicDao basicDao;
    
    @Override
    public void insertDataObject(SoLine obj) {
        this.basicDao.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(SoLine obj) {
        this.basicDao.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(SoLine obj) {
        this.basicDao.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(SoLine obj) {
        this.basicDao.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<SoLine> getDataObjects() {
        return this.basicDao.getDataAllObject(SoLine.class);
    }

    @Override
    public SoLine getDataObject(int id) {
        return (SoLine)this.basicDao.getDataObject(SoLine.class, id);
    }
    
    @Override
    public SoLine getDataObject(String code) {
        String sql = "select s.* from so_line s where s.so_line_code = :code";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", code);
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("s", SoLine.class);
        
        List<SoLine> list = this.basicDao.selectData(sql, entity, args);
        if(list.size()>0) {
            return list.get(0);
        }
        
        return null;
    }
    
    @Override
    public List<SoLine> getDataObjects(SoLineCO paramObj) {
        return null;
    }
    
    @Override
    public List<SoLine> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<SoLine> getDataObjects(Pages pages, SoLineCO paramObj) {
        return null;
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, SoLineCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.OWN)
    public List<SoLine> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, SoLineCO paramObj) {
        return null;
    }
    
    @Override
    public List<SoLine> getSoLineListBySoHeadCode(Pages pages, SoLineCO paramObj) {
        String sql = "select s.* from so_line s where so_head_code=:soheadcode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("soheadcode", paramObj.getSoHeadCode());
        sql = sql + " order by s.so_line_id";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("s", SoLine.class);
        
        return this.basicDao.getDataSql(sql, entity, args, pages);
    }
    
    @Override
    public void deletetSoLineBySoHeadCode(String soHeadCode) {
        String sql = "delete from so_line where so_head_code=:soheadcode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("soheadcode", soHeadCode);
        
        this.basicDao.executeSQLTransaction(sql, args);
    }
    
    @Override
    public BigDecimal getSoAmount(String soHeadCode) {
        String sql = "select sum(s.amount) from so_line s where so_head_code=:soheadcode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("soheadcode", soHeadCode);
        
        List list = this.basicDao.selectDataSqlCount(sql, args);
        if(list.size()>0) {
            return (BigDecimal)list.get(0);
        }
        
        return new BigDecimal(0);
    }
    
}
