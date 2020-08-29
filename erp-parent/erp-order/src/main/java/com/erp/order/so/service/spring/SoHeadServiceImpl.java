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
package com.erp.order.so.service.spring;

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
import com.erp.order.so.dao.SoHeadDao;
import com.erp.order.so.dao.model.SoHead;
import com.erp.order.so.dao.model.SoHeadCO;
import com.erp.order.so.service.SoHeadService;
import com.erp.order.so.service.SoLineService;

@Service
@Transactional(rollbackFor=Exception.class)
public class SoHeadServiceImpl implements SoHeadService {

    //注入Dao
    @Autowired
    private SoHeadDao soHeadDao;
    @Autowired
    private SoLineService soLineService;
    
    @Override
    public void insertDataObject(SoHead obj) {
        this.soHeadDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(SoHead obj) {
        this.soHeadDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(SoHead obj) {
        this.soHeadDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(SoHead obj) {
        this.soHeadDao.deleteDataObject(obj);
        this.soLineService.deletetSoLineBySoHeadCode(obj.getSoHeadCode());
    }

    @Override
    public List<SoHead> getDataObjects() {
        return this.soHeadDao.getDataObjects();
    }

    @Override
    public SoHead getDataObject(int id) {
        return this.soHeadDao.getDataObject(id);
    }

    @Override
    public SoHead getDataObject(String code) {
        return this.soHeadDao.getDataObject(code);
    }

    @Override
    public List<SoHead> getDataObjects(SoHeadCO paramObj) {
        return this.soHeadDao.getDataObjects(paramObj);
    }

    @Override
    public List<SoHead> getDataObjects(Pages pages) {
        return this.soHeadDao.getDataObjects(pages);
    }
    
    @Override
    public List<SoHead> getDataObjects(Pages pages, SoHeadCO paramObj) {
        return this.soHeadDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, SoHeadCO paramObj) {
        return this.soHeadDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<SoHead> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, SoHeadCO paramObj) {
        return this.soHeadDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public void updateApproveStatus(String code, String approveStatus) {
        this.soHeadDao.updateApproveStatus(code, approveStatus);
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200)
    public int getSoHeadNum(String startDate, String endDate) {
        return this.soHeadDao.getSoHeadNum(startDate, endDate);
    }
    
}