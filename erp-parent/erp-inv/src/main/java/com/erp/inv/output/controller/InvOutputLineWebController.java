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
package com.erp.inv.output.controller;

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
import com.erp.inv.input.dao.model.InvInputLine;
import com.erp.inv.input.dao.model.InvInputLineCO;
import com.erp.inv.output.dao.model.InvOutputLine;
import com.erp.inv.output.dao.model.InvOutputLineCO;
import com.erp.inv.output.service.InvOutputLineService;
import com.erp.inv.stock.service.InvStockService;
import com.erp.masterdata.common.service.MasterDataCommonService;
import com.erp.masterdata.material.dao.model.MdMaterial;
import com.erp.masterdata.material.service.MdMaterialService;
import com.erp.order.po.dao.model.PoLine;
import com.erp.order.po.dao.model.PoLineCO;
import com.erp.order.po.service.PoLineService;
import com.erp.order.so.dao.model.SoLine;
import com.erp.order.so.dao.model.SoLineCO;
import com.erp.order.so.service.SoLineService;

@Controller
@RequestMapping("/web/invOutputLine")
public class InvOutputLineWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(InvOutputLineWebController.class);
    
    //服务层注入
    @Autowired
    private InvOutputLineService invOutputLineService;
    @Autowired
    private MasterDataCommonService masterDataCommonService;
    @Autowired
    private DatasetCommonService datasetCommonService;
    @Autowired
    private SoLineService soLineService;
    @Autowired
    private InvStockService invStockService;
    
    
    
    @Override
    public String getExceptionRedirectURL() {
        // TODO Auto-generated method stub
        return null;
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-08-20 17:23:08
     * @author 
     * @param pages
     * @param invOutputLineCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getInvOutputLineList")
    public String getInvOutputLineList(Pages pages, InvOutputLineCO invOutputLineCO, Model model) {
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
        List<InvOutputLine> invOutputLineList = this.invOutputLineService.getInvOutputLineListByOutputHeadCode(pages, invOutputLineCO);
        for(InvOutputLine invOutputLine: invOutputLineList) {
            SoLine soLine = this.soLineService.getDataObject(invOutputLine.getOutputSourceLineCode());
            invOutputLine.setMaterialCode(soLine.getMaterialCode());
            invOutputLine.setMaterialName(materialMap.get(soLine.getMaterialCode()).toString());
            invOutputLine.setPrice(soLine.getPrice());
            invOutputLine.setQuantity(soLine.getQuantity());
            invOutputLine.setUnit(materialUnitMap.get(soLine.getUnit()).toString());
            invOutputLine.setSoLineAmount(soLine.getAmount());
            //获取物料规格
            MdMaterial mdMaterial = this.masterDataCommonService.getMdMaterialInfoCache(soLine.getMaterialCode());
            invOutputLine.setMaterialStandard(mdMaterial.getStandard());
        }
        
        //页面属性设置
        model.addAttribute("invOutputLineList", invOutputLineList);
        model.addAttribute("pages", pages);
        
        return "invOutput/tab/invOutputLineTab";
    }
    
    
    
    /**
     * 
     * @description 获取采购订单行选择框
     * @date 2020年7月20日
     * @author dongbin
     * @return
     * @return String
     *
     */
    @RequestMapping("getSelectSOLineModal")
    public String getSelectSOLineModal(Pages pages, SoLineCO soLineCO, InvOutputLineCO invOutputLineCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        pages.setMax(100);
        
        //分页查询采购订单行数据
        List<SoLine> soLineList = this.soLineService.getSoLineListBySoHeadCode(pages, soLineCO);
        //获取入库行数据
        Pages pagesTemp = new Pages();
        pagesTemp.setPage(1);
        pagesTemp.setMax(100);
        List<InvOutputLine> invOutputLineList = this.invOutputLineService.getInvOutputLineListByOutputHeadCode(pagesTemp, invOutputLineCO);
        
        //剔除已经做了入库行的采购订单行
        Iterator<SoLine> soLineIt = soLineList.iterator();
        Iterator<InvOutputLine> invOutputLineIt = invOutputLineList.iterator();
        while(soLineIt.hasNext()) {
            SoLine soLineTemp = soLineIt.next();
            while(invOutputLineIt.hasNext()) {
                InvOutputLine invOutputLineTemp = invOutputLineIt.next();
                if(soLineTemp.getSoLineCode().equals(invOutputLineTemp.getOutputSourceLineCode())) {
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
        
        return "invOutput/pop/selectSOLineModal";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-08-20 17:23:08
     * @author 
     * @param invOutputLine
     * @param model
     * @return String
     *
     */
    @RequestMapping("getInvOutputLine")
    public String getInvOutputLine(InvOutputLine invOutputLine, String outputSourceType, Model model) {
        //查询要编辑的数据
        if(invOutputLine!=null&&invOutputLine.getOutputLineId()!=null) {
            invOutputLine = this.invOutputLineService.getDataObject(invOutputLine.getOutputLineId());
            //根据来源获取单据数据
            if(StringUtils.isNotBlank(outputSourceType)) {
                if(outputSourceType.equals("SO")) {
                    //物料
                    Map materialMap = this.masterDataCommonService.getMaterialMap();
                    //物料单位
                    Map materialUnitMap = this.datasetCommonService.getMaterialUnit();
                    
                    SoLine soLine = this.soLineService.getDataObject(invOutputLine.getOutputSourceLineCode());
                    invOutputLine.setMaterialCode(soLine.getMaterialCode());
                    invOutputLine.setMaterialName(materialMap.get(soLine.getMaterialCode()).toString());
                    invOutputLine.setPrice(soLine.getPrice());
                    invOutputLine.setQuantity(soLine.getQuantity());
                    invOutputLine.setUnit(materialUnitMap.get(soLine.getUnit()).toString());
                    invOutputLine.setSoLineAmount(soLine.getAmount());
                    //获取物料规格
                    MdMaterial mdMaterial = this.masterDataCommonService.getMdMaterialInfoCache(soLine.getMaterialCode());
                    invOutputLine.setMaterialStandard(mdMaterial.getStandard());
                }
            }
        }else {
            //新增
            //根据来源获取单据数据
            if(StringUtils.isNotBlank(outputSourceType)) {
                if(outputSourceType.equals("SO")) {
                    if(StringUtils.isNotBlank(invOutputLine.getMaterialCode())) {
                        //获取物料规格
                        MdMaterial mdMaterial = this.masterDataCommonService.getMdMaterialInfoCache(invOutputLine.getMaterialCode());
                        invOutputLine.setMaterialStandard(mdMaterial.getStandard());
                    }
                }
            }    
              
        }
        
        //获取物料库存
        Double stockNumber = 0D;
        if(invOutputLine!=null&&StringUtils.isNotBlank(invOutputLine.getMaterialCode())) {
            stockNumber = this.invStockService.getStockNumberByMaterialCode(invOutputLine.getMaterialCode());
        }
        
        
        //页面属性设置
        model.addAttribute("invOutputLine", invOutputLine);
        model.addAttribute("stockNumber", stockNumber);
        
        return "invOutput/pop/addLineModal";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-08-20 17:23:08
     * @author 
     * @param invOutputLine
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editInvOutputLine")
    @ResponseBody
    public String editInvOutputLine(@Valid InvOutputLine invOutputLine, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        try {
            /*
            //参数校验
            Map<String, String> errorMap = this.validateParameters(bindingResult, model);
            if(errorMap.size()>0) {
                return "forward:getInvInputLine";
            }
            */
            
            //对当前编辑的对象初始化默认的字段
            if(invOutputLine.getOutputLineId()==null) {
                invOutputLine.setOutputLineCode(SnowFlake.generateId().toString());
            }
            
            //保存编辑的数据
            this.invOutputLineService.insertOrUpdateDataObject(invOutputLine);
            
            return "{\"result\":\"success\"}";
        }catch(Exception e) {
            return "{\"result\":\"error\"}";
        }
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-08-20 17:23:08
     * @author 
     * @param invOutputLine
     * @return String
     *
     */
    @RequestMapping("deleteInvOutputLine")
    @ResponseBody
    public String deleteInvOutputLine(InvOutputLine invOutputLine, RedirectAttributes attr) {
        try {
            //删除数据前验证数据
            if(invOutputLine!=null&&invOutputLine.getOutputLineId()!=null) {
                //删除数据
                this.invOutputLineService.deleteDataObject(invOutputLine);
            }
            
            return "{\"result\":\"success\"}";
        }catch(Exception e) {
            return "{\"result\":\"error\"}";
        }
    }
}
