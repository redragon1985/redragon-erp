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
import com.erp.finance.voucher.dao.FinVoucherModelLineDao;
import com.erp.finance.voucher.dao.model.FinVoucherModelLine;
import com.erp.finance.voucher.dao.model.FinVoucherModelLineCO;
import com.erp.finance.voucher.service.FinVoucherModelLineService;

@Service
@Transactional(rollbackFor=Exception.class)
public class FinVoucherModelLineServiceImpl implements FinVoucherModelLineService {

    //注入Dao
    @Autowired
    private FinVoucherModelLineDao finVoucherModelLineDao;
    
    @Override
    public void insertDataObject(FinVoucherModelLine obj) {
        this.finVoucherModelLineDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(FinVoucherModelLine obj) {
        this.finVoucherModelLineDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(FinVoucherModelLine obj) {
        this.finVoucherModelLineDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(FinVoucherModelLine obj) {
        this.finVoucherModelLineDao.deleteDataObject(obj);
    }

    @Override
    public List<FinVoucherModelLine> getDataObjects() {
        return this.finVoucherModelLineDao.getDataObjects();
    }

    @Override
    public FinVoucherModelLine getDataObject(int id) {
        return this.finVoucherModelLineDao.getDataObject(id);
    }

    @Override
    public FinVoucherModelLine getDataObject(String code) {
        return this.finVoucherModelLineDao.getDataObject(code);
    }

    @Override
    public List<FinVoucherModelLine> getDataObjects(FinVoucherModelLineCO paramObj) {
        return this.finVoucherModelLineDao.getDataObjects(paramObj);
    }

    @Override
    public List<FinVoucherModelLine> getDataObjects(Pages pages) {
        return this.finVoucherModelLineDao.getDataObjects(pages);
    }
    
    @Override
    public List<FinVoucherModelLine> getDataObjects(Pages pages, FinVoucherModelLineCO paramObj) {
        return this.finVoucherModelLineDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, FinVoucherModelLineCO paramObj) {
        return this.finVoucherModelLineDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<FinVoucherModelLine> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, FinVoucherModelLineCO paramObj) {
        return this.finVoucherModelLineDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public void deleteFinVoucherModelLineByVoucherHeadCode(String voucherHeadCode) {
        this.finVoucherModelLineDao.deleteFinVoucherModelLineByVoucherHeadCode(voucherHeadCode);
    }
    
    @Override
    public List<FinVoucherModelLine> getFinVoucherModelLineListByVoucherHeadCode(String voucherHeadCode) {
        return this.finVoucherModelLineDao.getFinVoucherModelLineListByVoucherHeadCode(voucherHeadCode);
    }
    
}