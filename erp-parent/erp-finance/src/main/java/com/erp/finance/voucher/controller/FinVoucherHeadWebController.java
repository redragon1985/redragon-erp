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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.erp.dataset.service.DatasetCommonService;
import com.erp.finance.voucher.dao.data.DataBox;
import com.erp.finance.voucher.dao.model.FinVoucherHead;
import com.erp.finance.voucher.dao.model.FinVoucherHeadCO;
import com.erp.finance.voucher.dao.model.FinVoucherLine;
import com.erp.finance.voucher.dao.model.FinVoucherModelHead;
import com.erp.finance.voucher.service.FinVoucherBillRService;
import com.erp.finance.voucher.service.FinVoucherHeadService;
import com.erp.finance.voucher.service.FinVoucherLineService;
import com.erp.finance.voucher.service.FinVoucherModelHeadService;
import com.erp.finance.voucher.service.FinVoucherModelLineService;
import com.erp.finance.voucher.util.FinVoucherUtil;
import com.erp.hr.dao.model.HrStaffInfoRO;
import com.erp.hr.service.HrCommonService;
import com.erp.masterdata.common.service.MasterDataCommonService;
import com.framework.controller.ControllerSupport;
import com.framework.dao.data.GlobalDataBox;
import com.framework.dao.model.Pages;
import com.framework.util.ShiroUtil;

import redragon.frame.hibernate.SnowFlake;

