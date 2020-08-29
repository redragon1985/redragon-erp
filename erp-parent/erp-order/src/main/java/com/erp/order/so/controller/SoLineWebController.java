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
package com.erp.order.so.controller;

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
import com.framework.util.JsonResultUtil;
import com.framework.util.JsonUtil;

import redragon.frame.hibernate.SnowFlake;

import com.erp.dataset.service.DatasetCommonService;
import com.erp.masterdata.common.service.MasterDataCommonService;
import com.erp.order.so.dao.model.SoLine;
import com.erp.order.so.dao.model.SoLineCO;
import com.erp.order.so.service.SoLineService;

@Controller
@RequestMapping("/web/soLine")
public class SoLineWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(SoLineWebController.class);
    
    //服务层注入
    @Autowired
    private SoLineService soLineService;
    @Autowired
    private MasterDataCommonService masterDataCommonService;
    @Autowired
    private DatasetCommonService datasetCommonService;
    
    @Override
    public String getExceptionRedirectURL() {
        return "redirect:getSoLineList";
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-07-17 20:16:18
     * @author 
     * @param pages
     * @param soLineCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getSoLineList")
    public String getSoLineList(Pages pages, SoLineCO soLineCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        pages.setMax(100);
        
        //分页查询数据
        List<SoLine> soLineList = this.soLineService.getSoLineListBySoHeadCode(pages, soLineCO);
        
        //物料
        Map materialMap = this.masterDataCommonService.getMaterialMap();
        //物料单位
        Map materialUnitMap = this.datasetCommonService.getMaterialUnit();
        
        //页面属性设置
        model.addAttribute("soLineList", soLineList);
        model.addAttribute("pages", pages);
        model.addAttribute("materialMap", materialMap);
        model.addAttribute("materialUnitMap", materialUnitMap);
        
        return "so/tab/soLineTab";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-07-17 20:16:18
     * @author 
     * @param soLine
     * @param model
     * @return String
     *
     */
    @RequestMapping("getSoLine")
    public String getSoLine(SoLine soLine, Model model) {
        //查询要编辑的数据
        if(soLine!=null&&soLine.getSoLineId()!=null) {
            soLine = this.soLineService.getDataObject(soLine.getSoLineId());
        }else {
            soLine.setAmount(0D);
        }
        
        //物料
        Map materialMap = this.masterDataCommonService.getMaterialMap();
        //物料单位
        Map materialUnitMap = this.datasetCommonService.getMaterialUnit();
        
        //页面属性设置
        model.addAttribute("soLine", soLine);
        model.addAttribute("materialMap", materialMap);
        model.addAttribute("materialUnitMap", materialUnitMap);
        
        return "so/pop/addLineModal";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-07-17 20:16:18
     * @author 
     * @param soLine
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editSoLine")
    @ResponseBody
    public String editSoLine(@Valid @RequestBody SoLine soLine, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        try {
            /*
            //参数校验
            Map<String, String> errorMap = this.validateParameters(bindingResult, model);
            if(errorMap.size()>0) {
                return "forward:getSoLine";
            }
            */
            
            //对当前编辑的对象初始化默认的字段
            if(soLine.getSoLineId()==null) {
                soLine.setSoLineCode(SnowFlake.generateId().toString());
            }
            
            //保存编辑的数据
            this.soLineService.insertOrUpdateDataObject(soLine);
            
            return "{\"result\":\"success\"}";
        }catch(Exception e) {
            return "{\"result\":\"error\"}";
        }
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-07-17 20:16:18
     * @author 
     * @param soLine
     * @return String
     *
     */
    @RequestMapping("deleteSoLine")
    @ResponseBody
    public String deleteSoLine(@RequestBody SoLine soLine, RedirectAttributes attr) {
        try {
            //删除数据前验证数据
            if(soLine!=null&&soLine.getSoLineId()!=null) {
                //删除数据
                this.soLineService.deleteDataObject(soLine);
            }
            
            return "{\"result\":\"success\"}";
        }catch(Exception e) {
            return "{\"result\":\"error\"}";
        }
    }
}
