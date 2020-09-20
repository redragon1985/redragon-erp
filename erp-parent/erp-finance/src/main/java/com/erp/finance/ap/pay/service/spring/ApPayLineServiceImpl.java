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
package com.erp.finance.ap.pay.service.spring;

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
import com.erp.finance.ap.invoice.dao.model.ApInvoiceLine;
import com.erp.finance.ap.pay.dao.ApPayLineDao;
import com.erp.finance.ap.pay.dao.model.ApPayLine;
import com.erp.finance.ap.pay.dao.model.ApPayLineCO;
import com.erp.finance.ap.pay.service.ApPayLineService;

@Service
@Transactional(rollbackFor=Exception.class)
public class ApPayLineServiceImpl implements ApPayLineService {

    //注入Dao
    @Autowired
    private ApPayLineDao apPayLineDao;
    
    @Override
    public void insertDataObject(ApPayLine obj) {
        this.apPayLineDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(ApPayLine obj) {
        this.apPayLineDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(ApPayLine obj) {
        this.apPayLineDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(ApPayLine obj) {
        this.apPayLineDao.deleteDataObject(obj);
    }

    @Override
    public List<ApPayLine> getDataObjects() {
        return this.apPayLineDao.getDataObjects();
    }

    @Override
    public ApPayLine getDataObject(int id) {
        return this.apPayLineDao.getDataObject(id);
    }

    @Override
    public ApPayLine getDataObject(String code) {
        return this.apPayLineDao.getDataObject(code);
    }

    @Override
    public List<ApPayLine> getDataObjects(ApPayLineCO paramObj) {
        return this.apPayLineDao.getDataObjects(paramObj);
    }

    @Override
    public List<ApPayLine> getDataObjects(Pages pages) {
        return this.apPayLineDao.getDataObjects(pages);
    }
    
    @Override
    public List<ApPayLine> getDataObjects(Pages pages, ApPayLineCO paramObj) {
        return this.apPayLineDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, ApPayLineCO paramObj) {
        return this.apPayLineDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<ApPayLine> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, ApPayLineCO paramObj) {
        return this.apPayLineDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public List<ApPayLine> getPayLineListByHeadCode(Pages pages, ApPayLineCO paramObj) {
        return this.apPayLineDao.getPayLineListByHeadCode(pages, paramObj);
    }
    
    @Override
    public void deleteLineByHeadCode(String headCode) {
        this.apPayLineDao.deleteLineByHeadCode(headCode);
    }
    
}