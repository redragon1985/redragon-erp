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
import com.erp.order.poa.dao.PoAgreementLineDao;
import com.erp.order.poa.dao.model.PoAgreementLine;
import com.erp.order.poa.dao.model.PoAgreementLineCO;
import com.erp.order.poa.service.PoAgreementLineService;

@Service
@Transactional(rollbackFor=Exception.class)
public class PoAgreementLineServiceImpl implements PoAgreementLineService {

    //注入Dao
    @Autowired
    private PoAgreementLineDao poAgreementLineDao;
    
    @Override
    public void insertDataObject(PoAgreementLine obj) {
        this.poAgreementLineDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(PoAgreementLine obj) {
        this.poAgreementLineDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(PoAgreementLine obj) {
        this.poAgreementLineDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(PoAgreementLine obj) {
        this.poAgreementLineDao.deleteDataObject(obj);
    }

    @Override
    public List<PoAgreementLine> getDataObjects() {
        return this.poAgreementLineDao.getDataObjects();
    }

    @Override
    public PoAgreementLine getDataObject(int id) {
        return this.poAgreementLineDao.getDataObject(id);
    }

    @Override
    public PoAgreementLine getDataObject(String code) {
        return this.poAgreementLineDao.getDataObject(code);
    }

    @Override
    public List<PoAgreementLine> getDataObjects(PoAgreementLineCO paramObj) {
        return this.poAgreementLineDao.getDataObjects(paramObj);
    }

    @Override
    public List<PoAgreementLine> getDataObjects(Pages pages) {
        return this.poAgreementLineDao.getDataObjects(pages);
    }
    
    @Override
    public List<PoAgreementLine> getDataObjects(Pages pages, PoAgreementLineCO paramObj) {
        return this.poAgreementLineDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, PoAgreementLineCO paramObj) {
        return this.poAgreementLineDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<PoAgreementLine> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, PoAgreementLineCO paramObj) {
        return this.poAgreementLineDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public void deletetPoLineByPoHeadCode(String poHeadCode) {
        this.poAgreementLineDao.deletetPoLineByPoHeadCode(poHeadCode);
    }
    
    @Override
    public BigDecimal getPoAmount(String poHeadCode) {
        return this.poAgreementLineDao.getPoAmount(poHeadCode);
    }
    
    @Override
    public List<PoAgreementLine> getPoLineListByPoHeadCode(Pages pages, PoAgreementLineCO paramObj) {
        return this.poAgreementLineDao.getPoLineListByPoHeadCode(pages, paramObj);
    }
    
}