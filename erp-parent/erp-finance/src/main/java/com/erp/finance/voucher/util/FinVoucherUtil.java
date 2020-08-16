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
 * @description FinVoucherUtil.java
 * @author dongbin
 * @version 
 * @copyright
 */

package com.erp.finance.voucher.util;

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
