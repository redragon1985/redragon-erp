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
package com.erp.finance.ar.receipt.dao.hibernate;

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
import com.erp.finance.ap.pay.dao.model.ApPayLine;
import com.erp.finance.ar.receipt.dao.ArReceiptLineDao;
import com.erp.finance.ar.receipt.dao.model.ArReceiptLine;
import com.erp.finance.ar.receipt.dao.model.ArReceiptLineCO;

@Repository
public class ArReceiptLineDaoImpl implements ArReceiptLineDao{ 

    //注入basicDao工具类
    @Autowired
    private BasicDao basicDao;
    
    @Override
    public void insertDataObject(ArReceiptLine obj) {
        this.basicDao.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(ArReceiptLine obj) {
        this.basicDao.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(ArReceiptLine obj) {
        this.basicDao.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(ArReceiptLine obj) {
        this.basicDao.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<ArReceiptLine> getDataObjects() {
        return this.basicDao.getDataAllObject(ArReceiptLine.class);
    }

    @Override
    public ArReceiptLine getDataObject(int id) {
        return (ArReceiptLine)this.basicDao.getDataObject(ArReceiptLine.class, id);
    }
    
    @Override
    public ArReceiptLine getDataObject(String code) {
        return null;
    }
    
    @Override
    public List<ArReceiptLine> getDataObjects(ArReceiptLineCO paramObj) {
        return null;
    }
    
    @Override
    public List<ArReceiptLine> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<ArReceiptLine> getDataObjects(Pages pages, ArReceiptLineCO paramObj) {
        return null;
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, ArReceiptLineCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.DATA_AUTH)
    public List<ArReceiptLine> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, ArReceiptLineCO paramObj) {
        return null;
    }
    
    @Override
    public List<ArReceiptLine> getReceiptLineListByHeadCode(Pages pages, ArReceiptLineCO paramObj) {
        String sql = "select l.* from ar_receipt_line l where l.receipt_head_code=:headcode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("headcode", paramObj.getReceiptHeadCode());
        sql = sql + " order by l.receipt_line_id";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("l", ArReceiptLine.class);
        
        return this.basicDao.getDataSql(sql, entity, args, pages);
    }
    
    @Override
    public void deleteLineByHeadCode(String headCode) {
        String sql = "delete from ar_receipt_line where receipt_head_code = :headcode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("headcode", headCode);
        
        this.basicDao.executeSQLTransaction(sql, args);
    }
    
    @Override
    public List<ArReceiptLine> getArReceiptLineListByHeadCode(String headCode) {
        String sql = "select l.* from ar_receipt_line l where l.receipt_head_code=:headcode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("headcode", headCode);
        sql = sql + " order by l.receipt_line_id";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("l", ArReceiptLine.class);
        
        return this.basicDao.selectData(sql, entity, args);
    }
    
    @Override
    public Double getInvoiceReceiveAmount(String invoiceHeadCode, Integer receiptLineId) {
        String sql = "select sum(l.invoice_receipt_amount) from ar_receipt_line l where l.invoice_head_code = :code and l.receipt_line_id <> :id";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", invoiceHeadCode);
        args.put("id", receiptLineId);
        
        List list = this.basicDao.selectDataSqlCount(sql, args);
        if(list!=null&&list.size()>0) {
            if(list.get(0)!=null) {
                return Double.valueOf(String.valueOf(list.get(0)));
            }
        }
        
        return 0D;
    }
    
}
