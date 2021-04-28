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
package com.erp.inv.check.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import com.framework.controller.ControllerSupport;
import com.framework.dao.data.GlobalDataBox;
import com.framework.dao.model.Pages;

import redragon.basic.tools.SnowFlake;
import redragon.basic.tools.TimeToolKit;

import com.erp.dataset.service.DatasetCommonService;
import com.erp.hr.dao.model.HrStaffInfoRO;
import com.erp.hr.service.HrCommonService;
import com.erp.inv.check.dao.model.InvStockCheckHead;
import com.erp.inv.check.dao.model.InvStockCheckHeadCO;
import com.erp.inv.check.dao.model.InvStockCheckLine;
import com.erp.inv.check.dao.model.InvStockData;
import com.erp.inv.check.service.InvStockCheckHeadService;
import com.erp.inv.check.service.InvStockCheckLineService;
import com.erp.inv.warehouse.dao.model.InvWarehouse;
import com.erp.inv.warehouse.service.InvWarehouseService;
import com.erp.masterdata.common.service.MasterDataCommonService;

@Controller
@RequestMapping("/web/invStockCheckHead")
public class InvStockCheckHeadWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(InvStockCheckHeadWebController.class);
    
    //服务层注入
    @Autowired
    private InvStockCheckHeadService invStockCheckHeadService;
    @Autowired
    private InvStockCheckLineService invStockCheckLineService;
    @Autowired
    private DatasetCommonService datasetCommonService;
    @Autowired
    private HrCommonService hrCommonService;
    @Autowired
    private MasterDataCommonService masterDataCommonService;
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
     * @date 2020-08-27 14:24:02
     * @author 
     * @param pages
     * @param invStockCheckHeadCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getInvStockCheckHeadList")
    public String getInvStockCheckHeadList(Pages pages, InvStockCheckHeadCO invStockCheckHeadCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        
        //分页查询数据
        List<InvStockCheckHead> invStockCheckHeadList = this.invStockCheckHeadService.getDataObjects(pages, invStockCheckHeadCO);
        
        //状态
        Map statusMap = GlobalDataBox.getStatusMap();
        
        //审批状态
        Map approveStatusMap = GlobalDataBox.getApproveStatusMap();
        
        //仓库
        InvWarehouse invWarehouse = this.invWarehouseService.getDataObject(invStockCheckHeadCO.getWarehouseCode());
        
        //页面属性设置
        model.addAttribute("invStockCheckHeadList", invStockCheckHeadList);
        model.addAttribute("pages", pages);
        model.addAttribute("statusMap", statusMap);
        model.addAttribute("approveStatusMap", approveStatusMap);
        model.addAttribute("invWarehouse", invWarehouse);
        
        return "basic.jsp?content=invStockCheck/invStockCheckList";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-08-27 14:24:02
     * @author 
     * @param invStockCheckHead
     * @param model
     * @return String
     *
     */
    @RequestMapping("getInvStockCheckHead")
    public String getInvStockCheckHead(InvStockCheckHead invStockCheckHead, Model model) {
        //盘点行
        List<InvStockCheckLine> invStockCheckLineList = new ArrayList<InvStockCheckLine>();
        
        //查询要编辑的数据
        if(invStockCheckHead!=null&&StringUtils.isNotBlank(invStockCheckHead.getCheckHeadCode())) {
            //修改
            invStockCheckHead = this.invStockCheckHeadService.getDataObject(invStockCheckHead.getCheckHeadCode());
            //获取行
            invStockCheckLineList = this.invStockCheckLineService.getInvStockCheckLineListByHeadCode(invStockCheckHead.getCheckHeadCode());
        }else {
            //新增
            invStockCheckHead.setStatus("NEW");
            
            TimeToolKit time = new TimeToolKit();
            time.getDate();
            invStockCheckHead.setCheckDate(time.year+"-"+time.month);
            
            //获取库存盘点基础数据
            List<InvStockData> stockList = this.invStockCheckHeadService.getInvStockDataForCheck(invStockCheckHead.getWarehouseCode());
            //循环设置盘点列表
            for(InvStockData stock: stockList) {
                InvStockCheckLine invStockCheckLine = new InvStockCheckLine();
                invStockCheckLine.setCheckBeforeQuantity(Double.valueOf(stock.getStockNumber()));
                invStockCheckLine.setMaterialCode(stock.getMaterialCode());
                invStockCheckLineList.add(invStockCheckLine);
            }
        }
        
        //状态
        Map statusMap = GlobalDataBox.getStatusMap();
        
        //审批状态
        Map approveStatusMap = GlobalDataBox.getApproveStatusMap();
        
        //物料Map
        Map materialMap = this.masterDataCommonService.getMaterialMap();
        
        //仓库
        InvWarehouse invWarehouse = this.invWarehouseService.getDataObject(invStockCheckHead.getWarehouseCode());
        
        //页面属性设置
        model.addAttribute("invStockCheckHead", invStockCheckHead);
        model.addAttribute("invWarehouse", invWarehouse);
        model.addAttribute("statusMap", statusMap);
        model.addAttribute("approveStatusMap", approveStatusMap);
        model.addAttribute("invStockCheckLineList", invStockCheckLineList);
        model.addAttribute("materialMap", materialMap);
        
        return "basic.jsp?content=invStockCheck/invStockCheckEdit";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-08-27 14:24:02
     * @author 
     * @param invStockCheckHead
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editInvStockCheckHead")
    public String editInvStockCheckHead(@Valid InvStockCheckHead invStockCheckHead, BindingResult bindingResult, Integer[] checkLineId, String[] materialCode, Double[] checkBeforeQuantity, Double[] checkAfterQuantity, String[] lineMemo, Model model, RedirectAttributes attr) {
        //参数校验
        Map<String, String> errorMap = this.validateParameters(bindingResult, model);
        if(errorMap.size()>0) {
            return "forward:getInvStockCheckHead";
        }
        
        //对当前编辑的对象初始化默认的字段
        if(invStockCheckHead.getCheckHeadId()==null) {
            invStockCheckHead.setCheckHeadCode("check"+ SnowFlake.generateId().toString());
        }
        //获取职员信息
        HrStaffInfoRO hrStaffInfoRO = this.hrCommonService.getStaffInfo(ShiroUtil.getUsername());
        invStockCheckHead.setDepartmentCode(hrStaffInfoRO.getDepartmentCode());
        invStockCheckHead.setStaffCode(hrStaffInfoRO.getStaffCode());
        
        //循环设置行列表
        List<InvStockCheckLine> invStockCheckLineList = new ArrayList<InvStockCheckLine>();
        if(materialCode!=null&&materialCode.length>0) {
            for(int a=0;a<materialCode.length;a++) {
                InvStockCheckLine invStockCheckLine = new InvStockCheckLine();
                try {
                    invStockCheckLine.setCheckLineId(checkLineId[a]);
                }catch(Exception e) {}
                
                invStockCheckLine.setCheckAfterQuantity(checkAfterQuantity[a]);
                invStockCheckLine.setCheckBeforeQuantity(checkBeforeQuantity[a]);
                invStockCheckLine.setCheckHeadCode(invStockCheckHead.getCheckHeadCode());
                if(invStockCheckLine.getCheckLineId()==null) {
                    invStockCheckLine.setCheckLineCode(SnowFlake.generateId().toString());
                }
                invStockCheckLine.setMaterialCode(materialCode[a]);
                invStockCheckLine.setMemo(lineMemo[a]);
                invStockCheckLineList.add(invStockCheckLine);
            }
        }
        
        //保存编辑的数据
        this.invStockCheckHeadService.insertOrUpdateStockCheck(invStockCheckHead, invStockCheckLineList);
        //提示信息
        attr.addFlashAttribute("hint", "success");
        //请求参数
        attr.addAttribute("checkHeadCode", invStockCheckHead.getCheckHeadCode());
        attr.addAttribute("warehouseCode", invStockCheckHead.getWarehouseCode());
        
        return "redirect:getInvStockCheckHead";
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-08-27 14:24:02
     * @author 
     * @param invStockCheckHead
     * @return String
     *
     */
    @RequestMapping("deleteInvStockCheckHead")
    public String deleteInvStockCheckHead(InvStockCheckHead invStockCheckHead, RedirectAttributes attr) {
        //删除数据前验证数据
        if(invStockCheckHead!=null&&invStockCheckHead.getCheckHeadId()!=null&&StringUtils.isNotBlank(invStockCheckHead.getCheckHeadCode())) {
            if(invStockCheckHead.getStatus().equals("NEW")) {
                //删除数据
                this.invStockCheckHeadService.deleteDataObject(invStockCheckHead);
                //提示信息
                attr.addFlashAttribute("hint", "success");
            }else {
                //提示信息
                attr.addFlashAttribute("hint", "hint");
                attr.addFlashAttribute("alertMessage", "非新建状态的库存盘点不能删除");
            }
        }
        
        //请求参数
        attr.addAttribute("warehouseCode", invStockCheckHead.getWarehouseCode());
        
        return "redirect:getInvStockCheckHeadList";
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
    public String updateApproveStatus(String code, String approveStatus, String warehouseCode, RedirectAttributes attr) {
        
        if(StringUtils.isNotBlank(code)&&StringUtils.isNotBlank(approveStatus)) {
            //更新审核状态
            this.invStockCheckHeadService.updateApproveStatus(code, approveStatus);
            //提示信息
            attr.addFlashAttribute("hint", "success");
            attr.addAttribute("checkHeadCode", code);
            attr.addAttribute("warehouseCode", warehouseCode);
        }else {
            //提示信息
            attr.addFlashAttribute("hint", "hint");
            attr.addFlashAttribute("alertMessage", "提交或审批数据错误");
            attr.addAttribute("checkHeadCode", code);
            attr.addAttribute("warehouseCode", warehouseCode);
        }
        
        return "redirect:getInvStockCheckHead";
    }
}
