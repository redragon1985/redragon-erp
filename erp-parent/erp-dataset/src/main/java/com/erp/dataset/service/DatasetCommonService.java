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
package com.erp.dataset.service;

import java.util.Map;

/**
 * @description
 * @date 2020年7月14日
 * @author dongbin
 * 
 */
public interface DatasetCommonService {
    
    //获取国家
    public abstract Map<String, String> getCountry();
    
    //获取城市
    public abstract Map<String, String> getCity();
    
    //获取银行
    public abstract Map<String, String> getBank();
    
    //获取物料类型 此功能被树形结构代替
    //public abstract Map<String, String> getMaterialCategory();
     
    //获取物料单位
    public abstract Map<String, String> getMaterialUnit();
    
    //获取项目类型
    public abstract Map<String, String> getProjectType();
    
    //获取币种
    public abstract Map<String, String> getCurrencyType();
    
    //获取采购订单类型
    public abstract Map<String, String> getPOType();
    
    //获取销售订单类型
    public abstract Map<String, String> getSOType();
    
    //获取计税类型
    public abstract Map<String, String> getTaxType();
    
    //获取付款方式
    public abstract Map<String, String> getPayMode();
    
    //获取凭证字
    public abstract Map<String, String> getVoucherType();
}
