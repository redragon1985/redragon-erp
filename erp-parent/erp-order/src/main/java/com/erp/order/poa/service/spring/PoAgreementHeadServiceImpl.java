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
package com.erp.order.poa.service.spring;

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
import com.erp.order.po.dao.PoLineDao;
import com.erp.order.po.service.PoLineService;
import com.erp.order.poa.dao.PoAgreementHeadDao;
import com.erp.order.poa.dao.PoAgreementLineDao;
import com.erp.order.poa.dao.model.PoAgreementHead;
import com.erp.order.poa.dao.model.PoAgreementHeadCO;
import com.erp.order.poa.service.PoAgreementHeadService;
import com.erp.order.poa.service.PoAgreementLineService;

@Service
@Transactional(rollbackFor=Exception.class)
public class PoAgreementHeadServiceImpl implements PoAgreementHeadService {

    //注入Dao
    @Autowired
    private PoAgreementHeadDao poAgreementHeadDao;
    @Autowired
    private PoAgreementLineService poAgreementLineService;
    @Autowired
    private PoAgreementLineDao poAgreementLineDao;
    
    @Override
    public void insertDataObject(PoAgreementHead obj) {
        this.poAgreementHeadDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(PoAgreementHead obj) {
        this.poAgreementHeadDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(PoAgreementHead obj) {
        this.poAgreementHeadDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(PoAgreementHead obj) {
        this.poAgreementHeadDao.deleteDataObject(obj);
        this.poAgreementLineService.deletetPoLineByPoHeadCode(obj.getPoHeadCode());
    }

    @Override
    public List<PoAgreementHead> getDataObjects() {
        return this.poAgreementHeadDao.getDataObjects();
    }

    @Override
    public PoAgreementHead getDataObject(int id) {
        return this.poAgreementHeadDao.getDataObject(id);
    }

    @Override
    public PoAgreementHead getDataObject(String code) {
        return this.poAgreementHeadDao.getDataObject(code);
    }

    @Override
    public List<PoAgreementHead> getDataObjects(PoAgreementHeadCO paramObj) {
        return this.poAgreementHeadDao.getDataObjects(paramObj);
    }

    @Override
    public List<PoAgreementHead> getDataObjects(Pages pages) {
        return this.poAgreementHeadDao.getDataObjects(pages);
    }
    
    @Override
    public List<PoAgreementHead> getDataObjects(Pages pages, PoAgreementHeadCO paramObj) {
        return this.poAgreementHeadDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, PoAgreementHeadCO paramObj) {
        return this.poAgreementHeadDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<PoAgreementHead> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, PoAgreementHeadCO paramObj) {
        return this.poAgreementHeadDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public void updateApproveStatus(String code, String approveStatus) {
        this.poAgreementHeadDao.updateApproveStatus(code, approveStatus);
        //更新行表版本号
        if(approveStatus.equals("UNSUBMIT")) {
            this.poAgreementLineDao.updateLineForVersion(code);
        }
    }
    
}