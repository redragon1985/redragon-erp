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
package com.erp.finance.pay.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.controller.ControllerSupport;
import com.framework.dao.model.Pages;
import com.framework.util.JsonResultUtil;
import com.framework.util.JsonUtil;

import redragon.frame.hibernate.SnowFlake;

import com.erp.dataset.service.DatasetCommonService;
import com.erp.finance.pay.dao.model.PayLine;
import com.erp.finance.pay.dao.model.PayLineCO;
import com.erp.finance.pay.service.PayLineService;
import com.erp.masterdata.common.service.MasterDataCommonService;
import com.erp.order.po.dao.model.PoHead;
import com.erp.order.po.dao.model.PoHeadCO;
import com.erp.order.po.dao.model.PoLine;
import com.erp.order.po.dao.model.PoLineCO;
import com.erp.order.po.service.PoLineService;

@Controller
@RequestMapping("/web/payLine")
public class PayLineWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(PayLineWebController.class);
    
    //服务层注入
    @Autowired
    private PayLineService payLineService;
    @Autowired
    private MasterDataCommonService masterDataCommonService;
    @Autowired
    private DatasetCommonService datasetCommonService;
    @Autowired
    private PoLineService poLineService;
    
    @Override
    public String getExceptionRedirectURL() {
        return "redirect:getPayLineList";
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
    @RequestMapping("getPayLineList")
    public String getPayLineList(Pages pages, PayLineCO payLineCO, Model model) {
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
        List<PayLine> payLineList = this.payLineService.getPayLineListByPayHeadCode(pages, payLineCO);
        for(PayLine payLine: payLineList) {
            PoLine poLine = this.poLineService.getDataObject(payLine.getPaySourceLineCode());
            payLine.setMaterialCode(poLine.getMaterialCode());
            payLine.setMaterialName(materialMap.get(poLine.getMaterialCode()).toString());
            payLine.setPrice(poLine.getPrice());
            payLine.setQuantity(poLine.getQuantity());
            payLine.setUnit(materialUnitMap.get(poLine.getUnit()).toString());
            payLine.setPoLineAmount(poLine.getAmount());
        }
        
        
        
        //页面属性设置
        model.addAttribute("payLineList", payLineList);
        model.addAttribute("pages", pages);
        
        return "pay/tab/payLineTab";
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
    public String getSelectPOLineModal(Pages pages, PoLineCO poLineCO, PayLineCO payLineCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        pages.setMax(100);
        
        //分页查询采购订单行数据
        List<PoLine> poLineList = this.poLineService.getPoLineListByPoHeadCode(pages, poLineCO);
        //获取付款行数据
        Pages pagesTemp = new Pages();
        pagesTemp.setPage(1);
        pagesTemp.setMax(100);
        List<PayLine> payLineList = this.payLineService.getPayLineListByPayHeadCode(pagesTemp, payLineCO);
        
        //剔除已经做了付款行的采购订单行
        Iterator<PoLine> poLineIt = poLineList.iterator();
        Iterator<PayLine> payLineIt = payLineList.iterator();
        while(poLineIt.hasNext()) {
            PoLine poLineTemp = poLineIt.next();
            while(payLineIt.hasNext()) {
                PayLine payLineTemp = payLineIt.next();
                if(poLineTemp.getPoLineCode().equals(payLineTemp.getPaySourceLineCode())) {
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
        
        return "pay/pop/selectPOLineModal";
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
    @RequestMapping("getPayLine")
    public String getPayLine(PayLine payLine, String paySourceType, Model model) {
        //查询要编辑的数据
        if(payLine!=null&&payLine.getPayLineId()!=null) {
            payLine = this.payLineService.getDataObject(payLine.getPayLineId());
            //根据来源获取单据数据
            if(StringUtils.isNotBlank(paySourceType)) {
                if(paySourceType.equals("PO")) {
                    //物料
                    Map materialMap = this.masterDataCommonService.getMaterialMap();
                    //物料单位
                    Map materialUnitMap = this.datasetCommonService.getMaterialUnit();
                    
                    PoLine poLine = this.poLineService.getDataObject(payLine.getPaySourceLineCode());
                    payLine.setMaterialCode(poLine.getMaterialCode());
                    payLine.setMaterialName(materialMap.get(poLine.getMaterialCode()).toString());
                    payLine.setPrice(poLine.getPrice());
                    payLine.setQuantity(poLine.getQuantity());
                    payLine.setUnit(materialUnitMap.get(poLine.getUnit()).toString());
                    payLine.setPoLineAmount(poLine.getAmount());
                }
            }
        }else {
            //payLine.setAmount(0D);
        }
        
        //页面属性设置
        model.addAttribute("payLine", payLine);
        
        return "pay/pop/addLineModal";
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
    @RequestMapping("editPayLine")
    @ResponseBody
    public String editPayLine(@Valid PayLine payLine, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        try {
            /*
            //参数校验
            Map<String, String> errorMap = this.validateParameters(bindingResult, model);
            if(errorMap.size()>0) {
                return "forward:getPayLine";
            }
            */
            
            //对当前编辑的对象初始化默认的字段
            if(payLine.getPayLineId()==null) {
                payLine.setPayLineCode(SnowFlake.generateId().toString());
            }
            
            //保存编辑的数据
            this.payLineService.insertOrUpdateDataObject(payLine);
            
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
    @RequestMapping("deletePayLine")
    @ResponseBody
    public String deletePayLine(PayLine payLine, RedirectAttributes attr) {
        try {
            //删除数据前验证数据
            if(payLine!=null&&payLine.getPayLineId()!=null) {
                //删除数据
                this.payLineService.deleteDataObject(payLine);
            }
            
            return "{\"result\":\"success\"}";
        }catch(Exception e) {
            return "{\"result\":\"error\"}";
        }
    }
}
