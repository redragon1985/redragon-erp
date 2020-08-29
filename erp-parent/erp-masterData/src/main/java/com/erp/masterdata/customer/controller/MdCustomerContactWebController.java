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
import com.erp.masterdata.customer.dao.model.MdCustomerContact;
import com.erp.masterdata.customer.dao.model.MdCustomerContactCO;
import com.erp.masterdata.customer.service.MdCustomerContactService;

@Controller
@RequestMapping("/web/mdCustomerContact")
public class MdCustomerContactWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(MdCustomerContactWebController.class);
    
    //服务层注入
    @Autowired
    private MdCustomerContactService mdCustomerContactService;
    
    @Override
    public String getExceptionRedirectURL() {
        return "redirect:getMdCustomerContactList";
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-07-11 21:00:14
     * @author 
     * @param pages
     * @param mdCustomerContactCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getMdCustomerContactList")
    public String getMdCustomerContactList(Pages pages, MdCustomerContactCO mdCustomerContactCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        pages.setMax(100);
        
        //分页查询数据
        List<MdCustomerContact> mdCustomerContactList = this.mdCustomerContactService.getContactListByCustomerCode(pages, mdCustomerContactCO);
        
        //页面属性设置
        model.addAttribute("mdCustomerContactList", mdCustomerContactList);
        model.addAttribute("pages", pages);
        
        return "mdCustomer/tab/customerContactTab";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-07-11 21:00:14
     * @author 
     * @param mdCustomerContact
     * @param model
     * @return String
     *
     */
    @RequestMapping("getMdCustomerContact")
    public String getMdCustomerContact(MdCustomerContact mdCustomerContact, Model model) {
        //查询要编辑的数据
        if(mdCustomerContact!=null&&mdCustomerContact.getContactId()!=null) {
            mdCustomerContact = this.mdCustomerContactService.getDataObject(mdCustomerContact.getContactId());
        }
        
        //页面属性设置
        model.addAttribute("mdCustomerContact", mdCustomerContact);
        
        return "mdCustomer/pop/addContactModal";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-07-11 21:00:14
     * @author 
     * @param mdCustomerContact
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editMdCustomerContact")
    @ResponseBody
    public String editMdCustomerContact(@Valid @RequestBody MdCustomerContact mdCustomerContact, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        try {
            /*
            //参数校验
            Map<String, String> errorMap = this.validateParameters(bindingResult, model);
            if(errorMap.size()>0) {
                return "forward:getMdCustomerContact";
            }
            */
            
            //对当前编辑的对象初始化默认的字段
            
            //保存编辑的数据
            this.mdCustomerContactService.insertOrUpdateDataObject(mdCustomerContact);
            
            return "{\"result\":\"success\"}";
        }catch(Exception e) {
            return "{\"result\":\"error\"}";
        }
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-07-11 21:00:14
     * @author 
     * @param mdCustomerContact
     * @return String
     *
     */
    @RequestMapping("deleteMdCustomerContact")
    @ResponseBody
    public String deleteMdCustomerContact(@RequestBody MdCustomerContact mdCustomerContact, RedirectAttributes attr) {
        try {
            //删除数据前验证数据
            if(mdCustomerContact!=null&&mdCustomerContact.getContactId()!=null) {
                //删除数据
                this.mdCustomerContactService.deleteDataObject(mdCustomerContact);
            }
            
            return "{\"result\":\"success\"}";
        }catch(Exception e) {
            return "{\"result\":\"error\"}";
        }
    }
}
