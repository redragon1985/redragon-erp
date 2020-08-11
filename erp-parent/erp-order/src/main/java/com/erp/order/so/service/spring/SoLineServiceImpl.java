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
package com.erp.order.so.service.spring;

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
import com.erp.order.so.dao.SoLineDao;
import com.erp.order.so.dao.model.SoLine;
import com.erp.order.so.dao.model.SoLineCO;
import com.erp.order.so.service.SoLineService;

@Service
@Transactional(rollbackFor=Exception.class)
public class SoLineServiceImpl implements SoLineService {

    //注入Dao
    @Autowired
    private SoLineDao soLineDao;
    
    @Override
    public void insertDataObject(SoLine obj) {
        this.soLineDao.insertDataObject(obj);
    }

    @Override
    public void updateDataObject(SoLine obj) {
        this.soLineDao.updateDataObject(obj);
    }
    
    @Override
    public void insertOrUpdateDataObject(SoLine obj) {
        this.soLineDao.insertOrUpdateDataObject(obj);
    }

    @Override
    public void deleteDataObject(SoLine obj) {
        this.soLineDao.deleteDataObject(obj);
    }

    @Override
    public List<SoLine> getDataObjects() {
        return this.soLineDao.getDataObjects();
    }

    @Override
    public SoLine getDataObject(int id) {
        return this.soLineDao.getDataObject(id);
    }

    @Override
    public SoLine getDataObject(String code) {
        return this.soLineDao.getDataObject(code);
    }

    @Override
    public List<SoLine> getDataObjects(SoLineCO paramObj) {
        return this.soLineDao.getDataObjects(paramObj);
    }

    @Override
    public List<SoLine> getDataObjects(Pages pages) {
        return this.soLineDao.getDataObjects(pages);
    }
    
    @Override
    public List<SoLine> getDataObjects(Pages pages, SoLineCO paramObj) {
        return this.soLineDao.getDataObjects(pages, paramObj);
    }
    
    @Override
    public List<Map<String, Object>> getDataObjectsArray(Pages pages, SoLineCO paramObj) {
        return this.soLineDao.getDataObjectsArray(pages, paramObj);
    }
    
    @Override
    public List<SoLine> getDataObjectsForDataAuth(String dataAuthSQL, Pages pages, SoLineCO paramObj) {
        return this.soLineDao.getDataObjectsForDataAuth(dataAuthSQL, pages, paramObj);
    }
    
    @Override
    public List<SoLine> getSoLineListBySoHeadCode(Pages pages, SoLineCO paramObj) {
        return this.soLineDao.getSoLineListBySoHeadCode(pages, paramObj);
    }
    
    @Override
    public void deletetSoLineBySoHeadCode(String soHeadCode) {
        this.soLineDao.deletetSoLineBySoHeadCode(soHeadCode);
    }
    
    @Override
    public BigDecimal getSoAmount(String soHeadCode) {
        return this.soLineDao.getSoAmount(soHeadCode);
    }
    
}