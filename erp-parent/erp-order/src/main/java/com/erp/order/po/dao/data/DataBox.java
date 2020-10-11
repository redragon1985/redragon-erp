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
        map.put("ALTER", "变更");
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
