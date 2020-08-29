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
package com.erp.inv.output.controller;

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
import com.erp.inv.output.dao.data.DataBox;
import com.erp.inv.output.dao.model.InvOutputHead;
import com.erp.inv.output.dao.model.InvOutputHeadCO;
import com.erp.inv.output.service.InvOutputHeadService;
import com.erp.inv.output.service.InvOutputLineService;
import com.erp.inv.stock.service.InvStockService;
import com.erp.inv.warehouse.dao.model.InvWarehouse;
import com.erp.inv.warehouse.service.InvWarehouseService;
import com.erp.masterdata.common.service.MasterDataCommonService;
import com.erp.masterdata.customer.dao.model.MdCustomer;
import com.erp.masterdata.customer.dao.model.MdCustomerContact;
import com.erp.masterdata.customer.dao.model.MdCustomerContactCO;
import com.erp.masterdata.customer.service.MdCustomerContactService;
import com.erp.masterdata.vendor.dao.model.MdVendorContact;
import com.erp.masterdata.vendor.dao.model.MdVendorContactCO;
import com.erp.order.po.dao.model.PoHead;
import com.erp.order.po.dao.model.PoHeadCO;
import com.erp.order.po.service.PoHeadService;
import com.erp.order.po.service.PoLineService;
import com.erp.order.so.dao.model.SoHead;
import com.erp.order.so.dao.model.SoHeadCO;
import com.erp.order.so.service.SoHeadService;
import com.erp.order.so.service.SoLineService;

