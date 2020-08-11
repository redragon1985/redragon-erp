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
package com.erp.finance.receipt.service;

import com.framework.api.DaoCRUDIF;
import com.framework.dao.model.Pages;

import java.math.BigDecimal;
import java.util.List;

import com.erp.finance.receipt.dao.model.ReceiptLine;
import com.erp.finance.receipt.dao.model.ReceiptLineCO;

public interface ReceiptLineService extends DaoCRUDIF<ReceiptLine, ReceiptLineCO> {
    
    //获取行列表（根据头code）
    public abstract List<ReceiptLine> getReceiptLineListByReceiptHeadCode(Pages pages, ReceiptLineCO paramObj);
    
    //获取采购订单历史付款金额
    public abstract BigDecimal getHISReceiptAmountForSO(String soHeadCode, String receiptHeadCode);
    
    //删除付款行（根据头）
    public abstract void deleteReceiptLineByReceiptHeadCode(String receiptHeadCode);
    
    //获取收款单总金额
    public abstract BigDecimal getReceiptAmountByReceiptHeadCode(String receiptHeadCode);
    
}
