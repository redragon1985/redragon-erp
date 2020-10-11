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
package com.erp.finance.ar.receipt.controller;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.controller.ControllerSupport;
import com.framework.dao.data.GlobalDataBox;
import com.framework.dao.model.Pages;
import com.framework.util.JsonResultUtil;
import com.framework.util.JsonUtil;
import com.framework.util.ShiroUtil;
import com.erp.common.voucher.service.FinVoucherBillRService;
import com.erp.common.voucher.service.FinVoucherHeadService;
import com.erp.common.voucher.service.FinVoucherModelHeadService;
import com.erp.dataset.service.DatasetCommonService;
import com.erp.finance.ap.pay.dao.model.ApPayHead;
import com.erp.finance.ap.pay.dao.model.ApPayLine;
import com.erp.finance.ap.pay.service.ApPayLineService;
import com.erp.finance.ar.receipt.dao.data.DataBox;
import com.erp.finance.ar.receipt.dao.model.ArReceiptHead;
import com.erp.finance.ar.receipt.dao.model.ArReceiptHeadCO;
import com.erp.finance.ar.receipt.dao.model.ArReceiptLine;
import com.erp.finance.ar.receipt.service.ArReceiptHeadService;
import com.erp.finance.ar.receipt.service.ArReceiptLineService;
import com.erp.hr.dao.model.HrStaffInfoRO;
import com.erp.hr.service.HrCommonService;
import com.erp.masterdata.common.service.MasterDataCommonService;
import com.erp.masterdata.vendor.service.MdVendorBankService;

