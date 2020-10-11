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
package com.erp.masterdata.material.dao.data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description
 * @date 2020年8月2日
 * @author dongbin
 * 
 */
public class DataBox {
    
    //获取物料或事情选择Map
    public static Map<String, String> getMaterialTypeMap(){
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("MATERIAL", "物料");
        map.put("MATTER", "服务");
        return map;
    }

}
