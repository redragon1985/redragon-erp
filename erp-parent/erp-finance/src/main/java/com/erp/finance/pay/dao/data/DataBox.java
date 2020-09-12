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
    public static Map<String, String> getApInvoiceStatusMap(){
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
    public static Map<String, String> getApInvoiceSourceType(){
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("PO", "采购订单");
        //bbc 此功能未开放
        //map.put("INPUT", "入库单");
        return map;
    }
}
