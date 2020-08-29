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

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import com.framework.controller.ControllerSupport;
import com.framework.dao.model.Pages;
import com.framework.util.JsonResultUtil;
import com.framework.util.JsonUtil;
import com.erp.masterdata.customer.dao.model.MdCustomerLicense;
import com.erp.masterdata.customer.dao.model.MdCustomerLicenseCO;
import com.erp.masterdata.customer.service.MdCustomerLicenseService;

@Controller
@RequestMapping("/web/mdCustomerLicense")
public class MdCustomerLicenseWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(MdCustomerLicenseWebController.class);
    
    //服务层注入
    @Autowired
    private MdCustomerLicenseService mdCustomerLicenseService;
    
    @Override
    public String getExceptionRedirectURL() {
        // TODO Auto-generated method stub
        return null;
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-07-11 21:00:46
     * @author 
     * @param pages
     * @param mdCustomerLicenseCO
     * @param model
     * @return String
     *
     */
//    @RequestMapping("getMdCustomerLicenseList")
//    public String getMdCustomerLicenseList(Pages pages, MdCustomerLicenseCO mdCustomerLicenseCO, Model model) {
//        //TODO: 分页查询数据
//        if(pages.getPage()==0) {
//            pages.setPage(1);
//        }
//        pages.setMax(10);
//        pages.setShowPageNum(7);
//        pages.setStartEndPageNum(2);
//        
//        //分页查询数据
//        List<MdCustomerLicense> mdCustomerLicenseList = this.mdCustomerLicenseService.getDataObjects(pages, mdCustomerLicenseCO);
//        
//        //页面属性设置
//        model.addAttribute("mdCustomerLicenseList", mdCustomerLicenseList);
//        model.addAttribute("pages", pages);
//        
//        return "mdCustomerLicense/mdCustomerLicenseList";
//    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-07-11 21:00:46
     * @author 
     * @param mdCustomerLicense
     * @param model
     * @return String
     *
     */
    @RequestMapping("getMdCustomerLicense")
    public String getMdCustomerLicense(MdCustomerLicense mdCustomerLicense, Model model) {
        //查询要编辑的数据
        if(mdCustomerLicense!=null&&StringUtils.isNotBlank(mdCustomerLicense.getCustomerCode())) {
            mdCustomerLicense = this.mdCustomerLicenseService.getLicenseListByCustomerCode(mdCustomerLicense);
        }
        
        //页面属性设置
        model.addAttribute("mdCustomerLicense", mdCustomerLicense);
        
        return "mdCustomer/tab/customerLicenseTab";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-07-11 21:00:46
     * @author 
     * @param mdCustomerLicense
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editMdCustomerLicense")
    public String editMdCustomerLicense(@Valid MdCustomerLicense mdCustomerLicense, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        //参数校验
        Map<String, String> errorMap = this.validateParameters(bindingResult, model);
        if(errorMap.size()>0) {
            return "forward:/web/mdCustomer/getMdCustomer";
        }
        
        //对当前编辑的对象初始化默认的字段
        
        //保存编辑的数据
        this.mdCustomerLicenseService.insertOrUpdateDataObject(mdCustomerLicense);
        //提示信息
        attr.addFlashAttribute("hint", "success");
        attr.addAttribute("customerCode", mdCustomerLicense.getCustomerCode());
        
        return "redirect:/web/mdCustomer/getMdCustomer";
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-07-11 21:00:46
     * @author 
     * @param mdCustomerLicense
     * @return String
     *
     */
//    @RequestMapping("deleteMdCustomerLicense")
//    public String deleteMdCustomerLicense(MdCustomerLicense mdCustomerLicense, RedirectAttributes attr) {
//        //TODO: 删除数据前验证数据
//        
//        //删除数据
//        this.mdCustomerLicenseService.deleteDataObject(mdCustomerLicense);
//        //提示信息
//        attr.addFlashAttribute("hint", "success");
//        
//        return "redirect:getMdCustomerLicenseList";
//    }
}
