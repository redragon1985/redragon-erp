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
/**
 * @description ApPayHeadService.java
 * @author dongbin
 * @version 
 * @copyright
 */

package com.erp.common.ap.pay.service;

/**
 * @description
 * @date 2020年10月8日
 * @author dongbin
 * 
 */
public interface ApPayHeadService {
    
    //是否存在关联采购发票的付款
    public abstract boolean isExistApPayRelateApInvoice(String headCode);

}
