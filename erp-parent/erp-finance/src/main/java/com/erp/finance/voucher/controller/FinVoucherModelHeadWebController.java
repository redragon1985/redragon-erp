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
package com.erp.finance.voucher.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.erp.dataset.service.DatasetCommonService;
import com.erp.finance.ap.invoice.dao.model.ApInvoiceHead;
import com.erp.finance.ap.invoice.dao.model.ApInvoiceHeadCO;
import com.erp.finance.ap.invoice.service.ApInvoiceHeadService;
import com.erp.finance.ap.invoice.service.ApInvoiceLineService;
import com.erp.finance.ar.invoice.dao.model.ArInvoiceHead;
import com.erp.finance.ar.invoice.dao.model.ArInvoiceHeadCO;
import com.erp.finance.ar.invoice.service.ArInvoiceHeadService;
import com.erp.finance.ar.invoice.service.ArInvoiceLineService;
import com.erp.finance.voucher.dao.data.DataBox;
import com.erp.finance.voucher.dao.model.FinVoucherModelHead;
import com.erp.finance.voucher.dao.model.FinVoucherModelHeadCO;
import com.erp.finance.voucher.dao.model.FinVoucherModelLine;
import com.erp.finance.voucher.service.FinVoucherModelHeadService;
import com.erp.finance.voucher.service.FinVoucherModelLineService;
import com.erp.finance.voucher.util.FinVoucherUtil;
import com.erp.hr.dao.model.HrStaffInfoRO;
import com.erp.hr.service.HrCommonService;
import com.erp.masterdata.common.service.MasterDataCommonService;
import com.framework.controller.ControllerSupport;
import com.framework.dao.model.Pages;
import com.framework.util.JsonResultUtil;
import com.framework.util.JsonUtil;
import com.framework.util.ShiroUtil;

import redragon.frame.hibernate.SnowFlake;

