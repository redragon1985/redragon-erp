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
import com.framework.dao.data.GlobalDataBox;
import com.framework.dao.model.Pages;
import com.erp.dataset.service.DatasetCommonService;
import com.erp.masterdata.customer.dao.model.MdCustomer;
import com.erp.masterdata.customer.dao.model.MdCustomerCO;
import com.erp.masterdata.customer.service.MdCustomerBankService;
import com.erp.masterdata.customer.service.MdCustomerContactService;
import com.erp.masterdata.customer.service.MdCustomerLicenseService;
import com.erp.masterdata.customer.service.MdCustomerService;

@Controller
@RequestMapping("/web/mdCustomer")
public class MdCustomerWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(MdCustomerWebController.class);
    
    //服务层注入
    @Autowired
    private MdCustomerService mdCustomerService;
    @Autowired
    private MdCustomerBankService mdCustomerBankService;
    @Autowired
    private MdCustomerContactService mdCustomerContactService;
    @Autowired
    private MdCustomerLicenseService mdCustomerLicenseService;
    @Autowired
    private DatasetCommonService datasetCommonService;
    
    @Override
    public String getExceptionRedirectURL() {
        return "redirect:getMdCustomerList";
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-07-11 20:58:34
     * @author 
     * @param pages
     * @param mdCustomerCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getMdCustomerList")
    public String getMdCustomerList(Pages pages, MdCustomerCO mdCustomerCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        
        //分页查询数据
        List<MdCustomer> mdCustomerList = this.mdCustomerService.getDataObjects(pages, mdCustomerCO);
        
        //获取城市
        Map cityMap = this.datasetCommonService.getCity();
        //审批状态
        Map approveStatusMap = GlobalDataBox.getApproveStatusMap();
        
        //页面属性设置
        model.addAttribute("mdCustomerList", mdCustomerList);
        model.addAttribute("pages", pages);
        model.addAttribute("cityMap", cityMap);
        model.addAttribute("approveStatusMap", approveStatusMap);
        
        return "basic.jsp?content=mdCustomer/mdCustomerList";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-07-11 20:58:34
     * @author 
     * @param mdCustomer
     * @param model
     * @return String
     *
     */
    @RequestMapping("getMdCustomer")
    public String getMdCustomer(MdCustomer mdCustomer, Model model) {
        //查询要编辑的数据
        if(mdCustomer!=null&&StringUtils.isNotBlank(mdCustomer.getCustomerCode())) {
            mdCustomer = this.mdCustomerService.getDataObject(mdCustomer.getCustomerCode());
        }
        
        //获取国家
        Map countryMap = this.datasetCommonService.getCountry();
        //获取城市
        Map cityMap = this.datasetCommonService.getCity();
        //审批状态
        Map approveStatusMap = GlobalDataBox.getApproveStatusMap();
        
        //页面属性设置
        model.addAttribute("mdCustomer", mdCustomer);
        model.addAttribute("countryMap", countryMap);
        model.addAttribute("cityMap", cityMap);
        model.addAttribute("approveStatusMap", approveStatusMap);
        
        return "basic.jsp?content=mdCustomer/mdCustomerEdit";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-07-11 20:58:34
     * @author 
     * @param mdCustomer
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editMdCustomer")
    public String editMdCustomer(@Valid MdCustomer mdCustomer, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        //参数校验
        Map<String, String> errorMap = this.validateParameters(bindingResult, model);
        if(errorMap.size()>0) {
            return "forward:getMdCustomer";
        }
        
        //对当前编辑的对象初始化默认的字段
        
        //保存编辑的数据
        this.mdCustomerService.insertOrUpdateDataObject(mdCustomer);
        //提示信息
        attr.addFlashAttribute("hint", "success");
        attr.addAttribute("customerCode", mdCustomer.getCustomerCode());
        
        return "redirect:getMdCustomer";
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-07-11 20:58:34
     * @author 
     * @param mdCustomer
     * @return String
     *
     */
    @RequestMapping("deleteMdCustomer")
    public String deleteMdCustomer(MdCustomer mdCustomer, RedirectAttributes attr) {
        //删除数据前验证数据
        if(mdCustomer!=null&&mdCustomer.getCustomerId()!=null&&StringUtils.isNotBlank(mdCustomer.getCustomerCode())) {
            int bankCount = this.mdCustomerBankService.getBankCountByCustomerCode(mdCustomer.getCustomerCode());
            int contactCount = this.mdCustomerContactService.getContactCountByCustomerCode(mdCustomer.getCustomerCode());
            int licenseCount = this.mdCustomerLicenseService.getLicenseCountByCustomerCode(mdCustomer.getCustomerCode());
            
            if(bankCount==0&&contactCount==0&&licenseCount==0) {
                //删除数据
                this.mdCustomerService.deleteDataObject(mdCustomer);
                //提示信息
                attr.addFlashAttribute("hint", "success");
            }else {
                //提示信息
                attr.addFlashAttribute("hint", "hint");
                attr.addFlashAttribute("alertMessage", "客户已关联其他数据，无法删除");
            }
        }
        
        return "redirect:getMdCustomerList";
    }
    
    
    
    /**
     * 
     * @description 更新审批状态
     * @date 2020年8月4日
     * @author dongbin
     * @param code
     * @param approveStatus
     * @param attr
     * @return
     * @return String
     *
     */
    @RequestMapping("updateApproveStatus")
    public String updateApproveStatus(String code, String approveStatus, RedirectAttributes attr) {
        
        if(StringUtils.isNotBlank(code)&&StringUtils.isNotBlank(approveStatus)) {
            //更新审核状态
            this.mdCustomerService.updateApproveStatus(code, approveStatus);
            //提示信息
            attr.addFlashAttribute("hint", "success");
            attr.addAttribute("customerCode", code);
        }else {
            //提示信息
            attr.addFlashAttribute("hint", "hint");
            attr.addFlashAttribute("alertMessage", "提交或审批数据错误");
            attr.addAttribute("customerCode", code);
        }
        
        return "redirect:getMdCustomer";
    }
}
