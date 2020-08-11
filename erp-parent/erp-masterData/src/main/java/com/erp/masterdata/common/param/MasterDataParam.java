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
    
    //项目
    public static final String PROJECT_CACHE_KEY = "MD_PROJECT";
    
    //科目
    public static final String SUBJECT_CACHE_KEY = "MD_SUBJECT";
    
}
