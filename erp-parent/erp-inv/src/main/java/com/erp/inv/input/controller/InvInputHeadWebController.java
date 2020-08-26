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
package com.erp.inv.input.controller;

import java.math.BigDecimal;
import java.util.HashMap;
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
import com.erp.hr.dao.model.HrStaffInfoRO;
import com.erp.hr.service.HrCommonService;
import com.erp.inv.input.dao.data.DataBox;
import com.erp.inv.input.dao.model.InvInputHead;
import com.erp.inv.input.dao.model.InvInputHeadCO;
import com.erp.inv.input.service.InvInputHeadService;
import com.erp.inv.input.service.InvInputLineService;
import com.erp.inv.stock.service.InvStockService;
import com.erp.inv.warehouse.dao.model.InvWarehouse;
import com.erp.inv.warehouse.service.InvWarehouseService;
import com.erp.masterdata.common.service.MasterDataCommonService;
import com.erp.masterdata.vendor.dao.model.MdVendor;
import com.erp.masterdata.vendor.dao.model.MdVendorContact;
import com.erp.masterdata.vendor.dao.model.MdVendorContactCO;
import com.erp.masterdata.vendor.service.MdVendorBankService;
import com.erp.masterdata.vendor.service.MdVendorContactService;
import com.erp.order.po.dao.model.PoHead;
import com.erp.order.po.dao.model.PoHeadCO;
import com.erp.order.po.service.PoHeadService;
import com.erp.order.po.service.PoLineService;

