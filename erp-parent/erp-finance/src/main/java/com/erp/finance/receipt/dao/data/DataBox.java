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
package com.erp.finance.receipt.dao.data;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description
 * @date 2020年7月16日
 * @author dongbin
 * 
 */
public class DataBox {

    //获取收款状态
    public static Map<String, String> getReceiptStatusMap(){
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("NEW", "新建");
        map.put("CONFIRM", "确认");
        map.put("CANCEL", "取消");
        return map;
    }
    
    
    //获取出纳状态
    public static Map<String, String> getReceivedStatusMap(){
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("N", "未收款");
        map.put("Y", "已收款");
        return map;
    }
    
    //获取收款来源类型
    public static Map<String, String> getReceiptSourceType(){
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("SO", "销售订单");
        //bbc 此功能未开放
        //map.put("OUTPUT", "出库单");
        return map;
    }
}
