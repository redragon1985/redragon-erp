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
package com.erp.masterdata.subject.dao.hibernate;

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
import com.erp.masterdata.subject.dao.MdFinanceSubjectDao;
import com.erp.masterdata.subject.dao.model.MdFinanceSubject;
import com.erp.masterdata.subject.dao.model.MdFinanceSubjectCO;

@Repository
public class MdFinanceSubjectDaoImpl implements MdFinanceSubjectDao{ 

    //注入basicDao工具类
    @Autowired
    private BasicDao basicDao;
    
    @Override
    public void insertDataObject(MdFinanceSubject obj) {
        this.basicDao.insertDataTransaction(obj);
    }

    @Override
    public void updateDataObject(MdFinanceSubject obj) {
        this.basicDao.updateDataTransaction(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(MdFinanceSubject obj) {
        this.basicDao.insertOrUpdateDataTransaction(obj);
    }

    @Override
    public void deleteDataObject(MdFinanceSubject obj) {
        this.basicDao.deleteDataTransactionJPA(obj);
    }

    @Override
    public List<MdFinanceSubject> getDataObjects() {
        return this.basicDao.getDataAllObject(MdFinanceSubject.class);
    }

    @Override
    public MdFinanceSubject getDataObject(int id) {
        return (MdFinanceSubject)this.basicDao.getDataObject(MdFinanceSubject.class, id);
    }
    
    @Override
    public MdFinanceSubject getDataObject(String code) {
        String sql = "select d.* from md_finance_subject d where d.subject_code = :code";
        
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("code", code);
        
        Map<String, Class<?>> entity = new HashMap<String, Class<?>>();
        entity.put("d", MdFinanceSubject.class);
        
        List<MdFinanceSubject> list = this.basicDao.selectData(sql, entity, args);
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
        
        return this.basicDao.selectData(sql, entity, args);
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
        
        List list = this.basicDao.selectDataSqlCount(sql, args);
        if(list.size()>0) {
            return this.basicDao.convertSQLCount(list.get(0));
        }
        
        return 0;
    }
    
}
