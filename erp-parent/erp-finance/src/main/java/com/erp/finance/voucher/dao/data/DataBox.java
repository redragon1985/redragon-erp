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
