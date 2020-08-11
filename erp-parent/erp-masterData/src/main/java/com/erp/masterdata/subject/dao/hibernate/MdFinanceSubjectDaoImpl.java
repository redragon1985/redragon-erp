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
package com.erp.masterdata.subject.dao.hibernate;

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
import com.erp.masterdata.subject.dao.MdFinanceSubjectDao;
import com.erp.masterdata.subject.dao.model.MdFinanceSubject;
import com.erp.masterdata.subject.dao.model.MdFinanceSubjectCO;

@Repository
public class MdFinanceSubjectDaoImpl implements MdFinanceSubjectDao{ 

    //注入DaoSupport工具类
    @Autowired
    private DaoSupport daoSupport;
    
    @Override
    public void insertDataObject(MdFinanceSubject obj) {
        this.daoSupport.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(MdFinanceSubject obj) {
        this.daoSupport.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(MdFinanceSubject obj) {
        this.daoSupport.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(MdFinanceSubject obj) {
        this.daoSupport.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<MdFinanceSubject> getDataObjects() {
        return this.daoSupport.getDataAllObject(MdFinanceSubject.class);
    }

    @Override
    public MdFinanceSubject getDataObject(int id) {
        return (MdFinanceSubject)this.daoSupport.getDataObject(MdFinanceSubject.class, id);
    }
    
    @Override
    public MdFinanceSubject getDataObject(String code) {
        String sql = "select d.* from md_finance_subject d where d.subject_code = :code";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", code);
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("d", MdFinanceSubject.class);
        
        List<MdFinanceSubject> list = this.daoSupport.selectDataSql(sql, entity, args);
        if(list.size()>0) {
            return list.get(0);
        }
        
        return null;
    }
    
    @Override
    public List<MdFinanceSubject> getDataObjects(MdFinanceSubjectCO paramObj) {
        String sql = "select d.* from md_finance_subject d where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
//        sql = sql + DaoUtil.getSQLCondition(paramObj, "", "and d.", args);
        
        sql = sql + " order by d.segment_code";
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("d", MdFinanceSubject.class);
        
        return this.daoSupport.selectDataSql(sql, entity, args);
    }
    
    @Override
    public List<MdFinanceSubject> getDataObjects(Pages pages) {
        return null;
    }
    
    @Override
    public List<MdFinanceSubject> getDataObjects(Pages pages, MdFinanceSubjectCO paramObj) {
        return null;
    }

    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, MdFinanceSubjectCO paramObj) {
        return null;
    }
    
    @Override
    @Permissions(PermissionType.OWN)
    public List<MdFinanceSubject> getDataObjectsForDataAuth(@SqlParam String dataAuthSQL, Pages pages, MdFinanceSubjectCO paramObj) {
        return null;
    }
    
    @Override
    public int getChildSubjectNum(Integer subjectId) {
        String sql = "select count(*) from md_finance_subject where parent_subject_code = (select d.subject_code from md_finance_subject d where d.subject_id = :departmentId)";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("departmentId", subjectId);
        
        List list = this.daoSupport.selectDataSqlCount(sql, args);
        if(list.size()>0) {
            return this.daoSupport.convertSQLCount(list.get(0));
        }
        
        return 0;
    }
    
}
