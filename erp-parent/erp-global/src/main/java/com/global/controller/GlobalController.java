/*
 * Copyright 2020-2021 redragon.dongbin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.finance.pay.service.PayHeadService;
import com.erp.finance.receipt.service.ReceiptHeadService;
import com.erp.finance.voucher.service.FinVoucherHeadService;
import com.erp.hr.dao.model.HrStaffInfoRO;
import com.erp.hr.service.HrCommonService;
import com.erp.masterdata.customer.service.MdCustomerService;
import com.erp.masterdata.vendor.service.MdVendorService;
import com.erp.order.po.service.PoHeadService;
import com.erp.order.so.service.SoHeadService;
import com.framework.util.JsonResultUtil;
import com.framework.util.ShiroUtil;

import redragon.basic.tools.TimeToolKit;

/**
 * @description
 * @date 2020年6月26日
 * @author dongbin
 * 
 */
@Controller
public class GlobalController {
    
    //服务类
    @Autowired
    private HrCommonService hrCommonService;
    @Autowired
    private FinVoucherHeadService finVoucherHeadService;
    @Autowired
    private PayHeadService payHeadService;
    @Autowired
    private ReceiptHeadService receiptHeadService;
    @Autowired
    private PoHeadService poHeadService;
    @Autowired
    private SoHeadService soHeadService;
    @Autowired
    private MdCustomerService mdCustomerService;
    @Autowired
    private MdVendorService mdVendorService;
    
    
    
    @RequestMapping("error404")
    public String error404() {
        return "/common/error/404";
    }
    
    @RequestMapping("apiError404")
    @ResponseBody
    public String apiError404() {
        return JsonResultUtil.getErrorJson(-1, "404");
    }
    
    @RequestMapping("error500")
    public String error500() {
        return "/common/error/500";
    }
    
    @RequestMapping("web/main")
    public String webMain(Model model) {
        //获取当前用户职员信息
        HrStaffInfoRO staffInfo = this.hrCommonService.getStaffInfo(ShiroUtil.getUsername());
        
        TimeToolKit time = new TimeToolKit();
        String startDate = time.getDate()+" 00:00:00";
        String endDate = time.getDate()+" 23:59:59";
        //获取采购订单数
        model.addAttribute("poHeadNum", this.poHeadService.getPoHeadNum(startDate, endDate));
        //获取销售订单数
        model.addAttribute("soHeadNum", this.soHeadService.getSoHeadNum(startDate, endDate));
        //获取付款单数
        model.addAttribute("payHeadNum", this.payHeadService.getPayHeadNum(startDate, endDate));
        //获取收款单数
        model.addAttribute("receiptHeadNum", this.receiptHeadService.getReceiptHeadNum(startDate, endDate));
        //获取凭证数
        model.addAttribute("voucherHeadNum", this.finVoucherHeadService.getVoucherHeadNum(startDate, endDate));
        //获取客户数
        model.addAttribute("customerNum", this.mdCustomerService.getCustomerNum());
        //获取供应商数
        model.addAttribute("vendorNum", this.mdVendorService.getVendorNum());
        
        //页面属性设置
        model.addAttribute("staffInfo", staffInfo);
        
        return "basic.jsp?content=main";
    }
    
    @RequestMapping("main")
    public String main() {
        return "redirect: web/main";
    }
    
    @RequestMapping("index")
    public String index() {
        return "redirect: main";
    }
    
    @RequestMapping("")
    public String root() {
        return "redirect: index";
    }
    
    
    
}
