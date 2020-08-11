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
import com.erp.masterdata.vendor.dao.model.MdVendorLicense;
import com.erp.masterdata.vendor.dao.model.MdVendorLicenseCO;
import com.erp.masterdata.vendor.service.MdVendorLicenseService;

@Controller
@RequestMapping("/web/mdVendorLicense")
public class MdVendorLicenseWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(MdVendorLicenseWebController.class);
    
    //服务层注入
    @Autowired
    private MdVendorLicenseService mdVendorLicenseService;
    
    @Override
    public String getExceptionRedirectURL() {
        // TODO Auto-generated method stub
        return null;
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-07-14 16:07:52
     * @author 
     * @param pages
     * @param mdVendorLicenseCO
     * @param model
     * @return String
     *
     */
//    @RequestMapping("getMdVendorLicenseList")
//    public String getMdVendorLicenseList(Pages pages, MdVendorLicenseCO mdVendorLicenseCO, Model model) {
//        //TODO: 分页查询数据
//        if(pages.getPage()==0) {
//            pages.setPage(1);
//        }
//        pages.setMax(10);
//        pages.setShowPageNum(7);
//        pages.setStartEndPageNum(2);
//        
//        //分页查询数据
//        List<MdVendorLicense> mdVendorLicenseList = this.mdVendorLicenseService.getDataObjects(pages, mdVendorLicenseCO);
//        
//        //页面属性设置
//        model.addAttribute("mdVendorLicenseList", mdVendorLicenseList);
//        model.addAttribute("pages", pages);
//        
//        return "mdVendorLicense/mdVendorLicenseList";
//    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-07-14 16:07:52
     * @author 
     * @param mdVendorLicense
     * @param model
     * @return String
     *
     */
    @RequestMapping("getMdVendorLicense")
    public String getMdVendorLicense(MdVendorLicense mdVendorLicense, Model model) {
        //查询要编辑的数据
        if(mdVendorLicense!=null&&StringUtils.isNotBlank(mdVendorLicense.getVendorCode())) {
            mdVendorLicense = this.mdVendorLicenseService.getLicenseListByVendorCode(mdVendorLicense);
        }
        
        //页面属性设置
        model.addAttribute("mdVendorLicense", mdVendorLicense);
        
        return "mdVendor/tab/vendorLicenseTab";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-07-14 16:07:52
     * @author 
     * @param mdVendorLicense
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editMdVendorLicense")
    public String editMdVendorLicense(@Valid MdVendorLicense mdVendorLicense, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        //参数校验
        Map<String, String> errorMap = this.validateParameters(bindingResult, model);
        if(errorMap.size()>0) {
            return "forward:/web/mdVendor/getMdVendor";
        }
        
        //对当前编辑的对象初始化默认的字段
        
        //保存编辑的数据
        this.mdVendorLicenseService.insertOrUpdateDataObject(mdVendorLicense);
        //提示信息
        attr.addFlashAttribute("hint", "success");
        attr.addAttribute("vendorCode", mdVendorLicense.getVendorCode());
        
        return "redirect:/web/mdVendor/getMdVendor";
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-07-14 16:07:52
     * @author 
     * @param mdVendorLicense
     * @return String
     *
     */
//    @RequestMapping("deleteMdVendorLicense")
//    public String deleteMdVendorLicense(MdVendorLicense mdVendorLicense, RedirectAttributes attr) {
//        //TODO: 删除数据前验证数据
//        
//        //删除数据
//        this.mdVendorLicenseService.deleteDataObject(mdVendorLicense);
//        //提示信息
//        attr.addFlashAttribute("hint", "success");
//        
//        return "redirect:getMdVendorLicenseList";
//    }
}
