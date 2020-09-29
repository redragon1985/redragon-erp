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
package com.erp.report.voucher.service.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erp.report.voucher.dao.VoucherReportDao;
import com.erp.report.voucher.dao.model.VoucherReportV;
import com.erp.report.voucher.service.VoucherReportService;

@Service
@Transactional(rollbackFor=Exception.class)
public class VoucherReportServiceImpl implements VoucherReportService {

    //注入Dao
    @Autowired
    private VoucherReportDao voucherReportDao;
    
    @Override
    public List<VoucherReportV> getVoucherReportList(String startDate, String endDate) {
        return this.voucherReportDao.getVoucherReportList(startDate, endDate);
    }

}
