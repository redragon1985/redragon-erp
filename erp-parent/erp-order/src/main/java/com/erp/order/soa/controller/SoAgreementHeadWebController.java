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
package com.erp.order.soa.controller;

import java.util.List;
import java.util.Map;
import javax.validation.Valid;

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
import com.framework.util.JsonResultUtil;
import com.framework.util.JsonUtil;
import com.framework.util.ShiroUtil;
import com.erp.common.ar.invoice.service.ArInvoiceHeadService;
import com.erp.common.inv.output.service.InvOutputHeadService;
import com.erp.dataset.service.DatasetCommonService;
import com.erp.hr.dao.model.HrStaffInfoRO;
import com.erp.hr.service.HrCommonService;
import com.erp.masterdata.common.service.MasterDataCommonService;
import com.erp.order.so.dao.data.DataBox;
import com.erp.order.so.dao.model.SoHead;
import com.erp.order.so.service.SoLineService;
import com.erp.order.soa.dao.model.SoAgreementHead;
import com.erp.order.soa.dao.model.SoAgreementHeadCO;
import com.erp.order.soa.service.SoAgreementHeadService;
import com.erp.order.soa.service.SoAgreementLineService;

@Controller
@RequestMapping("/web/soAgreementHead")
public class SoAgreementHeadWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(SoAgreementHeadWebController.class);
    
    //服务层注入
    @Autowired
    private SoAgreementHeadService soAgreementHeadService;
    @Autowired
    private DatasetCommonService datasetCommonService;
    @Autowired
    private HrCommonService hrCommonService;
    @Autowired
    private MasterDataCommonService masterDataCommonService;
    @Autowired
    private SoAgreementLineService soAgreementLineService;
