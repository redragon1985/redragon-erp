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
package com.erp.inv.warehouse.controller;

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
import com.framework.dao.model.Pages;
import com.erp.inv.warehouse.dao.model.InvWarehouse;
import com.erp.inv.warehouse.dao.model.InvWarehouseCO;
import com.erp.inv.warehouse.service.InvWarehouseService;

@Controller
@RequestMapping("/web/invWarehouse")
public class InvWarehouseWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(InvWarehouseWebController.class);
    
    //服务层注入
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
     * @date 2020-08-17 16:49:56
     * @author 
     * @param pages
     * @param invWarehouseCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getInvWarehouseList")
    public String getInvWarehouseList(Pages pages, InvWarehouseCO invWarehouseCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        
        //分页查询数据
        List<InvWarehouse> invWarehouseList = this.invWarehouseService.getDataObjects(pages, invWarehouseCO);
        
        //页面属性设置
        model.addAttribute("invWarehouseList", invWarehouseList);
        model.addAttribute("pages", pages);
        
        return "basic.jsp?content=invWarehouse/invWarehouseList";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-08-17 16:49:56
     * @author 
     * @param invWarehouse
     * @param model
     * @return String
     *
     */
    @RequestMapping("getInvWarehouse")
    public String getInvWarehouse(InvWarehouse invWarehouse, Model model) {
        //查询要编辑的数据
        if(invWarehouse!=null&&invWarehouse.getWarehouseId()!=null) {
            invWarehouse = this.invWarehouseService.getDataObject(invWarehouse.getWarehouseId());
        }
        
        //页面属性设置
        model.addAttribute("invWarehouse", invWarehouse);
        
        return "basic.jsp?content=invWarehouse/invWarehouseEdit";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-08-17 16:49:56
     * @author 
     * @param invWarehouse
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editInvWarehouse")
    public String editInvWarehouse(@Valid InvWarehouse invWarehouse, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        //参数校验
        Map<String, String> errorMap = this.validateParameters(bindingResult, model);
        if(errorMap.size()>0) {
            return "forward:getInvWarehouse";
        }
        
        //对当前编辑的对象初始化默认的字段
        
        //保存编辑的数据
        this.invWarehouseService.insertOrUpdateDataObject(invWarehouse);
        //提示信息
        attr.addFlashAttribute("hint", "success");
        
        return "redirect:getInvWarehouseList";
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-08-17 16:49:56
     * @author 
     * @param invWarehouse
     * @return String
     *
     */
    @RequestMapping("deleteInvWarehouse")
    public String deleteInvWarehouse(InvWarehouse invWarehouse, RedirectAttributes attr) {
        //删除数据前验证数据
        if(invWarehouse!=null&&invWarehouse.getWarehouseId()!=null&&StringUtils.isNotBlank(invWarehouse.getWarehouseCode())) {
            if(this.invWarehouseService.isExistRelateDataForInvWarehouse(invWarehouse.getWarehouseCode())) {
                //提示信息
                attr.addFlashAttribute("hint", "hint");
                attr.addFlashAttribute("alertMessage", "当前仓库存在库存，无法删除");
            }else {
                //删除数据
                this.invWarehouseService.deleteDataObject(invWarehouse);
                //提示信息
                attr.addFlashAttribute("hint", "success");
            }
        }
        
        return "redirect:getInvWarehouseList";
    }
}
