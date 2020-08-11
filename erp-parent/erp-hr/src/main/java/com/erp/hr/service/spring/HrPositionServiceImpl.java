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
package com.erp.hr.service.spring;

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
import com.erp.hr.dao.HrPositionDao;
import com.erp.hr.dao.model.HrPosition;
import com.erp.hr.dao.model.HrPositionCO;
import com.erp.hr.service.HrPositionService;

@Service
@Transactional(rollbackFor=Exception.class)
public class HrPositionServiceImpl implements HrPositionService {

    //注入Dao
    @Autowired
    private HrPositionDao hrPositionDao;
    
    @Override
    public void insertDataObject(HrPosition obj) {
        this.hrPositionDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(HrPosition obj) {
        this.hrPositionDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(HrPosition obj) {
        this.hrPositionDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(HrPosition obj) {
        this.hrPositionDao.deleteDataObject(obj);
    }

    @Override
    public List<HrPosition> getDataObjects() {
        return this.hrPositionDao.getDataObjects();
    }

    @Override
    public HrPosition getDataObject(int id) {
        return this.hrPositionDao.getDataObject(id);
    }

    @Override
    public HrPosition getDataObject(String code) {
        return this.hrPositionDao.getDataObject(code);
    }

    @Override
    public List<HrPosition> getDataObjects(HrPositionCO paramObj) {
        return this.hrPositionDao.getDataObjects(paramObj);
    }

    @Override
    public List<HrPosition> getDataObjects(Pages pages) {
        return this.hrPositionDao.getDataObjects(pages);
    }
    
    @Override
    public List<HrPosition> getDataObjects(Pages pages, HrPositionCO paramObj) {
        return this.hrPositionDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, HrPositionCO paramObj) {
        return this.hrPositionDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<HrPosition> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, HrPositionCO paramObj) {
        return this.hrPositionDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public boolean isExistRelateDataForHrPosition(String positionCode) {
        return this.hrPositionDao.isExistRelateDataForHrPosition(positionCode);
    }
    
}