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
import com.springboot.dao.data.GlobalDataBox;
import com.erp.dataset.service.DatasetCommonService;
import com.erp.masterdata.vendor.dao.model.MdVendor;
import com.erp.masterdata.vendor.dao.model.MdVendorCO;
import com.erp.masterdata.vendor.service.MdVendorBankService;
import com.erp.masterdata.vendor.service.MdVendorContactService;
import com.erp.masterdata.vendor.service.MdVendorLicenseService;
import com.erp.masterdata.vendor.service.MdVendorService;

@Controller
@RequestMapping("/web/mdVendor")
public class MdVendorWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(MdVendorWebController.class);
    
    //服务层注入
    @Autowired
    private MdVendorService mdVendorService;
    @Autowired
    private MdVendorBankService mdVendorBankService;
    @Autowired
    private MdVendorContactService mdVendorContactService;
    @Autowired
    private MdVendorLicenseService mdVendorLicenseService;
    @Autowired
    private DatasetCommonService datasetCommonService;
    
    @Override
    public String getExceptionRedirectURL() {
        return "redirect:getMdVendorList";
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-07-14 16:06:00
     * @author 
     * @param pages
     * @param mdVendorCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getMdVendorList")
    public String getMdVendorList(Pages pages, MdVendorCO mdVendorCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        
        //分页查询数据
        List<MdVendor> mdVendorList = this.mdVendorService.getDataObjects(pages, mdVendorCO);
        
        //获取城市
        Map cityMap = this.datasetCommonService.getCity();
        //审批状态
        Map approveStatusMap = GlobalDataBox.getApproveStatusMap();
        
        //页面属性设置
        model.addAttribute("mdVendorList", mdVendorList);
        model.addAttribute("pages", pages);
        model.addAttribute("cityMap", cityMap);
        model.addAttribute("approveStatusMap", approveStatusMap);
        
        return "basic.jsp?content=mdVendor/mdVendorList";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-07-14 16:06:00
     * @author 
     * @param mdVendor
     * @param model
     * @return String
     *
     */
    @RequestMapping("getMdVendor")
    public String getMdVendor(MdVendor mdVendor, Model model) {
        //查询要编辑的数据
        if(mdVendor!=null&&StringUtils.isNotBlank(mdVendor.getVendorCode())) {
            mdVendor = this.mdVendorService.getDataObject(mdVendor.getVendorCode());
        }
        
        //获取国家
        Map countryMap = this.datasetCommonService.getCountry();
        //获取城市
        Map cityMap = this.datasetCommonService.getCity();
        //审批状态
        Map approveStatusMap = GlobalDataBox.getApproveStatusMap();
        
        //页面属性设置
        model.addAttribute("mdVendor", mdVendor);
        model.addAttribute("countryMap", countryMap);
        model.addAttribute("cityMap", cityMap);
        model.addAttribute("approveStatusMap", approveStatusMap);
        
        return "basic.jsp?content=mdVendor/mdVendorEdit";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-07-14 16:06:00
     * @author 
     * @param mdVendor
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editMdVendor")
    public String editMdVendor(@Valid MdVendor mdVendor, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        //参数校验
        Map<String, String> errorMap = this.validateParameters(bindingResult, model);
        if(errorMap.size()>0) {
            return "forward:getMdVendor";
        }
        
        //对当前编辑的对象初始化默认的字段
        
        //保存编辑的数据
        this.mdVendorService.insertOrUpdateDataObject(mdVendor);
        //提示信息
        attr.addFlashAttribute("hint", "success");
        attr.addAttribute("vendorCode", mdVendor.getVendorCode());
        
        return "redirect:getMdVendor";
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-07-14 16:06:00
     * @author 
     * @param mdVendor
     * @return String
     *
     */
    @RequestMapping("deleteMdVendor")
    public String deleteMdVendor(MdVendor mdVendor, RedirectAttributes attr) {
        //删除数据前验证数据
        if(mdVendor!=null&&mdVendor.getVendorId()!=null&&StringUtils.isNotBlank(mdVendor.getVendorCode())) {
            int bankCount = this.mdVendorBankService.getBankCountByVendorCode(mdVendor.getVendorCode());
            int contactCount = this.mdVendorContactService.getContactCountByVendorCode(mdVendor.getVendorCode());
            int licenseCount = this.mdVendorLicenseService.getLicenseCountByVendorCode(mdVendor.getVendorCode());
            
            if(bankCount==0&&contactCount==0&&licenseCount==0) {
                //删除数据
                this.mdVendorService.deleteDataObject(mdVendor);
                //提示信息
                attr.addFlashAttribute("hint", "success");
            }else {
                //提示信息
                attr.addFlashAttribute("hint", "hint");
                attr.addFlashAttribute("alertMessage", "客户已关联其他数据，无法删除");
            }
        }
        
        return "redirect:getMdVendorList";
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
            this.mdVendorService.updateApproveStatus(code, approveStatus);
          //提示信息
            attr.addFlashAttribute("hint", "success");
            attr.addAttribute("vendorCode", code);
        }else {
            //提示信息
            attr.addFlashAttribute("hint", "hint");
            attr.addFlashAttribute("alertMessage", "提交或审批数据错误");
            attr.addAttribute("vendorCode", code);
        }
        
        return "redirect:getMdVendor";
    }
}
