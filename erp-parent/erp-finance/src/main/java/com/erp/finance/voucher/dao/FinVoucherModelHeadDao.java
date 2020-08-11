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
package com.erp.finance.voucher.dao;

import com.framework.api.DaoCRUDIF;

import java.util.List;

import com.erp.finance.voucher.dao.model.FinVoucherModelHead;
import com.erp.finance.voucher.dao.model.FinVoucherModelHeadCO;

public interface FinVoucherModelHeadDao extends DaoCRUDIF<FinVoucherModelHead, FinVoucherModelHeadCO>{
    
    //获取凭证自定义模板
    public abstract List<FinVoucherModelHead> getFinVoucherModelHeadListForCustom();
    
    //获取凭证模板的业务类型
    public abstract List<String> getFinVoucherModelHeadForBusinessType();
    
    //根据业务类型获取凭证模板头
    public abstract FinVoucherModelHead getFinVoucherModelHeadByBusinessType(String businessType);
    
}