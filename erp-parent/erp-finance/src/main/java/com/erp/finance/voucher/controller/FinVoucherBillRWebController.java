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

import java.util.List;
import java.util.Map;
import javax.validation.Valid;
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
import com.erp.finance.voucher.dao.model.FinVoucherBillR;
import com.erp.finance.voucher.dao.model.FinVoucherBillRCO;
import com.erp.finance.voucher.service.FinVoucherBillRService;

@Controller
@RequestMapping("/web/finVoucherBillR")
public class FinVoucherBillRWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(FinVoucherBillRWebController.class);
    
    //服务层注入
    @Autowired
    private FinVoucherBillRService finVoucherBillRService;
    
    @Override
    public String getExceptionRedirectURL() {
        // TODO Auto-generated method stub
        return null;
    }
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-07-31 12:50:25
     * @author 
     * @param pages
     * @param finVoucherBillRCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getFinVoucherBillRList")
    public String getFinVoucherBillRList(Pages pages, FinVoucherBillRCO finVoucherBillRCO, Model model) {
        //TODO: 分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        pages.setMax(10);
        pages.setShowPageNum(7);
        pages.setStartEndPageNum(2);
        
        //分页查询数据
        List<FinVoucherBillR> finVoucherBillRList = this.finVoucherBillRService.getDataObjects(pages, finVoucherBillRCO);
        
        //页面属性设置
        model.addAttribute("finVoucherBillRList", finVoucherBillRList);
        model.addAttribute("pages", pages);
        
        return "finVoucherBillR/finVoucherBillRList";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-07-31 12:50:25
     * @author 
     * @param finVoucherBillR
     * @param model
     * @return String
     *
     */
    @RequestMapping("getFinVoucherBillR")
    public String getFinVoucherBillR(FinVoucherBillR finVoucherBillR, Model model) {
        //TODO: 查询要编辑的数据
        
        //页面属性设置
        model.addAttribute("finVoucherBillR", finVoucherBillR);
        
        return "finVoucherBillR/finVoucherBillREdit";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-07-31 12:50:25
     * @author 
     * @param finVoucherBillR
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editFinVoucherBillR")
    public String editFinVoucherBillR(@Valid FinVoucherBillR finVoucherBillR, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        //参数校验
        Map<String, String> errorMap = this.validateParameters(bindingResult, model);
        if(errorMap.size()>0) {
            return "forward:getFinVoucherBillR";
        }
        
        //TODO: 对当前编辑的对象初始化默认的字段
        
        //保存编辑的数据
        this.finVoucherBillRService.insertOrUpdateDataObject(finVoucherBillR);
        //提示信息
        attr.addFlashAttribute("hint", "success");
        
        return "redirect:getFinVoucherBillRList";
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-07-31 12:50:25
     * @author 
     * @param finVoucherBillR
     * @return String
     *
     */
    @RequestMapping("deleteFinVoucherBillR")
    public String deleteFinVoucherBillR(FinVoucherBillR finVoucherBillR, RedirectAttributes attr) {
        //TODO: 删除数据前验证数据
        
        //删除数据
        this.finVoucherBillRService.deleteDataObject(finVoucherBillR);
        //提示信息
        attr.addFlashAttribute("hint", "success");
        
        return "redirect:getFinVoucherBillRList";
    }
}
