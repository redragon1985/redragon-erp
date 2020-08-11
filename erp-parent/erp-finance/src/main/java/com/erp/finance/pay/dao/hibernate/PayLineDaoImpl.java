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
package com.erp.finance.pay.dao.hibernate;

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
import com.erp.finance.pay.dao.PayLineDao;
import com.erp.finance.pay.dao.model.PayLine;
import com.erp.finance.pay.dao.model.PayLineCO;

@Repository
public class PayLineDaoImpl implements PayLineDao{ 

    //注入DaoSupport工具类
    @Autowired
    private DaoSupport daoSupport;
    
    @Override
    public void insertDataObject(PayLine obj) {
        this.daoSupport.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(PayLine obj) {
        this.daoSupport.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(PayLine obj) {
        this.daoSupport.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(PayLine obj) {
        this.daoSupport.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<PayLine> getDataObjects() {
        return this.daoSupport.getDataAllObject(PayLine.class);
    }

    @Override
    public PayLine getDataObject(int id) {
        return (PayLine)this.daoSupport.getDataObject(PayLine.class, id);
    }
    
    @Override
    public PayLine getDataObject(String code) {
        return null;
    }
    
    @Override
    public List<PayLine> getDataObjects(PayLineCO paramObj) {
        return null;
    }
    
    @Override
    public List<PayLine> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<PayLine> getDataObjects(Pages pages, PayLineCO paramObj) {
        return null;
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, PayLineCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.OWN)
    public List<PayLine> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, PayLineCO paramObj) {
        return null;
    }
    
    @Override
    public List<PayLine> getPayLineListByPayHeadCode(Pages pages, PayLineCO paramObj) {
        String sql = "select p.* from pay_line p where pay_head_code=:payheadcode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("payheadcode", paramObj.getPayHeadCode());
        sql = sql + " order by p.pay_line_id";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("p", PayLine.class);
        
        return this.daoSupport.getDataSqlByPage(sql, entity, args, pages);
    }
    
    @Override
    public BigDecimal getHISPayAmountForPO(String poHeadCode, String payHeadCode) {
        String sql = "select sum(l.amount) from pay_line l where l.pay_source_line_code in (select pl.po_line_code from po_line pl where pl.po_head_code = :poheadcode) and l.pay_head_code <> :payheadcode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("poheadcode", poHeadCode);
        args.put("payheadcode", payHeadCode);
        
        List list = this.daoSupport.selectDataSqlCount(sql, args);
        if(list.size()>0) {
            return (BigDecimal)list.get(0);
        }
        
        return new BigDecimal(0);
    }
    
    @Override
    public void deletePayLineByPayHeadCode(String payHeadCode) {
        String sql = "delete from pay_line where pay_head_code = :payheadcode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("payheadcode", payHeadCode);
        
        this.daoSupport.executeSQLTransaction(sql, args);
    }
    
    @Override
    public BigDecimal getPayAmountByPayHeadCode(String payHeadCode) {
        String sql = "select sum(l.amount) from pay_line l where l.pay_head_code = :payheadcode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("payheadcode", payHeadCode);
        
        List<BigDecimal> list = this.daoSupport.selectDataSqlCount(sql, args);
        if(list.size()>0) {
            return list.get(0);
        }
        
        return new BigDecimal(0);
    }
    
}
