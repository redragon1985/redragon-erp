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