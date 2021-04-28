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
package com.erp.finance.ap.invoice.controller;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.erp.dataset.service.DatasetCommonService;
import com.erp.finance.ap.invoice.dao.model.ApInvoiceLine;
import com.erp.finance.ap.invoice.dao.model.ApInvoiceLineCO;
import com.erp.finance.ap.invoice.service.ApInvoiceLineService;
import com.erp.inv.input.service.InvInputLineService;
import com.erp.masterdata.common.service.MasterDataCommonService;
import com.erp.masterdata.material.dao.model.MdMaterial;
import com.erp.order.po.dao.model.PoLine;
import com.erp.order.po.dao.model.PoLineCO;
import com.erp.order.po.service.PoLineService;
import com.framework.controller.ControllerSupport;
import com.framework.dao.model.Pages;
import redragon.basic.tools.SnowFlake;


@Controller
@RequestMapping("/web/apInvoiceLine")
public class ApInvoiceLineWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(ApInvoiceLineWebController.class);
    
    //服务层注入
    @Autowired
    private ApInvoiceLineService apInvoiceLineService;
    @Autowired
    private MasterDataCommonService masterDataCommonService;
    @Autowired
    private DatasetCommonService datasetCommonService;
    @Autowired
    private PoLineService poLineService;
    @Autowired
    private InvInputLineService invInputLineService;
    
    @Override
    public String getExceptionRedirectURL() {
        return "redirect:getApInvoiceLineList";
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-07-19 14:16:52
     * @author 
     * @param pages
     * @param payLineCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getApInvoiceLineList")
    public String getApInvoiceLineList(Pages pages, ApInvoiceLineCO payLineCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        pages.setMax(100);
        
        //物料
        Map materialMap = this.masterDataCommonService.getMaterialMap();
        //物料单位
        Map materialUnitMap = this.datasetCommonService.getMaterialUnit();
        
        //分页查询数据
        List<ApInvoiceLine> payLineList = this.apInvoiceLineService.getApInvoiceLineListByHeadCode(pages, payLineCO);
        for(ApInvoiceLine payLine: payLineList) {
            PoLine poLine = this.poLineService.getDataObject(payLine.getInvoiceSourceLineCode());
            payLine.setMaterialCode(poLine.getMaterialCode());
            payLine.setMaterialName(materialMap.get(poLine.getMaterialCode()).toString());
            payLine.setStandard(this.masterDataCommonService.getMdMaterialInfoCache(poLine.getMaterialCode()).getStandard());
            payLine.setPrice(poLine.getPrice());
            payLine.setInputQuantity(poLine.getQuantity());
            payLine.setUnit(materialUnitMap.get(poLine.getUnit()).toString());
            payLine.setPoLineAmount(poLine.getAmount());
        }
        
        
        
        //页面属性设置
        model.addAttribute("payLineList", payLineList);
        model.addAttribute("pages", pages);
        
        return "apInvoice/tab/apInvoiceLineTab";
    }
    
    
    
    /**
     * 
     * @description 获取采购订单选择框
     * @date 2020年7月20日
     * @author dongbin
     * @return
     * @return String
     *
     */
    @RequestMapping("getSelectPOLineModal")
    public String getSelectPOLineModal(Pages pages, PoLineCO poLineCO, ApInvoiceLineCO payLineCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        pages.setMax(1000);
        
        //分页查询采购订单行数据
        List<PoLine> poLineList = this.poLineService.getPoLineListByPoHeadCode(pages, poLineCO);
        //循环设置入库数量
        for(PoLine poLine: poLineList) {
            MdMaterial mdMaterial = this.masterDataCommonService.getMdMaterialInfoCache(poLine.getMaterialCode());
            if(mdMaterial.getMaterialType().equals("MATERIAL")) {
                //如果是物料则取入库数量
                poLine.setInputQuantity(this.invInputLineService.getInputQuantityByPoLineCode(poLine.getPoLineCode()));
            }else {
                //如果是服务则入库数量直接取采购数量
                poLine.setInputQuantity(poLine.getQuantity());
            }
        }
        
        //获取发票行数据
        Pages pagesTemp = new Pages();
        pagesTemp.setPage(1);
        pagesTemp.setMax(100);
        List<ApInvoiceLine> payLineList = this.apInvoiceLineService.getApInvoiceLineListByHeadCode(pagesTemp, payLineCO);
        
        //剔除已经做了发票行的采购订单行
        Iterator<PoLine> poLineIt = poLineList.iterator();
        while(poLineIt.hasNext()) {
            PoLine poLineTemp = poLineIt.next();
            for(ApInvoiceLine payLineTemp: payLineList) {
                if(poLineTemp.getPoLineCode().equals(payLineTemp.getInvoiceSourceLineCode())) {
                    poLineIt.remove();
                    break;
                }
            }
        }
        //重置总数据数量
        pages.setDataNumber(poLineList.size());
        
        //物料
        Map materialMap = this.masterDataCommonService.getMaterialMap();
        //物料单位
        Map materialUnitMap = this.datasetCommonService.getMaterialUnit();
        
        //页面属性设置
        model.addAttribute("poLineList", poLineList);
        model.addAttribute("pages", pages);
        model.addAttribute("materialMap", materialMap);
        model.addAttribute("materialUnitMap", materialUnitMap);
        
        return "apInvoice/pop/selectPOLineModal";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-07-19 14:16:52
     * @author 
     * @param payLine
     * @param model
     * @return String
     *
     */
    @RequestMapping("getApInvoiceLine")
    public String getApInvoiceLine(ApInvoiceLine payLine, String invoiceSourceType, Model model) {
        //查询要编辑的数据
        if(payLine!=null&&payLine.getInvoiceLineId()!=null) {
            payLine = this.apInvoiceLineService.getDataObject(payLine.getInvoiceLineId());
            //根据来源获取单据数据
            if(StringUtils.isNotBlank(invoiceSourceType)) {
                if(invoiceSourceType.equals("PO")) {
                    //物料
                    Map materialMap = this.masterDataCommonService.getMaterialMap();
                    //物料单位
                    Map materialUnitMap = this.datasetCommonService.getMaterialUnit();
                    
                    PoLine poLine = this.poLineService.getDataObject(payLine.getInvoiceSourceLineCode());
                    payLine.setMaterialCode(poLine.getMaterialCode());
                    payLine.setMaterialName(materialMap.get(poLine.getMaterialCode()).toString());
                    payLine.setStandard(this.masterDataCommonService.getMdMaterialInfoCache(poLine.getMaterialCode()).getStandard());
                    payLine.setPrice(poLine.getPrice());
                    
                    //货物入库数量
                    MdMaterial mdMaterial = this.masterDataCommonService.getMdMaterialInfoCache(poLine.getMaterialCode());
                    if(mdMaterial.getMaterialType().equals("MATERIAL")) {
                        //如果是物料则取入库数量
                        payLine.setInputQuantity(this.invInputLineService.getInputQuantityByPoLineCode(poLine.getPoLineCode()));
                    }else {
                        //如果是服务则入库数量直接取采购数量
                        payLine.setInputQuantity(poLine.getQuantity());
                    }
                    
                    //订单已开票数量
                    payLine.setMadeInvoiceQuantity(this.apInvoiceLineService.getMadeInvoiceQuantityByPoLine(poLine.getPoLineCode(), payLine.getInvoiceLineId()));
                    
                    payLine.setUnit(materialUnitMap.get(poLine.getUnit()).toString());
                    payLine.setPoLineAmount(poLine.getAmount());
                }
            }
        }else {
            //根据来源获取单据数据
            if(StringUtils.isNotBlank(invoiceSourceType)) {
                if(invoiceSourceType.equals("PO")) {
                    PoLine poLine = this.poLineService.getDataObject(payLine.getInvoiceSourceLineCode());
                    //订单已开票数量
                    payLine.setMadeInvoiceQuantity(this.apInvoiceLineService.getMadeInvoiceQuantityByPoLine(poLine.getPoLineCode(), 0));
                }
            }
            
            payLine.setStandard(this.masterDataCommonService.getMdMaterialInfoCache(payLine.getMaterialCode()).getStandard());
        }
        
        //页面属性设置
        model.addAttribute("payLine", payLine);
        
        return "apInvoice/pop/addLineModal";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-07-19 14:16:52
     * @author 
     * @param payLine
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editApInvoiceLine")
    @ResponseBody
    public String editApInvoiceLine(@Valid ApInvoiceLine payLine, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        try {
            /*
            //参数校验
            Map<String, String> errorMap = this.validateParameters(bindingResult, model);
            if(errorMap.size()>0) {
                return "forward:getPayLine";
            }
            */
            
            //对当前编辑的对象初始化默认的字段
            if(payLine.getInvoiceLineId()==null) {
                payLine.setInvoiceLineCode(SnowFlake.generateId().toString());
            }
            
            //保存编辑的数据
            this.apInvoiceLineService.insertOrUpdateDataObject(payLine);
            
            return "{\"result\":\"success\"}";
        }catch(Exception e) {
            return "{\"result\":\"error\"}";
        }
        
        
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-07-19 14:16:52
     * @author 
     * @param payLine
     * @return String
     *
     */
    @RequestMapping("deleteApInvoiceLine")
    @ResponseBody
    public String deleteApInvoiceLine(ApInvoiceLine payLine, RedirectAttributes attr) {
        try {
            //删除数据前验证数据
            if(payLine!=null&&payLine.getInvoiceLineId()!=null) {
                //删除数据
                this.apInvoiceLineService.deleteDataObject(payLine);
            }
            
            return "{\"result\":\"success\"}";
        }catch(Exception e) {
            return "{\"result\":\"error\"}";
        }
    }
}
