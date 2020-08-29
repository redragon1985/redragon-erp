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
