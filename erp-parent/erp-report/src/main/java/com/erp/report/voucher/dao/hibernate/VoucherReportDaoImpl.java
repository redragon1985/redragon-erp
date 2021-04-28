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
package com.erp.report.voucher.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.dao.BasicDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.erp.report.voucher.dao.VoucherReportDao;
import com.erp.report.voucher.dao.model.VoucherReportV;
import com.framework.util.DaoUtil;

@Repository
public class VoucherReportDaoImpl implements VoucherReportDao {
    
    //注入basicDao工具类
    @Autowired
    private BasicDao basicDao;

    @Override
    public List<VoucherReportV> getVoucherReportList(String startDate, String endDate) {
        String sql = "select * from fin_voucher_report_v where 1=1";
        
        Map<String, Object> args = new HashMap<String, Object>();
        sql = sql + DaoUtil.getSimpleSQLConditionForDateTime("voucherDate", startDate, endDate, "and ", args);
        
        return this.basicDao.selectData(sql, VoucherReportV.class, args);
    }

}