@Controller
@RequestMapping("/web/finVoucherModelHead")
public class FinVoucherModelHeadWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(FinVoucherModelHeadWebController.class);
    
    //服务层注入
    @Autowired
    private FinVoucherModelHeadService finVoucherModelHeadService;
    @Autowired
    private FinVoucherModelLineService finVoucherModelLineService;
    @Autowired
    private DatasetCommonService datasetCommonService;
    @Autowired
    private HrCommonService hrCommonService;
    @Autowired
    private MasterDataCommonService masterDataCommonService;
    @Autowired
    private ApInvoiceHeadService payHeadService;
    @Autowired
    private ApInvoiceLineService payLineService;
    @Autowired
    private ArInvoiceHeadService receiptHeadService;
    @Autowired
    private ArInvoiceLineService receiptLineService;
    
    @Override
    public String getExceptionRedirectURL() {
        return "redirect:getFinVoucherModelHeadList";
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-07-28 13:05:47
     * @author 
     * @param pages
     * @param finVoucherModelHeadCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getFinVoucherModelHeadList")
    public String getFinVoucherModelHeadList(Pages pages, FinVoucherModelHeadCO finVoucherModelHeadCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        
        //分页查询数据
        List<FinVoucherModelHead> finVoucherHeadList = this.finVoucherModelHeadService.getDataObjects(pages, finVoucherModelHeadCO);
        
        //循环设置职员和组织信息
        for(FinVoucherModelHead finVoucherHead: finVoucherHeadList) {
            finVoucherHead.setStaffName(this.hrCommonService.getHrStaff(finVoucherHead.getStaffCode()).getStaffName());
            finVoucherHead.setDepartmentName(this.hrCommonService.getHrDepartment(finVoucherHead.getDepartmentCode()).getDepartmentName());
        }
        
        //获取凭证字
        Map voucherTypeMap = this.datasetCommonService.getVoucherType();
        //获取凭证业务类型
        Map voucherBusinessTypeMap = DataBox.getVoucherBusinessType();
        
        //页面属性设置
        model.addAttribute("finVoucherHeadList", finVoucherHeadList);
        model.addAttribute("pages", pages);
        model.addAttribute("voucherTypeMap", voucherTypeMap);
        model.addAttribute("voucherBusinessTypeMap", voucherBusinessTypeMap);
        
        return "basic.jsp?content=finVoucher/voucherModelList";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-07-28 13:05:47
     * @author 
     * @param finVoucherModelHead
     * @param model
     * @return String
     *
     */
    @RequestMapping("getFinVoucherModelHead")
    public String getFinVoucherModelHead(FinVoucherModelHead finVoucherModelHead, Model model) {
        List<FinVoucherModelLine> finVoucherLineList = new ArrayList<FinVoucherModelLine>();
        
        //获取凭证字
        Map voucherTypeMap = this.datasetCommonService.getVoucherType();
        //获取凭证业务类型
        Map<String, String> voucherBusinessTypeMap = DataBox.getVoucherBusinessType();
        
        //查询要编辑的数据
        if(finVoucherModelHead!=null&&finVoucherModelHead.getVoucherHeadId()!=null&&StringUtils.isNotBlank(finVoucherModelHead.getVoucherHeadCode())) {
            finVoucherModelHead = this.finVoucherModelHeadService.getDataObject(finVoucherModelHead.getVoucherHeadId());
            //设置显示字段
            finVoucherModelHead.setStaffName(this.hrCommonService.getHrStaff(finVoucherModelHead.getStaffCode()).getStaffName());
            finVoucherModelHead.setDepartmentName(this.hrCommonService.getHrDepartment(finVoucherModelHead.getDepartmentCode()).getDepartmentName());
            //获取凭证行
            finVoucherLineList = this.finVoucherModelLineService.getFinVoucherModelLineListByVoucherHeadCode(finVoucherModelHead.getVoucherHeadCode());
            //循环设置设置科目
            for(FinVoucherModelLine line: finVoucherLineList) {
                line.setSubjectDesc(this.masterDataCommonService.getSubjectMap().get(line.getSubjectCode()));
            }
        }else {
            //初始化默认字段
            
            //获取当前用户职员信息
            HrStaffInfoRO staffInfo = this.hrCommonService.getStaffInfo(ShiroUtil.getUsername());
            finVoucherModelHead.setStaffCode(staffInfo.getStaffCode());
            finVoucherModelHead.setDepartmentCode(staffInfo.getDepartmentCode());
            finVoucherModelHead.setStaffName(staffInfo.getStaffName());
            finVoucherModelHead.setDepartmentName(staffInfo.getDepartmentName());
            
            //不允许新建已经创建的默认业务类型的模板
            //获取已创建的业务类型
            List<String> businessTypeList = this.finVoucherModelHeadService.getFinVoucherModelHeadForBusinessType();
            //循环过滤掉已创建的业务类型
            Iterator<Entry<String, String>> iterator = voucherBusinessTypeMap.entrySet().iterator();
            while(iterator.hasNext()) {
                String businessType = iterator.next().getKey();
                for(String businessTypeTemp: businessTypeList) {
                    if(!businessTypeTemp.equals("CUSTOM")&&businessType.equals(businessTypeTemp)) {
                        iterator.remove();
                        break;
                    }
                }
            }
        }
        
        //页面属性设置
        model.addAttribute("finVoucherHead", finVoucherModelHead);
        model.addAttribute("finVoucherLineList", finVoucherLineList);
        model.addAttribute("voucherTypeMap", voucherTypeMap);
        model.addAttribute("voucherBusinessTypeMap", voucherBusinessTypeMap);
        
        return "basic.jsp?content=finVoucher/voucherModelEdit";
    }
    
    
    
    /**
     * 
     * @description 获取选择单据Modal
     * @date 2020年7月29日
     * @author dongbin
     * @param businessType
     * @param pages
     * @param poHeadCO
     * @param receiptHeadCO
     * @param model
     * @return
     * @return String
     *
     */
    @RequestMapping("getSelectBillModal")
    public String getSelectBillModal(String businessType, Pages pages, ApInvoiceHeadCO poHeadCO, ArInvoiceHeadCO receiptHeadCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        
        if(businessType.equals("PAY")) {
            //分页查询数据
            List<ApInvoiceHead> payHeadList = this.payHeadService.getApInvoiceHeadListForNotCreateVoucher(pages, poHeadCO);
            //循环获取金额
            //循环设置职员和组织信息
            for(ApInvoiceHead payHead: payHeadList) {
                payHead.setAmount(this.payLineService.getApInvoiceAmountByHeadCode(payHead.getInvoiceHeadCode()).doubleValue());
                payHead.setStaffName(this.hrCommonService.getHrStaff(payHead.getStaffCode()).getStaffName());
                payHead.setDepartmentName(this.hrCommonService.getHrDepartment(payHead.getDepartmentCode()).getDepartmentName());
            }
            
            //付款来源类型
            Map paySourceTypeMap = com.erp.finance.ap.invoice.dao.data.DataBox.getApInvoiceSourceType();
            //状态
            Map payStatusMap = com.erp.finance.ap.invoice.dao.data.DataBox.getApInvoiceStatusMap();
            //获取出纳状态
            Map paidStatusMap = com.erp.finance.ap.invoice.dao.data.DataBox.getPaidStatusMap();
            //获取供应商
            Map vendorMap = this.masterDataCommonService.getVendorMap();
            
            //页面属性设置
            model.addAttribute("payHeadList", payHeadList);
            model.addAttribute("pages", pages);
            model.addAttribute("paySourceTypeMap", paySourceTypeMap);
            model.addAttribute("payStatusMap", payStatusMap);
            model.addAttribute("paidStatusMap", paidStatusMap);
            model.addAttribute("vendorMap", vendorMap);
            
            return "finVoucher/pop/selectPayBillModal";
        }else if(businessType.equals("RECEIPT")) {
          //分页查询数据
            List<ArInvoiceHead> receiptHeadList = this.receiptHeadService.getArInvoiceHeadListForNotCreateVoucher(pages, receiptHeadCO);
            //循环获取金额
            //循环设置职员和组织信息
            for(ArInvoiceHead receiptHead: receiptHeadList) {
                receiptHead.setAmount(this.receiptLineService.getArInvoiceAmountByHeadCode(receiptHead.getInvoiceHeadCode()).doubleValue());
                receiptHead.setStaffName(this.hrCommonService.getHrStaff(receiptHead.getStaffCode()).getStaffName());
                receiptHead.setDepartmentName(this.hrCommonService.getHrDepartment(receiptHead.getDepartmentCode()).getDepartmentName());
            }
            
            //收款来源类型
            Map receiptSourceTypeMap = com.erp.finance.ar.invoice.dao.data.DataBox.getArInvoiceSourceType();
            //状态
            Map receiptStatusMap = com.erp.finance.ar.invoice.dao.data.DataBox.getArInvoiceStatusMap();
            //获取出纳状态
            Map receivedStatusMap = com.erp.finance.ar.invoice.dao.data.DataBox.getReceivedStatusMap();
            //获取客户
            Map customerMap = this.masterDataCommonService.getCustomerMap();
            
            //页面属性设置
            model.addAttribute("receiptHeadList", receiptHeadList);
            model.addAttribute("pages", pages);
            model.addAttribute("receiptSourceTypeMap", receiptSourceTypeMap);
            model.addAttribute("receiptStatusMap", receiptStatusMap);
            model.addAttribute("receivedStatusMap", receivedStatusMap);
            model.addAttribute("customerMap", customerMap);
            
            return "finVoucher/pop/selectReceiptBillModal";
        }
        
        return "common/blank";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-07-28 13:05:47
     * @author 
     * @param finVoucherModelHead
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editFinVoucherModelHead")
    public String editFinVoucherModelHead(@Valid FinVoucherModelHead finVoucherModelHead, BindingResult bindingResult, Integer[] voucherLineId, String[] memo, String[] subjectCode, String[] drAmount, String[] crAmount, Model model, RedirectAttributes attr) {
        //参数校验
        Map<String, String> errorMap = this.validateParameters(bindingResult, model);
        if(errorMap.size()>0) {
            return "forward:getFinVoucherModelHead";
        }
        
        //对当前编辑的对象初始化默认的字段
        if(finVoucherModelHead.getVoucherHeadId()==null) {
            finVoucherModelHead.setVoucherHeadCode(SnowFlake.generateId().toString());
        }
        
        List<FinVoucherModelLine> finVoucherLineList = new ArrayList<FinVoucherModelLine>();
        //循环设置凭证行
        for(int a=0;a<subjectCode.length;a++) {
            FinVoucherModelLine finVoucherLine = new FinVoucherModelLine();
            //判断是新增还是修改
            if(voucherLineId[a]==null) {
                finVoucherLine.setVoucherLineCode(SnowFlake.generateId().toString());
                finVoucherLine.setVoucherHeadCode(finVoucherModelHead.getVoucherHeadCode());
            }else {
                finVoucherLine.setVoucherLineId(voucherLineId[a]);
            }
            
            finVoucherLine.setCrAmount(crAmount[a]);
            finVoucherLine.setDrAmount(drAmount[a]);
            finVoucherLine.setMemo(memo[a]);
            finVoucherLine.setSubjectCode(subjectCode[a]);
            
            finVoucherLineList.add(finVoucherLine);
        }
        
        //保存编辑的数据
        this.finVoucherModelHeadService.insertOrUpdateFinVoucherModel(finVoucherModelHead, finVoucherLineList);
        //提示信息
        attr.addFlashAttribute("hint", "success");
        
        return "redirect:getFinVoucherModelHeadList";
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-07-28 13:05:47
     * @author 
     * @param finVoucherModelHead
     * @return String
     *
     */
    @RequestMapping("deleteFinVoucherModelHead")
    public String deleteFinVoucherModelHead(FinVoucherModelHead finVoucherModelHead, RedirectAttributes attr) {
        //删除数据前验证数据
        if(finVoucherModelHead!=null&&finVoucherModelHead.getVoucherHeadId()!=null&&StringUtils.isNotBlank(finVoucherModelHead.getVoucherHeadCode())) {
            //删除数据
            this.finVoucherModelHeadService.deleteDataObject(finVoucherModelHead);
            
            //提示信息
            attr.addFlashAttribute("hint", "success");
        }
        
        return "redirect:getFinVoucherModelHeadList";
    }
    
    
    
    /**
     * 
     * @description 获取凭证模板Json数据
     * @date 2020年7月28日
     * @author dongbin
     * @param finVoucherHead
     * @return
     * @return String
     *
     */
    @RequestMapping("getVoucherModelForJson")
    @ResponseBody
    public String getVoucherModelForJson(FinVoucherModelHead finVoucherModelHead) {
        List<FinVoucherModelLine> finVoucherLineList = new ArrayList<FinVoucherModelLine>();
        
        //获取凭证模板头信息
        if(finVoucherModelHead!=null&&finVoucherModelHead.getVoucherHeadId()!=null) {
            finVoucherModelHead = this.finVoucherModelHeadService.getDataObject(finVoucherModelHead.getVoucherHeadId());
            //获取凭证行
            finVoucherLineList = this.finVoucherModelLineService.getFinVoucherModelLineListByVoucherHeadCode(finVoucherModelHead.getVoucherHeadCode());
            //循环设置设置科目
            for(FinVoucherModelLine line: finVoucherLineList) {
                line.setSubjectDesc(this.masterDataCommonService.getSubjectMap().get(line.getSubjectCode()));
            }
            finVoucherModelHead.setFinVoucherModelLineList(finVoucherLineList);
        }
        
        System.out.println(JsonUtil.objectToJson(finVoucherModelHead, "yyyy-MM-dd"));
        
        return JsonUtil.objectToJson(finVoucherModelHead, "yyyy-MM-dd");
    }
    
    
    
    /**
     * 
     * @description 自动创建凭证
     * @date 2020年7月30日
     * @author dongbin
     * @param payHeadCode
     * @param amount
     * @param businessType
     * @param model
     * @param attr
     * @return
     * @return String
     *
     */
    @RequestMapping("autoCreateVoucher")
    @ResponseBody
    public String autoCreateVoucher(String billHeadCode, Double amount, String businessType, Model model, RedirectAttributes attr) {
        //判断参数
        if(StringUtils.isNotBlank(billHeadCode)&&StringUtils.isNotBlank(businessType)&&amount!=null&&amount>0&&DataBox.getVoucherBusinessType().containsKey(businessType)) {
            try {
                //调用自动创建方法
                return this.finVoucherModelHeadService.autoCreateVoucher(billHeadCode, amount, businessType);
            }catch(Exception e) {
                return JsonResultUtil.getErrorJson(-1, "自动生成凭证执行错误");
            }
        }else {
            return JsonResultUtil.getErrorJson(-1, "付款单参数传递错误");
        }
    }
    
    
    
    /**
     * 
     * @description 跳转凭证字的流水号页面
     * @date 2020-07-04 18:25:32
     * @author 
     * @param hrPosition
     * @param model
     * @return String
     *
     */
    @RequestMapping("getVoucherTypeNumber")
    public String getVoucherTypeNumber(Model model) {
        //获取凭证类型
        Map<String, String> voucherTypeMap = this.datasetCommonService.getVoucherType();
        
        //循环凭证类型，获取对应的流水号
        Map<String, String> voucherNumberMap = new HashMap<String, String>();
        Set<Entry<String, String>> voucherTypeSet = voucherTypeMap.entrySet();
        for(Entry<String, String> temp: voucherTypeSet) {
            voucherNumberMap.put(temp.getKey(), FinVoucherUtil.getVoucherNumberCache(temp.getKey()).toString());
        }
        
        //页面属性设置
        model.addAttribute("voucherTypeMap", voucherTypeMap);
        model.addAttribute("voucherNumberMap", voucherNumberMap);
        
        return "basic.jsp?content=finVoucher/voucherTypeNumberEdit";
    }
    
    /**
     * 
     * @description 编辑凭证字的流水号
     * @date 2020-07-04 18:25:32
     * @author 
     * @param hrPosition
     * @param model
     * @return String
     *
     */
    @RequestMapping("editVoucherTypeNumber")
    public String editVoucherTypeNumber(String voucherType, Long voucherNumber, Model model) {
        //重置凭证初始流水号
        if(StringUtils.isNotBlank(voucherType)) {
            FinVoucherUtil.setVoucherNumberCache(voucherType, voucherNumber);
        }
        
        return "redirect:getVoucherTypeNumber";
    }
}
