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
package com.erp.hr.dao;

import com.framework.api.DaoCRUDIF;
import com.erp.hr.dao.model.HrStaff;
import com.erp.hr.dao.model.HrStaffCO;
import com.erp.hr.dao.model.HrStaffInfoRO;

public interface HrStaffDao extends DaoCRUDIF<HrStaff, HrStaffCO>{
    
    //获取职员信息RO
    public HrStaffInfoRO getHrStaffInfoRO(String username);
    
    //职员是否存在关联数据
    public abstract boolean isExistRelateDataForHrStaff(String staffCode);
    
    //根据staffCode获取username
    public abstract String getUsernameByStaffCode(String staffCode);
    
}