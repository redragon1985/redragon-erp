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
package com.erp.order.po.controller;

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
import com.framework.util.ShiroUtil;
import com.springboot.dao.data.GlobalDataBox;
import com.erp.dataset.service.DatasetCommonService;
import com.erp.hr.dao.model.HrStaffInfoRO;
import com.erp.hr.service.HrCommonService;
import com.erp.masterdata.common.service.MasterDataCommonService;
import com.erp.order.po.dao.data.DataBox;
import com.erp.order.po.dao.model.PoHead;
import com.erp.order.po.dao.model.PoHeadCO;
import com.erp.order.po.service.PoHeadService;
import com.erp.order.po.service.PoLineService;

@Controller
@RequestMapping("/web/poHead")
public class PoHeadWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(PoHeadWebController.class);
    
    //服务层注入
    @Autowired
    private PoHeadService poHeadService;
    @Autowired
    private DatasetCommonService datasetCommonService;
    @Autowired
    private HrCommonService hrCommonService;
    @Autowired
    private MasterDataCommonService masterDataCommonService;
    @Autowired
    private PoLineService poLineService;
    
    @Override
    public String getExceptionRedirectURL() {
        return "redirect:getPoHeadList";
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-07-15 15:56:54
     * @author 
     * @param pages
     * @param poHeadCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getPoHeadList")
    public String getPoHeadList(Pages pages, PoHeadCO poHeadCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        
        //分页查询数据
        List<PoHead> poHeadList = this.poHeadService.getDataObjects(pages, poHeadCO);
        
        //采购订单类型
        Map poTypeMap = this.datasetCommonService.getPOType();
        //状态
        Map poStatusMap = DataBox.getPoStatusMap();
        //获取项目
        Map projectMap = this.masterDataCommonService.getProjectMap();
        //获取供应商
        Map vendorMap = this.masterDataCommonService.getVendorMap();
        //审批状态
        Map approveStatusMap = GlobalDataBox.getApproveStatusMap();
        
        //循环设置职员和组织信息
        for(PoHead poHead: poHeadList) {
            poHead.setStaffName(this.hrCommonService.getHrStaff(poHead.getStaffCode()).getStaffName());
            poHead.setDepartmentName(this.hrCommonService.getHrDepartment(poHead.getDepartmentCode()).getDepartmentName());
        }
        
        //页面属性设置
        model.addAttribute("poHeadList", poHeadList);
        model.addAttribute("pages", pages);
        model.addAttribute("poTypeMap", poTypeMap);
        model.addAttribute("poStatusMap", poStatusMap);
        model.addAttribute("projectMap", projectMap);
        model.addAttribute("vendorMap", vendorMap);
        model.addAttribute("approveStatusMap", approveStatusMap);
        
        return "basic.jsp?content=po/poList";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-07-15 15:56:54
     * @author 
     * @param poHead
     * @param model
     * @return String
     *
     */
    @RequestMapping("getPoHead")
    public String getPoHead(PoHead poHead, Model model) {
        //查询要编辑的数据
        if(poHead!=null&&StringUtils.isNotBlank(poHead.getPoHeadCode())) {
            poHead = this.poHeadService.getDataObject(poHead.getPoHeadCode());
            //设置显示字段
            poHead.setStaffName(this.hrCommonService.getHrStaff(poHead.getStaffCode()).getStaffName());
            poHead.setDepartmentName(this.hrCommonService.getHrDepartment(poHead.getDepartmentCode()).getDepartmentName());
        }else {
            //初始化默认字段
            poHead.setAmount(0D);
            poHead.setPrepayAmount(0D);
            poHead.setReceiveStatus("N");
            poHead.setPaymentStatus("N");
            poHead.setStatus("NEW");
            
            //获取当前用户职员信息
            HrStaffInfoRO staffInfo = this.hrCommonService.getStaffInfo(ShiroUtil.getUsername());
            poHead.setStaffCode(staffInfo.getStaffCode());
            poHead.setDepartmentCode(staffInfo.getDepartmentCode());
            poHead.setStaffName(staffInfo.getStaffName());
            poHead.setDepartmentName(staffInfo.getDepartmentName());
        }
        
        //采购订单类型
        Map poTypeMap = this.datasetCommonService.getPOType();
        //币种
        Map currencyTypeMap = this.datasetCommonService.getCurrencyType();
        //计税类型
        Map taxTypeMap = this.datasetCommonService.getTaxType();
        //状态
        Map poStatusMap = DataBox.getPoStatusMap();
        //接收状态
        Map receiveStatusMap = DataBox.getPoReceiveStatusMap();
        //付款状态
        Map paymentStatusMap = DataBox.getPoPaymentStatusMap();
        //获取项目
        Map projectMap = this.masterDataCommonService.getProjectMap();
        //获取供应商
        Map vendorMap = this.masterDataCommonService.getVendorMap();
        //审批状态
        Map approveStatusMap = GlobalDataBox.getApproveStatusMap();
        
        //页面属性设置
        model.addAttribute("poHead", poHead);
        model.addAttribute("poTypeMap", poTypeMap);
        model.addAttribute("currencyTypeMap", currencyTypeMap);
        model.addAttribute("taxTypeMap", taxTypeMap);
        model.addAttribute("poStatusMap", poStatusMap);
        model.addAttribute("receiveStatusMap", receiveStatusMap);
        model.addAttribute("paymentStatusMap", paymentStatusMap);
        model.addAttribute("projectMap", projectMap);
        model.addAttribute("vendorMap", vendorMap);
        model.addAttribute("approveStatusMap", approveStatusMap);
        
        return "basic.jsp?content=po/poEdit";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-07-15 15:56:54
     * @author 
     * @param poHead
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editPoHead")
    public String editPoHead(@Valid PoHead poHead, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        //参数校验
        Map<String, String> errorMap = this.validateParameters(bindingResult, model);
        if(errorMap.size()>0) {
            return "forward:getPoHead";
        }
        
        //对当前编辑的对象初始化默认的字段
        
        //保存编辑的数据
        this.poHeadService.insertOrUpdateDataObject(poHead);
        //提示信息
        attr.addFlashAttribute("hint", "success");
        attr.addAttribute("poHeadCode", poHead.getPoHeadCode());
        
        return "redirect:getPoHead";
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-07-15 15:56:54
     * @author 
     * @param poHead
     * @return String
     *
     */
    @RequestMapping("deletePoHead")
    public String deletePoHead(PoHead poHead, RedirectAttributes attr) {
        //删除数据前验证数据
        if(poHead!=null&&poHead.getPoHeadId()!=null&&StringUtils.isNotBlank(poHead.getPoHeadCode())) {
            if(poHead.getStatus().equals("NEW")) {
                //删除数据
                this.poHeadService.deleteDataObject(poHead);
                this.poLineService.deletetPoLineByPoHeadCode(poHead.getPoHeadCode());
                
                //提示信息
                attr.addFlashAttribute("hint", "success");
            }else {
                //提示信息
                attr.addFlashAttribute("hint", "hint");
                attr.addFlashAttribute("alertMessage", "非新建状态的订单不能删除");
            }
            
        }
        
        return "redirect:getPoHeadList";
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
            this.poHeadService.updateApproveStatus(code, approveStatus);
          //提示信息
            attr.addFlashAttribute("hint", "success");
            attr.addAttribute("poHeadCode", code);
        }else {
            //提示信息
            attr.addFlashAttribute("hint", "hint");
            attr.addFlashAttribute("alertMessage", "提交或审批数据错误");
            attr.addAttribute("poHeadCode", code);
        }
        
        return "redirect:getPoHead";
    }
}
