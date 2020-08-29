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
package com.erp.inv.input.controller;

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
import com.erp.inv.input.service.InvInputLineService;
import com.erp.inv.output.dao.model.InvOutputLine;
import com.erp.masterdata.common.service.MasterDataCommonService;
import com.erp.masterdata.material.dao.model.MdMaterial;
import com.erp.masterdata.material.service.MdMaterialService;
import com.erp.order.po.dao.model.PoLine;
import com.erp.order.po.dao.model.PoLineCO;
import com.erp.order.po.service.PoLineService;

@Controller
@RequestMapping("/web/invInputLine")
public class InvInputLineWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(InvInputLineWebController.class);
    
    //服务层注入
    @Autowired
    private InvInputLineService invInputLineService;
    @Autowired
    private MasterDataCommonService masterDataCommonService;
    @Autowired
    private DatasetCommonService datasetCommonService;
    @Autowired
    private PoLineService poLineService;
    
    
    
    @Override
    public String getExceptionRedirectURL() {
        // TODO Auto-generated method stub
        return null;
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-08-20 17:21:44
     * @author 
     * @param pages
     * @param invInputLineCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getInvInputLineList")
    public String getInvInputLineList(Pages pages, InvInputLineCO invInputLineCO, Model model) {
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
        List<InvInputLine> invInputLineList = this.invInputLineService.getInvInputLineListByInputHeadCode(pages, invInputLineCO);
        for(InvInputLine invInputLine: invInputLineList) {
            PoLine poLine = this.poLineService.getDataObject(invInputLine.getInputSourceLineCode());
            invInputLine.setMaterialCode(poLine.getMaterialCode());
            invInputLine.setMaterialName(materialMap.get(poLine.getMaterialCode()).toString());
            invInputLine.setPrice(poLine.getPrice());
            invInputLine.setQuantity(poLine.getQuantity());
            invInputLine.setUnit(materialUnitMap.get(poLine.getUnit()).toString());
            invInputLine.setPoLineAmount(poLine.getAmount());
            //获取物料规格
            MdMaterial mdMaterial = this.masterDataCommonService.getMdMaterialInfoCache(poLine.getMaterialCode());
            invInputLine.setMaterialStandard(mdMaterial.getStandard());
        }
        
        //页面属性设置
        model.addAttribute("invInputLineList", invInputLineList);
        model.addAttribute("pages", pages);
        
        return "invInput/tab/invInputLineTab";
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
    @RequestMapping("getSelectPOLineModal")
    public String getSelectPOLineModal(Pages pages, PoLineCO poLineCO, InvInputLineCO invInputLineCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        pages.setMax(100);
        
        //分页查询采购订单行数据
        List<PoLine> poLineList = this.poLineService.getPoLineListByPoHeadCode(pages, poLineCO);
        //获取入库行数据
        Pages pagesTemp = new Pages();
        pagesTemp.setPage(1);
        pagesTemp.setMax(100);
        List<InvInputLine> invInputLineList = this.invInputLineService.getInvInputLineListByInputHeadCode(pagesTemp, invInputLineCO);
        
        //剔除已经做了入库行的采购订单行
        Iterator<PoLine> poLineIt = poLineList.iterator();
        Iterator<InvInputLine> invInputLineIt = invInputLineList.iterator();
        while(poLineIt.hasNext()) {
            PoLine poLineTemp = poLineIt.next();
            while(invInputLineIt.hasNext()) {
                InvInputLine invInputLineTemp = invInputLineIt.next();
                if(poLineTemp.getPoLineCode().equals(invInputLineTemp.getInputSourceLineCode())) {
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
        
        return "invInput/pop/selectPOLineModal";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-08-20 17:21:44
     * @author 
     * @param invInputLine
     * @param model
     * @return String
     *
     */
    @RequestMapping("getInvInputLine")
    public String getInvInputLine(InvInputLine invInputLine, String inputSourceType, Model model) {
        //查询要编辑的数据
        if(invInputLine!=null&&invInputLine.getInputLineId()!=null) {
            invInputLine = this.invInputLineService.getDataObject(invInputLine.getInputLineId());
            //根据来源获取单据数据
            if(StringUtils.isNotBlank(inputSourceType)) {
                if(inputSourceType.equals("PO")) {
                    //物料
                    Map materialMap = this.masterDataCommonService.getMaterialMap();
                    //物料单位
                    Map materialUnitMap = this.datasetCommonService.getMaterialUnit();
                    
                    PoLine poLine = this.poLineService.getDataObject(invInputLine.getInputSourceLineCode());
                    invInputLine.setMaterialCode(poLine.getMaterialCode());
                    invInputLine.setMaterialName(materialMap.get(poLine.getMaterialCode()).toString());
                    invInputLine.setPrice(poLine.getPrice());
                    invInputLine.setQuantity(poLine.getQuantity());
                    invInputLine.setUnit(materialUnitMap.get(poLine.getUnit()).toString());
                    invInputLine.setPoLineAmount(poLine.getAmount());
                    //获取物料规格
                    MdMaterial mdMaterial = this.masterDataCommonService.getMdMaterialInfoCache(poLine.getMaterialCode());
                    invInputLine.setMaterialStandard(mdMaterial.getStandard());
                }
            }
        }else {
            //新增
            //根据来源获取单据数据
            if(StringUtils.isNotBlank(inputSourceType)) {
                if(inputSourceType.equals("PO")) {
                    if(StringUtils.isNotBlank(invInputLine.getMaterialCode())) {
                        //获取物料规格
                        MdMaterial mdMaterial = this.masterDataCommonService.getMdMaterialInfoCache(invInputLine.getMaterialCode());
                        invInputLine.setMaterialStandard(mdMaterial.getStandard());
                    }
                }
            }    
              
        }
        
        //页面属性设置
        model.addAttribute("invInputLine", invInputLine);
        
        return "invInput/pop/addLineModal";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-08-20 17:21:44
     * @author 
     * @param invInputLine
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editInvInputLine")
    @ResponseBody
    public String editInvInputLine(@Valid InvInputLine invInputLine, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        try {
            /*
            //参数校验
            Map<String, String> errorMap = this.validateParameters(bindingResult, model);
            if(errorMap.size()>0) {
                return "forward:getInvInputLine";
            }
            */
            
            //对当前编辑的对象初始化默认的字段
            if(invInputLine.getInputLineId()==null) {
                invInputLine.setInputLineCode(SnowFlake.generateId().toString());
            }
            
            //保存编辑的数据
            this.invInputLineService.insertOrUpdateDataObject(invInputLine);
            
            return "{\"result\":\"success\"}";
        }catch(Exception e) {
            return "{\"result\":\"error\"}";
        }
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-08-20 17:21:44
     * @author 
     * @param invInputLine
     * @return String
     *
     */
    @RequestMapping("deleteInvInputLine")
    @ResponseBody
    public String deleteInvInputLine(InvInputLine invInputLine, RedirectAttributes attr) {
        try {
            //删除数据前验证数据
            if(invInputLine!=null&&invInputLine.getInputLineId()!=null) {
                //删除数据
                this.invInputLineService.deleteDataObject(invInputLine);
            }
            
            return "{\"result\":\"success\"}";
        }catch(Exception e) {
            return "{\"result\":\"error\"}";
        }
    }
}
