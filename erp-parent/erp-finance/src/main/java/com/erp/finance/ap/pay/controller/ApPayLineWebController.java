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
package com.erp.finance.ap.pay.controller;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.controller.ControllerSupport;
import com.framework.dao.model.Pages;
import com.framework.util.JsonResultUtil;
import com.framework.util.JsonUtil;

import redragon.frame.hibernate.SnowFlake;

import com.erp.dataset.service.DatasetCommonService;
import com.erp.finance.ap.invoice.dao.data.DataBox;
import com.erp.finance.ap.invoice.dao.model.ApInvoiceHead;
import com.erp.finance.ap.invoice.dao.model.ApInvoiceHeadCO;
import com.erp.finance.ap.invoice.dao.model.ApInvoiceLine;
import com.erp.finance.ap.invoice.dao.model.ApInvoiceLineCO;
import com.erp.finance.ap.invoice.service.ApInvoiceHeadService;
import com.erp.finance.ap.invoice.service.ApInvoiceLineService;
import com.erp.finance.ap.pay.dao.model.ApPayLine;
import com.erp.finance.ap.pay.dao.model.ApPayLineCO;
import com.erp.finance.ap.pay.service.ApPayLineService;
import com.erp.masterdata.common.service.MasterDataCommonService;
import com.erp.masterdata.material.dao.model.MdMaterial;
import com.erp.order.po.dao.model.PoLine;
import com.erp.order.po.dao.model.PoLineCO;

