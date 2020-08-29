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
package com.erp.masterdata.vendor.controller;

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
import com.erp.dataset.service.DatasetCommonService;
import com.erp.masterdata.vendor.dao.model.MdVendorBank;
import com.erp.masterdata.vendor.dao.model.MdVendorBankCO;
import com.erp.masterdata.vendor.service.MdVendorBankService;

@Controller
@RequestMapping("/web/mdVendorBank")
public class MdVendorBankWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(MdVendorBankWebController.class);
    
    //服务层注入
    @Autowired
    private MdVendorBankService mdVendorBankService;
    @Autowired
    private DatasetCommonService datasetCommonService;
    
    @Override
    public String getExceptionRedirectURL() {
        return "redirect:getMdVendorBankList";
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-07-14 16:06:48
     * @author 
     * @param pages
     * @param mdVendorBankCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getMdVendorBankList")
    public String getMdVendorBankList(Pages pages, MdVendorBankCO mdVendorBankCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        pages.setMax(100);
        
        //分页查询数据
        List<MdVendorBank> mdVendorBankList = this.mdVendorBankService.getBankListByVendorCode(pages, mdVendorBankCO);
        
        //获取银行
        Map bankMap = this.datasetCommonService.getBank();
        
        //页面属性设置
        model.addAttribute("mdVendorBankList", mdVendorBankList);
        model.addAttribute("pages", pages);
        model.addAttribute("bankMap", bankMap);
        
        return "mdVendor/tab/vendorBankTab";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-07-14 16:06:48
     * @author 
     * @param mdVendorBank
     * @param model
     * @return String
     *
     */
    @RequestMapping("getMdVendorBank")
    public String getMdVendorBank(MdVendorBank mdVendorBank, Model model) {
        //查询要编辑的数据
        if(mdVendorBank!=null&&mdVendorBank.getBankId()!=null) {
            mdVendorBank = this.mdVendorBankService.getDataObject(mdVendorBank.getBankId());
        }
        
        //获取银行
        Map bankMap = this.datasetCommonService.getBank();
        
        //页面属性设置
        model.addAttribute("mdVendorBank", mdVendorBank);
        model.addAttribute("bankMap", bankMap);
        
        return "mdVendor/pop/addBankModal";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-07-14 16:06:48
     * @author 
     * @param mdVendorBank
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editMdVendorBank")
    @ResponseBody
    public String editMdVendorBank(@Valid @RequestBody MdVendorBank mdVendorBank, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        try {
            /*
            //参数校验
            Map<String, String> errorMap = this.validateParameters(bindingResult, model);
            if(errorMap.size()>0) {
                return "forward:getMdVendorBank";
            }
            */
            
            //对当前编辑的对象初始化默认的字段
            
            //保存编辑的数据
            this.mdVendorBankService.insertOrUpdateDataObject(mdVendorBank);
            
            return "{\"result\":\"success\"}";
        }catch(Exception e) {
            return "{\"result\":\"error\"}";
        }
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-07-14 16:06:48
     * @author 
     * @param mdVendorBank
     * @return String
     *
     */
    @RequestMapping("deleteMdVendorBank")
    @ResponseBody
    public String deleteMdVendorBank(@RequestBody MdVendorBank mdVendorBank, RedirectAttributes attr) {
        try {
            //删除数据前验证数据
            if(mdVendorBank!=null&&mdVendorBank.getBankId()!=null) {
                //删除数据
                this.mdVendorBankService.deleteDataObject(mdVendorBank);
            }
            
            return "{\"result\":\"success\"}";
        }catch(Exception e) {
            return "{\"result\":\"error\"}";
        }
    }
}
