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
package com.erp.masterdata.customer.dao.hibernate;

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
import com.erp.masterdata.customer.dao.MdCustomerBankDao;
import com.erp.masterdata.customer.dao.model.MdCustomerBank;
import com.erp.masterdata.customer.dao.model.MdCustomerBankCO;

@Repository
public class MdCustomerBankDaoImpl implements MdCustomerBankDao{ 

    //注入DaoSupport工具类
    @Autowired
    private DaoSupport daoSupport;
    
    @Override
    public void insertDataObject(MdCustomerBank obj) {
        this.daoSupport.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(MdCustomerBank obj) {
        this.daoSupport.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(MdCustomerBank obj) {
        this.daoSupport.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(MdCustomerBank obj) {
        this.daoSupport.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<MdCustomerBank> getDataObjects() {
        return this.daoSupport.getDataAllObject(MdCustomerBank.class);
    }

    @Override
    public MdCustomerBank getDataObject(int id) {
        return (MdCustomerBank)this.daoSupport.getDataObject(MdCustomerBank.class, id);
    }
    
    @Override
    public MdCustomerBank getDataObject(String code) {
        return null;
    }
    
    @Override
    public List<MdCustomerBank> getDataObjects(MdCustomerBankCO paramObj) {
        return null;
    }
    
    @Override
    public List<MdCustomerBank> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<MdCustomerBank> getDataObjects(Pages pages, MdCustomerBankCO paramObj) {
        return null;
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, MdCustomerBankCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.OWN)
    public List<MdCustomerBank> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, MdCustomerBankCO paramObj) {
        return null;
    }
    
    @Override
    public int getBankCountByCustomerCode(String customerCode) {
        String sql = "select count(1) from md_customer_bank b where b.customer_code = :customerCode";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("customerCode", customerCode);
        
        List list = this.daoSupport.selectDataSqlCount(sql, args);
        if(list.size()>0) {
            return Integer.parseInt(String.valueOf(list.get(0)));
        }
        
        return 0;
    }
    
    @Override
    public List<MdCustomerBank> getBankListByCustomerCode(Pages pages, MdCustomerBankCO paramObj) {
        String sql = "select b.* from md_customer_bank b where customer_code=:code";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", paramObj.getCustomerCode());
        
        sql = sql + " order by b.bank_id desc";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("b", MdCustomerBank.class);
        
        return this.daoSupport.getDataSqlByPage(sql, entity, args, pages);
    }
    
}
