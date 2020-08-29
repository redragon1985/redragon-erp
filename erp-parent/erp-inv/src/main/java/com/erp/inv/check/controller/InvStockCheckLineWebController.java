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
import com.erp.dataset.service.DatasetCommonService;
import com.erp.inv.check.dao.model.InvStockCheckLine;
import com.erp.inv.check.dao.model.InvStockCheckLineCO;
import com.erp.inv.check.service.InvStockCheckLineService;
import com.erp.masterdata.common.service.MasterDataCommonService;

@Controller
@RequestMapping("/web/invStockCheckLine")
public class InvStockCheckLineWebController extends ControllerSupport{
    
    //日志处理
    private Logger logger = LoggerFactory.getLogger(InvStockCheckLineWebController.class);
    
    //服务层注入
    @Autowired
    private InvStockCheckLineService invStockCheckLineService;
    @Autowired
    private MasterDataCommonService masterDataCommonService;
    @Autowired
    private DatasetCommonService datasetCommonService;
    
    
    
    @Override
    public String getExceptionRedirectURL() {
        // TODO Auto-generated method stub
        return null;
    }
    
    
    
    /**
     * 
     * @description 查询数据列表
     * @date 2020-08-27 14:24:47
     * @author 
     * @param pages
     * @param invStockCheckLineCO
     * @param model
     * @return String
     *
     */
    @RequestMapping("getInvStockCheckLineList")
    public String getInvStockCheckLineList(Pages pages, InvStockCheckLineCO invStockCheckLineCO, Model model) {
        //分页查询数据
        if(pages.getPage()==0) {
            pages.setPage(1);
        }
        pages.setMax(10);
        pages.setShowPageNum(7);
        pages.setStartEndPageNum(2);
        
        //分页查询数据
        List<InvStockCheckLine> invStockCheckLineList = this.invStockCheckLineService.getDataObjects(pages, invStockCheckLineCO);
        
        //页面属性设置
        model.addAttribute("invStockCheckLineList", invStockCheckLineList);
        model.addAttribute("pages", pages);
        
        return "invStockCheckLine/invStockCheckLineList";
    }
    
    
    
    /**
     * 
     * @description 查询单条数据
     * @date 2020-08-27 14:24:47
     * @author 
     * @param invStockCheckLine
     * @param model
     * @return String
     *
     */
    @RequestMapping("getInvStockCheckLine")
    public String getInvStockCheckLine(InvStockCheckLine invStockCheckLine, Model model) {
        //TODO: 查询要编辑的数据
        
        //页面属性设置
        model.addAttribute("invStockCheckLine", invStockCheckLine);
        
        return "invStockCheckLine/invStockCheckLineEdit";
    }
    
    
    
    /**
     * 
     * @description 编辑数据
     * @date 2020-08-27 14:24:47
     * @author 
     * @param invStockCheckLine
     * @param bindingResult
     * @param model
     * @return String
     *
     */
    @RequestMapping("editInvStockCheckLine")
    public String editInvStockCheckLine(@Valid InvStockCheckLine invStockCheckLine, BindingResult bindingResult, Model model, RedirectAttributes attr) {
        //参数校验
        Map<String, String> errorMap = this.validateParameters(bindingResult, model);
        if(errorMap.size()>0) {
            return "forward:getInvStockCheckLine";
        }
        
        //TODO: 对当前编辑的对象初始化默认的字段
        
        //保存编辑的数据
        this.invStockCheckLineService.insertOrUpdateDataObject(invStockCheckLine);
        //提示信息
        attr.addFlashAttribute("hint", "success");
        
        return "redirect:getInvStockCheckLineList";
    }
    
    
    
    /**
     * 
     * @description 删除数据
     * @date 2020-08-27 14:24:47
     * @author 
     * @param invStockCheckLine
     * @return String
     *
     */
    @RequestMapping("deleteInvStockCheckLine")
    public String deleteInvStockCheckLine(InvStockCheckLine invStockCheckLine, RedirectAttributes attr) {
        //TODO: 删除数据前验证数据
        
        //删除数据
        this.invStockCheckLineService.deleteDataObject(invStockCheckLine);
        //提示信息
        attr.addFlashAttribute("hint", "success");
        
        return "redirect:getInvStockCheckLineList";
    }
}
