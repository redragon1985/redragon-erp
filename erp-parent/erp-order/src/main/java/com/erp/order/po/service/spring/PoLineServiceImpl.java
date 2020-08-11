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
package com.erp.order.po.service.spring;

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
import com.erp.order.po.dao.PoLineDao;
import com.erp.order.po.dao.model.PoLine;
import com.erp.order.po.dao.model.PoLineCO;
import com.erp.order.po.service.PoLineService;

@Service
@Transactional(rollbackFor=Exception.class)
public class PoLineServiceImpl implements PoLineService {

    //注入Dao
    @Autowired
    private PoLineDao poLineDao;
    
    @Override
    public void insertDataObject(PoLine obj) {
        this.poLineDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(PoLine obj) {
        this.poLineDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(PoLine obj) {
        this.poLineDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(PoLine obj) {
        this.poLineDao.deleteDataObject(obj);
    }

    @Override
    public List<PoLine> getDataObjects() {
        return this.poLineDao.getDataObjects();
    }

    @Override
    public PoLine getDataObject(int id) {
        return this.poLineDao.getDataObject(id);
    }

    @Override
    public PoLine getDataObject(String code) {
        return this.poLineDao.getDataObject(code);
    }

    @Override
    public List<PoLine> getDataObjects(PoLineCO paramObj) {
        return this.poLineDao.getDataObjects(paramObj);
    }

    @Override
    public List<PoLine> getDataObjects(Pages pages) {
        return this.poLineDao.getDataObjects(pages);
    }
    
    @Override
    public List<PoLine> getDataObjects(Pages pages, PoLineCO paramObj) {
        return this.poLineDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, PoLineCO paramObj) {
        return this.poLineDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<PoLine> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, PoLineCO paramObj) {
        return this.poLineDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public List<PoLine> getPoLineListByPoHeadCode(Pages pages, PoLineCO paramObj) {
        return this.poLineDao.getPoLineListByPoHeadCode(pages, paramObj);
    }
    
    @Override
    public void deletetPoLineByPoHeadCode(String poHeadCode) {
        this.poLineDao.deletetPoLineByPoHeadCode(poHeadCode);
    }
    
    @Override
    public BigDecimal getPoAmount(String poHeadCode) {
        return this.poLineDao.getPoAmount(poHeadCode);
    }
}