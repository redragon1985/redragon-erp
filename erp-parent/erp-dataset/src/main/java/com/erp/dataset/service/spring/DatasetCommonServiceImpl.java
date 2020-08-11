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
package com.erp.dataset.service.spring;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.dataset.dao.SysDatasetDao;
import com.erp.dataset.param.DatasetParam;
import com.erp.dataset.service.DatasetCommonService;
import com.framework.annotation.Cache;
import com.framework.annotation.Cache.CacheType;

/**
 * @description
 * @date 2020年7月14日
 * @author dongbin
 * 
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class DatasetCommonServiceImpl implements DatasetCommonService {

    //注入Dao
    @Autowired
    private SysDatasetDao sysDatasetDao;
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200, cacheKey=DatasetParam.BANK_CACHE_KEY)
    public Map<String, String> getBank() {
        return this.sysDatasetDao.getDatasetMap("bank");
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200, cacheKey=DatasetParam.CITY_CACHE_KEY)
    public Map<String, String> getCity() {
        return this.sysDatasetDao.getDatasetMap("city");
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200, cacheKey=DatasetParam.COUNTRY_CACHE_KEY)
    public Map<String, String> getCountry() {
        return this.sysDatasetDao.getDatasetMap("country");
    }
    
//    @Override
//    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200, cacheKey=DatasetParam.MATERIAL_CATEGORY_KEY)
//    @Deprecated
//    public Map<String, String> getMaterialCategory() {
//        return this.sysDatasetDao.getDatasetMap("material_category");
//    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200, cacheKey=DatasetParam.MATERIAL_UNIT_KEY)
    public Map<String, String> getMaterialUnit() {
        return this.sysDatasetDao.getDatasetMap("material_unit");
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200, cacheKey=DatasetParam.PROJECT_TYPE_KEY)
    public Map<String, String> getProjectType() {
        return this.sysDatasetDao.getDatasetMap("project_type");
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200, cacheKey=DatasetParam.CURRENCY_KEY)
    public Map<String, String> getCurrencyType() {
        return this.sysDatasetDao.getDatasetMap("currency");
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200, cacheKey=DatasetParam.PO_TYPE_KEY)
    public Map<String, String> getPOType() {
        return this.sysDatasetDao.getDatasetMap("po_type");
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200, cacheKey=DatasetParam.SO_TYPE_KEY)
    public Map<String, String> getSOType() {
        return this.sysDatasetDao.getDatasetMap("so_type");
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200, cacheKey=DatasetParam.TAX_TYPE_KEY)
    public Map<String, String> getTaxType() {
        return this.sysDatasetDao.getDatasetMap("tax_type");
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200, cacheKey=DatasetParam.PAY_MODE_KEY)
    public Map<String, String> getPayMode() {
        return this.sysDatasetDao.getDatasetMap("pay_mode");
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200, cacheKey=DatasetParam.VOUCHER_TYPE_KEY)
    public Map<String, String> getVoucherType() {
        return this.sysDatasetDao.getDatasetMap("voucher_type");
    }
    
}
