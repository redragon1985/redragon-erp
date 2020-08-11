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
package com.erp.finance.pay.dao.data;

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

    //获取付款状态
    public static Map<String, String> getPayStatusMap(){
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("NEW", "新建");
        map.put("CONFIRM", "确认");
        map.put("CANCEL", "取消");
        return map;
    }
    
    
    //获取出纳状态
    public static Map<String, String> getPaidStatusMap(){
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("N", "未付款");
        map.put("Y", "已付款");
        return map;
    }
    
    //获取付款来源类型
    public static Map<String, String> getPaySourceType(){
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("PO", "采购订单");
        //bbc 此功能未开放
        //map.put("INPUT", "入库单");
        return map;
    }
}
