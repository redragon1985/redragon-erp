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
package com.erp.finance.receipt.controller;

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
import com.erp.finance.pay.dao.model.PayLine;
import com.erp.finance.pay.dao.model.PayLineCO;
import com.erp.finance.receipt.dao.model.ReceiptLine;
import com.erp.finance.receipt.dao.model.ReceiptLineCO;
import com.erp.finance.receipt.service.ReceiptLineService;
import com.erp.masterdata.common.service.MasterDataCommonService;
import com.erp.order.po.dao.model.PoLine;
import com.erp.order.po.dao.model.PoLineCO;
import com.erp.order.po.service.PoLineService;
import com.erp.order.so.dao.model.SoLine;
import com.erp.order.so.dao.model.SoLineCO;
import com.erp.order.so.service.SoLineService;

@Controller
@RequestMapping("/web/receiptLine")
public class ReceiptLineWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(ReceiptLineWebController.class);
    
    //服务层注入
    @Autowired
    private ReceiptLineService receiptLineService;
    @Autowired
    private MasterDataCommonService masterDataCommonService;
    @Autowired
    private DatasetCommonService datasetCommonService;
    @Autowired
    private SoLineService soLineService;
    
    @Override
    public String getExceptionRedirectURL() {
        return "redirect:getReceiptLineList";
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-07-22 12:49:10
     * @author 
     * @param pages
     * @param receiptLineCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getReceiptLineList")
    public String getReceiptLineList(Pages pages, ReceiptLineCO receiptLineCO, Model model) {
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
        List<ReceiptLine> receiptLineList = this.receiptLineService.getReceiptLineListByReceiptHeadCode(pages, receiptLineCO);
        for(ReceiptLine receiptLine: receiptLineList) {
            SoLine soLine = this.soLineService.getDataObject(receiptLine.getReceiptSourceLineCode());
            receiptLine.setMaterialCode(soLine.getMaterialCode());
            receiptLine.setMaterialName(materialMap.get(soLine.getMaterialCode()).toString());
            receiptLine.setPrice(soLine.getPrice());
            receiptLine.setQuantity(soLine.getQuantity());
            receiptLine.setUnit(materialUnitMap.get(soLine.getUnit()).toString());
            receiptLine.setSoLineAmount(soLine.getAmount());
        }
        
        //页面属性设置
        model.addAttribute("receiptLineList", receiptLineList);
        model.addAttribute("pages", pages);
        
        return "receipt/tab/receiptLineTab";
    }
    
    
    
    /**
     * 
     * @description 获取销售订单选择框
     * @date 2020年7月20日
     * @author dongbin
     * @return
     * @return String
     *
     */
    @RequestMapping("getSelectSOLineModal")
    public String getSelectSOLineModal(Pages pages, SoLineCO soLineCO, ReceiptLineCO receiptLineCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        pages.setMax(100);
        
        //分页查询销售订单行数据
        List<SoLine> soLineList = this.soLineService.getSoLineListBySoHeadCode(pages, soLineCO);
        //获取付款行数据
        Pages pagesTemp = new Pages();
        pagesTemp.setPage(1);
        pagesTemp.setMax(100);
        List<ReceiptLine> receiptLineList = this.receiptLineService.getReceiptLineListByReceiptHeadCode(pagesTemp, receiptLineCO);
        
        //剔除已经做了付款行的采购订单行
        Iterator<SoLine> soLineIt = soLineList.iterator();
        Iterator<ReceiptLine> receiptLineIt = receiptLineList.iterator();
        while(soLineIt.hasNext()) {
            SoLine soLineTemp = soLineIt.next();
            while(receiptLineIt.hasNext()) {
                ReceiptLine receiptLineTemp = receiptLineIt.next();
                if(soLineTemp.getSoLineCode().equals(receiptLineTemp.getReceiptSourceLineCode())) {
                    soLineIt.remove();
                    break;
                }
            }
        }
        //重置总数据数量
        pages.setDataNumber(soLineList.size());
        
        //物料
        Map materialMap = this.masterDataCommonService.getMaterialMap();
        //物料单位
        Map materialUnitMap = this.datasetCommonService.getMaterialUnit();
        
        //页面属性设置
        model.addAttribute("soLineList", soLineList);
        model.addAttribute("pages", pages);
        model.addAttribute("materialMap", materialMap);
        model.addAttribute("materialUnitMap", materialUnitMap);
        
        return "receipt/pop/selectSOLineModal";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-07-22 12:49:10
     * @author 
     * @param receiptLine
     * @param model
     * @return String
     *
     */
    @RequestMapping("getReceiptLine")
    public String getReceiptLine(ReceiptLine receiptLine, String receiptSourceType, Model model) {
      //查询要编辑的数据
        if(receiptLine!=null&&receiptLine.getReceiptLineId()!=null) {
            receiptLine = this.receiptLineService.getDataObject(receiptLine.getReceiptLineId());
            //根据来源获取单据数据
            if(StringUtils.isNotBlank(receiptSourceType)) {
                if(receiptSourceType.equals("SO")) {
                    //物料
                    Map materialMap = this.masterDataCommonService.getMaterialMap();
                    //物料单位
                    Map materialUnitMap = this.datasetCommonService.getMaterialUnit();
                    
                    SoLine soLine = this.soLineService.getDataObject(receiptLine.getReceiptSourceLineCode());
                    receiptLine.setMaterialCode(soLine.getMaterialCode());
                    receiptLine.setMaterialName(materialMap.get(soLine.getMaterialCode()).toString());
                    receiptLine.setPrice(soLine.getPrice());
                    receiptLine.setQuantity(soLine.getQuantity());
                    receiptLine.setUnit(materialUnitMap.get(soLine.getUnit()).toString());
                    receiptLine.setSoLineAmount(soLine.getAmount());
                }
            }
        }else {
            //receiptLine.setAmount(0D);
        }
        
        //页面属性设置
        model.addAttribute("receiptLine", receiptLine);
        
        return "receipt/pop/addLineModal";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-07-22 12:49:10
     * @author 
     * @param receiptLine
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editReceiptLine")
    @ResponseBody
    public String editReceiptLine(@Valid ReceiptLine receiptLine, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        try {
            /*
            //参数校验
            Map<String, String> errorMap = this.validateParameters(bindingResult, model);
            if(errorMap.size()>0) {
                return "forward:getReceiptLine";
            }
            */
            
            //对当前编辑的对象初始化默认的字段
            if(receiptLine.getReceiptLineId()==null) {
                receiptLine.setReceiptLineCode(SnowFlake.generateId().toString());
            }
            
            //保存编辑的数据
            this.receiptLineService.insertOrUpdateDataObject(receiptLine);
            
            return "{\"result\":\"success\"}";
        }catch(Exception e) {
            return "{\"result\":\"error\"}";
        }
        
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-07-22 12:49:10
     * @author 
     * @param receiptLine
     * @return String
     *
     */
    @RequestMapping("deleteReceiptLine")
    @ResponseBody
    public String deleteReceiptLine(ReceiptLine receiptLine, RedirectAttributes attr) {
        try {
            //删除数据前验证数据
            if(receiptLine!=null&&receiptLine.getReceiptLineId()!=null) {
                //删除数据
                this.receiptLineService.deleteDataObject(receiptLine);
            }
            
            return "{\"result\":\"success\"}";
        }catch(Exception e) {
            return "{\"result\":\"error\"}";
        }
        
    }
}
