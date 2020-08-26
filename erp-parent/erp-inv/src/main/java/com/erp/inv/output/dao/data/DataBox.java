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
/**
 * @description DataBox.java
 * @author dongbin
 * @version 
 * @copyright
 */

package com.erp.inv.output.dao.data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description
 * @date 2020年8月21日
 * @author dongbin
 * 
 */
public class DataBox {
    
    //获取出库状态
    public static Map<String, String> getOutputStatusMap(){
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("NEW", "新建");
        map.put("CONFIRM", "确认");
        map.put("CANCEL", "取消");
        return map;
    }

    //获取出库来源类型
    public static Map<String, String> getOutputSourceTypeMap() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("SO", "销售订单");
        return map;
    }
    
    //获取出库类型
    public static Map<String, String> getOutputTypeMap(){
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("SO_OUT", "销售出库");
        map.put("PO_RETURN", "购入退出");
        map.put("CHECK_OUT", "盘点出库");
        map.put("TRANSFER_OUT", "调拨出库");
        return map;
    }
    
}
