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