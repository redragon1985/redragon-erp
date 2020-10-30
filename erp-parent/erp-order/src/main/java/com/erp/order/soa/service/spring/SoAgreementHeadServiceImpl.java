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
package com.erp.order.soa.service.spring;

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
import com.erp.order.so.service.SoLineService;
import com.erp.order.soa.dao.SoAgreementHeadDao;
import com.erp.order.soa.dao.SoAgreementLineDao;
import com.erp.order.soa.dao.model.SoAgreementHead;
import com.erp.order.soa.dao.model.SoAgreementHeadCO;
import com.erp.order.soa.service.SoAgreementHeadService;
import com.erp.order.soa.service.SoAgreementLineService;

@Service
@Transactional(rollbackFor=Exception.class)
public class SoAgreementHeadServiceImpl implements SoAgreementHeadService {

    //注入Dao
    @Autowired
    private SoAgreementHeadDao soAgreementHeadDao;
    @Autowired
    private SoAgreementLineService soAgreementLineService;
    @Autowired
    private SoAgreementLineDao soAgreementLineDao;
    
    @Override
    public void insertDataObject(SoAgreementHead obj) {
        this.soAgreementHeadDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(SoAgreementHead obj) {
        this.soAgreementHeadDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(SoAgreementHead obj) {
        this.soAgreementHeadDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(SoAgreementHead obj) {
        this.soAgreementHeadDao.deleteDataObject(obj);
        this.soAgreementLineService.deletetSoLineBySoHeadCode(obj.getSoHeadCode());
    }

    @Override
    public List<SoAgreementHead> getDataObjects() {
        return this.soAgreementHeadDao.getDataObjects();
    }

    @Override
    public SoAgreementHead getDataObject(int id) {
        return this.soAgreementHeadDao.getDataObject(id);
    }

    @Override
    public SoAgreementHead getDataObject(String code) {
        return this.soAgreementHeadDao.getDataObject(code);
    }

    @Override
    public List<SoAgreementHead> getDataObjects(SoAgreementHeadCO paramObj) {
        return this.soAgreementHeadDao.getDataObjects(paramObj);
    }

    @Override
    public List<SoAgreementHead> getDataObjects(Pages pages) {
        return this.soAgreementHeadDao.getDataObjects(pages);
    }
    
    @Override
    public List<SoAgreementHead> getDataObjects(Pages pages, SoAgreementHeadCO paramObj) {
        return this.soAgreementHeadDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, SoAgreementHeadCO paramObj) {
        return this.soAgreementHeadDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<SoAgreementHead> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, SoAgreementHeadCO paramObj) {
        return this.soAgreementHeadDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public void updateApproveStatus(String code, String approveStatus) {
        this.soAgreementHeadDao.updateApproveStatus(code, approveStatus);
        //更新行表版本号
        if(approveStatus.equals("UNSUBMIT")) {
            this.soAgreementLineDao.updateLineForVersion(code);
        }        
    }
    
}