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
import com.erp.common.voucher.dao.FinVoucherModelLineDao;
import com.erp.common.voucher.dao.model.FinVoucherModelLine;
import com.erp.common.voucher.dao.model.FinVoucherModelLineCO;
import com.erp.common.voucher.service.FinVoucherModelLineService;

@Service("finVoucherModelLineServiceCommon")
@Transactional(rollbackFor=Exception.class)
public class FinVoucherModelLineServiceImpl implements FinVoucherModelLineService {

    //注入Dao
    @Autowired
    @Qualifier("finVoucherModelLineDaoCommon")
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
    public List<FinVoucherModelLine> getFinVoucherModelLineListByVoucherHeadCode(String voucherHeadCode) {
        return this.finVoucherModelLineDao.getFinVoucherModelLineListByVoucherHeadCode(voucherHeadCode);
    }
    
}