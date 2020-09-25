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
package com.erp.common.voucher.service.spring;

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
import com.erp.common.voucher.dao.FinVoucherHeadDao;
import com.erp.common.voucher.dao.FinVoucherLineDao;
import com.erp.common.voucher.dao.model.FinVoucherHead;
import com.erp.common.voucher.dao.model.FinVoucherHeadCO;
import com.erp.common.voucher.dao.model.FinVoucherLine;
import com.erp.common.voucher.service.FinVoucherBillRService;
import com.erp.common.voucher.service.FinVoucherHeadService;

@Service("finVoucherHeadServiceCommon")
@Transactional(rollbackFor=Exception.class)
public class FinVoucherHeadServiceImpl implements FinVoucherHeadService {

    //注入Dao
    @Autowired
    @Qualifier("finVoucherHeadDaoCommon")
    private FinVoucherHeadDao finVoucherHeadDao;
    @Autowired
    @Qualifier("finVoucherLineDaoCommon")
    private FinVoucherLineDao finVoucherLineDao;
    @Autowired
    @Qualifier("finVoucherBillRServiceCommon")
    private FinVoucherBillRService finVoucherBillRService;
    
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
        this.finVoucherLineDao.deleteFinVoucherLineByVoucherHeadCode(obj.getVoucherHeadCode());
        this.finVoucherBillRService.deleteFinVoucherBillRByVoucherHeadCode(obj.getVoucherHeadCode());
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
    public void deleteFinVoucherHeadByVoucherHeadCode(String voucherHeadCode) {
        this.finVoucherHeadDao.deleteFinVoucherHeadByVoucherHeadCode(voucherHeadCode);
        this.finVoucherLineDao.deleteFinVoucherLineByVoucherHeadCode(voucherHeadCode);
        this.finVoucherBillRService.deleteFinVoucherBillRByVoucherHeadCode(voucherHeadCode);
    }
    
}