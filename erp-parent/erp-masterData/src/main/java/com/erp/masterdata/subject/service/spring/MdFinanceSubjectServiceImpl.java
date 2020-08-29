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
package com.erp.masterdata.subject.service.spring;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.framework.annotation.Cache;
import com.framework.annotation.Cache.CacheType;
import com.framework.annotation.Log;
import com.framework.dao.model.Pages;
import com.framework.util.EhcacheUtil;
import com.framework.util.RedisUtil;
import com.erp.masterdata.common.param.MasterDataParam;
import com.erp.masterdata.subject.dao.MdFinanceSubjectDao;
import com.erp.masterdata.subject.dao.model.MdFinanceSubject;
import com.erp.masterdata.subject.dao.model.MdFinanceSubjectCO;
import com.erp.masterdata.subject.service.MdFinanceSubjectService;

@Service
@Transactional(rollbackFor=Exception.class)
public class MdFinanceSubjectServiceImpl implements MdFinanceSubjectService {

    //注入Dao
    @Autowired
    private MdFinanceSubjectDao mdFinanceSubjectDao;
    
    @Override
    public void insertDataObject(MdFinanceSubject obj) {
        this.mdFinanceSubjectDao.insertDataObject(obj);
        //清除缓存
        EhcacheUtil.clear(MasterDataParam.SUBJECT_CACHE_KEY);
        RedisUtil.clear(MasterDataParam.SUBJECT_CACHE_KEY);
    }

    @Override
    public void updateDataObject(MdFinanceSubject obj) {
        this.mdFinanceSubjectDao.updateDataObject(obj);
        //清除缓存
        EhcacheUtil.clear(MasterDataParam.SUBJECT_CACHE_KEY);
        RedisUtil.clear(MasterDataParam.SUBJECT_CACHE_KEY);
    }
    
    @Override
    public void insertOrUpdateDataObject(MdFinanceSubject obj) {
        this.mdFinanceSubjectDao.insertOrUpdateDataObject(obj);
        //清除缓存
        EhcacheUtil.clear(MasterDataParam.SUBJECT_CACHE_KEY);
        RedisUtil.clear(MasterDataParam.SUBJECT_CACHE_KEY);
    }

    @Override
    public void deleteDataObject(MdFinanceSubject obj) {
        this.mdFinanceSubjectDao.deleteDataObject(obj);
        //清除缓存
        EhcacheUtil.clear(MasterDataParam.SUBJECT_CACHE_KEY);
        RedisUtil.clear(MasterDataParam.SUBJECT_CACHE_KEY);
    }

    @Override
    public List<MdFinanceSubject> getDataObjects() {
        return this.mdFinanceSubjectDao.getDataObjects();
    }

    @Override
    public MdFinanceSubject getDataObject(int id) {
        return this.mdFinanceSubjectDao.getDataObject(id);
    }

    @Override
    public MdFinanceSubject getDataObject(String code) {
        return this.mdFinanceSubjectDao.getDataObject(code);
    }

    @Override
    public List<MdFinanceSubject> getDataObjects(MdFinanceSubjectCO paramObj) {
        return this.mdFinanceSubjectDao.getDataObjects(paramObj);
    }

    @Override
    public List<MdFinanceSubject> getDataObjects(Pages pages) {
        return this.mdFinanceSubjectDao.getDataObjects(pages);
    }
    
    @Override
    public List<MdFinanceSubject> getDataObjects(Pages pages, MdFinanceSubjectCO paramObj) {
        return this.mdFinanceSubjectDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, MdFinanceSubjectCO paramObj) {
        return this.mdFinanceSubjectDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<MdFinanceSubject> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, MdFinanceSubjectCO paramObj) {
        return this.mdFinanceSubjectDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public int getChildSubjectNum(Integer subjectId) {
        return this.mdFinanceSubjectDao.getChildSubjectNum(subjectId);
    }
    
}