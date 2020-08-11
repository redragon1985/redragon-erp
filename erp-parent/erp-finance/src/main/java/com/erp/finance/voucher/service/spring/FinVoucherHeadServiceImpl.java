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
package com.erp.finance.voucher.service.spring;

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
import com.erp.finance.voucher.dao.FinVoucherHeadDao;
import com.erp.finance.voucher.dao.FinVoucherLineDao;
import com.erp.finance.voucher.dao.model.FinVoucherHead;
import com.erp.finance.voucher.dao.model.FinVoucherHeadCO;
import com.erp.finance.voucher.dao.model.FinVoucherLine;
import com.erp.finance.voucher.service.FinVoucherHeadService;

@Service
@Transactional(rollbackFor=Exception.class)
public class FinVoucherHeadServiceImpl implements FinVoucherHeadService {

    //注入Dao
    @Autowired
    private FinVoucherHeadDao finVoucherHeadDao;
    @Autowired
    private FinVoucherLineDao finVoucherLineDao;
    
    @Override
    public void insertDataObject(FinVoucherHead obj) {
        this.finVoucherHeadDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(FinVoucherHead obj) {
        this.finVoucherHeadDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(FinVoucherHead obj) {
        this.finVoucherHeadDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(FinVoucherHead obj) {
        this.finVoucherHeadDao.deleteDataObject(obj);
    }

    @Override
    public List<FinVoucherHead> getDataObjects() {
        return this.finVoucherHeadDao.getDataObjects();
    }

    @Override
    public FinVoucherHead getDataObject(int id) {
        return this.finVoucherHeadDao.getDataObject(id);
    }

    @Override
    public FinVoucherHead getDataObject(String code) {
        return this.finVoucherHeadDao.getDataObject(code);
    }

    @Override
    public List<FinVoucherHead> getDataObjects(FinVoucherHeadCO paramObj) {
        return this.finVoucherHeadDao.getDataObjects(paramObj);
    }

    @Override
    public List<FinVoucherHead> getDataObjects(Pages pages) {
        return this.finVoucherHeadDao.getDataObjects(pages);
    }
    
    @Override
    public List<FinVoucherHead> getDataObjects(Pages pages, FinVoucherHeadCO paramObj) {
        return this.finVoucherHeadDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, FinVoucherHeadCO paramObj) {
        return this.finVoucherHeadDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<FinVoucherHead> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, FinVoucherHeadCO paramObj) {
        return this.finVoucherHeadDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public void insertOrUpdateFinVoucher(FinVoucherHead finVoucherHead, List<FinVoucherLine> finVoucherLineList) {
        //编辑头
        this.finVoucherHeadDao.insertOrUpdateDataObject(finVoucherHead);
        //循环编辑行
        for(FinVoucherLine finVoucherLine: finVoucherLineList) {
            this.finVoucherLineDao.insertOrUpdateDataObject(finVoucherLine);
        }
    }
    
    @Override
    public void updateFinVoucherHeadForApproveStatus(Integer voucherHeadId, String status) {
        this.finVoucherHeadDao.updateFinVoucherHeadForStatus(voucherHeadId, status);
    }
    
    @Override
    public void updateFinVoucherHeadForStatus(Integer voucherHeadId, String approveStatus) {
        this.finVoucherHeadDao.updateFinVoucherHeadForApproveStatus(voucherHeadId, approveStatus);
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200)
    public int getVoucherHeadNum(String startDate, String endDate) {
        return this.finVoucherHeadDao.getVoucherHeadNum(startDate, endDate);
    }
    
}