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
package com.erp.common.voucher.dao;

import com.framework.api.DaoCRUDIF;
import com.erp.common.voucher.dao.model.FinVoucherBillR;
import com.erp.common.voucher.dao.model.FinVoucherBillRCO;

public interface FinVoucherBillRDao extends DaoCRUDIF<FinVoucherBillR, FinVoucherBillRCO>{
    
    //删除凭证与单据关联（根据凭证头code）
    public abstract void deleteFinVoucherBillRByVoucherHeadCode(String voucherHeadCode);
    
    //根据单据头code获取凭证头code
    public abstract String getVoucherHeadCodeByBillHeadCode(String billType, String billHeadCode);
    
    //是否存在关联付款的凭证
    public abstract boolean isExistVoucherRelateArPay(String headCode);
    
    //是否存在关联收款的凭证
    public abstract boolean isExistVoucherRelateArReceipt(String headCode);
    
}