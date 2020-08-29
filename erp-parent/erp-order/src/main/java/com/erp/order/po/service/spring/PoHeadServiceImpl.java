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
import com.erp.order.po.service.PoLineService;

@Service
@Transactional(rollbackFor=Exception.class)
public class PoHeadServiceImpl implements PoHeadService {

    //注入Dao
    @Autowired
    private PoHeadDao poHeadDao;
    @Autowired
    private PoLineService poLineService;
    
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
        this.poLineService.deletetPoLineByPoHeadCode(obj.getPoHeadCode());
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