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
package com.erp.common.voucher.util;

import com.framework.util.JedisUtil;
import com.framework.util.RedisUtil;

/**
 * @description
 * @date 2020年8月14日
 * @author dongbin
 * 
 */
public class FinVoucherUtil {
    
    //初始化凭证流水号（根据凭证字）
    public static void setVoucherNumberCache(String voucherType, Long voucherNumber) {
        String cacheKey = "GLOBAL_VOUCHER_NUMBER_"+voucherType.toUpperCase();
        RedisUtil.set(cacheKey, voucherNumber.toString());
    }
    
    //增加凭证流水号（根据凭证字）
    public static Long incrVoucherNumberCache(String voucherType) {
        String cacheKey = "GLOBAL_VOUCHER_NUMBER_"+voucherType.toUpperCase();
        return JedisUtil.getJedis().incr(cacheKey);
    }
    
    //获取凭证流水号（根据凭证字）
    public static Long getVoucherNumberCache(String voucherType) {
        String cacheKey = "GLOBAL_VOUCHER_NUMBER_"+voucherType.toUpperCase();
        String value = RedisUtil.get(cacheKey);
        try {
            if(value!=null) {
                return Long.valueOf(value);
            }
        }catch(Exception e) {
            
        }
        
        return -1L;
    }
    

}
