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
package com.erp.order.soa.controller;

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
import com.erp.order.so.dao.model.SoLine;
import com.erp.order.soa.dao.model.SoAgreementLine;
import com.erp.order.soa.dao.model.SoAgreementLineCO;
import com.erp.order.soa.service.SoAgreementLineService;

@Controller
@RequestMapping("/web/soAgreementLine")
public class SoAgreementLineWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(SoAgreementLineWebController.class);
    
    //服务层注入
    @Autowired
    private SoAgreementLineService soAgreementLineService;
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
     * @date 2020-10-19 17:03:49
     * @author 
     * @param pages
     * @param soAgreementLineCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getSoLineList")
    public String getSoAgreementLineList(Pages pages, SoAgreementLineCO soAgreementLineCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        pages.setMax(100);
        
        //分页查询数据
        List<SoAgreementLine> soAgreementLineList = new ArrayList<SoAgreementLine>();
        //根据version请求参数，判断数据从原表获取还是变更表获取
        if(soAgreementLineCO.getVersion()!=null&&soAgreementLineCO.getVersion()>0) {
//            SoLineM soLineM = new SoLineM();
//            soLineM.setSoHeadCode(soAgreementLineCO.getSoHeadCode());
//            soLineM.setVersion(soAgreementLineCO.getVersion());
//            List<SoLineM> list = this.soLineMService.getDataObjects(pages, soLineM);
//            for(SoLineM obj: list) {
//                soAgreementLineList.add(obj);
//            }
        }else {
            List<SoAgreementLine> list = this.soAgreementLineService.getSoLineListBySoHeadCode(pages, soAgreementLineCO);
            for(SoAgreementLine obj: list) {
                soAgreementLineList.add(obj);
            }
        }
        
        //物料
        Map materialMap = this.masterDataCommonService.getMaterialMap();
        //物料单位
        Map materialUnitMap = this.datasetCommonService.getMaterialUnit();
        
        //页面属性设置
        model.addAttribute("soLineList", soAgreementLineList);
        model.addAttribute("pages", pages);
        model.addAttribute("materialMap", materialMap);
        model.addAttribute("materialUnitMap", materialUnitMap);
        
        return "soa/tab/soaLineTab";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-10-19 17:03:49
     * @author 
     * @param soAgreementLine
     * @param model
     * @return String
     *
     */
    @RequestMapping("getSoLine")
    public String getSoAgreementLine(SoAgreementLine soAgreementLine, Model model) {
        //查询要编辑的数据
        if(soAgreementLine!=null&&soAgreementLine.getSoLineId()!=null) {
            soAgreementLine = this.soAgreementLineService.getDataObject(soAgreementLine.getSoLineId());
        }else {
            soAgreementLine.setAmount(0D);
        }
        
        //物料
        Map materialMap = this.masterDataCommonService.getMaterialMap();
        //物料单位
        Map materialUnitMap = this.datasetCommonService.getMaterialUnit();
        
        //页面属性设置
        model.addAttribute("soLine", soAgreementLine);
        model.addAttribute("materialMap", materialMap);
        model.addAttribute("materialUnitMap", materialUnitMap);
        
        return "soa/pop/addLineModal";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-10-19 17:03:49
     * @author 
     * @param soAgreementLine
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editSoLine")
    @ResponseBody
    public String editSoAgreementLine(@Valid @RequestBody SoAgreementLine soAgreementLine, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        try {
            /*
            //参数校验
            Map<String, String> errorMap = this.validateParameters(bindingResult, model);
            if(errorMap.size()>0) {
                return "forward:getSoLine";
            }
            */
            
            //对当前编辑的对象初始化默认的字段
            if(soAgreementLine.getSoLineId()==null) {
                soAgreementLine.setSoLineCode(SnowFlake.generateId().toString());
            }
            
            //保存编辑的数据
            this.soAgreementLineService.insertOrUpdateDataObject(soAgreementLine);
            
            return "{\"result\":\"success\"}";
        }catch(Exception e) {
            return "{\"result\":\"error\"}";
        }
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-10-19 17:03:49
     * @author 
     * @param soAgreementLine
     * @return String
     *
     */
    @RequestMapping("deleteSoLine")
    @ResponseBody
    public String deleteSoAgreementLine(@RequestBody SoAgreementLine soAgreementLine, RedirectAttributes attr) {
        try {
            //删除数据前验证数据
            if(soAgreementLine!=null&&soAgreementLine.getSoLineId()!=null) {
                //删除数据
                this.soAgreementLineService.deleteDataObject(soAgreementLine);
            }
            
            return "{\"result\":\"success\"}";
        }catch(Exception e) {
            return "{\"result\":\"error\"}";
        }
    }
}
