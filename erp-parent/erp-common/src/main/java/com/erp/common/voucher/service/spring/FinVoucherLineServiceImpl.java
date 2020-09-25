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
import com.erp.common.voucher.dao.FinVoucherLineDao;
import com.erp.common.voucher.dao.model.FinVoucherLine;
import com.erp.common.voucher.dao.model.FinVoucherLineCO;
import com.erp.common.voucher.service.FinVoucherLineService;

@Service("finVoucherLineServiceCommon")
@Transactional(rollbackFor=Exception.class)
public class FinVoucherLineServiceImpl implements FinVoucherLineService {

    //注入Dao
    @Autowired
    @Qualifier("finVoucherLineDaoCommon")
    private FinVoucherLineDao finVoucherLineDao;
    
    @Override
    public void insertDataObject(FinVoucherLine obj) {
        this.finVoucherLineDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(FinVoucherLine obj) {
        this.finVoucherLineDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(FinVoucherLine obj) {
        this.finVoucherLineDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(FinVoucherLine obj) {
        this.finVoucherLineDao.deleteDataObject(obj);
    }

    @Override
    public List<FinVoucherLine> getDataObjects() {
        return this.finVoucherLineDao.getDataObjects();
    }

    @Override
    public FinVoucherLine getDataObject(int id) {
        return this.finVoucherLineDao.getDataObject(id);
    }

    @Override
    public FinVoucherLine getDataObject(String code) {
        return this.finVoucherLineDao.getDataObject(code);
    }

    @Override
    public List<FinVoucherLine> getDataObjects(FinVoucherLineCO paramObj) {
        return this.finVoucherLineDao.getDataObjects(paramObj);
    }

    @Override
    public List<FinVoucherLine> getDataObjects(Pages pages) {
        return this.finVoucherLineDao.getDataObjects(pages);
    }
    
    @Override
    public List<FinVoucherLine> getDataObjects(Pages pages, FinVoucherLineCO paramObj) {
        return this.finVoucherLineDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, FinVoucherLineCO paramObj) {
        return this.finVoucherLineDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<FinVoucherLine> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, FinVoucherLineCO paramObj) {
        return this.finVoucherLineDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public void deleteFinVoucherLineByVoucherHeadCode(String voucherHeadCode) {
        this.finVoucherLineDao.deleteFinVoucherLineByVoucherHeadCode(voucherHeadCode);
    }
    
}