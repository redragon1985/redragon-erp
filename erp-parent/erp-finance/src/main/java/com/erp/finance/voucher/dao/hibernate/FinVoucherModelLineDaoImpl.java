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
import com.erp.finance.voucher.dao.FinVoucherModelLineDao;
import com.erp.finance.voucher.dao.model.FinVoucherLine;
import com.erp.finance.voucher.dao.model.FinVoucherModelLine;
import com.erp.finance.voucher.dao.model.FinVoucherModelLineCO;

@Repository
public class FinVoucherModelLineDaoImpl implements FinVoucherModelLineDao{ 

    //注入DaoSupport工具类
    @Autowired
    private DaoSupport daoSupport;
    
    @Override
    public void insertDataObject(FinVoucherModelLine obj) {
        this.daoSupport.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(FinVoucherModelLine obj) {
        this.daoSupport.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(FinVoucherModelLine obj) {
        this.daoSupport.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(FinVoucherModelLine obj) {
        this.daoSupport.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<FinVoucherModelLine> getDataObjects() {
        return this.daoSupport.getDataAllObject(FinVoucherModelLine.class);
    }

    @Override
    public FinVoucherModelLine getDataObject(int id) {
        return (FinVoucherModelLine)this.daoSupport.getDataObject(FinVoucherModelLine.class, id);
    }
    
    @Override
    public FinVoucherModelLine getDataObject(String code) {
        return null;
    }
    
    @Override
    public List<FinVoucherModelLine> getDataObjects(FinVoucherModelLineCO paramObj) {
        return null;
    }
    
    @Override
    public List<FinVoucherModelLine> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<FinVoucherModelLine> getDataObjects(Pages pages, FinVoucherModelLineCO paramObj) {
        return null;
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, FinVoucherModelLineCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.OWN)
    public List<FinVoucherModelLine> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, FinVoucherModelLineCO paramObj) {
        return null;
    }
    
    @Override
    public List<FinVoucherModelLine> getFinVoucherModelLineListByVoucherHeadCode(String voucherHeadCode) {
        String sql = "select l.* from fin_voucher_model_line l where voucher_head_code = :voucherheadcode order by l.voucher_line_id";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("voucherheadcode", voucherHeadCode);
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("l", FinVoucherModelLine.class);
        
        return this.daoSupport.selectDataSql(sql, entity, args);
    }
    
    @Override
    public void deleteFinVoucherModelLineByVoucherHeadCode(String voucherHeadCode) {
        String sql = "delete from fin_voucher_model_line where voucher_head_code = :voucherheadcode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("voucherheadcode", voucherHeadCode);
        
        this.daoSupport.executeSQLTransaction(sql, args);        
    }
    
}
