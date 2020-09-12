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
import com.erp.finance.pay.dao.ApInvoiceLineDao;
import com.erp.finance.pay.dao.model.ApInvoiceLine;
import com.erp.finance.pay.dao.model.ApInvoiceLineCO;
import com.erp.finance.pay.service.ApInvoiceLineService;

@Service
@Transactional(rollbackFor=Exception.class)
public class ApInvoiceLineServiceImpl implements ApInvoiceLineService {

    //注入Dao
    @Autowired
    private ApInvoiceLineDao payLineDao;
    
    @Override
    public void insertDataObject(ApInvoiceLine obj) {
        this.payLineDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(ApInvoiceLine obj) {
        this.payLineDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(ApInvoiceLine obj) {
        this.payLineDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(ApInvoiceLine obj) {
        this.payLineDao.deleteDataObject(obj);
    }

    @Override
    public List<ApInvoiceLine> getDataObjects() {
        return this.payLineDao.getDataObjects();
    }

    @Override
    public ApInvoiceLine getDataObject(int id) {
        return this.payLineDao.getDataObject(id);
    }

    @Override
    public ApInvoiceLine getDataObject(String code) {
        return this.payLineDao.getDataObject(code);
    }

    @Override
    public List<ApInvoiceLine> getDataObjects(ApInvoiceLineCO paramObj) {
        return this.payLineDao.getDataObjects(paramObj);
    }

    @Override
    public List<ApInvoiceLine> getDataObjects(Pages pages) {
        return this.payLineDao.getDataObjects(pages);
    }
    
    @Override
    public List<ApInvoiceLine> getDataObjects(Pages pages, ApInvoiceLineCO paramObj) {
        return this.payLineDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, ApInvoiceLineCO paramObj) {
        return this.payLineDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<ApInvoiceLine> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, ApInvoiceLineCO paramObj) {
        return this.payLineDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public List<ApInvoiceLine> getApInvoiceLineListByHeadCode(Pages pages, ApInvoiceLineCO paramObj) {
        return this.payLineDao.getApInvoiceLineListByHeadCode(pages, paramObj);
    }
    
    @Override
    public BigDecimal getHISApInvoiceAmountForPO(String poHeadCode, String payHeadCode) {
        return this.payLineDao.getHISApInvoiceAmountForPO(poHeadCode, payHeadCode);
    }
    
    @Override
    public void deleteLineByHeadCode(String headCode) {
        this.payLineDao.deleteLineByHeadCode(headCode);
    }
    
    @Override
    public BigDecimal getApInvoiceAmountByHeadCode(String headCode) {
        return this.payLineDao.getApInvoiceAmountByHeadCode(headCode);
    }
    
}