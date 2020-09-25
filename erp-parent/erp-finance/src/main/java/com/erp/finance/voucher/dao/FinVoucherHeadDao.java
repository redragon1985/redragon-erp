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
import com.erp.finance.voucher.dao.model.FinVoucherHead;
import com.erp.finance.voucher.dao.model.FinVoucherHeadCO;

public interface FinVoucherHeadDao extends DaoCRUDIF<FinVoucherHead, FinVoucherHeadCO>{
    
    //更新凭证头状态
    public abstract void updateFinVoucherHeadForStatus(Integer voucherHeadId, String status);
    
    //更新凭证审批状态
    public abstract void updateFinVoucherHeadForApproveStatus(Integer voucherHeadId, String approveStatus);
    
    //获取凭证数量
    public abstract int getVoucherHeadNum(String startDate, String endDate);
    
    //根据单据类型和头编码获取凭证头（分录）
    public abstract FinVoucherHead getVoucherHead(String billType, String billHeadCode);
    
}