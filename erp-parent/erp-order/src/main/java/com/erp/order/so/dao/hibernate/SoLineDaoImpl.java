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
package com.erp.order.so.dao.hibernate;

import java.math.BigDecimal;
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
import com.erp.order.po.dao.model.PoLine;
import com.erp.order.so.dao.SoLineDao;
import com.erp.order.so.dao.model.SoLine;
import com.erp.order.so.dao.model.SoLineCO;

@Repository
public class SoLineDaoImpl implements SoLineDao{ 

    //注入DaoSupport工具类
    @Autowired
    private DaoSupport daoSupport;
    
    @Override
    public void insertDataObject(SoLine obj) {
        this.daoSupport.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(SoLine obj) {
        this.daoSupport.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(SoLine obj) {
        this.daoSupport.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(SoLine obj) {
        this.daoSupport.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<SoLine> getDataObjects() {
        return this.daoSupport.getDataAllObject(SoLine.class);
    }

    @Override
    public SoLine getDataObject(int id) {
        return (SoLine)this.daoSupport.getDataObject(SoLine.class, id);
    }
    
    @Override
    public SoLine getDataObject(String code) {
        String sql = "select s.* from so_line s where s.so_line_code = :code";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", code);
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("s", SoLine.class);
        
        List<SoLine> list = this.daoSupport.selectDataSql(sql, entity, args);
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
        
        return this.daoSupport.getDataSqlByPage(sql, entity, args, pages);
    }
    
    @Override
    public void deletetSoLineBySoHeadCode(String soHeadCode) {
        String sql = "delete from so_line where so_head_code=:soheadcode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("soheadcode", soHeadCode);
        
        this.daoSupport.executeSQLTransaction(sql, args);
    }
    
    @Override
    public BigDecimal getSoAmount(String soHeadCode) {
        String sql = "select sum(s.amount) from so_line s where so_head_code=:soheadcode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("soheadcode", soHeadCode);
        
        List list = this.daoSupport.selectDataSqlCount(sql, args);
        if(list.size()>0) {
            return (BigDecimal)list.get(0);
        }
        
        return new BigDecimal(0);
    }
    
}
