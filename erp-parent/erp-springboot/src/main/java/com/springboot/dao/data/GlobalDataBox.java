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
package com.springboot.dao.data;

import java.util.HashMap;
import java.util.Map;

/**
 * @description
 * @date 2020年7月27日
 * @author dongbin
 * 
 */
public class GlobalDataBox {
    
    //获取审批状态
    public static Map<String, String> getApproveStatusMap(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("UNSUBMIT", "未提交");
        map.put("SUBMIT", "已提交");
        map.put("APPROVE", "已审批");
        map.put("REJECT", "已驳回");
        return map;
    }

}
