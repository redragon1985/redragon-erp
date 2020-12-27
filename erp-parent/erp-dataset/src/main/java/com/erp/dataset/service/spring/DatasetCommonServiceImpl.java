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
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200, cacheKey=DatasetParam.POA_TYPE_KEY)
    public Map<String, String> getPOAType() {
        return this.sysDatasetDao.getDatasetMap("poa_type");
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200, cacheKey=DatasetParam.SOA_TYPE_KEY)
    public Map<String, String> getSOAType() {
        return this.sysDatasetDao.getDatasetMap("soa_type");
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
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200, cacheKey=DatasetParam.PROD_RESOURCE_CHARGE_TYPE_KEY)
    public Map<String, String> getProdResourceChargeType() {
        return this.sysDatasetDao.getDatasetMap("prod_resource_charge_type");
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200, cacheKey=DatasetParam.PROD_RESOURCE_COUNT_TYPE_KEY)
    public Map<String, String> getProdResourceCountType() {
        return this.sysDatasetDao.getDatasetMap("prod_resource_count_type");
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200, cacheKey=DatasetParam.PROD_RESOURCE_TYPE_KEY)
    public Map<String, String> getProdResourceType() {
        return this.sysDatasetDao.getDatasetMap("prod_resource_type");
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200, cacheKey=DatasetParam.PROD_WORK_CENTER_TYPE_KEY)
    public Map<String, String> getProdWorkCenterType() {
        return this.sysDatasetDao.getDatasetMap("prod_work_center_type");
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200, cacheKey=DatasetParam.PROD_WORK_SCHEDULE_KEY)
    public Map<String, String> getProdWorkSchedule() {
        return this.sysDatasetDao.getDatasetMap("prod_work_schedule");
    }
    
    @Override
    @Cache(cacheType=CacheType.ALL, cacheSeconds=7200, cacheKey=DatasetParam.EXPENSE_ITEM_TYPE_KEY)
    public Map<String, String> getExpenseItemType() {
        return this.sysDatasetDao.getDatasetMap("expense_item_type");
    }
    
}
