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
package com.erp.order.poa.dao.hibernate;

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
import com.erp.order.po.dao.model.PoLineCO;
import com.erp.order.poa.dao.PoAgreementLineDao;
import com.erp.order.poa.dao.model.PoAgreementLine;
import com.erp.order.poa.dao.model.PoAgreementLineCO;

@Repository
public class PoAgreementLineDaoImpl implements PoAgreementLineDao{ 

    //注入DaoSupport工具类
    @Autowired
    private DaoSupport daoSupport;
    
    @Override
    public void insertDataObject(PoAgreementLine obj) {
        this.daoSupport.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(PoAgreementLine obj) {
        this.daoSupport.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(PoAgreementLine obj) {
        this.daoSupport.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(PoAgreementLine obj) {
        this.daoSupport.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<PoAgreementLine> getDataObjects() {
        return this.daoSupport.getDataAllObject(PoAgreementLine.class);
    }

    @Override
    public PoAgreementLine getDataObject(int id) {
        return (PoAgreementLine)this.daoSupport.getDataObject(PoAgreementLine.class, id);
    }
    
    @Override
    public PoAgreementLine getDataObject(String code) {
        String sql = "select p.* from po_agreement_line p where p.po_line_code = :code";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", code);
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("p", PoAgreementLine.class);
        
        List<PoAgreementLine> list = this.daoSupport.selectDataSql(sql, entity, args);
        if(list.size()>0) {
            return list.get(0);
        }
        
        return null;
    }
    
    @Override
    public List<PoAgreementLine> getDataObjects(PoAgreementLineCO paramObj) {
        return null;
    }
    
    @Override
    public List<PoAgreementLine> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<PoAgreementLine> getDataObjects(Pages pages, PoAgreementLineCO paramObj) {
        return null;
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, PoAgreementLineCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.DATA_AUTH)
    public List<PoAgreementLine> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, PoAgreementLineCO paramObj) {
        return null;
    }
    
    @Override
    public List<PoAgreementLine> getPoLineListByPoHeadCode(Pages pages, PoAgreementLineCO paramObj) {
        String sql = "select p.* from po_agreement_line p where po_head_code=:poheadcode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("poheadcode", paramObj.getPoHeadCode());
        sql = sql + " order by p.po_line_id";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("p", PoAgreementLine.class);
        
        return this.daoSupport.getDataSqlByPage(sql, entity, args, pages);
    }
    
    @Override
    public void deletetPoLineByPoHeadCode(String poHeadCode) {
        String sql = "delete from po_agreement_line where po_head_code=:poheadcode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("poheadcode", poHeadCode);
        
        this.daoSupport.executeSQLTransaction(sql, args);
    }
    
    @Override
    public BigDecimal getPoAmount(String poHeadCode) {
        String sql = "select sum(p.amount) from po_agreement_line p where po_head_code=:poheadcode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("poheadcode", poHeadCode);
        
        List list = this.daoSupport.selectDataSqlCount(sql, args);
        if(list.size()>0) {
            return (BigDecimal)list.get(0);
        }
        
        return new BigDecimal(0);
    }
    
    @Override
    public void updateLineForVersion(String code) {
        String sql = "update po_agreement_line set version = version+1 where po_head_code = :code";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", code);
        
        this.daoSupport.executeSQLTransaction(sql, args);
    }
    
}
