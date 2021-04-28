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
package com.erp.inv.stock.controller;

import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import com.framework.shiro.ShiroUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.controller.ControllerSupport;
import com.framework.dao.model.Pages;

import com.erp.dataset.service.DatasetCommonService;
import com.erp.hr.dao.model.HrStaffInfoRO;
import com.erp.hr.service.HrCommonService;
import com.erp.inv.stock.dao.data.DataBox;
import com.erp.inv.stock.dao.model.InvStock;
import com.erp.inv.stock.dao.model.InvStockCO;
import com.erp.inv.stock.service.InvStockService;
import com.erp.masterdata.common.service.MasterDataCommonService;
import com.erp.masterdata.material.dao.model.MdMaterial;
import com.erp.masterdata.material.service.MdMaterialService;
import redragon.basic.tools.SnowFlake;

@Controller
@RequestMapping("/web/invStock")
public class InvStockWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(InvStockWebController.class);
    
    //服务层注入
    @Autowired
    private InvStockService invStockService;
    @Autowired
    private MasterDataCommonService masterDataCommonService;
    @Autowired
    private DatasetCommonService datasetCommonService;
    @Autowired
    private HrCommonService hrCommonService;
    
    
    
    @Override
    public String getExceptionRedirectURL() {
        // TODO Auto-generated method stub
        return null;
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-08-17 16:52:00
     * @author 
     * @param pages
     * @param invStockCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getInvStockList")
    public String getInvStockList(Pages pages, InvStockCO invStockCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        
        //单位
        Map<String, String> materialUnitMap = this.datasetCommonService.getMaterialUnit();
        
        //分页查询数据
        List<InvStock> invStockList = this.invStockService.getInitInvStockList(pages, invStockCO);
        //循环获取物料信息
        for(InvStock invStock: invStockList) {
            MdMaterial mdMaterial = this.masterDataCommonService.getMdMaterialInfoCache(invStock.getMaterialCode());
            invStock.setMaterialName(mdMaterial.getMaterialName());
            invStock.setMaterialStandard(mdMaterial.getStandard());
            invStock.setMaterialUnit(materialUnitMap.get(mdMaterial.getMaterialUnit()));
        }
        
        //单据类型
        Map stockBillTypeMap = DataBox.getStockBillTypeMap();
        //物料
        Map materialMap = this.masterDataCommonService.getMaterialMap();
        
        //页面属性设置
        model.addAttribute("invStockList", invStockList);
        model.addAttribute("pages", pages);
        model.addAttribute("stockBillTypeMap", stockBillTypeMap);
        model.addAttribute("materialMap", materialMap);
        
        return "basic.jsp?content=invStock/invStockList";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-08-17 16:52:00
     * @author 
     * @param invStock
     * @param model
     * @return String
     *
     */
    @RequestMapping("getInvStock")
    public String getInvStock(InvStock invStock, Model model) {
        //查询要编辑的数据
        if(invStock!=null&&invStock.getStockId()!=null) {
            invStock = this.invStockService.getDataObject(invStock.getStockId());
        }
        
        //物料
        Map materialMap = this.masterDataCommonService.getMaterialForMaterialMap();
        
        //页面属性设置
        model.addAttribute("invStock", invStock);
        model.addAttribute("materialMap", materialMap);
        
        return "basic.jsp?content=invStock/invStockEdit";
    }
    
    
    
    /**
     * 
     * @description 获取物料信息（异步）
     * @date 2020年8月18日
     * @author dongbin
     * @param materialCode
     * @return
     * @return String
     *
     */
    @ResponseBody
    @RequestMapping("getMaterialInfoAjax")
    public String getMaterialInfoAjax(String materialCode) {
        MdMaterial mdMaterial = this.masterDataCommonService.getMdMaterialInfoCache(materialCode);
        
        //单位
        Map<String, String> materialUnitMap = this.datasetCommonService.getMaterialUnit();
        
        if(mdMaterial!=null) {
            return "{\"materialName\": \""+mdMaterial.getMaterialName()+"\",\"materialUnit\": \""+(StringUtils.isBlank(mdMaterial.getMaterialUnit())?"":materialUnitMap.get(mdMaterial.getMaterialUnit()))+"\",\"materialStandard\": \""+mdMaterial.getStandard()+"\"}";
        }
        
        return "";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-08-17 16:52:00
     * @author 
     * @param invStock
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editInvStock")
    public String editInvStock(@Valid InvStock invStock, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        if(!this.invStockService.isExistInitDataForInvStock(invStock.getWarehouseCode(), invStock.getMaterialCode())) {
            //参数校验
            Map<String, String> errorMap = this.validateParameters(bindingResult, model);
            if(errorMap.size()>0) {
                return "forward:getInvStock";
            }
            
            //对当前编辑的对象初始化默认的字段
            //设置编码
            invStock.setStockCode(SnowFlake.generateId().toString());
            //职员信息
            HrStaffInfoRO hrStaffInfoRO = this.hrCommonService.getStaffInfo(ShiroUtil.getUsername());
            invStock.setStaffCode(hrStaffInfoRO.getStaffCode());
            invStock.setDepartmentCode(hrStaffInfoRO.getDepartmentCode());
            //备注
            invStock.setMemo("库存初始化"+(StringUtils.isBlank(invStock.getMemo())?"":"，"+invStock.getMemo()));
            
            //保存编辑的数据
            this.invStockService.insertOrUpdateDataObject(invStock);
            //提示信息
            attr.addFlashAttribute("hint", "success");
            //参数传递
            attr.addAttribute("warehouseCode", invStock.getWarehouseCode());
        }else {
            //提示信息
            attr.addFlashAttribute("hint", "hint");
            attr.addFlashAttribute("alertMessage", "当前物料已存在初始库存，不能重复添加");
        }
        
        return "redirect:getInvStockList";
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-08-17 16:52:00
     * @author 
     * @param invStock
     * @return String
     *
     */
    @RequestMapping("deleteInvStock")
    public String deleteInvStock(InvStock invStock, RedirectAttributes attr) {
        //删除数据前验证数据
        if(invStock!=null&&invStock.getStockId()!=null&&StringUtils.isNotBlank(invStock.getWarehouseCode())&&StringUtils.isNotBlank(invStock.getMaterialCode())) {
            if(this.invStockService.isExistRelateDataForInvStock(invStock.getWarehouseCode(), invStock.getMaterialCode())) {
                //提示信息
                attr.addFlashAttribute("hint", "hint");
                attr.addFlashAttribute("alertMessage", "当前物料已存在出入库记录，无法删除");
            }else {
                //删除数据
                this.invStockService.deleteDataObject(invStock);
                //提示信息
                attr.addFlashAttribute("hint", "success");
            }
        }
        
        //参数传递
        attr.addAttribute("warehouseCode", invStock.getWarehouseCode());
        
        return "redirect:getInvStockList";
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-08-17 16:52:00
     * @author 
     * @param pages
     * @param invStockCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getInvStockDetailList")
    public String getInvStockDetailList(Pages pages, InvStockCO invStockCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        
        //单位
        Map<String, String> materialUnitMap = this.datasetCommonService.getMaterialUnit();
        
        if(invStockCO!=null&&StringUtils.isNotBlank(invStockCO.getMaterialCode())) {
            //获取物料信息
            MdMaterial mdMaterial = this.masterDataCommonService.getMdMaterialInfoCache(invStockCO.getMaterialCode());
            //获取物料库存
            Double stockNumber = this.invStockService.getStockNumberByMaterialCode(invStockCO.getMaterialCode());
            //分页查询数据
            List<InvStock> invStockList = this.invStockService.getDataObjects(pages, invStockCO);
            
            //页面属性设置
            model.addAttribute("mdMaterial", mdMaterial);
            model.addAttribute("stockNumber", stockNumber);
            model.addAttribute("invStockList", invStockList);
            model.addAttribute("pages", pages);
        }
        
        //单据类型
        Map stockBillTypeMap = DataBox.getStockBillTypeMap();
        
        //物料
        Map materialMap = this.masterDataCommonService.getMaterialForMaterialMap();
        
        //页面属性设置
        model.addAttribute("stockBillTypeMap", stockBillTypeMap);
        model.addAttribute("materialUnitMap", materialUnitMap);
        model.addAttribute("materialMap", materialMap);
        
        return "basic.jsp?content=invStock/invStockDetailList";
    }
}
