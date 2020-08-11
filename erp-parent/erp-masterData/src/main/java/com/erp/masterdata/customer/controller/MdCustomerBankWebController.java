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
package com.erp.masterdata.customer.controller;

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
import com.erp.masterdata.customer.dao.model.MdCustomerBank;
import com.erp.masterdata.customer.dao.model.MdCustomerBankCO;
import com.erp.masterdata.customer.service.MdCustomerBankService;

@Controller
@RequestMapping("/web/mdCustomerBank")
public class MdCustomerBankWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(MdCustomerBankWebController.class);
    
    //服务层注入
    @Autowired
    private MdCustomerBankService mdCustomerBankService;
    @Autowired
    private DatasetCommonService datasetCommonService;
    
    @Override
    public String getExceptionRedirectURL() {
        return "redirect:getMdCustomerBankList";
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-07-11 20:59:32
     * @author 
     * @param pages
     * @param mdCustomerBankCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getMdCustomerBankList")
    public String getMdCustomerBankList(Pages pages, MdCustomerBankCO mdCustomerBankCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        pages.setMax(100);
        
        //分页查询数据
        List<MdCustomerBank> mdCustomerBankList = this.mdCustomerBankService.getBankListByCustomerCode(pages, mdCustomerBankCO);
        
        //获取银行
        Map bankMap = this.datasetCommonService.getBank();
        
        //页面属性设置
        model.addAttribute("mdCustomerBankList", mdCustomerBankList);
        model.addAttribute("pages", pages);
        model.addAttribute("bankMap", bankMap);
        
        return "mdCustomer/tab/customerBankTab";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-07-11 20:59:32
     * @author 
     * @param mdCustomerBank
     * @param model
     * @return String
     *
     */
    @RequestMapping("getMdCustomerBank")
    public String getMdCustomerBank(MdCustomerBank mdCustomerBank, Model model) {
        //查询要编辑的数据
        if(mdCustomerBank!=null&&mdCustomerBank.getBankId()!=null) {
            mdCustomerBank = this.mdCustomerBankService.getDataObject(mdCustomerBank.getBankId());
        }
        
        //获取银行
        Map bankMap = this.datasetCommonService.getBank();
        
        //页面属性设置
        model.addAttribute("mdCustomerBank", mdCustomerBank);
        model.addAttribute("bankMap", bankMap);
        
        return "mdCustomer/pop/addBankModal";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-07-11 20:59:32
     * @author 
     * @param mdCustomerBank
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editMdCustomerBank")
    @ResponseBody
    public String editMdCustomerBank(@Valid @RequestBody MdCustomerBank mdCustomerBank, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        try {
            /*
            //参数校验
            Map<String, String> errorMap = this.validateParameters(bindingResult, model);
            if(errorMap.size()>0) {
                return "forward:getMdCustomerBank";
            }
            */
            
            //对当前编辑的对象初始化默认的字段
            
            //保存编辑的数据
            this.mdCustomerBankService.insertOrUpdateDataObject(mdCustomerBank);
            
            return "{\"result\":\"success\"}";
        }catch(Exception e) {
            return "{\"result\":\"error\"}";
        }
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-07-11 20:59:32
     * @author 
     * @param mdCustomerBank
     * @return String
     *
     */
    @RequestMapping("deleteMdCustomerBank")
    @ResponseBody
    public String deleteMdCustomerBank(@RequestBody MdCustomerBank mdCustomerBank, RedirectAttributes attr) {
        try {
            //删除数据前验证数据
            if(mdCustomerBank!=null&&mdCustomerBank.getBankId()!=null) {
                //删除数据
                this.mdCustomerBankService.deleteDataObject(mdCustomerBank);
            }
            
            return "{\"result\":\"success\"}";
        }catch(Exception e) {
            return "{\"result\":\"error\"}";
        }
    }
}
