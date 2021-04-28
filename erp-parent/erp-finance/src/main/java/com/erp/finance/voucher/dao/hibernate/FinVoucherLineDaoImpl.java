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
package com.erp.finance.voucher.dao.hibernate;

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
import com.erp.finance.voucher.dao.FinVoucherLineDao;
import com.erp.finance.voucher.dao.model.FinVoucherHead;
import com.erp.finance.voucher.dao.model.FinVoucherLine;
import com.erp.finance.voucher.dao.model.FinVoucherLineCO;

@Repository
public class FinVoucherLineDaoImpl implements FinVoucherLineDao{ 

    //注入basicDao工具类
    @Autowired
    private BasicDao basicDao;
    
    @Override
    public void insertDataObject(FinVoucherLine obj) {
        this.basicDao.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(FinVoucherLine obj) {
        this.basicDao.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(FinVoucherLine obj) {
        this.basicDao.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(FinVoucherLine obj) {
        this.basicDao.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<FinVoucherLine> getDataObjects() {
        return this.basicDao.getDataAllObject(FinVoucherLine.class);
    }

    @Override
    public FinVoucherLine getDataObject(int id) {
        return (FinVoucherLine)this.basicDao.getDataObject(FinVoucherLine.class, id);
    }
    
    @Override
    public FinVoucherLine getDataObject(String code) {
        return null;
    }
    
    @Override
    public List<FinVoucherLine> getDataObjects(FinVoucherLineCO paramObj) {
        return null;
    }
    
    @Override
    public List<FinVoucherLine> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<FinVoucherLine> getDataObjects(Pages pages, FinVoucherLineCO paramObj) {
        return null;
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, FinVoucherLineCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.OWN)
    public List<FinVoucherLine> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, FinVoucherLineCO paramObj) {
        return null;
    }
    
    @Override
    public List<FinVoucherLine> getFinVoucherLineListByVoucherHeadCode(String voucherHeadCode) {
        String sql = "select l.* from fin_voucher_line l where voucher_head_code = :voucherheadcode order by l.voucher_line_id";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("voucherheadcode", voucherHeadCode);
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("l", FinVoucherLine.class);
        
        return this.basicDao.selectData(sql, entity, args);
    }
    
    @Override
    public void deleteFinVoucherLineByVoucherHeadCode(String voucherHeadCode) {
        String sql = "delete from fin_voucher_line where voucher_head_code = :voucherheadcode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("voucherheadcode", voucherHeadCode);
        
        this.basicDao.executeSQLTransaction(sql, args);
    }
    
    @Override
    public BigDecimal getFinVoucherAmountByVoucherHeadCode(String voucherHeadCode) {
        String sql = "select sum(l.dr_amount) from fin_voucher_line l where l.voucher_head_code = :voucherheadcode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("voucherheadcode", voucherHeadCode);
        
        List<BigDecimal> list = this.basicDao.selectDataSqlCount(sql, args);
        if(list.size()>0) {
            return list.get(0);
        }
        
        return new BigDecimal(0D);
    }
    
    @Override
    public List<FinVoucherLine> getVoucherLineList(String billType, String billHeadCode) {
        String sql = "select l.* from fin_voucher_line l,fin_voucher_bill_r b "
                + "where l.voucher_head_code = b.voucher_head_code "
                + "and b.bill_type = :billType and b.bill_head_code = :billHeadCode";
     
         Map<String, Object> args = new HashMap<String, Object>();
         args.put("billType", billType);
         args.put("billHeadCode", billHeadCode);
         
         Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
         entity.put("l", FinVoucherLine.class);
         
         List<FinVoucherLine> list = this.basicDao.selectData(sql, entity, args);
         
         return list;
    }
    
}
