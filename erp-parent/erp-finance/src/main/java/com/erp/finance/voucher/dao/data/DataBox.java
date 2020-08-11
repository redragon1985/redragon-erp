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
package com.erp.finance.voucher.dao.data;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description
 * @date 2020年7月29日
 * @author dongbin
 * 
 */
public class DataBox {
    
    //获取凭证业务类型
    public static Map<String, String> getVoucherBusinessType(){
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("CUSTOM", "自定义");
        map.put("PAY", "付款单(系统默认)");
        map.put("RECEIPT", "收款单(系统默认)");
        map.put("INPUT", "入库单(系统默认)");
        map.put("OUTPUT", "出库单(系统默认)");
        return map;
    }

}
