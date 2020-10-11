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
package com.erp.order.so.controller;

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
import com.erp.common.ap.invoice.service.ApInvoiceHeadService;
import com.erp.common.ar.invoice.service.ArInvoiceHeadService;
import com.erp.common.inv.input.service.InvInputHeadService;
import com.erp.common.inv.output.service.InvOutputHeadService;
import com.erp.dataset.service.DatasetCommonService;
import com.erp.hr.dao.model.HrStaffInfoRO;
import com.erp.hr.service.HrCommonService;
import com.erp.masterdata.common.service.MasterDataCommonService;
import com.erp.order.po.dao.model.PoHead;
import com.erp.order.so.dao.data.DataBox;
import com.erp.order.so.dao.model.SoHead;
import com.erp.order.so.dao.model.SoHeadCO;
import com.erp.order.so.service.SoHeadService;
import com.erp.order.so.service.SoLineService;

@Controller
@RequestMapping("/web/soHead")
public class SoHeadWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(SoHeadWebController.class);
    
    //服务层注入
    @Autowired
    private SoHeadService soHeadService;
    @Autowired
    private DatasetCommonService datasetCommonService;
    @Autowired
    private HrCommonService hrCommonService;
    @Autowired
    private MasterDataCommonService masterDataCommonService;
    @Autowired
    private SoLineService soLineService;
    @Autowired
    @Qualifier("invOutputHeadServiceCommon")
    private InvOutputHeadService invOutputHeadService;
    @Autowired
    @Qualifier("arInvoiceHeadServiceCommon")
    private ArInvoiceHeadService arInvoiceHeadService;
    
    @Override
    public String getExceptionRedirectURL() {
        return "redirect:getSoHeadList";
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-07-17 20:12:35
     * @author 
     * @param pages
     * @param soHeadCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getSoHeadList")
    public String getSoHeadList(Pages pages, SoHeadCO soHeadCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        
        //分页查询数据
        List<SoHead> soHeadList = this.soHeadService.getDataObjectsForDataAuth("", pages, soHeadCO);
        
        //采购销售类型
        Map soTypeMap = this.datasetCommonService.getSOType();
        //状态
        Map soStatusMap = DataBox.getSoStatusMap();
        //获取项目
        Map projectMap = this.masterDataCommonService.getProjectMap();
        //获取客户
        Map customerMap = this.masterDataCommonService.getCustomerMap();
        //审批状态
        Map approveStatusMap = GlobalDataBox.getApproveStatusMap();
        
        //循环设置职员和组织信息
        for(SoHead soHead: soHeadList) {
            soHead.setStaffName(this.hrCommonService.getHrStaff(soHead.getStaffCode()).getStaffName());
            soHead.setDepartmentName(this.hrCommonService.getHrDepartment(soHead.getDepartmentCode()).getDepartmentName());
        }
        
        //页面属性设置
        model.addAttribute("soHeadList", soHeadList);
        model.addAttribute("pages", pages);
        model.addAttribute("soTypeMap", soTypeMap);
        model.addAttribute("soStatusMap", soStatusMap);
        model.addAttribute("projectMap", projectMap);
        model.addAttribute("customerMap", customerMap);
        model.addAttribute("approveStatusMap", approveStatusMap);
        
        return "basic.jsp?content=so/soList";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-07-17 20:12:35
     * @author 
     * @param soHead
     * @param model
     * @return String
     *
     */
    @RequestMapping("getSoHead")
    public String getSoHead(SoHead soHead, Model model) {
        //查询要编辑的数据
        if(soHead!=null&&StringUtils.isNotBlank(soHead.getSoHeadCode())) {
            soHead = this.soHeadService.getDataObject(soHead.getSoHeadCode());
            //设置显示字段
            soHead.setStaffName(this.hrCommonService.getHrStaff(soHead.getStaffCode()).getStaffName());
            soHead.setDepartmentName(this.hrCommonService.getHrDepartment(soHead.getDepartmentCode()).getDepartmentName());
        }else {
            //初始化默认字段
            soHead.setAmount(0D);
            soHead.setPreReceiptAmount(0D);
            soHead.setShipmentStatus("N");
            soHead.setReceiptStatus("N");
            soHead.setStatus("NEW");
            
            //获取当前用户职员信息
            HrStaffInfoRO staffInfo = this.hrCommonService.getStaffInfo(ShiroUtil.getUsername());
            soHead.setStaffCode(staffInfo.getStaffCode());
            soHead.setDepartmentCode(staffInfo.getDepartmentCode());
            soHead.setStaffName(staffInfo.getStaffName());
            soHead.setDepartmentName(staffInfo.getDepartmentName());
        }
        
        //销售订单类型
        Map soTypeMap = this.datasetCommonService.getSOType();
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
        model.addAttribute("soHead", soHead);
        model.addAttribute("soTypeMap", soTypeMap);
        model.addAttribute("currencyTypeMap", currencyTypeMap);
        model.addAttribute("taxTypeMap", taxTypeMap);
        model.addAttribute("soStatusMap", soStatusMap);
        model.addAttribute("shipmentStatusMap", shipmentStatusMap);
        model.addAttribute("receiptStatusMap", receiptStatusMap);
        model.addAttribute("projectMap", projectMap);
        model.addAttribute("customerMap", customerMap);
        model.addAttribute("approveStatusMap", approveStatusMap);
        
        return "basic.jsp?content=so/soEdit";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-07-17 20:12:35
     * @author 
     * @param soHead
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editSoHead")
    public String editSoHead(@Valid SoHead soHead, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        //参数校验
        Map<String, String> errorMap = this.validateParameters(bindingResult, model);
        if(errorMap.size()>0) {
            return "forward:getSoHead";
        }
        
        //对当前编辑的对象初始化默认的字段
        
        //保存编辑的数据
        this.soHeadService.insertOrUpdateDataObject(soHead);
        //提示信息
        attr.addFlashAttribute("hint", "success");
        attr.addAttribute("soHeadCode", soHead.getSoHeadCode());
        
        return "redirect:getSoHead";
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-07-17 20:12:35
     * @author 
     * @param soHead
     * @return String
     *
     */
    @RequestMapping("deleteSoHead")
    public String deleteSoHead(SoHead soHead, RedirectAttributes attr) {
        //删除数据前验证数据
        if(soHead!=null&&soHead.getSoHeadId()!=null&&StringUtils.isNotBlank(soHead.getSoHeadCode())) {
            if(soHead.getStatus().equals("NEW")) {
                //删除数据
                this.soHeadService.deleteDataObject(soHead);
                
                //提示信息
                attr.addFlashAttribute("hint", "success");
            }else {
                //提示信息
                attr.addFlashAttribute("hint", "hint");
                attr.addFlashAttribute("alertMessage", "非新建状态的订单不能删除");
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
            if(approveStatus.equals("UNSUBMIT")) {
                boolean outputFlag = this.invOutputHeadService.isExistInvOutputHeadRelateSO(code);
                if(!outputFlag) {
                    boolean invoiceFlag = this.arInvoiceHeadService.isExistArInvoiceRelateSO(code);
                    if(invoiceFlag) {
                        //提示信息
                        attr.addFlashAttribute("hint", "hint");
                        attr.addFlashAttribute("alertMessage", "当前销售订单已开票不能变更");
                        attr.addAttribute("soHeadCode", code);
                        return "redirect:getSoHead";
                    }
                }else {
                    //提示信息
                    attr.addFlashAttribute("hint", "hint");
                    attr.addFlashAttribute("alertMessage", "当前销售订单已入库不能变更");
                    attr.addAttribute("soHeadCode", code);
                    return "redirect:getSoHead";
                }
            }
            
            //更新审核状态
            this.soHeadService.updateApproveStatus(code, approveStatus);
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
