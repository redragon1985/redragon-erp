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
package com.erp.order.po.dao.data;

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

    //获取采购订单状态
    public static Map<String, String> getPoStatusMap(){
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("NEW", "新建");
        map.put("CONFIRM", "确认");
        map.put("CANCEL", "取消");
        return map;
    }
    
    //获取接收状态
    public static Map<String, String> getPoReceiveStatusMap(){
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("N", "未入库");
        map.put("Y", "已入库");
        map.put("PART", "部分入库");
        return map;
    }
    
    //获取付款状态
    public static Map<String, String> getPoPaymentStatusMap(){
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("N", "未付款");
        map.put("Y", "已付款");
        map.put("PART", "部分付款");
        return map;
    }
}
