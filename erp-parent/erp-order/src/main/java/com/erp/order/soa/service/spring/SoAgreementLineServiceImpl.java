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
package com.erp.order.soa.service.spring;

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
import com.erp.order.soa.dao.SoAgreementLineDao;
import com.erp.order.soa.dao.model.SoAgreementLine;
import com.erp.order.soa.dao.model.SoAgreementLineCO;
import com.erp.order.soa.service.SoAgreementLineService;

@Service
@Transactional(rollbackFor=Exception.class)
public class SoAgreementLineServiceImpl implements SoAgreementLineService {

    //注入Dao
    @Autowired
    private SoAgreementLineDao soAgreementLineDao;
    
    @Override
    public void insertDataObject(SoAgreementLine obj) {
        this.soAgreementLineDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(SoAgreementLine obj) {
        this.soAgreementLineDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(SoAgreementLine obj) {
        this.soAgreementLineDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(SoAgreementLine obj) {
        this.soAgreementLineDao.deleteDataObject(obj);
    }

    @Override
    public List<SoAgreementLine> getDataObjects() {
        return this.soAgreementLineDao.getDataObjects();
    }

    @Override
    public SoAgreementLine getDataObject(int id) {
        return this.soAgreementLineDao.getDataObject(id);
    }

    @Override
    public SoAgreementLine getDataObject(String code) {
        return this.soAgreementLineDao.getDataObject(code);
    }

    @Override
    public List<SoAgreementLine> getDataObjects(SoAgreementLineCO paramObj) {
        return this.soAgreementLineDao.getDataObjects(paramObj);
    }

    @Override
    public List<SoAgreementLine> getDataObjects(Pages pages) {
        return this.soAgreementLineDao.getDataObjects(pages);
    }
    
    @Override
    public List<SoAgreementLine> getDataObjects(Pages pages, SoAgreementLineCO paramObj) {
        return this.soAgreementLineDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, SoAgreementLineCO paramObj) {
        return this.soAgreementLineDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<SoAgreementLine> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, SoAgreementLineCO paramObj) {
        return this.soAgreementLineDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public void deletetSoLineBySoHeadCode(String soHeadCode) {
        this.soAgreementLineDao.deletetSoLineBySoHeadCode(soHeadCode);
    }
    
    @Override
    public BigDecimal getSoAmount(String soHeadCode) {
        return this.soAgreementLineDao.getSoAmount(soHeadCode);
    }
    
    @Override
    public List<SoAgreementLine> getSoLineListBySoHeadCode(Pages pages, SoAgreementLineCO paramObj) {
        return this.soAgreementLineDao.getSoLineListBySoHeadCode(pages, paramObj);
    }
    
    @Override
    public void updateLineForVersion(String code) {
        this.soAgreementLineDao.updateLineForVersion(code);
    }
    
}