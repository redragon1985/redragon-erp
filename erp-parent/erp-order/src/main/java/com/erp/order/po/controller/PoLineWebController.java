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
package com.erp.order.po.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.controller.ControllerSupport;
import com.framework.dao.model.Pages;


import com.erp.dataset.service.DatasetCommonService;
import com.erp.masterdata.common.service.MasterDataCommonService;
import com.erp.order.po.dao.model.PoLine;
import com.erp.order.po.dao.model.PoLineCO;
import com.erp.order.po.service.PoLineService;
import redragon.basic.tools.SnowFlake;

@Controller
@RequestMapping("/web/poLine")
public class PoLineWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(PoLineWebController.class);
    
    //服务层注入
    @Autowired
    private PoLineService poLineService;
    @Autowired
    private MasterDataCommonService masterDataCommonService;
    @Autowired
    private DatasetCommonService datasetCommonService;
    
    @Override
    public String getExceptionRedirectURL() {
        return "redirect:getPoLineList";
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-07-15 15:57:23
     * @author 
     * @param pages
     * @param poLineCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getPoLineList")
    public String getPoLineList(Pages pages, PoLineCO poLineCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        
        pages.setMax(100);
        
        //分页查询数据
        List<PoLine> poLineList = this.poLineService.getPoLineListByPoHeadCode(pages, poLineCO);
        
        //物料
        Map materialMap = this.masterDataCommonService.getMaterialMap();
        //物料单位
        Map materialUnitMap = this.datasetCommonService.getMaterialUnit();
        
        //页面属性设置
        model.addAttribute("poLineList", poLineList);
        model.addAttribute("pages", pages);
        model.addAttribute("materialMap", materialMap);
        model.addAttribute("materialUnitMap", materialUnitMap);
        
        return "po/tab/poLineTab";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-07-15 15:57:23
     * @author 
     * @param poLine
     * @param model
     * @return String
     *
     */
    @RequestMapping("getPoLine")
    public String getPoLine(PoLine poLine, Model model) {
        //查询要编辑的数据
        if(poLine!=null&&poLine.getPoLineId()!=null) {
            poLine = this.poLineService.getDataObject(poLine.getPoLineId());
        }else {
            poLine.setAmount(0D);
        }
        
        //物料
        Map materialMap = this.masterDataCommonService.getMaterialMap();
        //物料单位
        Map materialUnitMap = this.datasetCommonService.getMaterialUnit();
        
        //页面属性设置
        model.addAttribute("poLine", poLine);
        model.addAttribute("materialMap", materialMap);
        model.addAttribute("materialUnitMap", materialUnitMap);
        
        return "po/pop/addLineModal";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-07-15 15:57:23
     * @author 
     * @param poLine
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editPoLine")
    @ResponseBody
    public String editPoLine(@Valid @RequestBody PoLine poLine, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        try {
            /*
            //参数校验
            Map<String, String> errorMap = this.validateParameters(bindingResult, model);
            if(errorMap.size()>0) {
                return "forward:getPoLine";
            }
            */
            
            //对当前编辑的对象初始化默认的字段
            if(poLine.getPoLineId()==null) {
                poLine.setPoLineCode(SnowFlake.generateId().toString());
            }
            
            //保存编辑的数据
            this.poLineService.insertOrUpdateDataObject(poLine);
            
            return "{\"result\":\"success\"}";
        }catch(Exception e) {
            return "{\"result\":\"error\"}";
        }
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-07-15 15:57:23
     * @author 
     * @param poLine
     * @return String
     *
     */
    @RequestMapping("deletePoLine")
    @ResponseBody
    public String deletePoLine(@RequestBody PoLine poLine, RedirectAttributes attr) {
        try {
            //删除数据前验证数据
            if(poLine!=null&&poLine.getPoLineId()!=null) {
                //删除数据
                this.poLineService.deleteDataObject(poLine);
            }
            
            return "{\"result\":\"success\"}";
        }catch(Exception e) {
            return "{\"result\":\"error\"}";
        }
    }
}
