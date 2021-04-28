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
package com.erp.order.poa.controller;

import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import com.framework.shiro.ShiroUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import com.framework.controller.ControllerSupport;
import com.framework.dao.data.GlobalDataBox;
import com.framework.dao.model.Pages;
import com.erp.common.ap.invoice.service.ApInvoiceHeadService;
import com.erp.common.inv.input.service.InvInputHeadService;
import com.erp.dataset.service.DatasetCommonService;
import com.erp.hr.dao.model.HrStaffInfoRO;
import com.erp.hr.service.HrCommonService;
import com.erp.masterdata.common.service.MasterDataCommonService;
import com.erp.order.po.dao.data.DataBox;
import com.erp.order.po.dao.model.PoHead;
import com.erp.order.po.service.PoLineService;
import com.erp.order.poa.dao.model.PoAgreementHead;
import com.erp.order.poa.dao.model.PoAgreementHeadCO;
import com.erp.order.poa.service.PoAgreementHeadService;
import com.erp.order.poa.service.PoAgreementLineService;

@Controller
@RequestMapping("/web/poAgreementHead")
public class PoAgreementHeadWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(PoAgreementHeadWebController.class);
    
    //服务层注入
    @Autowired
    private PoAgreementHeadService poAgreementHeadService;
    @Autowired
    private DatasetCommonService datasetCommonService;
    @Autowired
    private HrCommonService hrCommonService;
    @Autowired
    private MasterDataCommonService masterDataCommonService;
    @Autowired
    private PoAgreementLineService poAgreementLineService;