@Controller
@RequestMapping("/web/apPayLine")
public class ApPayLineWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(ApPayLineWebController.class);
    
    //服务层注入
    @Autowired
    private ApPayLineService apPayLineService;
    @Autowired
    private MasterDataCommonService masterDataCommonService;
    @Autowired
    private DatasetCommonService datasetCommonService;
    @Autowired
    private ApInvoiceHeadService apInvoiceHeadService;
    @Autowired
    private ApInvoiceLineService apInvoiceLineService;
    
    @Override
    public String getExceptionRedirectURL() {
        return "redirect:getApPayLineList";
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-09-15 14:44:44
     * @author 
     * @param pages
     * @param apPayLineCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getApPayLineList")
    public String getApPayLineList(Pages pages, ApPayLineCO apPayLineCO, Model model) {
        
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        pages.setMax(100);
        
        //分页查询数据
        List<ApPayLine> apPayLineList = this.apPayLineService.getPayLineListByHeadCode(pages, apPayLineCO);
        //循环获取发票信息
        for(ApPayLine apPayLine: apPayLineList) {
            //获取发票头信息
            ApInvoiceHead apInvoiceHead = this.apInvoiceHeadService.getDataObject(apPayLine.getInvoiceHeadCode());
            //获取发票行汇总金额
            BigDecimal[] sumAmount = this.apInvoiceLineService.getInvoiceLineAmountSumByHeadCode(apPayLine.getInvoiceHeadCode());
            
            //设置字段
            apPayLine.setPoHeadCode(apInvoiceHead.getInvoiceSourceHeadCode());
            apPayLine.setInvoiceDate(apInvoiceHead.getInvoiceDate());
            apPayLine.setReferenceNumber(apInvoiceHead.getReferenceNumber());
            apPayLine.setInvoiceAmount(sumAmount[0].doubleValue());
            apPayLine.setTaxAmount(sumAmount[1].doubleValue());
        }
        
        
        //页面属性设置
        model.addAttribute("payLineList", apPayLineList);
        model.addAttribute("pages", pages);
        
        return "apPay/tab/apPayLineTab";
    }
    
    
    
    /**
     * 
     * @description 获取采购发票选择框
     * @date 2020年7月20日
     * @author dongbin
     * @return
     * @return String
     *
     */
    @RequestMapping("getSelectApInvoiceModal")
    public String getSelectApInvoiceModal(Pages pages, ApInvoiceHeadCO apInvoiceHeadCO, ApPayLineCO apPayLineCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        
        //分页查询采购订单行数据
        apInvoiceHeadCO.setStatus("CONFIRM");
        apInvoiceHeadCO.setApproveStatus("APPROVE");
        List<ApInvoiceHead> invoiceHeadList = this.apInvoiceHeadService.getDataObjects(pages, apInvoiceHeadCO);
        //循环设置发票信息
        for(ApInvoiceHead apInvoiceHead: invoiceHeadList) {
            apInvoiceHead.setReceiverName(this.masterDataCommonService.getVendorMap().get(apInvoiceHead.getReceiver()));
            //设置税额
            BigDecimal[] sumAmount = this.apInvoiceLineService.getInvoiceLineAmountSumByHeadCode(apInvoiceHead.getInvoiceHeadCode());
            apInvoiceHead.setTaxAmount(sumAmount[1].doubleValue());
        }
        
        //获取发票行数据
        Pages pagesTemp = new Pages();
        pagesTemp.setPage(1);
        pagesTemp.setMax(100);
        List<ApPayLine> payLineList = this.apPayLineService.getPayLineListByHeadCode(pagesTemp, apPayLineCO);
        
        //剔除已经做了发票行的采购订单行
        Iterator<ApInvoiceHead> invoiceIt = invoiceHeadList.iterator();
        while(invoiceIt.hasNext()) {
            ApInvoiceHead apInvoiceHeadTemp = invoiceIt.next();
            for(ApPayLine payLineTemp: payLineList) {
                if(apInvoiceHeadTemp.getInvoiceHeadCode().equals(payLineTemp.getInvoiceHeadCode())) {
                    invoiceIt.remove();
                    break;
                }
            }
        }
        //重置总数据数量
        pages.setDataNumber(invoiceHeadList.size());
        
        //页面属性设置
        model.addAttribute("invoiceHeadList", invoiceHeadList);
        model.addAttribute("pages", pages);
        
        return "apPay/pop/selectApInvoiceModal";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-09-15 14:44:44
     * @author 
     * @param apPayLine
     * @param model
     * @return String
     *
     */
    @RequestMapping("getApPayLine")
    public String getApPayLine(ApPayLine apPayLine, Model model) {
        
        //查询要编辑的数据
        if(apPayLine!=null&&apPayLine.getPayLineId()!=null) {
            apPayLine = this.apPayLineService.getDataObject(apPayLine.getPayLineId());
            //获取发票信息
            ApInvoiceHead apInvoiceHead = this.apInvoiceHeadService.getDataObject(apPayLine.getInvoiceHeadCode());
            //获取税额
            BigDecimal[] sumAmount = this.apInvoiceLineService.getInvoiceLineAmountSumByHeadCode(apInvoiceHead.getInvoiceHeadCode());
            //获取发票已核销金额
            Double invoicePaidAmount = this.apPayLineService.getInvoicePaidAmount(apPayLine.getInvoiceHeadCode(), apPayLine.getPayLineId());
            
            apPayLine.setInvoicePaidAmount(invoicePaidAmount);
            apPayLine.setInvoiceAmount(apInvoiceHead.getAmount());
            apPayLine.setTaxAmount(sumAmount[1].doubleValue());
            apPayLine.setPoHeadCode(apInvoiceHead.getInvoiceSourceHeadCode());
            apPayLine.setReferenceNumber(apInvoiceHead.getReferenceNumber());
            apPayLine.setInvoiceDate(apInvoiceHead.getInvoiceDate());
        }else {
            //获取发票已核销金额
            Double invoicePaidAmount = this.apPayLineService.getInvoicePaidAmount(apPayLine.getInvoiceHeadCode(), 0);
            apPayLine.setInvoicePaidAmount(invoicePaidAmount);
        }
        
        //页面属性设置
        model.addAttribute("payLine", apPayLine);
        
        return "apPay/pop/addLineModal";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-09-15 14:44:44
     * @author 
     * @param apPayLine
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editApPayLine")
    @ResponseBody
    public String editApPayLine(@Valid ApPayLine apPayLine, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        try {
            /*
            //参数校验
            Map<String, String> errorMap = this.validateParameters(bindingResult, model);
            if(errorMap.size()>0) {
                return "forward:getApPayLine";
            }
            */
            
            //对当前编辑的对象初始化默认的字段
            if(apPayLine.getPayLineId()==null) {
                apPayLine.setPayLineCode(SnowFlake.generateId().toString());
            }
            
            //保存编辑的数据
            this.apPayLineService.insertOrUpdateDataObject(apPayLine);
            
            return "{\"result\":\"success\"}";
        }catch(Exception e) {
            return "{\"result\":\"error\"}";
        }
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-09-15 14:44:44
     * @author 
     * @param apPayLine
     * @return String
     *
     */
    @RequestMapping("deleteApPayLine")
    @ResponseBody
    public String deleteApPayLine(ApPayLine apPayLine, RedirectAttributes attr) {
        try {
            //删除数据前验证数据
            if(apPayLine!=null&&apPayLine.getPayLineId()!=null) {
                //删除数据
                this.apPayLineService.deleteDataObject(apPayLine);
            }
            
            return "{\"result\":\"success\"}";
        }catch(Exception e) {
            return "{\"result\":\"error\"}";
        }
    }
}
