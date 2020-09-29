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
package com.erp.report.voucher.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.erp.report.voucher.dao.model.VoucherReportV;
import com.erp.report.voucher.service.VoucherReportService;
import com.framework.controller.ControllerSupport;

import redragon.io.office.ExcelFile;

@Controller
@RequestMapping("/web/voucherReport")
public class VoucherReportWebController extends ControllerSupport {
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(VoucherReportWebController.class);
    
    //服务层注入
    @Autowired
    private VoucherReportService voucherReportService;

    @Override
    public String getExceptionRedirectURL() {
        return null;
    }
    
    
    
    /**
     * 
     * @description 导出凭证数据
     * @date 2020年9月29日
     * @author dongbin
     * @param request
     * @param response
     * @param voucherStartDate
     * @param voucherEndDate
     * @param model
     * @return void
     *
     */
    @RequestMapping("getVoucherReportList")
    public void getVoucherReportList(HttpServletRequest request, HttpServletResponse response, String voucherStartDate, String voucherEndDate, Model model) {
        
        try {
            List<VoucherReportV> dataList = this.voucherReportService.getVoucherReportList(voucherStartDate, voucherEndDate);
            
            List<String> titleList = new ArrayList<String>();
            titleList.add("凭证编码");
            titleList.add("凭证字编码");
            titleList.add("凭证字");
            titleList.add("凭证号");
            titleList.add("凭证日期");
            titleList.add("原始单据数");
            titleList.add("摘要");
            titleList.add("科目编码");
            titleList.add("科目");
            titleList.add("借方");
            titleList.add("贷方");
            titleList.add("子模块业务编码");
            titleList.add("子模块业务");
            
            ExcelFile excel = new ExcelFile(response, "导出凭证数据", "凭证列表", request);
            excel.writeListToResponse(titleList, dataList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
