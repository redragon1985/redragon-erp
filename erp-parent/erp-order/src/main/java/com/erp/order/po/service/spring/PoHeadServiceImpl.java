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
package com.erp.order.po.service.spring;

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
import com.erp.order.po.dao.PoHeadDao;
import com.erp.order.po.dao.model.PoHead;
import com.erp.order.po.dao.model.PoHeadCO;
import com.erp.order.po.service.PoHeadService;

@Service
@Transactional(rollbackFor=Exception.class)
public class PoHeadServiceImpl implements PoHeadService {

    //注入Dao
    @Autowired
    private PoHeadDao poHeadDao;
    
    @Override
    public void insertDataObject(PoHead obj) {
        this.poHeadDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(PoHead obj) {
        this.poHeadDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(PoHead obj) {
        this.poHeadDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(PoHead obj) {
        this.poHeadDao.deleteDataObject(obj);
    }

    @Override
    public List<PoHead> getDataObjects() {
        return this.poHeadDao.getDataObjects();
    }

    @Override
    public PoHead getDataObject(int id) {
        return this.poHeadDao.getDataObject(id);
    }

    @Override
    public PoHead getDataObject(String code) {
        return this.poHeadDao.getDataObject(code);
    }

    @Override
    public List<PoHead> getDataObjects(PoHeadCO paramObj) {
        return this.poHeadDao.getDataObjects(paramObj);
    }

    @Override
    public List<PoHead> getDataObjects(Pages pages) {
        return this.poHeadDao.getDataObjects(pages);
    }
    
    @Override
    public List<PoHead> getDataObjects(Pages pages, PoHeadCO paramObj) {
        return this.poHeadDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, PoHeadCO paramObj) {
        return this.poHeadDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<PoHead> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, PoHeadCO paramObj) {
        return this.poHeadDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public void updateApproveStatus(String code, String approveStatus) {
        this.poHeadDao.updateApproveStatus(code, approveStatus);
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200)
    public int getPoHeadNum(String startDate, String endDate) {
        return this.poHeadDao.getPoHeadNum(startDate, endDate);
    }
    
}