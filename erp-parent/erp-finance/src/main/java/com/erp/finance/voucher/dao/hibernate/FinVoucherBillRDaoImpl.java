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
import com.erp.finance.voucher.dao.FinVoucherBillRDao;
import com.erp.finance.voucher.dao.model.FinVoucherBillR;
import com.erp.finance.voucher.dao.model.FinVoucherBillRCO;

@Repository
public class FinVoucherBillRDaoImpl implements FinVoucherBillRDao{ 

    //注入basicDao工具类
    @Autowired
    private BasicDao basicDao;
    
    @Override
    public void insertDataObject(FinVoucherBillR obj) {
        this.basicDao.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(FinVoucherBillR obj) {
        this.basicDao.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(FinVoucherBillR obj) {
        this.basicDao.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(FinVoucherBillR obj) {
        this.basicDao.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<FinVoucherBillR> getDataObjects() {
        return this.basicDao.getDataAllObject(FinVoucherBillR.class);
    }

    @Override
    public FinVoucherBillR getDataObject(int id) {
        return (FinVoucherBillR)this.basicDao.getDataObject(FinVoucherBillR.class, id);
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
        
        this.basicDao.executeSQLTransaction(sql, args);
    }
    
}
