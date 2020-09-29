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
package com.erp.finance.voucher.dao.model;

import org.apache.commons.lang.StringUtils;

import com.erp.finance.voucher.dao.model.FinVoucherHead;

public class FinVoucherHeadCO extends FinVoucherHead implements java.io.Serializable {
	
	// serialVersionUID
	private static final long serialVersionUID = 1L;
	
	//查询字段
	private String businessType;
	private String voucherStartDate;
	private String voucherEndDate;

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getVoucherStartDate() {
        if(StringUtils.isNotBlank(voucherStartDate)) {
            return voucherStartDate+" 00:00:00";
        }else {
            return voucherStartDate;
        }
        
    }

    public void setVoucherStartDate(String voucherStartDate) {
        this.voucherStartDate = voucherStartDate;
    }

    public String getVoucherEndDate() {
        if(StringUtils.isNotBlank(voucherEndDate)) {
            return voucherEndDate+" 23:59:59";
        }else {
            return voucherEndDate;
        }
    }

    public void setVoucherEndDate(String voucherEndDate) {
        this.voucherEndDate = voucherEndDate;
    }
	
}