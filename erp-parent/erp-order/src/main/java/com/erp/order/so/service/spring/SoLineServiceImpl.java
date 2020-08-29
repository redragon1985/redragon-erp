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

import java.math.BigDecimal;
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
import com.erp.order.so.dao.SoLineDao;
import com.erp.order.so.dao.model.SoLine;
import com.erp.order.so.dao.model.SoLineCO;
import com.erp.order.so.service.SoLineService;

@Service
@Transactional(rollbackFor=Exception.class)
public class SoLineServiceImpl implements SoLineService {

    //注入Dao
    @Autowired
    private SoLineDao soLineDao;
    
    @Override
    public void insertDataObject(SoLine obj) {
        this.soLineDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(SoLine obj) {
        this.soLineDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(SoLine obj) {
        this.soLineDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(SoLine obj) {
        this.soLineDao.deleteDataObject(obj);
    }

    @Override
    public List<SoLine> getDataObjects() {
        return this.soLineDao.getDataObjects();
    }

    @Override
    public SoLine getDataObject(int id) {
        return this.soLineDao.getDataObject(id);
    }

    @Override
    public SoLine getDataObject(String code) {
        return this.soLineDao.getDataObject(code);
    }

    @Override
    public List<SoLine> getDataObjects(SoLineCO paramObj) {
        return this.soLineDao.getDataObjects(paramObj);
    }

    @Override
    public List<SoLine> getDataObjects(Pages pages) {
        return this.soLineDao.getDataObjects(pages);
    }
    
    @Override
    public List<SoLine> getDataObjects(Pages pages, SoLineCO paramObj) {
        return this.soLineDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, SoLineCO paramObj) {
        return this.soLineDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<SoLine> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, SoLineCO paramObj) {
        return this.soLineDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public List<SoLine> getSoLineListBySoHeadCode(Pages pages, SoLineCO paramObj) {
        return this.soLineDao.getSoLineListBySoHeadCode(pages, paramObj);
    }
    
    @Override
    public void deletetSoLineBySoHeadCode(String soHeadCode) {
        this.soLineDao.deletetSoLineBySoHeadCode(soHeadCode);
    }
    
    @Override
    public BigDecimal getSoAmount(String soHeadCode) {
        return this.soLineDao.getSoAmount(soHeadCode);
    }
    
}