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
package com.erp.finance.ar.invoice.dao.hibernate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.finance.ar.invoice.dao.ArInvoiceLineDao;
import com.erp.finance.ar.invoice.dao.model.ArInvoiceLine;
import com.erp.finance.ar.invoice.dao.model.ArInvoiceLineCO;
import com.framework.annotation.Permissions;
import com.framework.annotation.Permissions.PermissionType;
import com.framework.annotation.SqlParam;
import com.framework.dao.DaoSupport;
import com.framework.dao.model.Pages;

@Repository
public class ArInvoiceLineDaoImpl implements ArInvoiceLineDao{ 

    //注入DaoSupport工具类
    @Autowired
    private DaoSupport daoSupport;
    
    @Override
    public void insertDataObject(ArInvoiceLine obj) {
        this.daoSupport.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(ArInvoiceLine obj) {
        this.daoSupport.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(ArInvoiceLine obj) {
        this.daoSupport.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(ArInvoiceLine obj) {
        this.daoSupport.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<ArInvoiceLine> getDataObjects() {
        return this.daoSupport.getDataAllObject(ArInvoiceLine.class);
    }

    @Override
    public ArInvoiceLine getDataObject(int id) {
        return (ArInvoiceLine)this.daoSupport.getDataObject(ArInvoiceLine.class, id);
    }
    
    @Override
    public ArInvoiceLine getDataObject(String code) {
        return null;
    }
    
    @Override
    public List<ArInvoiceLine> getDataObjects(ArInvoiceLineCO paramObj) {
        return null;
    }
    
    @Override
    public List<ArInvoiceLine> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<ArInvoiceLine> getDataObjects(Pages pages, ArInvoiceLineCO paramObj) {
        return null;
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, ArInvoiceLineCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.OWN)
    public List<ArInvoiceLine> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, ArInvoiceLineCO paramObj) {
        return null;
    }
    
    @Override
    public void deleteLineByHeadCode(String headCode) {
        String sql = "delete from ar_invoice_line where invoice_head_code = :receiptheadcode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("receiptheadcode", headCode);
        
        this.daoSupport.executeSQLTransaction(sql, args);
    }
    
    @Override
    public BigDecimal getHISArInvoiceAmountForSO(String soHeadCode, String receiptHeadCode) {
        String sql = "select sum(l.amount) from ar_invoice_line l where l.invoice_source_line_code in (select pl.so_line_code from so_line pl where pl.so_head_code = :soheadcode) and l.invoice_head_code <> :receiptheadcode";
        
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
    public List<ArInvoiceLine> getArInvoiceLineListByHeadCode(Pages pages, ArInvoiceLineCO paramObj) {
        String sql = "select p.* from ar_invoice_line p where invoice_head_code=:receiptheadcode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("receiptheadcode", paramObj.getInvoiceHeadCode());
        sql = sql + " order by p.invoice_line_id";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("p", ArInvoiceLine.class);
        
        return this.daoSupport.getDataSqlByPage(sql, entity, args, pages);
    }
    
    @Override
    public BigDecimal getArInvoiceAmountByHeadCode(String headCode) {
        String sql = "select sum(l.amount) from ar_invoice_line l where l.invoice_head_code = :receiptheadcode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("receiptheadcode", headCode);
        
        List<BigDecimal> list = this.daoSupport.selectDataSqlCount(sql, args);
        if(list.size()>0) {
            return list.get(0);
        }
        
        return new BigDecimal(0);
    }
    
    @Override
    public BigDecimal[] getInvoiceLineAmountSumByHeadCode(String headCode) {
        String sql = "select sum(amount) as amount,sum(tax_amount) as tax_amount from ar_invoice_line l where l.invoice_head_code = :headCode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("headCode", headCode);
        
        BigDecimal[] sumAmount = new BigDecimal[2];
        List<Object[]> list = this.daoSupport.selectDataSqlCount(sql, args);
        if(list.size()>0) {
            if(list.get(0)[0]!=null) {
                sumAmount[0] = new BigDecimal(String.valueOf(list.get(0)[0]));
            }else {
                sumAmount[0] = new BigDecimal(0D);
            }
            
            if(list.get(0)[1]!=null) {
                sumAmount[1] = new BigDecimal(String.valueOf(list.get(0)[1]));
            }else {
                sumAmount[1] = new BigDecimal(0D);
            }
        }
        
        return sumAmount;
    }
    
    @Override
    public List<ArInvoiceLine> getArInvoiceLineListByHeadCode(String headCode) {
        String sql = "select p.* from ar_invoice_line p where invoice_head_code=:receiptheadcode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("receiptheadcode", headCode);
        sql = sql + " order by p.invoice_line_id";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("p", ArInvoiceLine.class);
        
        return this.daoSupport.selectDataSql(sql, entity, args);
    }
    
}
