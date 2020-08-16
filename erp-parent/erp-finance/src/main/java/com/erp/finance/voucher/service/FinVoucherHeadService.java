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
package com.erp.finance.voucher.service;

import com.framework.api.DaoCRUDIF;

import java.util.List;

import com.erp.finance.voucher.dao.model.FinVoucherHead;
import com.erp.finance.voucher.dao.model.FinVoucherHeadCO;
import com.erp.finance.voucher.dao.model.FinVoucherLine;

public interface FinVoucherHeadService extends DaoCRUDIF<FinVoucherHead, FinVoucherHeadCO> {
    
    //同时插入或修改凭证头行
    public abstract void insertOrUpdateFinVoucher(FinVoucherHead finVoucherHead, List<FinVoucherLine> finVoucherLineList);
    
    //更新凭证头状态
    public abstract void updateFinVoucherHeadForStatus(Integer voucherHeadId, String voucherHeadCode, String status);
    
    //更新凭证审批状态
    public abstract void updateFinVoucherHeadForApproveStatus(Integer voucherHeadId, String approveStatus);
    
    //获取凭证数量
    public abstract int getVoucherHeadNum(String startDate, String endDate);
    
}