@Controller
@RequestMapping("/web/finVoucherHead")
public class FinVoucherHeadWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(FinVoucherHeadWebController.class);
    
    //服务层注入
    @Autowired
    private FinVoucherHeadService finVoucherHeadService;
    @Autowired
    private FinVoucherLineService finVoucherLineService;
    @Autowired
    private DatasetCommonService datasetCommonService;
    @Autowired
    private HrCommonService hrCommonService;
    @Autowired
    private MasterDataCommonService masterDataCommonService;
    @Autowired
    private FinVoucherModelHeadService finVoucherModelHeadService;
    @Autowired
    private FinVoucherModelLineService finVoucherModelLineService;
    @Autowired
    private FinVoucherBillRService finVoucherBillRService;
    
    @Override
    public String getExceptionRedirectURL() {
        return "redirect:getFinVoucherHeadList";
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-07-24 22:18:23
     * @author 
     * @param pages
     * @param finVoucherHeadCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getFinVoucherHeadList")
    public String getFinVoucherHeadList(Pages pages, FinVoucherHeadCO finVoucherHeadCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        
        //分页查询数据
        List<FinVoucherHead> finVoucherHeadList = this.finVoucherHeadService.getDataObjectsForDataAuth("", pages, finVoucherHeadCO);
        //循环获取凭证金额
        for(FinVoucherHead finVoucherHead: finVoucherHeadList) {
            finVoucherHead.setAmount(this.finVoucherLineService.getFinVoucherAmountByVoucherHeadCode(finVoucherHead.getVoucherHeadCode()));
        }
        
        //循环设置职员和组织信息
        for(FinVoucherHead finVoucherHead: finVoucherHeadList) {
            finVoucherHead.setStaffName(this.hrCommonService.getHrStaff(finVoucherHead.getStaffCode()).getStaffName());
            finVoucherHead.setDepartmentName(this.hrCommonService.getHrDepartment(finVoucherHead.getDepartmentCode()).getDepartmentName());
        }
        
        //获取凭证字
        Map voucherTypeMap = this.datasetCommonService.getVoucherType();
        //获取审批状态
        Map approveStatusMap = GlobalDataBox.getApproveStatusMap();
        //获取凭证业务类型
        Map voucherBusinessTypeMap = DataBox.getVoucherBusinessType();
        voucherBusinessTypeMap.remove("CUSTOM");
        
        //页面属性设置
        model.addAttribute("finVoucherHeadList", finVoucherHeadList);
        model.addAttribute("pages", pages);
        model.addAttribute("voucherTypeMap", voucherTypeMap);
        model.addAttribute("approveStatusMap", approveStatusMap);
        model.addAttribute("voucherBusinessTypeMap", voucherBusinessTypeMap);
        
        return "basic.jsp?content=finVoucher/voucherList";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-07-24 22:18:23
     * @author 
     * @param finVoucherHead
     * @param model
     * @return String
     *
     */
    @RequestMapping("getFinVoucherHead")
    public String getFinVoucherHead(FinVoucherHead finVoucherHead, Model model) {
        List<FinVoucherLine> finVoucherLineList = new ArrayList<FinVoucherLine>();
        
        //查询要编辑的数据
        if(finVoucherHead!=null&&finVoucherHead.getVoucherHeadId()!=null&&StringUtils.isNotBlank(finVoucherHead.getVoucherHeadCode())) {
            finVoucherHead = this.finVoucherHeadService.getDataObject(finVoucherHead.getVoucherHeadId());
            //设置显示字段
            finVoucherHead.setStaffName(this.hrCommonService.getHrStaff(finVoucherHead.getStaffCode()).getStaffName());
            finVoucherHead.setDepartmentName(this.hrCommonService.getHrDepartment(finVoucherHead.getDepartmentCode()).getDepartmentName());
            //获取凭证行
            finVoucherLineList = this.finVoucherLineService.getFinVoucherLineListByVoucherHeadCode(finVoucherHead.getVoucherHeadCode());
            //循环设置凭证头的dr和cr
            Double drAmount = 0D;
            Double crAmount = 0D;
            for(FinVoucherLine line: finVoucherLineList) {
                drAmount = drAmount + line.getDrAmount();
                crAmount = crAmount + line.getCrAmount();
                //设置科目
                line.setSubjectDesc(this.masterDataCommonService.getSubjectMap().get(line.getSubjectCode()));
            }
            finVoucherHead.setDrAmount(drAmount);
            finVoucherHead.setCrAmount(crAmount);
            if(crAmount.doubleValue()==drAmount.doubleValue()) {
                finVoucherHead.setAmount(new BigDecimal(drAmount));
            }else {
                finVoucherHead.setAmount(new BigDecimal(0));
            }
        }else {
            //初始化默认字段
            
            //获取当前用户职员信息
            HrStaffInfoRO staffInfo = this.hrCommonService.getStaffInfo(ShiroUtil.getUsername());
            finVoucherHead.setStaffCode(staffInfo.getStaffCode());
            finVoucherHead.setDepartmentCode(staffInfo.getDepartmentCode());
            finVoucherHead.setStaffName(staffInfo.getStaffName());
            finVoucherHead.setDepartmentName(staffInfo.getDepartmentName());
        }
        
        //获取凭证字
        Map voucherTypeMap = this.datasetCommonService.getVoucherType();
        //获取审批状态
        Map approveStatusMap = GlobalDataBox.getApproveStatusMap();
        //获取凭证模板
        List<FinVoucherModelHead> finVoucherModelHeadList = this.finVoucherModelHeadService.getFinVoucherModelHeadListForCustom();
        
        //页面属性设置
        model.addAttribute("finVoucherHead", finVoucherHead);
        model.addAttribute("finVoucherLineList", finVoucherLineList);
        model.addAttribute("voucherTypeMap", voucherTypeMap);
        model.addAttribute("approveStatusMap", approveStatusMap);
        model.addAttribute("finVoucherModelHeadList", finVoucherModelHeadList);
        
        return "basic.jsp?content=finVoucher/voucherEdit";
    }
    
    /**
     * 
     * @description 异步获取要添加的凭证行
     * @date 2020年7月25日
     * @author dongbin
     * @return
     * @return String
     *
     */
    @RequestMapping("getTrModelAjax")
    public String getTrModelAjax() {
        return "finVoucher/ajax/trModelAjax";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-07-24 22:18:23
     * @author 
     * @param finVoucherHead
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editFinVoucherHead")
    public String editFinVoucherHead(@Valid FinVoucherHead finVoucherHead, BindingResult bindingResult, Integer[] voucherLineId, String[] memo, String[] subjectCode, Double[] drAmount, Double[] crAmount, Model model, RedirectAttributes attr) {
        //参数校验
        Map<String, String> errorMap = this.validateParameters(bindingResult, model);
        if(errorMap.size()>0) {
            return "forward:getFinVoucherHead";
        }
        
        //对当前编辑的对象初始化默认的字段
        if(finVoucherHead.getVoucherHeadId()==null) {
            finVoucherHead.setVoucherHeadCode(SnowFlake.generateId().toString());
        }
        finVoucherHead.setVoucherNumber(FinVoucherUtil.incrVoucherNumberCache(finVoucherHead.getVoucherType()).toString());
        
        List<FinVoucherLine> finVoucherLineList = new ArrayList<FinVoucherLine>();
        //循环设置凭证行
        for(int a=0;a<subjectCode.length;a++) {
            FinVoucherLine finVoucherLine = new FinVoucherLine();
            //判断是新增还是修改
            if(voucherLineId[a]==null) {
                finVoucherLine.setVoucherLineCode(SnowFlake.generateId().toString());
                finVoucherLine.setVoucherHeadCode(finVoucherHead.getVoucherHeadCode());
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
        this.finVoucherHeadService.insertOrUpdateFinVoucher(finVoucherHead, finVoucherLineList);
        //提示信息
        attr.addFlashAttribute("hint", "success");
        //重定向参数
        attr.addAttribute("voucherHeadId", finVoucherHead.getVoucherHeadId());
        attr.addAttribute("voucherHeadCode", finVoucherHead.getVoucherHeadCode());
        
        return "redirect:getFinVoucherHead";
    }
    
    /**
     * 
     * @description 修改凭证头状态或审批状态
     * @date 2020年7月27日
     * @author dongbin
     * @param finVoucherHead
     * @param model
     * @param attr
     * @return
     * @return String
     *
     */
    @RequestMapping("editFinVoucherHeadStatus")
    public String editFinVoucherHeadStatus(FinVoucherHead finVoucherHead, Model model, RedirectAttributes attr) {
        if(finVoucherHead!=null&&finVoucherHead.getVoucherHeadId()!=null&&StringUtils.isNotBlank(finVoucherHead.getVoucherHeadCode())) {
            if(StringUtils.isNotBlank(finVoucherHead.getStatus())) {
                this.finVoucherHeadService.updateFinVoucherHeadForStatus(finVoucherHead.getVoucherHeadId(), finVoucherHead.getVoucherHeadCode(), finVoucherHead.getStatus());
                //提示信息
                attr.addFlashAttribute("hint", "success");
            }else if(StringUtils.isNotBlank(finVoucherHead.getApproveStatus())) {
                this.finVoucherHeadService.updateFinVoucherHeadForApproveStatus(finVoucherHead.getVoucherHeadId(), finVoucherHead.getApproveStatus());
                //提示信息
                attr.addFlashAttribute("hint", "success");
            }
        }
        
        //重定向参数
        attr.addAttribute("voucherHeadId", finVoucherHead.getVoucherHeadId());
        attr.addAttribute("voucherHeadCode", finVoucherHead.getVoucherHeadCode());
        
        return "redirect:getFinVoucherHead";
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-07-24 22:18:23
     * @author 
     * @param finVoucherHead
     * @return String
     *
     */
    @RequestMapping("deleteFinVoucherHead")
    public String deleteFinVoucherHead(FinVoucherHead finVoucherHead, RedirectAttributes attr) {
        //删除数据前验证数据
        if(finVoucherHead!=null&&finVoucherHead.getVoucherHeadId()!=null&&StringUtils.isNotBlank(finVoucherHead.getVoucherHeadCode())) {
            if(finVoucherHead.getApproveStatus().equals("UNSUBMIT")||finVoucherHead.getApproveStatus().equals("REJECT")) {
                //删除数据
                this.finVoucherHeadService.deleteDataObject(finVoucherHead);

                //提示信息
                attr.addFlashAttribute("hint", "success");
            }else {
                //提示信息
                attr.addFlashAttribute("hint", "hint");
                attr.addFlashAttribute("alertMessage", "凭证已审核不能删除");
            }
        }
        
        return "redirect:getFinVoucherHeadList";
    }

}
