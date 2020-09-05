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
package com.erp.masterdata.common.param;

/**
 * @description
 * @date 2020年7月16日
 * @author dongbin
 * 
 */
public class MasterDataParam {
    
    //主数据缓存key
    
    //客户
    public static final String CUSTOMER_CACHE_KEY = "MD_CUSTOMER";
    //供应商
    public static final String VENDOR_CACHE_KEY = "MD_VENDOR";
    
    //客户（我的公司）
    public static final String CUSTOMER_OWN_CACHE_KEY = "MD_CUSTOMER_OWN";
    //供应商（我的公司）
    public static final String VENDOR_OWN_CACHE_KEY = "MD_VENDOR_OWN";
    
    //物料
    public static final String MATERIAL_CACHE_KEY = "MD_MATERIAL";
    public static final String MATERIAL_MATERIAL_CACHE_KEY = "MD_MATERIAL_MATERIAL";
    public static final String MATERIAL_MATTER_CACHE_KEY = "MD_MATERIAL_MATTER";
    
    //项目
    public static final String PROJECT_CACHE_KEY = "MD_PROJECT";
    
    //科目
    public static final String SUBJECT_CACHE_KEY = "MD_SUBJECT";
    
}