//    @Autowired
//    private SoaEcoService soaEcoService;
    
    @Override
    public String getExceptionRedirectURL() {
        return "redirect:getSoHeadList";
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-10-19 17:02:47
     * @author 
     * @param pages
     * @param soAgreementHeadCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getSoHeadList")
    public String getSoAgreementHeadList(Pages pages, SoAgreementHeadCO soAgreementHeadCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        
        //分页查询数据
        List<SoAgreementHead> soAgreementHeadList = this.soAgreementHeadService.getDataObjectsForDataAuth("", pages, soAgreementHeadCO);
        
        //采购销售类型
        Map soTypeMap = this.datasetCommonService.getSOAType();
        //状态
        Map soStatusMap = DataBox.getSoStatusMap();
        //获取项目
        Map projectMap = this.masterDataCommonService.getProjectMap();
        //获取客户
        Map customerMap = this.masterDataCommonService.getCustomerMap();
        //审批状态
        Map approveStatusMap = GlobalDataBox.getApproveStatusMap();
        
        //循环设置职员和组织信息
        for(SoAgreementHead soHead: soAgreementHeadList) {
            soHead.setStaffName(this.hrCommonService.getHrStaff(soHead.getStaffCode()).getStaffName());
            soHead.setDepartmentName(this.hrCommonService.getHrDepartment(soHead.getDepartmentCode()).getDepartmentName());
        }
        
        //页面属性设置
        model.addAttribute("soHeadList", soAgreementHeadList);
        model.addAttribute("pages", pages);
        model.addAttribute("soTypeMap", soTypeMap);
        model.addAttribute("soStatusMap", soStatusMap);
        model.addAttribute("projectMap", projectMap);
        model.addAttribute("customerMap", customerMap);
        model.addAttribute("approveStatusMap", approveStatusMap);
        
        return "basic.jsp?content=soa/soaList";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-10-19 17:02:47
     * @author 
     * @param soAgreementHead
     * @param model
     * @return String
     *
     */
    @RequestMapping("getSoHead")
    public String getSoAgreementHead(SoAgreementHead soAgreementHead, Model model) {
        //查询要编辑的数据
        if(soAgreementHead!=null&&StringUtils.isNotBlank(soAgreementHead.getSoHeadCode())) {
            
            //根据version请求参数，判断数据从原表获取还是变更表获取
            if(soAgreementHead.getVersion()!=null&&soAgreementHead.getVersion()>0) {
                //soAgreementHead = this.soHeadMService.getDataObject(soAgreementHead.getSoHeadCode(), soAgreementHead.getVersion());
            }else {
                soAgreementHead = this.soAgreementHeadService.getDataObject(soAgreementHead.getSoHeadCode());
            }
            
            //设置显示字段
            soAgreementHead.setStaffName(this.hrCommonService.getHrStaff(soAgreementHead.getStaffCode()).getStaffName());
            soAgreementHead.setDepartmentName(this.hrCommonService.getHrDepartment(soAgreementHead.getDepartmentCode()).getDepartmentName());
        }else {
            //初始化默认字段
            soAgreementHead.setAmount(0D);
            soAgreementHead.setPreReceiptAmount(0D);
            soAgreementHead.setShipmentStatus("N");
            soAgreementHead.setReceiptStatus("N");
            soAgreementHead.setStatus("NEW");
            
            //获取当前用户职员信息
            HrStaffInfoRO staffInfo = this.hrCommonService.getStaffInfo(ShiroUtil.getUsername());
            soAgreementHead.setStaffCode(staffInfo.getStaffCode());
            soAgreementHead.setDepartmentCode(staffInfo.getDepartmentCode());
            soAgreementHead.setStaffName(staffInfo.getStaffName());
            soAgreementHead.setDepartmentName(staffInfo.getDepartmentName());
        }
        
        //销售订单类型
        Map soTypeMap = this.datasetCommonService.getSOAType();
        //币种
        Map currencyTypeMap = this.datasetCommonService.getCurrencyType();
        //计税类型
        Map taxTypeMap = this.datasetCommonService.getTaxType();
        //状态
        Map soStatusMap = DataBox.getSoStatusMap();
        //发运状态
        Map shipmentStatusMap = DataBox.getSoShipmentStatusMap();
        //收款状态
        Map receiptStatusMap = DataBox.getSoReceiptStatusMap();
        //获取项目
        Map projectMap = this.masterDataCommonService.getProjectMap();
        //获取客户
        Map customerMap = this.masterDataCommonService.getCustomerMap();
        //审批状态
        Map approveStatusMap = GlobalDataBox.getApproveStatusMap();
        
        //页面属性设置
        model.addAttribute("soHead", soAgreementHead);
        model.addAttribute("soTypeMap", soTypeMap);
        model.addAttribute("currencyTypeMap", currencyTypeMap);
        model.addAttribute("taxTypeMap", taxTypeMap);
        model.addAttribute("soStatusMap", soStatusMap);
        model.addAttribute("shipmentStatusMap", shipmentStatusMap);
        model.addAttribute("receiptStatusMap", receiptStatusMap);
        model.addAttribute("projectMap", projectMap);
        model.addAttribute("customerMap", customerMap);
        model.addAttribute("approveStatusMap", approveStatusMap);
        
        return "basic.jsp?content=soa/soaEdit";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-10-19 17:02:47
     * @author 
     * @param soAgreementHead
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editSoHead")
    public String editSoAgreementHead(@Valid SoAgreementHead soAgreementHead, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        //参数校验
        Map<String, String> errorMap = this.validateParameters(bindingResult, model);
        if(errorMap.size()>0) {
            return "forward:getSoHead";
        }
        
        //对当前编辑的对象初始化默认的字段
        
        //保存编辑的数据
        this.soAgreementHeadService.insertOrUpdateDataObject(soAgreementHead);
        //提示信息
        attr.addFlashAttribute("hint", "success");
        attr.addAttribute("soHeadCode", soAgreementHead.getSoHeadCode());
        
        return "redirect:getSoHead";
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-10-19 17:02:47
     * @author 
     * @param soAgreementHead
     * @return String
     *
     */
    @RequestMapping("deleteSoHead")
    public String deleteSoAgreementHead(SoAgreementHead soAgreementHead, RedirectAttributes attr) {
        //删除数据前验证数据
        if(soAgreementHead!=null&&soAgreementHead.getSoHeadId()!=null&&StringUtils.isNotBlank(soAgreementHead.getSoHeadCode())) {
            if(soAgreementHead.getStatus().equals("NEW")) {
                //删除数据
                this.soAgreementHeadService.deleteDataObject(soAgreementHead);
                
                //提示信息
                attr.addFlashAttribute("hint", "success");
            }else {
                //提示信息
                attr.addFlashAttribute("hint", "hint");
                attr.addFlashAttribute("alertMessage", "非新建状态的协议不能删除");
            }
            
        }
        
        return "redirect:getSoHeadList";
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
//                int errorCode = this.soaEcoService.saveModifyHistory(code);
//                if(errorCode!=0) {
//                    //提示信息
//                    attr.addFlashAttribute("hint", "hint");
//                    attr.addFlashAttribute("alertMessage", "当前销售协议保存变更历史错误，无法变更");
//                    attr.addAttribute("soHeadCode", code);
//                    return "redirect:getSoHead";
//                }
//            }
            
            //更新审核状态
            this.soAgreementHeadService.updateApproveStatus(code, approveStatus);
          //提示信息
            attr.addFlashAttribute("hint", "success");
            attr.addAttribute("soHeadCode", code);
        }else {
            //提示信息
            attr.addFlashAttribute("hint", "hint");
            attr.addFlashAttribute("alertMessage", "提交或审批数据错误");
            attr.addAttribute("soHeadCode", code);
        }
        
        return "redirect:getSoHead";
    }
}
