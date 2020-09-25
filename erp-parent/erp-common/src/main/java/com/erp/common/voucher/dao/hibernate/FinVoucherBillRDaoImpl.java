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
package com.erp.common.voucher.dao.hibernate;

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
import com.erp.common.voucher.dao.FinVoucherBillRDao;
import com.erp.common.voucher.dao.model.FinVoucherBillR;
import com.erp.common.voucher.dao.model.FinVoucherBillRCO;

@Repository("finVoucherBillRDaoCommon")
public class FinVoucherBillRDaoImpl implements FinVoucherBillRDao{ 

    //注入DaoSupport工具类
    @Autowired
    private DaoSupport daoSupport;
    
    @Override
    public void insertDataObject(FinVoucherBillR obj) {
        this.daoSupport.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(FinVoucherBillR obj) {
        this.daoSupport.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(FinVoucherBillR obj) {
        this.daoSupport.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(FinVoucherBillR obj) {
        this.daoSupport.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<FinVoucherBillR> getDataObjects() {
        return this.daoSupport.getDataAllObject(FinVoucherBillR.class);
    }

    @Override
    public FinVoucherBillR getDataObject(int id) {
        return (FinVoucherBillR)this.daoSupport.getDataObject(FinVoucherBillR.class, id);
    }
    
    @Override
    public FinVoucherBillR getDataObject(String code) {
        return null;
    }
    
    @Override
    public List<FinVoucherBillR> getDataObjects(FinVoucherBillRCO paramObj) {
        return null;
    }
    
    @Override
    public List<FinVoucherBillR> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<FinVoucherBillR> getDataObjects(Pages pages, FinVoucherBillRCO paramObj) {
        return null;
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, FinVoucherBillRCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.OWN)
    public List<FinVoucherBillR> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, FinVoucherBillRCO paramObj) {
        return null;
    }
    
    @Override
    public void deleteFinVoucherBillRByVoucherHeadCode(String voucherHeadCode) {
        String sql = "delete from fin_voucher_bill_r where voucher_head_code = :code";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", voucherHeadCode);
        
        this.daoSupport.executeSQLTransaction(sql, args);
    }
    
    @Override
    public String getVoucherHeadCodeByBillHeadCode(String billType, String billHeadCode) {
        String sql = "select voucher_head_code from fin_voucher_bill_r where bill_head_code = :billHeadCode and bill_type = :billType";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("billType", billType);
        args.put("billHeadCode", billHeadCode);
        
        List<String> list = this.daoSupport.selectDataSqlCount(sql, args);
        if(list!=null&&list.size()>0) {
            return list.get(0);
        }
        
        return null;
    }
    
}
