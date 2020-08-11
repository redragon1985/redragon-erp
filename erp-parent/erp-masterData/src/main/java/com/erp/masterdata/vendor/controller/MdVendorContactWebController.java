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
import com.erp.masterdata.vendor.dao.model.MdVendorContact;
import com.erp.masterdata.vendor.dao.model.MdVendorContactCO;
import com.erp.masterdata.vendor.service.MdVendorContactService;

@Controller
@RequestMapping("/web/mdVendorContact")
public class MdVendorContactWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(MdVendorContactWebController.class);
    
    //服务层注入
    @Autowired
    private MdVendorContactService mdVendorContactService;
    
    @Override
    public String getExceptionRedirectURL() {
        return "redirect:getMdVendorContactList";
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-07-14 16:07:23
     * @author 
     * @param pages
     * @param mdVendorContactCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getMdVendorContactList")
    public String getMdVendorContactList(Pages pages, MdVendorContactCO mdVendorContactCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        pages.setMax(100);
        
        //分页查询数据
        List<MdVendorContact> mdVendorContactList = this.mdVendorContactService.getContactListByVendorCode(pages, mdVendorContactCO);
        
        //页面属性设置
        model.addAttribute("mdVendorContactList", mdVendorContactList);
        model.addAttribute("pages", pages);
        
        return "mdVendor/tab/vendorContactTab";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-07-14 16:07:23
     * @author 
     * @param mdVendorContact
     * @param model
     * @return String
     *
     */
    @RequestMapping("getMdVendorContact")
    public String getMdVendorContact(MdVendorContact mdVendorContact, Model model) {
        //查询要编辑的数据
        if(mdVendorContact!=null&&mdVendorContact.getContactId()!=null) {
            mdVendorContact = this.mdVendorContactService.getDataObject(mdVendorContact.getContactId());
        }
        
        //页面属性设置
        model.addAttribute("mdVendorContact", mdVendorContact);
        
        return "mdVendor/pop/addContactModal";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-07-14 16:07:23
     * @author 
     * @param mdVendorContact
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editMdVendorContact")
    @ResponseBody
    public String editMdVendorContact(@Valid @RequestBody MdVendorContact mdVendorContact, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        try {
            /*
            //参数校验
            Map<String, String> errorMap = this.validateParameters(bindingResult, model);
            if(errorMap.size()>0) {
                return "forward:getMdVendorContact";
            }
            */
            
            //对当前编辑的对象初始化默认的字段
            
            //保存编辑的数据
            this.mdVendorContactService.insertOrUpdateDataObject(mdVendorContact);
            
            return "{\"result\":\"success\"}";
        }catch(Exception e) {
            return "{\"result\":\"error\"}";
        }
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-07-14 16:07:23
     * @author 
     * @param mdVendorContact
     * @return String
     *
     */
    @RequestMapping("deleteMdVendorContact")
    @ResponseBody
    public String deleteMdVendorContact(@RequestBody MdVendorContact mdVendorContact, RedirectAttributes attr) {
        try {
            //删除数据前验证数据
            if(mdVendorContact!=null&&mdVendorContact.getContactId()!=null) {
                //删除数据
                this.mdVendorContactService.deleteDataObject(mdVendorContact);
            }
            
            return "{\"result\":\"success\"}";
        }catch(Exception e) {
            return "{\"result\":\"error\"}";
        }
    }
}
