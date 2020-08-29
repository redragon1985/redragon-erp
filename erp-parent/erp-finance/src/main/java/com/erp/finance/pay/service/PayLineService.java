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
package com.erp.finance.pay.service;

import com.framework.api.DaoCRUDIF;
import com.framework.dao.model.Pages;

import java.math.BigDecimal;
import java.util.List;

import com.erp.finance.pay.dao.model.PayLine;
import com.erp.finance.pay.dao.model.PayLineCO;

public interface PayLineService extends DaoCRUDIF<PayLine, PayLineCO> {
    
    //获取行列表（根据头code）
    public abstract List<PayLine> getPayLineListByPayHeadCode(Pages pages, PayLineCO paramObj);
    
    //获取采购订单历史付款金额
    public abstract BigDecimal getHISPayAmountForPO(String poHeadCode, String payHeadCode);
    
    //删除付款行（根据头）
    public abstract void deletePayLineByPayHeadCode(String payHeadCode);
    
    //获取付款单总金额
    public abstract BigDecimal getPayAmountByPayHeadCode(String payHeadCode);
    
}
