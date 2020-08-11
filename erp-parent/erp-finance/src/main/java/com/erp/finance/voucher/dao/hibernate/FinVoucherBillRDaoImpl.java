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
package com.erp.finance.voucher.dao.hibernate;

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
import com.erp.finance.voucher.dao.FinVoucherBillRDao;
import com.erp.finance.voucher.dao.model.FinVoucherBillR;
import com.erp.finance.voucher.dao.model.FinVoucherBillRCO;

@Repository
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
    
}
