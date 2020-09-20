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
package com.erp.finance.ap.invoice.controller;

import java.math.BigDecimal;
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
import com.framework.util.JsonResultUtil;
import com.framework.util.JsonUtil;
import com.framework.util.ShiroUtil;
import com.erp.dataset.service.DatasetCommonService;
import com.erp.finance.ap.invoice.dao.data.DataBox;
import com.erp.finance.ap.invoice.dao.model.ApInvoiceHead;
import com.erp.finance.ap.invoice.dao.model.ApInvoiceHeadCO;
import com.erp.finance.ap.invoice.service.ApInvoiceHeadService;
import com.erp.finance.ap.invoice.service.ApInvoiceLineService;
import com.erp.hr.dao.model.HrStaffInfoRO;
import com.erp.hr.service.HrCommonService;
import com.erp.masterdata.common.service.MasterDataCommonService;
import com.erp.masterdata.customer.dao.model.MdCustomerBank;
import com.erp.masterdata.customer.dao.model.MdCustomerBankCO;
import com.erp.masterdata.vendor.dao.model.MdVendorBank;
import com.erp.masterdata.vendor.dao.model.MdVendorBankCO;
import com.erp.masterdata.vendor.service.MdVendorBankService;
import com.erp.order.po.dao.model.PoHead;
import com.erp.order.po.dao.model.PoHeadCO;
import com.erp.order.po.service.PoHeadService;
import com.erp.order.po.service.PoLineService;

