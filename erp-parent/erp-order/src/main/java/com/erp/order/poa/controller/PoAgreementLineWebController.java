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
package com.erp.order.poa.controller;

import java.util.ArrayList;
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
import com.erp.order.po.dao.model.PoLine;
import com.erp.order.poa.dao.model.PoAgreementLine;
import com.erp.order.poa.dao.model.PoAgreementLineCO;
import com.erp.order.poa.service.PoAgreementLineService;

@Controller
@RequestMapping("/web/poAgreementLine")
public class PoAgreementLineWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(PoAgreementLineWebController.class);
    
    //服务层注入
    @Autowired
    private PoAgreementLineService poAgreementLineService;
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
     * @date 2020-10-19 17:02:18
     * @author 
     * @param pages
     * @param poAgreementLineCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getPoLineList")
    public String getPoAgreementLineList(Pages pages, PoAgreementLineCO poAgreementLineCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        
        pages.setMax(100);
        
        //分页查询数据
        List<PoAgreementLine> poAgreementLineList = new ArrayList<PoAgreementLine>();
        //根据version请求参数，判断数据从原表获取还是变更表获取
        if(poAgreementLineCO.getVersion()!=null&&poAgreementLineCO.getVersion()>0) {
//            PoLineM poLineM = new PoLineM();
//            poLineM.setPoHeadCode(poAgreementLineCO.getPoHeadCode());
//            poLineM.setVersion(poAgreementLineCO.getVersion());
//            List<PoLineM> list = this.poLineMService.getDataObjects(pages, poLineM);
//            for(PoLineM obj: list) {
//                poLineList.add(obj);
//            }
        }else {
            List<PoAgreementLine> list = this.poAgreementLineService.getPoLineListByPoHeadCode(pages, poAgreementLineCO);
            for(PoAgreementLine obj: list) {
                poAgreementLineList.add(obj);
            }
        }
        
        //物料
        Map materialMap = this.masterDataCommonService.getMaterialMap();
        //物料单位
        Map materialUnitMap = this.datasetCommonService.getMaterialUnit();
        
        //页面属性设置
        model.addAttribute("poLineList", poAgreementLineList);
        model.addAttribute("pages", pages);
        model.addAttribute("materialMap", materialMap);
        model.addAttribute("materialUnitMap", materialUnitMap);
        
        return "poa/tab/poaLineTab";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-10-19 17:02:18
     * @author 
     * @param poAgreementLine
     * @param model
     * @return String
     *
     */
    @RequestMapping("getPoLine")
    public String getPoAgreementLine(PoAgreementLine poAgreementLine, Model model) {
        //查询要编辑的数据
        if(poAgreementLine!=null&&poAgreementLine.getPoLineId()!=null) {
            poAgreementLine = this.poAgreementLineService.getDataObject(poAgreementLine.getPoLineId());
        }else {
            poAgreementLine.setAmount(0D);
        }
        
        //物料
        Map materialMap = this.masterDataCommonService.getMaterialMap();
        //物料单位
        Map materialUnitMap = this.datasetCommonService.getMaterialUnit();
        
        //页面属性设置
        model.addAttribute("poLine", poAgreementLine);
        model.addAttribute("materialMap", materialMap);
        model.addAttribute("materialUnitMap", materialUnitMap);
        
        return "poa/pop/addLineModal";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-10-19 17:02:18
     * @author 
     * @param poAgreementLine
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editPoLine")
    @ResponseBody
    public String editPoAgreementLine(@Valid @RequestBody PoAgreementLine poAgreementLine, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        try {
            /*
            //参数校验
            Map<String, String> errorMap = this.validateParameters(bindingResult, model);
            if(errorMap.size()>0) {
                return "forward:getPoLine";
            }
            */
            
            //对当前编辑的对象初始化默认的字段
            if(poAgreementLine.getPoLineId()==null) {
                poAgreementLine.setPoLineCode(SnowFlake.generateId().toString());
            }
            
            //保存编辑的数据
            this.poAgreementLineService.insertOrUpdateDataObject(poAgreementLine);
            
            return "{\"result\":\"success\"}";
        }catch(Exception e) {
            return "{\"result\":\"error\"}";
        }
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-10-19 17:02:18
     * @author 
     * @param poAgreementLine
     * @return String
     *
     */
    @RequestMapping("deletePoLine")
    @ResponseBody
    public String deletePoAgreementLine(@RequestBody PoAgreementLine poAgreementLine, RedirectAttributes attr) {
        try {
            //删除数据前验证数据
            if(poAgreementLine!=null&&poAgreementLine.getPoLineId()!=null) {
                //删除数据
                this.poAgreementLineService.deleteDataObject(poAgreementLine);
            }
            
            return "{\"result\":\"success\"}";
        }catch(Exception e) {
            return "{\"result\":\"error\"}";
        }
    }
}
