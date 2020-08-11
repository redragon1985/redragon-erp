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
package com.erp.finance.receipt.dao.hibernate;

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
import com.erp.finance.pay.dao.model.PayLine;
import com.erp.finance.receipt.dao.ReceiptLineDao;
import com.erp.finance.receipt.dao.model.ReceiptLine;
import com.erp.finance.receipt.dao.model.ReceiptLineCO;

@Repository
public class ReceiptLineDaoImpl implements ReceiptLineDao{ 

    //注入DaoSupport工具类
    @Autowired
    private DaoSupport daoSupport;
    
    @Override
    public void insertDataObject(ReceiptLine obj) {
        this.daoSupport.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(ReceiptLine obj) {
        this.daoSupport.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(ReceiptLine obj) {
        this.daoSupport.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(ReceiptLine obj) {
        this.daoSupport.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<ReceiptLine> getDataObjects() {
        return this.daoSupport.getDataAllObject(ReceiptLine.class);
    }

    @Override
    public ReceiptLine getDataObject(int id) {
        return (ReceiptLine)this.daoSupport.getDataObject(ReceiptLine.class, id);
    }
    
    @Override
    public ReceiptLine getDataObject(String code) {
        return null;
    }
    
    @Override
    public List<ReceiptLine> getDataObjects(ReceiptLineCO paramObj) {
        return null;
    }
    
    @Override
    public List<ReceiptLine> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<ReceiptLine> getDataObjects(Pages pages, ReceiptLineCO paramObj) {
        return null;
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, ReceiptLineCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.OWN)
    public List<ReceiptLine> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, ReceiptLineCO paramObj) {
        return null;
    }
    
    @Override
    public void deleteReceiptLineByReceiptHeadCode(String receiptHeadCode) {
        String sql = "delete from receipt_line where receipt_head_code = :receiptheadcode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("receiptheadcode", receiptHeadCode);
        
        this.daoSupport.executeSQLTransaction(sql, args);
    }
    
    @Override
    public BigDecimal getHISReceiptAmountForSO(String soHeadCode, String receiptHeadCode) {
        String sql = "select sum(l.amount) from receipt_line l where l.receipt_source_line_code in (select pl.so_line_code from so_line pl where pl.so_head_code = :soheadcode) and l.receipt_head_code <> :receiptheadcode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("soheadcode", soHeadCode);
        args.put("receiptheadcode", receiptHeadCode);
        
        List list = this.daoSupport.selectDataSqlCount(sql, args);
        if(list.size()>0) {
            return (BigDecimal)list.get(0);
        }
        
        return new BigDecimal(0);
    }
    
    @Override
    public List<ReceiptLine> getReceiptLineListByReceiptHeadCode(Pages pages, ReceiptLineCO paramObj) {
        String sql = "select p.* from receipt_line p where receipt_head_code=:receiptheadcode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("receiptheadcode", paramObj.getReceiptHeadCode());
        sql = sql + " order by p.receipt_line_id";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("p", ReceiptLine.class);
        
        return this.daoSupport.getDataSqlByPage(sql, entity, args, pages);
    }
    
    @Override
    public BigDecimal getReceiptAmountByReceiptHeadCode(String receiptHeadCode) {
        String sql = "select sum(l.amount) from receipt_line l where l.receipt_head_code = :receiptheadcode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("receiptheadcode", receiptHeadCode);
        
        List<BigDecimal> list = this.daoSupport.selectDataSqlCount(sql, args);
        if(list.size()>0) {
            return list.get(0);
        }
        
        return new BigDecimal(0);
    }
    
}
