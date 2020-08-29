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
package com.erp.finance.receipt.controller;

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
import com.erp.finance.pay.dao.model.PayHead;
import com.erp.finance.receipt.dao.data.DataBox;
import com.erp.finance.receipt.dao.model.ReceiptHead;
import com.erp.finance.receipt.dao.model.ReceiptHeadCO;
import com.erp.finance.receipt.service.ReceiptHeadService;
import com.erp.finance.receipt.service.ReceiptLineService;
import com.erp.hr.dao.model.HrStaffInfoRO;
import com.erp.hr.service.HrCommonService;
import com.erp.masterdata.common.service.MasterDataCommonService;
import com.erp.masterdata.customer.dao.model.MdCustomerBank;
import com.erp.masterdata.customer.dao.model.MdCustomerBankCO;
import com.erp.masterdata.customer.service.MdCustomerBankService;
import com.erp.masterdata.vendor.dao.model.MdVendorBank;
import com.erp.masterdata.vendor.dao.model.MdVendorBankCO;
import com.erp.masterdata.vendor.service.MdVendorBankService;
import com.erp.order.po.dao.model.PoHead;
import com.erp.order.po.dao.model.PoHeadCO;
import com.erp.order.po.service.PoHeadService;
import com.erp.order.po.service.PoLineService;
import com.erp.order.so.dao.model.SoHead;
import com.erp.order.so.dao.model.SoHeadCO;
import com.erp.order.so.service.SoHeadService;
import com.erp.order.so.service.SoLineService;

