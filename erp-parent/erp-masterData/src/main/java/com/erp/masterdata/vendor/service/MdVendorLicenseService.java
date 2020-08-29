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
package com.erp.masterdata.vendor.service;

import com.framework.api.DaoCRUDIF;
import com.erp.masterdata.vendor.dao.model.MdVendorLicense;
import com.erp.masterdata.vendor.dao.model.MdVendorLicenseCO;

public interface MdVendorLicenseService extends DaoCRUDIF<MdVendorLicense, MdVendorLicenseCO> {
    
    //查询供应商的营业执照数量
    public abstract int getLicenseCountByVendorCode(String vendorCode);
    
    //查询供应商的营业执照列表(分页)
    public abstract MdVendorLicense getLicenseListByVendorCode(MdVendorLicense paramObj);
    
}