@Controller
@RequestMapping("/web/invInputHead")
public class InvInputHeadWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(InvInputHeadWebController.class);
    
    //服务层注入
    @Autowired
    private InvInputHeadService invInputHeadService;
    @Autowired
    private InvInputLineService invInputLineService;
    @Autowired
    private DatasetCommonService datasetCommonService;
    @Autowired
    private HrCommonService hrCommonService;
    @Autowired
    private MasterDataCommonService masterDataCommonService;
    @Autowired
    private PoHeadService poHeadService;
    @Autowired
    private PoLineService poLineService;
    @Autowired
    private InvWarehouseService invWarehouseService;
    
    
    
    @Override
    public String getExceptionRedirectURL() {
        // TODO Auto-generated method stub
        return null;
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-08-20 17:21:04
     * @author 
     * @param pages
     * @param invInputHeadCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getInvInputHeadList")
    public String getInvInputHeadList(Pages pages, InvInputHeadCO invInputHeadCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        
        //分页查询数据
        List<InvInputHead> invInputHeadList = this.invInputHeadService.getDataObjects(pages, invInputHeadCO);
        
        //入库状态
        Map inputStatusMap = DataBox.getInputStatusMap();
        //来源类型
        Map inputSourceTypeMap = DataBox.getInputSourceTypeMap();
        //入库类型
        Map inputTypeMap = DataBox.getInputTypeMap();
        //审批状态
        Map approveStatusMap = GlobalDataBox.getApproveStatusMap();
        //仓库
        List<InvWarehouse> warehouseList = this.invWarehouseService.getDataObjects();
        Map<String, InvWarehouse> warehouseMap = new HashMap<String, InvWarehouse>();
        for(InvWarehouse invWarehouse: warehouseList) {
            warehouseMap.put(invWarehouse.getWarehouseCode(), invWarehouse);
        }
        
        //页面属性设置
        model.addAttribute("invInputHeadList", invInputHeadList);
        model.addAttribute("pages", pages);
        model.addAttribute("inputStatusMap", inputStatusMap);
        model.addAttribute("inputSourceTypeMap", inputSourceTypeMap);
        model.addAttribute("inputTypeMap", inputTypeMap);
        model.addAttribute("approveStatusMap", approveStatusMap);
        model.addAttribute("warehouseMap", warehouseMap);
        
        return "basic.jsp?content=invInput/invInputList";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-08-20 17:21:04
     * @author 
     * @param invInputHead
     * @param model
     * @return String
     *
     */
    @RequestMapping("getInvInputHead")
    public String getInvInputHead(InvInputHead invInputHead, Model model) {
        //入库状态
        Map inputStatusMap = DataBox.getInputStatusMap();
        //来源类型
        Map inputSourceTypeMap = DataBox.getInputSourceTypeMap();
        //入库类型
        Map inputTypeMap = DataBox.getInputTypeMap();
        //审批状态
        Map approveStatusMap = GlobalDataBox.getApproveStatusMap();
        //仓库
        List<InvWarehouse> warehouseList = this.invWarehouseService.getDataObjects();
        Map<String, InvWarehouse> warehouseMap = new HashMap<String, InvWarehouse>();
        for(InvWarehouse invWarehouse: warehouseList) {
            warehouseMap.put(invWarehouse.getWarehouseCode(), invWarehouse);
        }
        
        //查询要编辑的数据
        if(invInputHead!=null&&StringUtils.isNotBlank(invInputHead.getInputHeadCode())) {
            invInputHead = this.invInputHeadService.getDataObject(invInputHead.getInputHeadCode());
            //设置显示字段
            invInputHead.setStaffName(this.hrCommonService.getHrStaff(invInputHead.getStaffCode()).getStaffName());
            invInputHead.setDepartmentName(this.hrCommonService.getHrDepartment(invInputHead.getDepartmentCode()).getDepartmentName());
            //仓库地址
            invInputHead.setWarehouseAddress(warehouseMap.get(invInputHead.getWarehouseCode()).getWarehouseAddress());
            //获取采购订单信息
            if(invInputHead.getInputSourceType().equals("PO")) {
                PoHead poHead = this.poHeadService.getDataObject(invInputHead.getInputSourceHeadCode());
                invInputHead.setInputSourceHeadName(poHead.getPoName());
                //获取供应商信息
                MdVendor mdVendor = this.masterDataCommonService.getMdVendorInfoCache(poHead.getVendorCode());
                if(mdVendor!=null) {
                    invInputHead.setVendorName(mdVendor.getVendorName());
                    
                    //获取联系人信息
                    List<MdVendorContact> mdVendorContactList = mdVendor.getMdVendorContactList();
                    if(mdVendorContactList.size()>0) {
                        invInputHead.setVendorContactDesc(mdVendorContactList.get(0).getContactName()+"；电话"+mdVendorContactList.get(0).getContactTelephone());
                    }
                }
                
                
            }
            
        }else {
            //初始化默认字段
            invInputHead.setStatus("NEW");
            
            //获取当前用户职员信息
            HrStaffInfoRO staffInfo = this.hrCommonService.getStaffInfo(ShiroUtil.getUsername());
            invInputHead.setStaffCode(staffInfo.getStaffCode());
            invInputHead.setDepartmentCode(staffInfo.getDepartmentCode());
            invInputHead.setStaffName(staffInfo.getStaffName());
            invInputHead.setDepartmentName(staffInfo.getDepartmentName());
        }
        
        
        
        //页面属性设置
        model.addAttribute("invInputHead", invInputHead);
        model.addAttribute("inputStatusMap", inputStatusMap);
        model.addAttribute("inputSourceTypeMap", inputSourceTypeMap);
        model.addAttribute("inputTypeMap", inputTypeMap);
        model.addAttribute("approveStatusMap", approveStatusMap);
        model.addAttribute("warehouseMap", warehouseMap);
        
        return "basic.jsp?content=invInput/invInputEdit";
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
            //获取联系人信息
            MdVendor mdVendor = this.masterDataCommonService.getMdVendorInfoCache(poHead.getVendorCode());
            if(mdVendor!=null) {
                List<MdVendorContact> mdVendorContactList = mdVendor.getMdVendorContactList();
                if(mdVendorContactList.size()>0) {
                    poHead.setVendorContactDesc(mdVendorContactList.get(0).getContactName()+"；电话"+mdVendorContactList.get(0).getContactTelephone());
                }
            }
        }
        
        //页面属性设置
        model.addAttribute("poHeadList", poHeadList);
        model.addAttribute("pages", pages);
        model.addAttribute("poTypeMap", poTypeMap);
        model.addAttribute("poStatusMap", poStatusMap);
        model.addAttribute("vendorMap", vendorMap);
        model.addAttribute("projectMap", projectMap);
        
        return "invInput/pop/selectPOModal";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-08-20 17:21:04
     * @author 
     * @param invInputHead
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editInvInputHead")
    public String editInvInputHead(@Valid InvInputHead invInputHead, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        //参数校验
        Map<String, String> errorMap = this.validateParameters(bindingResult, model);
        if(errorMap.size()>0) {
            return "forward:getInvInputHead";
        }
        
        //对当前编辑的对象初始化默认的字段
        
        //保存编辑的数据
        this.invInputHeadService.insertOrUpdateDataObject(invInputHead);
        //提示信息
        attr.addFlashAttribute("hint", "success");
        attr.addAttribute("inputHeadCode", invInputHead.getInputHeadCode());
        
        return "redirect:getInvInputHead";
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-08-20 17:21:04
     * @author 
     * @param invInputHead
     * @return String
     *
     */
    @RequestMapping("deleteInvInputHead")
    public String deleteInvInputHead(InvInputHead invInputHead, RedirectAttributes attr) {
        //删除数据前验证数据
        if(invInputHead!=null&&invInputHead.getInputHeadId()!=null) {
            if(invInputHead.getStatus().equals("NEW")) {
                //删除数据
                this.invInputHeadService.deleteDataObject(invInputHead);
                
                //提示信息
                attr.addFlashAttribute("hint", "success");
            }else {
                //提示信息
                attr.addFlashAttribute("hint", "hint");
                attr.addFlashAttribute("alertMessage", "非新建状态的入库单不能删除");
            }
        }
        
        return "redirect:getInvInputHeadList";
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
            this.invInputHeadService.updateApproveStatus(code, approveStatus);
            //提示信息
            attr.addFlashAttribute("hint", "success");
            attr.addAttribute("inputHeadCode", code);
        }else {
            //提示信息
            attr.addFlashAttribute("hint", "hint");
            attr.addFlashAttribute("alertMessage", "提交或审批数据错误");
            attr.addAttribute("inputHeadCode", code);
        }
        
        return "redirect:getInvInputHead";
    }
}
