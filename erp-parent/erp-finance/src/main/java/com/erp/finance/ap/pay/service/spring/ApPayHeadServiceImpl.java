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
import com.erp.finance.ap.pay.dao.ApPayHeadDao;
import com.erp.finance.ap.pay.dao.model.ApPayHead;
import com.erp.finance.ap.pay.dao.model.ApPayHeadCO;
import com.erp.finance.ap.pay.service.ApPayHeadService;
import com.erp.finance.ap.pay.service.ApPayLineService;

@Service
@Transactional(rollbackFor=Exception.class)
public class ApPayHeadServiceImpl implements ApPayHeadService {

    //注入Dao
    @Autowired
    private ApPayHeadDao apPayHeadDao;
    @Autowired
    private ApPayLineService apPayLineService;
    
    @Override
    public void insertDataObject(ApPayHead obj) {
        this.apPayHeadDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(ApPayHead obj) {
        this.apPayHeadDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(ApPayHead obj) {
        this.apPayHeadDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(ApPayHead obj) {
        this.apPayHeadDao.deleteDataObject(obj);
        this.apPayLineService.deleteLineByHeadCode(obj.getPayHeadCode());
    }

    @Override
    public List<ApPayHead> getDataObjects() {
        return this.apPayHeadDao.getDataObjects();
    }

    @Override
    public ApPayHead getDataObject(int id) {
        return this.apPayHeadDao.getDataObject(id);
    }

    @Override
    public ApPayHead getDataObject(String code) {
        return this.apPayHeadDao.getDataObject(code);
    }

    @Override
    public List<ApPayHead> getDataObjects(ApPayHeadCO paramObj) {
        return this.apPayHeadDao.getDataObjects(paramObj);
    }

    @Override
    public List<ApPayHead> getDataObjects(Pages pages) {
        return this.apPayHeadDao.getDataObjects(pages);
    }
    
    @Override
    public List<ApPayHead> getDataObjects(Pages pages, ApPayHeadCO paramObj) {
        return this.apPayHeadDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, ApPayHeadCO paramObj) {
        return this.apPayHeadDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<ApPayHead> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, ApPayHeadCO paramObj) {
        return this.apPayHeadDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public void updateApproveStatus(String code, String approveStatus) {
        this.apPayHeadDao.updateApproveStatus(code, approveStatus);
    }
    
}