@Controller
@RequestMapping("/web/arReceiptHead")
public class ArReceiptHeadWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(ArReceiptHeadWebController.class);
    
    //服务层注入
    @Autowired
    private ArReceiptHeadService arReceiptHeadService;
    @Autowired
    private ArReceiptLineService arReceiptLineService;
    @Autowired
    private DatasetCommonService datasetCommonService;
    @Autowired
    private HrCommonService hrCommonService;
    @Autowired
    private MasterDataCommonService masterDataCommonService;
    @Autowired
    @Qualifier("finVoucherModelHeadServiceCommon")
    private FinVoucherModelHeadService finVoucherModelHeadService;
    @Autowired
    @Qualifier("finVoucherHeadServiceCommon")
    private FinVoucherHeadService finVoucherHeadService;
    @Autowired
    @Qualifier("finVoucherBillRServiceCommon")
    private FinVoucherBillRService finVoucherBillRService;
    
    
    
    @Override
    public String getExceptionRedirectURL() {
        return "redirect:getArReceiptHeadList";
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-09-15 14:46:22
     * @author 
     * @param pages
     * @param arReceiptHeadCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getArReceiptHeadList")
    public String getArReceiptHeadList(Pages pages, ArReceiptHeadCO arReceiptHeadCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        
        //分页查询数据
        List<ArReceiptHead> arReceiptHeadList = this.arReceiptHeadService.getDataObjects(pages, arReceiptHeadCO);
        
        //付款类型
        Map receiptTypeMap = DataBox.getArReceiptType();
        //状态
        Map receiptStatusMap = GlobalDataBox.getStatusMap();
        //获取供应商
        Map customerMap = this.masterDataCommonService.getCustomerMap();
        //审批状态
        Map approveStatusMap = GlobalDataBox.getApproveStatusMap();
        
        //循环设置职员和组织信息
        for(ArReceiptHead receiptHead: arReceiptHeadList) {
            receiptHead.setStaffName(this.hrCommonService.getHrStaff(receiptHead.getStaffCode()).getStaffName());
            receiptHead.setDepartmentName(this.hrCommonService.getHrDepartment(receiptHead.getDepartmentCode()).getDepartmentName());
        }
        
        //页面属性设置
        model.addAttribute("arReceiptHeadList", arReceiptHeadList);
        model.addAttribute("pages", pages);
        model.addAttribute("receiptTypeMap", receiptTypeMap);
        model.addAttribute("receiptStatusMap", receiptStatusMap);
        model.addAttribute("customerMap", customerMap);
        model.addAttribute("approveStatusMap", approveStatusMap);
        
        return "basic.jsp?content=arReceipt/arReceiptList";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-09-15 14:46:22
     * @author 
     * @param arReceiptHead
     * @param model
     * @return String
     *
     */
    @RequestMapping("getArReceiptHead")
    public String getArReceiptHead(ArReceiptHead receiptHead, Model model) {
        //查询要编辑的数据
        if(receiptHead!=null&&StringUtils.isNotBlank(receiptHead.getReceiptHeadCode())) {
            receiptHead = this.arReceiptHeadService.getDataObject(receiptHead.getReceiptHeadCode());
            //设置显示字段
            receiptHead.setStaffName(this.hrCommonService.getHrStaff(receiptHead.getStaffCode()).getStaffName());
            receiptHead.setDepartmentName(this.hrCommonService.getHrDepartment(receiptHead.getDepartmentCode()).getDepartmentName());
            //获取供应商信息
            receiptHead.setCustomerName(this.masterDataCommonService.getCustomerMap().get(receiptHead.getCustomerCode()));
        }else {
            //初始化默认字段
            receiptHead.setStatus("NEW");
            
            //获取当前用户职员信息
            HrStaffInfoRO staffInfo = this.hrCommonService.getStaffInfo(ShiroUtil.getUsername());
            receiptHead.setStaffCode(staffInfo.getStaffCode());
            receiptHead.setDepartmentCode(staffInfo.getDepartmentCode());
            receiptHead.setStaffName(staffInfo.getStaffName());
            receiptHead.setDepartmentName(staffInfo.getDepartmentName());
        }
        
        //币种
        Map currencyMap = this.datasetCommonService.getCurrencyType();
        //收款来源类型
        Map receiptTypeMap = DataBox.getArReceiptType();
        //状态
        Map receiptStatusMap = GlobalDataBox.getStatusMap();
        //获取供应商
        Map customerMap = this.masterDataCommonService.getCustomerMap();
        //审批状态
        Map approveStatusMap = GlobalDataBox.getApproveStatusMap();
        
        //页面属性设置
        model.addAttribute("receiptHead", receiptHead);
        model.addAttribute("currencyMap", currencyMap);
        model.addAttribute("receiptTypeMap", receiptTypeMap);
        model.addAttribute("receiptStatusMap", receiptStatusMap);
        model.addAttribute("customerMap", customerMap);
        model.addAttribute("approveStatusMap", approveStatusMap);
        
        return "basic.jsp?content=arReceipt/arReceiptEdit";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-09-15 14:46:22
     * @author 
     * @param arReceiptHead
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editArReceiptHead")
    public String editArReceiptHead(@Valid ArReceiptHead arReceiptHead, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        //参数校验
        Map<String, String> errorMap = this.validateParameters(bindingResult, model);
        if(errorMap.size()>0) {
            return "forward:getArReceiptHead";
        }
        
        //对当前编辑的对象初始化默认的字段
        
        //保存编辑的数据
        this.arReceiptHeadService.insertOrUpdateDataObject(arReceiptHead);
        //提示信息
        attr.addFlashAttribute("hint", "success");
        attr.addAttribute("receiptHeadCode", arReceiptHead.getReceiptHeadCode());
        
        return "redirect:getArReceiptHead";
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-09-15 14:46:22
     * @author 
     * @param arReceiptHead
     * @return String
     *
     */
    @RequestMapping("deleteArReceiptHead")
    public String deleteArReceiptHead(ArReceiptHead arReceiptHead, RedirectAttributes attr) {
        //删除数据前验证数据
        if(arReceiptHead!=null&&arReceiptHead.getReceiptHeadId()!=null) {
            if(arReceiptHead.getStatus().equals("NEW")) {
                //删除数据
                this.arReceiptHeadService.deleteDataObject(arReceiptHead);
                
                //提示信息
                attr.addFlashAttribute("hint", "success");
            }else {
                //提示信息
                attr.addFlashAttribute("hint", "hint");
                attr.addFlashAttribute("alertMessage", "非新建状态的收款不能删除");
            }
        }
        
        return "redirect:getArReceiptHeadList";
    }
    
    
    
    /**
     * 
     * @description 更新审批状态
     * @date 2020年8月4日
     * @author dongbin
     * @param code
     * @param approveStatus
     * @param attr
     * @return String
     *
     */
    @RequestMapping("updateApproveStatus")
    public String updateApproveStatus(String code, String approveStatus, RedirectAttributes attr) {
        
        if(StringUtils.isNotBlank(code)&&StringUtils.isNotBlank(approveStatus)) {
            if(approveStatus.equals("UNSUBMIT")) {
                boolean voucherFlag = this.finVoucherBillRService.isExistVoucherRelateArReceipt(code);
                if(voucherFlag) {
                    //提示信息
                    attr.addFlashAttribute("hint", "hint");
                    attr.addFlashAttribute("alertMessage", "当前收款已生成凭证不能变更");
                    attr.addAttribute("receiptHeadCode", code);
                    return "redirect:getArReceiptHead";
                }
            }
            
            //更新审核状态
            this.arReceiptHeadService.updateApproveStatus(code, approveStatus);
            //提示信息
            attr.addFlashAttribute("hint", "success");
            attr.addAttribute("receiptHeadCode", code);
        }else {
            //提示信息
            attr.addFlashAttribute("hint", "hint");
            attr.addFlashAttribute("alertMessage", "提交或审批数据错误");
            attr.addAttribute("receiptHeadCode", code);
        }
        
        return "redirect:getArReceiptHead";
    }
    
    
    
    /**
     * 
     * @description 自动创建凭证分录
     * @date 2020年9月25日
     * @author dongbin
     * @param headCode
     * @return
     * @return String
     *
     */
    @RequestMapping("autoCreateVoucherEntry")
    @ResponseBody
    public String autoCreateVoucherEntry(String headCode){
        try {
            //删除自动生成的凭证和分录
            //根据单据头code获取凭证头code
            String voucherHeadCode = this.finVoucherBillRService.getVoucherHeadCodeByBillHeadCode("RECEIPT", headCode);
            //删除凭证
            if(StringUtils.isNotBlank(voucherHeadCode)) {
                this.finVoucherHeadService.deleteFinVoucherHeadByVoucherHeadCode(voucherHeadCode);
            }
            
            //自动生成凭证和分录
            //计算分录的金额
            //获取入库行
            List<ArReceiptLine> lineList = this.arReceiptLineService.getArReceiptLineListByHeadCode(headCode);
            
            BigDecimal voucherAmount = new BigDecimal(0D);
            //循环获取加和
            for(ArReceiptLine line: lineList) {
                BigDecimal amount = new BigDecimal(line.getInvoiceReceiptAmount());
                //计算合计金额
                voucherAmount = voucherAmount.add(amount);
            }
            
            //调用自动创建方法
            this.finVoucherModelHeadService.autoCreateVoucher(headCode, new Double[]{voucherAmount.doubleValue()}, "RECEIPT");
            return JsonResultUtil.getErrorJson(0);
        }catch(Exception e) {
            return JsonResultUtil.getErrorJson(-1, "重新生成分录错误");
        }
    }
}
