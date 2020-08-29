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
package com.erp.finance.pay.service.spring;

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
import com.erp.finance.pay.dao.PayHeadDao;
import com.erp.finance.pay.dao.model.PayHead;
import com.erp.finance.pay.dao.model.PayHeadCO;
import com.erp.finance.pay.service.PayHeadService;
import com.erp.finance.pay.service.PayLineService;

@Service
@Transactional(rollbackFor=Exception.class)
public class PayHeadServiceImpl implements PayHeadService {

    //注入Dao
    @Autowired
    private PayHeadDao payHeadDao;
    @Autowired
    private PayLineService payLineService;
    
    @Override
    public void insertDataObject(PayHead obj) {
        this.payHeadDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(PayHead obj) {
        this.payHeadDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(PayHead obj) {
        this.payHeadDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(PayHead obj) {
        this.payHeadDao.deleteDataObject(obj);
        this.payLineService.deletePayLineByPayHeadCode(obj.getPayHeadCode());
    }

    @Override
    public List<PayHead> getDataObjects() {
        return this.payHeadDao.getDataObjects();
    }

    @Override
    public PayHead getDataObject(int id) {
        return this.payHeadDao.getDataObject(id);
    }

    @Override
    public PayHead getDataObject(String code) {
        return this.payHeadDao.getDataObject(code);
    }

    @Override
    public List<PayHead> getDataObjects(PayHeadCO paramObj) {
        return this.payHeadDao.getDataObjects(paramObj);
    }

    @Override
    public List<PayHead> getDataObjects(Pages pages) {
        return this.payHeadDao.getDataObjects(pages);
    }
    
    @Override
    public List<PayHead> getDataObjects(Pages pages, PayHeadCO paramObj) {
        return this.payHeadDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, PayHeadCO paramObj) {
        return this.payHeadDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<PayHead> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, PayHeadCO paramObj) {
        return this.payHeadDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public List<PayHead> getPayHeadListForNotCreateVoucher(Pages pages, PayHeadCO paramObj) {
        return this.payHeadDao.getPayHeadListForNotCreateVoucher(pages, paramObj);
    }
    
    @Override
    public void updateApproveStatus(String code, String approveStatus) {
        this.payHeadDao.updateApproveStatus(code, approveStatus);
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200)
    public int getPayHeadNum(String startDate, String endDate) {
        return this.payHeadDao.getPayHeadNum(startDate, endDate);
    }
    
}