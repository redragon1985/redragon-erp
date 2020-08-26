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
package com.erp.masterdata.common.service;

import java.util.Map;

import com.erp.masterdata.customer.dao.model.MdCustomer;
import com.erp.masterdata.material.dao.model.MdMaterial;
import com.erp.masterdata.vendor.dao.model.MdVendor;

/**
 * @description
 * @date 2020年7月16日
 * @author dongbin
 * 
 */
public interface MasterDataCommonService {
    
    //获取客户Map
    public abstract Map<String, String> getCustomerMap();
    
    //获取供应商Map
    public abstract Map<String, String> getVendorMap();
    
    //获取客户Map(本公司)
    public abstract Map<String, String> getOwnCustomerMap();
    
    //获取供应商Map(本公司)
    public abstract Map<String, String> getOwnVendorMap();
    
    //获取物料Map
    public abstract Map<String, String> getMaterialMap();
    
    //获取项目Map
    public abstract Map<String, String> getProjectMap();
    
    //获取科目Map
    public abstract Map<String, String> getSubjectMap();
    
    //获取物料信息
    public abstract MdMaterial getMdMaterialInfoCache(String materialCode);
    
    //获取供应商信息
    public abstract MdVendor getMdVendorInfoCache(String vendorCode);
    
    //获取客户信息
    public abstract MdCustomer getMdCustomerInfoCache(String customerCode);
}
