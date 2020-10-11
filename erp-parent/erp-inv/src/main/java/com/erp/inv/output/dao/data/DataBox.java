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
        map.put("ALTER", "变更");
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
        //map.put("PO_RETURN", "购入退出");
        //map.put("TRANSFER_OUT", "调拨出库");
        return map;
    }
    
}