@Controller
@RequestMapping("/web/receiptHead")
public class ReceiptHeadWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(ReceiptHeadWebController.class);
    
    //服务层注入
    @Autowired
    private ReceiptHeadService receiptHeadService;
    @Autowired
    private ReceiptLineService receiptLineService;
    @Autowired
    private DatasetCommonService datasetCommonService;
    @Autowired
    private HrCommonService hrCommonService;
    @Autowired
    private MasterDataCommonService masterDataCommonService;
    @Autowired
    private MdVendorBankService mdVendorBankService;
    @Autowired
    private SoHeadService soHeadService;
    @Autowired
    private SoLineService soLineService;
    
    @Override
    public String getExceptionRedirectURL() {
        return "redirect:getReceiptHeadList";
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-07-22 12:48:34
     * @author 
     * @param pages
     * @param receiptHeadCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getReceiptHeadList")
    public String getReceiptHeadList(Pages pages, ReceiptHeadCO receiptHeadCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        
        //分页查询数据
        List<ReceiptHead> receiptHeadList = this.receiptHeadService.getDataObjectsForDataAuth("", pages, receiptHeadCO);
        
        //收款来源类型
        Map receiptSourceTypeMap = DataBox.getReceiptSourceType();
        //状态
        Map receiptStatusMap = DataBox.getReceiptStatusMap();
        //获取出纳状态
        Map receivedStatusMap = DataBox.getReceivedStatusMap();
        //获取客户
        Map customerMap = this.masterDataCommonService.getCustomerMap();
        //审批状态
        Map approveStatusMap = GlobalDataBox.getApproveStatusMap();
        
        //循环设置职员和组织信息
        for(ReceiptHead receiptHead: receiptHeadList) {
            receiptHead.setStaffName(this.hrCommonService.getHrStaff(receiptHead.getStaffCode()).getStaffName());
            receiptHead.setDepartmentName(this.hrCommonService.getHrDepartment(receiptHead.getDepartmentCode()).getDepartmentName());
        }
        
        //页面属性设置
        model.addAttribute("receiptHeadList", receiptHeadList);
        model.addAttribute("pages", pages);
        model.addAttribute("receiptSourceTypeMap", receiptSourceTypeMap);
        model.addAttribute("receiptStatusMap", receiptStatusMap);
        model.addAttribute("receivedStatusMap", receivedStatusMap);
        model.addAttribute("customerMap", customerMap);
        model.addAttribute("approveStatusMap", approveStatusMap);
        
        return "basic.jsp?content=receipt/receiptList";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-07-22 12:48:34
     * @author 
     * @param receiptHead
     * @param model
     * @return String
     *
     */
    @RequestMapping("getReceiptHead")
    public String getReceiptHead(ReceiptHead receiptHead, Model model) {
        //查询要编辑的数据
        if(receiptHead!=null&&StringUtils.isNotBlank(receiptHead.getReceiptHeadCode())) {
            receiptHead = this.receiptHeadService.getDataObject(receiptHead.getReceiptHeadCode());
            //设置显示字段
            receiptHead.setStaffName(this.hrCommonService.getHrStaff(receiptHead.getStaffCode()).getStaffName());
            receiptHead.setDepartmentName(this.hrCommonService.getHrDepartment(receiptHead.getDepartmentCode()).getDepartmentName());
            //获取采购订单信息
            if(receiptHead.getReceiptSourceType().equals("SO")) {
                SoHead soHead = this.soHeadService.getDataObject(receiptHead.getReceiptSourceHeadCode());
                receiptHead.setReceiptSourceHeadName(soHead.getSoName());
                //获取采购订单金额
                BigDecimal amount = this.soLineService.getSoAmount(receiptHead.getReceiptHeadCode());
                receiptHead.setReceiptSourceHeadAmount(amount==null?0D:amount.doubleValue());
                //获取已付款历史金额
                BigDecimal HISAmount = this.receiptLineService.getHISReceiptAmountForSO(receiptHead.getReceiptSourceHeadCode(), receiptHead.getReceiptHeadCode());
                receiptHead.setReceiptSourceHeadHISAmount(HISAmount==null?0D:HISAmount.doubleValue());
            }
            //获取收款人信息
            receiptHead.setPayerName(this.masterDataCommonService.getCustomerMap().get(receiptHead.getPayer()));
            //获取银行信息
            receiptHead.setBankName(this.datasetCommonService.getBank().get(receiptHead.getBankCode()));
        }else {
            //初始化默认字段
            receiptHead.setAmount(0D);
            receiptHead.setReceivedStatus("N");
            receiptHead.setPreReceiptFlag("N");
            receiptHead.setStatus("NEW");
            
            //获取当前用户职员信息
            HrStaffInfoRO staffInfo = this.hrCommonService.getStaffInfo(ShiroUtil.getUsername());
            receiptHead.setStaffCode(staffInfo.getStaffCode());
            receiptHead.setDepartmentCode(staffInfo.getDepartmentCode());
            receiptHead.setStaffName(staffInfo.getStaffName());
            receiptHead.setDepartmentName(staffInfo.getDepartmentName());
        }
        
        //币种
        Map currencyTypeMap = this.datasetCommonService.getCurrencyType();
        //付款来源类型
        Map receiptSourceTypeMap = DataBox.getReceiptSourceType();
        //状态
        Map receiptStatusMap = DataBox.getReceiptStatusMap();
        //获取出纳状态
        Map receivedStatusMap = DataBox.getReceivedStatusMap();
        //获取付款方式
        Map receiptModeMap = this.datasetCommonService.getPayMode();
        //获取供应商
        Map customerMap = this.masterDataCommonService.getCustomerMap();
        //获取本公司
        Map customerOwnMap = this.masterDataCommonService.getOwnVendorMap();
        //审批状态
        Map approveStatusMap = GlobalDataBox.getApproveStatusMap();
        
        //页面属性设置
        model.addAttribute("receiptHead", receiptHead);
        model.addAttribute("currencyTypeMap", currencyTypeMap);
        model.addAttribute("receiptSourceTypeMap", receiptSourceTypeMap);
        model.addAttribute("receiptStatusMap", receiptStatusMap);
        model.addAttribute("receivedStatusMap", receivedStatusMap);
        model.addAttribute("receiptModeMap", receiptModeMap);
        model.addAttribute("customerMap", customerMap);
        model.addAttribute("customerOwnMap", customerOwnMap);
        model.addAttribute("approveStatusMap", approveStatusMap);
        
        return "basic.jsp?content=receipt/receiptEdit";
    }
    
    
    
    /**
     * 
     * @description 获取销售订单选择框
     * @date 2020年7月20日
     * @author dongbin
     * @return
     * @return String
     *
     */
    @RequestMapping("getSelectSOModal")
    public String getSelectSOModal(Pages pages, SoHeadCO soHeadCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        
        //分页查询数据
        List<SoHead> soHeadList = this.soHeadService.getDataObjects(pages, soHeadCO);
        
        //采购订单类型
        Map soTypeMap = this.datasetCommonService.getSOType();
        //状态
        Map soStatusMap = com.erp.order.so.dao.data.DataBox.getSoStatusMap();
        //获取供应商
        Map customerMap = this.masterDataCommonService.getCustomerMap();
        //获取项目
        Map projectMap = this.masterDataCommonService.getProjectMap();
        
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
        model.addAttribute("customerMap", customerMap);
        model.addAttribute("projectMap", projectMap);
        
        return "receipt/pop/selectSOModal";
    }
    
    
    
    /**
     * 
     * @description 获取付款方对应的银行选择框
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
        List<MdVendorBank> mdVendorBankList = this.mdVendorBankService.getBankListByVendorCode(pages, mdVendorBankCO);
        
        //获取银行
        Map bankMap = this.datasetCommonService.getBank();
        
        
        //页面属性设置
        model.addAttribute("mdCustomerBankList", mdVendorBankList);
        model.addAttribute("pages", pages);
        model.addAttribute("bankMap", bankMap);
        
        return "receipt/pop/selectBankModal";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-07-22 12:48:34
     * @author 
     * @param receiptHead
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editReceiptHead")
    public String editReceiptHead(@Valid ReceiptHead receiptHead, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        //参数校验
        Map<String, String> errorMap = this.validateParameters(bindingResult, model);
        if(errorMap.size()>0) {
            return "forward:getReceiptHead";
        }
        
        //对当前编辑的对象初始化默认的字段
        
        //保存编辑的数据
        this.receiptHeadService.insertOrUpdateDataObject(receiptHead);
        //提示信息
        attr.addFlashAttribute("hint", "success");
        attr.addAttribute("receiptHeadCode", receiptHead.getReceiptHeadCode());
        
        return "redirect:getReceiptHead";
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-07-22 12:48:34
     * @author 
     * @param receiptHead
     * @return String
     *
     */
    @RequestMapping("deleteReceiptHead")
    public String deleteReceiptHead(ReceiptHead receiptHead, RedirectAttributes attr) {
        //删除数据前验证数据
        if(receiptHead!=null&&receiptHead.getReceiptHeadId()!=null) {
            if(receiptHead.getStatus().equals("NEW")) {
                //删除数据
                this.receiptHeadService.deleteDataObject(receiptHead);
                
                //提示信息
                attr.addFlashAttribute("hint", "success");
            }else {
                //提示信息
                attr.addFlashAttribute("hint", "hint");
                attr.addFlashAttribute("alertMessage", "非新建状态的收款不能删除");
            }
        }
        
        return "redirect:getReceiptHeadList";
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
            this.receiptHeadService.updateApproveStatus(code, approveStatus);
          //提示信息
            attr.addFlashAttribute("hint", "success");
            attr.addAttribute("receiptHeadCode", code);
        }else {
            //提示信息
            attr.addFlashAttribute("hint", "hint");
            attr.addFlashAttribute("alertMessage", "提交或审批数据错误");
            attr.addAttribute("receiptHeadCode", code);
        }
        
        return "redirect:getReceiptHead";
    }
}