@Controller
@RequestMapping("/web/invOutputHead")
public class InvOutputHeadWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(InvOutputHeadWebController.class);
    
    //服务层注入
    @Autowired
    private InvOutputHeadService invOutputHeadService;
    @Autowired
    private InvOutputLineService invOutputLineService;
    @Autowired
    private DatasetCommonService datasetCommonService;
    @Autowired
    private HrCommonService hrCommonService;
    @Autowired
    private MasterDataCommonService masterDataCommonService;
    @Autowired
    private SoHeadService soHeadService;
    @Autowired
    private SoLineService soLineService;
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
     * @date 2020-08-20 17:22:26
     * @author 
     * @param pages
     * @param invOutputHeadCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getInvOutputHeadList")
    public String getInvOutputHeadList(Pages pages, InvOutputHeadCO invOutputHeadCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        
        //分页查询数据
        List<InvOutputHead> invOutputHeadList = this.invOutputHeadService.getDataObjects(pages, invOutputHeadCO);
        
        //出库状态
        Map outputStatusMap = DataBox.getOutputStatusMap();
        //来源类型
        Map outputSourceTypeMap = DataBox.getOutputSourceTypeMap();
        //出库类型
        Map outputTypeMap = DataBox.getOutputTypeMap();
        //审批状态
        Map approveStatusMap = GlobalDataBox.getApproveStatusMap();
        //仓库
        List<InvWarehouse> warehouseList = this.invWarehouseService.getDataObjects();
        Map<String, InvWarehouse> warehouseMap = new HashMap<String, InvWarehouse>();
        for(InvWarehouse invWarehouse: warehouseList) {
            warehouseMap.put(invWarehouse.getWarehouseCode(), invWarehouse);
        }
        
        //页面属性设置
        model.addAttribute("invOutputHeadList", invOutputHeadList);
        model.addAttribute("pages", pages);
        model.addAttribute("outputStatusMap", outputStatusMap);
        model.addAttribute("outputSourceTypeMap", outputSourceTypeMap);
        model.addAttribute("outputTypeMap", outputTypeMap);
        model.addAttribute("approveStatusMap", approveStatusMap);
        model.addAttribute("warehouseMap", warehouseMap);
        
        return "basic.jsp?content=invOutput/invOutputList";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-08-20 17:22:26
     * @author 
     * @param invOutputHead
     * @param model
     * @return String
     *
     */
    @RequestMapping("getInvOutputHead")
    public String getInvOutputHead(InvOutputHead invOutputHead, Model model) {
        //出库状态
        Map outputStatusMap = DataBox.getOutputStatusMap();
        //来源类型
        Map outputSourceTypeMap = DataBox.getOutputSourceTypeMap();
        //出库类型
        Map outputTypeMap = DataBox.getOutputTypeMap();
        //审批状态
        Map approveStatusMap = GlobalDataBox.getApproveStatusMap();
        //仓库
        List<InvWarehouse> warehouseList = this.invWarehouseService.getDataObjects();
        Map<String, InvWarehouse> warehouseMap = new HashMap<String, InvWarehouse>();
        for(InvWarehouse invWarehouse: warehouseList) {
            warehouseMap.put(invWarehouse.getWarehouseCode(), invWarehouse);
        }
        
        //查询要编辑的数据
        if(invOutputHead!=null&&StringUtils.isNotBlank(invOutputHead.getOutputHeadCode())) {
            invOutputHead = this.invOutputHeadService.getDataObject(invOutputHead.getOutputHeadCode());
            //设置显示字段
            invOutputHead.setStaffName(this.hrCommonService.getHrStaff(invOutputHead.getStaffCode()).getStaffName());
            invOutputHead.setDepartmentName(this.hrCommonService.getHrDepartment(invOutputHead.getDepartmentCode()).getDepartmentName());
            //仓库地址
            invOutputHead.setWarehouseAddress(warehouseMap.get(invOutputHead.getWarehouseCode()).getWarehouseAddress());
            //获取采购订单信息
            if(invOutputHead.getOutputSourceType().equals("SO")) {
                SoHead soHead = this.soHeadService.getDataObject(invOutputHead.getOutputSourceHeadCode());
                invOutputHead.setOutputSourceHeadName(soHead.getSoName());
                //获取客户信息
                MdCustomer mdCustomer = this.masterDataCommonService.getMdCustomerInfoCache(soHead.getCustomerCode());
                if(mdCustomer!=null) {
                    invOutputHead.setCustomerName(mdCustomer.getCustomerName());
                    //获取联系人信息
                    List<MdCustomerContact> mdCustomerContactList = mdCustomer.getMdCustomerContactList();
                    if(mdCustomerContactList.size()>0) {
                        invOutputHead.setCustomerContactDesc(mdCustomerContactList.get(0).getContactName()+"；电话"+mdCustomerContactList.get(0).getContactTelephone());
                    }
                }
                
            }
            
        }else {
            //初始化默认字段
            invOutputHead.setStatus("NEW");
            
            //获取当前用户职员信息
            HrStaffInfoRO staffInfo = this.hrCommonService.getStaffInfo(ShiroUtil.getUsername());
            invOutputHead.setStaffCode(staffInfo.getStaffCode());
            invOutputHead.setDepartmentCode(staffInfo.getDepartmentCode());
            invOutputHead.setStaffName(staffInfo.getStaffName());
            invOutputHead.setDepartmentName(staffInfo.getDepartmentName());
        }
        
        
        
        //页面属性设置
        model.addAttribute("invOutputHead", invOutputHead);
        model.addAttribute("outputStatusMap", outputStatusMap);
        model.addAttribute("outputSourceTypeMap", outputSourceTypeMap);
        model.addAttribute("outputTypeMap", outputTypeMap);
        model.addAttribute("approveStatusMap", approveStatusMap);
        model.addAttribute("warehouseMap", warehouseMap);
        
        return "basic.jsp?content=invOutput/invOutputEdit";
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
            //获取联系人信息
            MdCustomer mdCustomer = this.masterDataCommonService.getMdCustomerInfoCache(soHead.getCustomerCode());
            if(mdCustomer!=null) {
                List<MdCustomerContact> mdCustomerContactList = mdCustomer.getMdCustomerContactList();
                if(mdCustomerContactList.size()>0) {
                    soHead.setCustomerContactDesc(mdCustomerContactList.get(0).getContactName()+"；电话"+mdCustomerContactList.get(0).getContactTelephone());
                }
            }
        }
        
        //页面属性设置
        model.addAttribute("soHeadList", soHeadList);
        model.addAttribute("pages", pages);
        model.addAttribute("soTypeMap", soTypeMap);
        model.addAttribute("soStatusMap", soStatusMap);
        model.addAttribute("customerMap", customerMap);
        model.addAttribute("projectMap", projectMap);
        
        return "invOutput/pop/selectSOModal";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-08-20 17:22:26
     * @author 
     * @param invOutputHead
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editInvOutputHead")
    public String editInvOutputHead(@Valid InvOutputHead invOutputHead, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        //参数校验
        Map<String, String> errorMap = this.validateParameters(bindingResult, model);
        if(errorMap.size()>0) {
            return "forward:getInvInputHead";
        }
        
        //对当前编辑的对象初始化默认的字段
        
        //保存编辑的数据
        this.invOutputHeadService.insertOrUpdateDataObject(invOutputHead);
        //提示信息
        attr.addFlashAttribute("hint", "success");
        attr.addAttribute("outputHeadCode", invOutputHead.getOutputHeadCode());
        
        return "redirect:getInvOutputHead";
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-08-20 17:22:26
     * @author 
     * @param invOutputHead
     * @return String
     *
     */
    @RequestMapping("deleteInvOutputHead")
    public String deleteInvOutputHead(InvOutputHead invOutputHead, RedirectAttributes attr) {
        //删除数据前验证数据
        if(invOutputHead!=null&&invOutputHead.getOutputHeadId()!=null) {
            if(invOutputHead.getStatus().equals("NEW")) {
                //删除数据
                this.invOutputHeadService.deleteDataObject(invOutputHead);
                
                //提示信息
                attr.addFlashAttribute("hint", "success");
            }else {
                //提示信息
                attr.addFlashAttribute("hint", "hint");
                attr.addFlashAttribute("alertMessage", "非新建状态的出库单不能删除");
            }
        }
        
        return "redirect:getInvOutputHeadList";
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
            this.invOutputHeadService.updateApproveStatus(code, approveStatus);
            //提示信息
            attr.addFlashAttribute("hint", "success");
            attr.addAttribute("outputHeadCode", code);
        }else {
            //提示信息
            attr.addFlashAttribute("hint", "hint");
            attr.addFlashAttribute("alertMessage", "提交或审批数据错误");
            attr.addAttribute("outputHeadCode", code);
        }
        
        return "redirect:getInvOutputHead";
    }
}
