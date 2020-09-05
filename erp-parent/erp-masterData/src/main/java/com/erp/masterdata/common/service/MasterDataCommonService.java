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
    
    //获取所有物料和事项Map
    public abstract Map<String, String> getMaterialMap();
    
    //获取物料Map
    public abstract Map<String, String> getMaterialForMaterialMap();
    
    //获取事项Map
    public abstract Map<String, String> getMaterialForMatterMap();
    
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