@Controller
@RequestMapping("/web/apInvoiceHead")
public class ApInvoiceHeadWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(ApInvoiceHeadWebController.class);
    
    //服务层注入
    @Autowired
    private ApInvoiceHeadService apInvoiceHeadService;
    @Autowired
    private ApInvoiceLineService apInvoiceLineService;
    @Autowired
    private DatasetCommonService datasetCommonService;
    @Autowired
    private HrCommonService hrCommonService;
    @Autowired
    private MasterDataCommonService masterDataCommonService;
    @Autowired
    private PoHeadService poHeadService;
    @Autowired
    private MdVendorBankService mdVendorBankService;
    @Autowired
    private PoLineService poLineService;
    
    @Override
    public String getExceptionRedirectURL() {
        return "redirect:getApInvoiceHeadList";
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-07-19 14:16:19
     * @author 
     * @param pages
     * @param payHeadCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getApInvoiceHeadList")
    public String getApInvoiceHeadList(Pages pages, ApInvoiceHeadCO payHeadCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        
        //分页查询数据
        List<ApInvoiceHead> payHeadList = this.apInvoiceHeadService.getDataObjectsForDataAuth("", pages, payHeadCO);
        
        //付款来源类型
        Map paySourceTypeMap = DataBox.getApInvoiceSourceType();
        //状态
        Map payStatusMap = DataBox.getApInvoiceStatusMap();
        //获取出纳状态
        Map paidStatusMap = DataBox.getPaidStatusMap();
        //获取供应商
        Map vendorMap = this.masterDataCommonService.getVendorMap();
        //审批状态
        Map approveStatusMap = GlobalDataBox.getApproveStatusMap();
        
        //循环设置职员和组织信息
        for(ApInvoiceHead payHead: payHeadList) {
            payHead.setStaffName(this.hrCommonService.getHrStaff(payHead.getStaffCode()).getStaffName());
            payHead.setDepartmentName(this.hrCommonService.getHrDepartment(payHead.getDepartmentCode()).getDepartmentName());
        }
        
        //页面属性设置
        model.addAttribute("payHeadList", payHeadList);
        model.addAttribute("pages", pages);
        model.addAttribute("paySourceTypeMap", paySourceTypeMap);
        model.addAttribute("payStatusMap", payStatusMap);
        model.addAttribute("paidStatusMap", paidStatusMap);
        model.addAttribute("vendorMap", vendorMap);
        model.addAttribute("approveStatusMap", approveStatusMap);
        
        return "basic.jsp?content=apInvoice/apInvoiceList";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-07-19 14:16:19
     * @author 
     * @param payHead
     * @param model
     * @return String
     *
     */
    @RequestMapping("getApInvoiceHead")
    public String getApInvoiceHead(ApInvoiceHead payHead, Model model) {
        //查询要编辑的数据
        if(payHead!=null&&StringUtils.isNotBlank(payHead.getInvoiceHeadCode())) {
            payHead = this.apInvoiceHeadService.getDataObject(payHead.getInvoiceHeadCode());
            //设置显示字段
            payHead.setStaffName(this.hrCommonService.getHrStaff(payHead.getStaffCode()).getStaffName());
            payHead.setDepartmentName(this.hrCommonService.getHrDepartment(payHead.getDepartmentCode()).getDepartmentName());
            //获取采购订单信息
            if(payHead.getInvoiceSourceType().equals("PO")) {
                PoHead poHead = this.poHeadService.getDataObject(payHead.getInvoiceSourceHeadCode());
                payHead.setPaySourceHeadName(poHead.getPoName());
                //获取采购订单金额
                BigDecimal amount = this.poLineService.getPoAmount(payHead.getInvoiceSourceHeadCode());
                payHead.setPaySourceHeadAmount(amount==null?0D:amount.doubleValue());
                //获取已付款历史金额
                BigDecimal HISAmount = this.apInvoiceLineService.getHISApInvoiceAmountForPO(payHead.getInvoiceSourceHeadCode(), payHead.getInvoiceHeadCode());
                payHead.setPaySourceHeadHISAmount(HISAmount==null?0D:HISAmount.doubleValue());
            }
            //获取收款人信息
            payHead.setReceiverName(this.masterDataCommonService.getVendorMap().get(payHead.getReceiver()));
            //获取银行信息
            payHead.setBankName(this.datasetCommonService.getBank().get(payHead.getBankCode()));
        }else {
            //初始化默认字段
            //payHead.setAmount(0D);
            payHead.setPaidStatus("N");
            payHead.setPrepayFlag("N");
            payHead.setStatus("NEW");
            
            //获取当前用户职员信息
            HrStaffInfoRO staffInfo = this.hrCommonService.getStaffInfo(ShiroUtil.getUsername());
            payHead.setStaffCode(staffInfo.getStaffCode());
            payHead.setDepartmentCode(staffInfo.getDepartmentCode());
            payHead.setStaffName(staffInfo.getStaffName());
            payHead.setDepartmentName(staffInfo.getDepartmentName());
        }
        
        //币种
        Map currencyTypeMap = this.datasetCommonService.getCurrencyType();
        //付款来源类型
        Map paySourceTypeMap = DataBox.getApInvoiceSourceType();
        //状态
        Map payStatusMap = DataBox.getApInvoiceStatusMap();
        //获取出纳状态
        Map paidStatusMap = DataBox.getPaidStatusMap();
        //获取付款方式
        Map payModeMap = this.datasetCommonService.getPayMode();
        //获取供应商
        Map vendorMap = this.masterDataCommonService.getVendorMap();
        //获取本公司
        Map vendorOwnMap = this.masterDataCommonService.getOwnVendorMap();
        //审批状态
        Map approveStatusMap = GlobalDataBox.getApproveStatusMap();
        
        //页面属性设置
        model.addAttribute("payHead", payHead);
        model.addAttribute("currencyTypeMap", currencyTypeMap);
        model.addAttribute("paySourceTypeMap", paySourceTypeMap);
        model.addAttribute("payStatusMap", payStatusMap);
        model.addAttribute("paidStatusMap", paidStatusMap);
        model.addAttribute("payModeMap", payModeMap);
        model.addAttribute("vendorMap", vendorMap);
        model.addAttribute("vendorOwnMap", vendorOwnMap);
        model.addAttribute("approveStatusMap", approveStatusMap);
        
        return "basic.jsp?content=apInvoice/apInvoiceEdit";
    }
    
    
    
    /**
     * 
     * @description 获取采购订单选择框
     * @date 2020年7月20日
     * @author dongbin
     * @return
     * @return String
     *
     */
    @RequestMapping("getSelectPOModal")
    public String getSelectPOModal(Pages pages, PoHeadCO poHeadCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        
        //分页查询数据
        List<PoHead> poHeadList = this.poHeadService.getDataObjects(pages, poHeadCO);
        
        //采购订单类型
        Map poTypeMap = this.datasetCommonService.getPOType();
        //状态
        Map poStatusMap = com.erp.order.po.dao.data.DataBox.getPoStatusMap();
        //获取供应商
        Map vendorMap = this.masterDataCommonService.getVendorMap();
        //获取项目
        Map projectMap = this.masterDataCommonService.getProjectMap();
        
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
        model.addAttribute("vendorMap", vendorMap);
        model.addAttribute("projectMap", projectMap);
        
        return "apInvoice/pop/selectPOModal";
    }
    
    
    
    /**
     * 
     * @description 获取收款方对应的银行选择框
     * @date 2020年7月20日
     * @author dongbin
     * @return
     * @return String
     *
     */
    @RequestMapping("getSelectBankModal")
    public String getSelectBankModal(Pages pages, MdVendorBankCO mdVendorBankCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        pages.setMax(100);
        
        //分页查询数据
        List<MdVendorBank> mdCustomerBankList = this.mdVendorBankService.getBankListByVendorCode(pages, mdVendorBankCO);
        
        //获取银行
        Map bankMap = this.datasetCommonService.getBank();
        
        
        //页面属性设置
        model.addAttribute("mdCustomerBankList", mdCustomerBankList);
        model.addAttribute("pages", pages);
        model.addAttribute("bankMap", bankMap);
        
        return "apInvoice/pop/selectBankModal";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-07-19 14:16:19
     * @author 
     * @param payHead
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editApInvoiceHead")
    public String editApInvoiceHead(@Valid ApInvoiceHead payHead, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        //参数校验
        Map<String, String> errorMap = this.validateParameters(bindingResult, model);
        if(errorMap.size()>0) {
            return "forward:getApInvoiceHead";
        }
        
        //对当前编辑的对象初始化默认的字段
        
        //保存编辑的数据
        this.apInvoiceHeadService.insertOrUpdateDataObject(payHead);
        //提示信息
        attr.addFlashAttribute("hint", "success");
        attr.addAttribute("invoiceHeadCode", payHead.getInvoiceHeadCode());
        
        return "redirect:getApInvoiceHead";
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-07-19 14:16:19
     * @author 
     * @param payHead
     * @return String
     *
     */
    @RequestMapping("deleteApInvoiceHead")
    public String deleteApInvoiceHead(ApInvoiceHead payHead, RedirectAttributes attr) {
        //删除数据前验证数据
        if(payHead!=null&&payHead.getInvoiceHeadId()!=null) {
            if(payHead.getStatus().equals("NEW")) {
                //删除数据
                this.apInvoiceHeadService.deleteDataObject(payHead);
                
                //提示信息
                attr.addFlashAttribute("hint", "success");
            }else {
                //提示信息
                attr.addFlashAttribute("hint", "hint");
                attr.addFlashAttribute("alertMessage", "非新建状态的发票不能删除");
            }
        }
        
        return "redirect:getApInvoiceHeadList";
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
            this.apInvoiceHeadService.updateApproveStatus(code, approveStatus);
          //提示信息
            attr.addFlashAttribute("hint", "success");
            attr.addAttribute("invoiceHeadCode", code);
        }else {
            //提示信息
            attr.addFlashAttribute("hint", "hint");
            attr.addFlashAttribute("alertMessage", "提交或审批数据错误");
            attr.addAttribute("invoiceHeadCode", code);
        }
        
        return "redirect:getApInvoiceHead";
    }
}
