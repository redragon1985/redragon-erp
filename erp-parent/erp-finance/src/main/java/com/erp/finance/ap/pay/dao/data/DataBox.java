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

package com.erp.finance.ap.pay.dao.data;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description
 * @date 2020年9月15日
 * @author dongbin
 * 
 */
public class DataBox {
    
    //获取付款类型
    public static Map<String, String> getApPayType(){
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("MONEY", "现金/银行");
        return map;
    }

}
