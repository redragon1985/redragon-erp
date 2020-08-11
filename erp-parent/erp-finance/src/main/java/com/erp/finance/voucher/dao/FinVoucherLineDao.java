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

import java.math.BigDecimal;
import java.util.List;

import com.erp.finance.voucher.dao.model.FinVoucherLine;
import com.erp.finance.voucher.dao.model.FinVoucherLineCO;

public interface FinVoucherLineDao extends DaoCRUDIF<FinVoucherLine, FinVoucherLineCO>{
    
    //获取凭证行（根据头编码）
    public abstract List<FinVoucherLine> getFinVoucherLineListByVoucherHeadCode(String voucherHeadCode);
    
    //删除凭证行（根据头编码）
    public abstract void deleteFinVoucherLineByVoucherHeadCode(String voucherHeadCode);
    
    //获取凭证金额
    public abstract BigDecimal getFinVoucherAmountByVoucherHeadCode(String voucherHeadCode);
    
}