//    @Autowired
//    private PoaEcoService poaEcoService;
    
    @Override
    public String getExceptionRedirectURL() {
        return "redirect:getPoHeadList";
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-10-19 17:01:51
     * @author 
     * @param pages
     * @param poAgreementHeadCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getPoHeadList")
    public String getPoAgreementHeadList(Pages pages, PoAgreementHeadCO poAgreementHeadCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        
        //分页查询数据
        List<PoAgreementHead> poAgreementHeadList = this.poAgreementHeadService.getDataObjectsForDataAuth("", pages, poAgreementHeadCO);
        
        //采购订单类型
        Map poTypeMap = this.datasetCommonService.getPOAType();
        //状态
        Map poStatusMap = DataBox.getPoStatusMap();
        //获取项目
        Map projectMap = this.masterDataCommonService.getProjectMap();
        //获取供应商
        Map vendorMap = this.masterDataCommonService.getVendorMap();
        //审批状态
        Map approveStatusMap = GlobalDataBox.getApproveStatusMap();
        
        //循环设置职员和组织信息
        for(PoAgreementHead poHead: poAgreementHeadList) {
            poHead.setStaffName(this.hrCommonService.getHrStaff(poHead.getStaffCode()).getStaffName());
            poHead.setDepartmentName(this.hrCommonService.getHrDepartment(poHead.getDepartmentCode()).getDepartmentName());
        }
        
        //页面属性设置
        model.addAttribute("poHeadList", poAgreementHeadList);
        model.addAttribute("pages", pages);
        model.addAttribute("poTypeMap", poTypeMap);
        model.addAttribute("poStatusMap", poStatusMap);
        model.addAttribute("projectMap", projectMap);
        model.addAttribute("vendorMap", vendorMap);
        model.addAttribute("approveStatusMap", approveStatusMap);
        
        return "basic.jsp?content=poa/poaList";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-10-19 17:01:51
     * @author 
     * @param poAgreementHead
     * @param model
     * @return String
     *
     */
    @RequestMapping("getPoHead")
    public String getPoAgreementHead(PoAgreementHead poAgreementHead, Model model) {
        //查询要编辑的数据
        if(poAgreementHead!=null&&StringUtils.isNotBlank(poAgreementHead.getPoHeadCode())) {
            
            //根据version请求参数，判断数据从原表获取还是变更表获取
            if(poAgreementHead.getVersion()!=null&&poAgreementHead.getVersion()>0) {
                //poAgreementHead = this.poHeadMService.getDataObject(poAgreementHead.getPoHeadCode(), poAgreementHead.getVersion());
            }else {
                poAgreementHead = this.poAgreementHeadService.getDataObject(poAgreementHead.getPoHeadCode());
            }
            
            //设置显示字段
            poAgreementHead.setStaffName(this.hrCommonService.getHrStaff(poAgreementHead.getStaffCode()).getStaffName());
            poAgreementHead.setDepartmentName(this.hrCommonService.getHrDepartment(poAgreementHead.getDepartmentCode()).getDepartmentName());
        }else {
            //初始化默认字段
            poAgreementHead.setAmount(0D);
            poAgreementHead.setPrepayAmount(0D);
            poAgreementHead.setReceiveStatus("N");
            poAgreementHead.setPaymentStatus("N");
            poAgreementHead.setStatus("NEW");
            
            //获取当前用户职员信息
            HrStaffInfoRO staffInfo = this.hrCommonService.getStaffInfo(ShiroUtil.getUsername());
            poAgreementHead.setStaffCode(staffInfo.getStaffCode());
            poAgreementHead.setDepartmentCode(staffInfo.getDepartmentCode());
            poAgreementHead.setStaffName(staffInfo.getStaffName());
            poAgreementHead.setDepartmentName(staffInfo.getDepartmentName());
        }
        
        //采购订单类型
        Map poTypeMap = this.datasetCommonService.getPOAType();
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
        model.addAttribute("poHead", poAgreementHead);
        model.addAttribute("poTypeMap", poTypeMap);
        model.addAttribute("currencyTypeMap", currencyTypeMap);
        model.addAttribute("taxTypeMap", taxTypeMap);
        model.addAttribute("poStatusMap", poStatusMap);
        model.addAttribute("receiveStatusMap", receiveStatusMap);
        model.addAttribute("paymentStatusMap", paymentStatusMap);
        model.addAttribute("projectMap", projectMap);
        model.addAttribute("vendorMap", vendorMap);
        model.addAttribute("approveStatusMap", approveStatusMap);
        
        return "basic.jsp?content=poa/poaEdit";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-10-19 17:01:51
     * @author 
     * @param poAgreementHead
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editPoHead")
    public String editPoAgreementHead(@Valid PoAgreementHead poAgreementHead, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        //参数校验
        Map<String, String> errorMap = this.validateParameters(bindingResult, model);
        if(errorMap.size()>0) {
            return "forward:getPoHead";
        }
        
        //对当前编辑的对象初始化默认的字段
        
        //保存编辑的数据
        this.poAgreementHeadService.insertOrUpdateDataObject(poAgreementHead);
        //提示信息
        attr.addFlashAttribute("hint", "success");
        attr.addAttribute("poHeadCode", poAgreementHead.getPoHeadCode());
        
        return "redirect:getPoHead";
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-10-19 17:01:51
     * @author 
     * @param poAgreementHead
     * @return String
     *
     */
    @RequestMapping("deletePoHead")
    public String deletePoAgreementHead(PoAgreementHead poAgreementHead, RedirectAttributes attr) {
        //删除数据前验证数据
        if(poAgreementHead!=null&&poAgreementHead.getPoHeadId()!=null&&StringUtils.isNotBlank(poAgreementHead.getPoHeadCode())) {
            if(poAgreementHead.getStatus().equals("NEW")) {
                //删除数据
                this.poAgreementHeadService.deleteDataObject(poAgreementHead);
                
                //提示信息
                attr.addFlashAttribute("hint", "success");
            }else {
                //提示信息
                attr.addFlashAttribute("hint", "hint");
                attr.addFlashAttribute("alertMessage", "非新建状态的协议不能删除");
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
//            if(approveStatus.equals("UNSUBMIT")) {
//                //ECO===记录变更历史数据
//                int errorCode = this.poaEcoService.saveModifyHistory(code);
//                if(errorCode!=0) {
//                    //提示信息
//                    attr.addFlashAttribute("hint", "hint");
//                    attr.addFlashAttribute("alertMessage", "当前采购协议保存变更历史错误，无法变更");
//                    attr.addAttribute("poHeadCode", code);
//                    return "redirect:getPoHead";
//                }
//            }
            
            //更新审核状态
            this.poAgreementHeadService.updateApproveStatus(code, approveStatus);
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
