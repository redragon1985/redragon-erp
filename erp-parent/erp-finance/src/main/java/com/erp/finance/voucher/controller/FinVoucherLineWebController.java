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
import com.framework.util.JsonResultUtil;
import com.framework.util.JsonUtil;
import com.erp.finance.voucher.dao.model.FinVoucherLine;
import com.erp.finance.voucher.dao.model.FinVoucherLineCO;
import com.erp.finance.voucher.service.FinVoucherLineService;

@Controller
@RequestMapping("/web/finVoucherLine")
public class FinVoucherLineWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(FinVoucherLineWebController.class);
    
    //服务层注入
    @Autowired
    private FinVoucherLineService finVoucherLineService;
    
    @Override
    public String getExceptionRedirectURL() {
        return "redirect:getFinVoucherLineList";
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-07-24 22:19:00
     * @author 
     * @param pages
     * @param finVoucherLineCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getFinVoucherLineList")
    public String getFinVoucherLineList(Pages pages, FinVoucherLineCO finVoucherLineCO, Model model) {
        //TODO: 分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        pages.setMax(10);
        pages.setShowPageNum(7);
        pages.setStartEndPageNum(2);
        
        //分页查询数据
        List<FinVoucherLine> finVoucherLineList = this.finVoucherLineService.getDataObjects(pages, finVoucherLineCO);
        
        //页面属性设置
        model.addAttribute("finVoucherLineList", finVoucherLineList);
        model.addAttribute("pages", pages);
        
        return "finVoucherLine/finVoucherLineList";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-07-24 22:19:00
     * @author 
     * @param finVoucherLine
     * @param model
     * @return String
     *
     */
    @RequestMapping("getFinVoucherLine")
    public String getFinVoucherLine(FinVoucherLine finVoucherLine, Model model) {
        //TODO: 查询要编辑的数据
        
        //页面属性设置
        model.addAttribute("finVoucherLine", finVoucherLine);
        
        return "finVoucherLine/finVoucherLineEdit";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-07-24 22:19:00
     * @author 
     * @param finVoucherLine
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editFinVoucherLine")
    public String editFinVoucherLine(@Valid FinVoucherLine finVoucherLine, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        //参数校验
        Map<String, String> errorMap = this.validateParameters(bindingResult, model);
        if(errorMap.size()>0) {
            return "forward:getFinVoucherLine";
        }
        
        //TODO: 对当前编辑的对象初始化默认的字段
        
        //保存编辑的数据
        this.finVoucherLineService.insertOrUpdateDataObject(finVoucherLine);
        //提示信息
        attr.addFlashAttribute("hint", "success");
        
        return "redirect:getFinVoucherLineList";
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-07-24 22:19:00
     * @author 
     * @param finVoucherLine
     * @return String
     *
     */
    @RequestMapping("deleteFinVoucherLine")
    public String deleteFinVoucherLine(FinVoucherLine finVoucherLine, String voucherHeadId, RedirectAttributes attr) {
        //删除数据前验证数据
        if(finVoucherLine!=null&&finVoucherLine.getVoucherLineId()!=null) {
            //删除数据
            this.finVoucherLineService.deleteDataObject(finVoucherLine);
            //提示信息
            attr.addFlashAttribute("hint", "success");
            attr.addAttribute("voucherHeadCode", finVoucherLine.getVoucherHeadCode());
            attr.addAttribute("voucherHeadId", voucherHeadId);
        }
        
        return "redirect:/web/finVoucherHead/getFinVoucherHead";
    }
}
