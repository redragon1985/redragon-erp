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
package com.erp.finance.voucher.dao;

import com.framework.api.DaoCRUDIF;

import java.math.BigDecimal;
import java.util.List;

import com.erp.finance.voucher.dao.model.FinVoucherHead;
import com.erp.finance.voucher.dao.model.FinVoucherLine;
import com.erp.finance.voucher.dao.model.FinVoucherLineCO;

public interface FinVoucherLineDao extends DaoCRUDIF<FinVoucherLine, FinVoucherLineCO>{
    
    //获取凭证行（根据头编码）
    public abstract List<FinVoucherLine> getFinVoucherLineListByVoucherHeadCode(String voucherHeadCode);
    
    //删除凭证行（根据头编码）
    public abstract void deleteFinVoucherLineByVoucherHeadCode(String voucherHeadCode);
    
    //获取凭证金额
    public abstract BigDecimal getFinVoucherAmountByVoucherHeadCode(String voucherHeadCode);
    
    //根据单据类型和头编码获取凭证行列表（分录）
    public abstract List<FinVoucherLine> getVoucherLineList(String billType, String billHeadCode);
    
}