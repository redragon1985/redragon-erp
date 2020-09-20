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
package com.erp.finance.ar.receipt.controller;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
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
import com.erp.finance.ap.invoice.dao.model.ApInvoiceHead;
import com.erp.finance.ap.invoice.dao.model.ApInvoiceHeadCO;
import com.erp.finance.ap.invoice.service.ApInvoiceHeadService;
import com.erp.finance.ap.invoice.service.ApInvoiceLineService;
import com.erp.finance.ap.pay.dao.model.ApPayLine;
import com.erp.finance.ap.pay.dao.model.ApPayLineCO;
import com.erp.finance.ar.invoice.dao.model.ArInvoiceHead;
import com.erp.finance.ar.invoice.dao.model.ArInvoiceHeadCO;
import com.erp.finance.ar.invoice.service.ArInvoiceHeadService;
import com.erp.finance.ar.invoice.service.ArInvoiceLineService;
import com.erp.finance.ar.receipt.dao.model.ArReceiptLine;
import com.erp.finance.ar.receipt.dao.model.ArReceiptLineCO;
import com.erp.finance.ar.receipt.service.ArReceiptLineService;
import com.erp.masterdata.common.service.MasterDataCommonService;

@Controller
@RequestMapping("/web/arReceiptLine")
public class ArReceiptLineWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(ArReceiptLineWebController.class);
    
    //服务层注入
    @Autowired
    private ArReceiptLineService arReceiptLineService;
    @Autowired
    private MasterDataCommonService masterDataCommonService;
    @Autowired
    private DatasetCommonService datasetCommonService;
    @Autowired
    private ArInvoiceHeadService arInvoiceHeadService;
    @Autowired
    private ArInvoiceLineService arInvoiceLineService;
    
    @Override
    public String getExceptionRedirectURL() {
        return "redirect:getArReceiptLineList";
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-09-15 14:47:08
     * @author 
     * @param pages
     * @param arReceiptLineCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getArReceiptLineList")
    public String getArReceiptLineList(Pages pages, ArReceiptLineCO arReceiptLineCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        pages.setMax(100);
        
        //分页查询数据
        List<ArReceiptLine> arReceiptLineList = this.arReceiptLineService.getReceiptLineListByHeadCode(pages, arReceiptLineCO);
        //循环获取发票信息
        for(ArReceiptLine arReceiptLine: arReceiptLineList) {
            //获取发票头信息
            ArInvoiceHead arInvoiceHead = this.arInvoiceHeadService.getDataObject(arReceiptLine.getInvoiceHeadCode());
            //获取发票行汇总金额
            BigDecimal[] sumAmount = this.arInvoiceLineService.getInvoiceLineAmountSumByHeadCode(arReceiptLine.getInvoiceHeadCode());
            
            //设置字段
            arReceiptLine.setSoHeadCode(arInvoiceHead.getInvoiceSourceHeadCode());
            arReceiptLine.setInvoiceDate(arInvoiceHead.getInvoiceDate());
            arReceiptLine.setReferenceNumber(arInvoiceHead.getReferenceNumber());
            arReceiptLine.setInvoiceAmount(sumAmount[0].doubleValue());
            arReceiptLine.setTaxAmount(sumAmount[1].doubleValue());
        }
        
        
        //页面属性设置
        model.addAttribute("receiptLineList", arReceiptLineList);
        model.addAttribute("pages", pages);
        
        return "arReceipt/tab/arReceiptLineTab";
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
    @RequestMapping("getSelectArInvoiceModal")
    public String getSelectArInvoiceModal(Pages pages, ArInvoiceHeadCO arInvoiceHeadCO, ArReceiptLineCO arReceiptLineCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        pages.setMax(100);
        
        //分页查询采购订单行数据
        List<ArInvoiceHead> invoiceHeadList = this.arInvoiceHeadService.getDataObjects(pages, arInvoiceHeadCO);
        //循环设置发票信息
        for(ArInvoiceHead arInvoiceHead: invoiceHeadList) {
            arInvoiceHead.setPayerName(this.masterDataCommonService.getCustomerMap().get(arInvoiceHead.getPayer()));
            //设置税额
            BigDecimal[] sumAmount = this.arInvoiceLineService.getInvoiceLineAmountSumByHeadCode(arInvoiceHead.getInvoiceHeadCode());
            arInvoiceHead.setTaxAmount(sumAmount[1].doubleValue());
        }
        
        //获取发票行数据
        Pages pagesTemp = new Pages();
        pagesTemp.setPage(1);
        pagesTemp.setMax(100);
        List<ArReceiptLine> receiptLineList = this.arReceiptLineService.getReceiptLineListByHeadCode(pagesTemp, arReceiptLineCO);
        
        //剔除已经做了发票行的采购订单行
        Iterator<ArInvoiceHead> invoiceIt = invoiceHeadList.iterator();
        Iterator<ArReceiptLine> receiptLineIt = receiptLineList.iterator();
        while(invoiceIt.hasNext()) {
            ArInvoiceHead apInvoiceHeadTemp = invoiceIt.next();
            while(receiptLineIt.hasNext()) {
                ArReceiptLine receiptLineTemp = receiptLineIt.next();
                if(apInvoiceHeadTemp.getInvoiceHeadCode().equals(receiptLineTemp.getInvoiceHeadCode())) {
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
        
        return "arReceipt/pop/selectArInvoiceModal";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-09-15 14:47:08
     * @author 
     * @param arReceiptLine
     * @param model
     * @return String
     *
     */
    @RequestMapping("getArReceiptLine")
    public String getArReceiptLine(ArReceiptLine arReceiptLine, Model model) {
        //查询要编辑的数据
        if(arReceiptLine!=null&&arReceiptLine.getReceiptLineId()!=null) {
            arReceiptLine = this.arReceiptLineService.getDataObject(arReceiptLine.getReceiptLineId());
            //获取发票信息
            ArInvoiceHead arInvoiceHead = this.arInvoiceHeadService.getDataObject(arReceiptLine.getInvoiceHeadCode());
            //获取税额
            BigDecimal[] sumAmount = this.arInvoiceLineService.getInvoiceLineAmountSumByHeadCode(arInvoiceHead.getInvoiceHeadCode());
            
            arReceiptLine.setInvoiceAmount(arInvoiceHead.getAmount());
            arReceiptLine.setTaxAmount(sumAmount[1].doubleValue());
            arReceiptLine.setSoHeadCode(arInvoiceHead.getInvoiceSourceHeadCode());
            arReceiptLine.setReferenceNumber(arInvoiceHead.getReferenceNumber());
            arReceiptLine.setInvoiceDate(arInvoiceHead.getInvoiceDate());
        }else {
            
        }
        
        //页面属性设置
        model.addAttribute("receiptLine", arReceiptLine);
        
        return "arReceipt/pop/addLineModal";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-09-15 14:47:08
     * @author 
     * @param arReceiptLine
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editArReceiptLine")
    @ResponseBody
    public String editArReceiptLine(@Valid ArReceiptLine arReceiptLine, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        try {
            /*
            //参数校验
            Map<String, String> errorMap = this.validateParameters(bindingResult, model);
            if(errorMap.size()>0) {
                return "forward:getApPayLine";
            }
            */
            
            //对当前编辑的对象初始化默认的字段
            if(arReceiptLine.getReceiptLineId()==null) {
                arReceiptLine.setReceiptLineCode(SnowFlake.generateId().toString());
            }
            
            //保存编辑的数据
            this.arReceiptLineService.insertOrUpdateDataObject(arReceiptLine);
            
            return "{\"result\":\"success\"}";
        }catch(Exception e) {
            return "{\"result\":\"error\"}";
        }
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-09-15 14:47:08
     * @author 
     * @param arReceiptLine
     * @return String
     *
     */
    @RequestMapping("deleteArReceiptLine")
    @ResponseBody
    public String deleteArReceiptLine(ArReceiptLine arReceiptLine, RedirectAttributes attr) {
        try {
            //删除数据前验证数据
            if(arReceiptLine!=null&&arReceiptLine.getReceiptLineId()!=null) {
                //删除数据
                this.arReceiptLineService.deleteDataObject(arReceiptLine);
            }
            
            return "{\"result\":\"success\"}";
        }catch(Exception e) {
            return "{\"result\":\"error\"}";
        }
    }
}
