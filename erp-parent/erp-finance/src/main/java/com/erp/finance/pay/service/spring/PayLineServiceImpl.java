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
package com.erp.finance.pay.service.spring;

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
import com.erp.finance.pay.dao.PayLineDao;
import com.erp.finance.pay.dao.model.PayLine;
import com.erp.finance.pay.dao.model.PayLineCO;
import com.erp.finance.pay.service.PayLineService;

@Service
@Transactional(rollbackFor=Exception.class)
public class PayLineServiceImpl implements PayLineService {

    //注入Dao
    @Autowired
    private PayLineDao payLineDao;
    
    @Override
    public void insertDataObject(PayLine obj) {
        this.payLineDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(PayLine obj) {
        this.payLineDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(PayLine obj) {
        this.payLineDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(PayLine obj) {
        this.payLineDao.deleteDataObject(obj);
    }

    @Override
    public List<PayLine> getDataObjects() {
        return this.payLineDao.getDataObjects();
    }

    @Override
    public PayLine getDataObject(int id) {
        return this.payLineDao.getDataObject(id);
    }

    @Override
    public PayLine getDataObject(String code) {
        return this.payLineDao.getDataObject(code);
    }

    @Override
    public List<PayLine> getDataObjects(PayLineCO paramObj) {
        return this.payLineDao.getDataObjects(paramObj);
    }

    @Override
    public List<PayLine> getDataObjects(Pages pages) {
        return this.payLineDao.getDataObjects(pages);
    }
    
    @Override
    public List<PayLine> getDataObjects(Pages pages, PayLineCO paramObj) {
        return this.payLineDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, PayLineCO paramObj) {
        return this.payLineDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<PayLine> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, PayLineCO paramObj) {
        return this.payLineDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public List<PayLine> getPayLineListByPayHeadCode(Pages pages, PayLineCO paramObj) {
        return this.payLineDao.getPayLineListByPayHeadCode(pages, paramObj);
    }
    
    @Override
    public BigDecimal getHISPayAmountForPO(String poHeadCode, String payHeadCode) {
        return this.payLineDao.getHISPayAmountForPO(poHeadCode, payHeadCode);
    }
    
    @Override
    public void deletePayLineByPayHeadCode(String payHeadCode) {
        this.payLineDao.deletePayLineByPayHeadCode(payHeadCode);
    }
    
    @Override
    public BigDecimal getPayAmountByPayHeadCode(String payHeadCode) {
        return this.payLineDao.getPayAmountByPayHeadCode(payHeadCode);
    }
    
}